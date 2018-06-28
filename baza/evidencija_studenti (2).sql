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

INSERT INTO `akademska_godina` (`sifra_godine`, `broj`, `sifra_studija`, `created_at`, `updated_at`) VALUES
(2, '2. GODINA', 1, NULL, NULL),
(3, '3. GODINA', 1, NULL, NULL),
(4, '1. GODINA', 2, NULL, NULL),
(5, '2. GODINA', 2, NULL, NULL),
(6, '1. GODINA', 1, '2018-03-27 18:26:47', '2018-03-29 18:03:28');

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

INSERT INTO `evidencija` (`sifra_evidencije`, `sifra_termina`, `vrsta_predavanja`, `datum_evidentiranja`, `prisutnost`, `sifra_studenta_na_kolegiju`, `created_at`, `updated_at`) VALUES
(59, 10, 'P', '2018-04-09', 1, 26, '2018-04-09 21:22:11', '2018-04-09 21:22:11'),
(60, 10, 'P', '2018-04-09', 0, 29, '2018-04-09 21:22:11', '2018-04-09 21:22:11'),
(61, 10, 'P', '2018-04-09', 0, 8, '2018-04-09 21:22:11', '2018-04-09 21:22:11'),
(62, 10, 'P', '2018-04-09', 1, 21, '2018-04-09 21:22:11', '2018-04-09 21:22:11'),
(63, 10, 'P', '2018-04-09', 0, 10, '2018-04-09 21:22:11', '2018-04-09 21:22:11'),
(64, 10, 'P', '2018-04-09', 0, 11, '2018-04-09 21:22:11', '2018-04-09 21:22:11'),
(65, 10, 'P', '2018-04-09', 1, 7, '2018-04-09 21:22:11', '2018-04-09 21:22:11'),
(66, 10, 'P', '2018-04-09', 1, 9, '2018-04-09 21:22:11', '2018-04-09 21:22:11'),
(67, 10, 'P', '2018-04-09', 1, 1, '2018-04-09 21:22:11', '2018-04-09 21:22:11'),
(68, 10, 'P', '2018-04-09', 1, 6, '2018-04-09 21:22:11', '2018-04-09 21:22:11'),
(69, 18, 'P', '2018-04-13', 0, 25, '2018-04-13 19:10:55', '2018-04-13 19:10:55'),
(70, 18, 'P', '2018-04-13', 0, 28, '2018-04-13 19:10:55', '2018-04-13 19:10:55'),
(71, 18, 'P', '2018-04-13', 0, 16, '2018-04-13 19:10:56', '2018-04-13 19:10:56'),
(72, 18, 'P', '2018-04-13', 0, 13, '2018-04-13 19:10:56', '2018-04-13 19:10:56'),
(73, 18, 'P', '2018-04-13', 0, 23, '2018-04-13 19:10:56', '2018-04-13 19:10:56'),
(74, 18, 'P', '2018-04-13', 0, 19, '2018-04-13 19:10:56', '2018-04-13 19:10:56'),
(75, 18, 'P', '2018-04-13', 0, 17, '2018-04-13 19:10:56', '2018-04-13 19:10:56'),
(76, 18, 'P', '2018-04-13', 1, 46, '2018-04-13 19:10:56', '2018-04-13 19:10:56'),
(77, 18, 'P', '2018-04-13', 0, 14, '2018-04-13 19:10:56', '2018-04-13 19:10:56'),
(78, 18, 'P', '2018-04-13', 0, 18, '2018-04-13 19:10:56', '2018-04-13 19:10:56'),
(79, 18, 'P', '2018-04-13', 1, 47, '2018-04-13 19:10:56', '2018-04-13 19:10:56'),
(80, 18, 'P', '2018-04-13', 0, 15, '2018-04-13 19:10:56', '2018-04-13 19:10:56'),
(81, 17, 'P', '2018-04-14', 1, 48, '2018-04-14 17:26:03', '2018-04-14 17:26:03'),
(82, 17, 'P', '2018-04-14', 1, 50, '2018-04-14 17:26:03', '2018-04-14 17:26:03'),
(83, 17, 'P', '2018-04-14', 1, 49, '2018-04-14 17:26:03', '2018-04-14 17:26:03'),
(84, 17, 'P', '2018-04-14', 0, 52, '2018-04-14 17:26:03', '2018-04-14 17:26:03'),
(85, 17, 'P', '2018-04-14', 0, 55, '2018-04-14 17:26:03', '2018-04-14 17:26:03'),
(86, 17, 'P', '2018-04-14', 0, 56, '2018-04-14 17:26:03', '2018-04-14 17:26:03'),
(87, 17, 'P', '2018-04-14', 0, 54, '2018-04-14 17:26:03', '2018-04-14 17:26:03'),
(88, 17, 'P', '2018-04-14', 0, 57, '2018-04-14 17:26:03', '2018-04-14 17:26:03'),
(89, 17, 'P', '2018-04-14', 0, 58, '2018-04-14 17:26:03', '2018-04-14 17:26:03'),
(90, 17, 'P', '2018-04-14', 0, 51, '2018-04-14 17:26:03', '2018-04-14 17:26:03'),
(91, 17, 'P', '2018-04-14', 0, 59, '2018-04-14 17:26:03', '2018-04-14 17:26:03'),
(92, 17, 'P', '2018-04-14', 0, 60, '2018-04-14 17:26:03', '2018-04-14 17:26:03'),
(93, 20, 'P', '2018-04-25', 1, 23, '2018-04-25 20:01:31', '2018-04-25 20:01:31'),
(94, 20, 'P', '2018-04-25', 1, 25, '2018-04-25 18:04:22', '2018-04-25 18:04:22'),
(95, 20, 'P', '2018-04-25', 0, 28, '2018-04-25 18:04:22', '2018-04-25 18:04:22'),
(96, 20, 'P', '2018-04-25', 0, 16, '2018-04-25 18:04:22', '2018-04-25 18:04:22'),
(97, 20, 'P', '2018-04-25', 0, 13, '2018-04-25 18:04:22', '2018-04-25 18:04:22'),
(98, 20, 'P', '2018-04-25', 0, 19, '2018-04-25 18:04:23', '2018-04-25 18:04:23'),
(99, 20, 'P', '2018-04-25', 0, 17, '2018-04-25 18:04:23', '2018-04-25 18:04:23'),
(100, 20, 'P', '2018-04-25', 0, 46, '2018-04-25 18:04:23', '2018-04-25 18:04:23'),
(101, 20, 'P', '2018-04-25', 0, 14, '2018-04-25 18:04:23', '2018-04-25 18:04:23'),
(102, 20, 'P', '2018-04-25', 0, 18, '2018-04-25 18:04:23', '2018-04-25 18:04:23'),
(103, 20, 'P', '2018-04-25', 0, 47, '2018-04-25 18:04:23', '2018-04-25 18:04:23'),
(104, 20, 'P', '2018-04-25', 0, 15, '2018-04-25 18:04:23', '2018-04-25 18:04:23'),
(105, 21, 'P', '2018-04-25', 1, 61, '2018-04-25 20:10:10', '2018-04-25 20:10:10');

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

