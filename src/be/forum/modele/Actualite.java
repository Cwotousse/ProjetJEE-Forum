package be.forum.modele;

public class Actualite {
	private String 	titre;
	private String 	description;
	
	public Actualite() { }

	public Actualite(String titre, String description) {
		this.setTitre(titre);
		this.setDescription(description);
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
