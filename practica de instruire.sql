-- Database: Cinematograf

-- DROP DATABASE IF EXISTS "Cinematograf";
CREATE TABLE Cinematografie(
    IdCinematografie INT PRIMARY KEY NOT NULL,
    Nume TEXT NOT NULL,
    Adresa VARCHAR(40) NOT NULL,
    CodPostal VARCHAR(10) NOT NULL,
    Oras TEXT NOT NULL,
    Tara TEXT NOT NULL
);

INSERT INTO Cinematografie VALUES
(1, 'Cinema City', 'Bd. Stefan cel Mare 136', 'MD-2001', 'Chisinau', 'Moldova'),
(2, 'Patria Multiplex', 'Str. Arborilor 21', 'MD-2025', 'Chisinau', 'Moldova'),
(3, 'Patria Loteanu', 'Bd. Stefan cel Mare 103', 'MD-2001', 'Chisinau', 'Moldova'),
(4, 'Cinema Buiucani', 'Str. Alba Iulia 75', 'MD-2071', 'Chisinau', 'Moldova'),
(5, 'Cinema Balti', 'Str. Independentei 12', 'MD-3100', 'Balti', 'Moldova');



CREATE TABLE Sali(
    IdSala INT PRIMARY KEY NOT NULL,
    NumeSala VARCHAR(20) NOT NULL,
    Capacitate INT NOT NULL,
    TipSala VARCHAR(20) NOT NULL,
    IdCinematografie INT NOT NULL,

    FOREIGN KEY (IdCinematografie)
    REFERENCES Cinematografie(IdCinematografie)
);

INSERT INTO Sali VALUES
(1,'Sala IMAX',120,'IMAX',1),
(2,'Sala VIP',80,'VIP',2),
(3,'Sala 3D',100,'3D',3),
(4,'Sala Standard',90,'2D',4),
(5,'Sala Dolby',110,'Dolby Atmos',5);



CREATE TABLE Filme(
    IdFilme INT PRIMARY KEY NOT NULL,
    Denumire VARCHAR(50) NOT NULL,
    Data_Lansare DATE NOT NULL,
    Gen TEXT NOT NULL,
    Audio VARCHAR(15) NOT NULL,
    Limita_Varsta INT NOT NULL,
    DurataMinute INT NOT NULL,
    Format VARCHAR(10) NOT NULL,
    Sunet TEXT NOT NULL
);

