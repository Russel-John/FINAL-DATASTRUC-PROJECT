import javax.swing.*;

public class SignUpForm extends JFrame {
    private ProductList productList;

    public SignUpForm(ProductList productList) {
        this.productList = productList;

        setTitle("Sign Up");
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

        JButton signUpBtn = new JButton("Sign Up");
        signUpBtn.setBounds(200, 250, 100, 30);
        signUpBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Account created successfully.");
            new LoginForm(productList);
            dispose();
        });
        add(signUpBtn);

        setVisible(true);
    }
}