--Vista de unitats disponibles

CREATE OR REPLACE VIEW vista_unitats_disponibles AS
SELECT DISTINCT unitat.id_producte, descr AS "Descripció", marca, model, p_venda*factura.iva AS "Preu+IVA", producte.p_llog_dia
FROM 	producte, unitat, factura
WHERE 	unitat.disponible=TRUE	
AND 	producte.id_prod=unitat.id_producte;