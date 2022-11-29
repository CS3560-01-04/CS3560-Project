DROP DATABASE IF EXISTS sql_product;
CREATE DATABASE sql_product;
USE sql_product;

CREATE TABLE product_item (
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
    
INSERT INTO product_item VALUES(1, 2395, 'Advil', 'Pain Reliever', 5.99, 70, 'Pfizer', '2023', '6', '15');
INSERT INTO product_item VALUES(2, 9173, 'Tylenol', 'Pain Reliever', 3.99, 23, 'Johnson & Johnson', '2023', '11', '6');
INSERT INTO product_item VALUES(3, 0513, 'Sertraline', 'Increase dopemine levels', 5.99, 14, 'Pfizer', '2023', '5', '13');
INSERT INTO product_item VALUES(4, 7239, 'Lactaid', 'Help disgest dairy', 9.99, 44, 'Johnson & Johnson', '2023', '4', '24');
INSERT INTO product_item VALUES(5, 6439, 'Melatonin', 'Helps sleep more', 7.99, 30, 'CVS', '2023', '12', '2');
INSERT INTO product_item VALUES(6, 8566, 'Hormone Blockers', 'Blocking nartural hormone production', 19.99, 50, 'Endo International', '2023', '11', '26');
INSERT INTO product_item VALUES(7, 0112, 'Anti-Depressants', 'Helps with depression', 15.99, 120, 'AbbVie Inc.', '2025', '2', '14');
INSERT INTO product_item VALUES(8, 3648, 'Codeine Cough Syrup', 'Helps soothe a cough or sore throat', 8.49, 43, 'Hi-Tech Pharmaceuticals Inc.', '2024', '3', '20');
INSERT INTO product_item VALUES(9, 7452, 'Medicinal Cannabis', 'Used to treat forms of nausea and epilepsy', 14.99, 420, 'Buds & Roses', '2024', '5', '9');
INSERT INTO product_item VALUES(10, 1256, 'Viagra', 'Helps to achieve and maintain an erection', 149.99, 23, 'Pfizer', '2024', '12', '2');
