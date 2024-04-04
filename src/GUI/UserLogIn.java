package GUI;

import CLI.User;
import Products.FileHandling;
import Products.Product;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

public class UserLogIn extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton; // Added register button

    public UserLogIn(  ArrayList<Product> products) {
        setTitle("User Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2)); // Changed layout to accommodate the register button

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        registerButton = new JButton("Register"); // Initializing register button

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Empty label for alignment
        panel.add(loginButton);
        panel.add(new JLabel()); // Empty label for alignment
        panel.add(registerButton); // Adding the register button to the panel

        //ActionListener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().strip();
                String password = new String(passwordField.getPassword());

                User user = null;
                user = FileHandling.readUserDataFromFile(username);
                System.out.println(user);
                if (user.getPassword().equals(password)) {
                    System.out.println("log");
                    new Gui(products, user);
                } else {
                    JOptionPane.showMessageDialog(UserLogIn.this, "Invalid username or password. Try again.");
                }


            }
        });

        //ActionListener for the register button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterPage();
            }
        });

        add(panel);
        setVisible(true);
    }

}
