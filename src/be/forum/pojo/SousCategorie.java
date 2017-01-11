package be.forum.pojo;

import java.io.Serializable;

public class SousCategorie implements Serializable {
	private static final long serialVersionUID = 1L;
	private int 		idSousCategorie;
	private Categorie 	categorie;
	private String 		titre;
	private String 		icone;
	
	/**
	 * Constructeur vide
	 */
	public SousCategorie() { }

	/**
	 * Constructeur
	 * @param idSousCategorie
	 * @param categorie
	 * @param titre
	 * @param icone
	 */
	public SousCategorie(int idSousCategorie, Categorie categorie, String titre, String icone) {
		this.setID(idSousCategorie);
		this.setCategorie(categorie);
		this.setTitre(titre);
		this.setIcone(icone);
	}
	
	/**
	 * Getters et setters
	 * @return
	 */
	public int getID() {
		return idSousCategorie;
	}

	public void setID(int idSousCategorie) {
		this.idSousCategorie = idSousCategorie;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}	
	
	public String getIcone() {
		return icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}

	@Override
	public boolean equals(Object obj) {
		SousCategorie sousCategorie;
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		} else {
			sousCategorie = (SousCategorie) obj;
			if (sousCategorie.getTitre().equals(this.getTitre())
				&& sousCategorie.getCategorie().equals(this.getCategorie())) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public int hashCode() {
		return this.getTitre().hashCode() + this.getCategorie().hashCode();
	}
}
