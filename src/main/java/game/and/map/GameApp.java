package game.and.map;

import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getAppWidth;
import static com.almasb.fxgl.dsl.FXGL.getAssetLoader;
import static com.almasb.fxgl.dsl.FXGL.getGameScene;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.getInput;
import static com.almasb.fxgl.dsl.FXGL.geti;
import static com.almasb.fxgl.dsl.FXGL.geto;
import static com.almasb.fxgl.dsl.FXGL.onCollision;
import static com.almasb.fxgl.dsl.FXGL.onCollisionBegin;
import static com.almasb.fxgl.dsl.FXGL.onCollisionEnd;
import static com.almasb.fxgl.dsl.FXGL.set;
import static com.almasb.fxgl.dsl.FXGL.spawn;
import static game.and.map.GameType.BLOCK;
import static game.and.map.GameType.CHEST;
import static game.and.map.GameType.ESCALIER;
import static game.and.map.GameType.MONSTER;
import static game.and.map.GameType.PLAYER;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.text.TextLevelLoader;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.pathfinding.CellState;
import com.almasb.fxgl.pathfinding.astar.AStarCell;
import com.almasb.fxgl.pathfinding.astar.AStarGrid;

import GUI.MySceneFactory;
import entity.Joueur;
import entity.Monster;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import other.components.PlayerComponent;

public class GameApp extends GameApplication {
	public static AnchorPane layout;
	public static String vJeu = "v2.2";
	
    public static final int BLOCK_SIZE = 80;  // Taille d'un bloc, dans la grille

    public static final int MAP_SIZE_PRINT = 21; // Taille de la carte
    
    public static final int MAP_SIZE_REEL = 21; // Taille de la carte

    public static final int TIME_PER_LEVEL = 1; // Telos en seconde

    public static int FLOOR = 0; // Telos en seconde
    
    public static boolean OnCollisionWithMonster = false;
    
    public static javafx.geometry.Point2D LAST_CLICK = new javafx.geometry.Point2D (0.0,0.0);
    
    public static Entity PlayerCombat;
    public static Entity CurrentBattle;
    
    public static Viewport viewport;
    
    public static Entity getPlayer() {
        return getGameWorld().getSingleton(PLAYER); // Instanciation du Joueur dans le Jeux
    }

