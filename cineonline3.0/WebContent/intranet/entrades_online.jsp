<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="gestioCompraReserva.CompraReserva" %>
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
			<%
				Vector llista = (Vector) session.getAttribute("llistaCR");
			%>
			<form name="frmcercaentrades" action="GestioCompresReservesServlet" method="post">
				<%
				String val = "";
				if (llista!=null)
				{
					val = (String) session.getAttribute("entrada");
				}
				%>
				<input type="Text" name="codi_entrega" value="<%= val %>" maxlength=20 style="width:200px" />
				<input type="Hidden" name="accio" value="cerca entrades" />
				<br /><br />
				<input type="Submit" name="bt_accio" value="cercar entrades online" class="boto_enllac" />
				<%
				if (llista!=null)
				{
					 Iterator it = llista.iterator();
					 if(it.hasNext())
					 {
						 CompraReserva entrada = (CompraReserva)it.next();
					 %>
					 	<br /><br />
					 	Número reserva <%= entrada.getNumeroReserva() %><br /><br />
					 	Data <%= entrada.getDataHora() %><br /><br />
					 	Número d'entrades <%= llista.size()%><br /><br />
					 	Preu entrada <%= entrada.getSessio().getPreu()%><br /><br />
					 	Preu total <%= entrada.getSessio().getPreu() * llista.size()%><br /><br />
					 	Pel·lícula <%= entrada.getSessio().getPelicula().getTitol() %><br /><br />
					 	Sala <%= entrada.getSessio().getSala().getNomSala() %><br /><br />
					 	<% if(entrada.isPagada()) { %>
					 	Venda efectuada
					 	<%
					 	}else{%>
					 	Venda pendent de pagament
					 	<%
					 		
					 	}
					 	%>
					 	<br /><br />
					 <%
					 }
				}
				 %>
			</form>
			<form name="frmlliuraentrades" action="GestioCompresReservesServlet" method="post">
				<input type="Hidden" name="accio" value="lliurar" />
				<input type="Hidden" name="codi_entrega" value="<%= val %>" />
				<input type="Submit" name="bt_accio" value="lliurar" class="boto_enllac" />
			</form>
		</span>
	</div>
	<div id="right">
	</div>	
<jsp:include page="esquelet_adm/footer.jsp"/>
