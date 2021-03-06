package com.sw.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		
		
		if(session != null && session.getAttribute("currentUser") != null)
		{
			session.removeAttribute("currentUser");
			session.invalidate();
			// Set infoMessage to successful loged out
			Login.setInfoMessage("Logout was successfull");
			
			request.getRequestDispatcher("./welcome.jsp").forward(request, response);
			Login.setInfoMessage("Logout was successfull");
			System.out.println("Session deleted");
		}
		else
		{
			request.getRequestDispatcher("./welcome.jsp").forward(request, response);
		}
	}
}
