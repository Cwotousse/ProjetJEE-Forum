package be.forum.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.forum.modele.UtilisateurModele;
import be.forum.pojo.Utilisateur;

public class AfficherListUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurModele utilisateurModele = new UtilisateurModele();
		ArrayList<Utilisateur> listUtilisateur = utilisateurModele.getList();
		
		PrintWriter out = response.getWriter();
		if (listUtilisateur.isEmpty()){
			out.println("empty.");
		} else {
			HttpSession session = request.getSession();
			if(!session.isNew()){
				Utilisateur utilisateurConnecté = (Utilisateur) session.getAttribute("utilisateur");
				if(utilisateurConnecté.getType().equals("Admin")){
					request.setAttribute("listUtilisateur", listUtilisateur);

			        RequestDispatcher dispatcher = request.getRequestDispatcher(request.getContextPath() + "/restrained_access.jsp");
			        dispatcher.forward(request, response); 
				} else 
					out.println("vous n'êtes pas autorisé à voir cette page.");
			} else
				out.println("pas de session en cours.");	
		}
		response.setContentType("text/html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
