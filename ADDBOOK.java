package net.javaguides.swing;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ADDBOOK extends JFrame {
    private JTextField bookTitleField;
    private JTextField bookIdField;
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
        panel.setLayout(new GridLayout(4, 2));

        JLabel bookTitleLabel = new JLabel("Book Title:");
        bookTitleField = new JTextField(20);
        JLabel bookIdLabel = new JLabel("Book ID:");
        bookIdField = new JTextField(20);

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

        panel.add(bookTitleLabel);
        panel.add(bookTitleField);
        panel.add(bookIdLabel);
        panel.add(bookIdField);
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
        String bookTitle = bookTitleField.getText();
        int bookId = generateUniqueBookId();
        int fees = 0;
        String timeBorrowed = getCurrentDateTime();
        String timeReturned = getCurrentDateTime();
        String bookStatus = "LIBRARY";

        try {
            // Add book to the first database
            PreparedStatement stmt1 = conn.prepareStatement("INSERT INTO books (book_id, book_title, author, floor, shelf, book_status) VALUES (?, ?, ?, ?, ?, ?)");
            stmt1.setInt(1, bookId);
            stmt1.setString(2, bookTitle);
            stmt1.setString(3, "");
            stmt1.setInt(4, 0);
            stmt1.setString(5, "");
            stmt1.setString(6, bookStatus);
            stmt1.executeUpdate();

            // Add book to the second database
            PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO fees_management (id, name, book_title, book_id, fees, time_borrowed, time_returned, book_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            stmt2.setInt(1, bookId);
            stmt2.setString(2, "Library");
            stmt2.setString(3, bookTitle);
            stmt2.setInt(4, bookId);
            stmt2.setInt(5, fees);
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
        int bookId = Integer.parseInt(bookIdField.getText());

        try {
            // Delete book from the first database
            PreparedStatement stmt1 = conn.prepareStatement("DELETE FROM books WHERE book_id = ?");
            stmt1.setInt(1, bookId);
            stmt1.executeUpdate();

            // Delete book from the second database
            PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM fees_management WHERE book_id = ?");
            stmt2.setInt(1, bookId);
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

    private int generateUniqueBookId() {
        Random random = new Random();
        int bookId;
        boolean isUnique = false;

        do {
            bookId = random.nextInt(100000);

            try {
                PreparedStatement stmt = conn.prepareStatement("SELECT book_id FROM fees_management WHERE book_id = ?");
                stmt.setInt(1, bookId);
                ResultSet rs = stmt.executeQuery();
                if (!rs.next()) {
                    isUnique = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } while (!isUnique);

        return bookId;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ADDBOOK();
            }
        });
    }
}
