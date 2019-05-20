package com.tlan;

import java.util.ArrayList;
import java.util.List;

import com.tlan.beans.KeyValueBean;

public class test {
	public static int allWeight = 7680; // 初始煤总量
	public static double preWeight = 1000; // 每次最大运煤量
	public static int kilometres = 1000; // 目的地与初始地距离
	public static int weightPerKilometre = 1; // 没公里消耗煤量
	public static int step = 30; // 动态规划间隔距离
	public static List<Object> transfers = new ArrayList<Object>();
	public static int index = 0;
	public static int[][] weightArr = new int[allWeight+1][kilometres+1];
	public static int[] tranferArr = new int[kilometres+1];
	public static int[] bestArr = new int[kilometres+1];
	
	public static void main(String[] args) {
		
//		System.out.println(KeyValueBean.class);
//		
//		String str = "京A-HP711";
//		System.out.println(str.substring(0,1));
//		System.out.println(str.substring(1, str.length()).split("-")[0]+str.substring(1, str.length()).split("-")[1]);
		System.out.println("start");
		for (int i=1; i<=allWeight; i++) {
			for (int j=1; j<=kilometres; j++) {
				weightArr[i][j] = getRemain(i, j);
			}
		}
		for (int i=1; i<=kilometres; i++) {
			bestArr[i] = weightArr[allWeight][i];
			tranferArr[i] = 0;
		}
		for (int i=1; i<=kilometres; i++) {
			for (int j=1; j<i; j++) {
				if (weightArr[bestArr[j]][i-j]>=0 && bestArr[i]<weightArr[bestArr[j]][i-j]) {
					bestArr[i] = weightArr[bestArr[j]][i-j];
					tranferArr[i] = j;
				}
			}
		}
		
		System.out.println(kilometres+" : "+bestArr[kilometres]);
		int index = kilometres;
		while (tranferArr[index]>0)	{
			index=tranferArr[index];
			System.out.println(index+" : "+bestArr[index]);
		}
		
		// System.out.println(getMaxRemain(allWeight, 0, kilometres));
		// System.out.println(transfers.toString());
	}

	public static int getRemain(int oriWeight, int distance) {
		int round = (int)Math.ceil(oriWeight / preWeight); // 直接运送需要来回多少趟
		
		int directWeight = distance * weightPerKilometre; // 直接运送每趟消耗煤量
		int nowWeight = oriWeight - (2 * round - 1) * directWeight; // 最大剩余没量，初始值为直接运送量

		return nowWeight;
	}
	
	
	
	// 最早的算法，效率太低。
	public static int getMaxRemain(int remainWeight, int first,
			int last) {
		int round = (int)Math.ceil(remainWeight / preWeight); // 直接运送需要来回多少趟
		int directWeight = (last - first) * weightPerKilometre; // 直接运送每趟消耗煤量
		int maxRemainWeight = remainWeight - (2 * round - 1) * directWeight; // 最大剩余没量，初始值为直接运送量
		if (maxRemainWeight < 0) {
			maxRemainWeight = 0;
		}
		int transfer = first + step; // 中转站

		while (transfer < last) {
			int fisrtRemain = getMaxRemain(remainWeight, first, transfer);
			int lastRemain = getMaxRemain(fisrtRemain, transfer, last);
			if (maxRemainWeight < lastRemain) {
				maxRemainWeight = lastRemain;
			}
			transfer += step;
		}

		return maxRemainWeight;
	}

}
