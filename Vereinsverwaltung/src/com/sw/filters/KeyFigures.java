package com.sw.filters;

import java.time.LocalDate;
import java.time.ZoneId;
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

	public ArrayList<Member> getNextBirthdays (int days) {
		ArrayList<Member> list = new ArrayList<>();
		
		MemberDao memberdao = new MemberDao();
		ArrayList<Member> memberlist = (ArrayList<Member>) memberdao.readMember();
		
		LocalDate today = LocalDate.now();
		LocalDate selectedDay = today.plusDays(days);
		
		for (int i=0; i<memberlist.size(); i++) {
			Date birthday = memberlist.get(i).getBirth();
			LocalDate birth = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			if (birth.isBefore(selectedDay.plusDays(1))) {
				list.add(memberlist.get(i));
			}
		}
		
		return list;
	}
	
	public ArrayList<Member> getNextFewBirthdays (int count) {
		ArrayList<Member> list = new ArrayList<>();
		
		MemberDao memberdao = new MemberDao();
		ArrayList<Member> memberlist = (ArrayList<Member>) memberdao.readMember();
		
		LocalDate nextBirthday = null;;
		Member nextBirth = null;
		
		for (int i=0; i<count; i++) {
			
			for (int j=0; j<memberlist.size(); j++) {
				Date birthday = memberlist.get(j).getBirth();
				LocalDate birth = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				if (birth.isBefore(nextBirthday.plusDays(1))) {
					nextBirthday = birth;
					nextBirth = memberlist.get(j);
				}
			}
			
			if(nextBirth != null) {
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
	
	public ArrayList<Inventory> getNextAudits (int days) {
		ArrayList<Inventory> list = new ArrayList<>();
		
		InventoryDao inventorydao = new InventoryDao();
		ArrayList<Inventory> inventorylist = (ArrayList<Inventory>) inventorydao.readInventory();
		
		LocalDate today = LocalDate.now();
		LocalDate selectedDay = today.plusDays(days);
		
		for (int i=0; i<inventorylist.size(); i++) {
			Date nextaudit = inventorylist.get(i).getNextAudit();
			LocalDate audit = nextaudit.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			if (audit.isBefore(selectedDay.plusDays(1))) {
				list.add(inventorylist.get(i));
			}
		}
		
		return list;
	}
	
	public ArrayList<Inventory> getNextFewAudits (int count) {
		ArrayList<Inventory> list = new ArrayList<>();
		
		InventoryDao inventorydao = new InventoryDao();
		ArrayList<Inventory> inventorylist = (ArrayList<Inventory>) inventorydao.readInventory();

		LocalDate nextAudit = null;
		Inventory nextInventory = null;
		
		for (int i=0; i<count; i++) {
			
			for (int j=0; j<inventorylist.size(); j++) {
				Date nextInventoryAudit = inventorylist.get(j).getNextAudit();
				LocalDate audit = nextInventoryAudit.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				if (nextAudit == null) nextAudit = getLastAudit(inventorylist);
				
				if (audit.isBefore(nextAudit.plusDays(1))) {
					nextAudit = audit;
					nextInventory = inventorylist.get(j);
				}
			}
			
			if(nextAudit != null) {
				list.add(nextInventory);
				inventorylist.remove(nextInventory);
			}
		}
		
		return list;
	}

	private LocalDate getLastAudit(ArrayList<Inventory> inventorylist) {
		LocalDate lastAudit = null;
		
		for (int i=0; i<inventorylist.size(); i++) {
			Date nextInventoryAudit = inventorylist.get(i).getNextAudit();
			LocalDate audit = nextInventoryAudit.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			if  (lastAudit == null  || audit.isAfter(lastAudit)) {
				lastAudit = audit;
			}
		}
		return lastAudit;
	}

	
}
