package com.wx.master1.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
    [
        code: 0
         {
         "phone" : ["12345678", "87654321"], // 数组
         "name" : "robert", // 字符串
         "age" : 100, // 数值
         "address" : { "country" : "china", "province" : "beijing" }, // 对象
         "married" : false // 布尔值
         },
         {
         "phone" : ["12345678", "87654321"], // 数组
         "name" : "robert", // 字符串
         "age" : 100, // 数值
         "address" : { "country" : "china", "province" : "beijing" }, // 对象
         "married" : false // 布尔值
         }
 ]
*/

public class Person1 {

    private List<String> phone; //可以用[]型
    private String name;
    private int age;
    private Address address;
    private String married;

    //会将数据中得email_address数据名数据给emailAddress
    //@SerializedName("email_address")
    //把"email"或"email_address"传个emailAddress
    @SerializedName(value = "emailAddress",alternate = {"email","email_address"})
    private String emailAddress;

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getMarried() {
        return married;
    }

    public void setMarried(String married) {
        this.married = married;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    static class Address {

        private String country;
        private String province;

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }
    }

}
