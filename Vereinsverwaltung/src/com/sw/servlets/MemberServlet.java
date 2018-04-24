package com.sw.servlets;

import java.io.IOException;
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
import com.sw.beans.User;
import com.sw.dao.MemberDao;

@WebServlet("/registerMember")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		// Get data from registerMember.jsp
		// Create an object member with the given attributes
		Member member = new Member();

		member.setName(request.getParameter("name"));
		member.setLname(request.getParameter("lname"));
		
		// Parse birth into Date object
		String tempBirth = request.getParameter("birth");
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
		member.setEmail(request.getParameter("email"));
		member.setTelefon(request.getParameter("telephone"));
		member.setAdressline01(request.getParameter("adressline01"));
		member.setAdressline02(request.getParameter("adressline02"));
		member.setPostalcode(request.getParameter("postalcode"));
		
		// Parse joinedDate into Date object
		String tempJoinedDate = request.getParameter("joinedDate");
		try
		{
			DateFormat dateFormatJoinedDate = new SimpleDateFormat("dd.MM.yyyy");
			Date joinedDate = (Date) dateFormatJoinedDate.parse(tempJoinedDate);
			member.setJoinedDate(joinedDate);
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
		member.setGender(request.getParameter("gender"));
		member.setTelefon(request.getParameter("telefon"));
		member.setEmail(request.getParameter("email"));
		member.setRole(request.getParameter("role"));
		
		// save new member into database
		MemberDao memberDao = new MemberDao();
		memberDao.writeMember(member);
		
		response.sendRedirect("./member.jsp");
		
	}

	
	private String generateUserName(Member member)
	{
		String generatedUserName = null;
		String memberName = member.getName(); // return the firstname
		String memberLname = member.getLname(); // return the Lastname

		Random random = new Random();
		String number = "0123456789";
		StringBuilder idgenerator = new StringBuilder();
		
		for(int i =0;i<8;i++) {
			idgenerator.append(number.charAt(random.nextInt(number.length())));   //generates Random ID to 8 characters
		}
		generatedUserName = memberName.substring(0,1)+"."+memberLname+"."+idgenerator.toString(); // firstletterofFirstname.lastname.RandomId

		return generatedUserName; // generatedUserName -> ([erster buchst. vorname][nachname][id])
	}
	
	private String generateUserPassword()
	{

		SecureRandom random = new SecureRandom(); //
		String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		
		StringBuilder passwordgenerator = new StringBuilder();
		for(int i =1; i<=8;i++){
			passwordgenerator.append(letters.charAt(random.nextInt(letters.length())));
			
		}
		return passwordgenerator.toString();
		
		
	}
	
}
