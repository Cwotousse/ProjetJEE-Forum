<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="">

<title>Forum Projet JEE</title>

<!-- Bootstrap core CSS -->
<link href="/ProjetJEE-Forum/VUE/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />

<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">

<link rel="stylesheet"
	href="assets/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="/ProjetJEE-Forum/VUE/assets/css/form-elements.css">
<link rel="stylesheet" href="/ProjetJEE-Forum/VUE/assets/css/style.css">
<link rel="stylesheet"
	href="/ProjetJEE-Forum/VUE/bootstrap/css/lumen.css">
<link href="/ProjetJEE-Forum/VUE/css/comment.css" rel="stylesheet">

</head>

<body>
	<c:set var="context" value="${pageContext.request.contextPath}" />
	<c:import url="include/navbar1.jsp" />

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
					<form role="form" action="<%=request.getContextPath()%>/login"
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
					<form role="form" action="<%=request.getContextPath()%>/register"
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
				<!-- row -->
				<div class="row" id="sub-categorie">
					<!-- col-lg-12 -->
					<div class="col-lg-12">
						<!-- wrapper wrapper-content animated fadeInRight -->
						<div class="wrapper wrapper-content animated fadeInRight">
							<!-- ibox-content m-b-sm border-bottom -->
							<div class="ibox-content m-b-sm border-bottom">
								<div class="p-xs">
									<div class="pull-left m-r-md">
										<i class="fa fa-globe text-navy mid-icon"></i>
									</div>
									<h2>Bienvenu(e) sur notre forum</h2>
									<span>Choisissez la sous-catégorie que vous souhaitez.</span>
								</div>
							</div>

							<div class="ibox-content forum-container">
								<div class="forum-title" id="total-sub-categorie">
									<div class="pull-right forum-desc">
										<small>Total de sous-catégorie:
											${listSousCategorie.size()}</small>
									</div>
									<h3>General subjects</h3>
								</div>
								<c:forEach items="${listSousCategorie}" var="sousCategorie">
									<div class="forum-item active" id="elem-sub-categorie">
										<div class="row">
											<div class="col-md-9">
												<div class="forum-icon">
													<i class="${sousCategorie.getIcone()}"></i>
												</div>
												<a
													href="${context}/displaysubjects?nomSousCategorie=${sousCategorie.getTitre()}"
													class="forum-item-title" id="btn-sub-categories"
													onclick="$('#total-sub-categorie').hide()">${sousCategorie.getTitre()}</a>
												<div class="forum-sub-title">Talk about sports,
													entertainment, music, movies, your favorite color, talk
													about enything.</div>
											</div>
											<div class="col-md-1 forum-info">
												<span class="views-number"> 1216 </span>
												<div>
													<small>Vues</small>
												</div>
											</div>
											<div class="col-md-1 forum-info">
												<span class="views-number"> 368 </span>
												<div>
													<small>Topics</small>
												</div>
											</div>
											<div class="col-md-1 forum-info">
												<span class="views-number"> 140 </span>
												<div>
													<small>Posts</small>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
						<!-- wrapper wrapper-content animated fadeInRight -->
					</div>
					<!-- col-lg-12 -->
				</div>
				<!-- row -->
				<div id="body-text">
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
		</div>

		<!-- AFFICHAGE DES SUJETS -->
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row" id="subject">
				<!-- col-lg-12 -->
				<div class="col-lg-12">
					<!-- wrapper wrapper-content animated fadeInRight -->
					<div class="wrapper wrapper-content animated fadeInRight">
						<!-- ibox-content m-b-sm border-bottom -->
						<div class="ibox-content m-b-sm border-bottom">
							<div class="p-xs">
								<div class="pull-left m-r-md">
									<i class="fa fa-globe text-navy mid-icon"></i>
								</div>
								<h2>Bienvenu(e) sur notre forum</h2>
								<span>Choisissez le sujet que vous souhaitez consulter.</span>
							</div>
						</div>

						<div class="ibox-content forum-container">
							<div class="forum-title">
								<div class="pull-right forum-desc">
									<!--  count du nombre de sujet dans cette sous cat -->
									<small>Total posts: ${listeSujets.size()}</small>
								</div>
								<h3>General subjects</h3>
							</div>
							<c:forEach items="${listeSujets}" var="sujet">
								<div class="forum-item active" id="elem-sujet">
									<div class="row">
										<div class="col-md-9">
											<div class="forum-icon">
												<i class="${sousCategorie.getIcone()}"></i>
											</div>
											<!-- titre du sujet avec un href -->
											<a
												href="<%=request.getContextPath()%>/displaycomments?nomSujet=${sujet.getTitre()}&nomSousCategorie=${sujet.getSousCategorie().getTitre()}&pseudoAuteur=${sujet.getUtilisateur().getPseudo()}&dateSujet=${sujet.getDateSujet()}"
												class="forum-item-title" title="${sujet.getTitre()}">${sujet.getTitre()}</a>

											<div class="forum-sub-title">Talk about sports,
												entertainment, music, movies, your favorite color, talk
												about enything.</div>
										</div>
										<div class="col-md-1 forum-info">
											<span class="views-number">${sujet.getUtilisateur().getPseudo()}</span>
											<div>
												<small>Auteur</small>
											</div>
										</div>
										<div class="col-md-1 forum-info">
											<span class="views-number">${sujet.getDateSujet()}</span>
											<div>
												<small>Date</small>
											</div>
										</div>
										<div class="col-md-1 forum-info">
											<span class="views-number"> 140 </span>
											<div>
												<small>Posts</small>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
					<!-- wrapper wrapper-content animated fadeInRight -->
				</div>
				<!-- col-lg-12 -->
			</div>
			<!-- row -->
		</div>
		<!-- container -->

		<!-- Affichage des commentaires -->
		<div id="comments">
			<c:forEach items="${listeCommentaire}" var="commentaire">
				<section class="container" id="elem-comment"> <section
					class="row clearfix"> <section class="col-md-12 column">
				<div class="row clearfix">
					<div class="col-md-12 column">
						<div class="panel panel-default">
							<div class="panel-heading">
								<section class="panel-title"> 
								<time class="pull-right"><i class="fa fa-calendar"></i>
								<div id="date-creation-sujet">
								${commentaire.getSujet().getDateSujet()}</div>
								</time>
								<section class="pull-left" id="id"> <abbr
									title="count of posts in this topic">#1</abbr> </section> </section>
							</div>
							<section class="row panel-body"> <section
								class="col-md-9">
							<h2 id="nom-sujet">${commentaire.getSujet().getTitre()}</h2>
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
								src="http://mirya.clanweb.eu/infusions/last_seen_users_panel/images/noAvatar.png"
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
							<div class="panel-footer" id="panelfooter">
								<div class="row">
									<section class="col-md-2 "> </section>
									<section class="col-md-6"> <small><a href="#"
										data-toggle="tooltip" title=""> </a> </small> <br>
									</section>

									<section class="col-md-4"> <a
										class="btn btn-primary launch-modal" href="#"
										data-modal-id="modal-comment" id="reply">Répondre <i
										class="fa fa-mail-reply "></i></a> <a href="#"
										class="btn btn-primary launch-modal" id="edit" href="#"
										data-modal-id="modal-modify">Modifier <i
										class="fa fa-edit "></i>
									</a> </section>
								</div>
							</div>
						</div>
					</div>
				</div>
				</section> </section> </section>
			</c:forEach>
		</div>
		<!-- MODAL ADD COMMENT -->
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
						<form role="form"
							action="<%=request.getContextPath()%>/addComment" method="POST"
							class="comment-form">
							<div class="form-group">
								<label class="sr-only" for="form-comment">Commentaire</label>
								<label class="form-comment form-control"  id="nom-sujet-label">nom</label>
								<label class="form-comment form-control"  id="date-sujet-label">date</label>
								
								<input type="hidden" name="nom-sujet-hidden" id="nom-sujet-hidden" value="xxx" ></input>
								<input type="hidden" name="date-sujet-hidden" id="date-sujet-hidden" value="xxx"></input>
								<textarea name="form-comment" placeholder="Votre commentaire..."
									class="form-comment form-control" id="form-comment"></textarea>
							</div>
							<button type="submit" class="btn">Confirmer</button>
						</form>
					</div>
				</div>
			</div>
		</div>

		<!-- MODAL MODIFY COMMENT -->
		<div class="modal fade" id="modal-modify" tabindex="-1" role="dialog"
			aria-labelledby="modal-modify-label" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Quitter</span>
						</button>
						<h3 class="modal-title" id="modal-modify-label">Modifier un
							commentaire</h3>
						<p>Veuillez entrer le commentaire que vous voulez modifier:</p>
					</div>

					<div class="modal-body">
						<form role="form" action="" method="POST" class="modify-form">
							<div class="form-group">
								<label class="sr-only" for="form-modify">Commentaire</label>
								<textarea name="form-modify" placeholder="Votre commentaire..."
									class="form-modify form-control" id="form-modify"></textarea>
							</div>
							<button type="submit" class="btn">Confirmer</button>
						</form>
					</div>
				</div>
			</div>
		</div>

		<!-- MODAL PROFILE-->
		<div class="modal fade" id="modal-profile" tabindex="-1" role="dialog"
			aria-labelledby="modal-profile-label" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Quitter</span>
						</button>
						<h3 class="modal-title" id="modal-profile-label">Votre profil</h3>
					</div>
					<div class="modal-body">
						<div class="div-pseudo">
							<strong>Pseudo</strong>
							<p>${sessionScope.utilisateur.getPseudo()}</p>
						</div>
						<div class="div-nom">
							<strong>Nom</strong>
							<p>${sessionScope.utilisateur.getNom()}</p>
						</div>
						<div class="div-prenom">
							<strong>Prénom</strong>
							<p>${sessionScope.utilisateur.getPrenom()}</p>
						</div>
						<div class="div-datenaissance">
							<strong>Date de naissance</strong>
							<p>${sessionScope.utilisateur.getDateNaissance()}</p>
						</div>
						<div class="div-email">
							<strong>Email</strong>
							<p>${sessionScope.utilisateur.getMail()}</p>
						</div>
						<div class="div-type">
							<strong>Type</strong>
							<p>${sessionScope.utilisateur.getType()}</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Fin affichage des commentaires -->

		<hr>

		<footer>
		<p>&copy; 2016 Company, Inc.</p>
		</footer>
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