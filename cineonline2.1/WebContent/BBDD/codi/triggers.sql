--*****************************************--

--Oscar Armadans
--Sergi Lario
--Oriol Martí
--Ivan Mateu
--Victor Otero

--*****************************************--


--*****************************************--

--TRIGGERS PER A LA COHERENCIA DELS ATRIBUTS DE SALA--

--*****************************************--

CREATE OR REPLACE FUNCTION insert_sal() RETURNS TRIGGER AS'
	
	
	DECLARE
		id_max INTEGER;
		descarta sala%ROWTYPE;
		butaques INTEGER;
	BEGIN
		SELECT * INTO descarta 
		FROM sala;
		IF NOT FOUND THEN
			id_max = 0;
		ELSE
			SELECT INTO id_max MAX (id) 
			FROM sala;
			id_max=id_max+1;
		END IF;
		
		NEW.id = id_max;
		IF NEW.nom IS NULL THEN
			RAISE EXCEPTION ''SA01-Sala: El nom de la sala no pot ser nul'';
		END IF;
		IF NEW.num_files IS NULL THEN
			RAISE EXCEPTION ''SA02-Sala: El numero de files de la sala no pot ser nul'';
		END IF;
		IF NEW.num_columnes IS NULL THEN
			RAISE EXCEPTION ''SA03-Sala: El numero de columnes de la sala no pot ser nul'';
		END IF;
		
		butaques := NEW.num_files * NEW.num_columnes;
		NEW.num_butaques = butaques;
		
		RETURN NEW;
	END;
'
LANGUAGE 'plpgsql';

--trigger per a la creació automàtica de butaques, al crear una sala, es crearan totes com a operatives
CREATE TRIGGER insertar_sala BEFORE INSERT ON sala  
	FOR EACH ROW EXECUTE PROCEDURE insert_sal();


	CREATE OR REPLACE FUNCTION crea_but() RETURNS TRIGGER AS'
	
	
	DECLARE
		butaques INTEGER;
		contador1 INTEGER;
		contador2 INTEGER;
	BEGIN
		contador1 = 0;
		contador2 = 0;
		WHILE contador1<NEW.num_files LOOP
			WHILE contador2<NEW.num_columnes LOOP
				INSERT INTO butaca (num_fila, num_columna, id_sala) VALUES (contador1, contador2, NEW.id);
				contador2 = contador2+1;
			END LOOP;
			contador2=0;
			contador1 = contador1+1;
		END LOOP;
		RETURN NEW;
	END;
'
LANGUAGE 'plpgsql';


CREATE TRIGGER crear_butaques AFTER INSERT ON sala  
	FOR EACH ROW EXECUTE PROCEDURE crea_but();



	
--trigger per a eliminar/afegir les butaques corresponents quan disminuim/augmentem el numero de files o el numero de columnes de la sala, si hi ha alguna butaca reservada/comprada en la sessio llança una excepcio
CREATE OR REPLACE FUNCTION modif_sala() RETURNS TRIGGER AS'	
	
	DECLARE
		nou_num_files INTEGER;
		nou_num_columnes INTEGER;
		contador1 INTEGER;
		contador2 INTEGER;
		compres_sala compra_reserva%ROWTYPE;
	BEGIN
		
		nou_num_files = NEW.num_files;
		nou_num_columnes = NEW.num_columnes;
		
		SELECT * INTO compres_sala
		FROM butaca, compra_reserva, sala, sessio
		WHERE sala.id=NEW.id
		AND compra_reserva.id_butaca =butaca.id 
		AND sala.id=butaca.id_sala
		AND compra_reserva.id_sessio=sessio.id
		AND butaca.num_fila>=nou_num_files
		AND butaca.num_columna>=nou_num_columnes
		AND sessio.data>NOW();		
		IF FOUND THEN
			RAISE EXCEPTION ''SA03-Sala: Alguna de les butaques a eliminar ja està comprada o reservada'';
		END IF;
		
		IF nou_num_files > OLD.num_files THEN
			contador1=OLD.num_files;
			contador2=0;
			WHILE contador1<nou_num_files LOOP
				WHILE contador2<OLD.num_columnes LOOP
					INSERT INTO butaca (num_fila, num_columna, id_sala) VALUES (contador1, contador2, NEW.id);
					contador2 = contador2+1;
				END LOOP;
				contador2=0;
				contador1 = contador1+1;
			END LOOP;
		END IF;	
		
		IF nou_num_files < OLD.num_files THEN
			DELETE FROM butaca 
			WHERE butaca.num_fila>nou_num_files-1 
			AND butaca.id_sala = NEW.id;
		END IF;
		
		IF nou_num_columnes > OLD.num_columnes THEN
			contador1=0;
			contador2=OLD.num_columnes;
			WHILE contador1<nou_num_files LOOP
				WHILE contador2<nou_num_columnes LOOP
					INSERT INTO butaca (num_fila, num_columna, id_sala) VALUES (contador1, contador2, NEW.id);
					contador2 = contador2+1;
				END LOOP;
				contador2=OLD.num_columnes;
				contador1 = contador1+1;
			END LOOP;
		END IF;	
		
		IF nou_num_columnes < OLD.num_columnes THEN
			DELETE FROM butaca 
			WHERE butaca.num_columna>nou_num_columnes-1
			AND butaca.id_sala = NEW.id;
		END IF;
		RETURN NEW;
	END;
