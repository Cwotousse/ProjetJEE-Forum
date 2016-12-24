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
<style>
body {
	margin-top: 20px;
}

/* USER LIST TABLE */
.user-list tbody td>img {
	position: relative;
	max-width: 50px;
	float: left;
	margin-right: 15px;
}

.user-list tbody td .user-link {
	display: block;
	font-size: 18px;
	padding-top: 3px;
	margin-left: 60px;
}

.user-list tbody td .user-subhead {
	font-size: 15px;
	font-style: italic;
}

/* TABLES */
.table {
	border-collapse: separate;
}

.table-hover>tbody>tr:hover>td, .table-hover>tbody>tr:hover>th {
	background-color: #eee;
}

.table thead>tr>th {
	border-bottom: 1px solid #C2C2C2;
	padding-bottom: 0;
	/*taille titre colonne*/
	font-size: 17px;
}

.table tbody>tr>td {
	/* taille info row*/
	font-size: 17px;
	background: #f5f5f5;
	border-top: 10px solid #fff;
	vertical-align: middle;
	padding: 12px 8px;
}

.table tbody>tr>td:first-child, .table thead>tr>th:first-child {
	padding-left: 20px;
}

.table thead>tr>th span {
	border-bottom: 2px solid #C2C2C2;
	display: inline-block;
	padding: 0 5px;
	padding-bottom: 5px;
	font-weight: normal;
}

.table thead>tr>th>a span {
	color: #344644;
}

.table thead>tr>th>a span:after {
	content: "\f0dc";
	font-family: FontAwesome;
	font-style: normal;
	font-weight: normal;
	text-decoration: inherit;
	margin-left: 5px;
	font-size: 0.75em;
}

.table thead>tr>th>a.asc span:after {
	content: "\f0dd";
}

.table thead>tr>th>a.desc span:after {
	content: "\f0de";
}

.table thead>tr>th>a:hover span {
	text-decoration: none;
	color: #2bb6a3;
	border-color: #2bb6a3;
}

.table.table-hover tbody>tr>td {
	-webkit-transition: background-color 0.15s ease-in-out 0s;
	transition: background-color 0.15s ease-in-out 0s;
}

.table tbody tr td .call-type {
	display: block;
	font-size: 0.75em;
	text-align: center;
}

.table tbody tr td .first-line {
	line-height: 1.5;
	font-weight: 400;
	font-size: 1.125em;
}

.table tbody tr td .first-line span {
	font-size: 0.875em;
	color: #969696;
	font-weight: 300;
}

.table tbody tr td .second-line {
	font-size: 0.875em;
	line-height: 1.2;
}

.table a.table-link {
	margin: 0 5px;
	/*icone*/
	font-size: 20px;
}

.table a.table-link:hover {
	text-decoration: none;
	color: #2aa493;
}

.table a.table-link.danger {
	color: #fe635f;
}

.table a.table-link.danger:hover {
	color: #dd504c;
}
</style>
</head>

