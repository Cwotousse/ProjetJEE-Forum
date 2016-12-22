package be.forum.pojo;

public class Categorie {
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
		// vérification si obj est null ou référence une instance d’une autre
		// classe
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
