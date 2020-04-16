package com.aditya.client.utils;

import java.util.*;
import java.io.*;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.aditya.model.Book;
import com.aditya.model.IssuedBook;
import com.aditya.model.Student;

public class ParseHelper {

	public static ArrayList<Book> parseListOfBooks(String response){
		ArrayList<Book> bookList = new ArrayList<>();
		try {
			XmlPullParser pullParser = XmlPullParserFactory.newInstance().newPullParser();
			pullParser.setInput(new StringReader(response));

			Book book = new Book();
			for (int event = pullParser.getEventType(); event != XmlPullParser.END_DOCUMENT; event = pullParser.next()) {

				if (event == XmlPullParser.START_TAG && pullParser.getName().equals("book")) {
					book = new Book();
				}

				if (event == XmlPullParser.START_TAG && pullParser.getName().equals("id")) {
					event = pullParser.next();
					book.setId(Integer.parseInt(pullParser.getText()));
				}

				if (event == XmlPullParser.START_TAG && pullParser.getName().equals("name")) {
					event = pullParser.next();
					book.setName(pullParser.getText());
				}

				if (event == XmlPullParser.START_TAG && pullParser.getName().equals("price")) {
					event = pullParser.next();
					book.setPrice(Integer.parseInt(pullParser.getText()));
				}

				if (event == XmlPullParser.END_TAG && pullParser.getName().equals("book")) {
					bookList.add(book);
				}
			}
		} catch (XmlPullParserException | NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		return bookList;
	}

	public static ArrayList<Student> parseListOfStudents(String response) {
		ArrayList<Student> studentList = new ArrayList<>();
		try {
			XmlPullParser pullParser = XmlPullParserFactory.newInstance().newPullParser();
			pullParser.setInput(new StringReader(response));

			Student student = new Student();
			for (int event = pullParser.getEventType(); event != XmlPullParser.END_DOCUMENT; event = pullParser.next()) {

				if (event == XmlPullParser.START_TAG && pullParser.getName().equals("student")) {
					student = new Student();
				}

				if (event == XmlPullParser.START_TAG && pullParser.getName().equals("id")) {
					event = pullParser.next();
					student.setId(Integer.parseInt(pullParser.getText()));
				}

				if (event == XmlPullParser.START_TAG && pullParser.getName().equals("name")) {
					event = pullParser.next();
					student.setName(pullParser.getText());
				}

				if (event == XmlPullParser.END_TAG && pullParser.getName().equals("student")) {
					studentList.add(student);
				}
			}
		} catch (XmlPullParserException | NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		return studentList;
	}

	public static ArrayList<IssuedBook> parseListOfIssuedList(String response) {
		ArrayList<IssuedBook> issuedBooksList = new ArrayList<>();
		try {
			XmlPullParser pullParser = XmlPullParserFactory.newInstance().newPullParser();
			pullParser.setInput(new StringReader(response));

			IssuedBook issuedBook = new IssuedBook();
			for (int event = pullParser.getEventType(); event != XmlPullParser.END_DOCUMENT; event = pullParser.next()) {

				if (event == XmlPullParser.START_TAG && pullParser.getName().equals("book_issued")) {
					issuedBook = new IssuedBook();
				}

				if (event == XmlPullParser.START_TAG && pullParser.getName().equals("id")) {
					event = pullParser.next();
					issuedBook.setId(Integer.parseInt(pullParser.getText()));
				}

				if (event == XmlPullParser.START_TAG && pullParser.getName().equals("student")) {
					event = pullParser.next();
					issuedBook.setStudent(pullParser.getText());
				}

				if (event == XmlPullParser.START_TAG && pullParser.getName().equals("book")) {
					event = pullParser.next();
					issuedBook.setBook(pullParser.getText());
				}
				
				if (event == XmlPullParser.END_TAG && pullParser.getName().equals("book_issued")) {
					issuedBooksList.add(issuedBook);
				}
			}
		} catch (XmlPullParserException | NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		return issuedBooksList;
	}
}
