package contents;

public class Item {
	private int id;
	private String name;
	private int quantity;
	private double price;
	private String image;
	private int category;
	private int isObserved;
	
	public Item(){};
	
	public Item(int id, String name, int quantity, double price, String image, int category) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.image = image;
		this.category = category;
	}
	
	public Item(String name, int quantity, double price, String image, int category) {
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.image = image;
		this.category = category;
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}
	
	public String getImage() {
		return image;
	}
	
	public int getCategory() {
		return category;
	}

	public int getIsObserved() {
		return isObserved;
	}

	public void setIsObserved(int isObserved) {
		this.isObserved = isObserved;
	}

	
}
