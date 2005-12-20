<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="gestioCinema.gestioSessions.Sessio" %>
<jsp:include page="esquelet/header.jsp"/>
	<div id="top">
		<jsp:include page="esquelet/top.jsp"/>		
	</div>
	<div id="left">
		<jsp:include page="esquelet/menu.jsp"/>
	</div>
	<div id="center">
		<span class="txt_titol">			
			Cartellera
		</span>
		<br /><br />
		<span class="txt">	
			Tria el dia de la cartellera
				<br /><br />
				<%
				String data2 = (String) session.getAttribute("dataActual");
				String args[] = data2.split(" ");
				String dataDMA = args[0];
				String args2[] = dataDMA.split("-");
				String any = args2[0];
				String mes = args2[1];
				String dia = args2[2];
				String data = args2[0] + args2[1] + args2[2];
				 %>
				 <%= data2 %>
				<form name="frmcartellera_query" action="GestioClientServlet" method="post">
					dia
					<select name="dia" style="width:45px">
					<%
						int i;
						String sel;
						for (i=1; i <= 31; i++)
						{
								sel = "";
								if(i== Integer.parseInt(dia))
								{
									sel = "selected";
								}
								
					%>
						<option value="<%= i %>" <%= sel %> /><%= i %>
					<%
						}
					%>
					</select>
					mes
					<select name="mes" style="width:45px">			
					<%
						
						for (i=1; i <= 12; i++)
						{
							sel = "";
							if(i== Integer.parseInt(mes))
							{
								sel = "selected";
							}
					%>
						<option value="<%= i %>" <%= sel %> /><%= i %>
					<%
						}
					%>
					</select>
					any
					<select name="anny" style="width:55px">			
			
					<%						
						for (i=2005; i <= 2010; i++)
						{
							sel = "";
							if(i== Integer.parseInt(any))
							{
								sel = "selected";
							}
					%>
						<option value="<%= i %>" <%= sel %> /><%= i  %>
					<%
						}
					%>
					</select>
					<input type="Submit" name="accio" value="cerca" class="boto_enllac" />
					<!-- 
					<input type="Submit" name="accio" value="ant" class="boto_enllac" align="right" />
					<input type="Submit" name="accio" value="seg" class="boto_enllac" align="right" />
					 -->
				</form>
				<br />
			<div id="caixa_cartellera">								
				<% 								
				Vector llista = new Vector();	
				llista = (Vector) session.getAttribute("llistaSessions");
				System.err.println("Numero de sessions a mostrar " + llista.size());
				if(llista!=null)
				{
					Iterator it = llista.iterator();
					if (it.hasNext())
					{
						%>
							<div id="titol_cartellera">SESSIONS DEL CINE a <%= data %></div>		
						<%															
						it = llista.iterator();
						boolean novaPelicula = true;
						while(it.hasNext())
						{
							Sessio s = (Sessio)it.next();
							%>
							<form name="frmcartellera_venda" action="GestioClientServlet"  method="post">
									<input type="Hidden" name="idSessio" value="<%= s.getId() %>" />
									<input type="Hidden" name="accio" value="venda" />																	
									<input type="Submit" name="bt_venda" value="<%= s.donaHora() %>" class="boto_horari" />							
							</form>	
						<%= "id sala " + s.getSala().getId() %>
						<%= "id peli " + s.getPelicula().getId() %>
						<%= "id sessio " + s.getId() %>
						<%= "<br />" %>
						<%} 
					}
				}
			%>
			</div>
			Clica sobre l'horari d'una sessió per fer la compra o reserva d'entrades.	
			<br /><br />
			<!--  
			
			
			 -->
			
			<!--  -->
		</span>
	</div>
	<div id="right">
		<jsp:include page="esquelet/navegadreta.jsp"/>
	</div>	
<jsp:include page="esquelet/footer.jsp"/>		