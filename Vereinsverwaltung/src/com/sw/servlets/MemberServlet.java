package com.sw.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.spi.DateFormatProvider;
import java.util.Date;
import java.util.IllegalFormatException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sw.beans.Member;
import com.sw.controller.MemberDashboardController;
import com.sw.dao.MemberDao;
import com.sw.security.HashText;

@WebServlet("/registerMember")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		// Get data from registerMember.jsp
		// Create an object member with the given attributes
		Member member = new Member();

		member.setName(request.getParameter("first_name"));
		member.setLastName(request.getParameter("last_name"));
		
		// Parse birth into Date object
		String tempBirth = request.getParameter("birth_date");
		DateFormat dateFormatBirth = new SimpleDateFormat("dd.MM.yyyy");
		try
		{
			Date birthDate = (Date) dateFormatBirth.parse(tempBirth);
			member.setBirth(birthDate);
		}
		catch(IllegalFormatException ife)
		{
			System.out.println("MemberServlet.java - doPost() - Problem with DateFormat: BirthDate");
			ife.printStackTrace();
		}
		catch(Exception ex)
		{
			System.out.println("MemberServlet.java - doPost() - Problem with DateFormat: BirthDate");
			ex.printStackTrace();
		}
		
		member.setGender(request.getParameter("gender"));
		member.setEmailAddress(request.getParameter("email_address"));
		member.setPhoneNumber(request.getParameter("phone_number"));
		member.setAdressline(request.getParameter("address_line"));
		member.setAdresslineAdd(request.getParameter("address_add"));
		member.setPostCode(request.getParameter("post_code"));
		
		// Parse joinedDate into Date object
		String tempEntryDate = request.getParameter("entry_date");
		try
		{
			DateFormat dateFormatJoinedDate = new SimpleDateFormat("dd.MM.yyyy");
			Date joinedDate = (Date) dateFormatJoinedDate.parse(tempEntryDate);
			member.setEntryDate(joinedDate);
		}
		catch(IllegalFormatException ife)
		{
			System.out.println("MemberServlet.java - doPost() - Problem with DateFormat: JoinedDate");
			ife.printStackTrace();
		}
		catch(Exception ex)
		{
			System.out.println("MemberServlet.java - doPost() - Problem with DateFormat: JoinedDate");
			ex.printStackTrace();
		}
		
		member.setCity(request.getParameter("city"));
		member.setUsername(generateUserName(member));
		member.setPassword(generateUserPassword());
		// save new member into database
		MemberDao memberDao = new MemberDao();
		memberDao.writeMember(member);
		
		response.sendRedirect("./member.jsp");
		
	}

	
	private String generateUserName(Member member)
	{
		String generatedUserName = null;
		String memberName = member.getFirstName();// return the firstname
		String memberLname = member.getLastName();// return the Lastname
		int memberId = MemberDashboardController.lstMemberCounter; // return the memberId

		generatedUserName = memberName.substring(0,1)+"."+memberLname+"."+memberId; // firstletterofFirstname.lastname.RandomId

		return generatedUserName; // generatedUserName -> ([erster buchst. vorname][nachname][id])
	}
	
	private String generateUserPassword()
	{

		SecureRandom random = new SecureRandom(); 
		String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		
		StringBuilder passwordgenerator = new StringBuilder();
		for(int i =1; i<=8;i++){
			passwordgenerator.append(letters.charAt(random.nextInt(letters.length())));
			
		}
		
		HashText ht = new HashText();
		String tempPassword = "";
		try 
		{
			tempPassword = ht.sha256(passwordgenerator.toString());
		} 
		catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tempPassword;
		
		
	}
	
}
