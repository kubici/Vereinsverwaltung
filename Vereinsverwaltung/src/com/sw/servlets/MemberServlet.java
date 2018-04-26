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
import com.sw.security.Generator;
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
		Generator gr = new Generator();
		member.setUsername(gr.generateUserName(member));
		HashText hash = new HashText();
		try {
			member.setPassword(hash.sha256(gr.generatePassword()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// save new member into database
		MemberDao memberDao = new MemberDao();
		memberDao.writeMember(member);
		
		response.sendRedirect("./member.jsp");
		
	}

	
	
	
}
