# Host: 127.0.0.1  (Version: 5.5.27)
# Date: 2016-03-21 19:01:25
# Generator: MySQL-Front 5.3  (Build 4.211)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "weixinuser"
#

DROP TABLE IF EXISTS `weixinuser`;
CREATE TABLE `weixinuser` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `wxid` varchar(28) NOT NULL DEFAULT '',
  `wxnick` varchar(30) DEFAULT NULL,
  `telphone` varchar(11) DEFAULT NULL,
  `status` varchar(10) NOT NULL DEFAULT 'online',
  `address` varchar(100) DEFAULT NULL,
  `curaddress` varchar(100) DEFAULT NULL,
  `createtime` date NOT NULL DEFAULT '0000-00-00',
  `lastmodifytime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `wxid` (`wxid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Data for table "weixinuser"
#

INSERT INTO `weixinuser` VALUES (2,'o7elat4UcAdxfQFf9ndT6P9h_YOw','树枝','13958053485','offline','浙江省杭州市',NULL,'2016-03-21','2016-03-21 17:49:49');
