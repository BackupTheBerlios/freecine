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
		Una entrada permet l'acc�s d'un client al cine per una sola sessi�.
		<br /><br />
		L'emissi� de les entrades online es realitza en les instal?lacions del cinema amb la presentaci� pr�via del n�mero de reserva. Les de taquilla s'adquireixen a la taquilla del cinema pagant a l'instant.
		<br /><br />
		La reserva o compra d?entrades �s per una sessi� en concret.
		<br /><br />
		Una compra �s l'adquisici� d'un conjunt d'entrades que es pot realitzar a les taquilles del cinema o b� per Internet. El pagament es fa a l'instant de la compra.
		<br /><br />
		En una compra totes les seves entrades s�n per una sola sessi�. Si el client desitja entrades d'altres sessions haur� d'iniciar una nova compra. 
		<br /><br />
		Una reserva permet al client assegurar-se butaques en una sessi� amb un temps d'antelaci�. Una reserva permet fer una compra fent possible el pagament d'entrades online. El pagament d'una reserva s'ha de fer efectiu amb un temps de 30 minuts d'antelaci� al inici de la sessi� a les taquilles. A no ser aix� la reserva es cancel?la per a posar a disposici� del p�blic. La reserva nom�s es pot fer online.
		<br /><br />
		La realitzaci� d'una reserva o compra pot fer-se amb cinc dies d'antelaci� com a m�xim de la data prevista, pels possibles canvis a la cartellera.
		<br /><br />
		Per iniciar una compra o reserva cal escollir una sessi� des de la cartellera.
		<br /><br />
				<form name="frmsinopsis_cartellera" action="GestioClientServlet" method="post" class="form_memu">
					<input type="Hidden" name="accio" value="goCartellera" />
					<input type="Submit" name="opcio_menu" value="cartellera" class="boto_enllac" />
				</form>
		</span>
	</div>
	<div id="right">
	</div>	
<jsp:include page="esquelet/footer.jsp"/>
