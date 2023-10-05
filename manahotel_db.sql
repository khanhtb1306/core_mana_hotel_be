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
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goods` (
                         `goods_id` varchar(50) NOT NULL,
                         `goods_name` varchar(250) DEFAULT NULL,
                         `goods_category_id` varchar(50) NOT NULL,
                         `status` bit(1) DEFAULT NULL,
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
-- Table structure for table `goods_category`
--

DROP TABLE IF EXISTS `goods_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goods_category` (
                                  `goods_category_id` varchar(50) NOT NULL,
                                  `goods_category_name` varchar(250) DEFAULT NULL,
                                  `status` bit(1) DEFAULT NULL,
                                  `created_by_id` bigint DEFAULT NULL,
                                  `updated_by_id` bigint DEFAULT NULL,
                                  `created_date` timestamp NULL DEFAULT NULL,
                                  `updated_date` timestamp NULL DEFAULT NULL,
                                  PRIMARY KEY (`goods_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `price_list`
--

DROP TABLE IF EXISTS `price_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `price_list` (
                              `price_list_id` bigint NOT NULL AUTO_INCREMENT,
                              `price_list_name` varchar(250) DEFAULT NULL,
                              `room_category_id` varchar(50) NOT NULL,
                              `price_by_day` float NOT NULL,
                              `price_by_night` float NOT NULL,
                              `price_by_hour` float NOT NULL,
                              `status` bit(1) DEFAULT NULL,
                              `start_date` timestamp NULL DEFAULT NULL,
                              `end_date` timestamp NULL DEFAULT NULL,
                              `created_by_id` bigint DEFAULT NULL,
                              `updated_by_id` bigint DEFAULT NULL,
                              `created_date` timestamp NULL DEFAULT NULL,
                              `updated_date` timestamp NULL DEFAULT NULL,
                              PRIMARY KEY (`price_list_id`),
                              KEY `room_category_id_idx` (`room_category_id`),
                              CONSTRAINT `pk_pl_rc` FOREIGN KEY (`room_category_id`) REFERENCES `room_category` (`room_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
                        `room_id` varchar(50) NOT NULL,
                        `room_name` varchar(250) DEFAULT NULL,
                        `room_category_id` varchar(50) NOT NULL,
                        `floor` varchar(250) DEFAULT NULL,
                        `status` bit(1) DEFAULT NULL,
                        `booking_status` bigint DEFAULT NULL,
                        `condition_status` bigint DEFAULT NULL,
                        `note` varchar(250) DEFAULT NULL,
                        `created_by_id` bigint DEFAULT NULL,
                        `updated_by_id` bigint DEFAULT NULL,
                        `created_date` timestamp NULL DEFAULT NULL,
                        `updated_date` timestamp NULL DEFAULT NULL,
                        PRIMARY KEY (`room_id`),
                        KEY `pk_r_rc_idx` (`room_category_id`),
                        CONSTRAINT `pk_r_rc` FOREIGN KEY (`room_category_id`) REFERENCES `room_category` (`room_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
                                 `status` bit(1) DEFAULT NULL,
                                 `description` varchar(500) DEFAULT NULL,
                                 `created_by_id` bigint DEFAULT NULL,
                                 `updated_by_id` bigint DEFAULT NULL,
                                 `created_date` timestamp NULL DEFAULT NULL,
                                 `updated_date` timestamp NULL DEFAULT NULL,
                                 PRIMARY KEY (`room_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
                         `status` bit(1) DEFAULT NULL,
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-06  1:12:34
