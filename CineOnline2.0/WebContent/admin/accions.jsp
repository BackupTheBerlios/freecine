<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String accio = request.getParameter("opcio_accio");	
if (accio != null)
{
	if (accio.compareTo("afegir")==0)
	{
		
	}
	else
	{
		if (accio.compareTo("modificar")==0)
		{
			
		}
		else
		{
			if (accio.compareTo("eliminar")==0)
			{
				
			}
			else
			{
				
			}
			
		}
	}
%>
ha arribat a accions: <%=  accio %>
<%
}
else
{
%>
no ha arribat res
<%
}%>
</body>
</html>