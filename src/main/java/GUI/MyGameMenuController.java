package GUI;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;

import entity.Joueur;
import game.and.map.GameApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.AudioClip;

public class MyGameMenuController {
	
 @FXML
 private Button resumeButton;
 @FXML
 private Button settingsButton;
 @FXML
 private Button mainMenuButton;
 @FXML
 private Button exitButton;


 @FXML
 private void initialize()
 {
	 resumeButton.setOnMouseEntered(e -> { 
	           resumeButton.setStyle("-fx-background-color: #914206; ");
	           buttonSFX();
	 });
	 resumeButton.setOnMouseExited(e -> resumeButton.setStyle("-fx-background-color: grey; "));
	 settingsButton.setOnMouseEntered(e -> {
			   settingsButton.setStyle("-fx-background-color: #914206; ");
			   buttonSFX();
			});
	 settingsButton.setOnMouseExited(e -> settingsButton.setStyle("-fx-background-color: grey; "));
	 mainMenuButton.setOnMouseEntered(e ->  {
		      mainMenuButton.setStyle("-fx-background-color: #914206; ");
		      buttonSFX();
	 }
	 );
	 mainMenuButton.setOnMouseExited(e -> mainMenuButton.setStyle("-fx-background-color: grey; "));
	 exitButton.setOnMouseEntered(e -> {
			  exitButton.setStyle("-fx-background-color: #914206; ");
			  buttonSFX();
			}
			);
    exitButton.setOnMouseExited(e -> exitButton.setStyle("-fx-background-color: grey; "));
		
 

 }
 
 
	public void buttonSFX()
	{
		AudioClip a1 = new AudioClip(getClass().getResource("/SFX/buttonSound.mp3").toString());
		a1.play();
	}


 @FXML
  private void resumeGame()
  {
	 FXGL.getGameController().gotoPlay();
  }
 @FXML
 private void goToMainMenu()
 {
      FXGL.getGameScene().removeChild(GameApp.layout);
		if( InGameController.scheduledExecutorService != null)
		{
			 InGameController.scheduledExecutorService.shutdown();
		}
	 FXGL.getGameController().gotoMainMenu();
	 
 }
 @FXML
 private void exitGame()
 {
	 InGameController.scheduledExecutorService.shutdown();
	 FXGL.getGameController().exit();
 }
}
