<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error Catàleg Pel·lícules</title>
</head>
<body>
	<%
	String error = request.getParameter("Error");
	if(request.getParameter("Error")!=null) {	%>
		<p>Error: <%=error%></p>
	<%}%>
</body>
</html>