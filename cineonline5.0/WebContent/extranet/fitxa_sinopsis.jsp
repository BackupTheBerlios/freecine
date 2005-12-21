<%@ page import="gestioCinema.gestioPelicules.Pelicula" %>
<jsp:include page="esquelet/header.jsp"/>
	<div id="top">
		<jsp:include page="esquelet/top.jsp"/>		
	</div>
	<div id="left">
		<jsp:include page="esquelet/menu.jsp"/>
	</div>
	<div id="center">
		<span class="txt_titol">			
			Fitxa de sinòpsis
		</span>
		<br /><br />
		<span class="txt">
		<%
					Pelicula peli = (Pelicula)session.getAttribute("pelicula");
					if (peli != null)
					{
						%>
			<div id="caixa_sinopsis">
				<div id="titol_caixa"><%=peli.getTitol()%> |  <%=peli.getTitolOriginal()%> </div>
				<div id="col_portada">
					<div id="foto_portada">
						<%
							if ((peli.getUrlImatge()!= null)&& (!peli.getUrlImatge().equals("")))
							{
								
						%>						
							<img src='extranet/../caratules/<%= peli.getUrlImatge() %>' alt="" width="136" height="176" hspace="2" vspace="2" border="0" />
						<%
							}
							else
							{
			
							%>							
							<img src='extranet/imatges/no_disponible.gif' alt="" width="136" height="176" hspace="2" vspace="2" border="0" />
						<%
							}
						%>
					</div>
						<form name="frmsinopsis_cartellera" action="GestioClientServlet" method="post" class="form_memu">
							<input type="Hidden" name="accio" value="goCartellera" />
							<input type="Submit" name="opcio_menu" value="cartellera" class="boto_enllac" />
						</form>
				</div>
				<div id="contingut_sinopsis">
					<span class="txt_titol_caixa">
					director
					</span>
					<span class="txt_caixa">
					<%= peli.getDirector() %>
					</span>
					<br />
					<span class="txt_titol_caixa">
					guionista
					</span>
					<span class="txt_caixa">
					<%= peli.getGuionista() %>
					</span>
					<br />
					<span class="txt_titol_caixa">
					productor
					</span>
					<span class="txt_caixa">
					<%= peli.getProductor() %>
					</span>
					<br />
					<span class="txt_titol_caixa">
					actors
					</span>
					<span class="txt_caixa">
					<%= peli.getActors() %>
					</span>
					<br />			
					<span class="txt_titol_caixa">
					any
					</span>
					<span class="txt_caixa">
					<%= peli.getAnny() %>
					</span>
					<br />
					<span class="txt_titol_caixa">
					nacionalitat
					</span>
					<span class="txt_caixa">
					<%= peli.getNacionalitat() %>
					</span>
					<br />
					<span class="txt_titol_caixa">
					durada
					</span>
					<span class="txt_caixa">
					<%= peli.getDurada() %>
					</span>
					<br />
					<span class="txt_titol_caixa">
					edat recomenada
					</span>
					<span class="txt_caixa">
					<%= peli.getEdatRecomenada() %>
					</span>
					<br />
					<span class="txt_titol_caixa">
					web
					</span>
					<span class="txt_caixa">
					<a href="http://<%= peli.getUrlWeb() %>" class="web" target="_blank"><%= peli.getUrlWeb() %></a>
					</span>
					<br /><br />
					<span class="txt_titol_caixa">
					sinòpsis
					</span>
					<span class="txt_caixa">
					<%= peli.getSinopsis() %>
					</span>			
					<br />
				</div>		
			</div>
			<%}%>
			<br /><br />
			<form name="frmsinopsis_cartellera" action="GestioClientServlet" method="post" class="form_memu">
					<input type="Hidden" name="accio" value="llistarPelicules" />
					<input type="Submit" name="opcio_menu" value="tornar" class="boto_enllac" />
				</form>
		</span>	
	</div>
	<div id="right">
			<jsp:include page="esquelet/navegadreta.jsp"/>
	</div>	
<jsp:include page="esquelet/footer.jsp"/>