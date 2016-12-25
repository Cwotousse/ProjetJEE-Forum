package be.forum.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.forum.modele.CommentaireModele;
import be.forum.pojo.Commentaire;

public class ModifierCommentaire extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			// Récupère les données
			String idCommentaire = request.getParameter("id-commentaire-hidden");
			String texte = request.getParameter("form-modify");
			
			if (!texte.equals("")) {
				// Instancie un commentaireModele
				CommentaireModele commentaireModele = new CommentaireModele();
			
				// Modifie le commentaire via le nouveau texte et l'id du commentaire
				commentaireModele.modifier(Integer.parseInt(idCommentaire), texte);
				
				// On récupère les données pour redidriger
				Commentaire com = CommentaireModele.getCommentaire(Integer.parseInt(idCommentaire));
				// Construction de l'URL de retour
				String completeURL = request.getContextPath() + "/displaycomments" + 
						"?&nomSujet=" + com.getSujet().getTitre() +
						"&nomSousCategorie=" + com.getSujet().getSousCategorie().getTitre() +
						"&pseudoAuteur=" + com.getUtilisateur().getPseudo() + 
						"&dateSujet=" + com.getSujet().getDateSujet();
				out.println(completeURL);
				
				// Redirige
				response.sendRedirect(completeURL);
			} else {
				out.println("Le commentaire est vide.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			out.print("Modification non-effectuée.");
		}			
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
