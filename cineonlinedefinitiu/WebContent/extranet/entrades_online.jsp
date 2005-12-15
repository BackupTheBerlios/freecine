<jsp:include page="esquelet/header.jsp"/>
	<div id="top">
		<jsp:include page="esquelet/top.jsp"/>		
	</div>
	<div id="left">
		<jsp:include page="esquelet/menu.jsp"/>
	</div>
	<div id="center">
		<span class="txt_titol">			
			Entrades online
		</span>
		<br /><br />
		<span class="txt">
		Les entrades online.... j0sajs0ajs0as0
		<br /><br />
				<form name="frmsinopsis_cartellera" action="GestioClientServlet" method="post" class="form_memu">
					<input type="Hidden" name="accio" value="goCartellera" />
					<input type="Submit" name="opcio_menu" value="cartellera" class="boto_venda" />
				</form>
		</span>
	</div>
	<div id="right">
	</div>	
<jsp:include page="esquelet/footer.jsp"/>
