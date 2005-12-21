CREATE OR REPLACE FUNCTION insert_sal () RETURNS TRIGGER AS'
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
		
		SELECT * INTO descarta
		FROM sala
		WHERE sala.nom = NEW.nom;
		IF FOUND THEN
			RAISE EXCEPTION ''SA07-Sala: El nom de la sala ja existeix, introdueix un altre nom'';
		END IF;

		RETURN NEW;
	END;
'
LANGUAGE 'plpgsql';

--trigger per a la creacio automatica de butaques, al crear una sala, es crearan totes com a operatives
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



--funci?per a comprobar que quan eliminem una sala no t?cap sessi?associada amb data igual o posterior a avui
CREATE OR REPLACE FUNCTION elim_sala() RETURNS TRIGGER AS'
	DECLARE
		descarta_sessio sessio%ROWTYPE;
	BEGIN	
		SELECT * INTO descarta_sessio
		FROM sessio
		WHERE sessio.id_sala = OLD.id
		AND sessio.data_hora_inici>=NOW();
		IF FOUND THEN
			RAISE EXCEPTION ''SA06-Sala:La sala no es pot eliminar perque t?alguna sessi?futura programada.Elimina les sessions'';
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


	
--trigger per a eliminar/afegir les butaques corresponents quan disminuim/augmentem el numero de files o el numero de columnes de la sala, si hi ha alguna butaca reservada/comprada en la sessio llan�a una excepcio
CREATE OR REPLACE FUNCTION modif_sala() RETURNS TRIGGER AS'	
	
	DECLARE
		nou_num_files INTEGER;
		nou_num_columnes INTEGER;
		contador1 INTEGER;
		contador2 INTEGER;
		descarta sala%ROWTYPE;
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
		AND sessio.data_hora_inici>NOW();
		
		
		
		IF FOUND THEN
			RAISE EXCEPTION ''SA04-Sala: Alguna de les butaques a eliminar ja esta comprada o reservada'';
		END IF;
		IF NEW.id != OLD.id THEN
			RAISE EXCEPTION ''SA05- Sala: L id no es pot modificar'';
		END IF;

		SELECT * INTO descarta
		FROM sala
		WHERE sala.nom = NEW.nom
		AND sala.id != NEW.id;
		IF FOUND THEN
			RAISE EXCEPTION ''SA07-Sala: El nom de la sala ja existeix, introdueix un altre nom'';
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
		NEW.num_butaques = NEW.num_files * NEW.num_columnes;
		RETURN NEW;
	END;
'
LANGUAGE 'plpgsql';



CREATE TRIGGER modificar_sala BEFORE UPDATE ON sala  
	FOR EACH ROW EXECUTE PROCEDURE modif_sala();

	
	
	
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
			RAISE EXCEPTION ''BU01-Butaca: clau foranea id_sala no existent a sala'';
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
		
		SELECT * INTO descarta
		FROM butaca
		WHERE butaca.num_fila = NEW.num_fila
		AND butaca.num_columna = NEW.num_columna
		AND butaca.id_sala = NEW.id_sala;
		IF FOUND THEN
			RAISE EXCEPTION ''BU08-Butaca: ja existeix aquesta butaca'';
		END IF;

		RETURN NEW;
	END;
'
LANGUAGE 'plpgsql';


CREATE TRIGGER insertar_butaca BEFORE INSERT ON butaca  
	FOR EACH ROW EXECUTE PROCEDURE insert_but();

--funci?per a comprobar que quan eliminem una butaca no t?cap sessi?associada amb data igual o posterior a avui
CREATE OR REPLACE FUNCTION elim_but() RETURNS TRIGGER AS'
	DECLARE
		descarta_sessio sessio%ROWTYPE;
	BEGIN	
		SELECT sessio.id INTO descarta_sessio
		FROM sessio, compra_reserva
		WHERE sessio.id = compra_reserva.id_sessio
		AND compra_reserva.id_butaca=OLD.id
		AND sessio.data_hora_inici>=NOW();
		IF FOUND THEN
			RAISE EXCEPTION ''BU02- Butaca: No es pot eliminar perque hi ha alguna compra_reserva amb una sessio futura'';
		END IF;
		RETURN OLD;
	END;
