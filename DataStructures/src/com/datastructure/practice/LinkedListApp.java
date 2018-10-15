package com.datastructure.practice;

public class LinkedListApp {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		list.insertFirst(1, 1.1);
		list.insertFirst(2, 2.2);
		list.insertFirst(3, 3.3);
		
		list.displayList();
		list.deleteFirst();
		list.displayList();
	}

}
