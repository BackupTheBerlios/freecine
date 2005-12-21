<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="gestioCinema.gestioSessions.Sessio" %>
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
			<u>Dades de la sessió:</u>
			<br /><br />
			<%
			if (session.getAttribute("sessio")== null)  
			{
				System.err.println("es nula");
			}
			else
			{
				System.err.println("MNO es nula");
			}
				
			  
				Sessio ses = (Sessio) session.getAttribute("sessio");
				//int idpelicula = ses.getPelicula().getId();
				String nom_pelicula = ses.getPelicula().getTitol();
				//int idsala = ses.getSala().getId();
				String nom_sala = ses.getSala().getNomSala();
				String data = ses.getDataHora();				
				int idsessio = ses.getId();
			%>
			Pel·lícula: <%= nom_pelicula %>
			<br /><br />
			Sala: <%= nom_sala %>
			<br /><br />
			Data: <%= data %>
			<br /><br />
			<form name="frmvenda" action="GestioClientServlet" method="post">
				<input type="Hidden" name="idSessio" value="<%= idsessio %>" />
				<input type="Hidden" name="accio" value="continuar venda" />
				Vull fer una 
				<select name="tipus_venda">
					<option value="compra" /> compra
					<option value="reserva" /> reserva
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
					<jsp:include page="butaques_sessio.jsp"/>
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
					<input type="Submit" name="opcio_menu" class="boto_enllac" value="continuar" />
				</center>
			</form>
		</span>
	</div>
	<div id="right">
		<jsp:include page="esquelet/navegadreta.jsp"/>
	</div>	
<jsp:include page="esquelet/footer.jsp"/>