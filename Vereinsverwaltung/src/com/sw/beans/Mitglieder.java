package com.sw.beans;

import java.util.Date;

public class Mitglieder 
{
	public Mitglieder()
	{
		
	}
	private int id;
	private String name;
	private String lname;
	private Date birth;
	private String team;
	
	// TODO Create Getter and Setter for attributes
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}

	
}
