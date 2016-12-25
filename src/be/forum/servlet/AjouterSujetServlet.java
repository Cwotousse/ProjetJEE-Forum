package be.forum.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

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
		// #TODO a faire
		PrintWriter out = response.getWriter();
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
						sujet = sujetModele.getDernierSujetCree();
						out.print(sujet.getTitre());
						// On cr�e le premier commentaire
						comModele.creer(sujet, textComm, sqlDate, utilisateur);
						// http://localhost:9090/ProjetJEE-Forum/displaycomments?nomSujet=Marseille%20nul&nomSousCategorie=Football&pseudoAuteur=Adista&dateSujet=2016-12-15
						String completeURL = request.getContextPath() + "/displaycomments" 
								+ "?&nomSujet=" + titreSujet
								+ "&nomSousCategorie=" + souscategorie
								+ "&pseudoAuteur="+ utilisateur.getPseudo() 
								+ "&dateSujet=" + sqlDate;
						out.println(completeURL);
						response.sendRedirect(completeURL);
					}
					else {
						out.println("Erreur lors de la cr�ation du sujet.");
					}
				} else {
					out.println("Un �l�ment n'a pas �t� correctement remplis.");
				}
			} else {
				out.println("Connectez-vous.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			out.println(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
