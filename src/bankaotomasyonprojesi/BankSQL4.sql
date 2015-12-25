-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.6.26


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema bank
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ bank;
USE bank;

--
-- Table structure for table `bank`.`hesap`
--

DROP TABLE IF EXISTS `hesap`;
CREATE TABLE `hesap` (
  `hesap_no` int(11) NOT NULL AUTO_INCREMENT,
  `hesap_tur` varchar(45) NOT NULL DEFAULT '0',
  `hesap_bakiye` float NOT NULL DEFAULT '0',
  `hesap_doviz_cinsi` varchar(45) NOT NULL DEFAULT '',
  `hesap_durum` varchar(45) NOT NULL DEFAULT '',
  `hesap_vade_basi` date DEFAULT NULL,
  `hesap_vade_sonu` date DEFAULT NULL,
  `hesap_vade_tutari` float DEFAULT NULL,
  `hesap_vade_tipi` varchar(45) DEFAULT NULL,
  `hesap_vade_suresi` int(11) DEFAULT NULL,
  `hesap_vade_faiz_orani` float DEFAULT NULL,
  `musteri_no` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`hesap_no`),
  KEY `FK_hesap_1` (`musteri_no`),
  CONSTRAINT `FK_hesap_1` FOREIGN KEY (`musteri_no`) REFERENCES `musteri` (`musteri_no`)
) ENGINE=InnoDB AUTO_INCREMENT=121242 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bank`.`hesap`
--

/*!40000 ALTER TABLE `hesap` DISABLE KEYS */;
INSERT INTO `hesap` (`hesap_no`,`hesap_tur`,`hesap_bakiye`,`hesap_doviz_cinsi`,`hesap_durum`,`hesap_vade_basi`,`hesap_vade_sonu`,`hesap_vade_tutari`,`hesap_vade_tipi`,`hesap_vade_suresi`,`hesap_vade_faiz_orani`,`musteri_no`) VALUES 
 (12,'vadesiz',0,'TL','aktif',NULL,NULL,0,NULL,0,0,13),
 (123,'vadeli',0,'TL','aktif','2015-12-19','2015-12-30',123132,'asdads',12,5,13),
 (124,'vadeli',0,'DOLAR','aktif','2015-12-25','2015-12-20',123,'asd',13,123123,13),
 (1212,'vadesiz',0,'TL','aktif',NULL,NULL,0,NULL,0,0,2),
 (121241,'vadesiz',0,'DOLAR','aktif',NULL,NULL,0,NULL,0,0,2);
/*!40000 ALTER TABLE `hesap` ENABLE KEYS */;


--
-- Table structure for table `bank`.`kasa`
--

DROP TABLE IF EXISTS `kasa`;
CREATE TABLE `kasa` (
  `kasa_id` int(11) NOT NULL,
  `kasa_para` float NOT NULL,
  PRIMARY KEY (`kasa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bank`.`kasa`
--

/*!40000 ALTER TABLE `kasa` DISABLE KEYS */;
INSERT INTO `kasa` (`kasa_id`,`kasa_para`) VALUES 
 (-1,1000000),
 (1,5390),
 (2,21013),
 (3,12),
 (4,0);
/*!40000 ALTER TABLE `kasa` ENABLE KEYS */;


--
-- Table structure for table `bank`.`kategori`
--

DROP TABLE IF EXISTS `kategori`;
CREATE TABLE `kategori` (
  `id` int(11) NOT NULL,
  `kategori_ad` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bank`.`kategori`
--

/*!40000 ALTER TABLE `kategori` DISABLE KEYS */;
INSERT INTO `kategori` (`id`,`kategori_ad`) VALUES 
 (1,'Bilgisayar'),
 (2,'Giyim'),
 (3,'Kozmetik');
/*!40000 ALTER TABLE `kategori` ENABLE KEYS */;


--
-- Table structure for table `bank`.`log`
--

DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `statu` tinyint(1) NOT NULL,
  `personel_id` int(11) NOT NULL,
  `musteri_no` int(11) DEFAULT NULL,
  `hesap_no` int(11) DEFAULT NULL,
  `kasa_id` int(11) DEFAULT NULL,
  `miktar` float DEFAULT NULL,
  `olay` varchar(45) NOT NULL,
  `tarih` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_log_1` (`personel_id`),
  KEY `FK_log_2` (`musteri_no`),
  KEY `FK_log_3` (`hesap_no`),
  KEY `FK_log_4` (`kasa_id`),
  CONSTRAINT `FK_log_1` FOREIGN KEY (`personel_id`) REFERENCES `personel` (`personel_id`),
  CONSTRAINT `FK_log_2` FOREIGN KEY (`musteri_no`) REFERENCES `musteri` (`musteri_no`),
  CONSTRAINT `FK_log_3` FOREIGN KEY (`hesap_no`) REFERENCES `hesap` (`hesap_no`),
  CONSTRAINT `FK_log_4` FOREIGN KEY (`kasa_id`) REFERENCES `kasa` (`kasa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bank`.`log`
--

/*!40000 ALTER TABLE `log` DISABLE KEYS */;
/*!40000 ALTER TABLE `log` ENABLE KEYS */;


--
-- Table structure for table `bank`.`musteri`
--

DROP TABLE IF EXISTS `musteri`;
CREATE TABLE `musteri` (
  `musteri_no` int(11) NOT NULL AUTO_INCREMENT,
  `musteri_tc` int(11) NOT NULL,
  `musteri_ad` varchar(45) NOT NULL,
  `musteri_soyad` varchar(45) NOT NULL,
  `musteri_guvenlik_no` int(11) NOT NULL,
  `musteri_baba` varchar(45) NOT NULL,
  `musteri_anne` varchar(45) NOT NULL,
  `musteri_dogum_tarihi` date NOT NULL DEFAULT '0000-00-00',
  `musteri_dogum_yeri` varchar(45) NOT NULL,
  `musteri_ogrenim` varchar(45) DEFAULT NULL,
  `musteri_universite` varchar(45) DEFAULT NULL,
  `musteri_sektor` varchar(45) DEFAULT NULL,
  `musteri_unvan` varchar(45) DEFAULT NULL,
  `musteri_cep` varchar(15) DEFAULT NULL,
  `musteri_is` varchar(15) NOT NULL,
  `musteri_ev` varchar(15) NOT NULL,
  `musteri_adres` text NOT NULL,
  PRIMARY KEY (`musteri_no`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bank`.`musteri`
--

/*!40000 ALTER TABLE `musteri` DISABLE KEYS */;
INSERT INTO `musteri` (`musteri_no`,`musteri_tc`,`musteri_ad`,`musteri_soyad`,`musteri_guvenlik_no`,`musteri_baba`,`musteri_anne`,`musteri_dogum_tarihi`,`musteri_dogum_yeri`,`musteri_ogrenim`,`musteri_universite`,`musteri_sektor`,`musteri_unvan`,`musteri_cep`,`musteri_is`,`musteri_ev`,`musteri_adres`) VALUES 
 (2,213213,'asas','asads',123213,'asdas','asads','2015-12-24','assaddas',NULL,NULL,NULL,NULL,'213132','123132','132213','adsads'),
 (3,213213,'asas','asads',123213,'asdas','asads','2015-12-24','assaddas',NULL,NULL,NULL,NULL,'213132','123132','132213','adsads'),
 (5,213213,'asas','asads',123213,'asdas','asads','2015-12-24','assaddas',NULL,NULL,NULL,NULL,'213132','123132','132213','adsads'),
 (7,213213,'asas','asads',123213,'asdas','asads','2015-12-24','assaddas',NULL,NULL,NULL,NULL,'213132','123132','132213','adsads'),
 (8,213213,'asas','asads',123213,'asdas','asads','2015-12-24','assaddas',NULL,NULL,NULL,NULL,'213132','123132','132213','adsads'),
 (9,213213,'asas','asads',123213,'asdas','asads','2015-12-24','assaddas',NULL,NULL,NULL,NULL,'213132','123132','132213','adsads'),
 (11,213213,'asas','asads',123213,'asdas','asads','2015-12-24','assaddas',NULL,NULL,NULL,NULL,'213132','123132','132213','adsads');
INSERT INTO `musteri` (`musteri_no`,`musteri_tc`,`musteri_ad`,`musteri_soyad`,`musteri_guvenlik_no`,`musteri_baba`,`musteri_anne`,`musteri_dogum_tarihi`,`musteri_dogum_yeri`,`musteri_ogrenim`,`musteri_universite`,`musteri_sektor`,`musteri_unvan`,`musteri_cep`,`musteri_is`,`musteri_ev`,`musteri_adres`) VALUES 
 (13,213213,'asas','asads',123213,'asdas','asads','2015-12-24','assaddas',NULL,NULL,NULL,NULL,'213132','123132','132213','adsads'),
 (15,213213,'asas','asads',123213,'asdas','asads','2015-12-24','assaddas',NULL,NULL,NULL,NULL,'213132','123132','132213','adsads'),
 (19,213213,'asas','asads',123213,'asdas','asads','2015-12-24','assaddas',NULL,NULL,NULL,NULL,'213132','123132','132213','adsads'),
 (20,213213,'asas','asads',123213,'asdas','asads','2015-12-24','assaddas',NULL,NULL,NULL,NULL,'213132','123132','132213','adsads'),
 (21,213213,'asas','asads',123213,'asdas','asads','2015-12-24','assaddas',NULL,NULL,NULL,NULL,'213132','123132','132213','adsads'),
 (22,213213,'asas','asads',123213,'asdas','asads','2015-12-24','assaddas',NULL,NULL,NULL,NULL,'213132','123132','132213','adsads'),
 (23,12345,'zzzz','zzz',123213,'asdas','asads','2015-12-24','assaddas','Lise mezunu',NULL,NULL,NULL,'213132','132213','123132','adsads');
