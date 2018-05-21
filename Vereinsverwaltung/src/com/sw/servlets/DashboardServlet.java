package com.sw.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sw.beans.Inventory;
import com.sw.beans.Member;
import com.sw.filters.KeyFigures;
import com.sw.security.ParseDate;

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
		KeyFigures figures = new KeyFigures();
		ParseDate parser = new ParseDate();
	        
        ArrayList<Inventory> list = figures.getNextFewAudits(10);
        for (int i=0; i<list.size(); i++) {
        	System.out.println(parser.convertStringII(list.get(i).getNextAudit()));
        }
	        
		System.out.println("doPost() - DashboardServlet");
		request.getRequestDispatcher("./index.jsp").forward(request, response);
	}

}
