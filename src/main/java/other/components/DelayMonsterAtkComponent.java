package other.components;

import static com.almasb.fxgl.dsl.FXGL.getDialogService;
import static com.almasb.fxgl.dsl.FXGL.getGameController;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.component.Required;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;

import entity.Joueur;
import entity.Monster;
import game.and.map.GameType;



@Required(AStarMoveComponent.class)
public class DelayMonsterAtkComponent extends Component {

    private AStarMoveComponent astar;
    
    boolean isDelayed = false;
    private double timeToSwitch = 0;
   
    

    @Override
    public void onUpdate(double tpf) {
       /*
    	if (!isDelayed) {
            move();
        } else {

            
            if (astar.isAtDestination()) {
                move();
            }
        }
        */
        var player = FXGL.getGameWorld().getSingleton(GameType.PLAYER);

        int xp = player.call("getCellX");
        int yp = player.call("getCellY");
        
        int xm = (int) astar.getEntity().getX()/80;
        int ym = (int) astar.getEntity().getY()/80;
    	
        int x = xm - xp;
        int y = ym - yp;
        
        //println("xm : " + x);
        //println("ym : " + y);
        
		@SuppressWarnings("unused")
		Joueur P1 = player.getProperties().getValue("Joueur1");

		@SuppressWarnings("unused")
		Monster M = astar.getEntity().getProperties().getValue("Mosnter1");
			
			
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
    			}
    			*/
        
        if(x > -3 && x < 3 && y > -3 && y < 3)
        {
        	/*
        	int MX = (int) astar.getEntity().getX();
        	int MY = (int) astar.getEntity().getY();
        	
        	AStarCell c = new AStarCell((int)(MX/80), (int)(MY/80), CellState.WALKABLE);
			AStarGrid grid = geto("grid");
			grid.set((int)(MX/80), (int)(MY/80),c);
			//println("MX " + (int)(MX/80));
			//println("MY " + (int)(MY/80));
			
			set("grid", grid);
        	*/
        	
        	move();
        	/*
			c = new AStarCell((int)(MX/80), (int)(MY/80), CellState.NOT_WALKABLE);
			grid = geto("grid");
			grid.set((int)(MX/80), (int)(MY/80),c);
			//println("MX " + (int)(MX/80));
			//println("MY " + (int)(MY/80));
			
			set("grid", grid);
        	*/
        	/*
        	int ValMax = 40;
        	int ValMin = 5;
        	
        	int RandomTimerAtk = (int)(Math.random() * (ValMax - ValMin) + ValMin + 1);
        	double RandomTimerAtk2 = (double) RandomTimerAtk * 0.1;
        	*/
        	
            timeToSwitch += tpf;
            
            double RandomTimerAtk2 = 2.0;

            
            if (timeToSwitch >= RandomTimerAtk2) {
            	MonsterAttack(astar.getEntity(),player);
                timeToSwitch = 0;
            }

        	
        	
        	
        }
    	 
    }

    private void move() {
        var player = FXGL.getGameWorld().getSingleton(GameType.PLAYER);

        int x = player.call("getCellX");
        int y = player.call("getCellY");

        astar.moveToCell(x, y);
    }

    public DelayMonsterAtkComponent withDelay() {
        isDelayed = true;
        return this;
    }
    
    public void MonsterAttack(Entity Mo,Entity P)
    {
    	Joueur J = P.getProperties().getValue("Joueur1");
    	Monster M = Mo.getProperties().getValue("Mosnter1");
    	
		int P1X = (int)(P.getX()/80);
		int P1Y = (int)(P.getY()/80);
		int M1X = (int)(Mo.getX()/80);
		int M1Y = (int)(Mo.getY()/80);
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
			int PuissanceAtk = (int)(M.getStat().getCurrentATK() * (0.5 * 2) *(0.5 * 2));
	    	int DegatSubit = 1; // (int) (PuissanceAtk - (J.getStat().getCurrentDEF() * 1.2));
	    	
	    	println("Degat Subit : " + DegatSubit);
	    	
	    	J.getStat().setCurrentHP(J.getStat().getCurrentHP()-(DegatSubit));
	    	
	    	println("HP : " + J.getStat().getCurrentHP());
	    	
	    	if (J.getStat().getCurrentHP() <= 0)
	    	{
				println("Le " + J.getNom() + " Est mort");
	    		P.removeFromWorld();
	    		getDialogService().showMessageBox("Game Over", getGameController()::exit);
	    	}
		}
	    	
    }
    
	public static void println(String T)
	{
		System.out.println(T);
	}
	




	

}
