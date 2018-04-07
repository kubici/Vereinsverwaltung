package com.sw.beans;

public class User 
{
	
	private String password;
	private String uname;
	
	public User(String uname, String password)
	{
		this.uname = uname;
		this.password = password;
		// TODO ID hinzufügen
	}

	public User()
	{
		
	}

	public String getUname()
	{
		return uname;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setUname(String uname)
	{
		this.uname = uname;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
}
