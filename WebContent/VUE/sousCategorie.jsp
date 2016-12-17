<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="be.forum.metier.SousCategorie"%>
<%@ page import="be.forum.pojo.SousCategoriePOJO"%>
<%@ page import="be.forum.dao.CategorieDAO"%>
<%@ page import="be.forum.pojo.CategoriePOJO"%>
<%@ page import="be.forum.dao.DAO"%>
<%@ page import="be.forum.dao.DAOFactory"%>
<%@ page import="be.forum.metier.Utilisateur"%>
<%@ page import="be.forum.metier.Sujet"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sujet</title>
</head>
<body>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Posts</th>
				<th>Titre</th>
				<th>Auteur</th>
				<th>Creation</th>
			</tr>
		</thead>
		<tbody>
			<%
				// retrieve your list from the request, with casting 
				// On récupère la liste des sujets
				ArrayList<Sujet> listSujet = (ArrayList<Sujet>) request.getAttribute("listeSujets");

				// print the information about every category of the list
				for (Sujet LS : listSujet) {
					out.println("<tr>");
					out.println("<td>" + "x" + "</th>");
					out.println("<td>" + LS.getTitre() + "</td>");
					out.println("<td>" + LS.getUtilisateur().getPseudo() + "</td>");
					out.println("<td>" + LS.getDateSujet() + "</td>");
					out.println("</tr>");
				}
			%>
		</tbody>

	</table>
</body>
</html>