package be.forum.modele;

import java.util.ArrayList;
import java.util.stream.Collectors;

import be.forum.dao.DAOFactory;
import be.forum.pojo.Sujet;

public class SujetModele {
	public ArrayList<Sujet> getList(){
		return new DAOFactory().getSujetDAO().getList();
	}
	
	/**
	 * R�cup�re la liste des sujets d'une sous-cat�gorie
	 * @param sousCat : nom de la sous-cat�gorie
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
}
