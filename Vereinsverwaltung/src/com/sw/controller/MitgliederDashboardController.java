package com.sw.controller;

import java.util.List;

import com.sw.beans.Mitglieder;
import com.sw.dao.MitgliederDao;

public class MitgliederDashboardController 
{
	private List<Mitglieder> lstMitglieder;
	
	public MitgliederDashboardController()
	{
		try
		{
			System.out.println("MitgliederDashboardController:");
			MitgliederDao mitgliederDao = new MitgliederDao();
			setLstMitglieder(mitgliederDao.readMitglieder());
			this.lstMitglieder = getLstMitglieder();
		}
		catch(Exception ex)
		{
			System.out.println("Exception: MitgliederDashboardController() constructor");
			ex.printStackTrace();
		}
	}
	
	public List<Mitglieder> getLstMitglieder()
	{
		return this.lstMitglieder;
	}
	
	public void setLstMitglieder(List<Mitglieder> lstMitglieder)
	{
		this.lstMitglieder = lstMitglieder;
	}
}
