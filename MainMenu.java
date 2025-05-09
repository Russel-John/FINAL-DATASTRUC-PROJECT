import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class MainMenu extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private ProductList productList;

    public MainMenu(ProductList productList) {
        this.productList = productList;

        setTitle("Inventory Management System");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel title = new JLabel("Inventory Management System", SwingConstants.CENTER);
        title.setBounds(50, 10, 400, 30);
        add(title);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(400, 10, 80, 25);
        logoutBtn.addActionListener(e -> {
            new LoginForm(productList);
            dispose();
        });
        add(logoutBtn);

        JLabel productLabel = new JLabel("Products");
        productLabel.setBounds(200, 60, 100, 30);
        add(productLabel);

        tableModel = new DefaultTableModel(new Object[]{"Name", "Price (₱)", "Stock"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 100, 400, 250);
        add(scrollPane);

        JButton addBtn = new JButton("Add");
        addBtn.setBounds(100, 370, 80, 30);
        addBtn.addActionListener(e -> openForm("Add", -1));
        add(addBtn);

        JButton editBtn = new JButton("Edit");
        editBtn.setBounds(200, 370, 80, 30);
        editBtn.addActionListener(e -> openForm("Edit", table.getSelectedRow()));
        add(editBtn);

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(300, 370, 80, 30);
        deleteBtn.addActionListener(e -> {
            int index = table.getSelectedRow();
            if (index != -1) {
                productList.deleteProduct(index);
                refreshTable();
            }
        });
        add(deleteBtn);

        refreshTable();
        setVisible(true);
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Product p : productList.getAllProducts()) {
            tableModel.addRow(new Object[]{p.getName(), "₱" + p.getPrice(), p.getStock()});
        }
    }

    private void openForm(String action, int index) {
        JFrame form = new JFrame(action);
        form.setSize(280, 220);
        form.setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 80, 25);
        form.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(100, 20, 150, 25);
        form.add(nameField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(20, 60, 80, 25);
        form.add(priceLabel);

        JTextField priceField = new JTextField();
        priceField.setBounds(100, 60, 150, 25);
        priceField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '.' && c != '\b') e.consume();
            }
        });
        form.add(priceField);

        JLabel stockLabel = new JLabel("Stock:");
        stockLabel.setBounds(20, 100, 80, 25);
        form.add(stockLabel);

        JTextField stockField = new JTextField();
        stockField.setBounds(100, 100, 150, 25);
        stockField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) e.consume();
            }
        });
        form.add(stockField);

        JButton saveBtn = new JButton("Save");
        saveBtn.setBounds(90, 140, 100, 30);
        saveBtn.addActionListener(e -> {
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int stock = Integer.parseInt(stockField.getText());

            if (action.equals("Add")) {
                int id = productList.size() + 1;
                productList.addProduct(new Product(id, name, price, stock));
            } else if (index != -1) {
                Product old = productList.getProduct(index);
                productList.updateProduct(index, new Product(old.getId(), name, price, stock));
            }
            refreshTable();
            form.dispose();
        });
        form.add(saveBtn);

        if (action.equals("Edit") && index != -1) {
            Product p = productList.getProduct(index);
            nameField.setText(p.getName());
            priceField.setText(String.valueOf(p.getPrice()));
            stockField.setText(String.valueOf(p.getStock()));
        }

        form.setVisible(true);
    }
}