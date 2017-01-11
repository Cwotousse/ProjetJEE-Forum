package be.forum.modele;

import java.util.ArrayList;

import be.forum.dao.DAOFactory;
import be.forum.pojo.Categorie;

public class CategorieModele {
	/**
	 * R�cup�re la liste des cat�gories
	 * @return liste des cat�gories
	 */
	public ArrayList<Categorie> getList(){
		return new DAOFactory().getCategorieDAO().getList();
	}
}
