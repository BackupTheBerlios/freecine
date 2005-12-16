--*****************************************--
--*******AMPLIACIÓ DE BASES DE DADES*******--
--*********insertar_clients.sql******+*****--
--*****************************************--

-- dom 20 nov 2005 03:39:40 CET VICTOR OTERO CELA
-- sáb 03 dic 2005 18:07:14 CET VICTOR OTERO CELA

--Abel Parera             DNI:39358422-V--
--Victor Otero		  DNI:46967753-M--	

--*****************************************--

INSERT INTO client (nif,login,nom,cognom1,cognom2,ciutat,carrer,num,pis,telf_contacte,activitat_favorita )
	VALUES('46967753M', 'victor','Victor','Otero','Cela','Barcelona','C/Villarroel','57','3-2',934549294,'\N');
INSERT INTO client (nif,login,nom,cognom1,cognom2,ciutat,carrer,num,pis,telf_contacte,activitat_favorita )
	VALUES('56233122J','voc0210_dep_a','Abel','Miró','Martorell','Berga','C/Pio Baroja','23','1-2-B',938213356,'\N');
INSERT INTO client (nif,login,nom,cognom1,cognom2,ciutat,carrer,num,pis,telf_contacte,activitat_favorita )
	VALUES('36353911M','voc0210_dep_b','Laura','Requena','Sánchez','Castelldefels','Pg Marítim','43','3-4',938763345,'\N');
INSERT INTO client (nif,login,nom,cognom1,cognom2,ciutat,carrer,num,pis,telf_contacte,activitat_favorita )
	VALUES('58963688P','voc0210_cli_1','Antoni','Fuentes','Garcia','Avià','Pç de l Ajuntament','2','1-1',938236548,'\N');
INSERT INTO client (nif,login,nom,cognom1,cognom2,ciutat,carrer,num,pis,telf_contacte,activitat_favorita )
	VALUES('54213541T','voc0210_cli_2','Josep','Badia','Gorgs','Barcelona','C/Escudellers','47','2',934236587,'\N');
INSERT INTO client (nif,login,nom,cognom1,cognom2,ciutat,carrer,num,pis,telf_contacte,activitat_favorita )
	VALUES('98222221F','voc0210_cli_3','Marta','Giraut','Peralba','Barcelona','Avda. Gaudí','12','1',938215698,'\N');
INSERT INTO client (nif,login,nom,cognom1,cognom2,ciutat,carrer,num,pis,telf_contacte,activitat_favorita )
	VALUES('21536554I','voc0210_cli_4','Ramon','Virgili','Martinet','Sant Boi','C/ Indústria','67','5-7-C',936542185,'\N');
INSERT INTO client (nif,login,nom,cognom1,cognom2,ciutat,carrer,num,pis,telf_contacte,activitat_favorita )
	VALUES('56213210V','voc0210_cli_5','Josefina','Balaguer','Pérez','Castellar de N Hug','Casa Can Coix','1','1',938452684,'\N');
INSERT INTO client (nif,login,nom,cognom1,cognom2,ciutat,carrer,num,pis,telf_contacte,activitat_favorita )
	VALUES('53544554P','voc0210_cli_6','Carles','López','Malo','Gironella','C/Ciutat','3','1-1',938254512,'\N');
INSERT INTO client (nif,login,nom,cognom1,cognom2,ciutat,carrer,num,pis,telf_contacte,activitat_favorita )
	VALUES('97979797Y','voc0210_cli_7','Alba','Moliner','Fariner','Cornellà','C/Riera Blanca','125A','7-5-B',934102538,'\N');


-- Creem un client per les vendes on no calgui registrar-se i també per ficar les factures dels clients desapareguts.

INSERT INTO client (nif,login,nom,cognom1,cognom2,ciutat,carrer,num,pis,telf_contacte,activitat_favorita )
	VALUES('00000000Z','voc0210_cli_0','Client','Generic','Generic','NO','NO','NO','NO',930000000,'\N');
