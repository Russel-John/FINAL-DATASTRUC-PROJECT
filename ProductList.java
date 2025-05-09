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