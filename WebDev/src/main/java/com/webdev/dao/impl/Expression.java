package com.webdev.dao.impl;

public class Expression {

	public static void main(String[] args) {
		int m =  10 ;
		int n,n1;
		n = ++m;
		System.out.println("-----------n-----------------"+n);
		n1=m++;
		System.out.println("------------n1--------------"+n1);
		int a = n--;
		System.out.println("-----------------n-- is "+a);
		int b = --n1;
		System.out.println("-----------------b-- is "+b);
		n =n-n1;
		System.out.println("N-----------------"+n);
	}

}
