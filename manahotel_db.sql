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
INSERT INTO `customer` VALUES ('C000000','Khách lẻ','NK000000','0812569567','2023-11-17 00:00:00','tapdoanhhl@gmail.com','thạch hòa thạch thất hà nội','132u738921','vn','123',_binary '\0',NULL,NULL,NULL,NULL),('C000001','Trịnh Bảo Khánh','NK000000','0898637030','2001-12-22 17:00:00','trinhbaokhanh1306@gmail.com','Hoa Lac','038201003039','Việt Nam','',_binary '','',2,_binary '','ACTIVE');
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
INSERT INTO `customer_group` VALUES ('NK000000','Khách lẻ',NULL,'ACTIVE');
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
INSERT INTO `department` VALUES ('PB000000','Nhân Viên','ACTIVE'),('PB000001','Lễ Tân','ACTIVE');
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
INSERT INTO `floor` VALUES (0,'Sảnh',NULL,NULL,NULL,NULL,NULL),(1,'Tầng 1',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(2,'Tầng 2',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(3,'Tầng 3',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(4,'Tầng 4',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(5,'Tầng 5',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00');
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
  `goods_unit_id` int NOT NULL,
  PRIMARY KEY (`import_goods_detail_id`),
  KEY `pk_igd_g_idx` (`goods_id`),
  KEY `pk_igd_ig_idx` (`import_goods_id`),
  KEY `pk_igd_gu_idx` (`goods_unit_id`),
  CONSTRAINT `pk_igd_g` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`),
  CONSTRAINT `pk_igd_gu` FOREIGN KEY (`goods_unit_id`) REFERENCES `goods_unit` (`goods_unit_id`),
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `type` varchar(250) DEFAULT NULL,
  `unit` varchar(250) DEFAULT NULL,
  `limit_value` bigint DEFAULT NULL,
  `type_value` varchar(250) DEFAULT NULL,
  `policy_value` float DEFAULT NULL,
  `note` varchar(350) DEFAULT NULL,
  `auto_add_to_invoice` bit(1) DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`policy_detail_id`),
  KEY `pk_pd_rc_idx` (`room_category_id`),
  KEY `pk_pd_p_idx` (`policy_id`),
  CONSTRAINT `pk_pd_p` FOREIGN KEY (`policy_id`) REFERENCES `policy` (`policy_id`),
  CONSTRAINT `pk_pd_rc` FOREIGN KEY (`room_category_id`) REFERENCES `room_category` (`room_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=214 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policy_detail`
--

LOCK TABLES `policy_detail` WRITE;
/*!40000 ALTER TABLE `policy_detail` DISABLE KEYS */;
INSERT INTO `policy_detail` VALUES (194,'CS000008','HP000001',NULL,NULL,1,NULL,0,NULL,NULL,1),(195,'CS000008','HP000002',NULL,NULL,1,NULL,0,NULL,NULL,1),(196,'CS000008','HP000003',NULL,NULL,1,NULL,0,NULL,NULL,1),(197,'CS000008','HP000004',NULL,NULL,1,NULL,0,NULL,NULL,1),(198,'CS000008','HP000005',NULL,NULL,1,NULL,0,NULL,NULL,1),(199,'CS000001','HP000001',NULL,NULL,1,NULL,0,NULL,NULL,1),(200,'CS000001','HP000002',NULL,NULL,1,NULL,0,NULL,NULL,1),(201,'CS000001','HP000003',NULL,NULL,1,NULL,0,NULL,NULL,1),(202,'CS000001','HP000004',NULL,NULL,1,NULL,0,NULL,NULL,1),(203,'CS000001','HP000005',NULL,NULL,1,NULL,0,NULL,NULL,1),(204,'CS000005','HP000000',NULL,NULL,NULL,NULL,70,NULL,NULL,1),(205,'CS000004','HP000000',NULL,NULL,30,NULL,30,NULL,NULL,1),(206,'CS000004','HP000000',NULL,NULL,15,NULL,10,NULL,NULL,1),(207,'CS000007','HP000000',NULL,NULL,1000000,NULL,1,NULL,NULL,1),(208,'CS000006','HP000000',NULL,NULL,1,NULL,10000,NULL,NULL,1),(213,'CS000003','HP000000','VAT',NULL,NULL,'%',5,NULL,_binary '\0',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recent_activity`
--

LOCK TABLES `recent_activity` WRITE;
/*!40000 ALTER TABLE `recent_activity` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=265 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_revenue`
--

LOCK TABLES `report_revenue` WRITE;
/*!40000 ALTER TABLE `report_revenue` DISABLE KEYS */;
INSERT INTO `report_revenue` VALUES (263,'2023-12-25 07:18:28',0),(264,'2023-12-25 07:18:28',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_room_capacity`
--

LOCK TABLES `report_room_capacity` WRITE;
/*!40000 ALTER TABLE `report_room_capacity` DISABLE KEYS */;
INSERT INTO `report_room_capacity` VALUES (82,'2023-12-25 07:18:28',0);
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
  `price_history_over_time` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`reservation_detail_id`),
  KEY `pk_rd_r_idx` (`room_id`),
  KEY `pk__idx` (`reservation_id`),
  CONSTRAINT `fk_rd_r` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`reservation_id`),
  CONSTRAINT `pk_rd_r` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
INSERT INTO `room` VALUES ('P000000','Sảnh','HP000000',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('P000001','P.101','HP000003',1,1,'ROOM_EMPTY','ROOM_CLEAN','','Phòng cơ bản',NULL,NULL,'2023-10-15 17:00:00','2023-12-25 07:19:56'),('P000002','P.102','HP000001',1,1,'ROOM_EMPTY','ROOM_CLEAN',NULL,'Phòng cơ bản',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000003','P.201','HP000002',2,1,'ROOM_EMPTY','ROOM_CLEAN',NULL,'Phòng hạng trung',NULL,NULL,'2023-10-15 17:00:00','2023-12-15 15:18:19'),('P000004','P.202','HP000002',2,1,'ROOM_EMPTY','ROOM_CLEAN',NULL,'Phòng hạng trung',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000005','P.301','HP000003',3,1,'ROOM_EMPTY','ROOM_CLEAN',NULL,'Phòng đơn',NULL,NULL,'2023-10-15 17:00:00','2023-12-12 16:42:03'),('P000006','P.302','HP000003',3,1,'ROOM_EMPTY','ROOM_CLEAN',NULL,'Phòng đơn',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000007','P.401','HP000004',4,1,'ROOM_EMPTY','ROOM_CLEAN',NULL,'Phòng gia đình',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000008','P.402','HP000004',4,1,'ROOM_EMPTY','ROOM_CLEAN',NULL,'Phòng gia đình',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000009','P.501','HP000005',5,1,'ROOM_EMPTY','ROOM_CLEAN',NULL,'Phòng VIP',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000010','P.502','HP000005',5,1,'ROOM_EMPTY','ROOM_CLEAN',NULL,'Phòng VIP',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00');
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
INSERT INTO `room_category` VALUES ('HP000000','Sảnh',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('HP000001','Phòng cơ bản',300000,300000,200000,2,1,3,2,20,1,'Phòng cơ bản',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-12-25 07:19:48'),('HP000002','Phòng hạng trung',700000,300000,200000,3,1,4,2,30,1,'Phòng hạng trung',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-12-04 18:06:45'),('HP000003','Phòng đơn',400000,400000,250000,1,1,2,2,18,1,'Phòng đơn',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-11-18 01:25:04'),('HP000004','Phòng gia đình',900000,500000,300000,4,1,5,2,40,1,'Phòng gia đình',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('HP000005','Phòng VIP',1200000,800000,500000,2,1,3,2,25,1,'Phòng VIP',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00');
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
INSERT INTO `staff` VALUES (0,'Quản Lý','admin','$2a$10$Z/GE11unhNydjntS/VUD6uG267P0qv9p3jl0L8tu.xf7hp/DAe9Ui','ROLE_MANAGER','ACTIVE','2000-11-29 17:00:00','VĂN LÂM HOTEL','manahotelsystem@gmail.com',_binary '','0992828828222222','null','087987872722','',NULL,NULL,NULL,NULL,'PB000000');
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
) ENGINE=InnoDB AUTO_INCREMENT=586 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (585,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjAsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAzNDg4NzA4LCJleHAiOjE3MDM1NzUxMDh9.jB4kCvKM2lMc8SEzPN2ke7Roys1ZeXzIrThVAokUV9Y',0,'2023-12-25 07:23:28');
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

-- Dump completed on 2023-12-25 14:31:00
