package be.forum.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.forum.metier.Commentaire;

public class AfficherCommentaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SimpleDateFormat formatter 	= new SimpleDateFormat("dd/MM/yy");
		//?nomSujet=Issou&nomSousCategorie=18-25&pseudoAuteur=Cwotousse&dateSujet=1992-12-17
		String nomSujet 			= request.getParameter("nomSujet");
		String nomSousCategorie 	= request.getParameter("nomSousCategorie");
		String pseudoAuteur 		= request.getParameter("pseudoAuteur");
		String dateSujet			= request.getParameter("dateSujet");

		Commentaire commentaire 	= new Commentaire();
		PrintWriter out 			= response.getWriter();
		try {
			// Il faut changer le format de la date reçue en param car celui-ci est incorrect
			//1992-12-17
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date d = sdf.parse(dateSujet);
			//17/12/92
			sdf.applyPattern("dd/MM/yy");
			String nouveauFormatStringUtil = sdf.format(d);
			
			// Ensuite on parse cette date en date.sql
			java.util.Date parsedDate = formatter.parse(nouveauFormatStringUtil);
	        java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
	        
	        // On récupère la liste de commentaire filtré
	        ArrayList<Commentaire> listCommentaire = commentaire.getListCommentaireFiltred(nomSujet, nomSousCategorie, pseudoAuteur,sqlDate);

			int nbrCommentaire = listCommentaire.size();

			if (listCommentaire.isEmpty() || nbrCommentaire == 0) {
				out.println("Il n'y a pas de commentaires pour ce sujet.");
			} else {
				request.setAttribute("listeCommentaire", listCommentaire);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/VUE/afficherCommentaire.jsp");
				dispatcher.forward(request, response);
				response.setContentType("text/html");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
