package net.javaguides.swing;

import java.sql.Timestamp;
import java.sql.Types;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Calendar;
import java.util.GregorianCalendar;
public class UserLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JLabel label;
    private JPanel contentPane;
 // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/student";

    // Database credentials
    static final String USER = "root";
    static final String PASS = "Aben5099";

    /**
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserLogin frame = new UserLogin();
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
    public UserLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblNewLabel.setBounds(423, 13, 273, 93);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        textField.setBounds(481, 170, 281, 68);
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(481, 286, 281, 68);
        contentPane.add(passwordField);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblUsername.setBounds(250, 166, 193, 52);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblPassword.setBounds(250, 286, 193, 52);
        contentPane.add(lblPassword);

        btnNewButton = new JButton("Login");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton.setBounds(545, 392, 162, 73);
        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String userName = textField.getText();
                String password = passwordField.getText();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "Aben5099");
                    PreparedStatement st = connection.prepareStatement("SELECT name, password FROM student WHERE name=? AND password=?");
                    st.setString(1, userName);
                    st.setString(2, password);
                    ResultSet rs = st.executeQuery();
                    if (userName.equals("library") && password.equals("abido555")||userName.equals("library") && password.equals("abido789")) {
                        dispose();
                        AdminHome ah = new AdminHome(userName);
                        ah.setTitle("Welcome Admin");
                        ah.setVisible(true);
                        JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in as an Admin");
                    } else if (rs.next()) {
                        dispose();
                        UserHome uh = new UserHome(userName);
                        uh.setTitle("Welcome");
                        uh.setVisible(true);
                        JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password");
                    }
                } catch (SQLException | ClassNotFoundException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });
        contentPane.add(btnNewButton);

        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPane.add(label);
    }
}
