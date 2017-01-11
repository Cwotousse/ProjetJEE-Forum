package be.forum.pojo;

import java.io.Serializable;

public class Categorie implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Variables
	 */
	private int idCategorie;
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
	public Categorie(int idCategorie, String titre){
		this.setID(idCategorie);
		this.setTitre(titre);
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
		Categorie categorie;
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		} else {
			categorie = (Categorie) obj;
			if (categorie.getTitre().equals(this.getTitre())) {
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
