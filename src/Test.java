import be.forum.dao.DAOFactory;
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

		UtilisateurPOJO testSprocCreate = new UtilisateurPOJO();
		testSprocCreate.setPseudo("testSproc2");
		testSprocCreate.setMotdepasse("testdfsfMODIFIE");
		testSprocCreate.setNom("testPaulMODIFIE");
		testSprocCreate.setPrenom("testJeanMODIFIE");
		testSprocCreate.setType("ModérateurMODIFIE");
		testSprocCreate.setMail("test@test.beMODIFIE");
		testSprocCreate.setDateNaissance(new java.sql.Date(925214));
		new DAOFactory().getUtilisateurDAO().find(2);
	}

}
