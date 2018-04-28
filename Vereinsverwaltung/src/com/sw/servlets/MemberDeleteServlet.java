package com.sw.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sw.beans.Member;
import com.sw.controller.MemberDashboardController;
import com.sw.dao.DBConnection;
import com.sw.dao.MemberDao;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/deleteMember")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(request.getSession().getAttribute("currentUser") == null) {
			System.out.println("No Session");
			response.sendRedirect("./welcome.jsp");
		} else {
			System.out.println("Session alive!");
			System.out.println(request.getSession().getAttribute("currentUser"));
		}
		
		Member member = new Member();
		String selectedMemberId = Integer.toString(member.getMemberId());
		request.setAttribute("member_Id", selectedMemberId);
		request.getRequestDispatcher("./deleteMember.jsp").forward(request, response);
		System.out.println("doPost() deleteMember: "+request.getParameter(selectedMemberId));
		
		MemberDao memberdao = new MemberDao();
		boolean result =memberdao.deleteMember(member);
		if(result) {
			
			System.out.println("Member : "+ member.getMemberId()+" is deleted from database");
			
		}else {
			System.out.println("Not Deleted");
		}
		
	}
}
