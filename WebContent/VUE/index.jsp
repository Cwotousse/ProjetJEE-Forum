<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="be.forum.modele.SousCategorie"%>
<%@ page import="be.forum.pojo.SousCategoriePOJO"%>
<%@ page import="be.forum.dao.CategorieDAO"%>
<%@ page import="be.forum.pojo.CategoriePOJO"%>
<%@ page import="be.forum.dao.DAO"%>
<%@ page import="be.forum.dao.DAOFactory"%>

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
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />

<!-- Custom styles for this template -->
<link href="bootstrap/css/jumbotron.css" rel="stylesheet">


<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">

<link rel="stylesheet"
	href="assets/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/css/form-elements.css">
<link rel="stylesheet" href="assets/css/style.css">
<style>
.carousel-inner>.item>img, .carousel-inner>.item>a>img {
	width: 70%;
	margin: auto;
}
</style>
</head>

<body>
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
							ArrayList<SousCategorie> listSousCategorie = sousCategorie.getList();
							for (int j = 0; j < 6; j++) {
								out.println("<li><a href=\"#\">" + listSousCategorie.get(j).getTitre() + "</a></li>");
							}
						%>
					</ul></li>
				<li class="dropdown"><a href="#jeuxvideos"
					class="dropdown-toggle" data-toggle="dropdown" role="button"
					aria-haspopup="true" aria-expanded="false"><%=listCategorie.get(1).getTitre()%><span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<%
							for (int j = 6; j < 13; j++) {
								out.println("<li><a href=\"#\">" + listSousCategorie.get(j).getTitre() + "</a></li>");
							}
						%>
					</ul></li>
				<li class="dropdown"><a href="#technologie"
					class="dropdown-toggle" data-toggle="dropdown" role="button"
					aria-haspopup="true" aria-expanded="false"><%=listCategorie.get(2).getTitre()%><span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<%
							for (int j = 13; j < 16; j++) {
								out.println("<li><a href=\"#\">" + listSousCategorie.get(j).getTitre() + "</a></li>");
							}
						%>
					</ul></li>
				<li class="dropdown"><a href="#blabla" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"><%=listCategorie.get(3).getTitre()%><span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<%
							for (int j = 16; j < 21; j++) {
								out.println("<li><a href=\"#\">" + listSousCategorie.get(j).getTitre() + "</a></li>");
							}
						%>
					</ul></li>
			</ul>
			<!-- Partie connexion de la navigation bar -->
			<!-- action="ConnexionServlet" -->
			<form class="navbar-form navbar-right" method="POST">
				<!-- <div class="form-group">
					<input type="text" name="pseudo" id="pseudo" value=""
						placeholder="Pseudo" class="form-control">
				</div>
				<div class="form-group">
					<input type="password" name="motdepasse" id="motdepasse" value=""
						placeholder="Mot de passe" class="form-control">
				</div>
				<button type="submit" id="connexion" class="btn btn-success">Se
					connecter</button>-->
				<a class="btn btn-link-1 launch-modal" href="#"
					data-modal-id="modal-login">Se connecter</a> <a
					class="btn btn-link-1 launch-modal" href="#"
					data-modal-id="modal-register">S'enregistrer</a>
			</form>
		</div>
		<!--/.navbar-collapse -->
	</div>
	</nav>

	<!-- MODAL LOGIN -->
	<div class="modal fade" id="modal-login" tabindex="-1" role="dialog"
		aria-labelledby="modal-login-label" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Quitter</span>
					</button>
					<h3 class="modal-title" id="modal-login-label">Connexion au
						forum</h3>
					<p>Entrez votre pseudo et votre mot de passe pour vous
						connecter:</p>
				</div>

				<div class="modal-body">
					<form role="form"
						action="http://localhost:9090/ProjetJEE-Forum/ConnexionServlet"
						method="POST" class="login-form">
						<div class="form-group">
							<label class="sr-only" for="pseudo">Pseudo</label> <input
								type="text" name="pseudo" placeholder="Pseudo..."
								class="form-username form-control" id="pseudo">
						</div>
						<div class="form-group">
							<label class="sr-only" for="motdepasse">Mot de passe</label> <input
								type="password" name="motdepasse" placeholder="Mot de passe..."
								class="form-password form-control" id="motdepasse">
						</div>
						<button type="submit" class="btn">Se connecter</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- MODAL REGISTER-->
	<div class="modal fade" id="modal-register" tabindex="-1" role="dialog"
		aria-labelledby="modal-register-label" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Quitter</span>
					</button>
					<h3 class="modal-title" id="modal-register-label">Enregistrez-vous
						maintenant</h3>
					<p>Remplissez les champs suivants:</p>
				</div>
				<div class="modal-body">
					<form role="form"
						action="http://localhost:9090/ProjetJEE-Forum/InscriptionServlet"
						method="post" class="registration-form">
						<div class="form-group">
							<label class="sr-only" for="form-username">Pseudo</label> <input
								type="text" name="form-username" placeholder="Pseudo..."
								class="form-username form-control" id="form-username">
						</div>
						<div class="form-group">
							<label class="sr-only" for="form-password-register">Mot
								de passe</label> <input type="password" name="form-password-register"
								placeholder="Mot de passe..."
								class="form-password-register form-control"
								id="form-password-register">
						</div>
						<div class="form-group">
							<label class="sr-only" for="form-last-name">Nom</label> <input
								type="text" name="form-last-name" placeholder="Nom..."
								class="form-last-name form-control" id="form-last-name">
						</div>
						<div class="form-group">
							<label class="sr-only" for="form-first-name">Prénom</label> <input
								type="text" name="form-first-name" placeholder="Prénom..."
								class="form-first-name form-control" id="form-first-name">
						</div>
						<div class="form-group">
							<label class="sr-only" for="form-email">Email</label> <input
								type="text" name="form-email" placeholder="Email..."
								class="form-email form-control" id="form-email">
						</div>
						<button type="submit" class="btn">S'enregistrer</button>
					</form>
				</div>
			</div>
		</div>
	</div>



	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
			<div class="container">
				<br>
				<!-- <div id="myCarousel" class="carousel slide" data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
						<li data-target="#myCarousel" data-slide-to="1"></li>
						<li data-target="#myCarousel" data-slide-to="2"></li>
						<li data-target="#myCarousel" data-slide-to="3"></li>
					</ol>

					<div class="carousel-inner" role="listbox">

						<div class="item active">
							<img src="http://esq.h-cdn.co/assets/15/32/1438985167-screen-shot-2015-08-07-at-60550-pm.png" alt="Chania" width="460" height="345">
							<div class="carousel-caption">
								<h3>Mia</h3>
								<p>PORNSTAR</p>
							</div>
						</div>

						<div class="item">
							<img src="http://vignette4.wikia.nocookie.net/prowrestling/images/a/a5/Annie_Cruz.jpg/revision/latest?cb=20140822020817" alt="Chania" width="460" height="345">
							<div class="carousel-caption">
								<h3>Annie</h3>
								<p>The idol of Adrien</p>
							</div>
						</div>

						<div class="item">
							<img src="http://www.w3schools.com/bootstrap/img_chania2.jpg" alt="Flower" width="460" height="345">
							<div class="carousel-caption">
								<h3>Flowers</h3>
								<p>Beatiful flowers in Kolymbari, Crete.</p>
							</div>
						</div>

						<div class="item">
							<img src="http://www.w3schools.com/bootstrap/img_chania2.jpg" alt="Flower" width="460" height="345">
							<div class="carousel-caption">
								<h3>Flowers</h3>
								<p>Beatiful flowers in Kolymbari, Crete.</p>
							</div>
						</div>

					</div>

					<a class="left carousel-control" href="#myCarousel" role="button"
						data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a> <a class="right carousel-control" href="#myCarousel" role="button"
						data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>
			</div>-->
				<h1>Hello, world!</h1>
				<p>This is a template for a simple marketing or informational
					website. It includes a large callout called a jumbotron and three
					supporting pieces of content. Use it as a starting point to create
					something more unique.</p>
				<p>
					<a class="btn btn-primary btn-lg" href="#" role="button">Learn
						more &raquo;</a>
				</p>
			</div>
		</div>

		<div class="container">
			<!-- Example row of columns -->
			<div class="row">
				<div class="col-md-4">
					<h2>Heading</h2>
					<p>Donec id elit non mi porta gravida at eget metus. Fusce
						dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,
						ut fermentum massa justo sit amet risus. Etiam porta sem malesuada
						magna mollis euismod. Donec sed odio dui.</p>
					<p>
						<a class="btn btn-default" href="#" role="button">View details
							&raquo;</a>
					</p>
				</div>
				<div class="col-md-4">
					<h2>Heading</h2>
					<p>Donec id elit non mi porta gravida at eget metus. Fusce
						dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,
						ut fermentum massa justo sit amet risus. Etiam porta sem malesuada
						magna mollis euismod. Donec sed odio dui.</p>
					<p>
						<a class="btn btn-default" href="#" role="button">View details
							&raquo;</a>
					</p>
				</div>
				<div class="col-md-4">
					<h2>Heading</h2>
					<p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis
						in, egestas eget quam. Vestibulum id ligula porta felis euismod
						semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris
						condimentum nibh, ut fermentum massa justo sit amet risus.</p>
					<p>
						<a class="btn btn-default" href="#" role="button">View details
							&raquo;</a>
					</p>
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
		<script src="bootstrap/js/bootstrap.min.js"></script>
		<script src="assets/js/jquery.backstretch.min.js"></script>
		<script src="assets/js/scripts.js"></script>
</body>
</html>