<body>
	<c:import url="/displayusers" />
	<c:set var="context" value="${pageContext.request.contextPath}" />
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
															<label class="sr-only" for="form-datenaissance-edit">Date de naissance</label>
															<input type="text" name="form-datenaissance-edit"
																value="${utilisateur.getDateNaissance()}"
																class="form-datenaissance-edit form-control" id="form-datenaissance-edit">
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
								<!-- <tr>
										<td><img
											src="http://bootdey.com/img/Content/avatar/avatar1.png"
											alt=""> <a href="#" class="user-link">Mila Kunis</a> <span
											class="user-subhead">Admin</span></td>
										<td>2013/08/08</td>
										<td class="text-center"><span class="label label-default">Inactive</span>
										</td>
										<td><a href="#">mila@kunis.com</a></td>
										<td style="width: 20%;"><a href="#" class="table-link">
												<span class="fa-stack"> <i
													class="fa fa-square fa-stack-2x"></i> <i
													class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
											</span>
										</a> <a href="#" class="table-link"> <span class="fa-stack">
													<i class="fa fa-square fa-stack-2x"></i> <i
													class="fa fa-pencil fa-stack-1x fa-inverse"></i>
											</span>
										</a> <a href="#" class="table-link danger"> <span
												class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i>
													<i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
											</span>
										</a></td>
									</tr>
									<tr>
										<td><img
											src="http://bootdey.com/img/Content/avatar/avatar2.png"
											alt=""> <a href="#" class="user-link">George
												Clooney</a> <span class="user-subhead">Member</span></td>
										<td>2013/08/12</td>
										<td class="text-center"><span class="label label-success">Active</span>
										</td>
										<td><a href="#">marlon@brando.com</a></td>
										<td style="width: 20%;"><a href="#" class="table-link">
												<span class="fa-stack"> <i
													class="fa fa-square fa-stack-2x"></i> <i
													class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
											</span>
										</a> <a href="#" class="table-link"> <span class="fa-stack">
													<i class="fa fa-square fa-stack-2x"></i> <i
													class="fa fa-pencil fa-stack-1x fa-inverse"></i>
											</span>
										</a> <a href="#" class="table-link danger"> <span
												class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i>
													<i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
											</span>
										</a></td>
									</tr>
									<br />
									<tr>
										<td><img
											src="http://bootdey.com/img/Content/avatar/avatar3.png"
											alt=""> <a href="#" class="user-link">Ryan Gossling</a>
											<span class="user-subhead">Registered</span></td>
										<td>2013/03/03</td>
										<td class="text-center"><span class="label label-danger">Banned</span>
										</td>
										<td><a href="#">jack@nicholson</a></td>
										<td style="width: 20%;"><a href="#" class="table-link">
												<span class="fa-stack"> <i
													class="fa fa-square fa-stack-2x"></i> <i
													class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
											</span>
										</a> <a href="#" class="table-link"> <span class="fa-stack">
													<i class="fa fa-square fa-stack-2x"></i> <i
													class="fa fa-pencil fa-stack-1x fa-inverse"></i>
											</span>
										</a> <a href="#" class="table-link danger"> <span
												class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i>
													<i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
											</span>
										</a></td>
									</tr>
									<tr>
										<td><img
											src="http://bootdey.com/img/Content/avatar/avatar4.png"
											alt=""> <a href="#" class="user-link">Emma Watson</a> <span
											class="user-subhead">Registered</span></td>
										<td>2004/01/24</td>
										<td class="text-center"><span class="label label-warning">Pending</span>
										</td>
										<td><a href="#">humphrey@bogart.com</a></td>
										<td style="width: 20%;"><a href="#" class="table-link">
												<span class="fa-stack"> <i
													class="fa fa-square fa-stack-2x"></i> <i
													class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
											</span>
										</a> <a href="#" class="table-link"> <span class="fa-stack">
													<i class="fa fa-square fa-stack-2x"></i> <i
													class="fa fa-pencil fa-stack-1x fa-inverse"></i>
											</span>
										</a> <a href="#" class="table-link danger"> <span
												class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i>
													<i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
											</span>
										</a></td>
									</tr>
									<tr>
										<td><img
											src="http://bootdey.com/img/Content/avatar/avatar5.png"
											alt=""> <a href="#" class="user-link">Robert Downey
												Jr.</a> <span class="user-subhead">Admin</span></td>
										<td>2013/12/31</td>
										<td class="text-center"><span class="label label-success">Active</span>
										</td>
										<td><a href="#">spencer@tracy</a></td>
										<td style="width: 20%;"><a href="#" class="table-link">
												<span class="fa-stack"> <i
													class="fa fa-square fa-stack-2x"></i> <i
													class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
											</span>
										</a> <a href="#" class="table-link"> <span class="fa-stack">
													<i class="fa fa-square fa-stack-2x"></i> <i
													class="fa fa-pencil fa-stack-1x fa-inverse"></i>
											</span>
										</a> <a href="#" class="table-link danger"> <span
												class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i>
													<i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
											</span>
										</a></td>
									</tr>
									<tr>
										<td><img
											src="http://bootdey.com/img/Content/avatar/avatar6.png"
											alt=""> <a href="#" class="user-link">Mila Kunis</a> <span
											class="user-subhead">Admin</span></td>
										<td>2013/08/08</td>
										<td class="text-center"><span class="label label-default">Inactive</span>
										</td>
										<td><a href="#">mila@kunis.com</a></td>
										<td style="width: 20%;"><a href="#" class="table-link">
												<span class="fa-stack"> <i
													class="fa fa-square fa-stack-2x"></i> <i
													class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
											</span>
										</a> <a href="#" class="table-link"> <span class="fa-stack">
													<i class="fa fa-square fa-stack-2x"></i> <i
													class="fa fa-pencil fa-stack-1x fa-inverse"></i>
											</span>
										</a> <a href="#" class="table-link danger"> <span
												class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i>
													<i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
											</span>
										</a></td>
									</tr>
									<tr>
										<td><img
											src="http://bootdey.com/img/Content/avatar/avatar7.png"
											alt=""> <a href="#" class="user-link">George
												Clooney</a> <span class="user-subhead">Member</span></td>
										<td>2013/08/12</td>
										<td class="text-center"><span class="label label-success">Active</span>
										</td>
										<td><a href="#">marlon@brando.com</a></td>
										<td style="width: 20%;"><a href="#" class="table-link">
												<span class="fa-stack"> <i
													class="fa fa-square fa-stack-2x"></i> <i
													class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
											</span>
										</a> <a href="#" class="table-link"> <span class="fa-stack">
													<i class="fa fa-square fa-stack-2x"></i> <i
													class="fa fa-pencil fa-stack-1x fa-inverse"></i>
											</span>
										</a> <a href="#" class="table-link danger"> <span
												class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i>
													<i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
											</span>
										</a></td>
									</tr>
									<tr>
										<td><img
											src="http://bootdey.com/img/Content/avatar/avatar1.png"
											alt=""> <a href="#" class="user-link">Ryan Gossling</a>
											<span class="user-subhead">Registered</span></td>
										<td>2013/03/03</td>
										<td class="text-center"><span class="label label-danger">Banned</span>
										</td>
										<td><a href="#">jack@nicholson</a></td>
										<td style="width: 20%;"><a href="#" class="table-link">
												<span class="fa-stack"> <i
													class="fa fa-square fa-stack-2x"></i> <i
													class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
											</span>
										</a> <a href="#" class="table-link"> <span class="fa-stack">
													<i class="fa fa-square fa-stack-2x"></i> <i
													class="fa fa-pencil fa-stack-1x fa-inverse"></i>
											</span>
										</a> <a href="#" class="table-link danger"> <span
												class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i>
													<i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
											</span>
										</a></td>
									</tr>
									<tr>
										<td><img
											src="http://bootdey.com/img/Content/avatar/avatar1.png"
											alt=""> <a href="#" class="user-link">Emma Watson</a> <span
											class="user-subhead">Registered</span></td>
										<td>2004/01/24</td>
										<td class="text-center"><span class="label label-warning">Pending</span>
										</td>
										<td><a href="#">humphrey@bogart.com</a></td>
										<td style="width: 20%;"><a href="#" class="table-link">
												<span class="fa-stack"> <i
													class="fa fa-square fa-stack-2x"></i> <i
													class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
											</span>
										</a> <a href="#" class="table-link"> <span class="fa-stack">
													<i class="fa fa-square fa-stack-2x"></i> <i
													class="fa fa-pencil fa-stack-1x fa-inverse"></i>
											</span>
										</a> <a href="#" class="table-link danger"> <span
												class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i>
													<i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
											</span>
										</a></td>
									</tr>
									<tr>
										<td><img
											src="http://bootdey.com/img/Content/avatar/avatar6.png"
											alt=""> <a href="#" class="user-link">Robert Downey
												Jr.</a> <span class="user-subhead">Admin</span></td>
										<td>2013/12/31</td>
										<td class="text-center"><span class="label label-success">Active</span>
										</td>
										<td><a href="#">spencer@tracy</a></td>
										<td style="width: 20%;"><a href="#" class="table-link">
												<span class="fa-stack"> <i
													class="fa fa-square fa-stack-2x"></i> <i
													class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
											</span>
										</a> <a href="#" class="table-link"> <span class="fa-stack">
													<i class="fa fa-square fa-stack-2x"></i> <i
													class="fa fa-pencil fa-stack-1x fa-inverse"></i>
											</span>
										</a> <a href="#" class="table-link danger"> <span
												class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i>
													<i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
											</span>
										</a></td>
									</tr>-->
							</tbody>
						</table>
					</div>
					<ul class="pagination pull-right">
						<li><a href="#"><i class="fa fa-chevron-left"></i></a></li>
						<li><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#"><i class="fa fa-chevron-right"></i></a></li>
					</ul>
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
	<script src="/ProjetJEE-Forum/VUE/restrained/js/restrained.js"></script>
</body>
</html>