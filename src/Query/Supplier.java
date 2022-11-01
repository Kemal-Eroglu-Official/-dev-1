package Query;

import SaleClasses.Product;

public class Supplier {
    private Product[] data;

    public Supplier(Product[] products) {
        Product[] data = new Product[products.length];

        for (int i = 0; i < products.length; i++) {
            data[i] = products[i];
        }
    	this.data = data;
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
