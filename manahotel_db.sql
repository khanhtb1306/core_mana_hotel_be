-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: manahotel_db
-- ------------------------------------------------------
-- Server version	8.1.0

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
-- Table structure for table `bank_account`
--

DROP TABLE IF EXISTS `bank_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bank_account` (
  `bank_account_id` bigint NOT NULL AUTO_INCREMENT,
  `bank_account_number` varchar(50) DEFAULT NULL,
  `bank_account_name` varchar(50) DEFAULT NULL,
  `bank_id` int DEFAULT NULL,
  PRIMARY KEY (`bank_account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_account`
--

LOCK TABLES `bank_account` WRITE;
/*!40000 ALTER TABLE `bank_account` DISABLE KEYS */;
INSERT INTO `bank_account` VALUES (3,'9386989386','TRINH BAO KHANH',970422);
/*!40000 ALTER TABLE `bank_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `control_policy`
--

DROP TABLE IF EXISTS `control_policy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `control_policy` (
  `control_policy_id` bigint NOT NULL AUTO_INCREMENT,
  `reservation_detail_id` bigint NOT NULL,
  `policy_id` varchar(50) NOT NULL,
  `type_value` varchar(250) DEFAULT NULL,
  `value` float DEFAULT NULL,
  `discrepancy` varchar(250) DEFAULT NULL,
  `note` varchar(350) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`control_policy_id`),
  KEY `pk_cp_rd_idx` (`reservation_detail_id`),
  KEY `pk_cp_pd_idx` (`policy_id`),
  CONSTRAINT `pk_cp_p` FOREIGN KEY (`policy_id`) REFERENCES `policy` (`policy_id`),
  CONSTRAINT `pk_cp_rd` FOREIGN KEY (`reservation_detail_id`) REFERENCES `reservation_detail` (`reservation_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `control_policy`
--

LOCK TABLES `control_policy` WRITE;
/*!40000 ALTER TABLE `control_policy` DISABLE KEYS */;
/*!40000 ALTER TABLE `control_policy` ENABLE KEYS */;
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
  `is_customer` bit(1) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
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
INSERT INTO `customer` VALUES ('C000000','Bán lẻ','NK000000','0812569567','2023-11-17 00:00:00','tapdoanhhl@gmail.com','thạch hòa thạch thất hà nội','132u738921','vn','123',_binary '\0',NULL,NULL,NULL,NULL),('C000001','Trinh Bao Khanh','NK000001','0898637030','2000-12-06 17:00:00','trinhbaokhanh1aa306@gmail.com','Hoa Lac','0992828828222222','Việt Nam','100000',_binary '','',NULL,NULL,NULL),('C000002','Nguyễn Văn A','NK000001','0898637030','2000-12-12 17:00:00','manahotelsystem@gmail.com','Hoa Lac','0992828828222222','Việt Nam','100000',_binary '','',NULL,_binary '\0','ACTIVE'),('C000003','Trinh Bao Khanh','NK000000','0898637030','2023-12-06 17:00:00','','Hoa Lac','','Việt Nam','',_binary '\0','',NULL,_binary '\0','ACTIVE'),('C000004','Trinh Bao Khanh','NK000000','0898637030','2000-12-12 17:00:00','','Hoa Lac','','Việt Nam','',_binary '\0','',NULL,_binary '\0','ACTIVE'),('C000005','Trinh Bao Khanh','NK000000','0898637030','2000-08-18 17:00:00','','Hoa Lac','','Việt Nam','',_binary '\0','',NULL,_binary '\0','ACTIVE'),('C000006','Trinh Bao Khanh','NK000000','0898637030','2000-12-13 17:00:00','','Hoa Lac','','Việt Nam','',_binary '\0','',NULL,_binary '\0','ACTIVE'),('C000007','Trinh Bao Khanh','NK000000','0898637030','2000-12-13 17:00:00','','Hoa Lac','','Việt Nam','',_binary '\0','',NULL,_binary '\0','ACTIVE');
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
  `discount` float DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`customer_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_group`
--

LOCK TABLES `customer_group` WRITE;
/*!40000 ALTER TABLE `customer_group` DISABLE KEYS */;
INSERT INTO `customer_group` VALUES ('NK000000','Bán lẻ',NULL,NULL),('NK000001','Khách Lẻ',NULL,'ACTIVE'),('NK000002','Đối tác',NULL,'ACTIVE');
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
INSERT INTO `department` VALUES ('PB000001','Nhân Viên',''),('PB000002','Lễ Tân','ACTIVE');
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
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `floor`
--

LOCK TABLES `floor` WRITE;
/*!40000 ALTER TABLE `floor` DISABLE KEYS */;
INSERT INTO `floor` VALUES (0,'Sảnh',NULL,NULL,NULL,NULL,NULL),(1,'Tầng 1',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(2,'Tầng 2',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(3,'Tầng 3',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(4,'Tầng 4',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(5,'Tầng 5',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(6,'Tầng 6',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(7,'Tầng 7',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(8,'Tầng 8',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(9,'Tầng 9',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(10,'Tầng 10',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(28,'c',6,NULL,NULL,'2023-12-07 09:10:03',NULL),(29,'1111',6,NULL,NULL,'2023-12-07 09:15:46',NULL),(30,'1',6,NULL,NULL,'2023-12-07 09:25:44',NULL),(31,'2',6,NULL,NULL,'2023-12-07 09:26:50',NULL),(32,'2',6,NULL,NULL,'2023-12-07 09:34:15',NULL),(33,'111',6,NULL,NULL,'2023-12-07 09:35:20',NULL),(34,'11112',6,NULL,NULL,'2023-12-07 09:35:32','2023-12-07 09:35:39'),(35,'1111111',6,NULL,NULL,'2023-12-07 09:35:48',NULL),(36,'1',6,NULL,NULL,'2023-12-07 09:52:14',NULL),(37,'1',6,NULL,NULL,'2023-12-07 12:34:35',NULL),(38,'1',6,NULL,NULL,'2023-12-07 12:34:51',NULL),(39,'1',6,NULL,NULL,'2023-12-07 13:56:45',NULL),(40,'1',6,NULL,NULL,'2023-12-07 14:00:53',NULL),(41,'1',6,NULL,NULL,'2023-12-07 14:12:31',NULL);
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
  `time` timestamp NULL DEFAULT NULL,
  `type` varchar(250) DEFAULT NULL,
  `paid_method` varchar(50) DEFAULT NULL,
  `value` float DEFAULT NULL,
  `payer_receiver` varchar(250) DEFAULT NULL,
  `staff` varchar(50) DEFAULT NULL,
  `note` varchar(350) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `transaction_code` varchar(50) DEFAULT NULL,
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
INSERT INTO `goods` VALUES ('SP000001','Sprite',_binary '',1,99976,100,100000,'Non nước ngọt Sprite','Non Sprite thể tích 150ml',NULL,NULL,NULL,'2023-10-17 05:00:00','2023-10-17 05:00:00'),('SP000002','Coca Cola',_binary '',1,99944,100,100000,'Non nước ngọt Coca Cola','Non Coca Cola thể tích 150ml',NULL,NULL,NULL,'2023-10-17 05:00:00','2023-10-17 05:00:00'),('SP000003','Thịt bò khô',_binary '',1,4048,100,100000,'Thịt bò khô','Thịt bò khô 10 miếng',NULL,NULL,2,'2023-10-17 05:00:00','2023-11-04 15:39:49'),('SP000004','Thuê xe máy',_binary '\0',1,NULL,NULL,NULL,'Xe máy','Xe máy đầy xăng',NULL,NULL,NULL,'2023-10-17 05:00:00','2023-10-17 05:00:00'),('SP000005','Thuê ô tô',_binary '\0',1,NULL,NULL,NULL,'Ô tô','Ô tô điện',NULL,NULL,NULL,'2023-10-17 05:00:00','2023-10-17 05:00:00'),('SP000006','Massage',_binary '\0',1,NULL,NULL,NULL,'Mát xa','Mát xa cực phê',NULL,NULL,NULL,'2023-10-17 05:00:00','2023-10-17 05:00:00'),('SP000007','Mỳ tôm',_binary '',1,99943,100,100000,'Mỳ tôm','Mỳ hảo hán',NULL,NULL,NULL,'2023-10-17 05:00:00','2023-10-17 05:00:00'),('SP000008','Bia Huda',_binary '',1,99999,100,100000,'Bia','Đậm tình miền Trung',NULL,NULL,NULL,'2023-10-17 05:00:00','2023-10-17 05:00:00'),('SP000009','Bim bim Lays',_binary '',1,10,100,100000,'Bim bim','Anh muốn Lays em',NULL,NULL,NULL,'2023-10-17 05:00:00','2023-10-17 05:00:00'),('SP000010','Cắt tóc',_binary '\0',1,NULL,NULL,NULL,'Cắt tóc','Tommy Xiaomi',NULL,NULL,NULL,'2023-10-17 05:00:00','2023-10-17 05:00:00');
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
-- Table structure for table `import_goods`
--

DROP TABLE IF EXISTS `import_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `import_goods` (
  `import_goods_id` varchar(50) NOT NULL,
  `time_import` timestamp NULL DEFAULT NULL,
  `supplier` varchar(250) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `paid` float DEFAULT NULL,
  `status` bigint DEFAULT NULL,
  `created_by_id` bigint DEFAULT NULL,
  PRIMARY KEY (`import_goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `import_goods`
--

LOCK TABLES `import_goods` WRITE;
/*!40000 ALTER TABLE `import_goods` DISABLE KEYS */;
/*!40000 ALTER TABLE `import_goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `import_goods_detail`
--

DROP TABLE IF EXISTS `import_goods_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `import_goods_detail` (
  `import_goods_detail_id` bigint NOT NULL AUTO_INCREMENT,
  `import_goods_id` varchar(50) NOT NULL,
  `goods_id` varchar(50) NOT NULL,
  `amount` bigint DEFAULT NULL,
  `cost` float DEFAULT NULL,
  `total` float DEFAULT NULL,
  PRIMARY KEY (`import_goods_detail_id`),
  KEY `pk_igd_g_idx` (`goods_id`),
  KEY `pk_igd_ig_idx` (`import_goods_id`),
  CONSTRAINT `pk_igd_g` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`),
  CONSTRAINT `pk_igd_ig` FOREIGN KEY (`import_goods_id`) REFERENCES `import_goods` (`import_goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `import_goods_detail`
--

LOCK TABLES `import_goods_detail` WRITE;
/*!40000 ALTER TABLE `import_goods_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `import_goods_detail` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory_check_detail`
--

LOCK TABLES `inventory_check_detail` WRITE;
/*!40000 ALTER TABLE `inventory_check_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventory_check_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `invoice_id` varchar(50) NOT NULL,
  `customer_id` varchar(50) NOT NULL,
  `staff_id` bigint NOT NULL,
  `total` float DEFAULT NULL,
  `discount` float DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `note` varchar(250) DEFAULT NULL,
  `price_other` float DEFAULT NULL,
  PRIMARY KEY (`invoice_id`),
  KEY `pk_i_c_idx` (`customer_id`),
  KEY `ok_i_stf_idx` (`staff_id`),
  CONSTRAINT `pk_i_c` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `pk_i_stf` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`staff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES ('HD000000','C000000',6,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice_reservation_detail`
--

DROP TABLE IF EXISTS `invoice_reservation_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice_reservation_detail` (
  `invoice_reservation_detail_id` bigint NOT NULL AUTO_INCREMENT,
  `invoice_id` varchar(50) NOT NULL,
  `reservation_detail_id` bigint NOT NULL,
  PRIMARY KEY (`invoice_reservation_detail_id`),
  KEY `pk_ird_i_idx` (`invoice_id`),
  KEY `pk_ird_rd_idx` (`reservation_detail_id`),
  CONSTRAINT `pk_ird_i` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`invoice_id`),
  CONSTRAINT `pk_ird_rd` FOREIGN KEY (`reservation_detail_id`) REFERENCES `reservation_detail` (`reservation_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice_reservation_detail`
--

LOCK TABLES `invoice_reservation_detail` WRITE;
/*!40000 ALTER TABLE `invoice_reservation_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice_reservation_detail` ENABLE KEYS */;
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
INSERT INTO `order` VALUES ('DH000000',0,NULL,NULL,NULL,NULL);
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
  `invoice_id` varchar(50) NOT NULL,
  `quantity` bigint DEFAULT NULL,
  `price` float DEFAULT NULL,
  PRIMARY KEY (`order_detail_id`),
  KEY `pk_od_o_idx` (`order_id`) /*!80000 INVISIBLE */,
  KEY `pk_od_g_idx` (`goods_id`) /*!80000 INVISIBLE */,
  KEY `pk_od_gu_idx` (`goods_unit_id`),
  KEY `pk_od_i_idx` (`invoice_id`),
  CONSTRAINT `pk_od_g` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`),
  CONSTRAINT `pk_od_gu` FOREIGN KEY (`goods_unit_id`) REFERENCES `goods_unit` (`goods_unit_id`),
  CONSTRAINT `pk_od_i` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`invoice_id`),
  CONSTRAINT `pk_od_o` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=194 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policy_detail`
--

LOCK TABLES `policy_detail` WRITE;
/*!40000 ALTER TABLE `policy_detail` DISABLE KEYS */;
INSERT INTO `policy_detail` VALUES (191,'CS000003','HP000000','NK000000','VAT',NULL,NULL,'VND',NULL,NULL,1,NULL,_binary '\0',1),(192,'CS000007','HP000000','NK000000',NULL,NULL,1000000,NULL,NULL,NULL,1,NULL,NULL,1),(193,'CS000006','HP000000','NK000000',NULL,NULL,1,NULL,NULL,NULL,50000,NULL,NULL,1);
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
INSERT INTO `price_list` VALUES ('BG000000','Default',NULL,NULL,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `price_list_detail`
--

LOCK TABLES `price_list_detail` WRITE;
/*!40000 ALTER TABLE `price_list_detail` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recent_activity`
--

LOCK TABLES `recent_activity` WRITE;
/*!40000 ALTER TABLE `recent_activity` DISABLE KEYS */;
INSERT INTO `recent_activity` VALUES (1,'Khánh','tạo hóa đơn',11999000,'2023-11-21 20:30:00'),(2,'Khánh','tạo hóa đơn',14375000,'2023-11-21 21:31:00'),(3,'admin','thực hiện kiểm kho',0,'2023-11-24 16:45:51'),(4,'Tien','tạo hóa đơn',10000000,'2023-11-24 20:00:00'),(5,'admin','thực hiện kiểm kho',0,'2023-11-27 14:09:02'),(6,'admin','thực hiện kiểm kho',0,'2023-11-27 14:31:50'),(21,'Trịnh Bảo Khánh','tạo hóa đơn',700,'2023-12-12 07:42:23'),(22,'Trịnh Bảo Khánh','tạo phiếu thu',111,'2023-12-12 16:31:06'),(23,'Trịnh Bảo Khánh','tạo hóa đơn',800000,'2023-12-13 15:48:47'),(24,'Trịnh Bảo Khánh','tạo hóa đơn',1200000,'2023-12-13 15:53:09');
/*!40000 ALTER TABLE `recent_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_revenue`
--

DROP TABLE IF EXISTS `report_revenue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report_revenue` (
  `report_revenue_id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` timestamp NULL DEFAULT NULL,
  `revenue_value` float DEFAULT NULL,
  PRIMARY KEY (`report_revenue_id`)
) ENGINE=InnoDB AUTO_INCREMENT=215 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_revenue`
--

LOCK TABLES `report_revenue` WRITE;
/*!40000 ALTER TABLE `report_revenue` DISABLE KEYS */;
INSERT INTO `report_revenue` VALUES (2,'2023-11-26 14:30:00',10000000),(3,'2023-11-19 14:30:00',5000000),(4,'2023-11-25 14:30:00',2500000),(5,'2023-11-18 14:30:00',1000000),(12,'2023-12-05 15:37:23',0),(13,'2023-12-05 15:37:24',0),(14,'2023-12-05 15:40:50',0),(15,'2023-12-05 15:40:50',0),(16,'2023-12-05 15:41:38',0),(17,'2023-12-05 15:41:38',0),(18,'2023-12-05 15:48:17',0),(19,'2023-12-05 15:48:17',0),(20,'2023-12-06 18:00:58',0),(21,'2023-12-06 18:00:58',0),(22,'2023-12-07 06:44:21',0),(23,'2023-12-07 06:44:21',0),(24,'2023-12-07 08:42:47',0),(25,'2023-12-07 08:42:47',0),(26,'2023-12-07 08:45:54',0),(27,'2023-12-07 08:45:54',0),(28,'2023-12-07 13:11:27',0),(29,'2023-12-07 13:11:27',0),(30,'2023-12-07 13:15:12',0),(31,'2023-12-07 13:15:12',0),(32,'2023-12-07 13:47:30',0),(33,'2023-12-07 13:47:30',0),(34,'2023-12-07 14:13:17',0),(35,'2023-12-07 14:13:17',0),(36,'2023-12-07 15:32:29',0),(37,'2023-12-07 15:32:29',0),(38,'2023-12-07 15:32:36',0),(39,'2023-12-07 15:32:36',0),(40,'2023-12-07 16:10:00',0),(41,'2023-12-07 16:10:00',0),(42,'2023-12-07 16:59:59',0),(43,'2023-12-07 19:12:57',0),(44,'2023-12-07 19:12:57',0),(45,'2023-12-07 19:13:12',0),(46,'2023-12-07 19:36:36',0),(47,'2023-12-07 19:36:36',0),(48,'2023-12-08 09:54:06',0),(49,'2023-12-08 09:54:06',0),(50,'2023-12-08 09:55:23',0),(51,'2023-12-08 09:55:23',0),(52,'2023-12-08 10:12:44',0),(53,'2023-12-08 10:12:44',0),(54,'2023-12-08 10:18:29',0),(55,'2023-12-08 10:18:29',0),(56,'2023-12-08 10:18:34',0),(57,'2023-12-08 10:18:52',0),(58,'2023-12-08 10:39:38',0),(59,'2023-12-08 10:39:38',0),(60,'2023-12-08 10:39:51',0),(61,'2023-12-08 10:52:03',0),(62,'2023-12-08 10:52:10',0),(63,'2023-12-08 10:54:39',0),(64,'2023-12-08 10:54:39',0),(65,'2023-12-08 11:08:01',0),(66,'2023-12-08 11:08:01',0),(67,'2023-12-08 11:08:01',0),(68,'2023-12-08 11:08:01',0),(69,'2023-12-08 11:08:01',0),(70,'2023-12-08 11:08:01',0),(71,'2023-12-09 08:50:08',0),(72,'2023-12-09 08:50:08',0),(73,'2023-12-09 08:55:13',0),(74,'2023-12-09 08:55:13',0),(75,'2023-12-09 08:55:26',0),(76,'2023-12-09 08:56:44',0),(77,'2023-12-09 08:56:44',0),(78,'2023-12-09 08:57:04',0),(79,'2023-12-09 09:11:01',0),(80,'2023-12-09 09:11:01',0),(81,'2023-12-09 09:12:28',0),(82,'2023-12-09 09:47:32',0),(83,'2023-12-09 09:47:32',0),(84,'2023-12-09 09:48:00',0),(85,'2023-12-09 09:49:20',0),(86,'2023-12-09 09:54:23',0),(87,'2023-12-09 09:55:41',0),(88,'2023-12-09 09:56:06',0),(89,'2023-12-09 09:56:18',0),(90,'2023-12-09 09:56:44',0),(91,'2023-12-09 09:57:01',0),(92,'2023-12-09 09:59:07',0),(93,'2023-12-09 09:59:07',0),(94,'2023-12-09 10:00:21',0),(95,'2023-12-09 10:03:33',0),(96,'2023-12-09 10:03:46',0),(97,'2023-12-09 10:03:46',0),(98,'2023-12-09 10:11:44',0),(99,'2023-12-09 10:11:44',0),(100,'2023-12-09 10:11:59',0),(101,'2023-12-09 10:12:40',0),(102,'2023-12-09 10:12:41',0),(103,'2023-12-09 10:13:40',0),(104,'2023-12-09 10:13:44',0),(105,'2023-12-09 10:13:56',0),(106,'2023-12-09 10:13:56',0),(107,'2023-12-09 10:15:08',0),(108,'2023-12-09 10:32:32',0),(109,'2023-12-09 10:32:32',0),(110,'2023-12-09 10:32:56',0),(111,'2023-12-09 10:33:10',0),(112,'2023-12-09 10:33:19',0),(113,'2023-12-09 10:34:07',0),(114,'2023-12-09 10:34:12',0),(115,'2023-12-09 10:34:12',0),(116,'2023-12-09 10:34:48',0),(117,'2023-12-09 10:34:59',0),(118,'2023-12-09 10:35:10',0),(119,'2023-12-09 10:35:21',0),(120,'2023-12-09 10:36:38',0),(121,'2023-12-09 10:36:40',0),(122,'2023-12-09 10:36:40',0),(123,'2023-12-09 10:37:36',0),(124,'2023-12-09 10:37:40',0),(125,'2023-12-09 10:37:40',0),(126,'2023-12-09 10:40:03',0),(127,'2023-12-09 10:40:03',0),(128,'2023-12-09 10:41:35',0),(129,'2023-12-09 10:41:43',0),(130,'2023-12-09 10:41:43',0),(131,'2023-12-09 10:42:50',0),(132,'2023-12-09 10:43:05',0),(133,'2023-12-09 10:43:05',0),(134,'2023-12-09 10:44:10',0),(135,'2023-12-09 10:44:10',0),(136,'2023-12-09 10:46:35',0),(137,'2023-12-09 10:46:35',0),(138,'2023-12-09 10:47:29',0),(139,'2023-12-09 10:51:53',0),(140,'2023-12-09 10:51:53',0),(141,'2023-12-09 10:53:04',0),(142,'2023-12-09 10:53:11',0),(143,'2023-12-09 10:53:11',0),(144,'2023-12-09 10:53:36',0),(145,'2023-12-09 10:53:36',0),(146,'2023-12-09 10:55:34',0),(147,'2023-12-09 10:55:48',0),(148,'2023-12-09 10:55:49',0),(149,'2023-12-09 10:57:23',0),(150,'2023-12-09 10:57:29',0),(151,'2023-12-09 10:57:29',0),(152,'2023-12-09 10:59:54',0),(153,'2023-12-09 10:59:57',0),(154,'2023-12-09 10:59:57',0),(155,'2023-12-09 11:00:27',0),(156,'2023-12-09 11:01:05',0),(157,'2023-12-09 11:02:05',0),(158,'2023-12-09 11:04:14',0),(159,'2023-12-09 11:06:44',0),(160,'2023-12-09 11:08:35',0),(161,'2023-12-09 11:08:36',0),(162,'2023-12-09 11:09:51',0),(163,'2023-12-09 11:09:51',0),(164,'2023-12-09 11:10:21',0),(165,'2023-12-09 11:11:12',0),(166,'2023-12-09 11:11:15',0),(167,'2023-12-09 11:11:15',0),(168,'2023-12-10 06:37:49',0),(169,'2023-12-10 06:37:49',0),(170,'2023-12-10 07:36:18',0),(171,'2023-12-10 07:36:18',0),(172,'2023-12-10 07:36:25',0),(173,'2023-12-10 07:36:25',0),(174,'2023-12-10 09:14:45',0),(175,'2023-12-10 09:14:45',0),(176,'2023-12-10 16:35:35',0),(177,'2023-12-10 16:35:35',0),(178,'2023-12-10 16:59:59',0),(179,'2023-12-11 15:11:44',0),(180,'2023-12-11 15:11:44',0),(181,'2023-12-11 15:33:07',0),(182,'2023-12-11 15:33:07',0),(183,'2023-12-11 16:59:59',0),(184,'2023-12-12 06:42:21',0),(185,'2023-12-12 06:42:21',0),(186,'2023-12-12 07:22:02',0),(187,'2023-12-12 07:22:02',0),(188,'2023-12-12 07:22:21',0),(189,'2023-12-12 07:22:21',0),(190,'2023-12-12 07:22:45',0),(191,'2023-12-12 07:22:45',0),(192,'2023-12-12 07:23:16',0),(193,'2023-12-12 07:23:16',0),(194,'2023-12-12 07:23:19',0),(195,'2023-12-12 07:23:55',0),(196,'2023-12-12 09:03:39',0),(197,'2023-12-12 09:03:39',0),(198,'2023-12-12 09:15:24',0),(199,'2023-12-12 09:15:24',0),(200,'2023-12-12 15:54:39',0),(201,'2023-12-12 15:54:39',0),(202,'2023-12-12 16:50:35',0),(203,'2023-12-12 16:50:35',0),(204,'2023-12-12 16:59:59',0),(205,'2023-12-13 15:46:05',0),(206,'2023-12-13 15:46:05',0),(207,'2023-12-13 16:20:06',1010000),(208,'2023-12-13 16:20:06',1010000),(209,'2023-12-13 16:59:59',1010000),(210,'2023-12-13 21:07:33',0),(211,'2023-12-13 21:07:34',0),(212,'2023-12-14 08:14:12',0),(213,'2023-12-14 08:14:12',0),(214,'2023-12-14 08:15:35',0);
/*!40000 ALTER TABLE `report_revenue` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_room_capacity`
--

LOCK TABLES `report_room_capacity` WRITE;
/*!40000 ALTER TABLE `report_room_capacity` DISABLE KEYS */;
INSERT INTO `report_room_capacity` VALUES (1,'2023-11-11 14:21:18',27),(2,'2023-11-12 14:25:03',56),(3,'2023-11-13 14:27:41',48),(4,'2023-11-14 14:30:41',11),(5,'2023-11-15 15:07:53',19.43),(6,'2023-11-16 16:02:14',10.167),(7,'2023-11-17 16:03:57',10.167),(8,'2023-11-18 16:04:18',1.0167),(9,'2023-11-19 16:06:00',10.9167),(10,'2023-11-20 17:09:11',0.583333),(11,'2023-11-21 17:10:02',58.3333),(18,'2023-11-22 23:59:01',5.83333),(21,'2023-11-23 23:59:02',10),(22,'2023-11-24 23:59:02',10),(26,'2023-11-25 23:59:02',10),(32,'2023-11-26 20:18:05',10),(35,'2023-12-26 20:18:05',11),(36,'2023-12-27 20:18:05',19),(37,'2023-12-19 20:00:00',19),(38,'2023-11-26 23:59:02',10),(39,'2023-11-27 00:05:48',10),(40,'2023-11-27 23:58:59',7.14286),(41,'2023-11-27 23:58:59',7.14286),(42,'2023-11-28 07:00:00',7.14286),(43,'2023-11-29 00:21:37',16.0333),(44,'2023-11-30 00:32:33',21.4286),(45,'2023-11-30 23:58:59',21.4286),(46,'2023-12-01 23:58:59',21.4286),(47,'2023-12-04 16:58:59',0),(48,'2023-12-05 09:06:04',-0.833333),(49,'2023-12-05 09:07:45',-0.833333),(50,'2023-12-05 09:10:05',-0.833333),(51,'2023-12-05 09:11:45',-0.833333),(52,'2023-12-05 09:13:25',-0.833333),(53,'2023-12-05 09:15:05',-0.833333),(54,'2023-12-05 09:16:45',-0.833333),(55,'2023-12-05 09:22:55',-0.833333),(56,'2023-12-05 09:24:36',-0.833333),(57,'2023-12-05 09:26:16',-0.833333),(58,'2023-12-05 09:26:34',-0.833333),(59,'2023-12-05 09:27:16',-0.833333),(60,'2023-12-05 09:27:27',-0.833333),(61,'2023-12-05 09:27:30',-0.833333),(62,'2023-12-05 09:28:08',-0.833333),(63,'2023-12-05 09:29:25',-0.833333),(64,'2023-12-05 09:29:41',-0.833333),(65,'2023-12-05 09:31:21',-0.833333),(66,'2023-12-05 09:32:23',-0.833333),(67,'2023-12-05 12:49:42',-0.833333),(68,'2023-12-07 16:58:59',0),(69,'2023-12-08 11:08:01',-1.61317),(70,'2023-12-09 11:11:15',7.57808),(71,'2023-12-10 16:58:59',9.99294),(72,'2023-12-11 16:58:59',9.99294),(73,'2023-12-12 07:22:56',5.99264),(74,'2023-12-13 16:58:59',23.9407),(75,'2023-12-14 08:14:12',15.8717);
/*!40000 ALTER TABLE `report_room_capacity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_top_room_class`
--

DROP TABLE IF EXISTS `report_top_room_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report_top_room_class` (
  `report_top_room_class_id` bigint NOT NULL AUTO_INCREMENT,
  `room_class_id` varchar(45) DEFAULT NULL,
  `revenue` float DEFAULT NULL,
  `number_of_rental` bigint DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`report_top_room_class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_top_room_class`
--

LOCK TABLES `report_top_room_class` WRITE;
/*!40000 ALTER TABLE `report_top_room_class` DISABLE KEYS */;
INSERT INTO `report_top_room_class` VALUES (1,'HP000001',1000000,4,_binary '','2023-12-07 07:00:09',NULL),(2,'HP000001',5000000,3,_binary '','2023-11-07 07:00:09',NULL),(3,'HP000002',4000000,2,_binary '','2023-12-07 08:00:09',NULL);
/*!40000 ALTER TABLE `report_top_room_class` ENABLE KEYS */;
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
  `duration_start` timestamp NULL DEFAULT NULL,
  `duration_end` timestamp NULL DEFAULT NULL,
  `note` varchar(350) DEFAULT NULL,
  `staff_check_in` bigint DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `created_by_id` bigint DEFAULT NULL,
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
INSERT INTO `reservation` VALUES ('DP000000','C000000','BG000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
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
  `change_room_class` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`reservation_detail_id`),
  KEY `pk_rd_r_idx` (`room_id`),
  KEY `pk__idx` (`reservation_id`),
  CONSTRAINT `fk_rd_r` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`reservation_id`),
  CONSTRAINT `pk_rd_r` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation_detail`
--

LOCK TABLES `reservation_detail` WRITE;
/*!40000 ALTER TABLE `reservation_detail` DISABLE KEYS */;
INSERT INTO `reservation_detail` VALUES (0,'DP000000','P000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation_detail_customer`
--

LOCK TABLES `reservation_detail_customer` WRITE;
/*!40000 ALTER TABLE `reservation_detail_customer` DISABLE KEYS */;
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
INSERT INTO `room` VALUES ('P000000','Sảnh','HP000000',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('P000001','P.101','HP000003',1,2,'ROOM_USING','0','','Phòng cơ bản',NULL,NULL,'2023-10-15 17:00:00','2023-12-12 07:26:40'),('P000002','P.102','HP000001',1,2,'ROOM_EMPTY','ROOM_CLEAN',NULL,'Phòng cơ bản',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000003','P.201','HP000002',2,1,'ROOM_USING','ROOM_CLEAN',NULL,'Phòng hạng trung',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000004','P.202','HP000002',2,1,'ROOM_USING','ROOM_CLEAN',NULL,'Phòng hạng trung',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000005','P.301','HP000003',3,1,'ROOM_EMPTY','ROOM_UNCLEAN',NULL,'Phòng đơn',NULL,NULL,'2023-10-15 17:00:00','2023-12-12 16:42:03'),('P000006','P.302','HP000003',3,1,'ROOM_USING','ROOM_CLEAN',NULL,'Phòng đơn',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000007','P.401','HP000004',4,1,'ROOM_EMPTY','ROOM_CLEAN',NULL,'Phòng gia đình',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000008','P.402','HP000004',4,1,'ROOM_EMPTY','ROOM_CLEAN',NULL,'Phòng gia đình',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000009','P.501','HP000005',5,1,'ROOM_EMPTY','ROOM_UNCLEAN',NULL,'Phòng VIP',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000010','P.502','HP000005',5,1,'ROOM_EMPTY','ROOM_UNCLEAN',NULL,'Phòng VIP',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00');
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
INSERT INTO `room_category` VALUES ('HP000000','Sảnh',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('HP000001','Phòng cơ bản',300000,300000,200000,2,1,3,2,20,2,'Phòng cơ bản',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-12-12 07:26:11'),('HP000002','Phòng hạng trung',700000,300000,200000,3,1,4,2,30,1,'Phòng hạng trung',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-12-04 18:06:45'),('HP000003','Phòng đơn',400000,400000,250000,1,1,2,2,18,1,'Phòng đơn',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-11-18 01:25:04'),('HP000004','Phòng gia đình',900000,500000,300000,4,1,5,2,40,1,'Phòng gia đình',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('HP000005','Phòng VIP',1200000,800000,500000,2,1,3,2,25,1,'Phòng VIP',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('HP000006','Phòng Suite',1500000,1000000,600000,2,1,3,2,30,2,'Phòng Suite',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-12-04 18:17:52'),('HP000007','Phòng VIP2',2000000,1500000,800000,6,1,7,2,70,2,'Phòng VIP2',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-12-04 18:17:47'),('HP000008','Phòng Ocean View',800000,500000,350000,3,1,3,1,35,2,'Phòng với view biển',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-12-04 18:17:43'),('HP000009','Phòng Deluxe Suite',1800000,1200000,700000,2,1,3,2,35,2,'Phòng Deluxe Suite',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-12-04 18:17:39'),('HP000010','Phòng Tổng Thống',3000000,2000000,1000000,2,1,3,2,40,2,'Phòng Phủng',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-12-04 18:17:35'),('HP000011','Phòng Cơ Bản',1,1,1,1,1,1,1,1,6,'','',NULL,NULL,'2023-12-06 18:15:44',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (2,'Đinh Văn Tiến','tien','$2a$10$ato6VNKwexGRQqMkV1pH2Os187zIP88S98kS55hXMYYoUY2xJAI0.','ROLE_MANAGER','ACTIVE',NULL,'w','123',NULL,'3',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'PB000001'),(3,'Lê Minh Nhật','h','$2a$10$ZN/3xo87NBoCB8kNAahbWuJl/Q.Caum4MAcwTxoDcXUAbigHzDEty','ROLE_MANAGER','ACTIVE','2023-11-07 00:00:00','e','tapdoanhhl@gmail.comdd',_binary '\0','sdsdsdas',NULL,'0812569567',NULL,NULL,NULL,NULL,NULL,'PB000001'),(6,'ADMIN','admin','$2a$10$Z/GE11unhNydjntS/VUD6uG267P0qv9p3jl0L8tu.xf7hp/DAe9Ui','ROLE_MANAGER','ACTIVE',NULL,'y',NULL,NULL,'9',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'PB000001'),(10,'Trịnh Bảo Khánh','khanh','$2a$10$UxveZveQROqPxZSR4FWgC.WcBP1npyttwVbpVCc8oBt02DN8398tm','ROLE_MANAGER','ACTIVE','2023-11-15 00:00:00','Số nhà 45B ngõ 46 Phạm Ngọc Thạch','khanhtbhe153468@fpt.edu.vn',_binary '','12344','null','0812569567',NULL,NULL,NULL,NULL,NULL,'PB000001'),(34,'Nguyễn Quang Huy','huyhuy','$2a$10$K.YQo4h1be2U8MDqPNJdpuWCW6Nvd6yWu6vzbc0/gIsBHvgoBDU8e','ROLE_RECEPTIONIST','ACTIVE','2023-11-06 00:00:00','thạch hòa thạch thất hà nội','tapdoanhhl52@gmail.com',_binary '','123456789','null','0812569567',NULL,NULL,NULL,NULL,NULL,'PB000001');
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
INSERT INTO `time_use` VALUES (1,'09:00:00','14:00:00','12:00:00','22:00:00','TIME_LATE',30,6);
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
) ENGINE=InnoDB AUTO_INCREMENT=576 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-14 15:47:01
