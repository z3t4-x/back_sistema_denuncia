CREATE DATABASE  IF NOT EXISTS `vocalia_superior_2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `vocalia_superior_2`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: vocalia_superior_2
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `catalogos`
--

DROP TABLE IF EXISTS `catalogos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `catalogos` (
  `id_catalogo` int NOT NULL AUTO_INCREMENT,
  `cd_usu_alta` varchar(255) DEFAULT NULL,
  `cd_usu_baja` varchar(255) DEFAULT NULL,
  `cd_usu_modif` varchar(255) DEFAULT NULL,
  `ds_nombre` varchar(255) DEFAULT NULL,
  `fc_alta_fila` datetime(6) DEFAULT NULL,
  `fc_baja_fila` datetime(6) DEFAULT NULL,
  `fc_modif_fila` datetime(6) DEFAULT NULL,
  `it_mantenible` varchar(255) DEFAULT NULL,
  `tl_descripcion` varchar(255) DEFAULT NULL,
  `id_usuario` bigint DEFAULT NULL,
  PRIMARY KEY (`id_catalogo`),
  KEY `FK4nmay2k5218mhaqj6wqge7nfk` (`id_usuario`),
  CONSTRAINT `FK4nmay2k5218mhaqj6wqge7nfk` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalogos`
--

LOCK TABLES `catalogos` WRITE;
/*!40000 ALTER TABLE `catalogos` DISABLE KEYS */;
INSERT INTO `catalogos` VALUES (1,'ADMIN',NULL,NULL,'SEXO','2015-04-03 14:00:45.000000',NULL,NULL,'N','GENERO M O F',NULL),(2,'ADMIN',NULL,NULL,'TIPO DE IDENTIFCACION','2023-05-12 01:09:45.000000',NULL,NULL,'N','DNI U CARNET EXTRANGERIA',NULL),(3,'ADMIN',NULL,NULL,'ESTADO DE LA DENUNCIA','2023-05-12 01:09:45.000000',NULL,NULL,'N','NULL',NULL),(4,'ADMIN',NULL,NULL,'TIPOS DE DOCUMENTO','2023-05-12 01:09:45.000000',NULL,NULL,'N','NULL',NULL),(5,'ADMIN',NULL,NULL,'AUXILIAR','2023-05-12 01:09:45.000000',NULL,NULL,'S','NULL',NULL),(6,'ADMIN',NULL,NULL,'TIPOS DE DELITOS','2023-05-12 01:09:45.000000',NULL,NULL,'S','NULL',NULL),(7,'ADMIN',NULL,NULL,'UNIDADES SANCIONADORAS','2023-05-12 01:09:45.000000',NULL,NULL,'S','UNID SANCIONADORA',NULL),(8,'ADMIN',NULL,NULL,'INSTITUCIÓN','2023-05-12 01:09:45.000000',NULL,NULL,'S','NULL',NULL),(9,'ADMIN',NULL,NULL,'TIPO DENUNCIANTE','2023-05-12 01:09:45.000000',NULL,NULL,'S','IDENTIFICA SI ES DENUNCIANTE O DENUNCIADO',NULL),(10,'ADMIN',NULL,NULL,'GRADO','2023-05-12 01:09:45.000000',NULL,NULL,'S','GRADO DEL OFICIAL',NULL),(11,'ADMIN',NULL,NULL,'FISCALIA','2023-05-12 01:09:45.000000',NULL,NULL,'S','FISCALIA',NULL),(12,'ADMIN',NULL,NULL,'MESA DE PARTE','2023-05-12 01:09:45.000000',NULL,NULL,'S','MESA DE PARTE',NULL);
/*!40000 ALTER TABLE `catalogos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catalogos_valores`
--

DROP TABLE IF EXISTS `catalogos_valores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `catalogos_valores` (
  `id_valor` int NOT NULL AUTO_INCREMENT,
  `cd_codigo` varchar(255) DEFAULT NULL,
  `cd_usu_alta` varchar(255) DEFAULT NULL,
  `cd_usu_baja` varchar(255) DEFAULT NULL,
  `cd_usu_modif` varchar(255) DEFAULT NULL,
  `ds_valor` varchar(255) DEFAULT NULL,
  `fc_alta_fila` datetime(6) DEFAULT NULL,
  `fc_baja_fila` datetime(6) DEFAULT NULL,
  `fc_modif_fila` datetime(6) DEFAULT NULL,
  `it_mantenible` varchar(255) DEFAULT NULL,
  `id_catalogo` int DEFAULT NULL,
  `id_usuario` bigint DEFAULT NULL,
  PRIMARY KEY (`id_valor`),
  KEY `FKsb7avino6icbgpioc5trekk4n` (`id_catalogo`),
  KEY `FKo4w2dbnygfesiox4x5a0sybai` (`id_usuario`),
  CONSTRAINT `FKo4w2dbnygfesiox4x5a0sybai` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  CONSTRAINT `FKsb7avino6icbgpioc5trekk4n` FOREIGN KEY (`id_catalogo`) REFERENCES `catalogos` (`id_catalogo`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalogos_valores`
--

LOCK TABLES `catalogos_valores` WRITE;
/*!40000 ALTER TABLE `catalogos_valores` DISABLE KEYS */;
INSERT INTO `catalogos_valores` VALUES (1,'M','ADMIN',NULL,NULL,'MASCULINO','2023-05-12 01:09:45.000000',NULL,NULL,'N',1,NULL),(2,'F','ADMIN',NULL,NULL,'FEMENINO','2023-05-12 01:09:45.000000',NULL,NULL,'N',1,NULL),(3,'DNI','ADMIN',NULL,NULL,'DNI','2023-05-12 01:09:45.000000',NULL,NULL,'N',2,NULL),(4,'CE','ADMIN',NULL,NULL,'CARNET DE EXTRANJERÍA','2023-05-12 01:09:45.000000',NULL,NULL,'N',2,NULL),(5,'DCIA','ADMIN',NULL,NULL,'DENUNCIA','2023-05-12 01:09:45.000000',NULL,NULL,'N',3,NULL),(6,'DVER','ADMIN',NULL,NULL,'DEVOLVER','2023-05-12 01:09:45.000000',NULL,NULL,'N',3,NULL),(7,'DST','ADMIN',NULL,NULL,'DESESTIMAR','2023-05-12 01:09:45.000000',NULL,NULL,'N',3,NULL),(8,'PRM','ADMIN',NULL,NULL,'PRELIMINAR','2023-05-12 01:09:45.000000',NULL,NULL,'N',3,NULL),(9,'PRPA','ADMIN',NULL,NULL,'PREPARATORIA','2023-05-12 01:09:45.000000',NULL,NULL,'N',3,NULL),(10,'RSION','ADMIN',NULL,NULL,'REVISIÓN','2023-05-12 01:09:45.000000',NULL,NULL,'N',3,NULL),(11,'ARCH','ADMIN',NULL,NULL,'ARCHIVADO','2023-05-12 01:09:45.000000',NULL,NULL,'N',3,NULL),(12,'OTR','ADMIN',NULL,NULL,'OTROS','2023-05-12 01:09:45.000000',NULL,NULL,'N',3,NULL),(13,'OFI','ADMIN',NULL,NULL,'OFICIO','2023-05-12 01:09:45.000000',NULL,NULL,'N',4,NULL),(14,'INF','ADMIN',NULL,NULL,'INFORME','2023-05-12 01:09:45.000000',NULL,NULL,'N',4,NULL),(15,'JTA','ADMIN',NULL,NULL,'JUNTA','2023-05-12 01:09:45.000000',NULL,NULL,'N',4,NULL),(16,'RES','ADMIN',NULL,NULL,'RESOLUCIÓN','2023-05-12 01:09:45.000000',NULL,NULL,'N',4,NULL),(17,'OTR','ADMIN',NULL,NULL,'OTROS','2023-05-12 01:09:45.000000',NULL,NULL,'N',4,NULL),(18,'AX1','ADMIN',NULL,NULL,'AUXILIAR_1','2023-05-12 01:09:45.000000',NULL,NULL,'N',5,NULL),(19,'AX2','ADMIN',NULL,NULL,'AUXILIAR_2','2023-05-12 01:09:45.000000',NULL,NULL,'N',5,NULL),(20,'AX3','ADMIN',NULL,NULL,'AUXILIAR_3','2023-05-12 01:09:45.000000',NULL,NULL,'N',5,NULL),(21,'AX4','ADMIN',NULL,NULL,'AUXILIAR_4','2023-05-12 01:09:45.000000',NULL,NULL,'N',5,NULL),(22,'DESC','ADMIN',NULL,NULL,'DESERCIÓN','2023-05-12 01:09:45.000000',NULL,NULL,'N',6,NULL),(23,'DESB','ADMIN',NULL,NULL,'DESOBEDIENCIA','2023-05-12 01:09:45.000000',NULL,NULL,'N',6,NULL),(24,'INS','ADMIN',NULL,NULL,'INSULTO','2023-05-12 01:09:45.000000',NULL,NULL,'N',6,NULL),(25,'ABU','ADMIN',NULL,NULL,'ABUSO','2023-05-12 01:09:45.000000',NULL,NULL,'N',6,NULL),(26,'HTO','ADMIN',NULL,NULL,'HURTO','2023-05-12 01:09:45.000000',NULL,NULL,'N',6,NULL),(27,'AFEC','ADMIN',NULL,NULL,'AFECTACIÓN','2023-05-12 01:09:45.000000',NULL,NULL,'N',6,NULL),(28,'COMGEN','ADMIN',NULL,NULL,'COMANDANCIA GENERAL','2023-05-12 01:09:45.000000',NULL,NULL,'N',7,NULL),(29,'SETRA','ADMIN',NULL,NULL,'SETRA','2023-05-12 01:09:45.000000',NULL,NULL,'N',7,NULL),(30,'BIM2','ADMIN',NULL,NULL,'BIM 2','2023-05-12 01:09:45.000000',NULL,NULL,'N',7,NULL),(31,'INSP','ADMIN',NULL,NULL,'INSPECTORÍA','2023-05-12 01:09:45.000000',NULL,NULL,'N',7,NULL),(32,'PNP','ADMIN',NULL,NULL,'POLICÍA NACIONAL DEL PERÚ','2023-05-12 01:09:45.000000',NULL,NULL,'N',8,NULL),(33,'DNCTE','ADMIN',NULL,NULL,'DENUCIANTE','2023-05-12 01:09:45.000000',NULL,NULL,'N',9,NULL),(34,'DNCDO','ADMIN',NULL,NULL,'DENUNCIADO','2023-05-12 01:09:45.000000',NULL,NULL,'N',9,NULL),(35,'TC1','ADMIN',NULL,NULL,'TECNICO  DE PRIMERA','2023-05-12 01:09:45.000000',NULL,NULL,'N',10,NULL),(36,'TC2','ADMIN',NULL,NULL,'TECNICO DE SEGUNDA','2023-05-12 01:09:45.000000',NULL,NULL,'N',10,NULL),(37,'TC3','ADMIN',NULL,NULL,'TECNICO DE TERCERA','2023-05-12 01:09:45.000000',NULL,NULL,'N',10,NULL),(38,'CRNL','ADMIN',NULL,NULL,'CORONEL','2023-05-12 01:09:45.000000',NULL,NULL,'N',10,NULL),(39,'13','ADMIN',NULL,NULL,'FISCALÍA 13','2023-05-12 01:09:45.000000',NULL,NULL,'S',11,NULL),(40,'14','ADMIN',NULL,NULL,'FISCALÍA 14','2023-05-12 01:09:45.000000',NULL,NULL,'S',11,NULL),(41,'M13','ADMIN',NULL,NULL,'MESA DE PARTE 13','2023-05-12 01:09:45.000000',NULL,NULL,'S',12,NULL),(42,'M14','ADMIN',NULL,NULL,'MESA DE PARTE 14','2023-05-12 01:09:45.000000',NULL,NULL,'S',12,NULL);
/*!40000 ALTER TABLE `catalogos_valores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `denuncia`
--

DROP TABLE IF EXISTS `denuncia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `denuncia` (
  `id_denuncia` int NOT NULL AUTO_INCREMENT,
  `cd_usu_alta` varchar(255) DEFAULT NULL,
  `cd_usu_baja` varchar(255) DEFAULT NULL,
  `cd_usu_modif` varchar(255) DEFAULT NULL,
  `ds_descripcion` varchar(255) DEFAULT NULL,
  `fc_alta_denuncia` datetime(6) NOT NULL,
  `fc_alta_fila` datetime(6) DEFAULT NULL,
  `fc_baja_fila` datetime(6) DEFAULT NULL,
  `fc_hechos` date NOT NULL,
  `fc_ingreso_documento` date NOT NULL,
  `fc_modif_fila` datetime(6) DEFAULT NULL,
  `fc_plazo` datetime(6) NOT NULL,
  `nm_denuncia` varchar(255) NOT NULL,
  `nm_documento` varchar(255) NOT NULL,
  `nm_expediente_inv_preliminar` varchar(255) DEFAULT NULL,
  `nm_expediente_preparatoria` varchar(255) DEFAULT NULL,
  `id_auxiliar` int NOT NULL,
  `id_estado` int NOT NULL,
  `id_fiscalia` int DEFAULT NULL,
  `id_mesa_parte` int DEFAULT NULL,
  `id_delito` int NOT NULL,
  `id_tipo_documento` int NOT NULL,
  `id_usuario` bigint DEFAULT NULL,
  PRIMARY KEY (`id_denuncia`),
  KEY `FKc1ntvgi3xs3vloiepk0fd7b9c` (`id_auxiliar`),
  KEY `FK9pbwyupllmrtega0auy6qy5n` (`id_estado`),
  KEY `FKlxa3nd5qklq7b33gs483byf2q` (`id_fiscalia`),
  KEY `FK3qk6d0fdfax16mki2wl3cegtb` (`id_mesa_parte`),
  KEY `FKio1xy0pyo8eodb0n86bcibn1q` (`id_delito`),
  KEY `FKesu9uc55winpgvmms67bt2097` (`id_tipo_documento`),
  KEY `FK5ojdlk8ag05w8ca97pk75uln1` (`id_usuario`),
  CONSTRAINT `FK3qk6d0fdfax16mki2wl3cegtb` FOREIGN KEY (`id_mesa_parte`) REFERENCES `catalogos_valores` (`id_valor`),
  CONSTRAINT `FK5ojdlk8ag05w8ca97pk75uln1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  CONSTRAINT `FK9pbwyupllmrtega0auy6qy5n` FOREIGN KEY (`id_estado`) REFERENCES `catalogos_valores` (`id_valor`),
  CONSTRAINT `FKc1ntvgi3xs3vloiepk0fd7b9c` FOREIGN KEY (`id_auxiliar`) REFERENCES `catalogos_valores` (`id_valor`),
  CONSTRAINT `FKesu9uc55winpgvmms67bt2097` FOREIGN KEY (`id_tipo_documento`) REFERENCES `catalogos_valores` (`id_valor`),
  CONSTRAINT `FKio1xy0pyo8eodb0n86bcibn1q` FOREIGN KEY (`id_delito`) REFERENCES `catalogos_valores` (`id_valor`),
  CONSTRAINT `FKlxa3nd5qklq7b33gs483byf2q` FOREIGN KEY (`id_fiscalia`) REFERENCES `catalogos_valores` (`id_valor`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `denuncia`
--

LOCK TABLES `denuncia` WRITE;
/*!40000 ALTER TABLE `denuncia` DISABLE KEYS */;
INSERT INTO `denuncia` VALUES (1,NULL,NULL,NULL,'eqewqeqw','2023-05-12 01:11:34.785000',NULL,NULL,'2023-05-12','2023-05-12',NULL,'2023-05-27 01:11:34.785000','DEN001-2023-13','0014',NULL,NULL,19,5,39,41,23,14,NULL),(2,NULL,NULL,NULL,'qweqwewq','2023-05-13 18:15:17.473000',NULL,NULL,'2023-05-12','2023-05-13',NULL,'2023-05-28 18:15:17.473000','DEN002-2023-13','00025',NULL,NULL,20,5,39,41,24,14,NULL),(3,NULL,NULL,NULL,'qweqwewq','2023-05-16 16:45:38.183000',NULL,NULL,'2023-05-12','2023-05-13',NULL,'2023-05-31 16:45:38.183000','DEN003-2023-13','00025',NULL,NULL,20,5,39,41,24,14,NULL),(4,NULL,NULL,NULL,'rwrwer','2023-05-18 13:32:33.603000',NULL,NULL,'2023-05-16','2023-05-18',NULL,'2023-06-02 13:32:33.603000','DEN004-2023-13','doc003',NULL,NULL,20,5,39,41,23,15,NULL),(5,NULL,NULL,NULL,'','2023-05-18 21:50:44.280000',NULL,NULL,'2023-05-18','2023-05-18',NULL,'2023-06-02 21:50:44.280000','DEN005-2023-13','j001',NULL,NULL,19,5,39,41,27,15,NULL),(6,NULL,NULL,NULL,'','2023-05-18 23:40:46.023000',NULL,NULL,'2023-05-18','2023-05-18',NULL,'2023-06-02 23:40:46.023000','DEN006-2023-13','doc10',NULL,NULL,20,5,39,41,23,16,NULL);
/*!40000 ALTER TABLE `denuncia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `denuncia_historico`
--

DROP TABLE IF EXISTS `denuncia_historico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `denuncia_historico` (
  `id_denuncia_hist` int NOT NULL AUTO_INCREMENT,
  `cd_expediente_preliminar` varchar(255) DEFAULT NULL,
  `cd_expediente_preparatoria` varchar(255) DEFAULT NULL,
  `cd_usu_alta` varchar(255) DEFAULT NULL,
  `cd_usu_baja` varchar(255) DEFAULT NULL,
  `cd_usu_modif` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `fc_alta_denuncia` datetime(6) NOT NULL,
  `fc_alta_fila` datetime(6) DEFAULT NULL,
  `fc_baja_fila` datetime(6) DEFAULT NULL,
  `fc_hechos` date DEFAULT NULL,
  `fc_ingreso_documento` date NOT NULL,
  `fc_modif_fila` datetime(6) DEFAULT NULL,
  `fc_plazo` date DEFAULT NULL,
  `num_denuncia` varchar(255) DEFAULT NULL,
  `num_documento` varchar(255) DEFAULT NULL,
  `id_auxiliar` int DEFAULT NULL,
  `id_denuncia` int DEFAULT NULL,
  `id_estado_anterior` int DEFAULT NULL,
  `id_estado_nuevo` int DEFAULT NULL,
  `id_fiscalia` int DEFAULT NULL,
  `id_delito` int DEFAULT NULL,
  `id_tipo_documento` int NOT NULL,
  PRIMARY KEY (`id_denuncia_hist`),
  KEY `FK44ex9t9wot3mwhay0f60lo6iq` (`id_auxiliar`),
  KEY `FK7n091c12rurgfkfcsvqhfoika` (`id_denuncia`),
  KEY `FKkv3ephd1e4mvjs7g4maw1cfey` (`id_estado_anterior`),
  KEY `FKaa3k5y2ttixu62wxepe8dav0` (`id_estado_nuevo`),
  KEY `FKl2n0edgmkfgn2oea9y9laf6eu` (`id_fiscalia`),
  KEY `FKb1aewf7g8tbbwmin1y6pe4ejo` (`id_delito`),
  KEY `FK4crwelh3fw9ir2othlc8f1xkc` (`id_tipo_documento`),
  CONSTRAINT `FK44ex9t9wot3mwhay0f60lo6iq` FOREIGN KEY (`id_auxiliar`) REFERENCES `catalogos_valores` (`id_valor`),
  CONSTRAINT `FK4crwelh3fw9ir2othlc8f1xkc` FOREIGN KEY (`id_tipo_documento`) REFERENCES `catalogos_valores` (`id_valor`),
  CONSTRAINT `FK7n091c12rurgfkfcsvqhfoika` FOREIGN KEY (`id_denuncia`) REFERENCES `denuncia` (`id_denuncia`),
  CONSTRAINT `FKaa3k5y2ttixu62wxepe8dav0` FOREIGN KEY (`id_estado_nuevo`) REFERENCES `catalogos_valores` (`id_valor`),
  CONSTRAINT `FKb1aewf7g8tbbwmin1y6pe4ejo` FOREIGN KEY (`id_delito`) REFERENCES `catalogos_valores` (`id_valor`),
  CONSTRAINT `FKkv3ephd1e4mvjs7g4maw1cfey` FOREIGN KEY (`id_estado_anterior`) REFERENCES `catalogos_valores` (`id_valor`),
  CONSTRAINT `FKl2n0edgmkfgn2oea9y9laf6eu` FOREIGN KEY (`id_fiscalia`) REFERENCES `catalogos_valores` (`id_valor`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `denuncia_historico`
--

LOCK TABLES `denuncia_historico` WRITE;
/*!40000 ALTER TABLE `denuncia_historico` DISABLE KEYS */;
INSERT INTO `denuncia_historico` VALUES (1,NULL,NULL,NULL,NULL,NULL,'eqewqeqw','2023-05-12 01:11:34.785000',NULL,NULL,'2023-05-12','2023-05-12',NULL,'2023-05-27','DEN001-2023-13',NULL,19,1,5,NULL,39,23,14),(2,NULL,NULL,NULL,NULL,NULL,'qweqwewq','2023-05-13 18:15:17.473000',NULL,NULL,'2023-05-12','2023-05-13',NULL,'2023-05-28','DEN002-2023-13',NULL,20,2,5,5,39,24,14),(3,NULL,NULL,NULL,NULL,NULL,'qweqwewq','2023-05-16 16:45:38.183000',NULL,NULL,'2023-05-12','2023-05-13',NULL,'2023-05-31','DEN003-2023-13',NULL,20,3,5,5,39,24,14),(4,NULL,NULL,NULL,NULL,NULL,'rwrwer','2023-05-18 13:32:33.603000',NULL,NULL,'2023-05-16','2023-05-18',NULL,'2023-06-02','DEN004-2023-13',NULL,20,4,5,5,39,23,15),(5,NULL,NULL,NULL,NULL,NULL,'','2023-05-18 21:50:44.280000',NULL,NULL,'2023-05-18','2023-05-18',NULL,'2023-06-02','DEN005-2023-13',NULL,19,5,5,5,39,27,15),(6,NULL,NULL,NULL,NULL,NULL,'','2023-05-18 23:40:46.023000',NULL,NULL,'2023-05-18','2023-05-18',NULL,'2023-06-02','DEN006-2023-13',NULL,20,6,5,5,39,23,16);
/*!40000 ALTER TABLE `denuncia_historico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `denuncia_persona`
--

DROP TABLE IF EXISTS `denuncia_persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `denuncia_persona` (
  `id_denuncia` int NOT NULL,
  `id_persona` int NOT NULL,
  `cd_usu_alta` varchar(255) DEFAULT NULL,
  `cd_usu_baja` varchar(255) DEFAULT NULL,
  `cd_usu_modif` varchar(255) DEFAULT NULL,
  `fc_alta_denuncia` datetime(6) DEFAULT NULL,
  `fc_alta_fila` datetime(6) DEFAULT NULL,
  `fc_baja_fila` datetime(6) DEFAULT NULL,
  `fc_modif_fila` datetime(6) DEFAULT NULL,
  `id_tipo_persona` int DEFAULT NULL,
  PRIMARY KEY (`id_denuncia`,`id_persona`),
  KEY `FK6ilevo34jnyojemhsppj5m792` (`id_persona`),
  KEY `FKd8sdwm46u3jefrc6opce3d46d` (`id_tipo_persona`),
  CONSTRAINT `FK6ilevo34jnyojemhsppj5m792` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`),
  CONSTRAINT `FKd8sdwm46u3jefrc6opce3d46d` FOREIGN KEY (`id_tipo_persona`) REFERENCES `catalogos_valores` (`id_valor`),
  CONSTRAINT `FKm3ly5vpxw0khi6qy5dhwks0y0` FOREIGN KEY (`id_denuncia`) REFERENCES `denuncia` (`id_denuncia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `denuncia_persona`
--

LOCK TABLES `denuncia_persona` WRITE;
/*!40000 ALTER TABLE `denuncia_persona` DISABLE KEYS */;
INSERT INTO `denuncia_persona` VALUES (1,1,NULL,NULL,NULL,'2023-05-12 01:11:34.814000',NULL,NULL,NULL,33),(1,2,NULL,NULL,NULL,'2023-05-12 01:11:34.818000',NULL,NULL,NULL,34),(2,1,NULL,NULL,NULL,'2023-05-13 18:15:17.536000',NULL,NULL,NULL,33),(2,2,NULL,NULL,NULL,'2023-05-13 18:15:17.539000',NULL,NULL,NULL,34),(3,1,NULL,NULL,NULL,'2023-05-16 16:45:38.259000',NULL,NULL,NULL,33),(3,2,NULL,NULL,NULL,'2023-05-16 16:45:38.260000',NULL,NULL,NULL,34),(4,1,NULL,NULL,NULL,'2023-05-18 13:32:33.758000',NULL,NULL,NULL,33),(4,2,NULL,NULL,NULL,'2023-05-18 13:32:33.759000',NULL,NULL,NULL,34),(5,1,NULL,NULL,NULL,'2023-05-18 21:50:44.345000',NULL,NULL,NULL,34),(5,3,NULL,NULL,NULL,'2023-05-18 21:50:44.342000',NULL,NULL,NULL,33),(6,1,NULL,NULL,NULL,'2023-05-18 23:40:46.098000',NULL,NULL,NULL,33),(6,3,NULL,NULL,NULL,'2023-05-18 23:40:46.102000',NULL,NULL,NULL,34);
/*!40000 ALTER TABLE `denuncia_persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `id_persona` int NOT NULL AUTO_INCREMENT,
  `apellido1` varchar(255) DEFAULT NULL,
  `apellido2` varchar(255) DEFAULT NULL,
  `cd_usu_alta` varchar(255) DEFAULT NULL,
  `cd_usu_baja` varchar(255) DEFAULT NULL,
  `cd_usu_modif` varchar(255) DEFAULT NULL,
  `dni` varchar(8) DEFAULT NULL,
  `fc_alta_fila` datetime(6) DEFAULT NULL,
  `fc_baja_fila` datetime(6) DEFAULT NULL,
  `fc_modif_fila` datetime(6) DEFAULT NULL,
  `fc_nacimiento` date DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `telefono` varchar(9) DEFAULT NULL,
  `id_genero` int DEFAULT NULL,
  `id_grado` int DEFAULT NULL,
  `id_tipo_identificacion` int DEFAULT NULL,
  `id_institucion` int DEFAULT NULL,
  `id_usuario` bigint DEFAULT NULL,
  PRIMARY KEY (`id_persona`),
  UNIQUE KEY `UK_hlwyecu2r9wagqayhej1kt5wy` (`dni`),
  KEY `FKm7p663eevivkl214hltphghb0` (`id_genero`),
  KEY `FKmr6j4ol56col3ux9y63s189dp` (`id_grado`),
  KEY `FKrgon68gqhrlrcy7jlt1ijadsb` (`id_tipo_identificacion`),
  KEY `FK50wckx59jjhu33466ytddmfvw` (`id_institucion`),
  KEY `FKdvjhjratvktrc6t459mqiidco` (`id_usuario`),
  CONSTRAINT `FK50wckx59jjhu33466ytddmfvw` FOREIGN KEY (`id_institucion`) REFERENCES `catalogos_valores` (`id_valor`),
  CONSTRAINT `FKdvjhjratvktrc6t459mqiidco` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  CONSTRAINT `FKm7p663eevivkl214hltphghb0` FOREIGN KEY (`id_genero`) REFERENCES `catalogos_valores` (`id_valor`),
  CONSTRAINT `FKmr6j4ol56col3ux9y63s189dp` FOREIGN KEY (`id_grado`) REFERENCES `catalogos_valores` (`id_valor`),
  CONSTRAINT `FKrgon68gqhrlrcy7jlt1ijadsb` FOREIGN KEY (`id_tipo_identificacion`) REFERENCES `catalogos_valores` (`id_valor`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (1,'Salarzar','Benedicto',NULL,NULL,NULL,'45977921',NULL,NULL,NULL,NULL,'Grek',NULL,1,36,3,NULL,NULL),(2,'Mendivel','Reyes',NULL,NULL,NULL,'45787477',NULL,NULL,NULL,NULL,'Lucia',NULL,2,36,3,NULL,NULL),(3,'Vazques','Calderon',NULL,NULL,NULL,'45977999',NULL,NULL,NULL,NULL,'Bagner',NULL,1,36,3,NULL,NULL);
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id_rol` bigint NOT NULL AUTO_INCREMENT,
  `cd_usu_alta` varchar(255) DEFAULT NULL,
  `cd_usu_baja` varchar(255) DEFAULT NULL,
  `cd_usu_modif` varchar(255) DEFAULT NULL,
  `fc_alta_fila` datetime(6) DEFAULT NULL,
  `fc_baja_fila` datetime(6) DEFAULT NULL,
  `fc_modif_fila` datetime(6) DEFAULT NULL,
  `nombre_rol` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usuario` bigint NOT NULL AUTO_INCREMENT,
  `cd_usu_alta` varchar(255) DEFAULT NULL,
  `cd_usu_baja` varchar(255) DEFAULT NULL,
  `cd_usu_modif` varchar(255) DEFAULT NULL,
  `contrasenia` varchar(255) DEFAULT NULL,
  `fc_alta_fila` datetime(6) DEFAULT NULL,
  `fc_baja_fila` datetime(6) DEFAULT NULL,
  `fc_modif_fila` datetime(6) DEFAULT NULL,
  `nombre_usuario` varchar(255) DEFAULT NULL,
  `id_fiscalia` int DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `FKg783ufgl7pg7gqdbd4401gv70` (`id_fiscalia`),
  CONSTRAINT `FKg783ufgl7pg7gqdbd4401gv70` FOREIGN KEY (`id_fiscalia`) REFERENCES `catalogos_valores` (`id_valor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_rol`
--

DROP TABLE IF EXISTS `usuario_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_rol` (
  `id_usuario` bigint NOT NULL,
  `id_rol` bigint NOT NULL,
  KEY `FKkxcv7htfnm9x1wkofnud0ewql` (`id_rol`),
  KEY `FK3ftpt75ebughsiy5g03b11akt` (`id_usuario`),
  CONSTRAINT `FK3ftpt75ebughsiy5g03b11akt` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  CONSTRAINT `FKkxcv7htfnm9x1wkofnud0ewql` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_rol`
--

LOCK TABLES `usuario_rol` WRITE;
/*!40000 ALTER TABLE `usuario_rol` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'vocalia_superior_2'
--

--
-- Dumping routines for database 'vocalia_superior_2'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-19  2:01:37
