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
			Generes
		</span>
		<br /><br />
		<span class="txt">
			<form name="frmnou" action="GestioPeliculesServlet" method="post" class="boto_horari">
				<input type="Hidden" name="accio" value="novaGenere" />
				<input type="Submit" name="opcio_menu" class="boto_menu" value="afegir genere" />
			</form>
			<% 
			Vector llista = new Vector();	
			llista = (Vector) session.getAttribute("llistaGeneres");
				if(llista!=null)
				{
					
					Iterator it = llista.iterator();
				%>
					<table border="2">
					<tr>
						<td></td>
						<td><strong>genere</strong></td>
					</tr>
				<%
					while(it.hasNext())
					{
						Vector obj = (Vector)it.next();
					 	Iterator itObj = obj.iterator();
				%>
					<tr>
						<td>
							<form name="frmfitxa" action="GestioPeliculesServlet" method="post" class="boto_horari">
								<input type="Hidden" name="accio" value="detallGenere" />
								<input type="Hidden" name="idGenere" value="<%= ""+ itObj.next() %>" />
								<input type="Submit" name="opcio_menu" class="boto_menu" value="fitxa" />
							</form>
						</td>
						<td><%= ""+itObj.next()%></td>
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
					No hi ha cap genere.
					<%
				}
				%>
		</span>
	</div>
	<div id="right">
	</div>	
<jsp:include page="esquelet_adm/footer.jsp"/>
