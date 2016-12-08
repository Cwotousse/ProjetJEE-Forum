package be.forum.modele;

import java.sql.Date;

import be.forum.modele.Sujet;
import be.forum.modele.Utilisateur;

public class Commentaire {
	/**
	 * Variables
	 */
	private Sujet 		sujet;
	private Utilisateur utilisateur;
	private String 		texte;
	private Date 		dateCommentaire;

	/**
	 * Constructeurs
	 */
	public Commentaire() {
	}

	public Commentaire(Sujet sujet, Utilisateur utilisateur, String texte,
			Date dateCommentaire) {
		this.setSujet(sujet);
		this.setUtilisateur(utilisateur);
		this.setTexte(texte);
		this.setDateCommentaire(dateCommentaire);
	}
	
	public Sujet getSujet() {
		return sujet;
	}

	public void setSujet(Sujet sujet) {
		this.sujet = sujet;
	}
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public Date getDateCommentaire() {
		return dateCommentaire;
	}

	public void setDateCommentaire(Date dateCommentaire) {
		this.dateCommentaire = dateCommentaire;
	}
}