INSERT INTO Filme VALUES
(1,'Avatar: Calea Apei','2022-12-16','Actiune','Engleza',12,192,'3D','Dolby Atmos'),
(2,'Oppenheimer','2023-07-21','Drama','Engleza',16,180,'2D','Dolby Digital'),
(3,'Barbie','2023-07-21','Comedie','Engleza',10,114,'2D','Stereo'),
(4,'John Wick 4','2023-03-24','Actiune','Engleza',18,169,'2D','Dolby Atmos'),
(5,'Interstellar','2014-11-07','SF','Engleza',12,169,'2D','Dolby Digital'),
(6,'Inception','2010-07-16','Thriller','Engleza',14,148,'2D','Dolby Digital'),
(7,'Titanic','1997-12-19','Romantic','Engleza',12,195,'2D','Stereo'),
(8,'Fast X','2023-05-19','Actiune','Engleza',12,141,'2D','Dolby Atmos'),
(9,'Spider-Man: No Way Home','2021-12-17','Actiune','Engleza',12,148,'3D','Dolby Atmos'),
(10,'The Batman','2022-03-04','Actiune','Engleza',14,176,'2D','Dolby Digital'),
(11,'Frozen II','2019-11-22','Animatie','Romana',0,103,'2D','Stereo'),
(12,'Minions','2022-07-01','Animatie','Romana',0,90,'2D','Stereo'),
(13,'Dune','2021-10-22','SF','Engleza',12,155,'2D','Dolby Atmos'),
(14,'The Matrix','1999-03-31','SF','Engleza',14,136,'2D','Dolby Digital'),
(15,'Joker','2019-10-04','Drama','Engleza',16,122,'2D','Dolby Digital'),
(16,'Deadpool','2016-02-12','Comedie','Engleza',18,108,'2D','Stereo'),
(17,'Aquaman','2018-12-21','Actiune','Engleza',12,143,'3D','Dolby Atmos'),
(18,'Shrek','2001-05-18','Animatie','Romana',0,90,'2D','Stereo'),
(19,'Gladiator','2000-05-05','Actiune','Engleza',16,155,'2D','Dolby Digital'),
(20,'The Lion King','2019-07-19','Animatie','Romana',0,118,'2D','Stereo'),
(21,'Doctor Strange','2022-05-06','Actiune','Engleza',12,126,'2D','Dolby Digital'),
(22,'Black Panther','2018-02-16','Actiune','Engleza',12,134,'2D','Dolby Atmos'),
(23,'Avengers Endgame','2019-04-26','Actiune','Engleza',12,181,'3D','Dolby Atmos'),
(24,'Thor Ragnarok','2017-11-03','Comedie','Engleza',12,130,'2D','Stereo'),
(25,'Guardians Galaxy','2014-08-01','Actiune','Engleza',12,121,'2D','Dolby Digital'),
(26,'Harry Potter 1','2001-11-16','Fantezie','Engleza',10,152,'2D','Stereo'),
(27,'Harry Potter 2','2002-11-15','Fantezie','Engleza',10,161,'2D','Stereo'),
(28,'Harry Potter 3','2004-06-04','Fantezie','Engleza',10,142,'2D','Stereo'),
(29,'Harry Potter 4','2005-11-18','Fantezie','Engleza',12,157,'2D','Stereo'),
(30,'Harry Potter 5','2007-07-11','Fantezie','Engleza',12,138,'2D','Stereo'),
(31,'Harry Potter 6','2009-07-15','Fantezie','Engleza',12,153,'2D','Stereo'),
(32,'Harry Potter 7','2010-11-19','Fantezie','Engleza',12,146,'2D','Stereo'),
(33,'Harry Potter 8','2011-07-15','Fantezie','Engleza',12,130,'2D','Stereo'),
(34,'Cars','2006-06-09','Animatie','Romana',0,117,'2D','Stereo'),
(35,'Toy Story','1995-11-22','Animatie','Romana',0,81,'2D','Stereo'),
(36,'Toy Story 2','1999-11-24','Animatie','Romana',0,92,'2D','Stereo'),
(37,'Toy Story 3','2010-06-18','Animatie','Romana',0,103,'2D','Stereo'),
(38,'Toy Story 4','2019-06-21','Animatie','Romana',0,100,'2D','Stereo'),
(39,'Coco','2017-11-22','Animatie','Romana',0,105,'2D','Stereo'),
(40,'Up','2009-05-29','Animatie','Romana',0,96,'2D','Stereo'),
(41,'Iron Man','2008-05-02','Actiune','Engleza',12,126,'2D','Dolby Digital'),
(42,'Iron Man 2','2010-05-07','Actiune','Engleza',12,124,'2D','Dolby Digital'),
(43,'Iron Man 3','2013-05-03','Actiune','Engleza',12,130,'2D','Dolby Digital'),
(44,'Hobbitul 1','2012-12-14','Fantezie','Engleza',12,169,'2D','Dolby Atmos'),
(45,'Hobbitul 2','2013-12-13','Fantezie','Engleza',12,161,'2D','Dolby Atmos'),
(46,'Hobbitul 3','2014-12-17','Fantezie','Engleza',12,144,'2D','Dolby Atmos'),
(47,'Transformers','2007-07-03','Actiune','Engleza',12,144,'2D','Dolby Digital'),
(48,'Transformers 2','2009-06-24','Actiune','Engleza',12,150,'2D','Dolby Digital'),
(49,'Transformers 3','2011-06-29','Actiune','Engleza',12,154,'2D','Dolby Digital'),
(50,'Transformers 4','2014-06-27','Actiune','Engleza',12,165,'2D','Dolby Digital');



