package com.restfulwebservices.practice.RestfulServices.model;

// TODO: Auto-generated Javadoc
/**
 * The Class HelloWorldBean.
 */
public class HelloWorldBean {
	
	/** The message. */
	private String message;
	
	
	
	/**
	 * Instantiates a new hello world bean.
	 *
	 * @param message the message
	 */
	public HelloWorldBean(String message) {
		super();
		this.message = message;
	}



	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}



	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}



	/**
	 * Instantiates a new hello world bean.
	 */
	public HelloWorldBean() {
		
	}

}
