CREATE DATABASE IF NOT EXISTS ordenes;

USE ordenes;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------

-- Table structure for clientes

-- ----------------------------

DROP TABLE IF EXISTS `clientes`;

CREATE TABLE `clientes` (

  `id` int(11) NOT NULL AUTO_INCREMENT,

  `nombre` varchar(255) DEFAULT NULL,

  `telefono` int(11) DEFAULT NULL,

  `direccion` varchar(255) DEFAULT NULL,

  `run` varchar(255) DEFAULT NULL,

  `fecha_cliente` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp(),

  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- ----------------------------

-- Records of clientes

-- ----------------------------



-- ----------------------------

-- Table structure for compras

-- ----------------------------

DROP TABLE IF EXISTS `compras`;

CREATE TABLE `compras` (

  `id` int(11) NOT NULL AUTO_INCREMENT,

  `numero` int(11) DEFAULT NULL,

  `year` int(11) DEFAULT NULL,

  `id_clientes` int(11) DEFAULT NULL,

  `monto` double DEFAULT NULL,

  `fecha_compra` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp(),

  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- ----------------------------

-- Records of compras

-- ----------------------------



-- ----------------------------

-- Table structure for productos_base

-- ----------------------------

DROP TABLE IF EXISTS `productos_base`;

CREATE TABLE `productos_base` (

  `id` int(11) NOT NULL AUTO_INCREMENT,

  `nombre` varchar(255) DEFAULT NULL,

  `precio` int(11) DEFAULT NULL,

  `stock` int(11) DEFAULT NULL,

  PRIMARY KEY (`id`)

) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;



-- ----------------------------

-- Records of productos_base

-- ----------------------------

INSERT INTO `productos_base` VALUES ('1', 'Resma de hoja de carta ', '5500', '20');

INSERT INTO `productos_base` VALUES ('2', 'Resma de hoja de oficio', '6000', '10');

INSERT INTO `productos_base` VALUES ('3', 'lapiz grafito', '200', '100');

INSERT INTO `productos_base` VALUES ('4', 'Audifonos ', '5000', '20');

INSERT INTO `productos_base` VALUES ('5', 'Corchetera', '7000', '30');

INSERT INTO `productos_base` VALUES ('6', 'Resma de opalina', '6300', '10');

INSERT INTO `productos_base` VALUES ('7', 'Perforadora ', '8900', '6');

INSERT INTO `productos_base` VALUES ('8', 'Caja de clip', '3500', '10');

INSERT INTO `productos_base` VALUES ('9', 'Caja de lapices de colores', '700', '50');

INSERT INTO `productos_base` VALUES ('10', 'Plumon pizarra', '500', '120');

INSERT INTO `productos_base` VALUES ('11', 'Mouse', '4500', '30');

INSERT INTO `productos_base` VALUES ('12', 'Agenda', '3000', '20');



-- ----------------------------

-- Table structure for productos_compras

-- ----------------------------

DROP TABLE IF EXISTS `productos_compras`;

CREATE TABLE `productos_compras` (

  `id` int(11) NOT NULL AUTO_INCREMENT,

  `nombre` varchar(255) DEFAULT NULL,

  `precio` double DEFAULT NULL,

  `cantidad` double DEFAULT NULL,

  `total` double DEFAULT NULL,

  `id_compras` int(11) DEFAULT NULL,

  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- ----------------------------

-- Records of productos_compras

-- ----------------------------



-- ----------------------------

-- Table structure for usuarios

-- ----------------------------

DROP TABLE IF EXISTS `usuarios`;

CREATE TABLE `usuarios` (

  `id` int(11) NOT NULL AUTO_INCREMENT,

  `usuario` varchar(255) DEFAULT NULL,

  `password` varchar(255) DEFAULT NULL,

  `fecha` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp(),

  PRIMARY KEY (`id`)

) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;



-- ----------------------------

-- Records of usuarios

-- ----------------------------

INSERT INTO `usuarios` VALUES ('1', 'admin', '1234', '2022-11-15 14:06:00');

SET FOREIGN_KEY_CHECKS=1;

