package be.forum.modele;

import java.util.ArrayList;
import java.util.Calendar;

import be.forum.dao.DAOFactory;
import be.forum.pojo.Historique;
import be.forum.pojo.Utilisateur;

public class HistoriqueModele {
	/**
	 * R�cup�re la liste des actualit�s
	 * @return listActualite
	 */
	public ArrayList<Historique> getList(){
		return new DAOFactory().getHistoriqueDAO().getList();
	}
	
	/**
	 * Cr�er un objet historique et l'ins�re dans la base de donn�es
	 * @param utilisateur
	 */
	public void creer(Utilisateur utilisateur){
		Historique historique = new Historique();
		//Je r�cup�re la date au moment de la connexion
		java.sql.Date dateConnexion = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		historique.setDateConnexion	(dateConnexion);
		historique.setUtilisateur	(utilisateur);
		new DAOFactory().getHistoriqueDAO().create(historique);
	}
}
