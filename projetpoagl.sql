-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Mer 11 Novembre 2020 à 13:59
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `projetpoagl`
--

-- --------------------------------------------------------

--
-- Structure de la table `effect`
--

CREATE TABLE IF NOT EXISTS `effect` (
  `idEffect` int(11) NOT NULL AUTO_INCREMENT,
  `effect` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idEffect`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `est_equipe`
--

CREATE TABLE IF NOT EXISTS `est_equipe` (
  `idJoueur` int(11) NOT NULL AUTO_INCREMENT,
  `idItem` int(11) NOT NULL,
  PRIMARY KEY (`idJoueur`,`idItem`),
  KEY `FK_est_equipe_idItem` (`idItem`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `floor`
--

CREATE TABLE IF NOT EXISTS `floor` (
  `idFloor` int(11) NOT NULL AUTO_INCREMENT,
  `idMap` int(11) DEFAULT NULL,
  PRIMARY KEY (`idFloor`),
  KEY `FK_Floor_idMap` (`idMap`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `floor`
--

INSERT INTO `floor` (`idFloor`, `idMap`) VALUES
(1, 1),
(2, 1),
(3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `item`
--

CREATE TABLE IF NOT EXISTS `item` (
  `idItem` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `maxDurability` int(11) DEFAULT NULL,
  `currentDurability` int(11) DEFAULT NULL,
  `rarete` int(11) DEFAULT NULL,
  `inventoryID` int(11) DEFAULT NULL,
  `idjoueur` int(11) DEFAULT NULL,
  `idmonstre` int(11) DEFAULT NULL,
  PRIMARY KEY (`idItem`),
  KEY `FK_Item_joueur_idjoueur` (`idjoueur`),
  KEY `FK_Item_monstre_idmonstre` (`idmonstre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `joueur`
--

CREATE TABLE IF NOT EXISTS `joueur` (
  `idJoueur` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) DEFAULT NULL,
  `entityType` varchar(50) DEFAULT NULL,
  `idstats` int(11) DEFAULT NULL,
  `idmoney` int(11) DEFAULT NULL,
  `idlvl` int(11) DEFAULT NULL,
  `idposition` int(11) DEFAULT NULL,
  `pointBonusJoueur` int(11) DEFAULT NULL,
  PRIMARY KEY (`idJoueur`),
  KEY `FK_Joueur_stats_idstats` (`idstats`),
  KEY `FK_Joueur_money_idmoney` (`idmoney`),
  KEY `FK_Joueur_lvl_idlvl` (`idlvl`),
  KEY `FK_Joueur_position_idposition` (`idposition`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `joueur`
--

INSERT INTO `joueur` (`idJoueur`, `nom`, `entityType`, `idstats`, `idmoney`, `idlvl`, `idposition`, `pointBonusJoueur`) VALUES
(1, 'dddd', NULL, 1, 1, 1, NULL, 1);

-- --------------------------------------------------------

--
-- Structure de la table `lvl`
--

CREATE TABLE IF NOT EXISTS `lvl` (
  `idLvl` int(11) NOT NULL AUTO_INCREMENT,
  `level` int(11) DEFAULT NULL,
  `totalXP` int(11) DEFAULT NULL,
  `currentXP` int(11) DEFAULT NULL,
  `xpNeeded` int(11) DEFAULT NULL,
  `idjoueur` int(11) DEFAULT NULL,
  `idmonstre` int(11) DEFAULT NULL,
  `pointBonus` int(11) DEFAULT NULL,
  PRIMARY KEY (`idLvl`),
  KEY `FK_lvl_joueur_idjoueur` (`idjoueur`),
  KEY `FK_lvl_monstre_idmonstre` (`idmonstre`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `lvl`
--

INSERT INTO `lvl` (`idLvl`, `level`, `totalXP`, `currentXP`, `xpNeeded`, `idjoueur`, `idmonstre`, `pointBonus`) VALUES
(1, 1, 0, 0, 20, 1, NULL, 0),
(2, 2, 20, 0, 60, NULL, NULL, 2),
(3, 3, 80, 0, 200, NULL, NULL, 2),
(4, 4, 280, 0, 500, NULL, NULL, 3),
(5, 5, 780, 0, 1273, NULL, NULL, 3);

-- --------------------------------------------------------

--
-- Structure de la table `map`
--

CREATE TABLE IF NOT EXISTS `map` (
  `idMap` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idMap`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `map`
--

INSERT INTO `map` (`idMap`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `money`
--

CREATE TABLE IF NOT EXISTS `money` (
  `idMoney` int(11) NOT NULL AUTO_INCREMENT,
  `moneyPlayer` int(11) DEFAULT NULL,
  `moneyBank` int(11) DEFAULT NULL,
  `idjoueur` int(11) DEFAULT NULL,
  PRIMARY KEY (`idMoney`),
  KEY `FK_Money_joueur_idjoueur` (`idjoueur`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `money`
--

INSERT INTO `money` (`idMoney`, `moneyPlayer`, `moneyBank`, `idjoueur`) VALUES
(1, 500, 1000, 1);

-- --------------------------------------------------------

--
-- Structure de la table `monstre`
--

CREATE TABLE IF NOT EXISTS `monstre` (
  `idMonstre` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) DEFAULT NULL,
  `entityType` varchar(50) DEFAULT NULL,
  `idposition` int(11) DEFAULT NULL,
  `idstats` int(11) DEFAULT NULL,
  `idlvl` int(11) DEFAULT NULL,
  PRIMARY KEY (`idMonstre`),
  KEY `FK_Monstre_position_idposition` (`idposition`),
  KEY `FK_Monstre_stats_idstats` (`idstats`),
  KEY `FK_Monstre_lvl_idlvl` (`idlvl`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `monstre_effect`
--

CREATE TABLE IF NOT EXISTS `monstre_effect` (
  `idEffect` int(11) NOT NULL AUTO_INCREMENT,
  `idMonstre` int(11) NOT NULL,
  PRIMARY KEY (`idEffect`,`idMonstre`),
  KEY `FK_Monstre_effect_idMonstre` (`idMonstre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `position`
--

CREATE TABLE IF NOT EXISTS `position` (
  `idPosition` int(11) NOT NULL AUTO_INCREMENT,
  `x` int(11) DEFAULT NULL,
  `y` int(11) DEFAULT NULL,
  `z` int(11) DEFAULT NULL,
  `idMap` int(11) DEFAULT NULL,
  `idmonstre` int(11) DEFAULT NULL,
  `idjoueur` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPosition`),
  KEY `FK_position_idMap` (`idMap`),
  KEY `FK_position_monstre_idmonstre` (`idmonstre`),
  KEY `FK_position_joueur_idjoueur` (`idjoueur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `possede_effect`
--

CREATE TABLE IF NOT EXISTS `possede_effect` (
  `idJoueur` int(11) NOT NULL AUTO_INCREMENT,
  `idEffect` int(11) NOT NULL,
  PRIMARY KEY (`idJoueur`,`idEffect`),
  KEY `FK_possede_effect_idEffect` (`idEffect`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `stats`
--

CREATE TABLE IF NOT EXISTS `stats` (
  `idStats` int(11) NOT NULL AUTO_INCREMENT,
  `maxHP` int(11) DEFAULT NULL,
  `currentHP` int(11) DEFAULT NULL,
  `maxATK` int(11) DEFAULT NULL,
  `currentATK` int(11) DEFAULT NULL,
  `maxDEF` int(11) DEFAULT NULL,
  `currentDEF` int(11) DEFAULT NULL,
  `maxMP` int(11) DEFAULT NULL,
  `currentMP` int(11) DEFAULT NULL,
  `maxSPA` int(11) DEFAULT NULL,
  `currentSPA` int(11) DEFAULT NULL,
  `maxSPD` int(11) DEFAULT NULL,
  `currentSPD` int(11) DEFAULT NULL,
  `maxSPE` int(11) DEFAULT NULL,
  `currentSPE` int(11) DEFAULT NULL,
  `idjoueur` int(11) DEFAULT NULL,
  `idmonstre` int(11) DEFAULT NULL,
  PRIMARY KEY (`idStats`),
  KEY `FK_Stats_joueur_idjoueur` (`idjoueur`),
  KEY `FK_Stats_monstre_idmonstre` (`idmonstre`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `stats`
--

INSERT INTO `stats` (`idStats`, `maxHP`, `currentHP`, `maxATK`, `currentATK`, `maxDEF`, `currentDEF`, `maxMP`, `currentMP`, `maxSPA`, `currentSPA`, `maxSPD`, `currentSPD`, `maxSPE`, `currentSPE`, `idjoueur`, `idmonstre`) VALUES
(1, 10, 10, 5, 5, 5, 5, 10, 10, 5, 5, 5, 5, 5, 5, 1, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `tuile`
--

CREATE TABLE IF NOT EXISTS `tuile` (
  `idTuile` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) DEFAULT NULL,
  `positionLigne` int(50) DEFAULT NULL,
  `idFloor` int(11) DEFAULT NULL,
  `positionColonne` int(50) DEFAULT NULL,
  PRIMARY KEY (`idTuile`),
  KEY `FK_Tuile_idFloor` (`idFloor`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=446 ;

--
-- Contenu de la table `tuile`
--

INSERT INTO `tuile` (`idTuile`, `type`, `positionLigne`, `idFloor`, `positionColonne`) VALUES
(5, '1', 0, 1, 0),
(6, '1', 0, 1, 1),
(7, '1', 0, 1, 2),
(8, '1', 0, 1, 3),
(9, '1', 0, 1, 4),
(10, '1', 0, 1, 5),
(11, '1', 0, 1, 6),
(12, '1', 0, 1, 7),
(13, '1', 0, 1, 8),
(14, '1', 0, 1, 9),
(15, '1', 0, 1, 10),
(16, '1', 0, 1, 11),
(17, '1', 0, 1, 12),
(18, '1', 0, 1, 13),
(19, '1', 0, 1, 14),
(20, '1', 0, 1, 15),
(21, '1', 0, 1, 16),
(22, '1', 0, 1, 17),
(23, '1', 0, 1, 18),
(24, '1', 0, 1, 19),
(25, '1', 0, 1, 20),
(26, '1', 1, 1, 0),
(27, '0', 1, 1, 1),
(28, '0', 1, 1, 2),
(29, '0', 1, 1, 3),
(30, '0', 1, 1, 4),
(31, '0', 1, 1, 5),
(32, '0', 1, 1, 6),
(33, '0', 1, 1, 7),
(34, '0', 1, 1, 8),
(35, '0', 1, 1, 9),
(36, '0', 1, 1, 10),
(37, '0', 1, 1, 11),
(38, '0', 1, 1, 12),
(39, '0', 1, 1, 13),
(40, '0', 1, 1, 14),
(41, '0', 1, 1, 15),
(42, '0', 1, 1, 16),
(43, '0', 1, 1, 17),
(44, '0', 1, 1, 18),
(45, '0', 1, 1, 19),
(46, '1', 1, 1, 20),
(47, '1', 2, 1, 0),
(48, '0', 2, 1, 1),
(49, '1', 2, 1, 2),
(50, '1', 2, 1, 3),
(51, '1', 2, 1, 4),
(52, '1', 2, 1, 5),
(53, '1', 2, 1, 6),
(54, '1', 2, 1, 7),
(55, '1', 2, 1, 8),
(56, '1', 2, 1, 9),
(57, '1', 2, 1, 10),
(58, '1', 2, 1, 11),
(59, '1', 2, 1, 12),
(60, '1', 2, 1, 13),
(61, '1', 2, 1, 14),
(62, '1', 2, 1, 15),
(63, '1', 2, 1, 16),
(64, '1', 2, 1, 17),
(65, '1', 2, 1, 18),
(66, '0', 2, 1, 19),
(67, '1', 2, 1, 20),
(68, '1', 3, 1, 0),
(69, '0', 3, 1, 1),
(70, '1', 3, 1, 2),
(71, '0', 3, 1, 3),
(72, '0', 3, 1, 4),
(73, '0', 3, 1, 5),
(74, '0', 3, 1, 6),
(75, '0', 3, 1, 7),
(76, '0', 3, 1, 8),
(77, '0', 3, 1, 9),
(78, '0', 3, 1, 10),
(79, '0', 3, 1, 11),
(80, '0', 3, 1, 12),
(81, '0', 3, 1, 13),
(82, '0', 3, 1, 14),
(83, '0', 3, 1, 15),
(84, '0', 3, 1, 16),
(85, '0', 3, 1, 17),
(86, '1', 3, 1, 18),
(87, '0', 3, 1, 19),
(88, '1', 3, 1, 20),
(89, '1', 4, 1, 0),
(90, '0', 4, 1, 1),
(91, '1', 4, 1, 2),
(92, '0', 4, 1, 3),
(93, '0', 4, 1, 4),
(94, '0', 4, 1, 5),
(95, '2', 4, 1, 6),
(96, '0', 4, 1, 7),
(97, '0', 4, 1, 8),
(98, '0', 4, 1, 9),
(99, '0', 4, 1, 10),
(100, '0', 4, 1, 11),
(101, '0', 4, 1, 12),
(102, '0', 4, 1, 13),
(103, '2', 4, 1, 14),
(104, '0', 4, 1, 15),
(105, '0', 4, 1, 16),
(106, '0', 4, 1, 17),
(107, '1', 4, 1, 18),
(108, '0', 4, 1, 19),
(109, '1', 4, 1, 20),
(110, '1', 5, 1, 0),
(111, '0', 5, 1, 1),
(112, '0', 5, 1, 2),
(113, '0', 5, 1, 3),
(114, '0', 5, 1, 4),
(115, '0', 5, 1, 5),
(116, '0', 5, 1, 6),
(117, '0', 5, 1, 7),
(118, '0', 5, 1, 8),
(119, '0', 5, 1, 9),
(120, '0', 5, 1, 10),
(121, '0', 5, 1, 11),
(122, '0', 5, 1, 12),
(123, '0', 5, 1, 13),
(124, '0', 5, 1, 14),
(125, '0', 5, 1, 15),
(126, '0', 5, 1, 16),
(127, '0', 5, 1, 17),
(128, '1', 5, 1, 18),
(129, '0', 5, 1, 19),
(130, 'E', 5, 1, 20),
(131, '1', 6, 1, 0),
(132, '1', 6, 1, 1),
(133, '1', 6, 1, 2),
(134, '0', 6, 1, 3),
(135, '0', 6, 1, 4),
(136, '0', 6, 1, 5),
(137, '0', 6, 1, 6),
(138, 'M', 6, 1, 7),
(139, '0', 6, 1, 8),
(140, '0', 6, 1, 9),
(141, '0', 6, 1, 10),
(142, '0', 6, 1, 11),
(143, '0', 6, 1, 12),
(144, '0', 6, 1, 13),
(145, '0', 6, 1, 14),
(146, '0', 6, 1, 15),
(147, '0', 6, 1, 16),
(148, '0', 6, 1, 17),
(149, '1', 6, 1, 18),
(150, '1', 6, 1, 19),
(151, '1', 6, 1, 20),
(152, '1', 7, 1, 0),
(153, '1', 7, 1, 1),
(154, '1', 7, 1, 2),
(155, '0', 7, 1, 3),
(156, '0', 7, 1, 4),
(157, '0', 7, 1, 5),
(158, '0', 7, 1, 6),
(159, '0', 7, 1, 7),
(160, '0', 7, 1, 8),
(161, '0', 7, 1, 9),
(162, '0', 7, 1, 10),
(163, '0', 7, 1, 11),
(164, '0', 7, 1, 12),
(165, '0', 7, 1, 13),
(166, '0', 7, 1, 14),
(167, '0', 7, 1, 15),
(168, '0', 7, 1, 16),
(169, '0', 7, 1, 17),
(170, '1', 7, 1, 18),
(171, '1', 7, 1, 19),
(172, '1', 7, 1, 20),
(173, '1', 8, 1, 0),
(174, '1', 8, 1, 1),
(175, '1', 8, 1, 2),
(176, '1', 8, 1, 3),
(177, '1', 8, 1, 4),
(178, '1', 8, 1, 5),
(179, '1', 8, 1, 6),
(180, '1', 8, 1, 7),
(181, '1', 8, 1, 8),
(182, '1', 8, 1, 9),
(183, '0', 8, 1, 10),
(184, '1', 8, 1, 11),
(185, '1', 8, 1, 12),
(186, '1', 8, 1, 13),
(187, '1', 8, 1, 14),
(188, '1', 8, 1, 15),
(189, '1', 8, 1, 16),
(190, '1', 8, 1, 17),
(191, '1', 8, 1, 18),
(192, '1', 8, 1, 19),
(193, '1', 8, 1, 20),
(194, '1', 9, 1, 0),
(195, '0', 9, 1, 1),
(196, '0', 9, 1, 2),
(197, '0', 9, 1, 3),
(198, '0', 9, 1, 4),
(199, '0', 9, 1, 5),
(200, '0', 9, 1, 6),
(201, '1', 9, 1, 7),
(202, '1', 9, 1, 8),
(203, '0', 9, 1, 9),
(204, '0', 9, 1, 10),
(205, '0', 9, 1, 11),
(206, '1', 9, 1, 12),
(207, '1', 9, 1, 13),
(208, '0', 9, 1, 14),
(209, '0', 9, 1, 15),
(210, '0', 9, 1, 16),
(211, '0', 9, 1, 17),
(212, '0', 9, 1, 18),
(213, '1', 9, 1, 19),
(214, '1', 9, 1, 20),
(215, '1', 10, 1, 0),
(216, '0', 10, 1, 1),
(217, '0', 10, 1, 2),
(218, '0', 10, 1, 3),
(219, '0', 10, 1, 4),
(220, '0', 10, 1, 5),
(221, '0', 10, 1, 6),
(222, '0', 10, 1, 7),
(223, '0', 10, 1, 8),
(224, '0', 10, 1, 9),
(225, '0', 10, 1, 10),
(226, '0', 10, 1, 11),
(227, '0', 10, 1, 12),
(228, '0', 10, 1, 13),
(229, '0', 10, 1, 14),
(230, '0', 10, 1, 15),
(231, '1', 10, 1, 16),
(232, '0', 10, 1, 17),
(233, '0', 10, 1, 18),
(234, '0', 10, 1, 19),
(235, '1', 10, 1, 20),
(236, '1', 11, 1, 0),
(237, '1', 11, 1, 1),
(238, '1', 11, 1, 2),
(239, '1', 11, 1, 3),
(240, '1', 11, 1, 4),
(241, '1', 11, 1, 5),
(242, '1', 11, 1, 6),
(243, '1', 11, 1, 7),
(244, '1', 11, 1, 8),
(245, '1', 11, 1, 9),
(246, '1', 11, 1, 10),
(247, '1', 11, 1, 11),
(248, '1', 11, 1, 12),
(249, '1', 11, 1, 13),
(250, '1', 11, 1, 14),
(251, '1', 11, 1, 15),
(252, '1', 11, 1, 16),
(253, '0', 11, 1, 17),
(254, '0', 11, 1, 18),
(255, '0', 11, 1, 19),
(256, '1', 11, 1, 20),
(257, '1', 12, 1, 0),
(258, '0', 12, 1, 1),
(259, '0', 12, 1, 2),
(260, '0', 12, 1, 3),
(261, '0', 12, 1, 4),
(262, '0', 12, 1, 5),
(263, '0', 12, 1, 6),
(264, '0', 12, 1, 7),
(265, '0', 12, 1, 8),
(266, '0', 12, 1, 9),
(267, '0', 12, 1, 10),
(268, '0', 12, 1, 11),
(269, '0', 12, 1, 12),
(270, '0', 12, 1, 13),
(271, '0', 12, 1, 14),
(272, '0', 12, 1, 15),
(273, '1', 12, 1, 16),
(274, '0', 12, 1, 17),
(275, '0', 12, 1, 18),
(276, '0', 12, 1, 19),
(277, '1', 12, 1, 20),
(278, '1', 13, 1, 0),
(279, '0', 13, 1, 1),
(280, '0', 13, 1, 2),
(281, '0', 13, 1, 3),
(282, '0', 13, 1, 4),
(283, '0', 13, 1, 5),
(284, '0', 13, 1, 6),
(285, '0', 13, 1, 7),
(286, '0', 13, 1, 8),
(287, 'M', 13, 1, 9),
(288, '0', 13, 1, 10),
(289, '0', 13, 1, 11),
(290, '0', 13, 1, 12),
(291, '0', 13, 1, 13),
(292, '0', 13, 1, 14),
(293, '0', 13, 1, 15),
(294, '0', 13, 1, 16),
(295, '0', 13, 1, 17),
(296, '0', 13, 1, 18),
(297, '0', 13, 1, 19),
(298, '1', 13, 1, 20),
(299, '1', 14, 1, 0),
(300, '1', 14, 1, 1),
(301, '1', 14, 1, 2),
(302, '0', 14, 1, 3),
(303, '1', 14, 1, 4),
(304, '1', 14, 1, 5),
(305, '1', 14, 1, 6),
(306, '1', 14, 1, 7),
(307, '1', 14, 1, 8),
(308, '1', 14, 1, 9),
(309, '1', 14, 1, 10),
(310, '1', 14, 1, 11),
(311, '1', 14, 1, 12),
(312, '1', 14, 1, 13),
(313, '1', 14, 1, 14),
(314, '1', 14, 1, 15),
(315, '1', 14, 1, 16),
(316, '1', 14, 1, 17),
(317, '1', 14, 1, 18),
(318, '1', 14, 1, 19),
(319, '1', 14, 1, 20),
(320, '1', 15, 1, 0),
(321, '0', 15, 1, 1),
(322, '0', 15, 1, 2),
(323, '0', 15, 1, 3),
(324, '1', 15, 1, 4),
(325, '1', 15, 1, 5),
(326, '1', 15, 1, 6),
(327, '0', 15, 1, 7),
(328, '0', 15, 1, 8),
(329, '1', 15, 1, 9),
(330, '0', 15, 1, 10),
(331, '0', 15, 1, 11),
(332, '0', 15, 1, 12),
(333, '0', 15, 1, 13),
(334, '1', 15, 1, 14),
(335, '0', 15, 1, 15),
(336, '0', 15, 1, 16),
(337, '0', 15, 1, 17),
(338, '0', 15, 1, 18),
(339, '0', 15, 1, 19),
(340, '1', 15, 1, 20),
(341, '1', 16, 1, 0),
(342, '0', 16, 1, 1),
(343, '1', 16, 1, 2),
(344, '1', 16, 1, 3),
(345, '1', 16, 1, 4),
(346, '1', 16, 1, 5),
(347, '1', 16, 1, 6),
(348, '0', 16, 1, 7),
(349, '0', 16, 1, 8),
(350, '0', 16, 1, 9),
(351, '0', 16, 1, 10),
(352, '0', 16, 1, 11),
(353, '0', 16, 1, 12),
(354, '0', 16, 1, 13),
(355, '0', 16, 1, 14),
(356, '0', 16, 1, 15),
(357, '0', 16, 1, 16),
(358, '0', 16, 1, 17),
(359, '0', 16, 1, 18),
(360, '0', 16, 1, 19),
(361, '1', 16, 1, 20),
(362, '1', 17, 1, 0),
(363, '0', 17, 1, 1),
(364, '0', 17, 1, 2),
(365, '0', 17, 1, 3),
(366, '0', 17, 1, 4),
(367, '0', 17, 1, 5),
(368, '0', 17, 1, 6),
(369, '0', 17, 1, 7),
(370, '0', 17, 1, 8),
(371, '1', 17, 1, 9),
(372, '0', 17, 1, 10),
(373, '0', 17, 1, 11),
(374, '0', 17, 1, 12),
(375, '0', 17, 1, 13),
(376, '1', 17, 1, 14),
(377, '0', 17, 1, 15),
(378, '0', 17, 1, 16),
(379, '0', 17, 1, 17),
(380, '0', 17, 1, 18),
(381, '0', 17, 1, 19),
(382, '1', 17, 1, 20),
(383, '1', 18, 1, 0),
(384, '1', 18, 1, 1),
(385, '1', 18, 1, 2),
(386, '1', 18, 1, 3),
(387, '1', 18, 1, 4),
(388, '1', 18, 1, 5),
(389, '1', 18, 1, 6),
(390, '1', 18, 1, 7),
(391, '1', 18, 1, 8),
(392, '1', 18, 1, 9),
(393, '1', 18, 1, 10),
(394, '1', 18, 1, 11),
(395, '1', 18, 1, 12),
(396, '1', 18, 1, 13),
(397, '1', 18, 1, 14),
(398, '1', 18, 1, 15),
(399, '1', 18, 1, 16),
(400, '1', 18, 1, 17),
(401, '1', 18, 1, 18),
(402, '0', 18, 1, 19),
(403, '1', 18, 1, 20),
(404, '1', 19, 1, 0),
(405, '0', 19, 1, 1),
(406, '0', 19, 1, 2),
(407, '0', 19, 1, 3),
(408, '0', 19, 1, 4),
(409, '0', 19, 1, 5),
(410, '0', 19, 1, 6),
(411, '0', 19, 1, 7),
(412, '0', 19, 1, 8),
(413, '0', 19, 1, 9),
(414, '0', 19, 1, 10),
(415, '0', 19, 1, 11),
(416, '0', 19, 1, 12),
(417, '0', 19, 1, 13),
(418, '0', 19, 1, 14),
(419, '0', 19, 1, 15),
(420, '0', 19, 1, 16),
(421, 'P', 19, 1, 17),
(422, '0', 19, 1, 18),
(423, '0', 19, 1, 19),
(424, '1', 19, 1, 20),
(425, '1', 20, 1, 0),
(426, '1', 20, 1, 1),
(427, '1', 20, 1, 2),
(428, '1', 20, 1, 3),
(429, '1', 20, 1, 4),
(430, '1', 20, 1, 5),
(431, '1', 20, 1, 6),
(432, '1', 20, 1, 7),
(433, '1', 20, 1, 8),
(434, '1', 20, 1, 9),
(435, '1', 20, 1, 10),
(436, '1', 20, 1, 11),
(437, '1', 20, 1, 12),
(438, '1', 20, 1, 13),
(439, '1', 20, 1, 14),
(440, '1', 20, 1, 15),
(441, '1', 20, 1, 16),
(442, '1', 20, 1, 17),
(443, '1', 20, 1, 18),
(444, '1', 20, 1, 19),
(445, '1', 20, 1, 20);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `est_equipe`
--
ALTER TABLE `est_equipe`
  ADD CONSTRAINT `FK_est_equipe_idItem` FOREIGN KEY (`idItem`) REFERENCES `item` (`idItem`),
  ADD CONSTRAINT `FK_est_equipe_idJoueur` FOREIGN KEY (`idJoueur`) REFERENCES `joueur` (`idJoueur`);

--
-- Contraintes pour la table `floor`
--
ALTER TABLE `floor`
  ADD CONSTRAINT `FK_Floor_idMap` FOREIGN KEY (`idMap`) REFERENCES `map` (`idMap`);

--
-- Contraintes pour la table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `FK_Item_joueur_idjoueur` FOREIGN KEY (`idjoueur`) REFERENCES `joueur` (`idJoueur`),
  ADD CONSTRAINT `FK_Item_monstre_idmonstre` FOREIGN KEY (`idmonstre`) REFERENCES `monstre` (`idMonstre`);

--
-- Contraintes pour la table `joueur`
--
ALTER TABLE `joueur`
  ADD CONSTRAINT `FK_Joueur_lvl_idlvl` FOREIGN KEY (`idlvl`) REFERENCES `lvl` (`idLvl`),
  ADD CONSTRAINT `FK_Joueur_money_idmoney` FOREIGN KEY (`idmoney`) REFERENCES `money` (`idMoney`),
  ADD CONSTRAINT `FK_Joueur_position_idposition` FOREIGN KEY (`idposition`) REFERENCES `position` (`idPosition`),
  ADD CONSTRAINT `FK_Joueur_stats_idstats` FOREIGN KEY (`idstats`) REFERENCES `stats` (`idStats`);

--
-- Contraintes pour la table `lvl`
--
ALTER TABLE `lvl`
  ADD CONSTRAINT `FK_lvl_joueur_idjoueur` FOREIGN KEY (`idjoueur`) REFERENCES `joueur` (`idJoueur`),
  ADD CONSTRAINT `FK_lvl_monstre_idmonstre` FOREIGN KEY (`idmonstre`) REFERENCES `monstre` (`idMonstre`);

--
-- Contraintes pour la table `money`
--
ALTER TABLE `money`
  ADD CONSTRAINT `FK_Money_joueur_idjoueur` FOREIGN KEY (`idjoueur`) REFERENCES `joueur` (`idJoueur`);

--
-- Contraintes pour la table `monstre`
--
ALTER TABLE `monstre`
  ADD CONSTRAINT `FK_Monstre_lvl_idlvl` FOREIGN KEY (`idlvl`) REFERENCES `lvl` (`idLvl`),
  ADD CONSTRAINT `FK_Monstre_position_idposition` FOREIGN KEY (`idposition`) REFERENCES `position` (`idPosition`),
  ADD CONSTRAINT `FK_Monstre_stats_idstats` FOREIGN KEY (`idstats`) REFERENCES `stats` (`idStats`);

--
-- Contraintes pour la table `monstre_effect`
--
ALTER TABLE `monstre_effect`
  ADD CONSTRAINT `FK_Monstre_effect_idEffect` FOREIGN KEY (`idEffect`) REFERENCES `effect` (`idEffect`),
  ADD CONSTRAINT `FK_Monstre_effect_idMonstre` FOREIGN KEY (`idMonstre`) REFERENCES `monstre` (`idMonstre`);

--
-- Contraintes pour la table `position`
--
ALTER TABLE `position`
  ADD CONSTRAINT `FK_position_idMap` FOREIGN KEY (`idMap`) REFERENCES `map` (`idMap`),
  ADD CONSTRAINT `FK_position_joueur_idjoueur` FOREIGN KEY (`idjoueur`) REFERENCES `joueur` (`idJoueur`),
  ADD CONSTRAINT `FK_position_monstre_idmonstre` FOREIGN KEY (`idmonstre`) REFERENCES `monstre` (`idMonstre`);

--
-- Contraintes pour la table `possede_effect`
--
ALTER TABLE `possede_effect`
  ADD CONSTRAINT `FK_possede_effect_idEffect` FOREIGN KEY (`idEffect`) REFERENCES `effect` (`idEffect`),
  ADD CONSTRAINT `FK_possede_effect_idJoueur` FOREIGN KEY (`idJoueur`) REFERENCES `joueur` (`idJoueur`);

--
-- Contraintes pour la table `stats`
--
ALTER TABLE `stats`
  ADD CONSTRAINT `FK_Stats_joueur_idjoueur` FOREIGN KEY (`idjoueur`) REFERENCES `joueur` (`idJoueur`),
  ADD CONSTRAINT `FK_Stats_monstre_idmonstre` FOREIGN KEY (`idmonstre`) REFERENCES `monstre` (`idMonstre`);

--
-- Contraintes pour la table `tuile`
--
ALTER TABLE `tuile`
  ADD CONSTRAINT `FK_Tuile_idFloor` FOREIGN KEY (`idFloor`) REFERENCES `floor` (`idFloor`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
