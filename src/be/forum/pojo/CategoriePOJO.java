package be.forum.pojo;

import be.forum.modele.Categorie;

public class CategoriePOJO {
	/**
	 * Variables
	 */
	private int idCategorie;
	private String titre;
	
	/**
	 * Constructeurs
	 */
	public CategoriePOJO(){}
	/**
	 * 
	 * @param idCategorie
	 * @param titre
	 */
	public CategoriePOJO(int idCategorie, String titre){
		this.setID(idCategorie);
		this.setTitre(titre);
	}
	
	/**
	 * Constructeur qui convertit un objet Categorie en objet CategoriePOJO
	 * @param categorie
	 */
	public CategoriePOJO(Categorie categorie){
		this.setTitre(categorie.getTitre());
	}

	
	/**
	 * Getters & Setters
	 */
	public int getID() {
		return idCategorie;
	}
	public void setID(int idCategorie) {
		this.idCategorie = idCategorie;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	@Override
	public boolean equals(Object obj) {
		CategoriePOJO categoriePOJO;
		// vérification si obj est null ou référence une instance d’une autre
		// classe
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		} else {
			categoriePOJO = (CategoriePOJO) obj;
			if (categoriePOJO.getTitre().equals(this.getTitre())) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public int hashCode() {
		return this.getTitre().hashCode();
	}
}