    public PlayerComponent getPlayerComponent() {
        return getPlayer().getComponent(PlayerComponent.class); // Debut de la gestion des  mouvement
    }
   
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1280);  // Insantiation de la Largeur
        settings.setHeight(900); // Instanciation de la Hauteur
        settings.setTitle("MAPFX"); // Nom de la fenettre du jeu
        settings.setVersion("1.0"); // Version du Jeu
        settings.setFullScreenAllowed(true);
       settings.setTitle("Slime Conqueror");
       settings.setVersion(vJeu);
     
 
        settings.setManualResizeEnabled(true); // Autoriser le changement de taille de la fenettre manuellement
        settings.setPreserveResizeRatio(true); // Garder le ratio du jeu
    	settings.setSceneFactory(new MySceneFactory());
    	settings.setMainMenuEnabled(true);
    	 settings.setGameMenuEnabled(true);
    	
    	
    }
   
  
	@Override
    protected void initUI()
    {
		
    	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/GUI/UIJeu.fxml"));
	    
			try {
				layout = loader.<AnchorPane>load();	
				 getGameScene().addChild(layout);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	  
	
    }

    // Touche Utiliser pour les mouvement
    
    @Override
    protected void initInput() {
    	Input input = getInput();
    	
    	
    	 getInput().addAction(new UserAction("HideGUI") {
             @Override
             protected void onActionBegin() {
           
             	if(layout.getScene() != null)
             	{
             		
             		getGameScene().removeChild(layout);
             	}
             	else
             	{
             		getGameScene().addChild(layout);
             	}
  
             }
                     }, KeyCode.H);
    	
        getInput().addAction(new UserAction("Up") {
            @Override
            protected void onAction() {
            	/*
            	int MX = (int) getPlayer().getX();
            	int MY = (int) getPlayer().getY();
            	
            	getGameTimer().runOnceAfter(() -> {
	            	int MX2 = MX;
	            	int MY2 = MY;
            	
            	AStarCell c = new AStarCell((int)(MX2/80), (int)(MY2/80), CellState.WALKABLE);
    			AStarGrid grid = geto("grid");
    			grid.set((int)(MX2/80), (int)(MY2/80),c);
    			//println("MX " + (int)(MX/80));
    			//println("MY " + (int)(MY/80));
    			
    			set("grid", grid);
    			
    			}, Duration.seconds(0.25));
            	*/
            	
                getPlayerComponent().up();
              //viewport.setY(viewport.getY() - 80);

                /*
    			int MX2 = MX;
    			int MY2 = MY - 80;
                
    			getGameTimer().runOnceAfter(() -> {
    				
    				//spawn("C",MX2,MY2);
	            	
	    			AStarCell c2 = new AStarCell((int)(MX2/80), (int)(MY2/80), CellState.NOT_WALKABLE);
	    			AStarGrid grid2 = geto("grid");
	    			grid2.set((int)(MX2/80), (int)(MY2/80),c2);
	    			//println("MX " + (int)(MX/80));
	    			//println("MY " + (int)(MY/80));
	    			
	    			set("grid", grid2);
	    			
    			}, Duration.seconds(0.5));
            	*/
            }
                    }, KeyCode.Z);

        getInput().addAction(new UserAction("Down") {
            @Override
            protected void onAction() {
            	/*
            	int MX = (int) getPlayer().getX();
            	int MY = (int) getPlayer().getY();
            	
            	getGameTimer().runOnceAfter(() -> {
	            	int MX2 = MX;
	            	int MY2 = MY;
            	
            	AStarCell c = new AStarCell((int)(MX2/80), (int)(MY2/80), CellState.WALKABLE);
    			AStarGrid grid = geto("grid");
    			grid.set((int)(MX2/80), (int)(MY2/80),c);
    			//println("MX " + (int)(MX/80));
    			//println("MY " + (int)(MY/80));
    			
    			set("grid", grid);
    			}, Duration.seconds(0.25));
            	*/
            	
                getPlayerComponent().down();
                //viewport.setY(viewport.getY() + 80);

                /*
    			int MX2 = MX;
    			int MY2 = MY + 80;
                
    			getGameTimer().runOnceAfter(() -> {
    				
    				//spawn("C",MX2,MY2);
	            	
	    			AStarCell c2 = new AStarCell((int)(MX2/80), (int)(MY2/80), CellState.NOT_WALKABLE);
	    			AStarGrid grid2 = geto("grid");
	    			grid2.set((int)(MX2/80), (int)(MY2/80),c2);
	    			//println("MX " + (int)(MX/80));
	    			//println("MY " + (int)(MY/80));
	    			
	    			set("grid", grid2);
	    			
    			}, Duration.seconds(0.5));
    			*/
            }
        }, KeyCode.S);

        getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
            	
            	/*
            	int MX = (int) getPlayer().getX();
            	int MY = (int) getPlayer().getY();
            	
            	getGameTimer().runOnceAfter(() -> {
	            	int MX2 = MX;
	            	int MY2 = MY;
            	
            	AStarCell c = new AStarCell((int)(MX2/80), (int)(MY2/80), CellState.WALKABLE);
    			AStarGrid grid = geto("grid");
    			grid.set((int)(MX2/80), (int)(MY2/80),c);
    			//println("MX " + (int)(MX/80));
    			//println("MY " + (int)(MY/80));
    			
    			set("grid", grid);
    			}, Duration.seconds(0.25));
    			*/
    			
    			getPlayerComponent().left();
                //viewport.setY(viewport.getX() - 80);
                
    			/*
    			int MX2 = MX - 80;
    			int MY2 = MY;
                
    			getGameTimer().runOnceAfter(() -> {
    				
    				//spawn("C",MX2,MY2);
	            	
	    			AStarCell c2 = new AStarCell((int)(MX2/80), (int)(MY2/80), CellState.NOT_WALKABLE);
	    			AStarGrid grid2 = geto("grid");
	    			grid2.set((int)(MX2/80), (int)(MY2/80),c2);
	    			//println("MX " + (int)(MX/80));
	    			//println("MY " + (int)(MY/80));
	    			
	    			set("grid", grid2);
	    			
    			}, Duration.seconds(0.5));
    			*/
            }
        }, KeyCode.Q);

        getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
            	
            	/*
            	int MX = (int) getPlayer().getX();
            	int MY = (int) getPlayer().getY();
            	
            	getGameTimer().runOnceAfter(() -> {
	            	int MX2 = MX;
	            	int MY2 = MY;
	            	
	            	AStarCell c = new AStarCell((int)(MX2/80), (int)(MY2/80), CellState.WALKABLE);
	    			AStarGrid grid = geto("grid");
	    			grid.set((int)(MX2/80), (int)(MY2/80),c);
	    			//println("MX " + (int)(MX/80));
	    			//println("MY " + (int)(MY/80));
	    			
	    			set("grid", grid);
    			}, Duration.seconds(0.25));
    			*/
    			
    			getPlayerComponent().right();

                //viewport.setY(viewport.getX() + 80);

    			/*
    			int MX2 = MX + 80;
    			int MY2 = MY;
    			
    			getGameTimer().runOnceAfter(() -> {
    				
    				//spawn("C",MX2,MY2);
	    			
	    			AStarCell c2 = new AStarCell((int)(MX2/80), (int)(MY2/80), CellState.NOT_WALKABLE);
	    			AStarGrid grid2 = geto("grid");
	    			grid2.set((int)(MX2/80), (int)(MY2/80),c2);
	    			//println("MX " + (int)(MX/80));
	    			//println("MY " + (int)(MY/80));
	    			
	    			set("grid", grid2);
	    			
    			}, Duration.seconds(0.5));
    			*/
            }
        }, KeyCode.D);
    
  
    	getInput().addAction(new UserAction("Teleport") {
    		@Override
    		protected void onActionBegin() {
    			
    			getPlayerComponent().teleport(0,BLOCK_SIZE,MAP_SIZE_PRINT);
    		}
    	}, KeyCode.F);
    	
    	getInput().addAction(new UserAction("AttackPos") {
    		@Override
    		protected void onActionBegin() {
    			
    			LAST_CLICK = input.getMousePositionWorld();
    			
    			int x = (int)LAST_CLICK.getX();
    			int y = (int)LAST_CLICK.getY();
    			
    			//x = x - (x % 80);
    			//y = y - (y % 80);
    			
    			//javafx.geometry.Point2D LAST_CLICKMK2 = new javafx.geometry.Point2D ((double)x,(double)y);
    			
    			//LAST_CLICK = LAST_CLICKMK2;
    			
    			//Entity Mon = spawn("M",x,y);
    			
    			//getGameWorld().getEntitiesAt(LAST_CLICK).add(Mon);
    			
    			int nbr = getGameWorld().getEntitiesByType(MONSTER).size();
    			
    			int nbrChest = getGameWorld().getEntitiesByType(CHEST).size();
    			
    			set("nbrMob", nbr);
    			
    			for(int i = 0; i < nbr; i++)
    			{
    			
    				nbr = geti("nbrMob");
    				
	    			Entity CurentEntityOnClic = getGameWorld().getEntitiesByType(MONSTER).get(i);
	    			
	    			//println("nbr : " + nbr);
	    			//println("Entity x = " + CurentEntityOnClic.getX()/80);
	    			//println("Entity y = " + CurentEntityOnClic.getX()/80);
	    			//println("Clic   x = " + x/80);
	    			//println("Clic   y = " + y/80);
	    			int xe = (int)CurentEntityOnClic.getX();
	    			int ye = (int)CurentEntityOnClic.getY();
	    			
	    			if(xe/80 == x/80 && ye/80 == y/80)
	    			{
		    			  			
		    			//println("ETT = " + CurentEntityOnClic );
		    			
		    			if(CurentEntityOnClic.getType() == MONSTER)
		    			{
		    				@SuppressWarnings("unused")
							Joueur P1 = getPlayer().getProperties().getValue("Joueur1");
		    				int P1X = (int)(getPlayer().getX()/BLOCK_SIZE);
		    				int P1Y = (int)(getPlayer().getY()/BLOCK_SIZE);
		    				int M1X = (int)(CurentEntityOnClic.getX()/BLOCK_SIZE);
		    				int M1Y = (int)(CurentEntityOnClic.getY()/BLOCK_SIZE);
		    				boolean PosEgal = (P1X == M1X && P1Y == M1Y);
		    				boolean PosXP1 = (P1X+1 == M1X && P1Y == M1Y);
		    				boolean PosXM1 = (P1X-1 == M1X && P1Y == M1Y);
		    				boolean PosYP1 = (P1X == M1X && P1Y+1 == M1Y);
		    				boolean PosYM1 = (P1X == M1X && P1Y-1 == M1Y);
		    				boolean PosXYP1 = (P1X+1 == M1X && P1Y+1 == M1Y);
		    				boolean PosXYM1 = (P1X-1 == M1X && P1Y-1 == M1Y);
		    				boolean PosXPYM1 = (P1X+1 == M1X && P1Y-1 == M1Y);
		    				boolean PosXMYP1 = (P1X-1 == M1X && P1Y+1 == M1Y);
		    				if(PosEgal || PosXP1 || PosXM1 || PosYP1 || PosYM1 || PosXYP1 || PosXYM1 || PosXPYM1 || PosXMYP1)
		    				{
				    			@SuppressWarnings("unused")
								Monster M = CurentEntityOnClic.getProperties().getValue("Mosnter1");
				    			
				    			PlayerAttack(getPlayer(),CurentEntityOnClic);
				    			
				    			/*
				    			int mhp = M.getStat().getCurrentHP();
					    			mhp--;
					    			M.getStat().setCurrentHP(mhp);
					    			println("HP : " + mhp);
					    			
					    			if(M.getStat().getCurrentHP() <= 0)
					    			{
					    				CurrentBattle.removeFromWorld();
					    				println("Le " + M.getNom() + " Est mort");
					    				CurrentBattle = null;
					    			}*/
					    			
				    		}
		    			}
	    			}
    			}
    			
    			
    			for(int i = 0; i < nbrChest; i++)
    			{
    				
	    			Entity CurentEntityOnClic = getGameWorld().getEntitiesByType(CHEST).get(i);
    				
	    			int xe = (int)CurentEntityOnClic.getX();
	    			int ye = (int)CurentEntityOnClic.getY();
	    			
	    			if(xe/80 == x/80 && ye/80 == y/80)
	    			{
			    		if(CurentEntityOnClic.getType() == CHEST)
			    		{		
			    				
			    		
			    				{
			    					CurentEntityOnClic.removeFromWorld();
			    					nbrChest--;
			    					
			    					
			    					AStarCell c = new AStarCell((int)(xe/80), (int)(ye/80), CellState.WALKABLE);
			    					
			    					AStarGrid grid = geto("grid");
			    					grid.set(xe/80,ye/80,c);
			    					
			    					set("grid", grid);
			    					
			    					Joueur P1 = getPlayer().getProperties().getValue("Joueur1");
			    					FXGL.getNotificationService().pushNotification(P1.getNom() + " vient de trouver une épée Légendaire : Excalibur" );
			    					FXGL.getNotificationService().pushNotification(P1.getNom() + " vient de trouver 200 gold !");
			        			}
			    				
			    			
	    			
			    		}
	    			}
    			//println("x" +  x);
    			//println("x" +  y);
    			}

    			
    		}
    	}, MouseButton.PRIMARY);
    	
    	   	
    	getInput().addAction(new UserAction("Attack") {
    		@Override
    		protected void onActionBegin() {
    			
    			
    			
    			
    			if(OnCollisionWithMonster)
    			{
    				OnCollisionWithMonster = false;
    			}
    			else
    			{
    				OnCollisionWithMonster = true;
    			}
    			
    			
    		}
    	}, KeyCode.I);
    
    	
    	
    	
    	
    	
    	
	}
    
	public static void println(String T)
	{
		System.out.println(T);
	}

    protected void initPhysics() {
    	onCollision(PLAYER, ESCALIER, (p,e) -> 
    	FLOOR = getPlayerComponent().teleport(++FLOOR,BLOCK_SIZE,MAP_SIZE_PRINT)
    	);
    	/*
    	onCollision(PLAYER, MONSTER, (p,m) -> {
    		while(m.isActive())
    		{
    			println("" + m.distance(p));
    		}
    	});
    	*/
    	onCollisionBegin(PLAYER, MONSTER, (p,m) -> {
    		OnCollisionWithMonster = true;
    		CurrentBattle = m;
    		PlayerCombat = p;
    	});
    	onCollisionEnd(PLAYER, MONSTER, (p,m) -> {
    		OnCollisionWithMonster = false;
    		CurrentBattle = null;
    	});

    	/*
 	    onCollisionBegin(PLAYER, MONSTER, (p,m) -> {
 	    	//if(OnCollisionWithMonster)
	    	{
 	    		//Component f = p.getComponents().get(7);
 	    		 	    		
 	    		//ArrayList<Entity> AllEntity = getGameWorld().getEntities();
 	    		
 	    		
 	    		int nAllEntity = AllEntity.size();
 	    		
 	    		for(int i = 0; i < nAllEntity; i++)
 	    		{
 	    			if(AllEntity.get(i).equals(m))
 	    			println("Monster");
 	    		}
 	    		
 	    		
 	    		Monster M = m.getProperties().getValue("Mosnter1");
 	    		
 	    		int XC = (int)(LAST_CLICK.getX()/BLOCK_SIZE);
 	    		int YC = (int)(LAST_CLICK.getY()/BLOCK_SIZE);
 	    		
 	    		
 	    		int XM = (int)(m.getX()/BLOCK_SIZE);
 	    		int YM = (int)(m.getY()/BLOCK_SIZE);
 	    		
 	    		
 	    		
 	    		if(XC == XM && YC == YM)
 	    		{
 	    			int mhp = M.getStat().getCurrentHP();
 	    			mhp--;
 	    			M.getStat().setCurrentHP(mhp);
 	    			if(M.getStat().getCurrentHP() <= 0)
 	    			{
 	    				m.removeFromWorld();
 	    			}
 	    		}
 	    		
 	    		println("XC = " +  XC);
 	    		println("XM = " +  XM);
 	    		println("YC = " +  YC);
 	    		println("YM = " +  YM);
 	    		
 	    		
 	    		
 	    		
 	    		
 	    		//String g = f.getEntity().getClass();
 	    		
 	    		//println(g);
 	    		
 	    		//m.removeFromWorld();
 	    		
 	    		//m.translateX(80);
 	    		
 	    		//m.setVisible(false);
 	    		
 	    		
 	    		getPlayerComponent().teleport(2,BLOCK_SIZE,MAP_SIZE);
 	    		OnCollisionWithMonster = false;
 	    		
	    	}
    		
 	    }); 
    	*/
    	
    	
    }
    
    // Gestion Obligatoire d'une variable de temps || Cherche solution pour s'en passer

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("time", TIME_PER_LEVEL);
    }
    
    public void  TeleportNextFloor()
    {
    	
    }
    
    public void PlayerAttack(Entity P,Entity Mo)
    {
    	Joueur J = P.getProperties().getValue("Joueur1");
    	Monster M = Mo.getProperties().getValue("Mosnter1");
    	
    	int MX = (int) Mo.getX();
    	int MY = (int) Mo.getY();
    	
    	int PuissanceAtk = (int)(J.getStat().getCurrentATK());
    	int DegatSubit = (int) (PuissanceAtk - ((M.getStat().getCurrentDEF())/2));
    	
    	
    	if(DegatSubit <= 0)
    	{
    		DegatSubit = 0;
    	}
    	else
    	{
    	M.getStat().setCurrentHP(M.getStat().getCurrentHP()-(DegatSubit));
    	}
    	println("Degat Subit : " + DegatSubit);
    	
    	println("HP : " + M.getStat().getCurrentHP());
    	
    	if (M.getStat().getCurrentHP() <= 0)
    	{
    		Mo.removeFromWorld();
    		J.getLv().addXP(M.getDroppedXP());
			println("Le " + M.getNom() + " Est mort");
			CurrentBattle = null;
			
			//MX = (MX - (MX)); //  - (MX - (BLOCK_SIZE) % BLOCK_SIZE))
			//MY = (MX - (MY)); // ((MX-(BLOCK_SIZE))%BLOCK_SIZE ))
			
			AStarCell c = new AStarCell((int)(MX/80), (int)(MY/80), CellState.NOT_WALKABLE);
			//spawn("C", ((MX - (BLOCK_SIZE / 2)) - (MX - (BLOCK_SIZE) % BLOCK_SIZE)), ((MX-(BLOCK_SIZE/2)) - ((MX-(BLOCK_SIZE))%BLOCK_SIZE )));

			MX = (int)(MX - (MX%80));
			MY = (int)(MY - (MY%80));
			@SuppressWarnings("unused")
			Entity CC = spawn("C",MX, MY);
			//CC.translateX(-40);
			//CC.translateY(-40);
			println("MX " + MX);
			println("MY " + MY);
			MX = (int)(MX/BLOCK_SIZE);
			MY = (int)(MY/BLOCK_SIZE);
			AStarGrid grid = geto("grid");
			grid.set(MX,MY,c);
			
			set("grid", grid);
			
			int nbr = geti("nbrMob");
			nbr--;
			set("nbrMob",nbr);

			
			//NotificationView N1 = null;
			//N1.setBackgroundColor(javafx.scene.paint.Color.rgb(39, 56, 58));
			//N1.setTextColor(javafx.scene.paint.Color.RED);
			
			/*
			FXGL.getNotificationService().setBackgroundColor(javafx.scene.paint.Color.rgb(39, 56, 58));
			FXGL.getNotificationService().setTextColor(Color.RED);
			FXGL.getNotificationService().onInit();
			FXGL.getNotificationService().pushNotification(J.getNom() + " vient de tuer " + M.getNom());
			FXGL.getNotificationService().pushNotification(J.getNom() + " gagne 100 point d'XP");
			*/
			
			
    	}
    	
    }


    @Override
    protected void initGame() {
        getGameScene().setBackgroundColor(Color.DARKSLATEGREY); // Fond de la carte
       


        getGameWorld().addEntityFactory(new GameFactory()); // Ajout des "classe, joueur, mur et autre"
        
        
        final String chemin = ""; // Chemin à remplir
        final File fichier =new File(chemin); 
        CreatAlea M=new CreatAlea();
        try {
          // fichier .createNewFile();// Creation du fichier
            final FileWriter writer = new FileWriter(fichier);// creation d'un writer (un écrivain)
            
            	
                writer.write("111111111111111111111\n");
               for(int i=0;i<19;i++) {
            	   System.out.println(i);
            	   writer.write("1"+M.ligne(i)+"1\n");
               }
               writer.write("1111111111111E1111111\n");
            
                writer.close();// quoiqu'il arrive, on ferme le fichier
            
        } catch (Exception e) {
            System.out.println("Impossible de creer le fichier");
        }
        

        Level level = getAssetLoader().loadLevel("map_level0.txt", new TextLevelLoader(80, 80, ' ')); // Instanciation de la carte
        getGameWorld().setLevel(level);        

        AStarGrid grid = AStarGrid.fromWorld(getGameWorld(), MAP_SIZE_REEL, MAP_SIZE_REEL, BLOCK_SIZE, BLOCK_SIZE, (type) -> { // Instanciation de la grille
            if (type == BLOCK)
                return CellState.NOT_WALKABLE;

            return CellState.WALKABLE;
        });
        
        

        set("grid", grid);
        
        PlayerCombat = null;
        
        PlayerCombat = getPlayer();
        
        Viewport viewport = getGameScene().getViewport();
        viewport.bindToEntity(getGameWorld().getSingleton(PLAYER), getAppWidth()/2,getAppHeight()/2);
        
        
        //viewport.bindToEntity(getGameWorld().getSingleton(MONSTER), getAppWidth()/2,getAppHeight()/2);
        
        viewport.setBounds(0, 0, MAP_SIZE_REEL * BLOCK_SIZE, MAP_SIZE_REEL * BLOCK_SIZE);

        //run(() -> inc("time", -1), Duration.seconds(1));

        
    }

    public static void main(String[] args) {
        launch(args);
       
    }
}
