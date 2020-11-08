package controllers;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import contents.Item;

public class itemController {
	String INSERT_ITEMS_SQL = "INSERT INTO items" + " (name, quantity, price, image, category) VALUES " + " (?, ?, ?, ?, ?);";
	String SELECT_ITEMS_SQL = "SELECT * FROM items;";

	protected Connection getConnection() {
		Connection connection = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
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
				String category = rs.getString("category");
				items.add(new Item(id, name, quantity, price, image, category));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return items;
	}
	
	public int registerItem(Item item) throws ClassNotFoundException {
		
		int result = 0;
		
		try{Connection connection = getConnection();
				
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ITEMS_SQL);
		preparedStatement.setString(1, item.getName());
		preparedStatement.setInt(2, item.getQuantity());
		preparedStatement.setDouble(3, item.getPrice());
		preparedStatement.setString(4, item.getImage());
		preparedStatement.setString(5, item.getCategory());
		
		System.out.println(preparedStatement);
		result = preparedStatement.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
