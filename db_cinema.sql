CREATE DATABASE  IF NOT EXISTS `bd_cinema` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bd_cinema`;
-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bd_cinema
-- ------------------------------------------------------
-- Server version	8.0.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `asiento_funcion`
--

DROP TABLE IF EXISTS `asiento_funcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asiento_funcion` (
  `funcion_sala_cinema_id` int NOT NULL,
  `funcion_sala_numero` int NOT NULL,
  `funcion_fecha` timestamp NOT NULL,
  `fila` varchar(1) COLLATE utf8mb4_spanish_ci NOT NULL,
  `posicion` int NOT NULL,
  `ocupado` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`funcion_sala_cinema_id`,`funcion_sala_numero`,`funcion_fecha`,`fila`,`posicion`),
  CONSTRAINT `fk_asiento_funcion` FOREIGN KEY (`funcion_sala_cinema_id`, `funcion_sala_numero`, `funcion_fecha`) REFERENCES `funcion` (`sala_cinema_id`, `sala_numero`, `fecha`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `asiento_sala`
--

DROP TABLE IF EXISTS `asiento_sala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asiento_sala` (
  `sala_cinema_id` int NOT NULL,
  `sala_numero` int NOT NULL,
  `fila` varchar(1) COLLATE utf8mb4_spanish_ci NOT NULL,
  `posicion` int NOT NULL,
  `disponible` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`sala_cinema_id`,`sala_numero`,`fila`,`posicion`),
  CONSTRAINT `fk_asiento_sala` FOREIGN KEY (`sala_cinema_id`, `sala_numero`) REFERENCES `sala` (`cinema_id`, `numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asiento_sala`
--

LOCK TABLES `asiento_sala` WRITE;
/*!40000 ALTER TABLE `asiento_sala` DISABLE KEYS */;
INSERT INTO `asiento_sala` VALUES (1,1,'A',1,1),(1,1,'A',2,1),(1,1,'A',3,1),(1,1,'A',4,1),(1,1,'A',5,1),(1,1,'A',6,1),(1,1,'A',7,1),(1,1,'A',8,1),(1,1,'A',9,1),(1,1,'A',10,1),(1,1,'A',11,1),(1,1,'A',12,1),(1,1,'A',13,1),(1,1,'A',14,1),(1,1,'B',15,1),(1,1,'B',16,1),(1,1,'B',17,1),(1,1,'B',18,1),(1,1,'B',19,1),(1,1,'B',20,1),(1,1,'B',21,1),(1,1,'B',22,1),(1,1,'B',23,1),(1,1,'B',24,1),(1,1,'B',25,1),(1,1,'B',26,1),(1,1,'B',27,1),(1,1,'B',28,1),(1,1,'C',29,1),(1,1,'C',30,1),(1,1,'C',31,1),(1,1,'C',32,1),(1,1,'C',33,1),(1,1,'C',34,1),(1,1,'C',35,1),(1,1,'C',36,1),(1,1,'C',37,1),(1,1,'C',38,1),(1,1,'C',39,1),(1,1,'C',40,1),(1,1,'C',41,1),(1,1,'C',42,1),(1,1,'D',43,1),(1,1,'D',44,1),(1,1,'D',45,1),(1,1,'D',46,1),(1,1,'D',47,1),(1,1,'D',48,1),(1,1,'D',49,1),(1,1,'D',50,1),(1,10,'A',1,1),(1,10,'A',2,1),(1,10,'A',3,1),(1,10,'A',4,1),(1,10,'A',5,1),(1,10,'A',6,1),(1,10,'A',7,1),(1,10,'A',8,1),(1,10,'A',9,1),(1,10,'A',10,1),(1,10,'A',11,1),(1,10,'A',12,1),(1,10,'A',13,1),(1,10,'A',14,1),(1,10,'B',15,1),(1,10,'B',16,1),(1,10,'B',17,1),(1,10,'B',18,1),(1,10,'B',19,1),(1,10,'B',20,1),(1,10,'B',21,1),(1,10,'B',22,1),(1,10,'B',23,1),(1,10,'B',24,1),(1,10,'B',25,1),(1,10,'B',26,1),(1,10,'B',27,1),(1,10,'B',28,1),(1,10,'C',29,1),(1,10,'C',30,1),(1,10,'C',31,1),(1,10,'C',32,1),(1,10,'C',33,1),(1,10,'C',34,1),(1,10,'C',35,1),(1,10,'C',36,1),(1,10,'C',37,1),(1,10,'C',38,1),(1,10,'C',39,1),(1,10,'C',40,1),(1,10,'C',41,1),(1,10,'C',42,1),(1,10,'D',43,1),(1,10,'D',44,1),(1,10,'D',45,1),(1,10,'D',46,1),(1,10,'D',47,1),(1,10,'D',48,1),(1,10,'D',49,1),(1,10,'D',50,1),(1,10,'D',51,1),(1,10,'D',52,1),(1,10,'D',53,1),(1,10,'D',54,1),(1,10,'D',55,1),(1,10,'D',56,1),(1,10,'E',57,1),(1,10,'E',58,1),(1,10,'E',59,1),(1,10,'E',60,1),(1,32,'A',1,1),(1,32,'A',2,1),(1,32,'A',3,1),(1,32,'A',4,1),(1,32,'A',5,1),(1,32,'A',6,1),(1,32,'A',7,1),(1,32,'A',8,1),(1,32,'A',9,1),(1,32,'A',10,1),(1,32,'A',11,1),(1,32,'A',12,1),(1,32,'A',13,1),(1,32,'A',14,1),(1,32,'B',15,1),(1,32,'B',16,1),(1,32,'B',17,1),(1,32,'B',18,1),(1,32,'B',19,1),(1,32,'B',20,1),(1,32,'B',21,1),(1,32,'B',22,1),(1,32,'B',23,1),(1,32,'B',24,1),(1,32,'B',25,1),(1,32,'B',26,1),(1,32,'B',27,1),(1,32,'B',28,1),(1,32,'C',29,1),(1,32,'C',30,1),(1,32,'C',31,1),(1,32,'C',32,1),(1,32,'C',33,1),(1,32,'C',34,1),(1,32,'C',35,1),(1,32,'C',36,1),(1,32,'C',37,1),(1,32,'C',38,1),(1,32,'C',39,1),(1,32,'C',40,1),(1,32,'C',41,1),(1,32,'C',42,1),(1,32,'D',43,1),(1,32,'D',44,1),(1,32,'D',45,1),(1,32,'D',46,1),(1,32,'D',47,1),(1,32,'D',48,1),(1,32,'D',49,1),(1,32,'D',50,1);
/*!40000 ALTER TABLE `asiento_sala` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cinema`
--

