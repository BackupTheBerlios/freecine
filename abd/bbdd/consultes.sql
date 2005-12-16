--*****************************************--
--*******AMPLIACI� DE BASES DE DADES*******--
--*************consultes.sql***************--
--*****************************************--

--Abel Parera             DNI:39358422-V--
--Victor Otero		  DNI:46967753-M--	

--*****************************************--

--1.Consulta de les dades d'un determinat client.

SELECT * 
FROM 	client
WHERE 	client.nif='98222221F';

--2.Consulta de les transaccions que ha realitzat un determinat client. 

SELECT 	client.nif, client.nom AS "Nom", client.cognom1 AS "1r Cognom", factura.data AS "Data", producte.descr AS "Desc. producte" ,
	  producte.marca AS "Marca", unitat.mida_talla, producte.model AS "Model", unitat.llog_vend AS "Lloguer/Venda", linia_factura.import 
FROM 	client, producte, unitat,factura, linia_factura
WHERE 	producte.id_prod=unitat.id_producte
AND 	linia_factura.id_unit=unitat.id_unitat
AND 	linia_factura.num_factura=factura.id_factura 
AND 	client.nif=factura.nif_client
AND 	factura.nif_client= '58963688P';

--3.Consulta del preu d'un producte.

SELECT 	producte.id_prod AS "id_producte", producte.descr AS "Descripció", producte.marca AS "Marca", 
	producte.model AS "Model", producte.p_venda AS "Preu"
FROM 	producte
WHERE 	producte.id_prod=7;

--4.Consulta de tots els productes associats a una activitat determinada.

SELECT 	producte.descr AS "Descripció", producte.marca AS "Marca",producte.model AS "Model",producte.activitat AS "Activitat"
FROM 	producte
WHERE 	producte.activitat='trekking';

--5.Consulta de les unitats de que es disposa d'un determinat producte.

SELECT 	producte.descr AS "Descripció", producte.marca AS "Marca", producte.model AS "Model", unitat.mida_talla
FROM 	producte,unitat
WHERE 	producte.id_prod=unitat.id_producte
AND 	producte.descr='bota';

--6.Consulta de les unitats de que es disposa d'una activitat determinada.

SELECT 	producte.descr AS "Descripció", producte.marca AS "Marca", producte.model AS "Model", producte.activitat AS "Activitat"
FROM 	producte,unitat
WHERE 	producte.id_prod=unitat.id_producte
AND 	producte.activitat='snowboard';

--7.-Número de facturas d'un client determinat 

SELECT DISTINCT client.nif, client.nom AS "Nom", client.cognom1 AS "1r Cognom", client.cognom2 AS "2n Cognom",
	COUNT (factura.id_factura) AS "Numero de facturas"
FROM 	factura, client
WHERE 	factura.nif_client=client.nif
AND 	client.nif='58963688P'
GROUP BY client.nif,client.nom,client.cognom1, client.cognom2,factura.id_factura;
                                               
--8.-Productes que històricament ha llogat un client - CORRETGIDA! -
-- Son les l�nies amb dies de lloguer > 0 , perque un producte sempre pot passar de venda a LL.

SELECT client.nif AS "DNI", client.nom AS "Nom",client.cognom1 AS "1r Cognom",producte.descr AS "Descripci�", producte.marca AS "Marca", producte.model AS "Model", unitat.mida_talla AS "Talla"
FROM client,producte,unitat,linia_factura, factura
WHERE   producte.id_prod = unitat.id_producte
AND 	linia_factura.dies_lloguer > 0
AND 	unitat.id_unitat = linia_factura.id_unit
AND 	linia_factura.num_factura = factura.id_factura
AND 	client.nif=factura.nif_client
AND 	factura.nif_client='58963688P';

--9.-Consulta dels productes que ara mateix te llogat un client. CORRETGIDA

SELECT DISTINCT producte.descr AS "Descripci�", producte.marca AS "Marca", producte.model AS "Model", unitat.mida_talla AS "Talla", CURRENT_DATE as "Data Avui", factura.data AS "Data llogat", linia_factura.dies_lloguer as "Dies llogat"
FROM producte,unitat,linia_factura,factura
WHERE producte.id_prod = unitat.id_producte
AND unitat.fact_llog = factura.id_factura 
AND linia_factura.num_factura = factura.id_factura
AND factura.nif_client='58963688P';

--10.Consulta de totes les unitats que estan llogades actualment.


SELECT client.nif AS "DNI", client.nom AS "Nom",client.cognom1 AS "1r Cognom",producte.descr AS "Descripción", producte.marca AS "Marca", producte.model AS "Model", CURRENT_DATE AS "Data Actual", factura.data AS "Dia Llogat"
FROM client,producte,unitat,linia_factura,factura
WHERE   producte.id_prod = unitat.id_producte
AND 	unitat.llog_vend = 'LL'
AND 	unitat.id_unitat = linia_factura.id_unit
AND 	linia_factura.num_factura = factura.id_factura
AND 	client.nif=factura.nif_client
AND 	unitat.disponible = false
AND	 unitat.fact_llog IS NOT NULL;

--11.Consulta de les unitats que es van adquirir la temporada passada.

SELECT unitat.id_unitat AS "Codi Unitat", producte.descr AS "Descripción", producte.marca AS "Marca", producte.model AS "Model", unitat.mida_talla AS "Talla", factura.data AS "Data Venda"
FROM producte,unitat,linia_factura,factura
WHERE producte.id_prod = unitat.id_producte
AND unitat.llog_vend = 'V'
AND unitat.id_unitat = linia_factura.id_unit
AND linia_factura.num_factura = factura.id_factura
AND EXTRACT(YEAR FROM(factura.data)) = 2005;

--12.Consulta de la suma de tots els imports totals de totes les factures. Corretgida: Nom�s factures confirmades. 

SELECT SUM(factura.import_final) AS "Vendes totals"
FROM factura
WHERE factura.confirm = true;

--13.Consulta de la suma de ingressos obtinguts mitjançant el lloguer.

SELECT SUM(linia_factura.import) AS "Ingressos Lloguers"
FROM linia_factura
WHERE linia_factura.dies_lloguer > 0;

--14.Consulta de la factura amb l'import total màxim.

SELECT factura.id_factura AS "Num. factura", import_final AS "import+IVA"
FROM factura
ORDER BY factura.import DESC
LIMIT 1;

--15.Consulta del producte més venut durant un mes determinat.

SELECT unitat.id_producte AS "Id producte", COUNT (unitat.id_producte) AS "Cops venut"
FROM linia_factura,factura,unitat
WHERE producte.id_prod = unitat.id_producte
AND unitat.id_unitat = linia_factura.id_unit
AND linia_factura.num_factura = factura.id_factura
AND EXTRACT(MONTH FROM(factura.data)) = 11
GROUP by "Id producte"
ORDER by "Cops venut" DESC
LIMIT 1;




