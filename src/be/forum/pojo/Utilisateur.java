package be.forum.pojo;

import java.io.Serializable;
import java.util.Date;

public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Variables
	 */
	private int 	idUtilisateur;
	private String 	pseudo;
	private String 	motdepasse;
	private String 	nom;
	private String 	prenom;
	private Date 	dateNaissance;
	private String 	mail;
	private String 	typeUtilisateur;
	
	/**
	 * Getters and setters
	 */
	public int getID() {
		return idUtilisateur;
	}
	public void setID(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getMotdepasse() {
		return motdepasse;
	}
	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getType() {
		return typeUtilisateur;
	}
	public void setType(String type) {
		this.typeUtilisateur = type;
	}
	
	/**
	 * Constructeur vide
	 */
	
	public Utilisateur(){}
	/**
	 * Constructeur de la classe Utilisateur
	 * @param idUtilisateur
	 * @param pseudo
	 * @param motdepasse
	 * @param nom
	 * @param prenom
	 * @param dateNaissance
	 * @param mail
	 * @param type
	 */
	public Utilisateur(int idUtilisateur, String pseudo, String motdepasse, String nom, String prenom, Date dateNaissance, String mail, String typeUtilisateur) {
		this.idUtilisateur 		= idUtilisateur;
		this.pseudo				= pseudo;
		this.motdepasse 		= motdepasse;
		this.nom 				= nom;
		this.prenom 			= prenom;
		this.dateNaissance 		= dateNaissance;
		this.typeUtilisateur	= typeUtilisateur;
		this.mail 				= mail;
	}
	
	@Override
	public boolean equals(Object obj) {
		Utilisateur utilisateur;
		// vérification si obj est null ou référence une instance d’une autre
		// classe
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		} else {
			utilisateur = (Utilisateur) obj;
			if (utilisateur.getPseudo().equals(this.getPseudo())
					&& utilisateur.getMotdepasse().equals(this.getMotdepasse())
					&& utilisateur.getNom().equals(this.getNom())
					&& utilisateur.getPrenom().equals(this.getPrenom())
					//&& utilisateur.getDateNaissance().equals(this.getDateNaissance())
					&& utilisateur.getMail().equals(this.getMail())
					&& utilisateur.getType().equals(this.getType())) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public int hashCode() {
		return this.getPseudo().hashCode() + this.getMotdepasse().hashCode() + this.getNom().hashCode()
				+ this.getPrenom().hashCode() + this.getDateNaissance().hashCode() + this.getMail().hashCode()
				+ this.getType().hashCode();
	}
}
