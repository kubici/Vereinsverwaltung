package com.sw.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.IllegalFormatException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sw.beans.Member;
import com.sw.dao.MemberDao;
import com.sw.dao.MemberHasRoleDao;
import com.sw.security.Generator;
import com.sw.security.HashText;
import com.sw.security.ParseDate;

@WebServlet("/registerMember")
public class MemberRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		Member member = new Member();

		member.setName(request.getParameter("first_name"));
		member.setLastName(request.getParameter("last_name"));
		
		String tempBirth = request.getParameter("birth_date");
		ParseDate parser = new ParseDate();
		member.setBirth(parser.autoConvert(tempBirth));
		member.setGender(request.getParameter("gender"));
		member.setEmailAddress(request.getParameter("email_address"));
		member.setPhoneNumber(request.getParameter("phone_number"));
		member.setAdressline(request.getParameter("address_line"));
		member.setAdresslineAdd(request.getParameter("address_add"));
		member.setPostCode(request.getParameter("post_code"));
		
		String tempEntryDate = request.getParameter("entry_date");
		try
		{
			DateFormat dateFormatJoinedDate = new SimpleDateFormat("dd.MM.yyyy");
			Date joinedDate = (Date) dateFormatJoinedDate.parse(tempEntryDate);
			member.setEntryDate(joinedDate);
		}
		catch(IllegalFormatException ife)
		{
			System.out.println("MemberServlet.java - doPost() - Problem with DateFormat: JoinedDate");
			ife.printStackTrace();
		}
		catch(Exception ex)
		{
			System.out.println("MemberServlet.java - doPost() - Problem with DateFormat: JoinedDate");
			ex.printStackTrace();
		}
		
		member.setCity(request.getParameter("city"));
		Generator generator = new Generator();
		member.setUsername(generator.generateUserName(member));
		HashText hash = new HashText();
		try {
			member.setPassword(hash.sha256(generator.generatePassword()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		MemberDao memberDao = new MemberDao();
		memberDao.writeMember(member);
		
		try {
			String[] result = request.getParameterValues("member_has_role");
			int[] result_member_has_role =  new int[result.length];
			for (int i=0; i<result.length; i++) {
				result_member_has_role[i] = Integer.parseInt(result[i]);
			}
			int current_id = memberDao.getMemberIdByUsername(member.getUsername());
			MemberHasRoleDao member_has_role_dao = new MemberHasRoleDao();
			member_has_role_dao.deleteMemberHasRoleREFERENCEmember_id(current_id);
			member_has_role_dao.insertMemberHasRole(current_id, result_member_has_role);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/MemberServlet").forward(request, response);
	}
}
