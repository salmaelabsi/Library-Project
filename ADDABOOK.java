package net.javaguides.swing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ADDABOOK extends JFrame implements ActionListener {

    // GUI elements
    private JTextField idField, titleField, authorField, floorField, shelfField;
    private JButton addButton, removeButton;
    private JLabel statusLabel;

    // Database connection details
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/swing_demo";
    static final String USER = "root";
    static final String PASS = "Aben5099";

    public ADDABOOK(String userSes,  UpdateBooks updateBooksFrame) {
        // Set up the GUI
        super("ADDABOOK");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

        panel.add(new JLabel("Book ID:"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("Title:"));
        titleField = new JTextField();
        panel.add(titleField);

        panel.add(new JLabel("Author:"));
        authorField = new JTextField();
        panel.add(authorField);

        panel.add(new JLabel("Floor:"));
        floorField = new JTextField();
        panel.add(floorField);

        panel.add(new JLabel("Shelf:"));
        shelfField = new JTextField();
        panel.add(shelfField);

        addButton = new JButton("Add Book");
        addButton.addActionListener(this);
        panel.add(addButton);

        removeButton = new JButton("Remove Book");
        removeButton.addActionListener(this);
        panel.add(removeButton);

        statusLabel = new JLabel("");
        panel.add(statusLabel);

        add(panel);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                updateBooksFrame.toFront();
            }
        });
    }

    // Handle button clicks
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addBook();
        } else if (e.getSource() == removeButton) {
            removeBook();
        }
    }

    // Add a new book to the database
    private void addBook() {
        try {
            // Connect to the database
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Prepare the SQL statement
            String sql = "INSERT INTO books (book_id, book_title, author, floor, shelf) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(idField.getText()));
            stmt.setString(2, titleField.getText());
            stmt.setString(3, authorField.getText());
            stmt.setInt(4, Integer.parseInt(floorField.getText()));
            stmt.setString(5, shelfField.getText());

            // Execute the statement
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                statusLabel.setText("Book added to database.");
            } else {
                statusLabel.setText("Error adding book.");
            }

            // Clean up
            stmt.close();
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            statusLabel.setText("Error adding book.");
        }
    }

    // Remove an existing book from the database
    private void removeBook() {
        try {
            // Connect to the database
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Prepare the SQL statement
            String sql = "DELETE FROM books WHERE book_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(idField.getText()));

            // Execute the statement
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted >0) { 


statusLabel.setText("Book removed from database.");
} else {
statusLabel.setText("Error removing book.");
}


     // Clean up
     stmt.close();
     conn.close();

 } catch (Exception ex) {
     ex.printStackTrace();
     statusLabel.setText("Error removing book.");
 }
}

public static void main(String[] args) {
// Get the user session from the command line arguments
String userSes = args[0];


 // Create the ADDABOOK window
 ADDABOOK window = new ADDABOOK(userSes, null);
}
}

// Note: This code assumes that the "books" table already exists in the "swing_demo" database, and that it has the following schema:
// CREATE TABLE books (
// book_id INT NOT NULL,
// book_title VARCHAR(255),
// author VARCHAR(255),
// floor INT,
// shelf VARCHAR(10),
// PRIMARY KEY (book_id)
// );