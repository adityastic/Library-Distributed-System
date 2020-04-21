package com.aditya.server.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.aditya.model.IssuedBook;
import com.aditya.server.utils.DatabaseConnector;

public enum IssueBookDao {
	INSTANCE;

	private IssueBookDao() {
	}

	public boolean issueBook(int studentID, int bookID) {
		CallableStatement callableStatement;
		try {
			callableStatement = DatabaseConnector.getConnection().prepareCall("INSERT INTO issue_book VALUES(null,?,?);");
			callableStatement.setInt(1, studentID);
			callableStatement.setInt(2, bookID);
			return (!callableStatement.execute()) ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<IssuedBook> getAllIssuedBooks() {
		ArrayList<IssuedBook> arrayList = new ArrayList<>();
		try {
			PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement("SELECT issb.id, b.name, s.name FROM ISSUE_BOOK as issb JOIN BOOK as b ON b.id = issb.book_id JOIN STUDENT as s ON s.id = issb.student_id");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				arrayList.add(
						new IssuedBook(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			return arrayList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean deleteAllIssuedBooks() {
		try {
			Statement stmt = DatabaseConnector.getConnection().createStatement();
			stmt.executeUpdate("TRUNCATE TABLE issue_book RESTART IDENTITY AND COMMIT");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
