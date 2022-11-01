package FileIO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import SaleClasses.Customer;
import SaleClasses.Product;
import SaleClasses.Sale;

public class FileIO {

    private Customer[] customers;
    private Product[][] products;
    private Sale[][] sales;

    private static int numberOfLinesInFile(String path) {
        Scanner scanner = new Scanner(path);
        int numberOfLines = 0;

        try {
            FileReader fr = new FileReader(path);
            BufferedReader brReader = new BufferedReader(fr);

            while (brReader.readLine() != null) {
                numberOfLines++;
            }

            brReader.close();
            fr.close();
            scanner.close();
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        return numberOfLines;
    }

    public void takeCustomers() {
        String path = "src\\FileIO\\data\\Customers.csv";
        int numberOfLines = FileIO.numberOfLinesInFile(path);
        Customer[] customers = null;

        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // First line includes the tags of ".csv" file.
            // That's why, the lenght of the array that holds customers should be
            // number of lines - 1
            customers = new Customer[numberOfLines - 1];

            for (int i = 0; i < numberOfLines; i++) {
                String line = bufferedReader.readLine();
                
                if (i != 0) {
                    StringTokenizer tokenizer = new StringTokenizer(line, ",");
                    String id = null, name = null, email = null, country = null, adress = null;
                    int whichAttribute = 0;
                    
                    while (tokenizer.hasMoreTokens()) {
                        String newOne = tokenizer.nextToken();
                        
                        switch (whichAttribute) {
                            case 0: id = newOne; break;
                            case 1: name = newOne; break;
                            case 2: email = newOne; break;
                            case 3: country = newOne; break;
                            case 4: adress = newOne; break;
                        }
                        
                        if (++whichAttribute == 5) {
                            customers[i - 1] = new Customer(id, name, email, country, adress);
                            whichAttribute = 0;
                        }
                    }
                }
            }
            bufferedReader.close();
            fileReader.close();
        } 
        catch (FileNotFoundException e) {
            System.exit(-1);
        } 
        catch (IOException e) {
            System.exit(-2);
        }
        this.customers = customers;
    }

    public void takeProducts() {
        Product[][] products = new Product[3][];

        for (int fileIndex = 0; fileIndex < 3; fileIndex++) {
            String path = "src\\FileIO\\data\\S" + String.valueOf(fileIndex + 1) + "_Products.csv";
            int numberOfLines = FileIO.numberOfLinesInFile(path);

            try {
                FileReader fileReader = new FileReader(path);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                products[fileIndex] = new Product[numberOfLines - 1];

                for (int i = 0; i < numberOfLines; i++) {
                    String line = bufferedReader.readLine();
                    
                    if (i != 0) {
                        StringTokenizer tokenizer = new StringTokenizer(line, ",");
                        String id = null, title = null;
                        double rate = 0, numberOfReviews = 0, price = 0;
                        int whichAttribute = 0;
                        
                        while (tokenizer.hasMoreTokens()) {
                            String newOne = tokenizer.nextToken();
                            switch (whichAttribute) {
                                case 0: id = newOne; break;
                                case 1: title = newOne; break;
                                case 2: rate = Double.valueOf(newOne); break;
                                case 3: numberOfReviews = Double.valueOf(newOne); break;
                                case 4: price = Double.valueOf(newOne); break;
                            }

                            if (++whichAttribute == 5) {
                                products[fileIndex][i - 1] = new Product(id, title, rate, numberOfReviews, price);
                                whichAttribute = 0;
                            }
                        }
                    }
                }

                bufferedReader.close();
                fileReader.close();
            } 
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                System.exit(-2);
            }
        }
        
        this.products = products;
    }

    public void takeSales() {
        assert this.customers != null;
        assert this.products != null;

        Sale[][] sales = new Sale[3][];

        for (int fileIndex = 0; fileIndex < 3; fileIndex++) {
            String path = "src\\FileIO\\data\\S" + String.valueOf(fileIndex + 1) + "_Sales.csv";
            int numberOfLines = FileIO.numberOfLinesInFile(path);

            try {
                FileReader fileReader = new FileReader(path);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                sales[fileIndex] = new Sale[numberOfLines - 1];

                for (int i = 0; i < numberOfLines; i++) {
                    String line = bufferedReader.readLine();
                    
                    if (i != 0) {
                        StringTokenizer tokenizer = new StringTokenizer(line, ",");
                        String id = null;
                        String sales_date = null;
                        String customer_id = null;
                        String product_id = null;
                        int whichAttribute = 0;
                        
                        while (tokenizer.hasMoreTokens()) {
                            String newOne = tokenizer.nextToken();
                            switch (whichAttribute) {
                                case 0: id = newOne; break;
                                case 1: customer_id = newOne; break;
                                case 2: product_id = newOne; break;
                                case 3: sales_date = newOne; break;
                            }

                            if (++whichAttribute == 4) {
                                Product product = this.getProductFromId(product_id);
                                Customer customer = this.getCustomerFromId(customer_id);
                                if (customer == null) {
                                    System.out.println("Customer is null");
                                    System.exit(-1);
                                }
                                sales[fileIndex][i - 1] = new Sale(id, customer, product, sales_date);
                                whichAttribute = 0;
                            }
                        }
                    }
                }

                bufferedReader.close();
                fileReader.close();
            } 
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                System.exit(-2);
            }
        }
        
        this.sales = sales;
    }

    private Product getProductFromId(String id) {
        for (int i = 0; i < this.products.length; i++) {
            for (int j = 0; j < this.products[i].length; j++) {
                if (this.products[i][j].getId().equals(id)) {
                    return new Product(this.products[i][j]);
                }
            }
        }
        return null;
    }

    private Customer getCustomerFromId(String id) {
        for (int i = 0; i < this.customers.length; i++) {
            if (this.customers[i].getId().equals(id)) {
                return new Customer(this.customers[i]);
            }
        }
        return null;
    }

    public Customer[] getCustomers() {
        Customer[] data = new Customer[this.customers.length];
        for (int i = 0; i < this.customers.length; i++) {
            data[i] = this.customers[i];
        }
        return data;
    }

    public Product[][] getProducts() {
        Product[][] data = new Product[this.products.length][];
        for (int i = 0; i < this.products.length; i++) {
            Product[] row = new Product[this.products[i].length];
            for (int j = 0; j < this.products[i].length; j++) {
                row[j] = this.products[i][j];
            }
            data[i] = row;
        }
        return data;
    }

    public Sale[][] getSales() {
        Sale[][] data = new Sale[this.sales.length][];
        for (int i = 0; i < this.sales.length; i++) {
            Sale[] row = new Sale[this.sales[i].length];
            for (int j = 0; j < this.sales[i].length; j++) {
                row[j] = this.sales[i][j];
            }
            data[i] = row;
        }
        return data;
    }
}
