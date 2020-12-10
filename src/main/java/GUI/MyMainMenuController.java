package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.almasb.fxgl.dsl.FXGL;

import game.and.map.GameApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
public class MyMainMenuController {
	
	 static MediaPlayer mediaPlayer;
	
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
	/**
	 * fonction qui initialise le menu principal et les boutons
	 *
	 *
	 *@author Gael
	 *
	 *
	 */
	
	
	
	private void initialize()
	{
		
		
		versionJeu.setText(GameApp.vJeu);
		startButton.setOnMouseEntered(e -> {
			
			startButton.setStyle("-fx-background-color: #914206; ");
			buttonSFX();
		}
		);
		startButton.setOnMouseExited(e -> startButton.setStyle("-fx-background-color: grey; "));
		
		startButton.setOnMouseClicked(e -> {
			
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/GUI/selectCharacter.fxml"));
		    
				try {
					AnchorPane layout = loader.<AnchorPane>load();	
					Scene scene = new Scene(layout); 
					Stage stage = new Stage();
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.setScene(scene);
					stage.show();
					
				} catch (IOException f) {
					f.printStackTrace();
				}	
				
		});
		
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
		
		
	    continueButton.setOnAction(e -> continueGame());
	   
	    mainMenuTheme();
	   
	}
	
	
	/**
	 * lance le theme du menu principal
	 * (le volume est a 0.0 au lieu de 0.5 pour eviter d avoir des problemes de droits)
	 * @author Gael
	 */
	
	public void mainMenuTheme()
	 {
		final URL musicURL = getClass().getResource("/BGM/MainMenuTheme.mp3");   
		final  Media media = new Media(musicURL.toExternalForm());
		mediaPlayer = new MediaPlayer(media); 
		mediaPlayer.setVolume(0.0);
		mediaPlayer.setOnEndOfMedia(new Runnable() {
		       public void run() {
		         mediaPlayer.seek(Duration.ZERO);
		       }
		   });
		
	 }
	
	/**
	 * son des boutons
	 * 
	 * @author Gael
	 */
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
	/**
	 * lance le jeu
	 * 
	 * @author Gael
	 */
	private void exitMenu()
	{
		if( InGameController.scheduledExecutorService != null)
		{
			 InGameController.scheduledExecutorService.shutdown();
		}
		 FXGL.getGameController().exit();
		
	}
	
	/**
	 * lance le jeu
	 * 
	 * @author Gael
	 */
	private void continueGame()
	{
		
		mediaPlayer.stop();
		
		FXGL.getGameController().startNewGame();
		
	}
	
	
	
	

}
