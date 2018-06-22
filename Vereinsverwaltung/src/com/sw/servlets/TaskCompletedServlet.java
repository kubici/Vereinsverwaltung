package com.sw.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sw.dao.TaskDao;

/**
 * Servlet implementation class TaskCompletedServlet
 */
@WebServlet("/TaskCompletedServlet")
public class TaskCompletedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskCompletedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException
	{
		System.out.println("service() - TaskCompletedServlet");
		
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		doPost(servletRequest, servletResponse);
	}
	
	//TODO  Add to SessionFilter

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("doPost() - TaskCompletedServlet");
		// TODO Implement complete auf true setzten
		
		// TODO taskID auslesen, Datenbankverbindung aufbauen, complet auf true setzen
		int taskId = Integer.parseInt(request.getParameter("taskId"));
		TaskDao taskDao = new TaskDao();
		try {
			taskDao.updateTask(taskId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("./overviewTasks.jsp").forward(request, response);
	}

}
