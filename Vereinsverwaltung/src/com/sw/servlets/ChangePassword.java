package com.sw.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sw.beans.Member;
import com.sw.dao.ChangePasswordDao;

@WebServlet("/changePWD")
public class ChangePassword extends HttpServlet{
	private static final long serialVersionUID = 1L;

	private static String infoMessage = null;
		
	public static String getInfoMessage(){
		if(infoMessage != null)		{
			return infoMessage;
		}
		return null;
	}
	
	public static void setInfoMessage(String infoMessage)	{
		if(infoMessage != null)		{
			ChangePassword.infoMessage = infoMessage;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if(request.getSession().getAttribute("currentUser") == null) {
			System.out.println("No Session");
			response.sendRedirect("./welcome.jsp");
		} else {
			System.out.println("Session alive!");
			System.out.println(request.getSession().getAttribute("currentUser"));
		}
		
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
				if(session != null && session.getAttribute("currentUser") != null) {
					session.removeAttribute("currentUser");
					session.invalidate();
					
					ChangePassword.setInfoMessage("Passwort�nderungen wurden �bernommen.");
					
					response.sendRedirect("./welcome.jsp");
					ChangePassword.setInfoMessage("Bitte mit neuem Passwort einloggen!");
					System.out.println("Session deleted");
				}
			} else {
				ChangePassword.infoMessage = "Ihr altes Passwort ist falsch!";
				System.out.println(infoMessage);
				request.setAttribute("infoMessage", infoMessage);
				response.sendRedirect("./changePassword.jsp");
			}
			
		} else {
			ChangePassword.infoMessage = "Das neue Passwort wurde nicht zweimal korrekt eingegeben!";
			System.out.println(infoMessage);
			request.setAttribute("infoMessage", infoMessage);
			response.sendRedirect("./changePassword.jsp");
		}
		
	}
}