INSERT INTO `kolegij` (`sifra_kolegija`, `naziv`, `sifra_godine`, `sifra_profesora`, `created_at`, `updated_at`) VALUES
(1, 'Programsko inženjerstvo', 4, 1, '2017-04-24 16:53:20', '2017-04-24 16:53:20'),
(5, 'Alati i razvoj informacijskog sustava', 4, 1, '2017-04-24 17:00:40', '2017-04-24 17:00:40'),
(8, 'Izgradnja objektno orijentiranih aplikacija', 4, 15, '2017-05-03 11:32:40', '2017-05-03 11:32:40'),
(13, 'Računalne mreže', 3, 1, '2017-05-17 16:14:28', '2017-05-26 20:27:38'),
(18, 'test1', 0, 1, '2017-05-29 16:32:22', '2017-05-29 16:33:01'),
(19, 'Planiranje informacijskih sustava', 5, 19, NULL, NULL),
(20, 'Sustavi baze podataka', 2, 1, NULL, NULL),
(21, 'Objektno orijentirane tehnologije I', 2, 15, '2018-04-03 17:50:11', '2018-04-03 17:50:11'),
(22, 'test', 4, NULL, '2018-04-11 19:26:59', '2018-04-11 19:26:59'),
(23, 'Modeliranje i simulacije', 4, 20, '2018-04-13 19:20:23', '2018-04-13 19:20:23'),
(24, 'Upravljanje bazama podataka', 4, 1, '2018-04-13 19:21:21', '2018-04-13 19:21:21'),
(25, 'Komunikacijske tehnologije', 4, NULL, '2018-04-13 19:21:38', '2018-04-13 19:21:38'),
(26, 'Kontrola i revizija informacijskih sustava', 4, 20, '2018-04-13 19:21:58', '2018-04-13 19:21:58'),
(27, 'Sustavi za vođenje proizvodnih procesa', 4, NULL, '2018-04-13 19:22:14', '2018-04-13 19:22:14'),
(28, 'Izgradnja multimedijskih sustava', 4, 15, '2018-04-13 19:22:40', '2018-04-24 20:11:13'),
(29, 'Upravljanje kvalitetom informacijskog sustava', 4, 20, '2018-04-13 19:22:51', '2018-04-13 19:22:51'),
(30, 'Računalno upravljanje složenim sustavima', 4, NULL, '2018-04-13 19:23:03', '2018-04-13 19:23:03'),
(31, 'Sučelje poslovnih i procesnih sustava', 4, NULL, '2018-04-13 19:23:14', '2018-04-13 19:23:14');

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

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(1, '2014_10_12_100000_create_password_resets_table', 1),
(2, '2017_04_10_092202_create_razinaPrava_table', 1),
(3, '2017_04_11_000000_create_users_table', 1),
(4, '2017_04_1_000001_create_kolegij_table', 1),
(5, '2017_04_21_090000_create_student_na_kolegiju_table', 1),
(6, '2017_04_21_092510_create_evidencija_table', 1),
(7, '2017_05_08_152216_create_termin_table', 1);

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

