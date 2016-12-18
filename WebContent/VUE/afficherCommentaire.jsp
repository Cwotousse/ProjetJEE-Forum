<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="be.forum.metier.Sujet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="be.forum.metier.SousCategorie"%>
<%@ page import="be.forum.pojo.SousCategoriePOJO"%>
<%@ page import="be.forum.dao.CategorieDAO"%>
<%@ page import="be.forum.pojo.CategoriePOJO"%>
<%@ page import="be.forum.dao.DAO"%>
<%@ page import="be.forum.dao.DAOFactory"%>
<%@ page import="be.forum.metier.Utilisateur"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${commentaire.getSujet().getTitre()}</title>
</head>
<body>
	<c:forEach items="${listeCommentaire}" var="commentaire">
		<div class="conteneur-message">
			<div class="bloc-avatar-msg">
				<div class="back-img-msg">
					<div>
						<a class="xXx "
							target="_blank" sl-processed="1"> <img
							class="user-avatar-msg"
							src="http://image.jeuxvideo.com/avatar-sm/default.jpg"
							alt="${commentaire.getUtilisateur().getPseudo()}">
						</a>
					</div>
				</div>
			</div>
			<div class="inner-head-content">
				<div class="bloc-header">
					<a class="xXx bloc-pseudo-msg text-user"
						target="_blank" sl-processed="1"> ${commentaire.getUtilisateur().getPseudo()} </a>
					<div class="bloc-mp-pseudo">
						<div class="bloc-options-msg"></div>
						<div class="bloc-date-msg">
							${commentaire.getDateCommentaire()}
						</div>
						<div class="bloc-contenu">
							<div class="txt-msg text-enrichi-forum ">
								${commentaire.getTexte()}
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
</body>
</html>