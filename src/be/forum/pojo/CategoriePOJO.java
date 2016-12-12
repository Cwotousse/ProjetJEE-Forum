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
	
	@Override
	public boolean equals(Object obj) {
		CategoriePOJO categoriePOJO;
		// v�rification si obj est null ou r�f�rence une instance d�une autre
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