CREATE TABLE Clienti(
    IdClienti INT PRIMARY KEY NOT NULL,
    Nume TEXT NOT NULL,
    Prenume TEXT NOT NULL,
    Email VARCHAR(50) NOT NULL,
	Telefon VARCHAR(15) NOT NULL
);

INSERT INTO Clienti VALUES
(1,'Popescu','Ion','ion1@gmail.com','0791000001'),
(2,'Ionescu','Maria','maria2@gmail.com','0791000002'),
(3,'Rusu','Andrei','andrei3@gmail.com','0791000003'),
(4,'Munteanu','Elena','elena4@gmail.com','0791000004'),
(5,'Popa','Daniel','daniel5@gmail.com','0791000005'),
(6,'Moraru','Ana','ana6@gmail.com','0791000006'),
(7,'Ceban','Victor','victor7@gmail.com','0791000007'),
(8,'Dumitru','Irina','irina8@gmail.com','0791000008'),
(9,'Luca','Mihai','mihai9@gmail.com','0791000009'),
(10,'Stan','Alina','alina10@gmail.com','0791000010'),
(11,'Toma','Cristian','cristian11@gmail.com','0791000011'),
(12,'Nistor','Bianca','bianca12@gmail.com','0791000012'),
(13,'Enache','Paul','paul13@gmail.com','0791000013'),
(14,'Voicu','Simona','simona14@gmail.com','0791000014'),
(15,'Marin','George','george15@gmail.com','0791000015'),
(16,'Barbu','Raluca','raluca16@gmail.com','0791000016'),
(17,'Ilie','Adrian','adrian17@gmail.com','0791000017'),
(18,'Costin','Daria','daria18@gmail.com','0791000018'),
(19,'Florea','Alex','alex19@gmail.com','0791000019'),
(20,'Neagu','Teodora','teodora20@gmail.com','0791000020'),
(21,'Dragomir','Vlad','vlad21@gmail.com','0791000021'),
(22,'Gheorghe','Ioana','ioana22@gmail.com','0791000022'),
(23,'Serban','Robert','robert23@gmail.com','0791000023'),
(24,'Matei','Larisa','larisa24@gmail.com','0791000024'),
(25,'Anghel','Florin','florin25@gmail.com','0791000025'),
(26,'Badea','Denisa','denisa26@gmail.com','0791000026'),
(27,'Dinu','Alexandra','alexandra27@gmail.com','0791000027'),
(28,'Filip','Rares','rares28@gmail.com','0791000028'),
(29,'Grigore','Paula','paula29@gmail.com','0791000029'),
(30,'Iacob','Stefan','stefan30@gmail.com','0791000030'),
(31,'Jipa','Camelia','camelia31@gmail.com','0791000031'),
(32,'Kovacs','Eva','eva32@gmail.com','0791000032'),
(33,'Lazar','Ovidiu','ovidiu33@gmail.com','0791000033'),
(34,'Mihai','Corina','corina34@gmail.com','0791000034'),
(35,'Nica','Sorin','sorin35@gmail.com','0791000035'),
(36,'Olaru','Diana','diana36@gmail.com','0791000036'),
(37,'Pavel','Tudor','tudor37@gmail.com','0791000037'),
(38,'Radulescu','Ilinca','ilinca38@gmail.com','0791000038'),
(39,'Sandu','Emil','emil39@gmail.com','0791000039'),
(40,'Tudor','Adela','adela40@gmail.com','0791000040'),
(41,'Ursu','Liviu','liviu41@gmail.com','0791000041'),
(42,'Vasilescu','Anca','anca42@gmail.com','0791000042'),
(43,'Zaharia','Bogdan','bogdan43@gmail.com','0791000043'),
(44,'Apostol','Irina','irina44@gmail.com','0791000044'),
(45,'Balan','Marius','marius45@gmail.com','0791000045'),
(46,'Chiriac','Silvia','silvia46@gmail.com','0791000046'),
(47,'Dobre','Ionut','ionut47@gmail.com','0791000047'),
(48,'Ene','Gabriela','gabriela48@gmail.com','0791000048'),
(49,'Fodor','Attila','attila49@gmail.com','0791000049'),
(50,'Georgescu','Claudia','claudia50@gmail.com','0791000050');

