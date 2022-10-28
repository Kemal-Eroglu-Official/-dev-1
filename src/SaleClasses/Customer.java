package SaleClasses;
public class Customer {
    private String id;
    private String name;
    private String email;
    private String country;
    private String adress;

    public Customer(String id, String name, String email, String country, String adress) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.country = country;
        this.adress = adress;
    }

    public Customer(Customer c) {
        this.id = c.id;
        this.name = c.name;
        this.email = c.email;
        this.country = c.country;
        this.adress = c.adress;
    }

    public String toString() {
        return "Id: " + id + ", Name: " + name + ", Email: " + email + ", Country: " + country + ", Adress: " + adress;
    }

    public String getAdress() {
        return adress;
    }

    public String getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
