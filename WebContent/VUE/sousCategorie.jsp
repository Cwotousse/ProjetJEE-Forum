<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="be.forum.metier.Sujet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="be.forum.metier.SousCategorie"%>
<%@ page import="be.forum.pojo.SousCategoriePOJO"%>
<%@ page import="be.forum.dao.CategorieDAO"%>
<%@ page import="be.forum.pojo.CategoriePOJO"%>
<%@ page import="be.forum.dao.DAO"%>
<%@ page import="be.forum.dao.DAOFactory"%>
<%@ page import="be.forum.metier.Utilisateur"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${sujet.getTitre()}</title>
</head>
<body>
	<!-- Et en utilisant du JSTL -->
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Titre</th>
				<th>Auteur</th>
				<th>NB</th>
				<th>Date sujet</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	<c:forEach items="${listeSujets}" var="sujet">
		<li>
			<!-- Titre -->
			<span class="topic-subject">
				<a class="lien-jv topic-title" href="<%=request.getContextPath()%>/displaycomments?nomSujet=${sujet.getTitre()}&nomSousCategorie=${sujet.getSousCategorie().getTitre()}&pseudoAuteur=${sujet.getUtilisateur().getPseudo()}&dateSujet=${sujet.getDateSujet()}" title="${sujet.getTitre()}" >${sujet.getTitre()}</a>
			</span>
			
			<!-- Auteur -->
			<a class="xXx text-user topic-author" href="<%=request.getContextPath()%>/displaycomments?nomSujet=${sujet.getTitre()}&nomSousCategorie=${sujet.getSousCategorie().getTitre()}&pseudoAuteur=${sujet.getUtilisateur().getPseudo()}&dateSujet=${sujet.getDateSujet()}" title="${sujet.getTitre()}" target="_blank" sl-processed="1"> ${sujet.getUtilisateur().getPseudo()} </a>
			
			<!-- Nombre posts -->
			<span class="topic-count"> # </span>
			
			<!-- Creation -->
			<span class="topic-date">
				<a class="xXx lien-jv" href="<%=request.getContextPath()%>/displaycomments?nomSujet=${sujet.getTitre()}&nomSousCategorie=${sujet.getSousCategorie().getTitre()}&pseudoAuteur=${sujet.getUtilisateur().getPseudo()}&dateSujet=${sujet.getDateSujet()}" title="${sujet.getTitre()}" sl-processed="1"> ${sujet.getDateSujet()} </a>
			</span>
		</li>
	</c:forEach>

	<!-- <a 	href="<%=request.getContextPath()%>
					/displaycomments?nomSujet=${sujet.getTitre()}
					&nomSousCategorie=${sujet.getSousCategorie().getTitre() }
					&pseudoAuteur=${sujet.getUtilisateur().getPseudo()}
					&dateSujet=${sujet.getDateSujet()}"> -->

</body>
</html>