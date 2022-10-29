package App;

import FileIO.FileIO;
import Query.SalesQuery;
import SaleClasses.Product;

public class App {
    public static void main(String[] args) throws Exception {
        SalesQuery query = new SalesQuery();
        query.setup();
        System.out.println(query.salesManagement.toString());
    }
}
