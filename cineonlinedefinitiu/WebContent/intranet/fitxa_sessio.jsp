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
		<form name="frm" action="GestioSessionsServlet" method="post">
		<%
		Sessio obj = (Sessio)session.getAttribute("sessio");
					if (obj != null)
					{
						%>
			
			<input type="Hidden" name="idSessio" value="<%= obj.getId() %>">
			data (dd/mm/aaaa)
			<input type="Text" name="data" value="<%= obj.getDataHora() %>" class="caixa_text" maxlength="10">
			<br><br>
			hora inici (hh:mm)<!--<%= obj.getHoraInici() %>-->
			<input type="Text" name="horaInici" class="caixa_text" value="" maxlength="5">
			<br><br>
			preu
			<input type="Text" name="preu" class="caixa_text" value="<%= obj.getPreu() %>" maxlength="5">
			<br><br>
			pel·lícula
			<input type="Text" name="idPelicula" class="caixa_text" value="<%= obj.getPelicula().getId() %>">
			<br><br>
			sala
			<input type="Text" name="idSala" class="caixa_text" value="<%= obj.getSala().getId() %>" />
			<br><br>
			<input type="Submit" name="accio" value="modificar" class="boto_accio" />
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
			<input type="Text" name="idPelicula" class="caixa_text" value="">
			<br><br>
			sala
			<input type="Text" name="idSala" class="caixa_text" value="" />
			<br><br>
			<%} %>
			</form>
		</span>
	</div>
	<div id="right">
	</div>	
<jsp:include page="esquelet_adm/footer.jsp"/>