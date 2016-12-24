package be.forum.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.forum.modele.UtilisateurModele;
import be.forum.pojo.Utilisateur;

public class ModifierUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ACCES_RESTREINT = "/restrained/restrained_access.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pseudo = request.getParameter("pseudo-hidden");
		String pseudoModifié = request.getParameter("form-username-edit");
		String motdepasseModifié = request.getParameter("form-password-edit");
		String prenomModifié = request.getParameter("form-first-name-edit");
		String nomModifié = request.getParameter("form-last-name-edit");
		String dateNaissanceModifié = request.getParameter("form-datenaissance-edit");
		String emailModifié = request.getParameter("form-email-edit");
		String typeModifié = request.getParameter("form-type-edit");
		
		PrintWriter out = response.getWriter();
		out.println("pseudo : " + pseudoModifié + " " + motdepasseModifié +" "+ prenomModifié + " " + nomModifié + " "+ dateNaissanceModifié + " " + emailModifié +" " + typeModifié);
		
		UtilisateurModele utilisateurModele = new UtilisateurModele();
		//Je récupère l'objet grâce à son pseudo pour avoir ses infos complètes (son id)
		Utilisateur utilisateurModifié = utilisateurModele.getUtilisateur(pseudo);
		utilisateurModifié.setPseudo(pseudoModifié);
		utilisateurModifié.setMotdepasse(motdepasseModifié);
		utilisateurModifié.setNom(nomModifié);
		utilisateurModifié.setPrenom(prenomModifié);
		utilisateurModifié.setMail(emailModifié);
		utilisateurModifié.setType(typeModifié);
		
		out.println("id " + utilisateurModifié.getID());

		// #TODO date incorrecte
		// Pour transformer une date util.date en sql.date
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date utilDateNaissance = null;
		try {
			utilDateNaissance = df.parse(dateNaissanceModifié);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		java.sql.Date sqlDateNaissance = new java.sql.Date(utilDateNaissance.getTime());
		
		utilisateurModifié.setDateNaissance(sqlDateNaissance);

		//méthode de la classe modèle pour modifier
		utilisateurModele.modifier(utilisateurModifié);
		
       // RequestDispatcher dispatcher = request.getRequestDispatcher(ACCES_RESTREINT);
       // dispatcher.forward(request, response); 
		response.sendRedirect("/ProjetJEE-Forum/VUE" + ACCES_RESTREINT);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
