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
			Fitxa de nacionalitat
		</span>
		<br /><br />
		<span class="txt">
			<form name="frmadmpelicules" action="GestioPeliculesServlet" method="post">
			<div id="caixa_pelicules">			
				<%	
				Vector nac = new Vector();	
				nac = (Vector)session.getAttribute("nacionalitat");
				
					if (nac!=null)
					{
						String amm = nac.elements().nextElement().toString();
						amm = amm.replace("[","");
						amm = amm.replace("]","");
						amm = amm.replace(" ","");
						String args[] = amm.split(",");
						%>
						<input type="Hidden" name="idNacionalitat" class="caixa_text" value="<%=args[0]%>" />
						nom del la nacionalitat<br />
						<input type="Text" name="nom" class="caixa_text" value="<%=args[1]%>" />
						<br /><br />
						<input type="Submit" name="accio" value="modificar nacionalitat" class="boto_accio" />
						<input type="Submit" name="accio" value="eliminar nacionalitat" class="boto_accio" />
					<%
					}
					else
					{
					%>
						<input type="Hidden" name="idNacionalitat" class="caixa_text" value="-1" />
						nom del la nacionalitat<br />
						<input type="Text" name="nom" class="caixa_text" value="" />
						<br /><br />
						<input type="Submit" name="accio" value="afegir nacionalitat" class="boto_accio" /> 
				<%
					}
				%>

			</div>
			</form>
		</span>	
	</div>
	<div id="right">
			<jsp:include page="esquelet_adm/navegadreta.jsp"/>
	</div>	
<jsp:include page="esquelet_adm/footer.jsp"/>