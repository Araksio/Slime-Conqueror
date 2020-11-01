package entity;

public class Items {
	
	// Important : Format des Ajout d'items pr�definit et de facon g�nerale
	// ItemsArmes Nom_Unique = new ItemsArme(UniqueID (d�finir l'ID de l'objet en particulier dans la BDD),ID (ID de l'object de facon g�n�rique),IventoryID (id de l'objet dans le sous inventaire),"Nom De L'objet" (sert juste pour l'affichage),TypeDeL'objet (Inutile dans l'instanciation car elle est faite autolatiquement entre les classe m�re et fille),CurrentDurability (Durabilit� actuelle de l'objet mettre 1 pour les item a usage unique), MaxDurability (Durabilit� maximal de l'objet), Rarity (Niveau de raret� de l'objet, pour en mettre une pr�d�fini utiliser les RH1 a RH11 sinon mettre : new Rarity() pour mettre une raret� al�atoire)))
	
	
	private static Rarity RH1 = new Rarity(1);   // Basique
	private static Rarity RH2 = new Rarity(2);   // Commune
	private static Rarity RH3 = new Rarity(3);   // Peu Commune
	private static Rarity RH4 = new Rarity(4);   // Rare
	private static Rarity RH5 = new Rarity(5);   // Super Rare
	private static Rarity RH6 = new Rarity(6);   // Epique
	private static Rarity RH7 = new Rarity(7);   // Ultra Rare
	private static Rarity RH8 = new Rarity(8);   // L�gendaire
	private static Rarity RH9 = new Rarity(9);   // Divine
	private static Rarity RH10 = new Rarity(10); // Demoniaque
	private static Rarity RH11 = new Rarity(11); // Rainbow
	
	
	public static final ItemsArmes Rainbow_Excalibur = new ItemsArmes(148774588,1,15,"Perfect Rainbow Excalibur SSSSS+",0,3000,3000,29500,RH11);
	public static final ItemsArmes Demoniac_Excalibur = new ItemsArmes(148774588,1,2,"Demoniaque Excalibur",0,3000,3000,8000,RH10);
	public static final ItemsArmes Divine_Excalibur = new ItemsArmes(148774588,1,3,"Divine Excalibur",0,3000,3000,5000,RH9);
	
	public static final ItemsArmes Exatest1 = new ItemsArmes(148774588,1,6,"Exatest1",1,3000,3000,5000,new Rarity());
	public static final ItemsArmes Exatest2 = new ItemsArmes(148774588,1,7,"Exatest2",1,3000,3000,5000,new Rarity());
	
	
	
	

}
