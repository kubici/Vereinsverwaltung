package com.sw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import com.sw.beans.User;

public class SessionControl 
{
	public static void checkSession(PageContext pageContext)
	{
		
		HttpSession session = pageContext.getSession();
		User currentUser = (User)session.getAttribute("currentUser");
		if(session != null && currentUser != null)
		{
				System.out.println("Session not null");
		}
		else
		{
			System.out.println("Session null");
			try 
			{
				pageContext.getRequest().getRequestDispatcher("./welcome.jsp").forward(pageContext.getRequest(), pageContext.getResponse());
				//pageContext.getRequest().sendRedirect("./welcome.jsp");
			}
			catch (ServletException e) 
			{
				e.printStackTrace();
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
