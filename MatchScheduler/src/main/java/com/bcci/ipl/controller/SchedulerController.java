package com.bcci.ipl.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcci.ipl.dao.impl.TeamDAOImpl;
import com.bcci.ipl.dao.impl.TournamentGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class SchedulerController {

	@Autowired
	TournamentGenerator tournamentGenerator;

	private static final Logger logger = Logger.getLogger(SchedulerController.class);

	@RequestMapping(value = "/createSchedules", method = RequestMethod.POST, headers = "Accept=*/*", produces = "application/json")
	public @ResponseBody String createSchedules(HttpServletRequest request) {
		logger.info("#controller#createSchedules#method starts");
		String startDate = request.getParameter("fromDate");
		
		try {
			tournamentGenerator.createFixtures(startDate);
			logger.info("#controller#createSchedules#method#fromDate" + startDate);

		} catch (Exception e) {
			logger.error("#controller#createSchedules#method#Exception" + e.getMessage());
		}
		logger.info("#controller#createSchedules#method ends");
		return "created";
	}

	@RequestMapping(value = "/getSchedules", method = RequestMethod.GET, headers = "Accept=*/*", produces = "application/json")
	public @ResponseBody String generateSchedule(HttpServletRequest request) {

		logger.info("#controller#getSchedules#method starts");

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		StringBuffer temp = null;
		try {
			logger.info("#controller#getSchedules#method#tournamentListSize"+tournamentGenerator.getSchedules().size());
			jsonString = mapper.writeValueAsString(tournamentGenerator.getSchedules());
			temp = new StringBuffer(jsonString);
			temp.insert(0, "{");
			temp.append("}");
			temp.insert(1, "\"data\"");
			temp.insert(7, ":");
			System.out.println(temp);
		} catch (Exception e) {
			logger.error("#controller#createSchedule#method#Exception" + e.getMessage());
		}
		logger.info("#controller#getSchedules#method ends");
		return temp.toString();
	}

}
