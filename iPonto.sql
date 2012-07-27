CREATE DATABASE  IF NOT EXISTS `ipontoweb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ipontoweb`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: ipontoweb
-- ------------------------------------------------------
-- Server version	5.5.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `uf`
--

DROP TABLE IF EXISTS `uf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uf` (
  `id_uf` int(11) NOT NULL AUTO_INCREMENT,
  `sigla` varchar(45) DEFAULT NULL,
  `estado` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_uf`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uf`
--

LOCK TABLES `uf` WRITE;
/*!40000 ALTER TABLE `uf` DISABLE KEYS */;
INSERT INTO `uf` VALUES (1,'AC','Acre'),(2,'AL','Alagoas'),(3,'AP','Amapá'),(4,'AM','Amazonas'),(5,'BA','Bahia'),(6,'CE','Ceará'),(7,'DF','Distrito Federal'),(8,'ES','Espírito Santo'),(9,'GO','Goiás'),(10,'MA','Maranhão'),(11,'MT','Mato Grosso'),(12,'MS','Mato Grosso do Sul'),(13,'MG','Minas Gerais'),(14,'PA','Pará'),(15,'PB','Paraíba'),(16,'PR','Paraná'),(17,'PE','Pernambuco'),(18,'PI','Piauí'),(19,'RJ','Rio de Janeiro'),(20,'RN','Rio Grande do Norte'),(21,'RS','Rio Grande do Sul'),(22,'RO','Rondônia'),(23,'RR','Roraima'),(24,'SC','Santa Catarina'),(25,'SP','São Paulo'),(26,'SE','Sergipe'),(27,'TO','Tocantins');
/*!40000 ALTER TABLE `uf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
  `id_clientes` int(11) NOT NULL AUTO_INCREMENT,
  `nome_cliente` varchar(100) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `cpf` varchar(45) DEFAULT NULL,
  `cnpj` varchar(45) DEFAULT NULL,
  `endereco` varchar(100) DEFAULT NULL,
  `tel_fixo` varchar(45) DEFAULT NULL,
  `tel_cel` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `cidade` varchar(100) DEFAULT NULL,
  `uf` varchar(45) DEFAULT NULL,
  `permissao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_clientes`),
  UNIQUE KEY `id_clientes_UNIQUE` (`id_clientes`),
  KEY `fk_uf` (`uf`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'Cliente de Teste',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'Cliente de Teste 02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `senha` varchar(200) NOT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `permissao` varchar(45) NOT NULL,
  `id_clientes` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `id_usuarios_UNIQUE` (`id_usuario`),
  KEY `fk_cliente` (`id_clientes`),
  CONSTRAINT `fk_cliente` FOREIGN KEY (`id_clientes`) REFERENCES `clientes` (`id_clientes`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'admin','admin','Admin de Teste','admin@teste.com','ATIVADO','MASTER',1),(2,'abc','a','Abcedário Elegante','abc@abc.com','ATIVADO','USER',1),(3,'123','123','123 de Oliveira 4','123@email.com','ATIVADO','USER',1),(4,'roger','roger','Roger Connolly Rios','roger.cr@hotmail.com','ATIVADO','ADMIN',1),(5,'zelda','zelda','Zelda','zetta@email.com','ATIVADO','ADMIN',1),(6,'babi.lima','babi','Barbara Lima','babi.lima@email.com','ATIVADO','USER',1),(7,'gigante','123','Novo Usuario Com Nome Gigante da Silva','gigante@email.com','ATIVADO','USER',1),(8,'pato.donald','123','Minnie Mouse','minnie.mouse@disney.com','ATIVADO','ADMIN',1),(9,'pluto','123','Pluto','pluto@disney.com','ATIVADO','USER',1),(10,'mickey.mouse','123','Mickey Mouse','mickey@disney.com','DESATIVADO','USER',1),(11,'pateta','123','Pateta','pateta@disney.com','ATIVADO','USER',1),(12,'tiopatinhas','123','Tio Patinhas','tiopatinhas@ducktales.com','ATIVADO','USER',1),(13,'huguinho','123','Huguinho','','ATIVADO','USER',1),(14,'zezinho','123','Zezinho','','ATIVADO','USER',1),(15,'luizinho','1','Luizinho','','DESATIVADO','USER',1),(16,'pele','123','Edson Arantes do Nascimento','pele@soccer.com','ATIVADO','ADMIN',1),(17,'margarida','123','Margarida','margarida@disney.com','ATIVADO','USER',1),(18,'patolino','123','Patolino','patolino@disney.com','ATIVADO','ADMIN',1),(19,'minnie.mouse','123','Minnie Mouse','minnie.mouse@disney.com','ATIVADO','ADMIN',1),(20,'chapola','123','Chapolin Colorado','','ATIVADO','ADMIN',1),(21,'allan','allan','Allan','','ATIVADO','ADMIN',1),(22,'novo.usuario','123','Novo Usuario','novo@usuario.com','ATIVADO','USER',1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pontos`
--

DROP TABLE IF EXISTS `pontos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pontos` (
  `id_ponto` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) DEFAULT NULL,
  `hora_ponto` datetime DEFAULT NULL,
  `hora_salva` datetime DEFAULT NULL,
  `id_usuario_edit` int(11) DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL,
  `ip` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_ponto`),
  KEY `fk1` (`id_usuario`),
  KEY `fk2` (`id_usuario_edit`),
  CONSTRAINT `fk1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk2` FOREIGN KEY (`id_usuario_edit`) REFERENCES `usuarios` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=219 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pontos`
--

LOCK TABLES `pontos` WRITE;
/*!40000 ALTER TABLE `pontos` DISABLE KEYS */;
INSERT INTO `pontos` VALUES (42,1,'2012-07-11 11:06:00','2012-07-11 11:06:39',1,0,'127.0.0.1'),(43,4,'2012-07-11 11:06:00','2012-07-11 11:06:50',4,0,'127.0.0.1'),(44,9,'2012-07-11 11:06:00','2012-07-11 11:06:56',9,0,'127.0.0.1'),(45,9,'2012-07-11 11:10:00','2012-07-11 11:10:26',9,1,'127.0.0.1'),(46,9,'2012-07-11 11:13:00','2012-07-11 11:13:06',9,2,'127.0.0.1'),(47,1,'2012-07-11 11:13:00','2012-07-11 11:13:11',1,1,'127.0.0.1'),(48,1,'2012-07-11 11:12:00','2012-07-24 09:25:00',1,2,'127.0.0.1'),(49,4,'2012-07-11 11:13:00','2012-07-11 11:13:58',4,1,'127.0.0.1'),(50,4,'2012-07-11 11:14:00','2012-07-11 11:14:01',4,2,'127.0.0.1'),(51,1,'2012-07-11 11:25:00','2012-07-24 11:24:39',1,3,'127.0.0.1'),(53,21,'2012-07-11 12:48:00','2012-07-11 12:48:31',21,0,'172.28.5.18'),(55,21,'2012-07-11 12:58:00','2012-07-24 10:27:02',1,1,'127.0.0.1'),(60,1,'2012-07-12 09:23:00','2012-07-12 09:23:59',1,0,'127.0.0.1'),(61,11,'2012-07-12 09:24:00','2012-07-12 09:24:51',11,0,'127.0.0.1'),(62,11,'2012-07-12 09:25:00','2012-07-12 09:25:26',11,1,'127.0.0.1'),(63,1,'2012-07-12 09:32:00','2012-07-24 09:24:44',1,1,'127.0.0.1'),(64,1,'2012-07-12 09:33:00','2012-07-12 09:33:23',1,2,'127.0.0.1'),(66,1,'2012-07-12 12:54:00','2012-07-12 12:54:51',1,4,'127.0.0.1'),(68,4,'2012-07-12 14:53:00','2012-07-12 14:53:15',4,0,'127.0.0.1'),(69,4,'2012-07-12 14:54:00','2012-07-12 14:54:50',4,1,'127.0.0.1'),(70,4,'2012-07-12 14:54:00','2012-07-12 14:54:58',4,2,'127.0.0.1'),(71,4,'2012-07-12 14:55:00','2012-07-12 14:55:07',4,3,'127.0.0.1'),(72,16,'2012-07-12 14:55:00','2012-07-12 14:55:39',16,0,'127.0.0.1'),(73,16,'2012-07-12 14:56:00','2012-07-12 14:56:36',16,1,'127.0.0.1'),(74,16,'2012-07-12 14:56:00','2012-07-12 14:56:41',16,2,'127.0.0.1'),(75,16,'2012-07-12 14:57:00','2012-07-12 14:57:29',16,3,'127.0.0.1'),(76,5,'2012-07-12 14:58:00','2012-07-12 14:58:42',5,0,'127.0.0.1'),(77,5,'2012-07-12 14:59:00','2012-07-12 14:59:02',5,1,'127.0.0.1'),(78,1,'2012-07-13 08:21:00','2012-07-13 08:21:44',1,0,'127.0.0.1'),(79,1,'2012-07-13 08:23:00','2012-07-13 08:23:24',1,1,'127.0.0.1'),(80,1,'2012-07-13 08:26:00','2012-07-13 08:26:26',1,2,'127.0.0.1'),(81,1,'2012-07-13 08:29:00','2012-07-13 08:29:04',1,3,'127.0.0.1'),(84,1,'2012-07-16 08:57:00','2012-07-16 08:57:41',1,0,'127.0.0.1'),(85,1,'2012-07-16 08:58:00','2012-07-16 08:58:59',1,1,'127.0.0.1'),(86,1,'2012-07-16 09:00:00','2012-07-24 14:18:01',1,2,'127.0.0.1'),(87,1,'2012-07-16 09:04:00','2012-07-24 14:31:46',1,3,'127.0.0.1'),(91,1,'2012-07-17 08:27:00','2012-07-17 08:27:51',1,0,'127.0.0.1'),(92,1,'2012-07-17 08:37:00','2012-07-24 09:24:25',1,1,'127.0.0.1'),(96,1,'2012-07-18 08:06:00','2012-07-18 08:06:01',1,0,'127.0.0.1'),(97,1,'2012-07-18 08:07:00','2012-07-24 14:17:41',1,1,'127.0.0.1'),(98,1,'2012-07-19 10:17:00','2012-07-19 10:17:35',1,0,'127.0.0.1'),(99,1,'2012-07-19 13:51:00','2012-07-19 13:51:10',1,1,'127.0.0.1'),(100,1,'2012-07-19 13:56:00','2012-07-19 13:56:26',1,2,'127.0.0.1'),(101,1,'2012-07-19 13:57:00','2012-07-19 13:57:20',1,3,'127.0.0.1'),(102,1,'2012-07-20 08:30:00','2012-07-20 08:30:07',1,0,'127.0.0.1'),(103,1,'2012-07-20 09:23:00','2012-07-20 09:23:11',1,1,'127.0.0.1'),(104,1,'2012-07-20 09:34:00','2012-07-20 09:34:56',1,2,'127.0.0.1'),(105,1,'2012-07-20 10:11:00','2012-07-24 09:23:54',1,3,'127.0.0.1'),(123,1,'2012-07-23 09:58:00','2012-07-23 09:58:43',1,0,'127.0.0.1'),(125,1,'2012-07-23 10:07:00','2012-07-23 10:07:34',1,1,'127.0.0.1'),(131,1,'2012-07-23 14:13:00','2012-07-23 14:13:29',1,2,'127.0.0.1'),(132,1,'2012-07-23 14:44:00','2012-07-24 09:23:42',1,3,'127.0.0.1'),(137,21,'2012-07-11 13:20:00','2012-07-24 11:00:02',1,3,'127.0.0.1'),(138,21,'2012-07-11 13:50:00','2012-07-24 11:00:28',1,4,'127.0.0.1'),(156,1,'2012-07-24 14:00:00','2012-07-24 14:14:08',1,0,'127.0.0.1'),(157,1,'2012-07-24 15:20:00','2012-07-24 14:14:22',1,NULL,'127.0.0.1'),(158,1,'2012-07-24 15:30:00','2012-07-24 14:14:04',1,NULL,'127.0.0.1'),(161,1,'2012-07-24 14:42:00','2012-07-24 14:42:51',1,1,'127.0.0.1'),(162,1,'2012-07-24 14:45:00','2012-07-24 14:45:34',1,2,'127.0.0.1'),(163,1,'2012-07-24 14:52:00','2012-07-24 14:52:53',1,3,'127.0.0.1'),(164,1,'2012-07-25 08:26:00','2012-07-25 08:26:13',1,0,'127.0.0.1'),(165,1,'2012-07-25 09:00:00','2012-07-25 08:26:57',1,NULL,'127.0.0.1'),(166,3,'2012-07-26 08:25:00','2012-07-26 08:25:22',3,0,'127.0.0.1'),(167,3,'2012-07-26 08:26:00','2012-07-26 08:26:30',3,1,'127.0.0.1'),(168,1,'2012-07-26 08:28:00','2012-07-26 08:28:49',1,0,'127.0.0.1'),(169,3,'2012-07-26 08:48:00','2012-07-26 08:48:31',3,2,'127.0.0.1'),(170,1,'2012-07-26 08:52:00','2012-07-26 08:52:23',1,1,'127.0.0.1'),(173,1,'2012-07-05 12:00:00','2012-07-26 14:37:19',1,0,'127.0.0.1'),(174,1,'2012-07-05 15:00:00','2012-07-26 14:37:19',1,1,'127.0.0.1'),(175,1,'2012-07-05 15:30:00','2012-07-26 14:37:19',1,2,'127.0.0.1'),(176,1,'2012-07-05 18:00:00','2012-07-26 14:37:19',1,3,'127.0.0.1'),(177,1,'2012-07-02 08:00:00','2012-07-26 14:39:49',1,0,'127.0.0.1'),(178,1,'2012-07-02 11:30:00','2012-07-26 14:39:49',1,1,'127.0.0.1'),(179,1,'2012-07-01 10:00:00','2012-07-27 08:43:45',1,0,'127.0.0.1'),(180,1,'2012-07-27 08:40:00','2012-07-27 08:40:18',1,0,'127.0.0.1'),(181,1,'2012-07-01 12:00:00','2012-07-27 08:43:35',1,NULL,'127.0.0.1'),(182,1,'2012-07-01 13:00:00','2012-07-27 08:43:55',1,NULL,'127.0.0.1'),(183,1,'2012-07-01 14:00:00','2012-07-27 08:50:17',1,NULL,'127.0.0.1'),(184,1,'2012-07-27 09:00:00','2012-07-27 08:50:31',1,NULL,'127.0.0.1'),(185,1,'2012-07-28 08:50:00','2012-07-27 08:51:07',1,0,'127.0.0.1'),(187,1,'2012-07-28 09:00:00','2012-07-27 09:47:22',1,NULL,'127.0.0.1'),(189,1,'2012-07-27 10:10:00','2012-07-27 10:10:14',1,2,'127.0.0.1'),(190,3,'2012-03-01 10:22:00','2012-07-27 10:22:31',1,0,'127.0.0.1'),(192,1,'2012-03-04 10:23:00','2012-07-27 10:23:21',1,0,'127.0.0.1'),(193,1,'2012-02-01 00:00:00','2012-07-27 10:23:35',1,0,'127.0.0.1'),(195,21,'2012-01-01 00:00:00','2012-07-27 10:31:00',1,0,'127.0.0.1'),(196,21,'2012-04-01 00:00:00','2012-07-27 10:34:40',1,0,'127.0.0.1'),(197,21,'2012-10-30 00:00:00','2012-07-27 10:37:03',1,0,'127.0.0.1'),(198,21,'2012-01-07 11:11:00','2012-07-27 10:37:58',1,0,'127.0.0.1'),(204,1,'2012-07-27 11:10:00','2012-07-27 11:10:46',1,3,'127.0.0.1'),(206,1,'2012-01-01 07:00:00','2012-07-27 11:39:54',1,0,'127.0.0.1'),(207,1,'2012-07-27 11:24:00','2012-07-27 11:24:29',1,4,'127.0.0.1'),(208,3,'2012-02-29 23:59:00','2012-07-27 11:24:55',1,0,'127.0.0.1'),(209,3,'2012-12-01 01:00:00','2012-07-27 11:25:19',1,0,'127.0.0.1'),(210,1,'2012-07-27 11:31:00','2012-07-27 11:31:42',1,5,'127.0.0.1'),(211,1,'2012-12-01 01:00:00','2012-07-27 11:32:26',1,0,'127.0.0.1'),(212,3,'2012-02-01 02:00:00','2012-07-27 11:45:56',1,0,'127.0.0.1'),(213,3,'2012-01-01 10:09:00','2012-07-27 13:23:12',1,0,'127.0.0.1'),(214,3,'2012-01-02 01:00:00','2012-07-27 11:52:32',1,0,'127.0.0.1'),(215,3,'2012-07-28 09:59:00','2012-07-27 12:59:50',1,0,'127.0.0.1'),(216,3,'2012-07-28 10:00:00','2012-07-27 12:59:36',1,NULL,'127.0.0.1'),(217,3,'2012-01-03 01:00:00','2012-07-27 13:04:15',1,0,'127.0.0.1'),(218,3,'2012-07-28 11:00:00','2012-07-27 13:21:21',1,NULL,'127.0.0.1');
/*!40000 ALTER TABLE `pontos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-07-27 13:35:07
