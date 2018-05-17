package com.sw.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sw.beans.Member;
import com.sw.dao.MemberDao;
import com.sw.dao.MemberHasRoleDao;
import com.sw.security.HashText;
import com.sw.security.ParseDate;

@WebServlet("/saveMember")
public class MemberEditServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Member member = new Member();
		
		
		member.setUsername((String) request.getParameter("username"));
		if(request.getParameter("last_name") != null)
			member.setLastName((String) request.getParameter("last_name"));
		if(request.getParameter("first_name") != null)
			member.setName((String) request.getParameter("first_name"));
		
		if(request.getParameter("birth_date") != null) {
			String date = request.getParameter("birth_date");
			ParseDate parse = new ParseDate();
			member.setBirth(parse.autoConvert(date));
		}
		
		if(request.getParameter("gender") != null) {
			member.setGender(request.getParameter("gender"));
			}
		
		if(request.getParameter("email_address") != null)
			member.setEmailAddress((String) request.getParameter("email_address"));
		if(request.getParameter("phone_number") != null)
			member.setPhoneNumber((String) request.getParameter("phone_number"));
		if(request.getParameter("address_line") != null)
			member.setAdressline((String) request.getParameter("address_line"));
		if(request.getParameter("address_add") != null)
			member.setAdresslineAdd((String) request.getParameter("address_add"));
		if(request.getParameter("post_code") != null)
			member.setPostCode((String) request.getParameter("post_code"));
		if(request.getParameter("city") != null)
			member.setCity((String) request.getParameter("city"));
		
		if(request.getParameter("password_change") == null) {
			
		} else {
			HashText hash = new HashText();
			String password;
			try {
				password = hash.sha256(request.getParameter("password_change").toString());
				member.setPassword(password);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		
		MemberDao memberdao = new MemberDao();
		memberdao.editMember(member);
		int current_id = memberdao.getMemberIdByUsername(member.getUsername());
		
		try {
			String[] result = request.getParameterValues("member_has_role");
			int[] result_member_has_role =  new int[result.length];
			for (int i=0; i<result.length; i++) {
				result_member_has_role[i] = Integer.parseInt(result[i]);
			}
			
			MemberHasRoleDao member_has_role_dao = new MemberHasRoleDao();
			member_has_role_dao.deleteMemberHasRoleREFERENCEmember_id(current_id);
			member_has_role_dao.insertMemberHasRole(current_id, result_member_has_role);
		} catch (NullPointerException e) {
			MemberHasRoleDao member_has_role_dao = new MemberHasRoleDao();
			member_has_role_dao.deleteMemberHasRoleREFERENCEmember_id(current_id);
		}
		
		request.getRequestDispatcher("./overviewMember.jsp").forward(request, response);
	}
}
