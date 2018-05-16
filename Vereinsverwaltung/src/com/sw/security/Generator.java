package com.sw.security;

import java.security.SecureRandom;

import com.sw.beans.Member;
import com.sw.servlets.MemberDashboardServlet;

public class Generator {
	
	public String generateUserName(Member member)
	{
		String generatedUserName = null;
		String memberName = member.getFirstName();// return the firstname
		String memberLname = member.getLastName();// return the Lastname
		int memberId = MemberDashboardServlet.lstMemberCounter; // return the memberId

		generatedUserName = memberName.substring(0,1)+""+memberLname+""+memberId; // firstletterofFirstname.lastname.RandomId
		
		return generatedUserName; // generatedUserName -> ([erster buchst. vorname][nachname][id])
	}
	
	public String generatePassword()
	{

		SecureRandom random = new SecureRandom(); 
		String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		
		StringBuilder passwordgenerator = new StringBuilder();
		for(int i =1; i<=8;i++){
			passwordgenerator.append(letters.charAt(random.nextInt(letters.length())));
			
		}

		String tempPassword = passwordgenerator.toString();
		return tempPassword;
		
		
	}
}
