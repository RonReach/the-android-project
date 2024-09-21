-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 21, 2024 at 03:35 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `android`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_product`
--

CREATE TABLE `tbl_product` (
  `pid` int(11) NOT NULL,
  `title` varchar(1024) NOT NULL,
  `body` text NOT NULL,
  `price` float NOT NULL,
  `qty` int(11) NOT NULL,
  `image` varchar(2048) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_product`
--

INSERT INTO `tbl_product` (`pid`, `title`, `body`, `price`, `qty`, `image`) VALUES
(5, 'Iphone 14 Pro Max', 'Iphone 14 Pro Max Purple 512gb', 1199, 10, 'https://iplanet.one/cdn/shop/files/iPhone_14_Pro_Deep_Purple_PDP_Image_Position-1a__WWEN_84a81709-e3c4-4452-b39c-18e35ace6f76.jpg?v=1691140977'),
(8, 'iWatch', 'Blue', 350, 10, 'https://applecafe.co.za/wp-content/uploads/2024/01/APPLE-WATCH-SERIES-6-40MM-01-scaled.jpg'),
(9, 'Macbook Pro', 'Black 256gb', 2200, 5, 'https://www.jbhifi.com.au/cdn/shop/products/659878-Product-0-I-638343164404745245_e7277ffd-be10-4f61-9659-8805c6e12a85.jpg?v=1708044175'),
(16, 'Acer Nitro 5', 'SSD 512GB', 800, 5, 'https://m.media-amazon.com/images/I/71USb2YssYL._AC_SL1388_.jpg'),
(17, 'Asus ROG Strix 2024', 'SSD 1TB', 1199, 30, 'https://images.khmer24.co/22-09-11/632344-asus-rog-strix-g17-g713ih-1662913093-67799204-b.jpg'),
(20, 'MacBook Air 13', 'MacBook Air 13 M2 CHIP', 1199, 10, 'https://i5.walmartimages.com/seo/Apple-MacBook-Air-13-3-inch-Laptop-Silver-M1-Chip-8GB-RAM-256GB-storage_056c08d5-2d68-44f2-beb0-dd8a47e2f8e8.2a2a210657937c3c11b37df5be8fa4ad.jpeg'),
(22, 'iPad Pro', 'From $999 or $83.25/mo', 1199, 10, 'https://switch.com.ph/cdn/shop/products/ROSA_iPad_Pro_Cellular_12-9_in_6th_Gen_Silver_PDP_Image_5G_Position-1b.jpg?v=1667032260&width=4000'),
(23, 'AirPods Max', 'The ultimate over‑ear listening experience.', 1000, 5, 'https://m.media-amazon.com/images/I/81jqUPkIVRL._AC_SL1500_.jpg'),
(24, 'AirPods Pro 2', 'Pro-level Active Noise', 1000, 5, 'https://images.khmer24.co/22-10-02/718201-airpod-pro-2-new-1664684624-62835421-b.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_product`
--
ALTER TABLE `tbl_product`
  ADD PRIMARY KEY (`pid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_product`
--
ALTER TABLE `tbl_product`
  MODIFY `pid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
