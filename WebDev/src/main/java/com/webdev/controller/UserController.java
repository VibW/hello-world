package com.webdev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webdev.Entities.User;
import com.webdev.dao.impl.UserDAO;

@Controller
public class UserController {
	
	@Autowired
	UserDAO userDAO;
	
	@RequestMapping("/signup")
	public String getSignUpForm(){
		return "SignUp";
	}
	
	@RequestMapping("/addUser")
	public String addUser(@ModelAttribute("user") User user){
		userDAO.addUser(user);
		return "welcome";
	}

}
