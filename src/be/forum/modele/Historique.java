package be.forum.modele;

import java.sql.Date;

public class Historique {
	/**
	 * Variables
	 */
	private Utilisateur utilisateur;
	private Date 		dateConnexion;
	
	/**
	 * 
	 */
	public Historique(){}
	/**
	 * 
	 * @param Utilisateur
	 * @param dateConnexion
	 */
	public Historique(Utilisateur utilisateur, Date dateConnexion)
	{
		this.utilisateur = utilisateur;
		this.dateConnexion = dateConnexion;
	}
	
	/**
	 * Getters and setters
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public Date getDateConnexion() {
		return dateConnexion;
	}
	public void setDateConnexion(Date dateConnexion) {
		this.dateConnexion = dateConnexion;
	}
}
