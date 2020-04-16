package com.aditya.client.utils;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.aditya.model.Book;
import com.aditya.model.IssuedBook;
import com.aditya.model.Student;

public class CSVUtil {

	public static void createCSVForIssuedBooks(ArrayList<IssuedBook> allIssuedBooksList) {
		try{
			FileWriter outputFile = new FileWriter("issued_books.csv");
			PrintWriter printWriter = new PrintWriter(outputFile);
			
			printWriter.print("Id,Student Name,Book Issued\n");
			for (IssuedBook issuedBook : allIssuedBooksList) {
				printWriter.print(issuedBook.getId() + "," + issuedBook.getStudent() + "," + issuedBook.getBook() + "\n");
				printWriter.flush();
			}
			printWriter.close();
		}
		catch(Exception e){e.printStackTrace();}
	}

	public static void createCSVForStudents(ArrayList<Student> allStudents) {
		try{
			FileWriter outputFile = new FileWriter("books.csv");
			PrintWriter printWriter = new PrintWriter(outputFile);
			
			printWriter.print("Id,Student Name\n");
			for (Student student : allStudents) {
				printWriter.print(student.getId() + "," + student.getName() + "\n");
				printWriter.flush();
			}
			printWriter.close();
		}
		catch(Exception e){e.printStackTrace();}
	}

	public static void createCSVForBooks(ArrayList<Book> allBooks) {
		try{
			FileWriter outputFile = new FileWriter("students.csv");
			PrintWriter printWriter = new PrintWriter(outputFile);
			
			printWriter.print("Id,Name,Price\n");
			for (Book book : allBooks) {
				printWriter.print(book.getId() + "," + book.getName() + "," + book.getPrice() + "\n");
				printWriter.flush();
			}
			printWriter.close();
		}
		catch(Exception e){e.printStackTrace();}
	}
}
