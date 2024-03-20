-- Crear l'usuari
CREATE USER storeusr@localhost IDENTIFIED BY 'storepsw';

-- Crear la base de dades
CREATE DATABASE IF NOT EXISTS storedb;
USE storedb;

-- Assignar a l'usuari permisos sobre la base de dades
GRANT SELECT, INSERT, UPDATE, DELETE ON storedb.* TO storeusr@localhost;

-- Crear la taula Category
CREATE TABLE IF NOT EXISTS categories (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(255) UNIQUE NOT NULL
);

-- Crear la taula Product
CREATE TABLE IF NOT EXISTS products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(255) UNIQUE NOT NULL,
    price DOUBLE NOT NULL,
    stock DOUBLE NOT NULL,
    category_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- Inserir dades de prova a la taula Category
INSERT INTO categories (code) VALUES
    ('C001'),
    ('C002'),
    ('C003');

-- Inserir dades de prova a la taula Product
INSERT INTO products (code, price, stock, category_id) VALUES
    ('P001', 19.99, 100, 1),
    ('P002', 29.99, 50, 2),
    ('P003', 39.99, 75, 3);
