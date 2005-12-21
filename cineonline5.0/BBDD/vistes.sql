--vista que mostra totes les reserves sense pagar d'una data concreta
CREATE OR REPLACE VIEW comp_res_sense_pagar AS
	SELECT compra_reserva.id, codi_entrega, data_hora, id_sessio, id_butaca, sessio.data_hora_inici AS data_hora_sessio
	FROM compra_reserva, sessio
	WHERE compra_reserva.id_sessio = sessio.id 
	AND compra_reserva.pagada = FALSE;

CREATE OR REPLACE FUNCTION compra_reserva_sense_pagar( DATE ) RETURNS SETOF comp_res_sense_pagar AS '
	DECLARE
		fila comp_res_sense_pagar%ROWTYPE;
		data ALIAS FOR $1;
	BEGIN
		FOR fila IN 
		SELECT * 
		FROM comp_res_sense_pagar AS compra_reserva_sense_pagar
		WHERE EXTRACT (YEAR FROM data_hora_sessio) = EXTRACT (YEAR FROM data)
		AND EXTRACT (MONTH FROM data_hora_sessio) = EXTRACT (MONTH FROM data)
		AND EXTRACT (DAY FROM data_hora_sessio) = EXTRACT (DAY FROM data)
		LOOP
			RETURN NEXT fila;
		END LOOP;
		RETURN;
	END;'
	LANGUAGE 'plpgsql';

--vista que mostra totes les reserves pagades d'una data concreta
CREATE OR REPLACE VIEW comp_res_pagada AS
	SELECT compra_reserva.id, codi_entrega, data_hora, id_sessio, id_butaca, sessio.data_hora_inici AS data_hora_sessio
	FROM compra_reserva, sessio
	WHERE compra_reserva.id_sessio = sessio.id 
	AND compra_reserva.pagada = TRUE;

CREATE OR REPLACE FUNCTION compra_reserva_pagada( DATE ) RETURNS SETOF comp_res_pagada AS '
	DECLARE
		fila comp_res_pagada%ROWTYPE;
		data ALIAS FOR $1;
	BEGIN
		FOR fila IN 
		SELECT * 
		FROM comp_res_pagada AS compra_reserva_pagada
		WHERE EXTRACT (YEAR FROM data_hora_sessio) = EXTRACT (YEAR FROM data)
		AND EXTRACT (MONTH FROM data_hora_sessio) = EXTRACT (MONTH FROM data)
		AND EXTRACT (DAY FROM data_hora_sessio) = EXTRACT (DAY FROM data)
		LOOP
			RETURN NEXT fila;
		END LOOP;
		RETURN;
	END;'
	LANGUAGE 'plpgsql';

--vista que mostra totes les pelicules que estaràn en taquilla durant un mes

CREATE OR REPLACE VIEW pel_taquilla_mes AS
	SELECT pelicula.id, pelicula.titol, pelicula.titol_original, 
	pelicula.anny, nacionalitat.pais as nacionalitat , genere.descripcio as genere, pelicula.durada, 
	pelicula.edat_recomanada, pelicula.tipus_color, pelicula.tipus_so , pelicula.director, 
	pelicula.guionista , pelicula.productor, pelicula.actors, pelicula.sinopsis, pelicula.url_web, pelicula.url_imatge, sessio.data_hora_inici AS data_hora_inici_sessio
	FROM pelicula, genere, nacionalitat, sessio
	WHERE pelicula.id_genere = genere.id
	AND pelicula.id_nacionalitat = nacionalitat.id
	AND pelicula.id = sessio.id_pelicula
	ORDER BY data_hora_inici_sessio;

CREATE OR REPLACE FUNCTION pelicules_taquilla_mes () RETURNS SETOF pel_taquilla_mes AS '
	DECLARE
		fila pel_taquilla_mes%ROWTYPE;
	BEGIN
		FOR fila IN
		SELECT  *
		FROM pel_taquilla_mes AS pelicula_taquilla_mes
		WHERE data_hora_inici_sessio BETWEEN NOW() AND (NOW() + INTERVAL ''1 MONTH'')
		LOOP
			RETURN NEXT fila;
		END LOOP;
		RETURN;
	END;'
	LANGUAGE 'plpgsql';

--vista que selecciona totes les butaques d'una sessió en concret amb un atribut indicant si estan comprades_reservades o no
CREATE OR REPLACE VIEW but_ses AS
	(SELECT butaca.id AS id_butaca, butaca.num_fila, butaca.num_columna, butaca.operativa, TRUE AS comprada_reservada, compra_reserva.pagada, ses.id AS id_sessio
	FROM butaca, sala, sessio AS ses, compra_reserva
	WHERE butaca.id_sala = sala.id
	AND sala.id=ses.id_sala
	AND compra_reserva.id_butaca = butaca.id
	AND EXISTS 
		( SELECT cr.id_butaca
		FROM compra_reserva AS cr
		WHERE cr.id_butaca=butaca.id
		AND compra_reserva.id_sessio=ses.id)
	)

	
	UNION

	(SELECT butaca.id AS id_butaca, butaca.num_fila, butaca.num_columna, butaca.operativa, FALSE AS comprada_reservada, FALSE AS pagada, ses.id AS id_sessio
	FROM butaca , sala, sessio AS ses
	WHERE butaca.id_sala = sala.id
	AND sala.id=ses.id_sala
	AND NOT EXISTS 
		( SELECT compra_reserva.id_butaca
		FROM compra_reserva
		WHERE compra_reserva.id_butaca=butaca.id
		AND compra_reserva.id_sessio = ses.id)
	)

	ORDER BY num_fila, num_columna;



CREATE OR REPLACE FUNCTION butaca_sessio( INTEGER ) RETURNS SETOF but_ses AS '
	DECLARE
		fila but_ses%ROWTYPE;
		ide_sessio ALIAS FOR $1;
	BEGIN
		FOR fila IN
		SELECT * 
		FROM but_ses AS butaca_sessio
		WHERE butaca_sessio.id_sessio = ide_sessio
		LOOP
			RETURN NEXT fila;
		END LOOP;
		RETURN;
	END;'
	LANGUAGE 'plpgsql';