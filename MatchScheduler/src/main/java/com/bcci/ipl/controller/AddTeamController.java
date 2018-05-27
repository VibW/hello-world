package com.bcci.ipl.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcci.ipl.dao.impl.TeamDAOImpl;
import com.bcci.ipl.model.Team;
import com.fasterxml.jackson.databind.ObjectMapper;

// TODO: Auto-generated Javadoc
/**
 * The Class AddTeamController.
 */
@Controller
public class AddTeamController {

	/** The team DAO. */
	@Autowired
	TeamDAOImpl teamDAO;

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(AddTeamController.class);

	/**
	 * Adds the team.
	 *
	 * @param request
	 *            the request
	 * @return the string
	 */
	@RequestMapping(value = "/addTeam", method = RequestMethod.POST)
	public @ResponseBody String addTeam(HttpServletRequest request) {
		String returnText = "added";
		try {
			logger.info("#controller#addTeam#method starts");
			Team team = new Team();
			String teamName = request.getParameter("teamName");
			String homeGround = request.getParameter("homeGround");
			logger.info("home ground----->" + homeGround);
			logger.info("Team Name------->" + teamName);
			team.setTeamName(teamName);
			team.setHomeGround(homeGround);
			teamDAO.insertTeam(team);

		} catch (Exception e) {
			returnText = "Error! while saving";
			logger.error("#controller#addTeam#Exception" + e.getMessage());
			return returnText;
		}
		logger.info("#controller#addTeam#method ends");
		return returnText;
	}

	/**
	 * Fetch all teams.
	 *
	 * @return the list
	 */
	@RequestMapping(value = "/getTeams", method = RequestMethod.GET, headers = "Accept=*/*", produces = "application/json")
	public @ResponseBody String fetchAllTeams() {
		logger.info("#controller#fetchAllTeams#method starts");
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		StringBuffer temp = null;
		try {

			logger.info("#controller#fetchAllTeams#List size" + teamDAO.getTeams().size());
			jsonString = mapper.writeValueAsString(teamDAO.getTeams());
			 temp = new StringBuffer(jsonString);
			temp.insert(0, "{");
			temp.append("}");
			temp.insert(1, "\"data\"");
			temp.insert(7, ":");
			System.out.println(temp);
		} catch (Exception e) {
			logger.error("#controller#fetchAllTeams#Exception" + e.getMessage());
		}
		logger.info("#controller#fetchAllTeams#method ends");
		return temp.toString();
	}
	
	
	/**
	 * Removes the team.
	 *
	 * @param request the request
	 * @return the string
	 */
	@RequestMapping(value = "/removeTeam", method = RequestMethod.POST)
	public @ResponseBody String removeTeam(HttpServletRequest request){
		logger.info("#controller#removeTeamTeam#method starts");
		String returnText=null;
		try {
			returnText = "Successfully Deleted Team";
			String id = request.getParameter("id");
			teamDAO.deleteTeam(Integer.parseInt(id));
		} catch (Exception e) {
			returnText = "Error! while deleting";
			logger.error("#controller#removeTeam#Exception" + e.getMessage());
			return returnText;
		}
		logger.info("#controller#removeTeamTeam#method ends");
		return returnText;
		
	}

}
