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

import entity.Joueur;
import entity.LV;
import entity.Monster;
import entity.Pos;
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
	Joueur J1;
	public static ArrayList<LV> lvls;
	// Création des mur
    @Spawns("1")
    public Entity newBlock(SpawnData data) {
        var rect = new Rectangle(76, 76, Color.BLACK);
        rect.setArcWidth(25);
        rect.setArcHeight(25);
        rect.setStrokeWidth(1);
        rect.setStroke(Color.BLUE);

        return entityBuilder(data)
                .type(BLOCK)
                .viewWithBBox(rect)
                .zIndex(-1)
                .build();
    }
    
    
    @Spawns("2")
    public Entity newBlock2(SpawnData data) {
        var rect = new Rectangle(76, 76, Color.YELLOW);
        rect.setArcWidth(250);
        rect.setArcHeight(250);
        rect.setStrokeWidth(1);
        rect.setStroke(Color.BLACK);

        return entityBuilder(data)
                .type(BLOCK)
                .viewWithBBox(rect)
                .zIndex(-1)
                .build();
    }
    
    
    @Spawns("C")
    public Entity newChest(SpawnData data) {
    	var view = texture("Chest.jpg");
    	
    	
        return entityBuilder(data)
                .type(CHEST)
                .zIndex(-1)
                .bbox(new HitBox(new Point2D(5, 5), BoundingShape.box(30, 30)))
                .with(new CollidableComponent(true))
                .with(new CellMoveComponent(BLOCK_SIZE, BLOCK_SIZE, 50))
                .view(view)
                .build();
    }
    
    @Spawns("M")
    public Entity newMonster(SpawnData data) {
        var view = texture("Monster.png");
        
        // penser a changer les stat prédefini en stat random avec plusieurs montre et stat possible
        
    	String Nom = "Soldat Squelette";
    	
    	Stat StatPlayer = new Stat(20,3,3,3,3,3,3); // HP,ATK,DEF,MP,SPA,SPD,SPE
    	Pos Pos1 = new Pos(2,1,0,0,1);
    	
    	Monster M1 = new Monster(Nom,Pos1,StatPlayer,GameType.MONSTER,5.0); 
    	
    	view.setTranslateX(-40);
    	view.setTranslateY(-40);

    	@SuppressWarnings("unused")
		Component C1;
    	
    	
        var mo = entityBuilder(data)
                .type(MONSTER)
                .bbox(new HitBox(new Point2D(50, 50), BoundingShape.box(300, 300)))
                .view(view)
                .zIndex(-1)
                .with(new CollidableComponent(true))
                .with(new CellMoveComponent(BLOCK_SIZE, BLOCK_SIZE, 50))
                .with(new AStarMoveComponent(new LazyValue<>(() -> geto("grid"))))
                .with(aiComponents.get())
                .with("Mosnter1",M1)
                .build();
        
        
        return mo;
    }
    
    
    //Creation du vide
    @Spawns("0")
    public Entity newVide(SpawnData data) {
        var view = texture("");
        view.setTranslateX(5);
        view.setTranslateY(5);

        return entityBuilder(data)
                .type(VIDE)
                .build();
    }
    
    //Creation des Escalier
    @Spawns("E")
    public Entity newEscalier(SpawnData data) {
        var view = texture("TP.png");

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
	
		int nombreLvlACreer;
		ResultSet resultatRequete = demandeRequete.executeQuery("SELECT count(*) FROM `lvl`"); 
		
//		a faire pour automatiser quand on creera d autre lvl
//		while(resultatRequete.next()) {
//			nombreLvlACreer = nombreLvlACreer-1;
//		}
			
			//ce for sert a creer les 5 premiers niveaux - les levels deja passé
			for(int i = 1 ; i<=5; i+=1) {

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
    			
    			
    			//fin partie sql java
    			
    			// pas encore fait le stockage de la position 
    		   Pos Pos1 = new Pos(5,1,0,0,1);
    			
				
				
    	
    	
    			
    	J1 = new Joueur(Pseudo,StatPlayer,Pos1,GameType.PLAYER); 
    	
    	ResultSet lvlDuJoueur = demandeRequete.executeQuery("Select idlvl from lvl where idjoueur=1");
		while(lvlDuJoueur.next()) {
	    	J1.setLV(lvls.get(lvlDuJoueur.getInt(1)-1));	
		}
    	
		ResultSet pointBonusJoueur = demandeRequete.executeQuery("Select pointBonusJoueur from joueur where idjoueur=1");
		while(pointBonusJoueur.next()) {
	    	J1.getLv().setPointsBonus(pointBonusJoueur.getInt(1));
		}
    	
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
    
	public static void println(String T)
	{
		System.out.println(T);
	}

}