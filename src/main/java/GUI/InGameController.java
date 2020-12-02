package GUI;

import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getAppWidth;
import static com.almasb.fxgl.dsl.FXGL.getGameScene;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.getInput;
import static com.almasb.fxgl.dsl.FXGL.set;
import static game.and.map.GameType.CHEST;
import static game.and.map.GameType.MONSTER;
import static game.and.map.GameType.PLAYER;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;

import entity.Competence;
import entity.Item;
import entity.Joueur;
import entity.Monster;
import game.and.map.GameApp;
import game.and.map.GameFactory;
import game.and.map.GameType;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
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
@FXML
private ProgressBar progHpBar;
@FXML
private ProgressBar progMpBar;
@FXML
private Label saveLabel2;
@FXML
private Label objectifLabel1;
@FXML
private Button cacherObjectifButton;
@FXML
private ScrollPane objectifPane;
@FXML
private Button skillButton1;
@FXML
private Button skillButton2;
@FXML
private Button skillButton3;
@FXML
private Button skillButton4;
@FXML
private Button skillButton5;
@FXML
private Button skillButton6;
@FXML
private Button skillButton7;
@FXML
private Button skillButton8;
@FXML
private Button skillButton9;
@FXML
private ProgressIndicator skillCooldown1;
@FXML
private ProgressIndicator skillCooldown2;
@FXML
private ListView<String> inventoryList;
@FXML
private Label nomSkill;
@FXML
private Label coutSkill;
@FXML
private Label cooldownSkill;
@FXML
private TextArea descriptionSkill;
@FXML
private ImageView imageSkill;
@FXML
private AnchorPane skillPane;


boolean wasPressed1 = false; 
boolean wasPressed2 = false;
public static int pointsBonus;
public static int nbr;
public static int nbrChests;
Joueur J = FXGL.getGameWorld().getSingleton(GameType.PLAYER).getProperties().getValue("Joueur1");
Competence[] CompetenceList = J.getCompetences();
Competence FirstCompetence = CompetenceList[0];	
Competence SecondCompetence = CompetenceList[1];

 
 public void initialize() throws URISyntaxException, IOException, SQLException {
	
	
 	getStats();
 	getImages();
    hoverButton();

  
 	
 }
 
	public static void println(String T)
	{
		System.out.println(T);
	}
 
