package com.restfulwebservices.practice.RestfulServices.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.restfulwebservices.practice.RestfulServices.model.HelloWorldBean;
import com.restfulwebservices.practice.RestfulServices.model.User;

@RestController
public class UserController {
	static List<User> userList;
	static {
		userList = new ArrayList<User>();
	}
	
	
	@GetMapping("/getUser")
	public User displayUser(){
		return new User("Vibhor",8);
	}
	
	@PostMapping("/addUser")
	public void addUser(@RequestBody User user){
		userList.add(user);
		
	}
	
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean getHelloWorldBean(){
		return new HelloWorldBean("Hello World from bean");
	}

}
