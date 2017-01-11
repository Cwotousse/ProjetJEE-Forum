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
		
		// Je r�cup�re l'utilisateur gr�ce � son pseudo
		Utilisateur utilisateurSupprim� = new Utilisateur();
		utilisateurSupprim� = utilisateurModele.getUtilisateur(pseudo);
		
		//R�cup�re la session
		HttpSession session = request.getSession();
		if(!session.isNew()){
			Utilisateur utilisateurConnect� = (Utilisateur) session.getAttribute("utilisateur");
			//Si le type de l'utilisateur est admin et que son pseudo n'est pas lui-m�me
			if(utilisateurConnect�.getType().equals("Admin") 
					&& !(utilisateurConnect�.getPseudo().equals(utilisateurSupprim�.getPseudo()))){
				// Si l'utilisateur a supprim� existe bien, on peut le supprimer
				if (utilisateurSupprim� != null) {
					utilisateurModele.supprimer(utilisateurSupprim�);
					response.sendRedirect("/ProjetJEE-Forum/VUE" + ACCES_RESTREINT);
				}
			} else 
				request.setAttribute("error_message", "Vous n'avez pas les droits pour supprimer une personne.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/VUE/erreur.jsp");
				dispatcher.forward(request, response);
				response.setContentType("text/html");
		} else{
			request.setAttribute("error_message", "Vous n'�tes pas connect�.");
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
