package com.aditya.server.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aditya.model.Student;
import com.aditya.server.utils.DatabaseConnector;

public enum StudentDao {
	INSTANCE;

	private StudentDao() {
	}

	public boolean addStudent(String name) {
		CallableStatement callableStatement;
		try {
			callableStatement = DatabaseConnector.getConnection().prepareCall("INSERT INTO student VALUES(null,?);");
			callableStatement.setString(1, name);
			return (!callableStatement.execute()) ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Student> getAllStudents() {
		ArrayList<Student> arrayList = new ArrayList<>();
		try {
			PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement("SELECT * FROM student");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				arrayList.add(
						new Student(rs.getInt(1), rs.getString(2)));
			}
			return arrayList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Student> getStudentsByName(String name) {
		ArrayList<Student> arrayList = new ArrayList<>();
		try {
			PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement("SELECT * FROM student WHERE name = ?");
			statement.setString(1, name);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				arrayList.add(
						new Student(rs.getInt(1), rs.getString(2)));
			}
			return arrayList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateStudent(int id, String name) {
		CallableStatement callableStatement;
		try {
			callableStatement = DatabaseConnector.getConnection().prepareCall("UPDATE student SET name = ? WHERE id = ?;");
			callableStatement.setString(1, name);
			callableStatement.setInt(2, id);
			return (!callableStatement.execute()) ? true : false;
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		return false;
	}

	public boolean deleteStudent(int id) {
		CallableStatement callableStatement;
		try {
			callableStatement = DatabaseConnector.getConnection().prepareCall("DELETE FROM student WHERE id = ?;");
			callableStatement.setInt(1, id);
			return (!callableStatement.execute()) ? true : false;
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		return false;
	}
	
	public boolean deleteAllStudents() {
		try {
			Statement stmt = DatabaseConnector.getConnection().createStatement();
			stmt.executeUpdate("TRUNCATE TABLE student RESTART IDENTITY AND COMMIT");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