CREATE TABLE Locuri(
    IdLoc INT PRIMARY KEY NOT NULL,
    Rand INT NOT NULL,
    NumarLoc INT NOT NULL,
    TipLoc VARCHAR(20) NOT NULL,
    IdSala INT NOT NULL,

    FOREIGN KEY (IdSala)
    REFERENCES Sali(IdSala)
);

INSERT INTO Locuri VALUES
(1,1,1,'Standard',1),
(2,1,2,'Standard',1),
(3,1,3,'VIP',1),
(4,1,4,'VIP',1),
(5,1,5,'Standard',1),
(6,2,1,'Standard',1),
(7,2,2,'VIP',1),
(8,2,3,'Standard',1),
(9,2,4,'VIP',1),
(10,2,5,'Standard',1),

(11,1,1,'Standard',2),
(12,1,2,'VIP',2),
(13,1,3,'Standard',2),
(14,1,4,'VIP',2),
(15,1,5,'Standard',2),
(16,2,1,'VIP',2),
(17,2,2,'Standard',2),
(18,2,3,'VIP',2),
(19,2,4,'Standard',2),
(20,2,5,'VIP',2),

(21,1,1,'Standard',3),
(22,1,2,'VIP',3),
(23,1,3,'Standard',3),
(24,1,4,'VIP',3),
(25,1,5,'Standard',3),
(26,2,1,'VIP',3),
(27,2,2,'Standard',3),
(28,2,3,'VIP',3),
(29,2,4,'Standard',3),
(30,2,5,'VIP',3),

(31,1,1,'Standard',4),
(32,1,2,'VIP',4),
(33,1,3,'Standard',4),
(34,1,4,'VIP',4),
(35,1,5,'Standard',4),
(36,2,1,'VIP',4),
(37,2,2,'Standard',4),
(38,2,3,'VIP',4),
(39,2,4,'Standard',4),
(40,2,5,'VIP',4),

(41,1,1,'Standard',5),
(42,1,2,'VIP',5),
(43,1,3,'Standard',5),
(44,1,4,'VIP',5),
(45,1,5,'Standard',5),
(46,2,1,'VIP',5),
(47,2,2,'Standard',5),
(48,2,3,'VIP',5),
(49,2,4,'Standard',5),
(50,2,5,'VIP',5);



CREATE TABLE Proiectii(
    IdProiectii INT PRIMARY KEY NOT NULL,
    Data DATE NOT NULL,
    Ora TIME NOT NULL,
    Limba VARCHAR(20) NOT NULL,
    Subtitrare VARCHAR(20) NOT NULL,
    PretBaza DECIMAL(6,2) NOT NULL,
    IdFilme INT NOT NULL,
    IdSala INT NOT NULL,

    FOREIGN KEY (IdFilme)
    REFERENCES Filme(IdFilme),

    FOREIGN KEY (IdSala)
    REFERENCES Sali(IdSala)
);

INSERT INTO Proiectii VALUES
(1,'2026-05-10','14:00','Engleza','Romana',120,1,1),
(2,'2026-05-10','16:00','Engleza','Romana',110,2,2),
(3,'2026-05-10','18:00','Engleza','Romana',100,3,3),
(4,'2026-05-10','20:00','Engleza','Romana',90,4,4),
(5,'2026-05-11','14:00','Engleza','Romana',130,5,5),
(6,'2026-05-11','16:00','Engleza','Romana',95,6,1),
(7,'2026-05-11','18:00','Engleza','Romana',105,7,2),
(8,'2026-05-11','20:00','Engleza','Romana',115,8,3),
(9,'2026-05-12','14:00','Engleza','Romana',125,9,4),
(10,'2026-05-12','16:00','Engleza','Romana',100,10,5),

