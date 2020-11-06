-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  ven. 06 nov. 2020 à 22:56
-- Version du serveur :  8.0.18
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `projetpoagl`
--

-- --------------------------------------------------------

--
-- Structure de la table `effect`
--

DROP TABLE IF EXISTS `effect`;
CREATE TABLE IF NOT EXISTS `effect` (
  `idEffect` int(11) NOT NULL AUTO_INCREMENT,
  `effect` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idEffect`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `est_equipe`
--

DROP TABLE IF EXISTS `est_equipe`;
CREATE TABLE IF NOT EXISTS `est_equipe` (
  `idJoueur` int(11) NOT NULL AUTO_INCREMENT,
  `idItem` int(11) NOT NULL,
  PRIMARY KEY (`idJoueur`,`idItem`),
  KEY `FK_est_equipe_idItem` (`idItem`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `floor`
--

DROP TABLE IF EXISTS `floor`;
CREATE TABLE IF NOT EXISTS `floor` (
  `idFloor` int(11) NOT NULL AUTO_INCREMENT,
  `idMap` int(11) DEFAULT NULL,
  PRIMARY KEY (`idFloor`),
  KEY `FK_Floor_idMap` (`idMap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `item`
--

DROP TABLE IF EXISTS `item`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `joueur`
--

DROP TABLE IF EXISTS `joueur`;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `joueur`
--

INSERT INTO `joueur` (`idJoueur`, `nom`, `entityType`, `idstats`, `idmoney`, `idlvl`, `idposition`, `pointBonusJoueur`) VALUES
(1, 'Onchigo', NULL, 1, 1, 1, NULL, 1);

-- --------------------------------------------------------

--
-- Structure de la table `lvl`
--

DROP TABLE IF EXISTS `lvl`;
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `lvl`
--

INSERT INTO `lvl` (`idLvl`, `level`, `totalXP`, `currentXP`, `xpNeeded`, `idjoueur`, `idmonstre`, `pointBonus`) VALUES
(1, 1, 15, 15, 20, 1, NULL, 0),
(2, 2, 20, 0, 60, NULL, NULL, 2),
(3, 3, 80, 0, 200, NULL, NULL, 2),
(4, 4, 280, 0, 500, NULL, NULL, 3),
(5, 5, 780, 0, 1273, NULL, NULL, 3);

-- --------------------------------------------------------

--
-- Structure de la table `map`
--

DROP TABLE IF EXISTS `map`;
CREATE TABLE IF NOT EXISTS `map` (
  `idMap` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idMap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `money`
--

DROP TABLE IF EXISTS `money`;
CREATE TABLE IF NOT EXISTS `money` (
  `idMoney` int(11) NOT NULL AUTO_INCREMENT,
  `moneyPlayer` int(11) DEFAULT NULL,
  `moneyBank` int(11) DEFAULT NULL,
  `idjoueur` int(11) DEFAULT NULL,
  PRIMARY KEY (`idMoney`),
  KEY `FK_Money_joueur_idjoueur` (`idjoueur`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `money`
--

INSERT INTO `money` (`idMoney`, `moneyPlayer`, `moneyBank`, `idjoueur`) VALUES
(1, 500, 1000, 1);

-- --------------------------------------------------------

--
-- Structure de la table `monstre`
--

DROP TABLE IF EXISTS `monstre`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `monstre_effect`
--

DROP TABLE IF EXISTS `monstre_effect`;
CREATE TABLE IF NOT EXISTS `monstre_effect` (
  `idEffect` int(11) NOT NULL AUTO_INCREMENT,
  `idMonstre` int(11) NOT NULL,
  PRIMARY KEY (`idEffect`,`idMonstre`),
  KEY `FK_Monstre_effect_idMonstre` (`idMonstre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `position`
--

DROP TABLE IF EXISTS `position`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `possede_effect`
--

DROP TABLE IF EXISTS `possede_effect`;
CREATE TABLE IF NOT EXISTS `possede_effect` (
  `idJoueur` int(11) NOT NULL AUTO_INCREMENT,
  `idEffect` int(11) NOT NULL,
  PRIMARY KEY (`idJoueur`,`idEffect`),
  KEY `FK_possede_effect_idEffect` (`idEffect`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `stats`
--

DROP TABLE IF EXISTS `stats`;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `stats`
--

INSERT INTO `stats` (`idStats`, `maxHP`, `currentHP`, `maxATK`, `currentATK`, `maxDEF`, `currentDEF`, `maxMP`, `currentMP`, `maxSPA`, `currentSPA`, `maxSPD`, `currentSPD`, `maxSPE`, `currentSPE`, `idjoueur`, `idmonstre`) VALUES
(1, 10, 8, 5, 5, 5, 5, 10, 10, 5, 5, 5, 5, 5, 5, 1, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `tuile`
--

DROP TABLE IF EXISTS `tuile`;
CREATE TABLE IF NOT EXISTS `tuile` (
  `idTuile` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) DEFAULT NULL,
  `position` varchar(50) DEFAULT NULL,
  `idFloor` int(11) DEFAULT NULL,
  PRIMARY KEY (`idTuile`),
  KEY `FK_Tuile_idFloor` (`idFloor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contraintes pour les tables déchargées
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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
