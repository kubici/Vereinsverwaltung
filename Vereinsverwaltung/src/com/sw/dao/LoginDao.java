package com.sw.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sw.beans.User;
import com.sw.security.HashText;

public class LoginDao 
{
	public boolean checkUser(User userToCheck)
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
	
	public PreparedStatement createPreparedStatement(Connection con, User userToCheck) throws SQLException, NoSuchAlgorithmException {
		String sql = "Select * from roles where username = ? AND pwd = ?";
		PreparedStatement pstatement = con.prepareStatement(sql);
		pstatement.setString(1, userToCheck.getUname());
		HashText ht = new HashText();
		pstatement.setString(2, ht.sha256(userToCheck.getPassword()));
		return pstatement;
	}
}
