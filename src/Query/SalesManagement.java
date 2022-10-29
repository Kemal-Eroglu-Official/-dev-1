package Query;

import SaleClasses.Sale;

public class SalesManagement {
    private Sale[][] data;
    
    public SalesManagement() {
        this.data = new Sale[3][];
    }

    public SalesManagement(Sale[][] sales) {
        assert sales.length == 3;
        this.data = sales;
    }

    // public void addSales(int whichSupplier, Sale[] sales) {
    //     assert 0 <= whichSupplier && whichSupplier < 3;
    //     this.data[whichSupplier] = Arrays.copyOf(sales, sales.length);
    // }

    public Sale getSale(int suppleirIndex, int saleIndex) {
        assert 0 <= suppleirIndex && suppleirIndex < 3;
        assert 0 <= saleIndex && saleIndex < this.data[suppleirIndex].length;
        return new Sale(this.data[suppleirIndex][saleIndex]);
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
