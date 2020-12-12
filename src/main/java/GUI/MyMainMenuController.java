package GUI;

import java.io.IOException;
import java.net.URL;

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

/**
 * MyManuMenuController permet de générer le menu principale en jeu.
 * Ce menu est composé de :
 * 
 * Un boutton pour commencer une nouvelle partie 
 * Un boutton pour continuer une partie 
 * Un boutton options
 * Un boutton pour quitter le jeu.
 * @author Gaël
 *
 */
public class MyMainMenuController {
	
	 static MediaPlayer mediaPlayer; // Une variable MediaPlayer pour jouer de la musique
	
	
    @FXML
    private static AnchorPane Anchor; // L'anchorPane du menu principale.
    
    @FXML
  	private Button exitButton;
    
	@FXML
	private Button startButton;
	
	@FXML
	private Button continueButton;
	
	@FXML
	private Button settingsButton;
	
	@FXML
	private Label versionJeu; // La version actuel du jeu
	
	
	/**
	 * Cette méthode permet de confirmer la fermeture de la fenêtre 
	 * @author Gaël
	 */
	 @FXML
	 private void closeRequest() {
	        
	       
	  Stage s = (Stage)versionJeu.getScene().getWindow();
	  s.setOnCloseRequest(e -> {
		
		  Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous vraiment fermer l'application?", ButtonType.YES, ButtonType.NO);
	        ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
	        if (ButtonType.NO.equals(result)) {
	          
	            e.consume();// Si on choisit non, l'evenement se consume et la fenêtre ne se ferme pas
	        }
	        else
	        {
	        	/*
	        	 * Sinon, on arrête le jeu et on ferme la fenêtre
	        	 */
	        	
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
	 
	
	/**
	 * Fonction qui initialise le menu principal, les boutons, ainsi que le label
	 *@author Gael
	 */

	 @FXML
	private void initialize()
	{
		
		
		versionJeu.setText(GameApp.vJeu); // On change le texte du label à la version du jeu
		 /*
		  * Lorsque l'on passe notre souris sur un des bouttons,
		  * On joue un petit son et le boutton devient orangé
		  * Puis lorsque l'on quitte le boutton, il reprends sa couleur originelle.
		  */
		startButton.setOnMouseEntered(e -> {
			
			startButton.setStyle("-fx-background-color: #914206; ");
			buttonSFX();
		}
		);
		startButton.setOnMouseExited(e -> startButton.setStyle("-fx-background-color: grey; "));
		
		
		/*
		 * Lorsqu'on appuie sur nouvelle partie,
		 * on ouvre une nouvelle fenêtre séléction de pseudo.
		 */
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
		
		
	    continueButton.setOnAction(e -> continueGame()); // On charge notre partie 
	   
	    mainMenuTheme(); // On lance la musique du jeu
	   
	}
	
	
	/**
	 * Initialise le thème du menu principal
	 * @author Gaël
	 */
	
	public void mainMenuTheme()
	 {
		final URL musicURL = getClass().getResource("/BGM/MainMenuTheme.mp3");   
		final  Media media = new Media(musicURL.toExternalForm());
		mediaPlayer = new MediaPlayer(media); 
		mediaPlayer.setVolume(1);
		mediaPlayer.setOnEndOfMedia(new Runnable() {
		       public void run() {
		         mediaPlayer.seek(Duration.ZERO);
		       }
		   });
		
	 }
	
	/**
	 * Initialise le son des boutons
	 * 
	 * @author Gaël
	 */
	public void buttonSFX()
	{
		AudioClip a1 = new AudioClip(getClass().getResource("/SFX/buttonSound.mp3").toString());
		a1.play();
	}
	
	
	/**
	 * On lance la musique du jeu lorsque la souris entre dans la fenêtre
	 * @author Gaël
	 */
	@FXML
	private void mouseEntered()
	{
		mediaPlayer.play();
	}
	
	@FXML
	/**
	 * Quitte le menu principal et le jeu
	 * 
	 * @author Gaël
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
	 * Charge notre partie et lance le jeu
	 * 
	 * @author Gaël
	 */
	private void continueGame()
	{
		
		mediaPlayer.stop();
		
		FXGL.getGameController().startNewGame();
		
	}
	
	
	
	

}
