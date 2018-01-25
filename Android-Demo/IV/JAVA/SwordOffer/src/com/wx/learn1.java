package com.wx;

import java.util.Stack;

public class learn1 {

	public static void main(String[] args) {
		//System.out.println(replace2("We are happy."));
		System.out.println(binary7(37));
	}
	
    /**
     * 1.在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到
	 * 下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断
     * 数组中是否含有该整数。	
     */
	private static boolean sort1(int[][] arrays,int data) {
		
		for (int i = 0;i<arrays.length;i++) {
			if(arrays[i][0] > data) return false;
			for (int j = 0 ; j < arrays[i].length;i++) {
				if (arrays[i][j] == data) {
					return true;
				} else if (arrays[i][j]>data) {
					break;
				}
			} 
		}
		
		return false;
	}

	/**
	 * 2.请实现一个函数，把字符串中的每个空格替换成"%20"，例如“We are happy.”，则
	 * 输出“We%20are%20happy.”。
	 */
	private static String replace2(String data) {
		return data.replace(" ", "%20");
	}
	
	/**
	 * 3.输入个链表的头结点，从尾到头反过来打印出每个结点的值
	 */
	class Entry3 {
		private int value;
		private Entry3 next;
	}
	private static void logEntry3(Entry3 root) {
		Stack<Entry3> stack = new Stack<>();
		while (root != null) {
			stack.push(root);
			root = root.next;
		}
		Entry3 temp;
		while(!stack.isEmpty()) {
			temp = stack.pop();
			System.out.println(temp);
		}
	}
	
	/**
	 * 4.输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序
     * 遍历和中序遍历的结果中都不含重复的数字。例如：前序遍历序列｛ 1, 2, 4, 7, 3,
	 * 5, 6, 8｝和中序遍历序列｛4, 7, 2, 1, 5, 3, 8，6}，重建二叉树并输出它的头结点。
	 */
	static class BinaryTreeNode {
		int value;
		BinaryTreeNode leftNode;
		BinaryTreeNode rightNode;
	}
	private static BinaryTreeNode  contruct4(int[] preOrder,int ps,int pe,int[] middleOrder,int ms,int me) {
		
		if (ps > pe) return null;
		
		int value = preOrder[ps];
		int index = ms;
		while(index <= me && middleOrder[index] != value) {
			index++;
		}
		
		if (index > me) {
			throw new RuntimeException();
		}
		
		BinaryTreeNode node = new BinaryTreeNode();
		node.value = value;
		node.leftNode = contruct4(preOrder, ps+1, ps+index-ms, middleOrder, ms, index-1);
		node.rightNode = contruct4(preOrder, ps+index-ms+1, pe, middleOrder, index+1, me);
		return node;
	}
	
	/**
	 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数appendTail 和
	 * deleteHead，分别完成在队列尾部插入结点和在队列头部删除结点的功能。
	 */
	static class Queue5<T> {
		
		Stack<T> stack1 = new Stack<>();
		
		Stack<T> stack2 = new Stack<>();
		
		public void appendTail(T t) {
			
			while (!stack2.isEmpty()) {
				stack1.add(stack2.pop());
			}
			
			stack1.add(t);
		}
		
		public T deleteHead() {
			
			while (!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
			
			if (stack2.isEmpty()) {
				throw new RuntimeException();
			}
			
			return stack2.pop();
		}
	}
	
	/**
	 * 写一个函数，输入n，求斐波那契数列的第n项值。
	 */
	static int fibonacci6(int n) {
		if (n == 0) return 0;
		if (n == 1) return 1;
		return fibonacci6(n-1)+fibonacci6(n-2);
	}
	
	/**
	 * 请实现一个函数，输入一个整数，输出该数二进制表示中1的个数。例如把9表示成
	 * 二进制1001，有2位1。因此如果输入9，该函数输出2
	 */
	static int binary7(int num) {
		int sumOne = 0;
		while (num > 0) {
			for (int i = 0;i < num;i++) {
				int temp = num - (1 << i);
				if (temp < 0) {
					++sumOne;
					num = num - (1<<i-1);
					break;
				} else if(temp == 0){
					++sumOne;
					num = num - (1<<i);
					break;
				}
			}
		}
		return sumOne;
	}
	
	/**
	 * 输入数字n，按顺序打印出从1到n位最大十进数的数值。比如输入3，则打印出1、
	 * 2、3一直到最大三位数即999
	 */
	static void print8(int n) {
		
	}
	
	
}
