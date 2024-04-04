package GUI;

import Products.Clothing;
import Products.Electronics;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableModelShoppingCart extends AbstractTableModel {

    private String[] columnNames={"Product","Quantity","Price($)"};
    private ArrayList<ShoppingCartItem> products;

    /**
     * Constructor for the TableModelShoppingCart class.
     *
     * @param products The list of ShoppingCartItem objects to be displayed in the table.
     */
    public TableModelShoppingCart(ArrayList<ShoppingCartItem> products) {
        this.products = products;
    }

    /**
     * Gets the number of rows in the table.
     *
     * @return The number of rows.
     */
    @Override
    public int getRowCount() {
        return products.size();
    }

    /**
     * Gets the number of columns in the table.
     *
     * @return The number of columns.
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Gets the value at a specific row and column in the table.
     *
     * @param rowIndex    The index of the row.
     * @param columnIndex The index of the column.
     * @return The value at the specified row and column.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp =null;
        if(columnIndex == 0){
            if(products.get(rowIndex).getProduct() instanceof Clothing){
                Clothing clothing = (Clothing) products.get(rowIndex).getProduct();
                temp = "<html>" +
                        clothing.getProductID() + "<br>" +
                        clothing.getProductName() + "<br>" +
                        clothing.getSize() + ", " +
                        clothing.getColor() +
                        "</html>";

            }else {
                Electronics electronics = (Electronics) products.get(rowIndex).getProduct();
                temp = "<html>" +
                        electronics.getProductID() + "<br>" +
                        electronics.getProductName() + "<br>" +
                        electronics.getBrand() + ", " +
                        electronics.getWarrantyPeriod() +
                        "</html>";

            }

        } else if (columnIndex ==1) {
            temp =products.get(rowIndex).getQuantity();
        } else if (columnIndex == 2) {
            temp = products.get(rowIndex).getProduct().getPrice()*products.get(rowIndex).getQuantity() +"$";


        }
        return temp;
    }

    /**
     * Gets the column name at a specific column index.
     *
     * @param column The index of the column.
     * @return The name of the column.
     */
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
