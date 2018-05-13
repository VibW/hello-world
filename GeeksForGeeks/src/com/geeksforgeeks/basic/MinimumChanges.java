package com.geeksforgeeks.basic;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MinimumChanges {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		scanner.nextLine();
		while (t-- > 0) {
			String a = scanner.nextLine();
			int sum = 0;
			Map<Character, Integer> charMap = new HashMap<Character, Integer>();
			for (int i = 0; i < a.length(); i++) {
				char charAt = a.charAt(i);
				if (!charMap.containsKey(charAt)) {
					charMap.put(charAt, 1);
				} else {
					charMap.put(charAt, charMap.get(charAt) + 1);
				}
			}
			for (Map.Entry<Character, Integer> entry : charMap.entrySet()) {
				// System.out.println(" Key " + entry.getKey() + " / " +
				// entry.getValue());
				if (entry.getValue() > 1) {
					sum = sum + (entry.getValue() - 1);
				}
			}
			System.out.println(sum);

		}
		scanner.close();

	}

}
