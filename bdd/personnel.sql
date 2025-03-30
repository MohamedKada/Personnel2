-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : dim. 30 mars 2025 à 19:07
-- Version du serveur : 8.2.0
-- Version de PHP : 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `personnel`
--

-- --------------------------------------------------------

--
-- Structure de la table `employe`
--

DROP TABLE IF EXISTS `employe`;
CREATE TABLE IF NOT EXISTS `employe` (
  `ID_EMP` int NOT NULL AUTO_INCREMENT,
  `NOM_EMP` varchar(255) DEFAULT NULL,
  `PRENOM_EMP` varchar(255) DEFAULT NULL,
  `MDP_EMP` varchar(255) DEFAULT NULL,
  `DATE_ARRIVE` date DEFAULT NULL,
  `DATE_DEPART` date DEFAULT NULL,
  `ADMIN` tinyint(1) DEFAULT NULL,
  `MAIL_EMP` varchar(255) DEFAULT NULL,
  `ID_LIGUE` int DEFAULT NULL,
  PRIMARY KEY (`ID_EMP`),
  KEY `ID_LIGUE` (`ID_LIGUE`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `employe`
--

INSERT INTO `employe` (`ID_EMP`, `NOM_EMP`, `PRENOM_EMP`, `MDP_EMP`, `DATE_ARRIVE`, `DATE_DEPART`, `ADMIN`, `MAIL_EMP`, `ID_LIGUE`) VALUES
(1, 'Root', 'Root', 'toor', '2025-02-14', '2025-04-11', 2, 'root@gmail.com', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `ligue`
--

DROP TABLE IF EXISTS `ligue`;
CREATE TABLE IF NOT EXISTS `ligue` (
  `ID_LIGUE` int NOT NULL AUTO_INCREMENT,
  `NOM_L` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_LIGUE`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `ligue`
--

INSERT INTO `ligue` (`ID_LIGUE`, `NOM_L`) VALUES
(1, 'Ligue de football'),
(2, 'Ligue de basketball2'),
(3, 'Ligue de tennis'),
(7, 'Ligue de badminton');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
