<span class="txt_titol">			
	Cartellera
</span>
<br /><br />
<span class="txt">			
	Consulta la cartellera del cinema!!
	<br /><br />	
	Tria el dia que vols visitar el cinema
	<br /><br />
	<form name="frmcartellera_query" action="javascript:alert('Anat a la cartellera del dia...');" method="post">
		dia
		<select name="dia" class="caixa_text">
		<%
			int i;
			for (i=1; i <= 31; i++)
			{
		%>
			<option value="<%= i %>" /><%= i %>
		<%
			}
		%>
		</select>
		mes
		<select name="mes" class="caixa_text">			
		<%
			
			for (i=1; i <= 12; i++)
			{
		%>
			<option value="<%= i %>" /><%= i %>
		<%
			}
		%>
		</select>
		any
		<select name="mes" class="caixa_text">			

		<%
			
			long data = System.nanoTime();
			for (i=2005; i <= 2006; i++)
			{
		%>
			<option value="<%= i %>" /><%= i  %>
		<%
			}
		%>
		</select>
		<input type="Submit" name="cerca" value="cerca" class="boto_venda" />
		<input type="Submit" name="carca" value="ant" class="boto_venda" align="right" />
		<input type="Submit" name="cerca" value="seg" class="boto_venda" align="right" />
	</form>
	<br />
	<div id="caixa_cartellera">
		<div id="titol_cartellera">SESSIONS DEL CINE a 01-01-2005</div>
		<div id="col_cartellera">
			<div id="nom_sala">
				SALA 1
			</div>
			<div id="pelicula_cartellera">
				El Pelicano			
			</div>
			<div id="horari_cartellera">
				<form name="frmcartellera_venda" action="default.jsp"  method="post">
					<input type="Hidden" name="nom_pelicula" value="El Pelicano" />
					<input type="Hidden" name="idpelicula" value="1" />
					<input type="Hidden" name="nom_sala" value="SALA 1" />
					<input type="Hidden" name="idsala" value="1" />
					<input type="Hidden" name="data" value="11/11/2005" />
					<input type="Submit" name="venda" value="15:10" class="boto_horari" />
					<input type="Submit" name="venda" value="18:15" class="boto_horari" />
					<input type="Submit" name="venda" value="20:20" class="boto_horari" />
					<input type="Submit" name="venda" value="22:30" class="boto_horari" />					
					<input type="Hidden" name="idsessio" value="1" />
				</form>
			</div>
		</div>
		<div id="col_cartellera">
			<div id="nom_sala">
				SALA 2
			</div>
			<div id="pelicula_cartellera">
				Zoombie			
			</div>
			<div id="horari_cartellera">
				<form name="frmcartellera_venda" action="default.jsp"  method="post">
					<input type="Hidden" name="nom_pelicula" value="Zoombie" />
					<input type="Hidden" name="idpelicula" value="2" />
					<input type="Hidden" name="nom_sala" value="SALA 2" />
					<input type="Hidden" name="idsala" value="2" />
					<input type="Hidden" name="data" value="11/11/2005" />				
					<input type="Submit" name="venda" value="20:20" class="boto_horari" />
					<input type="Submit" name="venda" value="22:30" class="boto_horari" />
					<input type="Hidden" name="idsessio" value="1" />
				</form>
			</div>
			<div id="pelicula_cartellera">
			Casablanca
			</div>
			<div id="horari_cartellera">
				<form name="frmcartellera_venda" action="default.jsp" method="post">
					<input type="Hidden" name="nom_pelicula" value="Casablanca" />
					<input type="Hidden" name="idpelicula" value="3" />
					<input type="Hidden" name="nom_sala" value="SALA 2" />
					<input type="Hidden" name="idsala" value="2" />
					<input type="Hidden" name="data" value="11/11/2005" />
					<input type="Submit" name="venda" value="20:20" class="boto_horari" />
					<input type="Submit" name="venda" value="22:30" class="boto_horari" />
					<input type="Hidden" name="idsessio" value="1" />
				</form>
			</div>			
		</div>
		<div id="col_cartellera">
			<div id="nom_sala">
				SALA 3
			</div>
			<div id="pelicula_cartellera">
			La sirenita
			</div>
			<div id="horari_cartellera">
				<form name="frmcartellera_venda" action="default.jsp" method="post">
					<input type="Hidden" name="nom_pelicula" value="La sirenita" />
					<input type="Hidden" name="idpelicula" value="4" />
					<input type="Hidden" name="nom_sala" value="SALA 3" />
					<input type="Hidden" name="idsala" value="3" />
					<input type="Hidden" name="data" value="11/11/2005" />
					<input type="Submit" name="venda" value="15:10" class="boto_horari" />
					<input type="Submit" name="venda" value="18:15" class="boto_horari" />
					<input type="Submit" name="venda" value="20:20" class="boto_horari" />
					<input type="Submit" name="venda" value="22:30" class="boto_horari" />
					<input type="Hidden" name="idsessio" value="1" />
				</form>
			</div>
		</div>				
		<div id="col_cartellera">
			<div id="nom_sala">
				SALA 4
			</div>
			<div id="pelicula_cartellera">
			Parretis de cine
			</div>
			<div id="horari_cartellera">
				<form name="frmcartellera_venda" action="default.jsp" method="post">
					<input type="Hidden" name="nom_pelicula" value="Parretis de cine" />
					<input type="Hidden" name="idpelicula" value="5" />
					<input type="Hidden" name="nom_sala" value="SALA 4" />
					<input type="Hidden" name="idsala" value="4" />
					<input type="Hidden" name="data" value="11/11/2005" />
					<input type="Submit" name="venda" value="15:10" class="boto_horari" />
					<input type="Submit" name="venda" value="18:15" class="boto_horari" />
					<input type="Submit" name="venda" value="20:20" class="boto_horari" />
					<input type="Submit" name="venda" value="22:30" class="boto_horari" />
					<input type="Hidden" name="idsessio" value="1" />
				</form>
			</div>
		</div>
		<div id="col_cartellera">
			<div id="nom_sala">
				SALA 5
			</div>
			<div id="pelicula_cartellera">
			Parretis de cine
			</div>
			<div id="horari_cartellera">
				<form name="frmcartellera_venda" action="default.jsp" method="post">
					<input type="Hidden" name="nom_pelicula" value="Parretis de cine" />
					<input type="Hidden" name="idpelicula" value="5" />
					<input type="Hidden" name="nom_sala" value="SALA 5" />
					<input type="Hidden" name="idsala" value="5" />
					<input type="Hidden" name="data" value="11/11/2005" />
					<input type="Submit" name="venda" value="15:10" class="boto_horari" />
					<input type="Submit" name="venda" value="18:15" class="boto_horari" />
					<input type="Submit" name="venda" value="20:20" class="boto_horari" />
					<input type="Submit" name="venda" value="22:30" class="boto_horari" />
					<input type="Hidden" name="idsessio" value="1" />
				</form>
			</div>
		</div>
		<div id="col_cartellera">
			<div id="nom_sala">
				SALA 6
			</div>
			<div id="pelicula_cartellera">
			Sé lo que hicistes el último verano
			</div>
			<div id="horari_cartellera">
				<form name="frmcartellera_venda" action="default.jsp" method="post">
					<input type="Hidden" name="nom_pelicula" value="Sé lo que hicistes el último verano" />
					<input type="Hidden" name="idpelicula" value="6" />
					<input type="Hidden" name="nom_sala" value="SALA 6" />
					<input type="Hidden" name="idsala" value="6" />
					<input type="Hidden" name="data" value="11/11/2005" />
					<input type="Submit" name="venda" value="18:15" class="boto_horari" />
					<input type="Submit" name="venda" value="22:30" class="boto_horari" />
					<input type="Hidden" name="idsessio" value="1" />
				</form>
			</div>
			<div id="pelicula_cartellera">
			Parretis de cine
			</div>
			<div id="horari_cartellera">
				<form name="frmcartellera_venda" action="default.jsp" method="post">
					<input type="Hidden" name="nom_pelicula" value="Parretis de cine" />
					<input type="Hidden" name="idpelicula" value="5" />
					<input type="Hidden" name="nom_sala" value="SALA 6" />
					<input type="Hidden" name="idsala" value="6" />
					<input type="Hidden" name="data" value="11/11/2005" />
					<input type="Submit" name="venda" value="15:10" class="boto_horari" />
					<input type="Submit" name="venda" value="20:20" class="boto_horari" />
					<input type="Hidden" name="idsessio" value="1" />
				</form>
			</div>
		</div>
		<div id="col_cartellera">
			<div id="nom_sala">
				SALA 7
			</div>
			<div id="pelicula_cartellera">
			Los lunes al sol
			</div>
			<div id="horari_cartellera">
				<form name="frmcartellera_venda" action="default.jsp" method="post">
					<input type="Hidden" name="nom_pelicula" value="Los lunes al sol" />
					<input type="Hidden" name="idpelicula" value="7" />
					<input type="Hidden" name="nom_sala" value="SALA 7" />
					<input type="Hidden" name="idsala" value="7" />
					<input type="Hidden" name="data" value="11/11/2005" />
					<input type="Submit" name="venda" value="15:10" class="boto_horari" />
					<input type="Submit" name="venda" value="18:15" class="boto_horari" />
					<input type="Submit" name="venda" value="20:20" class="boto_horari" />
					<input type="Submit" name="venda" value="22:30" class="boto_horari" />
					<input type="Submit" name="venda" value="00:30" class="boto_horari" />
					<input type="Hidden" name="idsessio" value="1" />
				</form>
			</div>			
		</div>
	</div>
	Clica sobre l'horari d'una sessió per fer la compra o reserva d'entrades.	
	<br /><br />
</span>