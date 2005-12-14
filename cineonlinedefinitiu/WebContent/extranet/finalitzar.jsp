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
			<br /><br />
			Recori prendre el codi clau per obtenir les entrades a taquilla!!
		</span>
		<br /><br />
		<span class="txt">			
			<%
			String tipusVenda = request.getParameter("tipus_venda");
			String numEntrades_str = request.getParameter("num_entrades");
			int numEntrades = 0;
			if (numEntrades_str != null)
			{
				numEntrades = Integer.valueOf(numEntrades_str).intValue();
			}
			if (tipusVenda != null)
			{
				%>
				Nº entrades: <%= numEntrades %>
				<br /><br />
				Import: <%= numEntrades * 5.50 %> euros
				<br /><br />
				Els codi clau és el següent:
				<ul type="square">
						<li />jdkl-sa75-84kj
				</ul>
				<%
			 	if (tipusVenda.compareTo("compra")==0)
				{
			 		String entitat = request.getParameter("entitat");
			 		String oficina = request.getParameter("oficina");
			 		String control = request.getParameter("control");
			 		String num_compte = request.getParameter("num_compte");
			 		String cc = entitat + "-" + oficina + "-" + control + "-" + num_compte;
				%>
					Número de compte corrent: <%= cc %>
					<br /><br />
					La compra s'ha efectuat correctament.					
				<%
				}
				else
				{
					if (tipusVenda.compareTo("reserva")==0)
					{
				%>
					La reserva s'ha efectuat correctament.
					<br /><br />
					No descuidi presentar el codi clau a taquilla mitja hora abans de l'inici de la sessió. En cas contrari la reserva serà cancel·lada.
				<%
					}
				}
				%>			
					<br /><br />
					<center>
					<form name="frmvendacartellera" action="cartellera.jsp" method="post">
						<input type="Submit" name="opcio_menu" class="boto_venda" value="cartellera" />
					</form>
					</center>
				<%
			}
			%>
		</span>
	</div>
	<div id="right">
		<jsp:include page="esquelet/navegadreta.jsp"/>
	</div>	
<jsp:include page="esquelet/footer.jsp"/>




