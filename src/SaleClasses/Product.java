package SaleClasses;

// A data class that holds informations about Product.
// There is no manipulator methods.
public class Product {
	private String id;
	private String title;
	private double rate;
	private double numberOfReviews;
	private double price;
	
	public Product(String id, String title, double rate, double numberOfReviews, double price) {
		this.id = id;
		this.title = title;
		this.rate = rate;
		this.numberOfReviews = numberOfReviews;
		this.price = price;
	}

	// This is a copy constructor of 'Product' class
	public Product(Product p) {
		this.id = p.id;
		this.title = p.title;
		this.rate = p.rate;
		this.numberOfReviews = p.numberOfReviews;
		this.price = p.price;
	}
	

	public boolean equals(Product p) {
		if (p == null) {
            return false;
        }
		else {
			return this.id.equals(p.getId()) &&
			this.title.equals(p.getTitle()) &&
			this.rate == p.getRate() &&
			this.numberOfReviews == p.getNumberOfReviews() &&
			this.price == p.getPrice();
		}
	}

	public String toString() {
		return id + ", " + title + ", " + rate + ", "+ numberOfReviews + ", " + price;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public double getRate() {
		return rate;
	}

	public double getNumberOfReviews() {
		return numberOfReviews;
	}

	public double getPrice() {
		return price;
	}
}
