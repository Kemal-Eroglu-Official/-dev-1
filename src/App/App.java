package App;

/**
 *  Authors:
 *          Kemal Eroğlu      280201009
 *          Ünal Dalkılıç     280201068
 *          Sıddık Can Boru   280201073
 *          Tarık Emre Gündüz 280201075
 */

import Query.SalesQuery;

public class App {
    public static void main(String[] args) {
        SalesQuery query = new SalesQuery();
        query.setup();
        System.out.println(query.find());
    }
}
