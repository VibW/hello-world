package com.webdev.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.webdev.Entities.User;

public class UserDAOImpl implements UserDAO {
	
	DataSource datasource;
	
	
	
	public DataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}

	Connection connection = null;
	PreparedStatement statement=null;
	
	public void addUser(User user) {
		String sql = "insert into Registration(FullName,Password,Email) values (?,?,?)";
		try{
			connection = this.getDatasource().getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1,user.getFullName() );
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmail());
			statement.executeUpdate();
			statement.close();
		}catch(SQLException e){
			e.printStackTrace();
		}

	}

}
