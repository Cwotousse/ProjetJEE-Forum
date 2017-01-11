package be.forum.modele;

import java.util.ArrayList;
import java.util.stream.Collectors;

import be.forum.dao.DAOFactory;
import be.forum.pojo.SousCategorie;

public class SousCategorieModele {
	/**
	 * R�cup�re la liste des sous-cat�gories
	 * @return listSousCategorie
	 */
	public ArrayList<SousCategorie> getList(){
		return new DAOFactory().getSousCategorieDAO().getList();
	}
	
	/**
	 * R�cup�re la liste des sous-cat�gories selon la cat�gorie
	 * @param titreCat
	 * @return liste filtr�e
	 */
	public ArrayList<SousCategorie> getList(String titreCat){
		return this.getList()
				.stream()
				.filter(x -> x.getCategorie().getTitre().equals(titreCat))
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	/**
	 * Recherche une sous cat�gorie gr�ce � son titre
	 * @param titreSousCat
	 * @return objet sous-cat�gorie trouv�
	 */
	
	public SousCategorie getSousCat(String titreSousCat){
		// R�cup�re le dernier �l�ment correspondant au filter
		return this.getList()
				.stream()
				.filter(x -> x.getTitre().equals(titreSousCat))
				.reduce((first, second) -> second).get();
	}
}
