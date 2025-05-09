public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            ProductList productList = new ProductList();
            new LoginForm(productList);
        });
    }
}
