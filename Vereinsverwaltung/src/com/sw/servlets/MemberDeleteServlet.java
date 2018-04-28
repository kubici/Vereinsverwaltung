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
		

		
		Member member = new Member();
		
		String selectedMemberId = Integer.toString(member.getMemberId());
		
		request.setAttribute("memberId", selectedMemberId);
		
		request.getRequestDispatcher("./deleteMember.jsp").forward(request, response);
		
		System.out.println("doPost() deleteMember: "+request.getParameter(selectedMemberId));
		
		MemberDao memberdao = new MemberDao();
		
		boolean result =memberdao.deleteMember(member);
		if(result) {
			response.sendRedirect("member.jsp");
		}else {
			System.out.println("Not Deleted");
		}
		
	}
}
