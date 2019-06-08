/*
SQLyog 企业版 - MySQL GUI v8.0
MySQL - 5.5.10 : Database - insuredinfodb
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`insureinfodb` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `insureinfodb`;

/*Table structure for table `backend_user` */

DROP TABLE IF EXISTS `backend_user`;

CREATE TABLE `backend_user` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `userCode` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户编码',
  `userName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名称',
  `userType` bigint(30) DEFAULT NULL COMMENT '用户角色类型（来源于数据字典表，分为：超管、审核总管、审核员）',
  `createdBy` bigint(30) DEFAULT NULL COMMENT '创建者（来源于backend_user用户表的用户id）',
  `creationDate` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyBy` bigint(30) DEFAULT NULL COMMENT '更新者（来源于backend_user用户表的用户id）',
  `modifyDate` datetime DEFAULT NULL COMMENT '最新更新时间',
  `userPassword` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `backend_user` */

insert  into `backend_user`(`id`,`userCode`,`userName`,`userType`,`createdBy`,`creationDate`,`modifyBy`,`modifyDate`,`userPassword`) values (1,'admin','系统管理员',1,1,'2016-08-20 00:13:41',NULL,NULL,'123456');

/*Table structure for table `data_dictionary` */

DROP TABLE IF EXISTS `data_dictionary`;

CREATE TABLE `data_dictionary` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `typeCode` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '类型编码',
  `typeName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '类型名称',
  `valueId` bigint(30) DEFAULT NULL COMMENT '类型值ID',
  `valueName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '类型值Name',
  `createdBy` bigint(30) DEFAULT NULL COMMENT '创建者（来源于backend_user用户表的用户id）',
  `creationDate` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyBy` bigint(30) DEFAULT NULL COMMENT '更新者（来源于backend_user用户表的用户id）',
  `modifyDate` datetime DEFAULT NULL COMMENT '最新更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `data_dictionary` */

insert  into `data_dictionary`(`id`,`typeCode`,`typeName`,`valueId`,`valueName`,`createdBy`,`creationDate`,`modifyBy`,`modifyDate`) values (1,'USER_TYPE','用户类型',1,'超级管理员',1,'2019-01-13 18:11:47',NULL,NULL),(2,'USER_TYPE','用户类型',2,'审核总管',1,'2019-01-13 18:11:47',NULL,NULL),(3,'USER_TYPE','用户类型',3,'审核员',1,'2019-01-13 18:11:47',NULL,NULL),(4,'USER_TYPE','用户类型',4,'销售',1,'2019-01-13 18:11:47',NULL,NULL),(5,'USER_TYPE','用户类型',5,'用户',1,'2019-01-13 18:11:47',NULL,NULL),(6,'POLICY_STATUS','保单状态',1,'草稿',1,'2019-01-13 18:11:47',NULL,NULL),(7,'POLICY_STATUS','保单状态',2,'待审核',1,'2019-01-13 18:11:47',NULL,NULL),(8,'POLICY_STATUS','保单状态',3,'审核未通过',1,'2019-01-13 18:11:47',NULL,NULL),(9,'POLICY_STATUS','保单状态',4,'审核通过',1,'2019-01-13 18:11:47',NULL,NULL),(10,'POLICY_STATUS','保单状态',5,'已生效保单',1,'2019-01-13 18:11:47',NULL,NULL),(11,'PAYMENT_TYPE','支付方式',1,'现金',1,'2019-01-13 18:11:47',NULL,NULL),(12,'PAYMENT_TYPE','支付方式',2,'支付宝/微信',1,'2019-01-13 18:11:47',NULL,NULL),(13,'PAYMENT_TYPE','支付方式',3,'信用卡',1,'2019-01-13 18:11:47',NULL,NULL);

/*Table structure for table `sales_user` */

DROP TABLE IF EXISTS `sales_user`;

CREATE TABLE `sales_user` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `salesCode` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '销售员帐号',
  `salesName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '销售员名称',
  `salesPassword` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '销售员密码',
  `salesEmail` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '销售员电子邮箱',
  `salesInfo` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '销售员简介',
  `createdBy` bigint(30) DEFAULT NULL COMMENT '创建者（来源于backend_user用户表的用户id）',
  `creationDate` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyBy` bigint(30) DEFAULT NULL COMMENT '更新者（来源于backend_user用户表的用户id）',
  `modifyDate` datetime DEFAULT NULL COMMENT '最新更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `sales_user` */

insert  into `sales_user`(`id`,`salesCode`,`salesName`,`salesPassword`,`salesEmail`,`salesInfo`,`createdBy`,`creationDate`,`modifyBy`,`modifyDate`) values (1,'test001','测试账户001','123456',NULL,NULL,1,'2016-08-20 00:13:41',NULL,NULL);

/*Table structure for table `insured_info` */

DROP TABLE IF EXISTS `insured_info`;

CREATE TABLE `insured_info` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `buyerName` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '购买保险人姓名',
  `buyerID` varchar(18) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '购买保险人身份证号',
  `buyerPhone` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '购买保险人联系方式',
  `policyStatus` bigint(10) DEFAULT NULL COMMENT '状态（来源于：data_dictionary，1 草稿 2 待审核 3 审核未通过 4 审核已通过 5 已生效保单）',
  `hasCA` bit(1) DEFAULT NULL COMMENT '是否需要购买车险（默认值false：否(0)，true：是(1)）',
  `hasGL` bit(1) DEFAULT NULL COMMENT '是否需要购买寿险（默认值false：否(0)，true：是(1)）',
  `commentCom` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注及风险信息',
  `updateDate` date DEFAULT NULL COMMENT '变更日期',
  `startDate` datetime DEFAULT NULL COMMENT '开始时间',
  `endDate` datetime DEFAULT NULL COMMENT '结束时间',
  `beneficiaryName` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '受益人姓名',
  `premium` bigint(30) DEFAULT NULL COMMENT '保费',
  `paymentType` bigint(30) DEFAULT NULL COMMENT '支付方式（来源于：data_dictionary）',
  `isApproved` bit(1) DEFAULT NULL COMMENT '是否被审核通过（默认值false：否(0)，true：是(1)）',
  `isPaid` bit(1) DEFAULT NULL COMMENT '是否已经付款（默认值false：否(0)，true：是(1)）',
  `createdBy` bigint(30) DEFAULT NULL COMMENT '创建者（来源于sales_user开发者信息表的用户id）',
  `creationDate` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyBy` bigint(30) DEFAULT NULL COMMENT '更新者（来源于sales_user开发者信息表的用户id）',
  `modifyDate` datetime DEFAULT NULL COMMENT '最新更新时间',
  `versionId` bigint(30) DEFAULT NULL COMMENT '最新的版本id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `insured_info` */

