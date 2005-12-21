<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="gestioCinema.gestioSales.Sala" %>
<jsp:include page="esquelet_adm/header.jsp"/>
	<div id="top">
		<jsp:include page="esquelet_adm/top.jsp"/>		
	</div>
	<div id="left">
		<jsp:include page="esquelet_adm/menu.jsp"/>
	</div>
	<div id="center">
		<span class="txt_titol">			
			Sales
		</span>
		<br /><br />
		<span class="txt">
			<form name="frmnou" action="GestioSalesServlet" method="post">
					<input type="Hidden" name="accio" value="novaSala" />
					<input type="Submit" name="opcio_menu" class="boto_enllac" value="nova sala" />
				</form>
			<% 
				Vector llista = new Vector();	
				llista = (Vector) session.getAttribute("llistaSales");
				if(llista!=null)
				{
					Iterator it = llista.iterator();
					int cont = 0;
				%>
					<table cellspacing="0">
					<tr>
						<td></td>
						<td><strong>nom de la sala</strong></td>
						<td><strong>número de butaques</strong></td>
					</tr>
				<%
					while(it.hasNext())
					{
						Sala obj = (Sala)it.next();
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
					<tr  class="<%= estilfila %>">
						<td>
							<form name="frmfitxa" action="GestioSalesServlet" method="post" class="boto_horari">
								<input type="Hidden" name="accio" value="detallSala" />
								<input type="Hidden" name="idSala" value="<%= ""+obj.getId() %>" />
								<input type="Submit" name="opcio_menu" class="boto_enllac" value="fitxa" />
							</form>
						</td>
						<td><%=obj.getNomSala()%></td>
						<td><%=obj.getNumButaques()%></td>
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
					No hi ha cap sala.
					<%
				}
				%>
		</span>
	</div>
	<div id="right">
	</div>	
<jsp:include page="esquelet_adm/footer.jsp"/>
