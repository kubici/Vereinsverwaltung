package com.sw.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.spi.DateFormatProvider;
import java.util.Date;
import java.util.IllegalFormatException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sw.beans.Mitglieder;
import com.sw.beans.User;
import com.sw.dao.MitgliederDao;

@WebServlet("/registerMitglieder")
public class MitgliederServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("doGet() - MitgliederServlet");
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get data from mitgliederView.jsp
		// Create an object mitglieder with the given attributes
		Mitglieder mitglieder = new Mitglieder();

		mitglieder.setName(request.getParameter("name"));
		mitglieder.setLname(request.getParameter("lname"));
		
		// Parse birth into Date object
		String tempBirth = request.getParameter("birth");
		DateFormat dateFormatBirth = new SimpleDateFormat("dd.MM.yyyy");
		try
		{
			Date birthDate = (Date) dateFormatBirth.parse(tempBirth);
			mitglieder.setBirth(birthDate);
		}
		catch(IllegalFormatException ife)
		{
			System.out.println("MitgliederServlet.java - doPost() - Problem with DateFormat: BirthDate");
			ife.printStackTrace();
		}
		catch(Exception ex)
		{
			System.out.println("MitgliederServlet.java - doPost() - Problem with DateFormat: BirthDate");
			ex.printStackTrace();
		}
		
		mitglieder.setGender(request.getParameter("gender"));
		mitglieder.setEmail(request.getParameter("email"));
		mitglieder.setTelefon(request.getParameter("telephone"));
		mitglieder.setAdressline01(request.getParameter("adressline01"));
		mitglieder.setAdressline02(request.getParameter("adressline02"));
		mitglieder.setPostalcode(request.getParameter("postalcode"));
		
		// Parse joinedDate into Date object
		String tempJoinedDate = request.getParameter("joinedDate");
		try
		{
			DateFormat dateFormatJoinedDate = new SimpleDateFormat("dd.MM.yyyy");
			Date joinedDate = (Date) dateFormatJoinedDate.parse(tempJoinedDate);
			mitglieder.setJoinedDate(joinedDate);
		}
		catch(IllegalFormatException ife)
		{
			System.out.println("MitgliederServlet.java - doPost() - Problem with DateFormat: JoinedDate");
			ife.printStackTrace();
		}
		catch(Exception ex)
		{
			System.out.println("MitgliederServlet.java - doPost() - Problem with DateFormat: JoinedDate");
			ex.printStackTrace();
		}
		
		mitglieder.setCity(request.getParameter("city"));
		mitglieder.setGender(request.getParameter("gender"));
		mitglieder.setTelefon(request.getParameter("telefon"));
		mitglieder.setEmail(request.getParameter("email"));
		mitglieder.setRole(request.getParameter("role"));
		
		// save new Mitglied into database
		MitgliederDao mitgliederDao = new MitgliederDao();
		mitgliederDao.writeMitglieder(mitglieder);
		
		response.sendRedirect("./mitglieder.jsp");
		
	}

}
