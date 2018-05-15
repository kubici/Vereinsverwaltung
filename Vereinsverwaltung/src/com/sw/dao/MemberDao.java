package com.sw.dao;

import java.security.KeyRep.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.sw.beans.Member;
import com.sw.security.ParseDate;

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
				
				ParseDate parse = new ParseDate();
				
				while(set.next())
				{
					String username = set.getString("username");
					String firstName = set.getString("first_name");
					String lastName = set.getString("last_name");
					String birth = set.getString("birth_date");
					String gender = set.getString("gender");
					String address_line = set.getString("address_line");
					String address_add = set.getString("address_add");
					String post_code = set.getString("post_code");
					String city = set.getString("city");
					String phone_number = set.getString("phone_number");
					String email_address = set.getString("email_address");
					String entry_date = set.getString("entry_date");
					int memberId = set.getInt("member_id");
					
					Member member = new Member();
					member.setUsername(username);
					member.setName(firstName);
					member.setLastName(lastName);
					member.setMemberId(memberId);
					member.setBirth(parse.convert(birth));
					member.setGender(gender);
					member.setAdressline(address_line);
					member.setAdresslineAdd(address_add);
					member.setPostCode(post_code);
					member.setCity(city);
					member.setPhoneNumber(phone_number);
					member.setEmailAddress(email_address);
					member.setEntryDate(parse.convert(entry_date)); 
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
	
	
	public void deleteMember(Member member) throws SQLException, Exception
	{
			String sql ="DELETE FROM swp_system.MEMBER WHERE member_Id = ?";
			PreparedStatement preparedStmt = this.MemberConnection.prepareStatement(sql);
			preparedStmt.setObject(1, member.getMemberId(), Types.INTEGER);
			preparedStmt.executeUpdate();
		    preparedStmt.close();
	}
	
	public boolean editMember(Member member)
	{
		try
		{
			if (member.getPassword() != null) {
				String sql = "Update swp_system.MEMBER SET first_name = ?, last_name = ?, birth_date = ?, address_line = ?, address_add = ?, post_code = ?,  city= ?, gender = ?, phone_number = ?, email_address = ?, password = ? Where username = ?";
				
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
				preparedStmt.setObject(11, member.getPassword(), Types.VARCHAR);
				preparedStmt.setObject(12, member.getUsername(), Types.VARCHAR);
		
			    preparedStmt.executeUpdate();
			    preparedStmt.close();
			} else {
				String sql = "Update swp_system.MEMBER SET first_name = ?, last_name = ?, birth_date = ?, address_line = ?, address_add = ?, post_code = ?,  city= ?, gender = ?, phone_number = ?, email_address = ? Where username = ? ";
				
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
				preparedStmt.setObject(11, member.getUsername(), Types.VARCHAR);
				
			    preparedStmt.executeUpdate();
			    preparedStmt.close();	
			}
		}
		catch(SQLException sqlE)
		{
			System.out.println("SQLException editMember() : ");
			sqlE.printStackTrace();
			return false;
		}
		catch(Exception ex)
		{
			System.out.println("Exception editMember() : ");
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	
	public int getMemberIdByUsername (String username) {
		String sql = "SELECT * FROM swp_system.MEMBER WHERE username = ?";
		try (	Connection con = DBConnection.getConnectionToDatabase();
				PreparedStatement pstatement = con.prepareStatement(sql);
				) {
			pstatement.setString(1, username);
			ResultSet set = pstatement.executeQuery();
			
			while(set.next()) {
				return set.getInt("member_id");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public Member getMemberByUsername (String username) {
		String sql = "SELECT * FROM swp_system.MEMBER WHERE username = ?";
		try(	Connection con = DBConnection.getConnectionToDatabase();
				PreparedStatement pstatement = con.prepareStatement(sql);
				) {
			pstatement.setString(1, username);
			ResultSet set = pstatement.executeQuery();
			
			while(set.next())
			{
				String user = set.getString("username");
				String firstName = set.getString("first_name");
				String lastName = set.getString("last_name");
				String birth = set.getString("birth_date");
				String gender = set.getString("gender");
				String address_line = set.getString("address_line");
				String address_add = set.getString("address_add");
				String post_code = set.getString("post_code");
				String city = set.getString("city");
				String phone_number = set.getString("phone_number");
				String email_address = set.getString("email_address");
				String entry_date = set.getString("entry_date");
				int memberId = set.getInt("member_id");
				
				Member member = new Member();
				member.setUsername(user);
				member.setName(firstName);
				member.setLastName(lastName);
				member.setMemberId(memberId);
				
				ParseDate parser = new ParseDate();
				member.setBirth(parser.convert(birth));
				member.setGender(gender);
				member.setAdressline(address_line);
				member.setAdresslineAdd(address_add);
				member.setPostCode(post_code);
				member.setCity(city);
				member.setPhoneNumber(phone_number);
				member.setEmailAddress(email_address);
				member.setEntryDate(parser.convert(entry_date)); 
				
				return member;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}

