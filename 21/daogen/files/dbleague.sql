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
CREATE TABLE `players` (
`id` INT(4) NOT NULL AUTO_INCREMENT,
`name` VARCHAR(10) NOT NULL UNIQUE,
`salary` DOUBLE DEFAULT 0.0,
`team_id` INT(4) NULL,
PRIMARY KEY (`id`)
);
ALTER TABLE players 
    ADD FOREIGN KEY fk_team (team_id) 
    REFERENCES team(id) 
    ON UPDATE CASCADE ON DELETE RESTRICT;
INSERT INTO teams VALUES 
  (1, "team01", "coach01", "cat01", 1000001.0),
  (2, "team02", "coach02", "cat02", 1000002.0),
  (3, "team03", "coach03", "cat03", 1000003.0),
  (4, "team04", "coach04", "cat04", 1000004.0),
  (5, "team05", "coach05", "cat05", 1000005.0),
  (6, "team06", "coach06", "cat01", 1000006.0),
  (7, "team07", "coach07", "cat02", 1000007.0),
  (8, "team08", "coach08", "cat03", 1000008.0),
  (9, "team09", "coach09", "cat04", 1000009.0),
  (10, "team10", "coach10", "cat05",1000010.0);
INSERT INTO players VALUES 
    (1, "player01", 10001.0, 1),
    (2, "player02", 10002.0, 1),
    (3, "player03", 10003.0, 2),
    (4, "player04", 10004.0, 2),
    (5, "player05", 10005.0, 3),
    (6, "player06", 10006.0, 3),
    (7, "player07", 10007.0, 4),
    (8, "player08", 10008.0, 4),
    (9, "player09", 10009.0, 5),
    (10, "player10", 10010.0, 6),
    (11, "player11", 10011.0, 1),
    (12, "player12", 10012.0, 1),
    (13, "player13", 10013.0, NULL);