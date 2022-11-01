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
        
        // Read data from files and,
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

    // The method that finds wanted objects.
    // It returns all the information as a string at once.
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
        // sale1 <= the first sale.
        Sale sale1 = this.salesManagement.getSale(0, 0);
        double biggestProfit = sale1.getSalesPrice() - sale1.getProduct().getPrice();

        // A 'for loop' that scans every sale.
        for (int i = 0; i < this.salesManagement.numberOfSuppliers(); i++) {
            for (int j = 0; j < this.salesManagement.length(i); j++) {
                
                // Take a new sale and,
                // calculate its profit.
                Sale sale2 = this.salesManagement.getSale(i, j);
                double profitOfSale2 = sale2.getSalesPrice() - sale2.getProduct().getPrice();

                if (biggestProfit < profitOfSale2) {
                    sale1 = sale2;
                    biggestProfit = profitOfSale2;
                }
            }
        }
        return sale1.getProduct().toString() + " =====> " + 
            "The biggest profit: " + biggestProfit + " TL.";
    }

    public String theMostExpensiveProduct() {
        Sale sale1 = this.salesManagement.getSale(0, 0);
        double biggestSalePrice = sale1.getSalesPrice();

        // A 'for loop' that scans every sale.
        for (int i = 0; i < this.salesManagement.numberOfSuppliers(); i++) {
            for (int j = 0; j < this.salesManagement.length(i); j++) {
                
                // Take a new sale and,
                // calculate its profit.
                Sale sale2 = this.salesManagement.getSale(i, j);
                double salesPrice2 = sale2.getSalesPrice();

                if (biggestSalePrice < salesPrice2) {
                    sale1 = sale2;
                    biggestSalePrice = salesPrice2;
                }
            }
        }
            
        return sale1.getProduct().toString() + " =====> " + 
            "The most expensive product: " + biggestSalePrice + " TL.";
    }

    public String topPurchaser() {
        
        // Create two arrays.
        // The first one will store customers.
        // The second one will keep track of how many purchases each customer has made.
        Customer[] customers = new Customer[this.numberOfCustomers];
        int[] numbersOfCustomersPurchasers = new int[this.numberOfCustomers];
        
        // Store the first customer.
        customers[0] = this.salesManagement.getSale(0, 0).getCustomer();
        numbersOfCustomersPurchasers[0] = 1;
        
        // This variable will be used when it is necessary to add a new customer.
        int indexOfFirstEmpty = 1;

        // A 'for loop' that scans every sale.
        for (int i = 0; i < this.salesManagement.numberOfSuppliers(); i++) {
            for (int j = 0; j < this.salesManagement.length(i); j++) {

                // Take a new customer.
                Customer newOne = this.salesManagement.getSale(i, j).getCustomer();
                
                // Find the position of this new customer in 'customers' array.
                // If pos is -1, that means 'newOne' is not in 'customers' array yet. 
                int pos = findPositionOfElement(customers, newOne);

                if (pos == -1) {
                    // Add 'newOne' to 'customers'.
                    customers[indexOfFirstEmpty] = newOne;
                    numbersOfCustomersPurchasers[indexOfFirstEmpty] = 1;
                    indexOfFirstEmpty++;
                }
                else {
                    // If 'newOne' is in 'customers' already.
                    // Increase customer 'newOne's purchase count by one.
                    numbersOfCustomersPurchasers[pos]++;
                }
            }
        }

        // Find the index of top purchaser.
        int indexOfMax = 0;
        for (int i = 0; i < this.numberOfCustomers; i++) {
            if (numbersOfCustomersPurchasers[indexOfMax] < numbersOfCustomersPurchasers[i]) {
                indexOfMax = i;
            }
        }

        return customers[indexOfMax].toString() + " =====> " + 
            "Top purchaser with " + numbersOfCustomersPurchasers[indexOfMax] + " puchases.";
    }

    // A method that finds position of a given element in a given array.
    // If return value is -1, it means that the element is not in the array.
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
                
                double salePrice = this.salesManagement.getSale(i, j).getSalesPrice();
                double price = this.salesManagement.getSale(i, j).getProduct().getPrice();
                double profit = salePrice - price;
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

        return this.salesManagement.getSale(0, indexOfLeastOne).getProduct().toString()
             + " =====> " + "The least profitable product: " + profits[indexOfLeastOne] + " TL.";
    }
}
