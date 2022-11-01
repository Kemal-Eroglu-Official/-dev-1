package App;

import Query.SalesQuery;

public class App {
    public static void main(String[] args) {
        SalesQuery query = new SalesQuery();
        query.setup();
        System.out.println(query.find());
    }
}
