package be.forum.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.forum.modele.ActualiteModele;
import be.forum.pojo.Actualite;

public class AfficherActualiteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ACCES_PUBLIC = "/VUE\\index.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActualiteModele actualiteModele = new ActualiteModele();
		ArrayList<Actualite> listActualite = actualiteModele.getList();
		
		PrintWriter out = response.getWriter();

		if (listActualite.isEmpty() ){
			out.println("Il n'y a pas de posts pour ce sujet.");
		} else {
			request.setAttribute("listActualite", listActualite);
	        RequestDispatcher dispatcher = request.getRequestDispatcher(ACCES_PUBLIC);
	        dispatcher.forward(request, response);
			response.setContentType("text/html");
			//response.setContentType("text/css");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
