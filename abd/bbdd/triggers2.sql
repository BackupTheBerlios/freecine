-- ************************************************************************
-- *									  *
-- *				TRIGGERS				  *
-- *			dom 20 nov 2005 02:47:41 CET	 		  *
-- ************************************************************************


-- Trigger 1: Insertar Linia Factura
-- =================================
-- a) Comprova si existeix la factura.
-- b) Comprova si la factura ja ha sigut confirmada (pagada).
-- 0) Comprova si existeix la unitat
-- 1) Comprova si la unitat que volem comprar està disponible
-- 2) Comprova si volem comprar una unitat que es només de lloguer
-- 3) Comprova si volem llogar una unitat que es només de venda
-- 4) Fa un contador per posar a id la linia de factura correlativa dins d'una factura
-- 5) Calcula l'import de la línia depenent si la unitat inserita es del tipus LL lloguer o compra
-- 6) Actualitza l'import de la factura de la línia
-- 7) Actualitza l'import+iva de la factura de la línia
-- 8) Posa l'unitat llogada o comprada a NO DISPONIBLE
-- 9) Posa l'unitat llogada la referència del número de factura que s'ha llogat

DROP FUNCTION func_insertar_linia() CASCADE;
CREATE OR REPLACE FUNCTION func_insertar_linia() RETURNS OPAQUE AS'
DECLARE
contador integer;
TMPunitat RECORD;
tmpfac RECORD;
BEGIN
	
	SELECT * FROM factura INTO tmpfac WHERE factura.id_factura = NEW.num_factura;
	IF NOT FOUND THEN
		RAISE EXCEPTION ''ERROR: Aquesta factura no existeix.'';
		RETURN NULL;
	END IF;
	
	IF tmpfac.confirm = true THEN
		RAISE EXCEPTION ''ERROR: Aquesta factura ja extà confirmada. No es poden afegir més línies.'';
		RETURN NULL;
	END IF;
	
	SELECT * FROM unitat INTO TMPunitat WHERE unitat.id_unitat = NEW.id_unit;

	IF NOT FOUND THEN
		RAISE EXCEPTION ''ERROR: Aquesta unitat no existeix.'';
		RETURN NULL;
	END IF;

	IF TMPunitat.disponible = false THEN
		RAISE NOTICE ''ERROR: Aquesta unitat no es pot comprar ni llogar'';
   		RETURN NULL;
	END IF;

	IF TMPunitat.llog_vend=''LL'' AND NEW.dies_lloguer = 0 THEN
		RAISE NOTICE ''ERROR: Aquesta unitat no es pot comprar'';
   		RETURN NULL;
	ELSIF TMPunitat.llog_vend=''V'' AND NEW.dies_lloguer > 0 THEN
		RAISE NOTICE ''ERROR: Aquesta unitat no es pot llogar'';
   		RETURN NULL;
	END IF;

	SELECT INTO contador COUNT(*) FROM linia_factura WHERE linia_factura.num_factura=NEW.num_factura;
	NEW.id := contador +1;
	
	
	IF TMPunitat.llog_vend = ''LL'' THEN
		NEW.import:= (producte.p_llog_dia * NEW.dies_lloguer) WHERE producte.id_prod = TMPunitat.id_producte;
		UPDATE unitat SET fact_llog = NEW.num_factura WHERE id_unitat = NEW.id_unit;
	ELSE
		NEW.import:= producte.p_venda WHERE producte.id_prod = TMPunitat.id_producte;

	END IF;

	UPDATE factura SET import = import+NEW.import WHERE id_factura = NEW.num_factura;
	UPDATE factura SET import_final = import*iva WHERE id_factura = NEW.num_factura;

	UPDATE unitat SET disponible = false WHERE id_unitat = NEW.id_unit;
	
	RETURN NEW;
END;
'LANGUAGE 'plpgsql';

CREATE TRIGGER insertar_linia
BEFORE INSERT
ON linia_factura FOR EACH ROW EXECUTE PROCEDURE
func_insertar_linia();




-- Trigger 1 bis: Eliminar linia
-- =============================

-- Què fa?
-- 1) Siempre se puede eliminar una linia de una factura abierta
-- 2) Comprueba que la factura está de verdad abierta (no confirmada)
-- 3) Pone su unidad a disponible
-- 4) Borra la referencia de factura para la unidad alguilada. -- sin probar --
-- 4) Actualiza el importe de la factura a la que pertenecia

DROP FUNCTION func_eliminar_linia() CASCADE;
CREATE OR REPLACE FUNCTION func_eliminar_linia() RETURNS OPAQUE AS'
DECLARE
	tmpconfirmada BOOL;
BEGIN

SELECT INTO tmpconfirmada confirm FROM factura WHERE factura.id_factura = OLD.num_factura; 

IF tmpconfirmada =false THEN	
   	UPDATE unitat SET disponible = true WHERE unitat.id_unitat = OLD.id_unit;
	UPDATE unitat SET fact_llog = NULL WHERE unitat.id_unitat = OLD.id_unit;
	UPDATE factura SET import = import-OLD.import WHERE id_factura = OLD.num_factura;
	UPDATE factura SET import_final = import*iva WHERE id_factura = OLD.num_factura;
   	RETURN OLD;
ELSE
   	RAISE NOTICE ''Aquesta linia pertany a una factura ja confirmada, no es pot eliminar.'';
   	RETURN NULL;
END IF;



END;
'LANGUAGE 'plpgsql';

CREATE TRIGGER eliminar_linia
BEFORE DELETE
ON linia_factura FOR EACH ROW EXECUTE PROCEDURE
func_eliminar_linia();



-- Trigger 2: Insertar Factura
-- ===========================

