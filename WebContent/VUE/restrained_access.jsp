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

<link rel="stylesheet" type="text/css"
	href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="/ProjetJEE-Forum/VUE/assets/css/form-elements.css">
<link rel="stylesheet"
	href="/ProjetJEE-Forum/VUE/css/restrained.css">
<style>
</style>
</head>

<body>
	<c:import url="include/navbar1.jsp" />
	<c:import url="/displayusers" />
	<c:set var="context" value="${pageContext.request.contextPath}" />
	<br />
	<br />
	<br />
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="main-box clearfix">
					<div class="table-responsive">
						<table class="table user-list">
							<thead>
								<tr>
									<th><span>ID</span></th>
									<th><span>Pseudo</span></th>
									<th><span>Utilisateur</span></th>
									<th><span>Date de naissance</span></th>
									<th><span>Email</span></th>
									<th>&nbsp;</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listUtilisateur}" var="utilisateur">
									<tr>
										<td><span class="label label-default">${utilisateur.getID()}</span></td>
										<td>${utilisateur.getPseudo()}</td>
										<td><img
											src="http://mirya.clanweb.eu/infusions/last_seen_users_panel/images/noAvatar.png"
											alt=""> <a href="#" class="user-link">${utilisateur.getPrenom()}
												${utilisateur.getNom()}</a> <span class="user-subhead">${utilisateur.getType()}</span></td>
										<td>${utilisateur.getDateNaissance()}</td>
										<td name="mail-param">${utilisateur.getMail()}</td>
										<td style="width: 20%;"><a
											class="btn btn-primary btn-sm btn-warning launch-modal"
											data-modal-id="modal-user-edit-${utilisateur.getID()}">Modifier</a>
											<form role="form" action="${context}/deleteuser"
												method="POST" class="delete-user-form">
												<input type="hidden" name="pseudo-hidden" id="pseudo-hidden"
													value="${utilisateur.getPseudo()}"></input>
												<button type="submit"
													class="btn btn-primary btn-sm btn-danger">Supprimer</button>
											</form></td>
									</tr>
									<!-- MODAL EDIT USER -->
									<!-- rajoute un id dans l'id du modal pour qu'il soit propre à chaque ligne -->
									<div class="modal fade"
										id="modal-user-edit-${utilisateur.getID()}" tabindex="-1"
										role="dialog" aria-labelledby="modal-user-label"
										aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal">
														<span aria-hidden="true">&times;</span><span
															class="sr-only">Quitter</span>
													</button>
													<h3 class="modal-title" id="modal-edit-label">Modification</h3>
													<p>Modifiez les champs nécessaires:</p>
												</div>
												<div class="modal-body">
													<form role="form" action="${context}/edituser"
														method="post" class="edit-form">
														<input type="hidden" name="pseudo-hidden"
															id="pseudo-hidden" value="${utilisateur.getPseudo()}"></input>
														<div class="form-group">
															<label class="sr-only" for="form-username-edit">Pseudo</label>
															<input type="text" name="form-username-edit"
																value="${utilisateur.getPseudo()}"
																class="form-username-edit form-control"
																id="form-username-edit">
														</div>
														<div class="form-group">
															<label class="sr-only" for="form-password-edit">Mot
																de passe</label> <input type="password"
																name="form-password-edit" placeholder="Mot de passe..."
																class="form-password-edit form-control"
																id="form-password-edit">
														</div>
														<div class="form-group">
															<label class="sr-only" for="form-last-name-edit">Nom</label>
															<input type="text" name="form-last-name-edit"
																value="${utilisateur.getNom()}"
																class="form-last-name-edit form-control"
																id="form-last-name-edit">
														</div>
														<div class="form-group">
															<label class="sr-only" for="form-first-name-edit">Prénom</label>
															<input type="text" name="form-first-name-edit"
																value="${utilisateur.getPrenom()}"
																class="form-first-name-edit form-control"
																id="form-first-name-edit">
														</div>
														<div class="form-group">
															<label class="sr-only" for="form-datenaissance-edit">Date
																de naissance</label> <input type="text"
																name="form-datenaissance-edit"
																value="${utilisateur.getDateNaissance()}"
																class="form-datenaissance-edit form-control"
																id="form-datenaissance-edit">
														</div>
														<div class="form-group">
															<label class="sr-only" for="form-type-edit">Type</label>
															<input type="text" name="form-type-edit"
																value="${utilisateur.getType()}"
																class="form-type-edit form-control" id="form-type-edit">
														</div>
														<div class="form-group">
															<label class="sr-only" for="form-email-edit">Email</label>
															<input type="text" name="form-email-edit"
																value="${utilisateur.getMail()}"
																class="form-email-edit form-control"
																id="form-email-edit">
														</div>
														<button type="submit" class="btn1">Modifier</button>
													</form>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
							</tbody>
						</table>
					</div>
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
	<!-- #TODO vérif sur les champs modales ne marchent pas si vide etc -->
	<script src="/ProjetJEE-Forum/VUE/js/restrained.js"></script>
</body>
</html>