'
LANGUAGE 'plpgsql';



CREATE TRIGGER modificar_sala BEFORE UPDATE ON sala  
	FOR EACH ROW EXECUTE PROCEDURE modif_sala();


--funci� per a comprobar que quan eliminem una sala no t� cap sessi� associada amb data igual o posterior a avui
CREATE OR REPLACE FUNCTION elim_sala() RETURNS TRIGGER AS'
	DECLARE
		descarta_sessio sessio%ROWTYPE;
	BEGIN	
		SELECT * INTO descarta_sessio
		FROM sessio
		WHERE sessio.id_sala = OLD.id
		AND sessio.data>=NOW();
		IF FOUND THEN
			RAISE EXCEPTION ''SA04-Sala:La sala no es pot eliminar perque t� alguna sessi� futura programada.Elimina les sessions'';
		END IF;
		DELETE FROM sessio
		WHERE OLD.id = sessio.id_sala;
		DELETE FROM butaca
		WHERE OLD.id = butaca.id_sala;
		RETURN OLD;

	END;
'
LANGUAGE 'plpgsql';

CREATE TRIGGER eliminar_sala BEFORE DELETE ON sala  
	FOR EACH ROW EXECUTE PROCEDURE elim_sala();


--*****************************************--

--TRIGGERS PER A LA COHERENCIA DELS ATRIBUTS DE BUTACA--

--*****************************************--

CREATE OR REPLACE FUNCTION insert_but() RETURNS TRIGGER AS'
	
	
	DECLARE
		id_max INTEGER;
		descarta butaca%ROWTYPE;
		descarta_sala sala%ROWTYPE;
	BEGIN
		SELECT * INTO descarta_sala 
		FROM sala
		WHERE NEW.id_sala=sala.id;
		IF NOT FOUND THEN
			RAISE EXCEPTION ''BU01-Butaca: clau forànea id_sala no existent a sala'';
		END IF;
	
		SELECT * INTO descarta 
		FROM butaca;
		IF NOT FOUND THEN
			id_max = 0;
		ELSE
			SELECT INTO id_max MAX (id) 
			FROM butaca;
			id_max=id_max+1;
		END IF;
		
		NEW.id = id_max;
		NEW.operativa = TRUE;
		RAISE NOTICE ''Butaca inserida'';
		RETURN NEW;
	END;
'
LANGUAGE 'plpgsql';


CREATE TRIGGER insertar_butaca BEFORE INSERT ON butaca  
	FOR EACH ROW EXECUTE PROCEDURE insert_but();

