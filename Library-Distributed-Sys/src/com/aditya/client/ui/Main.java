package com.aditya.client.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.aditya.client.service.BookService;
import com.aditya.client.service.IssueBookService;
import com.aditya.client.service.StudentService;
import com.aditya.client.utils.CSVUtil;
import com.aditya.model.Book;
import com.aditya.model.Student;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

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
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Options");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Populate All Tables");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Book book : new ArrayList<Book>(){{
					add(new Book(0, "Harry Potter", "99"));
					add(new Book(0, "Sisters", "49"));
					add(new Book(0, "Flamingo", "65"));
					add(new Book(0, "Fumes", "99"));
					add(new Book(0, "Crook", "9"));
				}}) {
					BookService.addBook(book.getName(), String.valueOf(book.getPrice()));
				}
				for (Student student : new ArrayList<Student>(){{
					add(new Student(0, "Aditya"));
					add(new Student(0, "Yash"));
					add(new Student(0, "Rupal"));
					add(new Student(0, "Vivek"));
				}}) {
					StudentService.addStudent(student.getName());
				}
				for (ArrayList<Integer> ib : new ArrayList<ArrayList<Integer>>(){{
					add(new ArrayList<Integer>() {{ add(4); add(5);}});
					add(new ArrayList<Integer>() {{ add(1); add(1);}});
					add(new ArrayList<Integer>() {{ add(2); add(1);}});
					add(new ArrayList<Integer>() {{ add(3); add(1);}});
					add(new ArrayList<Integer>() {{ add(1); add(1);}});
					add(new ArrayList<Integer>() {{ add(1); add(1);}});
					add(new ArrayList<Integer>() {{ add(1); add(4);}});
					add(new ArrayList<Integer>() {{ add(1); add(5);}});
					add(new ArrayList<Integer>() {{ add(1); add(6);}});
					add(new ArrayList<Integer>() {{ add(1); add(1);}});
				}}) {
					IssueBookService.issueBook(ib.get(0), ib.get(1));
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

	}

}
