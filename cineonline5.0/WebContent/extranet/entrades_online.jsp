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
		Una entrada permet l'accés d'un client al cine per una sola sessió.
		<br /><br />
		L'emissió de les entrades online es realitza en les instal?lacions del cinema amb la presentació prèvia del número de reserva. Les de taquilla s'adquireixen a la taquilla del cinema pagant a l'instant.
		<br /><br />
		La reserva o compra d?entrades és per una sessió en concret.
		<br /><br />
		Una compra és l'adquisició d'un conjunt d'entrades que es pot realitzar a les taquilles del cinema o bé per Internet. El pagament es fa a l'instant de la compra.
		<br /><br />
		En una compra totes les seves entrades són per una sola sessió. Si el client desitja entrades d'altres sessions haurà d'iniciar una nova compra. 
		<br /><br />
		Una reserva permet al client assegurar-se butaques en una sessió amb un temps d'antelació. Una reserva permet fer una compra fent possible el pagament d'entrades online. El pagament d'una reserva s'ha de fer efectiu amb un temps de 30 minuts d'antelació al inici de la sessió a les taquilles. A no ser així la reserva es cancel?la per a posar a disposició del públic. La reserva només es pot fer online.
		<br /><br />
		La realització d'una reserva o compra pot fer-se amb cinc dies d'antelació com a màxim de la data prevista, pels possibles canvis a la cartellera.
		<br /><br />
		Per iniciar una compra o reserva cal escollir una sessió des de la cartellera.
		<br /><br />
				<form name="frmsinopsis_cartellera" action="GestioClientServlet" method="post">
					<input type="Hidden" name="accio" value="goCartellera" />
					<input type="Submit" name="opcio_menu" value="cartellera" class="boto_enllac" />
				</form>
		</span>
	</div>
	<div id="right">
	</div>	
<jsp:include page="esquelet/footer.jsp"/>
