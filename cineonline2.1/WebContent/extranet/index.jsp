<jsp:include page="esquelet/header.jsp"/>
	<div id="top">
		<jsp:include page="esquelet/top.jsp"/>		
	</div>
	<div id="left">
		<jsp:include page="esquelet/menu.jsp"/>		
	</div>
	<div id="center">
	  	<% 
		String nomPage = request.getParameter("opcio_menu");
		String nomPagina = "";
		if (nomPage != null)
		{
			if (nomPage.compareTo("cartellera")==0)
			{
				nomPagina = "cartellera.jsp";
			}
			else
			{
				if(nomPage.compareTo("sinòpsis")==0)
				{
					nomPagina = "sinopsis.jsp";
				}
				else
				{
					if(nomPage.compareTo("entrades online")==0)
					{
						nomPagina = "venda.jsp";
					}
					else
					{
						if(nomPage.compareTo("tarifes")==0)
						{
							nomPagina = "tarifes.jsp";
						}
						else
						{
							if(nomPage.compareTo("on som?")==0)
							{
								nomPagina = "onsom.jsp";
							}
							else
							{
								if(nomPage.compareTo("serveis")==0)
								{
									nomPagina = "serveis.jsp";
								}
								else
								{
									if(nomPage.compareTo("continuar")==0)
									{
										nomPagina = "ticket.jsp";
									}
									else
									{
										if((nomPage.compareTo("reservar")==0)||(nomPage.compareTo("comprar")==0))
										{
											nomPagina = "finalitzar.jsp";
										}
										else
										{
											nomPagina = "cineonline.jsp";
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
			nomPagina = "cineonline.jsp";
		}
		
		String vendaPage = request.getParameter("venda");
		if (vendaPage != null)
		{
			nomPagina = "venda.jsp";
		}
		
		%>
		<br />
		<jsp:include page="<%=nomPagina %>"/>
		<br />
	</div>
	<div id="right">
		<jsp:include page="esquelet/navegadreta.jsp"/>
	</div>	
<jsp:include page="esquelet/footer.jsp"/>
