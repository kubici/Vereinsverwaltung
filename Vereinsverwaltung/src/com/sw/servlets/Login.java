package com.sw.servlets;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sw.beans.User;
import com.sw.dao.LoginDao;

@WebServlet("/welcome")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String uname = request.getParameter("username");
		String password = request.getParameter("pwd");
		String infoMessage = "";
		User currentUser = new User();
		currentUser.setPassword(password);
		currentUser.setUname(uname);

		LoginDao loginDao = new LoginDao();
		boolean checkUserValue = loginDao.checkUser(currentUser);
		
		if(checkUserValue == true)
		{
			final HttpSession session = request.getSession();
			session.setAttribute("currentUser", currentUser);
			infoMessage = "Successful login";
			request.setAttribute("infoMessage", infoMessage);
			response.sendRedirect("./index.jsp");
		}
		else
		{
			infoMessage = "Wrong username or password";
			System.out.println(infoMessage);
			// TODO Output to welcome 
			request.setAttribute("infoMessage", infoMessage);
			request.getRequestDispatcher("./welcome.jsp").forward(request, response);
			response.sendRedirect("./welcome.jsp");
		}
	}

}
