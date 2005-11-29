<span class="txt_titol">			
	Pel·lícules
</span>
<br /><br />
<span class="txt">

<jsp:include page="accions.jsp"/>

<form name="frmadmpelicules" action="default.jsp" method="post">
<input type="Hidden" name="opcio_menu" class="caixa_text" value="pel·lícules" />
<div id="caixa_pelicules">
<div id="columna1">
	<%
		String id = request.getParameter("id");	
		String idtxt = "";
		if (id != null)
		{
			idtxt = id;
		}
	%>
	<input type="Hidden" name="id" class="caixa_text" value="<%= idtxt %>" />
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
<%
	String idPelicula = request.getParameter("id");
	if ((idPelicula == null) || (idPelicula == ""))
	{
%>
<input type="Submit" name="opcio_accio" value="afegir" class="boto_accio" /> 
<%
	}
	else
	{
%>
		<input type="Submit" name="opcio_accio" value="modificar" class="boto_accio" />
		<input type="Submit" name="opcio_accio" value="eliminar" class="boto_accio" />
<%
	}
%>
</form>
<table>
	<tr>
		<td></td>
		<td><strong>títol</strong></td>
		<td><strong>títol original</strong></td>
		<td><strong>nacionalitat</strong></td>
		<td><strong>director</strong></td>
		<td><strong>any</strong></td>
	</tr>
	<form name="frmadmpelicules" action="default.jsp" method="post">
	<tr>
		<td>
			<input type="Hidden" name="opcio_menu" class="caixa_text" value="pel·lícules" />
			<input type="Hidden" name="id" class="caixa_text" value="1" />
			<input type="Submit" name="opcio_accio" value=">" class="boto_horari" />
		</td>
		<td>match point</td>
		<td>match point</td>
		<td>U.S.A</td>
		<td>Woody Allen</td>
		<td>2005</td>
	</tr>
	</form>
	<form name="frmadmpelicules" action="default.jsp" method="post">
	<tr class="parell">
		<td>
			<input type="Hidden" name="opcio_menu" class="caixa_text" value="pel·lícules" />
			<input type="Hidden" name="id" class="caixa_text" value="2" />
			<input type="Submit" name="opcio_accio" value=">" class="boto_horari" />
		</td>
		<td>match point dsfds sdgfdsfd sfds f</td>
		<td>match point  dsfdsf </td>
		<td>U.S.A sdfs</td>
		<td>Woody Allen dsf fsdfs </td>
		<td>2005</td>
	</tr>
	</form>
</table>


</span>
<br /><br />
