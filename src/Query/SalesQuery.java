package Query;

import FileIO.FileIO;
import SaleClasses.Product;

public class SalesQuery {
    public Supplier[] suppliers;
    public SalesManagement salesManagement;

    public SalesQuery() {}

    public void setup() {
        FileIO fileio = new FileIO();
        fileio.takeCustomers();
        fileio.takeProducts();
        fileio.takeSales();
        
        this.suppliers = new Supplier[3];
        Product[][] products = fileio.getProducts();
        for (int i = 0; i < 3; i++) {
            this.suppliers[i] = new Supplier(products[i]);
        }

        this.salesManagement = new SalesManagement(fileio.getSales());
    }

    public String toString() {
        String text = "";

        for (Supplier s : this.suppliers) {
            text += s.toString() + "\n";
        }

        text += salesManagement.toString();
        return text;
    }
}
