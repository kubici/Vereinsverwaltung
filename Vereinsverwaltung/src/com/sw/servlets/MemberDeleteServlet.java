package com.sw.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/deleteMember")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get the id of the selected Item in the jsp table
		System.out.println("doPost() deleteMember: ");
		System.out.println(request.getParameter("id"));
		
		// Convert String to Int
		String selectedMemberId = request.getParameter("id");
		
		// Connect to Database + do not write your own database connection
		// Use an existing class
		
		// implement deleteMember() - MemberDao
		// Write SQL Statement in deleteMember()
	}
}