--funci� per a comprobar que quan eliminem una butaca no t� cap sessi� associada amb data igual o posterior a avui
CREATE OR REPLACE FUNCTION elim_but() RETURNS TRIGGER AS'
	DECLARE
		descarta_sessio sessio%ROWTYPE;
	BEGIN	
		SELECT sessio.id INTO descarta_sessio
		FROM sessio, compra_reserva
		WHERE sessio.id = compra_reserva.id_sessio
		AND compra_reserva.id_butaca=OLD.id
		AND sessio.data>=NOW();
		IF FOUND THEN
			RAISE EXCEPTION ''BU02- Butaca: No es pot eliminar perque hi ha alguna compra_reserva amb una sessio futura'';
		END IF;
		RETURN OLD;
	END;
'
LANGUAGE 'plpgsql';


CREATE TRIGGER eliminar_butaca BEFORE DELETE ON butaca  
	FOR EACH ROW EXECUTE PROCEDURE elim_but();	

	

--*****************************************--

--TRIGGERS PER A LA COHERENCIA DELS ATRIBUTS DE SESSIO--

--*****************************************--

CREATE OR REPLACE FUNCTION insert_sess() RETURNS TRIGGER AS'
	
	
	DECLARE
		id_max INTEGER;
		descarta sessio%ROWTYPE;
		descarta_peli pelicula%ROWTYPE;
		descarta_sala sala%ROWTYPE;
	BEGIN
		SELECT * INTO descarta_peli 
		FROM pelicula
		WHERE NEW.id_pelicula=pelicula.id;
		IF NOT FOUND THEN
			RAISE EXCEPTION ''SE01-Sessió: clau forànea id_pelicula no existent a pelicula'';
		END IF;
		
		SELECT * INTO descarta_sala 
		FROM sala
		WHERE NEW.id_sala=sala.id;
		IF NOT FOUND THEN
			RAISE EXCEPTION ''SE02-Sessió: clau forànea id_sala no existent a sala'';
		END IF;
		
		SELECT * INTO descarta 
		FROM sessio;
		IF NOT FOUND THEN
			id_max = 0;
		ELSE
			SELECT INTO id_max MAX (id) 
			FROM sessio;
			id_max=id_max+1;
		END IF;
		
		NEW.id = id_max;
		
		IF NEW.data IS NULL THEN
			RAISE EXCEPTION ''SE03-Sessió: La data de la sessió no pot ser nula'';
		END IF;
		
		IF NEW.hora_inici IS NULL THEN
			RAISE EXCEPTION ''SE04-Sessió: La hora_inici de la sessió no pot ser nula'';
		END IF;
		
		IF NEW.preu IS NULL THEN
			RAISE EXCEPTION ''SE05-Sessió: El preu de la sessió no pot ser nul'';
		END IF;
		
		IF NEW.id_sala IS NULL THEN
			RAISE EXCEPTION ''SE06-Sessió: L id_sala de la sessió no pot ser nul'';
		END IF;
		
		IF NEW.id_pelicula IS NULL THEN
			RAISE EXCEPTION ''SE07-Sessió: L id pelicula de la sessió no pot ser nul'';
		END IF;
		
		RETURN NEW;
	END;
'
LANGUAGE 'plpgsql';


CREATE TRIGGER insertar_sessio BEFORE INSERT ON sessio  
	FOR EACH ROW EXECUTE PROCEDURE insert_sess();

--funci� per a comprobar que quan eliminem una sessio no t� cap compra_reserva associada
CREATE OR REPLACE FUNCTION elim_sess() RETURNS TRIGGER AS'
	DECLARE
		compres_reserva compra_reserva%ROWTYPE;
	BEGIN	
		IF OLD.data >= NOW() THEN
			SELECT * INTO compres_reserva
			FROM compra_reserva
			WHERE OLD.id = compra_reserva.id_sessio
			AND OLD.data >= NOW();
		
			IF FOUND THEN
				RAISE EXCEPTION ''SE08- Sessio: No es pot eliminar perque encara no ha passat i hi ha alguna compra_reserva'';
			ELSE
				DELETE FROM compra_reserva
				WHERE compra_reserva.id_sessio = OLD.id;
			END IF;
		ELSE
			DELETE FROM compra_reserva
			WHERE compra_reserva.id_sessio = OLD.id;

		END IF;
		RETURN OLD;
	END;
