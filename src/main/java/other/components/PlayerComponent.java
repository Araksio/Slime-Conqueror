package other.components;

import static com.almasb.fxgl.dsl.FXGL.getAssetLoader;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.set;
import static game.and.map.GameType.BLOCK;
import static other.components.PlayerComponent.MoveDirection.RIGHT;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.component.Required;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.text.TextLevelLoader;
import com.almasb.fxgl.pathfinding.CellMoveComponent;
import com.almasb.fxgl.pathfinding.CellState;
import com.almasb.fxgl.pathfinding.astar.AStarGrid;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;

@Required(AStarMoveComponent.class)
public class PlayerComponent extends Component {

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
    
    public int teleport(int floor, int BLOCK_SIZE, int MAP_SIZE) {

    	String Floor = "map_level" + floor + ".txt";
    	Level level1 = getAssetLoader().loadLevel(Floor, new TextLevelLoader(80, 80, ' '));
    	
        FXGL.getGameWorld().setLevel(level1);
        
        AStarGrid grid = AStarGrid.fromWorld(getGameWorld(), MAP_SIZE, MAP_SIZE, BLOCK_SIZE, BLOCK_SIZE, (type) -> {
            if (type == BLOCK)
                return CellState.NOT_WALKABLE;

            return CellState.WALKABLE;
        });
        
        set("grid", grid);
        
        return floor;
    }
    
    
    
   

}
