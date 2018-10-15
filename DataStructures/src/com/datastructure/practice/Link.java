package com.datastructure.practice;

/**
 * The Class Link.
 */
public class Link {
	
	/** The i data. */
	public int iData;
	
	/** The d data. */
	public double dData;
	
	/** The next. */
	public Link next;
	
	/**
	 * Instantiates a new link.
	 *
	 * @param iData the i data
	 * @param dData the d data
	 */
	public Link(int iData, double dData) {
		super();
		this.iData = iData;
		this.dData = dData;
	}
	
	/**
	 * Display link.
	 */
	public void displayLink(){
		System.out.println("{" + iData + ", " + dData + "} ");
	}

}
