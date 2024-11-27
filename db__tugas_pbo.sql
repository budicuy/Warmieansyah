-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 27, 2024 at 05:27 AM
-- Server version: 8.0.30
-- PHP Version: 8.2.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db__tugas_pbo`
--

-- --------------------------------------------------------

--
-- Table structure for table `menu_makanan`
--

CREATE TABLE `menu_makanan` (
  `id` int NOT NULL,
  `nama_menu` varchar(255) DEFAULT NULL,
  `harga` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `menu_makanan`
--

INSERT INTO `menu_makanan` (`id`, `nama_menu`, `harga`) VALUES
(15, 'Sakura Soto 4K', 4000.00),
(16, 'Sakura Goreng 4K', 4000.00),
(17, 'Indomie Goreng 6K', 6000.00),
(18, 'Indomie Rendang 6K', 6000.00),
(19, 'Indomie Goreng Aceh 6K', 6000.00),
(20, 'Indomie Seblak Jeletot', 7000.00),
(21, 'Indomie Kaldu Ayam', 6000.00),
(22, 'Sedap Goreng', 6000.00),
(23, 'Sedap Rasa Soto', 6000.00),
(24, 'Indomie Jumbo', 8000.00),
(25, 'Sarimi isi 2 Kecap', 8000.00),
(26, 'Sarimi isi 2 Ayam Kremes', 8000.00),
(27, 'Sarimi isi 2 Rasa Bakso Sapi', 8000.00),
(28, 'Sukses isi 2 Rasa Ayam Kecap', 8000.00);

-- --------------------------------------------------------

--
-- Table structure for table `menu_minuman`
--

CREATE TABLE `menu_minuman` (
  `id` int NOT NULL,
  `nama_menu` varchar(255) DEFAULT NULL,
  `harga` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `menu_minuman`
--

INSERT INTO `menu_minuman` (`id`, `nama_menu`, `harga`) VALUES
(1, 'Teh Botol', 4000.00),
(2, 'Aqua', 3000.00),
(3, 'Pocari Sweat', 6000.00),
(4, 'Fanta', 4000.00),
(5, 'Sprite', 4000.00),
(6, 'Coca Cola', 4000.00),
(7, 'Fruit Tea', 4000.00),
(8, 'Good Day', 4000.00),
(9, 'Teh Pucuk Harum', 4000.00),
(10, 'Teh Kotak', 4000.00),
(11, 'Teh Gelas', 4000.00),
(12, 'Teh Javana', 4000.00),
(13, 'Teh Sosro', 4000.00),
(14, 'Teh Javana', 4000.00),
(15, 'Teh Botol', 4000.00),
(16, 'Aqua', 3000.00),
(17, 'Pocari Sweat', 6000.00),
(18, 'Fanta', 4000.00),
(19, 'Sprite', 4000.00),
(20, 'Coca Cola', 4000.00),
(21, 'Fruit Tea', 4000.00),
(22, 'Good Day', 4000.00),
(23, 'Teh Pucuk Harum', 4000.00),
(24, 'Teh Kotak', 4000.00),
(25, 'Teh Gelas', 4000.00),
(26, 'Teh Javana', 4000.00),
(27, 'Teh Sosro', 4000.00),
(28, 'Teh Javana', 4000.00);

-- --------------------------------------------------------

--
-- Table structure for table `pesanan`
--

CREATE TABLE `pesanan` (
  `id` int NOT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `tanggal_pesan` date DEFAULT NULL,
  `menu_makanan` varchar(255) DEFAULT NULL,
  `menu_minuman` varchar(255) DEFAULT NULL,
  `total_harga` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `pesanan`
--

INSERT INTO `pesanan` (`id`, `nama`, `tanggal_pesan`, `menu_makanan`, `menu_minuman`, `total_harga`) VALUES
(2, 'ad', '2024-11-18', 'asdads', 'asdsa', 123123.00);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` enum('admin','user') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `email`, `password`, `role`) VALUES
(1, 'admin', 'admin@gmail.com', 'admin', 'admin'),
(2, 'user', 'user@gmail', 'user', 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `menu_makanan`
--
ALTER TABLE `menu_makanan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `menu_minuman`
--
ALTER TABLE `menu_minuman`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `menu_makanan`
--
ALTER TABLE `menu_makanan`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `menu_minuman`
--
ALTER TABLE `menu_minuman`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `pesanan`
--
ALTER TABLE `pesanan`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
