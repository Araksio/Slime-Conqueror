package GUI;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;

import entity.Joueur;
import game.and.map.GameApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.AudioClip;

/**
 * @author Gaël
 * MyGameMenuController permet de générer un menu pause en jeu.
 * Ce menu est composé de :
 * 
 * Un boutton pour reprendre la partie
 * Un boutton options
 * Un boutton revenant au menu principal
 * Un boutton pour quitter le jeu.
 */
public class MyGameMenuController {
	
 @FXML
 private Button resumeButton;
 @FXML
 private Button settingsButton;
 @FXML
 private Button mainMenuButton;
 @FXML
 private Button exitButton;


 /**
	 * @author Gaël
	 * On initialise le fonctionnement des bouttons.
	 */
 @FXML
 private void initialize()
 {
	 /*
	  * Lorsque l'on passe notre souris sur un des bouttons,
	  * On joue un petit son et le boutton devient orangé
	  * Puis lorsque l'on quitte le boutton, il reprends sa couleur originelle.
	  */
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
 
 /**
  * @author Gaël
  * Cette méthode permet de jouer un petit son lorsqu'elle est appelé.
  */
	public void buttonSFX()
	{
		AudioClip a1 = new AudioClip(getClass().getResource("/SFX/buttonSound.mp3").toString()); // On cherche le son en .mp3 dans SFX
		a1.play(); // Et on le joue
	}


	/**
	 * @author Gaël
	 * Cette méthode permet de reprendre le jeu lorsqu'elle est appelé
	 */
 @FXML
  private void resumeGame()
  {
	 
	 FXGL.getGameController().gotoPlay(); // Revient au jeu.
  }
 
 /**
	 * @author Gaël
	 * Cette méthode permet de revenir au menu principal lorsqu'elle est appelé
	 */
 @FXML
 private void goToMainMenu()
 {
      FXGL.getGameScene().removeChild(GameApp.layout); // On enlève le layout du jeu
		if( InGameController.scheduledExecutorService != null) // On regarde si il y a un rafraichissement en cours
		{
			 InGameController.scheduledExecutorService.shutdown(); // On l'arrête
		}
	 FXGL.getGameController().gotoMainMenu(); // On revient au menu principal
	 
 }
 
 /**
	 * @author Gaël
	 * Cette méthode permet de quitter le jeu lorsqu'elle est appelé
	 */
 @FXML
 private void exitGame()
 {
	 InGameController.scheduledExecutorService.shutdown(); // On arrête le rafraîchissement auto du jeu
	 FXGL.getGameController().exit(); // Et on ferme le jeu complètement
 }
}
