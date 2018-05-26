package com.sw.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberDashboardTest
 */
@WebServlet("/MemberDashboardServlet")
public class MemberDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {

		System.out.println("service() - MemberDashboardServlet");
		
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		doPost(servletRequest, servletResponse);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("doPost() - MemberDashboardServlet");
		//response.sendRedirect("./overviewMember.jsp");
		request.getRequestDispatcher("./overviewMember.jsp").forward(request, response);
	}

}