'
LANGUAGE 'plpgsql';


CREATE TRIGGER borrar_sessio BEFORE DELETE ON sessio  
	FOR EACH ROW EXECUTE PROCEDURE elim_sess();


--*****************************************--

--TRIGGERS PER A LA COHERENCIA DELS ATRIBUTS DE COMPRA_RESERVA--

--*****************************************--

CREATE OR REPLACE FUNCTION insert_comp_res() RETURNS TRIGGER AS'
	
	
	DECLARE
		id_max INTEGER;
		descarta compra_reserva%ROWTYPE;
		descarta_sessio sessio%ROWTYPE;
		descarta_butaca butaca%ROWTYPE;
	BEGIN
		SELECT * INTO descarta_sessio 
		FROM sessio
		WHERE NEW.id_sessio=sessio.id;
		IF NOT FOUND THEN
			RAISE EXCEPTION ''CR01-Compra_reserva: clau forànea id_sessio no existent a sessio'';
		END IF;
		
		SELECT * INTO descarta_butaca 
		FROM butaca
		WHERE NEW.id_butaca=butaca.id;
		IF NOT FOUND THEN
			RAISE EXCEPTION ''CR02-Compra_reserva: clau forànea id_butaca no existent a butaca'';
		END IF;
		
		SELECT * INTO descarta 
		FROM compra_reserva;
		IF NOT FOUND THEN
			id_max = 0;
		ELSE
			SELECT INTO id_max MAX (id) 
			FROM compra_reserva;
			id_max=id_max+1;
		END IF;
		
		NEW.id = id_max;
		
		IF NEW.pagada IS NULL THEN
			RAISE EXCEPTION ''CR03-Compra_reserva: L atribut pagada de compra_reserva no pot ser nul'';
		END IF;
		
		IF NEW.codi_entrega IS NULL THEN
			RAISE EXCEPTION ''CR04-Compra_reserva: El codi entrega de compra_reserva no pot ser nul'';
		END IF;
		
		RETURN NEW;
	END;
'
LANGUAGE 'plpgsql';


CREATE TRIGGER insertar_compra_reserva BEFORE INSERT ON compra_reserva  
	FOR EACH ROW EXECUTE PROCEDURE insert_comp_res();




--*****************************************--

--TRIGGERS PER A LA COHERENCIA DELS ATRIBUTS DE PELICULA--

--*****************************************--

CREATE OR REPLACE FUNCTION insert_pel() RETURNS TRIGGER AS'
	
	
	DECLARE
		id_max INTEGER;
		descarta pelicula%ROWTYPE;
		descarta_nacionalitat nacionalitat%ROWTYPE;
		descarta_genere genere%ROWTYPE;
	BEGIN
				
		SELECT * INTO descarta_genere 
		FROM genere
		WHERE NEW.id_genere=genere.id;
		IF NOT FOUND THEN
			RAISE EXCEPTION ''PE01-Pelicula: clau forànea id_genere no existent a genere'';
		END IF;
		
		SELECT * INTO descarta 
		FROM pelicula;
		IF NOT FOUND THEN
			id_max = 0;
		ELSE
			SELECT INTO id_max MAX (id) 
			FROM pelicula;
			id_max=id_max+1;
		END IF;
		
		NEW.id = id_max;
		
		IF NEW.titol IS NULL THEN
			RAISE EXCEPTION ''PE02-Pelicula: El titol de la pelicula no pot ser nul'';
		END IF;
		
		IF NEW.anny IS NULL THEN
			RAISE EXCEPTION ''PE03-Pelicula: L any de la pelicula no pot ser nul'';
		END IF;
		
		IF NEW.id_genere IS NULL THEN
			RAISE EXCEPTION ''PE04-Pelicula: El genere de la pelicula no pot ser nul'';
		END IF;
		
		IF NEW.durada IS NULL THEN
			RAISE EXCEPTION ''PE05-Pelicula: La durada de la pelicula no pot ser nula'';
		END IF;
		
		IF NEW.edat_recomanada IS NULL THEN
			RAISE EXCEPTION ''PE06-Pelicula: L edat recomenada de la pelicula no pot ser nula'';
		END IF;
		
		RETURN NEW;
		
	END;
