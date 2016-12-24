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
		String pseudoModifi� = request.getParameter("form-username-edit");
		String motdepasseModifi� = request.getParameter("form-password-edit");
		String prenomModifi� = request.getParameter("form-first-name-edit");
		String nomModifi� = request.getParameter("form-last-name-edit");
		String dateNaissanceModifi� = request.getParameter("form-datenaissance-edit");
		String emailModifi� = request.getParameter("form-email-edit");
		String typeModifi� = request.getParameter("form-type-edit");
		
		PrintWriter out = response.getWriter();
		out.println("pseudo : " + pseudoModifi� + " " + motdepasseModifi� +" "+ prenomModifi� + " " + nomModifi� + " "+ dateNaissanceModifi� + " " + emailModifi� +" " + typeModifi�);
		
		UtilisateurModele utilisateurModele = new UtilisateurModele();
		//Je r�cup�re l'objet gr�ce � son pseudo pour avoir ses infos compl�tes (son id)
		Utilisateur utilisateurModifi� = utilisateurModele.getUtilisateur(pseudo);
		utilisateurModifi�.setPseudo(pseudoModifi�);
		utilisateurModifi�.setMotdepasse(motdepasseModifi�);
		utilisateurModifi�.setNom(nomModifi�);
		utilisateurModifi�.setPrenom(prenomModifi�);
		utilisateurModifi�.setMail(emailModifi�);
		utilisateurModifi�.setType(typeModifi�);
		
		out.println("id " + utilisateurModifi�.getID());

		// #TODO date incorrecte
		// Pour transformer une date util.date en sql.date
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date utilDateNaissance = null;
		try {
			utilDateNaissance = df.parse(dateNaissanceModifi�);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		java.sql.Date sqlDateNaissance = new java.sql.Date(utilDateNaissance.getTime());
		
		utilisateurModifi�.setDateNaissance(sqlDateNaissance);

		//m�thode de la classe mod�le pour modifier
		utilisateurModele.modifier(utilisateurModifi�);
		
       // RequestDispatcher dispatcher = request.getRequestDispatcher(ACCES_RESTREINT);
       // dispatcher.forward(request, response); 
		response.sendRedirect("/ProjetJEE-Forum/VUE" + ACCES_RESTREINT);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
