package com.aditya.client.ui;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.aditya.client.service.BookService;
import com.aditya.client.service.IssueBookService;
import com.aditya.client.service.StudentService;
import com.aditya.model.Book;
import com.aditya.model.IssuedBook;
import com.aditya.model.Student;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class IssueBook {

	public JFrame frame;
	private static JTable table;
	private static DefaultTableModel model;
	private static JLabel lblNewLabel;
	private JButton btnNewButton_1_2;
	
	private static JTable table_1;
	private static DefaultTableModel model_1;

	private static JTable table_2;
	private static DefaultTableModel model_2;

	
	private static ArrayList<Book> bookList = new ArrayList<Book>();
	private static ArrayList<Student> studentList = new ArrayList<Student>();
	private static ArrayList<IssuedBook> issuedBooks = new ArrayList<IssuedBook>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IssueBook window = new IssueBook();
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
	public IssueBook() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 879, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 57, 390, 215);
		frame.getContentPane().add(scrollPane);
		table = new JTable(
				new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Name"}));
		scrollPane.setViewportView(table);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(45, 373, 400, 14);
		frame.getContentPane().add(lblNewLabel);

		btnNewButton_1_2 = new JButton("Back");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Main().frame.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1_2.setBounds(272, 317, 102, 29);
		frame.getContentPane().add(btnNewButton_1_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(26, 433, 390, 215);
		frame.getContentPane().add(scrollPane_1);

		table_1 = new JTable(
				new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Name", "Price"}));
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblNewLabel_2 = new JLabel("All Students");
		lblNewLabel_2.setBounds(193, 29, 100, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("All Books");
		lblNewLabel_2_1.setBounds(200, 405, 61, 16);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		JButton btnNewButton_1_2_1 = new JButton("Issue");
		btnNewButton_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() > -1 && table_1.getSelectedRow() > -1) {
						
						lblNewLabel.setText(IssueBookService.issueBook(
								Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString()),
								Integer.parseInt(table_1.getModel().getValueAt(table_1.getSelectedRow(), 0).toString()))
										? "Issued Book Successfully"
										: "Error with Issuing Book");

						viewBooksData();
						viewStudent();
						viewIssuedBooks();
					} else {
						lblNewLabel.setText("Please Select Student and Book to Issue");
					}
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1_2_1.setBounds(58, 317, 102, 29);
		frame.getContentPane().add(btnNewButton_1_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("All Issued Books");
		lblNewLabel_2_1_1.setBounds(605, 29, 106, 16);
		frame.getContentPane().add(lblNewLabel_2_1_1);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(457, 57, 390, 591);
		frame.getContentPane().add(scrollPane_2);
		
		table_2 = new JTable(
				new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Student Name", "Book Issued"}));
		scrollPane_2.setViewportView(table_2);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Options");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Populate Table");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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

		viewBooksData();
		viewStudent();
		viewIssuedBooks();
	}

	public static void viewStudent() {
		model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		studentList = StudentService.getAllStudents();
		try {
			for (Student student : studentList) {
				model.addRow(new Object[] { student.getId(), student.getName() });
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	

	public static void viewBooksData() {
		model_1 = (DefaultTableModel) table_1.getModel();
		model_1.setRowCount(0);
		bookList = BookService.getAllBooks();
		try {
			for (Book book : bookList) {
				model_1.addRow(new Object[] { book.getId(), book.getName(), book.getPrice() });
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void viewIssuedBooks() {
		model_2 = (DefaultTableModel) table_2.getModel();
		model_2.setRowCount(0);
		issuedBooks = IssueBookService.getAllIssuedBooksList();
		try {
			for (IssuedBook issue : issuedBooks) {
				model_2.addRow(new Object[] { issue.getId(), issue.getStudent(), issue.getBook() });
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