(11,'2026-05-12','18:00','Romana','Engleza',80,11,1),
(12,'2026-05-12','20:00','Romana','Engleza',85,12,2),
(13,'2026-05-13','14:00','Engleza','Romana',135,13,3),
(14,'2026-05-13','16:00','Engleza','Romana',95,14,4),
(15,'2026-05-13','18:00','Engleza','Romana',90,15,5),
(16,'2026-05-13','20:00','Engleza','Romana',100,16,1),
(17,'2026-05-14','14:00','Engleza','Romana',120,17,2),
(18,'2026-05-14','16:00','Romana','Engleza',75,18,3),
(19,'2026-05-14','18:00','Engleza','Romana',115,19,4),
(20,'2026-05-14','20:00','Romana','Engleza',85,20,5),

(21,'2026-05-15','14:00','Engleza','Romana',100,21,1),
(22,'2026-05-15','16:00','Engleza','Romana',110,22,2),
(23,'2026-05-15','18:00','Engleza','Romana',140,23,3),
(24,'2026-05-15','20:00','Engleza','Romana',105,24,4),
(25,'2026-05-16','14:00','Engleza','Romana',95,25,5),
(26,'2026-05-16','16:00','Engleza','Romana',90,26,1),
(27,'2026-05-16','18:00','Engleza','Romana',90,27,2),
(28,'2026-05-16','20:00','Engleza','Romana',90,28,3),
(29,'2026-05-17','14:00','Engleza','Romana',95,29,4),
(30,'2026-05-17','16:00','Engleza','Romana',95,30,5),

(31,'2026-05-17','18:00','Engleza','Romana',100,31,1),
(32,'2026-05-17','20:00','Engleza','Romana',100,32,2),
(33,'2026-05-18','14:00','Engleza','Romana',100,33,3),
(34,'2026-05-18','16:00','Romana','Engleza',80,34,4),
(35,'2026-05-18','18:00','Romana','Engleza',75,35,5),
(36,'2026-05-18','20:00','Romana','Engleza',75,36,1),
(37,'2026-05-19','14:00','Romana','Engleza',80,37,2),
(38,'2026-05-19','16:00','Romana','Engleza',85,38,3),
(39,'2026-05-19','18:00','Romana','Engleza',90,39,4),
(40,'2026-05-19','20:00','Romana','Engleza',80,40,5),

(41,'2026-05-20','14:00','Engleza','Romana',100,41,1),
(42,'2026-05-20','16:00','Engleza','Romana',105,42,2),
(43,'2026-05-20','18:00','Engleza','Romana',110,43,3),
(44,'2026-05-20','20:00','Engleza','Romana',120,44,4),
(45,'2026-05-21','14:00','Engleza','Romana',120,45,5),
(46,'2026-05-21','16:00','Engleza','Romana',115,46,1),
(47,'2026-05-21','18:00','Engleza','Romana',105,47,2),
(48,'2026-05-21','20:00','Engleza','Romana',105,48,3),
(49,'2026-05-22','14:00','Engleza','Romana',110,49,4),
(50,'2026-05-22','16:00','Engleza','Romana',115,50,5);



CREATE TABLE Bilete(
    IdBilet INT PRIMARY KEY NOT NULL,
    DataCumparare DATE NOT NULL,
    PretFinal DECIMAL(6,2) NOT NULL,
    StatusBilet VARCHAR(20) NOT NULL,
    IdProiectii INT NOT NULL,
    IdLoc INT NOT NULL,
    IdClienti INT NOT NULL,

    FOREIGN KEY (IdProiectii)
    REFERENCES Proiectii(IdProiectii),

    FOREIGN KEY (IdLoc)
    REFERENCES Locuri(IdLoc),

    FOREIGN KEY (IdClienti)
    REFERENCES Clienti(IdClienti)
);

