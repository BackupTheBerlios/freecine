<jsp:include page="esquelet/header.jsp"/>
	<div id="top">
		<jsp:include page="esquelet/top.jsp"/>		
	</div>
	<div id="left">
		<jsp:include page="esquelet/menu.jsp"/>
	</div>
	<div id="center">
		<%
			String tipusVenda = (String)session.getAttribute("tipusVenda");
			String codi_clau = (String)session.getAttribute("codiClau");
			String titol = "Acció denegada";
			String explicacio = "No s'ha pogut relitzar l'acció";
			
			if (tipusVenda != null)
			{
				if (tipusVenda.compareTo("compra")==0)
				{
					titol = "Compra finalitzada";
					explicacio = "La compra s'ha efectuat correctament.";
				}
				else
				{
					if (tipusVenda.compareTo("reserva")==0)
					{	
						titol = "Reserva finalitzada";
						explicacio =  "La reserva s'ha efectuat correctament.<br /><br />No descuidi presentar el codi clau a taquilla mitja hora abans de l'inici de la sessió. En cas contrari la reserva serà cancel·lada.";						
					}
				}
			}
		%>
		<span class="txt_titol">			
			<%= titol %>
		</span>
		<br /><br />
		<span class="txt">	
			<%= explicacio %>
			<br /><br />
			<% 
			if (codi_clau!="") 
			{
			%>
				El codi clau és el següent:
				<ul type="square">
						<li /><%= codi_clau %>
				</ul>				
			<%
			}
			%>
			<center>
				<form name="frmvendacartellera" action="GestioClientServlet" method="post">
					<input type="Hidden" name="accio" value="llistarSessionsCartellera" />
					<input type="Submit" name="opcio_menu" class="boto_enllac" value="cartellera">
				</form>
			</center>
		</span>
	</div>
	<div id="right">
		<jsp:include page="esquelet/navegadreta.jsp"/>
	</div>	
<jsp:include page="esquelet/footer.jsp"/>
