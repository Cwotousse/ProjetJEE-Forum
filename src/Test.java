import java.util.Calendar;

import be.forum.dao.CategorieDAO;
import be.forum.dao.DAO;
import be.forum.dao.DAOFactory;
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
		
		/*DAOFactory 						df 					= new DAOFactory();
		DAO<SousCategoriePOJO> 			sousCategorieDAO 	= df.getSousCategorieDAO();
		ArrayList<SousCategoriePOJO> 	listSousCategorie	= sousCategorieDAO.getList();
		for (int i = 0; i < listSousCategorie.size(); i++) {
			System.out.println("<li><a href=\"#>" + listSousCategorie.get(i).getTitre() + "</a></li>");
		}
		System.out.println(listSousCategorie.size());
		DAO<UtilisateurPOJO> utilisateurPOJO 	= df.getUtilisateurDAO();
		System.out.println(utilisateurPOJO.find(2).getNom());
		System.out.println(sousCategorieDAO.find(1).getTitre());*/

		/*UtilisateurPOJO testSprocCreate = new UtilisateurPOJO();
		testSprocCreate.setPseudo("testSproc2");
		testSprocCreate.setMotdepasse("testdfsfMODIFIE");
		testSprocCreate.setNom("testPaulMODIFIE");
		testSprocCreate.setPrenom("testJeanMODIFIE");
		testSprocCreate.setType("ModérateurMODIFIE");
		testSprocCreate.setMail("test@test.beMODIFIE");
		testSprocCreate.setDateNaissance(new java.sql.Date(925214));
		new DAOFactory().getUtilisateurDAO().find(2);*/
		
		// User
		/*UtilisateurPOJO testSprocCreateUser = new UtilisateurPOJO();
		testSprocCreateUser.setPseudo("testSproc2");
		testSprocCreateUser.setMotdepasse("testdfsfMODIFIE");
		testSprocCreateUser.setNom("testPaulMODIFIE");
		testSprocCreateUser.setPrenom("testJeanMODIFIE");
		testSprocCreateUser.setType("ModérateurMODIFIE");
		testSprocCreateUser.setMail("test@test.beMODIFIE");
		testSprocCreateUser.setDateNaissance(new java.sql.Date(925214));
		new DAOFactory().getUtilisateurDAO().create(testSprocCreateUser);*/

		//FIND UTILISATEUR ID 2 = WORK
		//System.out.println(new DAOFactory().getUtilisateurDAO().find(2).getPseudo());
		//AFFICHER LE TITRE DU SUJET ID 1 = WORK
		//System.out.println(new DAOFactory().getSujetDAO().find(1).getTitre());
		//AFFICHER LE PSEUDO DE L'UTILISATEUR QUI A CREE LE SUJET ID 1 = WORK
		//System.out.println(new DAOFactory().getSujetDAO().find(1).getUtilisateurPOJO().getPseudo());

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
		
		
		//Sujet
		/**
		 * CREATE -> FONCTIONNE AVEC DES FIND
		 * UPDATE -> FONCTIONNE
		 * DELETE -> FONCTIONNE AVEC DES FILTERS
		 * SELECT (FIND) -> FONCTIONNE
		 */
		SujetPOJO sujet = new SujetPOJO();
		sujet.setTitre("sujet qui va etre delete");
		/*sujet.setSousCategoriePOJO(sousCatDAO.find(7));
		sujet.setDateSujet(new java.sql.Date(45754));
		sujet.setUtilisateurPOJO(utilisateurDAO.find(1));*/
		
		//sujetDAO.create(sujet);
		//sujetDAO.delete(sujet);

		/**
		 #TODO
		 FILTER SUR LES ID UN PEU CHELOU
		 SI ON ARRIVE A COMPARER LES OBJETS, EN GROS FAUDRAIT OVERRIDE COMPARETO OU LES EQUALS IDK
		 COMPARER CHAQUE VAR (COMME EN COURS) SAUF ID PCQ IL Y EN AURA UN QUI N'EN AURA PAS FORCEMENT VU QUON LE SET PAS 
		 
		 LE FILTER ICI 
		 JE SAIS PAS SI IL FONCTIONNERA QUAND ON LE METTRA EN OEUVRE DANS NOTRE SITE CAR IL NECESSITE DES ID
		 POUR LES == VOIR CE QUE JE DIS AU DESSUS
		 */
		
		/*SujetPOJO sujetTrouve = sujetDAO
				.getList()
				.stream()
				.filter(x -> x.getTitre().equals(sujet.getTitre())
						//&& x.getDateSujet() == sujet.getDateSujet()
						&& x.getSousCategoriePOJO().getID() == sujet.getSousCategoriePOJO().getID()
						&& x.getUtilisateurPOJO().getID() == sujet.getUtilisateurPOJO().getID()
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
	}
}
