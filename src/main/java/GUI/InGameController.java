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
/**
 * @author Gaël (créateur et principale auteur), Esteban, Rémi, Gabriel, Louis.
 * InGameController permets de créer des éléments graphique.
 * Elle permets aussi de modifier l'interface graphique.
 * L'interface graphique changera selon les actions du joueur.
 * Cette interface graphique est caractérisé par : 
 * 
 * Un label des points de vie actuel/vie maximum du joueur.
 * Un label des points de magie actuel/magie maximum du joueur.
 * Une barre de points de vie du joueur.
 * Une barre de points de magie du joueur.
 * Un label de l'xp actuel du joueur.
 * Un label de l'xp à faire du joueur.
 * Un pseudo du joueur.
 * Un boutton ouvrant une fenêtre caractéristiques.
 * Un boutton ouvrant une fenêtre inventaire.
 * Un boutton ouvrant une fenêtre compétences.
 * Un boutton ouvrant une fenêtre carte.
 * Un boutton ouvrant une fenêtre options.
 * Un boutton fermant le jeu.
 * Une image du joueur.
 * Une image du boutton caractéristiques.
 * Une image du boutton inventaire.
 * Une image du boutton compétences.
 * Une image du boutton carte.
 * Une image du boutton options.
 * Une image du boutton quitter.
 * Un label affichant "Personnage".
 * Un label affichant "Inventaire".
 * Un label affichant "Skills".
 * Un label affichant "Compétence".
 * Un label affichant "Options".
 * Un label affichant "Quitter".
 * Un polygon pour chaque label pour pointer sur eux.
 * Un label lorsque le joueur sauvegarde.
 * Un AnchorPane représentant la fenêtre du jeu.
 * un AnchorPane représentant la fenêtre caractéristiques.
 * Un label des points de vie du joueur dans la fenêtre caractéristiques.
 * Un label des points de magie du joueur dans la fenêtre caractéristiques.
 * Un label des points d'attaques du joueur.
 * Un label des points de défense du joueur.
 * Un label des points de vitesse du joueur.
 * Un label des points d'attaque magique du joueur.
 * Un label des points de défense magique du joueur.
 * Un label de l'xp actuel du joueur dans la fenêtre caractéristiques.
 * Un label de l'xp à faire dans la fenêtre caractéristiques.
 * Un label de l'xp total du joueur dans la fenêtre caractéristiques.
 * Un label du pseudo du joueur dans la fenêtre caractéristiques.
 * Un boutton des points bonus d'attaque.
 * Un boutton des points bonus de défense.
 * Un boutton des points bonus de vitesse.
 * Un boutton des points bonus d'attaque magique.
 * Un boutton des points bonus de défense magique.
 * 9 bouttons de compétences utilisable du joueur.
 * 2 barres de progression du temps d'attente avant réutilisation de la compétence.
 * un AnchorPane affichant les détails d'une compétence.
 * Un label du nom de la compétence.
 * Un TextArea de la description de la compétence.
 * Un label du coût en points de magie de la compétence.
 * Un label du temps d'attente de la compétence.
 * Une image de la compétence.
 * Une listView de l'inventaire du joueur.
 * Un boutton pour cacher les objectifs.
 * Un scrollPane des objectifs.
 * Un label des objectifs.
 * @see GameApp,Joueur,Compétence.
 * 
 */
public class InGameController {
	
	//Variable permettant le rafraîchissement constant selon une durée donnée.
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


boolean wasPressed1 = false; //Permet de vérifier si skill1 a été pressé 
boolean wasPressed2 = false; //Permet de vérifier si skill2 a été pressé
public static int pointsBonus; //Représente les points bonus du joueur
public static int nbr; //Représente le nombre de monstres
public static int nbrChests; // Représente le nombre de coffres aux trésors
Joueur J = FXGL.getGameWorld().getSingleton(GameType.PLAYER).getProperties().getValue("Joueur1"); // Permets de récupérer les valeurs de Joueur J
Competence[] CompetenceList = J.getCompetences(); // Permet de récupérer les compétences de Joueur J
Competence competence1 = CompetenceList[0];	//Première compétence = première compétence de la liste des compétences du Joueur
Competence competence2 = CompetenceList[1]; //Seconde compétence = seconde compétence de la liste des compétences du Joueur.

 
/**
 * Initialise l'interface graphique
 * @throws URISyntaxException : Si la syntaxte de L'url n'est pas conforme
 * @throws SQLException : Si la requête SQL n'est pas conforme
 */
 public void initialize() throws URISyntaxException, SQLException {
	
	
 	getStats();
 	getImages();
    hoverButton();

  
 	
 }
 
