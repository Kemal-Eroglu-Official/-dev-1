package SaleClasses;
import java.sql.Date;

public class Sale {
    private String id;
    private Customer customer;
    private Product product;
    private Date sales_date;
    private double sales_price;

    private static boolean isParametersOkay(String id, Customer customer, Product product, Date sales_date) {
        return id != null &&
        id.length() != 0 &&
        customer != null &&
        product != null &&
        sales_date != null;
    }

    public Sale(String id, Customer customer, Product product, Date sales_date) {
        assert Sale.isParametersOkay(id, customer, product, sales_date);
        this.id = id;
        this.customer = customer;
        this.product = product;
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

    public Customer getCustomer() {
        return new Customer(customer);
    }

    public Product getProduct() {
        return new Product(product);
    }

    public String getId() {
        return id;
    }
    
    public Date getSales_date() {
        return new Date(sales_date.getTime());
    }

    public double getSales_price() {
        return sales_price;
    }
}
