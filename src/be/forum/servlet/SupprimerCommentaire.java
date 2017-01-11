package be.forum.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.forum.modele.CommentaireModele;
import be.forum.modele.SujetModele;
import be.forum.pojo.Commentaire;

public class SupprimerCommentaire extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Récupère les données
			String idCommentaire = request.getParameter("id-commentaire-hidden");
			
				// Instancie un commentaireModele
				CommentaireModele commentaireModele = new CommentaireModele();
			
				// On récupère les données AVANT LA SUPPRESSION pour redidriger
				Commentaire com = CommentaireModele.getCommentaire(Integer.parseInt(idCommentaire));
				
				// On va comparer le commentaire supprimé, si c'est le premier (donc la description du sujet) alors on supprime le sujet
				//String nomSujet, String nomSousCategorie, Date dateSujet
				Commentaire premierCommentaire = commentaireModele.
						getFirstComment(com.getSujet().getTitre(), com.getSujet().getSousCategorie().getTitre(), com.getSujet().getDateSujet());
				
				if(premierCommentaire.getID() == com.getID()){
					// On supprime le suje, tout ses commentaires et on redirige sur la page de la sous cat
					// On récupère la liste des commentaires pour ce sujet
					ArrayList<Commentaire> listeCommSujet = commentaireModele.
							getListCommentaireFiltered(com.getSujet().getTitre(), com.getSujet().getSousCategorie().getTitre(), com.getSujet().getDateSujet());
					// Suppression en boucle
					for(Commentaire cm : listeCommSujet){
						commentaireModele.supprimer(cm.getID());
					}
					
					// Suppression du sujet
					SujetModele sujetModele = new SujetModele();
					sujetModele.supprimer(sujetModele.find(com.getSujet().getID()));
					
					// Construction de l'URL de retour
					//http://localhost:9090/ProjetJEE-Forum/displaysubjects?nomSousCategorie=Football
					String completeURL = request.getContextPath() + "/displaysubjects" + 
							"?&nomSousCategorie=" + com.getSujet().getSousCategorie().getTitre();
				
					// Redirige
					response.sendRedirect(completeURL);
				}
				else{
					// On ne supprime uniquement que le commentaire sélectionné
					// Modifie le commentaire via le nouveau texte et l'id du commentaire
					commentaireModele.supprimer(Integer.parseInt(idCommentaire));
					
					// Construction de l'URL de retour
					String completeURL = request.getContextPath() + "/displaycomments" + 
							"?&nomSujet=" + com.getSujet().getTitre() +
							"&nomSousCategorie=" + com.getSujet().getSousCategorie().getTitre() +
							"&pseudoAuteur=" + com.getUtilisateur().getPseudo() + 
							"&dateSujet=" + com.getSujet().getDateSujet();
					// Redirige
					response.sendRedirect(completeURL);
				}
				
		} catch (Exception e) {
			e.getStackTrace();
			request.setAttribute("error_message", "Suppression non effectuée.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/VUE/erreur.jsp");
			dispatcher.forward(request, response);
			response.setContentType("text/html");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
