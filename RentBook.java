package net.javaguides.swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RentBook extends JFrame {
    private JPanel contentPane;
    private JComboBox<String> bookTitleComboBox;
    private JTextField nameTextField;
    private JTextField feesTextField;
    private JButton rentButton;

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/swing_demo";
    static final String USER = "root";
    static final String PASS = "Aben5099";

    public RentBook() {
        initializeUI();
        populateBookTitles();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 600, 400);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblRentABook = new JLabel("Rent a Book");
        lblRentABook.setForeground(Color.BLACK);
        lblRentABook.setFont(new Font("Times New Roman", Font.PLAIN, 26));
        lblRentABook.setBounds(220, 20, 150, 30);
        contentPane.add(lblRentABook);

        JLabel lblBookTitle = new JLabel("Book Title:");
        lblBookTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblBookTitle.setBounds(40, 80, 120, 20);
        contentPane.add(lblBookTitle);

        bookTitleComboBox = new JComboBox<>();
        bookTitleComboBox.setBounds(160, 80, 300, 25);
        contentPane.add(bookTitleComboBox);

        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblName.setBounds(40, 120, 120, 20);
        contentPane.add(lblName);

        nameTextField = new JTextField();
        nameTextField.setBounds(160, 120, 300, 25);
        contentPane.add(nameTextField);
        nameTextField.setColumns(10);

        JLabel lblFees = new JLabel("Fees:");
        lblFees.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblFees.setBounds(40, 160, 120, 20);
        contentPane.add(lblFees);

        feesTextField = new JTextField();
        feesTextField.setBounds(160, 160, 300, 25);
        contentPane.add(feesTextField);
        feesTextField.setColumns(10);

       

        rentButton = new JButton("Rent");
        rentButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rentButton.setBounds(240, 220, 100, 30);
        rentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rentBook();
            }
        });
        contentPane.add(rentButton);

        JLabel label = new JLabel("");
        label.setBounds(0, 0, 594, 371);
        contentPane.add(label);
    }

    private void populateBookTitles() {
        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String sql = "SELECT book_id, book_title FROM books";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int bookId = rs.getInt("book_id");
                String bookTitle = rs.getString("book_title");
                bookTitleComboBox.addItem(bookId + " - " + bookTitle);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private void rentBook() {
        String selectedBook = (String) bookTitleComboBox.getSelectedItem();
        int bookId = Integer.parseInt(selectedBook.split(" - ")[0]);
        String name = nameTextField.getText();
        String feesString = feesTextField.getText();

        if (name.isEmpty() || feesString.isEmpty()) {
            JOptionPane.showMessageDialog(contentPane, "Please enter all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Validate user in the student table
            PreparedStatement validateUserStmt = conn.prepareStatement("SELECT name FROM student WHERE name = ?");
            validateUserStmt.setString(1, name);
            ResultSet validateUserResult = validateUserStmt.executeQuery();
            if (!validateUserResult.next()) {
                JOptionPane.showMessageDialog(contentPane, "User does not exist", "Error", JOptionPane.ERROR_MESSAGE);
                validateUserStmt.close();
                conn.close();
                return;
            }
            validateUserStmt.close();

            // Update name and fees in fees_management table
            PreparedStatement updateFeesStmt = conn.prepareStatement("UPDATE fees_management SET name = ?, fees = ? WHERE book_id = ?");
            updateFeesStmt.setString(1, name);
            updateFeesStmt.setString(2, feesString);
            updateFeesStmt.setInt(3, bookId);
            updateFeesStmt.executeUpdate();
            updateFeesStmt.close();

            // Set time borrowed as the current date
            LocalDateTime timeBorrowed = LocalDateTime.now();
            PreparedStatement updateTimeBorrowedStmt = conn.prepareStatement("UPDATE fees_management SET time_borrowed = ? WHERE book_id = ?");
            updateTimeBorrowedStmt.setTimestamp(1, Timestamp.valueOf(timeBorrowed));
            updateTimeBorrowedStmt.setInt(2, bookId);
            updateTimeBorrowedStmt.executeUpdate();
            updateTimeBorrowedStmt.close();

            // Calculate time returned as 3 days from the current date
            LocalDateTime timeReturned = timeBorrowed.plusDays(3);
            PreparedStatement updateTimeReturnedStmt = conn.prepareStatement("UPDATE fees_management SET time_returned = ? WHERE book_id = ?");
            updateTimeReturnedStmt.setTimestamp(1, Timestamp.valueOf(timeReturned));
            updateTimeReturnedStmt.setInt(2, bookId);
            updateTimeReturnedStmt.executeUpdate();
            updateTimeReturnedStmt.close();

            // Update status as "booked" in both books and fees_management tables
            PreparedStatement updateStatusStmt1 = conn.prepareStatement("UPDATE books SET book_status = 'booked' WHERE book_id = ?");

        updateStatusStmt1.setInt(1, bookId);
        updateStatusStmt1.executeUpdate();
        updateStatusStmt1.close();

        PreparedStatement updateStatusStmt2 = conn.prepareStatement("UPDATE fees_management SET book_status = 'booked' WHERE book_id = ?");
        updateStatusStmt2.setInt(1, bookId);
        updateStatusStmt2.executeUpdate();
        updateStatusStmt2.close();

        conn.close();

        JOptionPane.showMessageDialog(contentPane, "Book rented successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        clearFields();
    } catch (SQLException | ClassNotFoundException ex) {
        ex.printStackTrace();
    }
}

private void clearFields() {
    bookTitleComboBox.setSelectedIndex(0);
    nameTextField.setText("");
    feesTextField.setText("");
}

public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
        public void run() {
            try {
                RentBook frame = new RentBook();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });
}
}

