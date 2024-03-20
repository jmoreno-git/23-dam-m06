CREATE USER 'warehouseusr'@'localhost' IDENTIFIED BY 'warehousepsw';
CREATE DATABASE warehousedb
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;
GRANT SELECT, INSERT, UPDATE, DELETE ON warehousedb.* TO 'warehouseusr'@'localhost';
USE warehousedb;
CREATE TABLE `products` (
    `id` INT(4) NOT NULL AUTO_INCREMENT,
    `code` VARCHAR(10) NOT NULL UNIQUE,
    `name` VARCHAR(20) NOT NULL,
    `price` DOUBLE DEFAULT 0.0,
    `container_id` INT(4),
    PRIMARY KEY (`id`)
);
ALTER TABLE `products` 
    ADD CONSTRAINT `fk_container` FOREIGN KEY (container_id) 
    REFERENCES products(id)
    ON UPDATE CASCADE ON DELETE RESTRICT;
INSERT INTO products VALUES 
    (1, "P01", "product01", 1001.0, null),
    (2, "P02", "product02", 1002.0, null),
    (3, "P03", "product03", 1003.0, null),
    (4, "P04", "product04", 1004.0, null),
    (5, "P05", "product05", 1005.0, 1),
    (6, "P06", "product06", 1006.0, 1),
    (7, "P07", "product07", 1007.0, 2),
    (8, "P08", "product08", 1008.0, 2),
    (9, "P09", "product09", 1009.0, 3);