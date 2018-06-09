package com.sw.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sw.beans.Member;
import com.sw.security.HashText;
/**
 * 
 * @author tobi
 *
 */
public class LoginDao 
{
	public boolean checkUser(Member userToCheck)
	{		
		try (Connection connection = DBConnection.getConnectionToDatabase();
				PreparedStatement pstatement = createPreparedStatement(connection, userToCheck);
				ResultSet set = pstatement.executeQuery();)
		{
			
			if (set.next()){
				return true;
			}

		}
		catch(Exception e)
		{
			System.out.println("Something went wrong in checkUser()");
			e.printStackTrace();
		}
		return false;
	}
	
	public PreparedStatement createPreparedStatement(Connection con, Member userToCheck) throws SQLException, NoSuchAlgorithmException {
		String sql = "Select * from MEMBER where username = ? AND password = ?";
		PreparedStatement pstatement = con.prepareStatement(sql);
		pstatement.setString(1, userToCheck.getUsername());
		HashText ht = new HashText();
		pstatement.setString(2, ht.sha256(userToCheck.getPassword()));
		return pstatement;
	}
}
