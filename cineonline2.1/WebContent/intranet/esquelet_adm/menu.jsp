<form name="frmmenu" id="frmmenu" action="default.jsp" method="post">
	<input type="Submit" name="opcio_menu" class="boto_menu" value="cartellera" />
	<input type="Submit" name="opcio_menu" class="boto_menu" value="entrades taquilla" />
	<input type="Submit" name="opcio_menu" class="boto_menu" value="entrades online" />
	<input type="Submit" name="opcio_menu" class="boto_menu" value="pel·lícules" />
	<input type="Submit" name="opcio_menu" class="boto_menu" value="sales" />
	<input type="Submit" name="opcio_menu" class="boto_menu" value="sessions" />
	<input type="Submit" name="opcio_menu" class="boto_menu" value="sortir" />
</form>

<form name="frmmenu" id="frmmenu" action="./gestioPeliculesServlet.jsp" method="post">
	<input type="Submit" name="opcio_menu" class="boto_menu" value="pel·lícules" />
	<input type="Hidden" name="accio" value="llistarPelicules" />
</form>