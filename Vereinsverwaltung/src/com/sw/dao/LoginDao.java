package com.sw.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sw.beans.User;

public class LoginDao 
{
	// Test Method
	public boolean checkUser(User userToCheck)
	{
		Connection connection = DBConnection.getConnectionToDatabase();
		
		if(connection != null)
		{
			try
			{
				String sql = "Select * from login where uname like '%" + userToCheck.getUname() + "%' and pass like '%"+ userToCheck.getPassword() + "%'";
				Statement statement = connection.createStatement();
				ResultSet set = statement.executeQuery(sql);
				if(set.first() == true)
				{
					return true;
				}
			}
			catch(Exception ex)
			{
				System.out.println("Something went wrong in checkUser()");
				ex.printStackTrace();
			}
		}
		return false;
	}
}
