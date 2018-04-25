package com.sw.beans;


public class User 
{
	// TODO can be deleted after testing the application 
	private String password;
	private String uname;
	private int id;
	
	public User(String uname, String password, int id)
	{
		this.uname = uname;
		this.password = password;
		this.id = id;
	}
	
	public User(String uname, String password)
	{
		this.uname = uname;
		this.password = password;
	}
	
	public User()
	{
		// Test data
		uname = "test1";
		password = "test2";
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public void setId(int id)
	{
		this.id = id;
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
