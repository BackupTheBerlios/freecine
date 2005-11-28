<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="gestioCinema.gestioPelicules.Pelicula" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Catàleg pel·lícules</title>
</head>
<body>

<table>
	
	<% 	Vector catalegPelicules = (Vector) session.getAttribute("catalegPelicules");
		Iterator itPelicules = catalegPelicules.iterator();
		while(itPelicules.hasNext()){
			Pelicula pelicula = (Pelicula)itPelicules.next();
		%>
		<tr>
			<td><%=pelicula.getTitol()%> </td>
			<td><%=pelicula.getDirector()%></td>
			<td><%=pelicula.getDurada()%></td>
			<td><%=pelicula.getEdatRecomenada()%></td>
			<td><a href="./getPeliculaServlet?id="+<%=pelicula.getId()%>>vereu detall</a></td>
		</tr>
		<%}%>
</table>
</body>
</html>