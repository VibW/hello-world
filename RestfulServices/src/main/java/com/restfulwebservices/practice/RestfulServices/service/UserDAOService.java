package com.restfulwebservices.practice.RestfulServices.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.restfulwebservices.practice.RestfulServices.model.User;

@Component
public class UserDAOService {
	
	private static List<User> userList = new ArrayList<>();
	private static int userCount = 8;
	
	static{
		userList.add(new User("Vibhor", 1, new Date()));
		userList.add(new User("Bipin", 2, new Date()));
		userList.add(new User("Romi", 3, new Date()));
	}
	
	public List<User> findUsers(){
		return userList;
	}
	
	public User saveUser(User user){
		if (user.getId()==null) {
			user.setId(++userCount);
		}
		userList.add(user);
		return user;
	}
	
	public User findUser(int id){
		for(User user : userList){
			if(user.getId()==id){
				return user;
			}
		}
		return null;
	}

}
