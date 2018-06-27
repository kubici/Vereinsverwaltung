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
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public DashboardServlet()
	{
		
	}
	
	
	
    @Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException 
    {
		System.out.println("service() ");
		
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		doPost(servletRequest, servletResponse);
	}



	public DashboardServlet(HttpServletRequest servletRequest, HttpServlet servletResponse) {
        
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Hier Keyfigures implementierbar
	        
		System.out.println("doPost() - DashboardServlet");
		request.getRequestDispatcher("./index.jsp").forward(request, response);
	}

}
