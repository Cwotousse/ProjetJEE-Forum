<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!--  truc de ouf -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="be.forum.metier.SousCategorie"%>
<%@ page import="be.forum.metier.Utilisateur"%>
<%@ page import="be.forum.metier.Sujet"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sujet</title>
</head>
<body>
	<%
		//Comme toi khey
		/*try {
			if (request.getAttribute("listeSujets") != null) {
				ArrayList<Sujet> listSujet = (ArrayList<Sujet>) request.getAttribute("listeSujets");

				// print the information about every category of the list
				for (Sujet LS : listSujet) {
					out.println("<tr>");
					out.println("<td>" + "x" + "</td>");
					out.println("<td>" + LS.getTitre() + "</td>");
					out.println("<td>" + LS.getUtilisateur().getPseudo() + "</td>");
					out.println("<td>" + LS.getDateSujet() + "</td>");
					out.println("</tr>");

				}
			} else {
				out.println("no data in array");
			}
		} catch (Exception e) {
			out.println("Exception : " + e);
		}*/
	%>
	<!-- Et en utilisant du JSTL -->
	<c:forEach items="${listeSujets}" var="sujet">
    	${sujet.getTitre()}<br/>
    	${sujet.getDateSujet()}<br/>
    	${sujet.getUtilisateur().getPseudo()}<br/>
    	${sujet.getSousCategorie().getTitre()}<br/>
	</c:forEach>

</body>
</html>