package GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.almasb.fxgl.dsl.FXGL;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class selectCharacterController {
@FXML
private TextField selectPseudo;
@FXML
private Button startButton;
@FXML
private void initialize()
{
	
	selectPseudo.setPromptText("Pseudo");
}


@FXML
private void startGame() throws SQLException {
	
	if(selectPseudo.getText().isEmpty() || selectPseudo.getText().length() < 3 || selectPseudo.getText().length() > 12)
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erreur Pseudonyme");
		alert.setHeaderText("Erreur Pseudo");
		alert.setContentText("Votre pseudo ne doit pas être vide et doit être contenue entre 3 et 12 caractères");
		alert.showAndWait();
	}
	else
	{
	String pseudo = selectPseudo.getText();
	//fonction associé au bouton "nouvelle partie" ou startButton, donc je supprime tous les tuples et recree des tuples de bases
			Connection db = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/projetpoagl?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
			Statement demandeRequete = db.createStatement();
			
			
			
			demandeRequete.executeUpdate("UPDATE `projetpoagl`.`joueur` SET `idstats` = NULL WHERE `joueur`.`idJoueur` = 1");
			demandeRequete.executeUpdate("UPDATE `projetpoagl`.`joueur` SET `idmoney` = NULL WHERE `joueur`.`idJoueur` = 1");
			demandeRequete.executeUpdate("UPDATE `projetpoagl`.`joueur` SET `idlvl` = NULL WHERE `joueur`.`idJoueur` = 1");
	// a decommenter une fois la position faite		demandeRequete.executeUpdate("UPDATE `projetpoagl`.`joueur` SET `idPosition` = '1' WHERE `joueur`.`idJoueur` = 1");

			demandeRequete.executeUpdate("UPDATE `projetpoagl`.`stats` SET `idjoueur` = NULL WHERE `stats`.`idStats` = 1");
			demandeRequete.executeUpdate("UPDATE `projetpoagl`.`money` SET `idjoueur` = NULL WHERE `money`.`idMoney` = 1");
			demandeRequete.executeUpdate("UPDATE `projetpoagl`.`lvl` SET `idjoueur` = NULL WHERE `lvl`.`idLvl` = 1");
	//pareil		demandeRequete.executeUpdate("");
			
			
			demandeRequete.executeUpdate("delete from effect");
			demandeRequete.executeUpdate("delete from est_equipe");
			demandeRequete.executeUpdate("delete from floor");
	//on ne supprime pas les objets		demandeRequete.executeUpdate("delete from item");
			demandeRequete.executeUpdate("delete from joueur");
			demandeRequete.executeUpdate("delete from lvl");
			demandeRequete.executeUpdate("delete from map");
			demandeRequete.executeUpdate("delete from money");
			demandeRequete.executeUpdate("delete from monstre");
			demandeRequete.executeUpdate("delete from monstre_effect");
			demandeRequete.executeUpdate("delete from position");
			demandeRequete.executeUpdate("delete from possede_effect");
			demandeRequete.executeUpdate("delete from stats");
			demandeRequete.executeUpdate("delete from tuile");
			
			
	//maintenant que tout est supprimé, on recree
			
			//cree le joueur
			demandeRequete.executeUpdate("INSERT INTO `projetpoagl`.`joueur` (`idJoueur`, `nom`, `entityType`, `idstats`, `idmoney`, `idlvl`, `idposition`) VALUES ('1', '"+ pseudo +"', NULL, NULL, NULL, NULL, NULL)");
			
			//cree les stats du joueurs
			demandeRequete.executeUpdate("INSERT INTO `projetpoagl`.`stats` (`idStats`, `maxHP`, `currentHP`, `maxATK`, `currentATK`, `maxDEF`, `currentDEF`, `maxMP`, `currentMP`, `maxSPA`, `currentSPA`, `maxSPD`, `currentSPD`, `maxSPE`, `currentSPE`, `idjoueur`, `idmonstre`) VALUES ('1', '10', '10', '5', '5', '5', '5', '10', '10', '5', '5', '5', '5', '5', '5', '1', NULL)");
			
			//cree l'argent du joueur
			demandeRequete.executeUpdate("INSERT INTO `projetpoagl`.`money` (`idMoney`, `moneyPlayer`, `moneyBank`, `idjoueur`) VALUES ('1', '500', '1000', NULL)");
			
			//cree le lvl du joueur
			demandeRequete.executeUpdate("INSERT INTO `projetpoagl`.`lvl` (`idLvl`, `level`, `totalXP`, `currentXP`, `xpNeeded`, `idjoueur`, `idmonstre`) VALUES ('1', '1', '0', '0', '10', NULL, NULL)");
			
			//cree la position du joueur, a faire plus tard 
			
			//lie le joueur,les stats, l'argent et les lvl entre eux
			
			demandeRequete.executeUpdate("UPDATE `projetpoagl`.`joueur` SET `idstats` = '1' WHERE `joueur`.`idJoueur` = 1");
			demandeRequete.executeUpdate("UPDATE `projetpoagl`.`joueur` SET `idmoney` = '1' WHERE `joueur`.`idJoueur` = 1");
			demandeRequete.executeUpdate("UPDATE `projetpoagl`.`joueur` SET `idlvl` = '1' WHERE `joueur`.`idJoueur` = 1");
	// a decommenter une fois la position faite		demandeRequete.executeUpdate("UPDATE `projetpoagl`.`joueur` SET `idPosition` = '1' WHERE `joueur`.`idJoueur` = 1");

			demandeRequete.executeUpdate("UPDATE `projetpoagl`.`stats` SET `idjoueur` = '1' WHERE `stats`.`idStats` = 1");
			demandeRequete.executeUpdate("UPDATE `projetpoagl`.`money` SET `idjoueur` = '1' WHERE `money`.`idMoney` = 1");
			demandeRequete.executeUpdate("UPDATE `projetpoagl`.`lvl` SET `idjoueur` = '1' WHERE `lvl`.`idLvl` = 1");
	//pareil		demandeRequete.executeUpdate("");
			
			
			
			
			
			
			
			MyMainMenuController.mediaPlayer.stop();
			Stage s = (Stage)startButton.getScene().getWindow();
			s.close();
			FXGL.getGameController().startNewGame();
	}
}
}
