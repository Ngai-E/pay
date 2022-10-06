/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 100421
 Source Host           : localhost:3306
 Source Schema         : payment_db

 Target Server Type    : MySQL
 Target Server Version : 100421
 File Encoding         : 65001

 Date: 06/10/2022 08:06:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_country
-- ----------------------------
DROP TABLE IF EXISTS `t_country`;
CREATE TABLE `t_country`  (
  `str_country_code` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `str_country_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `str_dialing_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dt_date_created` datetime(0) NULL DEFAULT NULL,
  `dt_date_updated` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`str_country_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_country
-- ----------------------------

-- ----------------------------
-- Table structure for t_country_payment
-- ----------------------------
DROP TABLE IF EXISTS `t_country_payment`;
CREATE TABLE `t_country_payment`  (
  `lg_country_payment_id` bigint NOT NULL AUTO_INCREMENT,
  `lg_country_id` int NULL DEFAULT NULL,
  `lg_payment_method_id` int NULL DEFAULT NULL,
  `dt_date_created` datetime(0) NULL DEFAULT NULL,
  `dt_date_updated` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`lg_country_payment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_country_payment
-- ----------------------------

-- ----------------------------
-- Table structure for t_paramters
-- ----------------------------
DROP TABLE IF EXISTS `t_paramters`;
CREATE TABLE `t_paramters`  (
  `str_key` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `str_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `b_status` tinyint(1) NULL DEFAULT 1,
  PRIMARY KEY (`str_key`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_paramters
-- ----------------------------
INSERT INTO `t_paramters` VALUES ('CALLBACK_URL', 'localhost:56032/callback', 1);
INSERT INTO `t_paramters` VALUES ('MONETBILL_BASE_URL', 'https://api.monetbil.com/payment/v1', 1);
INSERT INTO `t_paramters` VALUES ('MONETBILL_SERVICE_KEY', 'Pl7v7MG7MEgjtOZp7KqhSgNgnsfeqHeR', 1);
INSERT INTO `t_paramters` VALUES ('MONETBILL_SERVICE_SECRET', 'QmxEdwMULsSoWo87L0rSmUexaUXz9Ia9vpQ2hqt6t3dfaSiqcwjtATMFwdzooPTC', 1);
INSERT INTO `t_paramters` VALUES ('PAYMENT_CALLBACK_URL', 'https://b135-129-0-76-45.ngrok.io/v1/callback/%s/%s', 1);

-- ----------------------------
-- Table structure for t_payment_providers
-- ----------------------------
DROP TABLE IF EXISTS `t_payment_providers`;
CREATE TABLE `t_payment_providers`  (
  `lg_payment_providers` tinyint NOT NULL AUTO_INCREMENT,
  `str_payment_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `db_min_withdrawal_amount` double NULL DEFAULT NULL,
  `db_max_withdrawal_amount` double NULL DEFAULT NULL,
  `db_min_deposit_amount` double NULL DEFAULT NULL,
  `db_max_deposit_amount` double NULL DEFAULT NULL,
  `str_image_url` double NULL DEFAULT NULL,
  `str_payment_type` enum('MOBILE','CREDIT_CARD','BANK_TRANSFER','DEBIT_CARD','DIGITAL_WALLETS') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'MOBILE',
  `dt_date_created` datetime(0) NULL DEFAULT NULL,
  `dt_date_updated` datetime(0) NULL DEFAULT NULL,
  `str_driver_class_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `b_cashin` tinyint(1) NULL DEFAULT 0,
  `b_cashout` tinyint(1) NULL DEFAULT 0,
  `b_active` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`lg_payment_providers`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_payment_providers
-- ----------------------------
INSERT INTO `t_payment_providers` VALUES (1, '100', 100, 10000, 1, 100000, NULL, 'MOBILE', '2022-07-17 01:27:25', NULL, 'MonetBillPayment', 1, 0, 1);

-- ----------------------------
-- Table structure for t_received_callback
-- ----------------------------
DROP TABLE IF EXISTS `t_received_callback`;
CREATE TABLE `t_received_callback`  (
  `lg_trace_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `str_status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dt_date_created` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`lg_trace_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_received_callback
-- ----------------------------
INSERT INTO `t_received_callback` VALUES ('7f5aadb1-3e51-492d-9a76-b6a0677ead22', 'success', '2022-07-17 22:36:21');
INSERT INTO `t_received_callback` VALUES ('bbfabebb-be95-4aa6-907e-837ab9edc145', 'success', '2022-07-17 21:02:19');

-- ----------------------------
-- Table structure for t_trace
-- ----------------------------
DROP TABLE IF EXISTS `t_trace`;
CREATE TABLE `t_trace`  (
  `lg_trace_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `str_phone_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `b_callback_received` tinyint(1) NULL DEFAULT NULL,
  `str_callback_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dt_date_created` datetime(0) NULL DEFAULT NULL,
  `str_originating_transaction` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `db_amount` double NULL DEFAULT NULL,
  `lg_payment_providers` tinyint NULL DEFAULT NULL,
  PRIMARY KEY (`lg_trace_id`) USING BTREE,
  INDEX `t_trace_status_ibfk_1`(`lg_payment_providers`) USING BTREE,
  CONSTRAINT `t_trace_status_ibfk_1` FOREIGN KEY (`lg_payment_providers`) REFERENCES `t_payment_providers` (`lg_payment_providers`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_trace
-- ----------------------------
INSERT INTO `t_trace` VALUES ('4e23de1a-fe83-4089-a6f2-fe43002c5cc4', '237650931636', 0, 'http://localhost:50640/v1/callback/test', '2022-07-17 18:22:02', '12354', 1, 1);
INSERT INTO `t_trace` VALUES ('577644a2-a6de-45ec-aebc-09c56abe538e', '237650931636', 0, 'http://localhost:50640/v1/callback/test', '2022-07-17 18:08:40', '12354', 2, 1);
INSERT INTO `t_trace` VALUES ('60e9b0d3-26ac-4206-ac75-0b3a56528370', '237650931636', 0, 'http://localhost:50640/v1/callback/test', '2022-07-17 18:13:29', '12354', 1, 1);
INSERT INTO `t_trace` VALUES ('626c2940-1ba2-43a2-a43d-6e1c4efe7553', '237650931636', 0, 'http://localhost:50640/v1/callback/test', '2022-07-17 18:08:04', '12354', 2, 1);
INSERT INTO `t_trace` VALUES ('74be1612-7068-460c-a63e-d1982d3408a4', '237650931636', 0, 'string', '2022-07-17 01:42:53', '123456', 100, 1);
INSERT INTO `t_trace` VALUES ('7f5aadb1-3e51-492d-9a76-b6a0677ead22', '237650931636', 1, 'http://localhost:50640/v1/callback/test', '2022-07-17 19:37:39', '12354', 1, 1);
INSERT INTO `t_trace` VALUES ('8d49e340-c853-4c3a-ae5b-b1da3a9a8d0a', '237650931636', 0, 'string', '2022-07-17 01:44:19', '123456', 100, 1);
INSERT INTO `t_trace` VALUES ('98ceb8ed-2291-4f5a-bccf-0d4b001f99d9', '237650931636', 0, 'http://localhost:50640/v1/callback/test', '2022-07-17 19:30:57', '12354', 1, 1);
INSERT INTO `t_trace` VALUES ('b5054aa8-7003-4b04-a3ba-45bd5ed3fa18', '237650931636', 0, 'string', '2022-07-17 01:52:47', '123456', 100, 1);
INSERT INTO `t_trace` VALUES ('bbfabebb-be95-4aa6-907e-837ab9edc145', '237650931636', 1, 'http://localhost:50640/v1/callback/test', '2022-07-17 19:48:27', '12354', 1, 1);
INSERT INTO `t_trace` VALUES ('c75201c9-a858-4faa-8193-7d4cc1478ab0', '237650931636', 0, 'http://localhost:50640/v1/callback/test', '2022-07-17 18:36:42', '12354', 1, 1);
INSERT INTO `t_trace` VALUES ('ce83c73b-26c0-428e-94a9-d525e16cfe11', '237650931636', 0, 'http://localhost:50640/v1/callback/test', '2022-07-17 18:40:11', '12354', 1, 1);
INSERT INTO `t_trace` VALUES ('efbad972-9346-4651-885d-93f89a2c2fef', '237650931636', 0, 'string', '2022-07-17 01:40:41', '123456', 100, 1);

-- ----------------------------
-- Table structure for t_trace_status
-- ----------------------------
DROP TABLE IF EXISTS `t_trace_status`;
CREATE TABLE `t_trace_status`  (
  `lg_trace_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `lg_trace_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dt_date_created` datetime(0) NULL DEFAULT NULL,
  `str_status` enum('PROCESSING','SENT','FAILED','VALIDATED') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `str_external_transaction` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `str_msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `str_ext_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`lg_trace_status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_trace_status
-- ----------------------------
INSERT INTO `t_trace_status` VALUES ('05d6c110-fead-4d68-b38d-fcd81f75c904', 'bbfabebb-be95-4aa6-907e-837ab9edc145', '2022-07-17 21:54:19', 'VALIDATED', '', '', '');
INSERT INTO `t_trace_status` VALUES ('0752ecfd-22cd-41b9-b315-08a9bde13597', 'c75201c9-a858-4faa-8193-7d4cc1478ab0', '2022-07-17 18:36:49', 'SENT', NULL, NULL, NULL);
INSERT INTO `t_trace_status` VALUES ('07bb25b2-61d4-42a5-b0d0-aed0834ad8da', 'bbfabebb-be95-4aa6-907e-837ab9edc145', '2022-07-17 21:14:23', 'VALIDATED', '', '', '');
INSERT INTO `t_trace_status` VALUES ('27065bf9-4b0e-464d-9b79-c90f8408e3ff', 'bbfabebb-be95-4aa6-907e-837ab9edc145', '2022-07-17 20:27:37', 'VALIDATED', '', '', '');
INSERT INTO `t_trace_status` VALUES ('3162e466-e049-4ccd-b75d-9fab0fd7d944', '7f5aadb1-3e51-492d-9a76-b6a0677ead22', '2022-07-17 19:37:39', 'PROCESSING', NULL, NULL, NULL);
INSERT INTO `t_trace_status` VALUES ('32101a1b-238a-4465-8b8b-7ef2f23b0ec7', 'bbfabebb-be95-4aa6-907e-837ab9edc145', '2022-07-17 21:29:21', 'VALIDATED', '', '', '');
INSERT INTO `t_trace_status` VALUES ('3a11e0a0-cee3-4934-8618-8505a61213a2', 'bbfabebb-be95-4aa6-907e-837ab9edc145', '2022-07-17 20:35:17', 'VALIDATED', '', '', '');
INSERT INTO `t_trace_status` VALUES ('3befc850-3c43-4dbf-b01b-732f80843ed6', '577644a2-a6de-45ec-aebc-09c56abe538e', '2022-07-17 18:08:44', 'SENT', NULL, NULL, NULL);
INSERT INTO `t_trace_status` VALUES ('42ab316b-08f0-45f1-b258-49cf945d5932', 'c75201c9-a858-4faa-8193-7d4cc1478ab0', '2022-07-17 18:36:43', 'PROCESSING', NULL, NULL, NULL);
INSERT INTO `t_trace_status` VALUES ('45056ab5-06ee-4c01-a438-17c3a0e34955', 'bbfabebb-be95-4aa6-907e-837ab9edc145', '2022-07-17 19:48:38', 'VALIDATED', '22071719483493225941', 'payment pending', 'REQUEST_ACCEPTED');
INSERT INTO `t_trace_status` VALUES ('4ba15738-10b5-4638-9b55-a44dd5d21ff8', 'bbfabebb-be95-4aa6-907e-837ab9edc145', '2022-07-17 19:48:27', 'PROCESSING', NULL, NULL, NULL);
INSERT INTO `t_trace_status` VALUES ('4c767122-2772-4b0d-a752-f22127f42056', '577644a2-a6de-45ec-aebc-09c56abe538e', '2022-07-17 18:08:41', 'PROCESSING', NULL, NULL, NULL);
INSERT INTO `t_trace_status` VALUES ('585e076e-ea36-4458-95e6-f3c044729a19', '60e9b0d3-26ac-4206-ac75-0b3a56528370', '2022-07-17 18:13:29', 'PROCESSING', NULL, NULL, NULL);
INSERT INTO `t_trace_status` VALUES ('5b23b955-aebf-4c34-b504-e3e6b955f667', '626c2940-1ba2-43a2-a43d-6e1c4efe7553', '2022-07-17 18:08:04', 'PROCESSING', NULL, NULL, NULL);
INSERT INTO `t_trace_status` VALUES ('5da8ff73-1589-40e1-b8ed-31ef378dbe1a', 'ce83c73b-26c0-428e-94a9-d525e16cfe11', '2022-07-17 18:40:55', 'SENT', '22071718401775676613', 'payment pending', 'REQUEST_ACCEPTED');
INSERT INTO `t_trace_status` VALUES ('5ed4d018-1fd4-4ad3-a72e-2d990ee8318e', '626c2940-1ba2-43a2-a43d-6e1c4efe7553', '2022-07-17 18:08:04', 'PROCESSING', NULL, NULL, NULL);
INSERT INTO `t_trace_status` VALUES ('61c8f688-c92f-4720-80ee-21ff1bd2ede3', 'bbfabebb-be95-4aa6-907e-837ab9edc145', '2022-07-17 20:29:11', 'VALIDATED', '', '', '');
INSERT INTO `t_trace_status` VALUES ('65712b29-a293-45c8-b780-8ded121082ca', '8d49e340-c853-4c3a-ae5b-b1da3a9a8d0a', '2022-07-17 01:44:20', 'PROCESSING', NULL, NULL, NULL);
INSERT INTO `t_trace_status` VALUES ('664367f2-974a-4f07-8926-a2ba461e998a', '4e23de1a-fe83-4089-a6f2-fe43002c5cc4', '2022-07-17 18:22:03', 'PROCESSING', NULL, NULL, NULL);
INSERT INTO `t_trace_status` VALUES ('788be367-b3af-4c7b-833b-c51c89cc8857', 'bbfabebb-be95-4aa6-907e-837ab9edc145', '2022-07-17 21:08:16', 'VALIDATED', '', '', '');
INSERT INTO `t_trace_status` VALUES ('8ae73460-95b9-4ba9-b065-f7f149950720', '60e9b0d3-26ac-4206-ac75-0b3a56528370', '2022-07-17 18:13:31', 'SENT', NULL, NULL, NULL);
INSERT INTO `t_trace_status` VALUES ('8d27a187-01ab-4782-b502-27856dc18834', 'bbfabebb-be95-4aa6-907e-837ab9edc145', '2022-07-17 19:48:30', 'SENT', '22071719483493225941', 'payment pending', 'REQUEST_ACCEPTED');
INSERT INTO `t_trace_status` VALUES ('a0cb5acb-ee0f-420f-ba33-238ff20cb197', '4e23de1a-fe83-4089-a6f2-fe43002c5cc4', '2022-07-17 18:22:04', 'SENT', NULL, NULL, NULL);
INSERT INTO `t_trace_status` VALUES ('a51fd590-d5eb-44f0-9f93-dade598153c3', 'bbfabebb-be95-4aa6-907e-837ab9edc145', '2022-07-17 22:02:42', 'VALIDATED', '', '', '');
INSERT INTO `t_trace_status` VALUES ('a523ab86-efc2-4116-a66a-4425698cb077', 'b5054aa8-7003-4b04-a3ba-45bd5ed3fa18', '2022-07-17 01:52:49', 'PROCESSING', NULL, NULL, NULL);
INSERT INTO `t_trace_status` VALUES ('a96a6991-7b8e-4642-aee8-95b383612763', 'b5054aa8-7003-4b04-a3ba-45bd5ed3fa18', '2022-07-17 01:53:52', 'SENT', NULL, NULL, NULL);
INSERT INTO `t_trace_status` VALUES ('af2a34fc-994a-49c7-a99d-1c71e7136354', 'bbfabebb-be95-4aa6-907e-837ab9edc145', '2022-07-17 22:06:21', 'VALIDATED', '', '', '');
INSERT INTO `t_trace_status` VALUES ('b1c00e15-c011-4826-b438-c1c6e0280b8c', 'ce83c73b-26c0-428e-94a9-d525e16cfe11', '2022-07-17 18:40:11', 'PROCESSING', NULL, NULL, NULL);
INSERT INTO `t_trace_status` VALUES ('b368cc65-cd3e-4b5f-b9c8-37b6511d4c49', '7f5aadb1-3e51-492d-9a76-b6a0677ead22', '2022-07-17 22:36:21', 'VALIDATED', '', '', '');
INSERT INTO `t_trace_status` VALUES ('d8fb3bfa-6476-4416-99ed-78524ab3ba7c', 'bbfabebb-be95-4aa6-907e-837ab9edc145', '2022-07-17 22:05:26', 'VALIDATED', '', '', '');
INSERT INTO `t_trace_status` VALUES ('e1a4810a-dfbd-4480-9095-aad67e784335', '7f5aadb1-3e51-492d-9a76-b6a0677ead22', '2022-07-17 19:37:41', 'SENT', '22071719374545992648', 'payment pending', 'REQUEST_ACCEPTED');
INSERT INTO `t_trace_status` VALUES ('e7726ad0-4236-4229-adeb-0c6d634e3f68', '98ceb8ed-2291-4f5a-bccf-0d4b001f99d9', '2022-07-17 19:30:59', 'SENT', '22071719310377563922', 'payment pending', 'REQUEST_ACCEPTED');
INSERT INTO `t_trace_status` VALUES ('ef35a7a2-da0a-4f08-bbb6-81ef9d1a4163', '98ceb8ed-2291-4f5a-bccf-0d4b001f99d9', '2022-07-17 19:30:57', 'PROCESSING', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
