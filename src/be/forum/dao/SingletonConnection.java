package be.forum.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
	//En local
	/*private static final String DB_DRIVER 		= "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION 	= "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DB_USER 		= "forum";
	private static final String DB_PASSWORD 	= "pwd";*/
	
	//Sur le serveur de l'école
	private static final String DB_DRIVER 		= "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION 	= "jdbc:oracle:thin:@char-oracle11.condorcet.be:1521:xe";
	private static final String DB_USER 		= "exa2";
	private static final String DB_PASSWORD 	= "tribuLugubre";
	private static Connection connect = getInstance();

	private SingletonConnection() {
		try {
			Class.forName(DB_DRIVER);
			connect = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
		} catch (SQLException ex) {
			System.out.println("Erreur JDBC: " + ex.getMessage());
		} catch (ClassNotFoundException ex) {
			System.exit(0);
		}
	}

	public static Connection getInstance() {
		if (connect == null)
			new SingletonConnection();
		return connect;
	}
}