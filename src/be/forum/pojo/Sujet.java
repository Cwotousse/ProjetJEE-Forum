package be.forum.pojo;

import java.sql.Date;

public class Sujet {
	private int 			idSujet;
	private SousCategorie 	sousCategorie;
	private String 			titre;
	private Date 			dateSujet;
	private Utilisateur 	utilisateur;
	
	public Sujet() { }

	public Sujet(int idSujet, SousCategorie sousCategorie, String titre, Date dateSujet,
			Utilisateur utilisateur) {
		this.setID(idSujet);
		this.setSousCategorie(sousCategorie);
		this.setTitre(titre);
		this.setDateSujet(dateSujet);
		this.setUtilisateur(utilisateur);
	}

	public int getID() {
		return idSujet;
	}

	public void setID(int idSujet) {
		this.idSujet = idSujet;
	}
	
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

	@Override
	public boolean equals(Object obj) {
		Sujet sujet;
		// v�rification si obj est null ou r�f�rence une instance d�une autre
		// classe
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		} else {
			sujet = (Sujet) obj;
			if (sujet.getTitre().equals(this.getTitre())
					&& sujet.getSousCategorie().equals(this.getSousCategorie())
					&& sujet.getDateSujet().equals(this.getDateSujet())
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
