package com.bcci.ipl.model;

import java.util.Date;


/**
 * The Class Match.
 */
public class Match {
	
	/** The first team. */
	private Team firstTeam;
	
	/** The second team. */
	private Team secondTeam;
	
	/** The venue. */
	private Venue venue;
	
	/** The match date. */
	private Date matchDate;
	
	/** The is played. */
	private boolean isPlayed;
	
	/**
	 * Gets the first team.
	 *
	 * @return the first team
	 */
	public Team getFirstTeam() {
		return firstTeam;
	}
	
	/**
	 * Sets the first team.
	 *
	 * @param firstTeam the new first team
	 */
	public void setFirstTeam(Team firstTeam) {
		this.firstTeam = firstTeam;
	}
	
	/**
	 * Gets the second team.
	 *
	 * @return the second team
	 */
	public Team getSecondTeam() {
		return secondTeam;
	}
	
	/**
	 * Sets the second team.
	 *
	 * @param secondTeam the new second team
	 */
	public void setSecondTeam(Team secondTeam) {
		this.secondTeam = secondTeam;
	}
	
	/**
	 * Gets the venue.
	 *
	 * @return the venue
	 */
	public Venue getVenue() {
		return venue;
	}
	
	/**
	 * Sets the venue.
	 *
	 * @param venue the new venue
	 */
	public void setVenue(Venue venue) {
		this.venue = venue;
	}
	
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
	 * Checks if is played.
	 *
	 * @return true, if is played
	 */
	public boolean isPlayed() {
		return isPlayed;
	}
	
	/**
	 * Sets the played.
	 *
	 * @param isPlayed the new played
	 */
	public void setPlayed(boolean isPlayed) {
		this.isPlayed = isPlayed;
	}
	 
	public void setTeams(Team team1,Team team2){
		this.firstTeam = team1;
		this.secondTeam = team2;
	}
	
	
}
