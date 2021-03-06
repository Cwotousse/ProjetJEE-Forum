package be.forum.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.forum.modele.UtilisateurModele;
import be.forum.pojo.Utilisateur;

public class AfficherListUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UtilisateurModele utilisateurModele = new UtilisateurModele();
		ArrayList<Utilisateur> listUtilisateur = utilisateurModele.getList();

		if (listUtilisateur.isEmpty()) {
			request.setAttribute("error_message", "Pas d'utilisateurs enregistré.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/VUE/erreur.jsp");
			dispatcher.forward(request, response);
			response.setContentType("text/html");
		} else {
			HttpSession session = request.getSession();
			if (!session.isNew()) {
				Utilisateur utilisateurConnecté = (Utilisateur) session.getAttribute("utilisateur");
				if (utilisateurConnecté.getType().equals("Admin")) {
					request.setAttribute("listUtilisateur", listUtilisateur);

					RequestDispatcher dispatcher = request
							.getRequestDispatcher(request.getContextPath() + "/restrained_access.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("error_message", "Vous n'êtes pas autorisé à visionner cette page.");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/VUE/erreur.jsp");
					dispatcher.forward(request, response);
					response.setContentType("text/html");
				}
			} else {
				request.setAttribute("error_message", "Pas de session en cours.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/VUE/erreur.jsp");
				dispatcher.forward(request, response);
				response.setContentType("text/html");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
