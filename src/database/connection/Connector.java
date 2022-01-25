package database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {
	
	public static Connection getConnetion() {
		
		String url = "jdbc:mysql://localhost:3306/crud?useSSL=false";
		String user = "root";
		String password = "";
		
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void close(Connection connection) {
		try {
			if(connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement statement) {
		try {
			if(statement != null)
				statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet resultSet) {
		try {
			if(resultSet != null)
				resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection connection, Statement statement, ResultSet resultSet) {
		close(connection);
		close(resultSet);
		close(statement);
	}
	
	public static boolean isClosed(Connection connection) {
		try {
			if(connection != null)
				return connection.isClosed();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("How?");
	}
	
	
}
