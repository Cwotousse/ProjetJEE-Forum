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
		String pseudoModifi� = request.getParameter("form-username-edit");
		String motdepasseModifi� = request.getParameter("form-password-edit");
		String prenomModifi� = request.getParameter("form-first-name-edit");
		String nomModifi� = request.getParameter("form-last-name-edit");
		String emailModifi� = request.getParameter("form-email-edit");
		String typeModifi� = request.getParameter("form-type-edit");
		
		
		UtilisateurModele utilisateurModele = new UtilisateurModele();
		//Je r�cup�re l'objet gr�ce � son pseudo pour avoir ses infos compl�tes (son id)
		Utilisateur utilisateurModifi� = utilisateurModele.getUtilisateur(pseudo);
		utilisateurModifi�.setPseudo(pseudoModifi�);
		utilisateurModifi�.setMotdepasse(motdepasseModifi�);
		utilisateurModifi�.setNom(nomModifi�);
		utilisateurModifi�.setPrenom(prenomModifi�);
		utilisateurModifi�.setMail(emailModifi�);
		utilisateurModifi�.setType(typeModifi�);
		utilisateurModifi�.setDateNaissance(new java.sql.Date(Calendar.getInstance().getTime().getTime()));

		//R�cup�re la session
		HttpSession session = request.getSession();
		if(!session.isNew()){
			Utilisateur utilisateurConnect� = (Utilisateur) session.getAttribute("utilisateur");
			//Si le type de l'utilisateur est admin
			if(utilisateurConnect�.getType().equals("Admin")){
				utilisateurModele.modifier(utilisateurModifi�);
				response.sendRedirect("/ProjetJEE-Forum/VUE" + ACCES_RESTREINT);
			} else {
				//La personne n'est pas sens�e arriver ici �tant donn� que la page n'est pas affichable si pas admin
				request.setAttribute("error_message", "Vous n'avez pas les droits pour modifier une personne.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/VUE/erreur.jsp");
				dispatcher.forward(request, response);
				response.setContentType("text/html");
			}
		} else{
			request.setAttribute("error_message", "Vous n'�te pas connect�.");
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
