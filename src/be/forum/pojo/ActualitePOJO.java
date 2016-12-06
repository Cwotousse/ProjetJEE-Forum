package be.forum.pojo;

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
