<span class="txt_titol">			
	Sessions
</span>
<br /><br />
<span class="txt">
	<form name="frm" action="accions.jsp" method="post">
	<input type="Hidden" name="id" value="">
	data (dd/mm/aaaa)
	<input type="Text" name="data" value="" class="caixa_text" maxlength="10">
	<br><br>
	data última sessio (dd/mm/aaaa)
	<input type="Text" name="data" class="caixa_text" value="" maxlength="10"> (rang)
	<br><br>
	hora inici (hh:mm)
	<input type="Text" name="horaInici" class="caixa_text" value="" maxlength="5">
	<br><br>
	pel·lícula
	<input type="Text" name="pelicula" class="caixa_text" value="">
	<br><br>
	sala
	<input type="Text" name="sala" class="caixa_text" value="" />
	<br><br>
<!-- 	<input type="Button" onclick="javascript:document.location='butaques_sessio.htm'" value="Butaques de la sessió"> -->
		<input type="Button" onclick="javascript:alert('butaquessssssessssssioooooo')" class="boto_menu" value="Butaques de la sessió">
		<input type="Submit" name ="opcio_accio" class="boto_accio" value="afegir">
		<input type="Submit" name ="opcio_accio" class="boto_accio" value="modificar">
		<input type="Submit" name ="opcio_accio" class="boto_accio" value="eliminar">
		<br><br>
		<input type="Submit" name ="opcio_accio" class="boto_accio" value="afegir a rang" />
		<input type="Submit" name ="opcio_accio" class="boto_accio" value="modificar a rang" />
		<input type="Submit" name ="opcio_accio" class="boto_accio" value="eliminar a rang" />
	</form>
	<table border="2">
		<tr>
			<td></td>
			<td><strong>data i hora</strong></td>
			<td><strong>pel·lícula</strong></td>
			<td><strong>sala</strong></td>
		</tr>
		<tr>
			<td><a href="" class="web">triar</a></td>
			<td>15/11/2005 20:05</td>		
			<td>Match Point</td>
			<td>Sala 1</td>
		</tr>
	</table>
</span>
<br /><br />
