package be.forum.pojo;

import java.sql.Date;

import be.forum.metier.Commentaire;

public class CommentairePOJO {
	/**
	 * Variables
	 */
	private int idCommentaire;
	private SujetPOJO sujetPOJO;
	private UtilisateurPOJO utilisateurPOJO;
	private String texte;
	private Date dateCommentaire;

	/**
	 * Constructeurs
	 */
	public CommentairePOJO() {
	}

	public CommentairePOJO(int idCommentaire, SujetPOJO sujetPOJO, String texte, Date dateCommentaire, UtilisateurPOJO utilisateurPOJO) {
		this.setID(idCommentaire);
		this.setSujetPOJO(sujetPOJO);
		this.setUtilisateurPOJO(utilisateurPOJO);
		this.setTexte(texte);
		this.setDateCommentaire(dateCommentaire);
	}
	
	/**
	 * Constructeur qui convertit un objet Commentaire en objet CommentairePOJO
	 * @param commentaire
	 */
	public CommentairePOJO(Commentaire commentaire){
		this.setSujetPOJO(new SujetPOJO(commentaire.getSujet()));
		this.setUtilisateurPOJO(new UtilisateurPOJO(commentaire.getUtilisateur()));
		this.setTexte(commentaire.getTexte());
		this.setDateCommentaire(commentaire.getDateCommentaire());
	}

	public int getID() {
		return idCommentaire;
	}

	public void setID(int idCommentaire) {
		this.idCommentaire = idCommentaire;
	}
	
	public SujetPOJO getSujetPOJO() {
		return sujetPOJO;
	}

	public void setSujetPOJO(SujetPOJO sujetPOJO) {
		this.sujetPOJO = sujetPOJO;
	}
	
	public UtilisateurPOJO getUtilisateurPOJO() {
		return utilisateurPOJO;
	}

	public void setUtilisateurPOJO(UtilisateurPOJO utilisateurPOJO) {
		this.utilisateurPOJO = utilisateurPOJO;
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
