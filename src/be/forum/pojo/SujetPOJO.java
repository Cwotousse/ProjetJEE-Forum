package be.forum.pojo;

import java.sql.Date;

import be.forum.modele.Sujet;

public class SujetPOJO {
	private int idSujet;
	private SousCategoriePOJO sousCategoriePOJO;
	private String titre;
	private Date dateSujet;
	private UtilisateurPOJO utilisateurPOJO;
	
	public SujetPOJO() { }

	public SujetPOJO(int idSujet, SousCategoriePOJO sousCategoriePOJO, String titre, Date dateSujet,
			UtilisateurPOJO utilisateurPOJO) {
		this.setID(idSujet);
		this.setSousCategoriePOJO(sousCategoriePOJO);
		this.setTitre(titre);
		this.setDateSujet(dateSujet);
		this.setUtilisateurPOJO(utilisateurPOJO);
	}
	
	/**
	 * Constructeur qui convertit un objet sujet en objet SujetPOJO
	 * @param sujet
	 */
	public SujetPOJO(Sujet sujet){
		this.setSousCategoriePOJO	(new SousCategoriePOJO(sujet.getSousCategorie()));
		this.setTitre				(sujet.getTitre());
		this.setDateSujet			(sujet.getDateSujet());
		this.setUtilisateurPOJO		(new UtilisateurPOJO(sujet.getUtilisateur()));
	}

	public int getID() {
		return idSujet;
	}

	public void setID(int idSujet) {
		this.idSujet = idSujet;
	}
	
	public SousCategoriePOJO getSousCategoriePOJO() {
		return sousCategoriePOJO;
	}

	public void setSousCategoriePOJO(SousCategoriePOJO sousCategoriePOJO) {
		this.sousCategoriePOJO = sousCategoriePOJO;
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

	public UtilisateurPOJO getUtilisateurPOJO() {
		return utilisateurPOJO;
	}

	public void setUtilisateurPOJO(UtilisateurPOJO utilisateurPOJO) {
		this.utilisateurPOJO = utilisateurPOJO;
	}

	@Override
	public boolean equals(Object obj) {
		SujetPOJO sujetPOJO;
		// vérification si obj est null ou référence une instance d’une autre
		// classe
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		} else {
			sujetPOJO = (SujetPOJO) obj;
			if (sujetPOJO.getTitre().equals(this.getTitre())
					&& sujetPOJO.getSousCategoriePOJO().equals(this.getSousCategoriePOJO())
					&& sujetPOJO.getDateSujet().equals(this.getDateSujet())
					&& sujetPOJO.getUtilisateurPOJO().equals(this.getUtilisateurPOJO())) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public int hashCode() {
		return this.getTitre().hashCode() 
				+ this.getSousCategoriePOJO().hashCode() 
				+ this.getDateSujet().hashCode() 
				+ this.getUtilisateurPOJO().hashCode();
	}
}
