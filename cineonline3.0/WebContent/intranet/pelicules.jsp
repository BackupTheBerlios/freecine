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
			Pel�l�cules
		</span>
		<br /><br />
		<span class="txt">
				Cerca la teva pel�l�cula
				<br /><br />
				<form name="frmsinopsis_query" action="javascript:alert('Fent la consulta...');">
					t�tol <input type="Text" name="titol" maxlength="255" />
					director <input type="Text" name="director" maxlength="255" />
					any <input type="Text" name="any" maxlength="4" size="4" id="any" style="width:35px" />					 
					en 
					<select name="encartellera" style="width:80px">
						<option value="cartellera" />cartellera
						<option value="totes" />totes
					</select>
					<br /><br />
					<center><input type="Submit" name="cerca" value="cerca" class="boto_enllac" /></center>
				</form>
				<form name="frmnou" action="GestioPeliculesServlet" method="post" class="boto_horari">
					<input type="Hidden" name="accio" value="novaPelicula" />
					<input type="Submit" name="opcio_menu" class="boto_enllac" value="nova pel�l�cula" />
				</form>
				<% 
				Vector llista = new Vector();	
				llista = (Vector) session.getAttribute("llistaPelicules");
				if(llista!=null)
				{
					Iterator it = llista.iterator();
					int cont = 0;
				%>
					<table cellspacing="0">
					<tr>
						<td></td>
						<td><strong>t�tol</strong></td>
						<td><strong>t�tol original</strong></td>
						<td><strong>director</strong></td>
						<td><strong>any</strong></td>
					</tr>
				<%
					while(it.hasNext())
					{
						Pelicula pelicula = (Pelicula)it.next();
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
							<form name="frmfitxa" action="GestioPeliculesServlet" method="post" class="boto_horari">
								<input type="Hidden" name="accio" value="detallPelicula" />
								<input type="Hidden" name="idPelicula" value="<%= ""+pelicula.getId() %>" />
								<input type="Submit" name="opcio_menu" class="boto_enllac" value="fitxa" />
							</form>
						</td>
						<td><%=pelicula.getTitol()%></td>
						<td><%=pelicula.getTitolOriginal()%></td>
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
					No hi ha cap pel�l�cula.
					<%
				}
				%>
		</span>	
	</div>
	<div id="right">
			<jsp:include page="esquelet_adm/navegadreta.jsp"/>
	</div>	
<jsp:include page="esquelet_adm/footer.jsp"/>