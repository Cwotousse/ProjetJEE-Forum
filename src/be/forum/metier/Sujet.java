package be.forum.metier;

import java.sql.Date;
import java.util.ArrayList;

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
	
	/**
	 * Récupère la liste des sujets d'une catégorie donnée
	 * @return listSujets
	 */
	public ArrayList<Sujet> getListSelonSousCategorie(String sousCat){
		ArrayList<Sujet> listSujet = new ArrayList<Sujet>();
		try {
			ArrayList<SujetPOJO> listSujetPOJO = sujetDAO.getList();
			//#TODO Transformer le titre en son id et filter uniquement les sujets relatives à cette sous cat
			for(int i = 0; i < listSujetPOJO.size(); i++){
				Sujet sujet = new Sujet(listSujetPOJO.get(i));
				listSujet.add(sujet);
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		return listSujet;
	}
}
