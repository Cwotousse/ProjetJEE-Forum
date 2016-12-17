package be.forum.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.forum.metier.Sujet;

/**
 * Servlet implementation class AfficherSousCategorie
 */
@WebServlet("/AfficherSousCategorie")
public class AfficherSousCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// récupération de l’identifiant/login dans la requête
		String nomSousCategorie = request.getParameter("nomSousCategorie");
		int nbrPosts = 0;

		Sujet sujet = new Sujet();
		ArrayList<Sujet> listSujet = sujet.getListSelonSousCategorie(nomSousCategorie);
		nbrPosts = listSujet.size();
		
		// flux de sortie
		PrintWriter out = response.getWriter();
		if (listSujet.isEmpty() || listSujet.size() == 0){
			out.println("Il n'y a pas de posts pour cette sous-catégorie.");
		}
		else {
			//out.println(listSujet.get(0).getTitre());
			request.setAttribute("listeSujets", listSujet);
			response.sendRedirect("/ProjetJEE-Forum\\VUE\\sousCategorie.jsp");
			response.setContentType("text/html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
