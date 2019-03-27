package com.wx.realmdemo.activity;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.wx.realmdemo.R;
import com.wx.realmdemo.base.BaseActivity;
import com.wx.realmdemo.bean.User;
import com.wx.realmdemo.databinding.ActivityMain2Binding;
import com.wx.realmdemo.manager.RealmManager;

import io.realm.Realm;
import io.realm.RealmResults;

public class Main2Activity extends BaseActivity implements View.OnClickListener {

    private ActivityMain2Binding binding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    public void setDataBinding(ViewDataBinding binding) {
        this.binding = (ActivityMain2Binding) binding;
    }

    @Override
    public void initData() {
        binding.btnInsert.setOnClickListener(this);
        binding.btnDelete.setOnClickListener(this);
        binding.btnQuery.setOnClickListener(this);
        binding.btnQuery1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insert:
                insert();
                break;
            case R.id.btn_delete:
                delete();
                break;
            case R.id.btn_query:
                query();
                break;
            case R.id.btn_query1:
                query1();
                break;
        }
    }

    public void insert() {
        String name = binding.edName.getText().toString().trim();
        String age = binding.edAge.getText().toString().trim();
        boolean isMarriage = binding.checkBox.isChecked();

        if (name==null || name.isEmpty()) {
            binding.tvDetail.setText("姓名为空");
            return;
        }

        if (age==null || age.isEmpty()) {
            binding.tvDetail.setText("年龄为空");
            return;
        }

        try {
            Integer.parseInt(age);
        } catch (Exception e) {
            binding.tvDetail.setText("年龄数据非法");
            return;
        }

        final User user = new User();
        user.setAge(Integer.parseInt(age));
        user.setName(name);
        user.setMale(isMarriage);


        RealmManager.getInstance().getRealm()
                .executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealm(user);
                        binding.tvDetail.setText("插入成功:>> "+"姓名:"+user.getName() + "年龄:"+user.getAge() +"婚配:"+user.isMale());
                    }
                });

    }

    public void query() {
        String name = binding.edName.getText().toString().trim();

        RealmResults<User> mUsers;
        if (name==null || name.isEmpty()) {
            mUsers = RealmManager.getInstance().getRealm()
                    .where(User.class)
                    .findAll();
        } else {
            mUsers = RealmManager.getInstance().getRealm()
                    .where(User.class)
                    .equalTo("name",name)
                    .findAll();
        }

        if (mUsers==null || mUsers.size()==0) {
            binding.tvDetail.setText("没有数据");
        } else {
            String data = "";
            for (User user:mUsers) {
                data = data +"\n"
                        +"姓名:"+user.getName()
                        + "年龄:"+user.getAge()
                        +"婚配:"+user.isMale();
            }
            binding.tvDetail.setText(data);
        }


    }

    public void query1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    RealmManager.getInstance().getRealm()
                            .where(User.class)
                            .findAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        binding.tvDetail.setText("Realm access from incorrect thread. Realm objects can only be accessed on the thread they were created.");
    }

    public void delete() {
        String name = binding.edName.getText().toString().trim();

        final RealmResults<User> mUsers;
        if (name==null || name.isEmpty()) {
            mUsers = RealmManager.getInstance().getRealm()
                    .where(User.class)
                    .findAll();
        } else {
            mUsers = RealmManager.getInstance().getRealm()
                    .where(User.class)
                    .equalTo("name",name)
                    .findAll();
        }
        binding.tvDetail.setText("删除前:"+mUsers.size()+"条");
        RealmManager.getInstance().getRealm()
                .executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        mUsers.deleteAllFromRealm();
                    }
                });
        binding.tvDetail.setText(binding.tvDetail.getText().toString()+"\n"+"删除后:"+mUsers.size()+"条");

    }

}
