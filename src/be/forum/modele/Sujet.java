package be.forum.modele;

import java.sql.Date;

public class Sujet {
	private SousCategorie 	sousCategorie;
	private String 			titre;
	private Date 			dateSujet;
	private Utilisateur 	utilisateur;
	
	public Sujet() { }

	public Sujet(SousCategorie sousCategorie, String titre, Date dateSujet,
			Utilisateur utilisateur) {
		this.setSousCategorie(sousCategorie);
		this.setTitre(titre);
		this.setDateSujet(dateSujet);
		this.setUtilisateur(utilisateur);
	}

	public SousCategorie getSousCategorie() {
		return sousCategorie;
	}

	public void setSousCategorie(SousCategorie sousCategorie) {
		this.sousCategorie = sousCategorie;
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

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}	
}