INSERT INTO `student_na_kolegiju` (`sifra_studenta_na_kolegiju`, `sifra_kolegija`, `sifra_korisnika`, `created_at`, `updated_at`) VALUES
(1, 1, 5, '2017-05-03 05:00:27', '2017-05-03 05:00:27'),
(2, 5, 5, '2017-05-03 05:00:50', '2017-05-03 05:00:50'),
(6, 1, 9, '2017-05-03 09:05:59', '2017-05-03 09:05:59'),
(7, 1, 13, '2017-05-03 09:06:06', '2017-05-03 09:06:06'),
(8, 1, 11, '2017-05-03 09:06:14', '2017-05-03 09:06:14'),
(9, 1, 6, '2017-05-03 09:06:28', '2017-05-03 09:06:28'),
(10, 1, 10, '2017-05-03 09:06:35', '2017-05-03 09:06:35'),
(11, 1, 8, '2017-05-03 09:06:42', '2017-05-03 09:06:42'),
(13, 8, 14, '2017-05-09 08:04:40', '2017-05-09 08:04:40'),
(14, 8, 13, '2017-05-09 08:04:46', '2017-05-09 08:04:46'),
(15, 8, 9, '2017-05-09 08:04:51', '2017-05-09 08:04:51'),
(16, 8, 11, '2017-05-09 08:04:57', '2017-05-09 08:04:57'),
(17, 8, 12, '2017-05-09 08:05:06', '2017-05-09 08:05:06'),
(18, 8, 6, '2017-05-09 08:05:12', '2017-05-09 08:05:12'),
(19, 8, 10, '2017-05-09 08:05:20', '2017-05-09 08:05:20'),
(21, 1, 16, '2017-05-22 15:23:10', '2017-05-22 15:23:10'),
(22, 5, 16, '2017-05-22 15:23:19', '2017-05-22 15:23:19'),
(23, 8, 16, '2017-05-22 15:23:26', '2017-05-22 15:23:26'),
(24, 5, 17, '2017-05-23 11:54:52', '2017-05-23 11:54:52'),
(25, 8, 17, '2017-05-23 11:55:02', '2017-05-23 11:55:02'),
(26, 1, 17, '2017-05-23 11:55:14', '2017-05-23 11:55:14'),
(27, 5, 18, '2017-05-24 15:12:16', '2017-05-24 15:12:16'),
(28, 8, 18, '2017-05-24 15:12:30', '2017-05-24 15:12:30'),
(29, 1, 18, '2017-05-24 15:12:37', '2017-05-24 15:12:37'),
(30, 5, 10, '2017-05-27 18:26:59', '2017-05-27 18:26:59'),
(31, 5, 14, '2017-05-27 18:27:17', '2017-05-27 18:27:17'),
(32, 5, 11, '2017-05-27 18:27:50', '2017-05-27 18:27:50'),
(33, 5, 12, '2017-05-27 18:28:03', '2017-05-27 18:28:03'),
(34, 13, 17, '2017-05-27 18:28:19', '2017-05-27 18:28:19'),
(35, 13, 18, '2017-05-27 18:28:23', '2017-05-27 18:28:23'),
(36, 13, 11, '2017-05-27 18:28:26', '2017-05-27 18:28:26'),
(37, 13, 14, '2017-05-27 18:28:29', '2017-05-27 18:28:29'),
(38, 13, 7, '2017-05-27 18:28:31', '2017-05-27 18:28:31'),
(39, 13, 16, '2017-05-27 18:28:35', '2017-05-27 18:28:35'),
(40, 13, 10, '2017-05-27 18:28:37', '2017-05-27 18:28:37'),
(41, 13, 12, '2017-05-27 18:28:41', '2017-05-27 18:28:41'),
(45, 13, 13, '2017-05-29 16:33:49', '2017-05-29 16:33:49'),
(46, 8, 8, '2018-04-13 18:48:00', '2018-04-13 18:48:00'),
(47, 8, 5, '2018-04-13 18:51:37', '2018-04-13 18:51:37'),
(48, 21, 17, '2018-04-13 19:13:23', '2018-04-13 19:13:23'),
(49, 21, 11, '2018-04-13 19:13:29', '2018-04-13 19:13:29'),
(50, 21, 18, '2018-04-13 19:13:33', '2018-04-13 19:13:33'),
(51, 21, 6, '2018-04-13 19:13:37', '2018-04-13 19:13:37'),
(52, 21, 14, '2018-04-13 19:13:41', '2018-04-13 19:13:41'),
(54, 21, 12, '2018-04-13 19:13:50', '2018-04-13 19:13:50'),
(55, 21, 16, '2018-04-13 19:13:55', '2018-04-13 19:13:55'),
(56, 21, 10, '2018-04-13 19:14:00', '2018-04-13 19:14:00'),
(57, 21, 8, '2018-04-13 19:14:04', '2018-04-13 19:14:04'),
(58, 21, 13, '2018-04-13 19:14:09', '2018-04-13 19:14:09'),
(59, 21, 5, '2018-04-13 19:14:15', '2018-04-13 19:14:15'),
(60, 21, 9, '2018-04-13 19:14:20', '2018-04-13 19:14:20'),
(61, 28, 16, '2018-04-25 18:09:51', '2018-04-25 18:09:51'),
(62, 28, 17, '2018-04-25 18:09:56', '2018-04-25 18:09:56'),
(63, 28, 18, '2018-04-25 18:10:00', '2018-04-25 18:10:00'),
(64, 28, 11, '2018-04-25 18:10:04', '2018-04-25 18:10:04'),
(65, 28, 14, '2018-04-25 18:11:20', '2018-04-25 18:11:20'),
(66, 28, 7, '2018-04-25 18:11:24', '2018-04-25 18:11:24'),
(67, 28, 10, '2018-04-25 18:11:30', '2018-04-25 18:11:30'),
(68, 28, 12, '2018-04-25 18:11:36', '2018-04-25 18:11:36'),
(69, 28, 8, '2018-04-25 18:11:41', '2018-04-25 18:11:41'),
(70, 28, 13, '2018-04-25 18:11:45', '2018-04-25 18:11:45'),
(71, 28, 6, '2018-04-25 18:11:50', '2018-04-25 18:11:50'),
(72, 28, 5, '2018-04-25 18:11:54', '2018-04-25 18:11:54'),
(73, 28, 9, '2018-04-25 18:11:59', '2018-04-25 18:11:59');

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

