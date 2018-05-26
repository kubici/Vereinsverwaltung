package com.sw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.sw.beans.Inventory;
import com.sw.security.ParseDate;

public class InventoryDao {

	public List<Inventory> readInventory()
	{
		List<Inventory> List = new ArrayList<Inventory>();
		
			try (	Connection connection = DBConnection.getConnectionToDatabase();
					PreparedStatement pstatement = createPrepGetInventory(connection);
					ResultSet set = pstatement.executeQuery();
					)
			{
				
				ParseDate parse = new ParseDate();
				
				while(set.next())
				{
				String category= set.getString("category");
				String description= set.getString("description");
				String purchaseValue= set.getString("purchase_value");
				String lastAudit= set.getString("last_audit");
				String nextAudit= set.getString("next_audit");
				String acquisitionDate= set.getString("acquisition_date") ;
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
				ex.printStackTrace();
			}
			
			return List;
	}

	private PreparedStatement createPrepGetInventory(Connection connection) throws SQLException {
		String sql = "SELECT * FROM swp_system.INVENTORY";
		PreparedStatement pstatement = connection.prepareStatement(sql);
		
		return pstatement;
	}
	
	public List<Inventory> readInventoryNextAudit()
	{
		List<Inventory> List = new ArrayList<Inventory>();
		
			try (	Connection connection = DBConnection.getConnectionToDatabase();
					PreparedStatement pstatement = createPrepGetNextAudit(connection);
					ResultSet set = pstatement.executeQuery();
					)
			{
				
				ParseDate parse = new ParseDate();
				
				while(set.next())
				{
				String category= set.getString("category");
				String description= set.getString("description");
				String purchaseValue= set.getString("purchase_value");
				String lastAudit= set.getString("last_audit");
				String nextAudit= set.getString("next_audit");
				String acquisitionDate= set.getString("acquisition_date") ;
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
				ex.printStackTrace();
			}
			
			return List;
	}

	private PreparedStatement createPrepGetNextAudit(Connection connection) throws SQLException {
		String sql = "SELECT * FROM swp_system.INVENTORY ORDER BY next_audit DESC";
		PreparedStatement pstatement = connection.prepareStatement(sql);
		
		return pstatement;
	}
	
	public boolean insertInventory(Inventory inventory)
	{
		try (	Connection connection = DBConnection.getConnectionToDatabase();
				PreparedStatement pstatement = createPrepInsertInventory(connection, inventory);
				){
			
			pstatement.execute();
		}
		catch (SQLException sqlE) {
			sqlE.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	private PreparedStatement createPrepInsertInventory(Connection connection, Inventory inventory) throws SQLException {
		String sql = "Insert into swp_system.INVENTORY (category, description, purchase_value, last_audit, next_audit, acquisition_date, last_audit_by) values ( ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStmt = connection.prepareStatement(sql);

		preparedStmt.setObject(1, inventory.getCategory(), Types.VARCHAR);
		preparedStmt.setObject(2, inventory.getDescription(), Types.VARCHAR);
		preparedStmt.setObject(3, inventory.getPurchaseValue(), Types.VARCHAR);
		preparedStmt.setObject(4, inventory.getLastAudit(), Types.DATE);
		preparedStmt.setObject(5, inventory.getNextAudit(), Types.DATE);
		preparedStmt.setObject(6, inventory.getAcquisitionDate(),Types.DATE);
		preparedStmt.setObject(7, inventory.getLastauditby(), Types.VARCHAR);
		
		return preparedStmt;
	}

	public boolean deleteInventory(Inventory inventory)	{
		try  (	Connection connection = DBConnection.getConnectionToDatabase();
				PreparedStatement pstatement  = createPrepDeleteInventory(connection, inventory)
				){
			
			pstatement.executeUpdate();   
		}
		catch(SQLException sqlE){
			
			System.out.println("SQLException deleteInventory() : ");
			sqlE.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	private PreparedStatement createPrepDeleteInventory(Connection connection, Inventory inventory) throws SQLException {
		String sql ="DELETE FROM swp_system.Inventory WHERE inventory_Id = ?";
		PreparedStatement preparedStmt = connection.prepareStatement(sql);
		preparedStmt.setObject(1, inventory.getInventoryId(), Types.INTEGER);
		
		return preparedStmt;
	}

	public boolean editInventory(Inventory inventory)
	{
		try (	Connection connection = DBConnection.getConnectionToDatabase();
				PreparedStatement pstatement = createPrepEditInventory(connection, inventory);
				) {		
			
			pstatement.executeUpdate();
		}
		catch(SQLException sqlE) {
			sqlE.printStackTrace();
			return false;
		}
		return true;
	}
	
	private PreparedStatement createPrepEditInventory(Connection connection, Inventory inventory) throws SQLException {
		String sql = "Update swp_system.INVENTORY SET category = ?, description = ?, purchase_value = ?, last_audit = ?, next_audit = ?, acquisition_date = ?,  last_audit_by= ? WHERE inventory_id = ?";
		PreparedStatement preparedStmt = connection.prepareStatement(sql);
		preparedStmt.setObject(1, inventory.getCategory(), Types.VARCHAR);
		preparedStmt.setObject(2, inventory.getDescription(), Types.VARCHAR);
		preparedStmt.setObject(3, inventory.getPurchaseValue(), Types.VARCHAR);
		preparedStmt.setObject(4, inventory.getLastAudit(), Types.DATE);
		preparedStmt.setObject(5, inventory.getNextAudit(), Types.DATE);
		preparedStmt.setObject(6, inventory.getAcquisitionDate(), Types.DATE);
		preparedStmt.setObject(7, inventory.getLastauditby(), Types.VARCHAR);
		preparedStmt.setInt(8, inventory.getInventoryId());
		
		return preparedStmt;
	}

	public Inventory getInventoryById (int inventory_id) {
		String sql = "SELECT * FROM swp_system.INVENTORY WHERE inventory_id = ?";
		try (	Connection connection = DBConnection.getConnectionToDatabase();
				PreparedStatement pstatement = connection.prepareStatement(sql);
				) {
			pstatement.setInt(1, inventory_id);
			ResultSet set = pstatement.executeQuery();
			
			while (set.next()) {
				String category= set.getString("category");
				String description= set.getString("description");
				String purchaseValue= set.getString("purchase_value");
				String lastAudit= set.getString("last_audit");
				String nextAudit= set.getString("next_audit");
				String acquisitionDate= set.getString("next_audit") ;
				String lastauditby= set.getString("last_audit_by") ;
				int inventoryId = set.getInt("inventory_id");
					
				ParseDate parse = new ParseDate();
				
				Inventory inventory = new Inventory();
				inventory.setInventoryId(inventoryId);
				inventory.setCategory(category);
				inventory.setDescription(description);
				inventory.setPurchaseValue(purchaseValue);
				inventory.setLastAudit(parse.autoConvert(lastAudit));
				inventory.setNextAudit(parse.autoConvert(nextAudit));
				inventory.setAcquisitionDate(parse.autoConvert(acquisitionDate));
				inventory.setLastauditby(lastauditby);
				
				return inventory;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
