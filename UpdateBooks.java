package net.javaguides.swing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


public class UpdateBooks extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private JButton btnBack;
    private JScrollPane scrollPane;
    private boolean isAddBookOpen = false;
    
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/swing_demo";
    static final String USER = "root";
    static final String PASS = "Aben5099";
    public UpdateBooks(String userSes) {
        initializeUI(paramString(), null );
        populateTable();
    }

    private void initializeUI(String userSes,  UpdateBooks updateBooksFrame) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUpdateBooks = new JLabel("Update Books");
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

        JButton btnADDABOOK = new JButton("ADD A BOOK");
        btnADDABOOK.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnADDABOOK.setBounds(700, 422, 218, 73);
        btnADDABOOK.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	    if (e.getSource() == btnADDABOOK) {
        	        if (!isAddBookOpen) {
        	            ADDABOOK addBookFrame = new ADDABOOK(userSes, updateBooksFrame);
        	            addBookFrame.addWindowListener(new WindowAdapter() {
        	                @Override
        	                public void windowClosing(WindowEvent e) {
        	                    addBookFrame.setVisible(false);
        	                    isAddBookOpen = false;
        	                }
        	            });
        	            isAddBookOpen = false;
        	        }
        	    }
        	}
        });
    
        contentPane.add(btnADDABOOK);

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
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String sql = "SELECT book_id, book_title, author, floor, CAST(shelf AS CHAR) AS shelf FROM books";
            ResultSet rs = stmt.executeQuery(sql);

            DefaultTableModel model = new DefaultTableModel(new Object[][] {},
                    new String[] { "Book ID", "Title", "Author", "Floor", "Shelf" });
            while (rs.next()) {
                model.addRow(new Object[] { rs.getInt("book_id"), rs.getString("book_title"), rs.getString("author"),
                        rs.getInt("floor"), rs.getString("shelf") });
            }
            table.setModel(model);

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}