DROP TABLE IF EXISTS `cinema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cinema` (
  `id_cinema` int NOT NULL,
  `nombre` varchar(45) COLLATE utf8mb4_spanish_ci NOT NULL,
  `direccion` varchar(45) COLLATE utf8mb4_spanish_ci NOT NULL,
  PRIMARY KEY (`id_cinema`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cinema`
--

LOCK TABLES `cinema` WRITE;
/*!40000 ALTER TABLE `cinema` DISABLE KEYS */;
INSERT INTO `cinema` VALUES (1,'YEMADI','Mall Oxigeno Heredia');
/*!40000 ALTER TABLE `cinema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id_cliente` varchar(45) COLLATE utf8mb4_spanish_ci NOT NULL,
  `apellidos` varchar(45) COLLATE utf8mb4_spanish_ci NOT NULL,
  `nombre` varchar(45) COLLATE utf8mb4_spanish_ci NOT NULL,
  `telefono` varchar(45) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `tarjeta_pago` varchar(45) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('1023123','Mendez','Maria','','123123123'),('10239123','Azofeifa','Marilyn','','120931293'),('1029312','Jimenez','Julia','','1209381293'),('109231092','Karain','Kirian','','0123810923812'),('118923712893','Halmin','Hemiltom','','192371289371'),('12093123','Ma','Maria ','','10293109231'),('123091239','Ma','Maria','','1203912301'),('12312093128','Martinez','Keyla','','0912381902381'),('123123123','Villalobos','Bianca','','129301293'),('123123912','Montana','Hana','','918231982312'),('4564654','Bob','Marley','','864654654'),('admin','admin','admin',NULL,NULL),('dicars','Martinez','Dicarlo','123123','123213'),('vale123','Sanchez','Valeria','656565','654654654');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `factura` (
  `seq_factura` int NOT NULL AUTO_INCREMENT,
  `fecha` timestamp NOT NULL,
  `cliente_id` varchar(45) COLLATE utf8mb4_spanish_ci NOT NULL,
  `tarjeta_pago` varchar(45) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`seq_factura`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `funcion`
--

DROP TABLE IF EXISTS `funcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funcion` (
  `sala_cinema_id` int NOT NULL,
  `sala_numero` int NOT NULL,
  `fecha` timestamp NOT NULL,
  `pelicula_id` varchar(45) COLLATE utf8mb4_spanish_ci NOT NULL,
  PRIMARY KEY (`sala_cinema_id`,`sala_numero`,`fecha`),
  KEY `fk_funcion_pelicula_idx` (`pelicula_id`),
  CONSTRAINT `fk_funcion_pelicula` FOREIGN KEY (`pelicula_id`) REFERENCES `pelicula` (`id_pelicula`),
  CONSTRAINT `fk_funcion_sala` FOREIGN KEY (`sala_cinema_id`, `sala_numero`) REFERENCES `sala` (`cinema_id`, `numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pelicula`
--

DROP TABLE IF EXISTS `pelicula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pelicula` (
  `id_pelicula` varchar(45) COLLATE utf8mb4_spanish_ci NOT NULL,
  `titulo` varchar(45) COLLATE utf8mb4_spanish_ci NOT NULL,
  `movie_data` longtext COLLATE utf8mb4_spanish_ci NOT NULL,
  `cartelera` tinyint NOT NULL DEFAULT '1',
  `tipo_imagen` varchar(45) COLLATE utf8mb4_spanish_ci NOT NULL,
  `imagen` mediumblob NOT NULL,
  `tamano` int NOT NULL,
  PRIMARY KEY (`id_pelicula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sala`
--

DROP TABLE IF EXISTS `sala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sala` (
  `cinema_id` int NOT NULL,
  `numero` int NOT NULL,
  `capacidad` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`cinema_id`,`numero`),
  CONSTRAINT `fk_sala_cinema` FOREIGN KEY (`cinema_id`) REFERENCES `cinema` (`id_cinema`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sala`
--

LOCK TABLES `sala` WRITE;
/*!40000 ALTER TABLE `sala` DISABLE KEYS */;
INSERT INTO `sala` VALUES (1,1,50),(1,10,60),(1,32,50);
/*!40000 ALTER TABLE `sala` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tiquete`
--

DROP TABLE IF EXISTS `tiquete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tiquete` (
  `id_tiquete` int NOT NULL AUTO_INCREMENT,
  `factura_seq` int NOT NULL,
  `asiento_funcion_sala_cinema_id` int NOT NULL,
  `asiento_funcion_sala_numero` int NOT NULL,
  `asiento_funcion_fecha` timestamp NOT NULL,
  `asiento_funcion_fila` varchar(1) COLLATE utf8mb4_spanish_ci NOT NULL,
  `asiento_funcion_posicion` int NOT NULL,
  `monto` double NOT NULL,
  PRIMARY KEY (`id_tiquete`),
  KEY `fk_tiquete_factura_idx` (`factura_seq`),
  KEY `fk_tiquete_asiento_funcion_idx` (`asiento_funcion_sala_cinema_id`,`asiento_funcion_sala_numero`,`asiento_funcion_fecha`,`asiento_funcion_fila`,`asiento_funcion_posicion`),
  CONSTRAINT `fk_tiquete_asiento_funcion` FOREIGN KEY (`asiento_funcion_sala_cinema_id`, `asiento_funcion_sala_numero`, `asiento_funcion_fecha`, `asiento_funcion_fila`, `asiento_funcion_posicion`) REFERENCES `asiento_funcion` (`funcion_sala_cinema_id`, `funcion_sala_numero`, `funcion_fecha`, `fila`, `posicion`),
  CONSTRAINT `fk_tiquete_factura` FOREIGN KEY (`factura_seq`) REFERENCES `factura` (`seq_factura`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usuario` varchar(45) COLLATE utf8mb4_spanish_ci NOT NULL,
  `cliente_id` varchar(45) COLLATE utf8mb4_spanish_ci NOT NULL,
  `clave` varchar(45) COLLATE utf8mb4_spanish_ci NOT NULL,
  `rol` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_usuario`),
  KEY `fk_usuario_cliente_idx` (`cliente_id`),
  CONSTRAINT `fk_usuario_cliente` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('admin','admin','admin',1),('dicars','dicars','12312321',0),('vale123','vale123','vale123',0);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'bd_cinema'
--

--
-- Dumping routines for database 'bd_cinema'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-28  9:18:50
