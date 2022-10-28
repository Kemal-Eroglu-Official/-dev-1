package App;

import SaleClasses.Product;
import FileIO.FileIO;

public class App {
    public static void main(String[] args) throws Exception {
        Product[][] arr = FileIO.takeProducts();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.println(arr[i][j].toString());
            }
        }
    }
}
