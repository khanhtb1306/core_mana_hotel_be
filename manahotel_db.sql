-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: manahotel_db
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
                            `customer_id` varchar(50) NOT NULL,
                            `customer_name` varchar(250) DEFAULT NULL,
                            `customer_group` varchar(250) DEFAULT NULL,
                            `phone_number` varchar(50) DEFAULT NULL,
                            `dob` timestamp NULL DEFAULT NULL,
                            `email` varchar(350) DEFAULT NULL,
                            `address` varchar(350) DEFAULT NULL,
                            `identity` varchar(350) DEFAULT NULL,
                            `nationality` varchar(350) DEFAULT NULL,
                            `tax_code` varchar(350) DEFAULT NULL,
                            `gender` bit(1) DEFAULT NULL,
                            PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `floor`
--

LOCK TABLES `floor` WRITE;
/*!40000 ALTER TABLE `floor` DISABLE KEYS */;
/*!40000 ALTER TABLE `floor` ENABLE KEYS */;
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
                         `goods_category_id` varchar(50) NOT NULL,
                         `status` int DEFAULT NULL,
                         `cost` float DEFAULT NULL,
                         `price` float DEFAULT NULL,
                         `unit` varchar(250) DEFAULT NULL,
                         `inventory` bigint DEFAULT NULL,
                         `min_inventory` bigint DEFAULT NULL,
                         `max_inventory` bigint DEFAULT NULL,
                         `note` varchar(250) DEFAULT NULL,
                         `description` varchar(500) DEFAULT NULL,
                         `created_by_id` bigint DEFAULT NULL,
                         `updated_by_id` bigint DEFAULT NULL,
                         `created_date` timestamp NULL DEFAULT NULL,
                         `updated_date` timestamp NULL DEFAULT NULL,
                         PRIMARY KEY (`goods_id`),
                         KEY `pk_g_gc_idx` (`goods_category_id`),
                         CONSTRAINT `pk_g_gc` FOREIGN KEY (`goods_category_id`) REFERENCES `goods_category` (`goods_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES ('SP000001','Sữa chua','LH000002',1,28000,30000,'Hộp',100,10,1000,'Hàng hiếm','Cực múp',NULL,NULL,'2023-10-05 17:51:06','2023-10-05 18:10:13'),('SP000002','Sprite','LH000001',1,27000,30000,'Hộp',100015,0,100000,NULL,NULL,NULL,NULL,'2023-10-07 08:43:26',NULL),('SP000003','Thịt bò khô','LH000001',1,30000,80000,'Gói',100005,0,100000,NULL,NULL,NULL,NULL,'2023-10-07 09:15:30',NULL);
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods_category`
--

DROP TABLE IF EXISTS `goods_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goods_category` (
                                  `goods_category_id` varchar(50) NOT NULL,
                                  `goods_category_name` varchar(250) DEFAULT NULL,
                                  `status` int DEFAULT NULL,
                                  `created_by_id` int DEFAULT NULL,
                                  `updated_by_id` int DEFAULT NULL,
                                  `created_date` timestamp NULL DEFAULT NULL,
                                  `updated_date` timestamp NULL DEFAULT NULL,
                                  PRIMARY KEY (`goods_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods_category`
--

LOCK TABLES `goods_category` WRITE;
/*!40000 ALTER TABLE `goods_category` DISABLE KEYS */;
INSERT INTO `goods_category` VALUES ('LH000001','Hàng hóa',1,NULL,NULL,'2023-10-05 16:19:51','2023-10-05 17:08:02'),('LH000002','Dịch vụ',1,NULL,NULL,'2023-10-05 16:31:48',NULL);
/*!40000 ALTER TABLE `goods_category` ENABLE KEYS */;
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
INSERT INTO `inventory_check` VALUES ('KK000001','2023-10-08 11:41:56','',5,'2023-10-08 11:41:56',NULL),('KK000002','2023-10-07 18:42:54',NULL,5,'2023-10-07 18:42:54',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory_check_detail`
--

LOCK TABLES `inventory_check_detail` WRITE;
/*!40000 ALTER TABLE `inventory_check_detail` DISABLE KEYS */;
INSERT INTO `inventory_check_detail` VALUES (5,'KK000002','SP000001',100000,99900,2797200000,100,28000),(62,'KK000001','SP000002',100014,-1,-27000,100015,27000),(63,'KK000001','SP000003',130000,29995,899850000,100005,30000);
/*!40000 ALTER TABLE `inventory_check_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
                        `role_id` int NOT NULL AUTO_INCREMENT,
                        `role_name` varchar(250) DEFAULT NULL,
                        `status` bit(1) DEFAULT NULL,
                        `created_by_id` bigint DEFAULT NULL,
                        `updated_by_id` bigint DEFAULT NULL,
                        `created_date` timestamp NULL DEFAULT NULL,
                        `updated_date` timestamp NULL DEFAULT NULL,
                        PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
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
                        `booking_status` int DEFAULT NULL,
                        `condition_status` int DEFAULT NULL,
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
                                 `room_capacity` bigint DEFAULT NULL,
                                 `room_area` float DEFAULT NULL,
                                 `status` int DEFAULT NULL,
                                 `description` varchar(500) DEFAULT NULL,
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
/*!40000 ALTER TABLE `room_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
                         `staff_id` bigint NOT NULL,
                         `staff_name` varchar(250) DEFAULT NULL,
                         `username` varchar(50) DEFAULT NULL,
                         `password` varchar(150) DEFAULT NULL,
                         `role_id` int NOT NULL,
                         `status` int DEFAULT NULL,
                         `dob` timestamp NULL DEFAULT NULL,
                         `address` varchar(350) DEFAULT NULL,
                         `email` varchar(350) DEFAULT NULL,
                         `gender` bit(1) DEFAULT NULL,
                         `identity` varchar(350) DEFAULT NULL,
                         `tax_code` varchar(350) DEFAULT NULL,
                         `phone_number` varchar(50) DEFAULT NULL,
                         `created_by_id` bigint DEFAULT NULL,
                         `updated_by_id` bigint DEFAULT NULL,
                         `created_date` timestamp NULL DEFAULT NULL,
                         `updated_date` timestamp NULL DEFAULT NULL,
                         PRIMARY KEY (`staff_id`),
                         KEY `pk_s_r_idx` (`role_id`),
                         CONSTRAINT `pk_s_r` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-08 21:55:44
