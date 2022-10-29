package SaleClasses;

public class Sale {
    private String id;
    private Customer customer;
    private Product product;
    private String sales_date;
    private double sales_price;

    public boolean equals(Sale s) {
        if (s == null) {
            return false;
        }

        return this.id.equals(s.getId()) &&
        this.customer.equals(s.getCustomer()) &&
        this.product.equals(s.getProduct()) &&
        this.sales_date == s.getSales_date() &&
        this.sales_price == s.sales_price;
    }

    private static boolean isParametersOkay(String id, Customer customer, Product product, String sales_date) {
        return id != null &&
        id.length() != 0 &&
        customer != null &&
        product != null &&
        sales_date != null;
    }

    public Sale(String id, Customer customer, Product product, String sales_date) {
        assert Sale.isParametersOkay(id, customer, product, sales_date);
        this.id = id;
        this.customer = new Customer(customer);
        this.product = new Product(product);
        this.sales_price = product.getNumberOfReviews() * ((product.getRate() / 5.0) * 100) + product.getPrice();
        this.sales_date = sales_date;
    }

    public Sale(Sale s) {
        assert Sale.isParametersOkay(id, customer, product, sales_date);
        this.id = s.id;
        this.customer = new Customer(s.customer);
        this.product = new Product(s.product);
        this.sales_price = s.sales_price;
        this.sales_date = s.sales_date;
    }

    public String toString() {
        return id + ", " + customer.toString() + "," + product.toString() + "," + sales_date + "," + sales_price + ", ";
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
    
    public String getSales_date() {
        return sales_date;
    }

    public double getSales_price() {
        return sales_price;
    }
}
