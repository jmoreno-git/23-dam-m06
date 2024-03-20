CREATE USER 'flightssusr'@'localhost' IDENTIFIED BY 'flightspwd';
CREATE DATABASE flightsdb
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;
GRANT SELECT, INSERT, UPDATE, DELETE ON flightsdb.* TO 'flightssusr'@'localhost';
USE flightsdb;
CREATE TABLE `flights` (
`id` INT(4) NOT NULL AUTO_INCREMENT,
`code` VARCHAR(10) NOT NULL UNIQUE,
`capacity` INT(2) DEFAULT 0,
`date` DATE NOT NULL,
`time` TIME NOT NULL,
PRIMARY KEY (`id`)
);
CREATE TABLE `passengers` (
`id` INT(4) NOT NULL AUTO_INCREMENT,
`phone` VARCHAR(15) NOT NULL UNIQUE,
`name` VARCHAR(50) NOT NULL,
`minor` BOOLEAN DEFAULT true,
PRIMARY KEY (`id`)
);
CREATE TABLE `flightspassengers` (
`flight_id` INT(4) NOT NULL,
`passenger_id` INT(4) NOT NULL,
PRIMARY KEY (`flight_id`, `passenger_id`)
);
ALTER TABLE flightspassengers ADD FOREIGN KEY fk_flight (flight_id) REFERENCES flights(id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE flightspassengers ADD FOREIGN KEY fk_passenger (passenger_id) REFERENCES passengers(id) ON UPDATE CASCADE ON DELETE RESTRICT;
INSERT INTO flights VALUES 
    (1, 'flight01', 101, '2023-01-01', '01:01:01'),
    (2, 'flight02', 102, '2023-01-02', '01:01:02'),
    (3, 'flight03', 103, '2023-01-03', '01:01:03'),
    (4, 'flight04', 104, '2023-01-04', '01:01:04'),
    (5, 'flight05', 105, '2023-01-05', '01:01:05'),
    (6, 'flight06', 106, '2023-01-06', '01:01:06'),
    (7, 'flight07', 107, '2023-01-07', '01:01:07'),
    (8, 'flight08', 108, '2023-01-08', '01:01:08'),
    (9, 'flight09', 109, '2023-01-09', '01:01:09');
INSERT INTO passengers VALUES
    (1, '555555001', 'name01', false),
    (2, '555555002', 'name02', false),
    (3, '555555003', 'name03', true),
    (4, '555555004', 'name04', false),
    (5, '555555005', 'name05', true),
    (6, '555555006', 'name06', false),
    (7, '555555007', 'name07', false),
    (8, '555555008', 'name08', true),
    (9, '555555009', 'name09', false);
INSERT INTO flightspassengers VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (1, 5),
    (2, 2),
    (2, 4),
    (2, 6),
    (3, 1),
    (3, 3),
    (3, 5),
    (4, 6),
    (4, 7),
    (4, 8),
    (5, 7);
