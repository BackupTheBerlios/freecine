<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="gestioCinema.gestioSessions.Sessio" %>
<jsp:include page="esquelet_adm/header.jsp"/>
	<div id="top">
		<jsp:include page="esquelet_adm/top.jsp"/>		
	</div>
	<div id="left">
		<jsp:include page="esquelet_adm/menu.jsp"/>
	</div>
	<div id="center">
		<span class="txt_titol">			
			Cartellera
		</span>
		<br /><br />
		<span class="txt">
		
		<div id="caixa_cartellera"> <!-- caixa cartellera  -->
			<div id="titol_cartellera"><!-- titol cartellera  -->
				SESSIONS DEL CINE a 01-01-2005
			</div>
			<div id="col_cartellera"><!-- columna sala  -->
				<div id="nom_sala"><!-- nom sala  -->
					SALA 1
				</div>
				<!--  per cada pelicula de la sala -->
				<div id="pelicula_cartellera"><!-- nom pelicula  -->
					El Pelicano			
				</div>
				<div id="horari_cartellera"><!-- caixa d'hores  -->
					<form name="frmcartellera_venda" action="venda.jsp"  method="post">
						<input type="Hidden" name="nom_pelicula" value="El Pelicano" />
						<input type="Hidden" name="idpelicula" value="1" />
						<input type="Hidden" name="nom_sala" value="SALA 1" />
						<input type="Hidden" name="idsala" value="1" />						
						<input type="Hidden" name="data" value="11/11/2005" />
						<input type="Hidden" name="idsessio" value="1" />
						<!-- per cada sessio de la pelicula en la sala -->
						<input type="Submit" name="venda" value="15:10" class="boto_horari" />
						<input type="Submit" name="venda" value="18:15" class="boto_horari" />
						<input type="Submit" name="venda" value="20:20" class="boto_horari" />
						<input type="Submit" name="venda" value="22:30" class="boto_horari" />					
						<!-- per cada sessio de la pelicula en la sala -->
					</form>
				</div><!-- fi caixa d'hores  -->
			</div>
		<br /><br />
		</div>
		
<br /><br /><br /><br />
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
							<form name="frmcartellera_venda" action="venda.jsp"  method="post">
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
							<form name="frmcartellera_venda" action="venda.jsp"  method="post">
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
							<form name="frmcartellera_venda" action="venda.jsp" method="post">
								<input type="Hidden" name="nom_pelicula" value="Casablanca" />
								<input type="Hidden" name="idpelicula" value="3" />
								<input type="Hidden" name="nom_sala" value="SALA 2" />
								<input type="Hidden" name="idsala" value="2" />
								<input type="Hidden" name="data" value="11/11/2005" />
								<input type="Submit" name="venda" value="15:10" class="boto_horari" />
								<input type="Submit" name="venda" value="18:15" class="boto_horari" />				
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
							<form name="frmcartellera_venda" action="venda.jsp" method="post">
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
						<form name="frmcartellera_venda" action="venda.jsp" method="post">
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
						<form name="frmcartellera_venda" action="venda.jsp" method="post">
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
						<form name="frmcartellera_venda" action="venda.jsp" method="post">
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
						<form name="frmcartellera_venda" action="venda.jsp" method="post">
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
						<form name="frmcartellera_venda" action="venda.jsp" method="post">
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
		</span>
	</div>
	<div id="right">
	</div>	
<jsp:include page="esquelet_adm/footer.jsp"/>