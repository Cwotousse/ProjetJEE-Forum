package be.forum.modele;

import java.util.ArrayList;
import java.util.stream.Collectors;

import be.forum.dao.DAOFactory;
import be.forum.pojo.SousCategorie;
import be.forum.pojo.Sujet;
import be.forum.pojo.Utilisateur;

public class SujetModele {
	/**
	 * Récupère la liste des sujets
	 * @return liste des sujets
	 */
	public ArrayList<Sujet> getList() {
		return new DAOFactory().getSujetDAO().getList();
	}

	/**
	 * Récupère la liste des sujets d'une sous-catégorie
	 * @param sousCat
	 *            : nom de la sous-catégorie
	 * @return listSujetBySousCat
	 */
	public ArrayList<Sujet> getListSelonSousCategorie(String sousCat) {
		return this.getList().stream().filter(x -> x.getSousCategorie().getTitre().equals(sousCat))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * Filtre les sujet pour n'en n'avoir qu'un seul correspondant aux données entrée en paramètre
	 * @param titre
	 * @param dateSujet
	 * @return
	 */
	public Sujet getSujetSelonTitreEtDateSujet(String titre, java.sql.Date dateSujet) {
		return this.getList().stream().filter(x -> x.getTitre().equals(titre) && x.getDateSujet().equals(dateSujet))
				.findAny().orElse(null);
	}

	/**
	 * Récupère le dernier sujet en date (via l'ID max)
	 * @return
	 */
	public Sujet getDernierSujetCree() {
		return this.getList().stream().max((p1, p2) -> Integer.compare(p1.getID(), p2.getID())).get();
	}

	/**
	 * Créer un sujet, et vérifie si il n'existe pas déjà
	 * @param sousCategorie
	 * @param titre
	 * @param dateSujet
	 * @param utilisateur
	 * @return true si ajout ok sinon false
	 */
	public boolean creer(String titreSousCategorie, String titre, java.sql.Date dateSujet, Utilisateur utilisateur) {
		try {
			// Vérification champs vides fait en javascript
			Sujet sujet = new Sujet();

			// Il faut rechercher la sous-cat existant
			SousCategorieModele sousCategorieModele = new SousCategorieModele();
			SousCategorie sousCategorie = sousCategorieModele.getSousCat(titreSousCategorie);

			// On prend le premier élément trouvé (il n'y en a qu'un seul
			// d'existant normalement)
			sujet.setSousCategorie(sousCategorie);
			sujet.setTitre(titre);
			sujet.setDateSujet(dateSujet);
			sujet.setUtilisateur(utilisateur);

			// Vérification du pseudo et du mail, si ils existent déjà -> erreur
			// Renvoie true si la condition est positive sinon false
			if (this.getList().stream().anyMatch(x -> x.getTitre().equals(titre))) {
				return false;
			} else {
				new DAOFactory().getSujetDAO().create(sujet);
				return true;
			}
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Suppression d'un sujet
	 * @param sujet
	 */
	public void supprimer(Sujet sujet) {
		new DAOFactory().getSujetDAO().delete(sujet);
	}
	
	/**
	 * Retourne un sujet par rapport à son ID
	 * @param id
	 * @return le sujet trouvé
	 */
	public Sujet find(int id){
		return new DAOFactory().getSujetDAO().find(id);
	}
}
