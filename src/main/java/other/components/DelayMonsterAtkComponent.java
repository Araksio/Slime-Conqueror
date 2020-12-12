package other.components;

/**
 * Classe qui comprend l'inteligence artificielle des monstre
 * @author Pitohui.G7 
 *
 */

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
	private double timeToSwitch = 0; // variable qui gère le temps en mp

	@Override
	public void onUpdate(double tpf) {

		var player = FXGL.getGameWorld().getSingleton(GameType.PLAYER); // récupère le joueur

		// recupere les coordonée du joueur
		int xp = player.call("getCellX");
		int yp = player.call("getCellY");

		// recupere les coordonée du monstre
		int xm = (int) astar.getEntity().getX() / 80;
		int ym = (int) astar.getEntity().getY() / 80;

		// calcul la distance entre le monstre et le joueur
		int x = xm - xp;
		int y = ym - yp;

		Joueur P1 = player.getProperties().getValue("Joueur1"); // recupère l'entité Joueur

		Monster M = astar.getEntity().getProperties().getValue("Mosnter1"); // recupère l'entité monstre

		if (M.getState() == MonsterType.RENARD || M.getState() == MonsterType.BAT || M.getState() == MonsterType.LEZARD) // verifie
																															// le
																															// type
																															// du
																															// monstre
		{
			if (x > -3 && x < 3 && y > -3 && y < 3) // verifie que le monstre sois a moin de 3 bloc du joueur
			{

				move(player); // ce dirige vers le joueur

				timeToSwitch += tpf; // incrémente de 1 toute les milliseconde

				float RandomTimerAtk2 = astar.getEntity().getProperties().getValue("CADofMonster"); // recupère la
																									// cadence d'atk du
																									// monstre

				if (timeToSwitch >= RandomTimerAtk2) { // si le temps d'attente depasse la cadence alors le monstre
														// attaque

					MonsterAttack(astar.getEntity(), player);
					timeToSwitch = 0;
				}

			}
		} else if (M.getState() == MonsterType.DRAGONBOSS) {
			if (x > -7 && x < 7 && y > -7 && y < 7) {
				move(player);

				timeToSwitch += tpf;

				float RandomTimerAtk2 = astar.getEntity().getProperties().getValue("CADofMonster");

				if (timeToSwitch >= RandomTimerAtk2) {
					BossDragonAttack(astar.getEntity(), player);
					timeToSwitch = 0;
				}
			}

		}

	}

