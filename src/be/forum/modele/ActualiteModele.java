package be.forum.modele;

import java.util.ArrayList;

import be.forum.dao.DAOFactory;
import be.forum.pojo.Actualite;

public class ActualiteModele {
	/**
	 * Récupère la liste des actualités
	 * @return liste des actualites
	 */
	public ArrayList<Actualite> getList(){
		return new DAOFactory().getActualiteDAO().getList();
	}
}
