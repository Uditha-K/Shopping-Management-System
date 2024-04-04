import CLI.Validations;
import GUI.Gui;
import GUI.UserLogIn;
import Products.Clothing;
import Products.Electronics;
import Products.FileHandling;
import Products.Product;


import java.util.*;

public class WestminsterShoppingManager implements ShoppingManager {

    private ArrayList<Product> products ;
    private Scanner input = new Scanner(System.in);

    public WestminsterShoppingManager() {
        this.products =FileHandling.getProductFromFiles();
    }

    /**
     * Displays the main menu and processes user choices.
     */
    public void mainMenu() {
        boolean run = true;
        while (run) {
            System.out.println("-----------------------");
            System.out.println("  1-GUI    ");
            System.out.println("  2-Admin ");
            System.out.println("999-To Exit the Program");
            System.out.println("-----------------------");

            System.out.println("Enter your choice: ");
            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    new UserLogIn(products);
                    break;

                case "2":
                    admin();
                    break;

                case "999":
                    System.out.println("Exiting the program.");
                    run = false;
                    break;

                default:
                    System.out.println("Choose a valid option.");

            }
        }
    }

    /**
     * Displays the admin menu and processes admin choices.
     */
    public void admin() {
        boolean run = true;
        while (run) {
            System.out.println("-----------------------");
            System.out.println("  1-To Add Products    ");
            System.out.println("  2-To Delete Products ");
            System.out.println("  3-To Print Products  ");
            System.out.println("  4-To Save to File    ");
            System.out.println("  5-To Load from File  ");
            System.out.println("999-To Exit the Program");
            System.out.println("-----------------------");

            System.out.print("Enter your choice: ");
            String choice = input.nextLine().strip();

            switch (choice) {
                case "1":
                    addProducts();
                    break;

                case "2":
                    deleteProduct();
                    break;

                case "3":
                    Collections.sort(this.products);
                    printProducts();
                    break;

                case "4":
                    saveFile();
                    break;

                case "5":
                    loadFile();
                    break;

                case "999":
                    System.out.println("Exiting the program.");
                    run = false;
                    break;

                default:
                    System.out.println("Choose a valid option.");

            }
        }
    }

    /**
     * Adds products to the list based on user input.
     */
    public void addProducts(){
        int productType = Validations.intValidation("Enter the product type (Enter 1 for Clothing, 2 for Electronics.)");

        if(products.size()<50){
            String productID = productIdGenerator(productType);
            System.out.println("Product ID: " + productID);
            String productName = Validations.stringValidation("Enter the product name: ");
            int numberOfAvailableItems = Validations.intValidation("Enter available number of items: ");
            double price = Validations.doubleValidation("Enter product's price: ");

            if (productType == 1) {
                int size = Validations.intValidation("Enter your size: ");
                String color = Validations.stringValidation("Enter the color: ");

                products.add(new Clothing(productID, productName, numberOfAvailableItems, price, size, color));
            } else if (productType == 2) {
                String brand = Validations.stringValidation("Enter the brand: ");
                String warrantyPeriod = Validations.stringValidation("Enter the warranty period: ");

                products.add(new Electronics(productID, productName, numberOfAvailableItems, price, brand, warrantyPeriod));
            }
        }
        else {
            System.out.println("Maximum products count has reached.");
        }
    }

    /**
     * Prints the list of products.
     */
    public void printProducts() {
        if (products.isEmpty()) {
            System.out.println("No products added yet.");
        } else {
            System.out.println("Products:");
            for (Product product : products) {
                System.out.println(product.toString());
            }
        }
    }

    /**
     * Saves the current list of products to a file.
     */
    @Override
    public void saveFile() {
        FileHandling.saveProductsToFile(this.products);
    }

    /**
     * Loads the list of products from a file.
     */
    @Override
    public void loadFile() {
        this.products = FileHandling.getProductFromFiles();
    }

    /**
     * Deletes a product from the list based on user input.
     */
    public void deleteProduct() {
        if (products.isEmpty()) {
            System.out.println("No products added yet.");
        } else {
            printProducts();

            String deletedProduct = Validations.stringValidation("Enter the product ID to delete: ");
            Product productToDelete = null;
            for (Product product : products) {
                if (product.getProductID().equals(deletedProduct)) {
                    productToDelete = product;
                    break;
                }
            }
            if (productToDelete != null) {
                products.remove(productToDelete);
                FileHandling.saveProductsToFile(this.products);
                System.out.println("Product ID " + deletedProduct + " deleted.");
            } else {
                System.out.println("Product with ID " + deletedProduct + " not found.");
            }

            System.out.println("Remaining Product count is: " + products.size());
        }
    }

    /**
     * Generates a unique product ID based on the product type.
     *
     * @param productType The type of the product (1 for Clothing, 2 for Electronics).
     * @return A unique product ID.
     */
    public String productIdGenerator(int productType){
        if(productType == 1){
            Random randomID = new Random();
            int randomNumber = randomID.nextInt(1000);
            return "CLO"+ randomNumber;
        }
        else if(productType == 2){
            Random randomID = new Random();
            int randomNumber = randomID.nextInt(1000);
            return "ELE" + randomNumber;
        }
        return null;
    }
}
