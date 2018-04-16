package com.sw.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection 
{
	private static String dbUser = "root";
	private static String dbPassword = "root";
	
	public static Connection getConnectionToDatabase()
	{
		Connection connection = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("MySQL JDBC Driver Registered!");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swp_system?autoReconnect=true&useSSL=false",dbUser,dbPassword);
			
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println("No MySQL JDBC Driver");
			cnfe.printStackTrace();
		}
		catch(SQLException sqle)
		{
			System.out.println("Connection Failed! Check output console");
			sqle.printStackTrace();
		}
		
		if(connection != null)
		{
			System.out.println("Connection made to DB!");
		}
		return connection;
	}
}
