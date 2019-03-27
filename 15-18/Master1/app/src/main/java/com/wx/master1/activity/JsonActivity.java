package com.wx.master1.activity;

import android.databinding.ViewDataBinding;
import android.util.Log;
import android.view.View;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.wx.master1.R;
import com.wx.master1.base.BaseActivity;
import com.wx.master1.bean.Person;
import com.wx.master1.bean.Person1;
import com.wx.master1.bean.Person2;
import com.wx.master1.bean.Person3;
import com.wx.master1.databinding.ActivityJsonBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonActivity extends BaseActivity {

    private ActivityJsonBinding binding;
    private final String TAG = this.getClass().getSimpleName();

    @Override
    public int getLayoutId() {
        return R.layout.activity_json;
    }

    @Override
    public void setDataBinding(ViewDataBinding binding) {
        this.binding = (ActivityJsonBinding) binding;
    }

    @Override
    public void initData() {
        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"==============>>"+analysisJson1(getJsonString1()).getPhone().get(0));
            }
        });
        binding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d(TAG,"=================>>"+gsonAnalysis1(getJsonString1()).getName());
                //Log.d(TAG,"=================>>"+gsonAnalysis2(getJsonString2()).getEmailAddress());
                //Log.d(TAG,"=================>>"+gsonAnalysis3(getJsonString2()).getEmailAddress()+"  "+gsonAnalysis3(getJsonString2()).getPhone()[0]);
                Log.d(TAG,"=================>>"+gsonAnalysis4(getJsonString2()).getName());
            }
        });
        binding.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    /**
    * getType和optType api的使用
        getType可以将要获取的键的值转换为指定的类型，如果无法转换或没有值则抛出JSONException
        optType也是将要获取的键的值转换为指定的类型，无法转换或没有值时返回用户提供或这默认提供的值
    * */

    /**
    {
        "phone" : ["12345678", "87654321"], // 数组
        "name" : "robert", // 字符串
        "age" : 100, // 数值
        "address" : { "country" : "china", "province" : "beijing" }, // 对象
        "married" : false // 布尔值
    }
    */
    private String getJsonString1() {
        JSONObject person = new JSONObject();
        try {
            JSONArray phone = new JSONArray();
            phone.put("12345678")
                    .put("87654321");
            JSONObject address = new JSONObject();
            address.put("country","china")
                    .put("province","beijing");
            person.put("phone",phone)
                    .put("name","robert")
                    .put("age",22)
                    .put("address",address)
                    .put("married",false);
        } catch (JSONException e) {
            e.printStackTrace();
            person = new JSONObject();
        }
        return person.toString();
    }

    private String getJsonString2() {
        JSONObject person = new JSONObject();
        try {
            JSONArray phone = new JSONArray();
            phone.put("12345678")
                    .put("87654321");
            JSONObject address = new JSONObject();
            address.put("country","china")
                    .put("province","beijing");
            person.put("phone",phone)
                    .put("name","robert")
                    .put("age",22)
                    .put("address",address)
                    .put("married",false)
                    .put("email_address","12306@qq.com")
            ;
        } catch (JSONException e) {
            e.printStackTrace();
            person = new JSONObject();
        }
        return person.toString();
    }


    private Person analysisJson1(String data) {
        Person person = new Person();
        try {
            JSONObject jsonPerson = new JSONObject(data);

            List<String> phones = new ArrayList<>();
            JSONArray phone = jsonPerson.optJSONArray("phone");
            for (int i = 0; i<phone.length();i++) {
                phones.add(phone.optString(i));
            }
            person.setPhone(phones);

            //不写了

            return person;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String readAssetJson2() {
        InputStream inputStream = null;
        BufferedReader reader = null;
        StringBuffer buffer = new StringBuffer();
        try {
            inputStream = getAssets().open("json1.txt");
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String tempLineData = null;
            while ((tempLineData = reader.readLine()) != null) {
                buffer.append(tempLineData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }

    private Person gsonAnalysis1(String data) {
        Gson gson = new Gson();
        Person person  = gson.fromJson(data,Person.class);
        return person;
    }

    private Person1 gsonAnalysis2(String data) {
        Gson gson = new Gson();
        Person1 person1  = gson.fromJson(data,Person1.class);
        return person1;
    }

    /*
    * http://blog.csdn.net/chenyannan0617/article/details/51912789
    * 改变变量名方法二
    * */
    private Person2 gsonAnalysis3(String data) {
        Gson gson = new GsonBuilder()
                .setFieldNamingStrategy(new FieldNamingStrategy() {
                    @Override
                    public String translateName(Field f) {
                        if ("emailAddress".equals(f.getName())) {
                            return "email_address";
                        }
                        return f.getName();
                    }
                })
                .create();
        Person2 person2  = gson.fromJson(data,Person2.class);
        return person2;
    }

    ////注意这个注解在new Gson()时候是不起作用的,这个注解要想起作用是需要和GsonBuilder配合的
    private Person3 gsonAnalysis4(String data) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        Person3 person3  = gson.fromJson(data,Person3.class);
        return person3;
    }

    private void gsonBuilder(String data) {

        Gson gson = new GsonBuilder()
                //没有值得时候赋值为空
                .serializeNulls()
                .create();
        //减少重复定义
        Type type = new TypeToken<List<Person>>(){}.getType();
        List<Person> person = gson.fromJson(data,type);

        //输出
        gson.toJson(person,System.out);

    }

}
