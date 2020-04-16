package com.aditya.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "book_issued")
@XmlType(propOrder = { "id", "student", "book" })
public class IssuedBook {
	private String book, student;
	int id;
	
	public IssuedBook(int id, String book, String student) {
		super();
		this.book = book;
		this.student = student;
		this.id = id;
	}
	
	public IssuedBook() {
		super();
	}

	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
