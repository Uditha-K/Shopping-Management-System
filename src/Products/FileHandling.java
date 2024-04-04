package Products;

import CLI.User;
import Products.Clothing;
import Products.Electronics;
import Products.Product;
import java.io.*;
import java.util.ArrayList;

public class FileHandling {

    private static final String directoryPathUsers = "Users";

    /**
     * Saves user data to a file.
     *
     * @param user The user object containing username, password, and first purchase status.
     */
    public static void saveUserDataToFile(User user) {
        String filename = user.getUsername() + ".txt";

        File directory = new File(directoryPathUsers);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(directoryPathUsers + "/" + filename))) {
            if (user.getUsername() != null && user.getPassword() != null) {
                writer.write( user.getUsername() + " | " + user.getPassword() + " | " + user.isFirstPurchase() + "\n");
                System.out.println("CLI.User data saved to file: " + filename);
            } else {
                System.err.println("Username or password is null");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Checks if a user with a given username already exists.
     *
     * @param filename The username to check.
     * @return True if the user does not exist, false otherwise.
     */
    public static boolean checkForUsers(String filename) {
        File directory = new File(directoryPathUsers);
        File file = new File(directory + "/" + filename + ".txt");
        return !file.exists() || !file.isFile();
    }

    /**
     * Reads user data from a file.
     *
     * @param username The username of the user to retrieve.
     * @return The User object if found, or null if not found or an error occurs.
     */
    public static User readUserDataFromFile(String username) {
        String filename = username + ".txt";

        File file = new File(directoryPathUsers + "/" + filename);
        if (!file.exists()) {
            System.err.println("User file not found: " + filename);
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line != null) {
                String[] userData = line.split(" \\| ");
                String storedUsername = userData[0].trim();
                String storedPassword = userData[1].trim();
                boolean isFirstPurchase = Boolean.parseBoolean(userData[2]);

                User user = new User(storedUsername, storedPassword, isFirstPurchase);
                System.out.println(user);
                return user;
            } else {
                System.err.println("Empty file for user: " + filename);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Error creating user object: " + e.getMessage());
        }
        return null;
    }

    /**
     * Saves products to separate files based on their category (Clothing, Electronics).
     *
     * @param products The list of products to be saved.
     */
    public static void saveProductsToFile(ArrayList<Product> products) {
        String directoryPathForCloths = "Products/Clothing";
        String directoryPathForElectronics = "Products/Electronics";
        String filename = "ProductsList.txt";

        File directoryC = new File(directoryPathForCloths);
        File directoryE = new File(directoryPathForElectronics);

        // Checking if directories exist, and creating them if they don't
        if (!directoryC.exists()) {
            directoryC.mkdirs(); // Creating the directory and any missing parent directories
        }
        if (!directoryE.exists()) {
            directoryE.mkdirs();
        }

        try {
            BufferedWriter writerC = new BufferedWriter(new FileWriter(directoryPathForCloths + "/" + filename));
            BufferedWriter writerE = new BufferedWriter(new FileWriter(directoryPathForElectronics + "/" + filename));

            for (Product product : products) {
                if(product.getNumberOfAvailableItems() >0){
                    if (product instanceof Clothing clothing) {
                        writerC.write(clothing.getProductID() + "|" + clothing.getProductName() + "|" + clothing.getSize() + "|" + clothing.getColor() + "|" + clothing.getNumberOfAvailableItems() + "|" + clothing.getPrice() );
                        writerC.newLine();
                    } else if (product instanceof Electronics electronic) {
                        writerE.write(electronic.getProductID() + "|" + electronic.getProductName() + "|" + electronic.getBrand() + "|" + electronic.getWarrantyPeriod() + "|" + electronic.getNumberOfAvailableItems() + "|" + electronic.getPrice() );
                        writerE.newLine();
                    }
                }

            }

            // Close the writers after writing data
            writerC.close();
            writerE.close();

            System.out.println("CLI.User data saved to file: " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

    }

    /**
     * Reads products from separate files based on their category (Clothing, Electronics).
     *
     * @return The list of products read from files.
     */
    public static ArrayList<Product> getProductFromFiles() {
        ArrayList<Product> products = new ArrayList<>();
        String directoryPathForCloths = "Products/Clothing";
        String directoryPathForElectronics = "Products/Electronics";
        String filename = "ProductsList.txt";

        try {
            // Reading Products.Clothing file
            BufferedReader readerC = new BufferedReader(new FileReader(directoryPathForCloths + "/" + filename));
            String line;
            while ((line = readerC.readLine()) != null) {

                String[] data = line.split("\\|");


                String productID = data[0];
                String productName = data[1];
                int size = Integer.parseInt(data[2]);
                String color = data[3];
                int availableItems = Integer.parseInt(data[4]);
                double price = Double.parseDouble(data[5]);


                products.add(new Clothing(productID, productName, availableItems, price,  size, color));
            }
            readerC.close();

            // Reading Products.Electronics file
            BufferedReader readerE = new BufferedReader(new FileReader(directoryPathForElectronics + "/" + filename));
            while ((line = readerE.readLine()) != null) {
                // Splitting the line by "|" to extract individual data
                String[] data = line.split("\\|");
                // Assuming the structure: ID|Name|Brand|WarrantyPeriod|AvailableItems|Price|Info
                String productID = data[0];
                String productName = data[1];
                String brand = data[2];
                String warrantyPeriod = data[3];
                int availableItems = Integer.parseInt(data[4]);
                double price = Double.parseDouble(data[5]);


                products.add(new Electronics(productID, productName, availableItems, price, brand, warrantyPeriod));
            }
            readerE.close();
            return products;

        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return products;
    }


}
