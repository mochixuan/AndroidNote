package com.wx.acc.sample1;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

public class UserViewModel extends ViewModel{

    private LiveData<User> liveUser;

    public LiveData<User> getLiveUser() {
        return liveUser;
    }

    public void setLiveUser(LiveData<User> liveUser) {
        this.liveUser = liveUser;
    }

}
