package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBase {
	private Connection con;

	public DataBase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/projet?useSSL=false", "admin", "admin");
		} catch (Exception e) {
			System.out.println("Error connecting to database: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public Connection connecion() throws Exception {
		if (con == null) {
			throw new Exception("Error: database connection is null");
		}
		return con;
	}
}
