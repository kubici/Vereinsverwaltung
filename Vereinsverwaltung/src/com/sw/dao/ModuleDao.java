package com.sw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sw.beans.Module;
/**
 * 
 * @author tobi
 *
 */
public class ModuleDao {
	
	public List<Module> getModules () {
		List<Module> moduleset = new ArrayList<>();
		
		try (	Connection connection = DBConnection.getConnectionToDatabase();
				PreparedStatement pstatement = createPrepGetModules(connection);
				ResultSet set = pstatement.executeQuery();
				) 
		{
			while (set.next()) {
				Module module = new Module();
				module.setModule_id(set.getInt("module_id"));
				module.setModule_description(set.getString("description"));
				moduleset.add(module);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return moduleset;
	}

	private PreparedStatement createPrepGetModules(Connection connection) throws SQLException {
		String sql = "SELECT * FROM module";
		PreparedStatement pstatement = connection.prepareStatement(sql);
		
		return pstatement;
	}
	
}