package Products;

public abstract class Product implements Comparable<Product> {

    private String productID;
    private String productName;
    private int numberOfAvailableItems;
    private double price;

    public Product(String productID, String productName, int numberOfAvailableItems, double price) {
        this.productID = productID;
        this.productName = productName;
        this.numberOfAvailableItems = numberOfAvailableItems;
        this.price = price;
    }

    //Returning the product ID
    public String getProductID() {
        return productID;
    }

    //Returning the product name
    public String getProductName() {
        return productName;
    }

    //Returning the available items
    public int getNumberOfAvailableItems() {
        return numberOfAvailableItems;
    }

    //Returning the price
    public double getPrice() {
        return price;
    }

    public void buyAvailableItems() {
        if (numberOfAvailableItems > 0) {
            this.numberOfAvailableItems -= 1;
        } else {
            throw new RuntimeException("No  items to buy");
        }
    }

    /**
     * Compare this product to another product for sorting purposes.
     * Compares based on product name in a case-insensitive manner.
     *
     * @param o The other product to compare to.
     * @return A negative integer, zero, or a positive integer as this product is less than, equal to, or greater than the specified product.
     */
    @Override
    public int compareTo(Product o) {
        return this.productName.toLowerCase().compareTo(o.getProductName().toLowerCase());
    }

    /**
     * Returns a string representation of the product.
     *
     * @return A string representation containing product ID, product name, number of available items, and price.
     */
    @Override
    public String toString() {
        return "Product ID is: " + productID + ", productName='" + productName + '\'' + ", numberOfAvailableItems=" + numberOfAvailableItems + ", price=" + price + '}';
    }
}
