package com.bcci.ipl.model;


/**
 * The Class Venue.
 */
public class Venue {
	
	/** The venue name. */
	private String venueName;
	
	/** The is occupied. */
	private boolean isOccupied;
	
	public Venue(String venueName2) {
		this.venueName=venueName2;
	}

	/**
	 * Gets the venue name.
	 *
	 * @return the venue name
	 */
	public String getVenueName() {
		return venueName;
	}
	
	/**
	 * Sets the venue name.
	 *
	 * @param venueName the new venue name
	 */
	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}
	
	/**
	 * Checks if is occupied.
	 *
	 * @return true, if is occupied
	 */
	public boolean isOccupied() {
		return isOccupied;
	}
	
	/**
	 * Sets the occupied.
	 *
	 * @param isOccupied the new occupied
	 */
	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
	
	

}
