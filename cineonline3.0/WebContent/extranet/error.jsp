<jsp:include page="esquelet/header.jsp"/>
	<div id="top">
		<jsp:include page="esquelet/top.jsp"/>		
	</div>
	<div id="left">
		<jsp:include page="esquelet/menu.jsp"/>
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
		 <br /><br />
		 <a href="javascript:history.back();">enrera</a>
		</span>	
	</div>
	<div id="right">
			<jsp:include page="esquelet/navegadreta.jsp"/>
	</div>	
<jsp:include page="esquelet/footer.jsp"/>