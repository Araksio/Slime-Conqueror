package game.and.map;

import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getAppWidth;
import static com.almasb.fxgl.dsl.FXGL.getAssetLoader;
import static com.almasb.fxgl.dsl.FXGL.getDialogService;
import static com.almasb.fxgl.dsl.FXGL.getGameController;
import static com.almasb.fxgl.dsl.FXGL.getGameScene;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.getInput;
import static com.almasb.fxgl.dsl.FXGL.geti;
import static com.almasb.fxgl.dsl.FXGL.geto;
import static com.almasb.fxgl.dsl.FXGL.onCollision;
import static com.almasb.fxgl.dsl.FXGL.set;
import static com.almasb.fxgl.dsl.FXGL.spawn;
import static game.and.map.GameType.BLOCK;
import static game.and.map.GameType.CHEST;
import static game.and.map.GameType.ESCALIER;
import static game.and.map.GameType.MONSTER;
import static game.and.map.GameType.PLAYER;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import GUI.InGameController;
import GUI.MySceneFactory;
import entity.Item;
import entity.Joueur;
import entity.Loot;
import entity.Monster;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import other.components.PlayerComponent;

/**
 * Classe principale du jeux qui permet sous ouverture et la gestion de toute
 * les méchanique principale
 * 
 * @author remi(principalement), gabriel
 *
 */

public class GameApp extends GameApplication {
	public static AnchorPane layout; // Instanciation de la Fenettre du jeux
	public static String vJeu = "v4.0"; // Versiondu jeux

	public static final int BLOCK_SIZE = 80; // Taille d'un bloc, dans la grille

	public static final int MAP_SIZE_PRINT = 21; // Taille de la carte

	public static final int MAP_SIZE_REEL = 21; // Taille de la carte

	public static final int TIME_PER_LEVEL = 1; // durée d'une seconde en seconde

	public static int FLOOR = 0; // étage actuel

	public static boolean OnCollisionWithMonster = false; // permet de savoir si le joueur est en combat

	public static javafx.geometry.Point2D LAST_CLICK = new javafx.geometry.Point2D(0.0, 0.0);

	// Instanciation de Variable essentiel au fonctionnement du jeux

	public static Entity PlayerCombat; // Création d'une variable qui permet de récuperer le joueur qui combat
	public static Entity CurrentBattle; // Création d'une varialble qui permet de récuperer le monstre qui combat
	public static int DegatSubit; // Création d'une variable qui permet de savoir les dernier dégat subit par le
									// monstre
	public static Viewport viewport; // Création de la fenettre
	public static boolean chestOpened = false; // Permet de savoir si le coffre a déja été ouvert
	public static Entity CurentEntityOnClic; // Permet de récuperé la dernier entité sur lequel le joueur a cliqué
	public static ArrayList<Item> LootItemsOfMonster; // permet de stoquer les items looter par le monster
	public static ArrayList<Integer> QuantityLootItemsOfMonster; // permet de stoquer la quantité d'item que le monstre
																	// a looter

	public static Entity getPlayer() {
		return getGameWorld().getSingleton(PLAYER); // Fonction qui permet de recuperer l'entité Joueur
	}

	public PlayerComponent getPlayerComponent() {
		return getPlayer().getComponent(PlayerComponent.class); // Permet de récuperer l'entité de gestion des mouvement
																// du joueur
	}

	@Override
	protected void initSettings(GameSettings settings) {
		settings.setWidth(1280); // Insantiation de la Largeur
		settings.setHeight(900); // Instanciation de la Hauteur
		settings.setTitle("MAPFX"); // Nom de la fenettre du jeu
		settings.setVersion("1.0"); // Version du Jeu
		settings.setFullScreenAllowed(true); // Autoriser ou non a l'utilisateur le fais de mettre le jeux en plein
												// Ecran
		settings.setTitle("Slime Conqueror"); // Affiche le titre du jeux au dessus de la fenetre
		settings.setVersion(vJeu); // A

		settings.setManualResizeEnabled(true); // Autoriser le changement de taille de la fenettre manuellement
		settings.setPreserveResizeRatio(true); // Garder le ratio du jeux ou autoriser l'utilisateur a le modifier
												// manuellement depuis la fenettre
		settings.setSceneFactory(new MySceneFactory()); // Création d'une Scène factory qui permet de switch d'une sous
														// fenetre a l'autre dans la même fenetre (Fenetre du menu, le
														// jeux, fenetre du menu pose)
		settings.setMainMenuEnabled(true); // Activer le menu principal
		settings.setGameMenuEnabled(true); // Activer le menu du jeux

	}

