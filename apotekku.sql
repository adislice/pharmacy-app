/*
SQLyog Ultimate v12.5.1 (32 bit)
MySQL - 10.4.11-MariaDB : Database - apotekku
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`apotekku` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `apotekku`;

/*Table structure for table `alkes` */

DROP TABLE IF EXISTS `alkes`;

CREATE TABLE `alkes` (
  `id_alkes` int(11) NOT NULL AUTO_INCREMENT,
  `nama_alkes` varchar(255) NOT NULL,
  `harga` int(11) NOT NULL,
  `diskon` int(11) NOT NULL,
  `gambar` varchar(255) NOT NULL,
  `deskripsi` text DEFAULT NULL,
  PRIMARY KEY (`id_alkes`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `alkes` */

insert  into `alkes`(`id_alkes`,`nama_alkes`,`harga`,`diskon`,`gambar`,`deskripsi`) values 
(1,'Refill Aseptic Gel 500 ml ONEMED',36500,26,'','Aseptic Gel / alkohol dalam bentuk gel yang digunakan sebagai antiseptik cuci tangan tanpa bilas air. Kemasan 500ml refill/isi ulang. Produk langsung dari Showroom OneMed, harga termurah!\r\n\r\nAseptic Gel bisa dipakai di seluruh fasilitas kesehatan yang ada di Indonesia. Infection Control Solution by OneMed, sangat sederhana dan ekonomis!'),
(2,'AutoCheck 3 In 1 Alat Cek Gula Darah',248300,15,'','Cek kadar gula darah, asam urat, dan kolesterol dengan satu alat saja. Praktis tanpa pengkodean berulang-ulang.\r\n\r\nDijamin original dan bergaransi.');

/*Table structure for table `detail_transaksi_alkes` */

DROP TABLE IF EXISTS `detail_transaksi_alkes`;

CREATE TABLE `detail_transaksi_alkes` (
  `id_transaksi` int(11) DEFAULT NULL,
  `id_alkes` int(11) DEFAULT NULL,
  `qty` int(4) DEFAULT NULL,
  KEY `id_transaksi` (`id_transaksi`),
  KEY `id_alkes` (`id_alkes`),
  CONSTRAINT `detail_transaksi_alkes_ibfk_1` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`),
  CONSTRAINT `detail_transaksi_alkes_ibfk_2` FOREIGN KEY (`id_alkes`) REFERENCES `alkes` (`id_alkes`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `detail_transaksi_alkes` */

/*Table structure for table `detail_transaksi_obat` */

DROP TABLE IF EXISTS `detail_transaksi_obat`;

CREATE TABLE `detail_transaksi_obat` (
  `id_transaksi` int(11) DEFAULT NULL,
  `id_obat` int(11) DEFAULT NULL,
  `qty` int(4) DEFAULT NULL,
  KEY `id_transaksi` (`id_transaksi`),
  KEY `id_obat` (`id_obat`),
  CONSTRAINT `detail_transaksi_obat_ibfk_1` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`),
  CONSTRAINT `detail_transaksi_obat_ibfk_2` FOREIGN KEY (`id_obat`) REFERENCES `obat` (`id_obat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `detail_transaksi_obat` */

/*Table structure for table `obat` */

DROP TABLE IF EXISTS `obat`;

CREATE TABLE `obat` (
  `id_obat` int(11) NOT NULL AUTO_INCREMENT,
  `nama_obat` varchar(255) NOT NULL,
  `harga` int(11) NOT NULL,
  `jenis_obat` enum('Sirup','Tablet','Oles') NOT NULL,
  `diskon` int(11) NOT NULL,
  `gambar` varchar(255) NOT NULL,
  `deskripsi` text DEFAULT NULL,
  PRIMARY KEY (`id_obat`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `obat` */

insert  into `obat`(`id_obat`,`nama_obat`,`harga`,`jenis_obat`,`diskon`,`gambar`,`deskripsi`) values 
(1,'STIMUNO FORTE CAP 30S',87104,'Tablet',15,'','STIMUNO FORTE CAP 30S membantu memperbaiki sistem kekebalan tubuh serta membantu merangsang tubuh memproduksi lebih banyak antibodi.');

/*Table structure for table `transaksi` */

DROP TABLE IF EXISTS `transaksi`;

CREATE TABLE `transaksi` (
  `id_transaksi` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) DEFAULT NULL,
  `tanggal` datetime DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `penerima` varchar(255) DEFAULT NULL,
  `alamat_pengiriman` text DEFAULT NULL,
  `no_telp_penerima` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`id_transaksi`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `transaksi` */

insert  into `transaksi`(`id_transaksi`,`id_user`,`tanggal`,`total`,`penerima`,`alamat_pengiriman`,`no_telp_penerima`) values 
(1,1,'2021-12-27 07:09:14',100000,'John Doe','Sanggrahan, Giripurwo, Wonogiri, Jawa Tengah','08123456789');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(60) NOT NULL,
  `email` varchar(100) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `no_telp` varchar(18) NOT NULL,
  `alamat` text DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `user` */

insert  into `user`(`id_user`,`username`,`email`,`nama`,`no_telp`,`alamat`) values 
(1,'johndoe','johndoe@example.com','John Doe','08123456789','Wonogiri');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
