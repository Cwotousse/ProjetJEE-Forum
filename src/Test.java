import java.util.Calendar;

import be.forum.dao.DAO;
import be.forum.dao.DAOFactory;
import be.forum.pojo.Actualite;
import be.forum.pojo.Categorie;
import be.forum.pojo.Commentaire;
import be.forum.pojo.Historique;
import be.forum.pojo.SousCategorie;
import be.forum.pojo.Sujet;
import be.forum.pojo.Utilisateur;

public class Test {

	public static void main(String[] args) {
		// La date du jour, pour les tests
		java.sql.Date datePourTester = new java.sql.Date(Calendar.getInstance().getTime().getTime());

		//Les DAO
		DAO<Utilisateur> utilisateurDAO = new DAOFactory().getUtilisateurDAO();
		DAO<Categorie> catDAO = new DAOFactory().getCategorieDAO();
		DAO<SousCategorie> sousCatDAO = new DAOFactory().getSousCategorieDAO();
		DAO<Sujet> sujetDAO = new DAOFactory().getSujetDAO();
		DAO<Historique> historiqueDAO = new DAOFactory().getHistoriqueDAO();
		DAO<Actualite> actualiteDAO = new DAOFactory().getActualiteDAO();
		DAO<Commentaire> commentaireDAO = new DAOFactory().getCommentaireDAO();
	}
}
