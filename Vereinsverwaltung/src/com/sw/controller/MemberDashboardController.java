package com.sw.controller;

import java.util.List;

import com.sw.beans.Member;
import com.sw.dao.MemberDao;

public class MemberDashboardController 
{
	private List<Member> lstMember;
	
	public MemberDashboardController()
	{
		try
		{
			System.out.println("MitgliederDashboardController:");
			MemberDao memberDao = new MemberDao();
			setLstMember(memberDao.readMember());
			this.lstMember = getLstMember();
		}
		catch(Exception ex)
		{
			System.out.println("Exception: MitgliederDashboardController() constructor");
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
}
