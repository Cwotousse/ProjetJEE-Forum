package be.forum.pojo;

public class SousCategorie {
	private int 		idSousCategorie;
	private Categorie 	categorie;
	private String 		titre;
	private String 		icone;
	
	public SousCategorie() { }

	public SousCategorie(int idSousCategorie, Categorie categorie, String titre, String icone) {
		this.setID(idSousCategorie);
		this.setCategorie(categorie);
		this.setTitre(titre);
		this.setIcone(icone);
	}
	
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
		// vérification si obj est null ou référence une instance d’une autre
		// classe
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		} else {
			sousCategorie = (SousCategorie) obj;
			if (sousCategorie.getTitre().equals(this.getTitre())
				//Grace aux méthodes equals surchargées, ceci fonctionnera
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
