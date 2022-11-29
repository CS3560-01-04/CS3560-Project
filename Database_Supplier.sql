DROP DATABASE IF EXISTS sql_supplier;
CREATE DATABASE sql_supplier;
USE sql_supplier;

CREATE TABLE supplier (
	row_num INT (10) NOT NULL,
	supplier_id INT (6) NOT NULL AUTO_INCREMENT,
	supplier_name VARCHAR (50) NOT NULL,
	supplier_email VARCHAR (50) NOT NULL,
	supplier_phonenum VARCHAR(50) NOT NULL,
	PRIMARY KEY (supplier_id));

INSERT INTO supplier VALUES(1, 101, 'CVS', 'cvs@cvs.com', '909-420-6969');
INSERT INTO supplier VALUES(2, 102, 'Johnson & Johnson', 'johnsonandjohnson@johnsonandjohnson.com', '820-450-6532');
INSERT INTO supplier VALUES(3, 103, 'Pfizer', 'pfizer@pfizer.com', '450-642-8330');
INSERT INTO supplier VALUES(4, 104, 'Buds & Roses', 'budsandroses@budsandroses.com', '420-420-4200');
