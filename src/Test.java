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
		
		//Utilisateur
		/**
		 * CREATE -> FONCTIONNE
		 * UPDATE -> FONCTIONNE
		 * DELETE -> FONCTIONNE
		 * SELECT (FIND) -> FONCTIONNE
		 */
		UtilisateurPOJO util = new UtilisateurPOJO();
		util.setPseudo("pseudoUtilDelete");
		util.setMotdepasse("mdpUtilDelete");

		//utilisateurDAO.create(util);
		util.setMotdepasse("mdpUtilModifie");
		util.setPrenom("prenomModifie");
		//utilisateurDAO.update(util);
		//utilisateurDAO.delete(util);
		//System.out.println(utilisateurDAO.find(1).getPseudo());
		
		//Categorie
		/**
		 * CREATE -> FONCTIONNE
		 * UPDATE -> FONCTIONNE AVEC FILTER
		 * DELETE -> FONCTIONNE
		 * SELECT (FIND) -> FONCTIONNE
		 */
		//CategoriePOJO cat = new CategoriePOJO();
		//cat.setTitre("cat qui GFDGDGGDletedD");
		CategoriePOJO cat = catDAO.find(2);
		
		//catDAO.create(cat);
		//catDAO.delete(cat);

		//POUR TROUVER ID ET UPDATE PAR ID
		/*CategoriePOJO catTrouve = catDAO
				.getList()
				.stream()
				.filter(x -> x.getTitre().equals(cat.getTitre())
				).findAny()
				.orElse(null);
		if(catTrouve == null)
			System.out.println("null");
		else {
			System.out.println(catTrouve.getTitre());
			System.out.println(catTrouve.getID());
		}*/
		//cat.setID(catTrouve.getID());
		//cat.setTitre("cat modifié");
		//catDAO.update(cat);
		
		//System.out.println(catDAO.find(5).getTitre());
		
		//SousCategorie
		/**
		 * CREATE -> FONCTIONNE
		 * UPDATE -> FONCTIONNE
		 * DELETE -> FONCTIONNE
		 * SELECT (FIND) -> FONCTIONNE
		 */
		SousCategoriePOJO sousCat = new SousCategoriePOJO();
		sousCat.setTitre("sousCat qui va être testee");
		sousCat.setCategoriePOJO(cat);
		
		//sousCatDAO.create(sousCat);
		//SousCategoriePOJO sousCat = sousCatDAO.find(5);
		//System.out.println(sousCat.getTitre());
		//sousCat.setTitre("Updated");
		//sousCatDAO.update(sousCat);		
		//sousCatDAO.delete(sousCat);
		//System.out.println(utilisateurDAO.find(1).getPrenom());
		
		//Sujet
		/**
		 * CREATE -> FONCTIONNE AVEC DES FIND
		 * UPDATE -> FONCTIONNE
		 * DELETE -> FONCTIONNE AVEC DES FILTERS
		 * SELECT (FIND) -> FONCTIONNE
		 */
			SujetPOJO sujet = new SujetPOJO();
			sujet.setTitre(null);
			sujet.setSousCategoriePOJO(sousCatDAO.find(1));
			sujet.setDateSujet(datePourTester);
			sujet.setUtilisateurPOJO(utilisateurDAO.find(1));
			
			sujetDAO.create(sujet);
			//sujetDAO.delete(sujet);
	
		
		

		/*System.out.println(sujet.getUtilisateurPOJO().getPseudo());
		System.out.println(sujet.getUtilisateurPOJO().getMotdepasse());
		System.out.println(sujet.getUtilisateurPOJO().getNom());
		System.out.println(sujet.getUtilisateurPOJO().getPrenom());
		System.out.println(sujet.getUtilisateurPOJO().getDateNaissance());
		System.out.println(sujet.getUtilisateurPOJO().getType());
		System.out.println(sujet.getUtilisateurPOJO().getMail());
		//sujetDAO.create(sujet);
		//sujetDAO.delete(sujet);

		SujetPOJO sujetTrouve = sujetDAO
				.getList()
				.stream()
				.filter(x -> x.getTitre().equals(sujet.getTitre())
						//date marche pas mais les equals sont bons!
						//&& x.getDateSujet().equals(sujet.getDateSujet())
						&& x.getSousCategoriePOJO().equals(sujet.getSousCategoriePOJO())
						&& x.getUtilisateurPOJO().equals(sujet.getUtilisateurPOJO())
				).findAny()
				.orElse(null);
		if(sujetTrouve == null)
			System.out.println("null");
		else {
			System.out.println(sujetTrouve.getTitre());
			System.out.println(sujetTrouve.getID());
		}*/

		//sujet.setTitre("sujet qui va etre delete MODIFIE");
		//sujet.setDateSujet(new java.sql.Date(454));
		//sujet.setID(sujetTrouve.getID());
		
		//sujetDAO.update(sujet);
		//System.out.println(sujetDAO.find(3).getTitre());	
		
		//ACTUALITE
		/** 
		 * CREATE -> FONCTIONNE
		 * UPDATE -> FONCTIONNE
		 * DELETE -> PAS ENCORE SPROCKEe
		 * SELECT (FIND) -> FONCTIONNE
		 */
		ActualitePOJO actualite = new ActualitePOJO();
		actualite.setDescription("Description test actualite");
		actualite.setTitre("Titre test actualite");
		
		//actualiteDAO.create(actualite);
		//ActualitePOJO actualite = actualiteDAO.find(1);
		//System.out.println(actualite.getTitre());
		//actualite.setTitre("Titre test actualite updated");
		//actualiteDAO.update(actualite);
		//actualiteDAO.delete(actualite);
		
		
		//HISTORIQUE
		/** 
		 * CREATE -> FONCTIONNE
		 * UPDATE -> FONCTIONNE
		 * DELETE -> FONCTIONNE
		 * SELECT (FIND) -> FONCTIONNE
		 */
		HistoriquePOJO historique = new HistoriquePOJO();
		historique.setDateConnexion(datePourTester);
		historique.setUtilisateurPOJO(util);
		
		//HistoriquePOJO historique = historiqueDAO.find(1);
		//historique.setDateConnexion(new java.sql.Date(454));
		//System.out.println(historique.getDateConnexion().toString());
		//historiqueDAO.create(historique);
		//historiqueDAO.update(historique);
		//historiqueDAO.delete(historique);
		
		//COMMENTAIRE
		/** 
		 * CREATE -> FONCTIONNE
		 * UPDATE -> FONCTIONNE
		 * DELETE -> FONCTIONNE
		 * SELECT (FIND) -> FONCTIONNE
		 */
		CommentairePOJO commentaire = new CommentairePOJO();
		commentaire.setDateCommentaire(datePourTester);
		commentaire.setSujetPOJO(sujetDAO.find(1));
		commentaire.setTexte("commentaire qui va etre delete");
		
		//commentaire.setUtilisateurPOJO(utilisateurDAO.find(5));
		//commentaireDAO.create(commentaire);
		//CommentairePOJO commentaire = commentaireDAO.find(1);
		//commentaire.setTexte("Commentaire à update");
		//System.out.println(commentaire.getTexte());
		//commentaireDAO.update(commentaire);
		//commentaireDAO.delete(commentaire);
		
		/*UtilisateurPOJO util1 = new UtilisateurPOJO();
		util1.setPseudo("testpseudo");
		util1.setMotdepasse("testmdp");
		util1.setPrenom("testprenom");
		util1.setNom("testnom");
		//util1.setDateNaissance(datePourTester);
		util1.setType("admin");
		util1.setMail("testmail");
		
		UtilisateurPOJO util2 = new UtilisateurPOJO();
		util2.setPseudo("testpseudo");
		util2.setMotdepasse("testmdp");
		util2.setPrenom("testprenom");
		util2.setNom("testnom");
		//util2.setDateNaissance(datePourTester);
		util2.setType("admin");
		util2.setMail("testmail");
		
		/*if(util1.equals(util2))
			System.out.println("égal");
		else
			System.out.println("pas égal");*/
		
		/*for(UtilisateurPOJO util3 : utilisateurDAO.getList()){
			System.out.println(util3.getPseudo());
		}*/
		
		SujetPOJO sujetPOJO = new SujetPOJO();
		sujetPOJO.setDateSujet(datePourTester);
		sujetPOJO.setTitre("test");
		sujetPOJO.setSousCategoriePOJO(sousCatDAO.find(2));
		sujetPOJO.setUtilisateurPOJO(utilisateurDAO.find(1));
		//sujetDAO.create(sujetPOJO);
		String nomSousCategorie = "Football";
		Sujet suj = new Sujet();
		//ArrayList<Sujet> listSujet = sujet.getListSelonSousCategorie(nomSousCategorie);
		
		ArrayList<Sujet> listSuj = suj.getListSelonSousCategorie(nomSousCategorie);
		for (Sujet sujet2 : listSuj) {
			System.out.println(sujet2.getTitre());
		}
		
	}
}
