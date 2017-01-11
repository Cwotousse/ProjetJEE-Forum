package be.forum.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.forum.modele.UtilisateurModele;
import be.forum.pojo.Utilisateur;

public class SupprimerUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ACCES_RESTREINT = "/restrained_access.jsp";
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pseudo = request.getParameter("pseudo-hidden");
		UtilisateurModele utilisateurModele = new UtilisateurModele();
		
		// Je récupère l'utilisateur grâce à son pseudo
		Utilisateur utilisateurSupprimé = new Utilisateur();
		utilisateurSupprimé = utilisateurModele.getUtilisateur(pseudo);
		
		//Récupère la session
		HttpSession session = request.getSession();
		if(!session.isNew()){
			Utilisateur utilisateurConnecté = (Utilisateur) session.getAttribute("utilisateur");
			//Si le type de l'utilisateur est admin et que son pseudo n'est pas lui-même
			if(utilisateurConnecté.getType().equals("Admin") 
					&& !(utilisateurConnecté.getPseudo().equals(utilisateurSupprimé.getPseudo()))){
				// Si l'utilisateur a supprimé existe bien, on peut le supprimer
				if (utilisateurSupprimé != null) {
					utilisateurModele.supprimer(utilisateurSupprimé);
					response.sendRedirect("/ProjetJEE-Forum/VUE" + ACCES_RESTREINT);
				}
			} else 
				request.setAttribute("error_message", "Vous n'avez pas les droits pour supprimer une personne.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/VUE/erreur.jsp");
				dispatcher.forward(request, response);
				response.setContentType("text/html");
		} else{
			request.setAttribute("error_message", "Vous n'êtes pas connecté.");
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
