package net.javaguides.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class adduser extends JFrame implements ActionListener {

    private JTextField idField, nameField, passwordField;
    private JButton addButton, removeButton, displayButton;
    private JTextArea resultArea;

    public adduser(String userSes) {
        super("Add User");

        idField = new JTextField(10);
        nameField = new JTextField(10);
        passwordField = new JTextField(10);

        addButton = new JButton("Add User");
        addButton.addActionListener(this);

        removeButton = new JButton("Remove User");
        removeButton.addActionListener(this);

        displayButton = new JButton("Display Users");
        displayButton.addActionListener(this);

        resultArea = new JTextArea(10, 20);
        resultArea.setEditable(false);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Password:"));
        inputPanel.add(passwordField);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(displayButton);

        JPanel outputPanel = new JPanel();
        outputPanel.add(new JScrollPane(resultArea));

        Container contentPane = getContentPane();
        contentPane.add(inputPanel, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        contentPane.add(outputPanel, BorderLayout.SOUTH);

        setSize(400, 300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "Aben5099");
            PreparedStatement statement;
            ResultSet resultSet;
            String query;

            if (e.getSource() == addButton) {
                query = "INSERT INTO student (id, name, password) VALUES (?, ?, ?)";
                statement = connection.prepareStatement(query);
                statement.setInt(1, Integer.parseInt(idField.getText()));
                statement.setString(2, nameField.getText());
                statement.setString(3, passwordField.getText());
                statement.executeUpdate();
                resultArea.setText("User added.");
            } else if (e.getSource() == removeButton) {
                query = "DELETE FROM student WHERE id=?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, Integer.parseInt(idField.getText()));
                statement.executeUpdate();
                resultArea.setText("User removed.");
            } else if (e.getSource() == displayButton) {
                query = "SELECT id, name, password FROM student";
                statement = connection.prepareStatement(query);
                resultSet = statement.executeQuery();
                StringBuilder resultText = new StringBuilder();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String password = resultSet.getString("password");
                    resultText.append(id).append("\t").append(name).append("\t").append(password).append("\n");
                }
                resultArea.setText(resultText.toString());
            }

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            resultArea.setText("Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        String userSes = null;
        new adduser(userSes);
    }
}