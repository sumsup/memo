CREATE DATABASE  IF NOT EXISTS `map` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `map`;
-- MySQL dump 10.13  Distrib 5.7.23, for Win64 (x86_64)
--
-- Host: localhost    Database: map
-- ------------------------------------------------------
-- Server version	5.7.23-log

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `writer_id` int(11) NOT NULL,
  `category` varchar(20) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `writer_id_fk_idx` (`writer_id`),
  CONSTRAINT `writer_id_fk` FOREIGN KEY (`writer_id`) REFERENCES `writer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='카테고리';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (5,1,'일반','2020-07-05 03:29:56'),(6,1,'사람심리','2020-07-05 03:29:56'),(7,1,'행동메뉴얼','2020-07-05 03:29:56'),(8,1,'학습요령','2020-07-05 03:29:56'),(9,1,'식단','2020-07-05 14:13:20'),(10,1,'운동','2020-07-05 14:14:52'),(11,1,'여자심리','2020-07-05 14:15:59'),(13,3,'수정한 카테고리','2020-07-07 22:13:07'),(18,3,'등록한 카테고리','2021-03-01 21:22:30'),(19,1,'잡생각','2021-03-14 16:39:50');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `memo`
--

DROP TABLE IF EXISTS `memo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `memo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '아이디',
  `writer_id` int(11) NOT NULL COMMENT '메모한 사람',
  `memo` text NOT NULL COMMENT '메모 내용',
  `category` varchar(20) DEFAULT NULL COMMENT '카테고리',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '수정일',
  PRIMARY KEY (`id`),
  KEY `writer_id_fk_idx` (`writer_id`),
  CONSTRAINT `memo_writer_id_fk` FOREIGN KEY (`writer_id`) REFERENCES `writer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8 COMMENT='메모 테이블.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `memo`
--

