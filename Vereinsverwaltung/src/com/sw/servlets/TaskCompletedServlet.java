package com.sw.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sw.dao.TaskDao;

@WebServlet("/TaskCompletedServlet")
public class TaskCompletedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int taskId = Integer.parseInt(request.getParameter("id"));
		TaskDao taskDao = new TaskDao();
		taskDao.completeTask(taskId);
		
		request.getRequestDispatcher("./TaskServlet").forward(request, response);
	}

}
