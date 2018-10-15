package com.datastructure.practice;

/**
 * The Class LinkedList.
 */
public class LinkedList {
	
	/** The first. */
	public Link first;

	/**
	 * Instantiates a new linked list.
	 */
	public LinkedList() {
		super();
		first = null;
	}
	
	/**
	 * Insert first.
	 *
	 * @param i the i
	 * @param d the d
	 */
	public void insertFirst(int i,double d){
		Link newLink = new Link(i, d);
		newLink.next = first;
		first = newLink;
	}
	
	/**
	 * Delete first.
	 *
	 * @return the link
	 */
	public Link deleteFirst(){
		Link temp = first;
		first = first.next;
		return temp;
	}
	
	/**
	 * Display link.
	 */
	public void displayList(){
		Link current = first;
		while(current!=null){
			current.displayLink();
			current = current.next;
		}
		System.out.println("");
	}

}
