package GUI;

import Products.Clothing;
import Products.Electronics;
import Products.Product;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableModelMain extends AbstractTableModel {

    private String[] columnNames={"Product ID","Name","Category","Price($)","Info"};
    private ArrayList<Product> products;

    /**
     * Constructor for the TableModelMain class.
     *
     * @param products The list of products to be displayed in the table.
     */
    public TableModelMain(ArrayList<Product> products) {
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
            temp = products.get(rowIndex).getProductID();
        } else if (columnIndex ==1) {
            temp =products.get(rowIndex).getProductName();
        } else if (columnIndex == 2) {
            if(products.get(rowIndex) instanceof Clothing)
                temp = "Clothing";
            else
                temp = "Electronics";

        } else if (columnIndex==3) {
            temp=products.get(rowIndex).getPrice();
        }else {
            if(products.get(rowIndex) instanceof Clothing){
                Clothing tempCloth=(Clothing)products.get(rowIndex);
                temp=tempCloth.getSize()+","+tempCloth.getColor();
            }
            else{
                Electronics tempCloth=(Electronics)products.get(rowIndex);
                temp=tempCloth.getBrand()+","+tempCloth.getWarrantyPeriod();
            }

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
