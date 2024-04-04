import Products.Product;

import java.util.ArrayList;

public class ShoppingCart {

    private ArrayList<Product> products = new ArrayList<>();

    public ShoppingCart(ArrayList<Product> products) {
        this.products = products;
    }

    public void productToAdd(Product product){
        products.add(product);
    }

    public void productToRemove(Product product){
        products.remove(product);
    }

    public double totalCost(){
        double totalCost = 0;
        for (int i=0; i<products.size(); i++){
             totalCost = totalCost+products.get(i).getPrice()*products.get(i).getNumberOfAvailableItems();
        }
        return totalCost;
    }

}
