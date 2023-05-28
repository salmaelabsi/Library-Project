package net.javaguides.swing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class UserHome extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel Welcome;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserHome frame = new UserHome();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public UserHome() {

    }

    public UserHome(String userSes) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 800);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(1, 1, 1, 1));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        Welcome = new JLabel("Welcome " + userSes + "!");
        Welcome.setFont(new Font("Tahoma", Font.BOLD, 40));
        Welcome.setBounds(450, 50, 400, 100);
        contentPane.add(Welcome);

        JButton btnNewButton = new JButton("Logout");
        btnNewButton.setForeground(new Color(0, 0, 0));
        btnNewButton.setBackground(UIManager.getColor("Button.disabledForeground"));
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(btnNewButton, "Are you sure?");
                // JOptionPane.setRootFrame(null);
                if (a == JOptionPane.YES_OPTION) {
                    dispose();
                    UserLogin obj = new UserLogin();
                    obj.setTitle("Student-Login");
                    obj.setVisible(true);
                }
                dispose();
                UserLogin obj = new UserLogin();

                obj.setTitle("Student-Login");
                obj.setVisible(true);

            }

        });
        btnNewButton.setBounds(400, 200, 400, 100);
        contentPane.add(btnNewButton);

        JButton button = new JButton("Change-password");
        button.setBackground(UIManager.getColor("Button.disabledForeground"));
        button.setFont(new Font("Tahoma", Font.PLAIN, 30));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ChangePassword bo = new ChangePassword(userSes);
                bo.setTitle("Change Password");
                bo.setVisible(true);

            }
        });
        button.setBounds(400, 350, 400, 100);
        contentPane.add(button);

        JButton btnViewBooks = new JButton("View Library Books");
        btnViewBooks.setBackground(UIManager.getColor("Button.disabledForeground"));
        btnViewBooks.setFont(new Font("Tahoma", Font.PLAIN, 30));
        contentPane.add(btnViewBooks);
        
        btnViewBooks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ViewLibraryBooks ViewLibraryBooks = new ViewLibraryBooks();
                ViewLibraryBooks.setTitle("Update Books");
                ViewLibraryBooks.setVisible(true);
            }
        });
        btnViewBooks.setBounds(400, 500, 400, 100);
        contentPane.add(btnViewBooks);

        
    }
}