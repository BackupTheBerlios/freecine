<span class="txt_titol">			
	Sales
</span>
<br /><br />
<span class="txt">
	<form name="frm" action="accions.jsp" method="post" />
	<input type="Hidden" name="id" value="" />
	nom de la sala
	<input type="Text" name="nomSala" class="caixa_text" value="" />
	<br /><br />
	número butaques
	<input type="Text" name="numButaques" class="caixa_text" value="" maxlength="4" />
	<br /><br />
	número butaques operatives
	<input type="Text" name="numOperatives" class="caixa_text" value="" maxlength="4" />
	<br /><br />
	columnes malla
	<input type="Text" name="numMaxColumnes" class="caixa_text" value="" maxlength="3" />
	<br /><br />
	files malla
	<input type="Text" name="numMaxFiles" class="caixa_text" value="" maxlength="3" />
	<br /><br />
	descripció
	<textarea name="descripcio"  class="caixa_text" cols="30"></textarea>
	<br /><br />
		<input type="Button" onclick="javascript:document.location='butaques.jsp'" value="Butaques de la sala" class="boto_menu" />
		<input type="Submit"  name="opcio_accio" value="Afegir" class="boto_accio" />
		<input type="Submit" name="opcio_accio" value="Modificar" class="boto_accio" />
		<input type="Submit" name="opcio_accio" value="Eliminar" class="boto_accio" />
	</form>
	<table border="2">
		<tr>
			<td></td>
			<td><strong>nom de la sala</strong></td>
			<td><strong>número de butaues</strong></td>
			<td><strong>número de operatives</strong></td>
		</tr>
		<tr>
			<td><a href="" class="web">triar</a></td>
			<td>1</td>		
			<td>60</td>
			<td>58</td>
		</tr>
	</table>
</span>
<br /><br />
