<jsp:include page="esquelet_adm/header.jsp"/>
	<div id="top">
		<jsp:include page="esquelet_adm/top.jsp"/>		
	</div>
	<div id="left">
		<jsp:include page="esquelet_adm/menu.jsp"/>
	</div>
	<div id="center">
		<%
			String tipusVenda = request.getParameter("tipus_venda");
			String titol = "Acció denegada";
			String explicacio = "No s'ha pogut relitzar l'acció";
			String codi_clau = "";
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
						codi_clau = "jdsk54378943jh";
						
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
				<form name="frmvendacartellera" action="cartellera.jsp" method="post">
					<input type="Submit" name="opcio_menu" class="boto_venda" value="cartellera" />
				</form>
			</center>
		</span>
	</div>
	<div id="right">
		<jsp:include page="esquelet_adm/navegadreta.jsp"/>
	</div>	
<jsp:include page="esquelet_adm/footer.jsp"/>
