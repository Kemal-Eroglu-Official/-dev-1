package SaleClasses;

public class Sale {
    private String id;
    private Customer customer;
    private Product product;
    private String salesDate;
    private double salesPrice;
    
    public Sale(String id, Customer customer, Product product, String sales_date) {
        assert Sale.isArgumentsOkay(id, customer, product, sales_date);
        this.id = id;
        this.customer = new Customer(customer);
        this.product = new Product(product);
        this.salesPrice = product.getNumberOfReviews() * ((product.getRate() / 5.0) * 100) + product.getPrice();
        this.salesDate = sales_date;
    }

    public Sale(Sale s) {
        assert Sale.isArgumentsOkay(id, customer, product, salesDate);
        this.id = s.id;
        this.customer = new Customer(s.customer);
        this.product = new Product(s.product);
        this.salesPrice = s.salesPrice;
        this.salesDate = s.salesDate;
    }
    
    // A method that checks that each argument is in the correct form.
    private static boolean isArgumentsOkay(String id, Customer customer, Product product, String sales_date) {
        return id != null &&
        id.length() != 0 &&
        customer != null &&
        product != null &&
        sales_date != null;
    }
    
    public boolean equals(Sale s) {
        if (s == null) {
            return false;
        }

        return this.id.equals(s.getId()) &&
        this.customer.equals(s.getCustomer()) &&
        this.product.equals(s.getProduct()) &&
        this.salesDate == s.getSalesDate() &&
        this.salesPrice == s.salesPrice;
    }

    public String toString() {
        return id + ", " + customer.toString() + ", " + product.toString() + ", " + salesDate + ", " + salesPrice;
    }

    public Customer getCustomer() {
        return new Customer(customer);
    }

    public Product getProduct() {
        return new Product(product);
    }

    public String getId() {
        return id;
    }
    
    public String getSalesDate() {
        return salesDate;
    }

    public double getSalesPrice() {
        return salesPrice;
    }
}
