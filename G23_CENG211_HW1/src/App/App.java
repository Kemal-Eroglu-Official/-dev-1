package App;

/** This is our Drop-shipping App.
 *  The program starts from here and creates an SalesQuery object
 *  then calls our homework questions one by one. 
 *  
 *    @author  Kemal Eroğlu      280201009
 *    @author  Ünal Dalkılıç     280201068
 *    @author  Sıddık Can Boru   280201073
 *    @author  Tarık Emre Gündüz 280201075
 */

import Query.SalesQuery;

public class App {
    public static void main(String[] args) {
        SalesQuery query = new SalesQuery();
        query.setup();
        System.out.println(query.find());
    }
}
