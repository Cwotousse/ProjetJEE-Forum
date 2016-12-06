package be.forum.pojo;

import java.sql.Date;

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

	public CommentairePOJO(int idCommentaire, SujetPOJO sujetPOJO, UtilisateurPOJO utilisateurPOJO, String texte,
			Date dateCommentaire) {
		this.setID(idCommentaire);
		this.setSujetPOJO(sujetPOJO);
		this.setUtilisateurPOJO(utilisateurPOJO);
		this.setTexte(texte);
		this.setDateCommentaire(dateCommentaire);
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
