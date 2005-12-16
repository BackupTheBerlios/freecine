--*****************************************--
--*******AMPLIACIÓ DE BASES DE DADES*******--
--*********insertar_unitats.sql**********--
--*****************************************--

--Abel Parera             DNI:39358422-V--
--Victor Otero		  DNI:46967753-M--	

--*****************************************--

-- ¡ATENCiÓ! Si volem afegir unitats per a llogar, hem de poar la data d'adquisició a l'any passat


--Unitats del producte 1: Bota --
INSERT INTO unitat (id_producte,mida_talla,llog_vend) 
        VALUES (1,'44','V');
INSERT INTO unitat (id_producte,mida_talla,llog_vend)
        VALUES (1,'45','V');

--Unitats del producte 2: Bota --
INSERT INTO unitat (id_producte,mida_talla,llog_vend)
        VALUES (2,'43','V');
INSERT INTO unitat (id_producte,mida_talla,llog_vend)
        VALUES (2,'44','V');
INSERT INTO unitat (id_producte,mida_talla,llog_vend)
        VALUES (2,'45','V');

--Unitats del producte 3: Ski --
INSERT INTO unitat (id_producte,mida_talla,llog_vend)
        VALUES (3,'180','V');
INSERT INTO unitat (id_producte,mida_talla,llog_vend)
        VALUES (3,'185','V');
INSERT INTO unitat (id_producte,mida_talla,llog_vend)
        VALUES (3,'190','V');
--lloguer--
INSERT INTO unitat (id_producte,data_adquisicio,mida_talla,llog_vend)
        VALUES (3,'30/10/04','180','LL');
INSERT INTO unitat (id_producte,data_adquisicio,mida_talla,llog_vend)
        VALUES (3,'30/10/04','185','LL');


--Unitats del producte 4: Taules --
INSERT INTO unitat (id_producte,mida_talla,llog_vend)
        VALUES (4,'180','V');
INSERT INTO unitat (id_producte,mida_talla,llog_vend)
        VALUES (4,'185','V');
--lloguer--
INSERT INTO unitat (id_producte,data_adquisicio,mida_talla,llog_vend)
        VALUES (4,'31/10/04','180','LL');
INSERT INTO unitat (id_producte,data_adquisicio,mida_talla,llog_vend)
        VALUES (4,'31/10/04','185','LL');
INSERT INTO unitat (id_producte,data_adquisicio,mida_talla,llog_vend)
        VALUES (4,'31/10/04','190','LL');

--Unitats del producte 5: Fixacio --

INSERT INTO unitat (id_producte,mida_talla,llog_vend)
        VALUES (5,'44','V');
INSERT INTO unitat (id_producte,mida_talla,llog_vend)
        VALUES (5,'45','V');



--Unitats del producte 6: Guant --
INSERT INTO unitat (id_producte,mida_talla,llog_vend)
        VALUES (6,'L','V');
INSERT INTO unitat (id_producte,mida_talla,llog_vend)
        VALUES (6,'XL','V');


--Unitats del producte 7: Ulleres --
INSERT INTO unitat (id_producte,mida_talla,llog_vend)
        VALUES (7,'L','V');
INSERT INTO unitat (id_producte,mida_talla,llog_vend)
        VALUES (7,'XL','V');


--Unitats del producte 8: Bota --
INSERT INTO unitat (id_producte,mida_talla,llog_vend)
        VALUES (8,'44','V');
INSERT INTO unitat (id_producte,mida_talla,llog_vend)
        VALUES (8,'43','V');

--Unitats del producte 9: Mitjo --
INSERT INTO unitat (id_producte,mida_talla,llog_vend)
        VALUES (9,'M','V');
INSERT INTO unitat (id_producte,mida_talla,llog_vend)
        VALUES (9,'L','V');



