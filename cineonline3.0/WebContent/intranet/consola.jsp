<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Iterator" %>
<jsp:include page="esquelet_adm/header.jsp"/>
	<div id="top">
		<jsp:include page="esquelet_adm/top.jsp"/>		
	</div>
	<div id="left">
		<jsp:include page="esquelet_adm/menu.jsp"/>
	</div>
	<div id="center">
		<span class="txt_titol">			
			Consulta
		</span>
		<%
		String query = request.getParameter("query");
		if(query==null){
			query="";
		}
		Vector resultat = (Vector) session.getAttribute("resultat");
		%>
		<span class="txt">
			<form name="frmConsola" action="GestioCinemaServlet" method="post">				
				<input type="Hidden" name="accio" value="consulta" />
				<textarea rows=8 cols=80 name="query"><%= query  %></textarea>
				<br /><br />
				<input type="Submit" name="boto" value="ok" class="boto_venda">	
			</form>
			<% 				
				if(resultat!=null)
				{
					Iterator it = resultat.iterator();
				%>
					<table border="2">
				<%
					while(it.hasNext())
					{
						Vector tupla = (Vector)it.next();
						Iterator ItTupla = tupla.iterator();
				%>
					<tr>
						<%while(ItTupla.hasNext()){ %>
						<td><%= ""+ItTupla.next() %></td>						
						<%} %>
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
					No hi ha cap resultat.
					<%
				}
				%>
		</span>
	</div>

<jsp:include page="esquelet_adm/footer.jsp"/>