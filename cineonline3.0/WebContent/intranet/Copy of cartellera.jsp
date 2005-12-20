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
						
			<form name="frmcartellera_query" action="javascript:alert('Anat a la cartellera del dia...');" method="post">
				Tria el dia que vols consultar la cartellera
				<br /><br />
				dia
				<select name="dia">
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
				<select name="mes">			
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
				<select name="anny">			
		
				<%
					
					long data_ = System.nanoTime();
					for (i=2005; i <= 2006; i++)
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
			
			<br><br>
			<div id="caixa_cartellera">
		
			<% 
				String data = (String) session.getAttribute("dataActual");
			%>
				<div id="titol_cartellera">SESSIONS DEL CINE a <%= data %></div>
			<%
				Vector llista = new Vector();	
				llista = (Vector) session.getAttribute("llistaSessions");
				if(llista!=null)
				{
					Iterator it = llista.iterator();
					Sessio ses = new Sessio();
					Sessio sesAnt = new Sessio();
					boolean novaSala = false;
					boolean novaPeli = false;
					
					if(it.hasNext()){
						ses = (Sessio)it.next();
					}
					
					while (it.hasNext())
					{	
						
						if(sesAnt.getSala().getId()!=ses.getSala().getId()){
							novaSala = true;							
							%>							
							<div id="col_cartellera">
							<div id="nom_sala">
								<%= ses.getSala().getNomSala() %>
							</div>
							<% 
						}
						else
						{
							novaSala = false;							
						}
						
						if(sesAnt.getPelicula().getId()!=ses.getPelicula().getId()){
							novaPeli = true;
							%>				
							<div id="pelicula_cartellera">
								<%= ses.getPelicula().getTitol() %>			
							</div>
							<div id="horari_cartellera">
								<form name="frmcartellera_venda" action="venda.jsp"  method="post">
								<input type="Hidden" name="nom_pelicula" value="<%=ses.getPelicula().getTitol() %>" />
								<input type="Hidden" name="idpelicula" value="<%=ses.getPelicula().getId() %>" />
								<input type="Hidden" name="nom_sala" value="<%=ses.getSala().getNomSala() %>" />
								<input type="Hidden" name="idsala" value="<%=ses.getSala().getId() %>" />
								<input type="Hidden" name="data" value="<%=data %>" />
							<%
							
						}
						else
						{
							novaPeli = false;
						}
						%>
					
								<input type="Submit" name="venda" value="<%=ses.donarHora()%>" class="boto_horari" />					
								<input type="Hidden" name="idsessio" value="<%=ses.getId()%>" />
						<%
						sesAnt = ses;
						ses = (Sessio)it.next();
						if(sesAnt.getSala().getId()!=ses.getSala().getId()){
								//(novaPeli)&&(!novaSala)){ 							
						%>								
								</form>
							</div>
						
						<%}
						if(sesAnt.getSala().getId()!=ses.getSala().getId()){
								//(novaSala)&&(!novaPeli)){
							%>
						</div>
							<% 
						}

					}
				}
	
				%>								

			</div>
			Clica sobre l'horari d'una sessió per fer la compra o reserva d'entrades.	
			<br /><br />
		</span>
	</div>
	<div id="right">
		<jsp:include page="esquelet_adm/navegadreta.jsp"/>
	</div>	
<jsp:include page="esquelet_adm/footer.jsp"/>		