package com.sw.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MitgliederServlet
 */
@WebServlet("/MitgliederServlet")
public class MitgliederServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Implement functions to register a new Mitglied
		//response.sendRedirect("./mitgliederView.jsp");
		//doGet(request, response);
		
		// Get data from mitgliederView.jsp
		String name = request.getParameter("name");
		String lname = request.getParameter("lname");
		// TODO Change type to Date
		String birth = (request.getParameter("birth").toString());
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String tel = request.getParameter("telephone");
		String adressline01 = request.getParameter("adressline01");
		String adressline02 = request.getParameter("adressline02");
		String postalcode = request.getParameter("postalcode");
		String joinedDate = request.getParameter("beitritt");
		
		System.out.println("Test Data: " + name);
		
	}

}
