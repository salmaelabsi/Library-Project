
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
        btnNewButton.setBounds(0, 0, 600, 400);
        contentPane.add(btnNewButton);

        JButton button = new JButton("Change-password");
        button.setBackground(UIManager.getColor("Button.disabledForeground"));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ChangePassword bo = new ChangePassword(userSes);
                bo.setTitle("Change Password");
                bo.setVisible(true);

            }
        });
        button.setBounds(600, 0, 600, 400);
        contentPane.add(button);

        JButton btnViewBooks = new JButton("View Library Books");
        btnViewBooks.setBackground(UIManager.getColor("Button.disabledForeground"));
        btnViewBooks.setFont(new Font("Tahoma", Font.PLAIN, 30));
        btnViewBooks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Add code to display the list of books in the library
            }
        });
        btnViewBooks.setBounds(0, 400, 600, 400);
        contentPane.add(btnViewBooks);

        JButton btnBookABook = new JButton("Book a Book");
        btnBookABook.setBackground(UIManager.getColor("Button.disabledForeground"));
        btnBookABook.setFont(new Font("Tahoma", Font.PLAIN, 30));
        btnBookABook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Add code to allow the user to book a book
            }
        });
        btnBookABook.setBounds(600, 400, 600, 400);
        contentPane.add(btnBookABook);

    }
}
