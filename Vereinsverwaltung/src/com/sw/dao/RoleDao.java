package com.sw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sw.beans.Role;

public class RoleDao {

	public List<Role> getRoles () {
		List<Role> roleset = new ArrayList<>();
		
		try (	Connection connection = DBConnection.getConnectionToDatabase();
				PreparedStatement pstatement = createPrepGetRoles(connection);
				ResultSet set = pstatement.executeQuery();
				){
	
			while (set.next()) {
				Role role = new Role();
				role.setRole_id(set.getInt("role_id"));
				role.setRole_description(set.getString("description"));
				roleset.add(role);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return roleset;
	}
	
	private PreparedStatement createPrepGetRoles(Connection connection) throws SQLException {
		String sql = "SELECT * FROM role";
		PreparedStatement pstatement = connection.prepareStatement(sql);
		
		return pstatement;
	}

	public Role getRoleByName (String name) {		
		try (	Connection connection = DBConnection.getConnectionToDatabase();
				PreparedStatement pstatement = createPrepGetRoleByName(connection, name);
				ResultSet set = pstatement.executeQuery();
				){
	
			while (set.next()) {
				Role role = new Role();
				role.setRole_id(set.getInt("role_id"));
				role.setRole_description(set.getString("description"));
	
				return role;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private PreparedStatement createPrepGetRoleByName(Connection connection, String name) throws SQLException {
		String sql = "SELECT * FROM role WHERE description = ?";
		PreparedStatement pstatement = connection.prepareStatement(sql);
		pstatement.setString(1, name);
		
		return pstatement;
	}

	public boolean insertRole (Role role) {
		try (	Connection connection = DBConnection.getConnectionToDatabase();
				PreparedStatement pstatement = createPrepInsertRole(connection, role);
				){
			
			int i = pstatement.executeUpdate();
			if(i>0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	private PreparedStatement createPrepInsertRole(Connection connection, Role role) throws SQLException {
		String sql = "INSERT INTO swp_system.ROLE (description) VALUES (?)";
		PreparedStatement pstatement = connection.prepareStatement(sql);
		pstatement.setString(1, role.getRole_description());
		
		return pstatement;
	}

	public boolean deleteRole (int role_id) {
		try (	Connection connection = DBConnection.getConnectionToDatabase();
				PreparedStatement pstatement = createPrepDeleteRole(connection, role_id);
				){
			
			int i = pstatement.executeUpdate();
			if(i>0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	private PreparedStatement createPrepDeleteRole(Connection connection, int role_id) throws SQLException {
		String sql = "DELETE FROM swp_system.ROLE WHERE role_id = ?";
		PreparedStatement pstatement = connection.prepareStatement(sql);
		pstatement.setInt(1, role_id);
		
		return pstatement;
	}

	public boolean updateRole (Role role) {
		try (	Connection connection = DBConnection.getConnectionToDatabase();
				PreparedStatement pstatement = createPrepUpdateRoles(connection, role);
				){
			
			int i = pstatement.executeUpdate();
			if(i>0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	private PreparedStatement createPrepUpdateRoles(Connection connection, Role role) throws SQLException {
		String sql = "UPDATE swp_system.role SET description = ? WHERE role_id = ?";
		PreparedStatement pstatement = connection.prepareStatement(sql);
		pstatement.setString(1, role.getRole_description());
		pstatement.setInt(2, role.getRole_id());
		
		return pstatement;
	}
}