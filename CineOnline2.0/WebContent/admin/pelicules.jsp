<span class="txt_titol">			
	Películes
</span>
<br /><br />
<span class="txt">

<form name="frmadmpelicules" action="accions.jsp" method="post">
<input type="Hidden" name="id" class="caixa_text" value="" />
<div id="caixa_pelicules">
<div id="columna1">
	títol
	<input type="Text" name="titol" class="caixa_text" value="" />
	<br /><br />
	títol original
	<input type="Text" name="titolOriginal" class="caixa_text" value="" />
	<br /><br />
	any
	<input type="Text" name="any" class="caixa_text" maxlength="4" value="" />
	<br /><br />
	nacionalitat
	<input type="Text" name="nacionalitat" class="caixa_text" value="" />
	<br /><br />
	durada (minuts)
	<input type="Text" name="durada" class="caixa_text" value="" />
	<br /><br />
	edat recomanada
	<input type="Text" name="edatRecomanada" class="caixa_text" value="" />
	<br /><br />
	tipus color
	<input type="Text" name="tipusColor" class="caixa_text" value="" />
	<br /><br />
	tipus sò
	<input type="Text" name="tipusSo" class="caixa_text" value="" />
</div>
<div id="columna2">
	director
	<input type="Text" name="director" class="caixa_text" value="" />
	<br /><br />
	guionista
	<input type="Text" name="guionista" class="caixa_text" value="" />
	<br /><br />
	productor
	<input type="Text" name="productor" class="caixa_text" value="" />
	<br /><br />
	actors
	<textarea name="actors" class="caixa_text"  cols="30"></textarea>
	<br /><br />
	sinopsis
	<textarea name="sinopsis" class="caixa_text" cols="30"></textarea>
	<br /><br />
	web
	<input type="Text" name="web" class="caixa_text" value="" />
	<br /><br />
	imatge
	<input type="File" name="imatge" class="caixa_text"  value="" />
</div>
</div>
<input type="Submit" name="opcio_accio" value="afegir" class="boto_accio" /> 
<input type="Submit" name="opcio_accio" value="modificar" class="boto_accio" />
<input type="Submit" name="opcio_accio" value="eliminar" class="boto_accio" />
</form>
<table border="2">
	<tr>
		<td></td>
		<td><strong>títol</strong></td>
		<td><strong>títol original</strong></td>
		<td><strong>nacionalitat</strong></td>
		<td><strong>director</strong></td>
		<td><strong>any</strong></td>
	</tr>
	<tr>
		<td><a href="" class="web">Triar</a></td>
		<td>match point</td>
		<td>match point</td>
		<td>U.S.A</td>
		<td>Woody Allen</td>
		<td>2005</td>
	</tr>
</table>


</span>
<br /><br />
