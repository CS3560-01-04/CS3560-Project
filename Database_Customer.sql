DROP DATABASE IF EXISTS sql_customer;
CREATE DATABASE sql_customer;
USE sql_customer;

CREATE TABLE customer (
	row_num INT (10) NOT NULL,
    customer_id INT (6) NOT NULL AUTO_INCREMENT,
	first_name VARCHAR (50) NOT NULL,
	last_name VARCHAR (50) NOT NULL,
	customer_email VARCHAR (50) NOT NULL,
	customer_phonenum VARCHAR (20) NOT NULL,
	prescription VARCHAR (50),
	PRIMARY KEY (customer_id));

INSERT INTO customer VALUES(1, 101, 'Brandon', 'Leho', 'brandleho@gmail.com', '696-969-6969', 'Hormone Blockers');
INSERT INTO customer VALUES(2, 102, 'Manji', 'Hui', 'thetruehui@outlook.com', '420-696-4200', 'Anti-Depressants');
INSERT INTO customer VALUES(3, 103, 'Brandon', 'Lequang', 'alphabrandon@protonmail.com', '690-420-6969', 'Codeine Cough Syrup');
INSERT INTO customer VALUES(4, 104, 'Jonathan', 'Marquez', 'jj@yahoo.com', '969-024-9696', 'Medicinal Cannabis');
INSERT INTO customer VALUES(5, 105, 'Miguel', 'Sierra', 'miggiesmalls@hotmail.com', '420-420-6969', 'Viagra');
