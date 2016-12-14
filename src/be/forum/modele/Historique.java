package be.forum.modele;

import java.sql.Date;

import be.forum.pojo.HistoriquePOJO;

public class Historique {
	/**
	 * Variables
	 */
	private Utilisateur utilisateur;
	private Date 		dateConnexion;
	
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
	 * Constructeur qui convertit un objet HistoriquePOJO en objet Historique
	 * @param historiquePOJO
	 */
	public Historique(HistoriquePOJO historiquePOJO){
		this.setUtilisateur		(new Utilisateur(historiquePOJO.getUtilisateurPOJO()));
		this.setDateConnexion	(historiquePOJO.getDateConnexion());
	}
}
