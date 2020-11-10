package GUI;

import java.sql.*;

import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getAppWidth;
import static com.almasb.fxgl.dsl.FXGL.getGameScene;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.geti;
import static game.and.map.GameType.CHEST;
import static game.and.map.GameType.MONSTER;
import static game.and.map.GameType.PLAYER;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.dsl.FXGL;

import entity.Joueur;
import entity.Monster;
import game.and.map.GameFactory;
import game.and.map.GameType;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import other.components.PlayerComponent;
public class InGameController {
	 public static ScheduledExecutorService scheduledExecutorService;
	 
 @FXML
 private Label hpBar;
 @FXML
 private Label mpBar;
 @FXML
 private Label pseudoJoueur;
 @FXML
 private Button characterButton;
 @FXML
 private Button inventoryButton;
 @FXML
 private Button skillsButton;
 @FXML
 private Button mapButton;
 @FXML
 private Button optionsButton;
 @FXML
 private Button exitButton;
@FXML
private ImageView characterImage;
@FXML
private ImageView inventoryImage;
@FXML
private ImageView skillsImage;
@FXML
private ImageView mapImage;
@FXML
private ImageView optionsImage;
@FXML
private ImageView exitImage;
@FXML
private Label labelCharacter;
@FXML
private Polygon polygonCharacter;
@FXML
private Label labelInventory;
@FXML
private Polygon inventairePolygon;
@FXML
private Polygon skillsPolygon;
@FXML
private Polygon mapPolygon;
@FXML
private Polygon optionsPolygon;
@FXML
private Polygon exitPolygon;
@FXML
private Label labelSkills;
@FXML
private Label labelMap; 
@FXML
private Label labelOptions; 
@FXML
private Label labelQuitter; 
@FXML
private GridPane inventaireGridPane;
@FXML
private AnchorPane Pane;
@FXML
private Label saveLabel;
@FXML
private AnchorPane characterPane;
@FXML
private Label hpLabelCharacter;
@FXML
private Label mpLabelCharacter;
@FXML
private Label atkLabelCharacter;
@FXML
private Label defLabelCharacter;
@FXML
private Label spdLabelCharacter;
@FXML
private Label spaLabelCharacter;
@FXML
private Label speLabelCharacter;
@FXML
private Label pseudoLabelCharacter;
@FXML
private Label levelLabelCharacter;
@FXML
private Label xpLabelCharacter;
@FXML
private Label xpTotalLabelCharacter;
@FXML
private Label pointsBonusLabel;
@FXML
private Button atkBonusButton;
@FXML
private Button defBonusButton;
@FXML
private Button spdBonusButton;
@FXML
private Button spaBonusButton;
@FXML
private Button speBonusButton;

int pointsBonus;
public static int nbr;

 Joueur J = FXGL.getGameWorld().getSingleton(GameType.PLAYER).getProperties().getValue("Joueur1");


 
 public void initialize() throws URISyntaxException, IOException, SQLException {
	
 	getStats();
 	getImages();
    hoverButton();
   
 	
 }
 
@FXML
public void keyPressed(KeyEvent event) throws SQLException
{
	 if (event.getCode() == KeyCode.F1) { 
		 
		 // Ajout de la fonction save ici
		 //debut du sql
			Connection db = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/projetpoagl?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
			Statement demandeRequete = db.createStatement();
			

			demandeRequete.executeUpdate("UPDATE `projetpoagl`.`stats`  "
					+ "SET `maxHP` = '"+ J.getStat().getMaxHP()+"'"
					+ ", `currentHP` ='"+ J.getStat().getCurrentHP()+"'"
					+ ", `maxATK`= '"+ J.getStat().getMaxATK()+"'"
					+ ", `currentATK`= '"+ J.getStat().getCurrentATK()+"'"
					+ ", `maxDEF`= '"+ J.getStat().getMaxDEF()+"'"
					+ ", `currentDEF`= '"+ J.getStat().getCurrentDEF()+"'"
					+ ", `maxMP`= '"+ J.getStat().getMaxMP()+"'"
					+ ", `currentMP`= '"+ J.getStat().getCurrentMP()+"'"
					+ ", `maxSPA`= '"+ J.getStat().getMaxSPA()+"'"
					+ ", `currentSPA`= '"+ J.getStat().getCurrentSPA()+"'"
					+ ", `maxSPD`= '"+ J.getStat().getMaxSPD()+"'"
					+ ", `currentSPD`= '"+ J.getStat().getCurrentSPD()+"'"
					+ ", `maxSPE`= '"+ J.getStat().getMaxSPE()+"'"
					+ ", `currentSPE`= '"+ J.getStat().getCurrentSPE()+"'"
					+ "WHERE `stats`.`idJoueur` = 1");
			
			demandeRequete.executeUpdate("UPDATE `projetpoagl`.`lvl`"
					+ "SET `level` = '"+J.getLv().getNiveau()+"'"
					+ ",`totalXP`='"+J.getLv().getTotalXP()+"'"
					+ ",`currentXP`= '"+J.getLv().getCurrentXPforLV()+"'"
					+ ",`xpNeeded`= '"+J.getLv().getXPneedForNextLV()+"'"
					+ "WHERE `lvl`.`idJoueur` = 1");
			
			
			demandeRequete.executeUpdate("UPDATE `projetpoagl`.`joueur`"
					+ "SET `pointBonusJoueur` = '"+pointsBonus+"'"
					+ "WHERE `joueur`.`idjoueur` = 1");
		  
		 
		    
	    saveLabel.setVisible(true);
	   
	    PauseTransition pauseTransition = new PauseTransition(Duration.seconds(3));
	    pauseTransition.setOnFinished(e -> { 	
	    saveLabel.setVisible(false);
	    });
	    pauseTransition.play();
	    
	    } 
 
}
 