'
LANGUAGE 'plpgsql';


CREATE TRIGGER eliminar_butaca BEFORE DELETE ON butaca  
	FOR EACH ROW EXECUTE PROCEDURE elim_but();	

	
--funci?per a modificar una butaca 
CREATE OR REPLACE FUNCTION modif_but() RETURNS TRIGGER AS'
	DECLARE
		descarta_sessio sessio%ROWTYPE;
	BEGIN	
		IF NEW.num_columna != OLD.num_columna THEN
			RAISE EXCEPTION ''BU03- Butaca: El camp num_columna no es pot modificar'';
		END IF;
		
		IF NEW.num_fila != OLD.num_fila THEN
			RAISE EXCEPTION ''BU04- Butaca: El camp num_fila no es pot modificar'';
		END IF;
		
		IF NEW.id != OLD.id THEN
			RAISE EXCEPTION ''BU05- Butaca: El camp id no es pot modificar'';
		END IF;
		
		IF NEW.id_sala != OLD.id_sala THEN
			RAISE EXCEPTION ''BU06- Butaca: El camp id_sala no es pot modificar'';
		END IF;
		
		IF NEW.operativa != OLD.operativa THEN
			IF NEW.operativa IS FALSE THEN
				SELECT sessio.id INTO descarta_sessio
				FROM sessio, compra_reserva
				WHERE sessio.id = compra_reserva.id_sessio
				AND compra_reserva.id_butaca=NEW.id
				AND sessio.data_hora_inici>=NOW();	
				IF FOUND THEN
					RAISE EXCEPTION ''BU07- Butaca: No es pot ?odificar el camp operativa a FALSE perque hi ha alguna compra_reserva amb una sessio futura'';
				ELSE
					DELETE FROM compra_reserva
					WHERE compra_reserva.id_butaca= NEW.id ;
				END IF;
			END IF;
		END IF;
		RETURN NEW;
	END;
'
LANGUAGE 'plpgsql';


CREATE TRIGGER modificar_butaca BEFORE UPDATE ON butaca  
	FOR EACH ROW EXECUTE PROCEDURE modif_but();	

	
--*****************************************--

--TRIGGERS PER A LA COHERENCIA DELS ATRIBUTS DE SESSIO--

--*****************************************--

