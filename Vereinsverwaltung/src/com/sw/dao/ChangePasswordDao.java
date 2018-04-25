package com.sw.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sw.beans.Member;
import com.sw.security.HashText;

public class ChangePasswordDao {
	
	public boolean changePassword (Member member) {
		try(Connection con = DBConnection.getConnectionToDatabase();
			PreparedStatement pstatement = createPreparedStatement(con, member);
				) {
			
			Integer i = pstatement.executeUpdate();
			
			if (i>0) return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public PreparedStatement createPreparedStatement (Connection con, Member member) throws SQLException, NoSuchAlgorithmException {
		String sql = "UPDATE member SET password = ? WHERE username = ? AND password = ?";
		PreparedStatement pstatement = con.prepareStatement(sql);
		HashText ht = new HashText();
		pstatement.setString(1, ht.sha256(member.getNewPassword()));
		pstatement.setString(2, member.getUsername());
		pstatement.setString(3, ht.sha256(member.getPassword()));
		return pstatement;
	}
	
}
