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

@WebServlet("/addRole")
public class RoleAddServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String role_description = request.getParameter("role_description");
		
		Role role = new Role();
		role.setRole_description(role_description);
		
		RoleDao roledao = new RoleDao();
		roledao.insertRole(role); // TODO if false handling
		
		try {
			String[] result = request.getParameterValues("role_module_access");
			int[] result_access =  new int[result.length];
			for (int i=0; i<result.length; i++) {
				result_access[i] = Integer.parseInt(result[i]);
			}
			
			Role role_saved = (roledao.getRoleByName(role_description));
			RoleModuleAccessDao accessdao = new RoleModuleAccessDao();
			accessdao.insertRoleModuleAccess(role_saved.getRole_id(), result_access); // TODO if false handling
		} catch (Exception e) {
			// TODO noch überlegen
		}
		
		response.sendRedirect("./overviewRoles.jsp");
	}
}