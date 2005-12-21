<jsp:include page="esquelet/header.jsp"/>
	<div id="top">
		<jsp:include page="esquelet/top.jsp"/>		
	</div>
	<div id="left">
		<jsp:include page="esquelet/menu.jsp"/>		
	</div>
	<div id="center">
		<span class="txt_titol">			
			Entrades online
		</span>
		<br /><br />
		<span class="txt">			
			<%
			String vendaPage = request.getParameter("venda");
		 	if (vendaPage == null)
			{
			%>
				Compra o reserva ja les teves entrades per una sessió!!
				<br /><br />
				Per a realitzar una compra o reserva escull una sessió de la cartellera, gràcies.
				<br /><br />
				<center>
				<form name="frmvendacartellera" action="cartellera.jsp" method="post">
					<input type="Submit" name="opcio_menu" class="boto_venda" value="cartellera" />
				</form>
				</center>
			<%
			}
			else
			{
			%>
				<u>Dades de la sessió:</u>
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
				<form name="frmvenda" action="GestioSessionsServlet" method="post">
				<input type="Hidden" name="accio" value="continuar venda" />
				Vull fer una 
				<select name="tipus_venda" class="caixa_text">
					<option value="compra" /> compra
					<option value="reserva" /> reserva
				</select>
				<br /><br />
				<%
					int maxNumEntrades = 6;
				%>
				Nombre d'entrades (màxim <%= maxNumEntrades %>)
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
				Sessió numerada, escull les butaques corresponents a les entrades.
				<br /><br />
				<center>
					<jsp:include page="butaques.jsp"/>
				</center>
				<%
				}
				else
				{
				%>
				Sessió no numerada.
				<%
				}
				%>
				<br /><br />
				<center>		
					<input type="Submit" name="opcio_menu" class="boto_venda" value="continuar" />
				</center>
				</form>
			<%
			}
			%>
		</span>
	</div>
	<div id="right">
		<jsp:include page="esquelet/navegadreta.jsp"/>
	</div>	
<jsp:include page="esquelet/footer.jsp"/>
