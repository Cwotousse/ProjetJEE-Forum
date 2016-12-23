package be.forum.modele;

import java.util.ArrayList;
import java.util.stream.Collectors;

import be.forum.dao.DAOFactory;
import be.forum.pojo.SousCategorie;
import be.forum.pojo.Sujet;
import be.forum.pojo.Utilisateur;

public class SujetModele {
	public ArrayList<Sujet> getList(){
		return new DAOFactory().getSujetDAO().getList();
	}
	
	/**
	 * Récupère la liste des sujets d'une sous-catégorie
	 * @param sousCat : nom de la sous-catégorie
	 * @return listSujetBySousCat
	 */
	public ArrayList<Sujet> getListSelonSousCategorie(String sousCat){
		return this.getList()
				.stream()
				.filter(x -> x.getSousCategorie().getTitre().equals(sousCat))
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	public Sujet getSujetSelonTitreEtDateSujet(String titre, java.sql.Date dateSujet){
		return this.getList()
				.stream()
				.filter(x -> x.getTitre().equals(titre)
						&& x.getDateSujet().equals(dateSujet))
				.findAny()
				.orElse(null);	
	}
	
	/**
	 * Créer un sujet, et vérifie si il n'existe pas déjà
	 * @param sousCategorie
	 * @param titre
	 * @param dateSujet
	 * @param utilisateur
	 * @return true si ajout ok sinon false
	 */
	public boolean creer(SousCategorie sousCategorie, String titre, java.sql.Date dateSujet, Utilisateur utilisateur){
		//Vérification champs vides fait en javascript
		Sujet sujet = new Sujet();
		
		sujet.setSousCategorie(sousCategorie);
		sujet.setTitre(titre);
		sujet.setDateSujet(dateSujet);
		sujet.setUtilisateur(utilisateur);
		
		//Vérification du pseudo et du mail, si ils existent déjà -> erreur
		//Renvoie true si la condition est positive sinon false
		if(this.getList()
				.stream()
				.anyMatch(x -> x.getTitre().equals(titre))) {
			//#TODO Faire une liste d'erreur
			System.out.println("Sujet déjà existant.");
			return false;
		} else {
			new DAOFactory().getSujetDAO().create(sujet);
			return true;
		}
	}
}
