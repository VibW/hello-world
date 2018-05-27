

package com.bcci.ipl.model;

import java.util.Date;
import java.util.LinkedHashSet;

import org.apache.log4j.Logger;

import com.bcci.ipl.util.MatchScheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



/**
 * The Class Tournament.
 */
public class Tournament {

	/** The tournament name. */
	private String tournamentName;
	
	/** The tournament teams. */
	private LinkedHashSet<Team> tournamentTeams;
	
	/** The tournament venues. */
	private LinkedHashSet<Venue> tournamentVenues;
	
	/** The matches. */
	private ArrayList<Match> matches;
	
	/** The total number of teams. */
	private int totalNumberOfTeams;
	
	/** The total number of venues. */
	private int totalNumberOfVenues;
	
	/** The number of rounds. */
	private int numberOfRounds;
	
	/** The tournament begin date. */
	private Date tournamentBeginDate;

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(Tournament.class);
	
	/**
	 * Instantiates a new tournament.
	 *
	 * @param totNoTeams the tot no teams
	 * @param totNoVenues the tot no venues
	 * @param date the date
	 */
	public Tournament(int totNoTeams, int totNoVenues, Date date)
	{
		
		
		if(totNoTeams>0)
		{
			this.totalNumberOfTeams = totNoTeams;
			this.tournamentTeams = new LinkedHashSet<Team>(this.totalNumberOfTeams);
		}
		else
		{
			this.totalNumberOfTeams = 0;
			this.tournamentTeams = new LinkedHashSet<Team>();
		}
		
		if(totNoVenues>0)
		{
			this.totalNumberOfVenues = totNoVenues;
			this.tournamentVenues = new LinkedHashSet<Venue>(this.totalNumberOfVenues);
		}
		else
		{
			this.totalNumberOfVenues = 0;
			this.tournamentVenues = new LinkedHashSet<Venue>();
		}
		
		this.matches = new ArrayList<Match>();
		this.numberOfRounds = 0;
		this.tournamentBeginDate = date;
		
	}
	
	/**
	 * Gets the tournament begin date.
	 *
	 * @return the tournament begin date
	 */
	public Date getTournamentBeginDate() {
		return tournamentBeginDate;
	}

	/**
	 * Sets the tournament begin date.
	 *
	 * @param tournamentBeginDate the new tournament begin date
	 */
	public void setTournamentBeginDate(Date tournamentBeginDate) {
		this.tournamentBeginDate = tournamentBeginDate;
	}

	/**
	 * Gets the total number of teams.
	 *
	 * @return the total number of teams
	 */
	public int getTotalNumberOfTeams() {
		if(tournamentTeams!= null && this.totalNumberOfTeams != tournamentTeams.size())
			this.totalNumberOfTeams = tournamentTeams.size();
		
		return totalNumberOfTeams;
	}

	/**
	 * Sets the total number of teams.
	 *
	 * @param totalNumberOfTeams the new total number of teams
	 */
	public void setTotalNumberOfTeams(int totalNumberOfTeams) {
		if(totalNumberOfTeams>=0)
		{
			this.totalNumberOfTeams = totalNumberOfTeams;
		}
	}

	/**
	 * Gets the total number of venues.
	 *
	 * @return the total number of venues
	 */
	public int getTotalNumberOfVenues() {
		if(tournamentVenues!=null && this.totalNumberOfVenues != tournamentVenues.size())
			this.totalNumberOfVenues = tournamentVenues.size();
		return this.totalNumberOfVenues;
	}

	/**
	 * Sets the total number of venues.
	 *
	 * @param totalNumberOfVenues the new total number of venues
	 */
	public void setTotalNumberOfVenues(int totalNumberOfVenues) {
		if(totalNumberOfVenues>=0)
		{
			this.totalNumberOfVenues = totalNumberOfVenues;
		}
	}

	/**
	 * Gets the tournament name.
	 *
	 * @return the tournament name
	 */
	public String getTournamentName() {
		return tournamentName;
	}
	