LOCK TABLES `memo` WRITE;
/*!40000 ALTER TABLE `memo` DISABLE KEYS */;
INSERT INTO `memo` VALUES (1,1,'첫 메모 입니다.',NULL,'2020-07-02 00:49:31',NULL),(2,1,'두번째 입니다.',NULL,'2020-07-02 00:49:42',NULL),(18,1,'이제는 한번 들어가네?',NULL,'2020-07-04 21:29:46',NULL),(19,1,'이제는 한번 들어가네?',NULL,'2020-07-04 21:30:08',NULL),(20,1,'인서트 되수꽈?',NULL,'2020-07-07 20:45:48',NULL),(21,1,'이제 됨꽈?',NULL,'2020-07-07 20:50:13',NULL),(22,1,'리다이렉트 됨?',NULL,'2020-07-07 20:51:02',NULL),(23,1,'이거 잼있네!\r\n잘되니까 잼있넹~!',NULL,'2020-07-07 20:56:57',NULL),(24,1,'ë©ëª¨ íì¤í¸ì©',NULL,'2020-07-16 20:32:47',NULL),(25,1,'ë ë¤ì´ê°ë¤.',NULL,'2020-07-16 20:32:55',NULL),(26,1,'ë­ì¬ ì´ê±´?? ì ë ê¹¨ì ¸??',NULL,'2020-07-16 20:33:06',NULL),(27,1,'다시??',NULL,'2020-07-16 20:33:49',NULL),(28,1,'이제 되는 구려~~',NULL,'2020-07-16 20:33:55',NULL),(29,1,'가갸거겨 주륵주륵 읽었지요',NULL,'2020-07-16 20:34:04',NULL),(30,1,'또 인서트가 되누나',NULL,'2020-07-16 20:34:10',NULL),(31,1,'한번도 안해본 거지요.',NULL,'2020-07-16 20:34:40',NULL),(32,1,'뭘 안다고 까불어?',NULL,'2020-07-16 20:34:45',NULL),(33,1,'아무것도 모르는 것들이..',NULL,'2020-07-16 20:34:52',NULL),(34,1,'어린애들만 잔뜩 모여 있는 이곳',NULL,'2020-07-16 20:35:03',NULL),(35,1,'선 그었으니까 내 할 것만 한다.',NULL,'2020-07-16 20:35:12',NULL),(36,1,'선 긋고 내 할것 하고 내가 챙길 것들 챙겨서 나오면 그만인 것.',NULL,'2020-07-16 20:35:25',NULL),(37,1,'문제도 본인이 만들어 잘못도 본인이 해 화도 본인이 내',NULL,'2020-07-16 20:35:51',NULL),(38,1,'앙 김옥띠',NULL,'2020-07-16 22:29:43',NULL),(39,1,'페이지 늘어나나요\r\n',NULL,'2020-07-16 23:59:22',NULL),(40,1,'한번더',NULL,'2020-07-16 23:59:26',NULL),(41,1,'인설트 테슷흐','카테고리는 없음','2021-02-28 00:10:51',NULL),(42,1,'인설트 테슷흐','카테고리는 없음','2021-02-28 00:26:28',NULL),(43,1,'인설트 텟텟텟','카테고리는 없음','2021-02-28 00:27:22',NULL),(44,1,'patch로 업데이트 요청했습니다.',NULL,'2021-02-28 00:40:06','2021-03-01 15:29:53'),(45,1,'sfgfg',NULL,'2021-05-28 19:08:49','2021-05-28 19:08:49'),(46,1,'sfgfg',NULL,'2021-05-28 19:14:47','2021-05-28 19:14:47'),(47,1,'sfgfg',NULL,'2021-05-28 19:14:49','2021-05-28 19:14:49'),(48,1,'sfgfg',NULL,'2021-05-28 19:14:59','2021-05-28 19:14:59'),(49,1,'ffff',NULL,'2021-05-28 19:42:31','2021-05-28 19:42:31'),(50,1,'등럭',NULL,'2021-06-27 00:26:44','2021-06-27 00:26:44'),(51,1,'등럭',NULL,'2021-06-27 00:26:44','2021-06-27 00:26:44'),(52,1,'ddd???',NULL,'2021-06-27 00:28:06','2021-06-27 00:28:06'),(53,1,'되긴되네?',NULL,'2021-06-27 00:28:19','2021-06-27 00:28:19'),(54,1,'인서트 됩니까??',NULL,'2021-06-27 12:48:08','2021-06-27 12:48:08'),(55,1,'그렇습니다.',NULL,'2021-06-27 12:50:18','2021-06-27 12:50:18'),(56,1,'아하!!',NULL,'2021-06-27 12:50:36','2021-06-27 12:50:36'),(57,1,'ㅇㅇㅇ',NULL,'2021-06-27 12:52:06','2021-06-27 12:52:06'),(58,1,'ㅇㅇㅇㅇ??',NULL,'2021-06-27 12:52:14','2021-06-27 12:52:14'),(59,1,'<div>안냐세요.</div><div>줄바꿈입니다.</div>',NULL,'2021-06-27 13:54:02','2021-06-27 13:54:02'),(60,1,'<div>ddddd</div><div>ddd</div><div></div><div></div><div></div><div>ddd</div>',NULL,'2021-06-27 14:04:45','2021-06-27 14:04:45'),(61,1,'<div>dddd</div><div>dd</div><div></div><div>d</div><div>d</div><div>d</div><div></div><div></div><div></div><div></div><div>d</div>',NULL,'2021-06-27 14:04:58','2021-06-27 14:04:58'),(62,1,'<div>ㅇㅇㅇㅇㅇ</div><div></div><div></div><div>ㅇㅇㅇㅇ</div><div></div><div></div><div></div><div>ㅇㅇㅇㅇ</div><div></div><div></div><div></div><div>ㅇㅇㅇ</div>',NULL,'2021-06-27 16:17:20','2021-06-27 16:17:20'),(63,1,'<div>ㅇㅇㅇㅇ</div><div>&#8302;</div><div>&#8302;</div><div>&#8302;ㅇㅇㅇ</div><div>&#8302;</div><div>&#8302;</div><div>&#8302;</div><div>&#8302;ㅇ</div>',NULL,'2021-06-27 16:17:38','2021-06-27 16:17:38'),(64,1,'<div>됐다.</div><div>&#8302;</div><div>&#8302;이제됐다.</div><div>&#8302;</div><div>&#8302;</div><div>&#8302;</div><div>&#8302;</div><div>&#8302;이제 된 것이야.</div><div>&#8302;</div><div>&#8302;오호호호           호호호</div>',NULL,'2021-06-27 16:17:55','2021-06-27 16:17:55');
/*!40000 ALTER TABLE `memo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `writer`
--

DROP TABLE IF EXISTS `writer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `writer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password` varchar(20) NOT NULL,
  `nickname` varchar(45) NOT NULL,
  `sex` varchar(6) NOT NULL COMMENT '성별',
  `join_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '가입일',
  `birthday` varchar(10) DEFAULT NULL COMMENT '생년월일.',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `nickname_UNIQUE` (`nickname`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='회원 테이블.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `writer`
--

LOCK TABLES `writer` WRITE;
/*!40000 ALTER TABLE `writer` DISABLE KEYS */;
INSERT INTO `writer` VALUES (1,'starirof@naver.com','1234','english nickname','M','2020-06-26 12:42:52','19860225'),(2,'orenti@naver.com','4321','english nickname2','W','2020-06-26 12:43:35','19910718'),(3,'rocketgo@gmail.com','qwer','english nickname3','M','2020-06-26 12:45:29','19970506'),(4,'jieunchoi@notion.com','q1w2','english nickname4','W','2020-06-26 12:45:29','20020221'),(5,'poreirof@hanmail.net','pwpw1234','english nickname5','M','2020-06-27 00:58:59','20041103'),(6,'going@hanmail.net','qwe123','nick6','M','2020-06-28 00:06:40','19990211'),(7,'goodjob@lycos.co.kr','pw1234','어벤져스','M','2020-06-28 00:07:15','19850511'),(9,'kaka@naver.com','rewq1122','배트맨','M','2020-06-28 23:29:06','19990228'),(10,'hatehate@daum.net','pw1234','평화메이커','W','2020-06-29 00:53:14','19951115'),(11,'hororolrol@lycos.co.kr','pwpw1234','aventador','M','2020-06-29 00:54:12','20021212'),(14,'hewon@myspace.net','qwe123','평화순이','W','2020-06-30 05:36:33','19921201'),(15,'eeronman@naver.com','qwer1234','두번째 어벤져','M','2020-07-07 23:21:29','19921215');
/*!40000 ALTER TABLE `writer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-19 16:55:02
