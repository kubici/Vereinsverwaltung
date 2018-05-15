package com.sw.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sw.beans.Role;
import com.sw.dao.RoleDao;
import com.sw.dao.RoleModuleAccessDao;

@WebServlet("/editRole")
public class RoleEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int role_id = Integer.parseInt(request.getParameter("role_id"));
		System.out.println(role_id);
		String role_description = request.getParameter("role_description");
		System.out.println(role_description);
		
		Role role = new Role();
		role.setRole_id(role_id);
		role.setRole_description(role_description);
		RoleDao roledao = new RoleDao();
		roledao.updateRole(role); // TODO false handling
		
		try {
			String[] result = request.getParameterValues("role_module_access");
			int[] result_access =  new int[result.length];
			for (int i=0; i<result.length; i++) {
				result_access[i] = Integer.parseInt(result[i]);
			}
			RoleModuleAccessDao accessdao = new RoleModuleAccessDao();
			accessdao.deleteRoleModuleAccess(role.getRole_id());
			accessdao.insertRoleModuleAccess(role.getRole_id(), result_access);
		} catch (NullPointerException e) {
			RoleModuleAccessDao accessdao = new RoleModuleAccessDao();
			accessdao.deleteRoleModuleAccess(role.getRole_id());
		}
		
		request.getRequestDispatcher("./overviewRoles.jsp").forward(request, response);
	}
}