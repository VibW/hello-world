package com.journaldev.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SizeController {
	
	@RequestMapping(value="/welcome")
	public ModelAndView helloWorld(){
		
		ModelAndView model = new ModelAndView("welcome");
		return model;
	}
	
	@RequestMapping(value="/insertSize",method = RequestMethod.POST)
	public void insertTeamSize(@RequestParam("teamsize") String id){
		System.out.println("Size------->"+id);
		//ModelAndView model = new ModelAndView("AddTeam");
		
		//return model;
	}

}
