<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="gestioCinema.gestioUsuaris.Usuari" %>
<jsp:include page="esquelet_adm/header.jsp"/>
	<div id="top">
		<jsp:include page="esquelet_adm/top.jsp"/>		
	</div>
	<div id="left">
		<jsp:include page="esquelet_adm/menu.jsp"/>
	</div>
	<div id="center">
		<span class="txt_titol">			
			Usuaris de CineOnline
		</span>
		<br /><br />
		<span class="txt">				
				<% 
				Vector llista = new Vector();	
				llista = (Vector) session.getAttribute("llistaUsuaris");
				if(llista!=null)
				{
					Iterator it = llista.iterator();
					int cont = 0;
				%>
					<table cellspacing="0">
					<tr>
						<td></td>
						<td><strong>nom</strong></td>
						<td><strong>tipus</strong></td>
					</tr>
				<%
					while(it.hasNext())
					{
						Usuari us = (Usuari)it.next();
						String 	estilfila = "";
					 	cont ++;
					 	if(cont%2==0)
					 	{
					 		estilfila = "parell";
					 	}
					 	else
					 	{
					 		estilfila = "";
					 	}
				%>
					<tr class="<%= estilfila %>">
						<td>
							<form name="frmfitxa" action="GestioUsuarisServlet" method="post" class="boto_horari">
								<input type="Hidden" name="accio" value="detallUsuari" />
								<input type="Hidden" name="idPelicula" value="<%= ""+us.getId() %>" />
								<input type="Submit" name="opcio_menu" class="boto_enllac" value="fitxa" />
							</form>
						</td>
						<td><%= us.getLogin()%></td>
						<td><%= us.getRol()%></td>
					</tr>
				<%
					}
					%>
					</table>
					<%
				}
				else
				{
					%>
					No hi ha cap usuari.
					<%
				}
				%>
		</span>	
	</div>
	<div id="right">
			<jsp:include page="esquelet_adm/navegadreta.jsp"/>
	</div>	
<jsp:include page="esquelet_adm/footer.jsp"/>