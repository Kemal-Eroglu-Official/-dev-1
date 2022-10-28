package SaleClasses;
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

	public Product(Product p) {
		this.id = p.id;
		this.title = p.title;
		this.rate = p.rate;
		this.numberOfReviews = p.numberOfReviews;
		this.price = p.price;
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
