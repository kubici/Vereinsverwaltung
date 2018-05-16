package com.sw.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sw.beans.Inventory;
import com.sw.beans.Member;
import com.sw.dao.InventoryDao;
import com.sw.dao.MemberDao;
import com.sw.security.Generator;
import com.sw.security.HashText;
import com.sw.security.ParseDate;

/**
 * Servlet implementation class InventoryEditServlet
 */
@WebServlet("/editInventory")
public class InventoryEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InventoryEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		int selectedInventoryId = Integer.parseInt(request.getParameter("id"));
		System.out.println("Temp: " + selectedInventoryId);
		
		InventoryDao invenDao = new InventoryDao();
		Inventory inventory = new Inventory();
		ParseDate parse = new ParseDate();
			
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
		if(request.getParameter("acqusition_date") != null) {
			String date = request.getParameter("acqusition_date");
			inventory.setAcquisitionDate(parse.autoConvert(date));
		}
		if(request.getParameter("last_audit_by") != null)
			inventory.setLastauditby((String) request.getParameter("last_audit_by"));

//		System.out.println(inventory);
//		request.setAttribute("category", inventory.getCategory());
//		request.setAttribute("description", inventory.getDescription());
//
//		request.setAttribute("purchase_value", inventory.getPurchaseValue());
//		request.setAttribute("last_audit", parse.convertString(inventory.getLastAudit()));
//		
//		request.setAttribute("next_audit", parse.convertString(inventory.getNextAudit()));
//		request.setAttribute("acquisition_date", parse.convertString(inventory.getAcquisitionDate()));
//	
//		request.setAttribute("last_audit_by", inventory.getLastauditby());
	
//		request.getRequestDispatcher("./editInventory.jsp").forward(request, response);
		
		System.out.print("doPost() editInventory: ");
		invenDao.editInventory(inventory);
		
		request.getRequestDispatcher("./editInventory.jsp").forward(request, response);
//		response.sendRedirect("./overviewInventory.jsp");
		
		
	}
}


