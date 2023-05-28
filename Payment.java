package net.javaguides.swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;
import java.util.concurrent.TimeUnit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Payment extends JFrame implements ActionListener {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/swing_demo";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Aben5099";

    private JTextField bookIdTextField;

    public Payment() {
        setTitle("Payment");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(null);

        JLabel bookIdLabel = new JLabel("Book ID");
        bookIdLabel.setBounds(30, 30, 100, 20);
        add(bookIdLabel);

        bookIdTextField = new JTextField();
        bookIdTextField.setBounds(140, 30, 200, 20);
        add(bookIdTextField);

        JButton payButton = new JButton("Pay");
        payButton.setBounds(140, 70, 100, 30);
        payButton.addActionListener(this);
        add(payButton);

        JButton undoButton = new JButton("Undo");
        undoButton.setBounds(250, 70, 100, 30);
        undoButton.addActionListener(this);
        add(undoButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Pay")) {
            addPaymentDetails();
        } else if (e.getActionCommand().equals("Undo")) {
            undoPaymentDetails();
        }
    }

    private void addPaymentDetails() {
        int bookId = Integer.parseInt(bookIdTextField.getText());

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            // Retrieve the existing payment details for the given book ID
         // Retrieve the existing payment details for the given book ID
            String selectQuery = "SELECT * FROM fees_management WHERE book_id = ?";
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String bookTitle = resultSet.getString("book_title");
                double fees = resultSet.getDouble("fees");
                String timeBorrowed = resultSet.getString("time_borrowed");
                String timeReturned = resultSet.getString("time_returned");

                // Calculate the fine if the book is returned late
                double fine = 0.0;
                LocalDateTime borrowedTime = LocalDateTime.parse(timeBorrowed, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                LocalDateTime returnedTime = LocalDateTime.parse(timeReturned, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                long hoursDifference = Duration.between(returnedTime, borrowedTime).toHours();
                if (hoursDifference > 24) {
                    double lateFee = fees * 0.4; // 40% fine
                    fine = lateFee;
                    fees += lateFee;
                }

                // Update the payment details
                String updateQuery = "UPDATE fees_management SET name = ?, fees = ?, time_returned = CURRENT_TIMESTAMP WHERE book_id = ?";
                preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setString(1, "library");
                preparedStatement.setDouble(2, fees);
                preparedStatement.setInt(3, bookId);
                preparedStatement.executeUpdate();

                JOptionPane.showMessageDialog(this, "Payment details updated successfully\n\n" +
                        "Book Title: " + bookTitle + "\n" +
                        "Fee Paid: $" + fees + "\n" +
                        "Fine: $" + fine);
            } else {
                JOptionPane.showMessageDialog(this, "Payment details not found for the given book ID");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating payment details");
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void undoPaymentDetails() {
        int bookId = Integer.parseInt(bookIdTextField.getText());

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            // Update fees and time to default values, and change name to "library"
            String updateQuery = "UPDATE fees_management SET name = ?, fees = 0, time_returned = CURRENT_TIMESTAMP WHERE book_id = ?";
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, "library");
            preparedStatement.setInt(2, bookId);
            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(this, "Payment details undone successfully");
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error undoing payment details");
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Payment();
    }
}