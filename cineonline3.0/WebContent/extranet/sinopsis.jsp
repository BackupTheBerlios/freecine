<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="gestioCinema.gestioPelicules.Pelicula" %>
<jsp:include page="esquelet/header.jsp"/>
	<div id="top">
		<jsp:include page="esquelet/top.jsp"/>		
	</div>
	<div id="left">
		<jsp:include page="esquelet/menu.jsp"/>		
	</div>
	<div id="center">
		<span class="txt_titol">			
			Sinòpsis
		</span>
		<br /><br />
		<span class="txt">			
			Si abans de comprar o reservar l'entrada vols triar bé la teva pel·lícula consulta les sinòpsis!!
			<br /><br />
			Cerca la teva pel·lícula
			<br /><br />
			<form name="frmsinopsis_query" action="javascript:alert('Fent la consulta...');">
					títol <input type="Text" name="titol" maxlength="255" />
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
			<% 
				Vector llista = new Vector();	
				llista = (Vector) session.getAttribute("llistaPelicules");
				if(llista!=null)
				{
					Iterator it = llista.iterator();
				%>
					<table cellspacing="0">
					<tr>
						<td></td>
						<td><strong>títol</strong></td>
						<td><strong>títol original</strong></td>
						<td><strong>nacionalitat</strong></td>
						<td><strong>director</strong></td>
						<td><strong>any</strong></td>
					</tr>
				<%
				int i = 0;
				String classeFila = "";
				while(it.hasNext())
					{
						Pelicula pelicula = (Pelicula)it.next();
						if(i%2==0){classeFila = "parell";}else{classeFila = "";}
						i ++;
				%>
					<tr class="<%= classeFila %>">
					<form name="frmfitxa" action="GestioClientServlet" method="post">
						<td>							
								<input type="Hidden" name="accio" value="detallPelicula" />
								<input type="Hidden" name="idPelicula" value="<%= ""+pelicula.getId() %>" />
								<input type="Submit" name="opcio_menu" class="boto_enllac" value="fitxa" />

						</td>
													</form>
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
		<jsp:include page="esquelet/navegadreta.jsp"/>
	</div>	
<jsp:include page="esquelet/footer.jsp"/>