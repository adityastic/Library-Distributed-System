package com.aditya.server.utils;

import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class StartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
    	try {
			Statement stmt = DatabaseConnector.getConnection().createStatement();
			
			stmt.executeUpdate(
					"CREATE TABLE IF NOT EXISTS book (\n" + 
					"  id INTEGER IDENTITY NOT NULL,\n" + 
					"  name varchar(45) DEFAULT NULL,\n" + 
					"  price int DEFAULT NULL" +
					");");
			
			stmt.executeUpdate(
					"CREATE TABLE IF NOT EXISTS student (\n" + 
					"  id INTEGER IDENTITY NOT NULL,\n" + 
					"  name varchar(45) DEFAULT NULL" +
					");");
			
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS issue_book (\n" + 
					"  id INTEGER IDENTITY NOT NULL,\n" + 
					"  student_id int DEFAULT NULL,\n" + 
					"  book_id int DEFAULT NULL" +
					");");
			
			System.out.println("Executed Queries");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // Perform action during application's shutdown
    }
}