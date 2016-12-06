package be.forum.pojo;

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
	
}
