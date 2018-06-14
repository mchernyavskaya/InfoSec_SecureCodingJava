-- noinspection SqlNoDataSourceInspectionForFile

-- phpMyAdmin SQL Dump
--  version 4.5.1
--  http://www.phpmyadmin.net
-- ---- Host: 127.0.0.1
--  Generation Time: Dec 10, 2017 at 06:03 AM
--  Server version: 10.1.19-MariaDB
--  PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";
SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT;
SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS;
SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION;
SET NAMES utf8mb4;
--

-- -- Database: `users`--
--  Table structure for table `admin`

CREATE TABLE `admin` (
`id` int(11) DEFAULT NULL,
`username` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
`password` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL)
ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- Dumping data for table `admin`--
-- INSERT INTO `admin` (`id`, `username`, `password`) VALUES(1, 'admin', 'admin');
INSERT INTO admin(id, username, password)
VALUES (1, 'admin', '$2a$10$O/YDt8eAt.a1huFbtNTjPe.HpBn2dC4Bcu2Qp7PNcaBWAxKuB4dte');

-- Table structure for table `users`
CREATE TABLE `users` (
`id` int(11) NOT NULL,
`username` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
 `password` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL)
 ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- Dumping data for table `users`

-- INSERT INTO `users` (`id`, `username`, `password`) VALUES
-- (1, 'admin', 'admin'),
-- (2, 'bob', 'bob'),
-- (3, 'john', 'john');
INSERT INTO users(id, username, password)
VALUES (1, 'admin', '$2a$10$O/YDt8eAt.a1huFbtNTjPe.HpBn2dC4Bcu2Qp7PNcaBWAxKuB4dte');

INSERT INTO users(id, username, password)
VALUES (2, 'bob', '$2a$10$4jJ49D5lhH3jDCAZuB7VWuQipywGb/FzbZELa/a//AWVTQ73Q3TOm');

INSERT INTO users(id, username, password)
VALUES (3, 'john', '$2a$10$Y/5SDAC3hSlPPXUmOR3xLedNF70maraz6Zbaq./hkcyrg57XQF8aO');

SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT;
SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS;
SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION;