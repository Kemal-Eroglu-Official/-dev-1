package App;

/** This is our Drop-shipping App.
 *  The program starts from here and creates an SalesQuery object
 *  then calls our homework questions one by one. 
 *  
 *    @author  Kemal Eroğlu
 *    @author  Ünal Dalkılıç
 *    @author  Sıddık Can Boru
 *    @author  Tarık Emre Gündüz
 */

import Query.SalesQuery;

public class App {
    public static void main(String[] args) {
        SalesQuery query = new SalesQuery();
        query.setup();
        System.out.println(query.find());
    }
}
