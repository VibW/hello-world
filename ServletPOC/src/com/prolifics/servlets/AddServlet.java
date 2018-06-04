package com.prolifics.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddServlet extends HttpServlet {
	
	public void service(HttpServletRequest req,HttpServletResponse res) {
		try {
			PrintWriter writer=res.getWriter();
			writer.println(Integer.parseInt(req.getParameter("num1"))+Integer.parseInt(req.getParameter("num1")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
