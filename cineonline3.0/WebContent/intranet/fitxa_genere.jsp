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
			Fitxa de gènere
		</span>
		<br /><br />
		<span class="txt">
			<form name="frmadmpelicules" action="GestioPeliculesServlet" method="post">			
				<%	
				Vector genere = new Vector();	
				genere = (Vector)session.getAttribute("genere");
				
					if (genere!=null)
					{

						Iterator itGenere = genere.iterator();
						%>
						<input type="Hidden" name="idGenere" value="<%=itGenere.next()%>" />
						<input type="Hidden" name="accio" value="eliminar genere"/> 
						gènere<br />
						<input type="Text" name="nomGenere" value="<%=(String)itGenere.next()%>" />
						<br /><br />
						<input type="Submit" name="accio" value="eliminar gènere" class="boto_accio" />
					<%
					}
					else
					{
					%>
						<input type="Hidden" name="idGenere" value="-1" />
						<input type="Hidden" name="accio" value="afegir genere"/> 
						gènere<br />
						<input type="Text" name="nomGenere" value="" />
						<br /><br />
						<input type="Submit" name="bt_accio" value="afegir gènere" class="boto_accio" /> 
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