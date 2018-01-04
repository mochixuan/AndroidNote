package com.wx.text.reflect;

import java.lang.reflect.Method;

public class reflest1 {

	
	public static void main(String[] args) {
		//test1();
		test2();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void test1() {
		try {
			Class clazz1 = Class.forName("com.wx.text.bean.Person");
			Object obj1 = clazz1.newInstance();
			Method method1 = clazz1.getMethod("getAge");
			
			int age1  = (int) method1.invoke(obj1);
			
			Method method11 = clazz1.getMethod("setAge",int.class);
			method11.invoke(obj1, 100);
			
			int age11 = (int) method1.invoke(obj1);
			
			Method method111 = clazz1.getDeclaredMethod("setMarriage", boolean.class);
			method111.setAccessible(true); //设置为可访问
			method111.invoke(obj1, true);
			
			Method method112 = clazz1.getDeclaredMethod("isMarriage");
			method112.setAccessible(true);
			boolean isMarriage = (boolean) method112.invoke(obj1);
			
			System.out.println(age1+"   "+age11+"   "+isMarriage);
			System.out.println(method1.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void test2() {
		
	}
	
}
