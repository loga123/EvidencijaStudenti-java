-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 26, 2018 at 10:59 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `evidencija_studenti`
--

-- --------------------------------------------------------

--
-- Table structure for table `akademska_godina`
--

CREATE TABLE `akademska_godina` (
  `sifra_godine` int(10) UNSIGNED NOT NULL,
  `broj` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `sifra_studija` int(10) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `akademska_godina`
--


-- --------------------------------------------------------

--
-- Table structure for table `evidencija`
--

CREATE TABLE `evidencija` (
  `sifra_evidencije` int(10) UNSIGNED NOT NULL,
  `sifra_termina` int(10) UNSIGNED NOT NULL,
  `vrsta_predavanja` char(1) COLLATE utf8mb4_unicode_ci NOT NULL,
  `datum_evidentiranja` date NOT NULL,
  `prisutnost` tinyint(4) NOT NULL,
  `sifra_studenta_na_kolegiju` int(10) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `evidencija`
--

-- --------------------------------------------------------

--
-- Table structure for table `kolegij`
--

CREATE TABLE `kolegij` (
  `sifra_kolegija` int(10) UNSIGNED NOT NULL,
  `naziv` varchar(70) COLLATE utf8mb4_unicode_ci NOT NULL,
  `sifra_godine` int(10) UNSIGNED NOT NULL,
  `sifra_profesora` int(10) UNSIGNED DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `kolegij`
--

-- --------------------------------------------------------

--
-- Table structure for table `migrations`
--

CREATE TABLE `migrations` (
  `id` int(10) UNSIGNED NOT NULL,
  `migration` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `migrations`
--


-- --------------------------------------------------------

--
-- Table structure for table `password_resets`
--

CREATE TABLE `password_resets` (
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `razina_prava`
--

CREATE TABLE `razina_prava` (
  `sifra_razine` int(10) UNSIGNED NOT NULL,
  `opis` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `razina_prava`
--

INSERT INTO `razina_prava` (`sifra_razine`, `opis`, `created_at`, `updated_at`) VALUES
(1, 'Administrator', '2017-04-24 17:01:49', '2017-04-28 12:04:28'),
(2, 'Profesor', '2017-04-24 17:01:57', '2017-04-24 17:01:57'),
(3, 'Student', '2017-04-24 17:02:04', '2017-04-24 17:02:04');

-- --------------------------------------------------------

--
-- Table structure for table `student_na_kolegiju`
--

CREATE TABLE `student_na_kolegiju` (
  `sifra_studenta_na_kolegiju` int(10) UNSIGNED NOT NULL,
  `sifra_kolegija` int(10) UNSIGNED NOT NULL,
  `sifra_korisnika` int(10) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `student_na_kolegiju`
--



-- --------------------------------------------------------

--
-- Table structure for table `studij`
--

CREATE TABLE `studij` (
  `sifra_studija` int(10) UNSIGNED NOT NULL,
  `naziv` varchar(90) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `studij`
--



-- --------------------------------------------------------

--
-- Table structure for table `termin`
--

CREATE TABLE `termin` (
  `sifra_termina` int(10) UNSIGNED NOT NULL,
  `datum` date NOT NULL,
  `vrijeme_pocetka` time DEFAULT NULL,
  `vrijeme_kraja` time DEFAULT NULL,
  `sifra_kolegija` int(10) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `termin`
--

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `sifra_korisnika` int(10) UNSIGNED NOT NULL,
  `broj_iskaznice` varchar(19) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ime` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prezime` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `razina_prava` int(10) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `password_2` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `users`
--

--
-- Indexes for dumped tables
--

--
-- Indexes for table `akademska_godina`
--
ALTER TABLE `akademska_godina`
  ADD PRIMARY KEY (`sifra_godine`),
  ADD KEY `akademska_godina_sifra_studija_foreign` (`sifra_studija`);

--
-- Indexes for table `evidencija`
--
ALTER TABLE `evidencija`
  ADD PRIMARY KEY (`sifra_evidencije`),
  ADD KEY `evidencija_sifra_studenta_na_kolegiju_foreign` (`sifra_studenta_na_kolegiju`),
  ADD KEY `sifra_termina` (`sifra_termina`);

--
-- Indexes for table `kolegij`
--
ALTER TABLE `kolegij`
  ADD PRIMARY KEY (`sifra_kolegija`),
  ADD KEY `kolegij_sifra_profesora_foreign` (`sifra_profesora`),
  ADD KEY `kolegij_sifra_godine_foreign` (`sifra_godine`) USING BTREE;

--
-- Indexes for table `migrations`
--
ALTER TABLE `migrations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `password_resets`
--
ALTER TABLE `password_resets`
  ADD KEY `password_resets_email_index` (`email`);

--
-- Indexes for table `razina_prava`
--
ALTER TABLE `razina_prava`
  ADD PRIMARY KEY (`sifra_razine`);

--
-- Indexes for table `student_na_kolegiju`
--
ALTER TABLE `student_na_kolegiju`
  ADD PRIMARY KEY (`sifra_studenta_na_kolegiju`),
  ADD KEY `student_na_kolegiju_sifra_kolegija_foreign` (`sifra_kolegija`),
  ADD KEY `student_na_kolegiju_sifra_korisnika_foreign` (`sifra_korisnika`);

--
-- Indexes for table `studij`
--
ALTER TABLE `studij`
  ADD PRIMARY KEY (`sifra_studija`);

--
-- Indexes for table `termin`
--
ALTER TABLE `termin`
  ADD PRIMARY KEY (`sifra_termina`),
  ADD KEY `termin_sifra_kolegija_foreign` (`sifra_kolegija`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`sifra_korisnika`),
  ADD UNIQUE KEY `users_broj_iskaznice_unique` (`broj_iskaznice`),
  ADD UNIQUE KEY `users_email_unique` (`email`),
  ADD KEY `users_razina_prava_foreign` (`razina_prava`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `akademska_godina`
--
ALTER TABLE `akademska_godina`
  MODIFY `sifra_godine` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `evidencija`
--
ALTER TABLE `evidencija`
  MODIFY `sifra_evidencije` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=106;
--
-- AUTO_INCREMENT for table `kolegij`
--
ALTER TABLE `kolegij`
  MODIFY `sifra_kolegija` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;
--
-- AUTO_INCREMENT for table `migrations`
--
ALTER TABLE `migrations`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `razina_prava`
--
ALTER TABLE `razina_prava`
  MODIFY `sifra_razine` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `student_na_kolegiju`
--
ALTER TABLE `student_na_kolegiju`
  MODIFY `sifra_studenta_na_kolegiju` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;
--
-- AUTO_INCREMENT for table `studij`
--
ALTER TABLE `studij`
  MODIFY `sifra_studija` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `termin`
--
ALTER TABLE `termin`
  MODIFY `sifra_termina` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `sifra_korisnika` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `akademska_godina`
--
ALTER TABLE `akademska_godina`
  ADD CONSTRAINT `akademska_godina_sifra_studija_foreign` FOREIGN KEY (`sifra_studija`) REFERENCES `studij` (`sifra_studija`) ON DELETE CASCADE;

--
-- Constraints for table `evidencija`
--
ALTER TABLE `evidencija`
  ADD CONSTRAINT `evidencija_sifra_studenta_na_kolegiju_foreign` FOREIGN KEY (`sifra_studenta_na_kolegiju`) REFERENCES `student_na_kolegiju` (`sifra_studenta_na_kolegiju`) ON DELETE CASCADE,
  ADD CONSTRAINT `evidencija_sifra_termina_foreign` FOREIGN KEY (`sifra_termina`) REFERENCES `termin` (`sifra_termina`) ON DELETE CASCADE;

--
-- Constraints for table `kolegij`
--
ALTER TABLE `kolegij`
  ADD CONSTRAINT `kolegij_sifra_godine_foreign` FOREIGN KEY (`sifra_godine`) REFERENCES `akademska_godina` (`sifra_godine`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `kolegij_sifra_profesora_foreign` FOREIGN KEY (`sifra_profesora`) REFERENCES `users` (`sifra_korisnika`) ON UPDATE CASCADE;

--
-- Constraints for table `student_na_kolegiju`
--
ALTER TABLE `student_na_kolegiju`
  ADD CONSTRAINT `student_na_kolegiju_sifra_kolegija_foreign` FOREIGN KEY (`sifra_kolegija`) REFERENCES `kolegij` (`sifra_kolegija`) ON DELETE CASCADE,
  ADD CONSTRAINT `student_na_kolegiju_sifra_korisnika_foreign` FOREIGN KEY (`sifra_korisnika`) REFERENCES `users` (`sifra_korisnika`) ON DELETE CASCADE;

--
-- Constraints for table `termin`
--
ALTER TABLE `termin`
  ADD CONSTRAINT `termin_sifra_kolegija_foreign` FOREIGN KEY (`sifra_kolegija`) REFERENCES `kolegij` (`sifra_kolegija`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_razina_prava_foreign` FOREIGN KEY (`razina_prava`) REFERENCES `razina_prava` (`sifra_razine`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
