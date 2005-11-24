<span class="txt_titol">			
	Butaques de sessio
</span>
<br /><br />
<span class="txt">
	<form name="frm" action="accions.jsp" method="post">
	<input type="Hidden" name="numButaca" value="" />
	número fila
	<input type="Text" name="nomSala" value="" maxlength="3" class="caixa_text" />
	<br /><br />
	número columna
	<input type="Text" name="numButaques" value="" maxlength="3" class="caixa_text" />
	<br /><br />
	opetativa
	<input type="Checkbox" name="operativa" value="" disabled class="caixa_text" />
	<br /><br />
	estat
	<select name="estat" class="caixa_text">
		<option value="1" />Disponible
		<option value="2" />Comprada
		<option value="3" />Reservada
	</select>
	<br /><br />
		<input type="Submit" name="opcio_accio" value="modificar" class="boto_accio" />
	</form>
	<table border="2">
		<%
		int numMaxColumnes = 10;
		int numMaxFiles = 20;
		String butaquesTaula = "";
		for (int i=0; i<numMaxFiles;i++)
		{
		butaquesTaula+= "<tr>";
			for (int j=0;j<numMaxColumnes;j++)
			{
			butaquesTaula+= "<td><a href='butaques_sessio.jsp' title='(" + i + "," + j + ")'>|_|</a></td>";
			}
		butaquesTaula+= "</tr>";
		}
		 %>
		 <%= butaquesTaula %>
	</table>
</span>
