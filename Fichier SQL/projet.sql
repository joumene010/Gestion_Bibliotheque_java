-- phpMyAdmin SQL Dump
-- version 5.1.1deb5ubuntu1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 25, 2023 at 08:32 PM
-- Server version: 8.0.32-0ubuntu0.22.04.2
-- PHP Version: 8.1.2-1ubuntu2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projet`
--

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `Num_Abonne` int NOT NULL,
  `Email` varchar(40) NOT NULL,
  `Nom` varchar(20) NOT NULL,
  `motdepasse` varchar(20) NOT NULL,
  `Statut` varchar(12) NOT NULL,
  `Prenom` varchar(15) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`Num_Abonne`, `Email`, `Nom`, `motdepasse`, `Statut`, `Prenom`) VALUES
(23, 'roua@gmail.com', 'roua', '123456789', 'etudiant', 'roua'),
(24, 'joumene@gmail.com', 'ben said', '123456789', 'etudiant', 'joumene'),
(25, 'rayen@gmail.com', 'khlifi', '123456789', 'Enseignant', 'rayen'),
(26, 'cyrine@gmail.com', 'tlili', '123456789', 'Enseignant', 'cyrine');

-- --------------------------------------------------------

--
-- Table structure for table `emprunts`
--

CREATE TABLE `emprunts` (
  `idexemplaire` int NOT NULL,
  `date_emprunt` date NOT NULL,
  `date_retour` date DEFAULT NULL,
  `Livre_retourne` tinyint(1) DEFAULT NULL,
  `Num_ab` int NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `emprunts`
--

INSERT INTO `emprunts` (`idexemplaire`, `date_emprunt`, `date_retour`, `Livre_retourne`, `Num_ab`) VALUES
(1059, '2023-05-25', '2023-05-25', 0, 23),
(1058, '2023-05-25', '2023-05-25', 0, 23),
(1047, '2023-05-25', '2023-05-25', 0, 24),
(1057, '2023-05-25', '2023-05-25', 0, 23);

-- --------------------------------------------------------

--
-- Table structure for table `exemplaire`
--

CREATE TABLE `exemplaire` (
  `id_exp` int NOT NULL,
  `id_livre` int NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `exemplaire`
--

INSERT INTO `exemplaire` (`id_exp`, `id_livre`) VALUES
(1060, 3),
(1059, 3),
(1053, 1),
(1052, 1),
(1051, 1),
(1050, 1),
(1058, 2),
(1049, 1),
(1057, 2),
(1056, 1),
(1055, 1),
(1054, 1),
(1048, 1),
(1047, 1),
(1061, 3),
(1062, 3),
(1063, 3),
(1064, 3),
(1065, 3),
(1066, 3),
(1067, 3),
(1068, 3);

-- --------------------------------------------------------

--
-- Table structure for table `livre`
--

CREATE TABLE `livre` (
  `IDlivre` int NOT NULL,
  `nisbn` varchar(10) NOT NULL,
  `titre` varchar(25) NOT NULL,
  `auteur` varchar(35) NOT NULL,
  `quantite` int NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `livre`
--

INSERT INTO `livre` (`IDlivre`, `nisbn`, `titre`, `auteur`, `quantite`) VALUES
(1, '61122415', 'To Kill a Mockingbird', 'Harper Lee', 10),
(2, '141439563', 'Pride and Prejudice', 'Jane Austen', 2),
(3, '1234567899', 'the alchemist', 'paulo coelho', 10);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`Num_Abonne`);

--
-- Indexes for table `emprunts`
--
ALTER TABLE `emprunts`
  ADD PRIMARY KEY (`date_emprunt`,`idexemplaire`,`Num_ab`),
  ADD KEY `FK3` (`Num_ab`),
  ADD KEY `FK5` (`idexemplaire`);

--
-- Indexes for table `exemplaire`
--
ALTER TABLE `exemplaire`
  ADD PRIMARY KEY (`id_exp`),
  ADD KEY `FK1` (`id_livre`);

--
-- Indexes for table `livre`
--
ALTER TABLE `livre`
  ADD PRIMARY KEY (`IDlivre`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `Num_Abonne` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `exemplaire`
--
ALTER TABLE `exemplaire`
  MODIFY `id_exp` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1082;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
