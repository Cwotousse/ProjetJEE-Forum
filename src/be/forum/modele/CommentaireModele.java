package be.forum.modele;

import java.sql.Date;
import java.util.ArrayList;
import java.util.stream.Collectors;

import be.forum.dao.DAOFactory;
import be.forum.pojo.Commentaire;

public class CommentaireModele {
	public ArrayList<Commentaire> getList(){
		return new DAOFactory().getCommentaireDAO().getList();
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
