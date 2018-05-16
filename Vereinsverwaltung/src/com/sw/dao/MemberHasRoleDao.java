package com.sw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberHasRoleDao {
	
	public boolean insertMemberHasRole (int member_id, int[] res) {
		String sql = "INSERT INTO swp_system.member_has_role (ROLE_role_id, MEMBER_member_id) values (?, ?)";
		try (	Connection connection = DBConnection.getConnectionToDatabase();
				PreparedStatement pstatement = connection.prepareStatement(sql);
				) {
			for (int i=0; i<res.length; i++) {
				pstatement.setInt(1, res[i]);
				pstatement.setInt(2, member_id);
				pstatement.addBatch();
			}
			pstatement.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean deleteMemberHasRoleREFERENCEmember_id (int member_id) {
		try( 	Connection connection = DBConnection.getConnectionToDatabase();
				PreparedStatement pstatement = createPrepDeleteMemberHasRole (connection, member_id);
				) {
			int i = pstatement.executeUpdate();
					
			if(i>0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return false;
	}

	private PreparedStatement createPrepDeleteMemberHasRole(Connection connection, int member_id) throws SQLException {
		String sql = "DELETE FROM swp_system.member_has_role WHERE MEMBER_member_id = ?";
		PreparedStatement pstatement = connection.prepareStatement(sql);
		pstatement.setInt(1, member_id);
		
		return pstatement;
	}
	
	public boolean deleteMemberHasRoleREFERENCErole_id (int role_id) {
		try( 	Connection connection = DBConnection.getConnectionToDatabase();
				PreparedStatement pstatement = createPrepDeleteMemberHasRoleII (connection, role_id);
				) {
			int i = pstatement.executeUpdate();
					
			if(i>0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return false;
	}

	private PreparedStatement createPrepDeleteMemberHasRoleII (Connection connection, int role_id) throws SQLException {
		String sql = "DELETE FROM swp_system.member_has_role WHERE ROLE_role_id = ?";
		PreparedStatement pstatement = connection.prepareStatement(sql);
		pstatement.setInt(1, role_id);
		
		return pstatement;
	}
	
	public ArrayList<Integer> getRoleIdByMemberId (int member_id) {
		ArrayList<Integer> list = new ArrayList<>();
		
		try (	Connection connection = DBConnection.getConnectionToDatabase();
				PreparedStatement pstatement = createPrepGetRoleIdByMemberId (connection, member_id);
				ResultSet set = pstatement.executeQuery();
				){
			
			while (set.next()) {
				list.add(set.getInt("ROLE_role_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	private PreparedStatement createPrepGetRoleIdByMemberId(Connection connection, int member_id) throws SQLException {
		String sql = "SELECT ROLE_role_id FROM swp_system.member_has_role WHERE MEMBER_member_id = ?";
		PreparedStatement pstatement = connection.prepareStatement(sql);
		pstatement.setInt(1, member_id);
		
		return pstatement;
	}
}
