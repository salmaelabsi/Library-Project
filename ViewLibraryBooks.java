package net.javaguides.swing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

public class ViewLibraryBooks extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private JButton btnBack;
    private JScrollPane scrollPane;
    private JTextField txtSearch;
    private JButton btnSearch;

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/swing_demo";
    static final String USER = "root";
    static final String PASS = "Aben5099";

    public ViewLibraryBooks() {
        initializeUI();
        populateTable();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUpdateBooks = new JLabel("View Books");
        lblUpdateBooks.setForeground(Color.BLACK);
        lblUpdateBooks.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblUpdateBooks.setBounds(363, 52, 333, 93);
        contentPane.add(lblUpdateBooks);

        // Create a panel for search functionality
        JPanel searchPanel = new JPanel();
        JLabel lblSearch = new JLabel("Search:");
        txtSearch = new JTextField(20);
        btnSearch = new JButton("Search");

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

        searchPanel.setBounds(70, 150, 350, 30);
        contentPane.add(searchPanel);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(70, 200, 850, 250);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnBack.setBounds(50, 470, 218, 73);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdminHome ah = new AdminHome();
                ah.setTitle("Admin Home");
                ah.setVisible(true);
            }
        });
        contentPane.add(btnBack);

        JLabel label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPane.add(label);
    }

    private void populateTable() {
        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String sql = "SELECT  name, book_title, book_id, time_borrowed, time_returned FROM fees_management";
            ResultSet rs = stmt.executeQuery(sql);

            DefaultTableModel model = new DefaultTableModel(new Object[][] {},
                    new String[] { "Name", "Book Title", "Book ID", "Time Borrowed", "Time Returned" });
            while (rs.next()) {
                String name = rs.getString("name");
                if (name.equalsIgnoreCase("library") || name.equalsIgnoreCase("LIBRARY")
                        || name.equalsIgnoreCase("Library")) {
                    name = "Library";
                } else {
                    name = "Booked";
                }
                model.addRow(new Object[] { name, rs.getString("book_title"), rs.getInt("book_id"),
                        rs.getTimestamp("time_borrowed"), rs.getTimestamp("time_returned") });
            }
            table.setModel(model);

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private void filterTable(String searchText) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String sql = "SELECT  name, book_title, book_id, time_borrowed, time_returned FROM fees_management WHERE name LIKE '%"
                    + searchText + "%' OR book_title LIKE '%" + searchText + "%'";
            ResultSet rs = stmt.executeQuery(sql);

            DefaultTableModel model = new DefaultTableModel(new Object[][] {},
                    new String[] { "Name", "Book Title", "Book ID", "Time Borrowed", "Time Returned" });
            while (rs.next()) {
                String name = rs.getString("name");
                if (name.equalsIgnoreCase("library") || name.equalsIgnoreCase("LIBRARY")
                        || name.equalsIgnoreCase("Library")) {
                    name = "Library";
                } else {
                    name = "Booked";
                }
                model.addRow(new Object[] { name, rs.getString("book_title"), rs.getInt("book_id"),
                        rs.getTimestamp("time_borrowed"), rs.getTimestamp("time_returned") });
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
                    ViewLibraryBooks frame = new ViewLibraryBooks();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
