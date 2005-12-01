<jsp:include page="esquelet_adm/header.jsp"/>
	<div id="top">
		<jsp:include page="esquelet_adm/top.jsp"/>		
	</div>
	<div id="left">
		<jsp:include page="esquelet_adm/menu.jsp"/>
	</div>
	<div id="center">
		<span class="txt_titol">			
			Entrades online
		</span>
		<br /><br />
		<span class="txt">						
			<u>Dades de la sessi�:</u>
			<br /><br />
			<%
				String idpelicula = request.getParameter("idpelicula");
				String nom_pelicula = request.getParameter("nom_pelicula");
				String idsala = request.getParameter("idsala");
				String nom_sala = request.getParameter("nom_sala");
				String data = request.getParameter("data");
				String hora = request.getParameter("venda");
				String idsessio = request.getParameter("idsessio");
			%>
			<%= nom_pelicula %>
			<br /><br />
			<%= nom_sala %>
			<br /><br />
			<%= data %>
			<br /><br />
			<%= hora %>
			<br /><br />
			<form name="frmvenda" action="ticket.jsp" method="post">
				<input type="Hidden" name="idsessio" value="<%= idsessio %>" />
				<input type="Hidden" name="llista_butaques" value="" />
				Vull fer una 
				<select name="tipus_venda" class="caixa_text">
					<option value="compra" /> compra
					<option value="reserva" /> reserva
				</select>
				<br /><br />
				<%
					int maxNumEntrades = 6;
				%>
				Nombre d'entrades (m�xim <%= maxNumEntrades %>)
				<select name="num_entrades" class="caixa_text">
					<%
					int i;
					for (i=1; i <= maxNumEntrades; i++)
					{
					%>
						<option value="<%= i %>" /><%= i %>
					<%
						}
					%>		
				</select>
				<br /><br />
				<%
				boolean sessioNumerada = true;
				if (sessioNumerada)
				{
				%>
				Sessi� numerada, escull les butaques corresponents a les entrades.
				<br /><br />
				<center>
					<jsp:include page="butaques.jsp"/>
				</center>
				<%
				}
				else
				{
				%>
				Sessi� no numerada.
				<%
				}
				%>
				<br /><br />
				<center>		
					<input type="Submit" name="opcio_menu" class="boto_venda" value="continuar" />
				</center>
			</form>
		</span>
	</div>
	<div id="right">
		<jsp:include page="esquelet_adm/navegadreta.jsp"/>
	</div>	
<jsp:include page="esquelet_adm/footer.jsp"/>