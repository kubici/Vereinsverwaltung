package com.sw.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RoleServlet")
public class RoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException 
    {
		System.out.println("service() - RoleServlet");
		
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		doPost(servletRequest, servletResponse);
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("doPost() - RoleServlet");
		request.getRequestDispatcher("./overviewRoles.jsp").forward(request, response);
	}

}
