package com.sw.servlets;

import java.io.IOException;
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
		
		User userToCheck = new User();
		userToCheck.setPassword(password);
		userToCheck.setUname(uname);

		LoginDao loginDao = new LoginDao();
		boolean checkUserValue = loginDao.checkUser(userToCheck);
		
		if(checkUserValue == true)
		{
			HttpSession session = request.getSession();
			session.setAttribute("username", uname);
			response.sendRedirect("./index.jsp");
		}
		else
		{
			System.out.println("Wrong username or password!");
			response.sendRedirect("./welcome.jsp");
		}
	}

}
