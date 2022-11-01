package Query;

import FileIO.FileIO;
import SaleClasses.Customer;
import SaleClasses.Product;
import SaleClasses.Sale;

public class SalesQuery {
    private Supplier[] suppliers;
    private SalesManagement salesManagement;
    private int numberOfCustomers;

    public void setup() {
        FileIO fileio = new FileIO();
        
        // Read data from files and
        // assign them to fileio's attributes.
        fileio.takeCustomers();
        fileio.takeProducts();
        fileio.takeSales();
        
        // Number of customers will be needed in method 'topPurchaser'.
        this.numberOfCustomers = fileio.getCustomers().length;
        Product[][] products = fileio.getProducts();
        
        this.suppliers = new Supplier[3];
        
        for (int i = 0; i < 3; i++) {
            this.suppliers[i] = new Supplier(products[i]);
        }

        this.salesManagement = new SalesManagement(fileio.getSales());
    }

    // 
    public String find() {
        String text = theMostProfitableProduct();
        text += "\n" + theMostExpensiveProduct();
        text += "\n" + topPurchaser();
        text += "\n" + totalProfit();
        text += "\n" + theLeastProfitProductOfS1();
        return text;
    }

    public String toString() {
        String text = "";

        for (Supplier s : this.suppliers) {
            text += s.toString() + "\n";
        }

        text += salesManagement.toString();
        return text;
    }

    public String theMostProfitableProduct() {
        Sale sale1 = this.salesManagement.getSale(0, 0);
        double biggestProfit = sale1.getSalesPrice() - sale1.getProduct().getPrice();

        for (int i = 0; i < this.salesManagement.numberOfSuppliers(); i++) {
            for (int j = 0; j < this.salesManagement.length(i); j++) {
                Sale sale2 = this.salesManagement.getSale(i, j);
                double profitOfSale2 = sale2.getSalesPrice() - sale2.getProduct().getPrice();

                if (biggestProfit < profitOfSale2) {
                    sale1 = sale2;
                    biggestProfit = profitOfSale2;
                }
            }
        }
        return sale1.getProduct().toString() + " =====> " + biggestProfit;
    }

    public String theMostExpensiveProduct() {
        Sale sale1 = this.salesManagement.getSale(0, 0);
        double biggestSalePrice = sale1.getSalesPrice();

        for (int i = 0; i < this.salesManagement.numberOfSuppliers(); i++) {
            for (int j = 0; j < this.salesManagement.length(i); j++) {
                Sale sale2 = this.salesManagement.getSale(i, j);
                double salesPrice2 = sale2.getSalesPrice();

                if (biggestSalePrice < salesPrice2) {
                    sale1 = sale2;
                    biggestSalePrice = salesPrice2;
                }
            }
        }
            
        return sale1.getProduct().toString() + " =====> " + biggestSalePrice;
    }

    public String topPurchaser() {
        int totalNumberOfCustomer = this.numberOfCustomers;

        Customer[] customers = new Customer[totalNumberOfCustomer];
        int[] numbersOfCustomersPurchasers = new int[totalNumberOfCustomer];
        
        customers[0] = this.salesManagement.getSale(0, 0).getCustomer();
        numbersOfCustomersPurchasers[0] = 1;
        int indexOfFirstEmpty = 1;

        for (int i = 0; i < this.salesManagement.numberOfSuppliers(); i++) {
            for (int j = 0; j < this.salesManagement.length(i); j++) {

                Customer newOne = this.salesManagement.getSale(i, j).getCustomer();
                int pos = findPositionOfElement(customers, newOne);

                if (pos == -1) {
                    customers[indexOfFirstEmpty] = newOne;
                    numbersOfCustomersPurchasers[indexOfFirstEmpty] = 1;
                    indexOfFirstEmpty++;
                }
                else {
                    numbersOfCustomersPurchasers[pos]++;
                }
            }
        }

        int indexOfMax = 0;
        for (int i = 0; i < totalNumberOfCustomer; i++) {
            if (numbersOfCustomersPurchasers[indexOfMax] < numbersOfCustomersPurchasers[i]) {
                indexOfMax = i;
            }
        }

        return customers[indexOfMax].toString() + " =====> " + numbersOfCustomersPurchasers[indexOfMax];
        
    }

    private int findPositionOfElement(Customer[] arr, Customer elem) {
        for (int i = 0; i < arr.length; i++) {
            if (elem.equals(arr[i])) {
                return i;
            }
        }
        return -1;
    }

        
    // Calculating the total profits of all sales.
    public String totalProfit() {
        int totalProfits = 0;
        for(int i = 0; i < this.salesManagement.numberOfSuppliers(); i++) {
            for(int j = 0; j < this.salesManagement.length(i); j++) {
                double profit = this.salesManagement.getSale(i, j).getSalesPrice() - this.salesManagement.getSale(i, j).getProduct().getPrice();
                totalProfits += (int)profit;
            }
        }
        return "Total profit: " + totalProfits + " TL.";
    }


    // Calculating the least-profit product of S1 sales
    public String theLeastProfitProductOfS1() {
        // first we assign the length of S1 sales to 'numberOfSales'
        int numberOfSales = this.salesManagement.length(0); 
        
        // This is a temporary array of length of S1 and sales of S1
        Double[] profits = new Double[numberOfSales];
        
        // Profits of each products are assigned the 'profits' array
        for(int i = 0; i < numberOfSales; i++) {
            Sale sale = this.salesManagement.getSale(0, i);
            profits[i] = sale.getSalesPrice() - sale.getProduct().getPrice();
        }
        
        // We assign this variable to find the product
        int indexOfLeastOne = 0;
        
        // This loop finds the least profits and 
        // gives us where the position in the array 'profits' is
        
        for(int i = 0; i < profits.length; i++) {
            if(profits[indexOfLeastOne] > profits[i]) {
                indexOfLeastOne = i;
            }
        }

        return this.salesManagement.getSale(0, indexOfLeastOne).getProduct().toString() + " =====> " + profits[indexOfLeastOne];
    }
}
