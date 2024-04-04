package GUI;

import CLI.User;
import Products.Product;
import Products.FileHandling;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart extends JFrame {
    private TableModelShoppingCart tableModel;
    private HashMap<Product, Integer> selectedProducts;
    private JTable jTable;
    private JScrollPane scrollPane;
    private ArrayList<ShoppingCartItem> shoppingCartItems;
    private JPanel grid1;
    private JLabel totalAndDiscountsLabel;

    private User user;

    private JButton buy=new JButton("Buy");
    private ArrayList<Product>products;

    /**
     * Constructor for the ShoppingCart class.
     *
     * @param title           The title of the shopping cart window.
     * @param selectedProducts The products selected for purchase.
     * @param user            The user making the purchase.
     * @param products        The list of available products.
     */
    public ShoppingCart(String title, HashMap<Product, Integer> selectedProducts, User user,ArrayList<Product>products) throws HeadlessException {
        super(title);
        this.selectedProducts = selectedProducts;
        this.shoppingCartItems = new ArrayList<>();
        this.grid1 = new JPanel(new GridLayout(3, 1));
        this.add(grid1);
        this.user=user;
        this.products=products;
        setupTable();
        displayTotals();

        buy.addActionListener(e->{
            FileHandling.saveProductsToFile(products);
            this.user.setFirstPurchase(false);
            FileHandling.saveUserDataToFile(this.user);
            JOptionPane.showMessageDialog(ShoppingCart.this, "Thank you");
            ShoppingCart.this.dispose();
        });
    }

    /**
     * Displays the total price and discounts on the GUI.
     */
    private void displayTotals() {
        double totalPrice = getTotal();
        double firstPurchaseDiscount = getfirstPurchaseDiscount(totalPrice,user);
        double threeInSameCategoryDiscount = getThreeInSameCategoryDiscount(totalPrice);
        double finalTotal = totalPrice - firstPurchaseDiscount - threeInSameCategoryDiscount;

        String labelText = "<html><table>" +
                "<tr><td><b>Total:</b></td><td align='right'>$" + String.format("%.2f", totalPrice) + "</td></tr>" +
                "<tr><td><b>First Purchase Discount (10%):</b></td><td align='right'>$" + String.format("%.2f", firstPurchaseDiscount) + "</td></tr>" +
                "<tr><td><b>Three In Same Category Discount (20%):</b></td><td align='right'>$" + String.format("%.2f", threeInSameCategoryDiscount) + "</td></tr>" +
                "<tr><td><b>Final Total:</b></td><td align='right'>$" + String.format("%.2f", finalTotal) + "</td></tr>" +
                "</table></html>";

        totalAndDiscountsLabel = new JLabel(labelText);
        grid1.add(totalAndDiscountsLabel);


        buy.setPreferredSize(new Dimension(100, 40)); // Set width to 100 pixels and height to 40 pixels

        grid1.add(buy);
    }

    /**
     * Calculates the first purchase discount based on the total price and user's purchase history.
     *
     * @param totalPrice The total price of the products in the shopping cart.
     * @param user       The user making the purchase.
     * @return The first purchase discount amount.
     */
    private double getfirstPurchaseDiscount(double totalPrice, User user) {
        if(user.isFirstPurchase()){
            return totalPrice*0.1;
        }
        return 0;
    }

    /**
     * Calculates the discount for purchasing three or more products in the same category.
     *
     * @param totalPrice The total price of the products in the shopping cart.
     * @return The discount amount.
     */
    private double getThreeInSameCategoryDiscount(double totalPrice) {
        for (ShoppingCartItem item : shoppingCartItems) {
            if (item.getQuantity() >= 3) {
                return totalPrice * 0.2;
            }
        }
        return 0;
    }

    /**
     * Sets up the table to display the selected products and quantities in the shopping cart.
     */
    private void setupTable() {
        processProducts();
        this.tableModel = new TableModelShoppingCart(this.shoppingCartItems);
        this.jTable = new JTable(tableModel);

        int maxWidth = 100;
        this.jTable.setRowHeight(maxWidth);
        this.jTable.getColumnModel().getColumn(0).setPreferredWidth(maxWidth);

        this.scrollPane = new JScrollPane(jTable);
        this.grid1.add(scrollPane);
    }

    /**
     * Processes the selected products and quantities to populate the shopping cart items list.
     */
    private void processProducts() {
        for (Map.Entry<Product, Integer> entry : selectedProducts.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            shoppingCartItems.add(new ShoppingCartItem(quantity, product));

        }
    }

    /**
     * Calculates the total price of the products in the shopping cart.
     *
     * @return The total price.
     */
    private double getTotal() {
        double total = 0;
        for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
            total += shoppingCartItem.getTotal();
        }
        return total;
    }
}



