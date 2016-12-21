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
body {
	margin-top: 20px;
	background: #eee;
}

.white-bg {
	background-color: #ffffff;
}

.page-heading {
	border-top: 0;
	padding: 0 10px 20px 10px;
}

.forum-post-container .media {
	margin: 10px 10px 10px 10px;
	padding: 20px 10px 20px 10px;
	border-bottom: 1px solid #f1f1f1;
}

.forum-avatar {
	float: left;
	margin-right: 20px;
	text-align: center;
	width: 110px;
}

.forum-avatar .img-circle {
	height: 48px;
	width: 48px;
}

.author-info {
	color: #676a6c;
	font-size: 11px;
	margin-top: 5px;
	text-align: center;
}

.forum-post-info {
	padding: 9px 12px 6px 12px;
	background: #f9f9f9;
	border: 1px solid #f1f1f1;
}

.media-body>.media {
	background: #f9f9f9;
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
}

.forum-item {
	margin: 10px 0;
	padding: 10px 0 20px;
	border-bottom: 1px solid #f1f1f1;
}

.views-number {
	font-size: 24px;
	line-height: 18px;
	font-weight: 400;
}

.forum-container, .forum-post-container {
	padding: 30px !important;
}

.forum-item small {
	color: #999;
}

.forum-item .forum-sub-title {
	color: #999;
	margin-left: 50px;
}

.forum-title {
	margin: 15px 0 15px 0;
}

.forum-info {
	text-align: center;
}

.forum-desc {
	color: #999;
}

.forum-icon {
	float: left;
	width: 30px;
	margin-right: 20px;
	text-align: center;
}

a.forum-item-title {
	color: inherit;
	display: block;
	font-size: 18px;
	font-weight: 600;
}

a.forum-item-title:hover {
	color: inherit;
}

.forum-icon .fa {
	font-size: 30px;
	margin-top: 8px;
	color: #9b9b9b;
}

.forum-item.active .fa {
	color: #1ab394;
}

.forum-item.active a.forum-item-title {
	color: #1ab394;
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
	background-color: #ffffff;
	border-color: #e7eaec;
	border-image: none;
	border-style: solid solid none;
	border-width: 3px 0 0;
	color: inherit;
	margin-bottom: 0;
	padding: 14px 15px 7px;
	min-height: 48px;
}

.ibox-content {
	background-color: #ffffff;
	color: inherit;
	padding: 15px 20px 20px 20px;
	border-color: #e7eaec;
	border-image: none;
	border-style: solid solid none;
	border-width: 1px 0;
}

.ibox-footer {
	color: inherit;
	border-top: 1px solid #e7eaec;
	font-size: 90%;
	background: #ffffff;
	padding: 10px 15px;
}

.message-input {
	height: 90px !important;
}

.form-control, .single-line {
	background-color: #FFFFFF;
	background-image: none;
	border: 1px solid #e5e6e7;
	border-radius: 1px;
	color: inherit;
	display: block;
	padding: 6px 12px;
	transition: border-color 0.15s ease-in-out 0s, box-shadow 0.15s
		ease-in-out 0s;
	width: 100%;
	font-size: 14px;
}

.text-navy {
	color: #1ab394;
}

.mid-icon {
	font-size: 66px !important;
}

.m-b-sm {
	margin-bottom: 10px;
}
</style>
</head>

<body>
	<c:set var="context" value="${pageContext.request.contextPath}" />

	<c:import url="include/navbar1.jsp" />
	<!-- container -->
	<div class="container">
		<!-- row -->
		<div class="row">
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
							<div class="forum-item active">
								<div class="row">
									<div class="col-md-9">
										<div class="forum-icon">
											<i class="fa fa-shield"></i>
										</div>
										<!-- titre du sujet avec un href -->
										<a
											href="<%=request.getContextPath()%>/displaycomments?nomSujet=${sujet.getTitre()}&nomSousCategorie=${sujet.getSousCategorie().getTitre()}&pseudoAuteur=${sujet.getUtilisateur().getPseudo()}&dateSujet=${sujet.getDateSujet()}"
											class="forum-item-title" title="${sujet.getTitre()}">${sujet.getTitre()}</a>

										<div class="forum-sub-title">Talk about sports,
											entertainment, music, movies, your favorite color, talk about
											enything.</div>
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