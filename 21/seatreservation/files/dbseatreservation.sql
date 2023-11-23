CREATE USER 'usrseatreservation'@'localhost' IDENTIFIED BY 'pswseatreservation';
CREATE DATABASE dbseatreservation
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;
GRANT SELECT, INSERT, UPDATE, DELETE ON dbseatreservation.* TO 'usrseatreservation'@'localhost';
USE dbseatreservation;
CREATE TABLE `seats` (
`id` INT(4) NOT NULL AUTO_INCREMENT,
`name` VARCHAR(40) NOT NULL UNIQUE,
PRIMARY KEY (`id`)
);
CREATE TABLE `reservations` (
`id` INT(4) NOT NULL AUTO_INCREMENT,
`name` VARCHAR(40) NOT NULL,
PRIMARY KEY (`id`)
);
CREATE TABLE `reservationseat` (
`reservation_id` INT(4) NOT NULL,
`seat_id` INT(4) NOT NULL,
PRIMARY KEY (`reservation_id`, `seat_id`)
);
ALTER TABLE reservationseat ADD FOREIGN KEY fk_reservation (reservation_id) REFERENCES reservations(id);
ALTER TABLE reservationseat ADD FOREIGN KEY fk_seat (seat_id) REFERENCES seats(id);
INSERT INTO `seats` (`id`, `name`) 
VALUES
(1, 'name01'),
(2, 'name02'),
(3, 'name03'),
(4, 'name04'),
(5, 'name05'),
(6, 'name06'),
(7, 'name07'),
(8, 'name08');
