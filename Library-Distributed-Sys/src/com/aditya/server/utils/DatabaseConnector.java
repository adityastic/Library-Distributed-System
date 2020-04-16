package com.aditya.server.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
	static Connection connection = null;

	public static Connection getConnection() {

		if (connection == null) {
			try {
				Class.forName("org.hsqldb.jdbcDriver");
				connection = (Connection) DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/libraryDB", "SA", "");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}
}
