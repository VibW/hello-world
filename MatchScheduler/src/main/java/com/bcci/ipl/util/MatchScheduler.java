package com.bcci.ipl.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.bcci.ipl.model.Match;
import com.bcci.ipl.model.Team;
import com.bcci.ipl.model.Tournament;
import com.bcci.ipl.model.Venue;

/**
 * The Class MatchScheduler.
 */
public class MatchScheduler {

	private static final Logger logger = Logger.getLogger(MatchScheduler.class);

	/**
	 * Creates the match.
	 *
	 * @param tournament
	 *            the tournament
	 * @return the array list
	 */
	public ArrayList<Match> createMatch(Tournament tournament) {
		logger.info("#MatchScheduler#createMatch#method starts");
		ArrayList<Match> matches = new ArrayList<Match>();
		int totalRounds = tournament.getNumberOfRounds();

		ArrayList<Team> teamList = new ArrayList<Team>(tournament.getTournamentTeams());
		int totTeams = tournament.getTotalNumberOfTeams();//
		logger.info("#MatchScheduler#createMatch#method#totalTeams" + totTeams);
		try {
			for (int i = 1; i <= totalRounds; i++) {
				for (int j = 0; j < totTeams - 1; j++) {
					for (int k = j + 1; k < totTeams; k++) {
						Match match = new Match();
						match.setTeams(teamList.get(j), teamList.get(k));
						matches.add(match);
					}
				}
			}

		}

		catch (Exception e) {
			logger.error("#MatchScheduler#createMatch#method#Exception" + e.getMessage());
		}
		logger.info("#MatchScheduler#createMatch#method ends");
		return matches;

	}

	/**
	 * Creates the schedule.
	 *
	 * @param tournament
	 *            the tournament
	 * @param matches
	 *            the matches
	 * @return the array list
	 */
	public ArrayList<Match> createSchedule(Tournament tournament, ArrayList<Match> matches) {
		logger.info("MatchScheduler#createSchedule#method starts" + matches.size());
		ArrayList<Match> scheduledMatches = new ArrayList<Match>();
		ArrayList<Venue> venueList = new ArrayList<Venue>(tournament.getTournamentVenues());
		Date date = tournament.getTournamentBeginDate();
		int matchIndex = 0;
		int venueIndex = 0;
		int totVenues = tournament.getTotalNumberOfVenues();
		int numberOfRounds = tournament.getNumberOfRounds();
		int totMatches = numberOfRounds
				* ((tournament.getTotalNumberOfTeams() * (tournament.getTotalNumberOfTeams() - 1)) / 2);
		logger.info("MatchScheduler#createSchedule#method#matchesSize" + matches.size());
		int[] trackMatches = new int[totMatches];
		int indexOfTracking = 0;

		while (totMatches > 0) {
			Match match = matches.get(matchIndex);
			if (match.isPlayed() == false) {
				if (match.getFirstTeam().isNotPlaying() && match.getSecondTeam().isNotPlaying()) {

					if (venueList.get(venueIndex % totVenues).isOccupied() == false) {
						matches.get(matchIndex).setVenue(venueList.get(venueIndex % totVenues));
						venueList.get(venueIndex % totVenues).setOccupied(true);
						matches.get(matchIndex).setPlayed(true);
						matches.get(matchIndex).setMatchDate(date);
						matches.get(matchIndex).getFirstTeam().setNotPlaying(false);
						matches.get(matchIndex).getSecondTeam().setNotPlaying(false);
						scheduledMatches.add(matches.get(matchIndex));
						venueIndex = venueIndex + 1;
						totMatches = totMatches - 1;
						trackMatches[indexOfTracking] = matchIndex;
						indexOfTracking = indexOfTracking + 1;

						if ((venueIndex % totVenues) == 0) {

							for (Venue ven : venueList) {
								ven.setOccupied(false);
							}
						}
					}
				}
			}
			matchIndex = matchIndex + 1;
			if (matchIndex == matches.size()) {
				matchIndex = 0;
				date = dateIncrementer(date, 1).getTime();
				for (int trackedIndexes : trackMatches) {
					matches.get(trackedIndexes).getFirstTeam().setNotPlaying(true);
					matches.get(trackedIndexes).getSecondTeam().setNotPlaying(true);

				}
				trackMatches = new int[totMatches];
				indexOfTracking = 0;

			}

		}
		logger.info("MatchScheduler#createSchedule#method ends");
		return scheduledMatches;
	}

	/**
	 * Date incrementer.
	 *
	 * @param currentDate
	 *            the current date
	 * @param numberOfDaysToIncrement
	 *            the number of days to increment
	 * @return the calendar
	 */
	private Calendar dateIncrementer(Date currentDate, int numberOfDaysToIncrement) {
		logger.info("#MatchScheduler#dateIncrementer#method starts");
		Calendar cal = null;
		try {
			cal = Calendar.getInstance();
			cal.setTime(currentDate);
			cal.add(Calendar.DATE, numberOfDaysToIncrement);
		} catch (Exception e) {
			logger.error("#MatchScheduler#dateIncrementer#method#Exception" + e.getMessage());
		}
		logger.info("#MatchScheduler#dateIncrementer#method ends");
		return cal;
	}

}
