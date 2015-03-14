package com.sudhan.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {

	public static Connection getConnection() throws SQLException, ClassNotFoundException {

	    Connection conn = null;

	       Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection("jdbc:mysql://anirudhramnath.info/twitter_db?user=root&password=hd7850");
	      
	    return conn;
	}
}
