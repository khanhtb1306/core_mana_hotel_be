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
                            `image` longblob,
                            PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
                        `floor_id` int NOT NULL,
                        `status` int DEFAULT NULL,
                        `booking_status` int DEFAULT NULL,
                        `condition_status` int DEFAULT NULL,
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
                         `status` int DEFAULT NULL,
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
                         PRIMARY KEY (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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

-- Dump completed on 2023-10-19 12:55:28
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
                            `image` longblob,
                            PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
                        `floor_id` int NOT NULL,
                        `status` int DEFAULT NULL,
                        `booking_status` int DEFAULT NULL,
                        `condition_status` int DEFAULT NULL,
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
                         `status` int DEFAULT NULL,
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
                         PRIMARY KEY (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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

-- Dump completed on 2023-10-19 12:55:28
