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

<title>Forum Projet JEE</title>

<!-- Bootstrap core CSS -->
<link href="/ProjetJEE-Forum/VUE/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />

<!-- Custom styles for this template -->
<link href="/ProjetJEE-Forum/VUE/bootstrap/css/jumbotron.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">


<!-- <link rel="stylesheet" href="assets/css/form-elements.css">-->

<!--  pour émoticone à coté des boutons -->
<link rel="stylesheet"
	href="/ProjetJEE-Forum/VUE/assets/font-awesome/css/font-awesome.min.css">

<style>

/* CSS used here will be applied after bootstrap.css */
.fa-heart {
	color: #e74c3c;
}

[class^="fa fa-star"] {
	color: #f1c40f;
}

.fa-quote-right {
	font-size: .5em;
}

section.panel-title {
	padding: 15px;
	padding-top: 0;
}

#user-description {
	height: 100%;
	border-left: 2px solid #444;
	margin: 0 auto;
	text-align: center;
}

figure img {
	display: inline !important;
}

h4.online:before {
	background-color: green;
	height: 10px;
	width: 10px;
	border: 2px solid #11f464;
}

dt {
	text-align: left !important;
	width: 37% !important;
}

dd {
	margin-left: 2% !important;
}

.panel-footer {
	width: 100%;
	min-height: 40px;
}
</style>

</head>
<body>
	<c:import url="include/navbar1.jsp" />
	<c:forEach items="${listeCommentaire}" var="commentaire">
		<section class="container"> <section class="row clearfix">
		<section class="col-md-12 column">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="panel panel-default">
					<div class="panel-heading">
						<section class="panel-title"> <time class="pull-right">
						<i class="fa fa-calendar"></i> ${commentaire.getDateCommentaire()}<i
							class="fa fa-clock-o"></i> h min </time> <section class="pull-left"
							id="id"> <abbr title="count of posts in this topic">#1</abbr>
						</section> </section>
					</div>
					<section class="row panel-body"> <section
						class="col-md-9">
					<h2>
						<i class="fa fa-smile-o"></i> ${commentaire.getSujet().getTitre()}
					</h2>
					<hr>
					${commentaire.getTexte()} </section> <section id="user-description"
						class="col-md-3 "> <section class="well">
					<div class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
							class="fa fa-cricle"></i>${commentaire.getUtilisateur().getPseudo()}<span
							class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#"><i class="fa fa-user"></i> See profile</a></li>
							<li><a href="#"><i class="fa fa-envelope"></i> Send PM</a></li>
						</ul>
					</div>
					<figure> <img class="img-rounded img-responsive"
						src="http://www.webdesignforums.net/img/wdf_avatar.jpg"
						alt="Adista's avatar"> <figcaption class="text-center">
					<br>
					<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
						class="fa fa-star"></i> <i class="fa fa-star"></i> <i
						class="fa fa-star-half"></i> </figcaption> </figure>
					<dl class="dl-horizontal">
						<dt>joined date:</dt>
						<dd>15 September 2016</dd>
						<dt>Posts:</dt>
						<dd>785</dd>
						<dt>plus:</dt>
						<dd>+89</dd>
						<dt>like:</dt>
						<dd>150 like in 50 post</dd>
					</dl>
					</section> </section> </section>
					<div class="panel-footer">
						<div class="row">
							<section class="col-md-2 "> </section>
							<section class="col-md-6"> <small><a href="#"
								data-toggle="tooltip" title=""> </a> </small> <br>

							</section>
							<section class="col-md-4"> <span class="fa-stack">
								<i class="fa fa-quote-right fa-stack-1x"></i> <i
								class="fa fa-comment-o fa-lg fa-stack-1x"></i>
							</span> <a href="#"> Reply With Quote </a> | <i
								class="fa fa-mail-reply "></i> <a
								class="btn btn-link-1 launch-modal" href="#"
								data-modal-id="modal-comment">Répondre</a> | <i
								class="fa fa-edit "></i> <a href="#"> Edit Post </a> </section>
						</div>
					</div>
				</div>
			</div>
		</div>
		</section> </section> </section>
	</c:forEach>
	<!-- MODAL LOGIN -->
	<div class="modal fade" id="modal-comment" tabindex="-1" role="dialog"
		aria-labelledby="modal-comment-label" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Quitter</span>
					</button>
					<h3 class="modal-title" id="modal-comment-label">Ajouter un
						commentaire</h3>
					<p>Veuillez entrer le commentaire que vous voulez ajouter:</p>
				</div>

				<div class="modal-body">
					<form role="form" action="<%=request.getContextPath()%>/addComment"
						method="POST" class="comment-form">
						<div class="form-group">
							<label class="sr-only" for="form-comment">Commentaire</label>
							<textarea name="form-comment" placeholder="Votre commentaire..."
								class="form-comment form-control" id="form-comment"></textarea>
						</div>
						<button type="submit" class="btn">Confirmer</button>
					</form>
				</div>
			</div>
		</div>
	</div>
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