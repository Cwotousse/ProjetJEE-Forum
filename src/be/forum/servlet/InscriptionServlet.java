package be.forum.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.forum.modele.HistoriqueModele;
import be.forum.modele.UtilisateurModele;
import be.forum.pojo.Utilisateur;

public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String pseudo 		= request.getParameter("form-username");
				String motdepasse 	= request.getParameter("form-password-register");
				String nom 			= request.getParameter("form-last-name");
				String prenom 		= request.getParameter("form-first-name");
				String mail 		= request.getParameter("form-email");
				java.sql.Date datePourTester = new java.sql.Date(Calendar.getInstance().getTime().getTime());
				
				UtilisateurModele utilisateurModele = new UtilisateurModele();
				

				if(utilisateurModele.inscription(pseudo, motdepasse, nom, prenom, mail, datePourTester)) {
					HttpSession session = request.getSession();
					// si pas de session, destruction et création d’une nouvelle
					if (!session.isNew()) {
						session.invalidate();
						session = request.getSession();
					}
					
					Utilisateur utilisateurConnecté;
					utilisateurConnecté = utilisateurModele.getUtilisateur(pseudo);
					
					//J'insère dans la table bd Historique
					HistoriqueModele historiqueModele = new HistoriqueModele();
					historiqueModele.creer(utilisateurConnecté);
					
					session.setAttribute("utilisateur", utilisateurConnecté);
				    RequestDispatcher dispatcher = request.getRequestDispatcher("/VUE/index.jsp");
				    dispatcher.forward(request, response); 
					response.setContentType("text/html"); 
				} else{
					request.setAttribute("error_message", "Le pseudo saisi existe déjà.");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/VUE/erreur.jsp");
					dispatcher.forward(request, response);
					response.setContentType("text/html");
				}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
