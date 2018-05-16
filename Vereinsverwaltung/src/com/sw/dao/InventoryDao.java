package com.sw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.sw.beans.Inventory;
import com.sw.beans.Member;
import com.sw.security.ParseDate;

public class InventoryDao {
	
	
	
private Connection InventoryConnection = null;
	
	public InventoryDao() {
		Connection connection = DBConnection.getConnectionToDatabase();
		if(connection != null){
			this.InventoryConnection = connection;
		}
		else{
			System.out.println("InventoryConnection(): No Connection to Database possible");
		}
		
	}

	public List<Inventory> readInventory()
	{
		ResultSet set = null;
		
		if(this.InventoryConnection != null)
		{
			List<Inventory> List = new ArrayList<Inventory>();
			try
			{
				String sql = "Select * from INVENTORY "; 
				Statement statement = this.InventoryConnection.createStatement();
				set = statement.executeQuery(sql);
				
				ParseDate parse = new ParseDate();
				
				while(set.next())
				{
				String category= set.getString("category");
				String description= set.getString("description");
				String purchaseValue= set.getString("purchase_value");
				String lastAudit= set.getString("last_audit");
				String nextAudit= set.getString("next_audit");
				String acquisitionDate= set.getString("next_audit") ;
				String lastauditby= set.getString("last_audit_by") ;
				int inventoryId = set.getInt("inventory_id");
					
				Inventory inventory = new Inventory();
				inventory.setInventoryId(inventoryId);
				inventory.setCategory(category);
				inventory.setDescription(description);
				inventory.setPurchaseValue(purchaseValue);
				inventory.setLastAudit(parse.autoConvert(lastAudit));
				inventory.setNextAudit(parse.autoConvert(nextAudit));
				inventory.setAcquisitionDate(parse.autoConvert(acquisitionDate));
				inventory.setLastauditby(lastauditby);
			
				List.add(inventory);
			
				}
				
			}
			catch(Exception ex)
			{
				System.out.println("Exception readInventory():");
				ex.printStackTrace();
			}
			return List;
		}
		
		System.out.println("No inventory to read");
		return null;

	}
	
	public boolean insertInventory(Inventory inventory)
	{
		try
		{
			String sql = "Insert into swp_system.INVENTORY (category, description, purchase_value, last_audit, next_audit, acquisition_date, last_audit_by) values ( ?, ?, ?, ?, ?, ?,?)";
			
			PreparedStatement preparedStmt = this.InventoryConnection.prepareStatement(sql);

			preparedStmt.setObject(1, inventory.getCategory(), Types.VARCHAR);
			preparedStmt.setObject(2, inventory.getDescription(), Types.VARCHAR);
			
			preparedStmt.setObject(3, inventory.getPurchaseValue(), Types.VARCHAR);
			preparedStmt.setObject(4, inventory.getLastAudit(), Types.DATE);
			preparedStmt.setObject(5, inventory.getNextAudit(), Types.DATE);
			preparedStmt.setObject(6, inventory.getAcquisitionDate(),Types.DATE);
			preparedStmt.setObject(7, inventory.getLastauditby(), Types.VARCHAR);
			
		    preparedStmt.execute();
		}
		catch(SQLException sqlE)
		{
			System.out.println("SQLException insertInventory() : ");
			sqlE.printStackTrace();
			return false;
		}
		catch(Exception ex)
		{
			System.out.println("Exception insertInventory() : ");
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public boolean deleteInventory(Inventory inventory) throws SQLException, Exception
	{
		try {
			String sql ="DELETE FROM swp_system.Inventory WHERE inventory_Id = ?";
			PreparedStatement preparedStmt = this.InventoryConnection.prepareStatement(sql);
			preparedStmt.setObject(1, inventory.getInventoryId(), Types.INTEGER);
			preparedStmt.executeUpdate();
		    preparedStmt.close();
		    
		}
		
			catch(SQLException sqlE)
			{
				System.out.println("SQLException deleteInventory() : ");
				sqlE.printStackTrace();
				return false;
			}
			catch(Exception ex)
			{
				System.out.println("Exception deleteInventory() : ");
				ex.printStackTrace();
				return false;
			}
			return true;
	}
	
	public boolean editInventory(Inventory inventory)
	{
		try
		{		String sql = "Update swp_system.INVENTORY SET category = ?, description = ?, purchase_value = ?, last_audit = ?, next_audit = ?, acquisition_date = ?,  last_audit_by= ?";
				
				PreparedStatement preparedStmt = this.InventoryConnection.prepareStatement(sql);
				preparedStmt.setObject(1, inventory.getCategory(), Types.VARCHAR);
				preparedStmt.setObject(2, inventory.getDescription(), Types.VARCHAR);
				preparedStmt.setObject(3, inventory.getPurchaseValue(), Types.VARCHAR);
				preparedStmt.setObject(4, inventory.getLastAudit(), Types.DATE);
				preparedStmt.setObject(5, inventory.getNextAudit(), Types.DATE);
				preparedStmt.setObject(6, inventory.getAcquisitionDate(), Types.DATE);
				preparedStmt.setObject(7, inventory.getLastauditby(), Types.VARCHAR);
			    preparedStmt.executeUpdate();
			    preparedStmt.close();	
			
		}
		catch(SQLException sqlE)
		{
			System.out.println("SQLException editInventory() : ");
			sqlE.printStackTrace();
			return false;
		}
		catch(Exception ex)
		{
			System.out.println("Exception editInventory() : ");
			ex.printStackTrace();
			return false;
		}
		return true;
	}

}
