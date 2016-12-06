package be.forum.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.forum.dao.DAO;
import be.forum.dao.DAOFactory;
import be.forum.pojo.UtilisateurPOJO;

public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DAOFactory 					df 				= new DAOFactory();
		DAO<UtilisateurPOJO> 		utilisateurDAO 	= df.getUtilisateurDAO();
		ArrayList<UtilisateurPOJO>	listUtilisateur = utilisateurDAO.getList();
		
		// récupération de l’identifiant/login dans la requête
		String pseudo 		= request.getParameter("pseudo");
		// récupération du mot de passe dans la requête
		String motdepasse 	= request.getParameter("motdepasse");
		
		
		//Je filtre la liste en utilisant des lambdas
		//findAny -> si il trouve, il stock dans l'objet
		//orElse(null) -> si il trouve rien il stock null
		UtilisateurPOJO utilisateurTrouve = listUtilisateur
				.stream()
				.filter(x -> x.getPseudo().equals(pseudo) 
						&& x.getMotdepasse().equals(motdepasse))
				.findAny()
				.orElse(null);
				
		// flux de sortie
		PrintWriter out = response.getWriter();
		out.println(pseudo + " " + motdepasse);
		if(pseudo.equals("") || motdepasse.equals("")){
			out.println("Vous n'avez pas rempli les champs nécessaires.");
			//Je redirige vers une page
			//response.sendRedirect("\\..\\index.jsp"); 
		}

		if(utilisateurTrouve == null){
			out.println("Authentification incorrecte, mauvaise saisie des informations.");
			//response.sendRedirect("\\..\\index.jsp"); 
		} else {
			out.println("Authentification correcte, bienvenu(e) " + pseudo);
			HttpSession session=request.getSession();
			 //si pas de session, destruction et création d’une nouvelle 
			if(!session.isNew()) { 
				session.invalidate(); 
				session=request.getSession(); 
			}
		 	//stocker les paramètres de l’utilisateur dans la session 
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
