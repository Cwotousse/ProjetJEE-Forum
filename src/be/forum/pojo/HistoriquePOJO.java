package be.forum.pojo;

import java.sql.Date;

import be.forum.metier.Historique;

public class HistoriquePOJO {
	/**
	 * Variables
	 */
	private int 			idHistorique;
	private UtilisateurPOJO utilisateurPOJO;
	private Date 			dateConnexion;
	
	/**
	 * 
	 */
	public HistoriquePOJO(){}
	/**
	 * 
	 * @param idHistorique
	 * @param utilisateurPOJO
	 * @param dateConnexion
	 */
	public HistoriquePOJO(int idHistorique, Date dateConnexion, UtilisateurPOJO utilisateurPOJO)
	{
		this.idHistorique 		= idHistorique;
		this.utilisateurPOJO 	= utilisateurPOJO;
		this.dateConnexion 		= dateConnexion;
	}
	
	/**
	 * Constructeur qui convertit un objet Historique en objet HistoriquePOJO
	 * @param historique
	 */
	public HistoriquePOJO(Historique historique){
		this.setUtilisateurPOJO (new UtilisateurPOJO(historique.getUtilisateur()));
		this.setDateConnexion	(historique.getDateConnexion());
	}
	/**
	 * Getters and setters
	 */
	public int getID() {
		return idHistorique;
	}
	public void setID(int idHistorique) {
		this.idHistorique = idHistorique;
	}
	public UtilisateurPOJO getUtilisateurPOJO() {
		return utilisateurPOJO;
	}
	public void setUtilisateurPOJO(UtilisateurPOJO utilisateurPOJO) {
		this.utilisateurPOJO = utilisateurPOJO;
	}
	public Date getDateConnexion() {
		return dateConnexion;
	}
	public void setDateConnexion(Date dateConnexion) {
		this.dateConnexion = dateConnexion;
	}
}
