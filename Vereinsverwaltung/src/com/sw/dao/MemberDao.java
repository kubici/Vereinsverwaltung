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

import com.sw.beans.Member;

public class MemberDao 
{
	
	private Connection MemberConnection = null;
	
	public MemberDao()
	{
		Connection connection = DBConnection.getConnectionToDatabase();
		
		if(connection != null)
		{
			this.MemberConnection = connection;
		}
		else
		{
			System.out.println("MemberConnection(): No Connection to Database possible");
		}
	}
	
	// Read Mitglieder from Database and return them into a ResultSet back to Overview
	public List<Member> readMember()
	{
		ResultSet set = null;
		
		if(this.MemberConnection != null)
		{
			List<Member> memberList = new ArrayList<Member>();
			try
			{
				String sql = "Select * from mitglieder";
				Statement statement = this.MemberConnection.createStatement();
				set = statement.executeQuery(sql);
				
				while(set.next())
				{
					// TODO read the complet database
					String name = set.getString("name");
					String lname = set.getString("lname");
					
					Member m = new Member();
					m.setName(name);
					m.setLname(lname);
					memberList.add(m);
				}
				
			}
			catch(Exception ex)
			{
				System.out.println("Exception readMember():");
				ex.printStackTrace();
			}
			return memberList;
		}
		// No Member found
		System.out.println("No member found");
		return null;

		
		
	}
	// Write Member from register formular into database
	public boolean writeMember(Member member)
	{
		try
		{
			String sql = "Insert into swp_system.mitglieder (name, lname, birth, adressline01, adressline02, postalcode,  city, gender, telefon, email, joinedDate, role) values ( ?, ?,?,?, ?, ?, ?, ?, ?, ?,?, ?)";
			
			PreparedStatement preparedStmt = this.MemberConnection.prepareStatement(sql);
			preparedStmt.setObject(1, member.getName(), Types.VARCHAR);
			preparedStmt.setObject(2, member.getLname(), Types.VARCHAR);
			preparedStmt.setObject(3, member.getBirth(), Types.DATE);
			preparedStmt.setObject(4, member.getAdressline01(), Types.VARCHAR);
			preparedStmt.setObject(5, member.getAdressline02(), Types.VARCHAR);
			preparedStmt.setObject(6, member.getPostalcode(), Types.VARCHAR);
			preparedStmt.setObject(7, member.getCity(), Types.VARCHAR);
			preparedStmt.setObject(8, member.getGender(), Types.VARCHAR);
			preparedStmt.setObject(9, member.getTelefon(), Types.VARCHAR);
			preparedStmt.setObject(10, member.getEmail(), Types.VARCHAR);
			preparedStmt.setObject(11, member.getJoinedDate(), Types.DATE);
			preparedStmt.setObject(12, member.getRole(), Types.VARCHAR);
	
		    preparedStmt.execute();
		}
		catch(SQLException sqlE)
		{
			System.out.println("SQLException writeMember() : ");
			sqlE.printStackTrace();
			return false;
		}
		catch(Exception ex)
		{
			System.out.println("Exception writeMember() : ");
			ex.printStackTrace();
			return false;
		}
		return true;
	}
}