CREATE OR REPLACE FUNCTION insert_sess() RETURNS TRIGGER AS'
	
	
	DECLARE
		id_max INTEGER;
		descarta sessio%ROWTYPE;
		descarta_peli pelicula%ROWTYPE;
		descarta_sala sala%ROWTYPE;
		data_hora_fi_neteja TIMESTAMP WITHOUT TIME ZONE;
		durada_pelicula TIME;
	BEGIN
		SELECT * INTO descarta_peli 
		FROM pelicula
		WHERE NEW.id_pelicula=pelicula.id;
		IF NOT FOUND THEN
			RAISE EXCEPTION ''SE01-Sessio: clau foranea id_pelicula no existent a pelicula'';
		END IF;
		
		SELECT * INTO descarta_sala 
		FROM sala
		WHERE NEW.id_sala=sala.id;
		IF NOT FOUND THEN
			RAISE EXCEPTION ''SE02-Sessio: clau foranea id_sala no existent a sala'';
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
			
		SELECT pelicula.durada INTO durada_pelicula
		FROM pelicula
		WHERE NEW.id_pelicula = pelicula.id;
		
		NEW.data_hora_fi := NEW.data_hora_inici + durada_pelicula+''00:29:59'';
		
		SELECT * INTO descarta
		FROM sessio
		WHERE id_sala = NEW.id_sala
		AND ((sessio.data_hora_inici BETWEEN NEW.data_hora_inici AND NEW.data_hora_fi)
		OR (sessio.data_hora_fi BETWEEN NEW.data_hora_inici AND NEW.data_hora_fi));
		IF FOUND THEN 
			RAISE EXCEPTION ''SE13- Sessio: La sessio es solapa amb d altres sessions de la mateixa sala. Tingues en compte que entre sessio i sessio ha d haver-hi un marge de 30 minuts''; 
			
		END IF;
		
		IF NEW.data_hora_inici IS NULL THEN
			RAISE EXCEPTION ''SE03-Sessio: La data_hora_inici de la sessio no pot ser nula'';
		END IF;
		
		IF NEW.data_hora_fi IS NULL THEN
			RAISE EXCEPTION ''SE04-Sessio: La data_hora_fi de la sessio no pot ser nula'';
		END IF;
		
		IF NEW.preu IS NULL THEN
			RAISE EXCEPTION ''SE05-Sessio: El preu de la sessio no pot ser nul'';
		END IF;
		
		IF NEW.id_sala IS NULL THEN
			RAISE EXCEPTION ''SE06-Sessio: L id_sala de la sessio no pot ser nul'';
		END IF;
		
		IF NEW.id_pelicula IS NULL THEN
			RAISE EXCEPTION ''SE07-Sessio: L id pelicula de la sessio no pot ser nul'';
		END IF;
		
		RETURN NEW;
	END;
'
LANGUAGE 'plpgsql';


CREATE TRIGGER insertar_sessio BEFORE INSERT ON sessio  
	FOR EACH ROW EXECUTE PROCEDURE insert_sess();

--funci?per a comprobar que quan eliminem una sessio no t?cap compra_reserva associada
CREATE OR REPLACE FUNCTION elim_sess() RETURNS TRIGGER AS'
	DECLARE
		compres_reserva compra_reserva%ROWTYPE;
	BEGIN	
		IF OLD.data_hora_inici >= NOW() THEN
			SELECT * INTO compres_reserva
			FROM compra_reserva
			WHERE OLD.id = compra_reserva.id_sessio
			AND OLD.data_hora_inici >= NOW();
		
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

--funci?per a modificar una sessio, nomes es pot modificar el preu. 
CREATE OR REPLACE FUNCTION modif_sess() RETURNS TRIGGER AS'
	DECLARE
	BEGIN	
		IF NEW.data_hora_inici != OLD.data_hora_inici THEN
			RAISE EXCEPTION ''SE09- Sessio: El camp data_hora_inici no es pot modificar'';
		END IF;
		
		IF NEW.data_hora_fi != OLD.data_hora_fi THEN
			RAISE EXCEPTION ''SE10- Sessio: El camp data_hora_fi no es pot modificar'';
		END IF;
		
		IF NEW.id != OLD.id THEN
			RAISE EXCEPTION ''SE11- Sessio: El camp id no es pot modificar'';
		END IF;
		
		IF NEW.id_sala != OLD.id_sala THEN
			RAISE EXCEPTION ''SE12- Sessio: El camp id_sala no es pot modificar'';
		END IF;
		RETURN NEW;
	END;
'
LANGUAGE 'plpgsql';


CREATE TRIGGER modificar_sessio BEFORE UPDATE ON sessio  
	FOR EACH ROW EXECUTE PROCEDURE modif_sess();
	
		

--*****************************************--

--TRIGGERS PER A LA COHERENCIA DELS ATRIBUTS DE COMPRA_RESERVA--

--*****************************************--

