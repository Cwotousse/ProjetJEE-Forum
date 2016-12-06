package be.forum.dao;

import java.sql.Connection;

import be.forum.pojo.ActualitePOJO;
import be.forum.pojo.CategoriePOJO;
import be.forum.pojo.CommentairePOJO;
import be.forum.pojo.HistoriquePOJO;
import be.forum.pojo.SousCategoriePOJO;
import be.forum.pojo.SujetPOJO;
import be.forum.pojo.UtilisateurPOJO;

public class DAOFactory {
	protected static final Connection  conn = SingletonConnection.getInstance();
	
	public DAO<UtilisateurPOJO> getUtilisateurDAO(){
		return new UtilisateurDAO(conn);
	}
	public DAO<ActualitePOJO> getActualiteDAO(){
		return new ActualiteDAO(conn);
	}
	public DAO<CategoriePOJO> getCategorieDAO(){
		return new CategorieDAO(conn);
	}
	public DAO<CommentairePOJO> getCommentaireDAO(){
		return new CommentaireDAO(conn);
	}
	public DAO<HistoriquePOJO> getHistoriqueDAO(){
		return new HistoriqueDAO(conn);
	}
	public DAO<SujetPOJO> getSujetDAO(){
		return new SujetDAO(conn);
	}
	public DAO<SousCategoriePOJO> getSousCategorieDAO(){
		return new SousCategorieDAO(conn);
	}
}