package com.sw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.sw.beans.*;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class TaskDao 
{
	public List<Task> getTasks()
	{
		List<Task> taskset = new ArrayList();
		
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
		String sql = "SELECT * from tasks";
		PreparedStatement pstatement = connection.prepareStatement(sql);
		
		return pstatement;
	}
	
	public boolean deleteTask(int task_id)
	{
		throw new NotImplementedException();
		
	}
	
	public boolean insertTask(int task_id)
	{
		throw new NotImplementedException();
	}
}
