package com.sw.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sw.beans.Task;
import com.sw.dao.TaskDao;

@WebServlet("/addTask")
public class TaskAddServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("task_title");
		String text = request.getParameter("task_text");
		String creator = request.getParameter("task_creator");
		
		Task task = new Task();
		task.setCompleted(false);
		task.setTitel(title);
		task.setText(text);
		LocalDate localDate = LocalDate.now();
		Date today = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		task.setCreationDate(today);
		task.setCreator(creator);
		
		TaskDao taskdao = new TaskDao();
		taskdao.insertTask(task);
		
		request.getRequestDispatcher("./TaskServlet").forward(request, response);
	}
	
}
