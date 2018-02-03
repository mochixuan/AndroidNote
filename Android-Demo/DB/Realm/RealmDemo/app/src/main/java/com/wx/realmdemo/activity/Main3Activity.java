package com.wx.realmdemo.activity;

import android.databinding.ViewDataBinding;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wx.realmdemo.R;
import com.wx.realmdemo.base.BaseActivity;
import com.wx.realmdemo.bean.Course;
import com.wx.realmdemo.bean.Student;
import com.wx.realmdemo.databinding.ActivityMain3Binding;
import com.wx.realmdemo.manager.RealmManager;

import io.realm.Realm;
import io.realm.RealmList;

public class Main3Activity extends BaseActivity implements View.OnClickListener{

    private ActivityMain3Binding binding;
    private RealmList<Course> mCourses;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main3;
    }

    @Override
    public void setDataBinding(ViewDataBinding binding) {
        this.binding = (ActivityMain3Binding) binding;
    }

    @Override
    public void initData() {
        binding.btnInsert.setOnClickListener(this);
        binding.btnAddCourse.setOnClickListener(this);
        binding.btnQuery.setOnClickListener(this);
        binding.btnInsertJson.setOnClickListener(this);

        mCourses = new RealmList<>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insert:
                insert();
                break;
            case R.id.btn_add_course:
                addCourse();
                break;
            case R.id.btn_query:
                query();
                break;
            case R.id.btn_insert_json:
                insertJsonData();
                break;
        }
    }

    public void addCourse() {
        String name = binding.edCourseName.getText().toString().trim();
        int lession = Integer.parseInt(binding.edCourseNum.getText().toString().trim());
        Course course = new Course();
        course.setCourse(name);
        course.setLesson(lession);
        mCourses.add(course);
    }

    public void insert() {
        String name = binding.edStuName.getText().toString().trim();
        int num = Integer.parseInt(binding.edStuNum.getText().toString().trim());
        final Student student = new Student();
        student.setName(name);
        student.setNum(num);
        student.setCourses(mCourses);
        RealmManager.getInstance().getRealm()
                .executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealmOrUpdate(student);
                        mCourses.clear();
                    }
                });
    }

    public void query() {

        String num = binding.edStuNum.getText().toString().trim();

        if (num==null || num.isEmpty()) {
            binding.tvDetail.setText("请输入学号");
        } else {
            int lession = Integer.parseInt(binding.edStuNum.getText().toString().trim());
            Student student = RealmManager.getInstance().getRealm()
                                .where(Student.class)
                                .equalTo("num",lession)
                                .findFirst();

            if (student==null) {
                binding.tvDetail.setText("没有这个学生");
            } else {

                String data = "";
                if (student.getCourses() != null && student.getCourses().size()!=0){
                    for (Course course:student.getCourses()) {
                        data = data +"\n" +"课程名:"+course.getCourse()+" 课时:"+course.getLesson();
                    }
                }

                binding.tvDetail.setText("姓名:"+student.getName()+"学号:"+student.getNum()+data);
            }
        }

    }

    public void insertJsonData() {
        RealmList<Course> courses = new RealmList<>();
        courses.add(new Course("法语",12));
        courses.add(new Course("德语",11));
        Student student = new Student();
        student.setName("json数据");
        student.setNum(1);
        student.setCourses(courses);
        Gson gson = new Gson();
        final String jsonData = gson.toJson(student,Student.class);
        RealmManager.getInstance().getRealm()
                .executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.createOrUpdateObjectFromJson(Student.class,jsonData); //可以直接调用传入JsonObject可用看源码
                        Toast.makeText(Main3Activity.this,"插入成功",Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