@FXML
public void keyPressed(KeyEvent event) throws SQLException, IOException
{
	
	if(event.getCode() == KeyCode.DIGIT1)
	{
		
		 if(FirstCompetence.getCost() <= J.getStat().getCurrentMP() && FirstCompetence.getCoolDownIsOver() && J.getStat().getCurrentHP() != J.getStat().getMaxHP())
		 {
			 wasPressed1 = true;
			 FirstCompetence.UseCompetence();
			 int HPtoHeal = J.getStat().getMaxHP() - J.getStat().getCurrentHP();
			 J.getStat().setCurrentHP(J.getStat().getCurrentHP() + HPtoHeal);
			 J.getStat().setCurrentMP(J.getStat().getCurrentMP()-FirstCompetence.getCost());
			 skillButton1.setDisable(true);
			 skillCooldown1.setVisible(true);
			 Timeline time = new Timeline(

					    new KeyFrame(Duration.ZERO,new KeyValue(skillCooldown1.progressProperty(), 1)),
					    new KeyFrame(Duration.seconds(FirstCompetence.getCooldown()),new KeyValue(skillCooldown1.progressProperty(), 0))
					);
					time.setCycleCount(1);
					time.play();
					
				
		}
			 
		 
		 else if(FirstCompetence.getCost() > J.getStat().getCurrentMP())
		 {
			 System.out.println("Vous n'avez pas assez de MP");
		 }
		 else if(!FirstCompetence.getCoolDownIsOver())
		 {
			 System.out.println("La compétence est en cooldown");
		 }
		 else if(J.getStat().getCurrentHP() == J.getStat().getMaxHP())
		 {
			 System.out.println("Vous avez tout vos HPs");
		 }
			
	}
	
if(event.getCode() == KeyCode.DIGIT2)
{
	
	 if(SecondCompetence.getCost() <= J.getStat().getCurrentMP() && SecondCompetence.getCoolDownIsOver())
		 {
		 wasPressed2 = true;
		 SecondCompetence.UseCompetence();
			Entity P = FXGL.getGameWorld().getSingleton(GameType.PLAYER);
		    int px = (int) P.getX()/80;
		    int py = (int) P.getY()/80;
		    int nbr = getGameWorld().getEntitiesByType(MONSTER).size();
		    set("nbrMob", nbr);
		    for(int i = 0; i < nbr; i++)
		    {
		    	println("" + nbr);
		    	Entity CurentEntity = getGameWorld().getEntitiesByType(MONSTER).get(i);
		    	int mx = (int) CurentEntity.getX()/80;
		    	int my = (int) CurentEntity.getY()/80;
		    	int Distance = 3;
		    	if(Math.abs(px - mx) < Distance && Math.abs(py - my) < Distance)
		    	{
		    		println("Deleted");
		    		CurentEntity.removeFromWorld();
		    		nbr--;
		    		set("nbrMob", nbr);	    		
		    		println("nbr : " + nbr);
		    		println("i : " + i);
		    		i=0;
		    		
		    	}
		    }
		    J.getStat().setCurrentMP(J.getStat().getCurrentMP()-SecondCompetence.getCost());
		    skillButton2.setDisable(true);
			 skillCooldown2.setVisible(true);
			 Timeline time = new Timeline(

					    new KeyFrame(Duration.ZERO,new KeyValue(skillCooldown2.progressProperty(), 1)),
					    new KeyFrame(Duration.seconds(SecondCompetence.getCooldown()),new KeyValue(skillCooldown2.progressProperty(), 0))
					);
					time.setCycleCount(1);
					time.play();

		 }
		 
		 
	 }

	
 if (event.getCode() == KeyCode.F1) { 
		 
	 
	 /**
	  * si le joueur appuie ur f1 alors il enclenche cette fonction qui sauvegarde dans la db et en plus :
	  * @return relance le jeu (le jeu est en pause tant qu il ne fini pas sa fonction)
	  */
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
		  
			demandeRequete.executeUpdate("UPDATE `projetpoagl`.`item` SET `idjoueur` = NULL");
			demandeRequete.executeUpdate("delete from item where idjoueur is null and idmonstre is null");
			
			for(int i = 0; i < J.getInventaire()[5].GetSousInventaireQuantity().stream().count() ; i+=1) {
				
				//for pour savoir le nombre d objet i
				for(int k =0;k < J.getInventaire()[5].GetSousInventaireQuantity().get(i) ;k+=1) {
				demandeRequete.executeUpdate("INSERT INTO `projetpoagl`.`item` (`idItem`, `nom`, `type`, `maxDurability`, `currentDurability`, `rarete`, `inventoryID`, `idjoueur`, `idmonstre`) VALUES (NULL, '"+
						J.getInventaire()[5].GetSousInventaire().get(i).getNom()+"', NULL, NULL, NULL, NULL, NULL, '1', NULL);");
					}
			
				}
			
			
			
			//sauvegarde de l argent du joueur 
			
			demandeRequete.executeUpdate("update `money` set `moneyPlayer` ="+J.getPlayerMoney().getMoneyOnPlayer()+",`moneyBank` = "+J.getPlayerMoney().MoneyOnBank()+" where idjoueur = 1");
			
			
			
			
			
			
			
						
			
			//en dessous -> sauvegarde da la map 1 mais ne sert a rien a cause du systeme de fxgl
//			
//			//remet la sauvegare de la map a 0
//			demandeRequete.executeUpdate("delete from tuile");
//			
//			//va ecrire dans le fichier 1, pour l instant sauvegarde que le fichier 1
//			  File fichier =new File("src\\main\\resources\\assets\\levels\\map_level0.txt"); //ou est ce qu il est crée
//			 
//			  //toutes les maps font 21 x 21
//			  
//			  
//			  Scanner sc = new Scanner(fichier);
//
//			  
//			  int i = -1; // sert a compter les lignes
//					  while(sc.hasNextLine()) {
//						  i+=1;
//						 String ligne = sc.next();
//						  for(int j = 0; j < 21 ; j+=1) { // pour les colonnes 0 a 20  
//						 demandeRequete.executeUpdate("INSERT INTO `tuile` (`idTuile`, `type`, `positionLigne`, `idFloor`, `positionColonne`) VALUES (NULL, '"+ligne.charAt(j)+"', '"+i+"', '1','"+j+"')");
//					  }
//				  
//				  }
//				  
//			  
//			  
//			  sc.close();
	
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		    
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
		if(inventoryList.isVisible())
		{
			inventoryList.setVisible(false);
		}
		else
		{
			inventoryList.setVisible(true);
			inventoryList.setLayoutX(922);
			inventoryList.setLayoutY(522);
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
	 
	inventoryList.setOnMouseDragged(new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			inventoryList.setLayoutX(event.getSceneX());
			inventoryList.setLayoutY(event.getSceneY());
			
		}
     });
	 characterPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				characterPane.setLayoutX(event.getSceneX());
				characterPane.setLayoutY(event.getSceneY());
				
			}
	     });
		
	 cacherObjectifButton.setOnMouseClicked(e -> {
		if(objectifPane.isVisible())
		{
			objectifPane.setVisible(false);
			cacherObjectifButton.setText("Afficher objectifs");
			cacherObjectifButton.setPrefWidth(150.0);
			cacherObjectifButton.setLayoutX(0);
		}
		else
		{
			objectifPane.setVisible(true);
			cacherObjectifButton.setText("Cacher");
			cacherObjectifButton.setPrefWidth(75.0);
			cacherObjectifButton.setLayoutX(199.0);
		}
			
		 
	 });
	 
	 skillButton1.setOnMouseClicked(e -> {
		 	
		
		 if(FirstCompetence.getCost() <= J.getStat().getCurrentMP() && FirstCompetence.getCoolDownIsOver() && J.getStat().getCurrentHP() != J.getStat().getMaxHP())
		 {
			 wasPressed1 = true;
			 FirstCompetence.UseCompetence();
			 int HPtoHeal = J.getStat().getMaxHP() - J.getStat().getCurrentHP();
			 J.getStat().setCurrentHP(J.getStat().getCurrentHP() + HPtoHeal);
			 J.getStat().setCurrentMP(J.getStat().getCurrentMP()-FirstCompetence.getCost());
			 skillButton1.setDisable(true);
			 skillCooldown1.setVisible(true);
			 Timeline time = new Timeline(

					    new KeyFrame(Duration.ZERO,new KeyValue(skillCooldown1.progressProperty(), 1)),
					    new KeyFrame(Duration.seconds(FirstCompetence.getCooldown()),new KeyValue(skillCooldown1.progressProperty(), 0))
					);
					time.setCycleCount(1);
					time.play();

		 }
				
		
			 
		 
		 else if(FirstCompetence.getCost() > J.getStat().getCurrentMP())
		 {
			 System.out.println("Vous n'avez pas assez de MP");
		 }
		 else if(!FirstCompetence.getCoolDownIsOver())
		 {
			 System.out.println("La compétence est en cooldown");
		 }
		 else if(J.getStat().getCurrentHP() == J.getStat().getMaxHP())
		 {
			 System.out.println("Vous avez tout vos HPs");
		 }
		 }
	 );
	
	
		 

	 
	skillButton1.setOnMouseEntered(e -> {
		skillPane.setVisible(true);
		nomSkill.setText(" " +FirstCompetence.getNom());
		coutSkill.setText(" " + FirstCompetence.getCost() + " MP");
		cooldownSkill.setText(" "+ FirstCompetence.getCooldown() + " s.");
		descriptionSkill.setText(FirstCompetence.getDescription());
		
		
	});
	skillButton1.setOnMouseExited(e -> skillPane.setVisible(false));
	
	skillButton2.setOnMouseEntered(e -> {
		skillPane.setVisible(true);
		nomSkill.setText(" " +SecondCompetence.getNom());
		coutSkill.setText(" " + SecondCompetence.getCost() + " MP");
		cooldownSkill.setText(" "+ SecondCompetence.getCooldown() + " s.");
		descriptionSkill.setText(SecondCompetence.getDescription());
	});
	skillButton2.setOnMouseExited(e -> skillPane.setVisible(false));
	
	 
	 
	 skillButton2.setOnMouseClicked(e -> {
		 if(SecondCompetence.getCost() <= J.getStat().getCurrentMP() && SecondCompetence.getCoolDownIsOver())
		 {
		 wasPressed2 = true;
			Entity P = FXGL.getGameWorld().getSingleton(GameType.PLAYER);
		    int px = (int) P.getX()/80;
		    int py = (int) P.getY()/80;
		    int nbr = getGameWorld().getEntitiesByType(MONSTER).size();
		    set("nbrMob", nbr);
		    for(int i = 0; i < nbr; i++)
		    {
		    	println("" + nbr);
		    	Entity CurentEntity = getGameWorld().getEntitiesByType(MONSTER).get(i);
		    	int mx = (int) CurentEntity.getX()/80;
		    	int my = (int) CurentEntity.getY()/80;
		    	int Distance = 3;
		    	if(Math.abs(px - mx) < Distance && Math.abs(py - my) < Distance)
		    	{
		    		println("Deleted");
		    		CurentEntity.removeFromWorld();
		    		nbr--;
		    		set("nbrMob", nbr);	    		
		    		println("nbr : " + nbr);
		    		println("i : " + i);
		    		i--;
		    		
		    	}
		    }
		    J.getStat().setCurrentMP(J.getStat().getCurrentMP()-SecondCompetence.getCost());
		    skillButton2.setDisable(true);
			 skillCooldown2.setVisible(true);
			 Timeline time = new Timeline(

					    new KeyFrame(Duration.ZERO,new KeyValue(skillCooldown2.progressProperty(), 1)),
					    new KeyFrame(Duration.seconds(SecondCompetence.getCooldown()),new KeyValue(skillCooldown2.progressProperty(), 0))
					);
					time.setCycleCount(1);
					time.play();

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
	 ObservableList<String> list = FXCollections.observableArrayList(); 

	 
	 Viewport viewport = getGameScene().getViewport();
	 viewport.bindToEntity(getGameWorld().getSingleton(PLAYER), getAppWidth()/2,getAppHeight()/2);
	 nbr = getGameWorld().getEntitiesByType(MONSTER).size();
	 nbrChests = getGameWorld().getEntitiesByType(CHEST).size();
	 ArrayList<Label> labels = new ArrayList<Label>();
	 ArrayList<Label> labelsInventory = new ArrayList<Label>();
	 
	 for(int i = 0; i < 999; i++)
	 {
		 Label label = new Label(" ");
		 labelsInventory.add(label);
		 
			getGameScene().addUINodes(labelsInventory.get(i));
			
	 }
	 
	 ArrayList<ProgressBar> progressBarMonsters = new ArrayList<ProgressBar>();
	 pointsBonus = J.getLv().getPointsBonus();
	 pseudoLabelCharacter.setText(J.getNom());
	 
		for(int i = 0; i < getGameWorld().getEntitiesByType(MONSTER).size(); i++)
		{
			Label label = new Label("Label : " + i);
			labels.add(label);
			labels.get(i).setTextFill(Color.RED);
			labels.get(i).setFont(new Font("Eras Bold ITC", 14));
			
			ProgressBar progressBar = new ProgressBar();
			progressBarMonsters.add(progressBar);
			progressBarMonsters.get(i).setStyle("-fx-accent : red");
			
			getGameScene().addUINodes(labels.get(i),progressBarMonsters.get(i));
			
		}
			
	
	 if(J.getLv().getNiveau() == 1)
	    {
		 
	    	pointsBonus = 1;
	    	
	    }
	
	 J.getLv().setPointsBonus(J.getLv().getPointsBonus());
	
     scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
     scheduledExecutorService.scheduleAtFixedRate(() -> {
   
        Platform.runLater(() -> {
        	if(GameApp.chestOpened == true)
        	{
        		Label label = new Label();
        		label.setLayoutX(GameApp.CurentEntityOnClic.getX()-viewport.getX());
        		label.setTextFill(Color.BLACK);
        		label.setFont(new Font("Eras Bold ITC", 16));
        		getGameScene().addUINodes(label);
        		for(Item I : GameApp.LootItemsOfMonster)
				{
        			int pos = GameApp.LootItemsOfMonster.indexOf(I);
					System.out.println(I.getNom() + " x" + GameApp.QuantityLootItemsOfMonster.get(pos));
					label.setText(I.getNom() + " x" + GameApp.QuantityLootItemsOfMonster.get(pos));
				}
        		
        		 Timeline time = new Timeline(

 					    new KeyFrame(Duration.ZERO,new KeyValue(label.opacityProperty(), 1),new KeyValue(label.layoutYProperty(),GameApp.CurentEntityOnClic.getY()-viewport.getY()+70)),
        	
 					    new KeyFrame(Duration.seconds(2),new KeyValue(label.opacityProperty(), 0),new KeyValue(label.layoutYProperty(),GameApp.CurentEntityOnClic.getY()-viewport.getY()+120))
 					);
        		 time.setCycleCount(1);
					time.play();
        		
        		ArrayList<Item> Inventaire = J.getNewInventory();
        		for(int i = 0; i < J.ItemsCountOnInventoryV2(); i++)
        		{
        			
        			list.add(" ");
        			
        				if(list.get(i) != null)
        				{
        					list.set(i,Inventaire.get(i).getNom() + " x " + J.getNewInventoryQuantity().get(i));
        				}
        			
        		
        			
        		}
        		inventoryList.setItems(list);
        		
        		GameApp.chestOpened = false;
        		
        	}
        	
        	
        	
        	 if(FirstCompetence.getCoolDownIsOver() && wasPressed1 == true)
    		 {
        		 
    			skillButton1.setDisable(false);
    			skillCooldown1.setVisible(false);
    			wasPressed1 = false;
    		 }
        	 if(SecondCompetence.getCoolDownIsOver() && wasPressed2 == true)
        	 {
        		 System.out.println("fini");
        		 skillButton2.setDisable(false);
     			skillCooldown2.setVisible(false);
     			wasPressed2 = false;
        	 }
        	 
        	
      hpLabelCharacter.setText("HP : " + J.getStat().getCurrentHP() + " / " + J.getStat().getMaxHP());
      mpLabelCharacter.setText("MP : " + J.getStat().getCurrentMP() + " / " + J.getStat().getMaxMP());
      atkLabelCharacter.setText("ATK : " + J.getStat().getMaxATK());
      defLabelCharacter.setText("DEF : " + J.getStat().getMaxDEF());
      spdLabelCharacter.setText("SPD : " + J.getStat().getMaxSPD());
      objectifLabel1.setText("- Tuer " + getGameWorld().getEntitiesByType(MONSTER).size() + " monstres pour passer \nau prochain étage");
      if(getGameWorld().getEntitiesByType(MONSTER).size() == 0)
      {
    	  objectifLabel1.setText("Prochain étage débloqué");
    	  objectifLabel1.setTextFill(Color.GREEN);
      }
      else
      {
    	  objectifLabel1.setTextFill(Color.WHITE);
      }
      
      progHpBar.setProgress((double)J.getStat().getCurrentHP() / (double)J.getStat().getMaxHP());
      progMpBar.setProgress((double)J.getStat().getCurrentMP() / (double)J.getStat().getMaxMP());
      
      
      if(progHpBar.getProgress() <= 0.5 && progHpBar.getProgress() > 0.25)
      {
    	  progHpBar.setStyle("-fx-accent: orange; ");
    	  hpBar.setTextFill(Color.ORANGE);
      }
      else if(progHpBar.getProgress() <= 0.25)
      {
    	  progHpBar.setStyle("-fx-accent: red;");
    	  hpBar.setTextFill(Color.RED);
      }
      else if(progHpBar.getProgress() > 0.5)
      {
    	  progHpBar.setStyle("-fx-accent: #37cd26;");
    	  Color c = Color.web("#37cd26",1.0);
    	  hpBar.setTextFill(c);
      }  
      
      
      if(PlayerComponent.changedMap == true)
	  {
		  nbr = getGameWorld().getEntitiesByType(MONSTER).size();
		 // J = FXGL.getGameWorld().getSingleton(GameType.PLAYER).getProperties().getValue("Joueur1");
          
	      for(int i = 0; i < getGameWorld().getEntitiesByType(MONSTER).size(); i++)
			{
				Label label = new Label("Label : " + i);
				labels.add(label);
				labels.get(i).setTextFill(Color.RED);
				labels.get(i).setFont(new Font("Eras Bold ITC", 14));
				
				ProgressBar progressBar = new ProgressBar();
				progressBarMonsters.add(progressBar);
				progressBarMonsters.get(i).setStyle("-fx-accent : red");
				
				getGameScene().addUINodes(labels.get(i),progressBarMonsters.get(i));
				
			}
	      J = FXGL.getGameWorld().getSingleton(GameType.PLAYER).getProperties().getValue("Joueur1");
	        saveLabel2.setVisible(true);
		    PauseTransition pauseTransition = new PauseTransition(Duration.seconds(3));
		    pauseTransition.setOnFinished(e -> { 	
		    saveLabel2.setVisible(false);
		    });
		    pauseTransition.play();
		    
	  
       
	      PlayerComponent.changedMap = false; 
	      
	  }
      
      
      
      for(int i = 0; i < nbr; i++)
      {
    	  
    	  
    	if(getGameWorld().getEntitiesByType(MONSTER).size() > 0)
    	{
    	  Monster m = getGameWorld().getEntitiesByType(MONSTER).get(i).getProperties().getValue("Mosnter1");
    	  labels.get(i).setLayoutX(getGameWorld().getEntitiesByType(MONSTER).get(i).getX()-viewport.getX()+20);
    	  labels.get(i).setLayoutY(getGameWorld().getEntitiesByType(MONSTER).get(i).getY()-viewport.getY()+60);
    	  labels.get(i).setText(m.getStat().getCurrentHP() + " / " + m.getStat().getMaxHP());
    	  progressBarMonsters.get(i).setLayoutX(getGameWorld().getEntitiesByType(MONSTER).get(i).getX()-viewport.getX()-10);
    	  progressBarMonsters.get(i).setLayoutY(getGameWorld().getEntitiesByType(MONSTER).get(i).getY()-viewport.getY());
    	  progressBarMonsters.get(i).setProgress((double)m.getStat().getCurrentHP() / (double)m.getStat().getMaxHP());
    	  
    	}
    	
    	if(nbr != getGameWorld().getEntitiesByType(MONSTER).size())
    	{
    		nbr--;
    		getGameScene().removeUINodes(labels.get(nbr),progressBarMonsters.get(nbr));
    
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
      hpBar.setText("HP :                             " + J.getStat().getCurrentHP() + " / " + J.getStat().getMaxHP());
      mpBar.setText("MP :                             " + J.getStat().getCurrentMP() + " / " + J.getStat().getMaxMP());	
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
 
 public static void displayOnAttack()
 {
	 for(int i = 0; i < nbr; i++)
     {
		  Monster m = getGameWorld().getEntitiesByType(MONSTER).get(i).getProperties().getValue("Mosnter1");
		  Label label = new Label("Le "+m.getNom()+" a subit " + GameApp.DegatSubit + " dégats");
		  getGameScene().addUINodes(label);
     }
     
	 
	 
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