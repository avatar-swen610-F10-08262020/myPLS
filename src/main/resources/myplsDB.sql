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
-- Table structure for table `Classlist`
--

DROP TABLE IF EXISTS `Classlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Classlist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_id` int NOT NULL,
  `user_id` int NOT NULL,
  `course_grade` varchar(45) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Classlist`
--

LOCK TABLES `Classlist` WRITE;
/*!40000 ALTER TABLE `Classlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `Classlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Course`
--

DROP TABLE IF EXISTS `Course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Course` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `course_name` mediumtext NOT NULL,
  `description` longtext NOT NULL,
  `class_size` varchar(45) DEFAULT NULL,
  `start_date` varchar(45) DEFAULT NULL,
  `end_date` varchar(45) DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `course_code` varchar(45) DEFAULT NULL,
  `status` int DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Course`
--

LOCK TABLES `Course` WRITE;
/*!40000 ALTER TABLE `Course` DISABLE KEYS */;
INSERT INTO `Course` VALUES (1,'SWEN','Foundation of Software Engineering','20','30-03-2020','01-06-2020','2020-10-13 10:55:45','2101',1),(2,'PhD Seminar','seminar on phd','50','01-01-2020','03-03-2020','2020-10-13 12:26:48','3101',0),(3,'Statistical Machine Learning','dsgfsdgsdfgdsfg','34','2020-10-01','2020-10-31','2020-10-18 16:18:12','C1002',1),(4,'Statistical Machine Learning 2','Foundation of Software Engineering','12','2020-10-01','2020-10-31','2020-10-18 16:23:46','C1003',1),(5,'Statistical Machine Learning V4','dsfgsdfgsdfgsdfg','12','2020-10-01','2020-10-31','2020-10-18 16:29:29','C1020',1),(6,'Data Science','gsdfgsfdg sdfgsdfg','100','2020-10-01','2020-11-28','2020-10-18 16:31:24','C1078',1),(7,'HCI Advanced','Deep learning (also known as deep structured learning) is part of a broader family of machine learning methods based on artificial neural networks with representation learning. Learning can be supervised,','23','2020-10-01','','2020-10-18 16:45:16','C1022',1),(8,'HCI Advanced V2','Deep learning (also known as deep structured learning) is part of a broader family of machine learning methods based on artificial neural networks with representation learning. Learning can be supervised,','43','2020-10-01','2021-01-02','2020-10-18 16:47:18','C1079',1),(9,'NLP','or in a better way we can have like this\r\n\r\nLet\'s say your primary key is an Integer and object you save is \"ticket\", then you can get it like this. When you save the object, id is always returned','23','2020-10-01','2021-03-26','2020-10-18 16:51:50','NLP101',0),(10,'NLP Advance','newCourse.getId()','34','2020-10-01','2021-03-19','2020-10-18 16:54:06','C700N',1),(11,'Computer Architecture','Only if you never expect to run any existing software and expect to\r\nwrite everything from assemblers and compilers to process managers and\r\ndevice drivers yourself. The assumptions about power of 2 addressing\r\nand IEEE arithmetic are embedded very deeply in','34','2020-10-01','2021-04-30','2020-10-18 16:57:06','C2002',1),(12,'HCI Advanced V3','Once your are done annotating your image dataset in the Pascal VOC format, you can use ImageAI’s custom detection training code to train a new detectin model on your datasets, using just 6-lines of Python code. See the tutorial and documentations linked b','23','2020-10-01','2021-07-03','2020-10-18 16:59:50','C1029',1),(13,'System Engineering','Once your are done annotating your image dataset in the Pascal VOC format, you can use ImageAI’s custom detection training code to train a new detectin model on your datasets, using just 6-lines of Python code. See the tutorial and documentations linked b','34','2020-10-02','2021-05-29','2020-10-18 17:01:55','E1008',1),(14,'SW','adfadf','23','2020-10-01','2021-04-23','2020-10-18 17:06:54','C601',1),(15,'HCI ','sdfgsdfgsdfgsdfg','12','2020-10-01','2021-04-10','2020-10-18 17:10:46','HCI1001',1),(16,'SWEN 610','software engineering','30','2020-11-01','2021-03-12','2020-11-07 16:21:19','C1056',0),(17,'SWEN 611','swen','12','2020-11-01','2021-01-23','2020-11-07 16:26:30','C1099',1);
/*!40000 ALTER TABLE `Course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Course_Dependency`
--

DROP TABLE IF EXISTS `Course_Dependency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Course_Dependency` (
  `id` int NOT NULL AUTO_INCREMENT,
  `parent_id` int NOT NULL,
  `dependent_id` int NOT NULL,
  `status` int DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Course_Dependency`
