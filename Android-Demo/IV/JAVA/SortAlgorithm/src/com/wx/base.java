package com.wx;

import java.util.LinkedHashMap;
import java.util.Map;

public class base {

	public static void main(String[] args) {
		//bubbleSort();
		//choiceSort();
		//insertSort();
		//quickSort();
		linkHashMap();
	}

	//冒泡排序: 比较两个相邻的元素，将值大的元素交换至右端。O(n*n)
	static void bubbleSort() {
		int[] datas = {8,3,1,5,2,7,6,9,0,4};
		int temp = 0;
		for (int i = 0 ; i < datas.length - 1 ; i++) {
			for (int j = 0 ; j < datas.length - 1 - i ; j++) {
				if (datas[j] > datas[j+1]) {
					temp = datas[j];
					datas[j] = datas[j+1];
					datas[j+1] = temp;
				}
			}
		}

		for (int i = 0 ; i < datas.length ; i++) {
			System.out.print(datas[i]+" ");
		}
	}

	//选择排序: 每一趟从待排序的记录中选出最小的元素，顺序放在已排好序的序列最后，直到全部记录排序完毕。
	//时间复杂度O(n*n) 性能上优于冒泡排序 交换次数少 最大时n-1
	static void choiceSort() {
		int[] datas = {8,3,1,5,2,7,6,9,0,4};
		int temp = 0;
		for (int i = 0 ; i < datas.length - 1 ; i++) {
			int key = i;
			for (int j = i+1 ; j < datas.length ; j++) {
				if (datas[j] < datas[key]) {
					key = j;
				}
			}
			if (key != i) {
				temp = datas[i];
				datas[i] = datas[key];
				datas[key] = temp;
			}

		}

		for (int i = 0 ; i < datas.length ; i++) {
			System.out.print(datas[i]+" ");
		}
	}

	//插入排序: 通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应的位置并插入。
	//O(n*n) 性能上优于冒泡排序和选择排序
	static void insertSort() {
		int[] datas = {8,3,1,5,2,7,6,9,0,4};
		int temp = 0;
		int j = 0;
		for (int i = 1 ; i < datas.length ; i++) {
			temp = datas[i];
			for (j = i ;j > 0 && temp < datas[j-1]; j--) {
				datas[j] = datas[j-1];
			}
			if (i != j) datas[j] = temp;
		}

		for (int i = 0 ; i < datas.length ; i++) {
			System.out.print(datas[i]+" ");
		}
	}

	//快速排序O(nlog2n): 通过一趟排序将待排序记录分割成独立的两部分，其中一部分记录的关键字均比另一部分关键字小，则分别对这两部分继续进行排序，直到整个序列有序。
	static void quickSort() {
		int[] datas = {8,3,1,5,2,7,6,9,0,4,8,7,10};
		qSort(datas, 0, datas.length-1);
		for (int i = 0 ; i < datas.length ; i++) {
			System.out.print(datas[i]+" ");
		}
	}

	static void qSort(int[] arr , int low , int high) {
		if (low < high) {
			int pivot = partition(arr, low, high);
			qSort(arr , low, pivot-1);
			qSort(arr , pivot+1 , high );
		}
	}

	//快速排序
	static int partition(int[] arr,int low,int high) {
		int pivot = arr[low];
		while(low < high) {
			while (low<high && arr[high]>=pivot) --high;
			arr[low] = arr[high];
			while(low<high && arr[low]<=pivot) ++low;
			arr[high] = arr[low];
		}
		arr[low] = pivot;
		return low;
	}

	//linkedHashMap
	static void linkHashMap() {
		LinkedHashMap<Integer, String>  map = new LinkedHashMap<>(0,0.75f,true);
		map.put(0, "0");
		map.put(4, "0");
		map.put(3, "0");
		map.put(2, "0");
		map.put(1, "0");
		map.get(1);
		map.get(2);
		map.get(3);
		map.get(4);

		for(Map.Entry<Integer, String> entry: map.entrySet()) {
			System.out.println(entry.getKey()+"  "+entry.getValue());
		}
	}

}
