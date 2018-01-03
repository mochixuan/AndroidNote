package com.wx.text;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author wangxuan
 *
 */
public class test {
	
	public static void main(String[] args) throws Exception {
		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(1);
		array.getClass().getMethod("add", Object.class).invoke(array, "wang");
		for (int i = 0; i<array.size();i++) {
			System.out.println(array.get(i));
		}
		Object a = 1.0d;
		System.out.println(a.getClass());
		Integer i1 = 0;
		System.out.println(i1.getClass());
		System.out.println(show("wang1"));
		
		
		Collection<String> test1 = new ArrayList<String>();
	}
	
	public static <T> T show(T one) {
		return one;
	}
	
}
