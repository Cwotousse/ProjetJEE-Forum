package be.forum.metier;

import java.util.ArrayList;
import java.util.stream.Collectors;

import be.forum.dao.DAO;
import be.forum.dao.DAOFactory;
import be.forum.pojo.SousCategoriePOJO;

public class SousCategorie {
	private Categorie 		categorie;
	private String 			titre;
	DAO<SousCategoriePOJO> 	sousCategorieDAO = new DAOFactory().getSousCategorieDAO();

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}	
	
	/**
	 * Constructeur vide
	 */
	public SousCategorie() { }
	
	/**
	 * Constructeur qui remplit un objet avec les attributs de la classe
	 * @param categorie
	 * @param titre
	 */

	public SousCategorie(Categorie categorie, String titre) {
		this.setCategorie(categorie);
		this.setTitre(titre);
	}
	
	/**
	 * Constructeur qui convertit un objet SousCategoriePOJO en objet SousCategorie
	 * @param sousCategoriePOJO
	 */
	public SousCategorie(SousCategoriePOJO sousCategoriePOJO){
		this.setCategorie	(new Categorie(sousCategoriePOJO.getCategoriePOJO()));
		this.setTitre		(sousCategoriePOJO.getTitre());
	}
	
	/*** Méthodes ***/
	
	/**
	 * Récupère la liste des sous-catégories
	 * @return listSousCategorie
	 */
	
	public ArrayList<SousCategorie> getList(){
		ArrayList<SousCategoriePOJO>	 listSousCategoriePOJO 	= sousCategorieDAO.getList();
		ArrayList<SousCategorie> 		 listSousCategorie 		= new ArrayList<SousCategorie>();
		
		for(int i = 0; i < listSousCategoriePOJO.size(); i++){
			SousCategorie sousCategorie = new SousCategorie(listSousCategoriePOJO.get(i));
			listSousCategorie.add	(sousCategorie);
		}
		return listSousCategorie;
	}
	
	public ArrayList<SousCategorie> getList(String titreCat){
		SousCategorie sousCategorie = new SousCategorie();
		ArrayList<SousCategorie> listSousCategorie 
					= sousCategorie.getList()
					.stream()
					.filter(x -> x.getCategorie().getTitre().equals(titreCat))
					.collect(Collectors.toCollection(ArrayList::new));
		return listSousCategorie;
	}
}
