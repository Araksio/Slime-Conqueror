-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Sam 14 Novembre 2020 à 19:29
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
(1, 'rrrrr', NULL, 1, 1, 1, NULL, NULL);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=21 ;

--
-- Contenu de la table `lvl`
--

INSERT INTO `lvl` (`idLvl`, `level`, `totalXP`, `currentXP`, `xpNeeded`, `idjoueur`, `idmonstre`, `pointBonus`) VALUES
(1, 1, 0, 0, 20, 1, NULL, 0),
(2, 2, 20, 0, 50, NULL, NULL, 2),
(3, 3, 70, 0, 100, NULL, NULL, 2),
(4, 4, 170, 0, 200, NULL, NULL, 3),
(5, 5, 370, 0, 400, NULL, NULL, 3),
(6, 6, 370, 0, 750, NULL, NULL, 4),
(7, 7, 1120, 0, 1000, NULL, NULL, 4),
(8, 8, 2120, 0, 1320, NULL, NULL, 5),
(9, 9, 3440, 0, 1780, NULL, NULL, 5),
(10, 10, 5220, 0, 2350, NULL, NULL, 6),
(11, 11, 7570, 0, 2875, NULL, NULL, 6),
(12, 12, 10445, 0, 3210, NULL, NULL, 7),
(13, 13, 13655, 0, 3950, NULL, NULL, 7),
(14, 14, 17605, 0, 4465, NULL, NULL, 8),
(15, 15, 22070, 0, 5000, NULL, NULL, 8),
(16, 16, 27070, 0, 5795, NULL, NULL, 9),
(17, 17, 32865, 0, 6235, NULL, NULL, 9),
(18, 18, 39100, 0, 6965, NULL, NULL, 10),
(19, 19, 46065, 0, 7980, NULL, NULL, 10),
(20, 20, 54045, 0, 123456789, NULL, NULL, 11);

-- --------------------------------------------------------

--
-- Structure de la table `map`
--

CREATE TABLE IF NOT EXISTS `map` (
  `idMap` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idMap`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

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
