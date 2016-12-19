<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="be.forum.metier.Sujet"%>
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

<!-- Bootstrap core CSS -->
<link type="text/css"
	href="/ProjetJEE-Forum/VUE/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />

<!-- Custom styles for this template -->
<link type="text/css"
	href="/ProjetJEE-Forum/VUE/bootstrap/css/jumbotron.css"
	rel="stylesheet">

<link type="text/css" rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">

<link type="text/css" rel="stylesheet"
	href="assets/font-awesome/css/font-awesome.min.css">
<link type="text/css" rel="stylesheet"
	href="/ProjetJEE-Forum/VUE/assets/css/form-elements.css">
<link type="text/css" rel="stylesheet"
	href="/ProjetJEE-Forum/VUE/assets/css/style.css">
<style>
.page-heading {
	border-top: 0;
	padding: 0 10px 20px 10px;
}

.forum-post-container .media {
	margin: 10px 10px 10px 10px;
	padding: 20px 10px 20px 10px;
	border-bottom: 1px solid #f1f1f1;
}

.author-info {
	/*color: #676a6c;*/
	font-size: 11px;
	font-color: white;
	margin-top: 5px;
	/*text-align: center;*/
}

.forum-post-info {
	padding: 9px 12px 6px 12px;
	background: #f9f9f9;
	border: 1px solid #f1f1f1;
}

.media-body>.media {
	/*background: #f9f9f9;*/
	border-radius: 3px;
	border: 1px solid #f1f1f1;
}

.forum-post-container .media-body .photos {
	margin: 10px 0;
}

.forum-photo {
	max-width: 140px;
	border-radius: 3px;
}

.media-body>.media .forum-avatar {
	width: 70px;
	margin-right: 10px;
}

.media-body>.media .forum-avatar .img-circle {
	height: 38px;
	width: 38px;
}

.mid-icon {
	font-size: 66px;
	font-color: white;
}

/*Pour changer la taille des carrés autour des sujets*/
.forum-item {
	/*margin: 5px 0;
	padding: 2px 0 5px;*/
	border-bottom: 1px solid #f1f1f1;
}

.views-number {
	font-size: 15px;
	line-height: 18px;
	font-weight: 400;
	color: white;
}

.forum-container, .forum-post-container {
	padding: 5px !important;
}

.forum-item small {
	color: white;
}

.forum-item .forum-sub-title {
	color: white;
	/*margin-left: 50px;*/
}

.forum-title {
	margin: 15px 0 15px 0;
	color: white;
}

.forum-icon {
	float: left;
	width: 30px;
	margin-right: 20px;
	/*text-align: center;*/
}

a.forum-item-title {
	/*Pour déplacer le titre du sujet*/
	float: left;
	color: white;
	display: block;
	font-size: 18px;
	color: white;
	font-weight: 600;
}

a.forum-item-title:hover {
	color: inherit;
}

.forum-item.active .fa {
	/*color: #1ab394;*/
	
}

.forum-item.active a.forum-item-title {
	/*color: #1ab394;*/
	
}

@media ( max-width : 992px) {
	.forum-info {
		margin: 15px 0 10px 0;
		/* Comment this is you want to show forum info in small devices */
		display: none;
	}
	.forum-desc {
		float: none !important;
	}
}

* /
.ibox {
	clear: both;
	margin-bottom: 25px;
	margin-top: 0;
	padding: 0;
}

.ibox.collapsed .ibox-content {
	display: none;
}

.ibox.collapsed .fa.fa-chevron-up:before {
	content: "\f078";
}

.ibox.collapsed .fa.fa-chevron-down:before {
	content: "\f077";
}

.ibox:after, .ibox:before {
	display: table;
}

.ibox-title {
	-moz-border-bottom-colors: none;
	-moz-border-left-colors: none;
	-moz-border-right-colors: none;
	-moz-border-top-colors: none;
	/*background-color: #ffffff;*/
	/*border-color: #e7eaec;*/
	/*border-image: none;*/
	/*border-style: solid solid none;*/
	/*border-width: 3px 0 0;*/
	color: inherit;
	margin-bottom: 0;
	padding: 14px 15px 7px;
	min-height: 48px;
}

