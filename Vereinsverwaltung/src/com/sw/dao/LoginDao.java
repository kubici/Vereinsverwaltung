package com.sw.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
				
				List<User> userList = new ArrayList<User>();
				
				while(set.next())
				{
					User user = new User();
					user.setPassword(set.getString("pwd"));
					user.setUname(set.getString("username"));
					
					userList.add(user);
				}
				
				// Check if Username or Password is empty and if there is an existing entry
				if((!userToCheck.getUname().isEmpty()) && (!userToCheck.getPassword().isEmpty()))
				{
					for(int i = 0; i < userList.size(); i++)
					{
						if(userList.get(i).getPassword().equals(userToCheck.getPassword()) && userList.get(i).getUname().equals(userToCheck.getUname()))
						{
							return true;
						}
					}
					
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
