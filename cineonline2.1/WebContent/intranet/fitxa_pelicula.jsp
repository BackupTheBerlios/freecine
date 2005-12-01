<jsp:include page="esquelet_adm/header.jsp"/>
	<div id="top">
		<jsp:include page="esquelet_adm/top.jsp"/>		
	</div>
	<div id="left">
		<jsp:include page="esquelet_adm/menu.jsp"/>
	</div>
	<div id="center">
		<span class="txt_titol">			
			Pel·lícules
		</span>
		<br /><br />
		<span class="txt">
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
		</span>	
	</div>
	<div id="right">
			<jsp:include page="esquelet_adm/navegadreta.jsp"/>
	</div>	
<jsp:include page="esquelet_adm/footer.jsp"/>