package com.sw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import com.sw.beans.*;

public class TaskDao 
{
	public List<Task> getTasks()
	{
		List<Task> taskset = new ArrayList<Task>();
		
		try( Connection connection = DBConnection.getConnectionToDatabase();
			 PreparedStatement pstatement = createPrepGetTasks(connection);
			ResultSet set = pstatement.executeQuery();
			) 
		{
			while(set.next())
			{
				Task task = new Task();
				task.setCompleted(set.getBoolean("completed"));
				task.setCreationDate(set.getDate("creationDate"));
				task.setCreator(set.getString("creator"));
				task.setDoneBy(set.getString("doneBy"));
				task.setTaskId(set.getInt("task_id"));
				task.setText(set.getString("text"));
				task.setTitel(set.getString("titel"));
				taskset.add(task);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return taskset;
	}
	
	private PreparedStatement createPrepGetTasks(Connection connection) throws SQLException
	{
		String sql = "SELECT * from tasks ORDER BY completed";
		PreparedStatement pstatement = connection.prepareStatement(sql);
		
		return pstatement;
	}
	
	public boolean deleteTask(int taskId) {
		try (	Connection con = DBConnection.getConnectionToDatabase();
				PreparedStatement pstatement = createPrepDeleteTask(con, taskId);
				) {
			
			int i = pstatement.executeUpdate();
			if(i>0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	private PreparedStatement createPrepDeleteTask(Connection con, int taskId) throws SQLException {
		String sql = "DELETE FROM swp_system.TASKS WHERE task_id = ?";
		PreparedStatement pstatement = con.prepareStatement(sql);
		pstatement.setInt(1, taskId);
		
		return pstatement;
	}

	public boolean completeTask (int task_id) {
		try (	Connection con = DBConnection.getConnectionToDatabase();
				PreparedStatement pstatement = createPrepCompleteTask(con, task_id);
				) {
			
			int i = pstatement.executeUpdate();
			if(i>0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	private PreparedStatement createPrepCompleteTask(Connection con, int task_id) throws SQLException {
		String sql = "UPDATE swp_system.TASKS SET completed= true WHERE task_id = ?";
		PreparedStatement pstatement = con.prepareStatement(sql);
		pstatement.setInt(1, task_id);
		
		return pstatement;
	}

	public boolean insertTask (Task task) {
		try (	Connection con = DBConnection.getConnectionToDatabase();
				PreparedStatement pstatement = createPrepInsertTask(con, task);
				) {
			
			int i = pstatement.executeUpdate();
			if(i>0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	private PreparedStatement createPrepInsertTask(Connection con, Task task) throws SQLException {
		String sql = "INSERT INTO swp_system.TASKS (completed, titel, text, creationDate, creator) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement pstatement = con.prepareStatement(sql);
		pstatement.setBoolean(1, task.isCompleted());
		pstatement.setString(2, task.getTitel());
		pstatement.setString(3, task.getText());
		pstatement.setObject(4, task.getCreationDate(), Types.DATE);
		pstatement.setString(5, task.getCreator());
		
		return pstatement;		
	}
}
