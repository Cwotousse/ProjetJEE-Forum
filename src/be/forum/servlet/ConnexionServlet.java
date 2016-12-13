package be.forum.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.forum.modele.Utilisateur;

public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Utilisateur utilisateur = new Utilisateur();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// r�cup�ration de l�identifiant/login dans la requ�te
		String pseudo 		= request.getParameter("pseudo");
		// r�cup�ration du mot de passe dans la requ�te
		String motdepasse 	= request.getParameter("motdepasse");
				
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setPseudo(pseudo);
		utilisateur.setMotdepasse(motdepasse);
		
		Utilisateur utilisateurConnect� = utilisateur.connexion();

		// flux de sortie
		PrintWriter out = response.getWriter();
		out.println(pseudo + " " + motdepasse);
		if(pseudo.equals("") || motdepasse.equals("")){
			out.println("Vous n'avez pas rempli les champs n�cessaires.");
			//Je redirige vers une page
			//response.sendRedirect("\\..\\index.jsp"); 
		}

		if(utilisateurConnect� == null){
			out.println("Authentification incorrecte, mauvaise saisie des informations.");
			//response.sendRedirect("\\..\\index.jsp"); 
		} else {
			out.println("Authentification correcte, bienvenu(e) " + pseudo);
			HttpSession session = request.getSession();
			 //si pas de session, destruction et cr�ation d�une nouvelle 
			if(!session.isNew()) { 
				session.invalidate(); 
				session=request.getSession(); 
			}
		 	//stocker les param�tres de l�utilisateur dans la session 
			session.setAttribute("pseudo", pseudo);
		 	session.setAttribute("motdepasse", motdepasse); 
			//response.sendRedirect("index.jsp"); 
			response.setContentType("text/html"); 
			//response.sendRedirect("\\..\\index.jsp"); 
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
