
--seleccio de pelicules
SELECT pelicula.id, pelicula.titol, pelicula.titol_original, 
pelicula.anny, nacionalitat.pais as nacionalitat , genere.descripcio as genere, pelicula.durada, 
pelicula.edat_recomanada, pelicula.tipus_color, pelicula.tipus_so , pelicula.director, 
pelicula.guionista , pelicula.productor, pelicula.actors, pelicula.sinopsis, pelicula.url_web, pelicula.url_imatge
FROM pelicula, genere, nacionalitat
WHERE pelicula.id = 0
AND pelicula.id_genere = genere.id
AND pelicula.id_nacionalitat = nacionalitat.id;

--seleccio de sales
SELECT sala.id, sala.nom, sala.num_butaques, sala.num_columnes, sala.num_files, sala.descripcio
FROM sala
WHERE sala.id = 1;

--seleccio de sessions
SELECT sessio.id, sessio.data_hora_inici, sessio.data_hora_fi, sessio.preu, sala.nom AS sala, pelicula.titol AS pelicula
FROM sessio, sala, pelicula
WHERE sessio.id = 0
AND sessio.id_sala = sala.id
AND sessio.id_pelicula = pelicula.id;

--seleccio de compra_reserva
SELECT compra_reserva.id , compra_reserva.pagada, compra_reserva.codi_entrega , 
compra_reserva.data_hora, sessio.data_hora_inici AS data_hora, butaca.num_fila AS fila, butaca.num_columna AS columna, sala.nom AS sala
FROM compra_reserva, sessio, butaca, sala
WHERE compra_reserva.codi_entrega = '32423243'
AND compra_reserva.id_sessio = sessio.id
AND compra_reserva.id_butaca = butaca.id
AND sessio.id_sala = sala.id;


/*INSERT INTO sala( nom, num_columnes, num_files, descripcio) VALUES ('1', 10, 25, 'Tamany: 10x25');
INSERT INTO sala( nom, num_columnes, num_files, descripcio) VALUES ('2', 15, 30, 'Tamany: 15x30');
INSERT INTO sala( nom, num_columnes, num_files, descripcio) VALUES ('3', 10, 25, 'Tamany: 10x25');
INSERT INTO sala( nom, num_columnes, num_files, descripcio) VALUES ('4', 20, 50, 'Tamany: 20x50');
INSERT INTO genere (descripcio) VALUES ('Accio');
INSERT INTO nacionalitat (pais) VALUES ('ESPANYA');
INSERT INTO pelicula (titol, anny, id_genere, durada, edat_recomanada, id_nacionalitat) VALUES ('HEEE', 1983,0,'00:00:32',18,0);
INSERT INTO sessio (data_hora_inici, preu, id_sala, id_pelicula) VALUES ('05/12/03 12:23', '5.45',0,0);
*/

--seleccio de totes les butaques operatives comprades d'una sessio concreta

SELECT butaca.id, butaca.num_fila, butaca.num_columna, TRUE AS comprada_reservada
FROM butaca, sessio, sala, compra_reserva
WHERE sessio.id = 0
AND sessio.id_sala = sala.id
AND butaca.id_sala = sala.id
AND butaca.operativa = TRUE
AND compra_reserva.id_butaca = butaca.id
UNION
SELECT butaca.id, butaca.num_fila, butaca.num_columna, FALSE AS comprada_reservada
FROM butaca, sessio, sala
WHERE sessio.id = 0
AND sessio.id_sala = sala.id
AND butaca.id_sala = sala.id
AND butaca.operativa = TRUE
;


