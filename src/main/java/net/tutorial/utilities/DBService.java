package net.tutorial.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class DBService {

	private static DBService instance = new DBService();
	Connection dbConnection = null;
	private PreparedStatement ps = null;

	private DBService() {
		createTable();
	}

	public static DBService getInstance() {
		return instance;
	}
	
	public void cleanUp() {
		try {
			if (this.ps != null) {
				this.ps.close();
			}
			if (this.dbConnection != null) {
				this.dbConnection.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public Connection getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL JDBC Driver not found.");
			System.out.println(e.getMessage());
			return null;
		}

		try {
			EnvVariables envVar = new EnvVariables();
			Map<String, String> creds = envVar.getCredentials("cleardb");
			this.dbConnection = DriverManager.getConnection(creds.get("jdbcUrl"));
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}

		return this.dbConnection;
	}
	
	private void createTable() {
		this.dbConnection = getConnection();

		String createTableSQL = "CREATE TABLE IF NOT EXISTS `contacts` (" + "`_id` int(11) NOT NULL AUTO_INCREMENT,"
				+ "`name` varchar(45) DEFAULT NULL," + "`email` varchar(45) DEFAULT NULL,"
				+ "`mobile` varchar(45) DEFAULT NULL," + "PRIMARY KEY (`_id`)"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

		try {
			this.ps = this.dbConnection.prepareStatement(createTableSQL);
			this.ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}
	}
	
}
