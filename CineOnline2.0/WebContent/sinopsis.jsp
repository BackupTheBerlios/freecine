<span class="txt_titol">			
	Sinòpsis
</span>
<br /><br />
<span class="txt">			
	Si abans de comprar o reservar l'entrada vols triar bé la teva pel·lícula consulta les sinòpsis!!
	<br /><br />
	Cerca la teva pel·lícula
	<br /><br />
	<form name="frmsinopsis_query" action="javascript:alert('Fent la consulta...');">
		títol <input type="Text" name="titol" maxlength="255" class="caixa_text" />
		director <input type="Text" name="director" maxlength="255" class="caixa_text" />
		any <input type="Text" name="any" maxlength="4" class="caixa_text" size="4" id="any" />
		entre
		<select name="encartellera" class="caixa_text">
			<option value="cartellera" />cartellera
			<option value="totes" />totes
		</select>
		<br /><br />
		<center><input type="Submit" name="cerca" value="cerca" class="boto_venda" /></center>
	</form>
	<br />
	
	<div id="caixa_sinopsis">
		<div id="titol_caixa">Parretis de cine / Parretis making movies </div>
		<div id="col_portada">
			<div id="foto_portada">
				<img src="imatges/portada_parretis.gif" alt="" width="136" height="176" hspace="2" vspace="2" border="0" />
			</div>
				<form name="frmsinopsis_venda" action="index.jsp">
					<input type="Hidden" name="idpelicula" value="1" />
					<input type="Submit" name="opcio_menu" value="cartellera" class="boto_venda" />
				</form>
		</div>
		<div id="contingut_sinopsis">
			<span class="txt_titol_caixa">
			director
			</span>
			<span class="txt_caixa">
			Víctor Oterus
			</span>
			<br />
			<span class="txt_titol_caixa">
			guionista
			</span>
			<span class="txt_caixa">
			Òscar RossifumiOne
			</span>
			<br />
			<span class="txt_titol_caixa">
			productor
			</span>
			<span class="txt_caixa">
			Oriol Oriparres
			</span>
			<br />
			<span class="txt_titol_caixa">
			actors
			</span>
			<span class="txt_caixa">
			Ivan Isevic, Víctor Oterus, Òscar RossifumiOne, Oriol Oriparres i jo.
			</span>
			<br />			
			<span class="txt_titol_caixa">
			any
			</span>
			<span class="txt_caixa">
			2005
			</span>
			<br />
			<span class="txt_titol_caixa">
			nacionalitat
			</span>
			<span class="txt_caixa">
			CAT
			</span>
			<br />
			<span class="txt_titol_caixa">
			durada
			</span>
			<span class="txt_caixa">
			148 minuts
			</span>
			<br />
			<span class="txt_titol_caixa">
			edat recomanada
			</span>
			<span class="txt_caixa">
			18 anys
			</span>
			<br />
			<span class="txt_titol_caixa">
			web
			</span>
			<span class="txt_caixa">
			<a href="http://www.parretisdecine.com" class="web" target="_blank">www.parretisdecine.com</a>
			</span>
			<br /><br />
			<span class="txt_titol_caixa">
			sinòpsis
			</span>
			<span class="txt_caixa">
			Joe i els seus amics estudien informàtica de sistemes a la facultat de matemàtiques de la Universitat de Barcelona.
			<br />
			De cop i volta els hi proposen fer un petit sistema de compra i reserva online d'entrades de cine.
			<br />
			A partir d'aquí sorgeixen múltiples aventures entre els Parretis abans d'entregar el projecte final.			
			</span>			
			<br />
		</div>		
	</div>
</span>