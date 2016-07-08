-- MySQL dump 10.13  Distrib 5.6.30, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: ept2
-- ------------------------------------------------------
-- Server version	5.6.30-0ubuntu0.14.04.1

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
-- Current Database: `ept2`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ept2` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `ept2`;

--
-- Table structure for table `Answer_SEQ`
--

DROP TABLE IF EXISTS `Answer_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Answer_SEQ` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Answer_SEQ`
--

LOCK TABLES `Answer_SEQ` WRITE;
/*!40000 ALTER TABLE `Answer_SEQ` DISABLE KEYS */;
INSERT INTO `Answer_SEQ` VALUES (1360);
/*!40000 ALTER TABLE `Answer_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CategoryScore_SEQ`
--

DROP TABLE IF EXISTS `CategoryScore_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CategoryScore_SEQ` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CategoryScore_SEQ`
--

LOCK TABLES `CategoryScore_SEQ` WRITE;
/*!40000 ALTER TABLE `CategoryScore_SEQ` DISABLE KEYS */;
INSERT INTO `CategoryScore_SEQ` VALUES (1040);
/*!40000 ALTER TABLE `CategoryScore_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Category_SEQ`
--

DROP TABLE IF EXISTS `Category_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Category_SEQ` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Category_SEQ`
--

LOCK TABLES `Category_SEQ` WRITE;
/*!40000 ALTER TABLE `Category_SEQ` DISABLE KEYS */;
INSERT INTO `Category_SEQ` VALUES (1020);
/*!40000 ALTER TABLE `Category_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pencil_SEQ`
--

DROP TABLE IF EXISTS `Pencil_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Pencil_SEQ` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pencil_SEQ`
--

LOCK TABLES `Pencil_SEQ` WRITE;
/*!40000 ALTER TABLE `Pencil_SEQ` DISABLE KEYS */;
INSERT INTO `Pencil_SEQ` VALUES (1000);
/*!40000 ALTER TABLE `Pencil_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Progress_SEQ`
--

DROP TABLE IF EXISTS `Progress_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Progress_SEQ` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Progress_SEQ`
--

LOCK TABLES `Progress_SEQ` WRITE;
/*!40000 ALTER TABLE `Progress_SEQ` DISABLE KEYS */;
INSERT INTO `Progress_SEQ` VALUES (1030);
/*!40000 ALTER TABLE `Progress_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QuestionResponse_SEQ`
--

DROP TABLE IF EXISTS `QuestionResponse_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QuestionResponse_SEQ` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QuestionResponse_SEQ`
--

LOCK TABLES `QuestionResponse_SEQ` WRITE;
/*!40000 ALTER TABLE `QuestionResponse_SEQ` DISABLE KEYS */;
INSERT INTO `QuestionResponse_SEQ` VALUES (1040);
/*!40000 ALTER TABLE `QuestionResponse_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ResponseContent_SEQ`
--

DROP TABLE IF EXISTS `ResponseContent_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ResponseContent_SEQ` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ResponseContent_SEQ`
--

LOCK TABLES `ResponseContent_SEQ` WRITE;
/*!40000 ALTER TABLE `ResponseContent_SEQ` DISABLE KEYS */;
INSERT INTO `ResponseContent_SEQ` VALUES (1070);
/*!40000 ALTER TABLE `ResponseContent_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `School_SEQ`
--

DROP TABLE IF EXISTS `School_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `School_SEQ` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `School_SEQ`
--

LOCK TABLES `School_SEQ` WRITE;
/*!40000 ALTER TABLE `School_SEQ` DISABLE KEYS */;
INSERT INTO `School_SEQ` VALUES (1000);
/*!40000 ALTER TABLE `School_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Student_SEQ`
--

DROP TABLE IF EXISTS `Student_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Student_SEQ` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Student_SEQ`
--

LOCK TABLES `Student_SEQ` WRITE;
/*!40000 ALTER TABLE `Student_SEQ` DISABLE KEYS */;
INSERT INTO `Student_SEQ` VALUES (1030);
/*!40000 ALTER TABLE `Student_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TestComponent_SEQ`
--

DROP TABLE IF EXISTS `TestComponent_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TestComponent_SEQ` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TestComponent_SEQ`
--

LOCK TABLES `TestComponent_SEQ` WRITE;
/*!40000 ALTER TABLE `TestComponent_SEQ` DISABLE KEYS */;
INSERT INTO `TestComponent_SEQ` VALUES (1110);
/*!40000 ALTER TABLE `TestComponent_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TestSession_SEQ`
--

DROP TABLE IF EXISTS `TestSession_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TestSession_SEQ` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TestSession_SEQ`
--

LOCK TABLES `TestSession_SEQ` WRITE;
/*!40000 ALTER TABLE `TestSession_SEQ` DISABLE KEYS */;
INSERT INTO `TestSession_SEQ` VALUES (1030);
/*!40000 ALTER TABLE `TestSession_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TestTaker_SEQ`
--

DROP TABLE IF EXISTS `TestTaker_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TestTaker_SEQ` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TestTaker_SEQ`
--

LOCK TABLES `TestTaker_SEQ` WRITE;
/*!40000 ALTER TABLE `TestTaker_SEQ` DISABLE KEYS */;
INSERT INTO `TestTaker_SEQ` VALUES (1000);
/*!40000 ALTER TABLE `TestTaker_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Test_SEQ`
--

DROP TABLE IF EXISTS `Test_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Test_SEQ` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Test_SEQ`
--

LOCK TABLES `Test_SEQ` WRITE;
/*!40000 ALTER TABLE `Test_SEQ` DISABLE KEYS */;
INSERT INTO `Test_SEQ` VALUES (1020);
/*!40000 ALTER TABLE `Test_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `id` bigint(20) NOT NULL,
  `create_timestamp` datetime NOT NULL,
  `update_timestamp` datetime NOT NULL,
  `content` varchar(255) NOT NULL,
  `is_correct` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (1000,'2016-06-28 21:00:17','2016-06-28 21:00:17','First new answer, I am selected thus the correct answer',''),(1001,'2016-06-28 21:00:17','2016-06-28 21:00:17','Second new answer here ...','\0'),(1002,'2016-06-28 21:00:17','2016-06-28 21:00:17','Third new answer here ...','\0'),(1003,'2016-06-28 21:00:17','2016-06-28 21:00:17','Fourth new answer here ...','\0'),(1006,'2016-06-28 21:00:26','2016-06-28 21:00:57','Third new answer here ...','\0'),(1007,'2016-06-28 21:00:26','2016-06-28 21:00:57','Fourth new answer here ...','\0'),(1008,'2016-06-28 21:01:55','2016-06-28 21:01:55','First new answer, I am selected thus the correct answer',''),(1009,'2016-06-28 21:01:55','2016-06-28 21:01:55','Second new answer here ...','\0'),(1010,'2016-06-28 21:01:55','2016-06-28 21:01:55','Third new answer here ...','\0'),(1011,'2016-06-28 21:01:55','2016-06-28 21:01:55','Fourth new answer here ...','\0'),(1012,'2016-06-28 21:02:00','2016-06-28 21:02:38','The question asks: Why are you breaking up with bob?',''),(1013,'2016-06-28 21:02:00','2016-06-28 21:02:38','The question asks: Why must you leave now, Bob?','\0'),(1014,'2016-06-28 21:02:00','2016-06-28 21:02:38','Third new answer here ...','\0'),(1015,'2016-06-28 21:02:00','2016-06-28 21:02:38','Fourth new answer here ...','\0'),(1016,'2016-06-28 21:02:41','2016-06-28 21:02:41','First new answer, I am selected thus the correct answer',''),(1017,'2016-06-28 21:02:41','2016-06-28 21:02:41','Second new answer here ...','\0'),(1018,'2016-06-28 21:02:41','2016-06-28 21:02:41','Third new answer here ...','\0'),(1019,'2016-06-28 21:02:41','2016-06-28 21:02:41','Fourth new answer here ...','\0'),(1020,'2016-06-28 21:02:52','2016-06-28 21:03:01','You have 2 choices.',''),(1021,'2016-06-28 21:02:52','2016-06-28 21:03:01','You have 3 choices.','\0'),(1022,'2016-06-28 21:02:52','2016-06-28 21:03:01','Third new answer here ...','\0'),(1023,'2016-06-28 21:02:52','2016-06-28 21:03:01','Fourth new answer here ...','\0'),(1024,'2016-06-28 21:16:58','2016-06-28 21:16:58','First new answer, I am selected thus the correct answer',''),(1025,'2016-06-28 21:16:58','2016-06-28 21:16:58','Second new answer here ...','\0'),(1026,'2016-06-28 21:16:58','2016-06-28 21:16:58','Third new answer here ...','\0'),(1027,'2016-06-28 21:16:58','2016-06-28 21:16:58','Fourth new answer here ...','\0'),(1028,'2016-06-28 21:17:11','2016-06-28 21:18:06','thinks wearing uniforms at school is neccessary.',''),(1029,'2016-06-28 21:17:11','2016-06-28 21:18:06','thinks wearing school uniforms is a bad idea.','\0'),(1030,'2016-06-28 21:17:11','2016-06-28 21:18:06','is not sure what to think about wearing school uniforms','\0'),(1031,'2016-06-28 21:17:11','2016-06-28 21:18:02','Fourth new answer here ...','\0'),(1032,'2016-06-28 21:18:10','2016-06-28 21:18:10','First new answer, I am selected thus the correct answer',''),(1033,'2016-06-28 21:18:10','2016-06-28 21:18:10','Second new answer here ...','\0'),(1034,'2016-06-28 21:18:10','2016-06-28 21:18:10','Third new answer here ...','\0'),(1035,'2016-06-28 21:18:10','2016-06-28 21:18:10','Fourth new answer here ...','\0'),(1036,'2016-06-28 21:19:12','2016-06-28 21:19:59','I do, but he doesn\'t.','\0'),(1037,'2016-06-28 21:19:12','2016-06-28 21:19:59','But I don\'t read it.','\0'),(1038,'2016-06-28 21:19:12','2016-06-28 21:19:59','Not another language.',''),(1039,'2016-06-28 21:19:12','2016-06-28 21:19:41','Fourth new answer here ...','\0'),(1040,'2016-06-28 21:19:50','2016-06-28 21:19:50','First new answer, I am selected thus the correct answer',''),(1041,'2016-06-28 21:19:50','2016-06-28 21:19:50','Second new answer here ...','\0'),(1042,'2016-06-28 21:19:50','2016-06-28 21:19:50','Third new answer here ...','\0'),(1043,'2016-06-28 21:19:50','2016-06-28 21:19:50','Fourth new answer here ...','\0'),(1044,'2016-06-28 21:20:21','2016-06-28 21:21:57','She\'s a kind, old woman.','\0'),(1045,'2016-06-28 21:20:21','2016-06-28 21:21:57','She\'s a kind of old woman.',''),(1046,'2016-06-28 21:20:21','2016-06-28 21:20:49','Third new answer here ...','\0'),(1047,'2016-06-28 21:20:21','2016-06-28 21:20:51','Fourth new answer here ...','\0'),(1048,'2016-06-28 21:21:15','2016-06-28 21:21:15','First new answer, I am selected thus the correct answer',''),(1049,'2016-06-28 21:21:15','2016-06-28 21:21:15','Second new answer here ...','\0'),(1050,'2016-06-28 21:21:15','2016-06-28 21:21:15','Third new answer here ...','\0'),(1051,'2016-06-28 21:21:15','2016-06-28 21:21:15','Fourth new answer here ...','\0'),(1052,'2016-06-28 21:21:17','2016-06-28 21:22:09','I want my peas.','\0'),(1053,'2016-06-28 21:21:17','2016-06-28 21:22:09','I want my piece.',''),(1054,'2016-06-28 21:21:17','2016-06-28 21:21:17','Fourth new answer here ...','\0'),(1055,'2016-06-28 21:22:10','2016-06-28 21:22:10','First new answer, I am selected thus the correct answer',''),(1056,'2016-06-28 21:22:10','2016-06-28 21:22:10','Second new answer here ...','\0'),(1057,'2016-06-28 21:22:10','2016-06-28 21:22:10','Third new answer here ...','\0'),(1058,'2016-06-28 21:22:10','2016-06-28 21:22:10','Fourth new answer here ...','\0'),(1059,'2016-06-28 21:22:21','2016-06-28 21:25:49','How come you couldn\'t see any?',''),(1060,'2016-06-28 21:22:21','2016-06-28 21:25:49','How come you couldn\'t see Annie?','\0'),(1061,'2016-06-28 21:22:21','2016-06-28 21:23:00','Third new answer here ...','\0'),(1062,'2016-06-28 21:22:21','2016-06-28 21:23:02','Fourth new answer here ...','\0'),(1063,'2016-06-28 21:23:06','2016-06-28 21:23:06','First new answer, I am selected thus the correct answer',''),(1064,'2016-06-28 21:23:06','2016-06-28 21:23:06','Second new answer here ...','\0'),(1065,'2016-06-28 21:23:06','2016-06-28 21:23:06','Third new answer here ...','\0'),(1066,'2016-06-28 21:23:06','2016-06-28 21:23:06','Fourth new answer here ...','\0'),(1067,'2016-06-28 21:23:18','2016-06-28 21:30:15','Apologize',''),(1068,'2016-06-28 21:23:18','2016-06-28 21:30:15','Apologetic','\0'),(1069,'2016-06-28 21:23:18','2016-06-28 21:30:15','Apology','\0'),(1070,'2016-06-28 21:23:18','2016-06-28 21:23:34','Fourth new answer here ...','\0'),(1071,'2016-06-28 21:30:22','2016-06-28 21:30:22','First new answer, I am selected thus the correct answer',''),(1072,'2016-06-28 21:30:22','2016-06-28 21:30:22','Second new answer here ...','\0'),(1073,'2016-06-28 21:30:22','2016-06-28 21:30:22','Third new answer here ...','\0'),(1074,'2016-06-28 21:30:22','2016-06-28 21:30:22','Fourth new answer here ...','\0'),(1075,'2016-06-28 21:30:28','2016-06-28 21:30:52','I didn\'t know she could French well.',''),(1076,'2016-06-28 21:30:28','2016-06-28 21:30:52','I didn\'t know she could speak French.','\0'),(1077,'2016-06-28 21:30:28','2016-06-28 21:30:50','Third new answer here ...','\0'),(1078,'2016-06-28 21:30:28','2016-06-28 21:30:51','Fourth new answer here ...','\0'),(1083,'2016-06-28 21:36:48','2016-06-28 21:36:48','true',''),(1084,'2016-06-28 21:36:48','2016-06-28 21:36:48','false','\0'),(1085,'2016-06-28 21:37:12','2016-06-28 21:37:12','true',''),(1086,'2016-06-28 21:37:12','2016-06-28 21:37:12','false','\0'),(1090,'2016-06-28 21:42:17','2016-06-28 21:42:17','First new answer, I am selected thus the correct answer',''),(1091,'2016-06-28 21:42:17','2016-06-28 21:42:17','Second new answer here ...','\0'),(1092,'2016-06-28 21:42:17','2016-06-28 21:42:17','Third new answer here ...','\0'),(1093,'2016-06-28 21:42:17','2016-06-28 21:42:17','Fourth new answer here ...','\0'),(1098,'2016-06-28 21:42:37','2016-06-28 21:42:37','true',''),(1099,'2016-06-28 21:42:37','2016-06-28 21:42:37','false','\0'),(1100,'2016-06-28 21:44:23','2016-06-28 21:44:24','true',''),(1101,'2016-06-28 21:44:23','2016-06-28 21:44:24','false','\0'),(1102,'2016-06-28 21:44:35','2016-06-28 21:44:35','First new answer, I am selected thus the correct answer',''),(1103,'2016-06-28 21:44:35','2016-06-28 21:44:35','Second new answer here ...','\0'),(1104,'2016-06-28 21:44:35','2016-06-28 21:44:35','Third new answer here ...','\0'),(1105,'2016-06-28 21:44:35','2016-06-28 21:44:35','Fourth new answer here ...','\0'),(1110,'2016-06-28 21:44:47','2016-06-28 21:44:47','true',''),(1111,'2016-06-28 21:44:47','2016-06-28 21:44:47','false','\0'),(1112,'2016-06-28 21:44:52','2016-06-28 21:44:57','true','\0'),(1113,'2016-06-28 21:44:52','2016-06-28 21:44:57','false',''),(1118,'2016-06-28 21:45:03','2016-06-28 21:45:03','true',''),(1119,'2016-06-28 21:45:03','2016-06-28 21:45:03','false','\0'),(1120,'2016-06-28 21:45:13','2016-06-28 21:45:13','true',''),(1121,'2016-06-28 21:45:13','2016-06-28 21:45:13','false','\0'),(1122,'2016-06-28 21:45:14','2016-06-28 21:45:14','true',''),(1123,'2016-06-28 21:45:14','2016-06-28 21:45:14','false','\0'),(1124,'2016-06-28 21:45:15','2016-06-28 21:45:15','true',''),(1125,'2016-06-28 21:45:15','2016-06-28 21:45:15','false','\0'),(1126,'2016-06-28 21:45:18','2016-06-28 21:45:18','true',''),(1127,'2016-06-28 21:45:18','2016-06-28 21:45:18','false','\0'),(1128,'2016-06-28 21:45:26','2016-06-28 21:45:26','true',''),(1129,'2016-06-28 21:45:26','2016-06-28 21:45:26','false','\0'),(1130,'2016-06-28 21:45:34','2016-06-28 21:45:34','true',''),(1131,'2016-06-28 21:45:34','2016-06-28 21:45:34','false','\0'),(1132,'2016-06-28 21:45:44','2016-06-28 21:45:47','true','\0'),(1133,'2016-06-28 21:45:44','2016-06-28 21:45:47','false',''),(1134,'2016-06-28 21:54:36','2016-06-28 21:54:36','true',''),(1135,'2016-06-28 21:54:36','2016-06-28 21:54:36','false','\0'),(1136,'2016-06-28 21:54:52','2016-06-28 21:54:52','true',''),(1137,'2016-06-28 21:54:52','2016-06-28 21:54:52','false','\0'),(1138,'2016-06-28 21:55:01','2016-06-28 21:55:01','true',''),(1139,'2016-06-28 21:55:01','2016-06-28 21:55:01','false','\0'),(1140,'2016-06-28 21:55:05','2016-06-28 21:55:07','true','\0'),(1141,'2016-06-28 21:55:05','2016-06-28 21:55:07','false',''),(1142,'2016-06-28 21:55:08','2016-06-28 21:55:08','true',''),(1143,'2016-06-28 21:55:08','2016-06-28 21:55:08','false','\0'),(1144,'2016-06-28 21:55:12','2016-06-28 21:55:12','true',''),(1145,'2016-06-28 21:55:12','2016-06-28 21:55:12','false','\0'),(1146,'2016-06-28 21:55:20','2016-06-28 21:55:30','true','\0'),(1147,'2016-06-28 21:55:20','2016-06-28 21:55:30','false',''),(1148,'2016-06-28 21:55:27','2016-06-28 21:55:31','true','\0'),(1149,'2016-06-28 21:55:27','2016-06-28 21:55:31','false',''),(1150,'2016-06-28 21:55:42','2016-06-28 21:55:42','First new answer, I am selected thus the correct answer',''),(1151,'2016-06-28 21:55:42','2016-06-28 21:55:42','Second new answer here ...','\0'),(1152,'2016-06-28 21:55:42','2016-06-28 21:55:42','Third new answer here ...','\0'),(1153,'2016-06-28 21:55:42','2016-06-28 21:55:42','Fourth new answer here ...','\0'),(1154,'2016-06-28 21:55:46','2016-06-28 21:56:15','the amount of non-personal conversation between two people.','\0'),(1155,'2016-06-28 21:55:46','2016-06-28 21:56:15','the type of information two people share with one another.',''),(1156,'2016-06-28 21:55:46','2016-06-28 21:56:15','how frequently two people argue with one another.','\0'),(1157,'2016-06-28 21:55:46','2016-06-28 21:56:15','casual friendships.','\0'),(1158,'2016-06-28 21:56:16','2016-06-28 21:56:16','First new answer, I am selected thus the correct answer',''),(1159,'2016-06-28 21:56:16','2016-06-28 21:56:16','Second new answer here ...','\0'),(1160,'2016-06-28 21:56:16','2016-06-28 21:56:16','Third new answer here ...','\0'),(1161,'2016-06-28 21:56:16','2016-06-28 21:56:16','Fourth new answer here ...','\0'),(1162,'2016-06-28 21:56:21','2016-06-28 21:56:47','compare friendship with marriage.','\0'),(1163,'2016-06-28 21:56:21','2016-06-28 21:56:47','contrast friendship with familial relationships.','\0'),(1164,'2016-06-28 21:56:21','2016-06-28 21:56:47','expand on the definition of friendship.',''),(1165,'2016-06-28 21:56:21','2016-06-28 21:56:47','entertain the reader.','\0'),(1166,'2016-06-28 21:57:59','2016-06-28 21:57:59','First new answer, I am selected thus the correct answer',''),(1167,'2016-06-28 21:57:59','2016-06-28 21:57:59','Second new answer here ...','\0'),(1168,'2016-06-28 21:57:59','2016-06-28 21:57:59','Third new answer here ...','\0'),(1169,'2016-06-28 21:57:59','2016-06-28 21:57:59','Fourth new answer here ...','\0'),(1170,'2016-06-28 21:58:02','2016-06-28 21:59:14','Friendships are very difficult to start, therefore, very difficult to end.','\0'),(1171,'2016-06-28 21:58:02','2016-06-28 21:59:14','Friendships are based on the sharing of intensely personal information.','\0'),(1172,'2016-06-28 21:58:02','2016-06-28 21:59:14','Friendships are generally not supported by family members or society.',''),(1173,'2016-06-28 21:58:02','2016-06-28 21:59:14','Friendships are easily broken.','\0'),(1174,'2016-06-28 21:59:22','2016-06-28 21:59:22','First new answer, I am selected thus the correct answer',''),(1175,'2016-06-28 21:59:22','2016-06-28 21:59:22','Second new answer here ...','\0'),(1176,'2016-06-28 21:59:22','2016-06-28 21:59:22','Third new answer here ...','\0'),(1177,'2016-06-28 21:59:22','2016-06-28 21:59:22','Fourth new answer here ...','\0'),(1178,'2016-06-28 21:59:25','2016-06-28 21:59:47','mutual self-disclosure',''),(1179,'2016-06-28 21:59:25','2016-06-28 21:59:47','non-personal conversations','\0'),(1180,'2016-06-28 21:59:25','2016-06-28 21:59:47','a superficial relationship','\0'),(1181,'2016-06-28 21:59:25','2016-06-28 21:59:47','a lack of intimacy','\0'),(1182,'2016-06-28 21:59:53','2016-06-28 21:59:53','First new answer, I am selected thus the correct answer',''),(1183,'2016-06-28 21:59:53','2016-06-28 21:59:53','Second new answer here ...','\0'),(1184,'2016-06-28 21:59:53','2016-06-28 21:59:53','Third new answer here ...','\0'),(1185,'2016-06-28 21:59:53','2016-06-28 21:59:53','Fourth new answer here ...','\0'),(1186,'2016-06-28 22:04:41','2016-06-28 22:05:49','Highly choosy relationships like marriage or family are very different from friendship.','\0'),(1187,'2016-06-28 22:04:41','2016-06-28 22:05:49','People choose their friends, but they don’t choose their family.',''),(1188,'2016-06-28 22:04:41','2016-06-28 22:05:49','Friends enter into relationships differently from marriages.','\0'),(1189,'2016-06-28 22:04:41','2016-06-28 22:05:49','Friendships are more important than other relationships because the people in them are volunteers.','\0'),(1190,'2016-06-28 22:06:17','2016-06-28 22:06:17','First new answer, I am selected thus the correct answer',''),(1191,'2016-06-28 22:06:17','2016-06-28 22:06:17','Second new answer here ...','\0'),(1192,'2016-06-28 22:06:17','2016-06-28 22:06:17','Third new answer here ...','\0'),(1193,'2016-06-28 22:06:17','2016-06-28 22:06:17','Fourth new answer here ...','\0'),(1194,'2016-06-28 22:06:25','2016-06-28 22:06:46','ability to see','\0'),(1195,'2016-06-28 22:06:25','2016-06-28 22:06:46','thought','\0'),(1196,'2016-06-28 22:06:25','2016-06-28 22:06:46','supply',''),(1197,'2016-06-28 22:06:25','2016-06-28 22:06:46','food','\0'),(1198,'2016-06-28 22:06:50','2016-06-28 22:06:50','First new answer, I am selected thus the correct answer',''),(1199,'2016-06-28 22:06:50','2016-06-28 22:06:50','Second new answer here ...','\0'),(1200,'2016-06-28 22:06:50','2016-06-28 22:06:50','Third new answer here ...','\0'),(1201,'2016-06-28 22:06:50','2016-06-28 22:06:50','Fourth new answer here ...','\0'),(1202,'2016-06-28 22:06:53','2016-06-28 22:07:11','confined','\0'),(1203,'2016-06-28 22:06:53','2016-06-28 22:07:11','incorporated',''),(1204,'2016-06-28 22:06:53','2016-06-28 22:07:11','interpreted','\0'),(1205,'2016-06-28 22:06:53','2016-06-28 22:07:11','ensnared','\0'),(1206,'2016-06-28 22:07:14','2016-06-28 22:07:14','First new answer, I am selected thus the correct answer',''),(1207,'2016-06-28 22:07:14','2016-06-28 22:07:14','Second new answer here ...','\0'),(1208,'2016-06-28 22:07:14','2016-06-28 22:07:14','Third new answer here ...','\0'),(1209,'2016-06-28 22:07:14','2016-06-28 22:07:14','Fourth new answer here ...','\0'),(1210,'2016-06-28 22:07:17','2016-06-28 22:07:49','continue',''),(1211,'2016-06-28 22:07:17','2016-06-28 22:07:49','deal with','\0'),(1212,'2016-06-28 22:07:17','2016-06-28 22:07:49','heighten','\0'),(1213,'2016-06-28 22:07:17','2016-06-28 22:07:49','promote','\0'),(1220,'2016-07-07 18:55:10','2016-07-07 18:55:10','First new answer here ...',''),(1221,'2016-07-07 18:55:56','2016-07-07 18:56:08','for',''),(1222,'2016-07-07 18:56:00','2016-07-07 18:56:06','','\0'),(1223,'2016-07-07 18:56:14','2016-07-07 18:56:14','First new answer here ...',''),(1224,'2016-07-07 18:56:53','2016-07-07 18:57:05','up',''),(1225,'2016-07-07 18:57:00','2016-07-07 18:57:05','into',''),(1226,'2016-07-07 18:57:03','2016-07-07 18:57:03','new answer','\0'),(1310,'2016-07-07 19:38:33','2016-07-07 19:38:34','up','\0'),(1311,'2016-07-07 19:41:33','2016-07-07 19:41:35','up',''),(1321,'2016-07-07 19:45:40','2016-07-07 19:45:40','new answer',''),(1328,'2016-07-07 19:13:43','2016-07-07 19:13:45','into',''),(1329,'2016-07-07 19:44:25','2016-07-07 19:46:03','up',''),(1355,'2016-07-07 20:02:28','2016-07-07 20:02:28','The',''),(1356,'2016-07-07 20:06:11','2016-07-07 20:06:11','A',''),(1357,'2016-06-28 21:00:26','2016-06-28 21:00:57','The secretary was late.',''),(1358,'2016-06-28 21:00:26','2016-06-28 21:00:57','The boss was late.','\0');
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `create_timestamp` datetime NOT NULL,
  `update_timestamp` datetime NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `ordering` int(11) DEFAULT NULL,
  `timeAllowed` double DEFAULT NULL,
  `test_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjfcmfsn2m0ggkpjpedbdva60i` (`test_id`),
  CONSTRAINT `FKjfcmfsn2m0ggkpjpedbdva60i` FOREIGN KEY (`test_id`) REFERENCES `test` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1000,'2016-06-28 20:54:58','2016-06-28 20:54:58','Listening',1,30,1000),(1001,'2016-06-28 20:54:59','2016-06-28 20:54:59','Reading',1,45,1000),(1002,'2016-06-28 20:55:00','2016-06-28 20:55:00','Grammar',1,40,1000);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_score`
--

DROP TABLE IF EXISTS `category_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_score` (
  `id` bigint(20) NOT NULL,
  `create_timestamp` datetime NOT NULL,
  `update_timestamp` datetime NOT NULL,
  `score` decimal(19,2) DEFAULT NULL,
  `category_id` bigint(20) NOT NULL,
  `test_session_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhkb7cg47c7nv2qa5w4oqyp46f` (`category_id`),
  KEY `FKb0lrum0waamhxrr53m4apuj85` (`test_session_id`),
  CONSTRAINT `FKb0lrum0waamhxrr53m4apuj85` FOREIGN KEY (`test_session_id`) REFERENCES `test_session` (`id`),
  CONSTRAINT `FKhkb7cg47c7nv2qa5w4oqyp46f` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_score`
--

LOCK TABLES `category_score` WRITE;
/*!40000 ALTER TABLE `category_score` DISABLE KEYS */;
INSERT INTO `category_score` VALUES (1025,'2016-07-07 19:35:28','2016-07-07 19:35:28',0.00,1000,1010),(1026,'2016-07-07 19:35:28','2016-07-07 19:35:28',0.00,1001,1010),(1027,'2016-07-07 19:35:28','2016-07-07 19:35:28',2.00,1002,1010),(1028,'2016-07-07 19:35:28','2016-07-07 19:35:28',0.00,1000,1000),(1029,'2016-07-07 19:35:28','2016-07-07 19:35:28',0.00,1001,1000),(1030,'2016-07-07 19:35:28','2016-07-07 19:35:28',0.00,1002,1000),(1031,'2016-07-07 19:35:28','2016-07-07 19:35:28',3.00,1000,1020),(1032,'2016-07-07 19:35:28','2016-07-07 19:35:28',2.00,1001,1020),(1033,'2016-07-07 19:35:28','2016-07-07 19:35:28',1.00,1002,1020);
/*!40000 ALTER TABLE `category_score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `free_text`
--

DROP TABLE IF EXISTS `free_text`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `free_text` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK8e5vu93xyxjwb9lgbsr0qjwkv` FOREIGN KEY (`id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `free_text`
--

LOCK TABLES `free_text` WRITE;
/*!40000 ALTER TABLE `free_text` DISABLE KEYS */;
/*!40000 ALTER TABLE `free_text` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `html_component`
--

DROP TABLE IF EXISTS `html_component`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `html_component` (
  `content` text,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKb2x9s9ffg4vtyhk1t40dwdepe` FOREIGN KEY (`id`) REFERENCES `test_component` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `html_component`
--

LOCK TABLES `html_component` WRITE;
/*!40000 ALTER TABLE `html_component` DISABLE KEYS */;
INSERT INTO `html_component` VALUES ('<h1>For each question below, choose the <u>meaning</u> of what you hear.</h1>',1000),('<h1>Circle the <u>sentence</u> that you hear:</h1>',1007),('<h1>Listening for Main ideas and Details.</h1><p>You will hear an interview that originally aired on the CBC radio program, “Quirks and Quarks”.   This interview is with David Cope, a musical composer and professor of music at the University of California of Santa Cruz.<br/></p>',1013),('<p></p><p></p><div style=\"text-align: center;\"><b>FRIENDSHIP</b></div><p></p><p></p><p lang=\"en-US\" class=\"western\" style=\"text-align: center;\"><font size=\"2\"><b>Beverly Fehr</b></font></p><div style=\"text-align: center;\"><font size=\"2\"><i><b>Adapted from </b></i></font><font size=\"2\"><i><u><b>Friendship</b></u></i></font></div><div style=\"text-align: center;\"><font size=\"2\"><i><u><b>Processes</b></u></i></font><font size=\"2\"><i><b>.</b></i></font></div><p lang=\"en-US\" class=\"western\"><font size=\"2\"></font><font size=\"2\"></font></p><div style=\"text-align: center;\"><i><b> Newberry Park, Calif.:  Sage, 1996.</b></i></div><div style=\"text-align: center;\"><div style=\"text-align: left;\">Friendships are a kind of interpersonal relationship.  They differ from other relationships, such as familial or marital relationships, in that they are highly voluntary, and are characterized by intimacy, trust, loyalty, caring, enjoyment of one another’s company, and the <b>provision</b> of practical and emotional support. </div><div style=\"text-align: left;\"><br/></div><div style=\"text-align: left;\"><span>     There are different kinds of relationships, ranging from acquaintances, to casual friends, close friends, good friends, and best friends.  With acquaintances, conversations tend to be rather superficial and non-personal.  If, however, both people began to reveal somewhat more personal, intimate information about themselves, the relationship would evolve to the casual friendship stage.  If this process of mutual self-disclosure continued to increase, the relationship would progress to the level of a close, or even a best friendship.  Thus, friendships are likely to progress from acquaintanceships to close or best friendships when the relationship develops in the direction of greater intimacy, loyalty, trust, commitment, and so on.</span></div><div style=\"text-align: left;\"><br/></div><div style=\"text-align: left;\">     Friends regard one another as equals and are committed to the continuation of the relationship. Unlike familial relationships, we choose our friends.  We are also free to <b>terminate</b> friendships once we have begun them.  In contrast, relationships with family members or romantic partners are not easily ended.  Friends are more likely to passively drift apart, but in the case of marital relationships, there are complex social and legal steps that must be taken to end the relationship.  There also are few societal supports in place to foster and <b>maintain</b> friendships.  When people form romantic relationships, each partner generally becomes <b>integrated</b> with the other partner’s social network of friends and family.  If the relationship encounters difficulty, friends and family members may provide support, and offer encouragement to ensure that the relationship will continue.  However, if the friendship deteriorates, other friends and family members are not likely to <b>intervene</b> and attempt to restore the relationship. </div><div style=\"text-align: left;\"><br/></div></div><p>Because friendship is such a voluntary relationship, and because there are few societal supports to ensure its maintenance, friendship is often considered to be the most fragile kind of interpersonal relationship.</p>',1029),('<h4>Choose the word or phrase which is closest in meaning to the test word.  Be sure that the definition you choose reflects the context of the reading.  You may refer to the article to help you determine the meaning.</h4>',1039);
/*!40000 ALTER TABLE `html_component` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `multiple_choice`
--

DROP TABLE IF EXISTS `multiple_choice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `multiple_choice` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKc7p4fdbt5hmtla7jmxr6imer5` FOREIGN KEY (`id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `multiple_choice`
--

LOCK TABLES `multiple_choice` WRITE;
/*!40000 ALTER TABLE `multiple_choice` DISABLE KEYS */;
INSERT INTO `multiple_choice` VALUES (1001),(1002),(1003),(1004),(1005),(1006),(1008),(1009),(1010),(1011),(1034),(1035),(1036),(1037),(1038),(1040),(1041),(1042);
/*!40000 ALTER TABLE `multiple_choice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pencil`
--

DROP TABLE IF EXISTS `pencil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pencil` (
  `id` bigint(20) NOT NULL,
  `create_timestamp` datetime NOT NULL,
  `update_timestamp` datetime NOT NULL,
  `pencil_type` int(11) DEFAULT NULL,
  `test_taker_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoh0wlh2bcpc0f3a3q72i6mt9v` (`test_taker_id`),
  CONSTRAINT `FKoh0wlh2bcpc0f3a3q72i6mt9v` FOREIGN KEY (`test_taker_id`) REFERENCES `test_taker` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pencil`
--

LOCK TABLES `pencil` WRITE;
/*!40000 ALTER TABLE `pencil` DISABLE KEYS */;
/*!40000 ALTER TABLE `pencil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `progress`
--

DROP TABLE IF EXISTS `progress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `progress` (
  `id` bigint(20) NOT NULL,
  `create_timestamp` datetime NOT NULL,
  `update_timestamp` datetime NOT NULL,
  `end_at` datetime DEFAULT NULL,
  `start_at` datetime NOT NULL,
  `category_id` bigint(20) NOT NULL,
  `test_session_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm22q8qw73egr6l4j88lb1e432` (`category_id`),
  KEY `FKdntar9aibbtcwwg8cnl1h8844` (`test_session_id`),
  CONSTRAINT `FKdntar9aibbtcwwg8cnl1h8844` FOREIGN KEY (`test_session_id`) REFERENCES `test_session` (`id`),
  CONSTRAINT `FKm22q8qw73egr6l4j88lb1e432` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `progress`
--

LOCK TABLES `progress` WRITE;
/*!40000 ALTER TABLE `progress` DISABLE KEYS */;
INSERT INTO `progress` VALUES (1000,'2016-07-07 19:14:47','2016-07-07 19:14:47','2016-07-07 19:14:52','2016-07-07 19:14:47',1000,1000),(1001,'2016-07-07 19:14:52','2016-07-07 19:14:52','2016-07-07 19:14:53','2016-07-07 19:14:52',1001,1000),(1002,'2016-07-07 19:14:53','2016-07-07 19:14:53','2016-07-07 19:14:59','2016-07-07 19:14:53',1002,1000),(1010,'2016-07-07 19:22:23','2016-07-07 19:22:23','2016-07-07 19:22:26','2016-07-07 19:22:23',1000,1010),(1011,'2016-07-07 19:22:26','2016-07-07 19:22:26','2016-07-07 19:22:27','2016-07-07 19:22:26',1001,1010),(1012,'2016-07-07 19:22:27','2016-07-07 19:22:27','2016-07-07 19:33:03','2016-07-07 19:22:27',1002,1010),(1020,'2016-07-07 19:33:35','2016-07-07 19:33:35','2016-07-07 19:33:58','2016-07-07 19:33:35',1000,1020),(1021,'2016-07-07 19:33:58','2016-07-07 19:33:58','2016-07-07 19:34:10','2016-07-07 19:33:58',1001,1020),(1022,'2016-07-07 19:34:10','2016-07-07 19:34:10','2016-07-07 19:34:21','2016-07-07 19:34:10',1002,1020);
/*!40000 ALTER TABLE `progress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `content` varchar(255) DEFAULT NULL,
  `ordering` int(11) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK7l5ew8hphy9ixj9ra72tfawbw` FOREIGN KEY (`id`) REFERENCES `test_component` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES ('',1,1001),('',2,1002),('',3,1003),('The speaker...',4,1004),('',5,1005),('',7,1006),('',8,1008),('Circle the correct response to the question that you will hear.',9,1009),('Circle the correct response to the question that you will hear.',10,1010),('Circle the correct response to the question that you will hear.',11,1011),('“EMI” stands for Electronic Musical Intelligence.',13,1014),('The program writes music in the same style and using the same      rules as the composers.',14,1021),('Musicians were quite impressed with the music at first.',15,1023),('The Russian pianists play a work by Rachmaninov.',16,1025),('Cope doesn’t believe that EMI compromises human creativity.',17,1026),('Cope believes EMI has helped him compose many more works       than he could have composed alone.',18,1027),('Cope thinks that human composers are “dead”, and can be       replaced by the computer.',19,1028),('Friendship is one type of interpersonal relationship      mentioned in the article.',1,1030),('In general, society tends to value friendship less than marriage.',2,1031),('Friendships can be classified into six levels.',3,1032),('According to the definition of friendship, a rich person and a poor       person may have difficulty being friends.',4,1033),('In terms of friendship, intimacy is best determined by',5,1034),('The purpose of this article was to',6,1035),('Which of the following statements most accurately summarizes the main reason why       friendships are considered to be so ‘fragile’:',7,1036),('Acquaintances do not have:',8,1037),('Choose the sentence that best paraphrases the original meaning of the following quotation from the reading: “They (friendships) differ from other relationships, such as familial or marital relationships, in that they are highly voluntary.”',9,1038),('Provision',11,1040),('integrated',12,1041),('maintain',13,1042),('I was looking __ my book. Did you see it?',0,1050),('Can you look __ this problem for me? I don\'t know how to do.',1,1051),('__ book is in my bag.',2,1100);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_answer`
--

DROP TABLE IF EXISTS `question_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_answer` (
  `question_id` bigint(20) NOT NULL,
  `answer_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_pp3ml2kmf8vlgciq2vewu4xea` (`answer_id`),
  KEY `FKflwcda2rengsndju5f1deywok` (`question_id`),
  CONSTRAINT `FKflwcda2rengsndju5f1deywok` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`),
  CONSTRAINT `FKil3pbbv488omhx2gkakco46yl` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_answer`
--

LOCK TABLES `question_answer` WRITE;
/*!40000 ALTER TABLE `question_answer` DISABLE KEYS */;
INSERT INTO `question_answer` VALUES (1001,1357),(1001,1358),(1002,1012),(1002,1013),(1003,1020),(1003,1021),(1004,1028),(1004,1029),(1004,1030),(1005,1036),(1005,1037),(1005,1038),(1006,1044),(1006,1045),(1008,1052),(1008,1053),(1009,1059),(1009,1060),(1010,1067),(1010,1068),(1010,1069),(1011,1075),(1011,1076),(1014,1085),(1014,1086),(1021,1100),(1021,1101),(1023,1112),(1023,1113),(1025,1126),(1025,1127),(1026,1128),(1026,1129),(1027,1130),(1027,1131),(1028,1132),(1028,1133),(1030,1136),(1030,1137),(1031,1140),(1031,1141),(1032,1146),(1032,1147),(1033,1148),(1033,1149),(1034,1154),(1034,1155),(1034,1156),(1034,1157),(1035,1162),(1035,1163),(1035,1164),(1035,1165),(1036,1170),(1036,1171),(1036,1172),(1036,1173),(1037,1178),(1037,1179),(1037,1180),(1037,1181),(1038,1186),(1038,1187),(1038,1188),(1038,1189),(1040,1194),(1040,1195),(1040,1196),(1040,1197),(1041,1202),(1041,1203),(1041,1204),(1041,1205),(1042,1210),(1042,1211),(1042,1212),(1042,1213),(1050,1221),(1051,1328),(1051,1329),(1100,1355),(1100,1356);
/*!40000 ALTER TABLE `question_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_response`
--

DROP TABLE IF EXISTS `question_response`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_response` (
  `id` bigint(20) NOT NULL,
  `create_timestamp` datetime NOT NULL,
  `update_timestamp` datetime NOT NULL,
  `question_id` bigint(20) NOT NULL,
  `test_session_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiyouge03ev5tbesn70iofwubp` (`question_id`),
  KEY `FK5lv1uo722ab6597dcrpf9e7q4` (`test_session_id`),
  CONSTRAINT `FK5lv1uo722ab6597dcrpf9e7q4` FOREIGN KEY (`test_session_id`) REFERENCES `test_session` (`id`),
  CONSTRAINT `FKiyouge03ev5tbesn70iofwubp` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_response`
--

LOCK TABLES `question_response` WRITE;
/*!40000 ALTER TABLE `question_response` DISABLE KEYS */;
INSERT INTO `question_response` VALUES (1000,'2016-07-07 19:14:55','2016-07-07 19:14:55',1050,1000),(1001,'2016-07-07 19:14:56','2016-07-07 19:14:56',1051,1000),(1012,'2016-07-07 19:26:00','2016-07-07 19:26:00',1051,1010),(1020,'2016-07-07 19:33:00','2016-07-07 19:33:00',1050,1010),(1021,'2016-07-07 19:33:38','2016-07-07 19:33:38',1001,1020),(1022,'2016-07-07 19:33:47','2016-07-07 19:33:47',1002,1020),(1023,'2016-07-07 19:33:51','2016-07-07 19:33:51',1003,1020),(1024,'2016-07-07 19:33:55','2016-07-07 19:33:55',1028,1020),(1025,'2016-07-07 19:34:03','2016-07-07 19:34:03',1030,1020),(1026,'2016-07-07 19:34:04','2016-07-07 19:34:04',1031,1020),(1027,'2016-07-07 19:34:05','2016-07-07 19:34:05',1032,1020),(1028,'2016-07-07 19:34:06','2016-07-07 19:34:06',1033,1020),(1029,'2016-07-07 19:34:08','2016-07-07 19:34:08',1034,1020),(1030,'2016-07-07 19:34:13','2016-07-07 19:34:13',1050,1020),(1031,'2016-07-07 19:34:18','2016-07-07 19:34:18',1051,1020);
/*!40000 ALTER TABLE `question_response` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `response_content`
--

DROP TABLE IF EXISTS `response_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `response_content` (
  `id` bigint(20) NOT NULL,
  `create_timestamp` datetime NOT NULL,
  `update_timestamp` datetime NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  `answer_id` bigint(20) DEFAULT NULL,
  `question_response_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq2w74jr7875f3js2d58a8atgy` (`answer_id`),
  KEY `FKmd09ma9fev35bw4acpkclxc6j` (`question_response_id`),
  CONSTRAINT `FKmd09ma9fev35bw4acpkclxc6j` FOREIGN KEY (`question_response_id`) REFERENCES `question_response` (`id`),
  CONSTRAINT `FKq2w74jr7875f3js2d58a8atgy` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `response_content`
--

LOCK TABLES `response_content` WRITE;
/*!40000 ALTER TABLE `response_content` DISABLE KEYS */;
INSERT INTO `response_content` VALUES (1039,'2016-07-07 19:32:58','2016-07-07 19:32:58','up',NULL,1012),(1042,'2016-07-07 19:33:00','2016-07-07 19:33:00','for',NULL,1020),(1047,'2016-07-07 19:33:39','2016-07-07 19:33:39','The secretary was late.',NULL,1021),(1052,'2016-07-07 19:33:49','2016-07-07 19:33:49','The question asks: Why are you breaking up with bob?',NULL,1022),(1056,'2016-07-07 19:33:53','2016-07-07 19:33:53','You have 2 choices.',NULL,1023),(1057,'2016-07-07 19:33:55','2016-07-07 19:33:55','true',NULL,1024),(1058,'2016-07-07 19:34:03','2016-07-07 19:34:03','true',NULL,1025),(1059,'2016-07-07 19:34:04','2016-07-07 19:34:04','true',NULL,1026),(1060,'2016-07-07 19:34:05','2016-07-07 19:34:05','true',NULL,1027),(1061,'2016-07-07 19:34:06','2016-07-07 19:34:06','false',NULL,1028),(1062,'2016-07-07 19:34:08','2016-07-07 19:34:08','how frequently two people argue with one another.',NULL,1029),(1064,'2016-07-07 19:34:13','2016-07-07 19:34:13','up',NULL,1030),(1068,'2016-07-07 19:34:19','2016-07-07 19:34:19','into',NULL,1031);
/*!40000 ALTER TABLE `response_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `school`
--

DROP TABLE IF EXISTS `school`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `school` (
  `id` bigint(20) NOT NULL,
  `create_timestamp` datetime NOT NULL,
  `update_timestamp` datetime NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `school`
--

LOCK TABLES `school` WRITE;
/*!40000 ALTER TABLE `school` DISABLE KEYS */;
/*!40000 ALTER TABLE `school` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `short_answer`
--

DROP TABLE IF EXISTS `short_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `short_answer` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKnsxf6auvg8wyh8i9fkb0oqm6y` FOREIGN KEY (`id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `short_answer`
--

LOCK TABLES `short_answer` WRITE;
/*!40000 ALTER TABLE `short_answer` DISABLE KEYS */;
INSERT INTO `short_answer` VALUES (1050),(1051),(1100);
/*!40000 ALTER TABLE `short_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id` bigint(20) NOT NULL,
  `create_timestamp` datetime NOT NULL,
  `update_timestamp` datetime NOT NULL,
  `currentMajor` varchar(255) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `student_id` varchar(255) DEFAULT NULL,
  `university` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1000,'2016-07-07 19:14:47','2016-07-07 19:14:47','hello','2003-03-11','1231231@ccc.com','asdfads',1,'test ','123','University of Hong Kong'),(1010,'2016-07-07 19:22:22','2016-07-07 19:22:22','hello','2003-03-11','1231231@ccc.com','asdfads',1,'test ','123','University of Hong Kong'),(1020,'2016-07-07 19:33:35','2016-07-07 19:33:35','hello','2003-03-11','1231231@ccc.com','asdfads',1,'test ','123','University of Hong Kong');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test` (
  `id` bigint(20) NOT NULL,
  `create_timestamp` datetime NOT NULL,
  `update_timestamp` datetime NOT NULL,
  `active` bit(1) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES (1000,'2016-06-28 20:53:30','2016-06-28 20:53:30','',NULL,'<p>This is a sample test to be tested.</p>','Sample Test');
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_component`
--

DROP TABLE IF EXISTS `test_component`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_component` (
  `component_type` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL,
  `create_timestamp` datetime NOT NULL,
  `update_timestamp` datetime NOT NULL,
  `ordering` int(11) DEFAULT NULL,
  `category_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKw7d5flioi8dxipqh39b4s0dt` (`category_id`),
  CONSTRAINT `FKw7d5flioi8dxipqh39b4s0dt` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_component`
--

LOCK TABLES `test_component` WRITE;
/*!40000 ALTER TABLE `test_component` DISABLE KEYS */;
INSERT INTO `test_component` VALUES ('HTML',1000,'2016-06-28 20:57:06','2016-06-28 21:00:11',1,1000),('MULTIPLE_CHOICE',1001,'2016-06-28 21:00:17','2016-06-28 21:00:57',1,1000),('MULTIPLE_CHOICE',1002,'2016-06-28 21:01:55','2016-06-28 21:02:38',2,1000),('MULTIPLE_CHOICE',1003,'2016-06-28 21:02:41','2016-06-28 21:03:01',3,1000),('MULTIPLE_CHOICE',1004,'2016-06-28 21:16:58','2016-06-28 21:18:06',4,1000),('MULTIPLE_CHOICE',1005,'2016-06-28 21:18:10','2016-06-28 21:19:59',5,1000),('MULTIPLE_CHOICE',1006,'2016-06-28 21:19:50','2016-06-28 21:21:57',7,1000),('HTML',1007,'2016-06-28 21:20:53','2016-06-28 21:21:04',7,1000),('MULTIPLE_CHOICE',1008,'2016-06-28 21:21:15','2016-06-28 21:22:09',8,1000),('MULTIPLE_CHOICE',1009,'2016-06-28 21:22:10','2016-06-28 21:25:49',9,1000),('MULTIPLE_CHOICE',1010,'2016-06-28 21:23:06','2016-06-28 21:30:15',10,1000),('MULTIPLE_CHOICE',1011,'2016-06-28 21:30:22','2016-06-28 21:30:52',11,1000),('HTML',1013,'2016-06-28 21:31:04','2016-06-28 21:35:52',13,1000),('TRUE_FALSE',1014,'2016-06-28 21:36:48','2016-06-28 21:37:12',13,1000),('TRUE_FALSE',1021,'2016-06-28 21:42:37','2016-06-28 21:44:24',14,1000),('TRUE_FALSE',1023,'2016-06-28 21:44:47','2016-06-28 21:44:57',15,1000),('TRUE_FALSE',1025,'2016-06-28 21:45:03','2016-06-28 21:45:18',16,1000),('TRUE_FALSE',1026,'2016-06-28 21:45:13','2016-06-28 21:45:26',17,1000),('TRUE_FALSE',1027,'2016-06-28 21:45:14','2016-06-28 21:45:34',18,1000),('TRUE_FALSE',1028,'2016-06-28 21:45:15','2016-06-28 21:45:47',19,1000),('HTML',1029,'2016-06-28 21:48:25','2016-06-28 21:54:21',1,1001),('TRUE_FALSE',1030,'2016-06-28 21:54:36','2016-06-28 21:54:52',1,1001),('TRUE_FALSE',1031,'2016-06-28 21:55:01','2016-06-28 21:55:07',2,1001),('TRUE_FALSE',1032,'2016-06-28 21:55:08','2016-06-28 21:55:30',3,1001),('TRUE_FALSE',1033,'2016-06-28 21:55:12','2016-06-28 21:55:31',4,1001),('MULTIPLE_CHOICE',1034,'2016-06-28 21:55:42','2016-06-28 21:56:15',5,1001),('MULTIPLE_CHOICE',1035,'2016-06-28 21:56:16','2016-06-28 21:56:47',6,1001),('MULTIPLE_CHOICE',1036,'2016-06-28 21:57:59','2016-06-28 21:59:14',7,1001),('MULTIPLE_CHOICE',1037,'2016-06-28 21:59:22','2016-06-28 21:59:47',8,1001),('MULTIPLE_CHOICE',1038,'2016-06-28 21:59:53','2016-06-28 22:05:49',9,1001),('HTML',1039,'2016-06-28 22:06:04','2016-06-28 22:06:13',11,1001),('MULTIPLE_CHOICE',1040,'2016-06-28 22:06:17','2016-06-28 22:06:46',11,1001),('MULTIPLE_CHOICE',1041,'2016-06-28 22:06:50','2016-06-28 22:07:11',12,1001),('MULTIPLE_CHOICE',1042,'2016-06-28 22:07:14','2016-06-28 22:07:49',13,1001),('SHORT_ANSWER',1050,'2016-07-07 18:55:10','2016-07-07 18:56:08',0,1002),('SHORT_ANSWER',1051,'2016-07-07 18:56:14','2016-07-07 18:57:05',1,1002),('SHORT_ANSWER',1100,'2016-07-07 20:02:28','2016-07-07 20:02:28',2,1002);
/*!40000 ALTER TABLE `test_component` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_session`
--

DROP TABLE IF EXISTS `test_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_session` (
  `id` bigint(20) NOT NULL,
  `create_timestamp` datetime NOT NULL,
  `update_timestamp` datetime NOT NULL,
  `test_submitted` tinyint(1) DEFAULT '0',
  `student_id` bigint(20) DEFAULT NULL,
  `test_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2ph8v3p1jkgd3g15qbnm84msk` (`student_id`),
  KEY `FK42kp884nu38qvylfkxa76evuj` (`test_id`),
  CONSTRAINT `FK2ph8v3p1jkgd3g15qbnm84msk` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  CONSTRAINT `FK42kp884nu38qvylfkxa76evuj` FOREIGN KEY (`test_id`) REFERENCES `test` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_session`
--

LOCK TABLES `test_session` WRITE;
/*!40000 ALTER TABLE `test_session` DISABLE KEYS */;
INSERT INTO `test_session` VALUES (1000,'2016-07-07 19:14:47','2016-07-07 19:14:47',1,1000,1000),(1010,'2016-07-07 19:22:23','2016-07-07 19:22:23',1,1010,1000),(1020,'2016-07-07 19:33:35','2016-07-07 19:33:35',1,1020,1000);
/*!40000 ALTER TABLE `test_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_taker`
--

DROP TABLE IF EXISTS `test_taker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_taker` (
  `id` bigint(20) NOT NULL,
  `create_timestamp` datetime NOT NULL,
  `update_timestamp` datetime NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `school_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhdsmf287pcvg7fabbudyeffsu` (`school_id`),
  CONSTRAINT `FKhdsmf287pcvg7fabbudyeffsu` FOREIGN KEY (`school_id`) REFERENCES `school` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_taker`
--

LOCK TABLES `test_taker` WRITE;
/*!40000 ALTER TABLE `test_taker` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_taker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `true_false`
--

DROP TABLE IF EXISTS `true_false`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `true_false` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKriyt9iw8w5nusyiw900n9k81` FOREIGN KEY (`id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `true_false`
--

LOCK TABLES `true_false` WRITE;
/*!40000 ALTER TABLE `true_false` DISABLE KEYS */;
INSERT INTO `true_false` VALUES (1014),(1021),(1023),(1025),(1026),(1027),(1028),(1030),(1031),(1032),(1033);
/*!40000 ALTER TABLE `true_false` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-07 20:22:46