-- Què fa?
-- 1) Comprova que el client existeix quan inserim una nova factura. No cal fer-la... ja li buscarem excusa

DROP FUNCTION func_insertar_factura() CASCADE;
CREATE OR REPLACE FUNCTION func_insertar_factura() RETURNS OPAQUE AS'
DECLARE
	tmpclient RECORD;
BEGIN
	SELECT * FROM client INTO tmpclient WHERE client.nif = NEW.nif_client;
	IF NOT FOUND THEN
		RAISE EXCEPTION ''ERROR: Aquest client no existeix.'';
		RETURN NULL;
	END IF;
	RETURN NEW;
END;
'LANGUAGE 'plpgsql';

CREATE TRIGGER insertar_factura
BEFORE INSERT
ON factura FOR EACH ROW EXECUTE PROCEDURE
func_insertar_factura();

-- Trigger 3: Confirmar Factura
-- =============================

-- Què fa?
-- 1) Calcula la activitat favorita d'un client quan confirmem una factura.
-- NOTA: Para actualizar todas o alguna factura: UPDATE factura SET confirm = true WHERE id_factura = ;


DROP FUNCTION func_confirmar_factura() CASCADE;
CREATE OR REPLACE FUNCTION func_confirmar_factura() RETURNS OPAQUE AS'
DECLARE
BEGIN
	IF NEW.confirm = true THEN
		
		UPDATE client SET activitat_favorita = ( 
		SELECT producte.activitat FROM producte, linia_factura, factura, unitat WHERE linia_factura.num_factura = factura.id_factura AND factura.nif_client = NEW.nif_client AND producte.id_prod = unitat.id_producte AND unitat.id_unitat = linia_factura.id_unit
		GROUP BY activitat
		ORDER BY COUNT(producte.activitat)
		DESC LIMIT 1) WHERE nif = NEW.nif_client;
   		RETURN NULL;
	
	END IF;
	RETURN NEW;
END;
'LANGUAGE 'plpgsql';

CREATE TRIGGER confirmar_factura
AFTER UPDATE
ON factura FOR EACH ROW EXECUTE PROCEDURE
func_confirmar_factura();


-- Trigger 4: Insertar Unitat
-- ==========================

-- Què fa?

-- 1) Comprova si existeix el producte.
-- 2) Comprova que si la unitat es de l'any passat no es pugui posar a la venda
-- 3) Comprova que si la unitat es d'aquest any no es pugi posar a lloguer
-- 4) Comprova que si ja n'hi ha 5 unitats del mateix model i talla no te la deixi ficar. No es compatibilizaran les unitats ja venudes!!

DROP FUNCTION func_insertar_unitat() CASCADE;
CREATE OR REPLACE FUNCTION func_insertar_unitat() RETURNS OPAQUE AS'
DECLARE
	tmpproducte RECORD;
	temporada INTEGER;
	cont INTEGER;
BEGIN
	SELECT * FROM producte INTO tmpproducte WHERE producte.id_prod = NEW.id_producte;
	IF NOT FOUND THEN
		RAISE EXCEPTION ''ERROR: El producte no existeix.'';
		RETURN NULL;
	END IF;

	temporada := EXTRACT(YEAR FROM(NOW())) - EXTRACT(YEAR FROM(NEW.data_adquisicio));
	
	IF temporada = 0 AND NEW.llog_vend = ''LL'' THEN
		RAISE EXCEPTION ''No es pot posar de lloguer una unitat de aquesta temporada.'';
		RETURN NULL;
	ELSIF temporada > 0 AND NEW.llog_vend = ''V'' THEN
		RAISE EXCEPTION ''NO es pot posar a la venda un producte de la temporada passada.'';
		RETURN NULL;
	END IF;

	SELECT INTO cont COUNT(*) FROM unitat WHERE unitat.id_producte = NEW.id_producte AND unitat.mida_talla = NEW.mida_talla AND NOT (unitat.llog_vend = ''V'' AND unitat.disponible = false);
	
	IF cont >=5 THEN
		RAISE EXCEPTION ''No es poden afegir més unitats de aquest model i talla'';
		RETURN NULL;
	END IF;
	
	RETURN NEW;
END;
'LANGUAGE 'plpgsql';

CREATE TRIGGER insertar_unitat
BEFORE INSERT
ON unitat FOR EACH ROW EXECUTE PROCEDURE
func_insertar_unitat();


-- Trigger 5: Devolver Unidad alquilada
-- ====================================

-- Què fa?
-- 1) Si el usuari retorna tard una unitat, crea una factura nova sense confirmar i amb una línia que conté la unitat i els dies de més
-- PERFORM EXTRACT(DAY FROM(NOW()-factura.data)) <= linia_factura.dies_lloguer
-- Problema: farà aquest trigger quan insertir la unitat la primera vegada o només quan la modifiqui?

/*
DROP FUNCTION func_retornar_unitat() CASCADE;
CREATE OR REPLACE FUNCTION func_retornar_unitat() RETURNS OPAQUE AS'
DECLARE
	tmpunitat RECORD;
	tmpfactura RECORD;
	dies_extra INTEGER;
BEGIN
	IF NEW.disponible = true AND NEW.llog_venda = ''LL'' THEN
	
		dies_extra := EXTRACT(DAY FROM(NOW()-factura.data))-linia_factura.dies_lloguer WHERE linia_factura
		
		
	
	END IF;
	RETURN NEW;
END;
'LANGUAGE 'plpgsql';

CREATE TRIGGER retornar_unitat
AFTER UPDATE
ON unitat FOR EACH ROW EXECUTE PROCEDURE
func_retornar_unitat();
*/
