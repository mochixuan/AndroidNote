package com.ooziz.myapplication;

import android.Manifest;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattServer;
import android.bluetooth.BluetoothGattService;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.callback.BleIndicateCallback;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.scan.BleScanRuleConfig;
import com.ooziz.myapplication.adapter.ScanAdapter;
import com.ooziz.myapplication.base.BaseActivity;
import com.ooziz.myapplication.databinding.ActivityMainBinding;
import com.ooziz.myapplication.listener.OnItemClickListener;
import com.ooziz.myapplication.utils.UUIDUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.security.Permission;
import java.util.List;
import java.util.UUID;

import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private ScanAdapter mAdapter;
    private BleDevice mConnBleDevice;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setDataBinding(ViewDataBinding binding) {
        this.binding = (ActivityMainBinding) binding;
    }

    @Override
    public void initData() {
        requestPermission();

        //初始化及配置
        BleManager.getInstance().init(getApplication());
        BleManager.getInstance()
                .enableLog(true)
                .setReConnectCount(1,5000) //重连次数和重连时间间隔
                .setOperateTimeout(5000);   //操作超时时间

        if (BleManager.getInstance().isSupportBle()) {
            //是否已经打开
            if (!BleManager.getInstance().isBlueEnable()) {
                BleManager.getInstance().enableBluetooth();
            }
        } else {
            showToast("该设备不支持蓝牙");
        }

        //扫描
        binding.btnScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BleScanRuleConfig scanRuleConfig = new BleScanRuleConfig.Builder()
                        //.setDeviceName(true,"OOZIZ")
                        .setScanTimeOut(4000)
                        .build();
                BleManager.getInstance().initScanRule(scanRuleConfig);

                //监听
                BleManager.getInstance().scan(mBleScanCallback);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new ScanAdapter();
        binding.recyclerView.setAdapter(mAdapter);

        //连接
        mAdapter.setOnItemClickListener(new OnItemClickListener<BleDevice>() {
            @Override
            public void onItemClick(View view, BleDevice data, int index) {
                BleManager.getInstance().connect(data,mBleGattCallback);
            }
        });

        binding.btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mConnBleDevice != null) {
                    BleManager.getInstance().notify(
                            mConnBleDevice, UUIDUtils.UUID_LOST_SERVICE,
                            UUIDUtils.UUID_LOST_ENABLE, mBleNotifyCallback);
                }
            }
        });

        //断开
        binding.btnDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mConnBleDevice != null) {
                    BleManager.getInstance().disconnect(mConnBleDevice);
                }
            }
        });

        binding.btnRing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mConnBleDevice != null) {
                    startRing();
                }
            }
        });

        binding.btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mConnBleDevice != null) {
                    stopRing();
                }
            }
        });

    }

    private BleScanCallback mBleScanCallback = new BleScanCallback() {
        //主线程-本次扫描时段内所有被扫描且过滤后的设备集合。
        @Override
        public void onScanFinished(List<BleDevice> scanResultList) {
            mAdapter.replaceAllData(scanResultList,false);
        }

        //工作线程-扫描过程中所有被扫描到的结果回调。
        @Override
        public void onLeScan(BleDevice bleDevice) {
            super.onLeScan(bleDevice);
        }

        //会回到主线程，参数表示本次扫描动作是否开启成功。由于蓝牙没有打开，上一次扫描没有结束等原因，会造成扫描开启失败。
        @Override
        public void onScanStarted(boolean success) {

        }

        //主线程-扫描过程中的所有过滤后的结果回调。
        @Override
        public void onScanning(BleDevice bleDevice) {

        }
    };

    private BleGattCallback mBleGattCallback = new BleGattCallback() {
        @Override
        public void onStartConnect() {

        }

        @Override
        public void onConnectFail(BleDevice bleDevice, BleException exception) {
            showToast("连接失败");
            mConnBleDevice = null;
        }

        @Override
        public void onConnectSuccess(BleDevice bleDevice, BluetoothGatt gatt, int status) {
            showToast("连接成功: "+bleDevice.getName());
            mConnBleDevice = bleDevice;
        }

        //连接断开，特指连接后再断开的情况。在这里可以监控设备的连接状态，一旦连接断开，可以根据自身情况考虑对BleDevice对象进行重连操作。需要注意的是，断开和重连之间最好间隔一段时间，否则可能会出现长时间连接不上的情况。
        @Override
        public void onDisConnected(boolean isActiveDisConnected, BleDevice device, BluetoothGatt gatt, int status) {
            showToast("连接断开: "+device.getName());
            mConnBleDevice = null;
        }
    };

    private BleNotifyCallback mBleNotifyCallback = new BleNotifyCallback() {
        @Override
        public void onNotifySuccess() {
            // 打开通知操作成功
            Log.d("onNotifySuccess","==========>>"+Thread.currentThread());
            showToast("打开通知操作成功");
        }

        @Override
        public void onNotifyFailure(BleException exception) {
            // 打开通知操作失败
            Log.d("onNotifyFailure","==========>>"+exception.toString()+"  "+Thread.currentThread());
            showToast("打开通知操作失败");
        }

        @Override
        public void onCharacteristicChanged(byte[] data) {
            // 打开通知后，设备发过来的数据将在这里出现
            Log.d("TAG","========="+data.length);
        }
    };

    private BleIndicateCallback mBleIndicateCallback = new BleIndicateCallback(){

        @Override
        public void onIndicateSuccess() {
            // 打开通知操作成功
            Log.d("onIndicateSuccess","==========>>"+Thread.currentThread());
            showToast("打开通知操作成功");
        }

        @Override
        public void onIndicateFailure(BleException exception) {
            // 打开通知操作失败
            Log.d("onIndicateFailure","==========>>"+exception.toString()+"  "+Thread.currentThread());
            showToast("打开通知操作失败");
        }

        @Override
        public void onCharacteristicChanged(byte[] data) {

        }
    };

    private String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    private void requestPermission() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
                ).subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean isAllow) throws Exception {
                        if (!isAllow) {
                            showToast("请授予蓝牙权限");
                        }
                    }
                });
    }

    public void startRing() {
        byte[] data = new byte[]{(byte) 0xaa,0x08};
        BleManager.getInstance().write(
                mConnBleDevice,
                UUIDUtils.UUID_LOST_SERVICE,
                UUIDUtils.UUID_LOST_WRITE,
                data,
                new BleWriteCallback() {
                    @Override
                    public void onWriteSuccess(int current, int total, byte[] justWrite) {
                        // 发送数据到设备成功（分包发送的情况下，可以通过方法中返回的参数可以查看发送进度）
                        Log.d("onWriteSuccess","==========>>"+Thread.currentThread());
                        showToast("发送数据到设备成功");
                    }

                    @Override
                    public void onWriteFailure(BleException exception) {
                        // 发送数据到设备失败
                        Log.d("onWriteFailure","==========>>"+exception.getDescription()+Thread.currentThread());
                        showToast("发送数据到设备失败");
                    }
                }
        );
    }

    public void stopRing() {
        byte[] data = new byte[]{-86, 6, 0};
        data[2] = (byte)(data[0] + data[1]);
        BleManager.getInstance().write(
                mConnBleDevice,
                UUIDUtils.UUID_LOST_SERVICE,
                UUIDUtils.UUID_LOST_WRITE,
                data,
                new BleWriteCallback() {
                    @Override
                    public void onWriteSuccess(int current, int total, byte[] justWrite) {
                        // 发送数据到设备成功（分包发送的情况下，可以通过方法中返回的参数可以查看发送进度）
                        showToast("发送数据到设备成功");
                    }

                    @Override
                    public void onWriteFailure(BleException exception) {
                        // 发送数据到设备失败
                        showToast("发送数据到设备失败");
                    }
                }
        );
    }

}
