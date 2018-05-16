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

@WebServlet("/editInventory")
public class InventoryEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		int inventoryId = Integer.parseInt(request.getParameter("id"));
		
		InventoryDao inventoryDao = new InventoryDao();
		
		List<Inventory> inventoryList = inventoryDao.readInventory();
		
		Inventory inventory = null;
		if(inventoryList != null)
		{
			for(int i = 0; i < inventoryList.size(); i ++)
			{
				if(inventoryList.get(i) != null && inventoryList.get(i).getInventoryId() == inventoryId)
				{
					inventory = inventoryList.get(i);
				}
			}
		}
		
		request.setAttribute("description", inventory.getDescription());
		request.setAttribute("category", inventory.getCategory());
		request.setAttribute("purchaseValue", inventory.getPurchaseValue());
		request.setAttribute("lastAudit", inventory.getLastAudit());
		request.setAttribute("nextAudit", inventory.getNextAudit());
		request.setAttribute("acquisitionDate", inventory.getAcquisitionDate());
		request.setAttribute("lastauditby", inventory.getLastauditby());
		
		request.getRequestDispatcher("./editInventory.jsp").forward(request, response);
		
	}
}


