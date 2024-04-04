package GUI;

import Products.Product;

public class ShoppingCartItem {

    private int quantity;

    private Product product;

    public ShoppingCartItem(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }
    public double getTotal(){
        return this.product.getPrice()*this.quantity;
    }
}