INSERT INTO Bilete VALUES
(1,'2026-05-01',120,'Cumparat',1,1,1),
(2,'2026-05-01',110,'Rezervat',2,2,2),
(3,'2026-05-02',100,'Cumparat',3,3,3),
(4,'2026-05-02',90,'Cumparat',4,4,4),
(5,'2026-05-03',130,'Rezervat',5,5,5),
(6,'2026-05-03',95,'Cumparat',6,6,6),
(7,'2026-05-04',105,'Cumparat',7,7,7),
(8,'2026-05-04',115,'Rezervat',8,8,8),
(9,'2026-05-05',125,'Cumparat',9,9,9),
(10,'2026-05-05',100,'Cumparat',10,10,10),

(11,'2026-05-06',80,'Cumparat',11,11,11),
(12,'2026-05-06',85,'Rezervat',12,12,12),
(13,'2026-05-07',135,'Cumparat',13,13,13),
(14,'2026-05-07',95,'Cumparat',14,14,14),
(15,'2026-05-08',90,'Rezervat',15,15,15),
(16,'2026-05-08',100,'Cumparat',16,16,16),
(17,'2026-05-09',120,'Cumparat',17,17,17),
(18,'2026-05-09',75,'Rezervat',18,18,18),
(19,'2026-05-10',115,'Cumparat',19,19,19),
(20,'2026-05-10',85,'Cumparat',20,20,20),

(21,'2026-05-11',100,'Rezervat',21,21,21),
(22,'2026-05-11',110,'Cumparat',22,22,22),
(23,'2026-05-12',140,'Cumparat',23,23,23),
(24,'2026-05-12',105,'Rezervat',24,24,24),
(25,'2026-05-13',95,'Cumparat',25,25,25),
(26,'2026-05-13',90,'Cumparat',26,26,26),
(27,'2026-05-14',90,'Rezervat',27,27,27),
(28,'2026-05-14',90,'Cumparat',28,28,28),
(29,'2026-05-15',95,'Cumparat',29,29,29),
(30,'2026-05-15',95,'Rezervat',30,30,30),

(31,'2026-05-16',100,'Cumparat',31,31,31),
(32,'2026-05-16',100,'Cumparat',32,32,32),
(33,'2026-05-17',100,'Rezervat',33,33,33),
(34,'2026-05-17',80,'Cumparat',34,34,34),
(35,'2026-05-18',75,'Cumparat',35,35,35),
(36,'2026-05-18',75,'Rezervat',36,36,36),
(37,'2026-05-19',80,'Cumparat',37,37,37),
(38,'2026-05-19',85,'Cumparat',38,38,38),
(39,'2026-05-20',90,'Rezervat',39,39,39),
(40,'2026-05-20',80,'Cumparat',40,40,40),

(41,'2026-05-21',100,'Cumparat',41,41,41),
(42,'2026-05-21',105,'Rezervat',42,42,42),
(43,'2026-05-22',110,'Cumparat',43,43,43),
(44,'2026-05-22',120,'Cumparat',44,44,44),
(45,'2026-05-23',120,'Rezervat',45,45,45),
(46,'2026-05-23',115,'Cumparat',46,46,46),
(47,'2026-05-24',105,'Cumparat',47,47,47),
(48,'2026-05-24',105,'Rezervat',48,48,48),
(49,'2026-05-25',110,'Cumparat',49,49,49),
(50,'2026-05-25',115,'Cumparat',50,50,50);

CREATE TABLE Angajati(
    IdAngajat INT PRIMARY KEY NOT NULL,
    Nume VARCHAR(30) NOT NULL,
    Prenume VARCHAR(30) NOT NULL,
	Email VARCHAR(50) NOT NULL,
    Telefon VARCHAR(15) NOT NULL,
    Functie VARCHAR(30) NOT NULL,
    DataAngajarii DATE NOT NULL,
    SalariuLunar DECIMAL(8,2) NOT NULL,
    IdCinematografie INT NOT NULL,

    FOREIGN KEY (IdCinematografie)
    REFERENCES Cinematografie(IdCinematografie)
);

