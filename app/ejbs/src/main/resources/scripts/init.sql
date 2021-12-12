CREATE TABLE parking_place (
   id INT PRIMARY KEY,
   section_number INT NOT NULL ,
   place_number INT NOT NULL,

   UNIQUE (section_number, place_number)
);


CREATE TABLE parking_place_state_change (
   id INT PRIMARY KEY,
   date TIMESTAMP NOT NULL,
   state VARCHAR(10) NOT NULL,
   parking_place_id INT,

   FOREIGN KEY(parking_place_id) REFERENCES parking_place(id)
);

INSERT INTO parking_place VALUES(1,1,1);
INSERT INTO parking_place VALUES(2,1,2);
INSERT INTO parking_place VALUES(3,1,3);
INSERT INTO parking_place VALUES(4,1,4);
INSERT INTO parking_place VALUES(5,2,1);
INSERT INTO parking_place VALUES(5,2,2);