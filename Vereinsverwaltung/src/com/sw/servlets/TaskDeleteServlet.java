package com.sw.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sw.dao.TaskDao;

@WebServlet("/deleteTask")
public class TaskDeleteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int task_id = Integer.parseInt(request.getParameter("id"));
		
		TaskDao taskdao = new TaskDao();
		taskdao.deleteTask(task_id);
		
		request.getRequestDispatcher("./TaskServlet").forward(request, response);
	}
}
