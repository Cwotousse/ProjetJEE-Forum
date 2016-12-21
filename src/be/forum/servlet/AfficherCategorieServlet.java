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

public class AfficherCategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nbrPosts = 0;
		//Je récupère la liste des catégories
		Categorie categorie = new Categorie();
		ArrayList<Categorie> listCategorie = categorie.getList();
		nbrPosts = listCategorie.size();
		
		PrintWriter out = response.getWriter();
		//out.println("size of ids list :"+listCategorie.size());
		//out.println(nomSousCategorie);
		if (listCategorie.isEmpty() 
				|| nbrPosts == 0){
			out.println("Il n'y a pas de posts pour ce sujet.");
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
