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
			Fitxa de genere
		</span>
		<br /><br />
		<span class="txt">
			<form name="frmadmpelicules" action="GestioPeliculesServlet" method="post">
			<div id="caixa_pelicules">			
				<%	
				Vector genere = new Vector();	
				genere = (Vector)session.getAttribute("genere");
				
					if (genere!=null)
					{

						Iterator itGenere = genere.iterator();
						%>
						<input type="Hidden" name="idGenere" class="caixa_text" value="<%=itGenere.next()%>" />
						genere<br />
						<input type="Text" name="nomGenere" class="caixa_text" value="<%=(String)itGenere.next()%>" />
						<br /><br />
						<input type="Submit" name="accio" value="eliminar genere" class="boto_accio" />
					<%
					}
					else
					{
					%>
						<input type="Hidden" name="idGenere" class="caixa_text" value="-1" />
						genere<br />
						<input type="Text" name="nomGenere" class="caixa_text" value="" />
						<br /><br />
						<input type="Submit" name="accio" value="afegir genere" class="boto_accio" /> 
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