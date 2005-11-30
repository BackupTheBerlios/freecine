<jsp:include page="esquelet_adm/header.jsp"/>
	<div id="top">
		<jsp:include page="esquelet_adm/top.jsp"/>		
	</div>
	<div id="left">
		<jsp:include page="esquelet_adm/menu.jsp"/>
	</div>
	<div id="center">
		<span class="txt_titol">			
			Error
		</span>
		<br /><br />
		<span class="txt">
		<%
			String error = request.getParameter("error");	
			if (error != null)
			{
				%>
				<%= error %>
				<%
			}
			else
			{
				%>
				Error no especificat
				<%
			}
		 %>
		</span>	
	</div>
	<div id="right">
			<jsp:include page="esquelet_adm/navegadreta.jsp"/>
	</div>	
<jsp:include page="esquelet_adm/footer.jsp"/>