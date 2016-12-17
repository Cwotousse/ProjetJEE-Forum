package be.forum.pojo;

import java.util.Date;

import be.forum.metier.Utilisateur;

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
	public UtilisateurPOJO(int idUtilisateur, String pseudo, String motdepasse, String nom, String prenom, Date dateNaissance, String mail, String typeUtilisateur) {
		this.idUtilisateur 		= idUtilisateur;
		this.pseudo				= pseudo;
		this.motdepasse 		= motdepasse;
		this.nom 				= nom;
		this.prenom 			= prenom;
		this.dateNaissance 		= dateNaissance;
		this.typeUtilisateur	= typeUtilisateur;
		this.mail 				= mail;
	}
	
	public UtilisateurPOJO(Utilisateur utilisateur){
		this.setPseudo			(utilisateur.getPseudo());
		this.setMotdepasse		(utilisateur.getMotdepasse());
		this.setNom				(utilisateur.getNom());
		this.setPrenom			(utilisateur.getPrenom());
		this.setDateNaissance	(utilisateur.getDateNaissance());
		this.setMail			(utilisateur.getMail());
		this.setType			(utilisateur.getType());
	}
	
	//equals nécessite l'hashcode override
	// pas sur pour la date
	@Override
	public boolean equals(Object obj) {
		UtilisateurPOJO utilisateurPOJO;
		// vérification si obj est null ou référence une instance d’une autre
		// classe
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		} else {
			utilisateurPOJO = (UtilisateurPOJO) obj;
			if (utilisateurPOJO.getPseudo().equals(this.getPseudo())
					&& utilisateurPOJO.getMotdepasse().equals(this.getMotdepasse())
					&& utilisateurPOJO.getNom().equals(this.getNom())
					&& utilisateurPOJO.getPrenom().equals(this.getPrenom())
					//&& utilisateurPOJO.getDateNaissance().equals(this.getDateNaissance())
					&& utilisateurPOJO.getMail().equals(this.getMail())
					&& utilisateurPOJO.getType().equals(this.getType())) {
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
