package be.forum.modele;

import java.sql.Date;

import be.forum.pojo.SujetPOJO;

public class Sujet {
	private SousCategorie 	sousCategorie;
	private String 			titre;
	private Date 			dateSujet;
	private Utilisateur 	utilisateur;

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
	
	public Sujet() { }

	public Sujet(SousCategorie sousCategorie, String titre, Date dateSujet,
			Utilisateur utilisateur) {
		this.setSousCategorie(sousCategorie);
		this.setTitre(titre);
		this.setDateSujet(dateSujet);
		this.setUtilisateur(utilisateur);
	}
	
	/**
	 * Constructeur qui convertit un objet SujetPOJO en objet Sujet
	 * @param SujetPOJO
	 */
	public Sujet(SujetPOJO sujetPOJO){
		this.setSousCategorie	(new SousCategorie(sujetPOJO.getSousCategoriePOJO()));
		this.setTitre			(sujetPOJO.getTitre());
		this.setDateSujet		(sujetPOJO.getDateSujet());
		this.setUtilisateur		(new Utilisateur(sujetPOJO.getUtilisateurPOJO()));
	}
}
