package com.sw.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sw.beans.Member;
import com.sw.controller.MemberDashboardController;
import com.sw.security.Generator;
import com.sw.security.ParseDate;

/**
 * Servlet implementation class MemberEditServlet
 */
@WebServlet("/editMember")
public class MemberEditServlet extends HttpServlet {
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
			MemberEditServlet.infoMessage = infoMessage;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("memberId: " + request.getParameter("id")); // TODO this is the correct ID of the selected member
		
		HttpSession session = request.getSession();
		if(request.getSession().getAttribute("currentUser") == null) {
			System.out.println("No Session");
			response.sendRedirect("./welcome.jsp");
		} else {
			System.out.println("Session alive!");
			System.out.println(request.getSession().getAttribute("currentUser"));
		}
		
		MemberDashboardController mdc = new MemberDashboardController();
		List<Member> listMember = mdc.getLstMember();
		
		int i = (Integer.parseInt(request.getParameter("id"))) - 2; // -1
	//	int i = (Integer.parseInt(request.getParameter("id")));
		Member member = listMember.get(i);
		System.out.println(member);
		request.setAttribute("username", member.getUsername());
		request.setAttribute("last_name", member.getLastName());
		request.setAttribute("first_name", member.getFirstName());
		
		ParseDate parse = new ParseDate();
		request.setAttribute("birth", parse.convertString(member.getBirth()));
		request.setAttribute("gender", member.getGender());
		request.setAttribute("mail", member.getEmailAddress());
		request.setAttribute("phone", member.getPhoneNumber());
		request.setAttribute("address", member.getAdressline());
		request.setAttribute("address_add", member.getAdresslineAdd());
		request.setAttribute("post_code", member.getPostCode());
		request.setAttribute("city", member.getCity());
		
		Generator generator = new Generator();
		String newPassword = generator.generatePassword();
		request.setAttribute("password", newPassword);
	
		request.getRequestDispatcher("./editMember.jsp").forward(request, response);
		
		System.out.print("doPost() editMember: ");
		System.out.println(request.getParameter("id"));
		
	}
}
