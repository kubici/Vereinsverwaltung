package com.sw.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.IllegalFormatException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sw.beans.Inventory;
import com.sw.dao.InventoryDao;


/**
 * Servlet implementation class InventoryAddServlet
 */
@WebServlet("/registerInventory")
public class InventoryAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InventoryAddServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Inventory inventory = new Inventory();
	
		inventory.setCategory(request.getParameter("category"));
		inventory.setDescription(request.getParameter("description"));
		inventory.setPurchaseValue(request.getParameter("purchase_value"));
		
		String tempLastAudit = request.getParameter("last_audit");
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		try
		{
			Date date = (Date) dateFormat.parse(tempLastAudit);
			inventory.setLastAudit(date);
			
		}
		catch(IllegalFormatException ife)
		{
			System.out.println("InventoryAddServlet.java - doPost() - Problem with DateFormat: lastAudit");
			ife.printStackTrace();
		}
		catch(Exception ex)
		{
			System.out.println("InventoryAddServlet.java - doPost() - Problem with DateFormat: lastAudit");
			ex.printStackTrace();
		}
		
		String tempNextAudit = request.getParameter("next_audit");
		DateFormat dateFormatnextAudit = new SimpleDateFormat("dd.MM.yyyy");
		try
		{
			Date date = (Date) dateFormatnextAudit.parse(tempNextAudit);
			inventory.setNextAudit(date);
			
		}
		catch(IllegalFormatException ife)
		{
			System.out.println("InventoryAddServlet.java - doPost() - Problem with DateFormat: nextAudit");
			ife.printStackTrace();
		}
		catch(Exception ex)
		{
			System.out.println("InventoryAddServlet.java - doPost() - Problem with DateFormat: nextAudit");
			ex.printStackTrace();
		}String tempAquisitionDate = request.getParameter("acquisition_date");
		DateFormat aquisitionDate = new SimpleDateFormat("dd.MM.yyyy");
		try
		{
			Date date = (Date) aquisitionDate.parse(tempAquisitionDate);
			inventory.setAcquisitionDate(date);
			
		}
		catch(IllegalFormatException ife)
		{
			System.out.println("InventoryAddServlet.java - doPost() - Problem with DateFormat: acquisitionDate");
			ife.printStackTrace();
		}
		catch(Exception ex)
		{
			System.out.println("InventoryAddServlet.java - doPost() - Problem with DateFormat: acquisitionDate");
			ex.printStackTrace();
		}
		
		inventory.setLastauditby(request.getParameter("last_audit_by"));
		
		InventoryDao invendao = new InventoryDao();
		invendao.insertInventory(inventory);

		response.sendRedirect("./overviewInventory.jsp");
	
	}

}