CREATE OR REPLACE FUNCTION insert_comp_res() RETURNS TRIGGER AS'
	
	
	DECLARE
		id_max INTEGER;
		descarta compra_reserva%ROWTYPE;
		descarta_sessio sessio%ROWTYPE;
		descarta_butaca butaca%ROWTYPE;
		data_hora_sessio TIMESTAMP WITHOUT TIME ZONE;
		id_sala_per_butaca INTEGER;
		id_sala_per_sessio INTEGER;
	BEGIN
		SELECT * INTO descarta_sessio 
		FROM sessio
		WHERE NEW.id_sessio=sessio.id;
		IF NOT FOUND THEN
			RAISE EXCEPTION ''CR01-Compra_reserva: clau foranea id_sessio no existent a sessio'';
		END IF;
		
		SELECT * INTO descarta_butaca 
		FROM butaca
		WHERE NEW.id_butaca=butaca.id;
		IF NOT FOUND THEN
			RAISE EXCEPTION ''CR02-Compra_reserva: clau foranea id_butaca no existent a butaca'';
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
		NEW.data_hora = NOW();
		
		
		SELECT sessio.data_hora_inici INTO data_hora_sessio
		FROM sessio
		WHERE NEW.id_sessio = sessio.id;
		IF NEW.data_hora+interval ''5 days'' < data_hora_sessio THEN
			RAISE EXCEPTION ''CR09- Compra_reserva: No es pot comprar ni reservar fins que faltin 5 dies per a la sessio'';
		END IF;
		SELECT *  INTO descarta 
		FROM compra_reserva
		WHERE compra_reserva.id_sessio = NEW.id_sessio
		AND compra_reserva.id_butaca = NEW.id_butaca;
		IF FOUND THEN
			RAISE EXCEPTION ''CR10- Compra_reserva: Ja hi ha una compra_reserva en la mateixa sessio i butaca'';
		END IF;
		SELECT *  INTO descarta 
		FROM compra_reserva
		WHERE compra_reserva.codi_entrega = NEW.codi_entrega
		AND compra_reserva.id_sessio != NEW.id_sessio;
		IF FOUND THEN
			RAISE EXCEPTION ''CR11- Compra_reserva: Ja hi ha una compra_reserva amb el mateix codi d entrega en una sessio diferent'';
		END IF;
		
		SELECT butaca.id_sala INTO id_sala_per_butaca
		FROM butaca
		WHERE butaca.id = NEW.id_butaca;
		
		SELECT sessio.id_sala INTO id_sala_per_sessio
		FROM sessio
		WHERE sessio.id = NEW.id_sessio;
		
		IF id_sala_per_sessio != id_sala_per_butaca THEN
			RAISE EXCEPTION ''CR12- Compra_reserva: La sala anant per sessio o anant per butaca no coincideixen'';
		END IF;
		RETURN NEW;
	END;
'
LANGUAGE 'plpgsql';


CREATE TRIGGER insertar_compra_reserva BEFORE INSERT ON compra_reserva  
	FOR EACH ROW EXECUTE PROCEDURE insert_comp_res();


--funci?per a eliminar una compra_reserva, nomes es podra eliminar en cas de que no estigui pagada o de que ja hagi passat la sessio corresponent
CREATE OR REPLACE FUNCTION elim_comp_res() RETURNS TRIGGER AS'
	DECLARE
		data_hora_sessio TIMESTAMP WITHOUT TIME ZONE;
	BEGIN	
		SELECT sessio.data_hora_inici INTO data_hora_sessio
		FROM sessio
		WHERE sessio.id = OLD.id_sessio;
		IF data_hora_sessio >= NOW() THEN
			IF OLD.pagada = TRUE THEN
				RAISE EXCEPTION ''CR13- Compra_reserva: No es pot eliminar una compra_reserva d una sessio que encara no ha passat'';
			END IF;
		ELSE

		END IF;
		RETURN OLD;
	END;
'
LANGUAGE 'plpgsql';


CREATE TRIGGER eliminar_compra_reserva BEFORE DELETE ON compra_reserva  
	FOR EACH ROW EXECUTE PROCEDURE elim_comp_res();
	



