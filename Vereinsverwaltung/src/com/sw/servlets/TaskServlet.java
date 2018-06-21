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
 * Servlet implementation class TaskServlet
 */
@WebServlet("/TaskServlet")
public class TaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TaskServlet() 
    {
    		System.out.println("Konstruktor: TaskServlet()");
    }

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException 
	{
		System.out.println("service() - TaskServlet()");
		
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		doPost(servletRequest, servletResponse);
		
		// SessionFilter + TaskInventory eintragen
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("doPost() - InventoryServlet");
		request.getRequestDispatcher("./overviewTasks.jsp").forward(request, response);
	}

}
