package net.javaguides.swing;
import java.awt.event.WindowAdapter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.sql.Timestamp;
import java.sql.Types;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ADDBOOK extends JFrame {
    private JTextField idField;
    private JTextField nameField;
    private JTextField bookTitleField;
    private JTextField bookIdField;
    private JTextField feesField;
    private JButton addButton;
    private JButton deleteButton;
    private Connection conn;

    public ADDBOOK() {
    	
        initializeDatabase();

        setTitle("Add/Delete Books");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField(20);
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        JLabel bookTitleLabel = new JLabel("Book Title:");
        bookTitleField = new JTextField(20);
        JLabel bookIdLabel = new JLabel("Book ID:");
        bookIdField = new JTextField(20);
        JLabel feesLabel = new JLabel("Fees:");
        feesField = new JTextField(20);

        addButton = new JButton("Add Book");
        deleteButton = new JButton("Delete Book");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });

        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(bookTitleLabel);
        panel.add(bookTitleField);
        panel.add(bookIdLabel);
        panel.add(bookIdField);
        panel.add(feesLabel);
        panel.add(feesField);
        panel.add(addButton);
        panel.add(deleteButton);

        add(panel);

        setVisible(true);
    }

    private void initializeDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "Aben5099");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addBook() {
        String id = idField.getText();
        String name = nameField.getText();
        String bookTitle = bookTitleField.getText();
        String bookId = bookIdField.getText();
        double fees = 0.0;
        String timeBorrowed = getCurrentDateTime();
        String timeReturned = getCurrentDateTime();
        String bookStatus = "LIBRARY";
        

        try {
            // Add book to the first database
            PreparedStatement stmt1 = conn.prepareStatement("INSERT INTO books (book_id, book_title, author, floor, shelf, book_status) VALUES (?, ?, ?, ?, ?, ?)");
            stmt1.setString(1, bookId);
            stmt1.setString(2, bookTitle);
            stmt1.setString(3, "");
            stmt1.setInt(4, 0);
            stmt1.setString(5, "");
            stmt1.setString(6, bookStatus);
            stmt1.executeUpdate();

            // Add book to the second database
            PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO fees_management (id, name, book_title, book_id, fees, time_borrowed, time_returned, book_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            stmt2.setString(1, id);
            stmt2.setString(2, name);
            stmt2.setString(3, bookTitle);
            stmt2.setString(4, bookId);
            stmt2.setDouble(5, fees);
            stmt2.setString(6, timeBorrowed);
            stmt2.setString(7, timeReturned);
            stmt2.setString(8, bookStatus);
            stmt2.executeUpdate();

            JOptionPane.showMessageDialog(null, "Book added successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void deleteBook() {
        String bookId = bookIdField.getText();

        try {
            // Delete book from the first database
            PreparedStatement stmt1 = conn.prepareStatement("DELETE FROM books WHERE book_id = ?");
            stmt1.setString(1, bookId);
            stmt1.executeUpdate();

            // Delete book from the second database
            PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM fees_management WHERE book_id = ?");
            stmt2.setString(1, bookId);
            stmt2.executeUpdate();

            JOptionPane.showMessageDialog(null, "Book deleted successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String getCurrentDateTime() {
        java.util.Date date = new java.util.Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ADDBOOK();
            }
        });
    }
}
