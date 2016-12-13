package be.forum.modele;

import java.util.ArrayList;
import java.util.Calendar;
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
	 * Constructeur qui convertit un objet d'UtilisateurPOJO en objet d'Utilisateur
	 * @param utilisateurPOJO
	 */
	public Utilisateur(UtilisateurPOJO utilisateurPOJO){
		this.setPseudo			(utilisateurPOJO.getPseudo());
		this.setMotdepasse		(utilisateurPOJO.getMotdepasse());
		this.setNom				(utilisateurPOJO.getNom());
		this.setPrenom			(utilisateurPOJO.getPrenom());
		this.setDateNaissance	(utilisateurPOJO.getDateNaissance());
		this.setMail			(utilisateurPOJO.getMail());
		this.setType			(utilisateurPOJO.getType());
	}
	
	/**
	 * Méthodes
	 */
	
	/**
	 * Récupère la liste des utilisateurs
	 * @return listUtilisateur
	 */
	public ArrayList<Utilisateur> getList(){
		ArrayList<UtilisateurPOJO>	 listUtilisateurPOJO 	= utilisateurDAO.getList();
		ArrayList<Utilisateur> 		 listUtilisateur 		= new ArrayList<Utilisateur>();
		
		for(int i = 0; i < listUtilisateurPOJO.size(); i++){
			Utilisateur utilisateur = new Utilisateur(listUtilisateurPOJO.get(i));
			listUtilisateur.add	(utilisateur);
		}
		return listUtilisateur;
	}
	
	/**
	 * Connecte l'utilisateur au site
	 * - Filtre une liste, en cherchant si un pseudo et un mot de passe
	 * correspondent à un élément de la liste.
	 * @return objet utilisateur avec toutes les infos, sinon renvoie null
	 */
	public Utilisateur connexion(){
		Utilisateur utilisateurTrouve = this.getList()
				.stream()
				.filter(x -> x.getPseudo().equals(this.pseudo) 
						&& x.getMotdepasse().equals(this.motdepasse))
				.findAny()
				.orElse(null);
		return utilisateurTrouve;
	}
	
	public boolean inscription(){
		
		// #TODO FAIRE UN CONVERTISSEUR METIER EN POJO
		UtilisateurPOJO utilisateurPOJO = new UtilisateurPOJO();
		utilisateurPOJO.setPseudo		(this.getPseudo());
		utilisateurPOJO.setMotdepasse	(this.getMotdepasse());
		utilisateurPOJO.setNom			(this.getNom());
		utilisateurPOJO.setPrenom		(this.getPrenom());
		utilisateurPOJO.setMail			(this.getMail());
		utilisateurPOJO.setType			(this.getClass().getSimpleName());

		// La date du jour, pour les tests
		java.sql.Date datePourTester = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		utilisateurPOJO.setDateNaissance(datePourTester);

		//Vérification du pseudo, si il existe déjà -> erreur
		/*if(this.getList().stream().anyMatch(x -> x.getPseudo().equals(this.getPseudo()))) {
			//Pas besoin de vérification des champs, car déjà fait en JS
			System.out.println("Le pseudo utilisé existe déjà!");
		else {
			utilisateurDAO.create(utilisateurPOJO);
		}*/
		
		//Renvoie true si la condition est positive sinon false
		boolean flag = this.getList().stream().anyMatch(x -> x.getPseudo().equals(this.getPseudo())) ? true : false; 
		
		//Si false -> pas de pseudo déjà existant alors je peux inscrire
		if(!flag)
			utilisateurDAO.create(utilisateurPOJO);
		return !flag;
	}	
}
