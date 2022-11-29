DROP DATABASE IF EXISTS sql_supplier;
CREATE DATABASE sql_supplier;
USE sql_supplier;

CREATE TABLE supplier_info (
	row_num INT (10) NOT NULL,
    supplier_id INT (6) NOT NULL AUTO_INCREMENT,
	supplier_name VARCHAR (50) NOT NULL,
	supplier_email VARCHAR (50) NOT NULL,
	supplier_phonenum INT(50) NOT NULL,
	PRIMARY KEY (supplier_id));

INSERT INTO supplier_info VALUES(1, 101, 'CVS', 'cvs@cvs.com', '9094206969');
INSERT INTO supplier_info VALUES(2, 102, 'Johnson & Johnson', 'johnson&johnson@johnson&johnson.com', '8204506532');
INSERT INTO supplier_info VALUES(3, 103, 'Pfizer', 'pfizer@pfizer.com', '4506428330');
INSERT INTO supplier_info VALUES(4, 104, 'Buds & Roses', 'buds&roses@buds&roses.com', '4204204200');
INSERT INTO supplier_info VALUES(5, 105, 'Endo International', 'endointernational@endointernational.com', '9513575623');
INSERT INTO supplier_info VALUES(6, 106, 'AbbVie Inc.', 'abbvie@abbvie.com', '3571256547');
INSERT INTO supplier_info VALUES(7, 107, 'Hi-Tech Pharmaceuticals Inc.', 'hitechpharmaceuticals@hitechpharmaceuticals.com', '6598564253');
