package be.forum.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.forum.modele.CommentaireModele;
import be.forum.modele.HistoriqueModele;
import be.forum.pojo.Commentaire;
import be.forum.pojo.Historique;

public class AfficherCommentaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SimpleDateFormat formatter 	= new SimpleDateFormat("dd/MM/yy");
		String nomSujet 			= request.getParameter("nomSujet");
		String nomSousCategorie 	= request.getParameter("nomSousCategorie");
		//String pseudoAuteur 		= request.getParameter("pseudoAuteur");
		String dateSujet			= request.getParameter("dateSujet");

		CommentaireModele modele 	= new CommentaireModele();
		
		HistoriqueModele 		historiqueModele = new HistoriqueModele();
		ArrayList<Historique> 	listHistorique 	 = historiqueModele.getList();
		try {
			// Il faut changer le format de la date re�ue en param car celui-ci est incorrect
			//1992-12-17
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date d = sdf.parse(dateSujet);
			//17/12/92
			sdf.applyPattern("dd/MM/yy");
			String nouveauFormatStringUtil = sdf.format(d);
			
			// Ensuite on parse cette date en date.sql
			java.util.Date parsedDate = formatter.parse(nouveauFormatStringUtil);
	        java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
	        
	        // On r�cup�re la liste de commentaire filtr�
	        ArrayList<Commentaire> listCommentaire = modele.getListCommentaireFiltered(nomSujet, nomSousCategorie, sqlDate);

			int nbrCommentaire = listCommentaire.size();

			if (listCommentaire.isEmpty() || nbrCommentaire == 0) {
				request.setAttribute("error_message", "Il n'y a pas de commentaire pour ce sujet.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/VUE/erreur.jsp");
				dispatcher.forward(request, response);
				response.setContentType("text/html");
			} else {
				request.setAttribute("listeCommentaire", listCommentaire);
				request.setAttribute("listHistorique", listHistorique);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/VUE/index.jsp");
				dispatcher.forward(request, response);
				response.setContentType("text/html");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
