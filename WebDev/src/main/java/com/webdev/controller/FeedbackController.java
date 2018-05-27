package com.webdev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webdev.Entities.Feedback;
import com.webdev.dao.impl.FeedBackDAOImpl;
import com.webdev.dao.impl.FeedbackDAO;

@Controller
@RequestMapping("/")
public class FeedbackController {
	/*@Autowired
	FeedbackDAO fdao = new Feed;
	@Autowired
	Feedback fb;*/
	
	/*private FeedbackDAO feedbackDAOImpl;
	FeedBackDAOImpl fdao = new FeedBackDAOImpl();*/
	/*public void setFeedBackDAOImpl(FeedbackDAO feedbackDAOImpl) {
		this.feedbackDAOImpl = feedbackDAOImpl;
	}*/
	
	@Autowired
	FeedbackDAO feedbackDAO;
	
	@RequestMapping("/feedback")
	public String getFeedback(){
		return "feedback";
	}
/*	public FeedbackDAO getFeedbackDAOImpl() {
		return feedbackDAOImpl;
	}
	public void setFeedbackDAOImpl(FeedbackDAO feedbackDAOImpl) {
		this.feedbackDAOImpl = feedbackDAOImpl;
	}*/
	@RequestMapping("/addFeedback")
	public String addFeedback(Feedback feedback){
		feedbackDAO.addFeedback(feedback);
		return "welcome";
	}

}