	/**
	 * Sets the tournament name.
	 *
	 * @param name the new tournament name
	 */
	public void setTournamentName(String name) {
		if(name != null)
		{
			this.tournamentName = name;
		}
	}
	
	
	/**
	 * Gets the tournament teams.
	 *
	 * @return the tournament teams
	 */
	public LinkedHashSet<Team> getTournamentTeams() {
			return tournamentTeams;
	}

	/**
	 * Sets the tournament teams.
	 *
	 * @param tournamentTeams the new tournament teams
	 */
	private void setTournamentTeams(LinkedHashSet<Team> tournamentTeams)  // Hidden Method
	{
		this.tournamentTeams = tournamentTeams;
	}
	
	/**
	 * Sets the tournament teams.
	 *
	 * @param tournamentTeamNames the new tournament teams
	 */
	public void setTournamentTeams(ArrayList<String> tournamentTeamNames)  // Exposed Method
	{
		LinkedHashSet<Team> tournamentTeamSet = new LinkedHashSet<Team>();
		
		for(String teamName: tournamentTeamNames)
		{
			tournamentTeamSet.add(new Team(teamName));
		}
		this.setTournamentTeams(tournamentTeamSet);
	}
	


	/**
	 * Gets the tournament venues.
	 *
	 * @return the tournament venues
	 */
	public LinkedHashSet<Venue> getTournamentVenues() {
		return tournamentVenues;
	}

	/**
	 * Sets the tournament venues.
	 *
	 * @param tournamentVenues the new tournament venues
	 */
	private void setTournamentVenues(LinkedHashSet<Venue> tournamentVenues)	// Hidden Method
	{
		this.tournamentVenues = tournamentVenues;
	}
	
	/**
	 * Sets the tournament venues.
	 *
	 * @param tournamentVenueNames the new tournament venues
	 */
	public void setTournamentVenues(ArrayList<String> tournamentVenueNames)	// Exposed Method
	{
		LinkedHashSet<Venue> tournamentVenueSet = new LinkedHashSet<Venue>();
		
		for(String venueName: tournamentVenueNames)
		{
			tournamentVenueSet.add(new Venue(venueName));
		}
		this.setTournamentVenues(tournamentVenueSet);
	}
	
	/**
	 * Gets the number of rounds.
	 *
	 * @return the number of rounds
	 */
	public int getNumberOfRounds() {
		return numberOfRounds;
	}

	/**
	 * Sets the number of rounds.
	 *
	 * @param numberOfRounds the new number of rounds
	 */
	public void setNumberOfRounds(int numberOfRounds) {
		this.numberOfRounds = numberOfRounds;
	}

	/**
	 * Adds the team.
	 *
	 * @param team the team
	 */
	private void addTeam(Team team)  
	{
		if(this.tournamentTeams.size()==this.totalNumberOfTeams)
			this.totalNumberOfTeams = this.totalNumberOfTeams+1;
		
		this.tournamentTeams.add(team);
	}
	
	/**
	 * Adds the team.
	 *
	 * @param teamName the team name
	 */
	public void addTeam(String teamName)  
	{
		this.addTeam(new Team(teamName));
	}
	
	
	
	/**
	 * Adds the venue.
	 *
	 * @param venue the venue
	 */
	private void addVenue(Venue venue)  
	{
		if(this.tournamentVenues.size()==this.totalNumberOfVenues)
			this.totalNumberOfVenues = this.totalNumberOfVenues+1;
		
		this.tournamentVenues.add(venue);
	}
	
	/**
	 * Adds the venue.
	 *
	 * @param venueName the venue name
	 */
	public void addVenue(String venueName)  
	{
		this.addVenue(new Venue(venueName));
	}
	
	
	
	/**
	 * Gets the matches.
	 *
	 * @return the matches
	 */
	public ArrayList<Match> getMatches()
	{
		logger.info("#Tournament#getMatches()#method starts");
		try {
			MatchScheduler ms = new MatchScheduler();
			this.matches = ms.createMatch(this);
			this.matches = ms.createSchedule(this, this.matches);
		} catch (Exception e) {
			logger.error("#Tournament#getMatches()#method#Exception"+e.getMessage());
		}
		logger.info("#Tournament#getMatches()#method ends");
		return matches;
	}
	
	
	
}