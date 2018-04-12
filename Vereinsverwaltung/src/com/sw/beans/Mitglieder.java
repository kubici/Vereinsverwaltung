package com.sw.beans;

import java.util.Date;

public class Mitglieder 
{
	public Mitglieder()
	{
		
	}
	
	private String name;
	private String lname;
	private Date birth;
	private String adressline01;
	private String adressline02;
	private String postalcode;
	private String city;
	private String gender;
	private String telefon;
	private String email;
	private Date joinedDate;
	private String role;
	
	
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
	public String getAdressline01() {
		return adressline01;
	}
	public void setAdressline01(String adressline01) {
		this.adressline01 = adressline01;
	}
	public String getAdressline02() {
		return adressline02;
	}
	public void setAdressline02(String adressline02) {
		this.adressline02 = adressline02;
	}
	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
