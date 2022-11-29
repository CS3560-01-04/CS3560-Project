DROP DATABASE IF EXISTS sql_supplier;
CREATE DATABASE sql_supplier;
USE sql_supplier;

CREATE TABLE supplier_info (
	supplier_id INT (6) NOT NULL AUTO_INCREMENT,
    row_num INT (10) NOT NULL,
	supplier_name VARCHAR (50) NOT NULL,
	supplier_email VARCHAR (50) NOT NULL,
	supplier_phonenum VARCHAR(50) NOT NULL,
	PRIMARY KEY (supplier_id));

INSERT INTO supplier_info VALUES(1, 101, 'CVS', 'cvs@cvs.com', '909-420-6969');
INSERT INTO supplier_info VALUES(2, 102, 'Johnson & Johnson', 'johnson&johnson@johnson&johnson.com', '820-450-6532');
INSERT INTO supplier_info VALUES(3, 103, 'Pfizer', 'pfizer@pfizer.com', '450-642-8330');
INSERT INTO supplier_info VALUES(4, 104, 'Buds & Roses', 'buds&roses@buds&roses.com', '420-420-4200');
INSERT INTO supplier_info VALUES(5, 105, 'Endo International', 'endointernational@endointernational.com', '951-357-5623');
INSERT INTO supplier_info VALUES(6, 106, 'AbbVie Inc.', 'abbvie@abbvie.com', '357-125-6547');
INSERT INTO supplier_info VALUES(7, 107, 'Hi-Tech Pharmaceuticals Inc.', 'hitechpharmaceuticals@hitechpharmaceuticals.com', '659-856-4253');