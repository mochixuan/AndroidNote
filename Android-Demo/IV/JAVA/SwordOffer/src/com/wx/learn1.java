package com.wx;

public class learn1 {

	public static void main(String[] args) {
		
	}
	
    /**
     * 1.在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到
	 * 下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断
     * 数组中是否含有该整数。	
     */
	private boolean sort1(int[][] arrays,int data) {
		
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
	
}
