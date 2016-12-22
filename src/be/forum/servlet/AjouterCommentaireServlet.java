package be.forum.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.forum.dao.DAO;
import be.forum.dao.DAOFactory;
import be.forum.metier.Commentaire;
import be.forum.metier.Sujet;
import be.forum.metier.Utilisateur;
import be.forum.pojo.SujetPOJO;
import be.forum.pojo.UtilisateurPOJO;

public class AjouterCommentaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AjouterCommentaireServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			// Récuperation des données de session
			HttpSession session = request.getSession();
			
			// récupération du commentaire dans la requête
			String textComm = request.getParameter("form-comment");

			Sujet suj = new Sujet();
			suj.setTitre("Marseille nul");
			suj = suj.getSujet();
			
			
			// Utilisateur
			Utilisateur util = (Utilisateur) session.getAttribute("utilisateur");
			
			

			if (!session.isNew()) {
				if (!textComm.equals("")) {
					Commentaire commentaire = new Commentaire();
					commentaire.setTexte(textComm);
					commentaire.setDateCommentaire(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
					commentaire.setSujet(suj);
					commentaire.setUtilisateur();

					//out.println("id util : " + commentaire.getUtilisateur().getPseudo());
					 commentaire.ajoutCommentaire();

				} else {
					out.println("Le commentaire est vide.");
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
