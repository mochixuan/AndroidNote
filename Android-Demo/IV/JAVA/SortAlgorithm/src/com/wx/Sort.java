package com.wx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort {

	// 1. Student实现Comparable接口：
	// 2. 给排序方法传递一个Comparator参数：

	static class Person implements Comparable<Person>{

		String name;
		int age;
		List<String> childrens;

		public Person() {

		}
		public Person(String name, int age, List<String> childrens) {
			this.name = name;
			this.age = age;
			this.childrens = childrens;
		}
		private String getName() {
			return name;
		}
		private void setName(String name) {
			this.name = name;
		}
		private int getAge() {
			return age;
		}
		private void setAge(int age) {
			this.age = age;
		}
		private List<String> getChildrens() {
			return childrens;
		}
		private void setChildrens(List<String> childrens) {
			this.childrens = childrens;
		}

		@Override
		public int compareTo(Person person) {
			//System.out.println("=======>>"+this.age+"   "+person.age);
			return (this.age - person.age);
		}

	}

	static class Person1 {

		String name;
		int age;
		List<String> childrens;

		public Person1() {

		}
		public Person1(String name, int age, List<String> childrens) {
			this.name = name;
			this.age = age;
			this.childrens = childrens;
		}
		private String getName() {
			return name;
		}
		private void setName(String name) {
			this.name = name;
		}
		private int getAge() {
			return age;
		}
		private void setAge(int age) {
			this.age = age;
		}
		private List<String> getChildrens() {
			return childrens;
		}
		private void setChildrens(List<String> childrens) {
			this.childrens = childrens;
		}

	}

	public static void main(String[] args) {

		List<Person> persons = new ArrayList<>();
		List<String> list1 = new ArrayList<>();
		list1.add("w2");list1.add("w1");list1.add("w3");
		Person person1 = new Person("wang",22,list1);
		Person person2 = new Person("mo",20,list1);
		Person person3 = new Person("chi",24,list1);
		Person person4 = new Person("xuan",19,list1);
		persons.add(person1);
		persons.add(person2);
		persons.add(person3);
		persons.add(person4);

		Collections.sort(persons);

		for (Person person: persons) {
			System.out.println("=======>>"+person.age);
		}

		List<Person1> persons1 = new ArrayList<>();
		Person1 person11 = new Person1("wang",22,list1);
		Person1 person22 = new Person1("mo",20,list1);
		Person1 person33 = new Person1("chi",24,list1);
		Person1 person44 = new Person1("xuan",19,list1);
		persons1.add(person11);
		persons1.add(person22);
		persons1.add(person33);
		persons1.add(person44);

		Collections.sort(persons1,new Comparator<Person1>() {
			@Override
			public int compare(Person1 o1, Person1 o2) {
				return o1.name.compareTo(o2.name);
			}
		});

		for (Person1 person: persons1) {
			System.out.println("=======>>"+person.age);
		}
	}

}
