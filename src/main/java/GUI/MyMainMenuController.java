package GUI;

import java.net.URL;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.profile.DataFile;

import game.and.map.GameApp;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.*
;
public class MyMainMenuController {
	
	 MediaPlayer mediaPlayer;
	
	 // Controller
    @FXML
    private static AnchorPane Anchor;
    
    @FXML
  	private Button exitButton;
    
	@FXML
	private Button startButton;
	
	@FXML
	private Button continueButton;
	
	@FXML
	private Button settingsButton;
	
	@FXML
	private Label versionJeu;
	
	 @FXML
	 private void closeRequest() {
	        
	       
	  Stage s = (Stage)versionJeu.getScene().getWindow();
	  s.setOnCloseRequest(e -> {
		
		  Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous vraiment fermer l'application?", ButtonType.YES, ButtonType.NO);
	        ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
	        if (ButtonType.NO.equals(result)) {
	          
	            e.consume();
	        }
	        else
	        {
	        	
	        	if(InGameController.scheduledExecutorService != null)
	        	{
	        	InGameController.scheduledExecutorService.shutdown();
	        	}
	        	else
	        	{
	        		
	        	}
	        }
	      
	 
	  });
	 }
	 
	@FXML
	private void initialize()
	{
		
		
		versionJeu.setText(GameApp.vJeu);
		startButton.setOnMouseEntered(e -> {
			
			startButton.setStyle("-fx-background-color: #914206; ");
			buttonSFX();
		}
		);
		startButton.setOnMouseExited(e -> startButton.setStyle("-fx-background-color: grey; "));
		continueButton.setOnMouseEntered(e -> {
			continueButton.setStyle("-fx-background-color: #914206; ");
			buttonSFX();
		}
		);
	    continueButton.setOnMouseExited(e -> continueButton.setStyle("-fx-background-color: grey; "));
		settingsButton.setOnMouseEntered(e -> {
		   settingsButton.setStyle("-fx-background-color: #914206; ");
		   buttonSFX();
		});
		settingsButton.setOnMouseExited(e ->   settingsButton.setStyle("-fx-background-color: grey; "));
		exitButton.setOnMouseEntered(e -> {
		  exitButton.setStyle("-fx-background-color: #914206; ");
		  buttonSFX();
		}
		);
		exitButton.setOnMouseExited(e -> exitButton.setStyle("-fx-background-color: grey; "));
	    mainMenuTheme();
	    continueButton.setOnAction(e -> {
	    	
	    	
	    });
	    
	    
	    
	    continueButton.setOnAction(e -> continueGame());
	   
	   
	
	}
	
	
	
	
	public void mainMenuTheme()
	 {
		final URL musicURL = getClass().getResource("/BGM/MainMenuTheme.mp3");   
		final  Media media = new Media(musicURL.toExternalForm());
		mediaPlayer = new MediaPlayer(media); 
		mediaPlayer.setVolume(0.5);
		mediaPlayer.setOnEndOfMedia(new Runnable() {
		       public void run() {
		         mediaPlayer.seek(Duration.ZERO);
		       }
		   });
		
	 }
	public void buttonSFX()
	{
		AudioClip a1 = new AudioClip(getClass().getResource("/SFX/buttonSound.mp3").toString());
		a1.play();
	}
	
	
	@FXML
	private void mouseEntered()
	{
		mediaPlayer.play();
	}
	@FXML
	private void mouseExited()
	{
		
	}
	@FXML
	private void exitMenu()
	{
		if( InGameController.scheduledExecutorService != null)
		{
			 InGameController.scheduledExecutorService.shutdown();
		}
		 FXGL.getGameController().exit();
		
	}
	
	@FXML
	private void startGame() throws SQLException, InterruptedException
	{
		
		
		
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
		demandeRequete.executeUpdate("INSERT INTO `projetpoagl`.`joueur` (`idJoueur`, `nom`, `entityType`, `idstats`, `idmoney`, `idlvl`, `idposition`) VALUES ('1', 'joueur 1', NULL, NULL, NULL, NULL, NULL)");
		
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
		
		
		
		
		
		
		
		mediaPlayer.stop();
		
		FXGL.getGameController().startNewGame();
		
	}
	
	
	
	
	
	
	
	private void continueGame()
	{
		
		mediaPlayer.stop();
		
		FXGL.getGameController().startNewGame();
		
	}
	
	
	
	

}
