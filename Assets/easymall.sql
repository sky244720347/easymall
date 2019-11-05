-- MySQL dump 10.13  Distrib 5.5.62, for Win64 (AMD64)
--
-- Host: localhost    Database: easymall
-- ------------------------------------------------------
-- Server version	5.5.62

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `orderitem`
--

DROP TABLE IF EXISTS `orderitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderitem` (
  `order_id` varchar(100) NOT NULL DEFAULT '',
  `product_id` varchar(100) NOT NULL DEFAULT '',
  `buynum` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`,`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitem`
--

LOCK TABLES `orderitem` WRITE;
/*!40000 ALTER TABLE `orderitem` DISABLE KEYS */;
INSERT INTO `orderitem` VALUES ('4d7782d6-cbc8-4ba6-8dfb-bf1d9ebc050a','3',1),('83b9b3ec-05bc-4227-b459-24fe7ad4273b','10',1);
/*!40000 ALTER TABLE `orderitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` varchar(100) NOT NULL,
  `money` double DEFAULT NULL,
  `receiverinfo` varchar(255) DEFAULT NULL,
  `paystate` int(11) DEFAULT NULL,
  `ordertime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES ('4d7782d6-cbc8-4ba6-8dfb-bf1d9ebc050a',5,'上海市徐汇区',0,'2019-01-10 11:33:34',9),('83b9b3ec-05bc-4227-b459-24fe7ad4273b',12999,'上海市',1,'2019-01-10 10:22:04',5);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `id` varchar(100) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `category` varchar(100) DEFAULT NULL,
  `imgurl` varchar(255) DEFAULT NULL,
  `pnum` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES ('1','iPhone8',9999,'Phone','/img/cart/iphone8.jpg',500,'超长待机，双卡双待，充电两小时，通话一分钟！'),('10','iPhoneX',12999,'Phone','/img/cart/iphoneX.jpg',301,'比iPhone8更大'),('11','iPad',999,'PC','/img/cart/ipad.jpg',300,'大屏'),('12','Mac Air',5999,'PC','/img/cart/MacAir.jpg',300,'轻便'),('13','MacBook',8999,'PC','/img/cart/MacBook.jpg',300,'好用'),('14','TV',4999,'Home','/img/cart/TV.jpg',300,'色彩艳丽'),('15','信仰充值',0.01,'Money','/img/cart/chongzhi.jpg',10000,'充值信仰。。。'),('2','座垫',29,'Car','/img/cart/zuodian.jpg',300,'舒适'),('3','方便面',5,'Food','/img/cart/fbm.jpg',299,'好吃'),('4','雪碧',8,'Food','/img/cart/xuebi.jpg',300,'好喝'),('5','可乐',8,'Food','/img/cart/kele.jpg',3000,'好喝'),('6','Java编程思想',79,'Book','/img/cart/hongloumeng.jpg',300,'好看'),('7','衬衫',149,'Cloth','/img/cart/tshirt.jpg',300,'帅气'),('8','羽绒服',1999,'Cloth','/img/cart/yurongfu.jpg',300,'保暖'),('9','手机充值',99,'Money','/img/cart/chongzhi.jpg',300,'超值');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `role` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (5,'admin','202cb962ac59075b964b07152d234b70','123','a@b.c','admin'),(6,'li','d70c1e5d44de8a9150eb91ecff563578','li','li@b.c','user'),(8,'user','202cb962ac59075b964b07152d234b70','123','a@b.c','user'),(9,'lilanbin','922f53c64dac91c8ae7bbfab894189f0','li','lilanbin@sohu.com','user'),(11,'heweinan','202cb962ac59075b964b07152d234b70','he','a@b.c','user'),(12,'huangquan','202cb962ac59075b964b07152d234b70','hq','a@b.c','user'),(13,'yanwenhao','202cb962ac59075b964b07152d234b70','yan','a@b.c','user'),(14,'denghuiyong','202cb962ac59075b964b07152d234b70','deng','a@b.c','user'),(15,'shiziyi','202cb962ac59075b964b07152d234b70','shi','a@b.c','user'),(16,'pengwei','202cb962ac59075b964b07152d234b70','peng','a@b.c','user'),(17,'yubing','202cb962ac59075b964b07152d234b70','yu','a@b.c','user');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-18 17:02:24
