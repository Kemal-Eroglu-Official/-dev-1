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
        fileio.takeCustomers();
        fileio.takeProducts();
        fileio.takeSales();
        
        this.numberOfCustomers = fileio.getCustomers().length;
        this.suppliers = new Supplier[3];
        
        Product[][] products = fileio.getProducts();
        for (int i = 0; i < 3; i++) {
            this.suppliers[i] = new Supplier(products[i]);
        }

        this.salesManagement = new SalesManagement(fileio.getSales());
    }

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

    public String theLeastProfitProductOfS1() {
        // calculate the least-profit product of S1. (Please include the amount of profit to output.)

        int numberOfSales = this.salesManagement.length(0);
        Double[] profits = new Double[numberOfSales];
        
        for(int i = 0; i < numberOfSales; i++) {
            Sale sale = this.salesManagement.getSale(0, i);
            profits[i] = sale.getSalesPrice() - sale.getProduct().getPrice();
        }

        int indexOfLeastOne = 0;

        for(int i = 0; i < profits.length; i++) {
            if(profits[indexOfLeastOne] > profits[i]) {
                indexOfLeastOne = i;
            }
        }

        return this.salesManagement.getSale(0, indexOfLeastOne).getProduct().toString() + " =====> " + profits[indexOfLeastOne];
    }
}
