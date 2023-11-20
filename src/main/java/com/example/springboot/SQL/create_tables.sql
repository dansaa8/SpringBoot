DROP DATABASE IF EXISTS geo_POI;
CREATE DATABASE geo_POI;
USE geo_POI;

CREATE TABLE category (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE,
    symbol VARCHAR(255),
    description VARCHAR(255)
);

CREATE TABLE location (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE,
    status ENUM('private', 'public'),
    coordinate GEOMETRY NOT NULL SRID 4326,
    description VARCHAR(255),
    created_at DATETIME,
    last_modified DATETIME,
    fk_category INT,
    CONSTRAINT FOREIGN KEY (fk_category) REFERENCES category(id)
);