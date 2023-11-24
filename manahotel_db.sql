-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: manahoteldb.chcfkjpxl2uk.ap-southeast-1.rds.amazonaws.com    Database: MANAHOTEL_DB
-- ------------------------------------------------------
-- Server version	8.0.33

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `control_policy`
--

DROP TABLE IF EXISTS `control_policy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `control_policy` (
                                  `control_policy_id` varchar(50) NOT NULL,
                                  `reservation_id` varchar(50) NOT NULL,
                                  `policy_id` varchar(50) NOT NULL,
                                  `total_price` float DEFAULT NULL,
                                  `note` varchar(350) DEFAULT NULL,
                                  PRIMARY KEY (`control_policy_id`),
                                  KEY `pk_cp_r_idx` (`reservation_id`),
                                  KEY `pk_cp_p_idx` (`policy_id`),
                                  CONSTRAINT `pk_cp_p` FOREIGN KEY (`policy_id`) REFERENCES `policy` (`policy_id`),
                                  CONSTRAINT `pk_cp_r` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`reservation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `control_policy`
--

LOCK TABLES `control_policy` WRITE;
/*!40000 ALTER TABLE `control_policy` DISABLE KEYS */;
/*!40000 ALTER TABLE `control_policy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `control_policy_detail`
--

DROP TABLE IF EXISTS `control_policy_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `control_policy_detail` (
                                         `control_policy_detail_id` bigint NOT NULL AUTO_INCREMENT,
                                         `reservation_detail_id` bigint NOT NULL,
                                         `control_policy_id` varchar(50) NOT NULL,
                                         `policy_detail_id` bigint NOT NULL,
                                         `type_value` varchar(250) DEFAULT NULL,
                                         `value` float DEFAULT NULL,
                                         `discrepancy` varchar(250) DEFAULT NULL,
                                         `note` varchar(350) DEFAULT NULL,
                                         PRIMARY KEY (`control_policy_detail_id`),
                                         KEY `pk_cpd_rd_idx` (`reservation_detail_id`),
                                         KEY `pk_cpd_cp_idx` (`control_policy_id`),
                                         KEY `pk_cpd_pd_idx` (`policy_detail_id`),
                                         CONSTRAINT `pk_cpd_cp` FOREIGN KEY (`control_policy_id`) REFERENCES `control_policy` (`control_policy_id`),
                                         CONSTRAINT `pk_cpd_pd` FOREIGN KEY (`policy_detail_id`) REFERENCES `policy_detail` (`policy_detail_id`),
                                         CONSTRAINT `pk_cpd_rd` FOREIGN KEY (`reservation_detail_id`) REFERENCES `reservation_detail` (`reservation_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `control_policy_detail`
--

LOCK TABLES `control_policy_detail` WRITE;
/*!40000 ALTER TABLE `control_policy_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `control_policy_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
                            `customer_id` varchar(50) NOT NULL,
                            `customer_name` varchar(250) DEFAULT NULL,
                            `customer_group_id` varchar(250) NOT NULL,
                            `phone_number` varchar(50) DEFAULT NULL,
                            `dob` timestamp NULL DEFAULT NULL,
                            `email` varchar(350) DEFAULT NULL,
                            `address` varchar(350) DEFAULT NULL,
                            `identity` varchar(350) DEFAULT NULL,
                            `nationality` varchar(350) DEFAULT NULL,
                            `tax_code` varchar(350) DEFAULT NULL,
                            `gender` bit(1) DEFAULT NULL,
                            `image` longblob,
                            `point` float DEFAULT NULL,
                            PRIMARY KEY (`customer_id`),
                            KEY `pk_c_cg_idx` (`customer_group_id`),
                            CONSTRAINT `pk_c_cg` FOREIGN KEY (`customer_group_id`) REFERENCES `customer_group` (`customer_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('C000000','Bán lẻ','NK000002',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('C000001','Dinh Tien','NK000006','9815655945','2002-11-03 00:00:00','TienDV18@fsoft.com.vn','fpt','132u738921','VN','83291',_binary '','',NULL),('C000002','Hiển','NK000002','0123456789','2023-11-03 00:00:00','hiengay@gmail.com','Nhà','091826351','Việt Nam','0123456',_binary '',NULL,NULL),('C000003','Trung','NK000002','0123456789','2023-11-03 00:00:00','hiengay@gmail.com','Nhà','091826351','Việt Nam','0123456',_binary '',NULL,NULL),('C000004','sd','NK000002','f','2002-11-03 00:00:00','f','f','f','f','f',_binary '',NULL,NULL),('C000005','sd','NK000002','f','2002-11-03 00:00:00','f','f','f','f','f',_binary '',NULL,NULL),('C000006','Tien Van Dinh 2','NK000007','0981987625','2001-10-26 00:00:00','TienDV18@fsoft.com.vn','P2E2, Cong Vi, Ba Dinh','132u738921','Viet Nam','83291',_binary '','',NULL),('C000007','Hehe','NK000000','','2001-10-26 00:00:00','','','','','',_binary '','',NULL),('C000008','Huhu','NK000006','','2001-10-26 00:00:00','','','132u738921','Viet Nam','',_binary '','',NULL);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_group`
--

DROP TABLE IF EXISTS `customer_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_group` (
                                  `customer_group_id` varchar(45) NOT NULL,
                                  `customer_group_name` varchar(45) DEFAULT NULL,
                                  `status` varchar(45) DEFAULT NULL,
                                  PRIMARY KEY (`customer_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_group`
--

LOCK TABLES `customer_group` WRITE;
/*!40000 ALTER TABLE `customer_group` DISABLE KEYS */;
INSERT INTO `customer_group` VALUES ('NK000000','Bán lẻ',NULL),('NK000001','df','ACTIVE'),('NK000002','sd','ACTIVE'),('NK000003','dfsd','ACTIVE'),('NK000004','df','ACTIVE'),('NK000005','ddd','ACTIVE'),('NK000006','Hội nguời già','ACTIVE'),('NK000007','Hội nguời da đen','ACTIVE'),('NK000008','Hội nguời tàn tật','ACTIVE');
/*!40000 ALTER TABLE `customer_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
                              `department_id` varchar(50) NOT NULL,
                              `department_name` varchar(45) DEFAULT NULL,
                              `status` varchar(45) DEFAULT NULL,
                              PRIMARY KEY (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES ('PB000001','lễ tân',NULL),('PB000002','bb','a'),('PB000003','b','a'),('PB000004','b','a'),('PB000005','b','a'),('PB000006','dd',NULL);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `floor`
--

DROP TABLE IF EXISTS `floor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `floor` (
                         `floor_id` int NOT NULL AUTO_INCREMENT,
                         `floor_name` varchar(250) DEFAULT NULL,
                         `status` int DEFAULT NULL,
                         `created_by_id` int DEFAULT NULL,
                         `updated_by_id` int DEFAULT NULL,
                         `created_date` timestamp NULL DEFAULT NULL,
                         `updated_date` timestamp NULL DEFAULT NULL,
                         PRIMARY KEY (`floor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `floor`
--

LOCK TABLES `floor` WRITE;
/*!40000 ALTER TABLE `floor` DISABLE KEYS */;
INSERT INTO `floor` VALUES (0,'Sảnh',NULL,NULL,NULL,NULL,NULL),(1,'Tầng 1',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(2,'Tầng 2',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(3,'Tầng 3',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(4,'Tầng 4',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(5,'Tầng 5',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(6,'Tầng 6',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(7,'Tầng 7',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(8,'Tầng 8',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(9,'Tầng 9',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(10,'Tầng 10',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00');
/*!40000 ALTER TABLE `floor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fund_book`
--

DROP TABLE IF EXISTS `fund_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fund_book` (
                             `fund_book_id` varchar(50) NOT NULL,
                             `order_id` varchar(50) DEFAULT NULL,
                             `time` timestamp NULL DEFAULT NULL,
                             `type` varchar(250) DEFAULT NULL,
                             `paid_method` varchar(50) DEFAULT NULL,
                             `value` float DEFAULT NULL,
                             `prepaid` float DEFAULT NULL,
                             `paid` float DEFAULT NULL,
                             `payer` varchar(250) DEFAULT NULL,
                             `staff` varchar(250) DEFAULT NULL,
                             `note` varchar(350) DEFAULT NULL,
                             `status` varchar(50) DEFAULT NULL,
                             PRIMARY KEY (`fund_book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fund_book`
--

LOCK TABLES `fund_book` WRITE;
/*!40000 ALTER TABLE `fund_book` DISABLE KEYS */;
/*!40000 ALTER TABLE `fund_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goods` (
                         `goods_id` varchar(50) NOT NULL,
                         `goods_name` varchar(250) DEFAULT NULL,
                         `goods_category` bit(1) DEFAULT NULL,
                         `status` int DEFAULT NULL,
                         `inventory` bigint DEFAULT NULL,
                         `min_inventory` bigint DEFAULT NULL,
                         `max_inventory` bigint DEFAULT NULL,
                         `note` varchar(250) DEFAULT NULL,
                         `description` varchar(500) DEFAULT NULL,
                         `image` longblob,
                         `created_by_id` bigint DEFAULT NULL,
                         `updated_by_id` bigint DEFAULT NULL,
                         `created_date` timestamp NULL DEFAULT NULL,
                         `updated_date` timestamp NULL DEFAULT NULL,
                         PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES ('SP000001','Sprite',_binary '',1,99997,100,100000,'Non nước ngọt Sprite','Non Sprite thể tích 150ml',NULL,NULL,NULL,'2023-10-17 05:00:00','2023-10-17 05:00:00'),('SP000002','Coca Cola',_binary '',1,99981,100,100000,'Non nước ngọt Coca Cola','Non Coca Cola thể tích 150ml',NULL,NULL,NULL,'2023-10-17 05:00:00','2023-10-17 05:00:00'),('SP000003','Thịt bò khô',_binary '',1,99950,100,100000,'Thịt bò khô','Thịt bò khô 10 miếng','',NULL,2,'2023-10-17 05:00:00','2023-11-04 15:39:49'),('SP000004','Thuê xe máy',_binary '\0',1,NULL,NULL,NULL,'Xe máy','Xe máy đầy xăng',NULL,NULL,NULL,'2023-10-17 05:00:00','2023-10-17 05:00:00'),('SP000005','Thuê ô tô',_binary '\0',1,NULL,NULL,NULL,'Ô tô','Ô tô điện',NULL,NULL,NULL,'2023-10-17 05:00:00','2023-10-17 05:00:00'),('SP000006','Massage',_binary '\0',1,NULL,NULL,NULL,'Mát xa','Mát xa cực phê',NULL,NULL,NULL,'2023-10-17 05:00:00','2023-10-17 05:00:00'),('SP000007','Mỳ tôm',_binary '',1,99958,100,100000,'Mỳ tôm','Mỳ hảo hán',NULL,NULL,NULL,'2023-10-17 05:00:00','2023-10-17 05:00:00'),('SP000008','Bia Huda',_binary '',1,100000,100,100000,'Bia','Đậm tình miền Trung',NULL,NULL,NULL,'2023-10-17 05:00:00','2023-10-17 05:00:00'),('SP000009','Bim bim Lays',_binary '',1,100000,100,100000,'Bim bim','Anh muốn Lays em',NULL,NULL,NULL,'2023-10-17 05:00:00','2023-10-17 05:00:00'),('SP000010','Cắt tóc',_binary '\0',1,NULL,NULL,NULL,'Cắt tóc','Tommy Xiaomi',NULL,NULL,NULL,'2023-10-17 05:00:00','2023-10-17 05:00:00');
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods_unit`
--

DROP TABLE IF EXISTS `goods_unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goods_unit` (
                              `goods_unit_id` int NOT NULL AUTO_INCREMENT,
                              `goods_unit_name` varchar(250) DEFAULT NULL,
                              `goods_id` varchar(50) NOT NULL,
                              `cost` float DEFAULT NULL,
                              `price` float DEFAULT NULL,
                              `is_default` bit(1) DEFAULT NULL,
                              PRIMARY KEY (`goods_unit_id`),
                              KEY `pk_gu_g_idx` (`goods_id`),
                              CONSTRAINT `pk_gu_g` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods_unit`
--

LOCK TABLES `goods_unit` WRITE;
/*!40000 ALTER TABLE `goods_unit` DISABLE KEYS */;
INSERT INTO `goods_unit` VALUES (1,'Lon','SP000001',500,1000,_binary ''),(2,'Lon','SP000002',300,700,_binary ''),(3,'Gói','SP000003',50,100,_binary ''),(4,'Lần','SP000004',0,900,_binary ''),(5,'Lần','SP000005',0,1100,_binary ''),(6,'Lần','SP000006',0,750,_binary ''),(7,'Gói','SP000007',60,120,_binary ''),(8,'Lon','SP000008',450,950,_binary ''),(9,'Gói','SP000009',600,1300,_binary ''),(10,'Lần','SP000010',0,1200,_binary ''),(12,'Thùng','SP000003',1000,1800,_binary '\0'),(13,'Dây','SP000003',350,600,_binary '\0');
/*!40000 ALTER TABLE `goods_unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory_check`
--

DROP TABLE IF EXISTS `inventory_check`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory_check` (
                                   `inventory_check_id` varchar(50) NOT NULL,
                                   `time_balance` timestamp NULL DEFAULT NULL,
                                   `note` varchar(350) DEFAULT NULL,
                                   `status` bigint DEFAULT NULL,
                                   `created_date` timestamp NULL DEFAULT NULL,
                                   `created_by_id` bigint DEFAULT NULL,
                                   PRIMARY KEY (`inventory_check_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory_check`
--

LOCK TABLES `inventory_check` WRITE;
/*!40000 ALTER TABLE `inventory_check` DISABLE KEYS */;
INSERT INTO `inventory_check` VALUES ('KK000001','2023-10-17 05:00:00','Kiểm kê lần 1',5,'2023-10-17 05:00:00',NULL),('KK000002',NULL,'',4,'2023-11-11 11:16:25',2),('KK000003',NULL,'',4,'2023-11-11 11:53:47',2),('KK000004',NULL,'',4,'2023-11-11 11:57:23',2),('KK000005',NULL,'',4,'2023-11-11 11:59:37',2),('KK000006',NULL,'',4,'2023-11-11 12:00:12',2);
/*!40000 ALTER TABLE `inventory_check` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory_check_detail`
--

DROP TABLE IF EXISTS `inventory_check_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory_check_detail` (
                                          `inventory_check_detail_id` bigint NOT NULL AUTO_INCREMENT,
                                          `inventory_check_id` varchar(50) NOT NULL,
                                          `goods_id` varchar(45) NOT NULL,
                                          `actual_inventory` bigint DEFAULT NULL,
                                          `quantity_discrepancy` bigint DEFAULT NULL,
                                          `value_discrepancy` float DEFAULT NULL,
                                          `inventory` bigint DEFAULT NULL,
                                          `cost` float DEFAULT NULL,
                                          PRIMARY KEY (`inventory_check_detail_id`),
                                          KEY `pk_icd_g_idx` (`goods_id`),
                                          KEY `pk_icd_ic_idx` (`inventory_check_id`),
                                          CONSTRAINT `pk_icd_g` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`),
                                          CONSTRAINT `pk_icd_ic` FOREIGN KEY (`inventory_check_id`) REFERENCES `inventory_check` (`inventory_check_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory_check_detail`
--

LOCK TABLES `inventory_check_detail` WRITE;
/*!40000 ALTER TABLE `inventory_check_detail` DISABLE KEYS */;
INSERT INTO `inventory_check_detail` VALUES (1,'KK000001','SP000001',100000,0,0,100000,500),(2,'KK000001','SP000002',100000,0,0,100000,300),(3,'KK000001','SP000003',100000,0,0,100000,50),(4,'KK000001','SP000007',100000,0,0,100000,60),(5,'KK000001','SP000008',100000,0,0,100000,450),(6,'KK000001','SP000009',100000,0,0,100000,600),(7,'KK000002','SP000003',9997810,9897831,494892000,99979,50),(9,'KK000003','SP000001',200000,100000,50000000,100000,500),(10,'KK000004','SP000001',100000,0,0,100000,500),(11,'KK000004','SP000002',100000,0,0,100000,300),(12,'KK000005','SP000003',0,-99979,-4998950,99979,50),(14,'KK000006','SP000003',128,-99851,-4992550,99979,50);
/*!40000 ALTER TABLE `inventory_check_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
                         `order_id` varchar(50) NOT NULL,
                         `reservation_detail_id` bigint NOT NULL,
                         `total_pay` float DEFAULT NULL,
                         `status` varchar(50) DEFAULT NULL,
                         `created_by_id` bigint DEFAULT NULL,
                         `created_date` timestamp NULL DEFAULT NULL,
                         PRIMARY KEY (`order_id`),
                         KEY `pk_o_rd_idx` (`reservation_detail_id`),
                         CONSTRAINT `pk_o_rd` FOREIGN KEY (`reservation_detail_id`) REFERENCES `reservation_detail` (`reservation_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES ('HD000001',1,1,'CONFIRMED',NULL,'2023-11-03 19:51:58'),('HD000002',1,30,'CONFIRMED',3,'2023-11-10 19:35:13'),('HD000003',1,1,'CONFIRMED',3,'2023-11-03 19:53:39'),('HD000004',1,1,'CONFIRMED',3,'2023-11-03 19:55:02'),('HD000005',1,1,'CONFIRMED',1,'2023-11-03 19:57:54'),('HD000006',1,1,'CONFIRMED',3,'2023-11-03 20:34:14'),('HD000007',1,1,'CONFIRMED',3,'2023-11-03 20:36:24'),('HD000008',1,1,'CANCEL_ORDER',3,'2023-11-03 20:37:10'),('HD000009',1,1380,'UNCONFIRMED',3,'2023-11-21 16:32:16'),('HD000010',1,30,'UNCONFIRMED',3,'2023-11-21 14:06:50'),('HD000011',1,30,'UNCONFIRMED',3,'2023-11-21 14:18:27'),('HD000012',1,4340,'UNCONFIRMED',2,'2023-11-21 15:28:42'),('HD000013',1,2020,'UNCONFIRMED',2,'2023-11-21 15:37:38'),('HD000014',1,1720,'UNCONFIRMED',2,'2023-11-21 15:45:00'),('HD000015',1,2400,'UNCONFIRMED',2,'2023-11-21 15:46:05'),('HD000016',1,1520,'UNCONFIRMED',2,'2023-11-21 15:55:45'),('HD000017',1,1520,'UNCONFIRMED',2,'2023-11-21 16:03:40'),('HD000018',1,2500,'UNCONFIRMED',2,'2023-11-21 16:07:22'),('HD000019',1,1520,'UNCONFIRMED',2,'2023-11-21 16:15:25'),('HD000020',1,1580,'UNCONFIRMED',2,'2023-11-21 16:31:01'),('HD000021',2,1520,'PAID',2,'2023-11-22 03:17:21'),('HD000022',2,2760,'CANCEL_ORDER',2,'2023-11-22 03:33:47');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
                                `order_detail_id` bigint NOT NULL AUTO_INCREMENT,
                                `order_id` varchar(50) NOT NULL,
                                `goods_id` varchar(50) NOT NULL,
                                `goods_unit_id` int NOT NULL,
                                `quantity` bigint DEFAULT NULL,
                                `price` float DEFAULT NULL,
                                PRIMARY KEY (`order_detail_id`),
                                KEY `pk_od_o_idx` (`order_id`) /*!80000 INVISIBLE */,
                                KEY `pk_od_g_idx` (`goods_id`) /*!80000 INVISIBLE */,
                                KEY `pk_od_gu_idx` (`goods_unit_id`),
                                CONSTRAINT `pk_od_g` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`),
                                CONSTRAINT `pk_od_gu` FOREIGN KEY (`goods_unit_id`) REFERENCES `goods_unit` (`goods_unit_id`),
                                CONSTRAINT `pk_od_o` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (1,'HD000001','SP000003',3,1,1),(2,'HD000001','SP000003',12,1,1),(3,'HD000001','SP000001',1,1,1),(4,'HD000001','SP000004',4,1,1),(5,'HD000002','SP000003',3,1,1),(6,'HD000003','SP000003',3,1,1),(7,'HD000004','SP000003',3,1,1),(8,'HD000005','SP000003',3,1,1),(18,'HD000006','SP000003',3,1,1),(19,'HD000006','SP000007',7,3,2),(20,'HD000007','SP000003',3,2,1),(21,'HD000008','SP000007',7,10,2),(24,'HD000011','SP000003',3,10,1),(25,'HD000011','SP000007',7,10,2),(26,'HD000012','SP000002',2,4,700),(27,'HD000012','SP000007',7,2,120),(28,'HD000012','SP000003',13,2,600),(29,'HD000012','SP000003',3,1,100),(30,'HD000013','SP000002',2,1,700),(31,'HD000013','SP000007',7,1,120),(32,'HD000013','SP000003',13,2,600),(33,'HD000014','SP000002',2,2,700),(34,'HD000014','SP000007',7,1,120),(35,'HD000014','SP000003',3,2,100),(36,'HD000015','SP000002',2,2,700),(37,'HD000015','SP000001',1,1,1000),(38,'HD000016','SP000002',2,2,700),(39,'HD000016','SP000007',7,1,120),(40,'HD000017','SP000002',2,2,700),(41,'HD000017','SP000007',7,1,120),(42,'HD000018','SP000002',2,2,700),(43,'HD000018','SP000001',1,1,1000),(44,'HD000018','SP000003',3,1,100),(45,'HD000019','SP000002',2,2,700),(46,'HD000019','SP000007',7,1,120),(47,'HD000020','SP000003',3,5,100),(48,'HD000020','SP000007',7,4,120),(49,'HD000020','SP000003',13,1,600),(50,'HD000009','SP000003',3,3,100),(51,'HD000009','SP000007',7,4,120),(52,'HD000009','SP000003',13,1,600),(53,'HD000021','SP000002',2,2,700),(54,'HD000021','SP000007',7,1,120),(59,'HD000022','SP000001',1,1,1000),(60,'HD000022','SP000003',3,2,100),(61,'HD000022','SP000007',7,3,120),(62,'HD000022','SP000003',13,2,600);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `policy`
--

DROP TABLE IF EXISTS `policy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `policy` (
                          `policy_id` varchar(50) NOT NULL,
                          `policy_name` varchar(250) DEFAULT NULL,
                          `note` varchar(350) DEFAULT NULL,
                          `start_time` timestamp NULL DEFAULT NULL,
                          `end_time` timestamp NULL DEFAULT NULL,
                          `status` int DEFAULT NULL,
                          PRIMARY KEY (`policy_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policy`
--

LOCK TABLES `policy` WRITE;
/*!40000 ALTER TABLE `policy` DISABLE KEYS */;
INSERT INTO `policy` VALUES ('CS000001','LATER_OVERTIME_SURCHARGE','Phụ thu trả muộn',NULL,NULL,1),('CS000002','ADDITIONAL_ADULT_SURCHARGE','Phụ thu thêm người lớn',NULL,NULL,1),('CS000003','OTHER_REVENUE','Khoản thu khác',NULL,NULL,1),('CS000004','CHANGE_CANCEL_ROOM_SURCHARGE','Đổi trả hủy phòng',NULL,NULL,1),('CS000005','SETUP_DEPOSIT','Tiền cọc',NULL,NULL,1),('CS000006','PROMOTION_POLICY','Chính Sách Khuyến mãi',NULL,NULL,1),('CS000007','SETUP_POINT_SYSTEM','Đổi trả hủy phòng',NULL,NULL,1),('CS000008','EARLIER_OVERTIME_SURCHARGE','Phụ Thu đến sớm',NULL,NULL,1),('CS000009','ADDITIONAL_CHILDREN_SURCHARGE','Chính Sách Trẻ em',NULL,NULL,1);
/*!40000 ALTER TABLE `policy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `policy_detail`
--

DROP TABLE IF EXISTS `policy_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `policy_detail` (
                                 `policy_detail_id` bigint NOT NULL AUTO_INCREMENT,
                                 `policy_id` varchar(50) NOT NULL,
                                 `room_category_id` varchar(50) NOT NULL,
                                 `customer_group_id` varchar(250) NOT NULL,
                                 `type` varchar(250) DEFAULT NULL,
                                 `unit` varchar(250) DEFAULT NULL,
                                 `limit_value` bigint DEFAULT NULL,
                                 `type_value` varchar(250) DEFAULT NULL,
                                 `other` varchar(250) DEFAULT NULL,
                                 `requirement` varchar(250) DEFAULT NULL,
                                 `policy_value` float DEFAULT NULL,
                                 `note` varchar(350) DEFAULT NULL,
                                 `auto_add_to_invoice` bit(1) DEFAULT NULL,
                                 `status` int DEFAULT NULL,
                                 PRIMARY KEY (`policy_detail_id`),
                                 KEY `pk_pd_rc_idx` (`room_category_id`),
                                 KEY `pk_pd_p_idx` (`policy_id`),
                                 KEY `pk_pd_cg_idx` (`customer_group_id`),
                                 CONSTRAINT `pk_pd_cg` FOREIGN KEY (`customer_group_id`) REFERENCES `customer_group` (`customer_group_id`),
                                 CONSTRAINT `pk_pd_p` FOREIGN KEY (`policy_id`) REFERENCES `policy` (`policy_id`),
                                 CONSTRAINT `pk_pd_rc` FOREIGN KEY (`room_category_id`) REFERENCES `room_category` (`room_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policy_detail`
--

LOCK TABLES `policy_detail` WRITE;
/*!40000 ALTER TABLE `policy_detail` DISABLE KEYS */;
INSERT INTO `policy_detail` VALUES (1,'CS000001','HP000001','NK000000','','Giờ',1,'VND',NULL,NULL,10000,NULL,NULL,1),(2,'CS000001','HP000001','NK000000','','Giờ',3,'VND',NULL,NULL,20000,NULL,NULL,1),(7,'CS000002','HP000001','NK000001','','Người',3,'VND',NULL,NULL,200000,NULL,NULL,6),(8,'CS000002','HP000001','NK000001','','Người',1,'VND',NULL,NULL,100000,NULL,NULL,6),(9,'CS000001','HP000001','NK000000',NULL,NULL,1,NULL,NULL,NULL,77777800,NULL,NULL,6),(10,'CS000001','HP000001','NK000000',NULL,'Giờ',1,'VND',NULL,NULL,77777800,NULL,NULL,6),(12,'CS000008','HP000001','NK000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,6),(13,'CS000008','HP000001','NK000000',NULL,NULL,2,NULL,NULL,NULL,10000,NULL,NULL,6),(14,'CS000001','HP000001','NK000000',NULL,NULL,1,NULL,NULL,NULL,77777800,NULL,NULL,6),(15,'CS000008','HP000001','NK000000',NULL,NULL,3,NULL,NULL,NULL,50000,NULL,NULL,6),(16,'CS000001','HP000001','NK000000',NULL,NULL,2,NULL,NULL,NULL,30000,NULL,NULL,6),(17,'CS000008','HP000001','NK000000',NULL,NULL,5,NULL,NULL,NULL,100000,NULL,NULL,6),(18,'CS000008','HP000001','NK000000',NULL,NULL,4,NULL,NULL,NULL,30000,NULL,NULL,6),(19,'CS000008','HP000001','NK000000',NULL,NULL,4,NULL,NULL,NULL,40000,NULL,NULL,6),(20,'CS000008','HP000002','NK000000',NULL,NULL,2,NULL,NULL,NULL,10000,NULL,NULL,6),(21,'CS000008','HP000003','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(22,'CS000008','HP000004','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(23,'CS000008','HP000005','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(24,'CS000008','HP000006','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(25,'CS000008','HP000007','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(26,'CS000008','HP000008','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(27,'CS000008','HP000009','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(28,'CS000008','HP000010','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(29,'CS000001','HP000001','NK000000',NULL,NULL,1,NULL,NULL,NULL,10000,NULL,NULL,6),(30,'CS000001','HP000002','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(31,'CS000001','HP000003','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(32,'CS000001','HP000004','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(33,'CS000001','HP000005','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(34,'CS000001','HP000006','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(35,'CS000001','HP000007','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(36,'CS000001','HP000008','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(37,'CS000001','HP000009','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(38,'CS000001','HP000010','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(39,'CS000008','HP000003','NK000000',NULL,NULL,1,NULL,NULL,NULL,20000,NULL,NULL,1),(40,'CS000008','HP000004','NK000000',NULL,NULL,1,NULL,NULL,NULL,100,NULL,NULL,1),(41,'CS000008','HP000005','NK000000',NULL,NULL,1,NULL,NULL,NULL,10,NULL,NULL,1),(42,'CS000008','HP000006','NK000000',NULL,NULL,1,NULL,NULL,NULL,20,NULL,NULL,1),(43,'CS000008','HP000007','NK000000',NULL,NULL,1,NULL,NULL,NULL,30,NULL,NULL,1),(44,'CS000008','HP000008','NK000000',NULL,NULL,1,NULL,NULL,NULL,57,NULL,NULL,1),(45,'CS000008','HP000009','NK000000',NULL,NULL,1,NULL,NULL,NULL,70,NULL,NULL,1),(46,'CS000008','HP000010','NK000000',NULL,NULL,3,NULL,NULL,NULL,100,NULL,NULL,1),(47,'CS000001','HP000002','NK000000',NULL,NULL,1,NULL,NULL,NULL,1000,NULL,NULL,1),(48,'CS000001','HP000003','NK000000',NULL,NULL,1,NULL,NULL,NULL,1000,NULL,NULL,1),(49,'CS000001','HP000004','NK000000',NULL,NULL,2,NULL,NULL,NULL,2000,NULL,NULL,1),(50,'CS000001','HP000005','NK000000',NULL,NULL,1,NULL,NULL,NULL,10,NULL,NULL,1),(51,'CS000001','HP000006','NK000000',NULL,NULL,1,NULL,NULL,NULL,20,NULL,NULL,1),(52,'CS000001','HP000007','NK000000',NULL,NULL,1,NULL,NULL,NULL,40,NULL,NULL,1),(53,'CS000001','HP000008','NK000000',NULL,NULL,1,NULL,NULL,NULL,50,NULL,NULL,1),(54,'CS000001','HP000009','NK000000',NULL,NULL,1,NULL,NULL,NULL,80,NULL,NULL,1),(55,'CS000001','HP000010','NK000000',NULL,NULL,3,NULL,NULL,NULL,90,NULL,NULL,1),(56,'CS000001','HP000001','NK000000',NULL,NULL,2,NULL,NULL,NULL,0,NULL,NULL,6),(57,'CS000001','HP000001','NK000000',NULL,NULL,2,NULL,NULL,NULL,10000,NULL,NULL,6),(58,'CS000009','HP000001','NK000000',NULL,'Người ',1,'VND',NULL,NULL,333333,NULL,NULL,6),(59,'CS000002','HP000001','NK000000',NULL,NULL,1,NULL,NULL,NULL,200000,NULL,NULL,6),(60,'CS000002','HP000001','NK000000',NULL,NULL,3,NULL,NULL,NULL,100000,NULL,NULL,6),(61,'CS000002','HP000002','NK000000',NULL,NULL,1,NULL,NULL,NULL,10,NULL,NULL,6),(62,'CS000002','HP000003','NK000000',NULL,NULL,1,NULL,NULL,NULL,20,NULL,NULL,1),(63,'CS000002','HP000004','NK000000',NULL,NULL,1,NULL,NULL,NULL,30,NULL,NULL,1),(64,'CS000002','HP000005','NK000000',NULL,NULL,1,NULL,NULL,NULL,40,NULL,NULL,1),(65,'CS000002','HP000006','NK000000',NULL,NULL,1,NULL,NULL,NULL,50,NULL,NULL,1),(66,'CS000002','HP000007','NK000000',NULL,NULL,1,NULL,NULL,NULL,60,NULL,NULL,1),(67,'CS000002','HP000008','NK000000',NULL,NULL,1,NULL,NULL,NULL,70,NULL,NULL,1),(68,'CS000002','HP000009','NK000000',NULL,NULL,1,NULL,NULL,NULL,80,NULL,NULL,1),(69,'CS000002','HP000010','NK000000',NULL,NULL,1,NULL,NULL,NULL,90,NULL,NULL,1),(70,'CS000002','HP000010','NK000000',NULL,NULL,2,NULL,NULL,NULL,100,NULL,NULL,6),(71,'CS000009','HP000001','NK000000',NULL,NULL,2,NULL,NULL,NULL,20000,NULL,NULL,6),(72,'CS000009','HP000002','NK000000',NULL,NULL,1,NULL,NULL,NULL,10,NULL,NULL,6),(73,'CS000009','HP000003','NK000000',NULL,NULL,1,NULL,NULL,NULL,20,NULL,NULL,1),(74,'CS000009','HP000004','NK000000',NULL,NULL,1,NULL,NULL,NULL,30,NULL,NULL,1),(75,'CS000009','HP000005','NK000000',NULL,NULL,1,NULL,NULL,NULL,40,NULL,NULL,1),(76,'CS000009','HP000006','NK000000',NULL,NULL,1,NULL,NULL,NULL,50,NULL,NULL,1),(77,'CS000009','HP000007','NK000000',NULL,NULL,1,NULL,NULL,NULL,60,NULL,NULL,1),(78,'CS000009','HP000008','NK000000',NULL,NULL,1,NULL,NULL,NULL,70,NULL,NULL,1),(79,'CS000009','HP000009','NK000000',NULL,NULL,1,NULL,NULL,NULL,80,NULL,NULL,1),(80,'CS000009','HP000010','NK000000',NULL,NULL,2,NULL,NULL,NULL,90,NULL,NULL,6),(81,'CS000002','HP000001','NK000000',NULL,NULL,2,NULL,NULL,NULL,200000,NULL,NULL,6),(82,'CS000002','HP000002','NK000000',NULL,NULL,1,NULL,NULL,NULL,10,NULL,NULL,1),(83,'CS000009','HP000001','NK000000',NULL,NULL,1,NULL,NULL,NULL,20000,NULL,NULL,1),(84,'CS000009','HP000001','NK000000',NULL,NULL,2,NULL,NULL,NULL,0,NULL,NULL,6),(85,'CS000009','HP000002','NK000000',NULL,NULL,1,NULL,NULL,NULL,10,NULL,NULL,1),(86,'CS000003','HP000000','NK000000','VAT',NULL,NULL,'%',NULL,NULL,10,NULL,_binary '',1),(87,'CS000003','HP000000','NK000000','Dịch Vụ',NULL,NULL,'VND',NULL,NULL,10000,NULL,_binary '',1),(88,'CS000003','HP000000','NK000000',NULL,NULL,NULL,'VND',NULL,NULL,33333,NULL,NULL,NULL),(89,'CS000003','HP000000','NK000000','Sức khoẻ',NULL,NULL,'VND',NULL,NULL,10000,NULL,NULL,2),(90,'CS000003','HP000000','NK000000','Thuế',NULL,NULL,'%',NULL,NULL,20,NULL,NULL,2),(91,'CS000008','HP000001','NK000000',NULL,NULL,4,NULL,NULL,NULL,60000,NULL,NULL,6),(92,'CS000001','HP000001','NK000000',NULL,NULL,4,NULL,NULL,NULL,30000,NULL,NULL,1),(93,'CS000002','HP000001','NK000000',NULL,NULL,1,NULL,NULL,NULL,20000,NULL,NULL,1),(94,'CS000002','HP000001','NK000000',NULL,NULL,2,NULL,NULL,NULL,25000,NULL,NULL,6),(95,'CS000009','HP000001','NK000000',NULL,NULL,2,NULL,NULL,NULL,30000,NULL,NULL,6),(96,'CS000008','HP000002','NK000000',NULL,NULL,4,NULL,NULL,NULL,10000,NULL,NULL,6),(97,'CS000008','HP000001','NK000000',NULL,NULL,4,NULL,NULL,NULL,50000,NULL,NULL,1),(98,'CS000008','HP000001','NK000000',NULL,NULL,5,NULL,NULL,NULL,60000,NULL,NULL,1),(99,'CS000009','HP000010','NK000000',NULL,NULL,1,NULL,NULL,NULL,90,NULL,NULL,1),(100,'CS000008','HP000002','NK000000',NULL,NULL,2,NULL,NULL,NULL,10000,NULL,NULL,1),(101,'CS000008','HP000002','NK000000',NULL,NULL,4,NULL,NULL,NULL,100,NULL,NULL,1);
/*!40000 ALTER TABLE `policy_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `price_list`
--

DROP TABLE IF EXISTS `price_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `price_list` (
                              `price_list_id` varchar(50) NOT NULL,
                              `price_list_name` varchar(255) DEFAULT NULL,
                              `effective_time_start` timestamp NULL DEFAULT NULL,
                              `effective_time_end` timestamp NULL DEFAULT NULL,
                              `status` bigint DEFAULT NULL,
                              `note` varchar(350) DEFAULT NULL,
                              PRIMARY KEY (`price_list_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `price_list`
--

LOCK TABLES `price_list` WRITE;
/*!40000 ALTER TABLE `price_list` DISABLE KEYS */;
INSERT INTO `price_list` VALUES ('BG000000','Default',NULL,NULL,NULL,NULL),('BG000001','tet trung thu 3','2023-05-02 00:00:00','2023-08-03 00:00:00',1,'string 1'),('BG000002','Theo thứ','2023-11-16 00:00:00','2024-11-16 00:00:00',1,'Bảng giá theo từng thứ trong tuần');
/*!40000 ALTER TABLE `price_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `price_list_detail`
--

DROP TABLE IF EXISTS `price_list_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `price_list_detail` (
                                     `price_list_detail_id` bigint NOT NULL AUTO_INCREMENT,
                                     `price_list_id` varchar(50) NOT NULL,
                                     `room_category_id` varchar(50) NOT NULL,
                                     `price_by_day` float DEFAULT NULL,
                                     `price_by_night` float DEFAULT NULL,
                                     `price_by_hour` float DEFAULT NULL,
                                     `time_apply` timestamp NULL DEFAULT NULL,
                                     `day_of_week` varchar(255) DEFAULT NULL,
                                     PRIMARY KEY (`price_list_detail_id`),
                                     KEY `pk_pld_pl_idx` (`price_list_id`),
                                     KEY `pk_pld_rc_idx` (`room_category_id`),
                                     CONSTRAINT `pk_pld_pl` FOREIGN KEY (`price_list_id`) REFERENCES `price_list` (`price_list_id`),
                                     CONSTRAINT `pk_pld_rc` FOREIGN KEY (`room_category_id`) REFERENCES `room_category` (`room_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `price_list_detail`
--

LOCK TABLES `price_list_detail` WRITE;
/*!40000 ALTER TABLE `price_list_detail` DISABLE KEYS */;
INSERT INTO `price_list_detail` VALUES (23,'BG000001','HP000001',300001,300001,200001,NULL,'2|3|4|'),(24,'BG000001','HP000001',299999,299999,199999,NULL,'5|6|7|'),(25,'BG000001','HP000002',700000,300000,200000,NULL,'3|2|'),(26,'BG000001','HP000002',700000,300000,200000,NULL,'4|5|'),(27,'BG000001','HP000002',700000,300000,200000,NULL,'6|7|8|'),(28,'BG000002','HP000001',500000,450000,300000,'2023-11-16 00:00:00','7|8|'),(29,'BG000002','HP000001',300000,300000,200000,NULL,'2|3|5|4|'),(30,'BG000002','HP000001',250000,249999,150000,NULL,'6|'),(31,'BG000002','HP000005',1200000,800000,500000,'2023-11-17 00:00:00','7|8|'),(32,'BG000002','HP000005',1200000,800000,500000,NULL,'2|3|');
/*!40000 ALTER TABLE `price_list_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recent_activity`
--

DROP TABLE IF EXISTS `recent_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recent_activity` (
                                   `recent_activity_id` int NOT NULL AUTO_INCREMENT,
                                   `staff_name` varchar(45) DEFAULT NULL,
                                   `action` varchar(45) DEFAULT NULL,
                                   `value` float DEFAULT NULL,
                                   `create_time` timestamp NULL DEFAULT NULL,
                                   PRIMARY KEY (`recent_activity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recent_activity`
--

LOCK TABLES `recent_activity` WRITE;
/*!40000 ALTER TABLE `recent_activity` DISABLE KEYS */;
INSERT INTO `recent_activity` VALUES (1,'Khánh','tạo hóa đơn',11999000,'2023-11-21 20:30:00'),(2,'Khánh','tạo hóa đơn',14375000,'2023-11-21 21:31:00');
/*!40000 ALTER TABLE `recent_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_room_capacity`
--

DROP TABLE IF EXISTS `report_room_capacity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report_room_capacity` (
                                        `report_room_capacity_id` int NOT NULL AUTO_INCREMENT,
                                        `create_date` timestamp NULL DEFAULT NULL,
                                        `room_capacity_value` float DEFAULT NULL,
                                        PRIMARY KEY (`report_room_capacity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_room_capacity`
--

LOCK TABLES `report_room_capacity` WRITE;
/*!40000 ALTER TABLE `report_room_capacity` DISABLE KEYS */;
INSERT INTO `report_room_capacity` VALUES (1,'2023-11-11 14:21:18',27),(2,'2023-11-12 14:25:03',56),(3,'2023-11-13 14:27:41',48),(4,'2023-11-14 14:30:41',11),(5,'2023-11-15 15:07:53',19.43),(6,'2023-11-16 16:02:14',10.167),(7,'2023-11-17 16:03:57',10.167),(8,'2023-11-18 16:04:18',1.0167),(9,'2023-11-19 16:06:00',10.9167),(10,'2023-11-20 17:09:11',0.583333),(11,'2023-11-21 17:10:02',58.3333),(18,'2023-11-22 23:59:01',5.83333),(19,'2023-11-22 23:59:03',5.83333);
/*!40000 ALTER TABLE `report_room_capacity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
                               `reservation_id` varchar(50) NOT NULL,
                               `customer_id` varchar(50) NOT NULL,
                               `price_list_id` varchar(50) NOT NULL,
                               `total_adults` bigint DEFAULT NULL,
                               `total_children` bigint DEFAULT NULL,
                               `status` varchar(50) DEFAULT NULL,
                               `total_deposit` float DEFAULT NULL,
                               `total_price` float DEFAULT NULL,
                               `duration_start` timestamp NULL DEFAULT NULL,
                               `duration_end` timestamp NULL DEFAULT NULL,
                               `note` varchar(350) DEFAULT NULL,
                               `created_date` timestamp NULL DEFAULT NULL,
                               `created_by_id` bigint DEFAULT NULL,
                               `staff_check_in` bigint DEFAULT NULL,
                               `staff_check_out` bigint DEFAULT NULL,
                               PRIMARY KEY (`reservation_id`),
                               KEY `pk_r_c_idx` (`customer_id`),
                               KEY `pk_r_pl_idx` (`price_list_id`),
                               CONSTRAINT `pk_r_c` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
                               CONSTRAINT `pk_r_pl` FOREIGN KEY (`price_list_id`) REFERENCES `price_list` (`price_list_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES ('DP000000','C000000','BG000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DP000001','C000001','BG000001',10,10,'CHECK_IN',100,200,'2023-11-03 14:00:06','2023-11-22 14:00:00','Ok','2023-11-01 14:30:00',1,NULL,NULL),('DP000002','C000001','BG000001',10,10,'CHECK_IN',100,200,'2023-11-03 18:00:00','2023-11-04 18:00:00','ok','2023-11-01 14:30:00',1,NULL,NULL),('DP000003','C000002','BG000001',10,10,'BOOKING',0,0,'2023-11-04 14:11:37','2023-11-05 14:11:37','Lưu tạm','2023-11-03 21:28:20',1,1,NULL),('DP000004','C000001','BG000001',10,10,'BOOKING',1,1,'2023-12-04 14:11:37','2023-12-05 14:11:37','ok','2023-11-03 21:28:20',1,1,NULL);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation_detail`
--

DROP TABLE IF EXISTS `reservation_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation_detail` (
                                      `reservation_detail_id` bigint NOT NULL AUTO_INCREMENT,
                                      `reservation_id` varchar(50) NOT NULL,
                                      `room_id` varchar(50) NOT NULL,
                                      `check_in_estimate` timestamp NULL DEFAULT NULL,
                                      `check_out_estimate` timestamp NULL DEFAULT NULL,
                                      `check_in_actual` timestamp NULL DEFAULT NULL,
                                      `check_out_actual` timestamp NULL DEFAULT NULL,
                                      `price` float DEFAULT NULL,
                                      `reservation_type` varchar(50) DEFAULT NULL,
                                      `status` varchar(50) DEFAULT NULL,
                                      `reservation_detail_status` bigint DEFAULT NULL,
                                      PRIMARY KEY (`reservation_detail_id`),
                                      KEY `pk_rd_r_idx` (`room_id`),
                                      KEY `pk__idx` (`reservation_id`),
                                      CONSTRAINT `fk_rd_r` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`reservation_id`),
                                      CONSTRAINT `pk_rd_r` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation_detail`
--

LOCK TABLES `reservation_detail` WRITE;
/*!40000 ALTER TABLE `reservation_detail` DISABLE KEYS */;
INSERT INTO `reservation_detail` VALUES (0,'DP000000','P000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1,'DP000001','P000001','2023-11-03 17:00:00','2023-11-04 17:00:00','2023-11-03 17:00:00','2023-11-22 14:00:00',100,'DAILY','CHECK_OUT',1),(2,'DP000001','P000002','2023-11-03 17:00:00','2023-11-04 17:00:00','2023-11-03 17:00:00','2023-11-04 17:00:00',100,'DAILY','BOOKING',1),(3,'DP000002','P000003','2023-11-03 18:00:00','2023-11-04 18:00:00','2023-11-03 18:00:00','2023-11-04 17:00:00',100,'DAILY','CHECK_OUT',1),(4,'DP000003','P000005','2023-11-04 14:11:37','2023-11-05 14:11:37',NULL,NULL,0,'DAILY','BOOKING',1),(5,'DP000004','P000001','2023-12-04 14:11:37','2023-12-05 14:11:37',NULL,NULL,1,'DAILY','BOOKING',1),(17,'DP000001','P000007','2023-11-03 14:00:06','2023-11-11 12:00:06',NULL,NULL,300000,'DAILY','BOOKING',1),(28,'DP000001','P000009','2023-11-11 14:00:19','2023-11-12 12:00:19',NULL,NULL,1200000,'DAILY','BOOKING',6),(29,'DP000001','P000009','2023-11-11 14:00:19','2023-11-12 12:00:19',NULL,NULL,1200000,'DAILY','BOOKING',6),(30,'DP000001','P000001','2023-11-19 14:00:53','2023-11-20 12:00:53',NULL,NULL,300000,'DAILY','BOOKING',6),(31,'DP000001','P000009','2023-11-22 23:00:00','2023-11-23 00:00:00','2023-11-23 00:30:00',NULL,500000,'HOURLY','CHECK_IN',1);
/*!40000 ALTER TABLE `reservation_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation_detail_customer`
--

DROP TABLE IF EXISTS `reservation_detail_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation_detail_customer` (
                                               `reservation_detail_customer_id` bigint NOT NULL AUTO_INCREMENT,
                                               `reservation_detail_id` bigint NOT NULL,
                                               `customer_id` varchar(50) NOT NULL,
                                               PRIMARY KEY (`reservation_detail_customer_id`),
                                               KEY `pk_rdr_rd_idx` (`reservation_detail_id`),
                                               KEY `pk_rdr_c_idx` (`customer_id`),
                                               CONSTRAINT `pk_rdr_c` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
                                               CONSTRAINT `pk_rdr_rd` FOREIGN KEY (`reservation_detail_id`) REFERENCES `reservation_detail` (`reservation_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation_detail_customer`
--

LOCK TABLES `reservation_detail_customer` WRITE;
/*!40000 ALTER TABLE `reservation_detail_customer` DISABLE KEYS */;
INSERT INTO `reservation_detail_customer` VALUES (0,0,'C000000'),(1,1,'C000001'),(2,2,'C000001'),(3,3,'C000001'),(4,4,'C000002'),(5,4,'C000003'),(7,1,'C000008');
/*!40000 ALTER TABLE `reservation_detail_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
                        `room_id` varchar(50) NOT NULL,
                        `room_name` varchar(250) DEFAULT NULL,
                        `room_category_id` varchar(50) NOT NULL,
                        `floor_id` int NOT NULL,
                        `status` int DEFAULT NULL,
                        `booking_status` varchar(100) DEFAULT NULL,
                        `condition_status` varchar(100) DEFAULT NULL,
                        `image` longblob,
                        `note` varchar(250) DEFAULT NULL,
                        `created_by_id` int DEFAULT NULL,
                        `updated_by_id` int DEFAULT NULL,
                        `created_date` timestamp NULL DEFAULT NULL,
                        `updated_date` timestamp NULL DEFAULT NULL,
                        PRIMARY KEY (`room_id`),
                        KEY `pk_r_rc_idx` (`room_category_id`),
                        KEY `pk_r_f_idx` (`floor_id`),
                        CONSTRAINT `pk_r_f` FOREIGN KEY (`floor_id`) REFERENCES `floor` (`floor_id`),
                        CONSTRAINT `pk_r_rc` FOREIGN KEY (`room_category_id`) REFERENCES `room_category` (`room_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES ('P000000','Sảnh','HP000000',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('P000001','P.101','HP000001',1,1,'ROOM_USING','ROOM_CLEAN',NULL,'Phòng cơ bản',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000002','P.102','HP000001',1,1,'ROOM_USING','ROOM_UNCLEAN',NULL,'Phòng cơ bản',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000003','P.201','HP000002',2,1,'ROOM_USING','ROOM_CLEAN',NULL,'Phòng hạng trung',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000004','P.202','HP000002',2,1,'ROOM_EMPTY','ROOM_CLEAN',NULL,'Phòng hạng trung',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000005','P.301','HP000003',3,1,'ROOM_EMPTY','ROOM_CLEAN',NULL,'Phòng đơn',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000006','P.302','HP000003',3,1,'ROOM_EMPTY','ROOM_CLEAN',NULL,'Phòng đơn',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000007','P.401','HP000004',4,1,'ROOM_EMPTY','ROOM_CLEAN',NULL,'Phòng gia đình',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000008','P.402','HP000004',4,1,'ROOM_EMPTY','ROOM_CLEAN',NULL,'Phòng gia đình',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000009','P.501','HP000005',5,1,'ROOM_EMPTY','ROOM_CLEAN',NULL,'Phòng VIP',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000010','P.502','HP000005',5,1,'ROOM_EMPTY','ROOM_CLEAN',NULL,'Phòng VIP',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_category`
--

DROP TABLE IF EXISTS `room_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_category` (
                                 `room_category_id` varchar(50) NOT NULL,
                                 `room_category_name` varchar(250) DEFAULT NULL,
                                 `price_by_day` float DEFAULT NULL,
                                 `price_by_night` float DEFAULT NULL,
                                 `price_by_hour` float DEFAULT NULL,
                                 `num_of_adults` bigint DEFAULT NULL,
                                 `num_of_children` bigint DEFAULT NULL,
                                 `num_max_of_adults` bigint DEFAULT NULL,
                                 `num_max_of_children` bigint DEFAULT NULL,
                                 `room_area` float DEFAULT NULL,
                                 `status` int DEFAULT NULL,
                                 `description` varchar(500) DEFAULT NULL,
                                 `image` longblob,
                                 `created_by_id` bigint DEFAULT NULL,
                                 `updated_by_id` bigint DEFAULT NULL,
                                 `created_date` timestamp NULL DEFAULT NULL,
                                 `updated_date` timestamp NULL DEFAULT NULL,
                                 PRIMARY KEY (`room_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_category`
--

LOCK TABLES `room_category` WRITE;
/*!40000 ALTER TABLE `room_category` DISABLE KEYS */;
INSERT INTO `room_category` VALUES ('HP000000','Sảnh',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('HP000001','Phòng cơ bản',300000,300000,200000,2,1,3,2,20,1,'Phòng cơ bản','',NULL,NULL,'2023-10-15 17:00:00','2023-11-20 16:58:04'),('HP000002','Phòng hạng trung',700000,300000,200000,3,1,4,2,30,1,'Phòng hạng trung',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('HP000003','Phòng đơn',400000,400000,250000,1,1,2,2,18,1,'Phòng đơn',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-11-18 01:25:04'),('HP000004','Phòng gia đình',900000,500000,300000,4,1,5,2,40,1,'Phòng gia đình',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('HP000005','Phòng VIP',1200000,800000,500000,2,1,3,2,25,1,'Phòng VIP',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('HP000006','Phòng Suite',1500000,1000000,600000,2,1,3,2,30,1,'Phòng Suite',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('HP000007','Phòng VIP2',2000000,1500000,800000,6,1,7,2,70,1,'Phòng VIP2',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('HP000008','Phòng Ocean View',800000,500000,350000,3,1,3,1,35,1,'Phòng với view biển',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('HP000009','Phòng Deluxe Suite',1800000,1200000,700000,2,1,3,2,35,1,'Phòng Deluxe Suite',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('HP000010','Phòng Tổng Thống',3000000,2000000,1000000,2,1,3,2,40,1,'Phòng Phủng',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('HP000011','test 1',0,0,0,0,0,0,0,1,1,'','',NULL,NULL,'2023-11-19 16:59:19',NULL),('HP000012',' ',0,0,0,0,0,0,0,1,1,'','',NULL,NULL,'2023-11-19 16:59:57',NULL);
/*!40000 ALTER TABLE `room_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
                         `staff_id` bigint NOT NULL AUTO_INCREMENT,
                         `staff_name` varchar(250) DEFAULT NULL,
                         `username` varchar(50) DEFAULT NULL,
                         `password` varchar(150) DEFAULT NULL,
                         `role` varchar(250) DEFAULT NULL,
                         `status` varchar(250) DEFAULT NULL,
                         `dob` timestamp NULL DEFAULT NULL,
                         `address` varchar(350) DEFAULT NULL,
                         `email` varchar(350) DEFAULT NULL,
                         `gender` bit(1) DEFAULT NULL,
                         `identity` varchar(350) DEFAULT NULL,
                         `tax_code` varchar(350) DEFAULT NULL,
                         `phone_number` varchar(50) DEFAULT NULL,
                         `image` longblob,
                         `created_by_id` bigint DEFAULT NULL,
                         `updated_by_id` bigint DEFAULT NULL,
                         `created_date` timestamp NULL DEFAULT NULL,
                         `updated_date` timestamp NULL DEFAULT NULL,
                         `department_id` varchar(50) NOT NULL,
                         PRIMARY KEY (`staff_id`),
                         KEY `pk_s_d_idx` (`department_id`),
                         CONSTRAINT `pk_c_d` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'df','df',NULL,'ROLE_RECEPTIONIST','NO_ACTIVE','2023-11-03 00:00:00','q',NULL,_binary '\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'PB000001'),(2,NULL,'tien','$2a$10$ato6VNKwexGRQqMkV1pH2Os187zIP88S98kS55hXMYYoUY2xJAI0.','ROLE_MANAGER',NULL,NULL,'w',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'PB000001'),(3,NULL,'h','$2a$10$YZ226KZo1EElw05xxbVq4OXRMJEXJt1UWGA7ifbrnVvi5z.MbUDt.','ROLE_MANAGER',NULL,NULL,'e','nguyenhuync2937@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'PB000001'),(4,NULL,'string','$2a$10$19lUCuf4jA3G5lvTcckdm.NHZN4CzJIN2QaxGJXh58pDc2HItXff2','ROLE_MANAGER',NULL,NULL,'r',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'PB000001'),(5,'mm','dfmm','$2a$10$K4B0lq/iZPTUNM/DNaLa6erb0FCiOzIDhzFAYVa/cCtShC2xgi7gi','ROLE_RECEPTIONIST','NO_ACTIVE','2023-11-03 00:00:00','t','asd',_binary '\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'PB000001'),(6,NULL,'admin','$2a$10$Z/GE11unhNydjntS/VUD6uG267P0qv9p3jl0L8tu.xf7hp/DAe9Ui','ROLE_MANAGER',NULL,NULL,'y',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'PB000001'),(9,'d',NULL,'$2a$10$k1x79OlrPmTNdianth//9uLI5.3CsMS0yx9fXdDgd3SyAScMihSWe','ROLE_RECEPTIONIST','ACTIVE','2023-11-22 00:00:00','d','d',_binary '','d','d','0812569567','',NULL,NULL,NULL,NULL,'PB000001');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time_use`
--

DROP TABLE IF EXISTS `time_use`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `time_use` (
                            `time_use_id` bigint NOT NULL AUTO_INCREMENT,
                            `end_time_night` time DEFAULT NULL,
                            `start_time_day` time DEFAULT NULL,
                            `end_time_day` time DEFAULT NULL,
                            `start_time_night` time DEFAULT NULL,
                            `time_bonus_day_type` varchar(255) DEFAULT NULL,
                            `time_bonus_hour` bigint DEFAULT NULL,
                            `time_bonus_day` bigint DEFAULT NULL,
                            PRIMARY KEY (`time_use_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_use`
--

LOCK TABLES `time_use` WRITE;
/*!40000 ALTER TABLE `time_use` DISABLE KEYS */;
INSERT INTO `time_use` VALUES (1,'09:00:00','14:00:00','12:00:00','22:00:00','TIME_LATE',15,7);
/*!40000 ALTER TABLE `time_use` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token` (
                         `token_id` bigint NOT NULL AUTO_INCREMENT,
                         `token` varchar(200) DEFAULT NULL,
                         `staff_id` bigint NOT NULL,
                         `expiration_time` timestamp NULL DEFAULT NULL,
                         PRIMARY KEY (`token_id`),
                         KEY `fk_prt_s_idx` (`staff_id`),
                         CONSTRAINT `fk_prt_s` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=326 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (1,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4NzczNTYwLCJleHAiOjE2OTg3NzcxNjB9.yRM_MVW1qutFy7rk0tPyTHUue96kLVCzThlJxvMJ5p4',1,'1980-02-01 00:00:00'),(2,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4Nzc3NTIwLCJleHAiOjE2OTg3ODExMjB9.Ci8ZMpn1ZSJdFsa8owpctmqO5hcJ05qfLy3rYgXIblM',1,'1980-02-01 00:00:00'),(3,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4NzgyMDMyLCJleHAiOjE2OTg3ODU2MzJ9.k8Trt4BgQWjlzULbMj7ap0SjRiiiwiCfqhuaRCjUKFE',1,'1980-02-01 00:00:00'),(4,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4Nzg0MTA2LCJleHAiOjE2OTg3ODc3MDZ9.kj1mBjHMED-uphzyblhrc7qWSifVG3IiUcisbjIEqyQ',1,'1980-02-01 00:00:00'),(5,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4ODU0MDAxLCJleHAiOjE2OTg4NTc2MDF9.kdY-Wa7e6ooo6k1f6_-t0JjkQOqJQbdStY07I09RwGE',1,'1980-02-01 00:00:00'),(6,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4ODczNTM0LCJleHAiOjE2OTg4NzcxMzR9.XNFcKOG8ujN-GeyyUfxCu6psJJ6faz9TGmXYz8-m-fI',1,'1980-02-01 00:00:00'),(7,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4ODc0MTE0LCJleHAiOjE2OTg4Nzc3MTR9.1OKwHqewTKSnv4guPZBof7OyNsenV3t3BT7iHnHPk9o',1,'1980-02-01 00:00:00'),(8,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTA0MjM2LCJleHAiOjE2OTg5MDc4MzZ9.75I-cg6c6HXJuBAAbg4zJSWyKj-8Behvmy_cgQT-hzc',1,'1980-02-01 00:00:00'),(9,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTA4NjU1LCJleHAiOjE2OTg5MTIyNTV9.mz3lEyfMIdweWaYKelTwKJ6QlcQeLj9zSOj7FyI6xj0',1,'1980-02-01 00:00:00'),(10,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTEzNDEwLCJleHAiOjE2OTg5MTcwMTB9.azdqECQPtPP4XPR6CieCDu51XS0NbMGvj4KeAhZ2PsY',1,'1980-02-01 00:00:00'),(11,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTE3MDcwLCJleHAiOjE2OTg5MjA2NzB9.ydAJ3bnvkvK6Q2B_EPb-3v3GUm08IVQbyego__BA4qE',1,'1980-02-01 00:00:00'),(12,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTIwODYwLCJleHAiOjE2OTg5MjQ0NjB9.CMAK8UspBY4hnmO3yq4urt066U-UMHLDp1q7FCRc920',1,'1980-02-01 00:00:00'),(13,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTI0ODYxLCJleHAiOjE2OTg5Mjg0NjF9.L-fCED0RVM9UUMSd0PkDw6pP5HCKihg7u5iZs6aXYs8',1,'1980-02-01 00:00:00'),(14,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTI0ODYyLCJleHAiOjE2OTg5Mjg0NjJ9.Ph1DHzbEAeb3qXm7OG-BTpihYwhJbHpQLNpWMwRTITU',1,'1980-02-01 00:00:00'),(15,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTMxMTY2LCJleHAiOjE2OTg5MzQ3NjZ9.RYUoVy0blZckHHg1gOuoqM1RUToxhwMoI1ytrKvSYwM',1,'1980-02-01 00:00:00'),(16,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTM0Mjg3LCJleHAiOjE2OTg5Mzc4ODd9.n4_g0d6q6MW3ZBsJCHsU-qc90OxuEDciKmKCgGQzikg',1,'1980-02-01 00:00:00'),(17,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTM2MDM3LCJleHAiOjE2OTg5Mzk2Mzd9.c-ighL6Xc8rfFTsECQvJnxDatY8KrCiaTC-E2BUQ1ZQ',1,'1980-02-01 00:00:00'),(18,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTM2MDY3LCJleHAiOjE2OTg5Mzk2Njd9.5u4hCOcAcUU66XSXDq4N1GG8Ta5dcpbLI6sfYZO5hC4',1,'1980-02-01 00:00:00'),(19,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTM2MDcyLCJleHAiOjE2OTg5Mzk2NzJ9.6P_kSUvMl50A6aVYoUcjA5aXvS8a4RQWHMT6JaSTGOI',1,'1980-02-01 00:00:00'),(20,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTM2MDg2LCJleHAiOjE2OTg5Mzk2ODZ9.03IrBmHBqZtRo-vBDLVoHDX5AqlH7Z-9ebJ-_Fc3yIU',1,'1980-02-01 00:00:00'),(21,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTM3MDUxLCJleHAiOjE2OTg5NDA2NTF9.zHHH95FB_3JFeMwIglbtoua9CEyL5nFQJslYYyAWiks',1,'1980-02-01 00:00:00'),(22,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTM3ODM4LCJleHAiOjE2OTg5NDE0Mzh9.l5JboIxvr-rq5Wfa77pRM-Ufo5idITLKiXrrLX6VLUI',1,'1980-02-01 00:00:00'),(23,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTM3OTIyLCJleHAiOjE2OTg5NDE1MjJ9.WIVcu8uB39XMS1lsKgPZ2NBIDTGLAC_mPL5Yzw9_pRs',1,'1980-02-01 00:00:00'),(24,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTM4MzQ3LCJleHAiOjE2OTg5NDE5NDd9.iesQf1prXPr-slaZzoKOK-IlxAj_N89tvqci19k4obA',1,'1980-02-01 00:00:00'),(25,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTM4ODAxLCJleHAiOjE2OTg5NDI0MDF9.ZUYrZncYLE_PsrUN5NaMlFWDIDQNCdqUngq0wQI4VE4',1,'1980-02-01 00:00:00'),(26,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTM4ODU3LCJleHAiOjE2OTg5NDI0NTd9.V4ag9EJXxf2ktH3lrNivOSPaWmmT4hQMfh5lMjUHvRs',1,'1980-02-01 00:00:00'),(27,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTg5Mzk0MDUsImV4cCI6MTY5ODk0MzAwNX0._1iUn3ZrRhzRn6WQDD3pWh4wRbW1WebUXuabCT39sDk',2,'1980-02-01 00:00:00'),(28,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTg5NDMxOTYsImV4cCI6MTY5ODk0Njc5Nn0.NinbWKcjKZFVlLbDD4RMdS4MaeZlvm-ta8QpekHtwFU',2,'1980-02-01 00:00:00'),(29,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTg5NDc3ODQsImV4cCI6MTY5ODk1MTM4NH0.LMIl2-XtisYtvPWyWYhtq251uRWzfSfMzOFKc1BuSzw',2,'1980-02-01 00:00:00'),(30,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTg5NzU2MjcsImV4cCI6MTY5ODk3OTIyN30.b1VrUD-DUfHHaNsJiTuP1XnxP3czPN34ydFtW8Si4rI',2,'1980-02-01 00:00:00'),(31,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTg5ODA1MzksImV4cCI6MTY5ODk4NDEzOX0.r2iOtMepX4f362MlSllz9V_CGJUBOn2SRvDZwkNz4ME',2,'1980-02-01 00:00:00'),(32,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTg5ODYwMjMsImV4cCI6MTY5ODk4OTYyM30.MgekcT1p4aApSMGSmi1QmjkObbBy1uItKHQeDyqI7qw',2,'1980-02-01 00:00:00'),(33,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTg5ODk4MTEsImV4cCI6MTY5ODk5MzQxMX0.tBLQAIqSK38y4OuqU_GsI0xrte_WdLot6cie4MrlmJk',2,'1980-02-01 00:00:00'),(34,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTg5OTM1OTIsImV4cCI6MTY5ODk5NzE5Mn0.DzUwmrmgZuJ-V8gVdBPhy53W_5M2Hq9ZCJ_XkA4ywZI',2,'1980-02-01 00:00:00'),(35,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTg5OTkwNzIsImV4cCI6MTY5OTAwMjY3Mn0.RMxo6oh5VwRaDev27WxAmaPm-11aOUUEkUbHli2SflU',2,'1980-02-01 00:00:00'),(36,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkwMDUyNTQsImV4cCI6MTY5OTAwODg1NH0.u6by0C6CvvB1AfOpQpSpM1cftZyf0If-TTLqfZMVNmQ',2,'1980-02-01 00:00:00'),(37,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkwMDk1NjQsImV4cCI6MTY5OTAxMzE2NH0.3PKQOCRaeIaExMxPxPeaTInqe43SeUbtac2yjNyaSiI',2,'1980-02-01 00:00:00'),(38,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkwMTMzNTQsImV4cCI6MTY5OTAxNjk1NH0.AWH0ZN7qLzMSfeIEO4QtoX9nrq0-IsZy7Fszq2OpVug',2,'1980-02-01 00:00:00'),(39,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5MDIwNTI1LCJleHAiOjE2OTkwMjQxMjV9.yWT006Xx9yQmzuy0076zNFGJAK2knSIggzvgyY9DoJ4',1,'1980-02-01 00:00:00'),(40,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5MDIwNzA5LCJleHAiOjE2OTkwMjQzMDl9.O-KfKUxLF3XoWgcMi_Z6QhSSxSstoKpUd7G3WFbtaAQ',1,'1980-02-01 00:00:00'),(41,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkwMjA5MTQsImV4cCI6MTY5OTAyNDUxNH0.x-h96LDVV0gR_CqktZHf5Iqyfr66-iB_oTF2bx0JzJc',2,'1980-02-01 00:00:00'),(42,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkwMjU5MTUsImV4cCI6MTY5OTAyOTUxNX0.YyZIPGe3_0OHjB5Da_Cv36qQpHbIrauao9XrXinpfR8',2,'1980-02-01 00:00:00'),(43,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkwMzExODYsImV4cCI6MTY5OTAzNDc4Nn0.HyPhMPx7tczXz3Bsvmw1eE4DbnWqoUOnW4uFYril-HY',2,'1980-02-01 00:00:00'),(44,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTkwMzEzMzksImV4cCI6MTY5OTAzNDkzOX0.N8v6bAkBBS9iZe-3GcheRzuyrtYZOEMP0JFAP4y1EAY',3,'1980-02-01 00:00:00'),(45,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTkwMzIyNDcsImV4cCI6MTY5OTAzNTg0N30.W-bCZguof6Rf7qr3srnwnJt6GTvVyc-DF8Bbb3IkXQM',3,'1980-02-01 00:00:00'),(46,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkwMzUwNDcsImV4cCI6MTY5OTAzODY0N30.JnrDndKsxIh0VQOWkWa5oNs81X0OvRjTqbECuJgIzGg',2,'1980-02-01 00:00:00'),(47,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTkwNDEyMDQsImV4cCI6MTY5OTA0NDgwNH0.YLztsWeewzUW6WLRXFRwgUH_SHT9lcLVoz8Bev12jBA',3,'1980-02-01 00:00:00'),(48,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5MDQxMjA2LCJleHAiOjE2OTkwNDQ4MDZ9.obq2Kdt9ATQ78dXJyy4n004t6WK7eaQ5H0E4eUmw4NE',1,'1980-02-01 00:00:00'),(49,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkwNjgwMDAsImV4cCI6MTY5OTA3MTYwMH0.iy_RDqHoRf4BW6KxdI0zK2PDsYFClm8yLSEzBLP2Fv4',2,'1980-02-01 00:00:00'),(50,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkwNzk2MzgsImV4cCI6MTY5OTA4MzIzOH0.i-psjEowuIK3MCgaxQvb3x-FiYjNMf4oKNyiZsufepo',2,'1980-02-01 00:00:00'),(51,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkwODY5MTQsImV4cCI6MTY5OTA5MDUxNH0.PSX4rtWdBCvL9iImWVcvYF-P-zoJ-pKSnuD087hT6Fs',2,'1980-02-01 00:00:00'),(52,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5MDg5Njg1LCJleHAiOjE2OTkwOTMyODV9.6dCoXOnNmDVduU6oGgBxBXg1k1Qw6w9zPy_gf1Ts7d8',1,'1980-02-01 00:00:00'),(53,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkwOTI2NTAsImV4cCI6MTY5OTA5NjI1MH0.kRhelJzM3EET1WT9cGRZRvJvEACKOsqHsinmThBvfJo',2,'1980-02-01 00:00:00'),(54,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkwOTcwMzAsImV4cCI6MTY5OTEwMDYzMH0.EOaonUcLo-k1ljXOM5KB0Xw0TlMJd-9tkw5sBW-V148',2,'1980-02-01 00:00:00'),(55,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTkwOTc1NjMsImV4cCI6MTY5OTEwMTE2M30.chBNEJ90q3EA7OgRs1AJOCUm7ht6CNytEUaD25a_Pkk',3,'1980-02-01 00:00:00'),(56,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkxMDE5OTEsImV4cCI6MTY5OTEwNTU5MX0.vfzSuqv6DUk159wURveZj9m3Baz396upY3jSUcvRyzA',2,'1980-02-01 00:00:00'),(57,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkxMDgwMDEsImV4cCI6MTY5OTExMTYwMX0.A6gNTHxnuHwseJpzWps5hWJOXLklvFgxuTyc6r7wCxs',2,'1980-02-01 00:00:00'),(58,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkxMTc1MDUsImV4cCI6MTY5OTEyMTEwNX0.J-43E3gG1hUFNztYxQf7UscT9ztQnsF19HZwv2N2ROc',2,'1980-02-01 00:00:00'),(59,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkxNjM2MTksImV4cCI6MTY5OTE2NzIxOX0.SKYWwgIM693nOp7GwPzhy0LUfHwdHLBbHyN_3RLn7Zk',2,'1980-02-01 00:00:00'),(60,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkxNjcyNzMsImV4cCI6MTY5OTE3MDg3M30.L7gonvin_iB1k98_nF_cBV4yXxOqGrK1j1zWS1djchU',2,'1980-02-01 00:00:00'),(61,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkxNzMzMTgsImV4cCI6MTY5OTE3NjkxOH0.SwZ_nhx-zvidEHlXH3js9a3CfT_LUbuMiBBE8kJOVvo',2,'1980-02-01 00:00:00'),(62,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5MTk4NjkwLCJleHAiOjE2OTkyMDIyOTB9.meEzZbmcc0YAGeKzZJ9VnI2OGOQNFQNWUswUWezfjB4',1,'1980-02-01 00:00:00'),(63,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkyMDI5NzYsImV4cCI6MTY5OTIwNjU3Nn0.ndIn_Gjz9fPHVOxxdn3MoN-wNlssjLx-pYqCoHKd6fo',2,'1980-02-01 00:00:00'),(64,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5MjA5NDc0LCJleHAiOjE2OTkyMTMwNzR9.iIZ1iRL4z5PO_JHjbaoM8bygAujPDSJDOhhFE4XyhVI',1,'1980-02-01 00:00:00'),(65,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkyMzk2MDUsImV4cCI6MTY5OTI0MzIwNX0.3dMenel9FMB5AjCP7clwGgYaQnYI1OH3P1QmcZdM9g4',2,'1980-02-01 00:00:00'),(66,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkyNDQyODAsImV4cCI6MTY5OTI0Nzg4MH0.v65lha7pN8EOxKTLDyUlQQJPhOHmIp_M5E3bv9CvKBQ',2,'1980-02-01 00:00:00'),(67,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkyNTUxNzksImV4cCI6MTY5OTI1ODc3OX0.79aGhwrtbt1eKXKVTPZdVoA3agb-me-f_ZQsY6kgojI',2,'1980-02-01 00:00:00'),(68,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkyNTgyMzEsImV4cCI6MTY5OTI2MTgzMX0.CDbapS4LD56__YGdIuKM_bWFyPYWyaTu8je14jNdvBA',2,'1980-02-01 00:00:00'),(69,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkyNjI2MzIsImV4cCI6MTY5OTI2NjIzMn0.JIqtto8AKIeii5rK5FmK1XjBKAE8xFJt8RhCpYQLq2w',2,'1980-02-01 00:00:00'),(70,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkyNjY2OTcsImV4cCI6MTY5OTI3MDI5N30.zn7AF2FIU0OUfG4we0Zq5bZeFYk1qieG-vQQ2cBUrbo',2,'1980-02-01 00:00:00'),(71,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5MjY2ODM4LCJleHAiOjE2OTkyNzA0Mzh9.aXlksVeUjLSa9__-CzE47wv3wmoE6XzSIW6ZJ2VmSHk',1,'1980-02-01 00:00:00'),(72,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkyNjc4MTQsImV4cCI6MTY5OTI3MTQxNH0.OO0YARfEwMRsGmiEAb8vpgF_GU1eRJR7VQMZfFWDb94',2,'1980-02-01 00:00:00'),(73,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkyNjc4MjYsImV4cCI6MTY5OTI3MTQyNn0.W9ODOZd395f0OWczKoUWz7u4caDCjmq-A1xuaPPaRUI',2,'1980-02-01 00:00:00'),(74,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkyNzcwNzYsImV4cCI6MTY5OTI4MDY3Nn0.oI80lNkO3sGB_zKrQsgt2zuzNarUnSrXzkhbk2ee9aQ',2,'1980-02-01 00:00:00'),(75,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkyNzc1NzcsImV4cCI6MTY5OTI4MTE3N30.6ezhOyhxV_kK_3yieC2k1QYWtpa0LhuhL84OdTV-O6I',2,'1980-02-01 00:00:00'),(76,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkyNzkxNzksImV4cCI6MTY5OTI4Mjc3OX0.ubCVPQpkKgirS5fjlIyt6TFTdjivvA-kWxqemvan_E8',2,'1980-02-01 00:00:00'),(77,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkyODI4MjUsImV4cCI6MTY5OTI4NjQyNX0.7eo27ztAFtI38ips-j6Q38_VZf8sXghqOaKURDm8-yM',2,'1980-02-01 00:00:00'),(78,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkzMzY4NjAsImV4cCI6MTY5OTM0MDQ2MH0.yWxShruzKJurIKzyjvuhnqu-olrqG6UdSQXadAvYz-w',2,'1980-02-01 00:00:00'),(79,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTkzNDQ4MTAsImV4cCI6MTY5OTM0ODQxMH0.eDmonAPZ7Jy-m93yx-wKbN0BXbuEWkEd_esfFgJpFoQ',3,'1980-02-01 00:00:00'),(80,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkzNDk0NDAsImV4cCI6MTY5OTM1MzA0MH0.grG9ZKAtiUFWeihI9Zf-0trcUQMi3_qZS5qmG97WzNA',2,'1980-02-01 00:00:00'),(81,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkzNTM5NTksImV4cCI6MTY5OTM1NzU1OX0.OiISKM8f4aSQpFMCBPXf64m6xrIiOhKtHEHVolsNCwk',2,'1980-02-01 00:00:00'),(82,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkzNjYxODIsImV4cCI6MTY5OTM2OTc4Mn0._e5P4PqGJXhTKC2mqacp6ktT1DF5jUm11oxyzqvx0ac',2,'1980-02-01 00:00:00'),(83,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTkzNzMyMTcsImV4cCI6MTY5OTM3NjgxN30.sCodEdbU6I4RH3hD5iVsZBd095unEetgQN6kJHGHe7g',2,'1980-02-01 00:00:00'),(84,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk0MDk3MjEsImV4cCI6MTY5OTQxMzMyMX0.dEQD-fYTUWz-WWfjBc7Xq6Wo01Zbb510na2zeFdtkOY',2,'1980-02-01 00:00:00'),(85,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk0MTk3NDcsImV4cCI6MTY5OTQyMzM0N30.ghqxO33W1iHrOcqXDLMeA4elCD9fNVxVsiOR3Wf1Go0',3,'1980-02-01 00:00:00'),(86,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk0MzAxMTksImV4cCI6MTY5OTQzMzcxOX0.yxvQLbTA93Sk7w9I5Ely0m1VQRBD-30qNYCDdFosZPk',2,'1980-02-01 00:00:00'),(87,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5NDMxNjI1LCJleHAiOjE2OTk0MzUyMjV9.WTOaZzanglwz3MJl-s-o2CTCjV_zUwoGOHmMnQ91jdA',1,'1980-02-01 00:00:00'),(88,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk0MzM5MDEsImV4cCI6MTY5OTQzNzUwMX0.HhUEVxF4U_edAFOcWIgz1AgZV3JSjTVBnHf-nTFNapE',2,'1980-02-01 00:00:00'),(89,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk0Mzg4ODUsImV4cCI6MTY5OTQ0MjQ4NX0.7ewGXE-9hEFlG47k4BssFdAAUcCLJyPmP-WWOrczlG8',2,'1980-02-01 00:00:00'),(90,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk0NTA4MzEsImV4cCI6MTY5OTQ1NDQzMX0.BjsD2Tlob_MPlLv1Zmwva_BSwTQh7eQ440Ua9Dv_rsU',2,'1980-02-01 00:00:00'),(91,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk0NTUyNzAsImV4cCI6MTY5OTQ1ODg3MH0.PoPu72nOknX7Emue0-djXLMDf-Oh978izxCHpSNYiMA',2,'1980-02-01 00:00:00'),(92,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk0NjM1MDgsImV4cCI6MTY5OTQ2NzEwOH0.Q_roWxAwqZwE--fJesiSIPkb-p0sAALjUg6V1QK8s54',2,'1980-02-01 00:00:00'),(93,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk1MDUyMDYsImV4cCI6MTY5OTUwODgwNn0.Xq-hPg3sA3SXqS0PGzn9SiyW027smkjokzAHR2khMLQ',2,'1980-02-01 00:00:00'),(94,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk1MTIyMjQsImV4cCI6MTY5OTUxNTgyNH0.t95rhmfQhaHwaqHZ7kM4z9JECwNFTlSJluvo_5pVBSk',2,'1980-02-01 00:00:00'),(95,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk1MTUzNDcsImV4cCI6MTY5OTUxODk0N30.Mfhwgcj4bF65O-4f4iMbNfB9y5ihnsvOYxOl53hQ5Vo',2,'1980-02-01 00:00:00'),(96,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk1MTkwMTQsImV4cCI6MTY5OTUyMjYxNH0.jUqs4jLfo3v6k6kGqid-i7KFIzxAhDvw1J_V9YhHxBc',2,'1980-02-01 00:00:00'),(97,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk1MzYwODAsImV4cCI6MTY5OTUzOTY4MH0.SVdmn9o4lZIeT4dk7IUldWGHcC52nFrr3ebA_4aURzM',2,'1980-02-01 00:00:00'),(98,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk1NDA1NTYsImV4cCI6MTY5OTU0NDE1Nn0.afLZCGjSL9FDTITrmFDe5pM0IJ-T6vjLH90IPY2NIFQ',2,'1980-02-01 00:00:00'),(99,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk1NDQ0OTYsImV4cCI6MTY5OTU0ODA5Nn0._kg-exmlayz53CTsFGGuSiRGcirSw3Mb5hIbIwrNc_Y',2,'1980-02-01 00:00:00'),(100,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk1NDg1MzUsImV4cCI6MTY5OTU1MjEzNX0.fy6KgO_jbedavG7k2ydS0ZZbGaJpUKP3v7Dw33kgMs0',2,'1980-02-01 00:00:00'),(101,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk1ODM0NzIsImV4cCI6MTY5OTU4NzA3Mn0.rFo3m4MCZZ0cmVUjogMVO_mbZdphdNLYa8-KsjLtES0',2,'1980-02-01 00:00:00'),(102,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk1ODcxMTAsImV4cCI6MTY5OTU5MDcxMH0.Q700A0MtEoWEo_oRXgoUD-iAt0R9H30WwevkzsAbmoc',2,'1980-02-01 00:00:00'),(103,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk1OTI0MjMsImV4cCI6MTY5OTU5NjAyM30.NW0uuDzWFsa2UwLLBq_rC8q7W5pi_indIndsnkHFjbk',2,'1980-02-01 00:00:00'),(104,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk1OTQ2NzAsImV4cCI6MTY5OTU5ODI3MH0.M-j8dQjySgxF3zVEh65DmDL_0bZXQI951i669r1eF00',2,'1980-02-01 00:00:00'),(105,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk1OTgxMDEsImV4cCI6MTY5OTYwMTcwMX0.6X0w3GmaZqwoymMhH8Yv6HAf_VB-_8CWQ0vsA-Y0wDE',2,'1980-02-01 00:00:00'),(106,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk2MjE2MDgsImV4cCI6MTY5OTYyNTIwOH0.0zxF9rKbAzWtqKBEfNss2WL-aMGhjAJ2ROEJT6dQqLs',2,'1980-02-01 00:00:00'),(107,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5NjIyODc4LCJleHAiOjE2OTk2MjY0Nzh9.NJX1w8n1fnGzVOhUUNT0XBA_hAB_tVDJbDdBcs6WUYI',1,'1980-02-01 00:00:00'),(108,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk2MjU1NzQsImV4cCI6MTY5OTYyOTE3NH0.Cu2wrSmXRUs33lwd-sm-aKaxNoUV-m14y0tifIGJuUs',2,'1980-02-01 00:00:00'),(109,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk2MzA3MDgsImV4cCI6MTY5OTYzNDMwOH0.on9hNfB3GcrIGcgF5E_73dQiGT-0Uz5nf6sv10cClzw',2,'1980-02-01 00:00:00'),(110,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk2MzQ0ODAsImV4cCI6MTY5OTYzODA4MH0.IqV5UZvaaNegX5p3nUScYFvVmZLqtnV8sueLQJ498a8',2,'1980-02-01 00:00:00'),(111,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk2Mzk0MDUsImV4cCI6MTY5OTY0MzAwNX0.909WhwaWHIr8-9GwWni5mpt-ZhuLSlS835YUDkbhbhA',2,'1980-02-01 00:00:00'),(112,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk2Mzk5MjUsImV4cCI6MTY5OTY0MzUyNX0.vWPe7ryMPcpQVwYwylcMW5NSlthuJNP7s3GaF9A05Tw',3,'1980-02-01 00:00:00'),(113,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk2NDM4ODMsImV4cCI6MTY5OTY0NzQ4M30.SiCRcZWBhnPQyeXM7mAvOzj7IWFVoynMeNZRjBUhQMc',3,'1980-02-01 00:00:00'),(114,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk2NjQzMzEsImV4cCI6MTY5OTY2NzkzMX0.LoaN4dKSEesrNCY4tRWY5I4JfmyVjbJxZ1beDxZ9trY',2,'1980-02-01 00:00:00'),(115,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk2NjgwNjQsImV4cCI6MTY5OTY3MTY2NH0.BFWKMfJjZ9e5pxc7Tqt0bJLKEsHP6tdXJioGyZ0-EAo',2,'1980-02-01 00:00:00'),(116,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk2Njg2MDQsImV4cCI6MTY5OTY3MjIwNH0.KCIT7mORxJnQsnjhcUwUNPWNUTXas2wTNaTzNUNzoo4',3,'1980-02-01 00:00:00'),(117,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk2Njk0NTQsImV4cCI6MTY5OTY3MzA1NH0.1DZIMSUaUTdWXWOdrdDsLExeSmtQRUkd6SaGxLDz60w',2,'1980-02-01 00:00:00'),(118,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk2NzMyMTEsImV4cCI6MTY5OTY3NjgxMX0.CAlEl3mvYllqnwM5tvbNzpCuY28Pd6-gAingo0c4deI',2,'1980-02-01 00:00:00'),(119,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk2NzgzMDMsImV4cCI6MTY5OTY4MTkwM30.LH8JSBjcM1BNJAPasfyxW_eoFCQxFNTjv2pps2--Evk',2,'1980-02-01 00:00:00'),(120,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk2ODY1NjksImV4cCI6MTY5OTY5MDE2OX0.Gu5E7zXnMpvAVaGymB7f0l2dRQXsvu-4iZ7BY9jdTfc',2,'1980-02-01 00:00:00'),(121,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk2OTA2ODQsImV4cCI6MTY5OTY5NDI4NH0.lHj8Co0woZt49v-hy0OKriAPDNGBIH0Z7qJUzume5yM',2,'1980-02-01 00:00:00'),(122,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5Njk2MzUwLCJleHAiOjE2OTk2OTk5NTB9.XHAyRcjErQqVuhrpL1duzRtH8ugQeqGstwd80XDnjCY',1,'1980-02-01 00:00:00'),(123,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk2OTY2NjMsImV4cCI6MTY5OTcwMDI2M30.k0OzdoPEOyLPiz7WC5_tvr5P_RFSlPKE0F5HBLPn_PM',2,'1980-02-01 00:00:00'),(124,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk2OTcxNDgsImV4cCI6MTY5OTcwMDc0OH0.Gzg4bBqM2s5sZfLQeEvYG3nMah4cfHna97WWff0NueU',2,'1980-02-01 00:00:00'),(125,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5Njk4NTAzLCJleHAiOjE2OTk3MDIxMDN9.vf95BJwbSWyNDD_kOOg-9dVm0UYiXEmkKCbzfJndxUE',1,'1980-02-01 00:00:00'),(126,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk3MDk1MzQsImV4cCI6MTY5OTcxMzEzNH0.nxeaTE3u1isJv74zT5PC0ztQvpIXaULMM6L3AbalP-w',2,'1980-02-01 00:00:00'),(127,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjQsInN1YiI6InN0cmluZyIsImlhdCI6MTY5OTcxMDE5MSwiZXhwIjoxNjk5NzEzNzkxfQ.x8mHHJaieqyF4iFSO3yo47we9R4FEJA3tTxrp1dyPi0',4,'1980-02-01 00:00:00'),(128,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk3NTE5MzgsImV4cCI6MTY5OTc1NTUzOH0.WTdvuiQVRtul-XfxDLsykbyYJSh5RsAXJFSyzb857Yo',2,'1980-02-01 00:00:00'),(129,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk3NTIwMjcsImV4cCI6MTY5OTc1NTYyN30.2HOOE96NLSrCeMFRfiMvqi8FVTWdQX8OrlNspjUv9CM',2,'1980-02-01 00:00:00'),(130,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk3NTIwOTYsImV4cCI6MTY5OTc1NTY5Nn0.Tq5aYRJ3CVK6d8bpBII1JwFOz-lxDc-PjKh__1_-FFs',2,'1980-02-01 00:00:00'),(131,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk3NjEwNzQsImV4cCI6MTY5OTc2NDY3NH0.Wqwju_hLA5NEQNPoBU-SZp4Qzl8fpF9iYUJ8F7rIpRk',2,'1980-02-01 00:00:00'),(132,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5NzY4OTg1LCJleHAiOjE2OTk3NzI1ODV9.w7ki76HjnmZ0Gv4P8kNhlH4_shmSvni3hPvYJdejWqM',1,'1980-02-01 00:00:00'),(133,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk3NzE2NzgsImV4cCI6MTY5OTc3NTI3OH0.fOpJDDhb2NlZXPo6svRNbJAcUr3zblWonoKL-gOH6Xc',2,'1980-02-01 00:00:00'),(134,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk3NzI1NjMsImV4cCI6MTY5OTc3NjE2M30.YKq2b40c9BncJSsTB2p6djW8wt-P61FBFf_iPOWWyas',3,'1980-02-01 00:00:00'),(135,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk3NzQ1MjUsImV4cCI6MTY5OTc3ODEyNX0.0tA96WVtweJYTDe66inESCBY4u10vYfGMrZUNZ_UZ7o',2,'1980-02-01 00:00:00'),(136,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk3NzQ4NTQsImV4cCI6MTY5OTc3ODQ1NH0.6jzP3QHZ7Vzx1oQO3THOjAwjYSTtejENkgWrl9936fY',3,'1980-02-01 00:00:00'),(137,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5Nzc0OTgxLCJleHAiOjE2OTk3Nzg1ODF9.AIbyBee9QslZRMxXvaxqavAZbztG04JclabCEQh69vY',1,'1980-02-01 00:00:00'),(138,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk3ODU1MjMsImV4cCI6MTY5OTc4OTEyM30.p8Q9iQopTl09J6Ii4VhEEQNfSFvvipFHWbMK0demAcE',2,'1980-02-01 00:00:00'),(139,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5Nzg1NjQ3LCJleHAiOjE2OTk3ODkyNDd9.mBFUQRq9LpxX9jR5zZtDI72WUxR654M5oRHidEr9G6M',1,'1980-02-01 00:00:00'),(140,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk3ODU2NjMsImV4cCI6MTY5OTc4OTI2M30.98W8zCd3FihYYbvsytfWenweJZgo2eOR1oxkY14g9GY',2,'1980-02-01 00:00:00'),(141,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5Nzg1ODEyLCJleHAiOjE2OTk3ODk0MTJ9.CSXJQa1lvzKTXXBR7ZPsacmyCQsgaW1LbNhMz1iNxic',1,'1980-02-01 00:00:00'),(142,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjQsInN1YiI6InN0cmluZyIsImlhdCI6MTY5OTc4NTgxMiwiZXhwIjoxNjk5Nzg5NDEyfQ.SE0LfUbiDxwC2h2Doe15zHmASq-qFCS9BKGEU2KMCyc',4,'1980-02-01 00:00:00'),(143,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk4MDAxMTMsImV4cCI6MTY5OTgwMzcxM30.GQVHMW0TYGan_Nrfevv2PuRjyX5gdDgXJFJGT7HJHV0',2,'1980-02-01 00:00:00'),(144,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk4MDkwMTYsImV4cCI6MTY5OTgxMjYxNn0.dzo9fsbBZOIk761QrJ2MUDaWvmVGyMaVvA4VTcmg4bE',2,'1980-02-01 00:00:00'),(145,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk4MDk2MTQsImV4cCI6MTY5OTgxMzIxNH0.ryN1FEDcX2BYwkGLVZD39ES2POdBFXwClGAxwwWhauU',2,'1980-02-01 00:00:00'),(146,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjQsInN1YiI6InN0cmluZyIsImlhdCI6MTY5OTgwOTcwMCwiZXhwIjoxNjk5ODEzMzAwfQ.G4Ii0QCkmiD8PpFmau8M_FwHTz8WY5ACJ95cV4pnoB0',4,'1980-02-01 00:00:00'),(147,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5ODEwMzg2LCJleHAiOjE2OTk4MTM5ODZ9.5ILqpPmcIX-dKo9iYvpqA0ZSHd8KAKDx6MuAY2nnyU8',1,'2023-11-13 00:38:07'),(148,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk4MTE0MzgsImV4cCI6MTY5OTgxNTAzOH0.uq3HNRXhlQa5SHcEN5eqN_t68OZ1CbpakJIiS7pZw4k',2,'1980-02-01 00:00:00'),(149,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk4MTE0MzksImV4cCI6MTY5OTgxNTAzOX0.VtdPNzqAaC1FywYE6iXdyAzBimN2hUKAn334LvKIpI4',2,'1980-02-01 00:00:00'),(150,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk4MTQxOTUsImV4cCI6MTY5OTgxNzc5NX0.DqacrBDgvpafhGDCZJKKYp6QgvD23QrbV9ve4eHjyKM',2,'1980-02-01 00:00:00'),(151,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjQsInN1YiI6InN0cmluZyIsImlhdCI6MTY5OTgxNTExMCwiZXhwIjoxNjk5ODE4NzEwfQ.yJ8DC8PDLF6mJB4PjZijOo_pTbi0Yweg_nr4TfyZRx4',4,'1980-02-01 00:00:00'),(152,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk4MTczMDQsImV4cCI6MTY5OTgyMDkwNH0.pMV9hyOEBmVRVqbFIjzHgbz5fz712u4IldBIJ6_XkD8',3,'1980-02-01 00:00:00'),(153,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk4MTg1ODMsImV4cCI6MTY5OTgyMjE4M30.ownJt_jfTCPLDZybITS3x8rMLoCzZpWSO20Rnu3nfn0',3,'1980-02-01 00:00:00'),(154,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk4NTEyODAsImV4cCI6MTY5OTg1NDg4MH0.1sVl0UXIb5MjEpswbUP0qGyz3FNpXbSgndI_hDdB5hc',2,'1980-02-01 00:00:00'),(155,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk4NTQ5ODIsImV4cCI6MTY5OTg1ODU4Mn0.UI_eF1D4xp-vR3keibBFVkCgPRCuWbNB6Pe-Rlb0fTM',3,'1980-02-01 00:00:00'),(156,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk4NTY5MTMsImV4cCI6MTY5OTg2MDUxM30.PMiEr8u2GWHzDJ15k7uuuNUbSdb37lECg3JG5Q5lcwo',2,'1980-02-01 00:00:00'),(157,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjQsInN1YiI6InN0cmluZyIsImlhdCI6MTY5OTg1ODI1NiwiZXhwIjoxNjk5ODYxODU2fQ.s0fNV9Ug8-Pg7G5ZKRg_bYTxWCuQiYlkOQtQlqu4WtI',4,'1980-02-01 00:00:00'),(158,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk4NjE1MTQsImV4cCI6MTY5OTg2NTExNH0.igoBc15MTwV6bX5Q6H2gX-S-oDqrw2yPckjzVn-WPBs',2,'1980-02-01 00:00:00'),(159,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk4NjUxMjEsImV4cCI6MTY5OTg2ODcyMX0.vKoLjMB9hx8g69W9PzPTq3-TLmaq4MCwwD-gI4VIG_0',2,'1980-02-01 00:00:00'),(160,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk4NjY0MjIsImV4cCI6MTY5OTg3MDAyMn0.KQQpPb3r7H2AdXoeWg5OQnaY3xUq_D7BBrBpJ0JzMjs',2,'1980-02-01 00:00:00'),(161,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjQsInN1YiI6InN0cmluZyIsImlhdCI6MTY5OTg2NzAyNiwiZXhwIjoxNjk5ODcwNjI2fQ.RsOHFaNW-GXUj9y_6x9ClfnKhLy9ejK-D0do70VY9PI',4,'1980-02-01 00:00:00'),(162,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk4NzEwMzEsImV4cCI6MTY5OTg3NDYzMX0.NtijEQY2u2r6apEVDIM6VoOwNeQN_eXfzFuqkwHUWvU',2,'1980-02-01 00:00:00'),(163,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk4NzIzMTMsImV4cCI6MTY5OTg3NTkxM30.ZHT9mIUdt-ZVLLhAC9dABTQ0dYKAntp7rkmnwItk4C4',2,'1980-02-01 00:00:00'),(164,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk4NzYzODAsImV4cCI6MTY5OTg3OTk4MH0.WWGr_bJ2AhmicMnzCcQd3qsiB9lmLL0DrAgQyjHQcug',2,'1980-02-01 00:00:00'),(165,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5ODg3MDY5LCJleHAiOjE2OTk4OTA2Njl9.qkMZ4hzdaMoWIdwjtHTnIqCVo9vMc0Ye9OebXT6Wfzo',6,'1980-02-01 00:00:00'),(166,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk4ODkzODgsImV4cCI6MTY5OTg5Mjk4OH0.ZohDIi7f29XYIrP-g2-ULrq1hWkdhNRqLFzhQGs3sG8',2,'1980-02-01 00:00:00'),(167,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk4OTE5NzksImV4cCI6MTY5OTg5NTU3OX0.KkhctBNCU68QSFGKlm5jBBzQ9wATHZUaU8b9Fth_Y78',2,'1980-02-01 00:00:00'),(168,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk5Mjc0NzksImV4cCI6MTY5OTkzMTA3OX0._jDvAOfuK9JmjVjlsi6hiLo6vayGu-cxfWJJtL4Z37E',2,'1980-02-01 00:00:00'),(169,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk5Mjk1NTMsImV4cCI6MTY5OTkzMzE1M30.5KLAdIGTNbMcO_RQMWOSCwpI6TtKJ3tHRltvbFUBlpw',3,'1980-02-01 00:00:00'),(170,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk5MzEwNzYsImV4cCI6MTY5OTkzNDY3Nn0._9NSqK8Gs0c2VNAdZElKjGKUI9kzA_alzZe0Z-XeHl0',3,'1980-02-01 00:00:00'),(171,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk5MzE1OTUsImV4cCI6MTY5OTkzNTE5NX0.tincL6eAoEFVno3TXf49aNYTrqSCJDBnkNtHFbVXkeg',2,'1980-02-01 00:00:00'),(172,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk5NTE3MjIsImV4cCI6MTY5OTk1NTMyMn0.M2rAalkrQtQ-tDuAsEbpjSIaIK_pWqve7nZtxny5tRk',2,'1980-02-01 00:00:00'),(173,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk5NTE5ODMsImV4cCI6MTY5OTk1NTU4M30._lQxduOEch0loSAkiCEKV6GyYE_4dfWw4sPvoAEzvMU',3,'1980-02-01 00:00:00'),(174,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk5NTU4NjksImV4cCI6MTY5OTk1OTQ2OX0.UG21tXEURTi7G1-3NdrHwVSiab3vWK1ncb_teFb5aTc',2,'1980-02-01 00:00:00'),(175,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk5NjIwNTEsImV4cCI6MTY5OTk2NTY1MX0.NPHSNb4JAOeTN3Vnr06Z7GfaCrTW2RywMnAR1PV7zEA',3,'1980-02-01 00:00:00'),(176,'9f584d17-35ab-4315-92d4-af1a719971a8',3,'1980-02-01 00:00:00'),(177,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk5NjIxNTcsImV4cCI6MTY5OTk2NTc1N30.-bG_LsWVekBMc6u4kCK8SqWnYD2q8IO9LT7P2hKzoB8',3,'1980-02-01 00:00:00'),(178,'343f12d6-5984-4d2d-9459-cc917b772431',3,'1980-02-01 00:00:00'),(179,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk5NjIzMzcsImV4cCI6MTY5OTk2NTkzN30.RNypIAffMkxv-rjL0FL4jUHQjSYR3a1JYZkcpKaxb0Y',3,'1980-02-01 00:00:00'),(180,'a0b49473-6a35-4398-88c1-1def767ea885',3,'1980-02-01 00:00:00'),(181,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk5NzAxMjEsImV4cCI6MTY5OTk3MzcyMX0.HLlR9VbfZmInwkGUTiTY9PMQxJgmFxcfsN3Nl3yg1NY',2,'1980-02-01 00:00:00'),(182,'2675d2b6-4b5d-4257-b643-5f6fdeaa80f4',3,'1980-02-01 00:00:00'),(183,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk5NzAyNzAsImV4cCI6MTY5OTk3Mzg3MH0.E4vj2bbtBGTs20wPblaSqvnDzO_Ilw1DsqgqAzxfULI',3,'1980-02-01 00:00:00'),(184,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk5NzA1NTYsImV4cCI6MTY5OTk3NDE1Nn0.jO0JPW6d4HIcPWPOHRQs63iBFdkspE-q6LyK1yCkcek',2,'1980-02-01 00:00:00'),(185,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk5NzA4MjIsImV4cCI6MTY5OTk3NDQyMn0.CqpABhbcm_QTnbO2DCnc2Yx68ILG6YIBcfhcQdJExHY',2,'1980-02-01 00:00:00'),(186,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk5NzE2MzEsImV4cCI6MTY5OTk3NTIzMX0.6lKtsfljkBd7grFKgW6iqesUvF_AKDT4w8nK0T3qsOk',2,'1980-02-01 00:00:00'),(187,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk5NzE2ODMsImV4cCI6MTY5OTk3NTI4M30.UY-Sc27tITuchPUPNeh197lL_OPY61hqRR_PjOqVPAA',2,'1980-02-01 00:00:00'),(188,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk5NzE3MjUsImV4cCI6MTY5OTk3NTMyNX0.mhhZYAJRhyipu8Jywt3-dxIz69GjbCWWMUZxw7oxfkc',2,'1980-02-01 00:00:00'),(189,'b1cb7ab0-dd42-4f9d-8dee-d1e2e6b4e612',3,'1980-02-01 00:00:00'),(190,'464ad6f4-b105-4420-b2df-96b0d7ef4d87',3,'1980-02-01 00:00:00'),(191,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk5NzM4MzYsImV4cCI6MTY5OTk3NzQzNn0.psJHB-FC1aj6pQiB3teNtADoD6wxKUCBxe1xtPLrPRU',2,'1980-02-01 00:00:00'),(192,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5OTc0MjEwLCJleHAiOjE2OTk5Nzc4MTB9.8y_n0vs1yofldz5QDqeJyscnqBiGWYiTUh2bLkFxDKE',6,'1980-02-01 00:00:00'),(193,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE2OTk5Nzc2ODksImV4cCI6MTY5OTk4MTI4OX0.y4uYWHHPtQn8MEH0D1WzRBnNq5i9mE4k9ti9nDw-n4k',2,'1980-02-01 00:00:00'),(194,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk5Nzg1NTgsImV4cCI6MTY5OTk4MjE1OH0.RUXqMdUAjlsFyTSMqIPhwJSnCWwoRsrcSz8mVQb5AyQ',3,'1980-02-01 00:00:00'),(195,'53db3224-b939-4092-995c-6a4ccb623c10',3,'1980-02-01 00:00:00'),(196,'50e36efc-65d0-4813-9b34-3fa0cb428137',3,'1980-02-01 00:00:00'),(197,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5OTgwMjI5LCJleHAiOjE2OTk5ODM4Mjl9.Ma3AeF1USYrKndCOhjbcInvZJc1_iAvZHW5f03Gd7-k',6,'1980-02-01 00:00:00'),(198,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDAwMTUyNzEsImV4cCI6MTcwMDAxODg3MX0.wiLtfzsvdPjjaRQfGbCzAHr9Y84knL2IpurxFTv5JJM',2,'1980-02-01 00:00:00'),(199,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDAwMTc1MjgsImV4cCI6MTcwMDAyMTEyOH0.hxnvtUrDhPcexIOfDYHx_tagmo4VrhAjlXm9lqtNd1M',2,'1980-02-01 00:00:00'),(200,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDAwMzE0MzEsImV4cCI6MTcwMDAzNTAzMX0.37uh55oQF_eCSSFFKw7_eaXTPuYaDure47dRjlAZZcU',2,'1980-02-01 00:00:00'),(201,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDAwMzUwOTIsImV4cCI6MTcwMDAzODY5Mn0.3RCLykx1iPPB9KzDJHAEb8lWl_sS1dLZmTDDTP9a8l8',2,'1980-02-01 00:00:00'),(202,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAwMzUzMzIsImV4cCI6MTcwMDAzODkzMn0.jfuo1Scn-00Y9tn7JlgVssznMq5vVxoo6qzZppYhxFM',3,'1980-02-01 00:00:00'),(203,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjQsInN1YiI6InN0cmluZyIsImlhdCI6MTcwMDAzNjA5NCwiZXhwIjoxNzAwMDM5Njk0fQ.HhyRDAR9AN8rZFymDWfp5UINB02XsoNkAuEhN4nuatA',4,'1980-02-01 00:00:00'),(204,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDAwMzY4NDgsImV4cCI6MTcwMDA0MDQ0OH0.GJkL20s3Xaqku39XeCyLKTVFPsgq_EqkBauDmjFjSsg',2,'1980-02-01 00:00:00'),(205,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDAwNDE5NjIsImV4cCI6MTcwMDA0NTU2Mn0.8ncIwDYbo3C_34ZLkDPwA_nRSxJQUr5jDS4wfuyK9yk',2,'1980-02-01 00:00:00'),(206,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDAwNDUwMzcsImV4cCI6MTcwMDA0ODYzN30.Wm0lDWiFIMGjCl0XbtJFEhJj_iZvLtOq2JipppurGt8',2,'1980-02-01 00:00:00'),(207,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjQsInN1YiI6InN0cmluZyIsImlhdCI6MTcwMDA0NzA2NCwiZXhwIjoxNzAwMDUwNjY0fQ.hQcRSoU96cIbeoYoDSEIze2WVcng6x8_ym4r6Mk8FnM',4,'1980-02-01 00:00:00'),(208,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDAwNDczODQsImV4cCI6MTcwMDA1MDk4NH0.zZfLa60h8u9Mf5RBNNYGvxyexQNp7Mp14E6AKU__aGQ',2,'1980-02-01 00:00:00'),(209,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwMDU5NjMzLCJleHAiOjE3MDAwNjMyMzN9.LGdx9uG0Zy5GOFUKXAYDUo49fJIRRNZGbExBF5Vp1ZU',6,'1980-02-01 00:00:00'),(210,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwMDYwNTM0LCJleHAiOjE3MDAwNjQxMzR9.lXDb7mhjoX5LWifTXa8eN6-vdV2A9mypagu-6Fh22HM',6,'1980-02-01 00:00:00'),(211,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAwNzU2NDUsImV4cCI6MTcwMDA3OTI0NX0.VGQkIXOmX89tacjh-khtINbhaiaBayYDpm5wgUCLYCM',3,'1980-02-01 00:00:00'),(212,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAwNzU3OTIsImV4cCI6MTcwMDA3OTM5Mn0.sCBQBl-D1EnfLngQEy-l4_fMSTR4WLlc776zh9nzup8',3,'1980-02-01 00:00:00'),(213,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAwNzU4MjYsImV4cCI6MTcwMDA3OTQyNn0.p1riMJV7EBGXpMOEQqdbkKWDD59BPm1Hrn5uJLBlVZg',3,'1980-02-01 00:00:00'),(214,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDAxMDQ5NjUsImV4cCI6MTcwMDEwODU2NX0.F5wvpUsi0fUWfb8Did6r5-q7SXboNR7HL5_maRQnQTQ',2,'1980-02-01 00:00:00'),(215,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDAxMDY0MDYsImV4cCI6MTcwMDExMDAwNn0.VS2ahiVAjdmC6jFeXV3MCTH0EOHuw4KYcmKtTosxx1o',2,'1980-02-01 00:00:00'),(216,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMDkwMTAsImV4cCI6MTcwMDExMjYxMH0.hlgY8Pk-diu9WNmDCrdksMEq-1VimSpnd0q9jcH9B6w',3,'1980-02-01 00:00:00'),(217,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMjY3MTIsImV4cCI6MTcwMDEzMDMxMn0.CdMR_nCAogGGiL-EAtFcgyJLWHwHyPzucdZUYz4k4bg',3,'1980-02-01 00:00:00'),(218,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwMTI4MjEwLCJleHAiOjE3MDAxMzE4MTB9.gKydiYTZeD1mpLqMl3v8OJaB-m2ybOoBniNjhGY8Fcg',6,'1980-02-01 00:00:00'),(219,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMjgyNDksImV4cCI6MTcwMDEzMTg0OX0.Wv9KnHOfEuVyMsxb4E9w1hyrclqUmP8mi3v2Yrg_MAU',3,'1980-02-01 00:00:00'),(220,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMjgyODQsImV4cCI6MTcwMDEzMTg4NH0.WZw5LeEUJywvSHrfgZf0DX1wvIy6BDbWLwBdqG1Kenk',3,'1980-02-01 00:00:00'),(221,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMjgzNDIsImV4cCI6MTcwMDEzMTk0Mn0.Rc0VjemTH0_bSwUvzslVu1AL7OY5N-4Xa-z07Ls65Xs',3,'1980-02-01 00:00:00'),(222,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMjgzODQsImV4cCI6MTcwMDEzMTk4NH0.vFCqHJsDs56GnLt0Q29DyepsBreg3JopKuE_y7rnulQ',3,'1980-02-01 00:00:00'),(223,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMjg0MzAsImV4cCI6MTcwMDEzMjAzMH0.h1hJJi8Y9F7dgd8nZH64B5O05ISjH1Ub6o7ZXp2lnv8',3,'1980-02-01 00:00:00'),(224,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMjg1MDEsImV4cCI6MTcwMDEzMjEwMX0.rn--Z1pUdIXlicwTIj0kvdF2-gBVPJV-IdqJ8uR6P54',3,'1980-02-01 00:00:00'),(225,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDAxMjg2NDMsImV4cCI6MTcwMDEzMjI0M30.B3PbZV9xBfM2VW2Q1pIEplDNjmKbe3iYopOdiw8REk4',2,'1980-02-01 00:00:00'),(226,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMjg3OTEsImV4cCI6MTcwMDEzMjM5MX0.8le_ilk2ssV543bFjH-zLDxGMtWXfWjT7d4SXGgbOBc',3,'1980-02-01 00:00:00'),(227,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMjg4NTYsImV4cCI6MTcwMDEzMjQ1Nn0.FtEHWHABkFnqgmXdkLJoQv3pkRhqbzdsJEuu3a9B5Qc',3,'1980-02-01 00:00:00'),(228,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMjg5MTMsImV4cCI6MTcwMDEzMjUxM30.MdHi3ovSJa1OTg7Ho8IWmnibTNL3y-hwZicI1nzFJvE',3,'1980-02-01 00:00:00'),(229,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMjkyODUsImV4cCI6MTcwMDEzMjg4NX0.JCYGWOut88g6M9tR_nWn8a88INMjwb2xAe7d14LL8zs',3,'1980-02-01 00:00:00'),(230,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMjkzNDQsImV4cCI6MTcwMDEzMjk0NH0.ZEDbxs5ORSq5_LIWme8Y8jQlRAHE2BQLN3HvSK3wDXs',3,'1980-02-01 00:00:00'),(231,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMjkzOTQsImV4cCI6MTcwMDEzMjk5NH0.XvXQS2UIqBwNI97H5QoiOBDxRtr7Y9CeLE-Wm_md6sU',3,'1980-02-01 00:00:00'),(232,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMjk0NTMsImV4cCI6MTcwMDEzMzA1M30.74lvHba5-DFLLd02SpLlX6a11vPXFsR_Y0HRqVIOhRo',3,'1980-02-01 00:00:00'),(233,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDAxMzg0NDMsImV4cCI6MTcwMDE0MjA0M30.RUNel3c3m6Rp2gHkl-qiOP6uZSc0yTEtloWmqsQrlsk',2,'1980-02-01 00:00:00'),(234,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDAxNDM0NjAsImV4cCI6MTcwMDE0NzA2MH0.eXBHzu_4RtiFz-BgWxDqzwP8X-tVFtBLagFWiXGin_k',2,'1980-02-01 00:00:00'),(235,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDAxNDYyNDksImV4cCI6MTcwMDE0OTg0OX0.MWMvsdutfxyNmzVpCA_IrPjpNtgt3FHWn4vbN8Ktp7k',2,'1980-02-01 00:00:00'),(236,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwMTQ3OTM0LCJleHAiOjE3MDAxNTE1MzR9.DmaJb3G1xA0ICcZDxvKaC2DIG4oc3p2HC0Awra5a3Hk',6,'1980-02-01 00:00:00'),(237,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjQsInN1YiI6InN0cmluZyIsImlhdCI6MTcwMDIxMTA3OCwiZXhwIjoxNzAwMjE0Njc4fQ.7PsuT9cSL9YAZjp50MMjT4AqOuL35vUU62_OqgCsNb4',4,'1980-02-01 00:00:00'),(238,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjQsInN1YiI6InN0cmluZyIsImlhdCI6MTcwMDIxMTE3NywiZXhwIjoxNzAwMjE0Nzc3fQ.ucPdM1Yi1G5FC98HhiEOZ75Efah8byEy7tuYCwav_ZM',4,'1980-02-01 00:00:00'),(239,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDAyMTExOTIsImV4cCI6MTcwMDIxNDc5Mn0.24LrlBip08j1GeAy_okpvnpU77wFu9GCTm9d8DT9ci4',2,'1980-02-01 00:00:00'),(240,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDAyMTEyNzQsImV4cCI6MTcwMDIxNDg3NH0.HXwQaZP7v7WqjJ-RWaR8LFcIjSrU-DxkrQD7g77_GKU',2,'1980-02-01 00:00:00'),(241,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDAyMTI3MzYsImV4cCI6MTcwMDIxNjMzNn0.SGb7RMPTdKoCHqp40dvGpE1MU81U-R6XThes1x3mDZU',2,'1980-02-01 00:00:00'),(242,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDAyMjE0NzAsImV4cCI6MTcwMDIyNTA3MH0.hCzV-8OM64j-GiYMW6AuyQllvZnjKcuuQdXTwAyzKGc',2,'1980-02-01 00:00:00'),(243,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDAyNDU0MDUsImV4cCI6MTcwMDI0OTAwNX0.xyA0-9BEGupw6yUjORuYXqR3TtHHtxV4D0qxhFy0EiI',2,'1980-02-01 00:00:00'),(244,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDAyNDkyOTksImV4cCI6MTcwMDI1Mjg5OX0.J5GN-_9Yl272mlhc2yrTjdm1WRbk7bxVwvV7PltkpsE',2,'1980-02-01 00:00:00'),(245,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwMzM2MTQzLCJleHAiOjE3MDAzMzk3NDN9.pyd3YuetxcXW0Tj5is-UOqAfSYEXgIAdrVmhyb2YnLY',6,'1980-02-01 00:00:00'),(246,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwMzQxNDgzLCJleHAiOjE3MDAzNDUwODN9.KVHdyb_tN41Flpc5S0WiF7aQmVP9whJ7G2P2CDcSsac',6,'1980-02-01 00:00:00'),(247,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwMzQyMDU4LCJleHAiOjE3MDAzNDU2NTh9.L2Xm0uQfrmRs2h67wf5ufVCJiBUHORNQngBflXbWBAk',6,'1980-02-01 00:00:00'),(248,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwMzQyMDk1LCJleHAiOjE3MDAzNDU2OTV9.-h6w-aGYstu_IMVWGUleJTzkleGPHiv9j1AMJwW8qFE',6,'1980-02-01 00:00:00'),(249,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwMzg2NzQ0LCJleHAiOjE3MDAzOTAzNDR9.Ao7XzBTNnNP0Ib7JhS2IN4yrP0GfBjqvkTdnujk3KpM',6,'1980-02-01 00:00:00'),(250,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwMzg3ODY5LCJleHAiOjE3MDAzOTE0Njl9.7NhVRp_1L0HVMVJizcR2gPxxobkE5tjCUfnkjzJily0',6,'1980-02-01 00:00:00'),(251,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDAzOTgzODcsImV4cCI6MTcwMDQwMTk4N30.if9axoZKYBReaVUt8cyW-23mD2RMukl0GdWT7xS-AMI',2,'1980-02-01 00:00:00'),(252,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjQsInN1YiI6InN0cmluZyIsImlhdCI6MTcwMDQwMjg3MywiZXhwIjoxNzAwNDA2NDczfQ.yj262hHddCDvWjPORArXqQ2xbXDjzIF2C69S8l3N4_c',4,'1980-02-01 00:00:00'),(253,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA0MDM5ODgsImV4cCI6MTcwMDQwNzU4OH0.Poi_Fom7X4s2GQw06nSECnmgj313Bnng2EytepLJZbc',2,'1980-02-01 00:00:00'),(254,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA0MDQ3MDksImV4cCI6MTcwMDQwODMwOX0.33k7vrUH2PUVA4vKPvikaQUTEo5P9ADVjND-acmeIA8',2,'1980-02-01 00:00:00'),(255,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNDA1MzQwLCJleHAiOjE3MDA0MDg5NDB9.XQzbQxzNGEtoDQHs_Anz979LaHjlMQ6BYSc2C9MEMFA',6,'1980-02-01 00:00:00'),(256,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA0MDU3NjAsImV4cCI6MTcwMDQwOTM2MH0.g05zPn-tB0brWzMWZDsaQ04KA2npeJ_PvHw9S8QM9sU',2,'1980-02-01 00:00:00'),(257,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA0NDg3MDYsImV4cCI6MTcwMDQ1MjMwNn0.wFHOnqZkd2uHpq1QPSPDasraAtrtWDmni03YwBOtp7A',2,'1980-02-01 00:00:00'),(258,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA0NTI1MDUsImV4cCI6MTcwMDQ1NjEwNX0.6uDUsiVFroXn-6A2NZyqqFwhFTOPs0E2vFHPE2H_cS0',2,'1980-02-01 00:00:00'),(259,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA0NTY3MTgsImV4cCI6MTcwMDQ2MDMxOH0.TaCXZCm0p8goeUFnCeOMvWN_dN4lR1i0kbKLo1AF_pY',2,'1980-02-01 00:00:00'),(260,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA0NjA4NzcsImV4cCI6MTcwMDQ2NDQ3N30.n8X8-bE_Ao4Lpw32fpBzUTe_gFSVnQ2aXVkDuaDMgJ4',2,'1980-02-01 00:00:00'),(261,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA0NzAyMzMsImV4cCI6MTcwMDQ3MzgzM30.ljTcr4MSAsW9bmyNB9CH8r3drBm8FepjVpa3-6Z5OkY',2,'1980-02-01 00:00:00'),(262,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjQsInN1YiI6InN0cmluZyIsImlhdCI6MTcwMDQ3MjQ3NywiZXhwIjoxNzAwNDc2MDc3fQ.megGiBkMHQ9R48om7LVIdFtdYyBTyRIPqe0zhDl0XeM',4,'1980-02-01 00:00:00'),(263,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA0NzQyMDgsImV4cCI6MTcwMDQ3NzgwOH0.0d-c8o_ZFUwM4gM456XkUeRudwo0UIMYyUC_IrB86O4',2,'1980-02-01 00:00:00'),(264,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA0Nzg0NTQsImV4cCI6MTcwMDQ4MjA1NH0.4E4CThSO_-n3-g2w09t5HIC7u3yuZBqpoOSYL6GupR4',2,'1980-02-01 00:00:00'),(265,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjQsInN1YiI6InN0cmluZyIsImlhdCI6MTcwMDQ4MzY4MCwiZXhwIjoxNzAwNDg3MjgwfQ.0EE2DAxEuym6zi_sd8c8pyWcIhmc01Sy7TW_TTyQYtM',4,'1980-02-01 00:00:00'),(266,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA0ODkxNzEsImV4cCI6MTcwMDQ5Mjc3MX0.POeuTDSqiY5Sjn4L9DtE1-u0eAh2RE_4aCtNroUBlpE',2,'1980-02-01 00:00:00'),(267,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNDkyMTg2LCJleHAiOjE3MDA0OTU3ODZ9.ueudr2rltdJQhViNFUCI5QdriWd0SS_6PNtYyTiaqKE',6,'1980-02-01 00:00:00'),(268,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA0OTI5NjIsImV4cCI6MTcwMDQ5NjU2Mn0.hRV9fVNF4VsRIa2fE1x8Ro5xT7Y5hw74jqwIFp1SoMU',2,'1980-02-01 00:00:00'),(269,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA1MzMyOTcsImV4cCI6MTcwMDUzNjg5N30.V-HrKRnLUFJ0AkcDPNm6qpKtvWjhisSnOvqslNoliIM',2,'1980-02-01 00:00:00'),(270,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA1MzgzMzMsImV4cCI6MTcwMDU0MTkzM30.I2DI3AmVfGAorMCcw63GvPWCi8axNdYf_3CbMFeqFcI',2,'1980-02-01 00:00:00'),(271,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA1NDc0OTcsImV4cCI6MTcwMDU1MTA5N30.kxqvkB6cXtZiQiA7Hi3JAFo6AixbhPJafO_nIXQ3eAA',3,'1980-02-01 00:00:00'),(272,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA1NDg5NDcsImV4cCI6MTcwMDU1MjU0N30.gbuQFYZRzDXle7hR-lXEtTHyngWQqkeCoQS4CgQXjMg',2,'1980-02-01 00:00:00'),(273,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA1NTMyNjUsImV4cCI6MTcwMDU1Njg2NX0.CM7L6MBhfw5fE2x0zIC9JxEdUMPBVK4AhjXio32Nvn0',3,'1980-02-01 00:00:00'),(274,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNTU3NjI5LCJleHAiOjE3MDA1NjEyMjl9.ZtiP39JF0-BmZ-zI-a_0313WFuE9taJkF-7-R7Bq1sY',6,'1980-02-01 00:00:00'),(275,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA1NTc3NTQsImV4cCI6MTcwMDU2MTM1NH0.ZjWgYI8h-v6y4Z4oYt5sS8axVeuS7qMGwdgtEyjJeic',3,'1980-02-01 00:00:00'),(276,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA1NjA1MDcsImV4cCI6MTcwMDU2NDEwN30.OsqB2yf-oNG8Siuid0K0xaXRbuKuihbsbgM7IiYNGXY',2,'1980-02-01 00:00:00'),(277,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA1NjEyNjgsImV4cCI6MTcwMDU2NDg2OH0.R69e9X02BY5kMBfuhN00drfNlsWlZbdPfcf7cxBjzLg',2,'1980-02-01 00:00:00'),(278,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA1NjUwNTksImV4cCI6MTcwMDU2ODY1OX0.q-PFLByyMZHZrmmMYx6WQXbav4uSwJtINv9bcbgvIsU',2,'1980-02-01 00:00:00'),(279,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA1Njk3MzksImV4cCI6MTcwMDU3MzMzOX0.gTBDBHE-6W8a_yIPz7JV0UwJA7UbFen3EcIsLYVd8bI',2,'1980-02-01 00:00:00'),(280,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNTczMDExLCJleHAiOjE3MDA1NzY2MTF9.tzo5hRFFLjRLzBOdn3g9rYYZaO_6MsgALCndEMDZzMQ',6,'1980-02-01 00:00:00'),(281,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA1NzMzNTUsImV4cCI6MTcwMDU3Njk1NX0.hv_QwuPRsO_CASQoGM2jk-D-9p9fxo9-4v1pBl2NTTk',2,'1980-02-01 00:00:00'),(282,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA1NzUxMTcsImV4cCI6MTcwMDU3ODcxN30.m4jW_Bic6fWlaslAjhN5SLrhH7A9MY7gvAZY9kv-hUM',3,'1980-02-01 00:00:00'),(283,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjQsInN1YiI6InN0cmluZyIsImlhdCI6MTcwMDU3Nzg3NiwiZXhwIjoxNzAwNTgxNDc2fQ.ZVIeFSoy5wt309P1Y-FapLEvYcUHHxhqt-2p8rUe5Vc',4,'1980-02-01 00:00:00'),(284,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjQsInN1YiI6InN0cmluZyIsImlhdCI6MTcwMDU3OTIyMSwiZXhwIjoxNzAwNTgyODIxfQ.D-9ddXTpgNShQmpEuHYc7tUsnT5_HtAS8DYhBAzy6Xo',4,'1980-02-01 00:00:00'),(285,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNTc5OTM4LCJleHAiOjE3MDA1ODM1Mzh9.wazF9GZcxOwBulmgJrk0LrB9xvyJXlEK2JWGVPdFjTE',6,'1980-02-01 00:00:00'),(286,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNTgwMzAwLCJleHAiOjE3MDA1ODM5MDB9.oyPfwMfmE0UgrjFlDsL_WMMBaoPARNnY6ugadSMAdoE',6,'1980-02-01 00:00:00'),(287,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA1ODAzMTIsImV4cCI6MTcwMDU4MzkxMn0.NQR80ND4Nf7dl7du3crchpOvifEt2Dxnhmp46QbNh5o',2,'1980-02-01 00:00:00'),(288,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNTgwNDA3LCJleHAiOjE3MDA1ODQwMDd9.APMif8oIEAaP1-97Css_cx669qmnqqkE7ZDMYk_z_iA',6,'1980-02-01 00:00:00'),(289,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNTgwOTM4LCJleHAiOjE3MDA1ODQ1Mzh9.7UQXYpFRJAnYUGkQ-D1iTHrVUpmpbqqDBfJzMQU_Dl0',6,'1980-02-01 00:00:00'),(290,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjQsInN1YiI6InN0cmluZyIsImlhdCI6MTcwMDU4MTA5NiwiZXhwIjoxNzAwNTg0Njk2fQ.uaZxITL_aYfGCc16C_3nX1I-HEsHbQUXcYeapSbgxxM',4,'1980-02-01 00:00:00'),(291,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNTgxMTQyLCJleHAiOjE3MDA1ODQ3NDJ9.kqXRkZVIYH40puv8RFpr6PWy_aLOKTVY-p65XkU0sZM',6,'1980-02-01 00:00:00'),(292,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjQsInN1YiI6InN0cmluZyIsImlhdCI6MTcwMDU4MzcxMywiZXhwIjoxNzAwNTg3MzEzfQ.stiUmNdHezNO_LRNJonOWn6p76Y-4MHP8NHHsHE5Thg',4,'1980-02-01 00:00:00'),(293,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA1ODQxNTIsImV4cCI6MTcwMDU4Nzc1Mn0.CJcr1vIXk64Spd_TTMbsXITLOXs5Z1P8VvPMRm1F_vQ',2,'1980-02-01 00:00:00'),(294,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNTg1MDk0LCJleHAiOjE3MDA1ODg2OTR9.KIlfDQWorgW1fwqwrU1HdU9IjDY_VG_E7BwuhrD4JXE',6,'1980-02-01 00:00:00'),(295,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNTg2MzE1LCJleHAiOjE3MDA1ODk5MTV9.YHOAN6HUBHJHD-SZHsd5MWeHd-VCo2s8JKxpAG36Rrs',6,'1980-02-01 00:00:00'),(296,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA1ODg0MzgsImV4cCI6MTcwMDU5MjAzOH0.eVLCIRXESy0Scr-p85WdVy81zrES-4hgDwEyJEleK4w',3,'1980-02-01 00:00:00'),(297,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA1ODkyNzksImV4cCI6MTcwMDU5Mjg3OX0.P_lPVpWzTZMn1LXyzayQdI7B1-_SrDUfLLyahw_JTn4',3,'1980-02-01 00:00:00'),(298,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA1OTAxMjIsImV4cCI6MTcwMDU5MzcyMn0.1EPafw2U8jdEO7HNYt7GySfhRwkKNDjQV7p4i6ICXI8',3,'1980-02-01 00:00:00'),(299,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA1OTMwMjIsImV4cCI6MTcwMDU5NjYyMn0.l2bYpAajtYR9vUNcLrbexzIvAM3c1psabh11PB7yxJU',3,'1980-02-01 00:00:00'),(300,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA1OTQxMjksImV4cCI6MTcwMDU5NzcyOX0.bSlARSS7e0utg5ZLPho8mHTA0tlhvjAYXGjoI6_TMp8',3,'1980-02-01 00:00:00'),(301,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA1OTgwMjYsImV4cCI6MTcwMDYwMTYyNn0.w7meAV0dyiceDSNAXWXameFpxD9t5twjtGaPz-u-ppo',3,'1980-02-01 00:00:00'),(302,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA2MjI5ODksImV4cCI6MTcwMDYyNjU4OX0.5olWnzFuwxhyk6NW8iTsWUfRV4tDsNkEF2owAry_wh0',2,'1980-02-01 00:00:00'),(303,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA2MzQzNzcsImV4cCI6MTcwMDYzNzk3N30.rMqD0GnEKTLH_fw7v6TilUR1eTvYMDBirBiCcxed_Tw',2,'1980-02-01 00:00:00'),(304,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA2MzQzODgsImV4cCI6MTcwMDYzNzk4OH0.HuNaHVxs8hnUDj7bp_m31luJkzBwbN1dwbdBkof-xUQ',3,'1980-02-01 00:00:00'),(305,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA2MzgzMzksImV4cCI6MTcwMDY0MTkzOX0.KAlOFlvE0TyB7T3fQx8DvCuNSzvDxz2p1ZHxePaC_dc',3,'1980-02-01 00:00:00'),(306,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA2MzkyODIsImV4cCI6MTcwMDY0Mjg4Mn0.VjgAO1anV08Xvpy9Vtywjjhq1vp_5eNuck8lrR-0CwA',3,'1980-02-01 00:00:00'),(307,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNjQxOTU3LCJleHAiOjE3MDA2NDU1NTd9.0VWx2j0Gg4BldyBLE9MuEkH5HK0X1bA76IyjeIkHnRA',6,'1980-02-01 00:00:00'),(308,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA2NDM5NTIsImV4cCI6MTcwMDY0NzU1Mn0.2olx8KS-vBP6nTqnKrAkVvnQxUenfe03-UDIcQsUtGs',3,'1980-02-01 00:00:00'),(309,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA2NDcwMzQsImV4cCI6MTcwMDY1MDYzNH0.k6u9UfEyrLL66w1lNETN7AI98wGSyadNSngzsIF8D5c',2,'1980-02-01 00:00:00'),(310,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA2NDc2ODQsImV4cCI6MTcwMDY1MTI4NH0.MLT8mRG2LrbhthLqE-_820lGcm8jnxahduLZzYaGlYU',3,'1980-02-01 00:00:00'),(311,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjQsInN1YiI6InN0cmluZyIsImlhdCI6MTcwMDY0OTg2MywiZXhwIjoxNzAwNjUzNDYzfQ.p9o6WuBo_wO7_Q7WKhFtk0Twp9Okc4nRoz3cpHXaK6s',4,'1980-02-01 00:00:00'),(312,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA2NTAyMTMsImV4cCI6MTcwMDY1MzgxM30.mqm9NCjFnb7WZtzd6BgJAPIuHJDFmkdQRF9U-ZP_uz0',2,'1980-02-01 00:00:00'),(313,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjQsInN1YiI6InN0cmluZyIsImlhdCI6MTcwMDY1MDUyMCwiZXhwIjoxNzAwNjU0MTIwfQ.7KJY57GouQaXghYnSqQns62WHYLLV9B6ixwQYUL46kM',4,'1980-02-01 00:00:00'),(314,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjQsInN1YiI6InN0cmluZyIsImlhdCI6MTcwMDY1Mzc4OSwiZXhwIjoxNzAwNjU3Mzg5fQ.CbKixZzvFUszDkDMGo1XQzoDQ36hQRI1EnYRlycp_J0',4,'1980-02-01 00:00:00'),(315,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjQsInN1YiI6InN0cmluZyIsImlhdCI6MTcwMDY1NDU1MSwiZXhwIjoxNzAwNjU4MTUxfQ.8f8A6MAQjkX5g8diJYQP5DzdCRs6OZl3fbNDKJMO9_o',4,'1980-02-01 00:00:00'),(316,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA2NTc5NDUsImV4cCI6MTcwMDY2MTU0NX0.ZG_bcCom7ykB7U-TigaZcD0SjM5DYOeZDGM4N1wgoGs',2,'1980-02-01 00:00:00'),(317,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA2NjA5NDMsImV4cCI6MTcwMDY2NDU0M30.vMhcf6vuzjctv3ZE-fI_nXdr7fVj2wLvObohM_6Q-lw',3,'1980-02-01 00:00:00'),(318,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA2NjQ2MTEsImV4cCI6MTcwMDY2ODIxMX0.ekAPh5FYaGF22vESgjg1SuLafN1z-I3R70pAeObe1jQ',2,'1980-02-01 00:00:00'),(319,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA2NjQ3MjAsImV4cCI6MTcwMDY2ODMyMH0.0B3pCck2OobCw_gGw_w5mTptSCodYb9ffFasAvfU1B4',3,'1980-02-01 00:00:00'),(320,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNjY1NDY0LCJleHAiOjE3MDA2NjkwNjR9.JvvEU7q1HFYDUCkQgZVYt2AKu9j3qeuerDqVRemELYE',6,'2023-11-22 22:09:25'),(321,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjQsInN1YiI6InN0cmluZyIsImlhdCI6MTcwMDY2Nzc1NSwiZXhwIjoxNzAwNjcxMzU1fQ.Tev9fLq50--jIfIKs4g3DXv6d2xkwOcodv2v-V4xWjY',4,'2023-11-22 22:47:36'),(322,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA2NzAwNTEsImV4cCI6MTcwMDY3MzY1MX0.ZMX97Et-YIJu2WskNAhLJtPHOBNbbCUlfMeDx6MlRq8',3,'1980-02-01 00:00:00'),(323,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA2NzA3NDksImV4cCI6MTcwMDY3NDM0OX0.gDJgr3q1nqHsj9cbraHdaDixB0jYcV5bkOcw0M5cchY',2,'1980-02-01 00:00:00'),(324,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA2NzM3MDUsImV4cCI6MTcwMDY3NzMwNX0.GCgGaoMuaZ6CaaYRslngBJqMOp0wMw87mBBifS1LbEE',3,'2023-11-23 00:26:45'),(325,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDA2NzM4MjYsImV4cCI6MTcwMDY3NzQyNn0.sSSRVuFb4j00Tx7BpGH_pKaGw9RjmiWmANhpQF1B1Wo',2,'2023-11-23 00:28:47');
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-23  0:34:44
