CREATE TABLE genere (
id INT NOT NULL,
descripcio VARCHAR (20) NOT NULL,
CONSTRAINT unic_descripcio_genere UNIQUE (descripcio),
CONSTRAINT id_genere_pkey PRIMARY KEY(id));

CREATE TABLE nacionalitat (
id INT NOT NULL,
pais VARCHAR (20) NOT NULL,
CONSTRAINT unic_pais_nac UNIQUE (pais),
CONSTRAINT id_nacionalitat_pkey PRIMARY KEY(id));

--DROP TABLE pelicula;
CREATE TABLE pelicula (
id INT NOT NULL,
titol VARCHAR(30) NOT NULL,
titol_original VARCHAR(30),
anny INT NOT NULL,
id_nacionalitat INT,
id_genere INT NOT NULL,
durada INTERVAL NOT NULL,
edat_recomanada INT NOT NULL,
tipus_color VARCHAR(20),
tipus_so VARCHAR(20),
director VARCHAR(30),
guionista VARCHAR(30),
productor VARCHAR(20),
actors VARCHAR(50),
sinopsis VARCHAR(60),
url_web VARCHAR(20),
url_imatge VARCHAR(256),
FOREIGN KEY (id_nacionalitat) REFERENCES nacionalitat,
FOREIGN KEY (id_genere) REFERENCES genere,
CONSTRAINT unic_titol_pel UNIQUE (titol),
CONSTRAINT id_peli_pkey PRIMARY KEY(id));

--DROP TABLE sala;
CREATE TABLE sala (
id INT,
nom VARCHAR(20) NOT NULL,
num_butaques INT NOT NULL,
num_columnes INT NOT NULL,
num_files INT NOT NULL,
descripcio VARCHAR(40),
CHECK(num_butaques>0),
CHECK(num_columnes>0),
CHECK(num_files>0),
CONSTRAINT unic_nom_sal UNIQUE (nom),
CONSTRAINT id_sala_pkey PRIMARY KEY(id));

--DROP TABLE butaca;
CREATE TABLE butaca (
id INT,
num_fila INT NOT NULL,
num_columna INT NOT NULL,
operativa BOOLEAN DEFAULT TRUE NOT NULL,
id_sala INT NOT NULL,
FOREIGN KEY (id_sala) REFERENCES sala,
CONSTRAINT unica UNIQUE (num_fila, num_columna, id_sala),
CONSTRAINT id_but_pkey PRIMARY KEY(id));

--DROP TABLE sessio;
CREATE TABLE sessio (
id INT,
data_hora_inici TIMESTAMP WITHOUT TIME ZONE NOT NULL,
data_hora_fi TIMESTAMP WITHOUT TIME ZONE NOT NULL,
preu MONEY NOT NULL,
id_sala INT NOT NULL,
id_pelicula INT NOT NULL,
FOREIGN KEY(id_sala) REFERENCES sala,
FOREIGN KEY(id_pelicula) REFERENCES pelicula,
CHECK(preu>='0.0'),
CONSTRAINT id_sessio_pkey PRIMARY KEY(id));


CREATE TABLE compra_reserva (
id INT,
pagada BOOLEAN NOT NULL,
codi_entrega VARCHAR(20) NOT NULL,
data_hora TIMESTAMP WITHOUT TIME ZONE NOT NULL,
id_sessio INT,
id_butaca INT,
FOREIGN KEY(id_sessio) REFERENCES sessio,
FOREIGN KEY(id_butaca) REFERENCES butaca,
CONSTRAINT id_comp_res_pkey PRIMARY KEY(id));


--DROP TABLE treballador;
CREATE TABLE treballador (
id INT,
login VARCHAR(10) NOT NULL,
password VARCHAR(10) NOT NULL,
tipus INT DEFAULT 0 NOT NULL,
CHECK(tipus=0 or tipus=1 or tipus=2),
CONSTRAINT id_treb_pkey PRIMARY KEY(id));
