package be.forum.metier;

import java.sql.Date;
import java.util.ArrayList;

import be.forum.dao.DAO;
import be.forum.dao.DAOFactory;
import be.forum.pojo.HistoriquePOJO;

public class Historique {
	/**
	 * Variables
	 */
	private Utilisateur utilisateur;
	private Date 		dateConnexion;
	DAO<HistoriquePOJO> historiqueDAO = new DAOFactory().getHistoriqueDAO();
	/**
	 * Getters and setters
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public Date getDateConnexion() {
		return dateConnexion;
	}
	public void setDateConnexion(Date dateConnexion) {
		this.dateConnexion = dateConnexion;
	}
	
	public Historique(){}
	/**
	 * 
	 * @param Utilisateur
	 * @param dateConnexion
	 */
	public Historique(Utilisateur utilisateur, Date dateConnexion)
	{
		this.utilisateur = utilisateur;
		this.dateConnexion = dateConnexion;
	}
	
	/**
	 * Constructeur qui convertit un objet HistoriquePOJO en objet Historique
	 * @param historiquePOJO
	 */
	public Historique(HistoriquePOJO historiquePOJO){
		this.setUtilisateur		(new Utilisateur(historiquePOJO.getUtilisateurPOJO()));
		this.setDateConnexion	(historiquePOJO.getDateConnexion());
	}
	
	
	/**
	 * Récupère la liste des actualités
	 * @return listActualite
	 */
	public ArrayList<Historique> getList(){
		ArrayList<HistoriquePOJO>	 listHistoriquePOJO 	= historiqueDAO.getList();
		ArrayList<Historique> 		 listHistorique 		= new ArrayList<Historique>();
		
		for(int i = 0; i < listHistoriquePOJO.size(); i++){
			Historique historique = new Historique(listHistoriquePOJO.get(i));
			listHistorique.add	(historique);
		}
		return listHistorique;
	}
	
	@Override
	public boolean equals(Object obj) {
		Historique historique;
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		} else {
			historique = (Historique) obj;
			if (historique.getUtilisateur().equals(this.getUtilisateur())
					&& historique.getDateConnexion().equals(this.getDateConnexion())) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public int hashCode() {
		return this.getUtilisateur().hashCode() 
				+ this.getDateConnexion().hashCode();
	}
}
