package com.sw.controller;

import java.util.List;

import com.sw.beans.Member;
import com.sw.dao.MemberDao;

public class MemberDashboardController 
{
	public static int lstMemberCounter;
	
	private List<Member> lstMember;
	
	public MemberDashboardController()
	{
		try
		{
			System.out.println("MemberDashboardController:");
			MemberDao memberDao = new MemberDao();
			setLstMember(memberDao.readMember());
			this.lstMember = getLstMember();
			// TODO Counter needed? Looks strange with plus 2
			lstMemberCounter = (this.lstMember.size())+2;// Admin has id 1 and is not displayed in this list; Get the next higher value size() has to increment with 2
		}
		catch(Exception ex)
		{
			System.out.println("Exception: MemberDashboardController() constructor");
			ex.printStackTrace();
		}
	}
	
	public List<Member> getLstMember()
	{
		return this.lstMember;
	}
	
	public void setLstMember(List<Member> lstMember)
	{
		this.lstMember = lstMember;
	}
	
	
	public void editMember()
	{
		System.out.println("editmember()");
	}
	

}
