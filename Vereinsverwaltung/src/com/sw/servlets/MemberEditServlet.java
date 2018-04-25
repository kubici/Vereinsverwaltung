package com.sw.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberEditServlet
 */
@WebServlet("/editMember")
public class MemberEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get the id of the selected Item in the jsp table
		System.out.print("doPost() editMember: ");
		System.out.println(request.getParameter("id"));
		
		// Get lstMember from MemberDashboardController.java
		
		// Iterate through the list and search for selected item id
		
		// print results into a new html file 
		
		// create the html file
		
		// save changes into database
		
		// Put every SQL Statement into editMember() - MemberDao.java
	}
}
