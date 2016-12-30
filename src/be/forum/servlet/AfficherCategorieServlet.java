package be.forum.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.forum.modele.CategorieModele;
import be.forum.pojo.Categorie;

public class AfficherCategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Je récupère la liste des catégories
		CategorieModele modele = new CategorieModele();
		ArrayList<Categorie> listCategorie = modele.getList();
		
		if (listCategorie.isEmpty()){
			request.setAttribute("error_message", "Il n'y a pas de post pour ce sujet.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/VUE/erreur.jsp");
			dispatcher.forward(request, response);
			response.setContentType("text/html");
		} else {
			request.setAttribute("listCategorie", listCategorie);
	        RequestDispatcher dispatcher = request.getRequestDispatcher(request.getContextPath() + "/navbar1.jsp");
	        dispatcher.forward(request, response);
			response.setContentType("text/html");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