INSERT INTO `musteri` (`musteri_no`,`musteri_tc`,`musteri_ad`,`musteri_soyad`,`musteri_guvenlik_no`,`musteri_baba`,`musteri_anne`,`musteri_dogum_tarihi`,`musteri_dogum_yeri`,`musteri_ogrenim`,`musteri_universite`,`musteri_sektor`,`musteri_unvan`,`musteri_cep`,`musteri_is`,`musteri_ev`,`musteri_adres`) VALUES 
 (24,213213,'zzzz','zzzzz',123213,'asdas','asads','2015-12-24','assaddas',NULL,NULL,NULL,NULL,'213132','132213','123132','adsads'),
 (25,12345,'xczxczxc','zzz',123213,'asdas','asads','2015-12-24','assaddas','Lise mezunu',NULL,NULL,NULL,'213132','123132','132213','adsads'),
 (26,12345,'zzzzzxczxczxcczx','zzz',123213,'asdas','asads','2015-12-24','assaddas','Lise mezunu',NULL,NULL,NULL,'213132','123132','132213','adsads'),
 (28,213213,'MMMM','asads',123213,'asdas','asads','2015-12-24','assaddas',NULL,NULL,NULL,NULL,'213132','132213','123132','adsads');
/*!40000 ALTER TABLE `musteri` ENABLE KEYS */;


--
-- Table structure for table `bank`.`personel`
--

DROP TABLE IF EXISTS `personel`;
CREATE TABLE `personel` (
  `personel_id` int(11) NOT NULL AUTO_INCREMENT,
  `personel_sifre` varchar(45) NOT NULL,
  `personel_tc` int(11) NOT NULL,
  `personel_ad` varchar(45) NOT NULL,
  `personel_soyad` varchar(45) NOT NULL,
  `personel_guvenlik` int(11) NOT NULL,
  `personel_baba` varchar(45) NOT NULL,
  `personel_anne` varchar(45) NOT NULL,
  `personel_dogum_tarihi` date NOT NULL,
  `personel_dogum_yeri` varchar(45) NOT NULL,
  `personel_ogrenim` varchar(45) DEFAULT NULL,
  `personel_universite` varchar(45) DEFAULT NULL,
  `personel_cep` varchar(45) NOT NULL,
  `personel_is` varchar(45) DEFAULT NULL,
  `personel_ev` varchar(45) DEFAULT NULL,
  `personel_adres` varchar(45) NOT NULL,
  `personel_yonetici` tinyint(1) NOT NULL DEFAULT '0',
  `kasa_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`personel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=506 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bank`.`personel`
--

/*!40000 ALTER TABLE `personel` DISABLE KEYS */;
INSERT INTO `personel` (`personel_id`,`personel_sifre`,`personel_tc`,`personel_ad`,`personel_soyad`,`personel_guvenlik`,`personel_baba`,`personel_anne`,`personel_dogum_tarihi`,`personel_dogum_yeri`,`personel_ogrenim`,`personel_universite`,`personel_cep`,`personel_is`,`personel_ev`,`personel_adres`,`personel_yonetici`,`kasa_id`) VALUES 
 (2,'a',2147483647,'dayım','hacı',412342,'hacı osman','halime','1985-08-13','ankara','lisans','ODTU','05413458947','4444040','0212456987','celebiler mah. 504.sokak no:23 kat:1 Isparta ',0,2),
 (500,'a',1234213123,'admin','reyiz',14234,'admin babasi','admin annesi','1976-07-07','ankara','ilkoklul','ataturk i.ö.o','123123123','321312213','3123123','123123',1,0),
 (501,'a',4213,'asdas','asdasd',123312,'asdads','asdasdsa','1985-06-06','sadas','adsadsads','adsadad','123213','123132','123213','123213132',0,1),
 (502,'a',123213312,'asads','adsads',12321,'dasdas','adsasd','2015-12-18','sadads','saddas','adsdas','13321','21321','sadads','adsdas',0,NULL),
 (503,'a',123213312,'asads','adsads',12321,'dasdas','adsasd','2015-12-18','sadads','saddas','adsdas','13321','21321','sadads','adsdas',0,3),
 (504,'a',123213312,'asads','adsads',12321,'dasdas','adsasd','2015-12-18','sadads','saddas','adsdas','13321','21321','sadads','adsdas',0,4);
/*!40000 ALTER TABLE `personel` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
