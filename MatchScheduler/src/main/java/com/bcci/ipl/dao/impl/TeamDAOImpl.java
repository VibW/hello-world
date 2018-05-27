package com.bcci.ipl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcci.ipl.controller.AddTeamController;
import com.bcci.ipl.dao.TeamDAO;
import com.bcci.ipl.model.Team;

// TODO: Auto-generated Javadoc
/**
 * The Class TeamDAOImpl.
 */
public class TeamDAOImpl implements TeamDAO {

	/** The data source. */
	@Autowired
	private DataSource dataSource;

	/** The connection. */
	private Connection connection;
	
	/** The ps. */
	private PreparedStatement ps;
	
	/** The st. */
	private Statement st;
	
	/** The rs. */
	private ResultSet rs;

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(TeamDAOImpl.class);

	/**
	 * Sets the data source.
	 *
	 * @param dataSource the new data source
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * Gets the DB connection.
	 *
	 * @return the DB connection
	 */
	public Connection getDBConnection() {

		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			logger.error("#TeamDAOImpl#getDBConnection#Exception" + e.getMessage());

		}
		return connection;
	}

	/* (non-Javadoc)
	 * @see com.bcci.ipl.dao.TeamDAO#insertTeam(com.bcci.ipl.model.Team)
	 */
	public void insertTeam(Team team) {
		logger.info("#TeamDAOImpl#insertTeam#method starts");
		final String insertQuery = "INSERT INTO TEAM_DETAILS " + "(TEAM_NAME,HOME_GROUND) VALUES (?,?)";

		try {

			ps = getDBConnection().prepareStatement(insertQuery);
			ps.setString(1, team.getTeamName());
			ps.setString(2, team.getHomeGround());
			ps.execute();
		} catch (SQLException e) {
			logger.error("#TeamDAOImpl#insertTeam#Exception" + e.getMessage());
		} finally {
			closeConnection();
		}
		logger.info("#TeamDAOImpl#insertTeam#method ends");
	}

	/* (non-Javadoc)
	 * @see com.bcci.ipl.dao.TeamDAO#deleteTeam(java.lang.String)
	 */
	public void deleteTeam(int id) {
		logger.info("#TeamDAOImpl#deleteTeam#method starts");
		final String delQuery = "DELETE FROM TEAM_DETAILS  WHERE ID = '"+id+"'";

		try {
			ps = getDBConnection().prepareStatement(delQuery);
			//ps.setInt(3, id);
			ps.executeUpdate(delQuery);
			connection.commit();
			logger.info("#TeamDAOImpl#deleteTeam#deleted Team");
		} catch (SQLException e) {

			logger.error("#TeamDAOImpl#deleteTeam#Exception" + e.getMessage());
		} finally {
			closeConnection();
		}
		logger.info("#TeamDAOImpl#deleteTeam#method ends");
	}

	/**
	 * Gets the teams.
	 *
	 * @return the teams
	 */
	public List<Team> getTeams() {
		logger.info("#TeamDAOImpl#getTeams#method starts");
		final String fetchQuery = "SELECT TEAM_NAME,HOME_GROUND,ID from TEAM_DETAILS";
		List<Team> teamList = new ArrayList<Team>();

		try {
			st = getDBConnection().createStatement();
			rs = st.executeQuery(fetchQuery);
			while (rs.next()) {
				Team team = new Team();
				team.setTeamName(rs.getString(1));
				System.out.println("Get Teams------------->"+rs.getString(1));
				System.out.println("Get HG------------->"+rs.getString(2));
				System.out.println("Get ID------------->"+rs.getInt(3));
				team.setHomeGround(rs.getString(2));
				team.setId(rs.getInt(3));
				teamList.add(team);
			}
		} catch (SQLException e) {

			logger.error("#TeamDAOImpl#getTeams#Exception" + e.getMessage());
		} finally {
			closeConnection();
		}
		logger.info("#TeamDAOImpl#getTeams#method ends");
		return teamList;
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
