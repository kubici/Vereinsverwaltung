package com.sw.controller;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.sw.beans.Mitglieder;
import com.sw.dao.MitgliederDao;

//@Named(value = "mitgliederController")
@RequestScoped
@ManagedBean(name = "mitgliederController", eager = true)
public class MitgliederDashboardController 
{
	
	public List<Mitglieder> lstMitglieder;
	
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
