package be.forum.dao;

import java.sql.Connection;

import be.forum.pojo.Categorie;
import be.forum.pojo.Commentaire;
import be.forum.pojo.Historique;
import be.forum.pojo.SousCategorie;
import be.forum.pojo.Sujet;
import be.forum.pojo.Utilisateur;

public class DAOFactory {
	protected static final Connection  conn = SingletonConnection.getInstance();
	
	public DAO<Utilisateur> getUtilisateurDAO(){
		return new UtilisateurDAO(conn);
	}
	public DAO<Categorie> getCategorieDAO(){
		return new CategorieDAO(conn);
	}
	public DAO<Commentaire> getCommentaireDAO(){
		return new CommentaireDAO(conn);
	}
	public DAO<Historique> getHistoriqueDAO(){
		return new HistoriqueDAO(conn);
	}
	public DAO<Sujet> getSujetDAO(){
		return new SujetDAO(conn);
	}
	public DAO<SousCategorie> getSousCategorieDAO(){
		return new SousCategorieDAO(conn);
	}
}