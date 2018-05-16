package com.sw.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sw.dao.MemberHasRoleDao;
import com.sw.dao.RoleDao;
import com.sw.dao.RoleModuleAccessDao;

@WebServlet("/deleteRole")
public class RoleDeleteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int role_id = Integer.parseInt(request.getParameter("id"));
		
		RoleModuleAccessDao accessdao = new RoleModuleAccessDao();
		accessdao.deleteRoleModuleAccess(role_id);
		
		MemberHasRoleDao member_has_role_dao = new MemberHasRoleDao();
		member_has_role_dao.deleteMemberHasRoleREFERENCErole_id(role_id);
		
		RoleDao roledao = new RoleDao();
		roledao.deleteRole(role_id);
		
		request.getRequestDispatcher("./overviewRoles.jsp").forward(request, response);
	}
}