package com.wx.acc.sample1

import android.util.Log
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.*
import com.wx.acc.MainActivity
import com.wx.acc.R
import com.wx.acc.base.BaseActivity
import com.wx.acc.databinding.ActivitySample1Binding

class Sample1Activity : BaseActivity() , LifecycleOwner  {

    private lateinit var binding: ActivitySample1Binding
    private val mLifeCycleRegister: LifecycleRegistry = LifecycleRegistry(this);
    private lateinit var mUserViewModel: UserViewModel

    override val layoutId: Int
        get() = R.layout.activity_sample1

    override fun initData(binding: ViewDataBinding) {
        this.binding = binding as ActivitySample1Binding

        mLifeCycleRegister.addObserver(UserPresenter())

        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        mUserViewModel.liveUser.observe(this, Observer {
            Log.d(TAG,"=======User Change>>"+it.toString())
            binding.tvDesc.text = it.toString()
        })
        mUserViewModel.items.observe(this, Observer {
            binding.tvDesc.text = "长度: "+it.size
            Log.d(TAG,"=====items change>>>"+it.size);
        })
        mUserViewModel.user1.observe(this, Observer<User1> {
            Log.d(TAG,"======user1>>"+it.toString())
        })


        binding.btn1.setOnClickListener {
            mUserViewModel.liveUser.value!!.name = "tianta"
            Log.d(TAG,"=====btn1>>>"+mUserViewModel.liveUser.value!!.name);
        }

        binding.btn2.setOnClickListener {
            openActivity(MainActivity::class.java)
        }

        binding.btn3.setOnClickListener {
            mUserViewModel.items.value!!.add("1")
            Log.d(TAG,"=====btn3>>>"+mUserViewModel.items.value!!.size);
        }

        binding.btn4.setOnClickListener {
            mUserViewModel.items.value!!.removeAt(mUserViewModel.items.value!!.size-1)
        }
        binding.btn5.setOnClickListener {
            mUserViewModel.user1.age = mUserViewModel.user1.age+1
            //mUserViewModel.user1.value = User1("asa","asas",12)
            Log.d(TAG,"======btn5>>"+mUserViewModel.user1.toString());
        }
    }

    override fun getLifecycle(): Lifecycle {
        return mLifeCycleRegister
    }

    override fun onPause() {
        super.onPause()
        mUserViewModel.liveUser.value = User("xuan1","1",1)
        Log.d(TAG,"=======User onDestroy>>"+mUserViewModel.liveUser.value.toString())
    }

    override fun onStop() {
        super.onStop()
        mUserViewModel.liveUser.value = User("xuan12","12",12)
        Log.d(TAG,"=======User onDestroy>>"+mUserViewModel.liveUser.value.toString())
    }

    override fun onDestroy() {
        super.onDestroy()

        mUserViewModel.liveUser.value = User("xuan123","123",123)
        Log.d(TAG,"=======User onDestroy>>"+mUserViewModel.liveUser.value.toString())
    }

}
