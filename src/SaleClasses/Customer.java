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

    public boolean equals(Customer c) {
        return this.id.equals(c.getId()) &&
        this.name.equals(c.getName()) &&
        this.email.equals(c.getEmail()) &&
        this.country.equals(c.getCountry()) &&
        this.adress.equals(c.getAdress());
    }

    public String toString() {
        return id + "," + name + ", " + email + "," + country + "," + adress + ", ";
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
