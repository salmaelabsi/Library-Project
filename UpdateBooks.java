package net.javaguides.swing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class UpdateBooks extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private JButton btnBack;
    private JButton btnADDABOOK;
    private JButton btnRentABook;
    private JButton btnPayment;
    private JScrollPane scrollPane;
    private boolean isAddBookOpen = false;
    private boolean isRentBookOpen = false;
    private boolean isPaymentOpen = false;

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/swing_demo";
    static final String USER = "root";
    static final String PASS = "Aben5099";

    public UpdateBooks(String userSes) {
        initializeUI(userSes, null);
        populateTable();
    }

    private void initializeUI(String userSes, UpdateBooks updateBooksFrame) {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                AdminHome ah = new AdminHome();
                ah.setTitle("Admin Home");
                ah.setVisible(true);
            }
        });

        setBounds(450, 190, 1014, 597);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        JLabel lblUpdateBooks = new JLabel("Update Books");
        lblUpdateBooks.setForeground(Color.BLACK);
        lblUpdateBooks.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblUpdateBooks.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblUpdateBooks, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4, 20, 10));

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdminHome ah = new AdminHome();
                ah.setTitle("Admin Home");
                ah.setVisible(true);
            }
        });
        buttonPanel.add(btnBack);

        btnADDABOOK = new JButton("Add a Book");
        btnADDABOOK.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnADDABOOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!isAddBookOpen) {
                    ADDBOOK addBookFrame = new ADDBOOK();
                    addBookFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            addBookFrame.setVisible(false);
                            isAddBookOpen = false;
                        }
                    });
                    addBookFrame.setVisible(true);
                    isAddBookOpen = true;
                }
            }
        });
        buttonPanel.add(btnADDABOOK);

        btnRentABook = new JButton("Rent a Book");
        btnRentABook.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnRentABook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!isRentBookOpen) {
                    RentBook rentBookFrame = new RentBook();
                    rentBookFrame.setTitle("Rent a Book");
                    rentBookFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            rentBookFrame.setVisible(false);
                            isRentBookOpen = false;
                        }
                    });
                    rentBookFrame.setVisible(true);
                    isRentBookOpen = true;
                }
            }
        });
        buttonPanel.add(btnRentABook);

        btnPayment = new JButton("Payment");
        btnPayment.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnPayment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!isPaymentOpen) {
                    Payment paymentFrame = new Payment();
                    paymentFrame.setTitle("Payment");
                    paymentFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            paymentFrame.setVisible(false);
                            isPaymentOpen = false;
                        }
                    });
                    paymentFrame.setVisible(true);
                    isPaymentOpen = true;
                }
            }
        });
        buttonPanel.add(btnPayment);

        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        scrollPane.setViewportView(table);
        
        // Create a panel for search functionality
        JPanel searchPanel = new JPanel();
        JLabel lblSearch = new JLabel("Search:");
        JTextField txtSearch = new JTextField(20);
        JButton btnSearch = new JButton("Search");
        
        // Add search button action listener
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchText = txtSearch.getText().trim();
                filterTable(searchText);
            }
        });
        
        // Add components to the search panel
        searchPanel.add(lblSearch);
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);
        
        contentPane.add(searchPanel, BorderLayout.NORTH);
    }
    
    private void filterTable(String searchText) {
        try {
            Class.forName(JDBC_DRIVER);

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String sql = "SELECT name, book_title, book_id, fees, time_borrowed, time_returned FROM fees_management " +
                    "WHERE book_id = '" + searchText + "' OR book_title LIKE '%" + searchText + "%'";
            ResultSet rs = stmt.executeQuery(sql);

            DefaultTableModel model = new DefaultTableModel(new Object[][] {},
                    new String[] { "Name", "Book Title", "Book ID", "Fees", "Time Borrowed", "Time Returned" });
            while (rs.next()) {
                model.addRow(new Object[] { rs.getString("name"), rs.getString("book_title"),
                        rs.getInt("book_id"), rs.getBigDecimal("fees"), rs.getTimestamp("time_borrowed"),
                        rs.getTimestamp("time_returned") });
            }
            table.setModel(model);

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private void populateTable() {
        try {
            Class.forName(JDBC_DRIVER);

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String sql = "SELECT name, book_title, book_id, fees, time_borrowed, time_returned FROM fees_management";
            ResultSet rs = stmt.executeQuery(sql);

            DefaultTableModel model = new DefaultTableModel(new Object[][] {},
                    new String[] { "Name", "Book Title", "Book ID", "Fees", "Time Borrowed", "Time Returned" });
            while (rs.next()) {
                model.addRow(new Object[] { rs.getString("name"), rs.getString("book_title"),
                        rs.getInt("book_id"), rs.getBigDecimal("fees"), rs.getTimestamp("time_borrowed"),
                        rs.getTimestamp("time_returned") });
            }
            table.setModel(model);

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UpdateBooks frame = new UpdateBooks("user123");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
