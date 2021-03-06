package other.components;

import static com.almasb.fxgl.dsl.FXGL.getDialogService;
import static com.almasb.fxgl.dsl.FXGL.getGameController;
import static com.almasb.fxgl.dsl.FXGL.geto;
import static com.almasb.fxgl.dsl.FXGL.set;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.component.Required;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;

import GUI.InGameController;
import entity.Joueur;
import entity.Monster;
import entity.MonsterType;
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

		if(M.getState() == MonsterType.RENARD || M.getState() == MonsterType.BAT || M.getState() == MonsterType.LEZARD)
		{
			if(x > -3 && x < 3 && y > -3 && y < 3)
	        {
				

	        	
	        	move(player);

	            timeToSwitch += tpf;
	            
	            float RandomTimerAtk2 = astar.getEntity().getProperties().getValue("CADofMonster");

	            
	            if (timeToSwitch >= RandomTimerAtk2) {
	            	MonsterAttack(astar.getEntity(),player);
	                timeToSwitch = 0;
	            }

	        	
	        	
	        	
	        }
		}
		else if(M.getState() == MonsterType.DRAGONBOSS)
		{
			if(x > -7 && x < 7 && y > -7 && y < 7)
	        {
				move(player);
				
				
				timeToSwitch += tpf;
	            
	            float RandomTimerAtk2 = astar.getEntity().getProperties().getValue("CADofMonster");

	            
	            if (timeToSwitch >= RandomTimerAtk2) {
	            	BossDragonAttack(astar.getEntity(),player);
	                timeToSwitch = 0;
	            }
	        }

		}
        
    	 
    }
    