INSERT INTO `studij` (`sifra_studija`, `naziv`, `created_at`, `updated_at`) VALUES
(1, 'Stručni studij Informatike', NULL, NULL),
(2, 'Specijalistički diplomski stručni studij Informacijske tehnologije u poslovnim sustavima', NULL, NULL);

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

INSERT INTO `termin` (`sifra_termina`, `datum`, `vrijeme_pocetka`, `vrijeme_kraja`, `sifra_kolegija`, `created_at`, `updated_at`) VALUES
(1, '2017-05-29', '10:00:00', '14:00:00', 5, '2017-05-29 08:40:28', '2017-05-29 08:40:28'),
(2, '2017-05-29', '10:00:00', '19:00:00', 1, '2017-05-29 08:42:47', '2017-05-29 08:42:47'),
(3, '2017-05-30', '19:00:00', '20:00:00', 5, '2017-05-29 12:08:25', '2017-05-29 12:08:25'),
(6, '2017-05-31', '16:00:00', '20:00:00', 5, '2017-05-29 13:11:37', '2017-05-29 13:11:37'),
(7, '2017-06-01', '17:00:00', '21:00:00', 5, '2017-05-29 13:13:21', '2017-05-29 13:13:21'),
(8, '2017-06-06', '15:00:00', '17:00:00', 5, '2017-05-29 16:31:33', '2017-05-29 16:31:33'),
(10, '2017-05-31', '10:00:00', '11:45:00', 1, '2017-05-31 09:00:51', '2017-05-31 09:00:51'),
(12, '2017-05-31', '15:00:00', '18:15:00', 13, '2017-05-31 14:31:50', '2017-05-31 14:31:50'),
(13, '2017-06-14', '09:00:00', '23:00:00', 1, '2017-06-14 07:07:55', '2017-06-14 07:07:55'),
(14, '2017-06-17', '18:00:00', '19:00:00', 1, '2017-06-14 09:07:26', '2017-06-14 09:07:26'),
(17, '2018-04-06', '00:00:00', '01:00:00', 21, '2018-04-06 18:56:51', '2018-04-06 18:56:51'),
(18, '2018-04-13', '00:00:00', '01:00:00', 8, '2018-04-13 19:08:33', '2018-04-13 19:08:33'),
(19, '2018-04-16', '00:00:00', '01:00:00', 23, '2018-04-16 18:48:14', '2018-04-16 18:48:14'),
(20, '2018-04-25', '21:00:00', '23:00:00', 8, '2018-04-25 17:13:04', '2018-04-25 17:13:04'),
(21, '2018-04-25', '00:00:00', '23:00:00', 28, '2018-04-25 18:09:03', '2018-04-25 18:09:03');

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

