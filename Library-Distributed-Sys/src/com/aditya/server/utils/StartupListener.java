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
			stmt.executeUpdate("INSERT IGNORE INTO book VALUES (1,'Harry Potter',99),(3,'Sisters',49),(4,'Flamingo',65),(5,'Thook',99),(6,'Crook',9);");
			
			stmt.executeUpdate(
					"CREATE TABLE IF NOT EXISTS student (\n" + 
					"  id INTEGER IDENTITY NOT NULL,\n" + 
					"  name varchar(45) DEFAULT NULL" +
					");");
			stmt.executeUpdate("INSERT IGNORE INTO student VALUES (1,'Aditya'),(2,'Yash'),(3,'Rupal'),(4,'Vivek');");
			
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS issue_book (\n" + 
					"  id INTEGER IDENTITY NOT NULL,\n" + 
					"  student_id int DEFAULT NULL,\n" + 
					"  book_id int DEFAULT NULL" +
					");");
			stmt.executeUpdate("INSERT IGNORE INTO issue_book VALUES (7,4,5),(8,1,1),(9,2,1),(10,3,1),(11,1,1),(12,1,1),(13,1,4),(14,1,5),(15,1,6),(16,1,1);");
	
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