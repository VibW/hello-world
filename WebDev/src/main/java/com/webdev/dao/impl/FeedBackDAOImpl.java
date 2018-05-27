package com.webdev.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.webdev.Entities.Feedback;

public class FeedBackDAOImpl implements FeedbackDAO {
	
	DataSource datasource;
	
	
	
	public DataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}

	Connection connection = null;
	PreparedStatement statement=null;

	public void addFeedback(Feedback feedback) {
		String sql = "insert into feedback(name,email,message) values (?,?,?)";
		try{
			connection = this.getDatasource().getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, feedback.getName());
			statement.setString(2, feedback.getEmail());
			statement.setString(3, feedback.getMessage());
			statement.executeQuery();
			statement.close();
		}catch(SQLException e){
			e.printStackTrace();
		}

	}

}
