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

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
				String sql = "Select * from MEMBER where member_id > 1"; // read all Members without Admin
				Statement statement = this.MemberConnection.createStatement();
				set = statement.executeQuery(sql);
				
				
				while(set.next())
				{
					String firstName = set.getString("first_name");
					String lastName = set.getString("last_name");
					int memberId = set.getInt("member_id");
					
					Member member = new Member();
					member.setName(firstName);
					member.setLastName(lastName);
					member.setMemberId(memberId);
					// TODO load more attributes from database, to display them in the table
					memberList.add(member);
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
	
	
	public boolean writeMemberExclusive(Member member)
	{
		// TODO Write writeMemberExclusive-Method to have a second method with password and the normal method without saving the password
		throw new NotImplementedException();
	}
	
	
	
	// Write Member from register formular into database
	public boolean writeMember(Member member)
	{
		try
		{
			String sql = "Insert into swp_system.MEMBER (first_name, last_name, birth_date, address_line, address_add, post_code,  city, gender, phone_number, email_address, entry_date, username, password) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = this.MemberConnection.prepareStatement(sql);
			preparedStmt.setObject(1, member.getFirstName(), Types.VARCHAR);
			preparedStmt.setObject(2, member.getLastName(), Types.VARCHAR);
			preparedStmt.setObject(3, member.getBirth(), Types.DATE);
			preparedStmt.setObject(4, member.getAdressline(), Types.VARCHAR);
			preparedStmt.setObject(5, member.getAdresslineAdd(), Types.VARCHAR);
			preparedStmt.setObject(6, member.getPostCode(), Types.VARCHAR);
			preparedStmt.setObject(7, member.getCity(), Types.VARCHAR);
			preparedStmt.setObject(8, member.getGender(), Types.VARCHAR);
			preparedStmt.setObject(9, member.getPhoneNumber(), Types.VARCHAR);
			preparedStmt.setObject(10, member.getEmailAddress(), Types.VARCHAR);
			preparedStmt.setObject(11, member.getEntryDate(), Types.DATE);
			preparedStmt.setObject(12, member.getUsername() , Types.VARCHAR);
			preparedStmt.setObject(13, member.getPassword(), Types.VARCHAR);
	
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
	
	
	public boolean deleteMember(Member member)
	{
		return true;
	}
	
	public boolean editMember(Member member)
	{
		return true;
	}
}