insert  into `insured_info`(`id`,`buyerName`,`buyerID`,`buyerPhone`,`policyStatus`,`hasCA`,`hasGL`,`commentCom`,`updateDate`,`startDate`,`endDate`,`beneficiaryName`,`premium`,`paymentType`,`isApproved`,`isPaid`,`createdBy`,`creationDate`,`modifyBy`,`modifyDate`,`versionId`) values
  (5001,'小电荔','310105201506292345','18616666666',2,1,1,'It is 0',NULL,'2019-05-24 18:11:47','2020-05-24 18:11:47','大电力',1700,1,1,1,'test001','2019-05-24 15:11:47',NULL,NULL,1),
  (5002,'小电荔','310105201506292345','18616666666',5,0,0,'It is 1',NULL,'2019-05-24 18:11:47','2020-05-24 18:11:47','大电力',2000,1,1,1,'test001','2019-05-24 15:11:47',NULL,NULL,1),
  (5003,'小橙子','310105201506292345','18616666666',5,1,1,'It is 2',NULL,'2019-05-24 18:11:47','2020-05-24 18:11:47','大电力',1500,1,1,1,'test001','2019-05-24 15:11:47',NULL,NULL,1),
  (5004,'大西瓜','310105201506292345','18616666666',4,1,1,'It is 3',NULL,'2019-05-24 18:11:47','2020-05-24 18:11:47','大电力',1700,1,1,1,'test001','2019-05-24 15:11:47',NULL,NULL,1),
  (5005,'小芒果','310105201506292345','18616666666',4,1,1,'It is 4',NULL,'2019-05-25 18:11:47','2020-05-25 18:11:47','大电力',2600,1,1,1,'test001','2019-05-25 15:11:47',NULL,NULL,1),
  (5006,'小番茄','310105201506292345','18616666666',5,1,1,'It is 5',NULL,'2019-05-25 18:11:47','2020-05-25 18:11:47','大电力',2000,1,1,1,'test001','2019-05-25 15:11:47',NULL,NULL,1),
  (5007,'小橘子','310105201506292345','18616666666',5,1,1,'It is 6',NULL,'2019-05-25 18:11:47','2020-05-25 18:11:47','大电力',2100,1,1,1,'test001','2019-05-25 15:11:47',NULL,NULL,1),
  (5008,'小生梨','310105201506292345','18616666666',2,1,1,'It is 7',NULL,'2019-05-26 18:11:47','2020-05-26 18:11:47','大电力',2100,1,1,1,'test001','2019-05-26 15:11:47',NULL,NULL,1),
  (5009,'小可爱','310105201506292345','18616666666',3,1,1,'It is 8',NULL,'2019-05-26 18:11:47','2020-05-26 18:11:47','大电力',2400,1,1,1,'test001','2019-05-26 15:11:47',NULL,NULL,1),
  (5010,'小电荔','310105201506292345','18616666666',4,1,1,'It is 9',NULL,'2019-05-27 18:11:47','2020-05-27 18:11:47','大电力',2100,1,1,1,'test001','2019-05-27 15:11:47',NULL,NULL,1),
  (5011,'小电荔','310105201506292345','18616666666',2,1,1,'It is 10',NULL,'2019-05-28 18:11:47','2020-05-28 18:11:47','大电力',2400,1,1,1,'test001','2019-05-28 15:11:47',NULL,NULL,1),
  (5012,'小电荔','310105201506292345','18616666666',3,1,1,'It is 11',NULL,'2019-05-29 18:11:47','2020-05-29 18:11:47','大电力',3600,1,1,1,'test001','2019-05-29 15:11:47',NULL,NULL,1),
  (5013,'小电荔','310105201506292345','18616666666',2,1,1,'It is 12',NULL,'2019-05-29 18:11:47','2020-05-29 18:11:47','大电力',2700,1,1,1,'test001','2019-05-29 15:11:47',NULL,NULL,1),
  (5014,'小电荔','310105201506292345','18616666666',4,1,1,'It is 13',NULL,'2019-05-30 18:11:47','2020-05-30 18:11:47','大电力',2300,1,1,1,'test001','2019-05-30 15:11:47',NULL,NULL,1),
  (5015,'小电荔','310105201506292345','18616666666',4,1,1,'It is 14',NULL,'2019-05-30 18:11:47','2020-05-30 18:11:47','大电力',4100,1,1,1,'test001','2019-05-30 15:11:47',NULL,NULL,1),
  (5016,'小电荔','310105201506292345','18616666666',2,1,1,'It is 15',NULL,'2019-05-30 18:11:47','2020-05-30 18:11:47','大电力',4800,1,1,1,'test001','2019-05-30 15:11:47',NULL,NULL,1),
  (5017,'小电荔','310105201506292345','18616666666',5,1,1,'It is 16',NULL,'2019-05-31 18:11:47','2020-05-31 18:11:47','大电力',2600,1,1,1,'test001','2019-05-31 15:11:47',NULL,NULL,1),
  (5018,'小电荔','310105201506292345','18616666666',3,1,1,'It is 17',NULL,'2019-05-31 18:11:47','2020-05-31 18:11:47','大电力',4800,1,1,1,'test001','2019-05-31 15:11:47',NULL,NULL,1),
  (5019,'小电荔','310105201506292345','18616666666',4,1,1,'It is 18',NULL,'2019-05-31 18:11:47','2020-05-31 18:11:47','大电力',2300,1,1,1,'test001','2019-05-31 15:11:47',NULL,NULL,1),
  (5020,'小电荔','310105201506292345','18616666666',2,1,1,'It is 19',NULL,'2019-05-31 18:11:47','2020-05-31 18:11:47','大电力',4900,1,1,1,'test001','2019-05-31 15:11:47',NULL,NULL,1),
  (5021,'小电荔','310105201506292345','18616666666',4,1,1,'It is 20',NULL,'2019-06-01 18:11:47','2020-06-01 18:11:47','大电力',4500,1,1,1,'test001','2019-06-01 15:11:47',NULL,NULL,1),
  (5022,'小电荔','310105201506292345','18616666666',4,1,1,'It is 21',NULL,'2019-06-01 18:11:47','2020-06-01 18:11:47','大电力',2700,1,1,1,'test001','2019-06-01 15:11:47',NULL,NULL,1),
  (5023,'小电荔','310105201506292345','18616666666',4,1,1,'It is 22',NULL,'2019-06-01 18:11:47','2020-06-01 18:11:47','大电力',5100,1,1,1,'test001','2019-06-01 15:11:47',NULL,NULL,1),
  (5024,'小电荔','310105201506292345','18616666666',4,1,1,'It is 23',NULL,'2019-06-01 18:11:47','2020-06-01 18:11:47','大电力',3700,1,1,1,'test001','2019-06-01 15:11:47',NULL,NULL,1),
  (5025,'小电荔','310105201506292345','18616666666',2,1,1,'It is 24',NULL,'2019-06-01 18:11:47','2020-06-01 18:11:47','大电力',5100,1,1,1,'test001','2019-06-01 15:11:47',NULL,NULL,1),
  (5026,'小电荔','310105201506292345','18616666666',4,1,1,'It is 25',NULL,'2019-06-02 18:11:47','2020-06-02 18:11:47','大电力',5700,1,1,1,'test001','2019-06-02 15:11:47',NULL,NULL,1),
  (5027,'小电荔','310105201506292345','18616666666',4,1,1,'It is 26',NULL,'2019-06-02 18:11:47','2020-06-02 18:11:47','大电力',5300,1,1,1,'test001','2019-06-02 15:11:47',NULL,NULL,1),
  (5028,'小电荔','310105201506292345','18616666666',2,1,1,'It is 27',NULL,'2019-06-02 18:11:47','2020-06-02 18:11:47','大电力',4200,1,1,1,'test001','2019-06-02 15:11:47',NULL,NULL,1),
  (5029,'小电荔','310105201506292345','18616666666',2,1,1,'It is 28',NULL,'2019-06-03 18:11:47','2020-06-03 18:11:47','大电力',4500,1,1,1,'test001','2019-06-03 15:11:47',NULL,NULL,1),
  (5030,'小电荔','310105201506292345','18616666666',4,1,1,'It is 29',NULL,'2019-06-03 18:11:47','2020-06-03 18:11:47','大电力',2800,1,1,1,'test001','2019-06-03 15:11:47',NULL,NULL,1),
  (5031,'小电荔','310105201506292345','18616666666',4,1,1,'It is 30',NULL,'2019-06-04 18:11:47','2020-06-04 18:11:47','大电力',3700,1,1,1,'test001','2019-06-04 15:11:47',NULL,NULL,1),
  (5032,'小电荔','310105201506292345','18616666666',3,1,1,'It is 31',NULL,'2019-06-05 18:11:47','2020-06-05 18:11:47','大电力',2900,1,1,1,'test001','2019-06-05 15:11:47',NULL,NULL,1),
  (5033,'小电荔','310105201506292345','18616666666',5,1,1,'It is 32',NULL,'2019-06-05 18:11:47','2020-06-05 18:11:47','大电力',3800,1,1,1,'test001','2019-06-05 15:11:47',NULL,NULL,1);


