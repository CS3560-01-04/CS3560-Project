DROP DATABASE IF EXISTS sql_product;
CREATE DATABASE sql_product;
USE sql_product;

CREATE TABLE product_item (
	product_id INT (6) NOT NULL AUTO_INCREMENT,
    product_name VARCHAR (50) NOT NULL,
    descript VARCHAR (50),
    price DECIMAL (5,2) NOT NULL,
    quantity INT (5),
    supplier VARCHAR(50) NOT NULL,
    exp_date VARCHAR(10),
    PRIMARY KEY (product_id));
    
INSERT INTO product_item VALUES(1, 'advil', 'Pain Reliever', 5.99, 70, 'CVS', '2023-06-15');
INSERT INTO product_item VALUES(2, 'Tylenol', 'Pain Reliever', 3.99, 23, 'CVS', '2023-11-6');
INSERT INTO product_item VALUES(3, 'Sertrline', 'Increase dopemine levels', 5.99, 14, 'CVS', '2023-05-13');
INSERT INTO product_item VALUES(4, 'Lactaid', 'Help disgest dairy', 9.99, 44, 'CVS', '2023-04-24');
INSERT INTO product_item VALUES(5, 'Melatonin', 'Helps sleep more', 7.99, 30, 'CVS', '2023-12-02');