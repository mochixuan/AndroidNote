package com.wx.text.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.TreeMap;

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
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("语文: 99");
		list.add("数学: 98");
		list.add("英语: 100");
		list.remove(0);
		
		int[] a1 = {1,2,3,4,5,6};
		int[] b1 = {11,22,33,44,55,66,77,88};
		
		System.arraycopy(a1, 1, b1, 0, 2);
		
		String data1 = "";
		for (int i=0;i<b1.length;i++) {
			data1 = data1 + "  "+b1[i];
		}
		System.out.println(data1);
		
		LinkedList<String> list1 = new LinkedList<String>();
		list.add("语文: 1");
		list.add("数学: 2");
		list.add("英语: 3");
		
		TreeMap<Integer, String> tmap = new TreeMap<Integer, String>();
		tmap.put(1, "语文1");
		tmap.put(3, "英语3");
		tmap.put(2, "数学2");
		tmap.put(4, "政治4");
		tmap.put(5, "历史5");
		tmap.put(6, "地理6");
		tmap.put(7, "生物7");
		tmap.put(8, "化学8");
		for(Entry<Integer, String> entry : tmap.entrySet()) {
		    System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	
	}
	
	public static <T> T show(T one) {
		return one;
	}
	
}
