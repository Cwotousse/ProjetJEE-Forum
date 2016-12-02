package be.forum.dao;

import java.sql.Connection;

import be.forum.pojo.UtilisateurPOJO;

public class DAOFactory {
	protected static final Connection  conn = SingletonConnection.getInstance();
	
	public DAO<UtilisateurPOJO> getUtilisateurDAO(){
		return new UtilisateurDAO(conn);
	}
}