package com.sw.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sw.beans.Inventory;
import com.sw.dao.InventoryDao;

/**
 * Servlet implementation class InventoryDeleteServlet
 */
@WebServlet("/deleteInventory")
public class InventoryDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InventoryDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int selectedInventoryId = Integer.parseInt(request.getParameter("id"));
		System.out.println("Temp: " + selectedInventoryId);
		
		request.setAttribute("inventory_Id", selectedInventoryId);
		
		Inventory inventory = new Inventory();
		inventory.setInventoryId(selectedInventoryId);

		InventoryDao invendao = new InventoryDao();

		try 
		{
			invendao.deleteInventory(inventory);
		}
		catch(Exception ex)
		{
			
		}
		
		System.out.println("Inventory : "+ inventory.getInventoryId()+" is deleted from database");
		request.getRequestDispatcher("./overviewInventory.jsp").forward(request, response);
		}
	}
	

