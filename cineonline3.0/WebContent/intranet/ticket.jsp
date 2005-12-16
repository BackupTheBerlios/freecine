<jsp:include page="esquelet_adm/header.jsp"/>
	<div id="top">
		<jsp:include page="esquelet_adm/top.jsp"/>		
	</div>
	<div id="left">
		<jsp:include page="esquelet_adm/menu.jsp"/>
	</div>
	<div id="center">
		<span class="txt_titol">			
			Tiket d'entrades
		</span>
		<br /><br />
		<span class="txt">			
			<%
			String tipusVenda = request.getParameter("tipus_venda");
			String numEntrades_str = request.getParameter("num_entrades");
			String accio = "";
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
			 	if (tipusVenda.compareTo("compra")==0)
				{
			 		accio = "comprar";
				%>
						Compra				
				<%
				}
				else
				{
					if (tipusVenda.compareTo("reserva")==0)
					{
						accio = "reservar";
				%>
						Reserva
				<%
					}
				}
				%>
				</span>
				<br /><br />
				Nº entrades: <%= numEntrades %>
				<br /><br />
				Import a pagar a taquilla : <%= numEntrades * 5.50 %> euros
				<br /><br />
				<center>
				<form name="frmticketacabar" action="finalitzar.jsp" method="post">
					<input type="hidden" name="tipus_venda" value="<%= tipusVenda %>" />
					<input type="hidden" name="num_entrades" value="<%= numEntrades %>" />
					<input type="Submit" name="opcio_menu" class="boto_venda" value="<%= accio %>" />
				</form>
				</center>
				<%
			}
			%>
		</span>
	</div>
	<div id="right">
		<jsp:include page="esquelet_adm/navegadreta.jsp"/>
	</div>	
<jsp:include page="esquelet_adm/footer.jsp"/>



