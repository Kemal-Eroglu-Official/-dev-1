package Query;
// import java.util.Arrays;

import SaleClasses.Product;

public class Supplier {
    private Product[] data;

    public Supplier(Product[] products) {
    	this.data = copy(products);
    }
    
    private Product[] copy(Product[] p) {
    	Product[] temp = new Product[p.length];
    	for(int i = 0; i < p.length; i++)
    		temp[i] = new Product(p[i]);
    	return temp;
    }

    public int length() {
        return this.data.length;
    }

    public Product getProduct(int index) {
        return new Product(this.data[index]);
    }
     
    public boolean equals(Supplier obj) {
    	if(obj == null)
    		return false;
    	
    	for (int i = 0; i < obj.data.length; i++) {
    		if(!this.data[i].equals(obj.data[i]))
    			return false;
    	}
    	return true;
    }

    public String toString() {
        String text = "";
        for (Product p : this.data) {
            text += p.toString() + "\n";
        }
        return text;
    }
}
