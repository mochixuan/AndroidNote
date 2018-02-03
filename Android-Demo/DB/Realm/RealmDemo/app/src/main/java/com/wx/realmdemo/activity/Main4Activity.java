package com.wx.realmdemo.activity;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.wx.realmdemo.R;
import com.wx.realmdemo.base.BaseActivity;
import com.wx.realmdemo.bean.Course;
import com.wx.realmdemo.bean.Student;
import com.wx.realmdemo.databinding.ActivityMain4Binding;
import com.wx.realmdemo.manager.RealmManager;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 生成数据和提取数据时可以使用别的线程先生成这样快一点
 * */
public class Main4Activity extends BaseActivity implements View.OnClickListener {

    private ActivityMain4Binding binding;
    private RealmResults<Student> students;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main4;
    }

    @Override
    public void setDataBinding(ViewDataBinding binding) {
        this.binding = (ActivityMain4Binding) binding;
    }

    @Override
    public void initData() {
        binding.btnInsert.setOnClickListener(this);
        binding.btnRxInsert.setOnClickListener(this);
        binding.btnDelete.setOnClickListener(this);
        binding.btnQuery.setOnClickListener(this);
        binding.btnRxQuery.setOnClickListener(this);
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
            case R.id.btn_rx_query:
                rxQuery();
                break;
            case R.id.btn_rx_insert:
                rxInsert();
                break;
        }
    }

    private String timer;
    public void insert() {
        String data = binding.edDataNum.getText().toString().trim();
        if (data==null ||data.isEmpty()) {
            binding.tvDetail.setText("请输入插入的数量");
            return;
        }
        final int num = Integer.parseInt(data);
        RealmManager.getInstance().getRealm()
                .executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        timer="开始时间:"+System.currentTimeMillis();
                        RealmList<Student> mDataStudents = new RealmList<>();
                        for (int i = 0; i < num; i++) {     //可用先在别的线程生成这样会非常快 这里慢不是realm的原因是生成数据的原因 可用用RxJava试一试
                            RealmList<Course> courses = new RealmList<>();
                            courses.add(new Course("中文", i));
                            courses.add(new Course("英语", i));
                            Student student = new Student();
                            student.setName("哪里有未来");
                            student.setNum(i);
                            student.setCourses(courses);
                            mDataStudents.add(student);
                        }
                        realm.copyToRealmOrUpdate(mDataStudents);
                    }
                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        timer=timer+"\n结束时间:"+System.currentTimeMillis();
                        binding.tvDetail.setText(timer);
                    }
                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        timer = "存储错误:"+error.getMessage();
                        binding.tvDetail.setText(timer);
                    }
                });
    }

    public void rxInsert() {
        String data = binding.edDataNum.getText().toString().trim();
        if (data==null ||data.isEmpty()) {
            binding.tvDetail.setText("请输入插入的数量");
            return;
        }
        final int num = Integer.parseInt(data);
        Observable.create(new Observable.OnSubscribe<RealmList<Student>>() {
            @Override
            public void call(Subscriber<? super RealmList<Student>> subscriber) {
                RealmList<Student> mDataStudents = new RealmList<>();
                timer="数据开始生成:"+System.currentTimeMillis();
                for (int i = 0; i < num; i++) {
                    RealmList<Course> courses = new RealmList<>();
                    courses.add(new Course("中文", i));
                    courses.add(new Course("英语", i));
                    Student student = new Student();
                    student.setName("哪里有未来");
                    student.setNum(i);
                    student.setCourses(courses);
                    mDataStudents.add(student);
                }
                timer=timer+"\n数据生成成功:"+System.currentTimeMillis();
                subscriber.onNext(mDataStudents);
            }
        }).subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<RealmList<Student>>() {
            @Override
            public void call(final RealmList<Student> students) {
                RealmManager.getInstance().getRealm().executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        timer = timer + "\n数据开始插入:" + System.currentTimeMillis();
                        realm.copyToRealmOrUpdate(students);
                    }
                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        timer = timer + "\n数据插入成功:" + System.currentTimeMillis();
                        binding.tvDetail.setText(timer);
                    }
                });
            }
        });
    }

    public void query() {
        if (students==null) {
            students = RealmManager.getInstance()
                    .getRealm().where(Student.class)
                    .findAllAsync();
            students.addChangeListener(changeListener);
        } else {
            if (students.isLoaded()) {
                binding.tvDetail.setText("数据大小:"+students.size());
            } else {
                binding.tvDetail.setText("数据处理中");
            }
        }
    }

    long dataTimer;
    public void rxQuery() {
        dataTimer = System.currentTimeMillis();
        RealmManager.getInstance().getRealm()
                .where(Student.class)
                .findAllAsync()
                .asObservable()
                .filter(new Func1<RealmResults<Student>, Boolean>() {
                    @Override
                    public Boolean call(RealmResults<Student> results) {
                        return results.isLoaded();
                    }
                })
                /*.flatMap(new Func1<RealmResults<Student>, Observable<Student>>() {
                    @Override
                    public Observable<Student> call(RealmResults<Student> results) {
                        return Observable.from(results);
                    }
                })
                .filter(new Func1<Student, Boolean>() {
                    @Override
                    public Boolean call(Student student) {
                        return student.getNum()==0?true:(student.getNum()%2==0);        //取偶数 不会调用Oncomplete坑啊
                    }
                })*/
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RealmResults<Student>>() {
                    @Override
                    public void call(RealmResults<Student> results) {
                        dataTimer = System.currentTimeMillis() - dataTimer;
                        binding.tvDetail.setText("时间:"+dataTimer+"    数据数量:"+results.size());
                    }
                });
    }

    public void delete() {
       if (students!=null) {
           deleteData();
       } else {
           binding.tvDetail.setText("请先查询下");
       }
    }

    private void deleteData() {
        RealmManager.getInstance().getRealm()
                .executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        boolean isDeleteAll =  binding.radioGroup.getCheckedRadioButtonId()==R.id.radio_all;
                        if (isDeleteAll) {
                            students.deleteAllFromRealm();
                        } else {
                            Observable.create(new Observable.OnSubscribe<RealmList<Student>>() {
                                @Override
                                public void call(Subscriber<? super RealmList<Student>> subscriber) {
                                    int count = students.size()==0?0:students.size()/2;
                                    RealmList<Student> mdeleteData = new RealmList<>();
                                    for (int i =0;i<count;i++) {
                                        mdeleteData.add(students.get(i));
                                    }
                                    subscriber.onNext(mdeleteData);
                                }
                            }).subscribeOn(Schedulers.newThread())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Action1<RealmList<Student>>() {
                                        @Override
                                        public void call(RealmList<Student> students) {
                                            students.deleteAllFromRealm();
                                        }
                                    });
                        }
                    }
                });
    }

    private RealmChangeListener changeListener = new RealmChangeListener<RealmResults<Student>>() {
        @Override
        public void onChange(final RealmResults<Student> results) {
            binding.tvDetail.setText("数据大小:"+students.size()+"    对比:"+results.size());
            if (students != null) {
                students.removeAllChangeListeners();        //防止内存泄露
            }
        }
    };


    @Override
    protected void onStop() {
        super.onStop();
        if (students != null) {
            students.removeAllChangeListeners();        //防止内存泄露
        }
    }
}
