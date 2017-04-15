--
-- 	Script to populate the tables for the symphony database.
-- 	This script is used to insert data into the symphony database
-- 	tables under SQL.
--
-- 	Cloudscape commands:
-- 		>cloudscape -start
-- 		>cloudscape -isql < createtables.sql
-- 		>cloudscape -stop
--
-- 	$Id:	loadtables.sql, v 1.0.0.0 2001/11/12 R. Dyer

use Symphony;

DELETE FROM Movements;
DELETE FROM Movement;
DELETE FROM LastPerformed;
DELETE FROM ConcertSoloist;
DELETE FROM Soloist;
DELETE FROM Composition;
DELETE FROM Concert;
DELETE FROM Conductor;
DELETE FROM ConcertSeason;

-- INSERT INTO ConcertSeason VALUES ('01-JUN-2002');
-- INSERT INTO ConcertSeason VALUES ('01-JUN-2001');
INSERT INTO ConcertSeason VALUES ('2002-06-01');
INSERT INTO ConcertSeason VALUES ('2001-06-01');

INSERT INTO Conductor VALUES ('C1','J. Smith');
INSERT INTO Conductor VALUES ('C2','G. Strasvinski');
INSERT INTO Conductor VALUES ('C3','A. Mendelson');

-- 	Date format: dd-mm-yyyy for CS
-- 						dd-mmm-yyyy for Oracle
-- INSERT INTO Concert VALUES (1,'01-JUN-2002','C1','01-JUN-2002');
-- INSERT INTO Concert VALUES (2,'07-JUN-2002','C1','01-JUN-2002');
-- INSERT INTO Concert VALUES (3,'14-JUN-2002','C1','01-JUN-2002');
-- INSERT INTO Concert VALUES (4,'21-JUN-2002','C1','01-JUN-2002');
-- INSERT INTO Concert VALUES (5,'28-JUN-2002','C1','01-JUN-2002');
INSERT INTO Concert VALUES (1,'2002-06-01','C1','2002-06-01');
INSERT INTO Concert VALUES (2,'2002-06-07','C1','2002-06-01');
INSERT INTO Concert VALUES (3,'2002-06-14','C1','2002-06-01');
INSERT INTO Concert VALUES (4,'2002-06-21','C1','2002-06-01');
INSERT INTO Concert VALUES (5,'2002-06-28','C1','2002-06-01');

INSERT INTO Composition VALUES ('G.F. Handel','Air');
INSERT INTO Composition VALUES ('G.F. Handel','Prelude in G');
INSERT INTO Composition VALUES ('J.S. Bach','Little Prelude in A Minor');
INSERT INTO Composition VALUES ('W.A. Mozart','Adagio');
INSERT INTO Movement VALUES (1,'Sonatina No. 5');
INSERT INTO Movement VALUES (1,'Sonatina No. 5a');
INSERT INTO Movements VALUES ('W.A. Mozart','Adagio',1,'Sonatina No. 5');
INSERT INTO Movements VALUES ('W.A. Mozart','Adagio',1,'Sonatina No. 5a');

INSERT INTO Composition VALUES ('M. Clementi','Sonatina in C');
INSERT INTO Movement VALUES (1,'Op. 36, No. 3');
INSERT INTO Movement VALUES (1,'Op. 36, No. 4');
INSERT INTO Movement VALUES (1,'Op. 36, No. 5');
INSERT INTO Movements VALUES ('M. Clementi','Sonatina in C',1,'Op. 36, No. 3');
INSERT INTO Movements VALUES ('M. Clementi','Sonatina in C',1,'Op. 36, No. 4');
INSERT INTO Movements VALUES ('M. Clementi','Sonatina in C',1,'Op. 36, No. 5');

INSERT INTO Composition VALUES ('Fr. Kuhlau','Sonatina');
INSERT INTO Movement VALUES (1,'Op.88, No.1');
INSERT INTO Movements VALUES ('Fr. Kuhlau','Sonatina',1,'Op.88, No.1');
INSERT INTO Movement VALUES (2,'Op.89, No.1');
INSERT INTO Movement VALUES (3,'Op.86, No.2');
INSERT INTO Movement VALUES (4,'Op.85, No.2');
INSERT INTO Movement VALUES (5,'Op.84, No.3');
INSERT INTO Movement VALUES (6,'Op.81, No.3');
INSERT INTO Movement VALUES (7,'Op.82, No.4');
INSERT INTO Movement VALUES (9,'Op.83, No.4');
INSERT INTO Movement VALUES (10,'Op.73, No.4');
INSERT INTO Movement VALUES (11,'Op.63, No.4');
INSERT INTO Movement VALUES (12,'Op.53, No.4');


INSERT INTO Movements VALUES ('Fr. Kuhlau','Sonatina',2,'Op.89, No.1');
INSERT INTO Movements VALUES ('Fr. Kuhlau','Sonatina',3,'Op.86, No.2');
INSERT INTO Movements VALUES ('Fr. Kuhlau','Sonatina',4,'Op.85, No.2');
INSERT INTO Movements VALUES ('Fr. Kuhlau','Sonatina',5,'Op.84, No.3');
INSERT INTO Movements VALUES ('Fr. Kuhlau','Sonatina',6,'Op.81, No.3');
INSERT INTO Movements VALUES ('Fr. Kuhlau','Sonatina',7,'Op.82, No.4');
INSERT INTO Movements VALUES ('Fr. Kuhlau','Sonatina',9,'Op.83, No.4');
INSERT INTO Movements VALUES ('Fr. Kuhlau','Sonatina',10,'Op.73, No.4');
INSERT INTO Movements VALUES ('Fr. Kuhlau','Sonatina',11,'Op.63, No.4');
INSERT INTO Movements VALUES ('Fr. Kuhlau','Sonatina',12,'Op.53, No.4');



INSERT INTO ConcertCompositions VALUES (1,'G.F. Handel','Air');
INSERT INTO ConcertCompositions VALUES (1,'J.S. Bach','Little Prelude in A Minor');
INSERT INTO ConcertCompositions VALUES (1,'W.A. Mozart','Adagio');
INSERT INTO ConcertCompositions VALUES (1,'M. Clementi','Sonatina in C');

INSERT INTO Soloist VALUES ('S1','D. Becker');
INSERT INTO Soloist VALUES ('S2','J. L. Ponty');
INSERT INTO Soloist VALUES ('S3','J. Hendrix');
INSERT INTO Soloist VALUES ('S4','M. Davis');


-- INSERT INTO Lastperformed VALUES ('S4','G.F. Handel','Air','01-JUN-2002');
INSERT INTO Lastperformed VALUES ('S4','G.F. Handel','Air','2002-06-01');
INSERT INTO ConcertSoloist VALUES (1,'S4');

-- INSERT INTO Lastperformed VALUES ('S3','Fr. Kuhlau','Sonatina','28-JUN-2002');
INSERT INTO Lastperformed VALUES ('S3','Fr. Kuhlau','Sonatina','2002-06-28');
-- INSERT INTO Lastperformed VALUES ('S4','Fr. Kuhlau','Sonatina','28-JUN-2002');
INSERT INTO Lastperformed VALUES ('S4','Fr. Kuhlau','Sonatina','2002-06-28');
INSERT INTO ConcertSoloist VALUES (5,'S3');
INSERT INTO ConcertSoloist VALUES (5,'S4');
--
-- 	End of createtables.sql
--
