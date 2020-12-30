package controllers;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import contents.Category;
import contents.Item;
import contents.User;

public class siteController {
	String INSERT_ITEMS_SQL = "INSERT INTO items" + " (name, quantity, price, image, category) VALUES " + " (?, ?, ?, ?, ?);";
	String UPDATE_ITEM_SQL = "UPDATE items SET name=?, quantity=?, price=?, image=?, category=? WHERE id=?";
	String SELECT_ITEMS_SQL = "SELECT * FROM items;";
	String SELECT_ITEM_SQL = "SELECT * FROM items WHERE id LIKE ?;";
	String SELECT_ITEM_BY_NAME_SQL = "SELECT * FROM items WHERE name LIKE ?;";
	String SELECT_PASSWORD_SQL = "SELECT password FROM account WHERE email LIKE ?;";
	String INSERT_USER_SQL = "INSERT INTO account" + " (email, password) VALUES "+"(?, ?);";
	String SELECT_EMAIL_SQL = "SELECT email FROM account WHERE email LIKE ?;";
	String SELECT_USER_SQL = "SELECT name, surname, email FROM account WHERE email LIKE ?;";
	String SELECT_MAX_ID_ORDER_SQL = "SELECT MAX(order_id) AS id FROM orders;";
	String INSERT_ORDER_SQL = "INSERT INTO orders" + " (order_id, name, surname, email, address, city, quantity, item_id) VALUES " + " (?, ?, ?, ?, ?, ?, ?, ?);";
	String INSERT_BOUGHT_ITEM_SQL = "INSERT INTO bought_items" + " (account_id, order_id) VALUES " + " (?, ?);";
	String GET_USER_ID_SQL = "SELECT id FROM account WHERE email LIKE ?;";
	String GET_OBSERVED_SQL = "SELECT item_id FROM observed_items WHERE account_id LIKE ? AND item_id LIKE ?;";
	String OBSERVE_ITEM_SQL = "INSERT INTO observed_items" + " (account_id, item_id) VALUES " + " (?, ?);";
	String STOP_OBSERVING_ITEM_SQL = "DELETE FROM observed_items WHERE account_id LIKE ? AND item_id LIKE ?;";
	String SELECT_ITEMS_FROM_CATEGORY_SQL = "SELECT * FROM items WHERE category LIKE ?;";
	String SELECT_CATEGORIES_SQL = "SELECT * FROM categories;";
	String QUANTITY_DECREMENT_SQL = "UPDATE items SET quantity=quantity-? WHERE id LIKE ?;";
	String SELECT_OBSERVED_ITEMS_ACCOUNTS_SQL = "SELECT account_id FROM observed_items WHERE item_id LIKE ?;";
	String SELECT_ACCOUNT_EMAIL_SQL = "SELECT email FROM account WHERE id LIKE ?;";
	String SET_QUANTITY_SQL = "UPDATE items SET quantity=? WHERE id LIKE ?;";
	String SEARCH_SQL = "SELECT * FROM items WHERE name LIKE ?;";


