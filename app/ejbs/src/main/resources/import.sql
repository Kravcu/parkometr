INSERT INTO parking_place(id,section_number, place_number, current_state, street) VALUES(101,1,1,'FREE','Friedleina'),(102,1,2,'FREE','Friedleina'),(103,1,3,'FREE','Friedleina'),(104,1,4,'FREE','Friedleina'),(105,1,5,'FREE','Friedleina'),(106,2,1,'FREE','Friedleina'),(107,2,2,'FREE','Friedleina'),(108,2,3,'FREE','Friedleina'),(109,2,4,'FREE','Friedleina'),(110,2,5,'FREE','Friedleina'),(111,3,1,'FREE','Friedleina'),(112,3,2,'FREE','Friedleina'),(113,3,3,'FREE','Friedleina'),(114,3,4,'FREE','Friedleina'),(115,3,5,'FREE','Friedleina'),(116,4,1,'FREE','Friedleina'),(117,4,2,'FREE','Friedleina'),(118,4,3,'FREE','Friedleina'),(119,4,4,'FREE','Friedleina'),(120,4,5,'FREE','Friedleina');
INSERT INTO ticket(id, parking_place_id, purchase, expiration, registerPlate) VALUES (100,1, NOW(), NOW() + INTERVAL '1 hour', 'AXC123');

INSERT INTO users(id,login, password,role, section_number) VALUES (100, 'rwalski', 'hCAyas+OACOnNgOGI0knrw==', 'WORKER',1);
INSERT INTO users(id, login, password,role) VALUES (101, 'admin', 'hCAyas+OACOnNgOGI0knrw==', 'ADMIN');