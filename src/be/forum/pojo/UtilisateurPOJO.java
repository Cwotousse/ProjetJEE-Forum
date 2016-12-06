package be.forum.pojo;

import java.util.Date;

public class UtilisateurPOJO {
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
	private String 	type;
	
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
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public UtilisateurPOJO(){}
	/**
	 * Constructeur de la classe UtilisateurPOJO
	 * @param idUtilisateur
	 * @param pseudo
	 * @param motdepasse
	 * @param nom
	 * @param prenom
	 * @param dateNaissance
	 * @param mail
	 * @param type
	 */
	public UtilisateurPOJO(int idUtilisateur, String pseudo, String motdepasse, String nom, String prenom, Date dateNaissance, String mail, String type) {
		this.idUtilisateur 	= idUtilisateur;
		this.pseudo			= pseudo;
		this.motdepasse 	= motdepasse;
		this.nom 			= nom;
		this.prenom 		= prenom;
		this.dateNaissance 	= dateNaissance;
		this.type 			= type;
		this.mail 			= mail;
	}
}
