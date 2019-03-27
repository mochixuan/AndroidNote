package com.wx.daggerdemo.recycler;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.trello.rxlifecycle2.components.RxActivity;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.wx.daggerdemo.R;
import com.wx.daggerdemo.adapter.PersonAdapter;
import com.wx.daggerdemo.bean.PersonBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Refresh2Activity extends RxAppCompatActivity {

    @BindView(R.id.recycler_view)
    protected RecyclerView mRecyclerView;

    @BindView(R.id.swipe_refresh)
    protected SwipeRefreshLayout mSwipeRefresh;

    private PersonAdapter mPersonAdapter;

    private String IMG_URL = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1731874116,1076114407&fm=27&gp=0.jpg";
    private String DESC_TEXT = "learning and practice redux,react-redux,redux-saga,redux-persist,redux-thunk and so on";

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh2);
        ButterKnife.bind(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        mPersonAdapter = new PersonAdapter(R.layout.item_person,this);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mPersonAdapter);
        mPersonAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        mPersonAdapter.isFirstOnly(false);

//        Observable.interval(1,TimeUnit.SECONDS)
//                .compose(this.<Long>bindToLifecycle())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.newThread())
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        if (aLong%3 == 2) {
//                            mPersonAdapter.remove(1);
//                        } else {
//                            addData(1);
//                        }
//                    }
//                });

        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // mSwipeRefresh.setRefreshing(false);
                Observable.timer(1,TimeUnit.SECONDS)
                        .compose(Refresh2Activity.this.<Long>bindToLifecycle())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                addData(5,true);
                                mSwipeRefresh.setRefreshing(false);
                            }
                        });
            }
        });

        mPersonAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener(){
            @Override
            public void onLoadMoreRequested() {
                Observable.timer(1,TimeUnit.SECONDS)
                        .compose(Refresh2Activity.this.<Long>bindToLifecycle())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                addData(5,false);
                                mPersonAdapter.loadMoreComplete();
                            }
                        });
            }
        },mRecyclerView);
    }

    private void addData(final int length,boolean isTop) {
        List<PersonBean> personBeans = new ArrayList<>();
        for (int i = 0 ; i < length ; i++) {
            personBeans.add(new PersonBean(IMG_URL,"mochixuan"+mPersonAdapter.getItemCount()+(isTop ? "上" : "下"),DESC_TEXT));
        }
        mPersonAdapter.addData(personBeans);
    }

}
