-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Waktu pembuatan: 29 Nov 2024 pada 01.51
-- Versi server: 8.0.30
-- Versi PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_tugas_pbo`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `makanan`
--

CREATE TABLE `makanan` (
  `id` int NOT NULL,
  `menu_makanan` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `harga` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data untuk tabel `makanan`
--

INSERT INTO `makanan` (`id`, `menu_makanan`, `harga`) VALUES
(15, 'Sakura Soto ', 4000.00),
(16, 'Sakura Goreng ', 4000.00),
(17, 'Indomie Goreng ', 6000.00),
(18, 'Indomie Rendang ', 6000.00),
(19, 'Indomie Goreng Aceh ', 6000.00),
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
-- Struktur dari tabel `minuman`
--

CREATE TABLE `minuman` (
  `id` int NOT NULL,
  `menu_minuman` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `harga` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data untuk tabel `minuman`
--

INSERT INTO `minuman` (`id`, `menu_minuman`, `harga`) VALUES
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
-- Struktur dari tabel `pesanan`
--

CREATE TABLE `pesanan` (
  `id` int NOT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `no_meja` tinyint DEFAULT NULL,
  `tanggal_pesan` date DEFAULT NULL,
  `menu_makanan` varchar(255) DEFAULT NULL,
  `menu_minuman` varchar(255) DEFAULT NULL,
  `total_harga` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data untuk tabel `pesanan`
--

INSERT INTO `pesanan` (`id`, `nama`, `no_meja`, `tanggal_pesan`, `menu_makanan`, `menu_minuman`, `total_harga`) VALUES
(2, 'ad', NULL, '2024-11-18', 'asdads', 'asdsa', 123123.00),
(3, 'Nama Pelanggan', 5, '2024-11-28', 'Makanan 4', '', 5500.00),
(4, 'Nama Pelanggan', 5, '2024-11-28', 'Makanan 4, Makanan 2', 'Minuman 3, Minuman 1', 20500.00),
(7, NULL, 0, '2024-11-28', '', 'Minuman 2', 5000.00),
(8, NULL, 0, '2024-11-28', '', 'Minuman 4', 6000.00),
(9, NULL, 0, '2024-11-28', '', 'Minuman 1, Minuman 2', 11000.00),
(10, 'ada', 1, '2024-11-28', '', 'Minuman 2', 5000.00),
(11, 'adsad', 2, '2024-11-28', '', 'Minuman 4, Minuman 4, Minuman 4', 18000.00),
(12, 'adskask', 9, '2024-11-28', '', 'Minuman 2, Minuman 1, Minuman 3', 15000.00),
(13, 'Aditya', 77, '2024-11-29', 'Mie Indomie, Mie Seddap, Mie Indomie Soto Banjar, Mie Seddap Soto', '', 21000.00),
(14, 'Aditya', 9, '2024-11-29', 'Mie Seddap, Mie Indomie', 'Cappucino, Americano', 21000.00),
(15, 'kelompok', 1, '2024-11-29', 'Mie Seddap, Mie Indomie', 'Cappucino, Lemon Tea', 22000.00),
(16, '12', 12, '2024-11-29', 'Mie Indomie Soto Banjar, Mie Seddap Soto', 'Lemon Tea, Strawberry Milk', 24000.00),
(17, '12', 1, '2024-11-29', 'Mie Seddap Soto, Mie Sarimi Ayam Kremes', 'Ice Tea', 16500.00),
(18, '1', 2, '2024-11-29', 'Mie Indomie Soto Banjar, Mie Seddap Soto', 'Lemon Tea, Strawberry Milk', 24000.00),
(20, 'fff', 2, '2024-11-29', 'Mie Seddap, Mie Indomie', 'Ice Tea, Strawberry Milk', 21000.00),
(21, 'Kelompok 1', 1, '2024-11-29', 'Mie Seddap, Mie Sarimi Ayam Kremes, Mie Indomie', 'Americano', 22000.00);

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` enum('admin','user') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id`, `name`, `email`, `password`, `role`) VALUES
(1, 'admin', 'admin@gmail.com', 'admin', 'admin'),
(2, 'user', 'user@gmail', 'user', 'user');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `makanan`
--
ALTER TABLE `makanan`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `minuman`
--
ALTER TABLE `minuman`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `pesanan`
--
ALTER TABLE `pesanan`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `makanan`
--
ALTER TABLE `makanan`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT untuk tabel `minuman`
--
ALTER TABLE `minuman`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT untuk tabel `pesanan`
--
ALTER TABLE `pesanan`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT untuk tabel `user`
--
ALTER TABLE `user`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
