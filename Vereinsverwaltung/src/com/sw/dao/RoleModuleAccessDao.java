package com.sw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoleModuleAccessDao {
	
	public boolean insertRoleModuleAccess (int role_id, int[] res) {
			String sql = "INSERT INTO swp_system.role_module_access (ROLE_role_id, MODULE_module_id) values (?, ?)";
			try (	Connection connection = DBConnection.getConnectionToDatabase();
					PreparedStatement pstatement = connection.prepareStatement(sql);
					){
				for (int i=0; i<res.length; i++) {
					pstatement.setInt(1, role_id);
					pstatement.setInt(2, res[i]);
					pstatement.addBatch();
				}
				pstatement.executeBatch();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		return true;
	}
	
	public boolean deleteRoleModuleAccess (int role_id) {
		try (	Connection connection = DBConnection.getConnectionToDatabase();
				PreparedStatement pstatement = createPrepDeleteModuleAccess(connection, role_id);
				){
			
			int i = pstatement.executeUpdate();
			if(i>0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	private PreparedStatement createPrepDeleteModuleAccess(Connection connection, int role_id) throws SQLException {
		String sql = "DELETE FROM swp_system.role_module_access WHERE ROLE_role_id = ?";
		PreparedStatement pstatement = connection.prepareStatement(sql);
		pstatement.setInt(1, role_id);
		
		return pstatement;
	}

	public ArrayList<Integer> getModuleIdByRoleId (int role_id) {
		ArrayList<Integer> list =  new ArrayList<Integer>();
		
		try (	Connection connection = DBConnection.getConnectionToDatabase();
				PreparedStatement pstatement = createPrepGetModuleByRoleId(connection, role_id);
				ResultSet set = pstatement.executeQuery();
				){
	
			while (set.next()) {
				list.add(set.getInt("MODULE_module_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	private PreparedStatement createPrepGetModuleByRoleId(Connection connection, int role_id) throws SQLException {
		String sql = "SELECT MODULE_module_id FROM swp_system.role_module_access WHERE ROLE_role_id = ?";
		PreparedStatement pstatement = connection.prepareStatement(sql);
		pstatement.setInt(1, role_id);
		
		return pstatement;
	}
}