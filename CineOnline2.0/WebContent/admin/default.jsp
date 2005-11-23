<%
	String nomPage = request.getParameter("opcio_menu");
	String nomPagina = "";
%>
<jsp:include page="esquelet_adm/header.jsp"/>
	<div id="top">
		<jsp:include page="esquelet_adm/top.jsp"/>		
	</div>
	<div id="left">
		<%
		String vendaPage = request.getParameter("venda");
		if (vendaPage != null)
		{
			nomPagina = "venda.jsp";
		}
		
		if ((nomPage != null)&&(nomPage.compareTo("sortir")!=0))
		{	
		%>
			
			<jsp:include page="esquelet_adm/menu.jsp"/>		
		<%
		}
		%>
	</div>
	<div id="center">
	  	<% 		
		if (nomPage != null)
		{			
			if ((nomPage.compareTo("cartellera")==0)||(nomPage.compareTo("accedir")==0))
			{
				nomPagina = "cartellera.jsp";
			}
			else
			{
				if(nomPage.compareTo("entrades taquilla")==0)
				{
					nomPagina = "entrades_taquilla.jsp";
				}
				else
				{
					if(nomPage.compareTo("entrades online")==0)
					{
						nomPagina = "entrades_online.jsp";
					}
					else
					{
						if(nomPage.compareTo("pel·lícules")==0)
						{
							nomPagina = "pelicules.jsp";
						}
						else
						{
							if(nomPage.compareTo("sales")==0)
							{
								nomPagina = "sales.jsp";
							}
							else
							{
								if(nomPage.compareTo("sessions")==0)
								{
									nomPagina = "sessions.jsp";
								}
								else
								{
									if(nomPage.compareTo("sortir")==0)
									{
										nomPagina = "login.jsp";
									}
									else
									{
										if((nomPage.compareTo("reservar")==0)||(nomPage.compareTo("comprar")==0))
										{
											nomPagina = "finalitzar.jsp";
										}
										else
										{
											nomPagina = "login.jsp";
										}
									}
								}
							}
						}
					}
				}
			}
		}
		else
		{
			nomPagina = "login.jsp";
		}
		
		

		
		%>
		<br />
		<jsp:include page="<%=nomPagina %>"/>
		<br />
	</div>
	<div id="right">
		<%
		if ((nomPage != null)&&(nomPage.compareTo("sortir")!=0))
		{				
		%>
			<jsp:include page="esquelet_adm/navegadreta.jsp"/>
		<%
		}
		%>
		
	</div>	
<jsp:include page="esquelet_adm/footer.jsp"/>
