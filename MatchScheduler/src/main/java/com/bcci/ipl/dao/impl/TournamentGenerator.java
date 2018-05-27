package com.bcci.ipl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.bcci.ipl.controller.SchedulerController;
import com.bcci.ipl.model.Match;
import com.bcci.ipl.model.Tournament;
import com.bcci.ipl.model.TournamentDetails;



/**
 * The Class TournamentGenerator.
 */
public class TournamentGenerator {

	/** The data source. */
	private DataSource dataSource;

	/** The connection. */
	private Connection connection;

	/** The st. */
	private Statement st;

	/** The rs. */
	private ResultSet rs;

	/** The ps. */
	private PreparedStatement ps;

	/** The total rounds. */
	private int totalRounds = 2;

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(TournamentGenerator.class);

	/**
	 * Sets the data source.
	 *
	 * @param dataSource
	 *            the new data source
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * Gets the DB connection.
	 *
	 * @return the DB connection
	 */
	private Connection getDBConnection() {
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * Creates the fixtures.
	 *
	 * @param startDate
	 *            the start date
	 */
	public void createFixtures(String startDate) {
		logger.info("#TournamentGenerator#createFixture#method starts");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date parsedDate = null;
		try {
			logger.info("#TournamentGenerator#createFixture#method#startDate" + startDate);
			parsedDate = sdf.parse(startDate);
			logger.info("#TournamentGenerator#createFixture#method#startDate#afterParsing" + parsedDate);
		} catch (ParseException e1) {

			logger.error("#TournamentGenerator#createFixture#method#ParseException" + e1.getMessage());
		}
		String teamfetchQuery = "SELECT TEAM_NAME,HOME_GROUND from TEAM_DETAILS";
		ArrayList<String> teamList = new ArrayList<String>();
		ArrayList<String> venueList = new ArrayList<String>();
		try {
			st = getDBConnection().createStatement();
			rs = st.executeQuery(teamfetchQuery);
			while (rs.next()) {
				teamList.add(rs.getString(1));
				venueList.add(rs.getString(2));
			}
			logger.info("#TournamentGenerator#createFixture#method#TeamListSize" + teamList.size());
			logger.info("#TournamentGenerator#createFixture#method#VenueListSize" + venueList.size());
			generateSchedule(parsedDate, teamList, venueList);
		} catch (SQLException e) {

			logger.error("#TournamentGenerator#createFixture#method#SQLException" + e.getMessage());
		}
		finally{
			closeConnection();
		}
		logger.info("#TournamentGenerator#createFixture#method ends");

	}

	/**
	 * Generate schedule.
	 *
	 * @param startDate
	 *            the start date
	 * @param teamList
	 *            the team list
	 * @param venueList
	 *            the venue list
	 */
	private void generateSchedule(Date startDate, ArrayList<String> teamList, ArrayList<String> venueList) {
		logger.info("#TournamentGenerator#generateSchedule#method starts");
		final String insertFixtureQuery = "INSERT INTO FIXTURE_DETAILS (MATCH_DATE,MATCH_NAME,VENUE_NAME) "
				+ "VALUES (?,?,?)";
		Tournament tournament = new Tournament(teamList.size(), venueList.size(), startDate);
		for (String teamName : teamList) {
			tournament.addTeam(teamName);
		}
		logger.info("#TournamentGenerator#generateSchedule#method#addedTeam");
		for (String venueName : venueList) {
			tournament.addVenue(venueName);
		}
		logger.info("#TournamentGenerator#generateSchedule#method#addedVenue");
		tournament.setNumberOfRounds(totalRounds);
		ArrayList<Match> matches = tournament.getMatches();
		logger.info("#TournamentGenerator#generateSchedule#method#matchesSize" + matches.size());

		try {
			ps = getDBConnection().prepareStatement(insertFixtureQuery);
			for (Match match : matches) {

				ps.setDate(1, new java.sql.Date(match.getMatchDate().getTime()));
				ps.setString(2, match.getFirstTeam().getTeamName() + " vs " + match.getSecondTeam().getTeamName());
				ps.setString(3, match.getVenue().getVenueName());
				ps.executeUpdate();
			}

		} catch (SQLException e) {
			logger.error("#TournamentGenerator#generateSchedule#method#SQLException2" + e.getMessage());
		}
		finally{
			closeConnection();
		}

		logger.info("#TournamentGenerator#generateSchedule#method ends");
	}

	/**
	 * Gets the schedules.
	 *
	 * @return the schedules
	 */
	public List<TournamentDetails> getSchedules() {
		logger.info("#TournamentGenerator#getSchedule#method starts");
		final String fetchfixtureQuery = "SELECT * FROM FIXTURE_DETAILS";
		List<TournamentDetails> tournamentDetailsList = new ArrayList<TournamentDetails>();

		try {
			st = getDBConnection().createStatement();
			rs = st.executeQuery(fetchfixtureQuery);
			while (rs.next()) {
				TournamentDetails tournamentDetail = new TournamentDetails();
				tournamentDetail.setMatchDate(rs.getDate(1));
				tournamentDetail.setMatch(rs.getString(2));
				tournamentDetail.setVenue(rs.getString(3));
				tournamentDetailsList.add(tournamentDetail);
			}
		} catch (SQLException e) {

			logger.info("#TournamentGenerator#getSchedule#Exception" + e.getMessage());
		}
		finally{
			closeConnection();
		}
		logger.info("#TournamentGenerator#getSchedule#method ends");
		return tournamentDetailsList;

	}
	
	/**
	 * Close connection.
	 */
	private void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

}
