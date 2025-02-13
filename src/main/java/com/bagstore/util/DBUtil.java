package com.bagstore.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtil {
	private final String url ="jdbc:mysql://localhost:3306/bagstore";
	private final String userName = "root";
	private final String password = "Atul@6264";
	private final String  driver = "com.mysql.cj.jdbc.Driver";
	
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(url, userName, password);
		System.out.println("Message : Databases Connection Succesfully....");
		return connection;
	}
	public void close(Connection connection, PreparedStatement preparedStatement) throws SQLException {
		connection.close();
		preparedStatement.close();
		System.out.println("Message : Databases Connection Close Succesfully....");
	}
	
	
}
