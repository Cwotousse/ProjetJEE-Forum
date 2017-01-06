<%@page isErrorPage="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">-->
<!DOCTYPE html>
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
	rel="stylesheet">

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
					<form role="form" action="${context}/login" method="POST"
						class="login-form">
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
						<button type="submit" class="btn1">Se connecter</button>
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
					<form role="form" action="${context}/register" method="post"
						class="registration-form">
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
						<button type="submit" class="btn1">S'enregistrer</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- row -->
	<div class="jumbotron" id="body-text">
		<div class="container">
			<div>
				<h1>Une erreur est survenue</h1>
				<label> <c:out value="${error_message}"/> </label>
				<label><a class="fa fa-home fa-2x"
							href="/ProjetJEE-Forum/VUE/index.jsp">Revenir à l'accueil</a></label>
				<img class="img-responsive img-rounded"
					src="https://cdn-images-1.medium.com/max/800/1*qdFdhbR00beEaIKDI_WDCw.gif"
					alt="img error">
			</div>
		</div>
	</div>

	<c:import url="include/footer.jsp" />
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





