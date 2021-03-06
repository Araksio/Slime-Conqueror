package game.and.map;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGL.geto;
import static com.almasb.fxgl.dsl.FXGL.texture;
import static game.and.map.GameApp.BLOCK_SIZE;
import static game.and.map.GameType.BLOCK;
import static game.and.map.GameType.CHEST;
import static game.and.map.GameType.ESCALIER;
import static game.and.map.GameType.MONSTER;
import static game.and.map.GameType.VIDE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Supplier;

import com.almasb.fxgl.core.util.LazyValue;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.pathfinding.CellMoveComponent;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.AnimatedTexture;

import entity.Competence;
import entity.Competences;
import entity.Item;
import entity.Items;
import entity.Joueur;
import entity.LV;
import entity.Loot;
import entity.Money;
import entity.Monster;
import entity.MonsterType;
import entity.Pos;
import entity.Sous_Inventaire;
import entity.Stat;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import other.components.DelayMonsterAtkComponent;
import other.components.PlayerComponent;
import other.components.RandomAStarMoveComponent;

import java.sql.*
;public class GameFactory implements EntityFactory {
	
	/**
	 * initialisation de la plus part des composants visuel du jeu
	 * 
	 * @author Rémi, Esteban, Gabriel
	 */
	
	Joueur J1;
	public static ArrayList<LV> lvls;


	// Création des mur
	
	/**
	 * quand un caractere 1 est lu dans maplevel, le traduit en murGauche
	 * @param data : un caractere de maplevel
	 * @return un bloc graphique
	 * @author rémi, gabriel
	 */
    @Spawns("1")
    public Entity newBlock(SpawnData data) {
    	var view = texture("murGauche.png");
        

        return entityBuilder(data)
                .type(BLOCK)
                .viewWithBBox(view)
                .zIndex(-1)
                .build();
    }
    

	/**
	 * quand un caractere 2 est lu dans maplevel, le traduit en murDroite
	 * @param data : un caractere de maplevel
	 * @return un bloc graphique
	 * @author rémi, gabriel
	 */    
    @Spawns("2")
    public Entity newBlock2(SpawnData data) {
    	var view = texture("murDroite.png");

        return entityBuilder(data)
                .type(BLOCK)
                .viewWithBBox(view)
                .zIndex(-1)
                .build();
    }

    
    

	/**
	 * quand un caractere 3 est lu dans maplevel, le traduit en murHautBas
	 * @param data : un caractere de maplevel
	 * @return un bloc graphique
	 * @author gabriel
	 */    

    @Spawns("3")
    public Entity newBlock3(SpawnData data) {
    	var view = texture("murHautBas.png");

        return entityBuilder(data)
                .type(BLOCK)
                .viewWithBBox(view)
                .zIndex(-1)
                .build();
    }
    
    

	/**
	 * quand un caractere 4 est lu dans maplevel, le traduit en murCoinDroite
	 * @param data : un caractere de maplevel
	 * @return un bloc graphique
	 * @author gabriel
	 */    


    @Spawns("4")
    public Entity newBlock4(SpawnData data) {
    	var view = texture("murCoinDroite.png");

        return entityBuilder(data)
                .type(BLOCK)
                .viewWithBBox(view)
                .zIndex(-1)
                .build();
    }
    
    

	/**
	 * quand un caractere 5 est lu dans maplevel, le traduit en murCoinGauche
	 * @param data : un caractere de maplevel
	 * @return un bloc graphique
	 * @author gabriel 
	 */    

    @Spawns("5")
    public Entity newBlock5(SpawnData data) {
    	var view = texture("murCoinGauche.png");

        return entityBuilder(data)
                .type(BLOCK)
                .viewWithBBox(view)
                .zIndex(-1)
                .build();
    }
    
    /**
	 * quand un caractere 6 est lu dans maplevel, le traduit en inD
	 * @param data : un caractere de maplevel
	 * @return un bloc graphique
	 * @author gabriel 
	 */    

    @Spawns("6")
    public Entity newBlock6(SpawnData data) {
    	var view = texture("inD.png");

        return entityBuilder(data)
                .type(BLOCK)
                .viewWithBBox(view)
                .zIndex(-1)
                .build();
    }
    
    /**
	 * quand un caractere 7 est lu dans maplevel, le traduit en inG
	 * @param data : un caractere de maplevel
	 * @return un bloc graphique
	 * @author gabriel 
	 */    

    @Spawns("7")
    public Entity newBlock7(SpawnData data) {
    	var view = texture("inG.png");

        return entityBuilder(data)
                .type(BLOCK)
                .viewWithBBox(view)
                .zIndex(-1)
                .build();
    }
    

	/**
	 * quand un caractere C est lu dans maplevel, le traduit en coffre
	 * @param data : un caractere de maplevel
	 * @return un bloc graphique
	 * @author rémi
	 */    

    @Spawns("C")
    public Entity newChest(SpawnData data) {
    	var view = texture("Chest.png");
    	
        return entityBuilder(data)
                .type(CHEST)
                .bbox(new HitBox(new Point2D(5, 5), BoundingShape.box(30, 30)))
                .with(new CollidableComponent(true))
                .with(new CellMoveComponent(BLOCK_SIZE, BLOCK_SIZE, 50))
                .view(view)
                .build();
    }
    

    
    /**
     * quand un caractere M est lu dans maplevel, le traduit en monstre
     * 
     * @param data
     * @return mo  (variable ou est stocker les monstres)
     * @author Rémi, Gabriel
     */   

    @Spawns("M")
    public Entity newMonster(SpawnData data) {
    	
    	int RandomMonsterGenerate = (int)(Math.random()*(3)); // Generation d'un nombre aléatoire pour créer un monstre aléatoire
    	
    	if(RandomMonsterGenerate == 2)
    	{
    		 var view = texture("lezard.png"); // création d'une variable contenant l'emplacement du skin (apparence du monstre)
    	        
    	    	String Nom = "Lezard"; // Instanciation Nom du Monstre
    	    	
    	    	int HPofMonster =  (int)(Math.random() * (15 - 10) + 10); // Insanciation HP du Monstre
    	    	int AtkofMonster =  (int)(Math.random() * (3 - 2) + 2);  // Insanciation ATK du Monstre
    	    	int DefofMonster =  (int)(Math.random() * (3 - 2) + 2);  // Insanciation DEF du Monstre
    	    	int MPfMonster =  (int)(Math.random() * (12 - 8) + 8);  // Insanciation MP du Monstre 
    	    	int SPAofMonster =  (int)(Math.random() * (4 - 2) + 2);  // Insanciation SPA du Monstre 
    	    	int SPDofMonster =  (int)(Math.random() * (4 - 2) + 2);  // Insanciation SPD du Monstre 
    	    	int SPEofMonster =  (int)(Math.random() * (8 - 4) + 4);  // Insanciation SPE du Monstre
    	    	float CADofMonster = (float)(Math.random() * (20 - 10) + 10)/10;  // Insanciation de la cadence d'atk du Monstre
    	    	
 
    	    	
    	    	ArrayList<Item> LootItemsOfMonster = new ArrayList<Item>(); // Création de l'arraylist d'item utiliser pour le loot 
    	    	ArrayList<Integer> LootQuantityItemsOfMonster = new ArrayList<Integer>(); // Création de l'arraylist d'int utiliser pour le loot 
    	    	LootItemsOfMonster.add(Items.LezardTail); // ajout de l'item a loot dans la liste
    	    	LootQuantityItemsOfMonster.add((int)(Math.random() * (5 - 1) + 1 + 1)); // ajout de la quantité d'item dans la liste
    	    	String LootMessage = ""; // création du lootmessage
    	    	
       	    	Loot LootOfMonster = new Loot(LootItemsOfMonster,LootQuantityItemsOfMonster,LootMessage); // création du loot du monstre
       	    	int LootGold = (int)(Math.random() * (50 - 10) + 10 + 1); // création de la quantité d'or que le montre va looter

    	    	
    	    	Stat StatPlayer = new Stat(HPofMonster,AtkofMonster,DefofMonster,MPfMonster,SPAofMonster,SPDofMonster,SPEofMonster); // HP,ATK,DEF,MP,SPA,SPD,SPE // Instanciation des stat du montre
    	    	Pos Pos1 = new Pos(0,0,0,0,0); // intanciation de la position du monstre
    	    	 
    	    	Monster M1 = new Monster(Nom,Pos1,StatPlayer,GameType.MONSTER,MonsterType.LEZARD,5.0); // instanciation du monstre
    	    	
    	    	view.setTranslateX(-40);
    	    	view.setTranslateY(-40);
    	    	
    	        var mo = entityBuilder(data) // création de l'entité montre
    	                .type(MONSTER) // définition du type de monstre
    	                .bbox(new HitBox(new Point2D(50, 50), BoundingShape.box(300, 300))) // création de la hitbox du monstre
    	                .view(view) // relation entre le montre et son visuel
    	                .zIndex(0) // priorité d'affichage du monstre par apport au autre texture
    	                .with(new CollidableComponent(true)) // rendre la hitbox du monstre colisionable avec les autre entité
    	                .with(new CellMoveComponent(BLOCK_SIZE, BLOCK_SIZE, SPEofMonster*10)) // défini la vitesse de deplacement du monstre
    	                .with(new AStarMoveComponent(new LazyValue<>(() -> geto("grid")))) // définit les deplacement du monstre
    	                .with(aiComponents.get()) // donne une "IA" au montre pour qu'il puisse ce déplacer et attaquer
    	                .with("Mosnter1",M1) // stoque les statistique et autre paramettre du monstre dans une supervariable
    	                .with("CADofMonster",CADofMonster) // stoque la cadende d'atk du monstre dans une supervariable
    	                .with("LootOfMonster",LootOfMonster) // stoque le loot du monstre dans une supervariable
    	                .with("LootGold",LootGold) // stoque la valeur du monstre dans une supervariable
    	                .build();
    	        
    	        return mo; 
    	}
    	else if(RandomMonsterGenerate == 1)
    	{
    		 var view = texture("bat.png");
    	        
    	    	String Nom = "Chauve souris";
    	    	
    	    	int HPofMonster =  (int)(Math.random() * (15 - 10) + 10);
    	    	int AtkofMonster =  (int)(Math.random() * (3 - 2) + 2);
    	    	int DefofMonster =  (int)(Math.random() * (3 - 2) + 2);
    	    	int MPfMonster =  (int)(Math.random() * (12 - 8) + 8);
    	    	int SPAofMonster =  (int)(Math.random() * (4 - 2) + 2);
    	    	int SPDofMonster =  (int)(Math.random() * (4 - 2) + 2);
    	    	int SPEofMonster =  (int)(Math.random() * (8 - 4) + 4);
    	    	float CADofMonster = (float)(Math.random() * (20 - 10) + 10)/10;
    	    	
 
    	    	
    	    	ArrayList<Item> LootItemsOfMonster = new ArrayList<Item>();
    	    	ArrayList<Integer> LootQuantityItemsOfMonster = new ArrayList<Integer>();
    	    	LootItemsOfMonster.add(Items.BatWing);
    	    	LootQuantityItemsOfMonster.add((int)(Math.random() * (5 - 1) + 1 + 1));
    	    	String LootMessage = "";
    	    	
       	    	Loot LootOfMonster = new Loot(LootItemsOfMonster,LootQuantityItemsOfMonster,LootMessage);
    	    	

       	    	int LootGold = (int)(Math.random() * (50 - 10) + 10 + 1);

    	    	
    	    	Stat StatPlayer = new Stat(HPofMonster,AtkofMonster,DefofMonster,MPfMonster,SPAofMonster,SPDofMonster,SPEofMonster); // HP,ATK,DEF,MP,SPA,SPD,SPE
    	    	Pos Pos1 = new Pos(2,1,0,0,1);
    	    	
    	    	Monster M1 = new Monster(Nom,Pos1,StatPlayer,GameType.MONSTER,MonsterType.BAT,10.0); 


    			Component C1;
    			
    			view.setTranslateX(-40);
    	    	view.setTranslateY(-40);
    	    	
    	    	
    	        var mo = entityBuilder(data)
    	                .type(MONSTER)
    	                .bbox(new HitBox(new Point2D(50, 50), BoundingShape.box(300, 300)))
    	                .view(view)
    	                .zIndex(0)
    	                .with(new CollidableComponent(true))
    	                .with(new CellMoveComponent(BLOCK_SIZE, BLOCK_SIZE, SPEofMonster*10))
    	                .with(new AStarMoveComponent(new LazyValue<>(() -> geto("grid"))))
    	                .with(aiComponents.get())
    	                .with("Mosnter1",M1)
    	                .with("CADofMonster",CADofMonster)
    	                .with("LootOfMonster",LootOfMonster)
    	                .with("LootGold",LootGold)
    	                .build();
    	        
    	        return mo;
    	}
    	else
    	{
    		 var view = texture("renard.png");
    	        
    	        // penser a changer les stat prédefini en stat random avec plusieurs montre et stat possible
    	        
    	    	String Nom = "Renard";
    	    	
    	    	int HPofMonster =  (int)(Math.random() * (30 - 15) + 15);
    	    	int AtkofMonster =  (int)(Math.random() * (5 - 2) + 2);
    	    	int DefofMonster =  (int)(Math.random() * (5 - 2) + 2);
    	    	int MPfMonster =  (int)(Math.random() * (12 - 8) + 8);
    	    	int SPAofMonster =  (int)(Math.random() * (4 - 2) + 2);
    	    	int SPDofMonster =  (int)(Math.random() * (4 - 2) + 2);
    	    	int SPEofMonster =  (int)(Math.random() * (8 - 4) + 4);
    	    	float CADofMonster = (float)(Math.random() * (30 - 15) + 15)/10;
    	    	
    	    	ArrayList<Item> LootItemsOfMonster = new ArrayList<Item>();
    	    	ArrayList<Integer> LootQuantityItemsOfMonster = new ArrayList<Integer>();
    	    	LootItemsOfMonster.add(Items.FoxTail);
    	    	LootQuantityItemsOfMonster.add((int)(Math.random() * (5 - 1) + 1 + 1));
    	    	String LootMessage = "";
    	    	
       	    	Loot LootOfMonster = new Loot(LootItemsOfMonster,LootQuantityItemsOfMonster,LootMessage);
       	    	
       	    	int LootGold = (int)(Math.random() * (100 - 20) + 20 + 1);
    	    	
    	    	Stat StatPlayer = new Stat(HPofMonster,AtkofMonster,DefofMonster,MPfMonster,SPAofMonster,SPDofMonster,SPEofMonster); // HP,ATK,DEF,MP,SPA,SPD,SPE
    	    	Pos Pos1 = new Pos(0,0,0,0,0);
    	    	
    	    	Monster M1 = new Monster(Nom,Pos1,StatPlayer,GameType.MONSTER,MonsterType.RENARD,15.0); 

    	    	@SuppressWarnings("unused")
    			Component C1;
    	    	
    	    	view.setTranslateX(-40);
    	    	view.setTranslateY(-40);
    	    	
    	    	
    	        var mo = entityBuilder(data)
    	                .type(MONSTER)
    	                .bbox(new HitBox(new Point2D(50, 50), BoundingShape.box(300, 300)))
    	                .view(view)
    	                .zIndex(0)
    	                .with(new CollidableComponent(true))
    	                .with(new CellMoveComponent(BLOCK_SIZE, BLOCK_SIZE, 50))
    	                .with(new AStarMoveComponent(new LazyValue<>(() -> geto("grid"))))
    	                .with(aiComponents.get())
    	                .with("Mosnter1",M1)
    	                .with("CADofMonster",CADofMonster)
    	                .with("LootOfMonster",LootOfMonster)
    	                .with("LootGold",LootGold)
    	                .build();
    	        
    	        
    	        return mo;
    	}

    }
    
    
    

	/**
	 * quand un caractere D est lu dans maplevel, le traduit en Boss
	 * @param data : un caractere de maplevel
	 * @return un boss graphique
	 * @author rémi
	 */    

    
    @Spawns("D")
    public Entity newDragonBoss(SpawnData data) {
    	
    		 var view = texture("FireDragonBoss.png");
    	        
    	        // penser a changer les stat prédefini en stat random avec plusieurs montre et stat possible
    	        
    	    	String Nom = "Ultimate Fire Dragon";
    	    	
    	    	int HPofMonster =  300;
    	    	int AtkofMonster =  5;
    	    	int DefofMonster =  2;
    	    	int MPfMonster =  250;
    	    	int SPAofMonster =  15;
    	    	int SPDofMonster =  1;
    	    	int SPEofMonster =  10;
    	    	float CADofMonster = (float) 3.0;
    	    	
 
    	    	
    	    	ArrayList<Item> LootItemsOfMonster = new ArrayList<Item>();
    	    	ArrayList<Integer> LootQuantityItemsOfMonster = new ArrayList<Integer>();
    	    	LootItemsOfMonster.add(Items.DragonHead);
    	    	LootQuantityItemsOfMonster.add((int)(Math.random() * (5 - 1) + 1 + 1));
    	    	String LootMessage = "";
    	    	
       	    	Loot LootOfMonster = new Loot(LootItemsOfMonster,LootQuantityItemsOfMonster,LootMessage);

       	    	int LootGold = (int)(Math.random() * (50000 - 10000) + 10000 + 1);

    	    	
    	    	Stat StatPlayer = new Stat(HPofMonster,AtkofMonster,DefofMonster,MPfMonster,SPAofMonster,SPDofMonster,SPEofMonster); // HP,ATK,DEF,MP,SPA,SPD,SPE
    	    	Pos Pos1 = new Pos(2,1,0,0,1);
    	    	
    	    	Monster M1 = new Monster(Nom,Pos1,StatPlayer,GameType.MONSTER,MonsterType.DRAGONBOSS,50.0); 
    	    	
    	    	view.setTranslateX(-120);
    	    	view.setTranslateY(-120);


    			Component C1;
    	    	
    	    	
    	        var mo = entityBuilder(data)
    	                .type(MONSTER)
    	                .bbox(new HitBox(new Point2D(240, 240), BoundingShape.box(2, 2)))
    	                .view(view)
    	                .zIndex(0)
    	                .with(new CollidableComponent(true))
    	                .with(new CellMoveComponent(BLOCK_SIZE, BLOCK_SIZE, SPEofMonster*10))
    	                .with(new AStarMoveComponent(new LazyValue<>(() -> geto("grid"))))
    	                .with(aiComponents.get())
    	                .with("Mosnter1",M1)
    	                .with("CADofMonster",CADofMonster)
    	                .with("LootOfMonster",LootOfMonster)
    	                .with("LootGold",LootGold)
    	                .build();
    	        
    	        return mo;
    	
    	
    }
    
    
    //Creation du vide
    

	/**
	 * quand un caractere 0 est lu dans maplevel, le traduit en Vide
	 * @param data : un caractere de maplevel
	 * @return un bloc graphique
	 * @author rémi, gabriel
	 */    

    @Spawns("0")
    public Entity newVide(SpawnData data) {
    	var rect = new Rectangle(80, 80, Color.GREY);
    	 //var rect = texture("sol.png"); 

        return entityBuilder(data)
                .type(VIDE)
                .zIndex(-1)
                .viewWithBBox(rect)
                .build();
    }

  //Creation du vide2

	/**
	 * quand un caractere 9 est lu dans maplevel, le traduit en Vide
	 * @param data : un caractere de maplevel
	 * @return un bloc graphique
	 * @author gabriel
	 */    

    @Spawns("9")
    public Entity newVide2(SpawnData data) {
    	var rect = new Rectangle(80, 80, Color.BLACK);

        return entityBuilder(data)
                .type(VIDE)
                .zIndex(-1)
                .viewWithBBox(rect)
                .build();
    }
    
 
    
    
    //Creation des Escalier
    
	/**
	 * quand un caractere E est lu dans maplevel, le traduit en Escalier
	 * @param data : un caractere de maplevel
	 * @return un bloc graphique
	 * @author remi
	 */    

    @Spawns("E")
    public Entity newEscalier(SpawnData data) {
        var view = texture("escalier.png");

        return entityBuilder(data)
                .type(ESCALIER)
                .bbox(new HitBox(new Point2D(5, 5), BoundingShape.box(30, 30)))
                .view(view)
                .zIndex(-1)
                .with(new CollidableComponent(true))
                .with(new CellMoveComponent(BLOCK_SIZE, BLOCK_SIZE, 50))
                .build();
    }
    
    //Création des niveaux
    
	/**
	 * 
	 * cree une liste de niveau basé sur la base de donnée
	 * @return une array liste de niveau
	 * 
	 * @author Esteban
	 */    

    public static ArrayList<LV> createLVs() throws SQLException
	{
		lvls = new ArrayList<LV>();

		//la table lvl, l id 1 est reservé au joueur et apres c est reservé pour creer c'est niveaux
		
		//debut du sql
		Connection db = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/projetpoagl?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
		Statement demandeRequete = db.createStatement();

		ResultSet niveauACreer;
		
		int niveau;
		double xpTotal;
		double xpActuel;
		double xpProchain;
		int pointBonus;
		LV lvl ; 
	
		ResultSet resultatRequete = demandeRequete.executeQuery("SELECT count(*) FROM `lvl`"); 
		
		//sert à automatiser les lvl, c est a dire qu on peut rajouter autant de palier de lvl dans la db
		
		int nombreLvlACreer = 0;
		while(resultatRequete.next()) {
			nombreLvlACreer = resultatRequete.getInt(1);
		}
			
			//ce for sert a creer les niveaux - les niveaux deja passé
			for(int i = 1 ; i<=nombreLvlACreer; i+=1) {

				//je recupere toutes les infos pour creer chaque lvl
			niveauACreer = demandeRequete.executeQuery("SELECT * FROM `lvl` WHERE idLvl="+i);
			
			//obligatoire sinon ne marche pas
			while(niveauACreer.next()) {
				niveau = niveauACreer.getInt(2) ;
				xpTotal = (double) niveauACreer.getInt(3);
				xpActuel = (double) niveauACreer.getInt(4);
				xpProchain = (double) niveauACreer.getInt(5);
				pointBonus = niveauACreer.getInt(8);
				
			//on cree le lvl
		 lvl = new LV(niveau,xpTotal,xpActuel,xpProchain,pointBonus); 
					
			//on l ajoute a la liste
		lvls.add(lvl);
			}
			
		}
		
		
		
		
		
	
		
		//fin du sql

		return lvls;
	}
    
    
    
    //Creation du Joueur
    

	/**
	 * quand un caractere P est lu dans maplevel, cree le joueur et toutes ses statistiques liees
	 * en se basant principalement sur la base de donnée
	 * @param data : un caractere de maplevel
	 * @return un Joueur graphique
	 * @author rémi, esteban, gabriel
	 */    

    @Spawns("P")
    public Entity newPlayer(SpawnData data) throws SQLException {
    	
    	createLVs();
    	String Pseudo = "";

		Stat StatPlayer = new Stat(0, 0, 0, 0, 0, 0, 0);
		AnimatedTexture view = texture("player.png").toAnimatedTexture(1, Duration.seconds(0.33));
	    

    			
    			//partie sql java
		
	

    			Connection db = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/projetpoagl?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");

    			Statement demandeRequete = db.createStatement();
        
    			ResultSet pseudoDB = demandeRequete.executeQuery("Select nom from joueur where idjoueur=1");
    			
    			
  			  
    			while(pseudoDB.next()) {
    				  Pseudo = pseudoDB.getString(1);
    	    	}
    		 
    			//crée les stats du joueur
    			ResultSet statDB = demandeRequete.executeQuery("SELECT * FROM `stats` WHERE idStats=1");
    			
    			while(statDB.next()) {
    				StatPlayer = new Stat(statDB.getInt(2),statDB.getInt(4),statDB.getInt(6),statDB.getInt(8),statDB.getInt(10),statDB.getInt(12),statDB.getInt(14));
    				StatPlayer.setCurrentHP(statDB.getInt(3));
    				StatPlayer.setCurrentATK(statDB.getInt(5));
    				StatPlayer.setCurrentDEF(statDB.getInt(7));
    				StatPlayer.setCurrentMP(statDB.getInt(9));
    				StatPlayer.setCurrentSPA(statDB.getInt(11));
    				StatPlayer.setCurrentSPD(statDB.getInt(13));
    				StatPlayer.setCurrentSPE(statDB.getInt(15));
    			}
    			
    			
    			
    			  
    			  ResultSet argentDB = demandeRequete.executeQuery("SELECT * FROM `money` WHERE idJoueur=1");
      		
    			  
    			//fin partie sql java
    			
    			// pas encore fait le stockage de la position
    			
    			
    	        Sous_Inventaire PlayerInventory [] = new Sous_Inventaire [6];
    	        PlayerInventory[0] = new Sous_Inventaire("Armes");
    	        PlayerInventory[1] = new Sous_Inventaire("Armures/Tenue");
    	        PlayerInventory[2] = new Sous_Inventaire("Potion");
    	        PlayerInventory[3] = new Sous_Inventaire("Items Spéciaux");
    	        PlayerInventory[4] = new Sous_Inventaire("Craft");
    	        PlayerInventory[5] = new Sous_Inventaire("Loot");
    	        
    	      
    	        Money PlayerMoney = new Money(0,0);	        
    	        Competence[] CompetenceList = new Competence[9];
    	        
    	        CompetenceList[0] = Competences.HealII;
    	        CompetenceList[1] = Competences.Explosion;

    	        Pos Pos1 = new Pos(5,1,0,0,1);
    			
				
				
    	
    	
    			
    	J1 = new Joueur(Pseudo,StatPlayer,Pos1,GameType.PLAYER,PlayerInventory,PlayerMoney,CompetenceList); 
    	
    	while(argentDB.next()) {
			 J1.getPlayerMoney().addMoney(argentDB.getInt(2));
			 J1.getPlayerMoney().seMoneyOnBank(argentDB.getInt(3));
		  }
    	
    	//partie sql
     	
    	ResultSet inventaireSQL =demandeRequete.executeQuery("SELECT * FROM `item` WHERE idJoueur='1'");
    	//je dois corriger le resultat de la requete sql sans les espaces
    	while(inventaireSQL.next()) {
    			if(inventaireSQL.getString(2).equals("Queue de renard")) {
    				J1.addItems(new Items().FoxTail);
    			}else if(inventaireSQL.getString(2).equals("Aile de chauve-souris")) {
    				J1.addItems(new Items().BatWing);
    			}else if(inventaireSQL.getString(2).equals("Queue de lezard")) {
    				J1.addItems(new Items().LezardTail);
    			}else if(inventaireSQL.getString(2).equals("Dragon Head")){
    			J1.addItems(new Items().DragonHead);
    			}else {
    				System.out.println("item non reconnu");
    			}
    		}
    		
    	ResultSet lvlDuJoueur = demandeRequete.executeQuery("Select idlvl from lvl where idjoueur=1");
		while(lvlDuJoueur.next()) {
	    	J1.setLV(lvls.get(lvlDuJoueur.getInt(1)-1));	
		}
    	
		ResultSet pointBonusJoueur = demandeRequete.executeQuery("Select pointBonusJoueur from joueur where idjoueur=1");
		while(pointBonusJoueur.next()) {
	    	J1.getLv().setPointsBonus(pointBonusJoueur.getInt(1));
		}
    	//fin partie sql
    	data.put("J", J1);
    	
    	
    	
    	J1 = data.get("J");
        
        var e = entityBuilder(data)
                .type(J1.getType())
                .bbox(new HitBox(new Point2D(4, 4), BoundingShape.box(80, 80)))
                .view(view.loop())
                .with(new CollidableComponent(true))
                .with(new CellMoveComponent(BLOCK_SIZE, BLOCK_SIZE, 200).allowRotation(false)) // Mouuvement et vitesse 
                .with(new AStarMoveComponent(new LazyValue<>(() -> geto("grid"))))
                .with(new PlayerComponent())
                .zIndex(0)
                .with("Joueur1",J1)
                .build();
        
        

        e.setLocalAnchorFromCenter();

        

        return e;
    }

    
    private Supplier<Component> aiComponents = new Supplier<>() {
        private Map<Integer, Supplier<Component>> components = Map.of(
                0, () -> new DelayMonsterAtkComponent().withDelay(),
                1, RandomAStarMoveComponent::new,
                2, DelayMonsterAtkComponent::new
        );

        private int index = 0;

        @Override
        public Component get() {
            
            if (index == 4) {
                index = 0;
            }

            return components.get(index).get();
        }
    };


}