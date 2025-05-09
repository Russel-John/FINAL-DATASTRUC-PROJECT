import javax.swing.*;
import java.awt.event.*;

public class ProductForm extends JFrame {
    private ProductList productList;
    private Product product;
    private String action;

    public ProductForm(String action, Product product, ProductList productList) {
        this.productList = productList;
        this.action = action;
        this.product = product;

        setTitle(action.equals("add") ? "Add Product" : "Edit Product");
        setSize(400, 250);  // Adjusted size to fit the form
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 30, 100, 30);
        add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(150, 30, 200, 30);
        add(nameField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(50, 80, 100, 30);
        add(priceLabel);

        JTextField priceField = new JTextField();
        priceField.setBounds(150, 80, 200, 30);
        add(priceField);

        JLabel stockLabel = new JLabel("Stock:");
        stockLabel.setBounds(50, 130, 100, 30);
        add(stockLabel);

        JTextField stockField = new JTextField();
        stockField.setBounds(150, 130, 200, 30);
        add(stockField);

        if (action.equals("edit")) {
            nameField.setText(product.getName());
            priceField.setText(String.valueOf(product.getPrice()));
            stockField.setText(String.valueOf(product.getStock()));
        }

        JButton submitBtn = new JButton(action.equals("add") ? "Add" : "Update");
        submitBtn.setBounds(150, 180, 100, 30);
        submitBtn.addActionListener(e -> {
            String name = nameField.getText();
            double price = 0;
            int stock = 0;
            try {
                price = Double.parseDouble(priceField.getText());
                stock = Integer.parseInt(stockField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Price and Stock must be numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (action.equals("add")) {
                int id = productList.size() + 1;
                productList.addProduct(new Product(id, name, price, stock));
            } else {
                productList.updateProduct(productList.getAllProducts().indexOf(product), new Product(product.getId(), name, price, stock));
            }
            dispose();
        });
        add(submitBtn);

        setVisible(true);
    }
}
