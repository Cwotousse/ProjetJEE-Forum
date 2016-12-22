package be.forum.modele;

import java.util.ArrayList;

import be.forum.dao.DAOFactory;
import be.forum.pojo.Historique;

public class HistoriqueModele {
	/**
	 * Récupère la liste des actualités
	 * @return listActualite
	 */
	public ArrayList<Historique> getList(){
		return new DAOFactory().getHistoriqueDAO().getList();
	}
}
