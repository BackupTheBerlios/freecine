<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="gestioCinema.gestioPelicules.Pelicula" %>
<jsp:include page="esquelet_adm/header.jsp"/>
	<div id="top">
		<jsp:include page="esquelet_adm/top.jsp"/>		
	</div>
	<div id="left">
		<jsp:include page="esquelet_adm/menu.jsp"/>
	</div>
	<div id="center">
		<span class="txt_titol">			
			Pel·lícules
		</span>
		<br /><br />
		<span class="txt">
				Cerca la teva pel·lícula
				<br /><br />
				<form name="frmsinopsis_query" action="javascript:alert('Fent la consulta...');">
					títol <input type="Text" name="titol" maxlength="255" class="caixa_text" />
					director <input type="Text" name="director" maxlength="255" class="caixa_text" />
					any <input type="Text" name="any" maxlength="4" class="caixa_text" size="4" id="any" />
					entre
					<select name="encartellera" class="caixa_text">
						<option value="cartellera" />cartellera
						<option value="totes" />totes
					</select>
					<br /><br />
					<center><input type="Submit" name="cerca" value="cerca" class="boto_venda" /></center>
				</form>
				<form name="frmnou" action="GestioPeliculesServlet" method="post" class="boto_horari">
					<input type="Hidden" name="accio" value="novaPelicula" />
					<input type="Submit" name="opcio_menu" class="boto_menu" value="afegir peli" />
				</form>
				<% 
				Vector llista = new Vector();	
				llista = (Vector) session.getAttribute("llistaPelicules");
				if(llista!=null)
				{
					Iterator it = llista.iterator();
				%>
					<table border="2">
					<tr>
						<td></td>
						<td><strong>títol</strong></td>
						<td><strong>títol original</strong></td>
						<td><strong>nacionalitat</strong></td>
						<td><strong>director</strong></td>
						<td><strong>any</strong></td>
					</tr>
				<%
					while(it.hasNext())
					{
						Pelicula pelicula = (Pelicula)it.next();
				%>
					<tr>
						<td>
							<form name="frmfitxa" action="GestioPeliculesServlet" method="post" class="boto_horari">
								<input type="Hidden" name="accio" value="detallPelicula" />
								<input type="Hidden" name="idPelicula" value="<%= ""+pelicula.getId() %>" />
								<input type="Submit" name="opcio_menu" class="boto_menu" value="fitxa" />
							</form>
						</td>
						<td><%=pelicula.getTitol()%></td>
						<td><%=pelicula.getTitolOriginal()%></td>
						<td><%=pelicula.getNacionalitat()%></td>
						<td><%=pelicula.getDirector()%></td>
						<td><%=pelicula.getAnny()%></td>
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
					No hi ha cap pel·lícula.
					<%
				}
				%>
		</span>	
	</div>
	<div id="right">
			<jsp:include page="esquelet_adm/navegadreta.jsp"/>
	</div>	
<jsp:include page="esquelet_adm/footer.jsp"/>