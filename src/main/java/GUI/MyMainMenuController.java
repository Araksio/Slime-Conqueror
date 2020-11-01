package GUI;

import java.net.URL;

import com.almasb.fxgl.dsl.FXGL;

import game.and.map.GameApp;
import javafx.fxml.FXML;
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
	private void startGame()
	{
		
		mediaPlayer.stop();
		
		 FXGL.getGameController().startNewGame();
		
	}
	
	

}
