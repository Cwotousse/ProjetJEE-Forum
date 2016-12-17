package be.forum.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.forum.metier.Utilisateur;

public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// r�cup�ration de l�identifiant/login dans la requ�te
				String pseudo 		= request.getParameter("form-username");
				// r�cup�ration du mot de passe dans la requ�te
				String motdepasse 	= request.getParameter("form-password-register");
				String nom 			= request.getParameter("form-last-name");
				String prenom 		= request.getParameter("form-first-name");
				String mail 		= request.getParameter("form-email");
				
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setPseudo(pseudo);
				utilisateur.setMotdepasse(motdepasse);
				utilisateur.setNom(nom);
				utilisateur.setPrenom(prenom);
				utilisateur.setMail(mail);
				
				PrintWriter out = response.getWriter();
				if(utilisateur.inscription()) {
					//out.println("Inscription r�ussie!");
					//Je redirige la page
					response.sendRedirect("/ProjetJEE-Forum\\VUE\\index.jsp"); 
				}
				else
					out.println("Le pseudo que vous avez saisi existe d�j�.");
				
				response.setContentType("text/html"); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
