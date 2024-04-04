package GUI;

import CLI.User;
import Products.Clothing;
import Products.Electronics;
import Products.Product;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Gui extends JFrame {
    private JPanel mainPanel = new JPanel(new GridLayout(2, 1));
    private JPanel tablePanel = new JPanel(new GridLayout(2, 1));
    private JPanel filterPanel = new JPanel(new GridLayout(1, 3));
    private JPanel detailsPanel = new JPanel(new GridLayout(2, 1));
    private final JButton shoppingCartButton = new JButton("Shopping Cart");
    private String selectedProductId;
    private final String[] categories = {"ALL", "Clothing", "Electronics"};
    private JComboBox<String> categoryDropdown = new JComboBox<>(categories);
    private ArrayList<Product> products;
    private ArrayList<Product> filteredProducts;
    private HashMap<Product, Integer> cartItems;
    private JLabel selectCategoryLabel = new JLabel("Select Product Category");
    private TableModel tableModel;
    private JTable productTable;
    private JScrollPane scrollPane;
    private JButton addToCartButton = new JButton("Add To Cart");
    private User user;

    /**
     * Constructs the GUI for the shopping center.
     *
     * @param products The list of available products.
     * @param user     The current user of the application.
     */
    public Gui(ArrayList<Product> products, User user) {
        this.setTitle("Westminster shopping center");
        this.user = user;
        this.cartItems = new HashMap<>();
        this.products = products;
        Collections.sort(this.products);
        this.mainPanel.add(tablePanel);
        this.tablePanel.add(filterPanel);
        this.createTable();
        this.filterPanel.add(selectCategoryLabel);
        this.filterPanel.add(Box.createHorizontalStrut(10));
        this.filterPanel.add(categoryDropdown);
        this.filterPanel.add(Box.createHorizontalStrut(10));
        this.filterPanel.add(shoppingCartButton);

        this.categoryDropdown.setBackground(Color.WHITE);
        this.categoryDropdown.setForeground(Color.BLACK);
        this.categoryDropdown.setFont(new Font("Arial", Font.PLAIN, 14));

        this.shoppingCartButton.setBackground(Color.WHITE);
        this.shoppingCartButton.setForeground(Color.BLACK);
        this.shoppingCartButton.setFont(new Font("Arial", Font.BOLD, 14));

        this.filterPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.addToCartButton.addActionListener(e -> {
            System.out.println("Add to cart pressed");
            findAndAdd(selectedProductId);
        });

        this.categoryDropdown.addActionListener(e -> {
            String selectedCategory = (String) categoryDropdown.getSelectedItem();
            System.out.println(selectedCategory);
            this.filteredProducts = filterProducts(selectedCategory);
            createTable();
            this.revalidate();
            this.repaint();
        });

        this.shoppingCartButton.addActionListener(e -> {
            shoppingCart();
        });

        this.mainPanel.add(detailsPanel);
        this.add(mainPanel);

        this.setTitle("Westminster Shopping Center");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Filters products based on the selected category.
     *
     * @param type The selected category ("ALL", "Clothing", "Electronics").
     * @return The filtered list of products.
     */
    private ArrayList<Product> filterProducts(String type) {
        ArrayList<Product> filteredProducts = new ArrayList<>();

        for (Product product : this.products) {
            if ((type.equals("Clothing") && product instanceof Clothing) ||
                    (type.equals("Electronics") && product instanceof Electronics) ||
                    (type.equals("ALL"))) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    /**
     * Creates or updates the product table in the GUI.
     */
    private void createTable() {
        try {
            if (scrollPane != null) {
                this.tablePanel.remove(scrollPane);
            }

            if (filteredProducts != null && !filteredProducts.isEmpty()) {
                this.tableModel = new TableModelMain(filteredProducts);
            } else {
                this.tableModel = new TableModelMain(products);
            }

            this.productTable = new JTable(tableModel);

            // Set row height
            int rowHeight = 50; // Change this value according to your preference
            this.productTable.setRowHeight(rowHeight);

            // Set the preferred width for specific columns (change column index and width values)
            int columnWidth = 100; // Change this value according to your preference
            this.productTable.getColumnModel().getColumn(0).setPreferredWidth(columnWidth); // For the first column

            this.scrollPane = new JScrollPane(productTable);

            this.productTable.getSelectionModel().addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = productTable.getSelectedRow();
                    if (selectedRow != -1) {
                        selectedProductId = String.valueOf(productTable.getValueAt(selectedRow, 0).toString());
                        selectedProductDetails();
                        this.tablePanel.revalidate();
                        this.tablePanel.repaint();
                    }
                }
            });

            this.tablePanel.add(scrollPane);

            // Revalidate and repaint the panel to reflect changes

        } catch (NullPointerException x) {
            tableModel = new TableModelMain(products);
            productTable = new JTable(tableModel);
            scrollPane = new JScrollPane(productTable);

            productTable.getSelectionModel().addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = productTable.getSelectedRow();
                    if (selectedRow != -1) {
                        selectedProductId = String.valueOf(productTable.getValueAt(selectedRow, 0).toString());
                        selectedProductDetails();
                        this.revalidate();
                        this.repaint();
                    }
                }
            });
            this.tablePanel.add(scrollPane);
        }

    }

    /**
     * Displays details of the selected product in the details panel.
     */
    private void selectedProductDetails() {
        ItemDetails itemDetails = new ItemDetails(detailsPanel, addToCartButton);
        for (Product product : products) {
            if (product.getProductID().equals(selectedProductId)) {
                itemDetails.selectedProducts(product);
                break;
            }
        }
    }

    /**
     * Finds the selected product and adds it to the shopping cart.
     *
     * @param selectedProductId The ID of the selected product.
     */
    private void findAndAdd(String selectedProductId) {
        for (Product product : products) {
            try {
                if (product.getProductID().equals(selectedProductId)) {
                    product.buyAvailableItems();
                    System.out.println(product.getProductID() + " " + selectedProductId);
                    int quantity = cartItems.getOrDefault(product, 0);
                    cartItems.put(product, quantity + 1);
                    break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(Gui.this, "No items");
            }
        }

    }

    /**
     * Opens the shopping cart window to display the selected items.
     */
    private void shoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart("Shopping cart", cartItems, user, products);
        shoppingCart.setSize(400, 400);
        shoppingCart.setVisible(true);
    }
}
