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

@WebServlet("/registerInventory")
public class InventoryAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Inventory inventory = new Inventory();
	
		inventory.setCategory(request.getParameter("category"));
		inventory.setDescription(request.getParameter("description"));
		inventory.setPurchaseValue(request.getParameter("purchase_value"));
		inventory.setLastauditby(request.getParameter("last_audit_by"));
		
		ParseDate parser = new ParseDate();
		
		String tempLastAudit = request.getParameter("last_audit");
		String tempNextAudit = request.getParameter("next_audit");
		String tempAquisitionDate = request.getParameter("acquisition_date");
		
		inventory.setLastAudit(parser.autoConvert(tempLastAudit));
		inventory.setNextAudit(parser.autoConvert(tempNextAudit));
		inventory.setAcquisitionDate(parser.autoConvert(tempAquisitionDate));
		
		InventoryDao invendao = new InventoryDao();
		invendao.insertInventory(inventory);

		
		request.getRequestDispatcher("./InventoryServlet").forward(request, response);
	}

}
