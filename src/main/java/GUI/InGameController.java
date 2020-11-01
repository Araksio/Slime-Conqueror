package GUI;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.almasb.fxgl.dsl.FXGL;

import entity.Joueur;
import game.and.map.GameApp;
import game.and.map.GameType;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
public class InGameController{
	 public static ScheduledExecutorService scheduledExecutorService;
 @FXML
 private Label hpBar;
 @FXML
 private Label mpBar;
 @FXML
 private Label pseudoJoueur;
 @FXML
 private Button button;


 

 public void initialize() {
 	
 	getStats();
 	
 	
 }


 public void getStats()
 {
	
     scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
     scheduledExecutorService.scheduleAtFixedRate(() -> {
    	
        Platform.runLater(() -> {
           
         Joueur J = FXGL.getGameWorld().getSingleton(GameType.PLAYER).getProperties().getValue("Joueur1");
       	hpBar.setText("HP : " + J.getStat().getCurrentHP() + " / " + J.getStat().getMaxHP());
       	mpBar.setText("MP : " + J.getStat().getCurrentMP() + " / " + J.getStat().getMaxMP());	
       	 pseudoJoueur.setText(J.getNom());	
      
      
         });
       
     }, 0, 100, TimeUnit.MILLISECONDS);
  
    
    
 }


}
