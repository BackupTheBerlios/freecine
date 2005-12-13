UPDATE butaca
SET operativa = FALSE
WHERE id_sala = 0
AND num_columna = 9
AND num_fila>20;



DELETE FROM pelicula
WHERE id=3;

select * from sessio;
SELECT * 
FROM sessio
WHERE sessio.id_pelicula = 1
AND sessio.data_hora_inici <= NOW();