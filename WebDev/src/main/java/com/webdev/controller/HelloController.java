package com.webdev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HelloController {
@RequestMapping("/Home")
public String getHomePage(){
	return "index";
}
}
