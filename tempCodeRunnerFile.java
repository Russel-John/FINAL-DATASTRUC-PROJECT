// --- Product.java ---
public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;

    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return id + " - " + name + " - ₱" + price + " - Stock: " + stock;
    }
}


// --- ProductList.java ---
import java.util.ArrayList;

public class ProductList {
    private ArrayList<Product> products;

    public ProductList() {
        products = new ArrayList<>();
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public void updateProduct(int index, Product updatedProduct) {
        products.set(index, updatedProduct);
    }

    public void deleteProduct(int index) {
        products.remove(index);
    }

    public Product getProduct(int index) {
        return products.get(index);
    }

    public ArrayList<Product> getAllProducts() {
        return products;
    }

    public int size() {
        return products.size();
    }
}


// --- MainMenu.java ---
import javax.swing.*;
import java.awt.event.*;

public class MainMenu extends JFrame {
    public MainMenu(ProductList productList) {
        setTitle("Inventory Management System");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JButton manageProductsBtn = new JButton("Manage Products");
        manageProductsBtn.setBounds(150, 30, 200, 40);
        manageProductsBtn.addActionListener(e -> new ProductForm(productList));
        add(manageProductsBtn);

        // Other buttons like Orders, Process, Undo, Search, Exit can be added here

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductList productList = new ProductList();
            new MainMenu(productList);
        });
    }
}


// --- ProductForm.java ---
import javax.swing.*;
import java.awt.event.*;

public class ProductForm extends JFrame {
    private ProductList productList;
    private DefaultListModel<String> listModel;
    private JList<String> productJList;

    public ProductForm(ProductList productList) {
        this.productList = productList;
        setTitle("Product Management");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        listModel = new DefaultListModel<>();
        productJList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(productJList);
        scrollPane.setBounds(10, 10, 460, 300);
        add(scrollPane);

        JButton addBtn = new JButton("Add");
        addBtn.setBounds(10, 320, 100, 30);
        addBtn.addActionListener(e -> addProduct());
        add(addBtn);

        JButton editBtn = new JButton("Edit");
        editBtn.setBounds(120, 320, 100, 30);
        editBtn.addActionListener(e -> editProduct());
        add(editBtn);

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(230, 320, 100, 30);
        deleteBtn.addActionListener(e -> deleteProduct());
        add(deleteBtn);

        refreshList();
        setVisible(true);
    }

    private void refreshList() {
        listModel.clear();
        for (Product p : productList.getAllProducts()) {
            listModel.addElement(p.toString());
        }
    }

    private void addProduct() {
        String name = JOptionPane.showInputDialog("Enter Product Name:");
        double price = Double.parseDouble(JOptionPane.showInputDialog("Enter Price:"));
        int stock = Integer.parseInt(JOptionPane.showInputDialog("Enter Stock:"));
        int id = productList.size() + 1;
        productList.addProduct(new Product(id, name, price, stock));
        refreshList();
    }

    private void editProduct() {
        int index = productJList.getSelectedIndex();
        if (index != -1) {
            Product p = productList.getProduct(index);
            String name = JOptionPane.showInputDialog("Enter New Name:", p.getName());
            double price = Double.parseDouble(JOptionPane.showInputDialog("Enter New Price:", p.getPrice()));
            int stock = Integer.parseInt(JOptionPane.showInputDialog("Enter New Stock:", p.getStock()));
            productList.updateProduct(index, new Product(p.getId(), name, price, stock));
            refreshList();
        }
    }

    private void deleteProduct() {
        int index = productJList.getSelectedIndex();
        if (index != -1) {
            productList.deleteProduct(index);
            refreshList();
        }
    }
}