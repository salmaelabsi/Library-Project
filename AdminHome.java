package net.javaguides.swing;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminHome extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnLogout;
    private JButton btnChangePassword;
    private JButton btnUpdateBooks;
    private JButton btnAddUser;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminHome frame = new AdminHome();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public AdminHome(String userSes) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblAdminSection = new JLabel("Admin Section");
        lblAdminSection.setForeground(Color.BLACK);
        lblAdminSection.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblAdminSection.setBounds(378, 52, 333, 93);
        contentPane.add(lblAdminSection);

        btnLogout = new JButton("Logout");
        btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnLogout.setBounds(396, 422, 218, 73);
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                UserLogin ah = new UserLogin();
                ah.setTitle("Welcome");
                ah.setVisible(true);
                JOptionPane.showMessageDialog(btnLogout, "You have successfully logged out");
            }
        });
        contentPane.add(btnLogout);

        btnChangePassword = new JButton("Password");
        btnChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnChangePassword.setBounds(396, 161, 218, 73);
        btnChangePassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ChangePassword bo = new ChangePassword(userSes);
                bo.setTitle("Change Password");
                bo.setVisible(true);
            }
        });
        contentPane.add(btnChangePassword);

        btnUpdateBooks = new JButton("Update Books");
        btnUpdateBooks.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnUpdateBooks.setBounds(396, 292, 218, 73);
        btnUpdateBooks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UpdateBooks updateBooks = new UpdateBooks(userSes);
                updateBooks.setTitle("Update Books");
                updateBooks.setVisible(true);
            }
        });
        contentPane.add(btnUpdateBooks);

        btnAddUser = new JButton("Add User");
        btnAddUser.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnAddUser.setBounds(396, 223, 218, 73);
        btnAddUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adduser userAdd = new adduser(userSes);
                userAdd.setTitle("Add User");
                userAdd.setVisible(true);
            }
        });
        contentPane.add(btnAddUser);

    }

    public AdminHome() {
        // TODO Auto-generated constructor stub
    }
}