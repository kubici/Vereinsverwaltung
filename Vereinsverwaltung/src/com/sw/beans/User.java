package com.sw.beans;


public class User 
{
	
	private String password;
	private String uname;
	
	public User(String uname, String password)
	{
		uname = "test1";
		password = "test2";
		//this.uname = uname;
		//this.password = password;
		// TODO ID hinzufügen
	}

	public User()
	{
		uname = "test1";
		password = "test2";
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