'
LANGUAGE 'plpgsql';


CREATE TRIGGER insertar_pelicula BEFORE INSERT ON pelicula  
	FOR EACH ROW EXECUTE PROCEDURE insert_pel();




--*****************************************--

--TRIGGERS PER A LA COHERENCIA DELS ATRIBUTS DE NACIONALITAT--

--*****************************************--

CREATE OR REPLACE FUNCTION insert_nac() RETURNS TRIGGER AS'
	
	
	DECLARE
		id_max INTEGER;
		descarta nacionalitat%ROWTYPE;
	BEGIN
		SELECT * INTO descarta 
		FROM nacionalitat;
		IF NOT FOUND THEN
			id_max = 0;
		ELSE
			SELECT INTO id_max MAX (id) 
			FROM nacionalitat;
			id_max=id_max+1;
		END IF;
		
		NEW.id = id_max;
		
		IF NEW.pais IS NULL THEN
			RAISE EXCEPTION ''NA01-Nacionalitat: El pais de la nacionalitat no pot ser nul'';
		END IF;
		
		RETURN NEW;
	END;
'
LANGUAGE 'plpgsql';


CREATE TRIGGER insertar_nacionalitat BEFORE INSERT ON nacionalitat  
	FOR EACH ROW EXECUTE PROCEDURE insert_nac();




--*****************************************--

--TRIGGERS PER A LA COHERENCIA DELS ATRIBUTS DE GENERE--

--*****************************************--

CREATE OR REPLACE FUNCTION insert_gen() RETURNS TRIGGER AS'
	
	
	DECLARE
		id_max INTEGER;
		descarta genere%ROWTYPE;
	BEGIN
		SELECT * INTO descarta 
		FROM genere;
		IF NOT FOUND THEN
			id_max = 0;
		ELSE
			SELECT INTO id_max MAX (id) 
			FROM genere;
			id_max=id_max+1;
		END IF;
		
		NEW.id = id_max;
		
		IF NEW.descripcio IS NULL THEN
			RAISE EXCEPTION ''GE01-Genere: La descripcio del genere no pot ser nul'';
		END IF;
		
		RETURN NEW;
	END;
'
LANGUAGE 'plpgsql';


CREATE TRIGGER insertar_genere BEFORE INSERT ON genere  
	FOR EACH ROW EXECUTE PROCEDURE insert_gen();




--*****************************************--

--TRIGGERS PER A LA COHERENCIA DELS ATRIBUTS DE TREBALLADOR--

--*****************************************--

CREATE OR REPLACE FUNCTION insert_treb() RETURNS TRIGGER AS'
	
	
	DECLARE
		id_max INTEGER;
		descarta treballador%ROWTYPE;
	BEGIN
		SELECT * INTO descarta 
		FROM treballador;
		IF NOT FOUND THEN
			id_max = 0;
		ELSE
			SELECT INTO id_max MAX (id) 
			FROM treballador;
			id_max=id_max+1;
		END IF;
		
		NEW.id = id_max;
		
		IF NEW.login IS NULL THEN
			RAISE EXCEPTION ''TR01-Treballador: El login del treballador no pot ser nul'';
		END IF;
		
		IF NEW.password IS NULL THEN
			RAISE EXCEPTION ''TR02-Treballador: El password del treballador no pot ser nul'';
		END IF;
		RETURN NEW;
	END;
'
LANGUAGE 'plpgsql';


CREATE TRIGGER insertar_treb BEFORE INSERT ON treballador  
	FOR EACH ROW EXECUTE PROCEDURE insert_treb();