.ibox-content {
	/*Pour la couleur du fond*/
	background-color: #595959;
	/*border-radius: 7px;*/
	color: inherit;
	/*padding: 15px 20px 20px 20px;*/
	/*border-color: #ffffff;*/
	/*border-style: solid solid none;*/
	/*border-width: 1px 0;*/
}

.ibox-footer {
	color: inherit;
	border-top: 1px solid #e7eaec;
	font-size: 90%;
	font-color: white;
	/*background: #ffffff;*/
	padding: 10px 15px;
}

.message-input {
	height: 90px !important;
}

.form-control, .single-line {
	/*background-color: #FFFFFF;*/
	/*background-image: none;*/
	border: 1px solid #e5e6e7;
	border-radius: 1px;
	color: inherit;
	display: block;
	padding: 6px 12px;
	transition: border-color 0.15s ease-in-out 0s, box-shadow 0.15s
		ease-in-out 0s;
	width: 100%;
	font-size: 14px;
	font-color: white;
}

.text-navy {
	color: #1ab394;
}

.mid-icon {
	font-size: 66px !important;
	font-color: white;
}

.m-b-sm {
	margin-bottom: 10px;
}

.allSubjects {
	border-radius: 7px;
	background-color: #595959;
}
</style>
</head>

<body>
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
					aria-expanded="false"><%=listCategorie.get(0).getTitre()%><span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<%
							SousCategorie sousCategorie = new SousCategorie();
							ArrayList<SousCategorie> listSousCategorie = sousCategorie.getList(listCategorie.get(0).getTitre());
							for (int j = 0; j < listSousCategorie.size(); j++) {
								out.println("<li><a href=\" " + request.getContextPath() + "/displaySubjects?nomSousCategorie="
										+ listSousCategorie.get(j).getTitre() + "\">" + listSousCategorie.get(j).getTitre()
										+ "</a></li>");
							}
						%>
					</ul></li>
				<li class="dropdown"><a href="#jeuxvideos"
					class="dropdown-toggle" data-toggle="dropdown" role="button"
					aria-haspopup="true" aria-expanded="false"><%=listCategorie.get(1).getTitre()%><span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<%
							SousCategorie sousCategorie1 = new SousCategorie();
							ArrayList<SousCategorie> listSousCategorie1 = sousCategorie.getList(listCategorie.get(1).getTitre());
							for (int j = 0; j < listSousCategorie1.size(); j++) {
								out.println("<li><a href=\" " + request.getContextPath() + "/displaySubjects?nomSousCategorie="
										+ listSousCategorie1.get(j).getTitre() + "\">" + listSousCategorie1.get(j).getTitre()
										+ "</a></li>");
							}
						%>
					</ul></li>
				<li class="dropdown"><a href="#technologie"
					class="dropdown-toggle" data-toggle="dropdown" role="button"
					aria-haspopup="true" aria-expanded="false"><%=listCategorie.get(2).getTitre()%><span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<%
							SousCategorie sousCategorie2 = new SousCategorie();
							ArrayList<SousCategorie> listSousCategorie2 = sousCategorie.getList(listCategorie.get(2).getTitre());
							for (int j = 0; j < listSousCategorie2.size(); j++) {
								out.println("<li><a href=\" " + request.getContextPath() + "/displaySubjects?nomSousCategorie="
										+ listSousCategorie2.get(j).getTitre() + "\">" + listSousCategorie2.get(j).getTitre()
										+ "</a></li>");
							}
						%>
					</ul></li>
				<li class="dropdown"><a href="#blabla" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"><%=listCategorie.get(3).getTitre()%><span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<%
							SousCategorie sousCategorie3 = new SousCategorie();
							ArrayList<SousCategorie> listSousCategorie3 = sousCategorie.getList(listCategorie.get(3).getTitre());
							for (int j = 0; j < listSousCategorie3.size(); j++) {
								// Il n'y a pas besoin des <%= pour les request car on est déjà dans des <% 
								// On ajoute en parametre le nom de la catégorie pour chercher ses sujets
								out.println("<li><a href=\" " + request.getContextPath() + "/displaySubjects?nomSousCategorie="
										+ listSousCategorie3.get(j).getTitre() + "\">" + listSousCategorie3.get(j).getTitre()
										+ "</a></li>");
							}
						%>
					</ul></li>
			</ul>
			<!-- Partie connexion de la navigation bar -->
			<!-- action="ConnexionServlet" -->
			<form class="navbar-form navbar-right" method="POST">
				<a class="btn btn-link-1 launch-modal" href="#"
					data-modal-id="modal-login">Se connecter</a> <a
					class="btn btn-link-1 launch-modal" href="#"
					data-modal-id="modal-register">S'enregistrer</a>
			</form>
		</div>
		<!--/.navbar-collapse -->
	</div>
	</nav>

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
			<div class="container">
				<br>
				<!-- Et en utilisant du JSTL -->
				<!-- forum-title -->
				<div class="forum-title">
					<div class="pull-right forum-desc">
						<!--  count du nombre de sujet dans cette sous cat -->
						<small>Total posts: ${listeSujets.size()}</small>
					</div>
					<!-- titre sous catégorie -->
					<!-- #TODO peut etre null donc à corriger + i can do it but flemme -->
					<h3>${listeSujets.get(0).getSousCategorie().getTitre()}</h3>
				</div>
				<!-- forum-title -->
				<div class="allSubjects">
					<c:forEach items="${listeSujets}" var="sujet">
						<!-- row -->
						<div class="row">
							<!-- col-lg-12 -->
							<div class="col-lg-12">
								<!-- wrapper wrapper-content animated fadeInRight -->
								<div class="wrapper wrapper-content animated fadeInRight">
									<!-- ibox-content forum-container -->
									<div class="ibox-content forum-container">
										<!-- forum-item active -->
										<div class="forum-item active">
											<!-- row -->
											<div class="row">
												<div class="col-md-9">
													<!-- titre du sujet avec un href -->
													<a
														href="<%=request.getContextPath()%>/displaycomments?nomSujet=${sujet.getTitre()}&nomSousCategorie=${sujet.getSousCategorie().getTitre()}&pseudoAuteur=${sujet.getUtilisateur().getPseudo()}&dateSujet=${sujet.getDateSujet()}"
														class="forum-item-title" title="${sujet.getTitre()}">${sujet.getTitre()}</a>
												</div>
												<div class="col-md-1 forum-info">
													<!-- nom de l'auteur -->
													<span class="views-number">${sujet.getUtilisateur().getPseudo()}</span>
													<div>
														<small>Author</small>
													</div>
												</div>
												<div class="col-md-1 forum-info">
													<!--  date du sujet  -->
													<span class="views-number">${sujet.getDateSujet()}</span>
													<div>
														<small>Date</small>
													</div>
												</div>
												<!-- col-md-1 forum-info -->
												<div class="col-md-1 forum-info">
													<!-- on va surement delete -->
													<span class="views-number"> 140 </span>
													<div>
														<small>Comments</small>
													</div>
												</div>
												<!-- col-md-1 forum-info -->
											</div>
											<!-- row -->
										</div>
										<!-- forum-item active -->
									</div>
									<!-- ibox-content forum-container -->
								</div>
								<!-- wrapper wrapper-content animated fadeInRight -->
							</div>
							<!-- col-lg-12 -->
						</div>
						<!-- row -->
					</c:forEach>
				</div>
			</div>
		</div>

		<hr>

		<!-- #TODO faire un seul footer pour chaque page et l'include pareil pour l'entete -->
		<footer>
		<p>&copy; 2016 Company, Inc.</p>
		</footer>
	</div>
	<!-- /container -->


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