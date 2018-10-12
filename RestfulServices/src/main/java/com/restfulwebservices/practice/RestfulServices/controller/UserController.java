package com.restfulwebservices.practice.RestfulServices.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.restfulwebservices.practice.RestfulServices.model.HelloWorldBean;
import com.restfulwebservices.practice.RestfulServices.model.User;
import com.restfulwebservices.practice.RestfulServices.service.UserDAOService;

@RestController
public class UserController {

	@Autowired
	public UserDAOService userDAOService;

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userDAOService.findUsers();
	}

	@GetMapping("/user/{id}")
	public User retrieveUser(@PathVariable int id) {
		return userDAOService.findUser(id);
	}

	@PostMapping("createUser")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User savedUser = userDAOService.saveUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
