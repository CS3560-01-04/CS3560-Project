DROP DATABASE IF EXISTS sql_product;
CREATE DATABASE sql_product;
USE sql_product;

CREATE TABLE product (
	row_num INT (10) NOT NULL,
	product_id INT (6) NOT NULL AUTO_INCREMENT,
    	product_name VARCHAR (50) NOT NULL,
  	descript VARCHAR (50),
    	price DECIMAL (5,2) NOT NULL,
    	quantity INT (5),
    	supplier VARCHAR(50) NOT NULL,
    	exp_year VARCHAR(4) NOT NULL,
    	exp_month VARCHAR(2) NOT NULL,
    	exp_day VARCHAR(2) NOT NULL,
    	PRIMARY KEY (product_id));
    
INSERT INTO product VALUES(1, 2395, 'advil', 'Pain Reliever', 5.99, 70, 'CVS', '2023', '6', '15');
INSERT INTO product VALUES(2, 9173, 'Tylenol', 'Pain Reliever', 3.99, 23, 'CVS', '2023', '11', '6');
INSERT INTO product VALUES(3, 0513, 'Sertrline', 'Increase dopemine levels', 5.99, 14, 'CVS', '2023', '5', '13');
INSERT INTO product VALUES(4, 7239, 'Lactaid', 'Help disgest dairy', 9.99, 44, 'CVS', '2023', '4', '24');
INSERT INTO product VALUES(5, 6439, 'Melatonin', 'Helps sleep more', 7.99, 30, 'CVS', '2023', '12', '2');
