--*****************************************--
--*******AMPLIACIÓ DE BASES DE DADES*******--
--***********crear_taules.sql**************--
--*****************************************--

--Abel Parera             DNI:39358422-V--
--Victor Otero		  DNI:46967753-M--	

--*****************************************--

DROP TABLE producte CASCADE;
DROP TABLE factura CASCADE;
DROP TABLE linia_factura CASCADE;
DROP TABLE factura CASCADE;
DROP TABLE client CASCADE;
DROP DOMAIN d_telefon CASCADE;

CREATE DOMAIN d_telefon CHAR (9);

CREATE TABLE client (
	nif CHAR (9) PRIMARY KEY,
	login VARCHAR(18) NOT NULL,
	nom VARCHAR(15) NOT NULL,
        cognom1 VARCHAR(15) NOT NULL,
        cognom2 VARCHAR(15) NOT NULL,
        ciutat VARCHAR(20) NOT NULL,
        carrer VARCHAR(30) NOT NULL,
	num VARCHAR(4) NOT NULL,			--els números d'edifici poden anar de 0 a 999 amb una lletra al darrere.
	pis VARCHAR(6) NOT NULL,          	--contindrà el pis, la porta i l'escala de cada client
	telf_contacte d_telefon,
	activitat_favorita VARCHAR(15) 
	
);

CREATE TABLE producte (
        id_prod SERIAL8 PRIMARY KEY,
	descr VARCHAR(25) NOT NULL,
	model VARCHAR(15) NOT NULL,
        marca VARCHAR(15) NOT NULL,
        mat_prim VARCHAR(50) NOT NULL,
        activitat VARCHAR(15) NOT NULL,
        distribuidors VARCHAR(20) NOT NULL,
	p_venda NUMERIC (6,2) NOT NULL,
	p_llog_dia NUMERIC (6,2) DEFAULT 0,
--	stock INT NOT NULL DEFAULT 0,
--	CONSTRAINT ck_stock CHECK (stock<=5),
	CONSTRAINT ck_llog_vend CHECK 
		(p_llog_dia>=0 AND p_venda>=0)
);

CREATE TABLE unitat (
	id_unitat SERIAL8 PRIMARY KEY,
	id_producte INT8 NOT NULL,
	data_adquisicio DATE NOT NULL DEFAULT CURRENT_DATE,
	mida_talla CHAR(5) NOT NULL,
	disponible BOOL DEFAULT TRUE,
	llog_vend CHAR(2) NOT NULL CHECK (llog_vend='V' OR llog_vend='LL'),  --valdrà 'V' si és una venda i 'LL' si és un lloguer
	fact_llog INT8 DEFAULT NULL, -- Només s'usa per portar el control de les unitats llogades -> qui i quan les ha llogat (factura)
	CONSTRAINT fk_id_prod FOREIGN KEY (id_producte) REFERENCES producte(id_prod)

);


CREATE TABLE factura (
	id_factura SERIAL8 PRIMARY KEY,
	data DATE NOT NULL DEFAULT CURRENT_DATE,
	nif_client CHAR(9) NOT NULL,
	import NUMERIC (6,2) DEFAULT 0     	   --import sense iva
		CHECK (import >=0),
	iva NUMERIC (3,2) DEFAULT 1.16		   --%iva, per defecte 16%    
		CHECK (iva >=0),
	import_final NUMERIC (6,2) DEFAULT 0       --=(import*IVA) DEFAULT 0  --import amb l'iva ja afegit
		CHECK (import_final >=0),
	confirm BOOL DEFAULT FALSE,
	CONSTRAINT fk_nif_client FOREIGN KEY (nif_client) REFERENCES client(nif)
);

CREATE TABLE linia_factura (
	id INT8,
	num_factura INT8 NOT NULL,
	id_unit INT8 NOT NULL,
	import NUMERIC (6,2) DEFAULT 0
		CHECK (import >=0),
	dies_lloguer INT NOT NULL DEFAULT 0,
	CONSTRAINT fk_num_fact FOREIGN KEY (num_factura) REFERENCES factura(id_factura),
	CONSTRAINT fk_id_unita FOREIGN KEY (id_unit) REFERENCES unitat(id_unitat),
	PRIMARY KEY(id,num_factura)
);


	
	