 public void hoverButton() throws SQLException
 {
	
	   
		
	 characterButton.setOnMouseEntered(e -> {
		labelCharacter.setVisible(true);
		polygonCharacter.setVisible(true);
		
		
	 });
	 characterButton.setOnMouseExited(e -> {
			labelCharacter.setVisible(false);
			polygonCharacter.setVisible(false);
		 });
	 characterButton.setOnMouseClicked(e -> {
		if(!characterPane.isVisible())
		{
			characterPane.setVisible(true);
			characterPane.setLayoutX(873);
			characterPane.setLayoutY(385);
		}	
		else
		{
			characterPane.setVisible(false);
		}
	 });
	 
	 atkBonusButton.setOnMouseClicked(e -> {
		 
	 J.getStat().setMaxATK(J.getStat().getMaxATK() + 1);
	 J.getStat().setCurrentATK(J.getStat().getMaxATK());
	 pointsBonus--;
	 });
	 
	 defBonusButton.setOnMouseClicked(e -> {
		 
		 J.getStat().setMaxDEF(J.getStat().getMaxDEF() + 1);
		 J.getStat().setCurrentDEF(J.getStat().getMaxDEF());
		 pointsBonus--;
		 });
	 
	 spaBonusButton.setOnMouseClicked(e -> {
		 
		 J.getStat().setMaxSPA(J.getStat().getMaxSPA() + 1);
		 J.getStat().setCurrentSPA(J.getStat().getMaxSPA());
		 pointsBonus--;
		 });
	 
	 spdBonusButton.setOnMouseClicked(e -> {
		 
		 J.getStat().setMaxSPD(J.getStat().getMaxSPD() + 1);
		 J.getStat().setCurrentSPD(J.getStat().getMaxSPD());
		 pointsBonus--;
		 });
	 
	 speBonusButton.setOnMouseClicked(e -> {
		 
		 J.getStat().setMaxSPE(J.getStat().getMaxSPE() + 1);
		 J.getStat().setCurrentSPE(J.getStat().getMaxSPE());
		 pointsBonus--;
		 });
	 
	 
	 inventoryButton.setOnMouseEntered(e -> {
		 labelInventory.setVisible(true);
		 inventairePolygon.setVisible(true);
	 });
	 inventoryButton.setOnMouseExited(e -> {
		 labelInventory.setVisible(false);
		 inventairePolygon.setVisible(false);
	 });
	 inventoryButton.setOnMouseClicked(e -> {
		 if(!inventaireGridPane.isVisible())
		 {
			 inventaireGridPane.setVisible(true);
			 inventaireGridPane.setLayoutX(925);
			 inventaireGridPane.setLayoutY(421);
		 }
		 else {
			 inventaireGridPane.setVisible(false);
		 }
	 });
	 skillsButton.setOnMouseEntered(e -> {
		 labelSkills.setVisible(true);
		 skillsPolygon.setVisible(true);
	 });
	 skillsButton.setOnMouseExited(e -> {
		 labelSkills.setVisible(false);
		 skillsPolygon.setVisible(false);
	 });
	 
	 mapButton.setOnMouseEntered(e -> {
		 labelMap.setVisible(true);
		 mapPolygon.setVisible(true);
	 });
	 mapButton.setOnMouseExited(e -> {
		 labelMap.setVisible(false);
		 mapPolygon.setVisible(false);
	 });
	 
	 optionsButton.setOnMouseEntered(e -> {
		 labelOptions.setVisible(true);
		 optionsPolygon.setVisible(true);
		
	 });
	 optionsButton.setOnMouseExited(e -> {
		 labelOptions.setVisible(false);
		 optionsPolygon.setVisible(false);
	 });
	 
	 exitButton.setOnMouseEntered(e -> {
		 labelQuitter.setVisible(true);
		 exitPolygon.setVisible(true);
	 });
	 exitButton.setOnMouseExited(e -> {
		 labelQuitter.setVisible(false);
		 exitPolygon.setVisible(false);
	 });
	 exitButton.setOnMouseClicked(e -> {
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
	        	FXGL.getGameController().exit();
	        	}
	        	else
	        	{
	        		
	        	}
	        }
	      
	 });
	 
	 inventaireGridPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			inventaireGridPane.setLayoutX(event.getSceneX());
			inventaireGridPane.setLayoutY(event.getSceneY());
			
		}
     });
	 characterPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				characterPane.setLayoutX(event.getSceneX());
				characterPane.setLayoutY(event.getSceneY());
				
			}
	     });
		
 }

 public void getImages() throws URISyntaxException
 {
	    
	 final URL characterImageURL = getClass().getResource("/icons/character.png");
     Image character1Image = new Image(characterImageURL.toURI().toString());
	 characterImage.setImage(character1Image);
	 characterButton.setGraphic(characterImage);
	 
	 final URL bagImageURL = getClass().getResource("/icons/bag.png");
	 Image bagImage = new Image(bagImageURL.toURI().toString());
	 inventoryImage.setImage(bagImage);
	 inventoryButton.setGraphic(inventoryImage);
	 
	 final URL skillsImageURL = getClass().getResource("/icons/skills.png");
	 Image skillsImage1 = new Image(skillsImageURL.toURI().toString());
	 skillsImage.setImage(skillsImage1);
	 skillsButton.setGraphic(skillsImage);   
	    
	 final URL mapImageURL = getClass().getResource("/icons/map.png");
	 Image mapImage1 = new Image(mapImageURL.toURI().toString());
	 mapImage.setImage(mapImage1);
	 mapButton.setGraphic(mapImage);   
	 
	 final URL optionsImageURL = getClass().getResource("/icons/option.png");
	 Image optionsImage1 = new Image(optionsImageURL.toURI().toString());
	 optionsImage.setImage(optionsImage1);
	 optionsButton.setGraphic(optionsImage);
	 
	 final URL exitImageURL = getClass().getResource("/icons/exit.png");
	 Image exitImage1 = new Image(exitImageURL.toURI().toString());
	 exitImage.setImage(exitImage1);
	 exitButton.setGraphic(exitImage);   
 }

 
 
 public void getStats() throws SQLException
 {
	 
	 Viewport viewport = getGameScene().getViewport();
	 viewport.bindToEntity(getGameWorld().getSingleton(PLAYER), getAppWidth()/2,getAppHeight()/2);
	 nbr = getGameWorld().getEntitiesByType(MONSTER).size();
	 ArrayList<Label> labels = new ArrayList<Label>();
	 pointsBonus = J.getLv().getPointsBonus();
	 pseudoLabelCharacter.setText(J.getNom());
	 
		for(int i = 0; i < getGameWorld().getEntitiesByType(MONSTER).size(); i++)
		{
			Label label = new Label("Label : " + i);
			labels.add(label);
			getGameScene().addUINodes(labels.get(i));
			labels.get(i).setTextFill(Color.RED);
			labels.get(i).setFont(new Font("Eras Bold ITC", 14));
		}
			
	
	 if(J.getLv().getNiveau() == 1)
	    {
	    	pointsBonus = 1;
	    }
	 
	 
	 J.getLv().setPointsBonus(J.getLv().getPointsBonus());
	 
     scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
     scheduledExecutorService.scheduleAtFixedRate(() -> {
   
        Platform.runLater(() -> {
        	
      hpLabelCharacter.setText("HP : " + J.getStat().getCurrentHP() + " / " + J.getStat().getMaxHP());
      mpLabelCharacter.setText("MP : " + J.getStat().getCurrentMP() + " / " + J.getStat().getMaxMP());
      atkLabelCharacter.setText("ATK : " + J.getStat().getMaxATK());
      defLabelCharacter.setText("DEF : " + J.getStat().getMaxDEF());
      spdLabelCharacter.setText("SPD : " + J.getStat().getMaxSPD());
      
     
      if(PlayerComponent.changedMap == true)
	  {
		  nbr = getGameWorld().getEntitiesByType(MONSTER).size();
		  System.out.println(nbr);
	      PlayerComponent.changedMap = false;  
	      for(int i = 0; i < getGameWorld().getEntitiesByType(MONSTER).size(); i++)
			{
				Label label = new Label("Label : " + i);
				labels.add(label);
				getGameScene().addUINodes(labels.get(i));
				labels.get(i).setTextFill(Color.RED);
				labels.get(i).setFont(new Font("Eras Bold ITC", 14));
			}
	  }
      for(int i = 0; i < nbr; i++)
      {
    	  
    	  
    	if(getGameWorld().getEntitiesByType(MONSTER).size() > 0)
    	{
    	  Monster m = getGameWorld().getEntitiesByType(MONSTER).get(0).getProperties().getValue("Mosnter1");
    	  System.out.println(nbr);
    	 labels.get(i).setLayoutX(getGameWorld().getEntitiesByType(MONSTER).get(i).getX()-viewport.getX()-40);
    	  labels.get(i).setLayoutY(getGameWorld().getEntitiesByType(MONSTER).get(i).getY()-viewport.getY()-60);
    	  labels.get(i).setText("HP : " + m.getStat().getCurrentHP() + " / " + m.getStat().getMaxHP());
    	}
    	
    	if(nbr != getGameWorld().getEntitiesByType(MONSTER).size())
    	{
    		nbr--;
    		getGameScene().removeUINodes(labels.get(nbr));
    		
    	}
    
    	
    	
      }
    

      
      speLabelCharacter.setText("SPE : " + J.getStat().getMaxSPE());
      spaLabelCharacter.setText("SPA : " + J.getStat().getMaxSPA());
      pointsBonusLabel.setText("POINTS BONUS : "+ pointsBonus);
      xpTotalLabelCharacter.setText("XP TOTAL : " + J.getLv().getTotalXP());
      
      
     if(J.getLv().checkLVisAvalaible())
     {	
    	J.setLV(GameFactory.lvls.get(J.getLv().getNiveau()-1));
    	J.getStat().setMaxHP(J.getStat().getMaxHP() + (5 + (int)(Math.random() * ((10 - 5) + 1))));
    	J.getStat().setMaxMP(J.getStat().getMaxMP() + (5 + (int)(Math.random() * ((10 - 5) + 1))));
    	J.getStat().setCurrentHP(J.getStat().getMaxHP());
    	J.getStat().setCurrentMP(J.getStat().getMaxMP());
    	pointsBonus += J.getLv().getPointsBonus();
     }
      
     
     
      levelLabelCharacter.setText("LEVEL : " + J.getLv().getNiveau());
      xpLabelCharacter.setText("XP : " + J.getLv().getCurrentXPforLV() + " / " + J.getLv().getXPneedForNextLV());
      hpBar.setText("HP : " + J.getStat().getCurrentHP() + " / " + J.getStat().getMaxHP());
      mpBar.setText("MP : " + J.getStat().getCurrentMP() + " / " + J.getStat().getMaxMP());	
      pseudoJoueur.setText(J.getNom() + " " + "LV. "+J.getLv().getNiveau() + " XP : " + J.getLv().getCurrentXPforLV() + " / " + J.getLv().getXPneedForNextLV());	
      
      
       	if(pointsBonus == 0)
		{
			atkBonusButton.setVisible(false);
			defBonusButton.setVisible(false);
			spdBonusButton.setVisible(false);
			spaBonusButton.setVisible(false);
			speBonusButton.setVisible(false);
		}
       	
       	else
       	{
       		atkBonusButton.setVisible(true);
			defBonusButton.setVisible(true);
			spdBonusButton.setVisible(true);
			spaBonusButton.setVisible(true);
			speBonusButton.setVisible(true);
       	}
	
         });
       
     }, 0, 30, TimeUnit.MILLISECONDS);
 
 

 


 

       
        
  
}
 @FXML
 private void closeRequest() {
        
       
  Stage s = (Stage)hpBar.getScene().getWindow();
  s.setOnCloseRequest(e -> {
	
	  Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous vraiment fermer l'application?", ButtonType.YES, ButtonType.NO);
        ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
        if (ButtonType.NO.equals(result)) {
          
            e.consume();
        }
        else
        {
        	
        	if(scheduledExecutorService != null)
        	{
        	scheduledExecutorService.shutdown();
        	}
        	else
        	{
        		
        	}
        }
      
 
  });
 }
 }