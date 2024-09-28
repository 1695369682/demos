/*
 Navicat Premium Data Transfer

 Source Server         : mysql80
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : rbac

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 27/09/2024 21:43:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '总经办', 'BOSS');
INSERT INTO `department` VALUES (2, '人力资源部', 'HD');
INSERT INTO `department` VALUES (3, '开发部', 'DD');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL,
  `admin` bit(1) NULL DEFAULT NULL,
  `dept_id` bigint NULL DEFAULT NULL,
  `salt` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, 'admin', 'admin', '1', 'admin@qq.com', 18, b'1', 1, 1);
INSERT INTO `employee` VALUES (2, 'cc', '曹操', '1', 'cc@qq.com', 46, b'0', 1, 1);
INSERT INTO `employee` VALUES (3, 'xy', '荀彧', '1', 'xy@qq.com', 33, b'0', 2, 1);
INSERT INTO `employee` VALUES (4, 'ly', '刘晔', '1', 'ly@qq.com', 27, b'0', 3, 1);

-- ----------------------------
-- Table structure for employee_role
-- ----------------------------
DROP TABLE IF EXISTS `employee_role`;
CREATE TABLE `employee_role`  (
  `employee_id` bigint NULL DEFAULT NULL,
  `role_id` bigint NULL DEFAULT NULL,
  INDEX `FK_XXX`(`employee_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee_role
-- ----------------------------
INSERT INTO `employee_role` VALUES (2, 1);
INSERT INTO `employee_role` VALUES (3, 2);
INSERT INTO `employee_role` VALUES (4, 3);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '角色新增或修改', 'role:saveOrUpdate');
INSERT INTO `permission` VALUES (2, '部门查询', 'department:list');
INSERT INTO `permission` VALUES (3, '部门删除', 'department:delete');
INSERT INTO `permission` VALUES (4, '权限查询', 'permission:list');
INSERT INTO `permission` VALUES (5, '员工查询', 'employee:list');
INSERT INTO `permission` VALUES (6, '角色查询', 'role:list');
INSERT INTO `permission` VALUES (7, '部门新增或修改', 'department:saveOrUpdate');
INSERT INTO `permission` VALUES (8, '角色删除', 'role:delete');
INSERT INTO `permission` VALUES (9, '员工删除', 'employee:delete');
INSERT INTO `permission` VALUES (10, '员工新增或修改', 'employee:saveOrUpdate');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '老板', 'BOSS');
INSERT INTO `role` VALUES (2, '人事', 'HR');
INSERT INTO `role` VALUES (3, '技术经理', 'MGR');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `role_id` bigint NULL DEFAULT NULL,
  `permission_id` bigint NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1, 3);
INSERT INTO `role_permission` VALUES (1, 2);
INSERT INTO `role_permission` VALUES (1, 7);
INSERT INTO `role_permission` VALUES (1, 9);
INSERT INTO `role_permission` VALUES (1, 5);
INSERT INTO `role_permission` VALUES (1, 10);
INSERT INTO `role_permission` VALUES (1, 4);
INSERT INTO `role_permission` VALUES (1, 8);
INSERT INTO `role_permission` VALUES (1, 6);
INSERT INTO `role_permission` VALUES (1, 1);
INSERT INTO `role_permission` VALUES (2, 9);
INSERT INTO `role_permission` VALUES (2, 5);
INSERT INTO `role_permission` VALUES (2, 10);
INSERT INTO `role_permission` VALUES (3, 3);
INSERT INTO `role_permission` VALUES (3, 2);
INSERT INTO `role_permission` VALUES (3, 7);
INSERT INTO `role_permission` VALUES (3, 9);
INSERT INTO `role_permission` VALUES (3, 5);
INSERT INTO `role_permission` VALUES (3, 10);

SET FOREIGN_KEY_CHECKS = 1;
