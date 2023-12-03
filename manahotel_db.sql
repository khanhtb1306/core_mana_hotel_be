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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_account`
--

LOCK TABLES `bank_account` WRITE;
/*!40000 ALTER TABLE `bank_account` DISABLE KEYS */;
INSERT INTO `bank_account` VALUES (1,'0889967536','NGUYEN QUANG HUAN',970422);
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
                                  PRIMARY KEY (`control_policy_id`),
                                  KEY `pk_cp_rd_idx` (`reservation_detail_id`),
                                  KEY `pk_cp_pd_idx` (`policy_id`),
                                  CONSTRAINT `pk_cp_p` FOREIGN KEY (`policy_id`) REFERENCES `policy` (`policy_id`),
                                  CONSTRAINT `pk_cp_rd` FOREIGN KEY (`reservation_detail_id`) REFERENCES `reservation_detail` (`reservation_detail_id`)
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
INSERT INTO `customer` VALUES ('C000000','Bán lẻ','NK000000','0812569567','2023-11-17 00:00:00','tapdoanhhl@gmail.com','thạch hòa thạch thất hà nội','132u738921','vn','123',_binary '\0',NULL,NULL),('C000001','Dinh Tien','NK000006','9815655945','2002-11-03 00:00:00','TienDV18@fsoft.com.vn','fpt','132u738921','VN','83291',_binary '\0',NULL,NULL),('C000002','Hiển','NK000008','0123456789','2001-11-03 00:00:00','hiengay@gmail.com','Nhà','091826351','Việt Nam','0123456',_binary '',NULL,NULL),('C000003','Trung','NK000008','0123456789','2023-11-03 00:00:00','hiengay@gmail.com','Nhà','091826351','Việt Nam','0123456',_binary '',NULL,NULL),('C000006','Tien Van Dinh 2','NK000007','0981987625','2001-10-26 00:00:00','TienDV18@fsoft.com.vn','P2E2, Cong Vi, Ba Dinh','132u738921','Viet Nam','83291',_binary '',NULL,NULL),('C000009','Tien Van Dinh 123','NK000006','0981987625','2001-10-26 00:00:00','TienDV18@fsoft.com.vn','P2E2, Cong Vi, Ba Dinh','132u738921','Viet Nam','',_binary '\0',NULL,NULL),('C000010','Mai Dinh Tung','NK000006','','2017-10-04 00:00:00','','','','','',_binary '',NULL,NULL),('C000019','Cu Thu','NK000000','0987798590','2023-11-25 00:00:00','thucthhe150889','Ha Noi','12345678909876543','vn','wsdfgh',_binary '\0',NULL,NULL),('C000020','Tien Van Dinh','NK000001','0981987625','2001-10-26 00:00:00','TienDV18@fsoft.com.vn','P2E2, Cong Vi, Ba Dinh','132u738921','Viet Nam','83291',_binary '',NULL,NULL),('C000021','Tien Van Dinh','NK000001','0981987625','2001-10-26 00:00:00','TienDV18@fsoft.com.vn','P2E2, Cong Vi, Ba Dinh','132u738921','','',_binary '',NULL,NULL),('C000022','Dinh Tien','NK000000','','2021-10-26 00:00:00','','','','','',_binary '',NULL,NULL),('C000023','A','NK000000','09282927272','2015-05-13 00:00:00','','HA NOI','0992828828222222','VIET NAM','',_binary '',NULL,NULL),('C000024','Huy Nguyễn','NK000000','0812569567','2023-11-23 00:00:00','tapdoanhhl12123@gmail.com','thạch hòa thạch thất hà nội','132u73892112123','vn','123',_binary '',NULL,NULL);
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
INSERT INTO `customer_group` VALUES ('NK000000','Bán lẻ',NULL,NULL),('NK000001','Hội thanh niên',NULL,'ACTIVE'),('NK000006','Hội nguời già',NULL,'ACTIVE'),('NK000007','Hội nguời da đen',NULL,'ACTIVE'),('NK000008','Hội nguời tàn tật',NULL,'ACTIVE'),('NK000009','Đối tác quan trọng',NULL,'ACTIVE');
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
INSERT INTO `department` VALUES ('PB000001','lễ tân',NULL),('PB000002','dọn phòng','a');
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `floor`
--

LOCK TABLES `floor` WRITE;
/*!40000 ALTER TABLE `floor` DISABLE KEYS */;
INSERT INTO `floor` VALUES (0,'Sảnh',NULL,NULL,NULL,NULL,NULL),(1,'Tầng 1',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(2,'Tầng 2',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(3,'Tầng 3',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(4,'Tầng 4',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(5,'Tầng 5',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(6,'Tầng 6',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(7,'Tầng 7',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(8,'Tầng 8',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(9,'Tầng 9',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(10,'Tầng 10',1,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),(12,NULL,NULL,NULL,NULL,'2023-11-24 15:57:57',NULL);
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
                             `payer_receiver` varchar(250) DEFAULT NULL,
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
INSERT INTO `goods` VALUES ('SP000001','Sprite',_binary '',1,99990,100,100000,'Non nước ngọt Sprite','Non Sprite thể tích 150ml',NULL,NULL,NULL,'2023-10-17 05:00:00','2023-10-17 05:00:00'),('SP000002','Coca Cola',_binary '',1,99962,100,100000,'Non nước ngọt Coca Cola','Non Coca Cola thể tích 150ml',NULL,NULL,NULL,'2023-10-17 05:00:00','2023-10-17 05:00:00'),('SP000003','Thịt bò khô',_binary '',1,4049,100,100000,'Thịt bò khô','Thịt bò khô 10 miếng',NULL,NULL,2,'2023-10-17 05:00:00','2023-11-04 15:39:49'),('SP000004','Thuê xe máy',_binary '\0',1,NULL,NULL,NULL,'Xe máy','Xe máy đầy xăng',NULL,NULL,NULL,'2023-10-17 05:00:00','2023-10-17 05:00:00'),('SP000005','Thuê ô tô',_binary '\0',1,NULL,NULL,NULL,'Ô tô','Ô tô điện',NULL,NULL,NULL,'2023-10-17 05:00:00','2023-10-17 05:00:00'),('SP000006','Massage',_binary '\0',1,NULL,NULL,NULL,'Mát xa','Mát xa cực phê',NULL,NULL,NULL,'2023-10-17 05:00:00','2023-10-17 05:00:00'),('SP000007','Mỳ tôm',_binary '',1,99947,100,100000,'Mỳ tôm','Mỳ hảo hán',NULL,NULL,NULL,'2023-10-17 05:00:00','2023-10-17 05:00:00'),('SP000008','Bia Huda',_binary '',1,100000,100,100000,'Bia','Đậm tình miền Trung',NULL,NULL,NULL,'2023-10-17 05:00:00','2023-10-17 05:00:00'),('SP000009','Bim bim Lays',_binary '',1,10,100,100000,'Bim bim','Anh muốn Lays em',NULL,NULL,NULL,'2023-10-17 05:00:00','2023-10-17 05:00:00'),('SP000010','Cắt tóc',_binary '\0',1,NULL,NULL,NULL,'Cắt tóc','Tommy Xiaomi',NULL,NULL,NULL,'2023-10-17 05:00:00','2023-10-17 05:00:00'),('SP000011','Massage',_binary '',1,10,0,1000,NULL,NULL,NULL,NULL,NULL,'2023-11-26 22:00:22','2023-11-28 02:16:28'),('SP000012','test dich vu 1',_binary '\0',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2023-11-26 22:00:33',NULL),('SP000013','Massage',_binary '',6,10,0,1000,NULL,NULL,NULL,NULL,NULL,'2023-11-28 01:45:18',NULL);
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
INSERT INTO `goods_unit` VALUES (1,'Lon','SP000001',500,1000,_binary ''),(2,'Lon','SP000002',300,700,_binary ''),(3,'Gói','SP000003',50,100,_binary ''),(4,'Lần','SP000004',0,900,_binary ''),(5,'Lần','SP000005',0,1100,_binary ''),(6,'Lần','SP000006',0,750,_binary ''),(7,'Gói','SP000007',60,120,_binary ''),(8,'Lon','SP000008',450,950,_binary ''),(9,'Gói','SP000009',600,1300,_binary ''),(10,'Lần','SP000010',0,1200,_binary ''),(12,'Thùng','SP000003',1000,1800,_binary '\0'),(13,'Dây','SP000003',350,600,_binary '\0'),(14,'hop','SP000011',0,0,_binary ''),(15,'lan','SP000012',NULL,0,_binary ''),(16,'lần','SP000013',20,220,_binary '');
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
INSERT INTO `inventory_check` VALUES ('KK000001','2023-10-17 05:00:00','Kiểm kê lần 1',5,'2023-10-17 05:00:00',NULL),('KK000002','2023-11-27 14:31:48','',5,'2023-11-11 11:16:25',2),('KK000003',NULL,'',3,'2023-11-11 11:53:47',2),('KK000004',NULL,'',3,'2023-11-11 11:57:23',2),('KK000005',NULL,'',4,'2023-11-11 11:59:37',2),('KK000006',NULL,'',4,'2023-11-11 12:00:12',2),('KK000007','2023-11-24 16:45:48','',5,'2023-11-24 16:45:02',6),('KK000008',NULL,'',4,'2023-11-27 14:03:19',6),('KK000009',NULL,'',4,'2023-11-27 14:03:34',6),('KK000010',NULL,'',4,'2023-11-27 14:03:59',6),('KK000011',NULL,'',4,'2023-11-27 14:04:43',6),('KK000012','2023-11-27 14:08:59','',5,'2023-11-27 14:08:59',6);
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
INSERT INTO `inventory_check_detail` VALUES (1,'KK000001','SP000001',100000,0,0,100000,500),(2,'KK000001','SP000002',100000,0,0,100000,300),(3,'KK000001','SP000003',100000,0,0,100000,50),(4,'KK000001','SP000007',100000,0,0,100000,60),(5,'KK000001','SP000008',100000,0,0,100000,450),(6,'KK000001','SP000009',100000,0,0,100000,600),(9,'KK000003','SP000001',200000,100000,50000000,100000,500),(10,'KK000004','SP000001',100000,0,0,100000,500),(11,'KK000004','SP000002',100000,0,0,100000,300),(12,'KK000005','SP000003',0,-99979,-4998950,99979,50),(14,'KK000006','SP000003',128,-99851,-4992550,99979,50),(17,'KK000007','SP000003',4050,-95900,-4795000,99950,50),(18,'KK000007','SP000009',10,-99990,-59994000,100000,600),(19,'KK000008','SP000002',123,-99856,-29956800,99979,300),(20,'KK000009','SP000002',1023,-98956,-29686800,99979,300),(21,'KK000010','SP000002',123,-99856,-29956800,99979,300),(22,'KK000011','SP000003',101201230123,101201226075,5060060000000,4048,50),(23,'KK000012','SP000001',99990,-4,-2000,99994,500),(25,'KK000002','SP000003',4048,0,0,4048,50);
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
                           `reservation_id` varchar(50) NOT NULL,
                           `created_by_id` bigint DEFAULT NULL,
                           `total` float DEFAULT NULL,
                           `discount` float DEFAULT NULL,
                           `created_date` timestamp NULL DEFAULT NULL,
                           `status` varchar(50) DEFAULT NULL,
                           `note` varchar(250) DEFAULT NULL,
                           `paid_method` varchar(50) DEFAULT NULL,
                           `transaction_code` varchar(50) DEFAULT NULL,
                           PRIMARY KEY (`invoice_id`),
                           KEY `pk_i_c_idx` (`customer_id`),
                           KEY `pk_i_r_idx` (`reservation_id`),
                           CONSTRAINT `pk_i_c` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
                           CONSTRAINT `pk_i_r` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`reservation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES ('HD000000','C000000','DP000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice_reservation_detail`
--

DROP TABLE IF EXISTS `invoice_reservation_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice_reservation_detail` (
                                              `invoice_reservation_detail_id` bigint NOT NULL,
                                              `invoice_id` varchar(50) NOT NULL,
                                              `reservation_detail_id` bigint NOT NULL,
                                              PRIMARY KEY (`invoice_reservation_detail_id`),
                                              KEY `pk_ird_i_idx` (`invoice_id`),
                                              KEY `pk_ird_rd_idx` (`reservation_detail_id`),
                                              CONSTRAINT `pk_ird_i` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`invoice_id`),
                                              CONSTRAINT `pk_ird_rd` FOREIGN KEY (`reservation_detail_id`) REFERENCES `reservation_detail` (`reservation_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
INSERT INTO `order` VALUES ('HD000000',0,NULL,NULL,NULL,NULL),('HD000009',1,1380,'PAID',3,'2023-11-21 16:32:16'),('HD000012',1,4340,'PAID',2,'2023-11-21 15:28:42'),('HD000013',1,2020,'CANCEL_ORDER',2,'2023-11-21 15:37:38'),('HD000014',1,1720,'CONFIRMED',2,'2023-11-21 15:45:00'),('HD000015',1,2400,'CONFIRMED',2,'2023-11-21 15:46:05'),('HD000016',1,1520,'CONFIRMED',2,'2023-11-21 15:55:45'),('HD000017',1,1520,'UNCONFIRMED',2,'2023-11-21 16:03:40'),('HD000018',1,2500,'UNCONFIRMED',2,'2023-11-21 16:07:22'),('HD000019',1,1520,'UNCONFIRMED',2,'2023-11-21 16:15:25'),('HD000020',1,1580,'UNCONFIRMED',2,'2023-11-21 16:31:01'),('HD000021',2,1520,'PAID',2,'2023-11-22 03:17:21'),('HD000022',2,2760,'CANCEL_ORDER',2,'2023-11-22 03:33:47'),('HD000023',17,3200,'PAID',2,'2023-11-24 15:08:03'),('HD000024',17,1640,'CANCEL_ORDER',2,'2023-11-24 15:13:01'),('HD000025',38,3040,'CONFIRMED',2,'2023-11-28 07:04:54'),('HD000026',38,2340,'UNCONFIRMED',2,'2023-11-28 07:09:21'),('HD000027',2,2220,'UNCONFIRMED',2,'2023-11-29 03:16:33'),('HD000028',2,2220,'UNCONFIRMED',2,'2023-11-29 03:55:10'),('HD000029',2,820,'UNCONFIRMED',2,'2023-11-29 03:59:23'),('HD000030',17,1520,'UNCONFIRMED',2,'2023-11-29 04:00:53'),('HD000031',41,1000,'CANCEL_ORDER',34,'2023-11-29 09:56:04'),('HD000032',41,3120,'CANCEL_ORDER',34,'2023-11-29 09:57:14');
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
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (26,'HD000012','SP000002',2,'HD000000',4,700),(27,'HD000012','SP000007',7,'HD000000',2,120),(28,'HD000012','SP000003',13,'HD000000',2,600),(29,'HD000012','SP000003',3,'HD000000',1,100),(30,'HD000013','SP000002',2,'HD000000',1,700),(31,'HD000013','SP000007',7,'HD000000',1,120),(32,'HD000013','SP000003',13,'HD000000',2,600),(33,'HD000014','SP000002',2,'HD000000',2,700),(34,'HD000014','SP000007',7,'HD000000',1,120),(35,'HD000014','SP000003',3,'HD000000',2,100),(36,'HD000015','SP000002',2,'HD000000',2,700),(37,'HD000015','SP000001',1,'HD000000',1,1000),(38,'HD000016','SP000002',2,'HD000000',2,700),(39,'HD000016','SP000007',7,'HD000000',1,120),(40,'HD000017','SP000002',2,'HD000000',2,700),(41,'HD000017','SP000007',7,'HD000000',1,120),(42,'HD000018','SP000002',2,'HD000000',2,700),(43,'HD000018','SP000001',1,'HD000000',1,1000),(44,'HD000018','SP000003',3,'HD000000',1,100),(45,'HD000019','SP000002',2,'HD000000',2,700),(46,'HD000019','SP000007',7,'HD000000',1,120),(47,'HD000020','SP000003',3,'HD000000',5,100),(48,'HD000020','SP000007',7,'HD000000',4,120),(49,'HD000020','SP000003',13,'HD000000',1,600),(50,'HD000009','SP000003',3,'HD000000',3,100),(51,'HD000009','SP000007',7,'HD000000',4,120),(52,'HD000009','SP000003',13,'HD000000',1,600),(53,'HD000021','SP000002',2,'HD000000',2,700),(54,'HD000021','SP000007',7,'HD000000',1,120),(59,'HD000022','SP000001',1,'HD000000',1,1000),(60,'HD000022','SP000003',3,'HD000000',2,100),(61,'HD000022','SP000007',7,'HD000000',3,120),(62,'HD000022','SP000003',13,'HD000000',2,600),(63,'HD000023','SP000001',1,'HD000000',3,1000),(64,'HD000023','SP000003',3,'HD000000',2,100),(65,'HD000024','SP000002',2,'HD000000',2,700),(66,'HD000024','SP000007',7,'HD000000',2,120),(67,'HD000025','SP000002',2,'HD000000',4,700),(68,'HD000025','SP000007',7,'HD000000',2,120),(69,'HD000026','SP000002',2,'HD000000',3,700),(70,'HD000026','SP000007',7,'HD000000',2,120),(71,'HD000027','SP000002',2,'HD000000',3,700),(72,'HD000027','SP000007',7,'HD000000',1,120),(73,'HD000028','SP000002',2,'HD000000',3,700),(74,'HD000028','SP000007',7,'HD000000',1,120),(75,'HD000029','SP000002',2,'HD000000',1,700),(76,'HD000029','SP000007',7,'HD000000',1,120),(77,'HD000030','SP000002',2,'HD000000',2,700),(78,'HD000030','SP000007',7,'HD000000',1,120),(80,'HD000031','SP000003',3,'HD000000',-1,100),(81,'HD000032','SP000002',2,'HD000000',1,700),(82,'HD000032','SP000007',7,'HD000000',1,120);
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
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policy_detail`
--

LOCK TABLES `policy_detail` WRITE;
/*!40000 ALTER TABLE `policy_detail` DISABLE KEYS */;
INSERT INTO `policy_detail` VALUES (1,'CS000001','HP000001','NK000000','','Giờ',1,'%',NULL,NULL,10,NULL,NULL,1),(2,'CS000001','HP000001','NK000000','','Giờ',3,'%',NULL,NULL,30,NULL,NULL,1),(7,'CS000002','HP000001','NK000001','','Người',3,'VND',NULL,NULL,200000,NULL,NULL,6),(8,'CS000002','HP000001','NK000001','','Người',1,'VND',NULL,NULL,100000,NULL,NULL,6),(9,'CS000001','HP000001','NK000000',NULL,NULL,1,NULL,NULL,NULL,77777800,NULL,NULL,6),(10,'CS000001','HP000001','NK000000',NULL,'Giờ',1,'VND',NULL,NULL,77777800,NULL,NULL,6),(12,'CS000008','HP000001','NK000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,6),(13,'CS000008','HP000001','NK000000',NULL,NULL,2,NULL,NULL,NULL,10000,NULL,NULL,6),(14,'CS000001','HP000001','NK000000',NULL,NULL,1,NULL,NULL,NULL,77777800,NULL,NULL,6),(15,'CS000008','HP000001','NK000000',NULL,NULL,3,NULL,NULL,NULL,50000,NULL,NULL,6),(16,'CS000001','HP000001','NK000000',NULL,NULL,2,NULL,NULL,NULL,30000,NULL,NULL,6),(17,'CS000008','HP000001','NK000000',NULL,NULL,5,NULL,NULL,NULL,100000,NULL,NULL,6),(18,'CS000008','HP000001','NK000000',NULL,NULL,4,NULL,NULL,NULL,30000,NULL,NULL,6),(19,'CS000008','HP000001','NK000000',NULL,NULL,4,NULL,NULL,NULL,40000,NULL,NULL,6),(20,'CS000008','HP000002','NK000000',NULL,NULL,2,NULL,NULL,NULL,10000,NULL,NULL,6),(21,'CS000008','HP000003','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(22,'CS000008','HP000004','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(23,'CS000008','HP000005','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(24,'CS000008','HP000006','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(25,'CS000008','HP000007','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(26,'CS000008','HP000008','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(27,'CS000008','HP000009','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(28,'CS000008','HP000010','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(29,'CS000001','HP000001','NK000000',NULL,NULL,1,NULL,NULL,NULL,10000,NULL,NULL,6),(30,'CS000001','HP000002','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(31,'CS000001','HP000003','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(32,'CS000001','HP000004','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(33,'CS000001','HP000005','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(34,'CS000001','HP000006','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(35,'CS000001','HP000007','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(36,'CS000001','HP000008','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(37,'CS000001','HP000009','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(38,'CS000001','HP000010','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,6),(39,'CS000008','HP000003','NK000000',NULL,NULL,1,NULL,NULL,NULL,20000,NULL,NULL,1),(40,'CS000008','HP000004','NK000000',NULL,NULL,1,NULL,NULL,NULL,100,NULL,NULL,1),(41,'CS000008','HP000005','NK000000',NULL,NULL,1,NULL,NULL,NULL,10,NULL,NULL,1),(42,'CS000008','HP000006','NK000000',NULL,NULL,1,NULL,NULL,NULL,20,NULL,NULL,6),(43,'CS000008','HP000007','NK000000',NULL,NULL,1,NULL,NULL,NULL,30,NULL,NULL,1),(44,'CS000008','HP000008','NK000000',NULL,NULL,1,NULL,NULL,NULL,57,NULL,NULL,1),(45,'CS000008','HP000009','NK000000',NULL,NULL,1,NULL,NULL,NULL,70,NULL,NULL,1),(46,'CS000008','HP000010','NK000000',NULL,NULL,3,NULL,NULL,NULL,100,NULL,NULL,1),(47,'CS000001','HP000002','NK000000',NULL,NULL,1,NULL,NULL,NULL,1000,NULL,NULL,1),(48,'CS000001','HP000003','NK000000',NULL,NULL,1,NULL,NULL,NULL,1000,NULL,NULL,1),(49,'CS000001','HP000004','NK000000',NULL,NULL,2,NULL,NULL,NULL,2000,NULL,NULL,1),(50,'CS000001','HP000005','NK000000',NULL,NULL,1,NULL,NULL,NULL,10,NULL,NULL,1),(51,'CS000001','HP000006','NK000000',NULL,NULL,1,NULL,NULL,NULL,20,NULL,NULL,6),(52,'CS000001','HP000007','NK000000',NULL,NULL,1,NULL,NULL,NULL,40,NULL,NULL,1),(53,'CS000001','HP000008','NK000000',NULL,NULL,1,NULL,NULL,NULL,50,NULL,NULL,1),(54,'CS000001','HP000009','NK000000',NULL,NULL,1,NULL,NULL,NULL,80,NULL,NULL,1),(55,'CS000001','HP000010','NK000000',NULL,NULL,3,NULL,NULL,NULL,90,NULL,NULL,1),(56,'CS000001','HP000001','NK000000',NULL,NULL,2,NULL,NULL,NULL,0,NULL,NULL,6),(57,'CS000001','HP000001','NK000000',NULL,NULL,2,NULL,NULL,NULL,10000,NULL,NULL,6),(58,'CS000009','HP000001','NK000000',NULL,'Người ',1,'VND',NULL,NULL,333333,NULL,NULL,6),(59,'CS000002','HP000001','NK000000',NULL,NULL,1,NULL,NULL,NULL,200000,NULL,NULL,6),(60,'CS000002','HP000001','NK000000',NULL,NULL,3,NULL,NULL,NULL,100000,NULL,NULL,6),(61,'CS000002','HP000002','NK000000',NULL,NULL,1,NULL,NULL,NULL,10,NULL,NULL,6),(62,'CS000002','HP000003','NK000000',NULL,NULL,1,NULL,NULL,NULL,20,NULL,NULL,1),(63,'CS000002','HP000004','NK000000',NULL,NULL,1,NULL,NULL,NULL,30,NULL,NULL,1),(64,'CS000002','HP000005','NK000000',NULL,NULL,1,NULL,NULL,NULL,40,NULL,NULL,1),(65,'CS000002','HP000006','NK000000',NULL,NULL,1,NULL,NULL,NULL,50,NULL,NULL,1),(66,'CS000002','HP000007','NK000000',NULL,NULL,1,NULL,NULL,NULL,60,NULL,NULL,1),(67,'CS000002','HP000008','NK000000',NULL,NULL,1,NULL,NULL,NULL,70,NULL,NULL,1),(68,'CS000002','HP000009','NK000000',NULL,NULL,1,NULL,NULL,NULL,80,NULL,NULL,1),(69,'CS000002','HP000010','NK000000',NULL,NULL,1,NULL,NULL,NULL,90,NULL,NULL,1),(70,'CS000002','HP000010','NK000000',NULL,NULL,2,NULL,NULL,NULL,100,NULL,NULL,6),(71,'CS000009','HP000001','NK000000',NULL,NULL,2,NULL,NULL,NULL,20000,NULL,NULL,6),(72,'CS000009','HP000002','NK000000',NULL,NULL,1,NULL,NULL,NULL,10,NULL,NULL,6),(73,'CS000009','HP000003','NK000000',NULL,NULL,1,NULL,NULL,NULL,20,NULL,NULL,1),(74,'CS000009','HP000004','NK000000',NULL,NULL,1,NULL,NULL,NULL,30,NULL,NULL,1),(75,'CS000009','HP000005','NK000000',NULL,NULL,1,NULL,NULL,NULL,40,NULL,NULL,1),(76,'CS000009','HP000006','NK000000',NULL,NULL,1,NULL,NULL,NULL,50,NULL,NULL,1),(77,'CS000009','HP000007','NK000000',NULL,NULL,1,NULL,NULL,NULL,60,NULL,NULL,1),(78,'CS000009','HP000008','NK000000',NULL,NULL,1,NULL,NULL,NULL,70,NULL,NULL,1),(79,'CS000009','HP000009','NK000000',NULL,NULL,1,NULL,NULL,NULL,80,NULL,NULL,1),(80,'CS000009','HP000010','NK000000',NULL,NULL,2,NULL,NULL,NULL,90,NULL,NULL,6),(81,'CS000002','HP000001','NK000000',NULL,NULL,2,NULL,NULL,NULL,200000,NULL,NULL,6),(82,'CS000002','HP000002','NK000000',NULL,NULL,1,NULL,NULL,NULL,10,NULL,NULL,1),(83,'CS000009','HP000001','NK000000',NULL,NULL,1,NULL,NULL,NULL,20000,NULL,NULL,1),(84,'CS000009','HP000001','NK000000',NULL,NULL,2,NULL,NULL,NULL,0,NULL,NULL,6),(85,'CS000009','HP000002','NK000000',NULL,NULL,1,NULL,NULL,NULL,10,NULL,NULL,1),(86,'CS000003','HP000000','NK000000','VAT',NULL,NULL,'%',NULL,NULL,10,NULL,NULL,2),(87,'CS000003','HP000000','NK000000','Dịch Vụ',NULL,NULL,'VND',NULL,NULL,10000,NULL,_binary '',1),(88,'CS000003','HP000000','NK000000',NULL,NULL,NULL,'VND',NULL,NULL,33333,NULL,NULL,NULL),(89,'CS000003','HP000000','NK000000','Sức khoẻ',NULL,NULL,'VND',NULL,NULL,10000,NULL,NULL,2),(90,'CS000003','HP000000','NK000000','Thuế',NULL,NULL,'%',NULL,NULL,20,NULL,NULL,2),(91,'CS000008','HP000001','NK000000',NULL,NULL,4,NULL,NULL,NULL,60000,NULL,NULL,6),(92,'CS000001','HP000001','NK000000',NULL,'Giờ',4,'%',NULL,NULL,40,NULL,NULL,1),(93,'CS000002','HP000001','NK000000',NULL,NULL,1,NULL,NULL,NULL,20000,NULL,NULL,1),(94,'CS000002','HP000001','NK000000',NULL,NULL,2,NULL,NULL,NULL,25000,NULL,NULL,6),(95,'CS000009','HP000001','NK000000',NULL,NULL,2,NULL,NULL,NULL,30000,NULL,NULL,6),(96,'CS000008','HP000002','NK000000',NULL,NULL,4,NULL,NULL,NULL,10000,NULL,NULL,6),(97,'CS000008','HP000001','NK000000',NULL,NULL,1,'%',NULL,NULL,10,NULL,NULL,6),(98,'CS000008','HP000001','NK000000',NULL,NULL,3,'%',NULL,NULL,30,NULL,NULL,6),(99,'CS000009','HP000010','NK000000',NULL,NULL,1,NULL,NULL,NULL,90,NULL,NULL,1),(100,'CS000008','HP000002','NK000000',NULL,NULL,2,NULL,NULL,NULL,10000,NULL,NULL,1),(101,'CS000008','HP000002','NK000000',NULL,NULL,4,NULL,NULL,NULL,100,NULL,NULL,1),(102,'CS000005','HP000002','NK000000',NULL,NULL,NULL,NULL,NULL,NULL,40,NULL,NULL,1),(103,'CS000005','HP000001','NK000000',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,1),(104,'CS000005','HP000003','NK000000',NULL,NULL,NULL,NULL,NULL,NULL,20,NULL,NULL,1),(105,'CS000005','HP000004','NK000000',NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,1),(106,'CS000005','HP000005','NK000000',NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,1),(107,'CS000005','HP000007','NK000000',NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,1),(108,'CS000005','HP000008','NK000000',NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,1),(109,'CS000005','HP000009','NK000000',NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,1),(110,'CS000005','HP000010','NK000000',NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,1),(111,'CS000005','HP000011','NK000000',NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,6),(112,'CS000005','HP000012','NK000000',NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,6),(113,'CS000005','HP000013','NK000000',NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,6),(114,'CS000004','HP000000','NK000000',NULL,NULL,30,NULL,NULL,NULL,20,NULL,NULL,1),(115,'CS000004','HP000000','NK000000',NULL,NULL,2,NULL,NULL,NULL,70,NULL,NULL,1),(116,'CS000004','HP000000','NK000000',NULL,NULL,1,NULL,NULL,NULL,90,NULL,NULL,1),(117,'CS000006','HP000000','NK000000',NULL,NULL,1,NULL,NULL,NULL,10000,NULL,NULL,1),(118,'CS000007','HP000000','NK000000',NULL,NULL,100000,NULL,NULL,NULL,2,NULL,NULL,1),(119,'CS000003','HP000000','NK000000','VAT',NULL,NULL,'VND',NULL,NULL,0,NULL,NULL,6),(120,'CS000003','HP000000','NK000000','ád',NULL,NULL,'VND',NULL,NULL,0,NULL,NULL,6),(121,'CS000008','HP000001','NK000000',NULL,NULL,1,NULL,NULL,NULL,9,NULL,NULL,6),(122,'CS000008','HP000016','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,1),(123,'CS000008','HP000017','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,1),(124,'CS000008','HP000018','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,1),(125,'CS000008','HP000019','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,1),(126,'CS000008','HP000020','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,1),(127,'CS000001','HP000016','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,1),(128,'CS000001','HP000017','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,1),(129,'CS000001','HP000018','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,1),(130,'CS000001','HP000019','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,1),(131,'CS000001','HP000020','NK000000',NULL,NULL,1,NULL,NULL,NULL,0,NULL,NULL,1),(132,'CS000008','HP000001','NK000000',NULL,NULL,1,NULL,NULL,NULL,11,NULL,NULL,1),(133,'CS000008','HP000001','NK000000',NULL,NULL,2,NULL,NULL,NULL,9,NULL,NULL,1),(134,'CS000005','HP000016','NK000000',NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,1),(135,'CS000005','HP000017','NK000000',NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,1),(136,'CS000005','HP000018','NK000000',NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,1),(137,'CS000005','HP000019','NK000000',NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,1),(138,'CS000005','HP000020','NK000000',NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,1),(139,'CS000004','HP000000','NK000000',NULL,NULL,5,NULL,NULL,NULL,0,NULL,NULL,1);
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
INSERT INTO `price_list` VALUES ('BG000000','Default',NULL,NULL,NULL,NULL),('BG000001','tet trung thu 3','2023-05-02 00:00:00','2023-08-03 00:00:00',1,'string 1'),('BG000002','Theo thứ','2023-11-16 00:00:00','2024-11-16 00:00:00',1,'Bảng giá theo từng thứ trong tuần'),('BG000003','Theo thứ','2023-11-29 00:00:00','2024-11-29 00:00:00',1,'ok'),('BG000004','Theo thứ','2023-11-08 00:00:00','2024-11-29 00:00:00',1,'sd'),('BG000005','Theo thứ','2023-11-08 00:00:00','2024-11-29 00:00:00',1,'sd'),('BG000006','Theo thứ','2023-11-08 00:00:00','2024-11-29 00:00:00',1,'sd');
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recent_activity`
--

LOCK TABLES `recent_activity` WRITE;
/*!40000 ALTER TABLE `recent_activity` DISABLE KEYS */;
INSERT INTO `recent_activity` VALUES (1,'Khánh','tạo hóa đơn',11999000,'2023-11-21 20:30:00'),(2,'Khánh','tạo hóa đơn',14375000,'2023-11-21 21:31:00'),(3,'admin','thực hiện kiểm kho',0,'2023-11-24 16:45:51'),(4,'Tien','tạo hóa đơn',10000000,'2023-11-24 20:00:00'),(5,'admin','thực hiện kiểm kho',0,'2023-11-27 14:09:02'),(6,'admin','thực hiện kiểm kho',0,'2023-11-27 14:31:50'),(7,'tien','tạo hóa đơn',200,'2023-11-27 22:10:35'),(8,'tien','tạo hóa đơn',200,'2023-11-27 22:11:20'),(9,'tien','tạo hóa đơn',200,'2023-11-27 22:13:40'),(10,'tien','tạo hóa đơn',200,'2023-11-27 22:16:25'),(11,'tien','tạo hóa đơn',200,'2023-11-27 22:17:24'),(12,'tien','tạo hóa đơn',200,'2023-11-27 22:21:06'),(13,'tien','tạo hóa đơn',200,'2023-11-27 22:23:28'),(14,'tien','tạo hóa đơn',200,'2023-11-27 22:24:10'),(15,'tien','tạo hóa đơn',200,'2023-11-27 22:29:53'),(16,'tien','tạo hóa đơn',200,'2023-11-27 22:31:17'),(17,'tien','tạo hóa đơn',200,'2023-11-27 22:32:40'),(18,'tien','tạo hóa đơn',200,'2023-11-27 22:34:01'),(19,'tien','tạo hóa đơn',200,'2023-11-27 22:37:09'),(20,'tien','tạo hóa đơn',200,'2023-11-27 22:39:29');
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_revenue`
--

LOCK TABLES `report_revenue` WRITE;
/*!40000 ALTER TABLE `report_revenue` DISABLE KEYS */;
INSERT INTO `report_revenue` VALUES (2,'2023-11-26 14:30:00',10000000),(3,'2023-11-19 14:30:00',5000000),(4,'2023-11-25 14:30:00',2500000),(5,'2023-11-18 14:30:00',1000000),(9,'2023-11-30 23:59:59',0),(10,'2023-12-01 23:59:59',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_room_capacity`
--

LOCK TABLES `report_room_capacity` WRITE;
/*!40000 ALTER TABLE `report_room_capacity` DISABLE KEYS */;
INSERT INTO `report_room_capacity` VALUES (1,'2023-11-11 14:21:18',27),(2,'2023-11-12 14:25:03',56),(3,'2023-11-13 14:27:41',48),(4,'2023-11-14 14:30:41',11),(5,'2023-11-15 15:07:53',19.43),(6,'2023-11-16 16:02:14',10.167),(7,'2023-11-17 16:03:57',10.167),(8,'2023-11-18 16:04:18',1.0167),(9,'2023-11-19 16:06:00',10.9167),(10,'2023-11-20 17:09:11',0.583333),(11,'2023-11-21 17:10:02',58.3333),(18,'2023-11-22 23:59:01',5.83333),(21,'2023-11-23 23:59:02',10),(22,'2023-11-24 23:59:02',10),(26,'2023-11-25 23:59:02',10),(32,'2023-11-26 20:18:05',10),(35,'2023-12-26 20:18:05',11),(36,'2023-12-27 20:18:05',19),(37,'2023-12-19 20:00:00',19),(38,'2023-11-26 23:59:02',10),(39,'2023-11-27 00:05:48',10),(40,'2023-11-27 23:58:59',7.14286),(41,'2023-11-27 23:58:59',7.14286),(42,'2023-11-28 07:00:00',7.14286),(43,'2023-11-29 00:21:37',16.0333),(44,'2023-11-30 00:32:33',21.4286),(45,'2023-11-30 23:58:59',21.4286),(46,'2023-12-01 23:58:59',21.4286);
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
                               `paid_method` varchar(50) DEFAULT NULL,
                               `transaction_code` varchar(50) DEFAULT NULL,
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
INSERT INTO `reservation` VALUES ('DP000000','C000000','BG000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DP000001','C000000','BG000000',6,5,'CHECK_IN',100,200,NULL,NULL,'2023-11-03 17:00:00','2023-11-30 12:00:56','Ok',2,'2023-11-01 14:30:00',1,1),('DP000002','C000001','BG000001',5,5,'CHECK_IN',100,200,NULL,NULL,'2023-11-03 18:00:00','2023-11-04 18:00:00','ok',NULL,'2023-11-01 14:30:00',1,NULL),('DP000003','C000002','BG000001',5,5,'BOOKING',0,0,NULL,NULL,'2023-11-04 14:11:37','2023-11-05 14:11:37','Lưu tạm',1,'2023-11-03 21:28:20',1,NULL),('DP000004','C000001','BG000001',5,5,'BOOKING',1,1,NULL,NULL,'2023-12-04 14:11:37','2023-12-05 14:11:37','ok',1,'2023-11-03 21:28:20',1,NULL),('DP000005','C000000','BG000000',NULL,NULL,'BOOKING',0,900000,NULL,NULL,NULL,NULL,NULL,NULL,'2023-11-24 23:54:15',2,NULL),('DP000006','C000000','BG000000',NULL,NULL,'BOOKING',0,0,NULL,NULL,NULL,NULL,NULL,NULL,'2023-11-25 13:19:46',2,NULL),('DP000007','C000010','BG000000',NULL,NULL,'BOOKING',0,0,NULL,NULL,'2023-11-28 01:10:59','2023-11-28 15:16:35',NULL,NULL,'2023-11-27 23:10:23',2,NULL),('DP000008','C000000','BG000000',NULL,NULL,'BOOKING',0,0,NULL,NULL,'2023-11-28 20:03:11','2023-11-30 12:00:40',NULL,NULL,'2023-11-27 23:16:49',2,NULL),('DP000009','C000000','BG000000',NULL,NULL,'BOOKING',0,0,NULL,NULL,'2023-11-28 01:17:09','2023-11-28 10:36:56',NULL,NULL,'2023-11-27 23:19:04',2,NULL),('DP000010','C000000','BG000000',0,0,'BOOKING',0,0,NULL,NULL,'2023-11-28 14:00:19','2023-11-29 12:00:19',NULL,NULL,'2023-11-28 13:22:29',10,NULL),('DP000011','C000000','BG000000',NULL,NULL,'BOOKING',0,0,NULL,NULL,'2023-11-28 14:00:28','2023-11-29 12:00:28',NULL,NULL,'2023-11-28 15:29:40',2,NULL),('DP000012','C000000','BG000000',NULL,NULL,'BOOKING',0,0,NULL,NULL,'2023-11-28 14:00:48','2023-11-29 12:00:48',NULL,NULL,'2023-11-28 15:30:54',2,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation_detail`
--

LOCK TABLES `reservation_detail` WRITE;
/*!40000 ALTER TABLE `reservation_detail` DISABLE KEYS */;
INSERT INTO `reservation_detail` VALUES (0,'DP000000','P000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1,'DP000001','P000001','2023-11-03 17:00:00','2023-11-04 17:00:00','2023-11-03 17:00:00','2023-11-22 14:00:00',5700000,'DAILY','CHECK_OUT',1),(2,'DP000001','P000002','2023-11-27 22:00:35','2023-11-30 09:00:35','2023-11-29 18:35:08','2023-11-04 17:00:00',300000,'OVERNIGHT','CHECK_IN',1),(3,'DP000002','P000003','2023-11-03 18:00:00','2023-11-04 18:00:00','2023-11-03 18:00:00','2023-11-04 17:00:00',100,'DAILY','CHECK_OUT',1),(4,'DP000003','P000005','2023-11-04 14:11:37','2023-11-05 14:11:37',NULL,NULL,0,'DAILY','BOOKING',1),(5,'DP000004','P000001','2023-12-04 14:11:37','2023-12-05 14:11:37',NULL,NULL,1,'DAILY','BOOKING',1),(17,'DP000001','P000007','2023-11-27 14:00:25','2023-11-29 14:00:25',NULL,NULL,600000,'DAILY','BOOKING',1),(28,'DP000001','P000009','2023-11-11 14:00:19','2023-11-12 12:00:19',NULL,NULL,1200000,'DAILY','BOOKING',6),(29,'DP000001','P000009','2023-11-11 14:00:19','2023-11-12 12:00:19',NULL,NULL,1200000,'DAILY','BOOKING',6),(30,'DP000001','P000001','2023-11-19 14:00:53','2023-11-20 12:00:53',NULL,NULL,300000,'DAILY','BOOKING',6),(31,'DP000001','P000009','2023-11-22 23:00:00','2023-11-24 04:00:00','2023-11-22 23:00:00',NULL,5800000,'HOURLY','CHECK_IN',1),(32,'DP000001','P000001','2023-11-23 14:00:25','2023-11-24 12:00:25',NULL,NULL,300000,'DAILY','BOOKING',6),(33,'DP000001','P000009','2023-11-24 14:00:34','2023-11-25 12:00:34',NULL,NULL,1200000,'DAILY','BOOKING',6),(34,'DP000001','P000007','2023-11-24 11:00:22','2023-11-25 12:00:22',NULL,NULL,900000,'DAILY','BOOKING',6),(37,'DP000006','P000005','2023-11-25 14:00:30','2023-11-27 12:00:30',NULL,NULL,400000,'DAILY','BOOKING',1),(38,'DP000007','P000009','2023-11-28 00:35:10','2023-11-28 01:35:10','2023-11-28 01:10:59','2023-11-28 15:16:35',1200000,'HOURLY','CHECK_OUT',1),(39,'DP000008','P000010','2023-11-27 14:00:40','2023-11-30 12:00:40','2023-11-28 20:03:11',NULL,1200000,'DAILY','CHECK_IN',1),(40,'DP000009','P000016','2023-11-28 01:17:09','2023-11-28 04:17:09','2023-11-28 01:17:09','2023-11-28 10:36:56',100000,'HOURLY','CHECK_OUT',1),(41,'DP000010','P000005','2023-11-28 14:00:19','2023-11-29 12:00:19',NULL,NULL,400000,'DAILY','BOOKING',1),(42,'DP000001','P000006','2023-11-17 14:00:56','2023-11-30 12:00:56',NULL,NULL,3900000,'DAILY','BOOKING',1),(43,'DP000011','P000009','2023-11-28 14:00:28','2023-11-29 12:00:28',NULL,NULL,1200000,'DAILY','BOOKING',1),(44,'DP000012','P000016','2023-11-28 14:00:48','2023-11-29 12:00:48',NULL,NULL,100000,'DAILY','BOOKING',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation_detail_customer`
--

LOCK TABLES `reservation_detail_customer` WRITE;
/*!40000 ALTER TABLE `reservation_detail_customer` DISABLE KEYS */;
INSERT INTO `reservation_detail_customer` VALUES (0,0,'C000000'),(1,1,'C000001'),(2,2,'C000001'),(3,3,'C000001'),(4,4,'C000002'),(5,4,'C000003'),(9,1,'C000010'),(20,17,'C000021'),(21,17,'C000022'),(22,17,'C000023');
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
INSERT INTO `room` VALUES ('P000000','Sảnh','HP000000',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('P000001','P.101','HP000001',1,2,'ROOM_USING','ROOM_CLEAN',NULL,'Phòng cơ bản',NULL,NULL,'2023-10-15 17:00:00','2023-11-26 16:27:56'),('P000002','P.102','HP000001',1,1,'ROOM_USING','ROOM_UNCLEAN',NULL,'Phòng cơ bản',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000003','P.201','HP000002',2,1,'ROOM_USING','ROOM_CLEAN',NULL,'Phòng hạng trung',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000004','P.202','HP000002',2,1,'ROOM_EMPTY','ROOM_CLEAN',NULL,'Phòng hạng trung',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000005','P.301','HP000003',3,1,'ROOM_EMPTY','ROOM_CLEAN',NULL,'Phòng đơn',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000006','P.302','HP000003',3,1,'ROOM_EMPTY','ROOM_CLEAN',NULL,'Phòng đơn',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000007','P.401','HP000004',4,1,'ROOM_EMPTY','ROOM_CLEAN',NULL,'Phòng gia đình',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000008','P.402','HP000004',4,1,'ROOM_EMPTY','ROOM_CLEAN',NULL,'Phòng gia đình',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000009','P.501','HP000005',5,1,'ROOM_USING','ROOM_CLEAN',NULL,'Phòng VIP',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000010','P.502','HP000005',5,1,'ROOM_USING','ROOM_CLEAN',NULL,'Phòng VIP',NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('P000011','test 12','HP000001',1,6,'ROOM_EMPTY','ROOM_CLEAN',NULL,'',NULL,NULL,'2023-11-26 16:21:27',NULL),('P000012','test 123','HP000001',1,1,'ROOM_EMPTY','ROOM_CLEAN',NULL,'',NULL,NULL,'2023-11-26 17:19:45','2023-11-27 00:45:15'),('P000013','P.103','HP000001',1,1,'ROOM_EMPTY','ROOM_CLEAN',NULL,'',NULL,NULL,'2023-11-27 00:44:47',NULL),('P000014','test','HP000001',1,1,'ROOM_EMPTY','ROOM_CLEAN',NULL,'',NULL,NULL,'2023-11-27 00:55:15',NULL),('P000015','test 1','HP000019',1,1,'ROOM_EMPTY','ROOM_CLEAN',NULL,'',NULL,NULL,'2023-11-27 00:57:13','2023-11-27 01:00:06'),('P000016','test 2','HP000020',5,1,'ROOM_USING','ROOM_CLEAN',NULL,'',NULL,NULL,'2023-11-27 01:35:43',NULL);
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
INSERT INTO `room_category` VALUES ('HP000000','Sảnh',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('HP000001','Phòng cơ bản',300000,300000,200000,2,1,3,2,20,2,'Phòng cơ bản',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-11-24 15:43:42'),('HP000002','Phòng hạng trung',700000,300000,200000,3,1,4,2,30,2,'Phòng hạng trung',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-11-26 14:55:37'),('HP000003','Phòng đơn',400000,400000,250000,1,1,2,2,18,1,'Phòng đơn',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-11-18 01:25:04'),('HP000004','Phòng gia đình',900000,500000,300000,4,1,5,2,40,1,'Phòng gia đình',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('HP000005','Phòng VIP',1200000,800000,500000,2,1,3,2,25,1,'Phòng VIP',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('HP000006','Phòng Suite',1500000,1000000,600000,2,1,3,2,30,6,'Phòng Suite',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('HP000007','Phòng VIP2',2000000,1500000,800000,6,1,7,2,70,1,'Phòng VIP2',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('HP000008','Phòng Ocean View',800000,500000,350000,3,1,3,1,35,1,'Phòng với view biển',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('HP000009','Phòng Deluxe Suite',1800000,1200000,700000,2,1,3,2,35,1,'Phòng Deluxe Suite',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('HP000010','Phòng Tổng Thống',3000000,2000000,1000000,2,1,3,2,40,1,'Phòng Phủng',NULL,NULL,NULL,'2023-10-15 17:00:00','2023-10-15 17:00:00'),('HP000011','test 1',40,30,10,0,0,0,0,1,6,'',NULL,NULL,NULL,'2023-11-19 16:59:19','2023-11-26 14:45:55'),('HP000012',' ',0,0,0,0,0,0,0,1,6,'',NULL,NULL,NULL,'2023-11-19 16:59:57',NULL),('HP000013',' ',1,1,1,1,1,2,2,1,6,'',NULL,NULL,NULL,'2023-11-23 22:52:01',NULL),('HP000014','test 1',0,0,0,0,0,0,0,1,6,'',NULL,NULL,NULL,'2023-11-24 15:45:18',NULL),('HP000015','test 2',0,0,0,0,0,0,0,1,6,'',NULL,NULL,NULL,'2023-11-26 14:27:24',NULL),('HP000016','Phòng Tổng Thống 1',0,0,0,0,0,0,0,1,1,'',NULL,NULL,NULL,'2023-11-26 22:01:32','2023-11-26 23:03:42'),('HP000017','Phòng Tổng Thống 2',0,0,0,0,0,0,0,1,1,'',NULL,NULL,NULL,'2023-11-27 00:41:05',NULL),('HP000018','Phòng Tổng Thống 4',0,0,0,NULL,NULL,NULL,NULL,1,1,'',NULL,NULL,NULL,'2023-11-27 00:56:55',NULL),('HP000019','Hehe',0,0,0,NULL,NULL,NULL,NULL,1,1,'',NULL,NULL,NULL,'2023-11-27 00:57:02',NULL),('HP000020','123',100000,100000,20000,2,2,2,2,1,1,'',NULL,NULL,NULL,'2023-11-27 01:03:43','2023-11-27 01:34:44');
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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'df','df',NULL,'ROLE_MANAGER','NO_ACTIVE','2023-11-03 00:00:00','q','123123',_binary '\0','2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'PB000001'),(2,NULL,'tien','$2a$10$ato6VNKwexGRQqMkV1pH2Os187zIP88S98kS55hXMYYoUY2xJAI0.','ROLE_MANAGER',NULL,NULL,'w','123',NULL,'3',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'PB000001'),(3,'Le Minh Nhat','h','$2a$10$ZN/3xo87NBoCB8kNAahbWuJl/Q.Caum4MAcwTxoDcXUAbigHzDEty','ROLE_MANAGER','ACTIVE','2023-11-07 00:00:00','e','tapdoanhhl@gmail.comdd',_binary '\0','sdsdsdas',NULL,'0812569567',NULL,NULL,NULL,NULL,NULL,'PB000001'),(6,NULL,'admin','$2a$10$Z/GE11unhNydjntS/VUD6uG267P0qv9p3jl0L8tu.xf7hp/DAe9Ui','ROLE_MANAGER',NULL,NULL,'y',NULL,NULL,'9',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'PB000001'),(10,'khanh','khanh','$2a$10$UxveZveQROqPxZSR4FWgC.WcBP1npyttwVbpVCc8oBt02DN8398tm','ROLE_MANAGER','ACTIVE','2023-11-15 00:00:00','Số nhà 45B ngõ 46 Phạm Ngọc Thạch','khanhtbhe153468@fpt.edu.vn',_binary '','12344','null','0812569567',NULL,NULL,NULL,NULL,NULL,'PB000002'),(34,'Nguyen Quang Huy','huyhuy','$2a$10$K.YQo4h1be2U8MDqPNJdpuWCW6Nvd6yWu6vzbc0/gIsBHvgoBDU8e','ROLE_MANAGER','ACTIVE','2023-11-06 00:00:00','thạch hòa thạch thất hà nội','tapdoanhhl52@gmail.com',_binary '','123456789','null','0812569567',NULL,NULL,NULL,NULL,NULL,'PB000001'),(38,'Le Huy Duc','',NULL,'ROLE_RECEPTIONIST','ACTIVE','2023-10-31 00:00:00','Số nhà 45B ngõ 46 Phạm Ngọc Thạch','nguyenhuync37@gmail.com',_binary '','123123','null','0812569567',NULL,NULL,NULL,NULL,NULL,'PB000001');
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
INSERT INTO `time_use` VALUES (1,'09:00:00','14:00:00','12:00:00','22:00:00','TIME_LATE',30,7);
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
) ENGINE=InnoDB AUTO_INCREMENT=529 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (1,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4NzczNTYwLCJleHAiOjE2OTg3NzcxNjB9.yRM_MVW1qutFy7rk0tPyTHUue96kLVCzThlJxvMJ5p4',1,'1980-02-01 00:00:00'),(2,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4Nzc3NTIwLCJleHAiOjE2OTg3ODExMjB9.Ci8ZMpn1ZSJdFsa8owpctmqO5hcJ05qfLy3rYgXIblM',1,'1980-02-01 00:00:00'),(3,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4NzgyMDMyLCJleHAiOjE2OTg3ODU2MzJ9.k8Trt4BgQWjlzULbMj7ap0SjRiiiwiCfqhuaRCjUKFE',1,'1980-02-01 00:00:00'),(4,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4Nzg0MTA2LCJleHAiOjE2OTg3ODc3MDZ9.kj1mBjHMED-uphzyblhrc7qWSifVG3IiUcisbjIEqyQ',1,'1980-02-01 00:00:00'),(5,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4ODU0MDAxLCJleHAiOjE2OTg4NTc2MDF9.kdY-Wa7e6ooo6k1f6_-t0JjkQOqJQbdStY07I09RwGE',1,'1980-02-01 00:00:00'),(6,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4ODczNTM0LCJleHAiOjE2OTg4NzcxMzR9.XNFcKOG8ujN-GeyyUfxCu6psJJ6faz9TGmXYz8-m-fI',1,'1980-02-01 00:00:00'),(7,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4ODc0MTE0LCJleHAiOjE2OTg4Nzc3MTR9.1OKwHqewTKSnv4guPZBof7OyNsenV3t3BT7iHnHPk9o',1,'1980-02-01 00:00:00'),(8,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTA0MjM2LCJleHAiOjE2OTg5MDc4MzZ9.75I-cg6c6HXJuBAAbg4zJSWyKj-8Behvmy_cgQT-hzc',1,'1980-02-01 00:00:00'),(9,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTA4NjU1LCJleHAiOjE2OTg5MTIyNTV9.mz3lEyfMIdweWaYKelTwKJ6QlcQeLj9zSOj7FyI6xj0',1,'1980-02-01 00:00:00'),(10,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTEzNDEwLCJleHAiOjE2OTg5MTcwMTB9.azdqECQPtPP4XPR6CieCDu51XS0NbMGvj4KeAhZ2PsY',1,'1980-02-01 00:00:00'),(11,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTE3MDcwLCJleHAiOjE2OTg5MjA2NzB9.ydAJ3bnvkvK6Q2B_EPb-3v3GUm08IVQbyego__BA4qE',1,'1980-02-01 00:00:00'),(12,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTIwODYwLCJleHAiOjE2OTg5MjQ0NjB9.CMAK8UspBY4hnmO3yq4urt066U-UMHLDp1q7FCRc920',1,'1980-02-01 00:00:00'),(13,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTI0ODYxLCJleHAiOjE2OTg5Mjg0NjF9.L-fCED0RVM9UUMSd0PkDw6pP5HCKihg7u5iZs6aXYs8',1,'1980-02-01 00:00:00'),(14,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTI0ODYyLCJleHAiOjE2OTg5Mjg0NjJ9.Ph1DHzbEAeb3qXm7OG-BTpihYwhJbHpQLNpWMwRTITU',1,'1980-02-01 00:00:00'),(15,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTMxMTY2LCJleHAiOjE2OTg5MzQ3NjZ9.RYUoVy0blZckHHg1gOuoqM1RUToxhwMoI1ytrKvSYwM',1,'1980-02-01 00:00:00'),(16,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTM0Mjg3LCJleHAiOjE2OTg5Mzc4ODd9.n4_g0d6q6MW3ZBsJCHsU-qc90OxuEDciKmKCgGQzikg',1,'1980-02-01 00:00:00'),(17,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTM2MDM3LCJleHAiOjE2OTg5Mzk2Mzd9.c-ighL6Xc8rfFTsECQvJnxDatY8KrCiaTC-E2BUQ1ZQ',1,'1980-02-01 00:00:00'),(18,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTM2MDY3LCJleHAiOjE2OTg5Mzk2Njd9.5u4hCOcAcUU66XSXDq4N1GG8Ta5dcpbLI6sfYZO5hC4',1,'1980-02-01 00:00:00'),(19,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTM2MDcyLCJleHAiOjE2OTg5Mzk2NzJ9.6P_kSUvMl50A6aVYoUcjA5aXvS8a4RQWHMT6JaSTGOI',1,'1980-02-01 00:00:00'),(20,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTM2MDg2LCJleHAiOjE2OTg5Mzk2ODZ9.03IrBmHBqZtRo-vBDLVoHDX5AqlH7Z-9ebJ-_Fc3yIU',1,'1980-02-01 00:00:00'),(21,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTM3MDUxLCJleHAiOjE2OTg5NDA2NTF9.zHHH95FB_3JFeMwIglbtoua9CEyL5nFQJslYYyAWiks',1,'1980-02-01 00:00:00'),(22,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTM3ODM4LCJleHAiOjE2OTg5NDE0Mzh9.l5JboIxvr-rq5Wfa77pRM-Ufo5idITLKiXrrLX6VLUI',1,'1980-02-01 00:00:00'),(23,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTM3OTIyLCJleHAiOjE2OTg5NDE1MjJ9.WIVcu8uB39XMS1lsKgPZ2NBIDTGLAC_mPL5Yzw9_pRs',1,'1980-02-01 00:00:00'),(24,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTM4MzQ3LCJleHAiOjE2OTg5NDE5NDd9.iesQf1prXPr-slaZzoKOK-IlxAj_N89tvqci19k4obA',1,'1980-02-01 00:00:00'),(25,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTM4ODAxLCJleHAiOjE2OTg5NDI0MDF9.ZUYrZncYLE_PsrUN5NaMlFWDIDQNCdqUngq0wQI4VE4',1,'1980-02-01 00:00:00'),(26,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk4OTM4ODU3LCJleHAiOjE2OTg5NDI0NTd9.V4ag9EJXxf2ktH3lrNivOSPaWmmT4hQMfh5lMjUHvRs',1,'1980-02-01 00:00:00'),(39,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5MDIwNTI1LCJleHAiOjE2OTkwMjQxMjV9.yWT006Xx9yQmzuy0076zNFGJAK2knSIggzvgyY9DoJ4',1,'1980-02-01 00:00:00'),(40,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5MDIwNzA5LCJleHAiOjE2OTkwMjQzMDl9.O-KfKUxLF3XoWgcMi_Z6QhSSxSstoKpUd7G3WFbtaAQ',1,'1980-02-01 00:00:00'),(44,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTkwMzEzMzksImV4cCI6MTY5OTAzNDkzOX0.N8v6bAkBBS9iZe-3GcheRzuyrtYZOEMP0JFAP4y1EAY',3,'1980-02-01 00:00:00'),(45,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTkwMzIyNDcsImV4cCI6MTY5OTAzNTg0N30.W-bCZguof6Rf7qr3srnwnJt6GTvVyc-DF8Bbb3IkXQM',3,'1980-02-01 00:00:00'),(47,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTkwNDEyMDQsImV4cCI6MTY5OTA0NDgwNH0.YLztsWeewzUW6WLRXFRwgUH_SHT9lcLVoz8Bev12jBA',3,'1980-02-01 00:00:00'),(48,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5MDQxMjA2LCJleHAiOjE2OTkwNDQ4MDZ9.obq2Kdt9ATQ78dXJyy4n004t6WK7eaQ5H0E4eUmw4NE',1,'1980-02-01 00:00:00'),(52,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5MDg5Njg1LCJleHAiOjE2OTkwOTMyODV9.6dCoXOnNmDVduU6oGgBxBXg1k1Qw6w9zPy_gf1Ts7d8',1,'1980-02-01 00:00:00'),(55,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTkwOTc1NjMsImV4cCI6MTY5OTEwMTE2M30.chBNEJ90q3EA7OgRs1AJOCUm7ht6CNytEUaD25a_Pkk',3,'1980-02-01 00:00:00'),(62,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5MTk4NjkwLCJleHAiOjE2OTkyMDIyOTB9.meEzZbmcc0YAGeKzZJ9VnI2OGOQNFQNWUswUWezfjB4',1,'1980-02-01 00:00:00'),(64,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5MjA5NDc0LCJleHAiOjE2OTkyMTMwNzR9.iIZ1iRL4z5PO_JHjbaoM8bygAujPDSJDOhhFE4XyhVI',1,'1980-02-01 00:00:00'),(71,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5MjY2ODM4LCJleHAiOjE2OTkyNzA0Mzh9.aXlksVeUjLSa9__-CzE47wv3wmoE6XzSIW6ZJ2VmSHk',1,'1980-02-01 00:00:00'),(79,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTkzNDQ4MTAsImV4cCI6MTY5OTM0ODQxMH0.eDmonAPZ7Jy-m93yx-wKbN0BXbuEWkEd_esfFgJpFoQ',3,'1980-02-01 00:00:00'),(85,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk0MTk3NDcsImV4cCI6MTY5OTQyMzM0N30.ghqxO33W1iHrOcqXDLMeA4elCD9fNVxVsiOR3Wf1Go0',3,'1980-02-01 00:00:00'),(87,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5NDMxNjI1LCJleHAiOjE2OTk0MzUyMjV9.WTOaZzanglwz3MJl-s-o2CTCjV_zUwoGOHmMnQ91jdA',1,'1980-02-01 00:00:00'),(107,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5NjIyODc4LCJleHAiOjE2OTk2MjY0Nzh9.NJX1w8n1fnGzVOhUUNT0XBA_hAB_tVDJbDdBcs6WUYI',1,'1980-02-01 00:00:00'),(112,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk2Mzk5MjUsImV4cCI6MTY5OTY0MzUyNX0.vWPe7ryMPcpQVwYwylcMW5NSlthuJNP7s3GaF9A05Tw',3,'1980-02-01 00:00:00'),(113,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk2NDM4ODMsImV4cCI6MTY5OTY0NzQ4M30.SiCRcZWBhnPQyeXM7mAvOzj7IWFVoynMeNZRjBUhQMc',3,'1980-02-01 00:00:00'),(116,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk2Njg2MDQsImV4cCI6MTY5OTY3MjIwNH0.KCIT7mORxJnQsnjhcUwUNPWNUTXas2wTNaTzNUNzoo4',3,'1980-02-01 00:00:00'),(122,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5Njk2MzUwLCJleHAiOjE2OTk2OTk5NTB9.XHAyRcjErQqVuhrpL1duzRtH8ugQeqGstwd80XDnjCY',1,'1980-02-01 00:00:00'),(125,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5Njk4NTAzLCJleHAiOjE2OTk3MDIxMDN9.vf95BJwbSWyNDD_kOOg-9dVm0UYiXEmkKCbzfJndxUE',1,'1980-02-01 00:00:00'),(132,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5NzY4OTg1LCJleHAiOjE2OTk3NzI1ODV9.w7ki76HjnmZ0Gv4P8kNhlH4_shmSvni3hPvYJdejWqM',1,'1980-02-01 00:00:00'),(134,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk3NzI1NjMsImV4cCI6MTY5OTc3NjE2M30.YKq2b40c9BncJSsTB2p6djW8wt-P61FBFf_iPOWWyas',3,'1980-02-01 00:00:00'),(136,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk3NzQ4NTQsImV4cCI6MTY5OTc3ODQ1NH0.6jzP3QHZ7Vzx1oQO3THOjAwjYSTtejENkgWrl9936fY',3,'1980-02-01 00:00:00'),(137,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5Nzc0OTgxLCJleHAiOjE2OTk3Nzg1ODF9.AIbyBee9QslZRMxXvaxqavAZbztG04JclabCEQh69vY',1,'1980-02-01 00:00:00'),(139,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5Nzg1NjQ3LCJleHAiOjE2OTk3ODkyNDd9.mBFUQRq9LpxX9jR5zZtDI72WUxR654M5oRHidEr9G6M',1,'1980-02-01 00:00:00'),(141,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5Nzg1ODEyLCJleHAiOjE2OTk3ODk0MTJ9.CSXJQa1lvzKTXXBR7ZPsacmyCQsgaW1LbNhMz1iNxic',1,'1980-02-01 00:00:00'),(147,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5ODEwMzg2LCJleHAiOjE2OTk4MTM5ODZ9.5ILqpPmcIX-dKo9iYvpqA0ZSHd8KAKDx6MuAY2nnyU8',1,'2023-11-13 00:38:07'),(152,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk4MTczMDQsImV4cCI6MTY5OTgyMDkwNH0.pMV9hyOEBmVRVqbFIjzHgbz5fz712u4IldBIJ6_XkD8',3,'1980-02-01 00:00:00'),(153,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk4MTg1ODMsImV4cCI6MTY5OTgyMjE4M30.ownJt_jfTCPLDZybITS3x8rMLoCzZpWSO20Rnu3nfn0',3,'1980-02-01 00:00:00'),(155,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk4NTQ5ODIsImV4cCI6MTY5OTg1ODU4Mn0.UI_eF1D4xp-vR3keibBFVkCgPRCuWbNB6Pe-Rlb0fTM',3,'1980-02-01 00:00:00'),(165,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5ODg3MDY5LCJleHAiOjE2OTk4OTA2Njl9.qkMZ4hzdaMoWIdwjtHTnIqCVo9vMc0Ye9OebXT6Wfzo',6,'1980-02-01 00:00:00'),(169,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk5Mjk1NTMsImV4cCI6MTY5OTkzMzE1M30.5KLAdIGTNbMcO_RQMWOSCwpI6TtKJ3tHRltvbFUBlpw',3,'1980-02-01 00:00:00'),(170,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk5MzEwNzYsImV4cCI6MTY5OTkzNDY3Nn0._9NSqK8Gs0c2VNAdZElKjGKUI9kzA_alzZe0Z-XeHl0',3,'1980-02-01 00:00:00'),(173,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk5NTE5ODMsImV4cCI6MTY5OTk1NTU4M30._lQxduOEch0loSAkiCEKV6GyYE_4dfWw4sPvoAEzvMU',3,'1980-02-01 00:00:00'),(175,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk5NjIwNTEsImV4cCI6MTY5OTk2NTY1MX0.NPHSNb4JAOeTN3Vnr06Z7GfaCrTW2RywMnAR1PV7zEA',3,'1980-02-01 00:00:00'),(176,'9f584d17-35ab-4315-92d4-af1a719971a8',3,'1980-02-01 00:00:00'),(177,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk5NjIxNTcsImV4cCI6MTY5OTk2NTc1N30.-bG_LsWVekBMc6u4kCK8SqWnYD2q8IO9LT7P2hKzoB8',3,'1980-02-01 00:00:00'),(178,'343f12d6-5984-4d2d-9459-cc917b772431',3,'1980-02-01 00:00:00'),(179,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk5NjIzMzcsImV4cCI6MTY5OTk2NTkzN30.RNypIAffMkxv-rjL0FL4jUHQjSYR3a1JYZkcpKaxb0Y',3,'1980-02-01 00:00:00'),(180,'a0b49473-6a35-4398-88c1-1def767ea885',3,'1980-02-01 00:00:00'),(182,'2675d2b6-4b5d-4257-b643-5f6fdeaa80f4',3,'1980-02-01 00:00:00'),(183,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk5NzAyNzAsImV4cCI6MTY5OTk3Mzg3MH0.E4vj2bbtBGTs20wPblaSqvnDzO_Ilw1DsqgqAzxfULI',3,'1980-02-01 00:00:00'),(189,'b1cb7ab0-dd42-4f9d-8dee-d1e2e6b4e612',3,'1980-02-01 00:00:00'),(190,'464ad6f4-b105-4420-b2df-96b0d7ef4d87',3,'1980-02-01 00:00:00'),(192,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5OTc0MjEwLCJleHAiOjE2OTk5Nzc4MTB9.8y_n0vs1yofldz5QDqeJyscnqBiGWYiTUh2bLkFxDKE',6,'1980-02-01 00:00:00'),(194,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE2OTk5Nzg1NTgsImV4cCI6MTY5OTk4MjE1OH0.RUXqMdUAjlsFyTSMqIPhwJSnCWwoRsrcSz8mVQb5AyQ',3,'1980-02-01 00:00:00'),(195,'53db3224-b939-4092-995c-6a4ccb623c10',3,'1980-02-01 00:00:00'),(196,'50e36efc-65d0-4813-9b34-3fa0cb428137',3,'1980-02-01 00:00:00'),(197,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNjk5OTgwMjI5LCJleHAiOjE2OTk5ODM4Mjl9.Ma3AeF1USYrKndCOhjbcInvZJc1_iAvZHW5f03Gd7-k',6,'1980-02-01 00:00:00'),(202,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAwMzUzMzIsImV4cCI6MTcwMDAzODkzMn0.jfuo1Scn-00Y9tn7JlgVssznMq5vVxoo6qzZppYhxFM',3,'1980-02-01 00:00:00'),(209,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwMDU5NjMzLCJleHAiOjE3MDAwNjMyMzN9.LGdx9uG0Zy5GOFUKXAYDUo49fJIRRNZGbExBF5Vp1ZU',6,'1980-02-01 00:00:00'),(210,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwMDYwNTM0LCJleHAiOjE3MDAwNjQxMzR9.lXDb7mhjoX5LWifTXa8eN6-vdV2A9mypagu-6Fh22HM',6,'1980-02-01 00:00:00'),(211,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAwNzU2NDUsImV4cCI6MTcwMDA3OTI0NX0.VGQkIXOmX89tacjh-khtINbhaiaBayYDpm5wgUCLYCM',3,'1980-02-01 00:00:00'),(212,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAwNzU3OTIsImV4cCI6MTcwMDA3OTM5Mn0.sCBQBl-D1EnfLngQEy-l4_fMSTR4WLlc776zh9nzup8',3,'1980-02-01 00:00:00'),(213,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAwNzU4MjYsImV4cCI6MTcwMDA3OTQyNn0.p1riMJV7EBGXpMOEQqdbkKWDD59BPm1Hrn5uJLBlVZg',3,'1980-02-01 00:00:00'),(216,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMDkwMTAsImV4cCI6MTcwMDExMjYxMH0.hlgY8Pk-diu9WNmDCrdksMEq-1VimSpnd0q9jcH9B6w',3,'1980-02-01 00:00:00'),(217,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMjY3MTIsImV4cCI6MTcwMDEzMDMxMn0.CdMR_nCAogGGiL-EAtFcgyJLWHwHyPzucdZUYz4k4bg',3,'1980-02-01 00:00:00'),(218,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwMTI4MjEwLCJleHAiOjE3MDAxMzE4MTB9.gKydiYTZeD1mpLqMl3v8OJaB-m2ybOoBniNjhGY8Fcg',6,'1980-02-01 00:00:00'),(219,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMjgyNDksImV4cCI6MTcwMDEzMTg0OX0.Wv9KnHOfEuVyMsxb4E9w1hyrclqUmP8mi3v2Yrg_MAU',3,'1980-02-01 00:00:00'),(220,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMjgyODQsImV4cCI6MTcwMDEzMTg4NH0.WZw5LeEUJywvSHrfgZf0DX1wvIy6BDbWLwBdqG1Kenk',3,'1980-02-01 00:00:00'),(221,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMjgzNDIsImV4cCI6MTcwMDEzMTk0Mn0.Rc0VjemTH0_bSwUvzslVu1AL7OY5N-4Xa-z07Ls65Xs',3,'1980-02-01 00:00:00'),(222,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMjgzODQsImV4cCI6MTcwMDEzMTk4NH0.vFCqHJsDs56GnLt0Q29DyepsBreg3JopKuE_y7rnulQ',3,'1980-02-01 00:00:00'),(223,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMjg0MzAsImV4cCI6MTcwMDEzMjAzMH0.h1hJJi8Y9F7dgd8nZH64B5O05ISjH1Ub6o7ZXp2lnv8',3,'1980-02-01 00:00:00'),(224,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMjg1MDEsImV4cCI6MTcwMDEzMjEwMX0.rn--Z1pUdIXlicwTIj0kvdF2-gBVPJV-IdqJ8uR6P54',3,'1980-02-01 00:00:00'),(226,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMjg3OTEsImV4cCI6MTcwMDEzMjM5MX0.8le_ilk2ssV543bFjH-zLDxGMtWXfWjT7d4SXGgbOBc',3,'1980-02-01 00:00:00'),(227,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMjg4NTYsImV4cCI6MTcwMDEzMjQ1Nn0.FtEHWHABkFnqgmXdkLJoQv3pkRhqbzdsJEuu3a9B5Qc',3,'1980-02-01 00:00:00'),(228,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMjg5MTMsImV4cCI6MTcwMDEzMjUxM30.MdHi3ovSJa1OTg7Ho8IWmnibTNL3y-hwZicI1nzFJvE',3,'1980-02-01 00:00:00'),(229,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMjkyODUsImV4cCI6MTcwMDEzMjg4NX0.JCYGWOut88g6M9tR_nWn8a88INMjwb2xAe7d14LL8zs',3,'1980-02-01 00:00:00'),(230,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMjkzNDQsImV4cCI6MTcwMDEzMjk0NH0.ZEDbxs5ORSq5_LIWme8Y8jQlRAHE2BQLN3HvSK3wDXs',3,'1980-02-01 00:00:00'),(231,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMjkzOTQsImV4cCI6MTcwMDEzMjk5NH0.XvXQS2UIqBwNI97H5QoiOBDxRtr7Y9CeLE-Wm_md6sU',3,'1980-02-01 00:00:00'),(232,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDAxMjk0NTMsImV4cCI6MTcwMDEzMzA1M30.74lvHba5-DFLLd02SpLlX6a11vPXFsR_Y0HRqVIOhRo',3,'1980-02-01 00:00:00'),(236,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwMTQ3OTM0LCJleHAiOjE3MDAxNTE1MzR9.DmaJb3G1xA0ICcZDxvKaC2DIG4oc3p2HC0Awra5a3Hk',6,'1980-02-01 00:00:00'),(245,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwMzM2MTQzLCJleHAiOjE3MDAzMzk3NDN9.pyd3YuetxcXW0Tj5is-UOqAfSYEXgIAdrVmhyb2YnLY',6,'1980-02-01 00:00:00'),(246,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwMzQxNDgzLCJleHAiOjE3MDAzNDUwODN9.KVHdyb_tN41Flpc5S0WiF7aQmVP9whJ7G2P2CDcSsac',6,'1980-02-01 00:00:00'),(247,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwMzQyMDU4LCJleHAiOjE3MDAzNDU2NTh9.L2Xm0uQfrmRs2h67wf5ufVCJiBUHORNQngBflXbWBAk',6,'1980-02-01 00:00:00'),(248,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwMzQyMDk1LCJleHAiOjE3MDAzNDU2OTV9.-h6w-aGYstu_IMVWGUleJTzkleGPHiv9j1AMJwW8qFE',6,'1980-02-01 00:00:00'),(249,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwMzg2NzQ0LCJleHAiOjE3MDAzOTAzNDR9.Ao7XzBTNnNP0Ib7JhS2IN4yrP0GfBjqvkTdnujk3KpM',6,'1980-02-01 00:00:00'),(250,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwMzg3ODY5LCJleHAiOjE3MDAzOTE0Njl9.7NhVRp_1L0HVMVJizcR2gPxxobkE5tjCUfnkjzJily0',6,'1980-02-01 00:00:00'),(255,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNDA1MzQwLCJleHAiOjE3MDA0MDg5NDB9.XQzbQxzNGEtoDQHs_Anz979LaHjlMQ6BYSc2C9MEMFA',6,'1980-02-01 00:00:00'),(267,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNDkyMTg2LCJleHAiOjE3MDA0OTU3ODZ9.ueudr2rltdJQhViNFUCI5QdriWd0SS_6PNtYyTiaqKE',6,'1980-02-01 00:00:00'),(271,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA1NDc0OTcsImV4cCI6MTcwMDU1MTA5N30.kxqvkB6cXtZiQiA7Hi3JAFo6AixbhPJafO_nIXQ3eAA',3,'1980-02-01 00:00:00'),(273,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA1NTMyNjUsImV4cCI6MTcwMDU1Njg2NX0.CM7L6MBhfw5fE2x0zIC9JxEdUMPBVK4AhjXio32Nvn0',3,'1980-02-01 00:00:00'),(274,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNTU3NjI5LCJleHAiOjE3MDA1NjEyMjl9.ZtiP39JF0-BmZ-zI-a_0313WFuE9taJkF-7-R7Bq1sY',6,'1980-02-01 00:00:00'),(275,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA1NTc3NTQsImV4cCI6MTcwMDU2MTM1NH0.ZjWgYI8h-v6y4Z4oYt5sS8axVeuS7qMGwdgtEyjJeic',3,'1980-02-01 00:00:00'),(280,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNTczMDExLCJleHAiOjE3MDA1NzY2MTF9.tzo5hRFFLjRLzBOdn3g9rYYZaO_6MsgALCndEMDZzMQ',6,'1980-02-01 00:00:00'),(282,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA1NzUxMTcsImV4cCI6MTcwMDU3ODcxN30.m4jW_Bic6fWlaslAjhN5SLrhH7A9MY7gvAZY9kv-hUM',3,'1980-02-01 00:00:00'),(285,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNTc5OTM4LCJleHAiOjE3MDA1ODM1Mzh9.wazF9GZcxOwBulmgJrk0LrB9xvyJXlEK2JWGVPdFjTE',6,'1980-02-01 00:00:00'),(286,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNTgwMzAwLCJleHAiOjE3MDA1ODM5MDB9.oyPfwMfmE0UgrjFlDsL_WMMBaoPARNnY6ugadSMAdoE',6,'1980-02-01 00:00:00'),(288,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNTgwNDA3LCJleHAiOjE3MDA1ODQwMDd9.APMif8oIEAaP1-97Css_cx669qmnqqkE7ZDMYk_z_iA',6,'1980-02-01 00:00:00'),(289,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNTgwOTM4LCJleHAiOjE3MDA1ODQ1Mzh9.7UQXYpFRJAnYUGkQ-D1iTHrVUpmpbqqDBfJzMQU_Dl0',6,'1980-02-01 00:00:00'),(291,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNTgxMTQyLCJleHAiOjE3MDA1ODQ3NDJ9.kqXRkZVIYH40puv8RFpr6PWy_aLOKTVY-p65XkU0sZM',6,'1980-02-01 00:00:00'),(294,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNTg1MDk0LCJleHAiOjE3MDA1ODg2OTR9.KIlfDQWorgW1fwqwrU1HdU9IjDY_VG_E7BwuhrD4JXE',6,'1980-02-01 00:00:00'),(295,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNTg2MzE1LCJleHAiOjE3MDA1ODk5MTV9.YHOAN6HUBHJHD-SZHsd5MWeHd-VCo2s8JKxpAG36Rrs',6,'1980-02-01 00:00:00'),(296,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA1ODg0MzgsImV4cCI6MTcwMDU5MjAzOH0.eVLCIRXESy0Scr-p85WdVy81zrES-4hgDwEyJEleK4w',3,'1980-02-01 00:00:00'),(297,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA1ODkyNzksImV4cCI6MTcwMDU5Mjg3OX0.P_lPVpWzTZMn1LXyzayQdI7B1-_SrDUfLLyahw_JTn4',3,'1980-02-01 00:00:00'),(298,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA1OTAxMjIsImV4cCI6MTcwMDU5MzcyMn0.1EPafw2U8jdEO7HNYt7GySfhRwkKNDjQV7p4i6ICXI8',3,'1980-02-01 00:00:00'),(299,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA1OTMwMjIsImV4cCI6MTcwMDU5NjYyMn0.l2bYpAajtYR9vUNcLrbexzIvAM3c1psabh11PB7yxJU',3,'1980-02-01 00:00:00'),(300,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA1OTQxMjksImV4cCI6MTcwMDU5NzcyOX0.bSlARSS7e0utg5ZLPho8mHTA0tlhvjAYXGjoI6_TMp8',3,'1980-02-01 00:00:00'),(301,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA1OTgwMjYsImV4cCI6MTcwMDYwMTYyNn0.w7meAV0dyiceDSNAXWXameFpxD9t5twjtGaPz-u-ppo',3,'1980-02-01 00:00:00'),(304,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA2MzQzODgsImV4cCI6MTcwMDYzNzk4OH0.HuNaHVxs8hnUDj7bp_m31luJkzBwbN1dwbdBkof-xUQ',3,'1980-02-01 00:00:00'),(305,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA2MzgzMzksImV4cCI6MTcwMDY0MTkzOX0.KAlOFlvE0TyB7T3fQx8DvCuNSzvDxz2p1ZHxePaC_dc',3,'1980-02-01 00:00:00'),(306,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA2MzkyODIsImV4cCI6MTcwMDY0Mjg4Mn0.VjgAO1anV08Xvpy9Vtywjjhq1vp_5eNuck8lrR-0CwA',3,'1980-02-01 00:00:00'),(307,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNjQxOTU3LCJleHAiOjE3MDA2NDU1NTd9.0VWx2j0Gg4BldyBLE9MuEkH5HK0X1bA76IyjeIkHnRA',6,'1980-02-01 00:00:00'),(308,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA2NDM5NTIsImV4cCI6MTcwMDY0NzU1Mn0.2olx8KS-vBP6nTqnKrAkVvnQxUenfe03-UDIcQsUtGs',3,'1980-02-01 00:00:00'),(310,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA2NDc2ODQsImV4cCI6MTcwMDY1MTI4NH0.MLT8mRG2LrbhthLqE-_820lGcm8jnxahduLZzYaGlYU',3,'1980-02-01 00:00:00'),(317,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA2NjA5NDMsImV4cCI6MTcwMDY2NDU0M30.vMhcf6vuzjctv3ZE-fI_nXdr7fVj2wLvObohM_6Q-lw',3,'1980-02-01 00:00:00'),(319,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA2NjQ3MjAsImV4cCI6MTcwMDY2ODMyMH0.0B3pCck2OobCw_gGw_w5mTptSCodYb9ffFasAvfU1B4',3,'1980-02-01 00:00:00'),(320,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNjY1NDY0LCJleHAiOjE3MDA2NjkwNjR9.JvvEU7q1HFYDUCkQgZVYt2AKu9j3qeuerDqVRemELYE',6,'1980-02-01 00:00:00'),(322,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA2NzAwNTEsImV4cCI6MTcwMDY3MzY1MX0.ZMX97Et-YIJu2WskNAhLJtPHOBNbbCUlfMeDx6MlRq8',3,'1980-02-01 00:00:00'),(324,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA2NzM3MDUsImV4cCI6MTcwMDY3NzMwNX0.GCgGaoMuaZ6CaaYRslngBJqMOp0wMw87mBBifS1LbEE',3,'1980-02-01 00:00:00'),(326,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA2NzkyMDEsImV4cCI6MTcwMDY4MjgwMX0.4NQ0io5Qq0LqCwUqE4D78B9W4-sXvqds5yKOpIytKfw',3,'1980-02-01 00:00:00'),(327,'920005d1-119f-4599-a42f-3cffeeafa852',3,'1980-02-01 00:00:00'),(328,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA3MTE4NjYsImV4cCI6MTcwMDcxNTQ2Nn0.Rg7qIqikjdhvrc6PCu9Z7K7t99Xjk-Db4ABROPykrvA',3,'1980-02-01 00:00:00'),(330,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA3MTY1NDEsImV4cCI6MTcwMDcyMDE0MX0.QseQtwLYuKWDd82m_qqNEyMkE1H7sdr-E7nN1v-JLxA',3,'1980-02-01 00:00:00'),(331,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA3MjU5MDcsImV4cCI6MTcwMDcyOTUwN30.hjsTFcrHWYR1sPxvB9SZ-1jP342OaHUFo92PzQmuIlw',3,'1980-02-01 00:00:00'),(334,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA3MjgwODAsImV4cCI6MTcwMDczMTY4MH0.phG_nihO0MIiKBPQRxRVN5tiev2Arfd9OEj5Tcim0cc',3,'1980-02-01 00:00:00'),(336,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNzI5ODI1LCJleHAiOjE3MDA3MzM0MjV9.VYxXVezSWTjsrPQGj8ljG9LnqFW7Cz9UP0TJToKgkYA',6,'1980-02-01 00:00:00'),(337,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA3MzIxNjMsImV4cCI6MTcwMDczNTc2M30.Vf0PJnTAqOqsSwhBYi9UIT3BJco_Gpm4gyOzFG2AQXM',3,'1980-02-01 00:00:00'),(338,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA3MzI0MjgsImV4cCI6MTcwMDczNjAyOH0.k1wOOA_R1dMaObNVomDPR7quVVv2ryOuywyhG-rN_Iw',3,'1980-02-01 00:00:00'),(339,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNzMzNDc0LCJleHAiOjE3MDA3MzcwNzR9.qslW9-0cAGHgRyPACzJjY2VUXZCapI9AAy72AjtKfiQ',6,'1980-02-01 00:00:00'),(341,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA3MzQ3NjAsImV4cCI6MTcwMDczODM2MH0.E-9BpkA1dRJ26eqVbncySAMU5TIvhmoTFLShJ3uRL64',3,'1980-02-01 00:00:00'),(342,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA3MzU2MzIsImV4cCI6MTcwMDczOTIzMn0.0n8vCkXY4a3Cx40gjNy3z7knDw3FCafuI1WBRUk_FkU',3,'1980-02-01 00:00:00'),(343,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNzQ4MzEzLCJleHAiOjE3MDA3NTE5MTN9.GXYsbFnb2dYzckHBX7b2jJr0fdmhxQtUYtT0XZGOiJA',6,'1980-02-01 00:00:00'),(345,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNzUyNzA3LCJleHAiOjE3MDA3NTYzMDd9.maOhTbnpDYK0bNeQ08W5OYnt3KH9euxlVNa1W7CdDag',6,'1980-02-01 00:00:00'),(347,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA3NTM5NTcsImV4cCI6MTcwMDc4OTk1N30.rfTMkj-rqBaPGN-SpNSQDKtofd2tBBNVo3Iudzz84MQ',3,'1980-02-01 00:00:00'),(349,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwNzU2NzYyLCJleHAiOjE3MDA3NjAzNjJ9.tTkl7c0cc8mMOBFIDoqIijGv0fy2cjqt_EZhmeL7hyc',6,'1980-02-01 00:00:00'),(351,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA3OTY5MzAsImV4cCI6MTcwMDgzMjkzMH0.p4Ai5zDAsaCxAkiLjooOGqt_9YJR7om0Gl_8I4Lic28',3,'1980-02-01 00:00:00'),(353,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA3OTc3NzUsImV4cCI6MTcwMDgzMzc3NX0.-RKBtzVT90SdtiSDhvNT43f3wJOakqdDJrBhBhXzCyo',3,'1980-02-01 00:00:00'),(356,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwODExODQ4LCJleHAiOjE3MDA4MTU0NDh9.pJkWuh6aBCspt_aJtgEiiSgiKWg29_SeqAQWZjDL4JA',6,'1980-02-01 00:00:00'),(357,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwODExOTAwLCJleHAiOjE3MDA4MTU1MDB9.CnIKqW7Vog9hi4aoo8nQtx62v0oNocsV97Tz2rYMi58',6,'1980-02-01 00:00:00'),(358,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwODExOTIyLCJleHAiOjE3MDA4MTU1MjJ9.ltRJnFBg3Cv-I0vzSIQzRDo8iFVotnC1_AahX2ebssw',6,'1980-02-01 00:00:00'),(359,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwODEyMDc1LCJleHAiOjE3MDA4MTU2NzV9.j1l_UMVAqeeCwslFP_WT7BtdtgjWBL9kmEyiNHdcB48',6,'1980-02-01 00:00:00'),(360,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwODEyMjk1LCJleHAiOjE3MDA4MTU4OTV9.onr4YegYaT3b_8aHZsmwH_1L-bitVeceu1HjSdEcmBQ',6,'1980-02-01 00:00:00'),(363,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwODEzOTIzLCJleHAiOjE3MDA4MTc1MjN9.egWTXfRj_M_C1QvDIGrsuGiT06a_GflXkkv534E9Blk',6,'1980-02-01 00:00:00'),(365,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA4MTY4MTEsImV4cCI6MTcwMDg1MjgxMX0.Yyd0XPY2j1tUk_fCvijMbRS9Qd8WrwOI3uthBG5FejA',3,'1980-02-01 00:00:00'),(367,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwODE4OTQ3LCJleHAiOjE3MDA4MjI1NDd9.nza1NStxBYsmdl4XsPAB4lyi0qMO9y7JCx6hh3FYMVs',6,'1980-02-01 00:00:00'),(371,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwODMwNjY1LCJleHAiOjE3MDA4MzQyNjV9.86fSxCiQmMLKrzaEjZw8KntknKe2dwNLyb1VA0XFL2c',6,'1980-02-01 00:00:00'),(372,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA4MzIyMDQsImV4cCI6MTcwMDg2ODIwNH0.WkC0A7fFJYf3qGV_-FoFsjqzqkYaSls7tchHA3Ci42o',3,'1980-02-01 00:00:00'),(379,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwODQxNTUxLCJleHAiOjE3MDA4NDUxNTF9.JyhuI9_Duy2F4BWAsTAUqSx__jdZoIC2tcI_dOwetr8',6,'1980-02-01 00:00:00'),(386,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwODk4MDEwLCJleHAiOjE3MDA5MDE2MTB9.UKIuOaGa_EergTfZJ4afA4f_gz2VaKlCh9AE-o9mb5Q',6,'1980-02-01 00:00:00'),(390,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwOTE3ODA1LCJleHAiOjE3MDA5MjE0MDV9.5x0k3u0sVBbXC4WtIPYvppR3jE8TZIPdOlPqAwuBiHs',6,'1980-02-01 00:00:00'),(391,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA5MTg5NDIsImV4cCI6MTcwMDk1NDk0Mn0.vtCaX0i2YDV19pbwLDuhrvscxKSxybWrGXFLyRVT1qY',3,'1980-02-01 00:00:00'),(403,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwOTMxNzA5LCJleHAiOjE3MDA5MzUzMDl9.oxFb4UneeeCtnQcmjOefE0W77jG1VrcN6eNxfG8HXE0',6,'1980-02-01 00:00:00'),(409,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA5ODIwMzAsImV4cCI6MTcwMTAxODAzMH0.-C63X0G5b7l2j3aUD5osjYBkgt-ZSC_vlxSbTo3lj8c',3,'1980-02-01 00:00:00'),(410,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwOTgyMzA1LCJleHAiOjE3MDA5ODU5MDV9.HX-nMG8rD5PZeegAn6xrWyO5BFOowxod1GZENHTjhMk',6,'1980-02-01 00:00:00'),(411,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA5ODM0OTAsImV4cCI6MTcwMDI4ODUyM30.J3pmNXaWDrMDFYlh6fIHC4ziZ9PTjIZr1LNmub2QVKM',3,'1980-02-01 00:00:00'),(412,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDA5ODM2MTYsImV4cCI6MTcwMTM0MzYxNn0.ewYv--kJnswpYodQfz6L1BcT9_Mc0tzBxEuULtG-dBI',3,'1980-02-01 00:00:00'),(413,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwOTg4NjAyLCJleHAiOjE3MDA5OTIyMDJ9.v5OTfDRoAq9PzsKvukJK3SzjYQjQrAJ5zxojvOCbkNQ',6,'1980-02-01 00:00:00'),(414,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwOTg4Nzg4LCJleHAiOjE3MDA5OTIzODh9.kpYdUXNt8QnI-ap5LFyZ7AoakYN5Nxl3uAonu0oZKBg',6,'1980-02-01 00:00:00'),(415,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwOTg4ODEzLCJleHAiOjE3MDA5OTI0MTN9.nTubr9F-ka5rNYV2eKbuZPRxEvS3R3MMbMDvHg-QRo8',6,'1980-02-01 00:00:00'),(416,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwOTg4ODQyLCJleHAiOjE3MDA5OTI0NDJ9.26uIitTdqxY8iRMcJ_9YylUuLi_qdX-Sif3VIpATaT0',6,'1980-02-01 00:00:00'),(417,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwOTg5MTI2LCJleHAiOjE3MDA5OTI3MjZ9.fVKdMZaThaHxMkNHVfzh6C3XGhjjEkERie7Wl8n72hw',6,'1980-02-01 00:00:00'),(418,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwOTkwMzg3LCJleHAiOjE3MDA5OTM5ODd9.vlS8Zt2D7-3jb52wLBL7a0TEgLvtJxp4Heah9V0KV5I',6,'1980-02-01 00:00:00'),(419,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwOTk0MDAwLCJleHAiOjE3MDA5OTc2MDB9.-qTbZScZCOzmXdrCOQYp8jcxoidlOyC3z3hY5nr5AsA',6,'1980-02-01 00:00:00'),(420,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwOTk3OTA4LCJleHAiOjE3MDEwMDE1MDh9.Sp3cBcKVd4fYXKMPnBxHbmi_D0uSnI0ck6GA_FWQ2oc',6,'1980-02-01 00:00:00'),(421,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAwOTk5Nzk2LCJleHAiOjE3MDEwMDMzOTZ9.kpL0r_jXa8sFZJkKTexEFMlo5wpcV6t8wumlPWrd2MM',6,'1980-02-01 00:00:00'),(425,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMDA4OTM0LCJleHAiOjE3MDEwMTI1MzR9.SOe5t4PJAFZScR1kIu45WwswOUQ-QP0LqfPGcqY1c6A',6,'1980-02-01 00:00:00'),(426,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMDA5Mzk3LCJleHAiOjE3MDEwMTI5OTd9.Jt_0mF092jcfmfKhvv20WIwsJduuFtvOAOibjN16btQ',6,'1980-02-01 00:00:00'),(427,'91961cba-fe67-4277-9a28-d254db01282c',3,'1980-02-01 00:00:00'),(428,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMDA5NTM3LCJleHAiOjE3MDEwMTMxMzd9.8FvAmOC5yzGGG4mR8q3uy6pi_3P_G3HFGnQK0eMqQr4',6,'1980-02-01 00:00:00'),(429,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDEwMDk1NDUsImV4cCI6MTcwMTM2OTU0NX0.A6ZpUXt2u7_yM6emktcGzdwhn5sL8g54GChP2GAOLFA',3,'1980-02-01 00:00:00'),(430,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMDEwNTQxLCJleHAiOjE3MDEwMTQxNDF9.XvNmhOx4JFg_uE0aw5e2ZDBGgLgXgtvBNO2mXTcVAaI',6,'1980-02-01 00:00:00'),(431,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMDEwNTgzLCJleHAiOjE3MDEwMTQxODN9.S1LaCez_qnP1YonP9j9Q_cDGD6GlksvH4omdPEyzDiI',6,'1980-02-01 00:00:00'),(432,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjMsInN1YiI6ImgiLCJpYXQiOjE3MDEwMTA4MjMsImV4cCI6MTcwMTM3MDgyM30.v2qchv9WyPYDidAbZ_Z71rSfudl-3mt7ArXowl7YutE',3,'1980-02-01 00:00:00'),(435,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMDEzMjQ1LCJleHAiOjE3MDEwMTY4NDV9.MhUUi5D33RG8WKXFsPfJEu4gtbjUmDcKicSfZE-JHYE',6,'1980-02-01 00:00:00'),(437,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMDE1OTgzLCJleHAiOjE3MDEwMTk1ODN9.EmUFtE7mbpupEycOSaY5Qfha9j0_BNtaRRO_FZC_rug',6,'1980-02-01 00:00:00'),(443,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMDI4NDIxLCJleHAiOjE3MDEwMzIwMjF9.mSI9KmYfd_CzAvJPe6nXo5ETSbn4Pn65ksjlEF7EdJA',6,'1980-02-01 00:00:00'),(444,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9SRUNFUFRJT05JU1QiLCJzdGFmZl9pZCI6Mywic3ViIjoiaCIsImlhdCI6MTcwMTAyODY3MSwiZXhwIjoxNzAxMzg4NjcxfQ.yK_S9-FvUOoYfZZMRuCtaqoeNLOFd7o95DxAudnjcE0',3,'2023-11-27 03:02:52'),(445,'06ca56e0-65f5-4b24-8b8d-42a8c4977a44',34,'1980-02-01 00:00:00'),(446,'4f55428f-184c-4fbc-bf8a-6c3120cafc99',34,'1980-02-01 00:00:00'),(447,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9SRUNFUFRJT05JU1QiLCJzdGFmZl9pZCI6MzQsInN1YiI6Imh1eWh1eSIsImlhdCI6MTcwMTAzMDkwNCwiZXhwIjoxNzAxMzkwOTA0fQ.44EEpJ9KZ3Q4slhNtNZ-P6Zt0KjaAfE6JzQTepcKY8M',34,'1980-02-01 00:00:00'),(448,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9SRUNFUFRJT05JU1QiLCJzdGFmZl9pZCI6MzQsInN1YiI6Imh1eWh1eSIsImlhdCI6MTcwMTA0NDg0MSwiZXhwIjoxNzAxNDA0ODQxfQ.-c-euEFKQtcc4jm38HumqQRFW1s92CROtB5vac5R5n0',34,'1980-02-01 00:00:00'),(449,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMDYzODA1LCJleHAiOjE3MDEwNjc0MDV9.1zqowRimCc-LpAqg0Cn6XxlsbupcORf7G4AZTmdJy2U',6,'1980-02-01 00:00:00'),(450,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMDYzOTc1LCJleHAiOjE3MDEwNjc1NzV9.KaA-ZFAFu7AwWmv-SyC6U-n7V0CUT_22w-LSPibcTc8',6,'1980-02-01 00:00:00'),(451,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMDY4MDE3LCJleHAiOjE3MDEwNzE2MTd9.3qaMj8QpZyVJc_uiJqr2NbPzM70sPHpGrdYwr9TZ7Fg',6,'1980-02-01 00:00:00'),(453,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMDcxNzk4LCJleHAiOjE3MDEwNzUzOTh9.NdYuRZyWCy4Dzkp5HmXtIISyQdO0E0JRCDJq94ZGy5I',6,'1980-02-01 00:00:00'),(454,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMDcyOTI1LCJleHAiOjE3MDEwNzY1MjV9.59wyio25KSoqfudttQvZialvSLqJKjixzutXohSb--E',6,'1980-02-01 00:00:00'),(458,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMDc1MDM2LCJleHAiOjE3MDEwNzg2MzZ9.FVkisenJkx4umPzTTa49AhASwab1fi8SVGe81uTNaRw',6,'1980-02-01 00:00:00'),(459,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMDc1MDQzLCJleHAiOjE3MDEwNzg2NDN9.HqJYfhiEHaKcVRPWeiu5zRcR8UxnsgHVAUC317XUFhU',6,'1980-02-01 00:00:00'),(460,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMDc1MDY2LCJleHAiOjE3MDEwNzg2NjZ9.ZlGYBNsCtlfBXedntTK8NowkZqQ1aR-ftcLFLysKtLY',6,'1980-02-01 00:00:00'),(461,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMDc1MDY4LCJleHAiOjE3MDEwNzg2Njh9.qcXUxCmdQvZfKTHmZX7uObWeFGmI3SdEfVgcK8rn-4I',6,'1980-02-01 00:00:00'),(462,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDEwNzUyMjEsImV4cCI6MTcwMTA3ODgyMX0.0Yt3uMGXwH_FPEMjeoeZaPncMSZPqWei3lixEn4z71Y',2,'1980-02-01 00:00:00'),(463,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMDc1MjMyLCJleHAiOjE3MDEwNzg4MzJ9.OeW1Dr_-dHN-gmN8V4vhrNGSx5qo19kf0RrAiSzltQQ',6,'1980-02-01 00:00:00'),(464,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMDc1MjY3LCJleHAiOjE3MDEwNzg4Njd9.BlV_4wMEVGkLb_wfMWEwj1eEYdlvZw5nlfQqsSBS9VQ',6,'1980-02-01 00:00:00'),(465,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMDc1ODY4LCJleHAiOjE3MDE0MzU4Njh9.P8uHE9EtVR0I2ZQui761Ha2x05gecOsGbrre9-1i7rU',6,'1980-02-01 00:00:00'),(466,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjM0LCJzdWIiOiJodXlodXkiLCJpYXQiOjE3MDEwNzY1NjQsImV4cCI6MTcwMTExMjU2NH0.-5fXOZz111yMGcqDmFap6F16WOjAlGE9y1mn-O9npl0',34,'1980-02-01 00:00:00'),(467,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMDc3Mzk3LCJleHAiOjE3MDEwODA5OTd9.yMblFqOV3jeZcgRnlFZxh3u50w8-adiwX39Ti4m663g',6,'1980-02-01 00:00:00'),(468,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjM0LCJzdWIiOiJodXlodXkiLCJpYXQiOjE3MDEwNzc1NDUsImV4cCI6MTcwMTExMzU0NX0.k83Nlvcfy_Moxd7IHUpdr5flZQ4fQ3pG5b4RdA-tbQQ',34,'1980-02-01 00:00:00'),(469,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMDc3NjMzLCJleHAiOjE3MDEwODEyMzN9.tXOuPtnekYoYbER9QYNU5r8Cogny1xmK2gg4RITDbWk',6,'1980-02-01 00:00:00'),(470,'3a0e641f-04d7-4693-9e4b-0b2df0851aaa',10,'1980-02-01 00:00:00'),(471,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9SRUNFUFRJT05JU1QiLCJzdGFmZl9pZCI6MTAsInN1YiI6ImtoYW5oIiwiaWF0IjoxNzAxMDc4MDMxLCJleHAiOjE3MDE0MzgwMzF9.jB7GHJhLk_Q6L1O2QeryeTPfw1TsQnAk3SuizOqYz6A',10,'1980-02-01 00:00:00'),(472,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDEwNzkxNTYsImV4cCI6MTcwMTA4Mjc1Nn0.a_i4VdD46WZqRuJRwWjjofMWqMffMu5v3KyPnit2xOk',2,'1980-02-01 00:00:00'),(473,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9SRUNFUFRJT05JU1QiLCJzdGFmZl9pZCI6MTAsInN1YiI6ImtoYW5oIiwiaWF0IjoxNzAxMDc5NDE3LCJleHAiOjE3MDE0Mzk0MTd9.LHmcJ1Bkf33hEp00XTP2fYGlfGhyepknKWahiPSOK8c',10,'1980-02-01 00:00:00'),(474,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9SRUNFUFRJT05JU1QiLCJzdGFmZl9pZCI6MTAsInN1YiI6ImtoYW5oIiwiaWF0IjoxNzAxMDc5ODEwLCJleHAiOjE3MDE0Mzk4MTB9.RswumcS_G600FMvVXn5p3vVRWBPnIhnqnRZxyW83sFM',10,'1980-02-01 00:00:00'),(475,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9SRUNFUFRJT05JU1QiLCJzdGFmZl9pZCI6MTAsInN1YiI6ImtoYW5oIiwiaWF0IjoxNzAxMDgwMDgxLCJleHAiOjE3MDE0NDAwODF9.GQ4dRSrgDqD-S_sYoBk2lw3FNboRCtm9pLdNGjPn6C0',10,'1980-02-01 00:00:00'),(476,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9SRUNFUFRJT05JU1QiLCJzdGFmZl9pZCI6MTAsInN1YiI6ImtoYW5oIiwiaWF0IjoxNzAxMDgwMTE4LCJleHAiOjE3MDE0NDAxMTh9.7KG60KPahW0qAssxVsEpYCrKikYDfQm24kIYa3Ff5II',10,'1980-02-01 00:00:00'),(477,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEwLCJzdWIiOiJraGFuaCIsImlhdCI6MTcwMTA4MDE4MiwiZXhwIjoxNzAxNDQwMTgyfQ.jrq5bhEO3Zi6cDvOUgl90FnYO3T0RU2NwGtzYlnhUHk',10,'1980-02-01 00:00:00'),(478,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEwLCJzdWIiOiJraGFuaCIsImlhdCI6MTcwMTA4MTUxMCwiZXhwIjoxNzAxNDQxNTEwfQ.hnERvfNn_rGofgSiF9Zk1BYTCwNDEF-4MqKcfs_ty8U',10,'1980-02-01 00:00:00'),(479,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEwLCJzdWIiOiJraGFuaCIsImlhdCI6MTcwMTA4MjA1NSwiZXhwIjoxNzAxNDQyMDU1fQ.TCwCuqCquEHpvspzk5-07cZ9FeaEbiPr6h5Si7QZ8zY',10,'1980-02-01 00:00:00'),(480,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEwLCJzdWIiOiJraGFuaCIsImlhdCI6MTcwMTA4MjY1NiwiZXhwIjoxNzAxNDQyNjU2fQ.fpqOrHYuHWla5oMcNTH2T9Dg0gFM9FKT2EnQr5uOxos',10,'1980-02-01 00:00:00'),(481,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEwLCJzdWIiOiJraGFuaCIsImlhdCI6MTcwMTA4Mjc0NSwiZXhwIjoxNzAxNDQyNzQ1fQ.KKABFsGeofK1j7Nl923ZWiVfoD7tLt9qPO5Y9tQDN9E',10,'1980-02-01 00:00:00'),(482,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEwLCJzdWIiOiJraGFuaCIsImlhdCI6MTcwMTA4MzU3NSwiZXhwIjoxNzAxNDQzNTc1fQ.OOIWukSWoVDcfRM9wafkkyf92FWc4-D-MfPIskglOX8',10,'1980-02-01 00:00:00'),(483,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEwLCJzdWIiOiJraGFuaCIsImlhdCI6MTcwMTA5MTYzOSwiZXhwIjoxNzAxNDUxNjM5fQ.no_rJouQaFE0POJLPST0IDbtRwMjKkTtOHBJiQ5NeoA',10,'1980-02-01 00:00:00'),(484,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDEwOTY1NDIsImV4cCI6MTcwMTQ1NjU0Mn0.enjXCUurQTrD7Jo9nC7RdVSJgPiusuaPcVIgG7I1l_w',2,'1980-02-01 00:00:00'),(485,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMDk3NTk1LCJleHAiOjE3MDExMDExOTV9.TtAwRvCXPywFrVroSJoQKygOf0isMVrGWD1erWAOUJg',6,'1980-02-01 00:00:00'),(486,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEwLCJzdWIiOiJraGFuaCIsImlhdCI6MTcwMTA5ODk4NiwiZXhwIjoxNzAxNDU4OTg2fQ.JL2adhUThJPtZdloys2kwpCxd225_scdCUNno_gJav4',10,'1980-02-01 00:00:00'),(487,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDEwOTkwMDIsImV4cCI6MTcwMTQ1OTAwMn0.HvT1_6-Tq_Fy4UWwl__K3-xTFPrdhI0gLZsSxBksU8c',2,'1980-02-01 00:00:00'),(488,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDEwOTk0NjksImV4cCI6MTcwMTQ1OTQ2OX0.9i4QQEzA7RpEihYVkbWLHhbjRWlCFNZLebmJPOBm4c4',2,'1980-02-01 00:00:00'),(489,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMTAxMzYyLCJleHAiOjE3MDExMDQ5NjJ9.Tzz_dk5QxbXUYWEOqYZH0FI23OwFijWkwgojH9Pk-Zk',6,'1980-02-01 00:00:00'),(490,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjM0LCJzdWIiOiJodXlodXkiLCJpYXQiOjE3MDExMDIwODEsImV4cCI6LTkyMjMzNzAzMzU3NTI2OTR9.rQ7UrXpab3Ptk1bJ2xbNV1f4pOKM6rEj9hWSN4oEE7E',34,'1980-02-01 00:00:00'),(491,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjM0LCJzdWIiOiJodXlodXkiLCJpYXQiOjE3MDExMDIxMDgsImV4cCI6LTkyMjMzNzAzMzU3NTI2Njd9.djNYp8iPWdCy9_NQU-gdbEbCQxBrZ1XBJYdRmg0ZIR0',34,'1980-02-01 00:00:00'),(492,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjM0LCJzdWIiOiJodXlodXkiLCJpYXQiOjE3MDExMDIyMDcsImV4cCI6LTkyMjMzNzAzMzU3NTI1Njh9.4xf-1IUbKrVY5JBL80L2qRcne5uoVBEB7eztT1Q8qhU',34,'1980-02-01 00:00:00'),(493,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjM0LCJzdWIiOiJodXlodXkiLCJpYXQiOjE3MDExMDI0MjMsImV4cCI6MTcwMTEzODQyM30.Nxk5L-yrD3iV8BoBXPj6xBLg2bef3vXBl91MhbOJL0g',34,'1980-02-01 00:00:00'),(494,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMTA3NTY1LCJleHAiOjE3MDExMTExNjV9.ZIBCpAym5Qmm6h7a0W0zvkgN-vau1pJiRJh5pApfOjc',6,'1980-02-01 00:00:00'),(495,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDExMDg4NDAsImV4cCI6MTcwMTQ2ODg0MH0.9kBFKAPoOEORk3Tea9dvQFdSKMQtDgHtpSVky7XT8gQ',2,'1980-02-01 00:00:00'),(496,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEwLCJzdWIiOiJraGFuaCIsImlhdCI6MTcwMTE1MTMxNSwiZXhwIjoxNzAxNTExMzE1fQ.RhQa_BsXTnHcmjrkukyCpw186m372Mz2V6ROeFDOx4I',10,'1980-02-01 00:00:00'),(497,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEwLCJzdWIiOiJraGFuaCIsImlhdCI6MTcwMTE1MTc3OCwiZXhwIjoxNzAxNTExNzc4fQ.ys5Svp6FkEj26VG9DWqVxgMloITIK9lY5wW-gDrYzVo',10,'1980-02-01 00:00:00'),(498,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMTUxODY1LCJleHAiOjE3MDE1MTE4NjV9.NuYoZr7G18RsodN3GRXi0w0oy1RLXJ03TqrZEDC9HuA',6,'1980-02-01 00:00:00'),(499,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMTUyMzM2LCJleHAiOjE3MDE1MTIzMzZ9.A3fidLivIYRwW4kZgW5SR0PYzVCz3xzGjinKDRU1Bb4',6,'1980-02-01 00:00:00'),(500,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMTUyNTA1LCJleHAiOjE3MDE1MTI1MDV9.mRwp1pWffG2EM2QRnvsCAkWpCdBMbs7swcm4aYQ23qg',6,'1980-02-01 00:00:00'),(501,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMTUyNTUyLCJleHAiOjE3MDE1MTI1NTJ9.VMIR3zLmeorC7Btlq1vaF0vilkJ6KZDzz42ussNFyAU',6,'1980-02-01 00:00:00'),(502,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMTUyNTgyLCJleHAiOjE3MDE1MTI1ODJ9.BvGNpydmjt2WiN-Xcm6J11r1U26wf1bMWNX6sSnO5jI',6,'1980-02-01 00:00:00'),(503,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEwLCJzdWIiOiJraGFuaCIsImlhdCI6MTcwMTE1MjU4OCwiZXhwIjoxNzAxNTEyNTg4fQ.ga_tkOQz4hTh7X1dz9gtIMmkthnlIZ3CEZqkDCnb5L8',10,'1980-02-01 00:00:00'),(504,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMTUyODQyLCJleHAiOjE3MDE1MTI4NDJ9.Vt_4Po1bk0cMcg6CmrHiBOXKsRCuxxtvlx38g25Ks_4',6,'1980-02-01 00:00:00'),(505,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjM0LCJzdWIiOiJodXlodXkiLCJpYXQiOjE3MDExNTI4NjUsImV4cCI6MTcwMTUxMjg2NX0.AZ8x8Rgg102ToNcQseMTDZdux4di94eCStPKsYgxtLo',34,'1980-02-01 00:00:00'),(506,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDExNTQyMzQsImV4cCI6MTcwMTUxNDIzNH0.DmJefLEs_p1Civ2VQLd_qf68-tC2hyy237roxCiWjSA',2,'1980-02-01 00:00:00'),(507,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMTU0OTcyLCJleHAiOjE3MDE1MTQ5NzJ9.NU0yo4qSAlVWF2vEJ8ZdoYWXD1HgwgzHSk8hW_QLO-E',6,'1980-02-01 00:00:00'),(508,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMTU1MTA1LCJleHAiOjE3MDE1MTUxMDV9.xUImDGTn4MJ83A4FCMP5bkc0N-bubUHtv5towUxKoTw',6,'1980-02-01 00:00:00'),(509,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMTU1MzI0LCJleHAiOjE3MDE1MTUzMjR9.77gdN6odiFAPF5Y54sa1RcXH4lIi7vu1xga3WGYmUBc',6,'1980-02-01 00:00:00'),(510,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEwLCJzdWIiOiJraGFuaCIsImlhdCI6MTcwMTE1NjE0NSwiZXhwIjoxNzAxNTE2MTQ1fQ.QADgWT8IuNoPCsnOo7MU1WrKF5ePNmNrnSOV8Bg0XLw',10,'1980-02-01 00:00:00'),(511,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjM0LCJzdWIiOiJodXlodXkiLCJpYXQiOjE3MDExNTY0MDUsImV4cCI6MTcwMTUxNjQwNX0.cO6hYnbdNs9uyUZDlEW-gZ5-clfH4HD5VyAV3IKYgk4',34,'1980-02-01 00:00:00'),(512,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDExNTY1NTAsImV4cCI6MTcwMTUxNjU1MH0.7nTHFWTOI1RoUy1m6V3Io7kcwchdNIgIUNns2fa7tqQ',2,'1980-02-01 00:00:00'),(513,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjM0LCJzdWIiOiJodXlodXkiLCJpYXQiOjE3MDExNTgzMDMsImV4cCI6MTcwMTUxODMwM30.-e-t76doncWcPOOicTJOnYuNDRrVspbSqnOaELN-uKw',34,'2023-11-28 15:03:24'),(514,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDExNzYzODcsImV4cCI6MTcwMTUzNjM4N30.xX8Wm2cnTV_OgmBa9_Nf5srcZ3fSnW5JpKHifDAVsu0',2,'1980-02-01 00:00:00'),(515,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMTgzODg1LCJleHAiOjE3MDE1NDM4ODV9.g108e0swJoJi3lHOrtmzZuJDaxiaGhvLuwojHy0WVus',6,'1980-02-01 00:00:00'),(516,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEwLCJzdWIiOiJraGFuaCIsImlhdCI6MTcwMTE5MzU2MSwiZXhwIjoxNzAxNTUzNTYxfQ.KQkx5CCxYMT4NOqWeHi713_aUpCqXTAMK8zTI-ZV0Bc',10,'1980-02-01 00:00:00'),(517,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDEyNDI0NzMsImV4cCI6MTcwMTYwMjQ3M30.ML9xMnaTJNdtWpgA1UsvpLOvx8NJj79ycUKRopZBT-0',2,'1980-02-01 00:00:00'),(518,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEwLCJzdWIiOiJraGFuaCIsImlhdCI6MTcwMTI0MzcxOSwiZXhwIjoxNzAxNjAzNzE5fQ.awMiQd9k9tQT9tcgbsvhD9Tu9EQZ-OWFFqhqyMXFfL8',10,'1980-02-01 00:00:00'),(519,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDEyNjMzMDEsImV4cCI6MTcwMTYyMzMwMX0.8G59OTHUj2v20xXQ3uUgRdlko-XWDSv8UXrFMQWMhgE',2,'1980-02-01 00:00:00'),(520,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEwLCJzdWIiOiJraGFuaCIsImlhdCI6MTcwMTI4MDgzNywiZXhwIjoxNzAxNjQwODM3fQ.FEhCiA51G5sEMfpUOVPzNGxUqLrmCF1K2zm2HfqW1Ws',10,'1980-02-01 00:00:00'),(521,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMzM4OTQ4LCJleHAiOjE3MDE2OTg5NDh9.2l-lFBj6XHg9Sk-U5AN5LFrLSlsOsAhm-ZT5tdzgqUA',6,'1980-02-01 00:00:00'),(522,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxMzY4Mjk5LCJleHAiOjE3MDE3MjgyOTl9.RU5QWcUtUKffiv1gHyBOg-ToWUyvVdxB6tNXsAd30yY',6,'1980-02-01 00:00:00'),(523,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDE0MDc2MTYsImV4cCI6MTcwMTc2NzYxNn0.tzHczqe86pX66BKJn2HImb3Lx0YVK66m3zggaAvCt5Q',2,'1980-02-01 00:00:00'),(524,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjEwLCJzdWIiOiJraGFuaCIsImlhdCI6MTcwMTQyMDQ1MywiZXhwIjoxNzAxNzgwNDUzfQ.O_YAU5tEtPV6iUSxBfq_DIdG9Oh7I8Bp-xkCH7VKYMY',10,'2023-12-01 15:52:34'),(525,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjIsInN1YiI6InRpZW4iLCJpYXQiOjE3MDE0MjA2MTMsImV4cCI6MTcwMTc4MDYxM30.ZqWuG6ogy4WWC4eRi7veuRrILOAu5HDGeMJuZtdW4LI',2,'2023-12-01 15:55:14'),(526,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxNDM2NDU2LCJleHAiOjE3MDE3OTY0NTZ9.yXr04yVmV4XXWb8BdUy7HRZCjxamQr2kolkc-ryzE-Y',6,'1980-02-01 00:00:00'),(527,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxNDQwMTYxLCJleHAiOjE3MDE4MDAxNjF9.-xkQZm-qC78pxt7QquIExFm9zJdshFY4ObDb-mo2Wks',6,'1980-02-01 00:00:00'),(528,'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9NQU5BR0VSIiwic3RhZmZfaWQiOjYsInN1YiI6ImFkbWluIiwiaWF0IjoxNzAxNTEyNDk4LCJleHAiOjE3MDE4NzI0OTh9.UxgGo-a4BWZOd0dszEv3jmuY_CkL0M3aTUPnb2reH9c',6,'2023-12-02 17:26:39');
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

-- Dump completed on 2023-12-04  3:11:42
