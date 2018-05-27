package com.bcci.ipl.dao;

import java.util.List;

import com.bcci.ipl.model.Team;

// TODO: Auto-generated Javadoc
/**
 * The Interface TeamDAO.
 */
public interface TeamDAO {
	
	/**
	 * Insert team.
	 *
	 * @param team the team
	 */
	public void insertTeam(Team team);
	
	/**
	 * Delete team.
	 *
	 * @param id the id
	 */
	public void deleteTeam(int id);
	
	/**
	 * Gets the teams.
	 *
	 * @return the teams
	 */
	public List<Team> getTeams();

}
