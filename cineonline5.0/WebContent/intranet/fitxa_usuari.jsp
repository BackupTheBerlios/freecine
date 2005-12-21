<%@ page import="gestioCinema.gestioUsuaris.Usuari" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Vector" %>
<jsp:include page="esquelet_adm/header.jsp"/>
	<div id="top">
		<jsp:include page="esquelet_adm/top.jsp"/>		
	</div>
	<div id="left">
		<jsp:include page="esquelet_adm/menu.jsp"/>
	</div>
	<div id="center">
		<span class="txt_titol">			
			Fitxa d'usuari
		</span>
		<br /><br />
		<span class="txt">
			<form name="frmadmpelicules" action="GestioUsuarisServlet" method="post">		
				<%
					Usuari us = (Usuari)session.getAttribute("usuari");
					if (us != null)
					{
						Vector rols = (Vector)session.getAttribute("rols");
						Iterator itro=rols.iterator();
						
						%>
						<input type="Hidden" name="idUsuari"   value="<%= us.getId() %>" />
						login
						<input type="Text" name="login"   value="<%= us.getLogin() %>" />
						<br /><br />
						password
						<input type="Text" name="password"   value="<%= us.getPassword() %>" />
						<br /><br />						
						tipus
						<select name="tipus">
						<%
						String nomrol = "";
						String idrol = "";
						String esaquest = "";
						
						while(itro.hasNext()){
								idrol = itro.next().toString();
								if((""+us.getRol()).equals(idrol))
								{
									esaquest="selected";
								}
								else
								{
									esaquest="";
								}

								if(idrol.equals("0")){
									nomrol="Administrador";
								}else if(idrol.equals("1")){
									nomrol="Treballador";
								}else{
									nomrol="Client";
								}
							%>
							<option	name="tipusR" value="<%=idrol%>" <%= esaquest %> /> <%=nomrol%> 
							<%							
						}
						
							%>
						</select>
						<br /><br />						
						<input type="Submit" name="accio" value="modificar" class="boto_accio" />
						<input type="Submit" name="accio" value="eliminar" class="boto_accio" />
					<%
					}
					else
					{
						Vector rols = (Vector)session.getAttribute("rols");
						Iterator itro=rols.iterator();
						
						%>
						<input type="Hidden" name="idUsuari"   value="" />
						login
						<input type="Text" name="login"   value="" />
						<br /><br />
						password
						<input type="Text" name="password"   value="" />
						<br /><br />						
						tipus
						<select name="tipus">
						<%
						String nomrol = "";
						String idrol = "";
						
						while(itro.hasNext()){
								idrol = itro.next().toString();
								

								if(idrol.equals("0")){
									nomrol="Administrador";
								}else if(idrol.equals("1")){
									nomrol="Treballador";
								}else{
									nomrol="Client";
								}
							%>
							<option	name="tipusR" value="<%=idrol%>" /> <%=nomrol%> 
							<%							
						}
						
							%>
						</select>
						<br /><br />						
						<input type="Submit" name="accio" value="afegir usuari" class="boto_accio" />
						
					
					
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