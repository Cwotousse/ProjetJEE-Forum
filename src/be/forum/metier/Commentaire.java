package be.forum.metier;

import java.sql.Date;
import java.util.ArrayList;
import java.util.stream.Collectors;

import be.forum.dao.DAO;
import be.forum.dao.DAOFactory;
import be.forum.pojo.CommentairePOJO;

public class Commentaire {
	/**
	 * Variables
	 */
	private Sujet 		sujet;
	private Utilisateur utilisateur;
	private String 		texte;
	private Date 		dateCommentaire;
	DAO<CommentairePOJO> commentaireDAO = new DAOFactory().getCommentaireDAO();
	/**
	 * Constructeurs
	 */
	public Commentaire() {
	}

	public Commentaire(Sujet sujet, Utilisateur utilisateur, String texte,
			Date dateCommentaire) {
		this.setSujet(sujet);
		this.setUtilisateur(utilisateur);
		this.setTexte(texte);
		this.setDateCommentaire(dateCommentaire);
	}
	
	/**
	 * Constructeur qui convertit un objet CommentairePOJO en objet Commentaire
	 * @param commentairePOJO
	 */
	public Commentaire(CommentairePOJO commentairePOJO){
		this.setSujet(new Sujet(commentairePOJO.getSujetPOJO()));
		this.setUtilisateur(new Utilisateur(commentairePOJO.getUtilisateurPOJO()));
		this.setTexte(commentairePOJO.getTexte());
		this.setDateCommentaire(commentairePOJO.getDateCommentaire());
	}
	
	public Sujet getSujet() {
		return sujet;
	}

	public void setSujet(Sujet sujet) {
		this.sujet = sujet;
	}
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public Date getDateCommentaire() {
		return dateCommentaire;
	}

	public void setDateCommentaire(Date dateCommentaire) {
		this.dateCommentaire = dateCommentaire;
	}
	
	/**
	 * Méthodes
	 */
	
	public ArrayList<Commentaire> getList(){
		ArrayList<CommentairePOJO>	 listCommentairePOJO 	= commentaireDAO.getList();
		ArrayList<Commentaire> 		 listCommentaire 		= new ArrayList<Commentaire>();
		
		
		for(int i = 0; i < listCommentairePOJO.size(); i++){
			Commentaire commentaire = new Commentaire(listCommentairePOJO.get(i));
			listCommentaire.add	(commentaire);
		}
		return listCommentaire;
	}
	
	/**
	 * Récupère la liste des commentaires d'une sous-catégorie
	 * @param sousCat : nom de la sous-catégorie
	 * @return listCommentaireFiltred
	 */
	public ArrayList<Commentaire> getListCommentaireFiltred(String nomSujet, String nomSousCategorie, String pseudoAuteur, Date dateSujet){
		ArrayList<Commentaire> listCommentaireFiltred = this.getList()
					.stream()
					.filter(x -> x.getSujet().getTitre().equals(nomSujet) 
							&& x.getSujet().getSousCategorie().getTitre().equals(nomSousCategorie)
							&& x.getUtilisateur().getPseudo().equals(pseudoAuteur)
							&& x.getSujet().getDateSujet().equals(dateSujet))
					.collect(Collectors.toCollection(ArrayList::new));
		
		return listCommentaireFiltred;
	}
	
	@Override
	public boolean equals(Object obj) {
		Commentaire commentaire;
		// vérification si obj est null ou référence une instance d’une autre
		// classe
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		} else {
			commentaire = (Commentaire) obj;
			if (commentaire.getSujet().equals(this.getSujet())
					&& commentaire.getTexte().equals(this.getTexte())
					&& commentaire.getDateCommentaire()	.equals(this.getDateCommentaire())
					&& commentaire.getUtilisateur().equals(this.getUtilisateur())) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public int hashCode() {
		return this.getSujet().hashCode() 
				+ this.getTexte().hashCode() 
				+ this.getDateCommentaire().hashCode() 
				+ this.getUtilisateur().hashCode();
	}
}
