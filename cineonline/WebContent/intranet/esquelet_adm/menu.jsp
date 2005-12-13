<<<<<<< menu.jsp
<form name="frmmenu" action="cartellera.jsp" method="post" class="form_memu">
	<input type="Submit" name="opcio_menu" class="boto_menu" value="cartellera">
</form>
<form name="frmmenu" action="entrades_taquilla.jsp" method="post" class="form_memu">
	<input type="Submit" name="opcio_menu" class="boto_menu" value="entrades taquilla" />
</form>
<form name="frmmenu" action="entrades_online.jsp" method="post" class="form_memu">
	<input type="Submit" name="opcio_menu" class="boto_menu" value="entrades online" />
</form>
<!--  action="../../gestioPeliculesServlet.jsp"  -->
<form name="frmmenu" action="GestioPeliculesServlet" method="post" class="form_memu">
	<input type="Hidden" name="accio" value="llistarPelicules" />
	<input type="Submit" name="opcio_menu" class="boto_menu" value="pel·lícules" />
</form>
<form name="frmmenu" action="GestioSalesServlet" method="post" class="form_memu">
	<input type="Hidden" name="accio" value="llistarSales" />
	<input type="Submit" name="opcio_menu" class="boto_menu" value="sales" />
</form>
<form name="frmmenu" action="GestioSessionsServlet" method="post" class="form_memu">
	<input type="Submit" name="opcio_menu" class="boto_menu" value="sessions" />
</form>
<form name="frmmenu" action="GestioCinemaServlet" method="post" class="form_memu">
	<input type="Hidden" name="accio" value="sortir" />
	<input type="Submit" name="opcio_menu" class="boto_menu" value="sortir" />
=======
<form name="frmmenu" id="frmmenu" action="" method="post">
	<input type="Submit" name="cartellera.jsp" class="boto_menu" value="cartellera" />
	<input type="Submit" name="entrades_taquilla.jsp" class="boto_menu" value="entrades taquilla" />
	<input type="Submit" name="entrades_online.jsp" class="boto_menu" value="entrades online" />
	<input type="Submit" name="pelicules.jsp" class="boto_menu" value="pel·lícules" />
	<input type="Submit" name="sales.jsp" class="boto_menu" value="sales" />
	<input type="Submit" name="sessions.jsp" class="boto_menu" value="sessions" />
	<input type="Submit" name="login.jsp" class="boto_menu" value="sortir" />
>>>>>>> 1.3
</form>
<<<<<<< menu.jsp
<form name="frmmenu" action="GestioCinemaServlet" method="post" class="form_memu">
	<input type="Hidden" name="accio" value="consola" />
	<input type="Submit" name="opcio_menu" class="boto_menu" value="Gestió cinema" />
=======

<form name="frmmenu2" id="frmmenu2" action="./gestioPeliculesServlet.jsp" method="post">
	<input type="Submit" name="opcio_menu" class="boto_menu" value="pel·lícules" />
	<input type="Hidden" name="accio" value="llistarPelicules" />
>>>>>>> 1.3
</form>