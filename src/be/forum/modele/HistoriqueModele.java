package be.forum.modele;

import java.util.ArrayList;

import be.forum.dao.DAOFactory;
import be.forum.pojo.Historique;

public class HistoriqueModele {
	/**
	 * R�cup�re la liste des actualit�s
	 * @return listActualite
	 */
	public ArrayList<Historique> getList(){
		return new DAOFactory().getHistoriqueDAO().getList();
	}
}
