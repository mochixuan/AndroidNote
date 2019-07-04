package com.wx.acc.sample1;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class UserViewModel extends ViewModel implements LifecycleObserver {

    private MutableLiveData<User> liveUser;
    private MutableLiveData<List<String>> items;
    private User1 user1;

    public MutableLiveData<User> getLiveUser() {
        if (liveUser == null) {
            liveUser = new MutableLiveData<>();
            liveUser.setValue(new User("mochixuan","10101010101",20));
        }
        return liveUser;
    }

    public void setLiveUser(MutableLiveData<User> liveUser) {
        this.liveUser = liveUser;
    }


    public MutableLiveData<List<String>> getItems() {
        if (items == null) {
            items = new MutableLiveData<>();
            items.setValue(new ArrayList<String>());
        }
        return items;
    }

    public void setItems(MutableLiveData<List<String>> items) {
        this.items = items;
    }

    public User1 getUser1() {
        if (user1 == null) {
            user1 = new User1("init User1","1",1);
        }
        return user1;
    }

    public void setUser1(User1 user1) {
        this.user1 = user1;
    }
}
