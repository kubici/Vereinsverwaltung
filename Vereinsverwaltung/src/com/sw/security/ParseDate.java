package com.sw.security;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseDate {
	
	public Date autoConvert (String date) {
		try {
			if (!date.contains(".")) {
				DateFormat dateFormatBirth = new SimpleDateFormat("yyyy-mm-dd");
				Date birthDate = (Date) dateFormatBirth.parse(date);
				return birthDate;
			} else {
				DateFormat dateFormatBirth = new SimpleDateFormat("dd.mm.yyyy");
				Date birthDate = (Date) dateFormatBirth.parse(date);
				return birthDate;
			}
		} catch (ParseException e) {
			// TODO: handle exception
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
