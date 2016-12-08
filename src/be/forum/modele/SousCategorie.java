package be.forum.modele;

import java.util.ArrayList;

import be.forum.dao.DAO;
import be.forum.dao.DAOFactory;
import be.forum.pojo.SousCategoriePOJO;

public class SousCategorie {
	private Categorie 	categorie;
	private String 		titre;
	
	public SousCategorie() { }

	public SousCategorie(Categorie categorie, String titre) {
		this.setCategorie(categorie);
		this.setTitre(titre);
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}	
	
	/**
	 * Méthodes
	 */
	public ArrayList<SousCategoriePOJO> getList(){
		DAO<SousCategoriePOJO> 			sousCategorieDAO 	= new DAOFactory().getSousCategorieDAO();
		ArrayList<SousCategoriePOJO> 	listSousCategorie	= sousCategorieDAO.getList();
		return listSousCategorie;
	}
}