	// Fonction qui permet d'afficher l'interface dans la fenetre
	@Override
	protected void initUI() {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/GUI/UIJeu.fxml"));

		try {
			layout = loader.<AnchorPane>load();
			getGameScene().addChild(layout);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Gestion de la lecture des touche lorsque elle sont préssé

	@Override
	protected void initInput() {
		Input input = getInput();

		// Lorsque l'utilisateur appuis sur la touche H cela action ou désactive le GUI
		// (Affichage des stat et autre composant sur la fenetre)
		getInput().addAction(new UserAction("HideGUI") {
			@Override
			protected void onActionBegin() {

				if (layout.getScene() != null) {

					getGameScene().removeChild(layout);

				} else {
					getGameScene().addChild(layout);
				}

			}
		}, KeyCode.H);

		// Lorsque le joueur Appuis sur la touche Z cela lui permet d'avance vers le
		// haut
		getInput().addAction(new UserAction("Up") {
			@Override
			protected void onAction() {

				getPlayerComponent().up();

			}
		}, KeyCode.Z);

		// Lorsque le joueur Appuis sur la touche S cela lui permet de recule vers le
		// bas
		getInput().addAction(new UserAction("Down") {
			@Override
			protected void onAction() {

				getPlayerComponent().down();

			}
		}, KeyCode.S);

		// Lorsque le joueur Appuis sur la touche Q cela lui permet d'aller vers la
		// droite
		getInput().addAction(new UserAction("Left") {
			@Override
			protected void onAction() {

				getPlayerComponent().left();

			}
		}, KeyCode.Q);

		// Lorsque le joueur Appuis sur la touche D cela lui permet d'aller vers la
		// droite
		getInput().addAction(new UserAction("Right") {
			@Override
			protected void onAction() {

				getPlayerComponent().right();

			}
		}, KeyCode.D);

		// Lorsque le joueur Appuis sur le clic gauche de sa souris cela lui permet
		// d'attaque l'entité (si il y en a une de présente) sur la case où il a cliquer
		getInput().addAction(new UserAction("AttackPos") {
			@Override
			protected void onActionBegin() {

				LAST_CLICK = input.getMousePositionWorld(); // Donne a LAST_Clic la position de la souris dans le monde
															// du jeux au moment ou le joueur a cliquer sur le bouton
															// gauche de la souris

				int x = (int) LAST_CLICK.getX(); // donne a x la position en x du dernier clic
				int y = (int) LAST_CLICK.getY(); // donne a y la position en x du dernier clic

				int nbr = getGameWorld().getEntitiesByType(MONSTER).size(); // donne a nbr le nombre de monstre présent
																			// dans la carte actuelle du monde du jeux

				int nbrChest = getGameWorld().getEntitiesByType(CHEST).size(); // donne a nbrChest le nombre de coffre
																				// présent dans la carte actuelle du
																				// monde du jeux

				set("nbrMob", nbr); // Sauvegarde nbr pour le récuperer dans d'autre partie du code dans une
									// supervariable propre a fxgl

				for (int i = 0; i < nbr; i++) {

					nbr = geti("nbrMob"); // Récupere le nombre de monstre présente au cas où il est changer entre temps

					Entity CurentEntityOnClic = getGameWorld().getEntitiesByType(MONSTER).get(i); // Recupere le monstre
																									// i parmis tout les
																									// monstre présent
																									// sur la carte

					int xe = (int) CurentEntityOnClic.getX(); // recupere la position en X du monstre i
					int ye = (int) CurentEntityOnClic.getY(); // recupere la position en Y du monstre i

					if (xe / 80 == x / 80 && ye / 80 == y / 80) { // Si le monstre ce trouve bien dans la clase où le
																	// joueur est clique

						if (CurentEntityOnClic.getType() == MONSTER) { // On verifie qu'il est bien un monstre

							Joueur P1 = getPlayer().getProperties().getValue("Joueur1"); // On récupère L'entité Joueur

							// Récupération des coordonée du Joueur et du monstre
							int P1X = (int) (getPlayer().getX() / BLOCK_SIZE);
							int P1Y = (int) (getPlayer().getY() / BLOCK_SIZE);
							int M1X = (int) (CurentEntityOnClic.getX() / BLOCK_SIZE);
							int M1Y = (int) (CurentEntityOnClic.getY() / BLOCK_SIZE);

							// Verification que le monstre ce trouve dans une zone que le joueur peu
							// atteindre phyisuquement (Anticheat et permet en même temps de définir une
							// porté a l'arme du joueur
							boolean PosEgal = (P1X == M1X && P1Y == M1Y);
							boolean PosXP1 = (P1X + 1 == M1X && P1Y == M1Y);
							boolean PosXM1 = (P1X - 1 == M1X && P1Y == M1Y);
							boolean PosYP1 = (P1X == M1X && P1Y + 1 == M1Y);
							boolean PosYM1 = (P1X == M1X && P1Y - 1 == M1Y);
							boolean PosXYP1 = (P1X + 1 == M1X && P1Y + 1 == M1Y);
							boolean PosXYM1 = (P1X - 1 == M1X && P1Y - 1 == M1Y);
							boolean PosXPYM1 = (P1X + 1 == M1X && P1Y - 1 == M1Y);
							boolean PosXMYP1 = (P1X - 1 == M1X && P1Y + 1 == M1Y);
							if (PosEgal || PosXP1 || PosXM1 || PosYP1 || PosYM1 || PosXYP1 || PosXYM1 || PosXPYM1
									|| PosXMYP1) {

								PlayerAttack(getPlayer(), CurentEntityOnClic);

							}
						}
					}
					nbr = geti("nbrMob"); // Nouvelle vérification du nombre de monstre pour eviter que si le monstre ce
											// fasse tuer cela ne cose des bug
				}

				for (int i = 0; i < nbrChest; i++) {

					CurentEntityOnClic = getGameWorld().getEntitiesByType(CHEST).get(i); // Récupération de l'entité
																							// Chest i

					int xe = (int) CurentEntityOnClic.getX(); // recupere la position en X du coffre i
					int ye = (int) CurentEntityOnClic.getY(); // recupere la position en y du coffre i

					if (xe / 80 == x / 80 && ye / 80 == y / 80) { // Vérification que le coffre i ce trouve bien sur le
																	// clic
						if (CurentEntityOnClic.getType() == CHEST) { // Véficication du type du coffre

							{
								chestOpened = true; // On notifie le jeux que un coffre est ouvert

								Loot LootInTheChest = CurentEntityOnClic.getProperties().getValue("LootOnChest"); // Récuperation
																													// de
																													// l'entité
																													// loot
																													// associé
																													// au
																													// coffre
																													// lors
																													// de
																													// sa
																													// création
								int LootGold = CurentEntityOnClic.getProperties().getValue("LootGold"); // Récuperation
																										// de la valeur
																										// en argent du
																										// monstre
								CurentEntityOnClic.removeFromWorld(); // destuction de l'entité du coffre dans le jeux
								nbrChest--; // suprétion d'une unité dans le compte des coffres

								AStarCell c = new AStarCell((int) (xe / 80), (int) (ye / 80), CellState.WALKABLE); // Redonne
																													// la
																													// permition
																													// au
																													// autre
																													// entité
																													// d'aller
																													// dans
																													// la
																													// case
																													// où
																													// ce
																													// trouver
																													// le
																													// coffre

								AStarGrid grid = geto("grid"); // recuperation de la grille contenant les case du jeux
								grid.set(xe / 80, ye / 80, c); // Mise a jour de la case modifier dans la grille

								set("grid", grid); // Mise a jour de la grille dans le jeux

								LootInTheChest.AutoGenerateLootMessage(); // Géneration du message de loot dans l'entité
																			// loot

								LootItemsOfMonster = LootInTheChest.getListOfItemsLoot(); // Récupère l'arraylist
																							// contenant les items du
																							// loot du monstre
								QuantityLootItemsOfMonster = LootInTheChest.getListOfQuantityItemsLoot(); // Récupère
																											// l'arraylist
																											// contenant
																											// la
																											// quantité
																											// items du
																											// loot du
																											// monstre

								Joueur P1 = getPlayer().getProperties().getValue("Joueur1"); // récupère l'entité Joueur

								// Pour chaque Item on va l'ajoute dans la bonne quantité a l'inventaire du
								// joueur
								for (Item I : LootItemsOfMonster) {
									int pos = LootItemsOfMonster.indexOf(I);
									P1.addItems(I, QuantityLootItemsOfMonster.get(pos));
								}
								P1.showInventory(); // Affiche l'inventaire du joueur dans la console dev

								P1.getPlayerMoney().addMoney((double) LootGold); // Ajoute au Joueur la quantité d'or
																					// qu'il a gagne en ouvrant le loot
								println("Argent de " + P1.getNom() + " : "
										+ (int) (P1.getPlayerMoney().getMoneyOnPlayer()) + " gold"); // Affiche dans la
																										// console dev
																										// l'argent que
																										// le joueur
																										// possède
							}

						}
					}
				}

			}
		}, MouseButton.PRIMARY);

	}

	public static void println(String T) {
		System.out.println(T);
	}

	// Gestion de la physique propre au jeux
	protected void initPhysics() {
		onCollision(PLAYER, ESCALIER, (p, e) -> { // Si un Joueur entre en contact avec un escalier il seras téléporte
													// au prochain niveau
			int nbr = geti("nbrMob");
			if (nbr <= 0) {
				Joueur J = FXGL.getGameWorld().getSingleton(GameType.PLAYER).getProperties().getValue("Joueur1");
				// Gestion du passage d'un étage a l'autre et sauvegarde des donnée du joueur
				// dans la base de données automatique a chaque passage d'etage
				try {
					Connection db = (Connection) DriverManager.getConnection(
							"jdbc:mysql://localhost/projetpoagl?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
							"root", "root");

					Statement demandeRequete = db.createStatement();
					demandeRequete.executeUpdate("UPDATE `projetpoagl`.`stats`  " + "SET `maxHP` = '"
							+ J.getStat().getMaxHP() + "'" + ", `currentHP` ='" + J.getStat().getCurrentHP() + "'"
							+ ", `maxATK`= '" + J.getStat().getMaxATK() + "'" + ", `currentATK`= '"
							+ J.getStat().getCurrentATK() + "'" + ", `maxDEF`= '" + J.getStat().getMaxDEF() + "'"
							+ ", `currentDEF`= '" + J.getStat().getCurrentDEF() + "'" + ", `maxMP`= '"
							+ J.getStat().getMaxMP() + "'" + ", `currentMP`= '" + J.getStat().getCurrentMP() + "'"
							+ ", `maxSPA`= '" + J.getStat().getMaxSPA() + "'" + ", `currentSPA`= '"
							+ J.getStat().getCurrentSPA() + "'" + ", `maxSPD`= '" + J.getStat().getMaxSPD() + "'"
							+ ", `currentSPD`= '" + J.getStat().getCurrentSPD() + "'" + ", `maxSPE`= '"
							+ J.getStat().getMaxSPE() + "'" + ", `currentSPE`= '" + J.getStat().getCurrentSPE() + "'"
							+ "WHERE `stats`.`idJoueur` = 1");

					demandeRequete.executeUpdate("UPDATE `projetpoagl`.`lvl`" + "SET `level` = '"
							+ J.getLv().getNiveau() + "'" + ",`totalXP`='" + J.getLv().getTotalXP() + "'"
							+ ",`currentXP`= '" + J.getLv().getCurrentXPforLV() + "'" + ",`xpNeeded`= '"
							+ J.getLv().getXPneedForNextLV() + "'" + "WHERE `lvl`.`idJoueur` = 1");

					demandeRequete.executeUpdate("UPDATE `projetpoagl`.`joueur`" + "SET `pointBonusJoueur` = '"
							+ InGameController.pointsBonus + "'" + "WHERE `joueur`.`idjoueur` = 1");

					if (FLOOR == 2) {
						getDialogService().showMessageBox("BRAVO, Vous avez fini le jeu!!!", getGameController()::exit); // Si
																															// le
																															// dernier
																															// niveau
																															// existant
																															// est
																															// attein
																															// on
																															// félicite
																															// le
																															// joueur
																															// en
																															// lui
																															// disant
																															// qu'il
																															// a
																															// terminer
																															// le
																															// jeux
					} else {
						FLOOR = getPlayerComponent().teleport(++FLOOR, BLOCK_SIZE, MAP_SIZE_PRINT); // Si il existe un
																									// niveau suivant le
																									// joueur seras
																									// directement
																									// téléporte au
																									// prochain lv
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
	}

	@Override

	// Gestion des variable propre au jeux, la seule nécéssaire a son fonctionnement
	// a la gestion du temps
	protected void initGameVars(Map<String, Object> vars) {
		vars.put("time", TIME_PER_LEVEL);
	}

	// Fonction d'attaque propre au joueur lorsqu'il attaque un monstre
	public void PlayerAttack(Entity P, Entity Mo) {
		Joueur J = P.getProperties().getValue("Joueur1"); // Récuperation de l'entité joueur
		Monster M = Mo.getProperties().getValue("Mosnter1"); // Récuperation de l'entité monstre

		int MX = (int) Mo.getX(); // Récuperation de la position en X du monstre
		int MY = (int) Mo.getY(); // Récuperation de la position en Y du monstre

		int PuissanceAtk = (int) (J.getStat().getCurrentATK()); // Calcul de la puissance d'attaque du joueur a
																// l'instant où il attaque
		DegatSubit = (int) (PuissanceAtk - ((M.getStat().getCurrentDEF()) / 2)); // Calcul des degat de le monstre
																					// subiras

		if (DegatSubit <= 0) { // Verification que les dégat du monstre ne sont pas égal ou inférieur a Zero
								// (Cas qui peur arriver si le monstre a une défense trop élevé), si cela arrive
								// le monstre ne subiras que 1 dégat
			DegatSubit = 1;
		} else {
			M.getStat().setCurrentHP(M.getStat().getCurrentHP() - (DegatSubit)); // Le monstre pert un nombre de point
																					// de vie égal au dégat subit

		}

		if (M.getStat().getCurrentHP() <= 0) { // Si la vie du monstre est égale ou inférieur a 0 après l'attaque
			Loot LootOfMonster = Mo.getProperties().getValue("LootOfMonster"); // on récupère le loot du monstre dans
																				// une variable
			int LootGold = Mo.getProperties().getValue("LootGold"); // on récupère l'or que le monstre vaut dans une
																	// variable
			Mo.removeFromWorld(); // Supréssion de l'entité montre dans le jeux
			J.getLv().addXP(M.getDroppedXP()); // Ajout de l'xp que le monstre vallais au joueur
			CurrentBattle = null; // Indication au jeux que le combat actuel est fini

			AStarCell c = new AStarCell((int) (MX / 80), (int) (MY / 80), CellState.NOT_WALKABLE); // Mise en place
																									// d'une sécurité
																									// sur la zone où le
																									// monstre est mort
																									// pour qu'aucune
																									// entité ne puisse
																									// aller directement
																									// dessus

			MX = (int) (MX - (MX % 80)); // récuperation des ancitenne coordonée en X du monstre et convertion pour le
											// coffre
			MY = (int) (MY - (MY % 80)); // récuperation des ancitenne coordonée en Y du monstre et convertion pour le
											// coffre
			Entity CC = spawn("C", MX, MY); // Création d'une entité coffre que l'ont fait spawn au coordonée du monstre
			CC.getProperties().setValue("LootOnChest", LootOfMonster); // Instanciation d'une propriété associer au
																		// coffre pour lui associer le loot du monstre
			CC.getProperties().setValue("LootGold", LootGold); // Instanciation d'une propriété associer au coffre pour
																// lui associer le loot du monstre
			MX = (int) (MX / BLOCK_SIZE); // Nouvelle adaption des coordonée en X pour la mise en place du coffre dans
											// la grille de jeux
			MY = (int) (MY / BLOCK_SIZE); // Nouvelle adaption des coordonée en Y pour la mise en place du coffre dans
											// la grille de jeux
			AStarGrid grid = geto("grid"); // Récupération de la grille des entité du jeux
			grid.set(MX, MY, c); // mise en place du coffre dans la grille des entité du jeux

			set("grid", grid); // Mise A jour de la grille dans le jeux

			int nbr = geti("nbrMob"); // Récupération du monbre de monstre
			nbr--; // Actualisation du nombre de monstre
			set("nbrMob", nbr); // Mise a jour du nombre de monstre

			int MPToWin = (int) (Math.random() * (3 - 1) + 1); // Génération d'un nombre aléatoire entre 1 et 3 de MP
																// que le joueur gagne en battant le monstre
			// Actualisation du nombre de MP du joueur par apport au nombre de MP gagner en
			// tuant le monstre
			if (J.getStat().getCurrentMP() + MPToWin >= J.getStat().getMaxMP()) {
				J.getStat().setCurrentMP(J.getStat().getMaxMP());
			} else {
				J.getStat().setCurrentMP(J.getStat().getCurrentMP() + MPToWin);
			}

		}

	}

	@Override
	protected void initGame() {
		getGameScene().setBackgroundColor(Color.GREY); // Generation de la couleur de fond de la carte

		getGameWorld().addEntityFactory(new GameFactory()); // Generation de la Classe permetant la création entité tel
															// que le joueur, les monstre, les mur etc .."

		boolean GridOfAllEntityOnWorld[][] = new boolean[MAP_SIZE_REEL][MAP_SIZE_REEL];

		Level level = getAssetLoader().loadLevel("map_level0.txt", new TextLevelLoader(80, 80, ' ')); // Conversion et
																										// génération de
																										// la carte au
																										// format du
																										// jeux

		getGameWorld().setLevel(level); // Instanciation de la carte dans le monde du jeux

		// Création d'une grille de restriction des déplacement si il y a des mur ou
		// endroit où le joueur ne peut pas aller
		AStarGrid grid = AStarGrid.fromWorld(getGameWorld(), MAP_SIZE_REEL, MAP_SIZE_REEL, BLOCK_SIZE, BLOCK_SIZE,
				(type) -> { // Instanciation de la grille
					if (type == BLOCK)
						return CellState.NOT_WALKABLE;

					return CellState.WALKABLE;
				});

		ArrayList<Entity> AllEntity = getGameWorld().getEntities(); // Création d'une liste comprenant toute les entité

		// Création d'une 2nd grille de restriction de mouvement comprenant les entité
		for (Entity E : AllEntity) {

			if (E.getType() == GameType.VIDE) {
				GridOfAllEntityOnWorld[(int) E.getX() / 80][(int) E.getY() / 80] = false;
			} else {
				GridOfAllEntityOnWorld[(int) E.getX() / 80][(int) E.getY() / 80] = true;
			}

		}

		set("GridOfAllEntityOnWorld", GridOfAllEntityOnWorld); // Création de la grille dans le jeux

		set("grid", grid); // Création de la grille dans le jeux

		PlayerCombat = null; // Instanciation de la valeur PlayerCombat a null

		PlayerCombat = getPlayer(); // Instanciation de la valeur PlayerCombat

		int nbr = getGameWorld().getEntitiesByType(MONSTER).size(); // Récupération du nombre de monstre sur la carte
		set("nbrMob", nbr); // Mise a jour du nombre de monstre sur la carte

		Viewport viewport = getGameScene().getViewport(); // Mise A jour du viewport
		viewport.bindToEntity(getGameWorld().getSingleton(PLAYER), getAppWidth() / 2, getAppHeight() / 2); // Gestion de
																											// la vue
																											// pour que
																											// elle sois
																											// toujours
																											// centrer
																											// par
																											// apport au
																											// joueur si
																											// il ce
																											// déplace
																											// dans les
																											// grande
																											// map

		viewport.setBounds(0, 0, MAP_SIZE_REEL * BLOCK_SIZE, MAP_SIZE_REEL * BLOCK_SIZE); // Gestion de la taille
																							// maximale de la map par
																							// apport a la vue

	}

	public static void main(String[] args) {
		launch(args);

	}
}
