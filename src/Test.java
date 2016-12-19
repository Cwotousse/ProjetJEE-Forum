import java.util.ArrayList;
import java.util.Calendar;

import be.forum.dao.DAO;
import be.forum.dao.DAOFactory;
import be.forum.metier.Sujet;
import be.forum.pojo.ActualitePOJO;
import be.forum.pojo.CategoriePOJO;
import be.forum.pojo.CommentairePOJO;
import be.forum.pojo.HistoriquePOJO;
import be.forum.pojo.SousCategoriePOJO;
import be.forum.pojo.SujetPOJO;
import be.forum.pojo.UtilisateurPOJO;

public class Test {

	public static void main(String[] args) {
		// La date du jour, pour les tests
		java.sql.Date datePourTester = new java.sql.Date(Calendar.getInstance().getTime().getTime());

		//Les DAO
		DAO<UtilisateurPOJO> utilisateurDAO = new DAOFactory().getUtilisateurDAO();
		DAO<CategoriePOJO> catDAO = new DAOFactory().getCategorieDAO();
		DAO<SousCategoriePOJO> sousCatDAO = new DAOFactory().getSousCategorieDAO();
		DAO<SujetPOJO> sujetDAO = new DAOFactory().getSujetDAO();
		DAO<HistoriquePOJO> historiqueDAO = new DAOFactory().getHistoriqueDAO();
		DAO<ActualitePOJO> actualiteDAO = new DAOFactory().getActualiteDAO();
		DAO<CommentairePOJO> commentaireDAO = new DAOFactory().getCommentaireDAO();
		
		String nomSousCategorie = "Football";
		Sujet suj = new Sujet();
		//ArrayList<Sujet> listSujet = sujet.getListSelonSousCategorie(nomSousCategorie);
		
		ArrayList<Sujet> listSuj = suj.getListSelonSousCategorie(nomSousCategorie);
		for (Sujet sujet2 : listSuj) {
			System.out.println(sujet2.getTitre());
		}
		
	}
}