// Fonction qui recherche le joueur pour ce diriger vers lui

	private void move(Entity R) {

		Monster M = astar.getEntity().getProperties().getValue("Mosnter1");

		if (!astar.isMoving() && (M.getState() == MonsterType.BAT || M.getState() == MonsterType.RENARD
				|| M.getState() == MonsterType.LEZARD)) {
			Entity P = FXGL.getGameWorld().getSingleton(GameType.PLAYER);

			int px = (int) P.getX() / 80;
			int py = (int) P.getY() / 80;

			boolean GridOfAllEntityOnWorld[][] = geto("GridOfAllEntityOnWorld");

			int xp = (int) R.getX() / 80;
			int yp = (int) R.getY() / 80;

			Entity EntityCurrentMove = astar.getEntity();

			int x = (int) EntityCurrentMove.getX() / 80;
			int y = (int) EntityCurrentMove.getY() / 80;

			boolean CanMoveRight = !GridOfAllEntityOnWorld[x + 1][y];
			boolean CanMoveLeft = !GridOfAllEntityOnWorld[x - 1][y];
			boolean CanMoveUp = !GridOfAllEntityOnWorld[x][y - 1];
			boolean CanMoveDown = !GridOfAllEntityOnWorld[x][y + 1];

			if (x + 1 == px && y == py) {
				CanMoveRight = false;
			}
			if (x - 1 == px && y == py) {
				CanMoveLeft = false;
			}
			if (x == px && y - 1 == py) {
				CanMoveUp = false;
			}
			if (x == px && y + 1 == py) {
				CanMoveDown = false;
			}

			boolean NeedMoveToRight = false;
			boolean NeedMoveToLeft = false;
			boolean NeedMoveToUp = false;
			boolean NeedMoveToDown = false;

			int RisRight = Math.abs((xp + 1) - x);
			int RisLeft = Math.abs((xp - 1) - x);
			int RisUp = Math.abs((yp - 1) - y);
			int RisDown = Math.abs((yp + 1) - y);

			if (RisRight >= RisLeft && RisRight >= RisUp && RisRight >= RisDown) {
				NeedMoveToRight = true;

			}

			if (RisLeft >= RisRight && RisLeft >= RisUp && RisLeft >= RisDown) {
				NeedMoveToLeft = true;

			}

			if (RisUp >= RisRight && RisUp >= RisLeft && RisUp >= RisDown) {
				NeedMoveToUp = true;

			}

			if (RisDown >= RisRight && RisDown >= RisUp && RisDown >= RisLeft) {
				NeedMoveToDown = true;

			}

			if (CanMoveLeft && NeedMoveToLeft) {
				astar.moveToCell(x - 1, y);
				GridOfAllEntityOnWorld[x][y] = false;
				GridOfAllEntityOnWorld[x - 1][y] = true;
			} else if (CanMoveRight && NeedMoveToRight) {
				astar.moveToCell(x + 1, y);
				GridOfAllEntityOnWorld[x][y] = false;
				GridOfAllEntityOnWorld[x + 1][y] = true;
			} else if (CanMoveUp && NeedMoveToUp) {
				astar.moveToCell(x, y - 1);
				GridOfAllEntityOnWorld[x][y] = false;
				GridOfAllEntityOnWorld[x][y - 1] = true;
			} else if (CanMoveDown && NeedMoveToDown) {
				astar.moveToCell(x, y + 1);
				GridOfAllEntityOnWorld[x][y] = false;
				GridOfAllEntityOnWorld[x][y + 1] = true;
			} else {

			}

			set("GridOfAllEntityOnWorld", GridOfAllEntityOnWorld);
		} else if (!astar.isMoving() && M.getState() == MonsterType.DRAGONBOSS) {
			Entity P = FXGL.getGameWorld().getSingleton(GameType.PLAYER);

			int px = (int) P.getX() / 80;
			int py = (int) P.getY() / 80;

			boolean GridOfAllEntityOnWorld[][] = geto("GridOfAllEntityOnWorld");

			int xp = (int) R.getX() / 80;
			int yp = (int) R.getY() / 80;

			Entity EntityCurrentMove = astar.getEntity();

			int x = (int) EntityCurrentMove.getX() / 80;
			int y = (int) EntityCurrentMove.getY() / 80;

			boolean CanMoveRight = !GridOfAllEntityOnWorld[x + 1][y];
			boolean CanMoveLeft = !GridOfAllEntityOnWorld[x - 1][y];
			boolean CanMoveUp = !GridOfAllEntityOnWorld[x][y - 1];
			boolean CanMoveDown = !GridOfAllEntityOnWorld[x][y + 1];
			boolean DontMove = false;

			if (x + 1 == px && y == py || x + 1 == px && y + 1 == py) {
				CanMoveRight = false;
			}
			if (x - 1 == px && y == py || x - 1 == px && y - 1 == py) {
				CanMoveLeft = false;
			}
			if (x == px && y - 1 == py || x + 1 == px && y - 1 == py) {
				CanMoveUp = false;
			}
			if (x == px && y + 1 == py || x - 1 == px && y + 1 == py) {
				CanMoveDown = false;
			}
			if (x == px && y + 2 == py || x - 1 == px && y + 2 == py || x + 1 == px && y + 2 == py) {
				DontMove = true;
			}
			if (x == px && y - 2 == py || x - 1 == px && y - 2 == py || x + 1 == px && y - 2 == py) {
				DontMove = true;
			}
			if (x + 2 == px && y == py || x + 2 == px && y - 1 == py || x + 2 == px && y + 1 == py) {
				DontMove = true;
			}
			if (x - 2 == px && y == py || x - 2 == px && y - 1 == py || x - 2 == px && y + 1 == py) {
				DontMove = true;
			}

			boolean NeedMoveToRight = false;
			boolean NeedMoveToLeft = false;
			boolean NeedMoveToUp = false;
			boolean NeedMoveToDown = false;

			int RisRight = Math.abs((xp + 1) - x);
			int RisLeft = Math.abs((xp - 1) - x);
			int RisUp = Math.abs((yp - 1) - y);
			int RisDown = Math.abs((yp + 1) - y);

			if (RisRight >= RisLeft && RisRight >= RisUp && RisRight >= RisDown) {
				NeedMoveToRight = true;

			}

			if (RisLeft >= RisRight && RisLeft >= RisUp && RisLeft >= RisDown) {
				NeedMoveToLeft = true;

			}

			if (RisUp >= RisRight && RisUp >= RisLeft && RisUp >= RisDown) {
				NeedMoveToUp = true;

			}

			if (RisDown >= RisRight && RisDown >= RisUp && RisDown >= RisLeft) {
				NeedMoveToDown = true;

			}

			if (DontMove) {

			} else if (CanMoveLeft && NeedMoveToLeft) {
				astar.moveToCell(x - 2, y);
				GridOfAllEntityOnWorld[x][y] = false;
				GridOfAllEntityOnWorld[x - 2][y] = true;
			} else if (CanMoveRight && NeedMoveToRight) {
				astar.moveToCell(x + 2, y);
				GridOfAllEntityOnWorld[x][y] = false;
				GridOfAllEntityOnWorld[x + 2][y] = true;
			} else if (CanMoveUp && NeedMoveToUp) {
				astar.moveToCell(x, y - 2);
				GridOfAllEntityOnWorld[x][y] = false;
				GridOfAllEntityOnWorld[x][y - 2] = true;
			} else if (CanMoveDown && NeedMoveToDown) {
				astar.moveToCell(x, y + 2);
				GridOfAllEntityOnWorld[x][y] = false;
				GridOfAllEntityOnWorld[x][y + 2] = true;
			} else {

			}

			set("GridOfAllEntityOnWorld", GridOfAllEntityOnWorld);
		}

	}

	public DelayMonsterAtkComponent withDelay() {
		isDelayed = true;
		return this;
	}

	// Fonction permettant au monstre d'attaque le joueur
	public void MonsterAttack(Entity Mo, Entity P) {
		Joueur J = P.getProperties().getValue("Joueur1");
		Monster M = Mo.getProperties().getValue("Mosnter1");

		int P1X = (int) (P.getX() / 80);
		int P1Y = (int) (P.getY() / 80);
		int M1X = (int) (Mo.getX() / 80);
		int M1Y = (int) (Mo.getY() / 80);
		boolean PosEgal = (P1X == M1X && P1Y == M1Y);
		boolean PosXP1 = (P1X + 1 == M1X && P1Y == M1Y);
		boolean PosXM1 = (P1X - 1 == M1X && P1Y == M1Y);
		boolean PosYP1 = (P1X == M1X && P1Y + 1 == M1Y);
		boolean PosYM1 = (P1X == M1X && P1Y - 1 == M1Y);
		boolean PosXYP1 = (P1X + 1 == M1X && P1Y + 1 == M1Y);
		boolean PosXYM1 = (P1X - 1 == M1X && P1Y - 1 == M1Y);
		boolean PosXPYM1 = (P1X + 1 == M1X && P1Y - 1 == M1Y);
		boolean PosXMYP1 = (P1X - 1 == M1X && P1Y + 1 == M1Y);
		if (PosEgal || PosXP1 || PosXM1 || PosYP1 || PosYM1 || PosXYP1 || PosXYM1 || PosXPYM1 || PosXMYP1) {

			@SuppressWarnings("unused")
			int PuissanceAtk = (int) (M.getStat().getCurrentATK());
			int DegatSubit = (int) (PuissanceAtk - ((J.getStat().getCurrentDEF()) / 2)) + 1;

			println("Degat Subit : " + DegatSubit);
			if (DegatSubit < 0) {
				DegatSubit = 0;
			}
			J.getStat().setCurrentHP(J.getStat().getCurrentHP() - (DegatSubit));

			println("HP lol : " + J.getStat().getCurrentHP());

			if (J.getStat().getCurrentHP() <= 0) {
				println("Le " + J.getNom() + " Est mort");
				getDialogService().showMessageBox("Game Over", getGameController()::exit);

				InGameController.scheduledExecutorService.shutdown();
			}
		}

	}

	// Fonction permettant au boss d'attaque le joueur
	public void BossDragonAttack(Entity Mo, Entity P) {
		Joueur J = P.getProperties().getValue("Joueur1");
		Monster M = Mo.getProperties().getValue("Mosnter1");

		int px = (int) (P.getX() / 80);
		int py = (int) (P.getY() / 80);
		int mx = (int) (Mo.getX() / 80);
		int my = (int) (Mo.getY() / 80);
		int Distance = 3;
		if (Math.abs(px - mx) < Distance && Math.abs(py - my) < Distance) {

			int PuissanceAtk = (int) (M.getStat().getCurrentATK());
			int DegatSubit = (int) (PuissanceAtk - ((J.getStat().getCurrentDEF()) / 2)) + 1;

			println("Degat Subit : " + DegatSubit);
			if (DegatSubit < 0) {
				DegatSubit = 0;
			}
			J.getStat().setCurrentHP(J.getStat().getCurrentHP() - (DegatSubit));

			println("HP lol : " + J.getStat().getCurrentHP());

			if (J.getStat().getCurrentHP() <= 0) {
				println("Le " + J.getNom() + " Est mort");
				getDialogService().showMessageBox("Game Over", getGameController()::exit);

				InGameController.scheduledExecutorService.shutdown();
			}
		}

	}

	public static void println(String T) {
		System.out.println(T);
	}

}
