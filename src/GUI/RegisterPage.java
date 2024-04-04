package GUI;

import CLI.User;
import Products.FileHandling;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterPage extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    public RegisterPage() {

        //Setting up the JFrame properties
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 200);

        // Creating a panel with a grid layout for organizing components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        // Username components
        JLabel usernameLabel = new JLabel("Username:");
        panel.add(usernameLabel);
        usernameField = new JTextField();
        panel.add(usernameField);

        // Password components
        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel);
        passwordField = new JPasswordField();
        panel.add(passwordField);

        // Confirm Password components
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        panel.add(confirmPasswordLabel);
        confirmPasswordField = new JPasswordField();
        panel.add(confirmPasswordField);

        // Register Button
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                if (FileHandling.checkForUsers(username)){
                    if (password.equals(confirmPassword)) {
                        FileHandling.saveUserDataToFile(new User(username, password, true));

                    } else {
                        // Passwords don't match, show an error message
                        JOptionPane.showMessageDialog(RegisterPage.this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(RegisterPage.this, "Username taken", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(registerButton);

        //Adding the panel to the JFrane and making it visible
        this.add(panel);
        this.setVisible(true);

    }
}
