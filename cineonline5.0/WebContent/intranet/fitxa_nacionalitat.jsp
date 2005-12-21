<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Iterator" %>
<jsp:include page="esquelet_adm/header.jsp"/>
	<div id="top">
		<jsp:include page="esquelet_adm/top.jsp"/>		
	</div>
	<div id="left">
		<jsp:include page="esquelet_adm/menu.jsp"/>
	</div>
	<div id="center">
		<span class="txt_titol">			
			Fitxa de nacionalitat
		</span>
		<br /><br />
		<span class="txt">
			<form name="frmadmpelicules" action="GestioPeliculesServlet" method="post">			
				<%	
				Vector nac = new Vector();	
				nac = (Vector)session.getAttribute("nacionalitat");
				
					if (nac!=null)
					{
						Iterator itNac = nac.iterator();
						%>
						<input type="Hidden" name="idNacionalitat" value="<%= itNac.next() %>" />
						nacionalitat<br />
						<%if(itNac.hasNext()){ %>
						<input type="Text" name="nomNacionalitat" value="<%=(String) itNac.next()%>" />
						<%} else{%>
						<input type="Text" name="nomNacionalitat" value="" />
						<%} %>
						<br /><br />
						<input type="Submit" name="accio" value="eliminar nacionalitat" class="boto_accio" />
					<%
					}
					else
					{
					%>
						<input type="Hidden" name="idNacionalitat" value="-1" />
						nacionalitat<br />
						<input type="Text" name="nomNacionalitat" value="" />
						<br /><br />
						<input type="Submit" name="accio" value="afegir nacionalitat" class="boto_accio" /> 
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