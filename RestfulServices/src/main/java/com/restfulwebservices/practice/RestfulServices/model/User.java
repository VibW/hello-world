package com.restfulwebservices.practice.RestfulServices.model;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
public class User {
	
	/** The name. */
	private String name;
	
	/** The id. */
	private Integer id;
	
	/** The date of birth. */
	private Date dateOfBirth;
	
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Instantiates a new user.
	 */
	public User() {
		super();
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param name the name
	 * @param id the id
	 */
	

	public User(String name, Integer id, Date dateOfBirth) {
		super();
		this.name = name;
		this.id = id;
		this.dateOfBirth = dateOfBirth;
	}
	
	

}
