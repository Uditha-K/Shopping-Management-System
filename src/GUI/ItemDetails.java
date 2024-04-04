package GUI;

import Products.Clothing;
import Products.Electronics;
import Products.Product;

import javax.swing.*;
import java.awt.*;

public class ItemDetails {
    private Container grid1;
    private JLabel detailsLabel;
    private JButton button;

    /**
     * Constructor for the ItemDetails class.
     *
     * @param grid1  The container for the grid layout.
     * @param button The button associated with the item details.
     */
    public ItemDetails(Container grid1, JButton button) {
        this.grid1 = grid1;
        this.detailsLabel = new JLabel("", SwingConstants.LEFT);
        this.button = button;

        setLabelProperties(detailsLabel, 20);
    }

    /**
     * Displays the details of the selected product on the GUI.
     *
     * @param product The selected product for which details are to be displayed.
     */
    public void selectedProducts(Product product) {
        grid1.removeAll();
        String labelText = "<html><b>Selected Product Details</b><br/>" +
                "Product ID: " + product.getProductID() + "<br/>" +
                "Category: " + product.getClass().toString() + "<br/>" +
                "Name: " + product.getProductName() + "<br/>";

        if (product instanceof Clothing) {
            labelText += "Size: " + ((Clothing) product).getSize() + "<br/>" +
                    "Color: " + ((Clothing) product).getColor() + "<br/>";
        } else if (product instanceof Electronics) {
            labelText += "Brand: " + ((Electronics) product).getBrand() + "<br/>" +
                    "Warranty: " + ((Electronics) product).getWarrantyPeriod() + "<br/>";
        }

        labelText += "Items Available: " + product.getNumberOfAvailableItems() + "</html>";

        detailsLabel.setText(labelText);
        grid1.add(detailsLabel);
        grid1.add(button);
    }

    /**
     * Sets properties for the label, such as font and border.
     *
     * @param label        The label for which properties are to be set.
     * @param leftPadding  The left padding for the label.
     */
    private void setLabelProperties(JLabel label, int leftPadding) {
        label.setFont(label.getFont().deriveFont(Font.PLAIN, 14.0f));
        label.setBorder(BorderFactory.createEmptyBorder(0, leftPadding, 0, 0));
    }
}

