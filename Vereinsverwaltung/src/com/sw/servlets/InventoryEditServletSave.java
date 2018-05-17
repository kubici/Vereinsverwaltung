package com.sw.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sw.beans.Inventory;
import com.sw.dao.InventoryDao;
import com.sw.security.ParseDate;

@WebServlet("/InventoryEditServletSave")
public class InventoryEditServletSave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int selectedInventoryId = Integer.parseInt(request.getParameter("id"));

		InventoryDao invenDao = new InventoryDao();
		Inventory inventory = new Inventory();
		ParseDate parse = new ParseDate();
		
		inventory.setInventoryId(selectedInventoryId);
			
		if(request.getParameter("category") != null)
			inventory.setCategory((String) request.getParameter("category"));
		if(request.getParameter("description") != null)
			inventory.setDescription((String) request.getParameter("description"));

		if(request.getParameter("purchase_value") != null)
			inventory.setPurchaseValue((String) request.getParameter("purchase_value"));
	
		if(request.getParameter("last_audit") != null) {
			String date = request.getParameter("last_audit");
			inventory.setLastAudit(parse.autoConvert(date));	
		}
		if(request.getParameter("next_audit") != null) {
			String date = request.getParameter("next_audit");
			inventory.setNextAudit(parse.autoConvert(date));
		}
		if(request.getParameter("acquisition_date") != null) {
			String date = request.getParameter("acquisition_date");
			inventory.setAcquisitionDate(parse.autoConvert(date));
		}
		
		if(request.getParameter("last_audit_by") != null)
			inventory.setLastauditby((String) request.getParameter("last_audit_by"));

		invenDao.editInventory(inventory);

		response.sendRedirect("./overviewInventory.jsp");		
	}
}
