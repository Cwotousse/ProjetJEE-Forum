package be.forum.pojo;

import java.io.Serializable;

public class Actualite implements Serializable {
	private static final long serialVersionUID = 1L;
	private int 	idActualite;
	private String 	titre;
	private String 	description;
	private String 	image;
	
	public Actualite() { }

	public Actualite(int idActualite, String titre, String description, String image) {
		this.setID(idActualite);
		this.setTitre(titre);
		this.setDescription(description);
		this.setImage(image);
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
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}	
}
