<jsp:include page="esquelet_adm/header.jsp"/>
	<div id="top">
		<jsp:include page="esquelet_adm/top.jsp"/>		
	</div>
	<div id="left">
	</div>
	<div id="center">
		<span class="txt_titol">			
			Identificaci�
		</span>
		<span class="txt">
					<%  String msg = request.getParameter("missatge");
					if(msg!=null){
						%>
						<br /><br />
						<%= msg %>
						<br /><br />
						<%
					}
					%>
					
			<form name="frmlogin" action="GestioCinemaServlet" method="post">
				nom d'usuari
				<input type="Text" name="nomUsuari" maxlength="30" value="" class="caixa_text" />
				<br /><br />
				password
				<input type="Password" name="pwd" maxlength="20" value="" class="caixa_text" />
				<br /><br />
				<input type="Submit" name="accio" value="accedir" class="boto_venda">	
			</form>
			L'�s inapropiat del sistema est� penat amb multes majors a 30.000 euros.
		</span>
	</div>
	<div id="right">
	</div>	
<jsp:include page="esquelet_adm/footer.jsp"/>