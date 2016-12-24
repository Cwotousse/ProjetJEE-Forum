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
		// #TODO vérifier session, si l'util est admin
		// #TODO vérifier si le pseudo est pas lui meme alors erreur
		String pseudo = request.getParameter("pseudo-hidden");
		UtilisateurModele utilisateurModele = new UtilisateurModele();
		// Je récupère l'utilisateur grâce à son pseudo
		Utilisateur utilisateurSupprimé = new Utilisateur();
		utilisateurSupprimé = utilisateurModele.getUtilisateur(pseudo);

		// Si il a bien trouvé l'utilisateur grâce à son pseudo, je le supprime
		if (utilisateurSupprimé != null)
			utilisateurModele.supprimer(utilisateurSupprimé);
		
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
