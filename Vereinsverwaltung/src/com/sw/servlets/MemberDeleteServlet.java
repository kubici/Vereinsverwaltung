package com.sw.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sw.beans.Member;
import com.sw.servlets.MemberDashboardServlet;
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
		
		System.out.println(request.getParameter("id"));
		int selectedMemberId = Integer.parseInt(request.getParameter("id"));
		//selectedMemberId += 1; // plus 1 because Admin is at ID 0
		System.out.println("Temp: " + selectedMemberId);
		
		request.setAttribute("member_Id", selectedMemberId); // set Attribut for deleteMember.jsp
		
		Member member = new Member();
		member.setMemberId(selectedMemberId);
		
		MemberDao memberdao = new MemberDao();
		try 
		{
			memberdao.deleteMember(member);
		}
		catch(SQLException sqle)
		{
			
		}
		catch(Exception ex)
		{
			
		}
		
		System.out.println("Member : "+ member.getMemberId()+" is deleted from database");
		request.getRequestDispatcher("./overviewMember.jsp").forward(request, response);
	}
}
