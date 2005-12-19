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
			Tria el dia de la cartellera
				<br /><br />
				<form name="frmcartellera_query" action="javascript:alert('Anat a la cartellera del dia...');" method="post">
					dia
					<select name="dia" style="width:45px">
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
					<select name="mes" style="width:45px">			
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
					<select name="mes" style="width:55px">			
			
					<%						
						long datal = System.nanoTime();
						for (i=2005; i <= 2010; i++)
						{
					%>
						<option value="<%= i %>" /><%= i  %>
					<%
						}
					%>
					</select>
					<input type="Submit" name="cerca" value="cerca" class="boto_enllac" />
					<input type="Submit" name="carca" value="ant" class="boto_enllac" align="right" />
					<input type="Submit" name="cerca" value="seg" class="boto_enllac" align="right" />
				</form>
				<br />
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
									<form name="frmcartellera_venda" action="GestioSessionsServlet"  method="post">
										<input type="Hidden" name="idSessio" value="<%= ses.getId() %>" />
										<input type="Hidden" name="accio" value="venda" />
										
										<% if (ses.getDataHora().toString().compareTo(hora)> 0 ) 
										{%>
											<input type="Submit" name="bt_venda" value="<%= ses.donaHora() %>" class="boto_horari" />
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
			Clica sobre l'horari d'una sessió per fer la compra o reserva d'entrades.	
		</span>
	</div>
	<div id="right">
		<jsp:include page="esquelet_adm/navegadreta.jsp"/>
	</div>	
<jsp:include page="esquelet_adm/footer.jsp"/>		