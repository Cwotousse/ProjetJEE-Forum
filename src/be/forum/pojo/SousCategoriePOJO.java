package be.forum.pojo;

import be.forum.modele.SousCategorie;

public class SousCategoriePOJO {
	private int idSousCategorie;
	private CategoriePOJO categoriePOJO;
	private String titre;
	
	public SousCategoriePOJO() { }

	public SousCategoriePOJO(int idSousCategorie, CategoriePOJO categoriePOJO, String titre) {
		this.setID(idSousCategorie);
		this.setCategoriePOJO(categoriePOJO);
		this.setTitre(titre);
	}
	
	/**
	 * Constructeur qui convertit un objet SousCategorie en objet SousCategoriePOJO
	 * @param sousCategorie
	 */
	public SousCategoriePOJO(SousCategorie sousCategorie){
		this.setCategoriePOJO	(new CategoriePOJO(sousCategorie.getCategorie()));
		this.setTitre			(sousCategorie.getTitre());
	}

	public int getID() {
		return idSousCategorie;
	}

	public void setID(int idSousCategorie) {
		this.idSousCategorie = idSousCategorie;
	}

	public CategoriePOJO getCategoriePOJO() {
		return categoriePOJO;
	}

	public void setCategoriePOJO(CategoriePOJO categoriePOJO) {
		this.categoriePOJO = categoriePOJO;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}	
	
	@Override
	public boolean equals(Object obj) {
		SousCategoriePOJO sousCategoriePOJO;
		// vérification si obj est null ou référence une instance d’une autre
		// classe
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		} else {
			sousCategoriePOJO = (SousCategoriePOJO) obj;
			if (sousCategoriePOJO.getTitre().equals(this.getTitre())
				//Grace aux méthodes equals surchargées, ceci fonctionnera
				&& sousCategoriePOJO.getCategoriePOJO().equals(this.getCategoriePOJO())) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public int hashCode() {
		return this.getTitre().hashCode() + this.getCategoriePOJO().hashCode();
	}
}
