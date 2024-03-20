CREATE USER companyusr@localhost IDENTIFIED BY 'companypsw';

CREATE DATABASE IF NOT EXISTS companydb
    DEFAULT CHARACTER SET utf8
    DEFAULT COLLATE utf8_general_ci;

GRANT ALL PRIVILEGES ON companydb.* TO companyusr@localhost;

USE companydb;

CREATE TABLE IF NOT EXISTS companies (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nif VARCHAR(9) UNIQUE NOT NULL,
    name VARCHAR(50) UNIQUE NOT NULL,
    foundation_year int(4) NOT NULL
);

CREATE TABLE IF NOT EXISTS employees(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    salary DOUBLE NOT NULL,
    home_working BOOLEAN DEFAULT false,
    company_id BIGINT NOT NULL
);

ALTER TABLE employees 
    ADD FOREIGN KEY (company_id) REFERENCES companies(id)
    ON UPDATE CASCADE ON DELETE CASCADE;

INSERT INTO companies (nif, name, foundation_year) VALUES
    ('123456789', 'Company1', 2000),
    ('987654321', 'Company2', 1995),
    ('111222333', 'Company3', 2010),
    ('444555666', 'Company4', 1985),
    ('777888999', 'Company5', 2005),
    ('555444333', 'Company6', 1990),
    ('999888777', 'Company7', 2015),
    ('666555444', 'Company8', 1975),
    ('222333444', 'Company9', 2002),
    ('888999000', 'Company10', 1980);

INSERT INTO employees (username, salary, home_working, company_id) VALUES
    ('Employee1', 50000, TRUE, 1),
    ('Employee2', 60000, FALSE, 2),
    ('Employee3', 70000, TRUE, 3),
    ('Employee4', 55000, FALSE, 4),
    ('Employee5', 80000, TRUE, 5),
    ('Employee6', 45000, FALSE, 1),
    ('Employee7', 75000, TRUE, 2),
    ('Employee8', 90000, FALSE, 1),
    ('Employee9', 65000, TRUE, 9),
    ('Employee10', 70000, FALSE, 10);