package com.sw.security;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseDate {
	
	public Date autoConvert (String date) {
		try {
			if (!date.contains(".")) {
				DateFormat dateFormatBirth = new SimpleDateFormat("yyyy-MM-dd");
				Date birthDate = (Date) dateFormatBirth.parse(date);
				return birthDate;
			} else {
				DateFormat dateFormatBirth = new SimpleDateFormat("dd.MM.yyyy");
				Date birthDate = (Date) dateFormatBirth.parse(date);
				return birthDate;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String convertString (Date date) {
		DateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy");
		
		String stringDate = dateformat.format(date);
		return stringDate;
	}
	
	public String convertStringII (Date date) {
		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		
		String stringDate = dateformat.format(date);
		return stringDate;
	}
}
