package com.sw.filters;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

import com.sw.beans.Inventory;
import com.sw.beans.Member;
import com.sw.dao.InventoryDao;
import com.sw.dao.MemberDao;

public class KeyFigures {
	
	/*
	 * Kennzahlen der Mitglieder
	 */
	
	public ArrayList<Member> getNextFewBirthdays (int count) {
		ArrayList<Member> list = new ArrayList<>();
		
		MemberDao memberdao = new MemberDao();
		ArrayList<Member> memberlist = (ArrayList<Member>) memberdao.readMember();
		
		Member nextBirth = null;
		
		
		for (int i=0; i<count; i++) {
			int daystobday = 365;
			
			for (int j=0; j<memberlist.size(); j++) {
				Date birthdate = memberlist.get(j).getBirth();
				LocalDate birth = birthdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				MonthDay birthday = MonthDay.of(birth.getMonth(), birth.getDayOfMonth());
				MonthDay today = MonthDay.now();
				
				int daysleft = 0;
								
				if (birthday.isBefore(today)) {
					daysleft = (int) birthday.atYear(Year.now().getValue()).until(today.atYear(Year.now().plusYears(1).getValue()), ChronoUnit.DAYS);
				} else {
					daysleft = (int) today.atYear(Year.now().getValue()).until(birthday.atYear(Year.now().getValue()), ChronoUnit.DAYS);
				}
				
				if (daysleft <= daystobday) {
					nextBirth = memberlist.get(j);
					daystobday = daysleft;
				}
			}
			
			if(memberlist.contains(nextBirth)) {
				list.add(nextBirth);
				memberlist.remove(nextBirth);
			}
		}
		
		return list;
	}
	
	public int countMember () {
		MemberDao memberdao = new MemberDao();
		
		return memberdao.readMember().size();
	}
	
	public int countMales () {
		int males = 0;
		
		MemberDao memberdao = new MemberDao();
		ArrayList<Member> memberlist = (ArrayList<Member>) memberdao.readMember();
		
		for (int i=0; i<memberlist.size(); i++) {
			if (memberlist.get(i).getGender().equals("male")) males++;
		}
		
		return males;
	}
	
	public int countFemales () {
		int females = 0;
		
		MemberDao memberdao = new MemberDao();
		ArrayList<Member> memberlist = (ArrayList<Member>) memberdao.readMember();
		
		for (int i=0; i<memberlist.size(); i++) {
			if (memberlist.get(i).getGender().equals("female")) females++;
		}
		
		return females;
	}
	
	public int countOthers () {
		int other = 0;
		
		MemberDao memberdao = new MemberDao();
		ArrayList<Member> memberlist = (ArrayList<Member>) memberdao.readMember();
		
		for (int i=0; i<memberlist.size(); i++) {
			if (memberlist.get(i).getGender().equals("other")) other++;
		}
		
		return other;
	}
	
	/*
	 * Kennzahlen des Inventars
	 */
	
	public int countInventory () {
		InventoryDao inventorydao = new InventoryDao();
		
		return inventorydao.readInventory().size();
	}
	
	public ArrayList<Inventory> getNextFewAudits (int count) {
		ArrayList<Inventory> list = new ArrayList<>();
		
		InventoryDao inventorydao = new InventoryDao();
		ArrayList<Inventory> inventorylist = (ArrayList<Inventory>) inventorydao.readInventoryNextAudit();

		LocalDate today = LocalDate.now();
		Inventory nextInventory = null;
		
		for (int i=0; i<count; i++) {
			
			for (int j=0; j<inventorylist.size(); j++) {
				Date nextInventoryAudit = inventorylist.get(j).getNextAudit();
				LocalDate audit = nextInventoryAudit.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				if (today.minusDays(1).isBefore(audit)) {
					nextInventory = inventorylist.get(j);
				}
			}
			
			if (inventorylist.contains(nextInventory)) {
				list.add(nextInventory);
				inventorylist.remove(nextInventory);
			}			
		}
		
		return list;
	}

}
