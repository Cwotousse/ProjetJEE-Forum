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