 /**
  * Simplifie l'utilisation de System.out.println();
  * @param T
  *          Le string que l'on veut print avec cette méthode.
  */
	public static void println(String T)
	{
		System.out.println(T);
	}
	
	
	
	/**
	 * Méthode executé lorsque l'utilisateur appuie sur une touche.
	 * @param event
	 *             La touche sur laquelle l'utilisateur appuie.
	 * @throws SQLException : Si la requête SQL n'est pas conforme
	 */
 
@FXML
public void keyPressed(KeyEvent event) throws SQLException
{
	
	/**
	 * @Gaël @Louis
	 * @see skillButton1.setOnMouseClicked
	 * Fonction qui s'éxecute lorsque l'on appuit sur le bouton "1" du clavier.
	 * @return lancementCompetence1()
	 */
	if(event.getCode() == KeyCode.DIGIT1) {
		lancementCompetence1();
	}
	
	/**
	 * @Gaël @Louis
	 * @see skillButton2.setOnMouseClicked
	 * Fonction qui s'éxecute lorsque l'on appuit sur le bouton "2" du clavier.
	 * @return lancementCompetence2()
	 */
	if(event.getCode() == KeyCode.DIGIT2) {
		lancementCompetence2();
	}

	
	 /**
	  * @author Gaël,Estéban
	  * si le joueur appuie ur f1 alors il enclenche cette fonction qui sauvegarde dans la db et en plus :
	  * @return Attends 3 secondes avant de faire disparaître le label de sauvegarde réussie.
	  */
	if (event.getCode() == KeyCode.F1) { 
			 
		 
		
			 // Ajout de la fonction save ici
			 //debut du sql
				Connection db = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/projetpoagl?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root"); // Connection à la base de données
				Statement demandeRequete = db.createStatement(); // Création d'une requête
				
	/**
	 * @author Estéban
	 * Le programme exécute plusieurs requêtes permettant d'enregistrer les données dans la base de données.
	 */
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
		
				
		    
		    saveLabel.setVisible(true); //Le label de sauvegarde devient visible
		    
		    /**
		     * @author Gaël
		     * Le programme attends 3 secondes avant de rendre le label de sauvegarde invisible
		     */
		    PauseTransition pauseTransition = new PauseTransition(Duration.seconds(3));
		    pauseTransition.setOnFinished(e -> { 	
		    saveLabel.setVisible(false);
		    });
		    pauseTransition.play();
		    
		    } 
 
}
 

/**
 * @author Gaël,Louis
 * Méthode définissant toutes les actions de tous les bouttons.
 * 
 * @throws SQLException : Si une requête SQL n'est pas conforme.
 */
 public void hoverButton() throws SQLException
 {
	
	   
		
	 /**
	  * @author Gaël
	  * Lorsque que la souris entre sur le boutton character, affiche le nom du boutton et son pointeur.
	  */
	 characterButton.setOnMouseEntered(e -> {
		labelCharacter.setVisible(true);
		polygonCharacter.setVisible(true);
		
		
	 });
	 /**
	  * @author Gaël
	  * Lorsque que la souris quitte le boutton character, rends invisible le nom du boutton et son pointeur.
	  */
	 characterButton.setOnMouseExited(e -> {
			labelCharacter.setVisible(false);
			polygonCharacter.setVisible(false);
		 });
	 
	 /**
	  * @author Gaël
	  * Lorsque l'on click sur le boutton character, si le characterPane est visible, on le ferme, sinon on le rends visible. 
	  */
	 characterButton.setOnMouseClicked(e -> {
		if(!characterPane.isVisible())
		{
			characterPane.setVisible(true);
			characterPane.setLayoutX(873); 
			characterPane.setLayoutY(385);// Puis on lui donne une position précise
		}	
		else
		{
			characterPane.setVisible(false);
		}
	 });
	 
	 
	 /**
	  * @author Gaël
	  * Lorsque l'on appuie sur un boutton de points bonus, on ajoute un à la statistiques puis on enlève 1 à nos points bonus totaux
	  */
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
	 
	 /**
	  * @author Gaël
	  * Lorsque l'on passe notre souris sur un boutton,
	  * cela ouvre son nom et son pointeur.
	  * Si l'on clique sur un boutton, cela affiche une fenêtre
	  * coresspondante si elle existe.
	  */
	 
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
	 
	 /**
	  * @author Gaël
	  * Lorsque l'on click sur le boutton quitter, on demande une confirmation à l'utilisateur
	  */
	 exitButton.setOnMouseClicked(e -> {
		 Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous vraiment fermer l'application?", ButtonType.YES, ButtonType.NO);
	        ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
	        if (ButtonType.NO.equals(result)) {
	          
	            e.consume(); // Si l'utilisateur appuie sur non, on consumme l'évenement et on ne ferme pas le jeu
	        }
	        else
	        {
	        	/*
	        	 * Sinon on arrête l'exécution du rafraichissement auto et on ferme le jeu
	        	 */
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
	 
	 /**
	  * @author Gaël
	  * Lorsque l'on reste appuyé sur inventoryList ou characterPane et que l'on bouge la souris, la fenêtre va suivre la souris.
	  */
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
		
	 /**
	  * @author Gaël
	  * Lorsque l'on appuie sur le boutton cacherObjectif, si la fenêtre objectif est visible, on la ferme, sinon on l'ouvre.
	  */
	 cacherObjectifButton.setOnMouseClicked(e -> {
		if(objectifPane.isVisible())
		{
			objectifPane.setVisible(false);
			cacherObjectifButton.setText("Afficher objectifs"); //Si la fenêtre est invisible le boutton devient "Afficher Objectifs"
			cacherObjectifButton.setPrefWidth(150.0);
			cacherObjectifButton.setLayoutX(0);
		}
		else
		{
			objectifPane.setVisible(true);
			cacherObjectifButton.setText("Cacher"); //Sinon le boutton devient "Cacher"
			cacherObjectifButton.setPrefWidth(75.0);
			cacherObjectifButton.setLayoutX(199.0);
		}
			
		 
	 });
	 
	 
	
	
		 

	 /**
		 * @Gaël @Louis
		 * @param skillButton1 Bouton de la compétence n°1
		 * Fonction qui s'éxecute lorsque l'on pose la souris sur le bouton.
		 * @return petite fenêtre d'information
		 */
	skillButton1.setOnMouseEntered(e -> {
		skillPane.setVisible(true); //Rend la petite fenêtre "skillPane" visible.
		nomSkill.setText(" " +competence1.getNom()); //Affiche le nom de la compétence.
		coutSkill.setText(" " + competence1.getCost() + " MP"); //Affiche le coût de la compétence.
		cooldownSkill.setText(" "+ competence1.getCooldown() + " s."); //Affiche le temps avant de pouvoir utiliser la compétence.
		descriptionSkill.setText(competence1.getDescription());	//Affiche la description de la compétence.
	});
	
	
	/**
	 * @Gaël @Louis
	 * @param skillButton1 Bouton de la compétence n°1
	 * @see .setOnMouseEntered
	 * Fonction qui s'éxecute lorsque l'on enlève la souris qui est sur le bouton.
	 * @return fermeture de la petite fenêtre d'information
	 */
	skillButton1.setOnMouseExited(e -> skillPane.setVisible(false)); //Cache la petite fenêtre "skillPane".
	
	
	/**
	 * @Gaël @Louis
	 * @param skillButton1 Bouton de la compétence n°1
	 * @see KeyCode.DIGIT1
	 * Fonction qui s'éxecute lorsque l'on clique sur le bouton.
	 * @return lancementCompetence1()
	 */
	skillButton1.setOnMouseClicked(e -> lancementCompetence1());
	
	
	
	/**
	 * @Gaël @Louis
	 * @param skillButton2 Bouton de la compétence n°2
	 * Fonction qui s'éxecute lorsque l'on pose la souris sur le bouton.
	 * @return petite fenêtre d'information
	 */
	skillButton2.setOnMouseEntered(e -> {
		skillPane.setVisible(true); //Rend la petite fenêtre "skillPane" visible.
		nomSkill.setText(" " +competence2.getNom()); //Affiche le nom de la compétence.
		coutSkill.setText(" " + competence2.getCost() + " MP"); //Affiche le coût de la compétence.
		cooldownSkill.setText(" "+ competence2.getCooldown() + " s."); //Affiche le temps avant de pouvoir utiliser la compétence.
		descriptionSkill.setText(competence2.getDescription()); //Affiche la description de la compétence.
	});
	
	
	/**
	 * @Gaël @Louis
	 * @param skillButton2 Bouton de la compétence n°2
	 * @see .setOnMouseEntered
	 * Fonction qui s'éxecute lorsque l'on enlève la souris qui est sur le bouton.
	 * @return fermeture de la petite fenêtre d'information
	 */
	skillButton2.setOnMouseExited(e -> skillPane.setVisible(false)); //Cache la petite fenêtre "skillPane".
	
	 
	/**
	 * @Gaël @Louis
	 * @param skillButton2 Bouton de la compétence n°2
	 * @see KeyCode.DIGIT2
	 * Fonction qui s'éxecute lorsque l'on clique sur le bouton.
	 * @return lancementCompetence2()
	 */
	skillButton2.setOnMouseClicked(e -> lancementCompetence2());
	 
	
	
	
 }

 /**
  * @author Gaël
  * Cette méthode définie toutes les images pour chaque bouttons.
  * @throws URISyntaxException : Si l'URL n'est pas conforme.
  */
 public void getImages() throws URISyntaxException
 {
	    
	 final URL characterImageURL = getClass().getResource("/icons/character.png"); // On récupère l'URL de l'image que l'on souhaite
     Image character1Image = new Image(characterImageURL.toURI().toString()); //On définit l'image avec cette URL
	 characterImage.setImage(character1Image); //Puis on applique l'image a notre imageView
	 characterButton.setGraphic(characterImage);//Puis on défini l'image de notre boutton à notre imageView
	 
	 final URL bagImageURL = getClass().getResource("/icons/bag.png"); //Pareillement que plus haut etc..
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

 

/**
 * @author Gaël
 * Cette  méthode permets de définir les statistiques du joueur et des monstres.
 * Ainsi que de rafraîchir les éléments graphiques du jeu constamment.
 * 
 */
 public void getStats()
 {
	 ObservableList<String> list = FXCollections.observableArrayList(); // On crée une liste.

	 
	 Viewport viewport = getGameScene().getViewport(); // On récupère la fenêtre du jeu
	 viewport.bindToEntity(getGameWorld().getSingleton(PLAYER), getAppWidth()/2,getAppHeight()/2); // On applique cette fenêtre au joueur pour qu'elle le suive
	 nbr = getGameWorld().getEntitiesByType(MONSTER).size(); // nbr est défini comme le nombre de monstres.
	 nbrChests = getGameWorld().getEntitiesByType(CHEST).size(); // nbrChests est est défini comme le nombre de coffres aux trésors.
	 ArrayList<Label> labels = new ArrayList<Label>(); // On crée un tableau de labels.
	 ArrayList<Label> labelsInventory = new ArrayList<Label>(); // On crée un tableau de labels représentant le texte de l'inventaire
	 
	 /**
	  * @author Gaël
	  * On parcourt l'entièreté de l'inventaire (999), et on crée un label pour chaque place de l'inventaire.
	  */
	 for(int i = 0; i < 999; i++)
	 {
		 Label label = new Label(" ");
		 labelsInventory.add(label);
		 
			getGameScene().addUINodes(labelsInventory.get(i));
			
	 }
	 
	 
	 ArrayList<ProgressBar> progressBarMonsters = new ArrayList<ProgressBar>();// Création d'un tableau de barre de vie pour les monstres
	 pointsBonus = J.getLv().getPointsBonus(); // On définit les points bonus du Joueur dans la variable pointsBonus
	 pseudoLabelCharacter.setText(J.getNom()); // On affiche le pseudo du joueur dans la fenêtre des statistiques.
	 
	 
	 /**
	  * @author Gaël
	  * On parcourt le nombre de monstres dans le jeu, et on leur affecte un label et une barre de vie.
	  */
		for(int i = 0; i < getGameWorld().getEntitiesByType(MONSTER).size(); i++)
		{
			/**
			 * @author Gaël
			 * On crée des labels avec un style particulier
			 */
			Label label = new Label("Label : " + i);
			labels.add(label);
			labels.get(i).setTextFill(Color.RED);
			labels.get(i).setFont(new Font("Eras Bold ITC", 14)); 
			
			/**
			 * @author Gaël
			 * On crée des barres de progression avec un style particulier
			 */
			ProgressBar progressBar = new ProgressBar();
			progressBarMonsters.add(progressBar);
			progressBarMonsters.get(i).setStyle("-fx-accent : red");
			
			/**
			 * @author Gaël
			 * Puis on ajoute les labels et les barres de progression au jeu
			 */
			getGameScene().addUINodes(labels.get(i),progressBarMonsters.get(i));
			
		}
			
	
		/**
		 * @author Gaël
		 * Si le joueur est niveau 1, ses points bonus sont de 1 (permet d'éviter les bugs)
		 */
	 if(J.getLv().getNiveau() == 1)
	    {
		 
	    	pointsBonus = 1;
	    	
	    }
	
	 
	 J.getLv().setPointsBonus(J.getLv().getPointsBonus());// On définit les points bonus du joueur au nombre de points bonus qu'il a 
	
	 
	 /**
	  * @author Gaël
	  * On démarre l'execution de notre rafraichissement automatique et on le définit toutes les 30ms
	  */
     scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
     scheduledExecutorService.scheduleAtFixedRate(() -> {
   
        Platform.runLater(() -> {
        	/**
        	 * @author Gaël
        	 * Si le joueur ouvre un coffre, on crée une animation montrant quel objet est récupéré
        	 */
        	if(GameApp.chestOpened == true)
        	{
        		Label label = new Label(); // On crée un label
        		label.setLayoutX(GameApp.CurentEntityOnClic.getX()-viewport.getX()); // On lui mets comme position la position du coffre
        		label.setTextFill(Color.BLACK); // On lui donne un un style particulier
        		label.setFont(new Font("Eras Bold ITC", 16));
        		getGameScene().addUINodes(label); // On ajoute ce label au jeu
        		for(Item I : GameApp.LootItemsOfMonster)
				{
        			int pos = GameApp.LootItemsOfMonster.indexOf(I);
					System.out.println(I.getNom() + " x" + GameApp.QuantityLootItemsOfMonster.get(pos));
					label.setText(I.getNom() + " x" + GameApp.QuantityLootItemsOfMonster.get(pos));
				}
        		/**
        		 * @author Gaël
        		 * Timeline nous permets de créer une animation voulu
        		 */
        		 Timeline time = new Timeline(
        				 /**
        				  * @author Gaël
        				  * à la seconde 0, On définit l'opacité du label à 1 
        				  * et sa position à la position Y du coffre +70
        				  * puis à la seconde 2, l'opacité du label deviendra progressivement 0 
        				  * et sa position Y aura augmenté de 50.
        				  * Ceci nous créer une animation qui rends le label invisible en 2 secondes,
        				  * et le déplace légérement de haut en bas.
        				  */

 					    new KeyFrame(Duration.ZERO,new KeyValue(label.opacityProperty(), 1),new KeyValue(label.layoutYProperty(),GameApp.CurentEntityOnClic.getY()-viewport.getY()+70)),
        	
 					    new KeyFrame(Duration.seconds(2),new KeyValue(label.opacityProperty(), 0),new KeyValue(label.layoutYProperty(),GameApp.CurentEntityOnClic.getY()-viewport.getY()+120))
 					);
        		 time.setCycleCount(1); //On effectue l'animation une seule fois
					time.play(); // On joue l'animation
        		
        		ArrayList<Item> Inventaire = J.getNewInventory(); //On récupère l'inventaire du joueur dans un tableau d'objets.
        		/**
        		 * @author Gaël
        		 * On parcourt le nombre d'objets dans l'inventaire du joueur
        		 * puis pour chaque éléments dans l'inventaire du joueur,
        		 * on lui rajoute l'objet qu'il a récupéré dans le coffre.
        		 */
        		for(int i = 0; i < J.ItemsCountOnInventoryV2(); i++)
        		{
        			
        			list.add("");
        			
        				if(list.get(i) != null)
        				{
        					list.set(i,Inventaire.get(i).getNom() + " x " + J.getNewInventoryQuantity().get(i));
        				}
        			
        		
        			
        		}
        		inventoryList.setItems(list);
        		
        		GameApp.chestOpened = false; //Quand la fonction est terminé, le coffre n'est plus ouvert.
        		
        	}
        	
        	
        	
        	 if(competence1.getCoolDownIsOver() && wasPressed1 == true) { //Si le temps d'utilisation est écoulé et la compétence à été utilisé...
    			skillButton1.setDisable(false); //...réativation du bouton
    			skillCooldown1.setVisible(false); //...cache le compte à rebours
    			wasPressed1 = false; //Désactive la compétence
    		 }
        	 if(competence2.getCoolDownIsOver() && wasPressed2 == true) { //Si le temps d'utilisation est écoulé et la compétence à été utilisé...
        		//System.out.println("fini"); //DEBUG
        		skillButton2.setDisable(false); //...réativation du bouton
     			skillCooldown2.setVisible(false); //...cache le compte à rebours
     			wasPressed2 = false; //Désactive la compétence
        	 }
        	 
        	
        	 /**
        	  * @author Gaël
        	  * On définie tout les labels sur leur stats approprié du joueur..
        	  * qui changent régulièrement, ainsi que les objectifs.
        	  */
      hpLabelCharacter.setText("HP : " + J.getStat().getCurrentHP() + " / " + J.getStat().getMaxHP());
      mpLabelCharacter.setText("MP : " + J.getStat().getCurrentMP() + " / " + J.getStat().getMaxMP());
      atkLabelCharacter.setText("ATK : " + J.getStat().getMaxATK());
      defLabelCharacter.setText("DEF : " + J.getStat().getMaxDEF());
      spdLabelCharacter.setText("SPD : " + J.getStat().getMaxSPD());
      objectifLabel1.setText("- Tuer " + getGameWorld().getEntitiesByType(MONSTER).size() + " monstres pour passer \nau prochain étage");
      /**
       * @author Gaël
       * Si la condition est remplie, nous pouvons passé au prochain étage et le label devient vert !
       */
      if(getGameWorld().getEntitiesByType(MONSTER).size() == 0)
      {
    	  objectifLabel1.setText("Prochain étage débloqué");
    	  objectifLabel1.setTextFill(Color.GREEN);
      }
      /**
       * @author Gaël
       * Sinon l'objectif reste blanc et incomplet
       */
      else
      {
    	  objectifLabel1.setTextFill(Color.WHITE);
      }
      
      /**
       * @author Gaël
       * On définie les barres de points de vie et magie selon les points actuels du joueur / les points max du joueur
       */
      progHpBar.setProgress((double)J.getStat().getCurrentHP() / (double)J.getStat().getMaxHP());
      progMpBar.setProgress((double)J.getStat().getCurrentMP() / (double)J.getStat().getMaxMP());
      
      
      /**
       * @author Gaël
       * Si notre barre de vie est compris entre 25% et 50%, elle devient orange.
       * Si elle est comprise entre 0% et 25%, elle devient rouge
       * Au dessus de 50%, elle est verte
       */
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
      
     /**
      * @author Gaël
      * Si le joueur change de carte, et passe au niveau suivant
      * Alors on redéfinit le nombre de monstres, sa barre de vie ainsi que ses stats 
      */
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
	      
	      /**
	       * @author Gaël
	       * Une sauvegarde automatique se produit lors d'un changement de niveau
	       */
	      J = FXGL.getGameWorld().getSingleton(GameType.PLAYER).getProperties().getValue("Joueur1");
	        saveLabel2.setVisible(true);
		    PauseTransition pauseTransition = new PauseTransition(Duration.seconds(3));
		    pauseTransition.setOnFinished(e -> { 	
		    saveLabel2.setVisible(false);
		    });
		    pauseTransition.play();
		    
	  
       
	      PlayerComponent.changedMap = false; 
	      
	  }
      
      
      /**
       * @author Gaël
       * On positionne les labels des points de vie, ainsi que les barres de vie
       * à la position de chaque monstre sur la carte. 
       * Puis on définit la progression des barres de vie des monstres
       * à leur vie actuelle/leur vie maximum
       */
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
    	
    	/**
    	 * @author Gaël
    	 * Si le nombre de monstres dans nbr est différent du nombre de monstres actuelle
    	 * alors on supprime le label des points de vie, 
    	 * ainsi que la barre de vie du monstres qui n'existe plus
    	 * et on diminue de un le nombre de monstres dans nbr
    	 * pour que nbr = le nombre de monstre actuelle
    	 */
    	if(nbr != getGameWorld().getEntitiesByType(MONSTER).size())
    	{
    		nbr--;
    		getGameScene().removeUINodes(labels.get(nbr),progressBarMonsters.get(nbr));
    
    	}
    
    	
    	
      }
    

      /*
       * On définit les labels de statistiques du joueur dans la fenêtre character.
       */
      speLabelCharacter.setText("SPE : " + J.getStat().getMaxSPE());
      spaLabelCharacter.setText("SPA : " + J.getStat().getMaxSPA());
      pointsBonusLabel.setText("POINTS BONUS : "+ pointsBonus);
      xpTotalLabelCharacter.setText("XP TOTAL : " + J.getLv().getTotalXP());
      
      
      /**
       * @author Gaël
       * On regarde si le joueur peut passer un niveau,
       * si c'est le cas on augmente ses points de vie et ses points de magie
       * d'une valeur aléatoire entre 5 et 10
       * et on lui redonne toute sa vie et tout ses points de magie
       * en les mettant à la valeur maximum.
       * Puis on ajoute des points bonus selon le nombre de points bonus
       * que le niveau du joueur vient d'atteindre donne.
       */
     if(J.getLv().checkLVisAvalaible())
     {	
    	J.setLV(GameFactory.lvls.get(J.getLv().getNiveau()-1));
    	J.getStat().setMaxHP(J.getStat().getMaxHP() + (5 + (int)(Math.random() * ((10 - 5) + 1))));
    	J.getStat().setMaxMP(J.getStat().getMaxMP() + (5 + (int)(Math.random() * ((10 - 5) + 1))));
    	J.getStat().setCurrentHP(J.getStat().getMaxHP());
    	J.getStat().setCurrentMP(J.getStat().getMaxMP());
    	pointsBonus += J.getLv().getPointsBonus();
     }
      
     
     /*
      * On définit les labels de statistiques du joueur.
      */
     
      levelLabelCharacter.setText("LEVEL : " + J.getLv().getNiveau());
      xpLabelCharacter.setText("XP : " + J.getLv().getCurrentXPforLV() + " / " + J.getLv().getXPneedForNextLV());
      hpBar.setText("HP :                             " + J.getStat().getCurrentHP() + " / " + J.getStat().getMaxHP());
      mpBar.setText("MP :                             " + J.getStat().getCurrentMP() + " / " + J.getStat().getMaxMP());	
      pseudoJoueur.setText(J.getNom() + " " + "LV. "+J.getLv().getNiveau() + " XP : " + J.getLv().getCurrentXPforLV() + " / " + J.getLv().getXPneedForNextLV());	
   
      /**
       * @author Gaël
       * Si les pointsBonus sont égaux à 0,
       * alors on masque les bouttons de points bonus
       * pour éviter les abus des joueur.
       * Sinon, les points bonus sont visible.
       */
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
 
 
 
 
 
 /**
	 * @Gaël @Louis @Gabriel @Rémi
	 * @see KeyCode.DIGIT1
	 * @see skillButton1.setOnMouseClicked
	 * Fonction de lancement de la competence1.
	 * @return Soigne le joueur.
	 */
 public void lancementCompetence1() {
	 if(competence1.getCost() <= J.getStat().getCurrentMP() && competence1.getCoolDownIsOver() && J.getStat().getCurrentHP() != J.getStat().getMaxHP()) { //Si le joueur a assez de MP et que le temps d'attente est atteint, alors on peut lancer la fonction :
		 wasPressed1 = true; //Activation de la competence1
		 competence1.UseCompetence(); //?
		 int HPtoHeal = J.getStat().getMaxHP() - J.getStat().getCurrentHP(); //HP à soigner = HP maximum - HP actuels
		 J.getStat().setCurrentHP(J.getStat().getCurrentHP() + HPtoHeal); //Soigne le joueur : HP actuel + HP à soigner
		 J.getStat().setCurrentMP(J.getStat().getCurrentMP()-competence1.getCost()); //Met à jour la MP du joueur
		 skillButton1.setDisable(true); //Désactive le bouton de la compétence 1
		 skillCooldown1.setVisible(true); //Affiche le compte à rebours
		 Timeline time = new Timeline( //?
				    new KeyFrame(Duration.ZERO,new KeyValue(skillCooldown1.progressProperty(), 1)),
				    new KeyFrame(Duration.seconds(competence1.getCooldown()),new KeyValue(skillCooldown1.progressProperty(), 0))
				);
				time.setCycleCount(1);
				time.play();	
	 }
	 else if(competence1.getCost() > J.getStat().getCurrentMP())
	 {
		 System.out.println("Vous n'avez pas assez de MP");
	 }
	 else if(!competence1.getCoolDownIsOver())
	 {
		 System.out.println("La compétence est en cooldown");
	 }
	 else if(J.getStat().getCurrentHP() == J.getStat().getMaxHP())
	 {
		 System.out.println("Vous avez tout vos HPs");
	 }
 }
 
 
 /**
	 * @Gaël @Louis @Gabriel @Rémi
	 * @see KeyCode.DIGIT2
	 * @see skillButton2.setOnMouseClicked
	 * Fonction de lancement de la competence2.
	 * @return Inflige des dégâts aux monstres aux alentours du joueur.
	 */
 public void lancementCompetence2() {
	 if(competence2.getCost() <= J.getStat().getCurrentMP() && competence2.getCoolDownIsOver()) { //Si le joueur a assez de MP et que le temps d'attente est atteint, alors on peut lancer la fonction :
	 wasPressed2 = true; //Activation de la competence2
		Entity P = FXGL.getGameWorld().getSingleton(GameType.PLAYER); //Récupère les informations du joueur.
	    int px = (int) (P.getX()/80); //Stock la position "x" du joueur
	    int py = (int) P.getY()/80; //Stock la position "y" du joueur
	    int nbr = getGameWorld().getEntitiesByType(MONSTER).size(); //Stock dans "nbr" le nombre de moobs actuellement sur la map
	    //System.out.println("DEBUG LOUIS : nbrMonstre =" + nbr); //Affcihe le nombre de monstres sur la map
	    //set("nbrMob", nbr);
	    for(int i=0; i<nbr; i++) { //Boucle qui va parcourir tous les monstres du tableaux
	    	//println("" + nbr);
	    	Entity CurentEntity = getGameWorld().getEntitiesByType(MONSTER).get(i); //Séléctionne les monstres 1 par 1
	    	int mx = (int) (CurentEntity.getX()/80); //Stock la position "x" du monstre
	    	int my = (int) (CurentEntity.getY()/80); //Stock la position "y" du monstre
	    	int distance = 3; //Distance d'effet autours du joueur
//	    	if(Math.abs(px - mx) < distance && Math.abs(py - my) < distance) { //Test si les monstres sont dans le périmètre d'effet autours du joueur
//	    		println("Deleted");
//	    		CurentEntity.removeFromWorld(); //Permet de litéralement supprimer les monstres
//	    		nbr--;
//	    		set("nbrMob", nbr);	    		
//	    		println("nbr : " + nbr);
//	    		println("i : " + i);
//	    		i--;
//	    		
//	    	}
	    	if(Math.abs(px - mx) < distance && Math.abs(py - my) < distance) { //Test si les monstres sont dans le périmètre d'effet autours du joueur
	    		//A CONTINUER !
	    		System.out.println("Pas encore codé"); //DEBUG LOUIS
	    		//1)Infligé des dégâts aux monstres d'index i
	    		//2)Si le monstre est tué :
	    			//2.1)gagner l'Xp
	    			//2.2)Générer le loot
	    	}
	    } 
	    J.getStat().setCurrentMP(J.getStat().getCurrentMP()-competence2.getCost()); //Met à jour la MP du joueur
	    skillButton2.setDisable(true); //Désactive le bouton de la compétence 2
		skillCooldown2.setVisible(true); //Affiche le compte à rebours
		Timeline time = new Timeline( //?
				    new KeyFrame(Duration.ZERO,new KeyValue(skillCooldown2.progressProperty(), 1)),
				    new KeyFrame(Duration.seconds(competence2.getCooldown()),new KeyValue(skillCooldown2.progressProperty(), 0))
				);
				time.setCycleCount(1);
				time.play();
	 }
	 else if(competence2.getCost() > J.getStat().getCurrentMP()) { //Cas d'erreur si le joueur n'as pas assez de MP
		 System.out.println("Vous n'avez pas assez de MP");
	 }
	 else if(!competence2.getCoolDownIsOver()) { //Cas d'erreur si le temps avant de pouvoir réutiliser la compétence n'est pas désactivé
		 System.out.println("La compétence est en cooldown");
	 }
 }
 
 /**
  * @author Gaël
  * Permets une confirmation lorsque l'on veut fermer l'application
  */
 @FXML
 private void closeRequest() {
        
       
  Stage s = (Stage)hpBar.getScene().getWindow();
  s.setOnCloseRequest(e -> {
	
	  Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous vraiment fermer l'application?", ButtonType.YES, ButtonType.NO);
        ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
        if (ButtonType.NO.equals(result)) {
          
            e.consume(); // Si on choisit non, l'evenement se consume et la fenêtre ne se ferme pas
        }
        else
        {
        	/*
        	 * Sinon la fenêtre se ferme et on arrête le rafraîchissement auto
        	 */
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