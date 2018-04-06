package com.sw.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import com.sw.dao.DBConnection;

class dbConnectionTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void connectionTest() 
	{
		Connection connection = DBConnection.getConnectionToDatabase();
		assertNotNull(connection);
	}
}
