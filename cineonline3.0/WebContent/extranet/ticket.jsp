<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="gestioCinema.gestioSessions.Sessio" %>
<%@ page import="gestioCinema.gestioSessions.ButacaSessio" %>
<jsp:include page="esquelet/header.jsp"/>
	<div id="top">
		<jsp:include page="esquelet/top.jsp"/>		
	</div>
	<div id="left">
		<jsp:include page="esquelet/menu.jsp"/>
	</div>
	<div id="center">
		<span class="txt_titol">			
			Tiket d'entrades
		</span>
		<br /><br />
		<span class="txt">			
			<%
			Vector butaquesSelec = (Vector) session.getAttribute("butaquesSelec");
			Sessio ses = (Sessio) session.getAttribute("sessio");			
			String tipusVenda = (String)session.getAttribute("tipusVenda");						
			String numEntrades_str = "" + butaquesSelec.size();
			double preu = ses.getPreu();
			
//			ButacaSessio butses = (ButacaSessio) butaquesSelec.firstElement();
			
			int numEntrades = 0;
			if (numEntrades_str != null)
			{
				numEntrades = Integer.valueOf(numEntrades_str).intValue();
			}
			
			if (tipusVenda != null)
			{
			%>
				<span class="txt_titol">			
			<%
			 	if (tipusVenda.equals("compra"))
				{
				%>
						Compra				
				<%
				}
				else
				{
					if (tipusVenda.equals("reserva"))
					{
				%>
						Reserva
				<%
					}
				}
				%>
				</span>
				<br /><br />
				Pel·lícula: <%= ses.getPelicula().getTitol() %>
				<br /><br />
				Sala: <%= ses.getSala().getNomSala() %>
				<br /><br />
				Sala: <%= ses.getPreu() %>				
				<br /><br />
				Data: <%= ses.getDataHora() %>
				<br /><br />
				Nº entrades: <%= numEntrades %>
				<br /><br />
				Preu entrada: <%= preu %>
				<br /><br />
				Import a pagar : <%= numEntrades * preu %> euros
				<br /><br />								
				<form name="frmticketacabar" action="GestioClientServlet" method="post">
					<input type="hidden" name="tipus_venda" value="<%= tipusVenda %>" />
					<input type="hidden" name="idSessio" value="<%= ses.getId() %>" />
					<input type="hidden" name="accio" value="confirmar" />
				<%
				if (tipusVenda.equals("compra"))
				{
				%>	
				Compte corrent : 
				<input type="text" name="entitat" value="" size="4" maxlength="4" style="width:55px" />
				<input type="text" name="oficina" value="" size="4" maxlength="4" style="width:55px" />
				<input type="text" name="control" value="" size="2" maxlength="2" style="width:45px" />
				<input type="text" name="num_compte" value="" size="13" maxlength="11" style="width:85px" />
				<%
				}
				%>
					<br /><br />
					<input type="Submit" name="opcio_menu" class="boto_enllac" value="confirmar" />
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



