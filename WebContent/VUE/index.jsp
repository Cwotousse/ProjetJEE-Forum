<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="be.forum.modele.SousCategorie"%>
<%@ page import="be.forum.pojo.SousCategoriePOJO"%>
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
				<li class="dropdown"><a href="#sports" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Sports<span class="caret"></span></a>
					<ul class="dropdown-menu">
					<%
						SousCategorie sousCategorie = new SousCategorie();
	 					ArrayList<SousCategoriePOJO> listSousCategorie = sousCategorie.getList();
						for (int i = 0; i < listSousCategorie.size(); i++) {
							out.println("<li><a href=\"#\">" + listSousCategorie.get(i).getTitre() + "</a></li>");
						}
					%>
					</ul></li>
				<li class="dropdown"><a href="#jeuxvideos"
					class="dropdown-toggle" data-toggle="dropdown" role="button"
					aria-haspopup="true" aria-expanded="false">Jeux vidéos<span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">PS4</a></li>
						<li><a href="#">Xbox One</a></li>
						<li><a href="#">PC</a></li>
						<li><a href="#">iOS</a></li>
						<li><a href="#">Android</a></li>
						<li><a href="#">Wii U</a></li>
						<li><a href="#">PS3</a></li>
					</ul></li>
				<li class="dropdown"><a href="#technologie"
					class="dropdown-toggle" data-toggle="dropdown" role="button"
					aria-haspopup="true" aria-expanded="false">Technologie<span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">Programmation</a></li>
						<li><a href="#">Réseaux</a></li>
						<li><a href="#">Domotique</a></li>
						<li><a href="#">...</a></li>
					</ul></li>
				<li class="dropdown"><a href="#blabla" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Blabla<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">Moins de 15 ans</a></li>
						<li><a href="#">15 - 18 ans</a></li>
						<li><a href="#">18 - 25 ans</a></li>
						<li><a href="#">25 - 35 ans</a></li>
						<li><a href="#">Plus de 35 ans</a></li>
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
				<a class="btn btn-link-1 launch-modal" href="#" data-modal-id="modal-login">Se connecter</a>
				<a class="btn btn-link-1 launch-modal" href="#" data-modal-id="modal-register">S'enregistrer</a>
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
					<h3 class="modal-title" id="modal-login-label">Connexion au forum</h3>
					<p>Entrez votre pseudo et votre mot de passe pour vous connecter:</p>
				</div>

				<div class="modal-body">
					<form role="form" action="http://localhost:9090/ProjetJEE-Forum/ConnexionServlet" method="POST" class="login-form">
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
        <div class="modal fade" id="modal-register" tabindex="-1" role="dialog" aria-labelledby="modal-register-label" aria-hidden="true">
        	<div class="modal-dialog">
        		<div class="modal-content">
        			<div class="modal-header">
        				<button type="button" class="close" data-dismiss="modal">
        					<span aria-hidden="true">&times;</span><span class="sr-only">Quitter</span>
        				</button>
        				<h3 class="modal-title" id="modal-register-label">Enregistrez-vous maintenant</h3>
        				<p>Remplissez les champs suivants:</p>
        			</div>
        			<div class="modal-body">
	                    <form role="form" action="" method="post" class="registration-form">
	                    	<div class="form-group">
	                    		<label class="sr-only" for="form-first-name">Prénom</label>
	                        	<input type="text" name="form-first-name" placeholder="Prénom..." class="form-first-name form-control" id="form-first-name">
	                        </div>
	                        <div class="form-group">
	                        	<label class="sr-only" for="form-last-name">Nom</label>
	                        	<input type="text" name="form-last-name" placeholder="Nom..." class="form-last-name form-control" id="form-last-name">
	                        </div>
	                        <div class="form-group">
	                        	<label class="sr-only" for="form-email">Email</label>
	                        	<input type="text" name="form-email" placeholder="Email..." class="form-email form-control" id="form-email">
	                        </div>
	                        <div class="form-group">
	                        	<label class="sr-only" for="form-about-yourself">A propos de vous</label>
	                        	<textarea name="form-about-yourself" placeholder="A propos de vous..." 
	                        				class="form-about-yourself form-control" id="form-about-yourself"></textarea>
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
				<p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in,
					egestas eget quam. Vestibulum id ligula porta felis euismod semper.
					Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum
					nibh, ut fermentum massa justo sit amet risus.</p>
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