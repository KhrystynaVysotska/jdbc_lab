package ua.lviv.iot.persistant;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

	private static Connection connection = null;

	public ConnectionManager() {

	}

	public static Connection getConnection() {
		if (connection == null) {
			try (InputStream input = ConnectionManager.class.getClassLoader().getResourceAsStream("config.properties")) {
				Properties prop = new Properties();
				prop.load(input);

				connection = DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.user"),
						prop.getProperty("db.password"));
			} catch (IOException | SQLException ex) {
				ex.printStackTrace();
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
