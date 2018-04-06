package com.sw.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sw.beans.User;

public class LoginDao 
{
	public boolean checkUser(User userToCheck)
	{
		Connection connection = DBConnection.getConnectionToDatabase();
		
		if(connection != null)
		{
			try
			{
				String sql = "Select * from roles where username like '%" + userToCheck.getUname() + "%' and pwd like '%"+ userToCheck.getPassword() + "%'";
				Statement statement = connection.createStatement();
				ResultSet set = statement.executeQuery(sql);
				// Check if Username or Password is empty and if there is an existing entry
				if(set.first() == true && (!userToCheck.getUname().isEmpty()) && (!userToCheck.getPassword().isEmpty()))
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
