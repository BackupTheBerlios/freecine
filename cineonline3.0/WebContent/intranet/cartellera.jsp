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
		<span class="txt">						
			<br><br>
			<div id="caixa_cartellera">
				<% 
				String data = (String) session.getAttribute("dataActual");
				
				Vector llista = new Vector();	
				llista = (Vector) session.getAttribute("llistaSessions");
				if(llista!=null)
				{
					Iterator it = llista.iterator();
					Sessio ses = new Sessio();
					Sessio sesAnt = new Sessio();
					if(it.hasNext()){
						ses = (Sessio)it.next();
					}
					
					
			%>
				<div id="titol_cartellera">SESSIONS DEL CINE a <%= data %></div>
			<%	while(it.hasNext())
		     {
				%>	
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
			</div>
			<%
		     }
			%>
			Clica sobre l'horari d'una sessió per fer la compra o reserva d'entrades.	
			<br /><br />
		</span>
	</div>
	<div id="right">
		<jsp:include page="esquelet_adm/navegadreta.jsp"/>
	</div>	
<jsp:include page="esquelet_adm/footer.jsp"/>		