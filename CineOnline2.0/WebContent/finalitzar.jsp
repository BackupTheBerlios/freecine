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
		N� entrades: <%= numEntrades %>
		<br /><br />
		Import: <%= numEntrades * 5.50 %> euros
		<br /><br />
		Els codi clau �s el seg�ent:
		<ul type="square">
				<li />jdkl-sa75-84kj
		</ul>
		<%
	 	if (tipusVenda.compareTo("compra")==0)
		{
		%>
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
			No descuidi presentar el codi clau a taquilla mitja hora abans de l'inici de la sessi�. En cas contrari la reserva ser� cancel�lada.
		<%
			}
		}
		%>			
			<br /><br />
			<center>
			<form name="frmvendacartellera" action="index.jsp" method="post">
				<input type="Submit" name="opcio_menu" class="boto_venda" value="inici" />
			</form>
			</center>
		<%
	}
	%>
</span>




