package be.forum.pojo;

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
}