INSERT INTO Angajati VALUES
(1,'Popescu','Ion','ion.popescu@cinema.md','069111111','Manager','2020-01-15',15000,1),
(2,'Ionescu','Maria','maria.ionescu@cinema.md','069111112','Casier','2021-02-20',8500,1),
(3,'Rusu','Andrei','andrei.rusu@cinema.md','069111113','Operator','2019-03-11',9200,1),
(4,'Munteanu','Elena','elena.munteanu@cinema.md','069111114','Curatenie','2022-04-10',6500,1),
(5,'Popa','Daniel','daniel.popa@cinema.md','069111115','Paza','2021-05-18',7800,1),
(6,'Moraru','Ana','ana.moraru@cinema.md','069111116','Manager','2018-06-12',14800,2),
(7,'Ceban','Victor','victor.ceban@cinema.md','069111117','Casier','2020-07-07',8300,2),
(8,'Dumitru','Irina','irina.dumitru@cinema.md','069111118','Operator','2019-08-14',9100,2),
(9,'Luca','Mihai','mihai.luca@cinema.md','069111119','Curatenie','2023-01-19',6400,2),
(10,'Stan','Alina','alina.stan@cinema.md','069111120','Paza','2021-09-23',7700,2),
(11,'Toma','Cristian','cristian.toma@cinema.md','069111121','Manager','2017-10-02',15200,3),
(12,'Nistor','Bianca','bianca.nistor@cinema.md','069111122','Casier','2020-11-30',8600,3),
(13,'Enache','Paul','paul.enache@cinema.md','069111123','Operator','2019-12-25',9300,3),
(14,'Voicu','Simona','simona.voicu@cinema.md','069111124','Curatenie','2022-01-05',6600,3),
(15,'Marin','George','george.marin@cinema.md','069111125','Paza','2021-02-16',7900,3),
(16,'Barbu','Raluca','raluca.barbu@cinema.md','069111126','Manager','2018-03-27',14900,4),
(17,'Ilie','Adrian','adrian.ilie@cinema.md','069111127','Casier','2020-04-09',8400,4),
(18,'Costin','Daria','daria.costin@cinema.md','069111128','Operator','2019-05-15',9150,4),
(19,'Florea','Alex','alex.florea@cinema.md','069111129','Curatenie','2022-06-20',6700,4),
(20,'Neagu','Teodora','teodora.neagu@cinema.md','069111130','Paza','2021-07-28',7850,4),
(21,'Dragomir','Vlad','vlad.dragomir@cinema.md','069111131','Manager','2017-08-03',15100,5),
(22,'Gheorghe','Ioana','ioana.gheorghe@cinema.md','069111132','Casier','2020-09-14',8550,5),
(23,'Serban','Robert','robert.serban@cinema.md','069111133','Operator','2019-10-08',9250,5),
(24,'Matei','Larisa','larisa.matei@cinema.md','069111134','Curatenie','2022-11-11',6800,5),
(25,'Anghel','Florin','florin.anghel@cinema.md','069111135','Paza','2021-12-18',7900,5),
(26,'Badea','Denisa','denisa.badea@cinema.md','069111136','Casier','2020-01-09',8450,1),
(27,'Dinu','Alexandra','alexandra.dinu@cinema.md','069111137','Operator','2019-02-17',9100,2),
(28,'Filip','Rares','rares.filip@cinema.md','069111138','Paza','2021-03-29',7800,3),
(29,'Grigore','Paula','paula.grigore@cinema.md','069111139','Curatenie','2022-04-14',6600,4),
(30,'Iacob','Stefan','stefan.iacob@cinema.md','069111140','Casier','2020-05-05',8500,5);



SELECT * FROM Cinematografie;
SELECT * FROM Sali;
SELECT * FROM Filme;
SELECT * FROM Proiectii;
SELECT * FROM Locuri;
SELECT * FROM Clienti;
SELECT * FROM Bilete;
SELECT * FROM Angajati;


DELETE FROM cinematografie;


DROP TABLE Bilete;
DROP TABLE Proiectii;
DROP TABLE Locuri;
DROP TABLE Angajati;
DROP TABLE Clienti;
DROP TABLE Filme;
DROP TABLE Sali;
DROP TABLE Cinematografie;