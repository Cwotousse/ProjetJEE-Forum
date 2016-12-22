package be.forum.modele;

import java.util.ArrayList;

import be.forum.dao.DAOFactory;
import be.forum.pojo.Categorie;

public class CategorieModele {
	public ArrayList<Categorie> getList(){
		return new DAOFactory().getCategorieDAO().getList();
	}
}
