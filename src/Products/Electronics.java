package Products;

public class Electronics extends Product{

    private String brand;
    private String warrantyPeriod;

    public Electronics(String productID, String productName, int numberOfAvailableItems, double price, String brand, String warrantyPeriod) {
        super(productID, productName, numberOfAvailableItems, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getBrand() {
        return brand;
    }

    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }


}
