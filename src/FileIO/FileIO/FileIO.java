package FileIO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import SaleClasses.Customer;
import SaleClasses.Product;

public class FileIO {

    public static int numberOfLinesInFile(String path) {
        Scanner scanner = new Scanner(path);
        int numberOfLines = 0;

        FileReader fr;
        try {
            fr = new FileReader(path);
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

    public static Customer[] takeCustomers() {
        String path = "\\data\\Customers.csv";
        int numberOfLines = FileIO.numberOfLinesInFile(path);
        Customer[] customers = null;

        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

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
        return customers;
    }

    public static Product[][] takeProducts() {
        Product[][] products = new Product[3][];

        for (int fileIndex = 0; fileIndex < 3; fileIndex++) {
            String path = "\\data\\S" + String.valueOf(fileIndex + 1) + "_Products.csv";
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
        
        return products;
    }
}