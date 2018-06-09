package com.sw.beans;

import java.util.Date;
/**
 * 
 * @author tobi
 *
 */

public class Member 
{
	// default constructor
	public Member()
	{
		
	}
	private int memberId;
	private String username;
	private String password;
	private String new_password;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String adressline;
	private String adresslineAdd;
	private String postCode;
	private String city;
	private String gender;
	private String phoneNumber;
	private String emailAddress;
	private Date entryDate;
	
	
	public String getPassword()
	{
		return this.password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getNewPassword()
	{
		return this.new_password;
	}
	
	public void setNewPassword(String new_password)
	{
		this.new_password = new_password;
	}
	
	public String getUsername()
	{
		return this.username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public int getMemberId()
	{
		return this.memberId;
	}
	
	public void setMemberId(int memberId)
	{
		this.memberId = memberId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirth() {
		return birthDate;
	}
	public void setBirth(Date birth) {
		this.birthDate = birth;
	}
	public String getAdressline() {
		return adressline;
	}
	public void setAdressline(String adressline) {
		this.adressline = adressline;
	}
	public String getAdresslineAdd() {
		return adresslineAdd;
	}
	public void setAdresslineAdd(String adresslineAdd) {
		this.adresslineAdd = adresslineAdd;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
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
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public Date getEntryDate() {
		return this.entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}	
	
}
