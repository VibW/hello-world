package com.geeksforgeeks.basic;

import java.util.ArrayList;
import java.util.Scanner;

public class NumberSystem {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t =scanner.nextInt();
		while (t-->0) {
			int n = scanner.nextInt();
			int k = scanner.nextInt();
			ArrayList<Integer> holder = new ArrayList<Integer>();
			while (n > 0) {
				int rem = n % k;

				holder.add(rem);
				n = n / k;
			}
			for (int i = holder.size() - 1; i >= 0; i--) {
				System.out.print(holder.get(i));
			}
			System.out.println();
		}
		scanner.close();
	}

}//237342378 9
