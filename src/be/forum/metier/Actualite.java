package be.forum.metier;

import java.util.ArrayList;

import be.forum.dao.DAO;
import be.forum.dao.DAOFactory;
import be.forum.pojo.ActualitePOJO;

public class Actualite {
	private String 	titre;
	private String 	description;
	DAO<ActualitePOJO> actualiteDAO = new DAOFactory().getActualiteDAO();
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
	
	public Actualite() { }

	public Actualite(String titre, String description) {
		this.setTitre		(titre);
		this.setDescription	(description);
	}
	
	/**
	 * Constructeur qui convertit un objet ActualitePOJO en objet Actualite
	 * @param actualitePOJO
	 */
	public Actualite(ActualitePOJO actualitePOJO){
		this.setTitre		(actualitePOJO.getTitre());
		this.setDescription	(actualitePOJO.getDescription());
	}
	
	/**
	 * Récupère la liste des actualités
	 * @return listActualite
	 */
	public ArrayList<Actualite> getList(){
		ArrayList<ActualitePOJO>	 listActualitePOJO 	= actualiteDAO.getList();
		ArrayList<Actualite> 		 listActualite 		= new ArrayList<Actualite>();
		
		for(int i = 0; i < listActualitePOJO.size(); i++){
			Actualite actualite = new Actualite(listActualitePOJO.get(i));
			listActualite.add	(actualite);
		}
		return listActualite;
	}
	
	@Override
	public boolean equals(Object obj) {
		Actualite actualite;
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		} else {
			actualite = (Actualite) obj;
			if (actualite.getTitre().equals(this.getTitre())
					&& actualite.getDescription().equals(this.getDescription())) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public int hashCode() {
		return this.getTitre().hashCode()
				+ this.getDescription().hashCode();
	}
}
