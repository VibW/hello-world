package com.geeksforgeeks.basic;

import java.util.Scanner;

public class SaveIronMan {

	public static void main(String[] args) {
		Scanner scanner  = new Scanner(System.in);
		int t  = scanner.nextInt();
		scanner.nextLine();
		while (t-->0) {
			String test = scanner.nextLine();
			StringBuffer orig = new StringBuffer();
			
			for (int i = 0; i < test.length(); i++) {
				if (Character.isLetterOrDigit(test.charAt(i))) {
					orig.append(test.charAt(i));
				}
			}
			if (orig.toString().equalsIgnoreCase(orig.reverse().toString())) {
				System.out.println("YES");
			} 
			else{
				System.out.println("NO");
			}
		}
		scanner.close();

	}

}
