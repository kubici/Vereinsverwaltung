package com.sw.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.util.List;

import com.sw.beans.Mitglieder;
import com.sw.dao.DBConnection;
import com.sw.dao.MitgliederDao;



// TODO No need for this Servlet

@WebServlet("/MitgliederHomeServlet")
public class MitgliederHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MitgliederHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		DBConnection.getConnectionToDatabase();
		MitgliederDao mitgliederDao = new MitgliederDao();
		List<Mitglieder> mitglieder = mitgliederDao.readMitglieder();
		
		
		request.getSession().setAttribute("mitgliederResults", mitglieder.get(0));
		request.getRequestDispatcher("/mitgliederHome.jsp").forward(request, response);
	}

}
