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
		
		// Get the id of the selected Item in the jsp table
		
		
		request.getRequestDispatcher("./deleteMember.jsp").forward(request, response);
		
		String selectedMemberId = request.getParameter("id");
		
		Member member = new Member();
		request.setAttribute(selectedMemberId, member.getUsername());
	
		MemberDao memberdao = new MemberDao();
		memberdao.deleteMember(member);
		System.out.println("doPost() deleteMember: "+request.getParameter("id"));
	
		
	}
}
