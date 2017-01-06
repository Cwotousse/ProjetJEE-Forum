package be.forum.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.forum.modele.UtilisateurModele;

public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String pseudo 		= request.getParameter("form-username");
				String motdepasse 	= request.getParameter("form-password-register");
				String nom 			= request.getParameter("form-last-name");
				String prenom 		= request.getParameter("form-first-name");
				String mail 		= request.getParameter("form-email");
				//String dateNaissance= request.getParameter("form-birthdate");
				//Date de naissance
				java.sql.Date datePourTester = new java.sql.Date(Calendar.getInstance().getTime().getTime());
				
				UtilisateurModele utilisateurModele = new UtilisateurModele();
				
				if(utilisateurModele.inscription(pseudo, motdepasse, nom, prenom, mail, datePourTester))
					this.getServletContext().getRequestDispatcher("/VUE\\index.jsp").forward(request, response);
				else{
					request.setAttribute("error_message", "Le pseudo saisi existe déjà.");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/VUE/erreur.jsp");
					dispatcher.forward(request, response);
					response.setContentType("text/html");
				}
				response.setContentType("text/html"); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
