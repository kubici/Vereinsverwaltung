package com.sw.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.sw.beans.Member;
import com.sw.dao.MemberDao;
import com.sw.security.HashText;
import com.sw.security.ParseDate;

@WebServlet("/saveMember")
public class MemberEditServletSave extends HttpServlet{
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
			MemberEditServletSave.infoMessage = infoMessage;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
		HttpSession session = request.getSession();
		if(request.getSession().getAttribute("currentUser") == null) {
			System.out.println("No Session");
			response.sendRedirect("./welcome.jsp");
		} else {
			System.out.println("Session alive!");
			System.out.println(request.getSession().getAttribute("currentUser"));
		}
		
		Member member = new Member();
		
		member.setUsername((String) request.getParameter("username"));
		if(request.getParameter("last_name") != null)
			member.setLastName((String) request.getParameter("last_name"));
		if(request.getParameter("first_name") != null)
			member.setName((String) request.getParameter("first_name"));
		
		if(request.getParameter("birth_date") != null) {
			String date = request.getParameter("birth_date");
			ParseDate parse = new ParseDate();
			member.setBirth(parse.convert(date));
		}
		
		//TODO implement gender
		if(request.getParameter("email_address") != null)
			member.setEmailAddress((String) request.getParameter("email_address"));
		if(request.getParameter("phone_number") != null)
			member.setPhoneNumber((String) request.getParameter("phone_number"));
		if(request.getParameter("address_line") != null)
			member.setAdressline((String) request.getParameter("address_line"));
		if(request.getParameter("address_add") != null)
			member.setAdresslineAdd((String) request.getParameter("address_add"));
		if(request.getParameter("post_code") != null)
			member.setPostCode((String) request.getParameter("post_code"));
		if(request.getParameter("city") != null)
			member.setCity((String) request.getParameter("city"));
		
		if(request.getParameter("password_change") == null) {
			
		} else {
			HashText hash = new HashText();
			String password;
			password = hash.sha256(request.getParameter("password_change").toString());
			member.setPassword(password);
		}
		
		MemberDao md = new MemberDao();
		boolean checkEdit = md.editMember(member);
		if(checkEdit) {
			response.sendRedirect("./member.jsp");
		}  else {
			System.out.println("failed");
		}
		
		
		
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
