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
public class ViewLibraryBooks extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private JButton btnBack;
    private JScrollPane scrollPane;

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

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnBack.setBounds(50, 422, 218, 73);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdminHome ah = new AdminHome();
                ah.setTitle("Admin Home");
                ah.setVisible(true);
            }
        });
        contentPane.add(btnBack);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(70, 150, 850, 200);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        JLabel label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPane.add(label);
    }

    private void populateTable() {
        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String sql = "SELECT id, name, book_title, book_id, time_borrowed, time_returned FROM fees_management";
            ResultSet rs = stmt.executeQuery(sql);

            DefaultTableModel model = new DefaultTableModel(new Object[][] {},
                    new String[] { "ID", "Name", "Book Title", "Book ID", "Time Borrowed", "Time Returned" });
            while (rs.next()) {
                String name = rs.getString("name");
                if (name.equalsIgnoreCase("library")) {
                    name = "Library";
                } else {
                    name = "Booked";
                }
                model.addRow(new Object[] { rs.getInt("id"), name, rs.getString("book_title"),
                        rs.getInt("book_id"), rs.getTimestamp("time_borrowed"),
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
                    ViewLibraryBooks frame = new ViewLibraryBooks();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}