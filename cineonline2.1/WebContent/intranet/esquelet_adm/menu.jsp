<form name="frmmenu" id="frmmenu" action="" method="post">
	<input type="Submit" name="cartellera.jsp" class="boto_menu" value="cartellera" />
	<input type="Submit" name="entrades_taquilla.jsp" class="boto_menu" value="entrades taquilla" />
	<input type="Submit" name="entrades_online.jsp" class="boto_menu" value="entrades online" />
	<input type="Submit" name="pelicules.jsp" class="boto_menu" value="pel·lícules" />
	<input type="Submit" name="sales.jsp" class="boto_menu" value="sales" />
	<input type="Submit" name="sessions.jsp" class="boto_menu" value="sessions" />
	<input type="Submit" name="login.jsp" class="boto_menu" value="sortir" />
</form>

<form name="frmmenu2" id="frmmenu2" action="./gestioPeliculesServlet.jsp" method="post">
	<input type="Submit" name="opcio_menu" class="boto_menu" value="pel·lícules" />
	<input type="Hidden" name="accio" value="llistarPelicules" />
</form>