/*
CREATE OR REPLACE FUNCTION sa_correcte() RETURNS TRIGGER AS'
	
	
	DECLARE
		butaques INTEGER;
	BEGIN
		butaques := NEW.num_files * NEW.num_columnes;
		NEW.num_butaques = butaques;
		NEW.num_butaques_operatives =NEW.num_files * NEW.num_columnes;
		RETURN NEW;
	END;
'
LANGUAGE 'plpgsql';

--trigger per a crear les dades derivades al insertar una sala

CREATE TRIGGER sala_correcte BEFORE INSERT ON sala  
	FOR EACH ROW EXECUTE PROCEDURE sa_correcte();


--*****************************************--

--TRIGGERS PER A LA COHERENCIA DELS ATRIBUTS DE PELÍCULA--

--*****************************************--



CREATE OR REPLACE FUNCTION peli_correcte() RETURNS TRIGGER AS'
	
	
	DECLARE
		nou_id INTEGER;
	BEGIN
		IF NEW.any < 1850 THEN
			RAISE EXCEPTION ''0000-Lany de la pelicula no pot ser menor que 1850'';
		END IF;
		IF NEW.durada < 1 THEN
			RAISE EXCEPTION ''0001-La durada duna pelicula no pot ser menor que 1'';
		END IF;
		RETURN NEW;
	END;
'
LANGUAGE 'plpgsql';

--trigger per a comprobar la coherencia de les dades al insertar pelicula

CREATE TRIGGER pelicula_correcte BEFORE INSERT OR UPDATE ON pelicula  
	FOR EACH ROW EXECUTE PROCEDURE peli_correcte();

	 





--*****************************************--

--TRIGGERS PER A LA COHERENCIA DELS ATRIBUTS DE BUTACA--

--*****************************************--


CREATE OR REPLACE FUNCTION inser_id_butaca() RETURNS TRIGGER AS'
	
	DECLARE
		id_max INTEGER;
		descarta butaca%ROWTYPE;
	BEGIN
		SELECT * INTO descarta FROM butaca;
		IF NOT FOUND THEN
			id_max = 0;
		ELSE
			SELECT INTO id_max MAX (id) FROM butaca;
			id_max=id_max+1;
		END IF;
		
		NEW.id = id_max;
		SELECT id INTO 
		FROM sessio 
		WHERE NEW.id_sala = sessio.id_sala; 
		IF (!NEW.operativa) THEN
			INSERT INTO butaca_sessio (estat,num_fila, num_columna) VALUES (0, NEW.num_fila, NEW.num_columna);
		ELSE
			INSERT INTO butaca_sessio (estat,num_fila, num_columna) VALUES (4, NEW.num_fila, NEW.num_columna);
		END IF;
		RETURN NEW;
	END;
'
LANGUAGE 'plpgsql';

--trigger per a insertar automàticament l'id de la butaca, un cop fet aixo creem totes les butaques sessio corresponents a totes les sessions

CREATE TRIGGER inserta_id_butaca AFTER INSERT ON butaca  
	FOR EACH ROW EXECUTE PROCEDURE inser_id_butaca();

	
--*****************************************--

--TRIGGERS PER A LA COHERENCIA DELS ATRIBUTS DE SESSIO--

--*****************************************--

CREATE OR REPLACE FUNCTION inser_id_butaca_sess() RETURNS TRIGGER AS'
	
	DECLARE
		id_max INTEGER;
		descarta_sess butaca_sessio%ROWTYPE;
		descarta butaca%ROWTYPE;
		
	BEGIN
		SELECT * INTO descarta 
		FROM butaca, sessio, butaca_sessio
		WHERE butaca.id_sala = sessio.id_sala
		AND butaca_sessio.id_sessio = sessio.id
		AND ;
		SELECT * INTO descarta_sess FROM butaca_sessio;
		IF NOT FOUND THEN
			id_max = 0;
		ELSE
			SELECT INTO id_max MAX (id) FROM butaca_sessio;
			id_max=id_max+1;
		END IF;
		
		NEW.id = id_max;
		
		RETURN NEW;
	END;
'
LANGUAGE 'plpgsql';

--trigger per a comprobar la coherencia de les dades al insertar butaca sessio, la butaca corresponent tambe ha d'existir i ha d'estar operativa
CREATE TRIGGER inserta_id_butaca_sessio BEFORE INSERT ON butaca_sessio  
	FOR EACH ROW EXECUTE PROCEDURE inser_id_butaca_sessio();

	*/ 