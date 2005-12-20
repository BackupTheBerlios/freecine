--funció que insereix una sessio amb una data tants dies posteriors com indiqui el primer paràmetre
--i amb l'hora que indiqui el segon
CREATE OR REPLACE FUNCTION inserir_sessio( INTEGER, TIME, MONEY, INTEGER, INTEGER ) RETURNS TIMESTAMP WITHOUT TIME ZONE AS '
	DECLARE
		suma_dies ALIAS FOR $1;
		hora ALIAS FOR $2;
		dies_interv INTERVAL;
		data_hora TIMESTAMP WITHOUT TIME ZONE;
		data_actual DATE;
	BEGIN
		dies_interv = INTERVAL ''1 day'' * suma_dies;
		data_actual = NOW();
		data_hora =data_actual+hora+ dies_interv;
		INSERT INTO sessio (data_hora_inici, preu, id_sala, id_pelicula) VALUES ( data_hora, $3, $4, $5);
		RETURN data_hora;
	END;'
LANGUAGE 'plpgsql';


SELECT * FROM inserir_sessio ( 0, '14:00:00', '5.5', 0, 0);
SELECT * FROM inserir_sessio ( 0, '15:00:00', '5.5', 0, 0);
SELECT * FROM inserir_sessio ( 0, '18:00:00', '5.5', 0, 0);
SELECT * FROM inserir_sessio ( 0, '21:00:00', '5.5', 0, 0);
SELECT * FROM inserir_sessio ( 1, '00:00:00', '5.5', 0, 0);
SELECT * FROM inserir_sessio ( 1, '15:00:00', '5.5', 0, 0);
SELECT * FROM inserir_sessio ( 1, '18:00:00', '5.5', 0, 0);
SELECT * FROM inserir_sessio ( 1, '21:00:00', '5.5', 0, 0);
SELECT * FROM inserir_sessio ( 2, '00:00:00', '5.5', 0, 0);
SELECT * FROM inserir_sessio ( 2, '15:00:00', '5.5', 0, 0);
SELECT * FROM inserir_sessio ( 2, '18:00:00', '5.5', 0, 0);
SELECT * FROM inserir_sessio ( 2, '21:00:00', '5.5', 0, 0);
SELECT * FROM inserir_sessio ( 3, '00:00:00', '5.5', 0, 0);
SELECT * FROM inserir_sessio ( 3, '15:00:00', '5.5', 0, 0);
SELECT * FROM inserir_sessio ( 3, '18:00:00', '5.5', 0, 0);
SELECT * FROM inserir_sessio ( 3, '21:00:00', '5.5', 0, 0);
SELECT * FROM inserir_sessio ( 4, '00:00:00', '5.5', 0, 0);

SELECT * FROM inserir_sessio ( 0, '15:00:00', '5.5', 1, 0);
SELECT * FROM inserir_sessio ( 0, '18:00:00', '5.5', 1, 0);
SELECT * FROM inserir_sessio ( 0, '21:00:00', '5.5', 1, 0);
SELECT * FROM inserir_sessio ( 1, '00:00:00', '5.5', 1, 0);
SELECT * FROM inserir_sessio ( 1, '15:00:00', '5.5', 1, 0);
SELECT * FROM inserir_sessio ( 1, '18:00:00', '5.5', 1, 0);
SELECT * FROM inserir_sessio ( 1, '21:00:00', '5.5', 1, 0);
SELECT * FROM inserir_sessio ( 2, '00:00:00', '5.5', 1, 0);
SELECT * FROM inserir_sessio ( 2, '15:00:00', '5.5', 1, 0);
SELECT * FROM inserir_sessio ( 2, '18:00:00', '5.5', 1, 0);
SELECT * FROM inserir_sessio ( 2, '21:00:00', '5.5', 1, 0);
SELECT * FROM inserir_sessio ( 3, '00:00:00', '5.5', 1, 0);
SELECT * FROM inserir_sessio ( 3, '15:00:00', '5.5', 1, 0);
SELECT * FROM inserir_sessio ( 3, '18:00:00', '5.5', 1, 0);
SELECT * FROM inserir_sessio ( 3, '21:00:00', '5.5', 1, 0);
SELECT * FROM inserir_sessio ( 4, '00:00:00', '5.5', 1, 0);

SELECT * FROM inserir_sessio ( 0, '15:00:00', '5.5', 2, 1);
SELECT * FROM inserir_sessio ( 0, '18:00:00', '5.5', 2, 1);
SELECT * FROM inserir_sessio ( 0, '21:00:00', '5.5', 2, 1);
SELECT * FROM inserir_sessio ( 1, '00:00:00', '5.5', 2, 1);
SELECT * FROM inserir_sessio ( 1, '15:00:00', '5.5', 2, 1);
SELECT * FROM inserir_sessio ( 1, '18:00:00', '5.5', 2, 1);
SELECT * FROM inserir_sessio ( 1, '21:00:00', '5.5', 2, 1);
SELECT * FROM inserir_sessio ( 2, '00:00:00', '5.5', 2, 1);
SELECT * FROM inserir_sessio ( 2, '15:00:00', '5.5', 2, 1);
SELECT * FROM inserir_sessio ( 2, '18:00:00', '5.5', 2, 1);
SELECT * FROM inserir_sessio ( 2, '21:00:00', '5.5', 2, 1);
SELECT * FROM inserir_sessio ( 3, '00:00:00', '5.5', 2, 1);
SELECT * FROM inserir_sessio ( 3, '15:00:00', '5.5', 2, 1);
SELECT * FROM inserir_sessio ( 3, '18:00:00', '5.5', 2, 1);
SELECT * FROM inserir_sessio ( 3, '21:00:00', '5.5', 2, 1);
SELECT * FROM inserir_sessio ( 4, '00:00:00', '5.5', 2, 1);

SELECT * FROM inserir_sessio ( 0, '15:00:00', '5.5', 3, 2);
SELECT * FROM inserir_sessio ( 0, '18:00:00', '5.5', 3, 2);
SELECT * FROM inserir_sessio ( 0, '21:00:00', '5.5', 3, 2);
SELECT * FROM inserir_sessio ( 1, '00:00:00', '5.5', 3, 2);
SELECT * FROM inserir_sessio ( 1, '15:00:00', '5.5', 3, 2);
SELECT * FROM inserir_sessio ( 1, '18:00:00', '5.5', 3, 2);
SELECT * FROM inserir_sessio ( 1, '21:00:00', '5.5', 3, 2);
SELECT * FROM inserir_sessio ( 2, '00:00:00', '5.5', 3, 2);
SELECT * FROM inserir_sessio ( 2, '15:00:00', '5.5', 3, 2);
SELECT * FROM inserir_sessio ( 2, '18:00:00', '5.5', 3, 2);
SELECT * FROM inserir_sessio ( 2, '21:00:00', '5.5', 3, 2);
SELECT * FROM inserir_sessio ( 3, '00:00:00', '5.5', 3, 2);
SELECT * FROM inserir_sessio ( 3, '15:00:00', '5.5', 3, 2);
SELECT * FROM inserir_sessio ( 3, '18:00:00', '5.5', 3, 2);
SELECT * FROM inserir_sessio ( 3, '21:00:00', '5.5', 3, 2);
SELECT * FROM inserir_sessio ( 4, '00:00:00', '5.5', 3, 2);
