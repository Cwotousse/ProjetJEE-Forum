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
	<c:import url="/displaycategories" />

	<c:set var="context" value="${pageContext.request.contextPath}" />

	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${context}/VUE/index.jsp">Forum</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<!-- Elements de la navigation bar -->

			<ul class="nav navbar-nav">
				<li ><a href="#accueil" class="fa fa-home fa-2x"></a></li>
				<c:forEach items="${listCategorie}" var="categorie">
					<li><a
						href="${context}/displaysubcategories?nomCategorie=${categorie.getTitre()}">${categorie.getTitre()}</a></li>
				</c:forEach>
			</ul>
			<!-- Partie connexion de la navigation bar -->
			<!-- action="ConnexionServlet" -->
			<form class="navbar-form navbar-right" method="POST">
				<a class="btn btn-primary btn-sm launch-modal" href="#"
					data-modal-id="modal-login">Se connecter</a> <a
					class="btn btn-primary btn-sm launch-modal" href="#"
					data-modal-id="modal-register">S'enregistrer</a>
			</form>
		</div>
		<!--/.navbar-collapse -->
	</div>
	</nav>
</body>
</html>