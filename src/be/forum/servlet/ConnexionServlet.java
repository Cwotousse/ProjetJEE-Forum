package be.forum.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.forum.modele.UtilisateurModele;
import be.forum.pojo.Utilisateur;

public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pseudo 		= request.getParameter("pseudo");
		String motdepasse 	= request.getParameter("motdepasse");

		UtilisateurModele modele = new UtilisateurModele();

		Utilisateur utilisateurConnecté = modele.connexion(pseudo, motdepasse);
		PrintWriter out = response.getWriter();
		out.println(pseudo + " " + motdepasse);
		if (pseudo.equals("") || motdepasse.equals("")) {
			out.println("Vous n'avez pas rempli les champs nécessaires.");
		}

		if (utilisateurConnecté == null) {
			out.println("Authentification incorrecte, mauvaise saisie des informations.");
		} else {
			out.println("Authentification correcte, bienvenu(e) " + pseudo);
			HttpSession session = request.getSession();
			// si pas de session, destruction et création d’une nouvelle
			if (!session.isNew()) {
				session.invalidate();
				session = request.getSession();
			}
			// stocker les paramètres de l’utilisateur dans la session
			// Je cherche l'utilisateur grâce à son pseudo et mot de passe et je
			// retourne toutes ses infos dans l'objet utilisateurConnecté
			utilisateurConnecté = modele.getList().stream()
					.filter(x -> x.getPseudo().equals(pseudo) && x.getMotdepasse().equals(motdepasse))
					.findAny()
					.orElse(null);

			// J'ajoute l'objet en faisant un "setAttribute()"
			session.setAttribute("utilisateur", utilisateurConnecté);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/VUE\\index.jsp");
	        dispatcher.forward(request, response); 
			response.setContentType("text/html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
