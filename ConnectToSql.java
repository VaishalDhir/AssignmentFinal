package com.AssignmentFinal.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToSql {
	
	private String  databaseName = "midterm";
	private String  databaseuserName = "admin";
	private String  databasePass = "Vaishal123";
	private String host = "database-3.czap0v6cuyiw.us-east-2.rds.amazonaws.com";
	private String databaseport = "3306";
	
	private String jdbcurl = "jdbc:mysql://"+host+":"+databaseport+"/"+databaseName+"?user="+databaseuserName+"&password="+databasePass;
	
	private Connection con = null;
	
	public Connection getConnection()
	{
		try
		{
			System.out.println("Load Driver");
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			System.out.println("Make Connection To Mysql");
			
			con = DriverManager.getConnection(jdbcurl);
			
			return con;
			
		}catch (ClassNotFoundException e) {
			System.out.println("Connection Not Found error : "+e.getMessage());
		}catch (SQLException e) {
			System.out.println("SQL Exception : "+e.getMessage());
		}
		
		return con;
	}

}