	protected Connection getConnection() {
		Connection connection = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp?useSSL=false", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public List<Item> selectAllItems() {
		List<Item> items = new ArrayList<>();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ITEMS_SQL);){
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int quantity = rs.getInt("quantity");
				double price = rs.getDouble("price");
				String image = rs.getString("image");
				int category = rs.getInt("category");
				items.add(new Item(id, name, quantity, price, image, category));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return items;
	}
	
	public Item selectItem(int id) {
		Item item = new Item();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ITEM_SQL);){
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				item = new Item(rs.getInt("id"), rs.getString("name"), rs.getInt("quantity"), rs.getDouble("price"), rs.getString("image"), rs.getInt("category"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return item;
	}
	
	
	public int registerItem(Item item) throws ClassNotFoundException {
		
		int result = 0;
		
		try{Connection connection = getConnection();
				
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ITEMS_SQL);
		preparedStatement.setString(1, item.getName());
		preparedStatement.setInt(2, item.getQuantity());
		preparedStatement.setDouble(3, item.getPrice());
		preparedStatement.setString(4, item.getImage());
		preparedStatement.setInt(5, item.getCategory());
		
		System.out.println(preparedStatement);
		result = preparedStatement.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void registerUser(String email, String password) {
		try{Connection connection = getConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL);
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, password);
		preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkLogin(String email, String password) {
		try{Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PASSWORD_SQL);
		preparedStatement.setString(1, email);
		ResultSet rs = preparedStatement.executeQuery();
		if(rs.next() == true) {
		String result = rs.getString("password");
		if (result.equals(password)) return true;
		else return false;
		}
		else return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

	public boolean checkEmail(String email) {
		try{
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMAIL_SQL);
		preparedStatement.setString(1, email);
		ResultSet rs = preparedStatement.executeQuery();
		if(rs.next() == false) return true;
		else return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public User getUser(String email) {
		try{Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_SQL);
				System.out.println(preparedStatement);
				preparedStatement.setString(1, email);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
				String name = "";
				String surname = "";
				if (rs.getString("name") != null) name = rs.getString("name");

				if (rs.getString("surname") != null) surname = rs.getString("surname");
				return new User(name, surname, email);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		return null;
	}
	
	public int getMaxOrdersId() {
		int id = -1;
		try{Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MAX_ID_ORDER_SQL);
		ResultSet rs = preparedStatement.executeQuery();
		if(rs.next()){
		id = rs.getInt("id");
		return id;
		}
	} catch(Exception e) {
		e.printStackTrace();
	}
		return id;
	}
	
	public void insertOrder(int id, String name, String surname, String email, String address, String city, int quantity, int item_id) {
		try{Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_SQL);
		preparedStatement.setInt(1, id);
		preparedStatement.setString(2, name);
		preparedStatement.setString(3, surname);
		preparedStatement.setString(4, email);
		preparedStatement.setString(5, address);
		preparedStatement.setString(6, city);
		preparedStatement.setInt(7, quantity);
		preparedStatement.setInt(8, item_id);
		preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertBoughtItems(int account_id, int order_id) {
		try{Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOUGHT_ITEM_SQL);
		preparedStatement.setInt(1, account_id);
		preparedStatement.setInt(2, order_id);
		preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public int getUserId(String email) {
		int id = -1;
		try{Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_ID_SQL);
				preparedStatement.setString(1, email);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
				id = rs.getInt("id");
				return id;
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		return id;
	}
	
	public boolean isItemObserved(int account_id, int item_id) {
		try{Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(GET_OBSERVED_SQL);
		preparedStatement.setInt(1, account_id);
		preparedStatement.setInt(2, item_id);
		ResultSet rs = preparedStatement.executeQuery();
		if(rs.next()){
		return true;
		}
	} catch(Exception e) {
		e.printStackTrace();
	}
		
		return false;
	}
	
	public void observeItem(int account_id, int item_id) {
		try{Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(OBSERVE_ITEM_SQL);
		preparedStatement.setInt(1, account_id);
		preparedStatement.setInt(2, item_id);
		preparedStatement.executeUpdate();
	} catch(Exception e) {
		e.printStackTrace();
	}
	
	}
	public void stopObservingItem(int account_id, int item_id) {
		try{Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(STOP_OBSERVING_ITEM_SQL);
		preparedStatement.setInt(1, account_id);
		preparedStatement.setInt(2, item_id);
		preparedStatement.executeUpdate();
	} catch(Exception e) {
		e.printStackTrace();
	}
	}
	
	public List<Item> selectItemsFromCategory(int _category) {
		List<Item> items = new ArrayList<>();
		try {Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ITEMS_FROM_CATEGORY_SQL);
			preparedStatement.setInt(1, _category);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int quantity = rs.getInt("quantity");
				double price = rs.getDouble("price");
				String image = rs.getString("image");
				int category = rs.getInt("category");
				items.add(new Item(id, name, quantity, price, image, category));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return items;
	}
	
	public List<Category> selectAllCategories() {
		List<Category> categories = new ArrayList<>();
		try {Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORIES_SQL);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				categories.add(new Category(id, name));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return categories;
	}
	
	public void decrementItemQuantity(int quantity, int id) {
		try {Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(QUANTITY_DECREMENT_SQL);
		preparedStatement.setInt(1, quantity);
		preparedStatement.setInt(2, id);
		preparedStatement.executeUpdate();
	} catch(Exception e) {
		e.printStackTrace();
	}
	}
	
	public Item selectItemByName(String name) {
		Item item = new Item();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ITEM_BY_NAME_SQL);){
			preparedStatement.setString(1, name);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				item = new Item(rs.getInt("id"), rs.getString("name"), rs.getInt("quantity"), rs.getDouble("price"), rs.getString("image"), rs.getInt("category"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return item;
	}
	
	public void updateItem(int id, String name, int quantity, double price, String image, int category) {
		try {Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ITEM_SQL);
				preparedStatement.setString(1, name);
				preparedStatement.setInt(2, quantity);
				preparedStatement.setDouble(3, price);
				preparedStatement.setString(4, image);
				preparedStatement.setInt(5, category);
				preparedStatement.setInt(6, id);
				System.out.println(preparedStatement);
				preparedStatement.executeUpdate();

			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	public void sendEmailsItemReady(int item_id) {
		try{Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_OBSERVED_ITEMS_ACCOUNTS_SQL);
		preparedStatement.setInt(1, item_id);
		ResultSet rs = preparedStatement.executeQuery();
		List<Integer> account_ids = new ArrayList<>();
		while(rs.next()){
			account_ids.add(rs.getInt("account_id"));
		}
		if(account_ids.size() > 0) {
		preparedStatement = connection.prepareStatement(SELECT_ACCOUNT_EMAIL_SQL);
		List<String> account_emails = new ArrayList<>();
		for(int i=0; i < account_ids.size(); i++) {
			preparedStatement.setInt(1, account_ids.get(i));
			rs = preparedStatement.executeQuery();
			account_emails.add(rs.getString("email"));
		}
		Mailer.sendMany(account_emails, "Stan przedmiotu", buildEmailItemReady(item_id));
		}
	} catch(Exception e) {
		e.printStackTrace();
	}
	}
	
	public void sendEmailsItemOut(int item_id) {
		try{Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_OBSERVED_ITEMS_ACCOUNTS_SQL);
		preparedStatement.setInt(1, item_id);
		ResultSet rs = preparedStatement.executeQuery();
		List<Integer> account_ids = new ArrayList<>();
		while(rs.next()){
			account_ids.add(rs.getInt("account_id"));
		}
		if(account_ids.size() > 0) {
		preparedStatement = connection.prepareStatement(SELECT_ACCOUNT_EMAIL_SQL);
		List<String> account_emails = new ArrayList<>();
		for(int i=0; i < account_ids.size(); i++) {
			preparedStatement.setInt(1, account_ids.get(i));
			rs = preparedStatement.executeQuery();
			account_emails.add(rs.getString("email"));
		}
		Mailer.sendMany(account_emails, "Stan przedmiotu", buildEmailItemOut(item_id));
		}
	} catch(Exception e) {
		e.printStackTrace();
	}
	}
	
	private String buildEmailItemReady(int item_id) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Nastepujacy przedmiot: \n");
		stringBuilder.append(selectItem(item_id).getName()+"\n");
		stringBuilder.append("jest juz dostepny w sklepie w cenie "+selectItem(item_id).getPrice()+"!");
		return stringBuilder.toString();
	}
	
	private String buildEmailItemOut(int item_id) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Nastepujacy przedmiot: \n");
		stringBuilder.append(selectItem(item_id).getName()+"\n");
		stringBuilder.append("nie jest juz dostepny w sklepie, nalezy czekac na dostawe produktu.)");
		return stringBuilder.toString();
	}
	
	public void setItemQuantity(int item_id, int main_quantity, int quantity) {
		try {Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SET_QUANTITY_SQL);
		preparedStatement.setInt(1, item_id);
		preparedStatement.setInt(2, main_quantity-quantity);
		preparedStatement.executeUpdate();
	} catch(Exception e) {
		e.printStackTrace();
	}
	}
	
	public List<Item> searchItems(String _name) {
		List<Item> items = new ArrayList<>();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_SQL);){
			preparedStatement.setString(1, "%"+_name+"%");
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int quantity = rs.getInt("quantity");
				double price = rs.getDouble("price");
				String image = rs.getString("image");
				int category = rs.getInt("category");
				items.add(new Item(id, name, quantity, price, image, category));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return items;
	}
}
