package be.forum.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.forum.modele.CommentaireModele;
import be.forum.modele.SujetModele;
import be.forum.pojo.Sujet;
import be.forum.pojo.Utilisateur;

public class AjouterSujetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// R�cuperation des donn�es de session
			HttpSession session = request.getSession();

			// r�cup�ration du commentaire dans la requ�te
			String souscategorie 	= request.getParameter("form-hidden-souscat");
			String titreSujet 		= request.getParameter("form-subject-title");
			String textComm 		= request.getParameter("form-subject-comment");

			if (!session.isNew()) {
				if (!textComm.equals("") && !titreSujet.equals("")) {
					// Il faut cr�er le sujet ET le premier commentaire
					SujetModele sujetModele 	= new SujetModele();
					Sujet sujet 				= new Sujet();
					CommentaireModele comModele = new CommentaireModele();
					java.sql.Date sqlDate 		= new java.sql.Date(Calendar.getInstance().getTime().getTime());

					// Utilisateur
					Utilisateur utilisateur 	= (Utilisateur) session.getAttribute("utilisateur");

					if(sujetModele.creer(souscategorie, titreSujet, sqlDate, utilisateur)){
						// On r�cup�re le sujet cr�� car on en a besoin pour le commentaire
						// On cr�e le premier commentaire
						comModele.creer(sujet, textComm, sqlDate, utilisateur);
						String completeURL = request.getContextPath() + "/displaycomments" 
								+ "?&nomSujet=" + titreSujet
								+ "&nomSousCategorie=" + souscategorie
								+ "&pseudoAuteur="+ utilisateur.getPseudo() 
								+ "&dateSujet=" + sqlDate;
						response.sendRedirect(completeURL);
					}
					else {
						request.setAttribute("error_message", "Erreur lors de la cr�ation du sujet.");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/VUE/erreur.jsp");
						dispatcher.forward(request, response);
						response.setContentType("text/html");
					}
				} else {
					request.setAttribute("error_message", "Un �l�ment n'a pas �t� correctement remplis.");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/VUE/erreur.jsp");
					dispatcher.forward(request, response);
					response.setContentType("text/html");
				}
			} else {
				request.setAttribute("error_message", "Connectez-vous.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/VUE/erreur.jsp");
				dispatcher.forward(request, response);
				response.setContentType("text/html");
			}
		} catch (Exception e) {
			e.getStackTrace();
			request.setAttribute("error_message", e.getMessage());
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
