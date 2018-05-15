package com.sw.security;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.IllegalFormatException;

public class ParseDate {

	public Date convert (String date) {
		DateFormat dateFormatBirth = new SimpleDateFormat("yyyy-mm-dd");
		try
		{
			Date birthDate = (Date) dateFormatBirth.parse(date);
			return birthDate;
		}
		catch(IllegalFormatException ife)
		{
			System.out.println("MemberServlet.java - doPost() - Problem with DateFormat: BirthDate");
			ife.printStackTrace();
		}
		catch(Exception ex)
		{
			System.out.println("MemberServlet.java - doPost() - Problem with DateFormat: BirthDate");
			ex.printStackTrace();
		}
		return null;
	}
	
	public Date convertEuro (String date) {
		DateFormat dateFormatBirth = new SimpleDateFormat("dd.mm.yyyy");
		try
		{
			Date birthDate = (Date) dateFormatBirth.parse(date);
			return birthDate;
		}
		catch(IllegalFormatException ife)
		{
			System.out.println("MemberServlet.java - doPost() - Problem with DateFormat: BirthDate");
			ife.printStackTrace();
		}
		catch(Exception ex)
		{
			System.out.println("MemberServlet.java - doPost() - Problem with DateFormat: BirthDate");
			ex.printStackTrace();
		}
		return null;
	}
	
	public String convertString (Date date) {
		DateFormat dateformat = new SimpleDateFormat("dd.mm.yyyy");
		
		String stringDate = dateformat.format(date);
		return stringDate;
	}
	
	public String convertStringII (Date date) {
		DateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");
		
		String stringDate = dateformat.format(date);
		return stringDate;
	}
}
