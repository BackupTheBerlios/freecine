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
			<div id="caixa_cartellera">								
				<% 
				String data = (String) session.getAttribute("dataActual");				
				Vector llista = new Vector();	
				llista = (Vector) session.getAttribute("llistaSessions");
				System.err.println("Zzzzzzzzzzz " + llista.size());
				if(llista!=null)
				{
					Iterator it = llista.iterator();
					Sessio ses = new Sessio();
					int idSesAnt = -1;
					int idPeliAnt = -1;
					String hora = "";
					%>
					<div id="titol_cartellera">SESSIONS DEL CINE a <%= data %></div>		
					<%		
						while (it.hasNext())
						{
							ses = (Sessio)it.next();

							if((int)ses.getSala().getId()!= idSesAnt)
							{
					%>
						<div id="col_cartellera">
								<div id="nom_sala">
									<%= ses.getSala().getNomSala() %>
								</div>
							<%} 
							
							if((int)ses.getPelicula().getId()!= idPeliAnt)
							{
							%>
							
							 <div id="pelicula_cartellera">
									<%= ses.getPelicula().getTitol() %>		
							</div>
							<div id="horari_cartellera">
									<form name="frmcartellera_venda" action="venda.jsp"  method="post">
										<input type="Hidden" name="nom_pelicula" value="El Pelicano" />
										<input type="Hidden" name="idpelicula" value="1" />
										<input type="Hidden" name="nom_sala" value="SALA 1" />
										<input type="Hidden" name="idsala" value="1" />
										<input type="Hidden" name="data" value="11/11/2005" />
										<input type="Hidden" name="idsessio" value="1" />																				
										<input type="Submit" name="venda" value="15:10" class="boto_horari" />
										
										<% if (ses.getDataHora().toString().compareTo(hora)> 0 ) 
										{%>
											<input type="Submit" name="venda" value="<%= ses.getDataHora() %>" class="boto_horari" />
										<%} 										
										%>
							<%} %>
																											
							<%if((ses.getSala().getId()!=idSesAnt))
							{
							%>
							</form>
							</div> 
						</div>
					<%
							}							
							idSesAnt = ses.getSala().getId();							
							idPeliAnt = ses.getPelicula().getId();	
							hora = ses.getDataHora().toString();
						}
			}
			%>
			</div>
			Clica sobre l'horari d'una sessi� per fer la compra o reserva d'entrades.	
		</span>
	</div>
	<div id="right">
		<jsp:include page="esquelet_adm/navegadreta.jsp"/>
	</div>	
<jsp:include page="esquelet_adm/footer.jsp"/>		