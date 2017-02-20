package com.ppdl.rxjava;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ppdl.rxjava.FlatMap.Course;
import com.ppdl.rxjava.FlatMap.Student;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class SecondActivity extends BaseActivty implements View.OnClickListener{

    private TextView tvReceiver;
    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    public void InitView() {
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        tvReceiver = (TextView) findViewById(R.id.tv_receiver);
    }

    @Override
    public void InitData() {
        flatTestData();
        mData = new ArrayList<>();
        mData.add("ABA");
        mData.add("BBA");
        mData.add("CBA");
        mData.add("DBA");
        mData.add("EBA");
        mData.add("FBA");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                observable1.subscribe(subscriber1);
                break;
            case R.id.btn2:
                printString();
                break;
            case R.id.btn3:
                ThreadChange();
                break;
            case R.id.btn4:
                TestFlatMap();
                break;
            case R.id.btn5:
                filter();
                break;
            case R.id.btn6:
                take();
                break;
        }
    }

    public void setTvReceiver(String data,int num){
        tvReceiver.setText("接收到按钮"+num+":"+data);
    }

    public void setTvReceivers(String data){
        if(tvReceiver.getText().toString().isEmpty()){
            tvReceiver.setText(data);
        }else{
            tvReceiver.setText(tvReceiver.getText().toString()+" : "+data);
        }

    }

    @Override
    protected void onDestroy() {

        /*if(subscriber1.isUnsubscribed()){                 //解除订阅
            subscriber1.unsubscribe();
        }*/

        super.onDestroy();
    }

    /*------------------------1.start--------------------------*/
    //发送--事件源
    rx.Observable<String> observable1= rx.Observable.create(new rx.Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("hello world !");
            subscriber.onCompleted();
        }
    });
    //接收--观察者
    Subscriber<String> subscriber1=new Subscriber<String>() {

        /*@Override
        public void onStart() {
            super.onStart();
        }*/

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            setTvReceiver(e.toString(),1);
        }

        @Override
        public void onNext(String s) {
            setTvReceiver(s,1);
        }
    };


    /*------------------------2.start--------------------------*/
    public void printString(){
        String[] names={"江西","深圳","北京"};
        rx.Observable.from(names).subscribe(new Action1<String>() {     /*一个Action1相当于onNext()*/
            @Override
            public void call(String s) {
                setTvReceivers(s);
            }
        });
    }


    /*------------------------3.start--------------------------*/
    //简单线程处理
    public void ThreadChange(){
        rx.Observable.just("江西","深圳","北京","库里")
                .subscribeOn(Schedulers.io())                      //发布者线程
                .observeOn(AndroidSchedulers.mainThread())         //接收者线程Schedulers.io()错误
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        setTvReceivers(s);
                    }
                });

        /*Observable.just("asas","asas")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return Integer.parseInt(s);
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {

                    }
                });*/
    }


    /*------------------------4.start--------------------------*/
    //FlatMap使用
    public void TestFlatMap(){
        rx.Observable.from(getAllStudent())
                .flatMap(new Func1<Student, rx.Observable<Course>>() {
                    @Override
                    public rx.Observable<Course> call(Student student) {
                        //System.out.println("--------:1");                     两次一六次2
                        return rx.Observable.from(student.getCourses());
                    }
                }).subscribe(new Action1<Course>() {
            @Override
            public void call(Course course) {
                //System.out.println("--------:2");
                setTvReceivers(course.getCourseName());
            }
        });
    }

    private Student student1;
    private Student student2;
    public void flatTestData(){
        student1=new Student();
        student2=new Student();

        List<Course> mCourses1=new ArrayList<>();
        mCourses1.add(new Course("语文1"));
        mCourses1.add(new Course("数学1"));
        mCourses1.add(new Course("计算机1"));

        List<Course> mCourses2=new ArrayList<>();
        mCourses2.add(new Course("语文2"));
        mCourses2.add(new Course("数学2"));
        mCourses2.add(new Course("计算机2"));


        student1.setName("wang1");
        student1.setAge("11");
        student1.setCourses(mCourses1);

        student2.setName("wang2");
        student2.setAge("22");
        student2.setCourses(mCourses2);
    }

    public List<Student> getAllStudent(){
        List<Student> mStudent=new ArrayList<>();
        mStudent.add(student1);
        mStudent.add(student2);
        return mStudent;
    }



    /*------------------------5.start--------------------------*/
    public void filter(){

        rx.Observable.from(mData)
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return s.startsWith("B");  //显示B开头
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                setTvReceivers(s);
            }
        });

    }

    /*------------------------6.start--------------------------*/

    public void take(){
        rx.Observable.from(mData)
                .take(3)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        setTvReceivers(s);
                    }
                });
    }

}
