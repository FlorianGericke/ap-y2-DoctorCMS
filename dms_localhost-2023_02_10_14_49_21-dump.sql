-- MySQL dump 10.13  Distrib 8.0.31, for macos12 (arm64)
--
-- Host: 127.0.0.1    Database: dms
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `addresses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `street` varchar(100) NOT NULL,
  `house_number` varchar(100) DEFAULT NULL,
  `zip` int NOT NULL,
  `location` varchar(100) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `state` varchar(100) NOT NULL DEFAULT 'created',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointments`
--

DROP TABLE IF EXISTS `appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `state` varchar(100) NOT NULL DEFAULT 'created',
  `patient_id` int DEFAULT NULL,
  `doctor_id` int DEFAULT NULL,
  `facility_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `appointments_doctors_id_fk` (`doctor_id`),
  KEY `appointments_facilities_id_fk` (`facility_id`),
  KEY `appointments_patients_insurance_number_fk` (`patient_id`),
  CONSTRAINT `appointments_doctors_id_fk` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`id`),
  CONSTRAINT `appointments_facilities_id_fk` FOREIGN KEY (`facility_id`) REFERENCES `facilities` (`id`),
  CONSTRAINT `appointments_patients_insurance_number_fk` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`insurance_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments`
--

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;
/*!40000 ALTER TABLE `appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departments`
--

DROP TABLE IF EXISTS `departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `state` varchar(100) NOT NULL DEFAULT 'created',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departments`
--

LOCK TABLES `departments` WRITE;
/*!40000 ALTER TABLE `departments` DISABLE KEYS */;
/*!40000 ALTER TABLE `departments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctors`
--

DROP TABLE IF EXISTS `doctors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctors` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `state` varchar(100) NOT NULL DEFAULT 'created',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctors`
--

LOCK TABLES `doctors` WRITE;
/*!40000 ALTER TABLE `doctors` DISABLE KEYS */;
INSERT INTO `doctors` (`id`, `first_name`, `last_name`, `created_at`, `updated_at`, `deleted_at`, `state`) VALUES (1,'Leonora','Colette','1993-03-01 10:06:59',NULL,NULL,'created'),(2,'Abraham','Ashley','1988-09-24 12:28:23',NULL,NULL,'created'),(3,'Deborah','Ivy','2004-10-29 12:28:34',NULL,NULL,'created'),(4,'Kay','Hope','2005-06-27 22:39:02',NULL,NULL,'created'),(5,'Vilma','Lila','2016-11-14 20:14:11',NULL,NULL,'created'),(6,'Austen','Clint','2007-06-25 16:10:10',NULL,NULL,'created'),(7,'Kirsten','Tyra','2017-10-04 03:49:54',NULL,NULL,'created'),(8,'Melvin','Palmer','2001-05-14 19:04:50',NULL,NULL,'created'),(9,'Jenny','Lucia','2003-01-25 07:16:59',NULL,NULL,'created'),(10,'Austin','Cordelia','1995-10-29 00:35:30',NULL,NULL,'created'),(11,'Franklin','Vince','1986-07-13 15:22:41',NULL,NULL,'created'),(12,'Nate','Eudora','2017-03-12 17:01:46',NULL,NULL,'created'),(13,'Ultan','Thomas','2019-08-20 08:59:16',NULL,NULL,'created'),(14,'Colby','Justin','1981-10-28 18:34:36',NULL,NULL,'created'),(15,'Bubba','Alfred','1995-06-08 00:38:59',NULL,NULL,'created'),(16,'Joey','William','1983-12-05 02:57:20',NULL,NULL,'created'),(17,'Annabelle','Norman','1988-11-18 12:59:04',NULL,NULL,'created'),(18,'Marianne','Darlene','1980-06-09 16:03:21',NULL,NULL,'created'),(19,'Eva','Bubba','1988-07-02 05:46:08',NULL,NULL,'created'),(20,'Julian','Priscilla','1995-08-31 11:59:56',NULL,NULL,'created'),(21,'Gregory','Miles','2018-11-06 10:56:21',NULL,NULL,'created'),(22,'Humphrey','Natalie','1998-08-10 04:25:42',NULL,NULL,'created'),(23,'Dwight','Rosemary','1990-01-07 04:30:45',NULL,NULL,'created'),(24,'Rhiannon','William','2019-02-18 22:07:48',NULL,NULL,'created'),(25,'Sibyl','Bridget','1993-04-13 12:40:11',NULL,NULL,'created'),(26,'Jacqueline','Imelda','1992-03-21 19:53:40',NULL,NULL,'created'),(27,'Ben','Ernest','1984-01-12 20:25:53',NULL,NULL,'created'),(28,'Roger','Cliff','2006-07-14 21:03:06',NULL,NULL,'created'),(29,'Murray','Buck','2006-01-22 05:49:40',NULL,NULL,'created'),(30,'Ernest','Poppy','1989-12-14 17:32:28',NULL,NULL,'created');
/*!40000 ALTER TABLE `doctors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facilities`
--

DROP TABLE IF EXISTS `facilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facilities` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `state` varchar(100) NOT NULL DEFAULT 'created',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facilities`
--

LOCK TABLES `facilities` WRITE;
/*!40000 ALTER TABLE `facilities` DISABLE KEYS */;
/*!40000 ALTER TABLE `facilities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facility_address`
--

DROP TABLE IF EXISTS `facility_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facility_address` (
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `state` varchar(100) NOT NULL DEFAULT 'created',
  `facility_id` int DEFAULT NULL,
  `address_id` int DEFAULT NULL,
  KEY `facility_address_addresses_id_fk` (`address_id`),
  KEY `facility_address_facilities_id_fk` (`facility_id`),
  CONSTRAINT `facility_address_addresses_id_fk` FOREIGN KEY (`address_id`) REFERENCES `addresses` (`id`),
  CONSTRAINT `facility_address_facilities_id_fk` FOREIGN KEY (`facility_id`) REFERENCES `facilities` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facility_address`
--

LOCK TABLES `facility_address` WRITE;
/*!40000 ALTER TABLE `facility_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `facility_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facility_department`
--

DROP TABLE IF EXISTS `facility_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facility_department` (
  `facility_id` int DEFAULT NULL,
  `department_id` int DEFAULT NULL,
  `doctor_id` int DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updateAt` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `state` varchar(100) DEFAULT 'created',
  KEY `facility_department_departments_id_fk` (`department_id`),
  KEY `facility_department_facilities_id_fk` (`facility_id`),
  KEY `facility_department_doctors_id_fk` (`doctor_id`),
  CONSTRAINT `facility_department_departments_id_fk` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`),
  CONSTRAINT `facility_department_doctors_id_fk` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`id`),
  CONSTRAINT `facility_department_facilities_id_fk` FOREIGN KEY (`facility_id`) REFERENCES `facilities` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facility_department`
--

LOCK TABLES `facility_department` WRITE;
/*!40000 ALTER TABLE `facility_department` DISABLE KEYS */;
/*!40000 ALTER TABLE `facility_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patients` (
  `insurance_number` int NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `age` int NOT NULL,
  `phone` varchar(100) NOT NULL,
  `address_id` int DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `state` varchar(100) NOT NULL DEFAULT 'created',
  PRIMARY KEY (`insurance_number`),
  KEY `patients_addresses_id_fk` (`address_id`),
  CONSTRAINT `patients_addresses_id_fk` FOREIGN KEY (`address_id`) REFERENCES `addresses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-10 14:48:52
