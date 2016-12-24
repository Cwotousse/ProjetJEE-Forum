package be.forum.modele;

import java.util.ArrayList;

import be.forum.dao.DAOFactory;
import be.forum.pojo.Utilisateur;

public class UtilisateurModele {
	/**
	 * Récupère la liste des utilisateurs
	 * @return listUtilisateur
	 */
	public ArrayList<Utilisateur> getList(){
		return new DAOFactory().getUtilisateurDAO().getList();
	}
	
	/**
	 * Recherche un utilisateur dans la liste grâce à son pseudo
	 * @param pseudo
	 * @return objet utilisateur trouvé sinon null
	 */
	public Utilisateur getUtilisateur(String pseudo){
		return this.getList().stream()
				.filter(x -> x.getPseudo().equals(pseudo))
				.findAny()
				.orElse(null);
	}
	/**
	 * Connecte l'utilisateur au site
	 * - Filtre une liste, en cherchant si un pseudo et un mot de passe
	 * correspondent à un élément de la liste.
	 * @return objet utilisateur avec toutes les infos, sinon renvoie null
	 */
	public Utilisateur connexion(String pseudo, String motdepasse){
		return this.getList()
				.stream()
				.filter(x -> x.getPseudo().equals(pseudo) 
						&& x.getMotdepasse().equals(motdepasse))
				.findAny()
				.orElse(null);
	}
	
	/**
	 * Ajoute un utilisateur dans la base de données, si son pseudo ou son mail n'existe pas déjà
	 * @param pseudo
	 * @param motdepasse
	 * @param nom
	 * @param prenom
	 * @param mail
	 * @param dateNaissance
	 * @return true si ok sinon false
	 */
	public boolean inscription(String pseudo, String motdepasse, String nom, String prenom, String mail, java.sql.Date dateNaissance){
		//Vérification champs vides fait en javascript
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setPseudo		(pseudo);
		utilisateur.setMotdepasse	(motdepasse);
		utilisateur.setNom			(nom);
		utilisateur.setPrenom		(prenom);
		utilisateur.setMail			(mail);
		utilisateur.setDateNaissance(dateNaissance);
		utilisateur.setType			(utilisateur.getClass().getSimpleName());
		
		//Vérification du pseudo et du mail, si ils existent déjà -> erreur
		//Renvoie true si la condition est positive sinon false
		if(this.getList()
				.stream()
				.anyMatch(x -> x.getPseudo().equals(pseudo) 
						|| x.getMail().equals(mail))) {
			//#TODO Faire une liste d'erreur
			System.out.println("Pseudo ou email déjà utilisé.");
			return false;
		} else {
			new DAOFactory().getUtilisateurDAO().create(utilisateur);
			return true;
		}
	}	
	
	/**
	 * Supprime un utilisateur
	 * @param utilisateur
	 */
	public void supprimer(Utilisateur utilisateur){
		new DAOFactory().getUtilisateurDAO().delete(utilisateur);
	}
	
	/**
	 * Modifie les informations d'un utilisateur
	 * @param utilisateur
	 */
	public void modifier(Utilisateur utilisateur){
		new DAOFactory().getUtilisateurDAO().update(utilisateur);
	}
}
