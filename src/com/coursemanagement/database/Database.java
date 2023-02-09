package com.coursemanagement.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	final private String url = "jdbc:mysql://localhost:3306/coursemanagementsystem";
	final private String user = "root";
	final private String password = "12345678";
	Connection connection;
	
	public Connection getConnection() {
		//load driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url,user,password);
			if(connection != null) {
				return connection;
			}
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		return null;
	}
	
	
}

