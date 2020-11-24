package controllers;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import contents.Item;
import contents.User;

public class siteController {
	String INSERT_ITEMS_SQL = "INSERT INTO items" + " (name, quantity, price, image, category) VALUES " + " (?, ?, ?, ?, ?);";
	String SELECT_ITEMS_SQL = "SELECT * FROM items;";
	String SELECT_PASSWORD_SQL = "SELECT password FROM account WHERE email LIKE ?;";
	String INSERT_USER_SQL = "INSERT INTO account" + " (email, password) VALUES "+"(?, ?);";
	String SELECT_EMAIL_SQL = "SELECT email FROM account WHERE email LIKE ?;";
	String SELECT_USER_SQL = "SELECT name, surname, email FROM account WHERE email LIKE ?";
	public static String loggedInEmail;

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
	
}
