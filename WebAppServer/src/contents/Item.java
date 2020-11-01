package contents;

public class Item {
	private static int id;
	private static String name;
	private static int quantity;
	private static double price;
	
	public static int getId() {
		return id;
	}
	public static void setId(int _id) {
		id = _id;
	}
	public static String getName() {
		return name;
	}
	public static void setName(String _name) {
		name = _name;
	}
	
	public static int getQuantity() {
		return quantity;
	}
	public static void setQuantity(int _quantity) {
		quantity = _quantity;
	}
	public static double getPrice() {
		return price;
	}
	public static void setPrice(double _price) {
		price = _price;
	}
}
