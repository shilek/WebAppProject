package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import contents.Item;

public class itemController {
	public int registerItem(Item item) throws ClassNotFoundException {
		String INSERT_ITEMS_SQL = "INSERT INTO items" + " (name, quantity, price) VALUES " + " (?, ?, ?);";
		
		int result = 0;
		
		Class.forName("com.mysql.jdbc.Driver");
		
		try{Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp?useSSL=false", "root", "root");
				
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ITEMS_SQL);
		preparedStatement.setString(1, item.getName());
		preparedStatement.setInt(2, item.getQuantity());
		preparedStatement.setDouble(3, item.getPrice());
		
		System.out.println(preparedStatement);
		result = preparedStatement.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
