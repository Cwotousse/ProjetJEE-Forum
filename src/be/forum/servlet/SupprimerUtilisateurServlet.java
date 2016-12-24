package be.forum.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.forum.modele.UtilisateurModele;
import be.forum.pojo.Utilisateur;

public class SupprimerUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ACCES_RESTREINT = "/restrained/restrained_access.jsp";
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// #TODO v�rifier session, si l'util est admin
		// #TODO v�rifier si le pseudo est pas lui meme alors erreur
		String pseudo = request.getParameter("pseudo-hidden");
		UtilisateurModele utilisateurModele = new UtilisateurModele();
		// Je r�cup�re l'utilisateur gr�ce � son pseudo
		Utilisateur utilisateurSupprim� = new Utilisateur();
		utilisateurSupprim� = utilisateurModele.getUtilisateur(pseudo);

		// Si il a bien trouv� l'utilisateur gr�ce � son pseudo, je le supprime
		if (utilisateurSupprim� != null)
			utilisateurModele.supprimer(utilisateurSupprim�);
		
        //RequestDispatcher dispatcher = request.getRequestDispatcher(ACCES_RESTREINT);
        //dispatcher.forward(request, response); 
	
		//#TODO changer le chemin en getContext etc
		response.sendRedirect("/ProjetJEE-Forum/VUE"+ACCES_RESTREINT);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
