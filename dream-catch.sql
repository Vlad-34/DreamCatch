-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dream_catch
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `entries`
--

DROP TABLE IF EXISTS `entries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entries` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `duration` int NOT NULL,
  `energy` int NOT NULL,
  `stress` int NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entries`
--

LOCK TABLES `entries` WRITE;
/*!40000 ALTER TABLE `entries` DISABLE KEYS */;
INSERT INTO `entries` VALUES (1,'2023-04-01 00:00:00.000000','Am visat.',2,2,5,1),(2,'2023-04-01 00:00:00.000000','Am visat.',5,5,1,1),(3,'2023-04-01 00:00:00.000000','Am visat.',4,4,4,1),(4,'2023-04-01 00:00:00.000000','Am visat.',2,1,4,1),(5,'2023-04-01 00:00:00.000000','Am visat.',5,2,1,1),(6,'2023-04-01 00:00:00.000000','Am visat si eu.',2,3,2,2),(7,'2023-04-01 00:00:00.000000','Am visat si eu.',4,3,3,2),(8,'2023-04-01 00:00:00.000000','Am visat si eu.',3,4,3,2),(9,'2023-04-01 00:00:00.000000','Am visat si eu.',3,2,5,2),(10,'2023-04-01 00:00:00.000000','Am visat si eu.',1,2,3,2);
/*!40000 ALTER TABLE `entries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entry_tags`
--

DROP TABLE IF EXISTS `entry_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entry_tags` (
  `entry_id` bigint NOT NULL,
  `tags` varchar(255) DEFAULT NULL,
  KEY `FK8578vf1i1ayhjalfnom5vkunj` (`entry_id`),
  CONSTRAINT `FK8578vf1i1ayhjalfnom5vkunj` FOREIGN KEY (`entry_id`) REFERENCES `entries` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entry_tags`
--

LOCK TABLES `entry_tags` WRITE;
/*!40000 ALTER TABLE `entry_tags` DISABLE KEYS */;
INSERT INTO `entry_tags` VALUES (1,'ENERGIZED'),(2,'SCARED'),(4,'EXHAUSTED'),(4,'HAPPY'),(5,'SURPRIZED'),(5,'SAD'),(6,'SURPRIZED'),(6,'SAD'),(7,'EXHAUSTED'),(8,'SCARED'),(10,'SURPRIZED');
/*!40000 ALTER TABLE `entry_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'$2a$10$aM/y7pzqnhJmcHVUtoDTkeDjwS01eyxZFOF5z5Sb5ryT1w6xVmFFe','$2a$10$aM/y7pzqnhJmcHVUtoDTke','vlad'),(2,'$2a$10$vkOX8JodMbub3gUQpgDGD.LCkSQPQ8OaNjPzVn6n3AZH7Utzxid7S','$2a$10$vkOX8JodMbub3gUQpgDGD.','bianca');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-01 21:16:44
