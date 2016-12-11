import be.forum.dao.DAOFactory;
import be.forum.pojo.SousCategoriePOJO;
import be.forum.pojo.SujetPOJO;
import be.forum.pojo.UtilisateurPOJO;

public class Test {

	public static void main(String[] args) {
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
		UtilisateurPOJO testSprocCreateUser = new DAOFactory().getUtilisateurDAO().find(2);
		
		// Sous catégorie
		SousCategoriePOJO testSprocGetSousCategorie = new SousCategoriePOJO();
		testSprocGetSousCategorie = new DAOFactory().getSousCategorieDAO().find(2);
		
		// Sujet
		/*SujetPOJO testSprocCreateSujet = new SujetPOJO();
		testSprocCreateSujet.setDateSujet(new java.sql.Date(925214));
		testSprocCreateSujet.setSousCategoriePOJO(testSprocGetSousCategorie);
		testSprocCreateSujet.setTitre("Issou");
		testSprocCreateSujet.setUtilisateurPOJO(testSprocCreateUser);
		new DAOFactory().getSujetDAO().create(testSprocCreateSujet);*/
		
		/*SujetPOJO testSprocUpdateSujet = new DAOFactory().getSujetDAO().find(1);
		testSprocUpdateSujet.setTitre("MODIFIE");
		new DAOFactory().getSujetDAO().update(testSprocUpdateSujet);*/
		
		//System.out.println(new DAOFactory().getSujetDAO().find(1).getUtilisateurPOJO().getNom());
		
		SujetPOJO testSprocDeleteSujet = new DAOFactory().getSujetDAO().find(1);
		new DAOFactory().getSujetDAO().delete(testSprocDeleteSujet);
		
		// Commentaire
		/*CommentairePOJO testSprocCreate = new CommentairePOJO();
		testSprocCreate.setTexte("Commentaire");
		testSprocCreate.setDateCommentaire(new java.sql.Date(925214));
		testSprocCreate.setSujetPOJO(sujetPOJO);
		testSprocCreate.setUtilisateurPOJO(testSprocCreateUser);
		new DAOFactory().getCommentaireDAO().create(testSprocCreate);*/
		
		// Historique
		System.out.println("Finished");
	}

}
