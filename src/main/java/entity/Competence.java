package entity;


/**
 * La classe compétence comporte les diférentes varibles (cooldown,cost...) importante pour leur fonctionement en jeu
 * 
 * @author rémi(principalement),gabriel
 *
 */

public class Competence {
	
	private String name; // nom de la compétence
	private double cooldown; // temps avant de pouvoir utiliser de nouveau la compétence
	private int cost; // coût en mana de l'utilisation de la competence
	private String description; // décrit l'effet de la compétence
	private long next;
	

	/////////////////////////// constructeur ///////////////////////////
	public Competence(String _name, double _cooldown, int _cost, String _description)
	{
		name = _name;
		cooldown = _cooldown;
		cost = _cost;
		description = _description;
	}
	
	
	/////////////////////////// getters and setters ///////////////////////////
	public static void println(String T)
	{
		System.out.println(T);
	}
	
	public String getNom()
	{
		return this.name;
	}
	
	public double getCooldown()
	{
		return this.cooldown;
	}
	
	public int getCost()
	{
		return this.cost;
	}
	
	public long getNext()
	{
		return this.next;
	}
	
	public void setNom(String _Nom)
	{
		this.name = _Nom;
	}
	
	public void setCooldown(double _Cooldown)
	{
		this.cooldown = _Cooldown;
	}
	
	public void setCost(int _Cost)
	{
		this.cost = _Cost;
	}
	
	public void setNext(long _Next)
	{
		this.next = _Next;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	/**
	 * permet de savoir si la compétence est disponible ou non
	 * @return booléan
	 * @author Rémi
	 */
	
	public boolean getCoolDownIsOver()
	{
		if(System.currentTimeMillis() > this.next)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * met la compétence en delais de recupération
	 * 
	 * @author Rémi
	 */
	
	public void UseCompetence()
	{
		long NextUse = (long) (System.currentTimeMillis() + (this.cooldown * 1000));
		this.setNext(NextUse);
	}

	

}
