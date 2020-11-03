package ua.lviv.iot.persistant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/vysotska?useUnicode=true&serverTimezone=UTC";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "lab_password";

	private static Connection connection = null;

	public ConnectionManager() {

	}

	public static Connection getConnection() {
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			} catch (SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
				System.out.println("SQLState: " + e.getSQLState());
				System.out.println("VendorError: " + e.getErrorCode());
			}
		}
		return connection;
	}

	public static void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
			}
		}
	}
}
