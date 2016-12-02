package be.forum.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
    private static Connection snglConnection = null;

    private SingletonConnection() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            String url = "jdbc:ucanaccess://SocialNetwork.accdb";
            snglConnection = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            //Log.putErrorInLog("Erreur Class Not Found :" +e.getMessage());
            System.out.println("Classe de driver introuvable : " + e.getMessage());
        } catch (SQLException e) {
        	//Log.putErrorInLog("Erreur SQL Exception :" + e.getMessage());
        	System.out.println("Erreur JDBC: " + e.getMessage());
        }

        if (snglConnection == null) {
        	//Log.putErrorInLog("Le serveur s'est interrompu !");
        }
    }

    public static Connection getInstance() {
        if (snglConnection == null) {
            new SingletonConnection();
        }
        return snglConnection;
    }
}