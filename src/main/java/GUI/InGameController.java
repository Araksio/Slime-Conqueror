package GUI;


import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.almasb.fxgl.dsl.FXGL;

import entity.Joueur;
import game.and.map.GameType;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Polygon;
public class InGameController{
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


 Joueur J = FXGL.getGameWorld().getSingleton(GameType.PLAYER).getProperties().getValue("Joueur1");
 

 
 public void initialize() throws URISyntaxException, IOException {
	
 	getStats();
 	getImages();
    hoverButton();
   
 
	
 	
 }
 
 public void hoverButton()
 {
	 characterButton.setOnMouseEntered(e -> {
		labelCharacter.setVisible(true);
		polygonCharacter.setVisible(true);
		FXGL.getSceneService();
	 });
	 characterButton.setOnMouseExited(e -> {
			labelCharacter.setVisible(false);
			polygonCharacter.setVisible(false);
		 });
	 characterButton.setOnMouseClicked(e -> {
		
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

 
 
 public void getStats()
 {
	
     scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
     scheduledExecutorService.scheduleAtFixedRate(() -> {
    	
        Platform.runLater(() -> {
           
      
       	hpBar.setText("HP : " + J.getStat().getCurrentHP() + " / " + J.getStat().getMaxHP());
       	mpBar.setText("MP : " + J.getStat().getCurrentMP() + " / " + J.getStat().getMaxMP());	
       	 pseudoJoueur.setText(J.getNom());	
      
      
         });
       
     }, 0, 100, TimeUnit.MILLISECONDS);
 }
 

    
    
 }



