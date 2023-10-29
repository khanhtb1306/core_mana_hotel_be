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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
                         `order_id` varchar(50) NOT NULL AUTO_INCREMENT,
                         `reservation_detail_id` bigint NOT NULL,
                         `total_pay` float DEFAULT NULL,
                         `status` bigint DEFAULT NULL,
                         `created_by_id` bigint DEFAULT NULL,
                         `created_date` timestamp NULL DEFAULT NULL,
                         PRIMARY KEY (`order_id`),
                         KEY `pk_o_rd_idx` (`reservation_detail_id`),
                         CONSTRAINT `pk_o_rd` FOREIGN KEY (`reservation_detail_id`) REFERENCES `reservation_detail` (`reservation_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
                                `quantity` bigint DEFAULT NULL,
                                `price` float DEFAULT NULL,
                                PRIMARY KEY (`order_detail_id`),
                                KEY `pk_od_o_idx` (`order_id`),
                                KEY `pk_od_g_idx` (`goods_id`),
                                CONSTRAINT `pk_od_g` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`),
                                CONSTRAINT `pk_od_o` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
                                     `time_apply` timestamp DEFAULT NULL,
                                     `day_of_week` varchar(255) DEFAULT NULL,
                                     PRIMARY KEY (`price_list_detail_id`),
                                     KEY `pk_pld_pl_idx` (`price_list_id`),
                                     KEY `pk_pld_rc_idx` (`room_category_id`),
                                     CONSTRAINT `pk_pld_pl` FOREIGN KEY (`price_list_id`) REFERENCES `price_list` (`price_list_id`),
                                     CONSTRAINT `pk_pld_rc` FOREIGN KEY (`room_category_id`) REFERENCES `room_category` (`room_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
                               `reservation_id` varchar(50) NOT NULL,
                               `customer_id` varchar(50) NOT NULL,
                               `total_adults` bigint DEFAULT NULL,
                               `total_children` bigint DEFAULT NULL,
                               `check_in_estimate` timestamp NULL DEFAULT NULL,
                               `check_out_estimate` timestamp NULL DEFAULT NULL,
                               `check_in_actual` timestamp NULL DEFAULT NULL,
                               `check_out_actual` timestamp NULL DEFAULT NULL,
                               `status` bigint DEFAULT NULL,
                               `total_deposit` float DEFAULT NULL,
                               `total_price` float DEFAULT NULL,
                               `created_date` timestamp NULL DEFAULT NULL,
                               `created_by_id` bigint DEFAULT NULL,
                               `staff_check_in` bigint DEFAULT NULL,
                               `staff_check_out` bigint DEFAULT NULL,
                               PRIMARY KEY (`reservation_id`),
                               KEY `pk_r_c_idx` (`customer_id`),
                               CONSTRAINT `pk_r_c` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
                                      `start_date` timestamp NULL DEFAULT NULL,
                                      `end_date` timestamp NULL DEFAULT NULL,
                                      `price` float DEFAULT NULL,
                                      `reservation_type` varchar(50) DEFAULT NULL,
                                      PRIMARY KEY (`reservation_detail_id`),
                                      KEY `pk_rd_r_idx` (`room_id`),
                                      KEY `pk__idx` (`reservation_id`),
                                      CONSTRAINT `fk_rd_r` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`reservation_id`),
                                      CONSTRAINT `pk_rd_r` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reservation_detail_room`
--

DROP TABLE IF EXISTS `reservation_detail_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation_detail_room` (
                                           `reservation_detail_room_id` bigint NOT NULL,
                                           `reservation_detail_id` bigint NOT NULL,
                                           `customer_id` varchar(50) NOT NULL,
                                           PRIMARY KEY (`reservation_detail_room_id`),
                                           KEY `pk_rdr_rd_idx` (`reservation_detail_id`),
                                           KEY `pk_rdr_c_idx` (`customer_id`),
                                           CONSTRAINT `pk_rdr_c` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
                                           CONSTRAINT `pk_rdr_rd` FOREIGN KEY (`reservation_detail_id`) REFERENCES `reservation_detail` (`reservation_detail_id`)
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-28  1:06:32
INSERT INTO `room_category` (`room_category_id`, `room_category_name`, `price_by_day`, `price_by_night`, `price_by_hour`, `num_of_adults`, `num_of_children`,`num_max_of_adults`,`num_max_of_children`, `room_area`, `status`, `description`, `image`, `created_by_id`, `updated_by_id`, `created_date`, `updated_date`)
VALUES
('HP000001', 'Phòng cơ bản', 300000, 300000.0, 200000, 2,1,3,2, 20.0, 1, 'Phòng cơ bản', NULL, NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00'),
('HP000002', 'Phòng hạng trung', 700000, 300000.0, 200000, 3,1,4,2, 30.0, 1, 'Phòng hạng trung', NULL, NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00'),
('HP000003', 'Phòng đơn', 400000, 400000.0, 250000, 1,1,2,2, 18.0, 1, 'Phòng đơn', NULL, NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00'),
('HP000004', 'Phòng gia đình', 900000, 500000.0, 300000, 4,1,5,2, 40.0, 1, 'Phòng gia đình', NULL, NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00'),
('HP000005', 'Phòng VIP', 1200000, 800000.0, 500000, 2,1,3,2, 25.0, 1, 'Phòng VIP', NULL, NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00'),
('HP000006', 'Phòng Suite', 1500000, 1000000.0, 600000, 2,1,3,2, 30.0, 1, 'Phòng Suite', NULL, NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00'),
('HP000007', 'Phòng VIP2', 2000000, 1500000.0, 800000, 6,1,7,2, 70.0, 1, 'Phòng VIP2', NULL, NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00'),
('HP000008', 'Phòng Ocean View', 800000, 500000.0, 350000, 3,1,4,1, 35.0, 1, 'Phòng với view biển', NULL, NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00'),
('HP000009', 'Phòng Deluxe Suite', 1800000, 1200000.0, 700000, 2,1,3,2, 35.0, 1, 'Phòng Deluxe Suite', NULL, NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00'),
('HP000010', 'Phòng Tổng Thống', 3000000, 2000000.0, 1000000, 2,1,3,2, 40.0, 1, 'Phòng Phủng', NULL, NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00');

INSERT INTO `floor` (`floor_name`, `status`, `created_by_id`, `updated_by_id`, `created_date`, `updated_date`)
VALUES
('Tầng 1', 1, NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00'),
('Tầng 2', 1, NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00'),
('Tầng 3', 1, NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00'),
('Tầng 4', 1, NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00'),
('Tầng 5', 1, NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00'),
('Tầng 6', 1, NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00'),
('Tầng 7', 1, NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00'),
('Tầng 8', 1, NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00'),
('Tầng 9', 1, NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00'),
('Tầng 10', 1, NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00');


INSERT INTO `room` (`room_id`, `room_name`, `room_category_id`, `floor_id`, `status`, `booking_status`, `condition_status`, `image`, `note`, `created_by_id`, `updated_by_id`, `created_date`, `updated_date`)
VALUES
('P000001', 'P.101', 'HP000001', 1, 1, 'ROOM_EMPTY', 'ROOM_CLEAN', NULL, 'Phòng cơ bản', NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00'),
('P000002', 'P.102', 'HP000001', 1, 1, 'ROOM_EMPTY', 'ROOM_CLEAN', NULL, 'Phòng cơ bản', NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00'),
('P000003', 'P.201', 'HP000002', 2, 1, 'ROOM_EMPTY', 'ROOM_CLEAN', NULL, 'Phòng hạng trung', NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00'),
('P000004', 'P.202', 'HP000002', 2, 1, 'ROOM_EMPTY', 'ROOM_CLEAN', NULL, 'Phòng hạng trung', NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00'),
('P000005', 'P.301', 'HP000003', 3, 1, 'ROOM_EMPTY', 'ROOM_CLEAN', NULL, 'Phòng đơn', NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00'),
('P000006', 'P.302', 'HP000003', 3, 1, 'ROOM_EMPTY', 'ROOM_CLEAN', NULL, 'Phòng đơn', NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00'),
('P000007', 'P.401', 'HP000004', 4, 1, 'ROOM_EMPTY', 'ROOM_CLEAN', NULL, 'Phòng gia đình', NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00'),
('P000008', 'P.402', 'HP000004', 4, 1, 'ROOM_EMPTY', 'ROOM_CLEAN', NULL, 'Phòng gia đình', NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00'),
('P000009', 'P.501', 'HP000005', 5, 1, 'ROOM_EMPTY', 'ROOM_CLEAN', NULL, 'Phòng VIP', NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00'),
('P000010', 'P.502', 'HP000005', 5, 1, 'ROOM_EMPTY', 'ROOM_CLEAN', NULL, 'Phòng VIP', NULL, NULL, '2023-10-16 00:00:00', '2023-10-16 00:00:00');


INSERT INTO `goods`
(`goods_id`, `goods_name`, `goods_category`, `status`, `inventory`, `min_inventory`, `max_inventory`, `note`, `description`, `image`, `created_by_id`, `updated_by_id`, `created_date`, `updated_date`)
VALUES
('SP000001', 'Sprite', true, 1, 100000, 100, 100000, 'Non nước ngọt Sprite', 'Non Sprite thể tích 150ml', NULL, NULL, NULL, '2023-10-17 12:00:00', '2023-10-17 12:00:00'),
('SP000002', 'Coca Cola', true, 1, 100000, 100, 100000, 'Non nước ngọt Coca Cola', 'Non Coca Cola thể tích 150ml', NULL, NULL, NULL, '2023-10-17 12:00:00', '2023-10-17 12:00:00'),
('SP000003', 'Thịt bò khô', true, 1, 100000, 100, 100000, 'Thịt bò khô', 'Thịt bò khô 10 miếng', NULL, NULL, NULL, '2023-10-17 12:00:00', '2023-10-17 12:00:00'),
('SP000004', 'Thuê xe máy', false, 1, NULL, NULL, NULL, 'Xe máy', 'Xe máy đầy xăng', NULL, NULL, NULL, '2023-10-17 12:00:00', '2023-10-17 12:00:00'),
('SP000005', 'Thuê ô tô', false, 1, NULL, NULL, NULL, 'Ô tô', 'Ô tô điện', NULL, NULL, NULL, '2023-10-17 12:00:00', '2023-10-17 12:00:00'),
('SP000006', 'Massage', false, 1, NULL, NULL, NULL, 'Mát xa', 'Mát xa cực phê', NULL, NULL, NULL, '2023-10-17 12:00:00', '2023-10-17 12:00:00'),
('SP000007', 'Mỳ tôm', true, 1, 100000, 100, 100000, 'Mỳ tôm', 'Mỳ hảo hán', NULL, NULL, NULL, '2023-10-17 12:00:00', '2023-10-17 12:00:00'),
('SP000008', 'Bia Huda', true, 1, 100000, 100, 100000, 'Bia', 'Đậm tình miền Trung', NULL, NULL, NULL, '2023-10-17 12:00:00', '2023-10-17 12:00:00'),
('SP000009', 'Bim bim Lays', true, 1, 100000, 100, 100000, 'Bim bim', 'Anh muốn Lays em', NULL, NULL, NULL, '2023-10-17 12:00:00', '2023-10-17 12:00:00'),
('SP000010', 'Cắt tóc', false, 1, NULL, NULL, NULL, 'Cắt tóc', 'Tommy Xiaomi', NULL, NULL, NULL, '2023-10-17 12:00:00', '2023-10-17 12:00:00');

INSERT INTO `goods_unit`
(`goods_unit_name`, `goods_id`, `cost`, `price`, `is_default`)
VALUES
('Lon', 'SP000001', 500, 1000, 1),
('Lon', 'SP000002', 300, 700, 1),
('Gói', 'SP000003', 50, 100, 1),
('Lần', 'SP000004', 0, 900, 1),
('Lần', 'SP000005', 0, 1100, 1),
('Lần', 'SP000006', 0, 750, 1),
('Gói', 'SP000007', 60, 120, 1),
('Lon', 'SP000008', 450, 950, 1),
('Gói', 'SP000009', 600, 1300, 1),
('Lần', 'SP000010', 0, 1200, 1);

INSERT INTO `inventory_check`
(`inventory_check_id`, `time_balance`, `note`, `status`, `created_date`, `created_by_id`)
VALUES
('KK000001', '2023-10-17 12:00:00', 'Kiểm kê lần 1', 5, '2023-10-17 12:00:00', NULL);

INSERT INTO `inventory_check_detail`
(`inventory_check_id`, `goods_id`, `actual_inventory`, `quantity_discrepancy`, `value_discrepancy`, `inventory`, `cost`)
VALUES
('KK000001', 'SP000001', 100000, 0, 0, 100000, 500),
('KK000001', 'SP000002', 100000, 0, 0, 100000, 300),
('KK000001', 'SP000003', 100000, 0, 0, 100000, 50),
('KK000001', 'SP000007', 100000, 0, 0, 100000, 60),
('KK000001', 'SP000008', 100000, 0, 0, 100000, 450),
('KK000001', 'SP000009', 100000, 0, 0, 100000, 600);