package be.forum.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.forum.modele.SujetModele;
import be.forum.pojo.SousCategorie;
import be.forum.pojo.Sujet;

public class AfficherSujetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomSousCategorie = request.getParameter("nomSousCategorie");

		SujetModele modele = new SujetModele();
		ArrayList<Sujet> listSujet = modele.getListSelonSousCategorie(nomSousCategorie);

		if (listSujet.isEmpty()){
			// Si c'est vide on ajoute un faux sujet pour juste afficher le bouton "ajouter sujet"
			Sujet sujetVide = new Sujet();
			SousCategorie sousCategorie = new SousCategorie();
			sousCategorie.setTitre(nomSousCategorie);
			sujetVide.setSousCategorie(sousCategorie);
			
			listSujet.add(sujetVide);
		} 
		
		request.setAttribute("listeSujets", listSujet);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/VUE/index.jsp");
        dispatcher.forward(request, response); 
		response.setContentType("text/html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
