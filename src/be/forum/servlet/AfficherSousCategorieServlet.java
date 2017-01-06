package be.forum.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.forum.modele.SousCategorieModele;
import be.forum.pojo.SousCategorie;

public class AfficherSousCategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomCategorie = request.getParameter("nomCategorie");
		int nbrPosts = 0;
		
		SousCategorieModele modele = new SousCategorieModele();
		ArrayList<SousCategorie> listSousCategorie = modele.getList(nomCategorie);
		nbrPosts = listSousCategorie.size();
		
		PrintWriter out = response.getWriter();
		out.println("size of ids list :"+ listSousCategorie.size());
		//out.println(nomSousCategorie);
		if (listSousCategorie.isEmpty() || nbrPosts == 0){
			request.setAttribute("error_message", "Element vide ou non trouvé.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/VUE/erreur.jsp");
			dispatcher.forward(request, response);
			response.setContentType("text/html");
		} else {
			request.setAttribute("listSousCategorie", listSousCategorie);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/VUE/index.jsp");
	        dispatcher.forward(request, response); 
			response.setContentType("text/html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
