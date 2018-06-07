package com.sw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.sw.beans.Member;
import com.sw.security.ParseDate;
/**
 * 
 * @author tobi
 *
 */
public class MemberDao 
{	
	// read Member with the given sql Query
	
	public List<Member> readMember() {
		
		List<Member> memberList = new ArrayList<Member>();
		String sql = "Select * from MEMBER where member_id > 1";
		
		try (	Connection connection = DBConnection.getConnectionToDatabase();
				PreparedStatement pstatement = connection.prepareStatement(sql);
				) {

			ResultSet set = pstatement.executeQuery(sql);
			ParseDate parse = new ParseDate();
			
			while(set.next()) {
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
				member.setBirth(parse.autoConvert(birth));
				member.setGender(gender);
				member.setAdressline(address_line);
				member.setAdresslineAdd(address_add);
				member.setPostCode(post_code);
				member.setCity(city);
				member.setPhoneNumber(phone_number);
				member.setEmailAddress(email_address);
				member.setEntryDate(parse.autoConvert(entry_date)); 
				
				memberList.add(member);
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return memberList;		
	}
	
	// readMember with a customized sql Query
	
	public List<Member> readMember(String sql) {
		
		List<Member> memberList = new ArrayList<Member>();
		// String sql = "Select * from MEMBER where member_id > 1";
		
		try (	Connection connection = DBConnection.getConnectionToDatabase();
				PreparedStatement pstatement = connection.prepareStatement(sql);
				) {

			ResultSet set = pstatement.executeQuery(sql);
			ParseDate parse = new ParseDate();
			
			while(set.next()) {
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
				member.setBirth(parse.autoConvert(birth));
				member.setGender(gender);
				member.setAdressline(address_line);
				member.setAdresslineAdd(address_add);
				member.setPostCode(post_code);
				member.setCity(city);
				member.setPhoneNumber(phone_number);
				member.setEmailAddress(email_address);
				member.setEntryDate(parse.autoConvert(entry_date)); 
				
				memberList.add(member);
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return memberList;		
	}
	
	public boolean writeMember(Member member) {
		
		try	(	Connection connection = DBConnection.getConnectionToDatabase();
				PreparedStatement pstatement = createPrepWriteMember(connection, member);
				){
			
		    pstatement.execute();
		}
		catch(SQLException sqlE) {
			sqlE.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	private PreparedStatement createPrepWriteMember(Connection connection, Member member) throws SQLException {
		String sql = "Insert into swp_system.MEMBER (first_name, last_name, birth_date, address_line, address_add, post_code,  city, gender, phone_number, email_address, entry_date, username, password) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStmt = connection.prepareStatement(sql);
		
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
		
		return preparedStmt;
	}

	public void deleteMember(Member member) {
		String sql ="DELETE FROM swp_system.MEMBER WHERE member_Id = ?";

		try (	Connection connection = DBConnection.getConnectionToDatabase();
				PreparedStatement pstatement = connection.prepareStatement(sql);
				) {
			pstatement.setObject(1, member.getMemberId(), Types.INTEGER);
			pstatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	public boolean editMember(Member member)
	{
		try (	Connection connection = DBConnection.getConnectionToDatabase();
				PreparedStatement pstatement = createPrepEditMember(connection, member);
				) {
		
			pstatement.executeUpdate();
		}
		catch(SQLException sqlE) {
			
			sqlE.printStackTrace();
			return false;
		}
		return true;
	}
	
	private PreparedStatement createPrepEditMember(Connection connection, Member member) throws SQLException {
		if (member.getPassword() != null) {
			
			String sql = "Update swp_system.MEMBER SET first_name = ?, last_name = ?, birth_date = ?, address_line = ?, address_add = ?, post_code = ?,  city= ?, gender = ?, phone_number = ?, email_address = ?, password = ? Where username = ?";
			
			PreparedStatement preparedStmt = connection.prepareStatement(sql);
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
	
		    return preparedStmt;
		} else {
			
			String sql = "Update swp_system.MEMBER SET first_name = ?, last_name = ?, birth_date = ?, address_line = ?, address_add = ?, post_code = ?,  city= ?, gender = ?, phone_number = ?, email_address = ? Where username = ? ";
			
			PreparedStatement preparedStmt = connection.prepareStatement(sql);
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
			
		    return preparedStmt;	
		}
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
				member.setBirth(parser.autoConvert(birth));
				member.setGender(gender);
				member.setAdressline(address_line);
				member.setAdresslineAdd(address_add);
				member.setPostCode(post_code);
				member.setCity(city);
				member.setPhoneNumber(phone_number);
				member.setEmailAddress(email_address);
				member.setEntryDate(parser.autoConvert(entry_date)); 
				
				return member;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}

