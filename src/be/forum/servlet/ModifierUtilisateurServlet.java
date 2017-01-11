package be.forum.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.forum.modele.UtilisateurModele;
import be.forum.pojo.Utilisateur;

public class ModifierUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ACCES_RESTREINT = "/restrained_access.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pseudo = request.getParameter("pseudo-hidden");
		String pseudoModifié = request.getParameter("form-username-edit");
		String motdepasseModifié = request.getParameter("form-password-edit");
		String prenomModifié = request.getParameter("form-first-name-edit");
		String nomModifié = request.getParameter("form-last-name-edit");
		String emailModifié = request.getParameter("form-email-edit");
		String typeModifié = request.getParameter("form-type-edit");
		
		
		UtilisateurModele utilisateurModele = new UtilisateurModele();
		//Je récupère l'objet grâce à son pseudo pour avoir ses infos complètes (son id)
		Utilisateur utilisateurModifié = utilisateurModele.getUtilisateur(pseudo);
		utilisateurModifié.setPseudo(pseudoModifié);
		utilisateurModifié.setMotdepasse(motdepasseModifié);
		utilisateurModifié.setNom(nomModifié);
		utilisateurModifié.setPrenom(prenomModifié);
		utilisateurModifié.setMail(emailModifié);
		utilisateurModifié.setType(typeModifié);
		utilisateurModifié.setDateNaissance(new java.sql.Date(Calendar.getInstance().getTime().getTime()));

		//Récupère la session
		HttpSession session = request.getSession();
		if(!session.isNew()){
			Utilisateur utilisateurConnecté = (Utilisateur) session.getAttribute("utilisateur");
			//Si le type de l'utilisateur est admin
			if(utilisateurConnecté.getType().equals("Admin")){
				utilisateurModele.modifier(utilisateurModifié);
				response.sendRedirect("/ProjetJEE-Forum/VUE" + ACCES_RESTREINT);
			} else {
				//La personne n'est pas sensée arriver ici étant donné que la page n'est pas affichable si pas admin
				request.setAttribute("error_message", "Vous n'avez pas les droits pour modifier une personne.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/VUE/erreur.jsp");
				dispatcher.forward(request, response);
				response.setContentType("text/html");
			}
		} else{
			request.setAttribute("error_message", "Vous n'ête pas connecté.");
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
