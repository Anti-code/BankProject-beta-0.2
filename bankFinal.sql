/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Mehmet
 * Created: Dec 18, 2015
 */

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
-- Create schema bankFinal
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ bankFinal;
USE bankFinal;

--
-- Table structure for table `bankFinal`.`hesap`
--

DROP TABLE IF EXISTS `hesap`;
CREATE TABLE `hesap` (
  `hesap_no` int(11) NOT NULL AUTO_INCREMENT,
  `hesap_tur` tinyint(1) NOT NULL DEFAULT '0',
  `hesap_bakiye` float NOT NULL DEFAULT '0',
  `hesap_doviz_cinsi` varchar(45) NOT NULL DEFAULT '',
  `hesap_durum` varchar(45) NOT NULL DEFAULT '',
  `hesap_vade_basi` date DEFAULT NULL,
  `hesap_vade_sonu` date DEFAULT NULL,
  `hesap_vade_tutari` float DEFAULT NULL,
  `hesap_vade_tipi` varchar(45) DEFAULT NULL,
  `hesap_vade_suresi` date DEFAULT NULL,
  `hesap_vade_faiz_orani` float DEFAULT NULL,
  `musteri_no` int(11) NOT NULL,
  PRIMARY KEY (`hesap_no`),
  KEY `FK_hesap_1` (`musteri_tc`),
  CONSTRAINT `FK_hesap_1` FOREIGN KEY (`musteri_no`) REFERENCES `musteri` (`musteri_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bankFinal`.`hesap`
--

/*!40000 ALTER TABLE `hesap` DISABLE KEYS */;
/*!40000 ALTER TABLE `hesap` ENABLE KEYS */;


--
-- Table structure for table `bankFinal`.`hesap_islem`
--

DROP TABLE IF EXISTS `hesap_islem`;
CREATE TABLE `hesap_islem` (
  `hesap_islem_id` int(11) NOT NULL AUTO_INCREMENT,
  `personel_id` int(11) NOT NULL,
  `hesap_no` int(11) NOT NULL,
  `hesap_islem_tur` varchar(45) NOT NULL,
  `hesap_islem_tarih` date NOT NULL,
  `hesap_islem_para` float NOT NULL,
  `kasa_id` int(11) NOT NULL,
  `musteri_tc` int(11) NOT NULL,
  PRIMARY KEY (`hesap_islem_id`),
  KEY `FK_hesap_islem_1` (`hesap_no`),
  KEY `FK_hesap_islem_2` (`musteri_no`),
  KEY `FK_hesap_islem_3` (`personel_id`),
  KEY `FK_hesap_islem_4` (`kasa_id`),
  CONSTRAINT `FK_hesap_islem_1` FOREIGN KEY (`hesap_no`) REFERENCES `hesap` (`hesap_no`),
  CONSTRAINT `FK_hesap_islem_2` FOREIGN KEY (`musteri_no`) REFERENCES `musteri` (`musteri_no`),
  CONSTRAINT `FK_hesap_islem_3` FOREIGN KEY (`personel_id`) REFERENCES `personel` (`personel_id`),
  CONSTRAINT `FK_hesap_islem_4` FOREIGN KEY (`kasa_id`) REFERENCES `kasa` (`kasa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bankFinal`.`hesap_islem`
--

/*!40000 ALTER TABLE `hesap_islem` DISABLE KEYS */;
/*!40000 ALTER TABLE `hesap_islem` ENABLE KEYS */;


--
-- Table structure for table `bankFinal`.`kasa`
--

DROP TABLE IF EXISTS `kasa`;
CREATE TABLE `kasa` (
  `kasa_id` int(11) NOT NULL,
  `kasa_para` float NOT NULL,
  PRIMARY KEY (`kasa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bankFinal`.`kasa`
--

/*!40000 ALTER TABLE `kasa` DISABLE KEYS */;
/*!40000 ALTER TABLE `kasa` ENABLE KEYS */;


--
-- Table structure for table `bankFinal`.`musteri`
--

DROP TABLE IF EXISTS `musteri`;
CREATE TABLE `musteri` (
  'musteri_no' int(11) NOT NULL DEFAULT '0',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bankFinal`.`musteri`
--

/*!40000 ALTER TABLE `musteri` DISABLE KEYS */;
/*!40000 ALTER TABLE `musteri` ENABLE KEYS */;


--
-- Table structure for table `bankFinal`.`musteri_islem`
--

DROP TABLE IF EXISTS `musteri_islem`;
CREATE TABLE `musteri_islem` (
  `musteri_islem_id` int(11) NOT NULL AUTO_INCREMENT,
  `personel_id` int(11) NOT NULL,
  `musteri_no` int(11) NOT NULL,
  `musteri_islem_tur` varchar(45) NOT NULL,
  `musteri_islem_tarih` date NOT NULL,
  PRIMARY KEY (`musteri_islem_id`),
  KEY `FK_musteri_islem_1` (`personel_id`),
  KEY `FK_musteri_islem_2` (`musteri_no`),
  CONSTRAINT `FK_musteri_islem_1` FOREIGN KEY (`personel_id`) REFERENCES `personel` (`personel_id`),
  CONSTRAINT `FK_musteri_islem_2` FOREIGN KEY (`musteri_no`) REFERENCES `musteri` (`musteri_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bankFinal`.`musteri_islem`
--

/*!40000 ALTER TABLE `musteri_islem` DISABLE KEYS */;
/*!40000 ALTER TABLE `musteri_islem` ENABLE KEYS */;


--
-- Table structure for table `bankFinal`.`personel`
--

DROP TABLE IF EXISTS `personel`;
CREATE TABLE `personel` (
  `personel_id` int(11) NOT NULL AUTO_INCREMENT,
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
  PRIMARY KEY (`personel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bankFinal`.`personel`
--

/*!40000 ALTER TABLE `personel` DISABLE KEYS */;
/*!40000 ALTER TABLE `personel` ENABLE KEYS */;


--
-- Table structure for table `bankFinal`.`personel_islem`
--

DROP TABLE IF EXISTS `personel_islem`;
CREATE TABLE `personel_islem` (
  `personel_islem_id` int(11) NOT NULL AUTO_INCREMENT,
  `personel_id` int(11) DEFAULT NULL,
  `personel_islem_tur` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`personel_islem_id`),
  KEY `FK_personel_islem_1` (`personel_id`),
  CONSTRAINT `FK_personel_islem_1` FOREIGN KEY (`personel_id`) REFERENCES `personel` (`personel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bankFinal`.`personel_islem`
--

/*!40000 ALTER TABLE `personel_islem` DISABLE KEYS */;
/*!40000 ALTER TABLE `personel_islem` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