/*Table structure for table `CALine_info` */

DROP TABLE IF EXISTS `CALine_info`;

CREATE TABLE `CALine_info` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `insuredId` bigint(30) DEFAULT NULL COMMENT 'insuredId（来源于：insured_info表的主键id）',
  `vehicleModel` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '车辆型号',
  `vehicleBuyInTime` datetime DEFAULT NULL COMMENT '车辆买入时间',
  `limit_Ext` bigint(30) DEFAULT NULL COMMENT '保额赔付上限（元人民币）',
  `carDamage` bit(1) DEFAULT NULL COMMENT '是否购买车损险（默认值false：否(0)，true：是(1)）',
  `carFire` bit(1) DEFAULT NULL COMMENT '是否购买自燃险（默认值false：否(0)，true：是(1)）',
  `carGlass` bit(1) DEFAULT NULL COMMENT '是否购买玻璃险（默认值false：否(0)，true：是(1)）',
  `commentCA` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注及风险信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `CALine_info` */

insert  into `CALine_info`(`id`,`insuredId`,`vehicleModel`,`vehicleBuyInTime`,`limit_Ext`,`carDamage`,`carFire`,`carGlass`) values
  (1,5001,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (2,5002,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (3,5003,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (4,5004,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (5,5005,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (6,5006,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (7,5007,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (8,5008,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (9,5009,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (10,5010,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (11,5011,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (12,5012,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (13,5013,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (14,5014,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (15,5015,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (16,5016,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (17,5017,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (18,5018,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (19,5019,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (20,5020,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (21,5021,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (22,5022,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (23,5023,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (24,5024,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (25,5025,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (26,5026,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (27,5027,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (28,5028,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (29,5029,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (30,5030,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (31,5031,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (32,5032,'model 3','2015-01-13 18:11:47',800000,1,1,1),
  (33,5033,'model 3','2015-01-13 18:11:47',800000,1,1,1);



/*Table structure for table `GLLine_info` */

DROP TABLE IF EXISTS `GLLine_info`;

CREATE TABLE `GLLine_info` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `insuredId` bigint(30) DEFAULT NULL COMMENT 'insuredId（来源于：insured_info表的主键id）',
  `insuredPeople` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '被保人姓名',
  `insuredPeopleID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '被保人身份证',
  `insuredPeopleAge` bigint(30) DEFAULT NULL COMMENT '被保人年龄',
  `insuredPeopleGender`bit(1) DEFAULT NULL COMMENT '被保人性别（false：女(0)，true：男(1)）',
  `limit_Ext` bigint(30) DEFAULT NULL COMMENT '保额赔付上限（元人民币）',
  `peopleAccident` bit(1) DEFAULT NULL COMMENT '是否购买人身意外险（默认值false：否(0)，true：是(1)）',
  `peopleIllness` bit(1) DEFAULT NULL COMMENT  '是否购买重疾险（默认值false：否(0)，true：是(1)）',
  `commentGL` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注及风险信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `GLLine_info` */

insert  into `GLLine_info`(`id`,`insuredId`,`insuredPeople`,`insuredPeopleID`,`insuredPeopleAge`,`insuredPeopleGender`,`limit_Ext`,`peopleAccident`,`peopleIllness`) values
  (1,5001,'张三','310105199810102345',21,1,1000000,1,1),
  (2,5002,'张三','310105199810102345',21,1,1000000,1,1),
  (3,5003,'张三','310105199810102345',21,1,1000000,1,1),
  (4,5004,'张三','310105199810102345',21,1,1000000,1,1),
  (5,5005,'张三','310105199810102345',21,1,1000000,1,1),
  (6,5006,'张三','310105199810102345',21,1,1000000,1,1),
  (7,5007,'张三','310105199810102345',21,1,1000000,1,1),
  (8,5008,'张三','310105199810102345',21,1,1000000,1,1),
  (9,5009,'张三','310105199810102345',21,1,1000000,1,1),
  (10,5010,'张三','310105199810102345',21,1,1000000,1,1),
  (11,5011,'张三','310105199810102345',21,1,1000000,1,1),
  (12,5012,'张三','310105199810102345',21,1,1000000,1,1),
  (13,5013,'张三','310105199810102345',21,1,1000000,1,1),
  (14,5014,'张三','310105199810102345',21,1,1000000,1,1),
  (15,5015,'张三','310105199810102345',21,1,1000000,1,1),
  (16,5016,'张三','310105199810102345',21,1,1000000,1,1),
  (17,5017,'张三','310105199810102345',21,1,1000000,1,1),
  (18,5018,'张三','310105199810102345',21,1,1000000,1,1),
  (19,5019,'张三','310105199810102345',21,1,1000000,1,1),
  (20,5020,'张三','310105199810102345',21,1,1000000,1,1),
  (21,5021,'张三','310105199810102345',21,1,1000000,1,1),
  (22,5022,'张三','310105199810102345',21,1,1000000,1,1),
  (23,5023,'张三','310105199810102345',21,1,1000000,1,1),
  (24,5024,'张三','310105199810102345',21,1,1000000,1,1),
  (25,5025,'张三','310105199810102345',21,1,1000000,1,1),
  (26,5026,'张三','310105199810102345',21,1,1000000,1,1),
  (27,5027,'张三','310105199810102345',21,1,1000000,1,1),
  (28,5028,'张三','310105199810102345',21,1,1000000,1,1),
  (29,5029,'张三','310105199810102345',21,1,1000000,1,1),
  (30,5030,'张三','310105199810102345',21,1,1000000,1,1),
  (31,5031,'张三','310105199810102345',21,1,1000000,1,1),
  (32,5032,'张三','310105199810102345',21,1,1000000,1,1),
  (33,5033,'张三','310105199810102345',21,1,1000000,1,1);


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
