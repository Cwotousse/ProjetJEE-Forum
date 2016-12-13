package be.forum.modele;

import java.util.ArrayList;
import java.util.Date;

import be.forum.dao.DAO;
import be.forum.dao.DAOFactory;
import be.forum.pojo.UtilisateurPOJO;

public class Utilisateur {
	private String 	pseudo;
	private String 	motdepasse;
	private String 	nom;
	private String 	prenom;
	private Date 	dateNaissance;
	private String 	mail;
	private String 	type;
	DAO<UtilisateurPOJO> utilisateurDAO = new DAOFactory().getUtilisateurDAO();
	
	/**
	 * Getters and setters
	 */
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
	
	public Utilisateur(){}
	/**
	 * Constructeur de la classe Utilisateur
	 * @param pseudo
	 * @param motdepasse
	 * @param nom
	 * @param prenom
	 * @param dateNaissance
	 * @param mail
	 * @param type
	 */
	public Utilisateur(String pseudo, String motdepasse, String nom, String prenom, Date dateNaissance, String mail, String type) {
		this.pseudo			= pseudo;
		this.motdepasse 	= motdepasse;
		this.nom 			= nom;
		this.prenom 		= prenom;
		this.dateNaissance 	= dateNaissance;
		this.mail 			= mail;
		this.type 			= type;
	}
	
	/**
	 * Méthodes
	 */
	
	// #TODO convertir en Utilisateur
	public UtilisateurPOJO connexion(String pseudo, String connexion){
		ArrayList<UtilisateurPOJO> listUtilisateur = utilisateurDAO.getList();
		UtilisateurPOJO utilisateurTrouve = listUtilisateur
				.stream()
				.filter(x -> x.getPseudo().equals(pseudo) 
						&& x.getMotdepasse().equals(motdepasse))
				.findAny()
				.orElse(null);
		return utilisateurTrouve;
	}
	
	public void inscription(UtilisateurPOJO utilisateur){
		if(utilisateur.getPseudo().equals("") 
				|| utilisateur.getMotdepasse().equals("")
				|| utilisateur.getNom().equals("")
				|| utilisateur.getPrenom().equals("")
				|| utilisateur.getDateNaissance().equals("")
				|| utilisateur.getType().equals("")
				|| utilisateur.getMail().equals(""))
			System.out.println("Champs vide");
		else
			utilisateurDAO.create(utilisateur);
	}
	
	
}
