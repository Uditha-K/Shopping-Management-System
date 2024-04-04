package Products;

public class Clothing extends Product {

    private int size;
    private String color;

    public Clothing(String productID, String productName, int numberOfAvailableItems, double price, int size, String color) {
        super(productID, productName, numberOfAvailableItems, price);
        this.size = size;
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }


}


