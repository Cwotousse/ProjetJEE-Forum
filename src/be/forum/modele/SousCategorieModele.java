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
	
	public ArrayList<SousCategorie> getList(String titreCat){
		return this.getList()
				.stream()
				.filter(x -> x.getCategorie().getTitre().equals(titreCat))
				.collect(Collectors.toCollection(ArrayList::new));
	}
}
