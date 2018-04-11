package com.sw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sw.beans.Mitglieder;

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
	public List<Mitglieder> readMitglieder()
	{
		ResultSet set = null;
		
		if(this.MitgliederConnection != null)
		{
			List<Mitglieder> mitgliederList = new ArrayList<Mitglieder>();
			try
			{
				String sql = "Select * from mitglieder";
				Statement statement = this.MitgliederConnection.createStatement();
				set = statement.executeQuery(sql);
				
				while(set.next())
				{
					String name = set.getString("name");
					String lname = set.getString("lname");
					
					Mitglieder m = new Mitglieder();
					m.setName(name);
					m.setLname(lname);
					mitgliederList.add(m);
				}
				
			}
			catch(Exception ex)
			{
				System.out.println("Exception readMitglieder:");
				ex.printStackTrace();
			}
			return mitgliederList;
		}
		// No Mitglieder found
		System.out.println("No Mitglieder found");
		return null;

		
		
	}
	
	public boolean writeMitglieder(String name, String lname, Date birth, String team)
	{
		try
		{
			// TODO Platzhalter für SQL Script?
			String sql = "Insert into swp_system.mitglieder (name, lname, birth, team) values ( ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = this.MitgliederConnection.prepareStatement(sql);
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, lname);
			preparedStmt.setString(3, birth.toString());
			preparedStmt.setString(4, team);
			
		    preparedStmt.execute();
		}
		catch(SQLException sqlE)
		{
			System.out.println("SQLException wirteMitglieder : ");
			sqlE.printStackTrace();
			return false;
		}
		catch(Exception ex)
		{
			System.out.println("Exception wirteMitglieder : ");
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	
}

