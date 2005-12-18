
/*==========================
  =====VISTES DE CLIENT=====
  ==========================*/

--Vista de les dades del client 

CREATE OR REPLACE VIEW vista_dades_client AS
	SELECT 	nif, login, nom, cognom1, cognom2, ciutat, carrer, num, pis, telf_contacte,activitat_favorita
	FROM 	client;
	
CREATE OR REPLACE FUNCTION consulta_dades_client() RETURNS SETOF vista_dades_client AS'
DECLARE
	fila vista_dades_client%ROWTYPE;
	BEGIN
		FOR fila IN SELECT * FROM vista_dades_client WHERE login=current_user
		LOOP
			 RETURN NEXT fila;
		END LOOP;
		RETURN;
	END;
	'LANGUAGE 'plpgsql';

--Vista de productes disponibles

CREATE OR REPLACE VIEW vista_productes_disponibles_client AS
SELECT DISTINCT id_prod, descr AS "Descripció", model, marca, activitat, p_venda AS "Preu", p_venda*factura.iva AS "Preu+IVA"
FROM 	producte,unitat, factura
WHERE 	unitat.disponible=TRUE	
AND 	producte.id_prod=unitat.id_producte;

-- Vista dels productes llogats actualment per un client
-- Necesito la columna login para la función

CREATE OR REPLACE VIEW vista_productes_llogats_client AS 
SELECT unitat.id_unitat, producte.descr AS "Descripció", producte.marca AS "Marca", producte.model AS "Model", client.nif AS "Client", client.login as "login", factura.data AS "Data llogat", linia_factura.dies_lloguer AS "Dies llogat", factura.data+linia_factura.dies_lloguer  AS "Data Retorn"
FROM client, producte,unitat,linia_factura,factura
WHERE producte.id_prod = unitat.id_producte
AND unitat.llog_vend = 'LL'
AND unitat.disponible = FALSE
AND unitat.id_unitat = linia_factura.id_unit
AND linia_factura.num_factura = factura.id_factura
AND factura.nif_client=client.nif;

CREATE OR REPLACE FUNCTION consulta_prod_llogats() RETURNS SETOF vista_productes_llogats_client AS'
DECLARE
	fila vista_productes_llogats_client%ROWTYPE;
	BEGIN
		FOR fila IN SELECT * FROM vista_productes_llogats_client WHERE login=current_user
		LOOP
			 RETURN NEXT fila;
		END LOOP;
		RETURN;
	END;
	'LANGUAGE 'plpgsql';

--Vista factures del client*1.1

CREATE OR REPLACE VIEW vista_factures_client AS    
	SELECT DISTINCT nif,login, nom, cognom1, cognom2, id_factura, data, import, import_final AS "import+IVA" 
	FROM 	factura RIGHT JOIN client ON factura.nif_client=client.nif;
		
CREATE OR REPLACE FUNCTION consulta_factures_client() RETURNS SETOF vista_factures_client AS'
DECLARE
	fila vista_factures_client%ROWTYPE;
	BEGIN
		FOR fila IN SELECT *
				FROM vista_factures_client 
				WHERE login=current_user
				
		LOOP
			 RETURN NEXT fila;
		END LOOP;
		RETURN;
	END;'
	LANGUAGE 'plpgsql';


/*==========================
  ===VISTES DEL DEPENDENT===
  ==========================*/

-- Malament.
-- Vista dels clients que triguen més dies en retornar els productes que els especificats en el lloguer

CREATE OR REPLACE VIEW vista_clients_mesdies_lloguer AS    
	SELECT DISTINCT client.nif, client.nom, client.cognom1, client.telf_contacte, CURRENT_DATE AS "Data Actual", factura.data AS "Data Lloguer", 		 linia_factura.dies_lloguer as "Dies llogat", unitat.llog_vend AS "LL/V"
	FROM client,factura,linia_factura,unitat
	WHERE (CURRENT_DATE-factura.data)>linia_factura.dies_lloguer
	AND unitat.llog_vend='LL'
	AND unitat.disponible
	AND linia_factura.dies_lloguer<>0;

-- ABEL: Modificar perque també retorni productes dels que mai hem tingut cap unitat disponible. (!) (JA FUNCIONA!!!!!!!)
-- Vista 2.2
-- Vista que mostri els productes que s'hagin quedat sense unitats per vendre. (JA FUNCIONA!!!!!!!)

CREATE OR REPLACE VIEW vista_unitats_esgotades AS
SELECT  unitat.id_producte, producte.descr, producte.model, producte.marca, unitat.mida_talla
FROM linia_factura,factura,unitat,producte
WHERE producte.id_prod = unitat.id_producte
AND unitat.disponible = false
AND unitat.llog_vend = 'V'
GROUP by unitat.id_producte, producte.descr, producte.marca, producte.model, unitat.mida_talla
ORDER by descr ASC;

--Vista de l'import acumulat de les vendes d/abel2/e productes i total d'unitats venudes per una data o un rang de dates

CREATE OR REPLACE VIEW vista_import_acumulat AS
SELECT (select SUM(linia_factura.import)*factura.iva from linia_factura where linia_factura.dies_lloguer=0) AS "import", 
	(select COUNT(linia_factura.id) from linia_factura where linia_factura.dies_lloguer=0)  AS "unitats",
	factura.data AS "Data1", factura.data AS "Data2" 
FROM factura, linia_factura, unitat, producte
WHERE linia_factura.num_factura=factura.id_factura
AND linia_factura.id_unit=unitat.id_unitat
AND unitat.llog_vend='V'
GROUP BY factura.data, factura.iva;

--Funió de vista_import_acumulat per una data en concret
CREATE OR REPLACE FUNCTION funcio_import_data (DATE) RETURNS SETOF vista_import_acumulat AS '
DECLARE
		fila vista_import_acumulat%ROWTYPE;
		data_i ALIAS FOR $1;
	BEGIN
		FOR fila IN SELECT  vista_import_acumulat.import, vista_import_acumulat.unitats, data_i
		FROM vista_import_acumulat  
		WHERE factura.data=data_i 
		LIMIT 1

		LOOP
			RETURN NEXT fila;
		
		END LOOP;
		RETURN;
	END;'
	LANGUAGE 'plpgsql'; 


--Funió de vista_import_acumulat per un rang de dates
CREATE OR REPLACE FUNCTION funcio_import_arang (DATE,DATE) RETURNS SETOF vista_import_acumulat AS '
DECLARE
		fila vista_import_acumulat%ROWTYPE;
		data_i ALIAS FOR $1;
		data_f ALIAS FOR $2;
		
	BEGIN
		
		FOR fila IN SELECT vista_import_acumulat.import, vista_import_acumulat.unitats, data_i,data_f
		FROM vista_import_acumulat  
		WHERE factura.data BETWEEN data_i AND data_f
		LIMIT 1
				
		LOOP
			 RETURN NEXT fila;
		END LOOP;
		RETURN;
	END;'
	LANGUAGE 'plpgsql'; 



--
-- Altres vistes
--

--Vista de unitats disponibles

CREATE OR REPLACE VIEW vista_unitats_disponibles AS
SELECT DISTINCT unitat.id_producte, descr AS "Descripció", marca, model, p_venda*factura.iva AS "Preu+IVA", producte.p_llog_dia
FROM 	producte, unitat, factura
WHERE 	unitat.disponible=TRUE	
AND 	producte.id_prod=unitat.id_producte;

