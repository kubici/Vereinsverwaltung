package com.sw.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sw.beans.Member;
import com.sw.dao.MemberDao;
import com.sw.dao.MemberHasRoleDao;

/**
 * Servlet implementation class MemberDeleteServlet
 */
/**
 * 
 * @author tobi
 *
 */
@WebServlet("/deleteMember")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getParameter("id"));
		int selectedMemberId = Integer.parseInt(request.getParameter("id"));
		System.out.println("Temp: " + selectedMemberId);
		
		request.setAttribute("member_Id", selectedMemberId); // set Attribut for deleteMember.jsp
		
		Member member = new Member();
		member.setMemberId(selectedMemberId);
		
		MemberHasRoleDao member_has_role_dao = new MemberHasRoleDao();
		member_has_role_dao.deleteMemberHasRoleREFERENCEmember_id(member.getMemberId());
		
		MemberDao memberdao = new MemberDao();
		try 
		{
			memberdao.deleteMember(member);
		}
		catch(Exception ex)
		{
			
		}
		
		System.out.println("Member : "+ member.getMemberId()+" is deleted from database");
		request.getRequestDispatcher("/MemberServlet").forward(request, response);
	}
}
