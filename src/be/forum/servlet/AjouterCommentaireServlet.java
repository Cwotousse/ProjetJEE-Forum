package be.forum.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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

public class AjouterCommentaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AjouterCommentaireServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			// R�cuperation des donn�es de session
			HttpSession session = request.getSession();

			// r�cup�ration du commentaire dans la requ�te
			String textComm = request.getParameter("form-comment");

			// Il faut r�cup�rer la date et le titre du topic afin de rechercher son ID
			SujetModele sujM = new SujetModele();

			// Il faut changer le format de la date re�ue en param car celui-ci
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

			// On r�cup�re le sujet correspondant
			Sujet suj = sujM.getSujetSelonTitreEtDateSujet(request.getParameter("nom-sujet-hidden"), sqlDate);

			// Utilisateur
			Utilisateur util = (Utilisateur) session.getAttribute("utilisateur");

			if (!session.isNew()) {
				if (!textComm.equals("")) {
					CommentaireModele commentaire = new CommentaireModele();
					commentaire.creer(suj, textComm,
							new java.sql.Date(Calendar.getInstance().getTime().getTime()), util);
					//http://localhost:9090/ProjetJEE-Forum/displaycomments?nomSujet=Marseille%20nul&nomSousCategorie=Football&pseudoAuteur=Adista&dateSujet=2016-12-15
					String completeURL = request.getContextPath() + "/displaycomments" + 
							"?&nomSujet=" + suj.getTitre() +
							"&nomSousCategorie=" + suj.getSousCategorie().getTitre() +
							"&pseudoAuteur=" + util.getPseudo() + 
							"&dateSujet=" + sqlDate;
					out.println(completeURL);
					response.sendRedirect(completeURL);
				} else {
					request.setAttribute("error_message", "Le commentaire est vide.");
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
			out.println(e.getMessage());
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
