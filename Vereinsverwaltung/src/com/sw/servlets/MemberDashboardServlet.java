package com.sw.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sw.beans.Member;
import com.sw.dao.MemberDao;

/**
 * Servlet implementation class MemberDashboardTest
 */
@WebServlet("/MemberDashboardServlet")
public class MemberDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private List<Member> lstMember;
	public static int lstMemberCounter;
	
    public MemberDashboardServlet() 
    {
    	try
		{
			System.out.println("MemberDashboardServlet:");
			MemberDao memberDao = new MemberDao();
			setLstMember(memberDao.readMember());
			this.lstMember = getLstMember();
			// TODO Counter needed? Looks strange with plus 2
			lstMemberCounter = (this.lstMember.size())+2;// Admin has id 1 and is not displayed in this list; Get the next higher value size() has to increment with 2
		}
		catch(Exception ex)
		{
			System.out.println("Exception: MemberDashboardServlet() constructor");
			ex.printStackTrace();
		}
    }
    
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {

		System.out.println("service() ");
		
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		doPost(servletRequest, servletResponse);
		
	}
		
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("doPost() - MemberDashboardServlet");
		response.sendRedirect("./index.jsp");
	}
	
	
	public List<Member> getLstMember()
	{
		return this.lstMember;
	}
	
	public void setLstMember(List<Member> lstMember)
	{
		this.lstMember = lstMember;
	}

}
