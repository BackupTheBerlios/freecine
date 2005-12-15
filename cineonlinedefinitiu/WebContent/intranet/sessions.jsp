<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="gestioCinema.gestioSessions.Sessio" %>
<jsp:include page="esquelet_adm/header.jsp"/>
	<div id="top">
		<jsp:include page="esquelet_adm/top.jsp"/>		
	</div>
	<div id="left">
		<jsp:include page="esquelet_adm/menu.jsp"/>
	</div>
	<div id="center">
		<span class="txt_titol">			
			Sessions
		</span>
		<br /><br />
		<span class="txt">
		<% 
				Vector llista = new Vector();	
				llista = (Vector) session.getAttribute("llistaSessions");
				if(llista.size()!=0)
				{
					Iterator it = llista.iterator();
				%>
			<table border="2">
				<tr>
					<td></td>
					<td><strong>data i hora</strong></td>
					<td><strong>pel·lícula</strong></td>
					<td><strong>sala</strong></td>
				</tr>
				<%
					while(it.hasNext())
					{
						Sessio obj = (Sessio)it.next();
				%>
				<tr>
					<td>
							<form name="frmfitxa" action="GestioSessionsServlet" method="post" class="boto_horari">
								<input type="Hidden" name="accio" value="detallSessio" />
								<input type="Hidden" name="idSessio" value="<%= ""+ obj.getId() %>" />
								<input type="Submit" name="opcio_menu" class="boto_menu" value="fitxa" />
							</form>
					</td>
					<td><%= obj.getDataHora() %></td>		
					<td><%= ""+ obj.getPelicula().getTitol() %></td>
					<td><%= ""+ obj.getSala().getNomSala() %></td>
				</tr>
				<%
					}
					%>
			</table>
			<%
					}
					%>
		</span>
	</div>
	<div id="right">
	</div>	
<jsp:include page="esquelet_adm/footer.jsp"/>