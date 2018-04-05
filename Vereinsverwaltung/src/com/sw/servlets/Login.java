package com.sw.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sw.beans.User;
import com.sw.dao.DBConnection;
import com.sw.dao.LoginDao;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String uname = request.getParameter("uname");
		String password = request.getParameter("pass");
		
		User userToCheck = new User();
		userToCheck.setPassword(password);
		userToCheck.setUname(uname);

		
		LoginDao loginDao = new LoginDao();
		boolean checkUserValue = loginDao.checkUser(userToCheck);
		
		if(checkUserValue == true)
		{
			HttpSession session = request.getSession();
			session.setAttribute("username", uname);
			response.sendRedirect("./html/home.jsp");
		}
		else
		{
			response.sendRedirect("./html/login.jsp");
			//http://localhost:8080/sw-praktikum-2%C3%9F18/html/login.jsp
		}
	}



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	

}
