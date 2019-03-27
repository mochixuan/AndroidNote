package com.ppdl.myapplication.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.NotNull;

import static android.R.attr.id;

@Entity
public class dataBean {

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String json;

    @Generated(hash = 66198133)
    public dataBean(Long id, @NotNull String json) {
        this.id = id;
        this.json = json;
    }

    @Generated(hash = 238468224)
    public dataBean() {
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getJson() {
        return this.json;
    }
    public void setJson(String json) {
        this.json = json;
    }

}
