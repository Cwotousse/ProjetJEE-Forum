package be.forum.metier;

import java.sql.Date;
import java.util.ArrayList;
import java.util.stream.Collectors;

import be.forum.dao.DAO;
import be.forum.dao.DAOFactory;
import be.forum.pojo.SujetPOJO;

public class Sujet {
	private SousCategorie 	sousCategorie;
	private String 			titre;
	private Date 			dateSujet;
	private Utilisateur 	utilisateur;
	DAO<SujetPOJO> sujetDAO = new DAOFactory().getSujetDAO();
	
	public SousCategorie getSousCategorie() {
		return sousCategorie;
	}

	public void setSousCategorie(SousCategorie sousCategorie) {
		this.sousCategorie = sousCategorie;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Date getDateSujet() {
		return dateSujet;
	}

	public void setDateSujet(Date dateSujet) {
		this.dateSujet = dateSujet;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}	
	
	public Sujet() { }

	public Sujet(SousCategorie sousCategorie, String titre, Date dateSujet,
			Utilisateur utilisateur) {
		this.setSousCategorie(sousCategorie);
		this.setTitre(titre);
		this.setDateSujet(dateSujet);
		this.setUtilisateur(utilisateur);
	}
	
	/**
	 * Constructeur qui convertit un objet SujetPOJO en objet Sujet
	 * @param SujetPOJO
	 */
	public Sujet(SujetPOJO sujetPOJO){
		this.setSousCategorie	(new SousCategorie(sujetPOJO.getSousCategoriePOJO()));
		this.setTitre			(sujetPOJO.getTitre());
		this.setDateSujet		(sujetPOJO.getDateSujet());
		this.setUtilisateur		(new Utilisateur(sujetPOJO.getUtilisateurPOJO()));
	}
	
	/**
	 * Méthodes
	 */
	
	public ArrayList<Sujet> getList(){
		ArrayList<SujetPOJO>	 listSujetPOJO 	= sujetDAO.getList();
		ArrayList<Sujet> 		 listSujet 		= new ArrayList<Sujet>();
		
		for(int i = 0; i < listSujetPOJO.size(); i++){
			Sujet sujet = new Sujet(listSujetPOJO.get(i));
			listSujet.add	(sujet);
		}
		return listSujet;
	}
	
	/**
	 * Récupère la liste des sujets d'une sous-catégorie
	 * @param sousCat : nom de la sous-catégorie
	 * @return listSujetBySousCat
	 */
	public ArrayList<Sujet> getListSelonSousCategorie(String sousCat){
		ArrayList<Sujet> listSujetBySousCat = this.getList()
					.stream()
					.filter(x -> x.getSousCategorie().getTitre().equals(sousCat))
					.collect(Collectors.toCollection(ArrayList::new));
		return listSujetBySousCat;
	}
	
	@Override
	public boolean equals(Object obj) {
		Sujet sujet;
		// vérification si obj est null ou référence une instance d’une autre
		// classe
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		} else {
			sujet = (Sujet) obj;
			if (sujet.getTitre().equals(this.getTitre())
					&& sujet.getSousCategorie().equals(this.getSousCategorie())
					&& sujet.getDateSujet()	.equals(this.getDateSujet())
					&& sujet.getUtilisateur().equals(this.getUtilisateur())) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public int hashCode() {
		return this.getTitre().hashCode() 
				+ this.getSousCategorie().hashCode() 
				+ this.getDateSujet().hashCode() 
				+ this.getUtilisateur().hashCode();
	}
}
