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
			Fitxa de sala
		</span>
		<br /><br />
		<span class="txt">
			<form name="frmadmpelicules" action="GestioSalesServlet" method="post">
							
			<div id="caixa_pelicules">
				<%
					Sala obj = (Sala)session.getAttribute("sala");
					if (obj != null)
					{
						
						%>
						<input type="Hidden" name="idSala" value="<%= ""+obj.getId() %>" />
						nom de la sala
						<input type="Text" name="nomSala" class="caixa_text" value="<%= obj.getNomSala() %>" />
						<br /><br />
						número butaques
						<input type="Text" name="numButaques" readonly class="caixa_text" value="<%= obj.getNumButaques() %>" maxlength="4" />
						<br /><br />
						número butaques operatives
						<input type="Text" name="numOperatives" readonly class="caixa_text" value="<%= obj.numButaquesOperatives() %>" maxlength="4" />
						<br /><br />
						columnes malla
						<input type="Text" name="numMaxColumnes" class="caixa_text" value="<%= obj.getNumMaxColumnes() %>" maxlength="3" />
						<br /><br />
						files malla
						<input type="Text" name="numMaxFiles" class="caixa_text" value="<%= obj.getNumMaxFiles() %>" maxlength="3" />
						<br /><br />
						descripció
						<textarea name="descripcio"  class="caixa_text" cols="30"><%= obj.getDescripcio() %></textarea>						
						<br /><br />
					<input type="Submit" name="accio" value="modificar" class="boto_accio" />
					<input type="Submit" name="accio" value="eliminar" class="boto_accio" />
					<br /><br />
						
						<%= obj.printaButaques() %>
						
					<%
					}
					else
					{%>
						<div id="columna1">
						<input type="Hidden" name="idSala" value="" />
						nom de la sala
						<input type="Text" name="nomSala" class="caixa_text" value="" />
						<br /><br />
						columnes malla
						<input type="Text" name="numMaxColumnes" class="caixa_text" value="" maxlength="3" />
						<br /><br />
						files malla
						<input type="Text" name="numMaxFiles" class="caixa_text" value="" maxlength="3" />
						<br /><br />
						descripció
						<textarea name="descripcio"  class="caixa_text" cols="30"></textarea>
						<br /><br />						
					<input type="Submit" name="accio" value="afegir" id="afegirSala" class="boto_accio" /> 
				<%
					}
				%>

			 </div>
			
			</form>
		</span>	
	</div>
	<div id="right">
			<jsp:include page="esquelet_adm/navegadreta.jsp"/>
	</div>	
<jsp:include page="esquelet_adm/footer.jsp"/>