--

LOCK TABLES `Course_Dependency` WRITE;
/*!40000 ALTER TABLE `Course_Dependency` DISABLE KEYS */;
INSERT INTO `Course_Dependency` VALUES (1,4,5,1,'2020-10-19 18:44:34'),(2,6,5,1,'2020-10-19 18:44:34'),(3,7,5,0,'2020-10-19 18:44:34'),(4,8,5,0,'2020-10-19 18:45:02'),(5,9,5,0,'2020-10-19 18:45:02'),(6,11,5,0,'2020-10-19 18:50:35'),(7,12,5,0,'2020-10-19 18:50:36'),(8,13,5,0,'2020-10-19 18:50:36'),(9,2,5,0,'2020-10-19 18:51:02'),(10,3,5,0,'2020-10-19 18:51:02'),(11,14,5,1,'2020-10-19 18:52:42'),(12,15,5,1,'2020-10-19 18:52:42'),(13,11,9,0,'2020-10-19 18:54:14'),(14,8,9,0,'2020-10-19 18:54:14'),(15,10,9,0,'2020-10-19 18:54:14'),(16,7,9,0,'2020-10-19 18:55:06'),(17,13,9,1,'2020-10-19 18:55:30'),(18,14,9,1,'2020-10-19 18:55:30'),(19,7,6,1,'2020-10-19 18:58:23'),(20,8,6,0,'2020-10-19 18:58:23'),(21,5,6,1,'2020-10-19 18:58:28'),(22,9,6,0,'2020-10-19 18:58:28'),(23,3,17,1,'2020-11-07 16:28:34'),(24,4,17,1,'2020-11-07 16:28:34');
/*!40000 ALTER TABLE `Course_Dependency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Course_Feedback`
--

DROP TABLE IF EXISTS `Course_Feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Course_Feedback` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `course_id` int NOT NULL,
  `feedback` longtext,
  `rating` int NOT NULL,
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Course_Feedback`
--

LOCK TABLES `Course_Feedback` WRITE;
/*!40000 ALTER TABLE `Course_Feedback` DISABLE KEYS */;
INSERT INTO `Course_Feedback` VALUES (1,1,1,'poor teaching',3,'2020-10-20 18:21:50',1),(2,12,1,'Very instructive',5,'2020-10-20 18:21:50',1),(3,12,11,'I have learnt many things from this course.',5,'2020-10-22 09:21:45',1),(4,5,11,'This course delivers least amount of information to me.',2,'2020-10-22 09:21:45',1),(5,3,11,'I like this course very much',3,'2020-10-22 09:21:45',1),(6,3,11,'This course help me to learn the computer architecture',4,'2020-11-07 16:31:53',1);
/*!40000 ALTER TABLE `Course_Feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Course_Week`
--

DROP TABLE IF EXISTS `Course_Week`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Course_Week` (
  `id` int NOT NULL AUTO_INCREMENT,
  `week_number` int DEFAULT NULL,
  `week_name` varchar(45) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Course_Week`
--

LOCK TABLES `Course_Week` WRITE;
/*!40000 ALTER TABLE `Course_Week` DISABLE KEYS */;
INSERT INTO `Course_Week` VALUES (1,1,'Week 1','2020-10-26 20:19:12'),(2,2,'Week 2','2020-10-26 20:19:12'),(3,3,'Week 3','2020-10-26 20:19:12'),(4,4,'Week 4','2020-10-26 20:19:12'),(5,5,'Week 5','2020-10-26 20:19:12'),(6,6,'Week 6','2020-10-26 20:19:12'),(7,7,'Week 7','2020-10-26 20:19:12'),(8,8,'Week 8','2020-10-26 20:19:12'),(9,9,'Week 9','2020-10-26 20:19:12'),(10,10,'Week 10','2020-10-26 20:19:12'),(11,11,'Week 11','2020-10-26 20:19:12'),(12,12,'Week 12','2020-10-26 20:19:12'),(13,13,'Week 13','2020-10-26 20:19:12');
/*!40000 ALTER TABLE `Course_Week` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Learner_course`
--

DROP TABLE IF EXISTS `Learner_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Learner_course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_id` int NOT NULL,
  `user_id` int NOT NULL,
  `status` int DEFAULT '1',
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `completed` int DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `course_id_UNIQUE` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Learner_course`
--

LOCK TABLES `Learner_course` WRITE;
/*!40000 ALTER TABLE `Learner_course` DISABLE KEYS */;
INSERT INTO `Learner_course` VALUES (1,7,1,1,'2020-11-07 13:05:27',0);
/*!40000 ALTER TABLE `Learner_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Lesson`
--

DROP TABLE IF EXISTS `Lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Lesson` (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_id` int DEFAULT NULL,
  `week_id` int DEFAULT NULL,
  `lesson_content_path` text,
  `content_type` varchar(45) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Lesson`
--

LOCK TABLES `Lesson` WRITE;
/*!40000 ALTER TABLE `Lesson` DISABLE KEYS */;
INSERT INTO `Lesson` VALUES (1,1,1,'SWEN.pdf','pdf',1,'2020-10-26 20:42:48'),(2,1,2,'swen_2','docx',1,'2020-10-26 20:45:14'),(3,1,3,'audio.mp3','mp3',1,'2020-10-26 20:45:14'),(4,1,4,'video.mov','mov',1,'2020-10-26 20:45:14'),(6,11,2,'CA_1603904245849.pdf','application/pdf',1,'2020-10-28 12:57:25'),(7,11,1,'CA_1603904371796.pdf','application/pdf',1,'2020-10-28 12:59:31'),(8,6,2,'DS_1604152069732.pdf','application/pdf',1,'2020-10-31 09:47:49'),(9,6,1,'DS_1_1604152109162.mov','video/quicktime',1,'2020-10-31 09:48:29'),(10,6,1,'DS_1604152165590.png','image/png',1,'2020-10-31 09:49:25');
/*!40000 ALTER TABLE `Lesson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Professor_Course`
--

DROP TABLE IF EXISTS `Professor_Course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Professor_Course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_id` int NOT NULL,
  `user_id` int NOT NULL,
  `status` int DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Professor_Course`
--

LOCK TABLES `Professor_Course` WRITE;
/*!40000 ALTER TABLE `Professor_Course` DISABLE KEYS */;
INSERT INTO `Professor_Course` VALUES (1,11,2,1,'2020-10-18 16:57:06'),(2,12,4,1,'2020-10-18 16:59:50'),(3,13,4,1,'2020-10-18 17:02:01'),(4,14,2,1,'2020-10-18 17:06:54'),(5,15,2,1,'2020-10-18 17:10:46'),(6,10,2,1,'2020-10-18 18:47:25'),(7,9,4,1,'2020-10-18 18:47:25'),(8,8,2,1,'2020-10-18 18:47:25'),(9,7,4,1,'2020-10-18 18:47:25'),(10,6,4,1,'2020-10-18 18:47:25'),(11,5,4,1,'2020-10-18 18:47:25'),(12,4,2,1,'2020-10-18 18:47:25'),(13,3,4,1,'2020-10-18 18:47:25'),(14,2,4,1,'2020-10-18 18:47:25'),(15,1,2,1,'2020-10-18 18:47:25'),(16,16,2,1,'2020-11-07 16:21:19'),(17,17,2,1,'2020-11-07 16:26:30');
/*!40000 ALTER TABLE `Professor_Course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Quiz`
--

DROP TABLE IF EXISTS `Quiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Quiz` (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_id` varchar(45) DEFAULT NULL,
  `quiz_description` mediumtext,
  `quiz_length` int DEFAULT NULL,
  `start_date` varchar(45) DEFAULT NULL,
  `end_date` varchar(45) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Quiz`
--

LOCK TABLES `Quiz` WRITE;
/*!40000 ALTER TABLE `Quiz` DISABLE KEYS */;
INSERT INTO `Quiz` VALUES (1,'11','midterm',30,'2020-11-01','2020-12-18',1,'2020-11-01 11:25:14');
/*!40000 ALTER TABLE `Quiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Quiz_Options`
--

DROP TABLE IF EXISTS `Quiz_Options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Quiz_Options` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question_id` int DEFAULT NULL,
  `option_number` int DEFAULT NULL,
  `description` longtext,
  `option_status` int DEFAULT '0',
  `status` int DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Quiz_Options`
--

LOCK TABLES `Quiz_Options` WRITE;
/*!40000 ALTER TABLE `Quiz_Options` DISABLE KEYS */;
INSERT INTO `Quiz_Options` VALUES (1,1,1,'computer',0,1,'2020-11-01 11:25:14'),(2,1,2,'architecture',1,1,'2020-11-01 11:25:14'),(3,1,3,'value 3',0,1,'2020-11-01 11:25:14'),(4,1,4,'specific',0,1,'2020-11-01 11:25:14'),(5,1,5,'value 5',0,1,'2020-11-01 11:25:14'),(6,2,1,'layer 7',0,1,'2020-11-01 11:25:14'),(7,2,2,'information disk layer',1,1,'2020-11-01 11:25:14'),(8,2,3,'operating system',0,1,'2020-11-01 11:25:14'),(9,2,4,'database',0,1,'2020-11-01 11:25:14'),(10,2,5,'dataset 2',0,1,'2020-11-01 11:25:14'),(11,3,1,'1',0,1,'2020-11-01 11:25:14'),(12,3,2,'2',1,1,'2020-11-01 11:25:14'),(13,3,3,'4',0,1,'2020-11-01 11:25:14'),(14,3,4,'5',0,1,'2020-11-01 11:25:14'),(15,3,5,'7',0,1,'2020-11-01 11:25:14'),(16,4,1,'3',1,0,'2020-11-06 02:36:13'),(17,4,1,'Design 1',0,1,'2020-11-06 18:45:17'),(18,4,2,'Design 2',0,1,'2020-11-06 18:45:17'),(19,4,3,'Design 3',0,1,'2020-11-06 18:45:17'),(20,4,4,'Design 4',1,1,'2020-11-06 18:45:17'),(21,4,5,'Design 5',0,1,'2020-11-06 18:45:18');
/*!40000 ALTER TABLE `Quiz_Options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Quiz_Questions`
--

DROP TABLE IF EXISTS `Quiz_Questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Quiz_Questions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quiz_id` int DEFAULT NULL,
  `question_number` int DEFAULT NULL,
  `question_description` longtext,
  `status` int DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Quiz_Questions`
--

LOCK TABLES `Quiz_Questions` WRITE;
/*!40000 ALTER TABLE `Quiz_Questions` DISABLE KEYS */;
INSERT INTO `Quiz_Questions` VALUES (1,1,1,'What is computer architecture level 1?',1,'2020-11-01 11:25:14'),(2,1,2,'How to connect OS with network layer?',1,'2020-11-01 11:25:14'),(3,1,3,'How many Layers OS version have?',1,'2020-11-01 11:25:14'),(4,1,4,'What is system design?',1,'2020-11-06 18:45:17');
/*!40000 ALTER TABLE `Quiz_Questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` longtext NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` longtext CHARACTER SET utf32 COLLATE utf32_general_ci NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `user_type_id` int NOT NULL,
  `status` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `user_type_id_idx` (`user_type_id`),
  CONSTRAINT `user_type_id` FOREIGN KEY (`user_type_id`) REFERENCES `User_Type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'Mahdi','Khurrana','mahdi@gmail.com','12345678','2020-09-28 21:37:12',3,1),(2,'shakil','zaman','shakil@gmail.com','12345678','2020-09-28 21:37:53',2,1),(3,'Akhter','amin','farhanbuet09@gmail.com','12345678','2020-09-28 21:38:24',1,1),(4,'Pradeep','Bajracharya','pb8294@rit.edu','12345678','2020-09-28 21:39:48',2,1),(5,'Ahmedul','toslim','toslim@gmail.com','123456789','2020-09-29 03:57:38',3,0),(9,'jhau','meng','jharu3432@gmail.com','12345678','2020-10-01 01:05:19',3,1),(12,'avatar','swen','swenavatar@gmail.com','12345678','2020-10-01 03:57:25',3,0),(13,'jake','maine','jake@gmail.com','Jake@610','2020-10-01 13:24:40',3,0),(14,'jake','anne','jakea@gmail.com','Far@1234','2020-10-01 13:45:41',3,1),(15,'Akhter','Amin','jaja@gmail.com','Farhan@56','2020-10-02 03:27:04',3,0),(16,'admin','admin','admin@gmail.com','Admin@2020','2020-11-08 00:40:41',3,0),(17,'jenny','clinton','jenny@gmail.com','Jenny@1234','2020-11-08 00:56:08',2,1);
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

-- Dump completed on 2020-11-07 20:12:45
