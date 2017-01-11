package be.forum.modele;

import java.util.ArrayList;
import java.util.stream.Collectors;

import be.forum.dao.DAOFactory;
import be.forum.pojo.SousCategorie;
import be.forum.pojo.Sujet;
import be.forum.pojo.Utilisateur;

public class SujetModele {
	/**
	 * R�cup�re la liste des sujets
	 * @return liste des sujets
	 */
	public ArrayList<Sujet> getList() {
		return new DAOFactory().getSujetDAO().getList();
	}

	/**
	 * R�cup�re la liste des sujets d'une sous-cat�gorie
	 * @param sousCat
	 *            : nom de la sous-cat�gorie
	 * @return listSujetBySousCat
	 */
	public ArrayList<Sujet> getListSelonSousCategorie(String sousCat) {
		return this.getList().stream().filter(x -> x.getSousCategorie().getTitre().equals(sousCat))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * Filtre les sujet pour n'en n'avoir qu'un seul correspondant aux donn�es entr�e en param�tre
	 * @param titre
	 * @param dateSujet
	 * @return
	 */
	public Sujet getSujetSelonTitreEtDateSujet(String titre, java.sql.Date dateSujet) {
		return this.getList().stream().filter(x -> x.getTitre().equals(titre) && x.getDateSujet().equals(dateSujet))
				.findAny().orElse(null);
	}

	/**
	 * R�cup�re le dernier sujet en date (via l'ID max)
	 * @return
	 */
	public Sujet getDernierSujetCree() {
		return this.getList().stream().max((p1, p2) -> Integer.compare(p1.getID(), p2.getID())).get();
	}

	/**
	 * Cr�er un sujet, et v�rifie si il n'existe pas d�j�
	 * @param sousCategorie
	 * @param titre
	 * @param dateSujet
	 * @param utilisateur
	 * @return true si ajout ok sinon false
	 */
	public boolean creer(String titreSousCategorie, String titre, java.sql.Date dateSujet, Utilisateur utilisateur) {
		try {
			// V�rification champs vides fait en javascript
			Sujet sujet = new Sujet();

			// Il faut rechercher la sous-cat existant
			SousCategorieModele sousCategorieModele = new SousCategorieModele();
			SousCategorie sousCategorie = sousCategorieModele.getSousCat(titreSousCategorie);

			// On prend le premier �l�ment trouv� (il n'y en a qu'un seul
			// d'existant normalement)
			sujet.setSousCategorie(sousCategorie);
			sujet.setTitre(titre);
			sujet.setDateSujet(dateSujet);
			sujet.setUtilisateur(utilisateur);

			// V�rification du pseudo et du mail, si ils existent d�j� -> erreur
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
	 * Retourne un sujet par rapport � son ID
	 * @param id
	 * @return le sujet trouv�
	 */
	public Sujet find(int id){
		return new DAOFactory().getSujetDAO().find(id);
	}
}
