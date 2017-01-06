package be.forum.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.forum.modele.HistoriqueModele;
import be.forum.modele.UtilisateurModele;
import be.forum.pojo.Utilisateur;

public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ACCES_PUBLIC = "/VUE\\index.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pseudo 		= request.getParameter("pseudo");
		String motdepasse 	= request.getParameter("motdepasse");

		UtilisateurModele utilisateurModele = new UtilisateurModele();
		Utilisateur utilisateurConnecté = utilisateurModele.connexion(pseudo, motdepasse);
		
		if (pseudo.equals("") || motdepasse.equals("")) {
			request.setAttribute("error_message", "Vous n'avez pas rempli les champs nécéssaires.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/VUE/erreur.jsp");
			dispatcher.forward(request, response);
			response.setContentType("text/html");
		}

		if (utilisateurConnecté == null) {
			request.setAttribute("error_message", "Authentification incorrecte, mauvaise saisie des informations.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/VUE/erreur.jsp");
			dispatcher.forward(request, response);
			response.setContentType("text/html");
		} else {
			HttpSession session = request.getSession();
			// si pas de session, destruction et création d’une nouvelle
			if (!session.isNew()) {
				session.invalidate();
				session = request.getSession();
			}
			
			// stocker les paramètres de l’utilisateur dans la session
			// Je cherche l'utilisateur grâce à son pseudo et mot de passe et je
			// retourne toutes ses infos dans l'objet utilisateurConnecté
			utilisateurConnecté = utilisateurModele.getList().stream()
					.filter(x -> x.getPseudo().equals(pseudo) && x.getMotdepasse().equals(motdepasse))
					.findAny()
					.orElse(null);
			//J'insère dans la table bd Historique
			HistoriqueModele historiqueModele = new HistoriqueModele();
			historiqueModele.creer(utilisateurConnecté);
			
			session.setAttribute("utilisateur", utilisateurConnecté);
		    RequestDispatcher dispatcher = request.getRequestDispatcher(ACCES_PUBLIC);
		    dispatcher.forward(request, response); 

			response.setContentType("text/html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
