package com.wx.master1.bean;

import java.util.List;

/**
    {
        "phone" : ["12345678", "87654321"], // 数组
        "name" : "robert", // 字符串
        "age" : 100, // 数值
        "address" : { "country" : "china", "province" : "beijing" }, // 对象
        "married" : false // 布尔值
    }
*/

public class Person {

    private List<String> phone;
    private String name;
    private int age;
    private Address address;
    private String married;

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
