package be.forum.modele;

import be.forum.pojo.ActualitePOJO;

public class Actualite {
	private String 	titre;
	private String 	description;

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
	
	public Actualite() { }

	public Actualite(String titre, String description) {
		this.setTitre		(titre);
		this.setDescription	(description);
	}
	
	/**
	 * Constructeur qui convertit un objet ActualitePOJO en objet Actualite
	 * @param actualitePOJO
	 */
	public Actualite(ActualitePOJO actualitePOJO){
		this.setTitre		(actualitePOJO.getTitre());
		this.setDescription	(actualitePOJO.getDescription());
	}
}
