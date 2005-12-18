-- Modificacions
-- dom 20 nov 2005 02:46:08 CET VICTOR OTERO CELA





-- Modificació 1: De venda a lloguer
-- =================================

-- Que fa?
-- 1) Actualitza les unitats i posa les de la temporada passada a lloguer si no ho estaven
-- 2) Si un producto justo cumple la condición pero ya se vendió no se actualiza (no disponible)


CREATE OR REPLACE FUNCTION func_actualitza_unitats() RETURNS INTEGER AS '
DECLARE
	temporada INTEGER;
BEGIN
	UPDATE unitat SET llog_vend = ''LL'' WHERE (EXTRACT(YEAR FROM(NOW())) - EXTRACT(YEAR FROM(data_adquisicio))) >= 1 AND disponible = true AND llog_vend = ''V'';

RETURN 1;
END;
'LANGUAGE 'plpgsql';



-- Modificació 2:Cancelar/Eliminar factura
-- ========================================

-- Que fa?
-- 1) En cas de cancelar una factura, esborra les seves línies, i posa les unitats a disponibles.
--	a) Se comprueba si la factura no esta confirmdada
--	b) Se eliminan las linias con num de factura = nuestra factura
--	c) Se itera y se van poniendo otra vez a disponible todas las unidades.	  | Esto lo hace también el trigger de eliminar linia. Es una redundancia.	
--	d) Se itera y se borra la referencia de factura de las unidades.	< | 
--										  | UPDATE unitat SET disponible = true WHERE id_unitat = registre.id_unit;
--	e) Se elimina la factura.
-- 2) En cas de confirmar la factura, no es fa res. Ja  està tot fet :)
-- 3) Se podría hacer un trigger para ON DELETE.

CREATE OR REPLACE FUNCTION func_elimina_factura(INTEGER) RETURNS INTEGER AS '
DECLARE
tmpfactura RECORD;
registre RECORD;
BEGIN

SELECT * FROM factura INTO tmpfactura WHERE factura.id_factura = $1;
IF tmpfactura.confirm = false THEN
	FOR registre IN SELECT * FROM linia_factura WHERE linia_factura.num_factura = tmpfactura.id_factura ORDER BY id LOOP	
		DELETE FROM linia_factura WHERE linia_factura.id = registre.id AND linia_factura.num_factura = registre.num_factura;
		
	END LOOP;
	DELETE FROM factura WHERE factura.id_factura = $1;
	RETURN 1;   
ELSE
   RAISE NOTICE ''Aquesta factura ja ha estat confirmada: NO ES POT ELIMINAR'';
   RETURN NULL;
END IF;
END;
'LANGUAGE 'plpgsql';





-- Modificació 3: Borrar clients de > 5 anys
-- =========================================

-- Que fa?
-- 1) Esborra els clients que fa més de 5 anys que no tenen cap factura.
-- 2) Què fem amb els clients que no tenen cap factura? Suposo que es per que no han tingut temps a comprar res encara XD
-- 3) Què fem amb les factures? Les posem amb NIF 00000000Z... client de carrer.
-- 4) TODO: CAMBIAR LA RESTA DE NOW()-FACTURA.DATA PARA QUE ME DE AÑOS Y NO DIAS, QUE QUEDA MUY MAL XD 

CREATE OR REPLACE FUNCTION func_purga_clients() RETURNS INTEGER AS'
DECLARE
BEGIN
	DELETE FROM client WHERE client.nif = factura.nif_client AND EXTRACT(DAY FROM(NOW()-factura.data)) >= 1825 AND NOT (client.nif = ''00000000Z'');
	UPDATE factura SET nif_client = ''00000000Z'' WHERE EXTRACT(DAY FROM(NOW()-factura.data)) >= 1825 AND NOT (factura.nif_client = ''00000000Z'');
	RETURN 1;
END;
'LANGUAGE 'plpgsql';





-- Modificació 4: Retornar unitat llogada
-- ======================================

-- Que fa?
--	Posa una unitat llogada un altre cop a disponible. Si el client s'ha passat de dies, li crea una factura amb l'extra.
--     	Això es podria haver fet als triggers quan es posava una unitat de lloguer un altre cop a disponible, pero s'ha desestimat
--	l'opció perque sempre pots tenir unitats de lloguer no disponibles per qualsevol motiu. Millor fer una funció per això.
-- 1) Comprovem que la unitat estava llogada.(cal comprovar si volem tornar una unitat d'una factura que només sigui confirmada)
-- 2) Amb la seva factura comprovem si s'ha passat dels dies.
-- 3) Si s'ha passat, li creem una factura nova sense línies amb el nou deute i retornem la unitat a l'stock.
-- 4) Si no s'ha passat només retornem la factura

-- Retorna:
-- 0: si el client no ha de pagar res
-- -1: si la unitat no estava llogada
-- id_factura: id de la factura amb la multa a pagar.

CREATE OR REPLACE FUNCTION func_retornar_unitat(INTEGER) RETURNS INTEGER AS'
DECLARE
	tmpunitat RECORD;
	tmpfactura RECORD;
	tmpproducte RECORD;
	id_factura INTEGER;
	dies	INTEGER;
BEGIN
	SELECT * FROM unitat INTO tmpunitat WHERE unitat.id_unitat = $1;
	SELECT * FROM factura INTO tmpfactura WHERE factura.id_factura = tmpunitat.fact_llog;
	SELECT * FROM producte INTO tmpproducte WHERE producte.id_prod = tmpunitat.id_producte;

	IF tmpunitat.llog_vend = ''LL'' AND tmpunitat.disponible = false AND tmpfactura.confirm THEN
		
		dies:= EXTRACT(DAY FROM(NOW()-factura.data))-linia_factura.dies_lloguer WHERE factura.id_factura = tmpunitat.fact_llog AND linia_factura.id_unit = $1 AND linia_factura.num_factura = factura.id_factura;		

		IF dies > 0 THEN
			RAISE NOTICE ''El client sha passat de dies, haurà de pagar'';
			
			INSERT INTO factura(nif_client,import,import_final) VALUES (tmpfactura.nif_client,tmpproducte.p_llog_dia*dies,tmpproducte.p_llog_dia*dies*1.16);
			SELECT currval(''factura_id_factura_seq'') INTO id_factura;
			UPDATE unitat SET disponible = true WHERE unitat.id_unitat = $1;
			UPDATE unitat SET fact_llog = NULL WHERE unitat.id_unitat = $1;
			RETURN id_factura;
		ELSE
			RAISE NOTICE ''El client no ha excedit els dies de lloguer, no ha de pagar res'';
			UPDATE unitat SET disponible = true WHERE unitat.id_unitat = $1;
			UPDATE unitat SET fact_llog = NULL WHERE unitat.id_unitat = $1;
			RETURN 0;
		END IF;
	
	ELSE
		RAISE NOTICE ''Aquesta unitat no estava llogada, no es pot retornar'';
		RETURN -1;
	END IF;

END;
'LANGUAGE 'plpgsql';



-- Modificació 5: Nova Factura sáb 10 dic 2005 21:16:15 CET 
-- ========================================================

-- Que fa?
-- Crea una nova factura i retorna l'integer del seu id.

CREATE OR REPLACE FUNCTION func_nova_factura(TEXT) RETURNS INTEGER AS'
DECLARE 
		id INTEGER;
	BEGIN
		INSERT INTO factura(nif_client) VALUES ($1);
		SELECT currval(''factura_id_factura_seq'') INTO id;
		RETURN id;
	END;	
'LANGUAGE 'plpgsql';
