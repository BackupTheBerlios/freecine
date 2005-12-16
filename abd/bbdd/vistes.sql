--*****************************************--
--*******AMPLIACIÓ DE BASES DE DADES*******--
--**************vistes.sql*****************--
--*****************************************--

--Abel Parera             DNI:39358422-V--
--Victor Otero		  DNI:46967753-M--	

--*****************************************--

/*==========================
  =====VISTES DE CLIENT=====
  ==========================*/



-- Vista 1.1 (!) Revisar
-- Vista de les dades del client 

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


-- Vista 1.2 OK
-- Vista de producctes disponibles

CREATE OR REPLACE VIEW vista_productes_disponibles_client AS
	SELECT DISTINCT id_prod, descr AS "Descripció", model, marca, activitat, p_venda AS "Preu", p_venda*1.16 AS "Preu+IVA"
	FROM 	producte,unitat
	WHERE 	unitat.disponible=TRUE	
	AND 	producte.id_prod=unitat.id_producte;
	


-- Vista 1.3 (?)
-- Vista dels productes llogats actualment per un client

CREATE OR REPLACE VIEW vista_productes_llogats_client AS 
SELECT client.nif, producte.descr AS "Descripció", client.login, producte.marca AS "Marca", producte.model AS "Model", unitat.mida_talla AS "Talla", unitat.llog_vend AS "Estat", CURRENT_DATE AS "Data Avui", factura.data AS "Data llogat", linia_factura.dies_lloguer AS "Dies llogat", factura.data+linia_factura.dies_lloguer  AS "Data Retorn"
FROM client, producte,unitat,linia_factura,factura
WHERE producte.id_prod = unitat.id_producte
AND unitat.llog_vend = 'LL'
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



-- Vista 1.4
-- Vista factures del client

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



-- Vista 2.1
-- Vista dels client que triguen més dies en retornar els productes que els especificats en el lloguer

DROP VIEW  vista_clients_mesdies_lloguer;
CREATE OR REPLACE VIEW vista_clients_mesdies_lloguer AS    
	SELECT DISTINCT client.nif, client.nom, client.cognom1, client.telf_contacte, CURRENT_DATE AS "Data Actual", factura.data AS "Data Lloguer", 				 linia_factura.dies_lloguer as "Dies llogat", unitat.llog_vend AS "LL/V"
	FROM client,factura,linia_factura,unitat
	WHERE (CURRENT_DATE-factura.data)>linia_factura.dies_lloguer
	AND unitat.llog_vend='LL';




-- ABEL: Modificar perque també retorni productes dels que mai hem tingut cap unitat disponible. (!)
-- Vista 2.2
-- Vista que mostri els productes que s'hagin quedat sense unitats per vendre.

CREATE OR REPLACE VIEW vista_unitats_esgotades AS
SELECT unitat.id_producte, producte.descr, producte.model, producte.marca, unitat.mida_talla
FROM linia_factura,factura,unitat
WHERE producte.id_prod = unitat.id_producte
AND unitat.disponible = false
AND unitat.llog_vend = 'V'
GROUP by unitat.id_producte, producte.descr, producte.marca, producte.model, unitat.mida_talla
ORDER by descr ASC;



-- Vista 2.3
-- Vista de l'import acumulat de les vendes de productes i total d'unitats venudes per una data o un rang de dates

CREATE OR REPLACE VIEW vista_import_acumulat AS
SELECT  SUM(factura.import_final) AS "import", factura.data
FROM factura, linia_factura, unitat, producte
WHERE linia_factura.num_factura=factura.id_factura
GROUP BY factura.data;

CREATE OR REPLACE FUNCTION funcio_import_acumulat(DATE,DATE) RETURNS REAL AS '
	DECLARE
		import REAL;
		data_i ALIAS FOR $1;
		data_f ALIAS FOR $2;
	BEGIN
		import=0;
		SELECT SUM(vista_import_acumulat.import) INTO import
		FROM vista_import_acumulat AS dades 
		WHERE dades.data BETWEEN data_i AND data_f;
		RETURN import;
	END;'
	LANGUAGE 'plpgsql'; 
