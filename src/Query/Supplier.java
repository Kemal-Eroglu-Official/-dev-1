package Query;
// import java.util.Arrays;

import SaleClasses.Product;

public class Supplier {
    private Product[] data;
    // private int firstEmptyIndex;

    // public Supplier(Product[] data) {
    //     this.data = data;
    //     // this.firstEmptyIndex = data.length;
    // }

    // // public Supplier(int numberOfProducts) {
    // //     this.data = new Product[numberOfProducts];
    // //     this.firstEmptyIndex = 0;
    // // }

    // // public void append(Product new_one) {
    // //     if (this.firstEmptyIndex >= this.data.length) {
    // //         this.data = Arrays.copyOf(this.data, this.data.length + 1);
    // //     }
        
    // //     this.data[firstEmptyIndex] = new_one;
    // //     this.firstEmptyIndex++;
    // // }

    public Supplier(Product[] products) {
    	this.data = copy(products);
    }
    
    public Supplier(Supplier obj) {
    	this(obj.copy(obj.data));
    }

    public Product getProduct(int index) {
        return new Product(this.data[index]);
    }

    public Product[] getProducts() {
    	return copy(this.data);
    }
    
    private Product[] copy(Product[] p) {
    	Product[] temp = new Product[p.length];
    	for(int i = 0; i < p.length; i++)
    		temp[i] = new Product(p[i]);
    	return temp;
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
