package be.forum.pojo;

import be.forum.metier.Actualite;

public class ActualitePOJO {
	private int 	idActualite;
	private String 	titre;
	private String 	description;
	
	public ActualitePOJO() { }

	public ActualitePOJO(int idActualite, String titre, String description) {
		this.setID(idActualite);
		this.setTitre(titre);
		this.setDescription(description);
	}
	
	/**
	 * Constructeur qui convertit un objet Actualite en objet ActualitePOJO
	 * @param actualite
	 */
	public ActualitePOJO(Actualite actualite){
		this.setTitre		(actualite.getTitre());
		this.setDescription	(actualite.getDescription());
	}

	public int getID() {
		return idActualite;
	}

	public void setID(int idActualite) {
		this.idActualite = idActualite;
	}

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
}
