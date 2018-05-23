package com.webdev.Entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/*@Component
@Scope("prototype")*/
public class Feedback {
	private String Name;
	private String Email;
	private String Message;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	
	
	
	

}