--funci?per a modificar una compra_reserva, nomes es pot modificar l'atribut pagada de FALSE a TRUE. 
CREATE OR REPLACE FUNCTION modif_comp_res() RETURNS TRIGGER AS'
	DECLARE
	BEGIN	
		IF NEW.data_hora != OLD.data_hora THEN
			RAISE EXCEPTION ''CR05- Compra_reserva: El camp data_hora no es pot modificar'';
		END IF;
		
		IF NEW.codi_entrega != OLD.codi_entrega THEN
			RAISE EXCEPTION ''CR06- Compra_reserva: El camp codi_entrega no es pot modificar'';
		END IF;
		
		IF NEW.pagada != OLD.pagada THEN
			IF NEW.pagada IS FALSE THEN
				RAISE EXCEPTION ''CR07- Compra_reserva: El camp pagada no es pot posar a FALSE'';
			END IF;
		END IF;
		
		IF NEW.id != OLD.id THEN
			RAISE EXCEPTION ''CR08- Compra_reserva: El camp id no es pot modificar'';
		END IF;
		
		RETURN NEW;
	END;
'
LANGUAGE 'plpgsql';


CREATE TRIGGER modificar_compra_reserva BEFORE UPDATE ON compra_reserva  
	FOR EACH ROW EXECUTE PROCEDURE modif_comp_res();
	


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
			RAISE EXCEPTION ''PE01-Pelicula: clau foranea id_genere no existent a genere'';
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

		SELECT * INTO descarta
		FROM pelicula
		WHERE pelicula.titol = NEW.titol
		AND pelicula.id != NEW.id;
		IF FOUND THEN
			RAISE EXCEPTION ''PE09-Pelicula: El titol de la pelicula ja existeix, introdueix un altre titol'';
		END IF;

		RETURN NEW;
		
	END;
'
LANGUAGE 'plpgsql';


CREATE TRIGGER insertar_pelicula BEFORE INSERT ON pelicula  
	FOR EACH ROW EXECUTE PROCEDURE insert_pel();


CREATE OR REPLACE FUNCTION elim_pel() RETURNS TRIGGER AS'
	
	
	DECLARE
		descarta sessio%ROWTYPE;
	BEGIN
		SELECT * INTO descarta
		FROM sessio
		WHERE sessio.id_pelicula = OLD.id
		AND sessio.data_hora_inici >= NOW();
		IF FOUND THEN
			RAISE EXCEPTION ''PE08- Pelicula: No es pot eliminar la pelicula perque hi han sessions futures associades, elimina primer les sessions'';
		ELSE
			DELETE FROM sessio
			WHERE sessio.id_pelicula = OLD.id;
		END IF;
		RETURN OLD;
	END;
'
LANGUAGE 'plpgsql';


CREATE TRIGGER eliminar_pelicula BEFORE DELETE ON pelicula  
	FOR EACH ROW EXECUTE PROCEDURE elim_pel();

--funci?per a modificar una pelicula,no es pot modificar l'id
CREATE OR REPLACE FUNCTION modif_pel() RETURNS TRIGGER AS'
	DECLARE
		descarta pelicula%ROWTYPE;
	BEGIN			
		IF NEW.id != OLD.id THEN
			RAISE EXCEPTION ''PE07- Pelicula: El camp id no es pot modificar'';
		END IF;
		
		SELECT * INTO descarta
		FROM pelicula
		WHERE pelicula.titol = NEW.titol
		AND pelicula.id != NEW.id;
		IF FOUND THEN
			RAISE EXCEPTION ''PE09-Pelicula: El titol de la pelicula ja existeix, introdueix un altre titol'';
		END IF;


		RETURN NEW;
	END;
'
LANGUAGE 'plpgsql';


CREATE TRIGGER modificar_pelicula BEFORE UPDATE ON pelicula  
	FOR EACH ROW EXECUTE PROCEDURE modif_pel();
	




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
		
		SELECT * INTO descarta
		FROM nacionalitat
		WHERE nacionalitat.pais = NEW.pais;
		IF FOUND THEN
			RAISE EXCEPTION ''NA04-Nacionalitat: Aquesta nacionalitat ja existeix, introdueix un altre pais'';
		END IF;

		RETURN NEW;
	END;
