package com.aditya.client.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.aditya.client.service.BookService;
import com.aditya.client.service.IssueBookService;
import com.aditya.client.service.StudentService;
import com.aditya.client.utils.CSVUtil;

import javax.swing.JButton;

public class Main {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("Populate Book");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Books().frame.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton.setBounds(142, 72, 139, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnEmployee = new JButton("Populate Student");
		btnEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Students().frame.setVisible(true);
				frame.dispose();
			}
		});
		btnEmployee.setBounds(142, 124, 148, 23);
		frame.getContentPane().add(btnEmployee);

		JButton btnNewButton_1_1 = new JButton("Issue Book");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new IssueBook().frame.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1_1.setBounds(142, 179, 148, 23);
		frame.getContentPane().add(btnNewButton_1_1);

		JButton btnNewButton_1_1_1 = new JButton("Export All Tables");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CSVUtil.createCSVForIssuedBooks(IssueBookService.getAllIssuedBooksList());
				CSVUtil.createCSVForBooks(BookService.getAllBooks());
				CSVUtil.createCSVForStudents(StudentService.getAllStudents());
			}
		});
		btnNewButton_1_1_1.setBounds(118, 234, 206, 23);
		frame.getContentPane().add(btnNewButton_1_1_1);

	}

}
