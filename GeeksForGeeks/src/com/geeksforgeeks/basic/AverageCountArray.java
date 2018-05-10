package com.geeksforgeeks.basic;

import java.util.Scanner;

public class AverageCountArray {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		while (t-- > 0) {
			int n = scanner.nextInt();
			int x = scanner.nextInt();
			int avg = 0;
			int count = 0;
			int[] a = new int[n];
			for (int i = 0; i < a.length; i++) {
				a[i] = scanner.nextInt();
			}
			int[] b = new int[a.length];
			for (int i = 0; i < a.length; i++) {
				avg = (a[i] + x) / 2;

				for (int j = 0; j < a.length; j++) {
					if (avg == a[j]) {
						b[i] = ++count;
					}
				}
				count = 0;
			}
			for (int i = 0; i < b.length; i++) {
				System.out.print(b[i] + "\t");
			}
		}
		scanner.close();
	}

}
