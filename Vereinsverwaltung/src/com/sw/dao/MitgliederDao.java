package com.sw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
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
	
	public boolean writeMitglieder(Mitglieder mitglieder)
	{
		try
		{
			// insert into swp_system.mitglieder (name, lname, birth, adressline01, postalcode, city, gender, telefon, email, role) values ("Tobias", "Kolb", str_to_date('15-10-1995', '%d-%m-%Y'), "Itzgrund 5", "95512", "Bayreuth", "Männlich", "01601651965","kolb.to95@outlook.de", str_to_date('01.01.2017', '%d-%m-%Y'), "admin");

			// TODO Platzhalter für SQL Script?
			String sql = "Insert into swp_system.mitglieder (name, lname, birth, adressline01, adressline02, postalcode,  city, gender, telefon, email, joinedDate, role) values ( ?, ?,?,?, ?, ?, ?, ?, ?, ?,?, ?)";
			
			PreparedStatement preparedStmt = this.MitgliederConnection.prepareStatement(sql);
			preparedStmt.setObject(1, mitglieder.getName(), Types.VARCHAR);
			preparedStmt.setObject(2, mitglieder.getLname(), Types.VARCHAR);
			preparedStmt.setObject(3, mitglieder.getBirth(), Types.DATE);
			preparedStmt.setObject(4, mitglieder.getAdressline01(), Types.VARCHAR);
			preparedStmt.setObject(5, mitglieder.getAdressline02(), Types.VARCHAR);
			preparedStmt.setObject(6, mitglieder.getPostalcode(), Types.VARCHAR);
			preparedStmt.setObject(7, mitglieder.getCity(), Types.VARCHAR);
			preparedStmt.setObject(8, mitglieder.getGender(), Types.VARCHAR);
			preparedStmt.setObject(9, mitglieder.getTelefon(), Types.VARCHAR);
			preparedStmt.setObject(10, mitglieder.getEmail(), Types.VARCHAR);
			preparedStmt.setObject(11, mitglieder.getJoinedDate(), Types.DATE);
			preparedStmt.setObject(12, mitglieder.getRole(), Types.VARCHAR);
			
			
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

