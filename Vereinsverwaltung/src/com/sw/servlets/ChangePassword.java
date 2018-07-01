package com.sw.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sw.beans.Member;
import com.sw.dao.ChangePasswordDao;

@WebServlet("/changePWD")
public class ChangePassword extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {		
		String oldPWD = request.getParameter("pwd_old");
		String newPWD01 = request.getParameter("pwd_new01");
		String newPWD02 = request.getParameter("pwd_new02");
		
		Member member = new Member();
		member.setUsername(request.getSession().getAttribute("username").toString());
		member.setPassword(oldPWD);
		member.setNewPassword(newPWD01);
		
		if(newPWD01.equals(newPWD02)) {
			ChangePasswordDao cpd = new ChangePasswordDao();
			boolean checkChange = cpd.changePassword(member);
			
			if(checkChange) {
				System.out.println("Session deleted");
				request.getRequestDispatcher("./welcome.jsp").forward(request, response);
			} else {
				System.out.println("Ihr altes Passwort ist falsch");
				request.getRequestDispatcher("./changePassword.jsp").forward(request, response);
			}
			
		} else {
			System.out.println("Keine �bereinstimmung des neuen Passworts");
			request.getRequestDispatcher("./changePassword.jsp").forward(request, response);
		}	
	}
}
