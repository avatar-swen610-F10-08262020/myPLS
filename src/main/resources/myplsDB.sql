-- MySQL dump 10.13  Distrib 8.0.21, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: myplsDB
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `Access_Control_List`
--

DROP TABLE IF EXISTS `Access_Control_List`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Access_Control_List` (
  `id` int NOT NULL AUTO_INCREMENT,
  `operation` varchar(45) DEFAULT NULL,
  `user_type_permission` varchar(45) DEFAULT NULL,
  `acl_permission` varchar(45) DEFAULT NULL,
  `menu_name` varchar(45) DEFAULT NULL,
  `user_type_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_type_id_idx` (`user_type_id`),
  CONSTRAINT `user_type_id_acl` FOREIGN KEY (`user_type_id`) REFERENCES `User_Type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Access_Control_List`
--

LOCK TABLES `Access_Control_List` WRITE;
/*!40000 ALTER TABLE `Access_Control_List` DISABLE KEYS */;
INSERT INTO `Access_Control_List` VALUES (1,'User Modification','Can update User Status','Write','User_Modify',1),(2,'Browse User List','Can search User List','Read','User_Search',1),(3,'Browse User List','Can search User List','Read','User_Search',2),(4,'Course Creation','Can create Course','Write','Course_Create',1),(5,'Course Modify','Can add, upload Course material','Write','Course_Modify',1),(6,'Course Modify','Can add, upload Course material','Write','Course_Modify',2),(7,'Course Enroll','Can enroll into course','Write','Course_Enroll',3),(8,'Browse User List','Can search User List','Read','User_Search',3),(9,'Quiz Creation','Can create Quiz ','Write','Quiz_Create',1),(10,'Quiz Creation','Can create Quiz ','Write','Quiz_Create',2),(11,'Quiz Participation','Can participate Quiz','Write','Quiz_Participate',3),(12,'Browse Course','Can search Course List','Read','Course_Search',1),(13,'Browse Course','Can search Course List','Read','Course_Search',2),(14,'Browse Course','Can search Course List','Read','Course_Search',3),(15,'View Announcement',' Can see Announcement','Read','Announcement_View',1),(16,'View Announcement',' Can see Announcement','Read','Announcement_View',2),(17,'View Announcement',' Can see Announcement','Read','Announcement_View',3),(18,'Announcement Create','Can create Accouncment','Write','Announcement_Create',2),(19,'Announcement Create','Can create Accouncment','Write','Announcement_Create',1),(20,'Announcement Update','Can update Announcement','Write','Announcement_Update',1);
/*!40000 ALTER TABLE `Access_Control_List` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(16) NOT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(32) NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `user_type_id` int NOT NULL,
  `status` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `user_type_id_idx` (`user_type_id`),
  CONSTRAINT `user_type_id` FOREIGN KEY (`user_type_id`) REFERENCES `User_Type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'Mahdi','Khurrana','mahdi@gmail.com','12345678','2020-09-28 21:37:12',3,1),(2,'shakil','zaman','shakil@gmail.com','12345678','2020-09-28 21:37:53',2,1),(3,'Akhter','amin','farhanbuet09@gmail.com','12345678','2020-09-28 21:38:24',1,1),(4,'Pradeep','Bajracharya','pb8294@rit.edu','12345678','2020-09-28 21:39:48',2,1),(5,'ahmed','toslim','toslim@gmail.com','123456789','2020-09-29 03:57:38',3,0),(9,'jhau','meng','jharu3432@gmail.com','12345678','2020-10-01 01:05:19',3,1),(12,'avatar','swen','swenavatar@gmail.com','12345678','2020-10-01 03:57:25',3,0),(13,'jake','maine','jake@gmail.com','Jake@610','2020-10-01 13:24:40',3,0),(14,'jake','anne','jakea@gmail.com','Far@1234','2020-10-01 13:45:41',3,1),(15,'Akhter','Amin','jaja@gmail.com','Farhan@56','2020-10-02 03:27:04',3,0);
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User_Type`
--

DROP TABLE IF EXISTS `User_Type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User_Type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_type_id` int NOT NULL,
  `user_type_name` varchar(45) NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_type_id_UNIQUE` (`user_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User_Type`
--

LOCK TABLES `User_Type` WRITE;
/*!40000 ALTER TABLE `User_Type` DISABLE KEYS */;
INSERT INTO `User_Type` VALUES (1,1,'Admin','2020-09-28 17:35:58'),(2,2,'Professor','2020-09-28 17:35:58'),(3,3,'Learner','2020-09-28 17:35:58');
/*!40000 ALTER TABLE `User_Type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-03 12:08:47
