package be.forum.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.forum.modele.Utilisateur;

public class AfficherNavBarConnectéServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//<jsp:include page="/servletURL" />
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		//Je récupère l'utilisateur à partir de la session
		if(!(session == null)){
			Utilisateur utilisateurConnecté = (Utilisateur) session.getAttribute("utilisateur");
			out.println(utilisateurConnecté.getPseudo());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
