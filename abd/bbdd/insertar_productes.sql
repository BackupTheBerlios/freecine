--*****************************************--
--*******AMPLIACIÓ DE BASES DE DADES*******--
--*********insertar_productes.sql**********--
--*****************************************--

--Abel Parera             DNI:39358422-V--
--Victor Otero		  DNI:46967753-M--	

--*****************************************--


-- ski --
INSERT INTO producte (descr,model,marca,mat_prim,activitat,distribuidors,p_venda,p_llog_dia) 
        VALUES ('bota','B5W','atomic','polyester','esqui','J.Solanich S.L',100,15);
INSERT INTO producte (descr,model,marca,mat_prim,activitat,distribuidors,p_venda,p_llog_dia) 
        VALUES ('bota','B2V','atomic','plexiglas','esqui','Snow Complemets',80,15);
INSERT INTO producte (descr,model,marca,mat_prim,activitat,distribuidors,p_venda,p_llog_dia) 
        VALUES ('esqui','Superestar','Rossignol','fibra carbó','esqui','J.Carbonell S.L',499,50);

--snowboard --
INSERT INTO producte (descr,model,marca,mat_prim,activitat,distribuidors,p_venda,p_llog_dia)
        VALUES ('taula','ZZ·3','Heat','alumini','snowboard','J.Solanich S.L',250,40);
INSERT INTO producte (descr,model,marca,mat_prim,activitat,distribuidors,p_venda,p_llog_dia) 
        VALUES ('fixació','FX10','Heat','alumini','snowboard','J.Solanich S.L',150,0);

-- accesoris --
INSERT INTO producte (descr,model,marca,mat_prim,activitat,distribuidors,p_venda,p_llog_dia) 
        VALUES ('guant','Goretex','Termua','polyester','accesori','J.Mas S.A',30,0);
INSERT INTO producte (descr,model,marca,mat_prim,activitat,distribuidors,p_venda,p_llog_dia) 
        VALUES ('ulleres','X78','adidas','fibra carbó','accesori','Tandem S.A',90,0);

-- trekking --
INSERT INTO producte (descr,model,marca,mat_prim,activitat,distribuidors,p_venda,p_llog_dia)
        VALUES ('bota','TR100','salomon','polyester','trekking','Tandem S.A',100,0);
INSERT INTO producte (descr,model,marca,mat_prim,activitat,distribuidors,p_venda,p_llog_dia)
        VALUES ('mitjo','TR1','salomon','cotó','trekking','Tandem S.A',90,0);
