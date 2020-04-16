package com.aditya.client.ui;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.aditya.client.service.BookService;
import com.aditya.model.Book;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Books {

	public JFrame frame;
	private static JTable table;
	private static DefaultTableModel model;
	private static ArrayList<Book> tabledata = new ArrayList<Book>();
	private static JLabel lblNewLabel;
	private JButton btnNewButton_1_2;
	private JButton btnNewButton;
	private int rowId;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel_1_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Books window = new Books();
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
	public Books() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 684, 457);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.setBounds(338, 290, 102, 29);
		frame.getContentPane().add(btnNewButton_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 55, 631, 215);
		frame.getContentPane().add(scrollPane);
		table = new JTable(
				new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Name", "Price" }));
		scrollPane.setViewportView(table);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(69, 397, 197, 14);
		frame.getContentPane().add(lblNewLabel);

		btnNewButton_1_2 = new JButton("Back");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Main().frame.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1_2.setBounds(448, 331, 102, 29);
		frame.getContentPane().add(btnNewButton_1_2);

		btnNewButton = new JButton("Update");

		btnNewButton.setBounds(448, 290, 102, 29);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Id");
		lblNewLabel_1.setBounds(69, 295, 61, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(142, 290, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(142, 323, 130, 26);
		frame.getContentPane().add(textField_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Name");
		lblNewLabel_1_1.setBounds(69, 328, 61, 16);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Books");
		lblNewLabel_2.setBounds(281, 23, 61, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton_1_1 = new JButton("Add");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_1.getText().equals("") || textField_2.getText().equals("")) {
					lblNewLabel.setText("Add all fields");
				} else {
					if (BookService.addBook(textField_1.getText(), textField_2.getText())) {
						lblNewLabel.setText("Book Inserted");
						ViewData();
					} else {
						lblNewLabel.setText("Error when adding book");
					}
				}
			}
		});
		btnNewButton_1_1.setBounds(338, 331, 102, 29);
		frame.getContentPane().add(btnNewButton_1_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(142, 356, 130, 26);
		frame.getContentPane().add(textField_2);
		
		lblNewLabel_1_2 = new JLabel("Price");
		lblNewLabel_1_2.setBounds(69, 361, 61, 16);
		frame.getContentPane().add(lblNewLabel_1_2);

		ViewData();

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() > -1) {
						lblNewLabel.setText((BookService.deleteBook(
								Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString())))
										? "Data Delete Successfully"
										: "Error with Deleting Data");
						ViewData();
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
					} else {
						lblNewLabel.setText("Please Select Row From Tabel");
					}
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				if (row >= 0) {
					rowId = Integer.parseInt(model.getValueAt(row, 0).toString());
					textField.setText(model.getValueAt(row, 0).toString());
					textField_1.setText(model.getValueAt(row, 1).toString());
					textField_2.setText(model.getValueAt(row, 2).toString());
				}
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textField.getText().equals("") || textField_1.getText().equals("")) {
					lblNewLabel.setText("Please Select Row From Tabel");
				} else {
					lblNewLabel.setText((BookService.updateBook(rowId, textField_1.getText(), textField_2.getText()))
									? "Update Successfully"
									: "Not Successfully");
					ViewData();
				}
			}
		});
	}

	public static void ViewData() {
		if (tabledata == null) {
			lblNewLabel.setText("No Product Found");
		} else {
			model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			tabledata = BookService.getAllBooks();
			try {
				for (Book productData : tabledata) {
					model.addRow(new Object[] { productData.getId(), productData.getName(), productData.getPrice() });
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
