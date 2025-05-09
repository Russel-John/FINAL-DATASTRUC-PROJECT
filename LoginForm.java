import javax.swing.*;

public class LoginForm extends JFrame {
    private ProductList productList;

    public LoginForm(ProductList productList) {
        this.productList = productList;

        setTitle("Login");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(150, 100, 100, 30);
        add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(150, 130, 200, 30);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(150, 170, 100, 30);
        add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 200, 200, 30);
        add(passwordField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(150, 250, 100, 30);
        loginBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (username.equals("admin") && password.equals("admin")) {
                new MainMenu(productList);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid login credentials.");
            }
        });
        add(loginBtn);

        JButton signUpBtn = new JButton("Sign Up");
        signUpBtn.setBounds(270, 250, 100, 30);
        signUpBtn.addActionListener(e -> {
            new SignUpForm(productList);
            dispose();
        });
        add(signUpBtn);

        setVisible(true);
    }
}