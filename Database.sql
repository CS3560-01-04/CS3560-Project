CREATE DATABASE Inventory;
USE Inventory; 
CREATE TABLE `Product` (
  `ProductID` int(11) NOT NULL AUTO_INCREMENT,
  `ProductName` varchar(50) NOT NULL,
  `Description`varchar(80),
  `InStock` int(11) NOT NULL,
  `Price` decimal(4,2) NOT NULL,
  `Supplier` varchar(45),
  `DateExp`varchar(45),
  PRIMARY KEY (`ProductId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO Product VALUES ('1','advil','Pain Reliever','70','5.99','CVS','07/23');
INSERT INTO Product VALUES ('2','Tylenol','Pain Reliever','23','3.99','CVS','05/23');
INSERT INTO Product VALUES ('3','Sertraline','Increase dopemine levels','14','5.99','CVS','n/a');
INSERT INTO Product VALUES ('4','Lactaid','Help digest dairy','44','9.99','CVS','N/A');
INSERT INTO Product VALUES ('5','Melatonin','Helps sleep more','30','7.99','CVS','07/23');
CREATE TABLE `CustomerInfo` (
  `CustomerId` int(11) NOT NULL AUTO_INCREMENT,
  `FrstLstName` varchar(80) NOT NULL,
  `Phonenum` int(11) NOT NULL,
  `Email` varchar(80) NOT NULL,
  PRIMARY KEY (`CustomerId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
USE Inventory; 
INSERT INTO CustomerInfo VALUES ('1','Emily Harris','1234567890','mockmail@gmail.com');
INSERT INTO CustomerInfo VALUES ('2','Johannes Wenstworth','1234567890','mockmail@gmail.com');
INSERT INTO CustomerInfo VALUES ('3','Marlene Joanes','1234567890','mockmail@gmail.com');
INSERT INTO CustomerInfo VALUES ('4','Durk Wellington','1234567890','mockmail@gmail.com');
INSERT INTO CustomerInfo VALUES ('5','Tina Waltz','1234567890','mockmail@gmail.com');

