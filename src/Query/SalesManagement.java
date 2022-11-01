package Query;

import SaleClasses.Sale;

public class SalesManagement {
    private Sale[][] data;
    
    public SalesManagement(Sale[][] sales) {
        Sale[][] data = new Sale[sales.length][];
        for (int i = 0; i < sales.length; i++) {
            Sale[] row = new Sale[sales[i].length];
            for (int j = 0; j < sales[i].length; j++) {
                row[j] = sales[i][j];
            }
            data[i] = row;
        }
        this.data = data;
    }

    public Sale getSale(int suppleirIndex, int saleIndex) {
        assert 0 <= suppleirIndex && suppleirIndex < this.data.length;
        assert 0 <= saleIndex && saleIndex < this.data[suppleirIndex].length;
        return new Sale(this.data[suppleirIndex][saleIndex]);
    }

    public int numberOfSuppliers() {
        return this.data.length;
    }

    // Number of sales in a specific supplier.
    public int length(int whichSupplier) {
        return this.data[whichSupplier].length;
    }

    public int getTotalNumberOfSales() {
        int counter = 0;
        for (int i = 0; i < this.data.length; i++) {
            counter += this.data[i].length;
        }
        return counter;
    }

    public String toString() {
        String text = "";
        for (int i = 0; i < this.data.length; i++) {
            for (int j = 0; j < this.data[i].length; j++) {
                text += this.data[i][j].toString() + "\n";
            }
        }
        return text;
    }
}
