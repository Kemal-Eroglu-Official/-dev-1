// import java.util.Arrays;

import SaleClasses.Product;

public class Supplier {
    private Product[] data;
    // private int firstEmptyIndex;

    public Supplier(Product[] data) {
        this.data = data;
        // this.firstEmptyIndex = data.length;
    }

    // public Supplier(int numberOfProducts) {
    //     this.data = new Product[numberOfProducts];
    //     this.firstEmptyIndex = 0;
    // }

    // public void append(Product new_one) {
    //     if (this.firstEmptyIndex >= this.data.length) {
    //         this.data = Arrays.copyOf(this.data, this.data.length + 1);
    //     }
        
    //     this.data[firstEmptyIndex] = new_one;
    //     this.firstEmptyIndex++;
    // }

    public Product getProduct(int index) {
        return new Product(this.data[index]);
    }
}
