package be.forum.pojo;

import be.forum.metier.SousCategorie;

public class SousCategoriePOJO {
	private int idSousCategorie;
	private CategoriePOJO categoriePOJO;
	private String titre;
	private String icone;
	
	public SousCategoriePOJO() { }

	public SousCategoriePOJO(int idSousCategorie, CategoriePOJO categoriePOJO, String titre, String icone) {
		this.setID(idSousCategorie);
		this.setCategoriePOJO(categoriePOJO);
		this.setTitre(titre);
		this.setIcone(icone);
	}
	
	/**
	 * Constructeur qui convertit un objet SousCategorie en objet SousCategoriePOJO
	 * @param sousCategorie
	 */
	public SousCategoriePOJO(SousCategorie sousCategorie){
		this.setCategoriePOJO	(new CategoriePOJO(sousCategorie.getCategorie()));
		this.setTitre			(sousCategorie.getTitre());
		this.setIcone			(sousCategorie.getIcone());
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
	
	/**
	 * @return the icone
	 */
	public String getIcone() {
		return icone;
	}

	/**
	 * @param icone the icone to set
	 */
	public void setIcone(String icone) {
		this.icone = icone;
	}

	@Override
	public boolean equals(Object obj) {
		SousCategoriePOJO sousCategoriePOJO;
		// v�rification si obj est null ou r�f�rence une instance d�une autre
		// classe
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		} else {
			sousCategoriePOJO = (SousCategoriePOJO) obj;
			if (sousCategoriePOJO.getTitre().equals(this.getTitre())
				//Grace aux m�thodes equals surcharg�es, ceci fonctionnera
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