INSERT INTO `users` (`sifra_korisnika`, `broj_iskaznice`, `ime`, `prezime`, `email`, `password`, `remember_token`, `razina_prava`, `created_at`, `updated_at`, `password_2`) VALUES
(1, '', 'Marin', 'Kaluža', 'mkaluza@veleri.hr', '$2y$10$ZygfcLlYHouMopITF/9kyeyxo3CFI3OxAwM6GJuuXY4im07HT/0K6', 'uOFJcZhNXqMsA87BUZox41Z0G3t6DepLLGmisbna7aqalSMm2Yap6wLsOoYQ', 2, NULL, '2017-05-23 20:24:20', 'bWthbHV6YQ=='),
(4, '6019833102420233091', 'Ivan', 'Periša', 'iperisa1@veleri.hr', '$2y$10$QixCThLU70HTJNJzl/HBf.A6cd/NZ1SzqvP.PrWA7GxJ5m7oeH6tu', 'ajZbdcCM5cG4KDSVxvrmkhdnxLR7iS1GxEZovMEYtMct6wmOnX5Qv9x5P7Lp', 1, '2017-04-28 16:05:13', '2017-06-14 09:09:51', 'aXBlcmlzYTE='),
(5, '6019833102310337846', 'Juraj', 'Vrzan', 'jvrzan@veleri.hr', '$2y$10$F/U9TOC6BjTh0pVZ0tVrbuS1.DH0a/GLeeQWtG7aVjC/aAGWXsYIy', '8R9KZIOahqwZ6jYO5YiJfdSj788TDgRwC53Epqez9nsR4dN4BSUBItWFcyeY', 3, '2017-04-28 16:11:29', '2017-05-09 12:16:56', NULL),
(6, '6019832102420229363', 'Romina', 'Vlah', 'rvlah@veleri.hr', '$2y$10$czVw3kFTMHNsmwmqIBXa3uAOzM/M75QoljtfZv9/xSm0BOwj9iuZ6', 'eaKihSeoXkBC8z423Crg6Lns5yLVEY82HtDvp7N06S0UF7eEUDAYePBmGpTQ', 3, '2017-04-28 16:13:02', '2017-05-05 14:11:59', NULL),
(7, '2323232323232323232', 'Pero', 'Perić', 'pero@peric.hr', '$2y$10$ZUm2PY3f1ypl1jWuf.BZWu6CEcCmfECrb36mDjFuf.iJ5SrKSUx6e', NULL, 3, '2017-05-02 15:41:02', '2017-05-02 15:41:02', NULL),
(8, '6019832102420234470', 'Tea', 'Tomljanović', 'ttomljan@veleri.hr', '$2y$10$Lr9hx7EOsWMhSVEby2DRB.Y/EpJjjM1Gd3vrOiPK53hNTqf1Q7muq', NULL, 3, '2017-05-03 09:01:36', '2017-05-03 09:01:36', NULL),
(9, '6019832102420019327', 'Dino', 'Vucelić', 'dvucelic@veleri.hr', '$2y$10$P1Ee8Gl/KG20CrBfrgkphOi4YR7/BSAQXbSZCdidgGYJmDyHQFTFm', NULL, 3, '2017-05-03 09:02:14', '2017-05-03 09:02:14', NULL),
(10, '6019833102420014467', 'Sanja', 'Petrović', 'spetrovic@veleri.hr', '$2y$10$StveMcZUAditkiDjrCmLIe19JAd4kNLcrZL5F7ho/lKW.ZSjSsSxK', '0RRHO3EBpp6CtiMkKmASgU7dOJ73AdtRZSUmy7RKFU4c1WKXMzkiQngMV8dx', 3, '2017-05-03 09:02:50', '2017-05-03 09:02:50', NULL),
(11, '6019834100364083117', 'Ivo', 'Fabijanić', 'ifabijan@veleri.hr', '$2y$10$SUkcyoToRc8jB.gnr9YlgO4ifBtCvE4aaszHckPRVHcXbm5IPgxQ2', NULL, 3, '2017-05-03 09:03:55', '2017-05-03 09:03:55', NULL),
(12, '6019832100161038019', 'Robert', 'Rešetar', 'rresetar@veleri.hr', '$2y$10$VN4GVLEb/BKIUqm1j/LQWeBkNZ3X9eWOu5Tbx/OcXAXFi7LZDCWey', NULL, 3, '2017-05-03 09:04:32', '2017-05-03 09:04:32', NULL),
(13, '6019834102420116311', 'Igor', 'Verić', 'iveric@veleri.hr', '$2y$10$bHuiKA1VzhKYT.Q1R1qE7.wNrX7c2sx8oHMghUAp9IZX9mCxFjXZ.', 'mWHjrrNDinrLn1zRHB1jCIDBq7rK57PbFDhxed9njce4M0KrmzujCJYq0yjV', 3, '2017-05-03 09:04:59', '2017-05-03 09:04:59', NULL),
(14, '6019831102420250725', 'Daniel', 'Marković', 'dmarkovic@veleri.hr', '$2y$10$H.B2uUkxkc/GC9BQSIeiAeMWUUfowGzUDIffTUetHLOsZQupVmYw2', '9gi4Ko3RihdyfvKYT9JEl0A7u8Kz2RUFJwgptBIItq0WbTkom0mhUgWalEIw', 3, '2017-05-03 09:05:36', '2017-05-03 09:05:36', NULL),
(15, '1111111111111111111', 'Vlatka', 'Davidović', 'vdavidovic@veleri.hr', '$2y$10$eBtlxS0HkI9JKWJf/qB9aOVizn/SoEgDjFTnfhYoy1X/UMfM9hSoO', 'o4eHm3lF6Uqqoi4RFv3cQGyafPxqE5Ab6KmaYAtOWdw3eqEy997WonqyAfei', 2, '2017-05-03 11:25:52', '2017-05-03 11:25:52', 'dmRhdmlkb3ZpYw=='),
(16, '6019832102420233092', 'Ivan', 'Periša', 'iperisa@veleri.hr', '$2y$10$Lb3Yun.Lc94D5S/E14ck1ePmOihDehBgBIhqJYluTsU4ugH.qeMUC', 'ZGLU5S71Mp7sqfqUL2YtlYugBCEdXWsYy7eVnf0dTDVE6ZyyShT9XTLtD1W8', 3, '2017-05-22 15:22:46', '2017-05-22 15:22:46', NULL),
(17, '6019832102420010144', 'Ante', 'Božić', 'abozic@veleri.hr', '$2y$10$NMhLYv.oTrlgJzQdXJDDOeQ5zeEQ4uGduw9l2j.JpXplNwxEFYqqa', 'DoxNfvZV6lDz1wsL7baUndUib3dXivvW5nMp6FJi5R7Vm91XneJ0v5jmljTL', 3, '2017-05-23 11:54:20', '2017-05-23 11:54:20', NULL),
(18, '6019832102420099675', 'Antun', 'Đuranec', 'aduranec@veleri.hr', '$2y$10$RwBtshYG9TFM.olKvLQ8a.pSkOnoRrJ8yGRA4TQbrZ3Q/tQvScwz6', 'NnnEiXE3r3C1HX3Orv4thPqXCxY0bXUHL0H7IvsPewldz0ZhjajsWujxR0uj', 3, '2017-05-24 15:11:46', '2017-05-24 15:11:46', NULL),
(19, '0', 'Alen', 'Jakupović', 'ajakupovic@veleri.hr', '$2y$10$h75pEiqbdphg7WbTJyIoA.MCLC8NOISRSkw64OS6dVvb6UPcg6z6C', NULL, 2, '2018-03-25 17:43:18', '2018-03-25 17:43:18', NULL),
(20, '155', 'Ivan', 'Pogarčić', 'ipogarcic@veleri.hr', '$2y$10$NiDxzAqvY2hasyeyjd3fSu.l3179TmAiw.L60kTYeiI/HznmW6Mpy', 'uWP35HAyKxlrOuV8krbhnJcicpieylPyBFlk4W8sijSMgopmgUhDIjN7Gr5Y', 2, '2018-04-13 19:25:28', '2018-04-16 18:52:42', NULL);

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
