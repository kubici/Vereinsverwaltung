package com.sw.servlets;

import java.io.IOException;
import java.sql.SQLException;

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

/**
 * Servlet implementation class InventoryDeleteServlet
 */
@WebServlet("/deleteInventory")
public class InventoryDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InventoryDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
		catch(SQLException sqle)
		{
			
		}
		catch(Exception ex)
		{
			
		}
		
		System.out.println("Inventory : "+ inventory.getInventoryId()+" is deleted from database");
		request.getRequestDispatcher("./overviewInventory.jsp").forward(request, response);
		//response.sendRedirect("./overviewInventory.jsp");
		}
	}
	

