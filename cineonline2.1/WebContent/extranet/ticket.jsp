<span class="txt_titol">			
	Entrades online
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
	 	if (tipusVenda.compareTo("compra")==0)
		{
		%>
			<span class="txt_titol">			
				Compra
			</span>
			<br /><br />
			N� entrades: <%= numEntrades %>
			<br /><br />
			Import a pagar : <%= numEntrades * 5.50 %> euros
			<br /><br />
			Introdueix les dades banc�ries corresponents.
			<br /><br />
			<form name="frmvendacartellera" action="index.jsp" method="post">				
				<input type="hidden" name="tipus_venda" value="<%= tipusVenda %>" />
				<input type="hidden" name="num_entrades" value="<%= numEntrades %>" />
				<input type="text" name="entitat" value="" class="caixa_text" size="4" maxlength="4"/>
				<input type="text" name="oficina" value="" class="caixa_text" size="4" maxlength="4" />
				<input type="text" name="control" value="" class="caixa_text" size="2" maxlength="2" />
				<input type="text" name="num_compte" value="" class="caixa_text" size="13" maxlength="11"/>
				<br /><br />
				<center>
					<input type="Submit" name="opcio_menu" class="boto_venda" value="comprar" />
				</center>
			</form>
		<%
		}
		else
		{
			if (tipusVenda.compareTo("reserva")==0)
			{
		%>
			<span class="txt_titol">			
				Reserva
			</span>
			<br /><br />
			N� entrades: <%= numEntrades %>
			<br /><br />
			Import a pagar a taquilla : <%= numEntrades * 5.50 %> euros
			<br /><br />				
			</ul>
			<center>
			<form name="frmticketacabar" action="index.jsp" method="post">
				<input type="hidden" name="tipus_venda" value="<%= tipusVenda %>" />
				<input type="hidden" name="num_entrades" value="<%= numEntrades %>" />
				<input type="Submit" name="opcio_menu" class="boto_venda" value="reservar" />
			</form>
			</center>
		<%
			}
		}
	}
	%>
</span>




