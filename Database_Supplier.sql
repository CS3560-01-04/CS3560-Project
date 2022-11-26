DROP DATABASE IF EXISTS sql_supplier;
CREATE DATABASE sql_supplier;
USE sql_supplier;

CREATE TABLE supplier_info (
	supplier_id INT (6) NOT NULL AUTO_INCREMENT,
	supplier_name VARCHAR (50) NOT NULL,
	supplier_email VARCHAR (50) NOT NULL,
	supplier_phonenum VARCHAR(50) NOT NULL,
	PRIMARY KEY (supplier_id));

INSERT INTO supplier_info VALUES(1, 'CVS', 'cvs@cvs.com', '909-420-6969');
INSERT INTO supplier_info VALUES(2, 'Johnson & Johnson', 'johnsonandjohnson@johnsonandjohnson.com', '820-450-6532');
INSERT INTO supplier_info VALUES(3, 'Pfizer', 'pfizer@pfizer.com', '450-642-8330');
INSERT INTO supplier_info VALUES(4, 'Buds & Roses', 'budsandroses@budsandroses.com', '420-420-4200');