'
LANGUAGE 'plpgsql';


CREATE TRIGGER insertar_nacionalitat BEFORE INSERT ON nacionalitat  
	FOR EACH ROW EXECUTE PROCEDURE insert_nac();


--funci?per a modificar una nacionalitat, no es pot modificar res
CREATE OR REPLACE FUNCTION modif_nac() RETURNS TRIGGER AS'
	DECLARE
	BEGIN		
		
		IF NEW.pais != OLD.pais THEN
			RAISE EXCEPTION ''NA02- Nacionalitat: El camp pais no es pot modificar'';
		END IF;
			
		IF NEW.id != OLD.id THEN
			RAISE EXCEPTION ''NA03- Nacionalitat: El camp id no es pot modificar'';
		END IF;
		
		RETURN NEW;
	END;
'
LANGUAGE 'plpgsql';


CREATE TRIGGER modificar_nacionalitat BEFORE UPDATE ON nacionalitat  
	FOR EACH ROW EXECUTE PROCEDURE modif_nac();
	

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

		SELECT * INTO descarta
		FROM genere
		WHERE genere.descripcio = NEW.descripcio;
		IF FOUND THEN
			RAISE EXCEPTION ''GE04-Genere: Aquest genere ja existeix, introdueix una altra descripcio'';
		END IF;

		
		RETURN NEW;
	END;
'
LANGUAGE 'plpgsql';


CREATE TRIGGER insertar_genere BEFORE INSERT ON genere  
	FOR EACH ROW EXECUTE PROCEDURE insert_gen();



--funci?per a modificar un genere, no es pot modificar res
CREATE OR REPLACE FUNCTION modif_gen() RETURNS TRIGGER AS'
	DECLARE
		descarta genere%ROWTYPE;
	BEGIN		
		
		IF NEW.descripcio != OLD.descripcio THEN
			RAISE EXCEPTION ''GE02- Genere: El camp descripcio no es pot modificar'';
		END IF;
			
		IF NEW.id != OLD.id THEN
			RAISE EXCEPTION ''GE03- Genere: El camp id no es pot modificar'';
		END IF;
		
		RETURN NEW;
	END;
'
LANGUAGE 'plpgsql';


CREATE TRIGGER modificar_genere BEFORE UPDATE ON genere  
	FOR EACH ROW EXECUTE PROCEDURE modif_gen();
	


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

		SELECT * INTO descarta
		FROM treballador
		WHERE treballador.login = NEW.login;
		IF FOUND THEN
			RAISE EXCEPTION ''TR04-Treballador:Ja existeix un altre treballador amb aquest login, introdueix un altre login'';
		END IF;
		RETURN NEW;
	END;
'
LANGUAGE 'plpgsql';


CREATE TRIGGER insertar_treb BEFORE INSERT ON treballador  
	FOR EACH ROW EXECUTE PROCEDURE insert_treb();



--funci?per a modificar una treballador, el camp id no es pot modificar, si modifiquem login aleshores mirarem que no existeixi ja
CREATE OR REPLACE FUNCTION modif_tre() RETURNS TRIGGER AS'
	DECLARE
	BEGIN		
		IF NEW.id != OLD.id THEN
			RAISE EXCEPTION ''TR03- Treballador: El camp id no es pot modificar'';
		END IF;
		
		SELECT * INTO descarta
		FROM treballador
		WHERE treballador.login = NEW.login
		AND treballador.id != NEW.id;
		IF FOUND THEN
			RAISE EXCEPTION ''TR04-Treballador:Ja existeix un altre treballador amb aquest login, introdueix un altre login'';
		END IF;
		RETURN NEW;
	END;
'
LANGUAGE 'plpgsql';


CREATE TRIGGER modificar_treballador BEFORE UPDATE ON treballador  
	FOR EACH ROW EXECUTE PROCEDURE modif_tre();
	