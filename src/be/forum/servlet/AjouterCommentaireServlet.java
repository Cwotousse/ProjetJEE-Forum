package be.forum.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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

			// Il faut récupérer la date et le titre du topic afin de rechercher son ID
			SujetModele sujM = new SujetModele();

			// Il faut changer le format de la date reçue en param car celui-ci
			// est incorrect
			// 1992-12-17
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date d = sdf.parse(request.getParameter("date-sujet-hidden"));
			// 17/12/92
			sdf.applyPattern("dd/MM/yy");
			String nouveauFormatStringUtil = sdf.format(d);

			// Ensuite on parse cette date en date.sql
			SimpleDateFormat formatter 	= new SimpleDateFormat("dd/MM/yy");
			java.util.Date parsedDate = formatter.parse(nouveauFormatStringUtil);
			java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());

			// On récupère le sujet correspondant
			Sujet suj = sujM.getSujetSelonTitreEtDateSujet(request.getParameter("nom-sujet-hidden"), sqlDate);

			// Utilisateur
			Utilisateur util = (Utilisateur) session.getAttribute("utilisateur");

			if (!session.isNew()) {
				if (!textComm.equals("")) {
					CommentaireModele commentaire = new CommentaireModele();
					commentaire.ajouterCommentaire(suj, textComm,
							new java.sql.Date(Calendar.getInstance().getTime().getTime()), util);
					response.sendRedirect("/VUE\\index.jsp");
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
