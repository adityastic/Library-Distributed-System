package com.aditya.server.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aditya.model.Book;
import com.aditya.server.utils.DatabaseConnector;

public enum BookDao {
	INSTANCE;

	private BookDao() {
	}

	public boolean addBook(String bookName, String price) {
		CallableStatement callableStatement;
		try {
			callableStatement = DatabaseConnector.getConnection().prepareCall("INSERT INTO book VALUES(null,?,?);");
			callableStatement.setString(1, bookName);
			callableStatement.setBigDecimal(2, new BigDecimal(price));
			return (!callableStatement.execute()) ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Book> getAllBooks() {
		ArrayList<Book> arrayList = new ArrayList<>();
		try {
			PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement("SELECT * FROM book");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				arrayList.add(
						new Book(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			return arrayList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Book> getBooksByName(String name) {
		ArrayList<Book> arrayList = new ArrayList<>();
		try {
			PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement("SELECT * FROM book WHERE name = ?");
			statement.setString(1, name);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				arrayList.add(
						new Book(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			return arrayList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean deleteBook(int id) {
		CallableStatement callableStatement;
		try {
			callableStatement = DatabaseConnector.getConnection().prepareCall("DELETE FROM book WHERE id = ?;");
			callableStatement.setInt(1, id);
			return (!callableStatement.execute()) ? true : false;
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		return false;
	}

	public boolean deleteAllBooks() {
		try {
			Statement stmt = DatabaseConnector.getConnection().createStatement();
			stmt.executeUpdate("TRUNCATE TABLE book RESTART IDENTITY AND COMMIT");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateBook(int id, String name, String price) {
		CallableStatement callableStatement;
		try {
			callableStatement = DatabaseConnector.getConnection().prepareCall("UPDATE book SET name = ?, price = ? WHERE id = ?;");
			callableStatement.setString(1, name);
			callableStatement.setBigDecimal(2, new BigDecimal(price));
			callableStatement.setInt(3, id);
			return (!callableStatement.execute()) ? true : false;
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		return false;
	}
}
