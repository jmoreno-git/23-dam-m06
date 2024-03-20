CREATE USER 'usrleasure'@'localhost' IDENTIFIED BY 'pswleasure';
CREATE DATABASE dbleasure
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;
GRANT SELECT, INSERT, UPDATE, DELETE ON dbleasure.* TO 'usrleasure'@'localhost';
USE dbleasure;
CREATE TABLE `activities` (
`id` INT(4) NOT NULL AUTO_INCREMENT,
`name` VARCHAR(10) NOT NULL UNIQUE,
`capacity` INT DEFAULT 0,
PRIMARY KEY (`id`)
);
CREATE TABLE `users` (
`id` INT(4) NOT NULL AUTO_INCREMENT,
`name` VARCHAR(10) NOT NULL UNIQUE,
`age` INT DEFAULT 0,
PRIMARY KEY (`id`)
);
CREATE TABLE `activitiesusers` (
`activity_id` INT(4) NOT NULL,
`user_id`  INT(4) NOT NULL,
PRIMARY KEY (`activity_id`, `user_id`)
);
ALTER TABLE `activitiesusers` ADD FOREIGN KEY (activity_id) REFERENCES activities(id)
    ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `activitiesusers` ADD FOREIGN KEY (user_id) REFERENCES users(id)
    ON DELETE CASCADE ON UPDATE CASCADE;
INSERT INTO activities VALUES 
  (1, "activ01", 11),
  (2, "activ02", 12),
  (3, "activ03", 13),
  (4, "activ04", 14),
  (5, "activ05", 15),
  (6, "activ06", 16),
  (7, "activ07", 17),
  (8, "activ08", 18),
  (9, "activ09", 19),
  (10, "activ10", 20);
INSERT INTO users VALUES 
  (1, "user01", 21),
  (2, "user02", 22),
  (3, "user03", 23),
  (4, "user04", 24),
  (5, "user05", 25),
  (6, "user06", 26),
  (7, "user07", 27),
  (8, "user08", 28),
  (9, "user09", 29),
  (10, "user10", 30);
INSERT INTO activitiesusers VALUES
  (1, 1),
  (1, 2),
  (1, 3),
  (1, 4),
  (1, 5),
  (1, 6),
  (1, 7),
  (2, 1),
  (2, 2),
  (2, 4),
  (3, 5),
  (3, 6),
  (3, 7),
  (4, 8),
  (5, 9);