package com.sw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MitgliederDao 
{
	// Not tested yet!
	// Only implemented
	// TODO Test it!
	
	private Connection MitgliederConnection = null;
	
	public MitgliederDao()
	{
		Connection connection = DBConnection.getConnectionToDatabase();
		
		if(connection != null)
		{
			this.MitgliederConnection = connection;
		}
		else
		{
			System.out.println("MitgliederDao(): No Connection to Database possible");
		}
	}
	
	// Read Mitglieder from Database and return them into a ResultSet back to Overview
	public ResultSet readMitglieder()
	{
		ResultSet set = null;
		
		if(this.MitgliederConnection != null)
		{
			try
			{
				String sql = "Select * from mitglieder";
				Statement statement = this.MitgliederConnection.createStatement();
				set = statement.executeQuery(sql);
				
			}
			catch(Exception ex)
			{
				System.out.println("Exception readMitglieder:");
				ex.printStackTrace();
			}
			return set;
		}
		// No Mitglieder found
		return null;

		
		
	}
	
	public void writeMitglieder(String name, String lname, Date birth, String team)
	{
		try
		{
			// TODO Platzhalter für SQL Script?
			String sql = "Insert into swp_system.mitglieder (id, name, lname, birth, team) values (?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = this.MitgliederConnection.prepareStatement(sql);
			preparedStmt.setString(1, "0");
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, lname);
			preparedStmt.setString(4, birth.toString());
			preparedStmt.setString(5, team);
			
		    preparedStmt.execute();
		}
		catch(SQLException sqlE)
		{
			System.out.println("SQLException wirteMitglieder : ");
			sqlE.printStackTrace();
		}
		catch(Exception ex)
		{
			System.out.println("Exception wirteMitglieder : ");
			ex.printStackTrace();
		}

	}
	
}

