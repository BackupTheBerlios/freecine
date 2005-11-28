<%
	String tipusVenda = request.getParameter("tipus_venda");
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
	<center>
		<form name="frmvendacartellera" action="default.jsp" method="post">
			<input type="Submit" name="opcio_menu" class="boto_venda" value="cartellera" />
		</form>
	</center>
</span>
<br /><br />
