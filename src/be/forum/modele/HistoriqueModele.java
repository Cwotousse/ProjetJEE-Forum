package be.forum.modele;

import java.util.ArrayList;
import java.util.Calendar;

import be.forum.dao.DAOFactory;
import be.forum.pojo.Historique;
import be.forum.pojo.Utilisateur;

public class HistoriqueModele {
	/**
	 * Récupère la liste des actualités
	 * @return listActualite
	 */
	public ArrayList<Historique> getList(){
		return new DAOFactory().getHistoriqueDAO().getList();
	}
	
	/**
	 * Créer un objet historique et l'insère dans la base de données
	 * @param utilisateur
	 */
	public void creer(Utilisateur utilisateur){
		Historique historique = new Historique();
		//Je récupère la date au moment de la connexion
		java.sql.Date dateConnexion = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		historique.setDateConnexion	(dateConnexion);
		historique.setUtilisateur	(utilisateur);
		new DAOFactory().getHistoriqueDAO().create(historique);
	}
}