private void move(Entity R) {
    	
	Monster M = astar.getEntity().getProperties().getValue("Mosnter1");
	
        if(!astar.isMoving() && (M.getState() == MonsterType.BAT || M.getState() == MonsterType.RENARD || M.getState() == MonsterType.LEZARD))
        {
        	Entity P = FXGL.getGameWorld().getSingleton(GameType.PLAYER);
        	
	    	int px = (int) P.getX()/80;
	    	int py = (int) P.getY()/80;
        	
	    	boolean GridOfAllEntityOnWorld [][] = geto("GridOfAllEntityOnWorld");
	    	
	    	int xp = (int) R.getX()/80;
	    	int yp = (int) R.getY()/80;

	    	Entity EntityCurrentMove = astar.getEntity();
	    	
	    	int x = (int) EntityCurrentMove.getX()/80;
	    	int y = (int) EntityCurrentMove.getY()/80;
	    	
	    	//println("type : " + EntityCurrentMove.getType());
	    	//println("x : " + x);
	    	//println("y : " + y);
	    	
	        boolean CanMoveRight = !GridOfAllEntityOnWorld[x+1][y];
	        boolean CanMoveLeft = !GridOfAllEntityOnWorld[x-1][y];
	        boolean CanMoveUp = !GridOfAllEntityOnWorld[x][y-1];
	        boolean CanMoveDown = !GridOfAllEntityOnWorld[x][y+1];
	        
	        
	        if(x+1 == px && y == py)
	        {
	        	CanMoveRight = false;
	        }
	        if(x-1 == px && y == py)
	        {
	        	CanMoveLeft = false;
	        }
	        if(x == px && y-1 == py)
	        {
	        	CanMoveUp = false;
	        }
	        if(x == px && y+1 == py)
	        {
	        	CanMoveDown = false;
	        }
	        
	        
	        boolean NeedMoveToRight = false;
	        boolean NeedMoveToLeft = false;
	        boolean NeedMoveToUp = false;
	        boolean NeedMoveToDown = false;
	        
			int RisRight = Math.abs((xp+1) - x);
			int RisLeft =  Math.abs((xp-1) - x);
			int RisUp =    Math.abs((yp-1) - y);
			int RisDown =  Math.abs((yp+1) - y);
    	
			//int RisXVerif = Math.abs((xp) - x);
			//int RisYVerif = Math.abs((yp) - y);
			
			//println("RisRight : " + RisRight);
			//println("RisLeft : " + RisLeft);
			//println("RisUp : " + RisUp);
			//println("RisDown : " + RisDown);
			
			//println("RisXVerif : " + RisXVerif);
			//println("RisYVerif : " + RisYVerif);

			if(RisRight >= RisLeft && RisRight >= RisUp && RisRight >= RisDown)
			{
				NeedMoveToRight = true;
				//println("NeedMoveToRight");
				
			}
			
			if(RisLeft >= RisRight && RisLeft >= RisUp && RisLeft >= RisDown)
			{
				NeedMoveToLeft = true;
				//println("NeedMoveToLeft");
			}
			
			if(RisUp >= RisRight && RisUp >= RisLeft && RisUp >= RisDown)
			{
				NeedMoveToUp = true;
				//println("NeedMoveToUp");
			}
			
			if(RisDown >= RisRight && RisDown >= RisUp && RisDown >= RisLeft)
			{
				NeedMoveToDown = true;
				//println("NeedMoveToDown");
			}
			
			//println("--------------");

	        //int ValMax = 4;
			//int ValMin = 1;
			//int randomMove = (int)(Math.random() * (ValMax - ValMin) + ValMin + 1);
			if(CanMoveLeft && NeedMoveToLeft) 
			{
				astar.moveToCell(x-1, y);
				GridOfAllEntityOnWorld[x][y] = false;
				GridOfAllEntityOnWorld[x-1][y] = true;
			}
			else if(CanMoveRight && NeedMoveToRight)
			{
				astar.moveToCell(x+1, y);
				GridOfAllEntityOnWorld[x][y] = false;
				GridOfAllEntityOnWorld[x+1][y] = true;
			}
			else if(CanMoveUp && NeedMoveToUp)
			{
				astar.moveToCell(x, y-1);
				GridOfAllEntityOnWorld[x][y] = false;
				GridOfAllEntityOnWorld[x][y-1] = true;
			}
			else if(CanMoveDown && NeedMoveToDown)
			{
				astar.moveToCell(x, y+1);
				GridOfAllEntityOnWorld[x][y] = false;
				GridOfAllEntityOnWorld[x][y+1] = true;
			}
			else
			{
				
			}
	    	
			set("GridOfAllEntityOnWorld",GridOfAllEntityOnWorld);
        }
        else if(!astar.isMoving() && M.getState() == MonsterType.DRAGONBOSS )
        {
        	Entity P = FXGL.getGameWorld().getSingleton(GameType.PLAYER);
        	
	    	int px = (int) P.getX()/80;
	    	int py = (int) P.getY()/80;
        	
	    	boolean GridOfAllEntityOnWorld [][] = geto("GridOfAllEntityOnWorld");
	    	
	    	int xp = (int) R.getX()/80;
	    	int yp = (int) R.getY()/80;

	    	Entity EntityCurrentMove = astar.getEntity();
	    	
	    	int x = (int) EntityCurrentMove.getX()/80;
	    	int y = (int) EntityCurrentMove.getY()/80;
	    	
	    	//println("type : " + EntityCurrentMove.getType());
	    	//println("x : " + x);
	    	//println("y : " + y);
	    	
	        boolean CanMoveRight = !GridOfAllEntityOnWorld[x+1][y];
	        boolean CanMoveLeft = !GridOfAllEntityOnWorld[x-1][y];
	        boolean CanMoveUp = !GridOfAllEntityOnWorld[x][y-1];
	        boolean CanMoveDown = !GridOfAllEntityOnWorld[x][y+1];
	        boolean DontMove = false;
	        
	        
	        if(x+1 == px && y == py || x+1 == px && y+1 == py)
	        {
	        	CanMoveRight = false;
	        }
	        if(x-1 == px && y == py || x-1 == px && y-1 == py)
	        {
	        	CanMoveLeft = false;
	        }
	        if(x == px && y-1 == py || x+1 == px && y-1 == py)
	        {
	        	CanMoveUp = false;
	        }
	        if(x == px && y+1 == py || x-1 == px && y+1 == py)
	        {
	        	CanMoveDown = false;
	        }
	        if(x == px && y+2 == py || x-1 == px && y+2 == py || x+1 == px && y+2 == py)
	        {
	        	DontMove = true;
	        }
	        if(x == px && y-2 == py || x-1 == px && y-2 == py || x+1 == px && y-2 == py)
	        {
	        	DontMove = true;
	        }
	        if(x+2 == px && y == py || x+2 == px && y-1 == py || x+2 == px && y+1 == py)
	        {
	        	DontMove = true;
	        }
	        if(x-2 == px && y == py || x-2 == px && y-1 == py || x-2 == px && y+1 == py)
	        {
	        	DontMove = true;
	        }
	        
	        
	        boolean NeedMoveToRight = false;
	        boolean NeedMoveToLeft = false;
	        boolean NeedMoveToUp = false;
	        boolean NeedMoveToDown = false;
	        
			int RisRight = Math.abs((xp+1) - x);
			int RisLeft =  Math.abs((xp-1) - x);
			int RisUp =    Math.abs((yp-1) - y);
			int RisDown =  Math.abs((yp+1) - y);
    	
			//int RisXVerif = Math.abs((xp) - x);
			//int RisYVerif = Math.abs((yp) - y);
			
			//println("RisRight : " + RisRight);
			//println("RisLeft : " + RisLeft);
			//println("RisUp : " + RisUp);
			//println("RisDown : " + RisDown);
			
			//println("RisXVerif : " + RisXVerif);
			//println("RisYVerif : " + RisYVerif);

			if(RisRight >= RisLeft && RisRight >= RisUp && RisRight >= RisDown)
			{
				NeedMoveToRight = true;
				//println("NeedMoveToRight");
				
			}
			
			if(RisLeft >= RisRight && RisLeft >= RisUp && RisLeft >= RisDown)
			{
				NeedMoveToLeft = true;
				//println("NeedMoveToLeft");
			}
			
			if(RisUp >= RisRight && RisUp >= RisLeft && RisUp >= RisDown)
			{
				NeedMoveToUp = true;
				//println("NeedMoveToUp");
			}
			
			if(RisDown >= RisRight && RisDown >= RisUp && RisDown >= RisLeft)
			{
				NeedMoveToDown = true;
				//println("NeedMoveToDown");
			}
			
			//println("--------------");

	        //int ValMax = 4;
			//int ValMin = 1;
			//int randomMove = (int)(Math.random() * (ValMax - ValMin) + ValMin + 1);
			if(DontMove)
			{
				
			}
			else if(CanMoveLeft && NeedMoveToLeft) 
			{
				astar.moveToCell(x-2, y);
				GridOfAllEntityOnWorld[x][y] = false;
				GridOfAllEntityOnWorld[x-2][y] = true;
			}
			else if(CanMoveRight && NeedMoveToRight)
			{
				astar.moveToCell(x+2, y);
				GridOfAllEntityOnWorld[x][y] = false;
				GridOfAllEntityOnWorld[x+2][y] = true;
			}
			else if(CanMoveUp && NeedMoveToUp)
			{
				astar.moveToCell(x, y-2);
				GridOfAllEntityOnWorld[x][y] = false;
				GridOfAllEntityOnWorld[x][y-2] = true;
			}
			else if(CanMoveDown && NeedMoveToDown)
			{
				astar.moveToCell(x, y+2);
				GridOfAllEntityOnWorld[x][y] = false;
				GridOfAllEntityOnWorld[x][y+2] = true;
			}
			else
			{
				
			}
	    	
			set("GridOfAllEntityOnWorld",GridOfAllEntityOnWorld);
        }
        
        		
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
			int PuissanceAtk = (int)(M.getStat().getCurrentATK());
	    	int DegatSubit = (int) (PuissanceAtk - ((J.getStat().getCurrentDEF())/2))+1; 
	    	
	    	println("Degat Subit : " + DegatSubit);
	    	if(DegatSubit < 0)
	    	{
	    		DegatSubit = 0;
	    	}
	    	J.getStat().setCurrentHP(J.getStat().getCurrentHP()-(DegatSubit));
	    	
	    	println("HP lol : " + J.getStat().getCurrentHP());
	    	
	    	if (J.getStat().getCurrentHP() <= 0)
	    	{
				println("Le " + J.getNom() + " Est mort");
				getDialogService().showMessageBox("Game Over", getGameController()::exit);
	    		//P.removeFromWorld();
	    		InGameController.scheduledExecutorService.shutdown();
	    	}
		}
	    	
		
		
    }
    
    public void BossDragonAttack(Entity Mo,Entity P)
    {
    	Joueur J = P.getProperties().getValue("Joueur1");
    	Monster M = Mo.getProperties().getValue("Mosnter1");
    	
		int px = (int)(P.getX()/80);
		int py = (int)(P.getY()/80);
		int mx = (int)(Mo.getX()/80);
		int my = (int)(Mo.getY()/80);
		int Distance = 3;
		if(Math.abs(px - mx) < Distance && Math.abs(py - my) < Distance)
		{
    	
			int PuissanceAtk = (int)(M.getStat().getCurrentATK());
	    	int DegatSubit = (int) (PuissanceAtk - ((J.getStat().getCurrentDEF())/2))+1; 
	    	
	    	println("Degat Subit : " + DegatSubit);
	    	if(DegatSubit < 0)
	    	{
	    		DegatSubit = 0;
	    	}
	    	J.getStat().setCurrentHP(J.getStat().getCurrentHP()-(DegatSubit));
	    	
	    	println("HP lol : " + J.getStat().getCurrentHP());
	    	
	    	if (J.getStat().getCurrentHP() <= 0)
	    	{
				println("Le " + J.getNom() + " Est mort");
				getDialogService().showMessageBox("Game Over", getGameController()::exit);
	    		//P.removeFromWorld();
	    		InGameController.scheduledExecutorService.shutdown();
	    	}
		}
	    	
		
		
    }
    
	public static void println(String T)
	{
		System.out.println(T);
	}
	





	
}
