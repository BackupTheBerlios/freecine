<%@ page import="gestioCinema.gestioSessions.Sessio" %>
<%@ page import="gestioCinema.gestioPelicules.Pelicula" %>
<%@ page import="gestioCinema.gestioSales.Sala" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Vector" %>

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
		<form name="frm" action="GestioSessionsServlet" method="post">
		<%		
						Vector pelicules = (Vector)session.getAttribute("llistaPelicules");
						Iterator itpel=pelicules.iterator();
						Vector sales = (Vector)session.getAttribute("llistaSales");
						Iterator itsal=sales.iterator();
		%>
		<%
		Sessio sessio = (Sessio)session.getAttribute("sessio");
		if (sessio != null)
		{%>
			
			<input type="Hidden" name="idSessio" value="<%= sessio.getId() %>">
			<%
			String[] dh = sessio.getDataHora().split(" ");		
			%>
			data (dd/mm/aa)
			<input type="Text" name="data" value="<%= dh[0] %>" class="caixa_text" maxlength="10">
			<br><br>
			hora inici (hh:mm)<!---->
			<input type="Text" name="horaInici" class="caixa_text" value="<%= dh[1] %>" maxlength="5">
			<br><br>
			preu
			<input type="Text" name="preu" class="caixa_text" value="<%= sessio.getPreu() %>" maxlength="5">
			</span>
			<br /><br />
			<span class="txt">
			<form name="frmadmsessions" action="GestioSessioServlet" method="post">
			<div id="caixa_sessions">							
					<div id="columna1">
						pel·lícula						
						<select name="selPelicula" class="fitxa">
						<%while(itpel.hasNext()){
							
							Pelicula pel = (Pelicula)itpel.next();
							String idpel="";
							String nompel="";
							String esaquest="";
							idpel = ""+pel.getId();
							nompel = pel.getTitol();
							if((""+sessio.getPelicula().getId()).equals(idpel))
							{
								esaquest="selected";
							}
							else
							{
								esaquest="";
							}
								
							%>
							<option	name="idPelicula" value="<%=idpel%>" <%= esaquest %> /> <%=nompel%> 
							<%							
						}
						
							%>
						</select>
						<br /><br />
						
						sala
						<select name="selSala" class="fitxa">
						<%while(itsal.hasNext()){
							
							Sala sal = (Sala)itsal.next();
							String idsal="";
							String nomsal="";
							String esaquest="";
							idsal = ""+sal.getId();
							nomsal = sal.getNomSala();
							if((""+sessio.getSala().getId()).equals(idsal))
							{
								esaquest="selected";
							}
							else
							{
								esaquest="";
								
							}
							
							%>
							<option	name="idSala" value="<%=idsal%>" <%= esaquest %> /> <%=nomsal%> 
							<%							
						}
							%>
						</select>
						<br /><br />
						<!-- <input type="Submit" name="accio" value="modificar" class="boto_accio" />-->
						<input type="Submit" name="accio" value="eliminar" class="boto_accio" />
					<%
					}
					else
					{%>
			<input type="Hidden" name="idSessio" value="-1">
			data (dd/mm/aaaa)
			<input type="Text" name="data" value="" class="caixa_text" maxlength="10">
			<br><br>
			data última sessio (dd/mm/aaaa)
			<input type="Text" name="dataUltima" class="caixa_text" value="" maxlength="10"> (rang)
			<br><br>
			hora inici (hh:mm)
			<input type="Text" name="horaInici" class="caixa_text" value="" maxlength="5">
			<br><br>
			preu
			<input type="Text" name="preu" class="caixa_text" value="" maxlength="5">
			<br><br>
			pel·lícula						
						<select name="idPelicula" class="fitxa">
						<%while(itpel.hasNext()){
							
							Pelicula pel = (Pelicula)itpel.next();
							String idpel="";
							String nompel="";
			
							idpel = ""+pel.getId();
							nompel = pel.getTitol();
							
								
							%>
							<option	name="id" value="<%=idpel%>"  /> <%=nompel%> 
							<%							
						}
						
							%>
						</select>
						<br /><br />
						
						sala
						<select name="idSala" class="fitxa">
						<%while(itsal.hasNext()){
							
							Sala sal = (Sala)itsal.next();
							String idsal="";
							String nomsal="";
							
							idsal = ""+sal.getId();
							nomsal = sal.getNomSala();
							
							
							%>
							<option	name="id2" value="<%=idsal%>"  /> <%=nomsal%> 
							<%							
						}
							%>
						</select>
						<br /><br />						
						<input type="Submit" name="accio" value="afegir" class="boto_accio" />
			<%}%>
			</form>
		</span>
	</div>
	<div id="right">
	</div>	
<jsp:include page="esquelet_adm/footer.jsp"/>