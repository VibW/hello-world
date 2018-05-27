package com.bcci.ipl.model;


import java.util.Date;


/**
 * The Class TournamentDetails.
 */
public class TournamentDetails {
	
	/** The match date. */
	private Date matchDate;
	
	/** The match. */
	private String match;
	
	/** The venue. */
	private String venue;
	
	/**
	 * Gets the match date.
	 *
	 * @return the match date
	 */
	public Date getMatchDate() {
		return matchDate;
	}
	
	/**
	 * Sets the match date.
	 *
	 * @param matchDate the new match date
	 */
	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}
	
	/**
	 * Gets the match.
	 *
	 * @return the match
	 */
	public String getMatch() {
		return match;
	}
	
	/**
	 * Sets the match.
	 *
	 * @param match the new match
	 */
	public void setMatch(String match) {
		this.match = match;
	}
	
	/**
	 * Gets the venue.
	 *
	 * @return the venue
	 */
	public String getVenue() {
		return venue;
	}
	
	/**
	 * Sets the venue.
	 *
	 * @param venue the new venue
	 */
	public void setVenue(String venue) {
		this.venue = venue;
	}
	
	

}
