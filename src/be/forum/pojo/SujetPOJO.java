package be.forum.pojo;

import java.sql.Date;

public class SujetPOJO {
	private int idSujet;
	private SousCategoriePOJO sousCategoriePOJO;
	private String titre;
	private Date dateSujet;
	private UtilisateurPOJO utilisateurPOJO;
	
	public SujetPOJO() { }

	public SujetPOJO(int idSujet, SousCategoriePOJO sousCategoriePOJO, String titre, Date dateSujet,
			UtilisateurPOJO utilisateurPOJO) {
		this.setID(idSujet);
		this.sousCategoriePOJO = sousCategoriePOJO;
		this.setTitre(titre);
		this.setDateSujet(dateSujet);
		this.setUtilisateurPOJO(utilisateurPOJO);
	}

	public int getID() {
		return idSujet;
	}

	public void setID(int idSujet) {
		this.idSujet = idSujet;
	}
	
	public SousCategoriePOJO getSousCategoriePOJO() {
		return sousCategoriePOJO;
	}

	public void setSousCategoriePOJO(SousCategoriePOJO sousCategoriePOJO) {
		this.sousCategoriePOJO = sousCategoriePOJO;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Date getDateSujet() {
		return dateSujet;
	}

	public void setDateSujet(Date dateSujet) {
		this.dateSujet = dateSujet;
	}

	public UtilisateurPOJO getUtilisateurPOJO() {
		return utilisateurPOJO;
	}

	public void setUtilisateurPOJO(UtilisateurPOJO utilisateurPOJO) {
		this.utilisateurPOJO = utilisateurPOJO;
	}	
}
