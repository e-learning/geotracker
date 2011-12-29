package ru.eltech.elerning.geotracker.ui;

import ru.eltech.elerning.geotracker.core.model.LoginResult;
import ru.eltech.elerning.geotracker.core.services.Geo2TagService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 12/22/11
 */
public class LoginWindow {
    private JButton signUpButton;
    private JTextField loginTextField;
    private JPanel mainPanel;
    private JPasswordField passwordField;
    private JLabel loginStatusBarLabel;
    private JButton logInButton;

    private static JFrame loginWindow;

    public LoginWindow() {
        loginTextField.setText("korgov");
        passwordField.setText("korgov");

        logInButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final String login = loginTextField.getText();
                final String pwd = String.valueOf(passwordField.getPassword());
                final LoginResult loginResult = Geo2TagService.loginAndWrapResult(login, pwd);
                switch (loginResult.getStatus()) {
                    case ERROR:
                        loginStatusBarLabel.setForeground(Color.RED);
                        loginStatusBarLabel.setText(loginResult.getMessage());
                        loginWindow.pack();
                        break;
                    case OK:
                        loginStatusBarLabel.setForeground(Color.BLACK);
                        loginStatusBarLabel.setText("");
                        loginWindow.pack();
                        final JFrame mainWindow = new JFrame("Geo Tracker. Hello, " + login);
                        mainWindow.setContentPane(new MainWindow(loginResult.getAuthToken(), login).getMainPanel());
                        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        mainWindow.pack();
                        setVisible(false);
                        setHideOnClose();
                        mainWindow.setVisible(true);
                }
            }
        });
        
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final String login = loginTextField.getText();
                final String pwd = String.valueOf(passwordField.getPassword());
                final LoginResult addResult = Geo2TagService.addUserAndWrapResult(login, pwd);
                switch (addResult.getStatus()) {
                    case ERROR:
                        loginStatusBarLabel.setForeground(Color.RED);
                        break;
                    case OK:
                        loginStatusBarLabel.setForeground(new Color(0, 100, 0)); //dark green
                }
                loginStatusBarLabel.setText(addResult.getMessage());
                loginWindow.pack();
            }
        });
    }

    private static void setHideOnClose() {
        loginWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private static void setVisible(final boolean value) {
        loginWindow.setVisible(value);
    }

    public static void main(final String[] args) {
        loginWindow = new JFrame("Geo Tracker. Please login..");
        loginWindow.setContentPane(new LoginWindow().mainPanel);
        loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loginWindow.pack();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int x = (screenSize.width - loginWindow.getWidth()) / 2;
        final int y = (screenSize.height - loginWindow.getHeight()) / 2;
        loginWindow.setBounds(x, y, loginWindow.getWidth(), loginWindow.getHeight());
        loginWindow.setVisible(true);
    }
}
