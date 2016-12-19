package be.forum.metier;

import java.util.ArrayList;

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
	public ArrayList<Categorie> getList(){
		ArrayList<CategoriePOJO> 	listCategoriePOJO	= new DAOFactory().getCategorieDAO().getList();
		ArrayList<Categorie> 		listCategorie		= new ArrayList<Categorie>();
		for(int i = 0; i < listCategoriePOJO.size(); i++){
			Categorie categorie = new Categorie(listCategoriePOJO.get(i));
			listCategorie.add	(categorie);
		}
		return listCategorie;
	}
}
