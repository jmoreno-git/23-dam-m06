CREATE USER storeusr@localhost IDENTIFIED BY 'storepsw';
 
CREATE DATABASE storedb
    DEFAULT CHARACTER SET utf8
    DEFAULT COLLATE utf8_general_ci;
 
GRANT ALL PRIVILEGES ON storedb.* TO storeusr@localhost;
 
USE storedb;
 
CREATE TABLE categories (
    code VARCHAR(10) NOT NULL,
    name VARCHAR(40) NOT NULL,
    PRIMARY KEY (code)
) ENGINE=InnoDB;

CREATE TABLE products (
    id INT(4) NOT NULL AUTO_INCREMENT,
    code VARCHAR(10) NOT NULL UNIQUE,
    description VARCHAR(40) NOT NULL,
    price DOUBLE DEFAULT 0.0,
    category_code VARCHAR(10) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

ALTER TABLE products ADD FOREIGN KEY (category_code) REFERENCES categories(code) ON DELETE NO ACTION ON UPDATE CASCADE;

INSERT INTO categories (code, name) VALUES
    ('c01', 'c01_desc'),
    ('c02', 'c02_desc'),
    ('c03', 'c03_desc'),
    ('c04', 'c04_desc'),
    ('c05', 'c05_desc'),
    ('c06', 'c06_desc');
    
INSERT INTO products (code, description, price, category_code) VALUES 
    ('a01', 'a01_desc', 101, 'c01'),
    ('a02', 'a02_desc', 102, 'c01'),
    ('a03', 'a03_desc', 103, 'c01'),
    ('a04', 'a04_desc', 104, 'c02'),
    ('a05', 'a05_desc', 105, 'c02'),
    ('a06', 'a06_desc', 106, 'c03'),
    ('a07', 'a07_desc', 107, 'c04'),
    ('a08', 'a08_desc', 108, 'c04'),
    ('a09', 'a09_desc', 109, 'c05'),
    ('a10', 'a10_desc', 110, 'c05');