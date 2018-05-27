package com.bcci.ipl.model;

// TODO: Auto-generated Javadoc
/**
 * The Class Team.
 */
public class Team {
	
	/** The team name. */
	private String teamName;
	
	/** The home ground. */
	private String homeGround;
	
	/** The not playing. */
	private boolean notPlaying;
	
	/** The id. */
	private int id;
	
	public Team(String teamName2) {
		this.teamName = teamName2;
		
		this.notPlaying = true;
	}

	public Team() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the team name.
	 *
	 * @return the team name
	 */
	public String getTeamName() {
		return teamName;
	}
	
	/**
	 * Sets the team name.
	 *
	 * @param teamName the new team name
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	/**
	 * Gets the home ground.
	 *
	 * @return the home ground
	 */
	public String getHomeGround() {
		return homeGround;
	}
	
	/**
	 * Sets the home ground.
	 *
	 * @param homeGround the new home ground
	 */
	public void setHomeGround(String homeGround) {
		this.homeGround = homeGround;
	}
	
	/**
	 * Checks if is not playing.
	 *
	 * @return true, if is not playing
	 */
	public boolean isNotPlaying() {
		return notPlaying;
	}
	
	/**
	 * Sets the not playing.
	 *
	 * @param notPlaying the new not playing
	 */
	public void setNotPlaying(boolean notPlaying) {
		this.notPlaying = notPlaying;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
	

}
