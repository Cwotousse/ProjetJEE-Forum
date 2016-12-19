<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="be.forum.metier.SousCategorie"%>
<%@ page import="be.forum.pojo.SousCategoriePOJO"%>
<%@ page import="be.forum.dao.CategorieDAO"%>
<%@ page import="be.forum.pojo.CategoriePOJO"%>
<%@ page import="be.forum.dao.DAO"%>
<%@ page import="be.forum.dao.DAOFactory"%>
<%@ page import="be.forum.metier.Utilisateur"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="">

<title>Forum Projet JEE</title>

<!-- Bootstrap core CSS -->
<link href="/ProjetJEE-Forum/VUE/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />

<!-- Custom styles for this template -->
<link href="/ProjetJEE-Forum/VUE/bootstrap/css/jumbotron.css"
	rel="stylesheet">


<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">

<link rel="stylesheet"
	href="/ProjetJEE-Forum/VUE/assets/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="/ProjetJEE-Forum/VUE/assets/css/form-elements.css">
<link rel="stylesheet" href="/ProjetJEE-Forum/VUE/assets/css/style.css">
</head>
<body>
	<!-- grâce à cet import, je récupère les listes pour afficher la navbar -->
	<c:import url="/onloadIndex" />

	<c:set var="context" value="${pageContext.request.contextPath}" />

	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Forum</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<!-- Elements de la navigation bar -->

			<!--  #TODO mettre du codejava pour récup la liste des catégories et créer le nbr de nav button en fonction de ça -->
			<ul class="nav navbar-nav">
				<li class="active"><a href="#accueil">Accueil</a></li>
				<%
					DAO<CategoriePOJO> categorieDAO = new DAOFactory().getCategorieDAO();
					ArrayList<CategoriePOJO> listCategorie = categorieDAO.getList();
				%>
				<li class="dropdown"><a href="#sports" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"><%= listCategorie.get(0).getTitre() %><span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<c:forEach items="${listSousCategorie0}" var="sousCat0">
							<li><a
								href="${context}/displaySubjects?nomSousCategorie=${sousCat0.getTitre()}">${sousCat0.getTitre()}
							</a></li>
						</c:forEach>
					</ul></li>
				<li class="dropdown"><a href="#sports" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"><%= listCategorie.get(1).getTitre() %><span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<c:forEach items="${listSousCategorie1}" var="sousCat1">
							<li><a
								href="${context}/displaySubjects?nomSousCategorie=${sousCat1.getTitre()}">${sousCat1.getTitre()}
							</a></li>
						</c:forEach>
					</ul></li>
				<li class="dropdown"><a href="#sports" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"><%= listCategorie.get(2).getTitre() %><span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<c:forEach items="${listSousCategorie2}" var="sousCat2">
							<li><a
								href="${context}/displaySubjects?nomSousCategorie=${sousCat2.getTitre()}">${sousCat2.getTitre()}
							</a></li>
						</c:forEach>
					</ul></li>
				<li class="dropdown"><a href="#sports" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"><%= listCategorie.get(3).getTitre() %><span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<c:forEach items="${listSousCategorie3}" var="sousCat3">
							<li><a
								href="${context}/displaySubjects?nomSousCategorie=${sousCat3.getTitre()}">${sousCat3.getTitre()}
							</a></li>
						</c:forEach>
					</ul></li>
			</ul>
			<!-- Partie connexion de la navigation bar -->
			<!-- action="DeconnexionServlet" -->
			<form class="navbar-form navbar-right" method="POST">
				<a class="btn btn-link-1 launch-modal" href="#"
					data-modal-id="modal-profile">Mon profil</a> 
				<a class="btn btn-link-1 launch-modal" href="<%=request.getContextPath()%>/disconnect"
					data-modal-id="modal-disconnect">Se déconnecter</a>
			</form>
		</div>
		<!--/.navbar-collapse -->
	</div>
	</nav>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="/ProjetJEE-Forum/VUE/bootstrap/js/bootstrap.min.js"></script>
	<script src="/ProjetJEE-Forum/VUE/assets/js/jquery.backstretch.min.js"></script>
	<script src="/ProjetJEE-Forum/VUE/assets/js/scripts.js"></script>
</body>
</html>