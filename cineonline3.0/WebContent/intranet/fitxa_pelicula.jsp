<%@ page import="gestioCinema.gestioPelicules.Pelicula" %>
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
			Fitxa de pel·lícula
		</span>
		<br /><br />
		<span class="txt">
			<form name="frmadmpelicules" action="GestioPeliculesServlet" method="post">
			<div id="caixa_pelicules">			
				<%
					Pelicula peli = (Pelicula)session.getAttribute("pelicula");
					if (peli != null)
					{
						Vector generes = (Vector)session.getAttribute("generes");
						Iterator itge=generes.iterator();
						Vector nacionalitats = (Vector)session.getAttribute("nacionalitats");
						Iterator itnac=nacionalitats.iterator();
						%>
					<div id="columna1">
						<input type="Hidden" name="idPelicula"   value="<%= peli.getId() %>" />
						títol
						<input type="Text" name="titol"   value="<%= peli.getTitol() %>" />
						<br /><br />
						títol original
						<input type="Text" name="titolOriginal"   value="<%= peli.getTitolOriginal() %>" />
						<br /><br />
						any
						<input type="Text" name="anny"   maxlength="4" value="<%= peli.getAnny() %>" />
						<br /><br />
						nacionalitat
						<select name="nacionalitat">
						<%while(itnac.hasNext()){
							
							Vector nac = (Vector)itnac.next();
							Iterator itselect = nac.iterator();
							String idnac="";
							String nomnac="";
							String esaquest="";
							while(itselect.hasNext()){
								idnac = itselect.next().toString();
								nomnac = (String)itselect.next();
								if(peli.getNacionalitat().equals(idnac))
								{
									esaquest="selected";
								}
								else
								{
									esaquest="";
								}
								
							}
							%>
							<option	name="nacionalitat" value="<%=idnac%>" <%= esaquest %> /> <%=nomnac%> 
							<%							
						}
						
							%>
						</select>
						<br /><br />
						
						genere
						<select name="genere">
						<%while(itge.hasNext()){
							
							Vector ge = (Vector)itge.next();
							Iterator itselect = ge.iterator();
							String idge="";
							String nomge="";
							String esaquest="";
							while(itselect.hasNext()){
								idge = itselect.next().toString();
								nomge = (String)itselect.next();
								if(peli.getGenere().equals(idge))
								{
									esaquest="selected";
								}
								else
								{
									esaquest="";
								}
								
							}
							%>
							<option	name="genere" value="<%=idge%>" <%= esaquest %> /> <%=nomge%> 
							<%							
						}
							%>
						</select>
						<br /><br />
						durada (minuts)
						<input type="Text" name="durada"   value="<%= peli.duradaMinuts() %>" />
						<br /><br />
						edat recomenada
						<input type="Text" name="edatRecomenada"   value="<%= peli.getEdatRecomenada() %>" />
						<br /><br />
						tipus color
						<input type="Text" name="tipusColor"   value="<%= peli.getTipusColor() %>" />
						<br /><br />
						tipus sò
						<input type="Text" name="tipusSo"   value="<%= peli.getTipusSo() %>" />
					</div>
					<div id="columna2">
						director
						<input type="Text" name="director"   value="<%= peli.getDirector() %>" />
						<br /><br />
						guionista
						<input type="Text" name="guionista"   value="<%= peli.getGuionista() %>" />
						<br /><br />
						productor
						<input type="Text" name="productor"   value="<%= peli.getProductor() %>" />
						<br /><br />
						actors
						<textarea name="actors"    cols="30"><%= peli.getActors() %></textarea>
						<br /><br />
						sinopsis
						<textarea name="sinopsis"   cols="30"><%= peli.getSinopsis() %></textarea>
						<br /><br />
						web
						<input type="Text" name="urlWeb"   value="<%= peli.getUrlWeb() %>" />
						<br /><br />
						imatge
						<input type="File" name="urlImatge"   value="<%= peli.getUrlImatge() %>" />
						<br /><br />
						<input type="Submit" name="accio" value="modificar" class="boto_accio" />
						<input type="Submit" name="accio" value="eliminar" class="boto_accio" />
					</div>
					
					<%
					}
					else
					{
						Vector generes = (Vector)session.getAttribute("generes");
						Iterator itge=generes.iterator();
						Vector nacionalitats = (Vector)session.getAttribute("nacionalitats");
						Iterator itnac=nacionalitats.iterator();
					
					%>
					<div id="columna1">
						<input type="Hidden" name="id"  value="" />
						títol
						<input type="Text" name="titol"  value="" />
						<br /><br />
						títol original
						<input type="Text" name="titolOriginal"  value="" />
						<br /><br />
						any
						<input type="Text" name="anny"   maxlength="4" value="" />
						<br /><br />
						nacionalitat
						<select name="nacionalitat">
						<%while(itnac.hasNext()){
							
							Vector nac = (Vector)itnac.next();
							Iterator itselect = nac.iterator();
							String idnac="";
							String nomnac="";
							String esaquest="";
							while(itselect.hasNext()){
								idnac = itselect.next().toString();
								nomnac = (String)itselect.next();
															
							}
							%>
							<option	name="nacionalitat" value="<%=idnac%>" /> <%=nomnac%> 
							<%							
						}
						
							%>
						</select>
						<br /><br />
						genere
						<select name="genere">
						<%while(itge.hasNext()){
							
							Vector ge = (Vector)itge.next();
							Iterator itselect = ge.iterator();
							String idge="";
							String nomge="";
							String esaquest="";
							while(itselect.hasNext()){
								idge = itselect.next().toString();
								nomge = (String)itselect.next();
													
							}
							%>
							<option	name="genere" value="<%=idge%>"  /> <%=nomge%> 
							<%							
						}
							%>
						</select>
						<br /><br />
						durada (minuts)
						<input type="Text" name="durada"   value="" />
						<br /><br />
						edat recomenada
						<input type="Text" name="edatRecomenada"   value="" />
						<br /><br />
						tipus color
						<input type="Text" name="tipusColor"   value="" />
						<br /><br />
						tipus sò
						<input type="Text" name="tipusSo"   value="" />
					</div>
					<div id="columna2">
						director
						<input type="Text" name="director"   value="" />
						<br /><br />
						guionista
						<input type="Text" name="guionista"   value="" />
						<br /><br />
						productor
						<input type="Text" name="productor"   value="" />
						<br /><br />
						actors
						<textarea name="actors"    cols="30"></textarea>
						<br /><br />
						sinopsis
						<textarea name="sinopsis" cols="30"></textarea>
						<br /><br />
						web
						<input type="Text" name="urlWeb" value="" />
						<br /><br />
						imatge
						<input type="File" name="urlImatge"  value="" />
						<br /><br />
						<input type="Submit" name="accio" value="afegir" id="afegirPelicula" class="boto_accio" /> 
					</div>
					
					
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