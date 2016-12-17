package be.forum.metier;

import java.util.ArrayList;

import be.forum.dao.DAO;
import be.forum.dao.DAOFactory;
import be.forum.pojo.CategoriePOJO;

public class Categorie {
	/**
	 * Variables
	 */
	private String titre;
	
	/**
	 * Constructeurs
	 */
	public Categorie(){}
	/**
	 * 
	 * @param idCategorie
	 * @param titre
	 */
	public Categorie(String titre){
		this.setTitre(titre);
	}
	
	/**
	 * Constructeur qui convertit un objet CategoriePOJO en objet Categorie
	 * @param categoriePOJO
	 */
	public Categorie(CategoriePOJO categoriePOJO){
		this.setTitre(categoriePOJO.getTitre());
	}

	
	/**
	 * Getters & Setters
	 */
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	/**
	 * Méthodes
	 */
	public ArrayList<CategoriePOJO> getList(){
		DAO<CategoriePOJO> 			categorieDAO 	= new DAOFactory().getCategorieDAO();
		ArrayList<CategoriePOJO> 	listCategorie	= categorieDAO.getList();
		return listCategorie;
	}
}
