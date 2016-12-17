package be.forum.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
	private static final String DB_DRIVER 		= "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION 	= "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DB_USER 		= "forum";
	private static final String DB_PASSWORD 	= "pwd";
	/*private static final String DB_DRIVER 		= "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION 	= "jdbc:oracle:thin:@char-oracle11.condorcet.be:1521:xe";
	private static final String DB_USER 		= "exa2";
	private static final String DB_PASSWORD 	= "tribuLugubre";*/
	// Objet de connexion
	private static Connection connect = getInstance();

	// Caractéristique du singleton ! Le constructeur privé :
	private SingletonConnection() {
		try {
			// Chargement de la classe du driver par la JVM
			Class.forName(DB_DRIVER);
			connect = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
		} catch (SQLException ex) {
			//System.out.println("Erreur JDBC: " + ex.getMessage());
		} catch (ClassNotFoundException ex) {
			//System.out.println("Classe de driver introuvable : " + ex.getMessage());
			System.exit(0);
		}
	}

	// Méthode qui va nous retourner notre instance et la créer si elle n'existe pas
	public static Connection getInstance() {
		if (connect == null) {
			//System.out.println("Instanciation de la connexion.");
			new SingletonConnection();
			//System.out.println("Connexion réussie!");
		}
		return connect;
	}
}