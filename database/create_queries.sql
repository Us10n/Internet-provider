CREATE DATABASE IF NOT EXISTS internetProvider;

USE internetProvider;

CREATE TABLE IF NOT EXISTS Users
(
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
    role ENUM('ADMIN','USER') NOT NULL,
    password VARCHAR(20) NOT NULL,
    email VARCHAR(25) NOT NULL UNIQUE,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    status ENUM("UNVERIFIED", "VERIFIED", "BLOCKED", "BANNED") NOT NULL DEFAULT 'Unverified',
    token VARCHAR(16) NOT NULL UNIQUE,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS SpecialOffers
(
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
	title VARCHAR(45) NOT NULL UNIQUE,
    description TEXT(2048) NOT NULL,
    start_date DATE NOT NULL,
    expiration_date DATE NOT NULL,
    discount TINYINT(3) NOT NULL,
    image_url VARCHAR(45) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Tariffs
(
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
    special_offers_id BIGINT UNSIGNED,
	name VARCHAR(15) NOT NULL UNIQUE,
    description TEXT(2048) NOT NULL,
    status ENUM("ACTIVE","ARCHIEVE", "DEACTIVATED") NOT NULL,
	internet_speed DECIMAL(10,2) NOT NULL,
    rating DECIMAL(10,1) NOT NULL,
    image_url VARCHAR(45) NOT NULL,
    balance DECIMAL(10,3) NOT NULL,
	PRIMARY KEY (id),
    FOREIGN KEY (special_offers_id) REFERENCES SpecialOffers (id)
);

CREATE TABLE IF NOT EXISTS BankAccounts
(
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
    users_id BIGINT UNSIGNED NOT NULL,
    tariffs_id BIGINT UNSIGNED,
	balance DECIMAL(10,3) NOT NULL,
	PRIMARY KEY (id),
    FOREIGN KEY (users_id) REFERENCES Users (id),
    FOREIGN KEY (tariffs_id) REFERENCES Tariffs (id)
);

CREATE TABLE IF NOT EXISTS Feedbacks
(
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
    users_id BIGINT UNSIGNED NOT NULL,
    tariffs_id BIGINT UNSIGNED NOT NULL,
	rating INT(10) NOT NULL,
	body TEXT(120) NOT NULL,
	PRIMARY KEY (id),
    FOREIGN KEY (users_id) REFERENCES Users (id),
    FOREIGN KEY (tariffs_id) REFERENCES Tariffs (id)
);
