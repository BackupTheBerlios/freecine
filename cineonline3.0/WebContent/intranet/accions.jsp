<%
String tipusObjecte = request.getParameter("opcio_menu");
if (tipusObjecte != null)
{
	if (tipusObjecte.compareTo("Pel�l�cules")==0)
	{

	}
	else
	{
		if (tipusObjecte.compareTo("Sales")==0)
		{
			
		}
		else
		{
			if (tipusObjecte.compareTo("Sessions")==0)
			{
				
			}
			else
			{
				
			}
			
		}
	}
	%>
	<br>
	L'objecte a tractar �s : <%=  tipusObjecte %>
<%
}
else
{
%>
<br>
No ha arribat cap objecte
<%
}

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
<br>
L'acci� a fer �s accions: <%=  accio %>
<%
}
else
{
%>
<br>
No ha arribat cap acci�
<%
}

String id = request.getParameter("id");	
if (id != null)
{
%>
<br>
L'id del objecte �s: <%=  id %>
<%
}
else
{
%>
<br>
No ha arribat cap id
<%
}
%>