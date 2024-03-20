CREATE USER 'usrleague'@'localhost' IDENTIFIED BY 'pswleague';
CREATE DATABASE dbleague
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;
GRANT SELECT, INSERT, UPDATE, DELETE ON dbleague.* TO 'usrleague'@'localhost';
USE dbleague;
CREATE TABLE `teams` (
`id` INT(4) NOT NULL AUTO_INCREMENT,
`name` VARCHAR(10) NOT NULL UNIQUE,
`coach` VARCHAR(10) NOT NULL,
`category` VARCHAR(10) NOT NULL,
`budget` DOUBLE DEFAULT 0.0,
PRIMARY KEY (`id`)
);
INSERT INTO teams VALUES 
  (1, "team01", "coach01", "cat01", 1001.0),
  (2, "team02", "coach02", "cat02", 1002.0),
  (3, "team03", "coach03", "cat03", 1003.0),
  (4, "team04", "coach04", "cat04", 1004.0),
  (5, "team05", "coach05", "cat05", 1005.0),
  (6, "team06", "coach06", "cat01", 1006.0),
  (7, "team07", "coach07", "cat02", 1007.0),
  (8, "team08", "coach08", "cat03", 1008.0),
  (9, "team09", "coach09", "cat04", 1009.0),
  (10, "team10", "coach10", "cat05", 1010.0);