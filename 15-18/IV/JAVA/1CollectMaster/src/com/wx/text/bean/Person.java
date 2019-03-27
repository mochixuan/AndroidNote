package com.wx.text.bean;

public class Person {
	
	private int age = 22;
	
	private String name = "mochixuan";
	
	private boolean isMarriage;

	public Person() {
		
	}
	
	public Person(int age,String name) {
		this.age = age;
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private boolean isMarriage() {
		return isMarriage;
	}

	private void setMarriage(boolean isMarriage) {
		this.isMarriage = isMarriage;
	}
	
}
