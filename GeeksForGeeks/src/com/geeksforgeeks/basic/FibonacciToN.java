package com.geeksforgeeks.basic;

import java.util.Scanner;

public class FibonacciToN {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		while (t-- > 0) {
			int n = scanner.nextInt();
			int a = 0, b = 1;
			int m = n;
			int c = 0;
			System.out.print(a + " " + b + " ");
			while (n-- > 0) {
				c = a + b;
				a = b;
				b = c;
				if (c > m) {
					break;
				} else if (c <= m) {
					System.out.print(c + " ");
				}

			}
		}
	}

}
