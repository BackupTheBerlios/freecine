--*****************************************--
--*******AMPLIACIÓ DE BASES DE DADES*******--
--***************segur.sql*****************--
--*****************************************--

--Abel Parera             DNI:39358422-V--
--Victor Otero		  DNI:46967753-M--	

--*****************************************--

--PRIVILEGIS DE L'ADMINISTRADOR
GRANT ALL ON  DATABASE abd TO victor;
GRANT ALL ON TABLE client TO victor;
GRANT ALL ON TABLE factura TO victor;
GRANT ALL ON TABLE linia_factura TO victor;
GRANT ALL ON TABLE producte TO victor;
GRANT ALL ON TABLE unitat TO victor;

--PRIVILEGIS DELS  DEPENDENTS

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE client TO victordep;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE factura TO victordep;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE unitat TO victordep;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE producte TO victordep;
GRANT SELECT ON TABLE vista_clients_mesdies_lloguer TO victordep;
GRANT SELECT ON TABLE vista_unitats_esgotades TO victordep;
GRANT SELECT ON TABLE vista_import_acumulat TO victordep;

--PRIVILEGIS DELS CLIENTS

GRANT SELECT ON TABLE vista_dades_client TO victorcli;
GRANT SELECT ON TABLE vista_productes_disponibles_client TO victorcli;
GRANT SELECT ON TABLE vista_productes_llogats_client TO victorcli;
GRANT SELECT ON TABLE vista_factures_client TO victorcli;


