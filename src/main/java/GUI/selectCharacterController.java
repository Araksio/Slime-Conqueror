package GUI;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.almasb.fxgl.dsl.FXGL;

import game.and.map.Write;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * selectCharacterController permets de gérer la séléction du pseudo du joueur.
 * Elle est composé d'un TextField, pour écrire son pseudo
 * Et d'un boutton pour confirmer.
 * @author Gaël
 *
 */
public class selectCharacterController {
@FXML
private TextField selectPseudo;
@FXML
private Button startButton;

/**
 * On initialise le controller et on mets comme PromptText
 * (texte qui s'enlève quand l'utilisateur écrit), "Pseudo"
 * @author Gaël
 */
@FXML
private void initialize()
{
	
	selectPseudo.setPromptText("Pseudo");
}

/**
 * test si le pseudo est correct puis crée les informations du joueur dans la
 * base de donnée et lance le jeu
 *  
 * @author Gael, Esteban
 * 
 * @throws SQLException
 * @throws InterruptedException
 * 
 * 
 * 
 */
@FXML
private void startGame() throws SQLException, InterruptedException {
	
	{
		/*
		 * Si le pseudo du joueur n'est pas conformer,
		 * On ouvre une fenêtre d'alerte
		 */
		if(selectPseudo.getText().isEmpty() || selectPseudo.getText().length() < 3 || selectPseudo.getText().length() > 12)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");
			alert.setHeaderText("Erreur Pseudo");
			alert.setContentText("Votre pseudo ne doit pas être vide et doit être contenue entre 3 et 12 caractères");
			alert.showAndWait();
		}
		else
		{
			 try {
				 //decommenter quand ce sera fixe
//					new Write(3,3,"map_level0.txt");
				 
				 //solution de secours
				 new Write(2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

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
						
						//effacer l'id joueur des items sinon sql pas content
						
						demandeRequete.executeUpdate("UPDATE `projetpoagl`.`item` SET `idjoueur` = NULL");
					//	demandeRequete.executeUpdate("UPDATE `projetpoagl`.`item` SET `idMonstre` = NULL");
						
						
				//on remet l argent du joueur a 0
						
						demandeRequete.executeUpdate("update `money` set `moneyPlayer` = 500,`moneyBank` = 1000 where idMoney = 1");
						
						
						
						demandeRequete.executeUpdate("delete from effect");
						demandeRequete.executeUpdate("delete from est_equipe");
			//on ne supprime pas les map			demandeRequete.executeUpdate("delete from map");
						demandeRequete.executeUpdate("delete from item");
						demandeRequete.executeUpdate("delete from joueur");
//			on recree juste le lvl 1			demandeRequete.executeUpdate("delete from lvl");
			//on ne supprime pas les floors			demandeRequete.executeUpdate("delete from floor");
//			one ne supprime pas l argent du joueur			demandeRequete.executeUpdate("delete from money");
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
						
						
						//cree le lvl du joueur
						demandeRequete.executeUpdate("UPDATE `projetpoagl`.`lvl` SET `level` = '1',`totalXP` = '0',`currentXP` = '0',`xpNeeded` = '20',`pointBonus` = '0' WHERE `lvl`.`idLvl` = 1");
						
						
						
						//lie le joueur,les stats, l'argent et les lvl entre eux
						
						demandeRequete.executeUpdate("UPDATE `projetpoagl`.`joueur` SET `idstats` = '1' WHERE `joueur`.`idJoueur` = 1");
						demandeRequete.executeUpdate("UPDATE `projetpoagl`.`joueur` SET `idmoney` = '1' WHERE `joueur`.`idJoueur` = 1");
						demandeRequete.executeUpdate("UPDATE `projetpoagl`.`joueur` SET `idlvl` = '1' WHERE `joueur`.`idJoueur` = 1");
				// a decommenter une fois la position faite		demandeRequete.executeUpdate("UPDATE `projetpoagl`.`joueur` SET `idPosition` = '1' WHERE `joueur`.`idJoueur` = 1");

						demandeRequete.executeUpdate("UPDATE `projetpoagl`.`stats` SET `idjoueur` = '1' WHERE `stats`.`idStats` = 1");
						demandeRequete.executeUpdate("UPDATE `projetpoagl`.`money` SET `idjoueur` = '1' WHERE `money`.`idMoney` = 1");
						demandeRequete.executeUpdate("UPDATE `projetpoagl`.`lvl` SET `idjoueur` = '1' WHERE `lvl`.`idLvl` = 1");
				//pareil		demandeRequete.executeUpdate("");
						
							
					
					
					
					
					
					
					
					
			MyMainMenuController.mediaPlayer.stop(); // On arrête la musique
			Stage s = (Stage)startButton.getScene().getWindow();
			s.close(); // On fermer la fenêtre de séléction de pseudo
			
			
			FXGL.getGameController().startNewGame(); // Puis on lance désormais le jeu
	}
	}
}
}