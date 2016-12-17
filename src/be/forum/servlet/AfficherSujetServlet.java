package be.forum.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.forum.metier.Sujet;

public class AfficherSujetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomSousCategorie = request.getParameter("nomSousCategorie");
		int nbrPosts = 0;

		Sujet sujet = new Sujet();
		ArrayList<Sujet> listSujet = sujet.getListSelonSousCategorie(nomSousCategorie);
		nbrPosts = listSujet.size();
		
		PrintWriter out = response.getWriter();
		out.println("size of ids list :"+listSujet.size());
		//out.println(nomSousCategorie);
		if (listSujet.isEmpty() || nbrPosts == 0){
			out.println("Il n'y a pas de posts pour cette sous-catégorie.");
		}
		else {
			//out.println(listSujet.get(0).getTitre());
			request.setAttribute("listeSujets", listSujet);
			//you redirect the page instead of forward it. 
			//response.sendRedirect("/ProjetJEE-Forum\\VUE\\sousCategorie.jsp");

	        RequestDispatcher dispatcher = request.getRequestDispatcher("/VUE/sousCategorie.jsp");
	        dispatcher.forward(request, response);

	        
			response.setContentType("text/html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
