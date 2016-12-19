package be.forum.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.forum.metier.Categorie;
import be.forum.metier.SousCategorie;

public class OnLoadIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nbrPosts = 0;
		//Je récupère la liste des catégories
		Categorie categorie = new Categorie();
		ArrayList<Categorie> listCategorie = categorie.getList();
		nbrPosts = listCategorie.size();
		
		
		//Je récupère la liste des sous catégories selon la catégorie
		SousCategorie sousCategorie = new SousCategorie();
		//Sports
		ArrayList<SousCategorie> listSousCategorie0 = sousCategorie.getList(listCategorie.get(0).getTitre());
		//Jeux vidéos
		ArrayList<SousCategorie> listSousCategorie1 = sousCategorie.getList(listCategorie.get(1).getTitre());
		//Technologie
		ArrayList<SousCategorie> listSousCategorie2 = sousCategorie.getList(listCategorie.get(2).getTitre());
		//Blabla
		ArrayList<SousCategorie> listSousCategorie3 = sousCategorie.getList(listCategorie.get(3).getTitre());
		
		PrintWriter out = response.getWriter();
		//out.println("size of ids list :"+listCategorie.size());
		//out.println(nomSousCategorie);
		if (listCategorie.isEmpty() 
				|| nbrPosts == 0 
				|| listSousCategorie0.isEmpty() 
				|| listSousCategorie1.isEmpty() 
				|| listSousCategorie2.isEmpty() 
				|| listSousCategorie3.isEmpty()){
			out.println("Il n'y a pas de posts pour ce sujet.");
		} else {
			request.setAttribute("listCategorie", listCategorie);
			request.setAttribute("listSousCategorie0", listSousCategorie0);
			request.setAttribute("listSousCategorie1", listSousCategorie1);
			request.setAttribute("listSousCategorie2", listSousCategorie2);
			request.setAttribute("listSousCategorie3", listSousCategorie3);
	        RequestDispatcher dispatcher = request.getRequestDispatcher(request.getContextPath() + "/navbar1.jsp");
	        dispatcher.forward(request, response);
			response.setContentType("text/html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
