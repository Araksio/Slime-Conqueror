package entity;
/**
 * dans cette classe sont instancier les différent objet du jeu
 * 
 * @author remi, gabriel
 */
public class Items {
	

	
	
	private static Rarity RH1 = new Rarity(1);   // Commune
	private static Rarity RH2 = new Rarity(2);   // Rare
	private static Rarity RH3 = new Rarity(3);   // Epique
	
	
	
	
	
	public static final ItemsLoot LezardTail = new ItemsLoot(148774586,1,6,"Queue de lezard",5,3000,3000,"Une queue d'un lezard du dungeon",RH1);
	public static final ItemsLoot BatWing = new ItemsLoot(148774587,1,7,"Aile de chauve-souris",5,3000,3000,"Une aile de chauve-souris du dungeon",RH1);
	public static final ItemsLoot FoxTail = new ItemsLoot(148774588,1,8,"Queue de renard",5,3000,3000,"Une queue d'un renard du dungeon",RH1);
	public static final ItemsLoot DragonHead = new ItemsLoot(148774589,1,9,"Dragon Head",5,3000,3000,"Tête de dragon, ce revend très cher au marché",RH3);
	
	
	
	

}
