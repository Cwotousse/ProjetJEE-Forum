package be.forum.pojo;

import java.io.Serializable;
import java.sql.Date;

public class Historique implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Variables
	 */
	private int 			idHistorique;
	private Utilisateur 	utilisateur;
	private Date 			dateConnexion;
	
	/**
	 * 
	 */
	public Historique(){}
	/**
	 * 
	 * @param idHistorique
	 * @param utilisateur
	 * @param dateConnexion
	 */
	public Historique(int idHistorique, Date dateConnexion, Utilisateur utilisateur)
	{
		this.idHistorique 		= idHistorique;
		this.utilisateur 		= utilisateur;
		this.dateConnexion 		= dateConnexion;
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
