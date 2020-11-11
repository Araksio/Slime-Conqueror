package other.components;

import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getAppWidth;
import static com.almasb.fxgl.dsl.FXGL.getAssetLoader;
import static com.almasb.fxgl.dsl.FXGL.getGameScene;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.set;
import static com.almasb.fxgl.dsl.FXGL.texture;
import static game.and.map.GameType.BLOCK;
import static game.and.map.GameType.MONSTER;
import static game.and.map.GameType.PLAYER;
import static other.components.PlayerComponent.MoveDirection.RIGHT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.component.Required;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.text.TextLevelLoader;
import com.almasb.fxgl.pathfinding.CellMoveComponent;
import com.almasb.fxgl.pathfinding.CellState;
import com.almasb.fxgl.pathfinding.astar.AStarGrid;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;
import com.almasb.fxgl.texture.AnimatedTexture;

import GUI.InGameController;
import entity.Stat;
import game.and.map.GameApp;
import javafx.util.Duration;

@Required(AStarMoveComponent.class)
public class PlayerComponent extends Component {
public static boolean changedMap;
    enum MoveDirection {
        UP, RIGHT, DOWN, LEFT
    }

    private CellMoveComponent moveComponent;

    private AStarMoveComponent astar;

    @SuppressWarnings("unused")
	private MoveDirection currentMoveDir = RIGHT;
    @SuppressWarnings("unused")
	private MoveDirection nextMoveDir = RIGHT;

    public void up() {
    	astar.moveToUpCell();
        //nextMoveDir = UP;
    }

    public void down() {
    	astar.moveToDownCell();
        //nextMoveDir = DOWN;
    }

    public void left() {
    	astar.moveToLeftCell();
       //nextMoveDir = LEFT;
    }

    public void right() {
    	astar.moveToRightCell();
        //nextMoveDir = RIGHT;
    }

    @Override
    public void onUpdate(double tpf) {
        @SuppressWarnings("unused")
		var x = moveComponent.getCellX();
        @SuppressWarnings("unused")
		var y = moveComponent.getCellY();
       
    }
    
    public int teleport(int floor, int BLOCK_SIZE, int MAP_SIZE) throws SQLException {

    	String Floor = "map_level" + floor + ".txt";
    	Level level1 = getAssetLoader().loadLevel(Floor, new TextLevelLoader(80, 80, ' '));
    	
        FXGL.getGameWorld().setLevel(level1);
        Viewport viewport = getGameScene().getViewport();
        viewport.bindToEntity(getGameWorld().getSingleton(PLAYER), getAppWidth()/2,getAppHeight()/2);
        viewport.setBounds(0, 0, GameApp.MAP_SIZE_REEL * BLOCK_SIZE, GameApp.MAP_SIZE_REEL * BLOCK_SIZE);
        changedMap = true;
      
        AStarGrid grid = AStarGrid.fromWorld(getGameWorld(), MAP_SIZE, MAP_SIZE, BLOCK_SIZE, BLOCK_SIZE, (type) -> {
            if (type == BLOCK)
                return CellState.NOT_WALKABLE;

            return CellState.WALKABLE;
        });
        
        set("grid", grid);
        
        Stat StatPlayer = new Stat(0, 0, 0, 0, 0, 0, 0);
		

    			
    			//partie sql java
    			

    			Connection db = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/projetpoagl?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");

    			Statement demandeRequete = db.createStatement();
        
    		
    			
  			 
    		 
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
    			
    			
        
        
        
        return floor;
    }
    
    
    
   

}
