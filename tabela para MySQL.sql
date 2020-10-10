CREATE DATABASE `springDB`;

USE springDB;

CREATE TABLE `Emp99` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `salary` int(11) DEFAULT NULL,
  `designation` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

SET GLOBAL time_zone = '-3:00';