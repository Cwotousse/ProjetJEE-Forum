package be.forum.pojo;

import java.io.Serializable;
import java.sql.Date;

public class Commentaire implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Variables
	 */
	private int idCommentaire;
	private Sujet sujet;
	private Utilisateur utilisateur;
	private String texte;
	private Date dateCommentaire;

	/**
	 * Constructeurs
	 */
	public Commentaire() {
	}

	public Commentaire(int idCommentaire, Sujet sujet, String texte, Date dateCommentaire, Utilisateur utilisateur) {
		this.setID(idCommentaire);
		this.setSujet(sujet);
		this.setUtilisateur(utilisateur);
		this.setTexte(texte);
		this.setDateCommentaire(dateCommentaire);
	}

	public int getID() {
		return idCommentaire;
	}

	public void setID(int idCommentaire) {
		this.idCommentaire = idCommentaire;
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
