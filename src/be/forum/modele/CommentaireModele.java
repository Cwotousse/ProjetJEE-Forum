package be.forum.modele;

import java.sql.Date;
import java.util.ArrayList;
import java.util.stream.Collectors;

import be.forum.dao.DAOFactory;
import be.forum.pojo.Commentaire;
import be.forum.pojo.Sujet;
import be.forum.pojo.Utilisateur;

public class CommentaireModele {
	/**
	 * Récupère la liste des commentaires
	 * @return liste des commentaires
	 */
	public ArrayList<Commentaire> getList(){
		return new DAOFactory().getCommentaireDAO().getList();
	}
	
	/**
	 * Récupère le premier commentaire, donc la description du sujet
	 * @return
	 */
	public Commentaire getFirstComment(String nomSujet, String nomSousCategorie, Date dateSujet){
		return this.getListCommentaireFiltered(nomSujet, nomSousCategorie, dateSujet)
				.stream()
				.min((p1, p2) -> Integer.compare(p1.getID(), p2.getID())).get();
	}
	
	/**
	 * Créer un commentaire et l'insère dans la base de données
	 * @param sujet
	 * @param texte
	 * @param dateCommentaire
	 * @param utilisateur
	 */
	public void creer(Sujet sujet, String texte, Date dateCommentaire, Utilisateur utilisateur){
		//Vérification champs vides fait en javascript
		Commentaire commentaire = new Commentaire();
		commentaire.setSujet(sujet);
		commentaire.setTexte(texte);
		commentaire.setDateCommentaire(dateCommentaire);
		commentaire.setUtilisateur(utilisateur);
		new DAOFactory().getCommentaireDAO().create(commentaire);
	}
	
	/**
	 * Modifier un commentaire existant
	 * @param id
	 * @param texte
	 */
	public void modifier(int id, String texte){
		//Vérification champs vides fait en javascript
		Commentaire commentaire = new DAOFactory().getCommentaireDAO().find(id);
		// Il n'y a que le texte à modifier
		commentaire.setTexte(texte);
		new DAOFactory().getCommentaireDAO().update(commentaire);
	}
	
	/**
	 * Suppression du commentaire sur base de l'ID
	 * @param id
	 */
	public void supprimer(int id){
		new DAOFactory().getCommentaireDAO().delete(new DAOFactory().getCommentaireDAO().find(id));
	}
	
	/**
	 * Retourne un objet de type commentaire correspondant à un ID entré en paramètre
	 * @param id
	 * @return
	 */
	public static Commentaire getCommentaire(int id){
		return new DAOFactory().getCommentaireDAO().find(id);
	}
	
	/**
	 * Récupère la liste des commentaires d'une sous-catégorie
	 * @param sousCat : nom de la sous-catégorie
	 * @return listCommentaireFiltred
	 */
	public ArrayList<Commentaire> getListCommentaireFiltered(String nomSujet, String nomSousCategorie, Date dateSujet){
		ArrayList<Commentaire> listCommentaireFiltered = this.getList()
					.stream()
					.filter(x -> x.getSujet().getTitre().equals(nomSujet) 
							&& x.getSujet().getSousCategorie().getTitre().equals(nomSousCategorie)
							&& x.getSujet().getDateSujet().equals(dateSujet))
					.collect(Collectors.toCollection(ArrayList::new));
		
		return listCommentaireFiltered;
	}
}
